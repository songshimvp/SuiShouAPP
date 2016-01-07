package songshi.suishou.newsTab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.songshi.suishou.R;

public class LoadListView extends ListView implements OnScrollListener {

	public LoadListView(Context context) {
		super(context);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	public LoadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	public LoadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	View footer;

	// ���صײ�������ʾ���� ��listView
	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		footer = inflater.inflate(R.layout.news_tab_footer, null);
		footer.findViewById(R.id.news_loadMore).setVisibility(View.GONE); // ��ʼ����
		this.addFooterView(footer);

		this.setOnScrollListener(this);
	}

	int totalItemCount;// ������
	int lastVisibleItem;// ���һ���ɼ���Item
	boolean isLoading; // ���ڼ���

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (totalItemCount == lastVisibleItem
				&& scrollState == SCROLL_STATE_IDLE) {
			if (!isLoading) {
				isLoading = true;
				footer.findViewById(R.id.news_loadMore).setVisibility(
						View.VISIBLE);
				
				//���ظ���
				loadlistener.onLoad();
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.lastVisibleItem = firstVisibleItem + visibleItemCount;
		this.totalItemCount = totalItemCount;
	}
	
	private ILoadlistener loadlistener;
	public void setLoadInterface(ILoadlistener loadlistener){
		this.loadlistener=loadlistener;
	}
	
	//���ظ������ݵĻص��ӿ�
	public interface ILoadlistener{
		public void onLoad();
	}
	
	public void LoadComplete(){
		isLoading=false;
		footer.findViewById(R.id.news_loadMore).setVisibility(
				View.GONE);
	}
}
