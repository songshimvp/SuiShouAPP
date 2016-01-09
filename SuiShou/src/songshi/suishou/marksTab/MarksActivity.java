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
	// 动态添加标签数据——从网络数据库获取String
	private String[] mValues = new String[] { "很好", "非常好", "Very Nice", "界面整洁",
			"一般", "凑活", "好", "界面颜色单调", "还不错", " 可以", "很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活", "好", "界面颜色单调", "还不错", " 可以", "很好", "非常好",
			"Very Nice", "界面整洁", "一般", "凑活", "好", "界面颜色单调", "还不错", " 可以", "很好",
			"非常好", "Very Nice", "界面整洁", "一般", "凑活", "好", "界面颜色单调", "还不错",
			" 可以", "很好", "非常好", "Very Nice", "界面整洁", "一般", "凑活", "好", "界面颜色单调",
			"还不错", " 可以", "很好", "非常好", "Very Nice", "界面整洁", "一般", "凑活", "好",
			"界面颜色单调", "还不错", " 可以", "很好", "非常好", "Very Nice", "界面整洁", "一般",
			"凑活", "好", "界面颜色单调", "还不错", " 可以", "很好", "非常好", "Very Nice",
			"界面整洁", "一般", "凑活", "好", "界面颜色单调", "还不错", " 可以", "很好", "非常好",
			"Very Nice", "界面整洁", "一般", "凑活", "好", "界面颜色单调", "还不错", " 可以" }; // 为了测试ScrollView的滚动效果
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
					// 判断是否滑动到底部
					if (mScrollView.getChildAt(0).getMeasuredHeight() <= mScrollView
							.getHeight() + mScrollView.getScrollY()) {
						Toast.makeText(MarksActivity.this, "已经到底部了，去留下你的足迹吧",
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

	// new标签，布局标签
	public void initData() {
		// 用Button作为标签
		/*
		 * for (int i = 0; i < mValues.length; i++) { Button btn = new
		 * Button(getActivity()); MarginLayoutParams lp = new
		 * MarginLayoutParams( LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT); btn.setText(mValues[i]);
		 * mFlowLayoutMarks.addView(btn, lp); }
		 */

		// 用TextView作为标签
		LayoutInflater minflater = LayoutInflater.from(this);
		for (int i = 0; i < mValues.length; i++) {
			TextView tv = (TextView) minflater.inflate(R.layout.tv,
					mFlowLayoutMarks, false);
			tv.setText(mValues[i]);
			mFlowLayoutMarks.addView(tv);
		}
	}
}
