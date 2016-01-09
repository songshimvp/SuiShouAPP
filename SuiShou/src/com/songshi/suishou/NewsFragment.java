package com.songshi.suishou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import songshi.suishou.newsTab.LoadListView;
import songshi.suishou.newsTab.LoadListView.ILoadlistener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

import com.yalantis.phoenix.PullToRefreshView;

public class NewsFragment extends Fragment implements OnItemClickListener, ILoadlistener {

	private LoadListView listView;
	private int NewsIcon[] = { R.drawable.tools1, R.drawable.tools2,
			R.drawable.tools3, R.drawable.tools4, R.drawable.games1,
			R.drawable.games2, R.drawable.games3, R.drawable.games4,
			R.drawable.games5, R.drawable.games6 };
	private String NewsName[] = { "ToolsItem1", "ToolsItem2", "ToolsItem3",
			"ToolsItem4", "GamesItem1", "GamesItem2", "GamesItem3",
			"GamesItem4", "GamesItem5", "GamesItem6" };
	private SimpleAdapter adapter;
	private List<Map<String, Object>> NewsDataList;

	private PullToRefreshView mPullToRefreshView;
	public static final int REFRESH_DELAY = 2000;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.news_tab, container, false);
		listView = (LoadListView) view.findViewById(R.id.newsListView);
		listView.setLoadInterface(this);
		NewsDataList = new ArrayList<Map<String, Object>>();
		adapter = new SimpleAdapter(getActivity(), getNewsData(),
				R.layout.news_tab_item,
				new String[] { "NewsImage", "NewsName" }, new int[] {
						R.id.newsImageViewItem, R.id.newssName });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		//下拉刷新
		mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
		mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
		    @Override
		    public void onRefresh() {
		        mPullToRefreshView.postDelayed(new Runnable() {
		            @Override
		            public void run() {
		            	
		            	//获取最新数据
						getReflashNewsData();
						//通知界面显示刷新数据				
						adapter = new SimpleAdapter(getActivity(), NewsDataList,
								R.layout.news_tab_item,
								new String[] { "NewsImage", "NewsName" }, new int[] {
										R.id.newsImageViewItem, R.id.newssName });   //传入的是整个（新旧）NewsDataList
						listView.setAdapter(adapter);
		                mPullToRefreshView.setRefreshing(false);
		            }
		        }, REFRESH_DELAY);
		    }
		 });
		
		return view;
	}

	private List<? extends Map<String, ?>> getNewsData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < NewsIcon.length; i++) {
			Map<String, Object> GamesMap = new HashMap<String, Object>();
			GamesMap.put("NewsImage", NewsIcon[i]);
			GamesMap.put("NewsName", NewsName[i]);
			NewsDataList.add(GamesMap);
		}
		return NewsDataList;
	}

	// 获取下拉刷新数据
	private List<? extends Map<String, ?>> getReflashNewsData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; i++) {
			Map<String, Object> GamesMap = new HashMap<String, Object>();
			GamesMap.put("NewsImage", NewsIcon[i]);
			GamesMap.put("NewsName", NewsName[i] + "刷新数据" + i);
			NewsDataList.add(0, GamesMap);
		}
		return NewsDataList;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ListView的点击事件
		
	}

	// 获取LoadMore数据
			private List<? extends Map<String, ?>> getLoadMoreNewsData() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 2; i++) {
					Map<String, Object> GamesMap = new HashMap<String, Object>();
					GamesMap.put("NewsImage", NewsIcon[i]);
					GamesMap.put("NewsName", NewsName[i] + "加载更多数据" + i);
					NewsDataList.add(GamesMap);
				}
				return NewsDataList;
			}
			
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		Handler handler=new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//获取更多数据
				getLoadMoreNewsData();
				//更新ListView的显示
				adapter = new SimpleAdapter(getActivity(), NewsDataList,
						R.layout.news_tab_item,
						new String[] { "NewsImage", "NewsName" }, new int[] {
								R.id.newsImageViewItem, R.id.newssName });   //传入的是整个（新旧）NewsDataList
				listView.setAdapter(adapter);
				//LiStView加载完毕
				listView.LoadComplete();
				
			}
		}, 3000);   //此处为了“模拟缓冲获取数据的效果”，设置3000毫秒的延时，实际应用中不必这么做！
			
	}
}
