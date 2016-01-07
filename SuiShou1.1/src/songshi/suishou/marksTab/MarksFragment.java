package songshi.suishou.marksTab;

import com.songshi.suishou.R;
import com.songshi.suishou.R.id;
import com.songshi.suishou.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MarksFragment extends Fragment {

	// ��̬��ӱ�ǩ���ݡ������������ݿ��ȡString
	private String[] mValues = new String[] { "�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����" ,"�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����","�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����","�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����","�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����","�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����","�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����","�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����","�ܺ�", "�ǳ���", "Very Nice",
			"��������", "һ��", "�ջ�",
			"��", "������ɫ����", "������",
			" ����"};   //Ϊ�˲���ScrollView�Ĺ���Ч��
	private FlowLayoutMarks mFlowLayoutMarks;

	private ScrollView mScrollView;
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// ������onCreate�����л�ȡ�ؼ�����Ϊfragment��û��start��������onStart�����л�ȡ��
		mFlowLayoutMarks = (FlowLayoutMarks) getActivity().findViewById(
				R.id.flowLayoutMarks);
		initData();
		
		mScrollView=(ScrollView) getActivity().findViewById(R.id.mark_scrollView);
		
		initEvent();
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		mScrollView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				/*case MotionEvent.ACTION_UP:

					break;

				case MotionEvent.ACTION_DOWN:

					break;*/
				case MotionEvent.ACTION_MOVE:
					//�ж��Ƿ񻬶����ײ�
					if(mScrollView.getChildAt(0).getMeasuredHeight()<=mScrollView.getHeight()+mScrollView.getScrollY()){
						Toast.makeText(getActivity(), "�Ѿ����ײ��ˣ�ȥ��������㼣��", Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.marks_tab, container, false);

	}

	//new��ǩ�����ֱ�ǩ
	public void initData() {
		//��Button��Ϊ��ǩ
		/*for (int i = 0; i < mValues.length; i++) {
			Button btn = new Button(getActivity());
			MarginLayoutParams lp = new MarginLayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			btn.setText(mValues[i]);
			mFlowLayoutMarks.addView(btn, lp);
		}*/
		
		//��TextView��Ϊ��ǩ
		LayoutInflater minflater=LayoutInflater.from(getActivity());
		for (int i = 0; i < mValues.length; i++) {
			TextView tv=(TextView) minflater.inflate(R.layout.tv, mFlowLayoutMarks, false);
			tv.setText(mValues[i]);
			mFlowLayoutMarks.addView(tv);
		}
	}
}
