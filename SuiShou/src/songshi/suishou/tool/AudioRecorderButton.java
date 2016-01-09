package songshi.suishou.tool;

import songshi.suishou.tool.AudioManage.AudioStateListenter;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.songshi.suishou.R;

public class AudioRecorderButton extends Button implements AudioStateListenter {

	private static final int STATE_NORMAL = 1;
	private static final int STATE_RECORDERING = 2;
	private static final int STATE_WANT_TO_CALCEL = 3;

	private int mCurState = STATE_NORMAL;
	private boolean isRecordering = false; // �Ѿ���ʼ¼��
	private boolean mReady; // �Ƿ񴥷�onClick

	private static final int DISTANCE_Y_CANCEL = 50;

	private AudioDialogManage audioDialogManage;

	private AudioManage mAudioManage;

	public AudioRecorderButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		audioDialogManage = new AudioDialogManage(getContext());

		String dir = Environment.getExternalStorageDirectory()
				+ "/suishou_recorder_audios";// �˴���Ҫ�ж��Ƿ��д洢��
		mAudioManage = AudioManage.getInstance(dir);
		mAudioManage.setOnAudioStateListenter(this);

		setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				mReady = true;
				// ������ʾӦ����audio end prepared�Ժ�
				mAudioManage.prepareAudio();
				return true;
			}
		});
		// TODO Auto-generated constructor stub
	}

	//¼����ɺ�Ļص�
	public interface AudioFinishRecorderListenter{
		void onFinish(float seconds,String FilePath);
	}
	
	private AudioFinishRecorderListenter mListenter;
	
	public void setAudioFinishRecorderListenter(AudioFinishRecorderListenter listenter){
		this.mListenter=listenter;
	}
	
	public AudioRecorderButton(Context context) {
		super(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (action) {

		case MotionEvent.ACTION_DOWN:
			changeState(STATE_RECORDERING);
			break;

		case MotionEvent.ACTION_MOVE:

			// ����X��Y�����꣬�ж��Ƿ���Ҫȡ��
			if (isRecordering) {
				if (wantToCancel(x, y)) {
					changeState(STATE_WANT_TO_CALCEL);
				} else {
					changeState(STATE_RECORDERING);
				}
			}
			break;

		case MotionEvent.ACTION_UP:
			if (!mReady) {
				reset();
				return super.onTouchEvent(event);
			}

			if (!isRecordering || mTime < 0.5f) {
				audioDialogManage.tooShort();
				mAudioManage.cancel();
				mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);// �ӳ�
			} 
			/*else if (mTime < 0.5f) {
				audioDialogManage.tooShort();
				isRecordering=false;
				mAudioManage.cancel();
				mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);// �ӳ�
			}*/

			else if (mCurState == STATE_RECORDERING) { //����¼�ƽ���
				
				audioDialogManage.dimissDialog();
				// callbackToAct
				if(mListenter!=null){
					mListenter.onFinish(mTime, mAudioManage.getCurrentFilePath());
				}
				// release
				mAudioManage.release();
				
				
			} else if (mCurState == STATE_WANT_TO_CALCEL) {
				// cancel
				audioDialogManage.dimissDialog();
				mAudioManage.cancel();
			}

			reset();
			break;
		}
		return super.onTouchEvent(event);
	}

	// �ָ�״̬�Լ�һЩ��־λ
	private void reset() {
		// TODO Auto-generated method stub
		isRecordering = false;
		mReady = false;
		mTime = 0;
		changeState(STATE_NORMAL);
	}

	private boolean wantToCancel(int x, int y) {
		// TODO Auto-generated method stub
		// �ж���ָ�Ļ����Ƿ񳬳���Χ
		if (x < 0 || x > getWidth()) {
			return true;
		}
		if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {
			return true;
		}
		return false;
	}

	private void changeState(int state) {
		// TODO Auto-generated method stub
		if (mCurState != state) {
			mCurState = state;
			switch (state) {
			case STATE_NORMAL:
				setBackgroundResource(R.drawable.btn_recorder_normal);
				setText(R.string.str_recorder_normal);
				break;

			case STATE_RECORDERING:
				setBackgroundResource(R.drawable.btn_recorder_recordering);
				setText(R.string.str_recorder_recording);
				if (isRecordering) {
					// ����Dialog.recording()
					audioDialogManage.recording();
				}
				break;

			case STATE_WANT_TO_CALCEL:
				setBackgroundResource(R.drawable.btn_recorder_recordering);
				setText(R.string.str_recorder_want_cancel);
				// ����Dialog.wantCancel()
				audioDialogManage.wantToCancel();
				break;
			}
		}
	}

	private static final int MSG_AUDIO_PREPARED = 0x110;
	private static final int MSG_VOICE_CHANGE = 0x111;
	private static final int MSG_DIALOG_DIMISS = 0x112;

	private float mTime;

	// ��ȡ������С��Runnable
	private Runnable mGetVoiceLevelRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRecordering) {
				try {
					Thread.sleep(100);
					mTime += 0.1f;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mHandler.sendEmptyMessage(MSG_VOICE_CHANGE);
			}
		}
	};

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_AUDIO_PREPARED:
				audioDialogManage.showRecorderingDialog();
				isRecordering = true;

				new Thread(mGetVoiceLevelRunnable).start();
				break;

			case MSG_VOICE_CHANGE:
				audioDialogManage.updateVoiceLevel(mAudioManage
						.getVoiceLevel(7));
				break;

			case MSG_DIALOG_DIMISS:
				audioDialogManage.dimissDialog();
				break;
			}
		};
	};

	@Override
	public void wellPrepared() {
		// TODO Auto-generated method stub
		mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
	}
}
