package com.songshi.suishou;

import songshi.suishou.tool.NetWork;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.Toast;

public class LoadActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.load_image);

		ImageView loadImage = (ImageView) findViewById(R.id.loadImage);

		// ͸���Ƚ��䶯��
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.4f, 1.0f);// ��΢����ʾ����ȫ��ʾ
		// ����ʱ��
		alphaAnimation.setDuration(5000);
		// ������Ͷ������й���
		loadImage.setAnimation(alphaAnimation);

		alphaAnimation.setAnimationListener(new AnimationListener() {

			// ������ʼ
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				Toast.makeText(LoadActivity.this, "��ӭʹ������", Toast.LENGTH_SHORT).show();
				//NetWork.isNetworkAvailable(LoadActivity.this);
				
				NetWork.setNetWork(LoadActivity.this);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			// ��������
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
				Intent mainIntent = new Intent(LoadActivity.this,
						MainActivity.class);
				startActivity(mainIntent);
				LoadActivity.this.finish();
			}
		});
	}

	

}
