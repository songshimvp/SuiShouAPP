package songshi.suishou.toolsTab;

import java.util.ArrayList;
import java.util.List;

import songshi.suishou.tool.AudioManage;
import songshi.suishou.tool.AudioRecorderAdapter;
import songshi.suishou.tool.AudioRecorderButton;
import songshi.suishou.tool.AudioRecorderButton.AudioFinishRecorderListenter;
import songshi.suishou.tool.MediaManage;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.songshi.suishou.R;

//AudioRecorderButton
//DialogManager
//AudioManager
public class VoiceNotes extends Activity {

	private ListView mListView;
	private ArrayAdapter<Recorder> mAdapter;
	private List<Recorder> mDatas = new ArrayList<VoiceNotes.Recorder>();

	private AudioRecorderButton mAudioRecorderButton;
	private View mAnimView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tools_voice_notes);

		mListView = (ListView) findViewById(R.id.voiceNotesListView);
		mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.recorderButton);
		mAudioRecorderButton
				.setAudioFinishRecorderListenter(new AudioFinishRecorderListenter() {

					@Override
					public void onFinish(float seconds, String FilePath) {
						// TODO Auto-generated method stub
						Recorder recorder = new Recorder(seconds, FilePath);
						mDatas.add(recorder);
						mAdapter.notifyDataSetChanged();
						mListView.setSelection(mDatas.size() - 1);
					}
				});

		mAdapter = new AudioRecorderAdapter(this, mDatas);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mAnimView != null) {
					mAnimView.setBackgroundResource(R.drawable.play_after);
					mAnimView = null;
				}
				// ���Ŷ���
				mAnimView = view.findViewById(R.id.recorder_anim);
				mAnimView.setBackgroundResource(R.drawable.audio_play_anim);
				AnimationDrawable anim = (AnimationDrawable) mAnimView
						.getBackground();
				anim.start();
				// ������Ƶ
				MediaManage.playSound(mDatas.get(position).filePath,
						new MediaPlayer.OnCompletionListener() {

							@Override
							public void onCompletion(MediaPlayer mp) {
								mAnimView.setBackgroundResource(R.drawable.play_after);

							}
						});

			}

		});
	}

	public class Recorder {
		float time;
		String filePath;

		public Recorder(float time, String filePath) {
			super();
			this.time = time;
			this.filePath = filePath;
		}

		public float getTime() {
			return time;
		}

		public void setTime(float time) {
			this.time = time;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MediaManage.pause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MediaManage.resume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MediaManage.release();
	}
}
