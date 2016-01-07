package com.songshi.suishou;

import java.util.ArrayList;
import java.util.List;

import songshi.suishou.tool.BookSimpleAdapter;
import songshi.suishou.tool.DividerItemDecoration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BooksFragment extends Fragment {

	private View view;

	private RecyclerView mRecyclerView;
	private List<String> mDataslist;
	private BookSimpleAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.books_tab, container, false);
		
		initDatas();
		initViews();

		mAdapter = new BookSimpleAdapter(getActivity(), mDataslist);

		mRecyclerView.setAdapter(mAdapter);

		//设置RecyclerView的布局管理
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
				getActivity(), LinearLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		
		//设置RecyclerView的Item分割线
		mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
		return view;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mRecyclerView = (RecyclerView) view.findViewById(R.id.bookRecyclerView);
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		mDataslist = new ArrayList<String>();
		for (int i = 'A'; i <= 'z'; i++) {
			mDataslist.add("" + (char) i);
		}
	}
}
