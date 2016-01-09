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

	// 动态添加标签数据——从网络数据库获取String
	private String[] mValues = new String[] { "很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以" ,"很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以","很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以","很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以","很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以","很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以","很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以","很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以","很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活",
			"好", "界面颜色单调", "还不错",
			" 可以"};   //为了测试ScrollView的滚动效果
	private FlowLayoutMarks mFlowLayoutMarks;

	private ScrollView mScrollView;
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// 不能在onCreate函数中获取控件，因为fragment还没有start，可以在onStart函数中获取：
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
					//判断是否滑动到底部
					if(mScrollView.getChildAt(0).getMeasuredHeight()<=mScrollView.getHeight()+mScrollView.getScrollY()){
						Toast.makeText(getActivity(), "已经到底部了，去留下你的足迹吧", Toast.LENGTH_SHORT).show();
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

	//new标签，布局标签
	public void initData() {
		//用Button作为标签
		/*for (int i = 0; i < mValues.length; i++) {
			Button btn = new Button(getActivity());
			MarginLayoutParams lp = new MarginLayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			btn.setText(mValues[i]);
			mFlowLayoutMarks.addView(btn, lp);
		}*/
		
		//用TextView作为标签
		LayoutInflater minflater=LayoutInflater.from(getActivity());
		for (int i = 0; i < mValues.length; i++) {
			TextView tv=(TextView) minflater.inflate(R.layout.tv, mFlowLayoutMarks, false);
			tv.setText(mValues[i]);
			mFlowLayoutMarks.addView(tv);
		}
	}
}
