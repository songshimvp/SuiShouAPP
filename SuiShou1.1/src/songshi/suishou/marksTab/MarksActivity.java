package songshi.suishou.marksTab;

import com.songshi.suishou.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MarksActivity extends Activity {
	// ��̬��ӱ�ǩ���ݡ������������ݿ��ȡString
	private String[] mValues = new String[] { "�ܺ�", "�ǳ���", "Very Nice", "��������",
			"һ��", "�ջ�", "��", "������ɫ����", "������", " ����", "�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�", "��", "������ɫ����", "������", " ����", "�ܺ�", "�ǳ���",
			"Very Nice", "��������", "һ��", "�ջ�", "��", "������ɫ����", "������", " ����", "�ܺ�",
			"�ǳ���", "Very Nice", "��������", "һ��", "�ջ�", "��", "������ɫ����", "������",
			" ����", "�ܺ�", "�ǳ���", "Very Nice", "��������", "һ��", "�ջ�", "��", "������ɫ����",
			"������", " ����", "�ܺ�", "�ǳ���", "Very Nice", "��������", "һ��", "�ջ�", "��",
			"������ɫ����", "������", " ����", "�ܺ�", "�ǳ���", "Very Nice", "��������", "һ��",
			"�ջ�", "��", "������ɫ����", "������", " ����", "�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�", "��", "������ɫ����", "������", " ����", "�ܺ�", "�ǳ���",
			"Very Nice", "��������", "һ��", "�ջ�", "��", "������ɫ����", "������", " ����" }; // Ϊ�˲���ScrollView�Ĺ���Ч��
	private FlowLayoutMarks mFlowLayoutMarks;

	private ScrollView mScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marks_tab);

		mFlowLayoutMarks = (FlowLayoutMarks) findViewById(R.id.flowLayoutMarks);
		initData();

		mScrollView = (ScrollView) findViewById(R.id.mark_scrollView);

		initEvent();
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		mScrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				/*
				 * case MotionEvent.ACTION_UP:
				 * 
				 * break;
				 * 
				 * case MotionEvent.ACTION_DOWN:
				 * 
				 * break;
				 */
				case MotionEvent.ACTION_MOVE:
					// �ж��Ƿ񻬶����ײ�
					if (mScrollView.getChildAt(0).getMeasuredHeight() <= mScrollView
							.getHeight() + mScrollView.getScrollY()) {
						Toast.makeText(MarksActivity.this, "�Ѿ����ײ��ˣ�ȥ��������㼣��",
								Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
	}

	// new��ǩ�����ֱ�ǩ
	public void initData() {
		// ��Button��Ϊ��ǩ
		/*
		 * for (int i = 0; i < mValues.length; i++) { Button btn = new
		 * Button(getActivity()); MarginLayoutParams lp = new
		 * MarginLayoutParams( LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT); btn.setText(mValues[i]);
		 * mFlowLayoutMarks.addView(btn, lp); }
		 */

		// ��TextView��Ϊ��ǩ
		LayoutInflater minflater = LayoutInflater.from(this);
		for (int i = 0; i < mValues.length; i++) {
			TextView tv = (TextView) minflater.inflate(R.layout.tv,
					mFlowLayoutMarks, false);
			tv.setText(mValues[i]);
			mFlowLayoutMarks.addView(tv);
		}
	}
}
