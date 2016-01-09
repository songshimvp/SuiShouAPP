package com.songshi.suishou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import songshi.suishou.toolsTab.Light;
import songshi.suishou.toolsTab.VoiceNotes;

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

public class ToolsFragment extends Fragment implements OnItemClickListener {

	private GridView gridView;
	private List<Map<String, Object>> toolsDatalist;

	// 图标
	private int[] iconTools = { R.drawable.voicenotes_icon, R.drawable.tools2,
			R.drawable.tools3, R.drawable.tools4 };
	// 名称
	private String[] nameTools = { "录音", "ToolsItem2", "ToolsItem3", "ToolsItem4" };

	private SimpleAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.tools_tab, container, false);
		gridView = (GridView) view.findViewById(R.id.toolsGridView);

		toolsDatalist = new ArrayList<Map<String, Object>>();
		// getToolsData();
		adapter = new SimpleAdapter(getActivity(), getToolsData(),
				R.layout.tools_tab_item, new String[] { "toolsImage",
						"toolsName" }, new int[] { R.id.toolsImageViewItem,
						R.id.toolsName });
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
		return view;
	}

	private List<Map<String, Object>> getToolsData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < iconTools.length; i++) {
			Map<String, Object> toolsMap = new HashMap<String, Object>();
			toolsMap.put("toolsImage", iconTools[i]);
			toolsMap.put("toolsName", nameTools[i]);
			toolsDatalist.add(toolsMap);
		}
		return toolsDatalist;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			Toast.makeText(getActivity(), "欢迎来到"+nameTools[position], Toast.LENGTH_SHORT).show();
			Intent VoiceNotesIntent=new Intent(getActivity(), VoiceNotes.class);
			startActivity(VoiceNotesIntent);
			break;
		case 1:
			Toast.makeText(getActivity(), "欢迎来到"+nameTools[position], Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(getActivity(), "欢迎来到"+nameTools[position], Toast.LENGTH_SHORT).show();
			Intent lightIntent=new Intent(getActivity(), Light.class);
			startActivity(lightIntent);
			break;
		case 3:
			Toast.makeText(getActivity(), "欢迎来到"+nameTools[position], Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
