package com.songshi.suishou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import songshi.suishou.gamesTab.GuessSing;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GamesFragment extends Fragment implements OnItemClickListener {

	private GridView gridView;
	private int[] GamesIcon = { R.drawable.games1, R.drawable.games2,
			R.drawable.games3, R.drawable.games4, R.drawable.games5,
			R.drawable.games6, };
	private String[] GamesName = { "GamesItem1", "GamesItem2", "GamesItem3",
			"GamesItem4", "GamesItem5", "GamesItem6" };
	private List<Map<String, Object>> GamesDataList;
	private SimpleAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.games_tab, container, false);

		gridView = (GridView) view.findViewById(R.id.gamesGridView);
		GamesDataList = new ArrayList<Map<String, Object>>();
		adapter = new SimpleAdapter(getActivity(), getGamesData(),
				R.layout.games_tab_item, new String[] { "GamesImage",
						"GamesName" }, new int[] { R.id.gamesImageViewItem,
						R.id.gamesName });
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
		return view;
	}

	private List<? extends Map<String, ?>> getGamesData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < GamesIcon.length; i++) {
			Map<String, Object> GamesMap = new HashMap<String, Object>();
			GamesMap.put("GamesImage", GamesIcon[i]);
			GamesMap.put("GamesName", GamesName[i]);
			GamesDataList.add(GamesMap);
		}
		return GamesDataList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			Toast.makeText(getActivity(), "欢迎来到" + GamesName[position],
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getActivity(), GuessSing.class);
			startActivity(intent);
			break;
		case 1:
			Toast.makeText(getActivity(), "欢迎来到" + GamesName[position],
					Toast.LENGTH_SHORT).show();
			
			break;
		case 2:
			Toast.makeText(getActivity(), "欢迎来到" + GamesName[position],
					Toast.LENGTH_SHORT).show();
			
			break;
		case 3:
			Toast.makeText(getActivity(), "欢迎来到" + GamesName[position],
					Toast.LENGTH_SHORT).show();
			
			break;
		case 4:
			Toast.makeText(getActivity(), "欢迎来到" + GamesName[position],
					Toast.LENGTH_SHORT).show();
			
			break;
		case 5:
			Toast.makeText(getActivity(), "欢迎来到" + GamesName[position],
					Toast.LENGTH_SHORT).show();
			
			break;
		}
	}
}
