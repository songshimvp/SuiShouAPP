package songshi.suishou.tool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.songshi.suishou.R;

public class NetWork {

	public static boolean isNetworkAvailable(Context context) {
		// �ж�����״̬
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			Toast.makeText(context, "��ǰû������", Toast.LENGTH_LONG).show();
			return false;

		} else {
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
			if (networkInfo != null) {
				for (NetworkInfo netWork : networkInfo) {
					if (netWork.getState() == NetworkInfo.State.CONNECTED) {
						Toast.makeText(context, "�����磬��ˢ�·���", Toast.LENGTH_LONG)
								.show();
						return true;
					}
				}
			}
		}
		Toast.makeText(context, "û���κ���������", Toast.LENGTH_LONG).show();
		return false;
	}

	public static void setNetWork(final Context context) {
		if (!isNetworkAvailable(context)) {

			new AlertDialog.Builder(context).setIcon(R.drawable.dialog_alert)
					.setTitle("����״̬��ʾ").setMessage("��ǰû�п��õ����磬��������������")
					.setPositiveButton("����", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							context.startActivity(new Intent(
									android.provider.Settings.ACTION_SETTINGS));
							
							//context.finish();
						}
					}).setNegativeButton("ȡ��", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							return ;
						}
					}).create().show();
		}
	}
}
