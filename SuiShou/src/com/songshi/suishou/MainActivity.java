package com.songshi.suishou;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import songshi.suishou.marksTab.MarksActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {

	private ViewPager mViewPager;

	/*
	 * private View mNewsView; private View mToolsView; private View mGamesView;
	 * private View mMarksView;
	 */

	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mTabsList = new ArrayList<Fragment>();

	private List<ChangeColorIconWithTextView> mSelfViews = new ArrayList<ChangeColorIconWithTextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		setOverflowButtonAlways();
		getActionBar().setDisplayShowHomeEnabled(false); // 设置不显示Icon

		initView();
		initEvent();
		initDatas();
		mViewPager.setAdapter(mAdapter);
	}

	// 初始化所有事件
	private void initEvent() {
		// TODO Auto-generated method stub
		mViewPager.setOnPageChangeListener(this);

		// 。。。。。。
	}

	private void initDatas() {

		// 四个Fragment作为数据源
		mTabsList = new ArrayList<Fragment>();
		Fragment mTabNewsFra = new NewsFragment();
		Fragment mTabBooksFra = new BooksFragment();
		//Fragment mTabMarksFra = new MarksFragment();
		Fragment mTabToolsFra = new ToolsFragment();
		Fragment mTabGamesFra = new GamesFragment();
		
		mTabsList.add(mTabNewsFra);
		mTabsList.add(mTabBooksFra);
		// mTabsList.add(mTabMarksFra);
		mTabsList.add(mTabToolsFra);
		mTabsList.add(mTabGamesFra);

		// 数据适配器
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mTabsList.size();
			}

			@Override
			public android.support.v4.app.Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mTabsList.get(arg0);
			}
		};
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		/*
		 * mNewsView=findViewById(R.id.newsView);
		 * mToolsView=findViewById(R.id.toolsView);
		 * mGamesView=findViewById(R.id.gamesView);
		 * mMarksView=findViewById(R.id.marksView);
		 */

		ChangeColorIconWithTextView newsSelfView = (ChangeColorIconWithTextView) findViewById(R.id.newsView);
		mSelfViews.add(newsSelfView);
		ChangeColorIconWithTextView marksSelfView = (ChangeColorIconWithTextView) findViewById(R.id.marksView);
		mSelfViews.add(marksSelfView);
		ChangeColorIconWithTextView toolsSelfView = (ChangeColorIconWithTextView) findViewById(R.id.toolsView);
		mSelfViews.add(toolsSelfView);
		ChangeColorIconWithTextView gamesSelfView = (ChangeColorIconWithTextView) findViewById(R.id.gamesView);
		mSelfViews.add(gamesSelfView);
		

		newsSelfView.setOnClickListener(this);
		toolsSelfView.setOnClickListener(this);
		gamesSelfView.setOnClickListener(this);
		marksSelfView.setOnClickListener(this);

		newsSelfView.setIconAlpha(1.0F); // 初始时第一项为选中（绿色），其他为无色
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_news:
			Toast.makeText(this, "点击了天下事指导", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_marks:
			Toast.makeText(this, "点击了书香屋指导", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_tools:
			Toast.makeText(this, "点击了工具箱指导", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_games:
			Toast.makeText(this, "点击了娱乐房指导", Toast.LENGTH_SHORT).show();
			break;
		

		case R.id.action_comment:
			Toast.makeText(this, "点击了雁过留痕", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(MainActivity.this, MarksActivity.class);
			startActivity(intent);
			break;
		case R.id.action_we:
			Toast.makeText(this, "点击了关于我们", Toast.LENGTH_SHORT).show();
			break;

		}

		return super.onOptionsItemSelected(item);
	}

	// 利用反射改变系统的行为，强制系统显示ActionBar的Overflow的图标
	private void setOverflowButtonAlways() {

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKey = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKey.setAccessible(true);
			menuKey.setBoolean(config, false); // 设置一直显示Overflow Button

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 设置menu显示Icon
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return super.onMenuOpened(featureId, menu);
	}

	// 随着点击，变换底部View的颜色，变换 “内容Fragment”
	@Override
	public void onClick(View v) {

		resetOtherTabs(); // 重置其他底部View的颜色

		switch (v.getId()) {
		case R.id.newsView:
			mSelfViews.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0, false);
			break;
			
		case R.id.marksView:
			mSelfViews.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1, false);
			break;
			
		case R.id.toolsView:
			mSelfViews.get(2).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(2, false);
			break;

		case R.id.gamesView:
			mSelfViews.get(3).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(3, false);
			break;

		
			
		}
	}

	// 重置其他底部View的颜色
	private void resetOtherTabs() {
		// TODO Auto-generated method stub
		for (int i = 0; i < mSelfViews.size(); i++) {
			mSelfViews.get(i).setIconAlpha(0);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		// Log.e("MainActivity", "position = " + position + ",positionOffset = "
		// + positionOffset);
		/*
		 * 观察LOG日志： 从第一页到第二页：position =0；positionOffset：0.0~1.0；
		 * 从第二页到第一页：position =0；positionOffset：1.0~0.0；
		 */
		if (positionOffset > 0) {
			ChangeColorIconWithTextView leftView = mSelfViews.get(position);
			ChangeColorIconWithTextView rightView = mSelfViews
					.get(position + 1);

			leftView.setIconAlpha(1 - positionOffset);
			// 利用这种标记左、右透明度的方法，实现了随着ViewPager中Fragment的滑动，底部View也同时变化
			rightView.setIconAlpha(positionOffset);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}
