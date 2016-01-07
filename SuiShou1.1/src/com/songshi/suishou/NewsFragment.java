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
		
		//����ˢ��
		mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
		mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
		    @Override
		    public void onRefresh() {
		        mPullToRefreshView.postDelayed(new Runnable() {
		            @Override
		            public void run() {
		            	
		            	//��ȡ��������
						getReflashNewsData();
						//֪ͨ������ʾˢ������				
						adapter = new SimpleAdapter(getActivity(), NewsDataList,
								R.layout.news_tab_item,
								new String[] { "NewsImage", "NewsName" }, new int[] {
										R.id.newsImageViewItem, R.id.newssName });   //��������������¾ɣ�NewsDataList
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

	// ��ȡ����ˢ������
	private List<? extends Map<String, ?>> getReflashNewsData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; i++) {
			Map<String, Object> GamesMap = new HashMap<String, Object>();
			GamesMap.put("NewsImage", NewsIcon[i]);
			GamesMap.put("NewsName", NewsName[i] + "ˢ������" + i);
			NewsDataList.add(0, GamesMap);
		}
		return NewsDataList;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ListView�ĵ���¼�
		
	}

	// ��ȡLoadMore����
			private List<? extends Map<String, ?>> getLoadMoreNewsData() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 2; i++) {
					Map<String, Object> GamesMap = new HashMap<String, Object>();
					GamesMap.put("NewsImage", NewsIcon[i]);
					GamesMap.put("NewsName", NewsName[i] + "���ظ�������" + i);
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
				//��ȡ��������
				getLoadMoreNewsData();
				//����ListView����ʾ
				adapter = new SimpleAdapter(getActivity(), NewsDataList,
						R.layout.news_tab_item,
						new String[] { "NewsImage", "NewsName" }, new int[] {
								R.id.newsImageViewItem, R.id.newssName });   //��������������¾ɣ�NewsDataList
				listView.setAdapter(adapter);
				//LiStView�������
				listView.LoadComplete();
				
			}
		}, 3000);   //�˴�Ϊ�ˡ�ģ�⻺���ȡ���ݵ�Ч����������3000�������ʱ��ʵ��Ӧ���в�����ô����
			
	}
}
