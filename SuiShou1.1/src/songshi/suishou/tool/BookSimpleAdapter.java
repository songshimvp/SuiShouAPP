package songshi.suishou.tool;

import java.util.List;

import com.songshi.suishou.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//��д�̳���ViewHolder��MyViewHolder��Ȼ�󴫵ݸ�android.support.v7.widget.RecyclerView.Adapter;����Ϊ����
public class BookSimpleAdapter extends Adapter<MyViewHolder> {

	private LayoutInflater mInflater;
	private Context mContext;
	private List<String> mDatasList;
	
	public BookSimpleAdapter(Context context,List<String> datasList){
		this.mContext=context;
		this.mDatasList=datasList;
		this.mInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//ͨ��ViewHolderȥ����ֵ
	@Override
	public void onBindViewHolder(MyViewHolder holder, int pos) {
		// TODO Auto-generated method stub
		holder.tv.setText(mDatasList.get(pos));
	}

	//���Կ���Google��ʼǿ�ƿ�����ʹ��ViewHolderģʽ
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		
		View view=mInflater.inflate(R.layout.books_tab_recyclerview_item, arg0,false);
		
		MyViewHolder viewHolder=new MyViewHolder(view);
		return viewHolder;
	}
	
}

class MyViewHolder extends ViewHolder{

	//MyViewHolder�������Լ���ǰItem�����пؼ�
	TextView tv;
	
	public MyViewHolder(View arg0) {
		super(arg0);
		//��ʼ�����пؼ�
		tv=(TextView) arg0.findViewById(R.id.booksItem);
		
	}
	
}