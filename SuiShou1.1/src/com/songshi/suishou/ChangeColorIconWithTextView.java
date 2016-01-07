package com.songshi.suishou;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

//自定义的View类
public class ChangeColorIconWithTextView extends View {

	// 针对自定义属性，去声明对应的类型变量
	private int mColor = 0xFF45C01A;
	private Bitmap mIconBitmap;
	private String mTetx = "随手";
	private int mTextSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	// 画图变量
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;
	private Paint mTextPaint;
	private float mAlpha;
	private Rect mIconRect;
	private Rect mTextRect;

	// 继承View，重写构造函数
	public ChangeColorIconWithTextView(Context context) {
		this(context, null);
	}

	public ChangeColorIconWithTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ChangeColorIconWithTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle); // 构造函数一调二、二调三

		TypedArray types = context.obtainStyledAttributes(attrs,
				R.styleable.ChangeColorIconWithTextView);
		// 获取自定义属性，并且映射到当前mIconBitmap、mColor、mTetx、mTextSize
		for (int i = 0; i < types.getIndexCount(); i++) {
			int attr = types.getIndex(i);
			switch (attr) {
			case R.styleable.ChangeColorIconWithTextView_icon:
				BitmapDrawable drawable = (BitmapDrawable) types
						.getDrawable(attr);
				mIconBitmap = drawable.getBitmap();
				break;

			case R.styleable.ChangeColorIconWithTextView_color:
				mColor = types.getColor(attr, 0xFF45C01A);
				break;

			case R.styleable.ChangeColorIconWithTextView_text:
				mTetx = types.getString(attr);
				break;

			case R.styleable.ChangeColorIconWithTextView_text_size:
				mTextSize = (int) types.getDimension(attr, TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
								getResources().getDisplayMetrics()));
				break;
			}
		}
		types.recycle(); // 回收

		// 初始化画图的变量
		mTextRect = new Rect();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0Xff555555);
		mTextPaint.getTextBounds(mTetx, 0, mTetx.length(), mTextRect);

	}

	// 重写onMeasure方法（自定义View的第四步）
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - mTextRect.height()); // 底部Icon的边长（正方形）——取
															// 宽度 和 高度的最小值

		int left = getMeasuredWidth() / 2 - iconWidth / 2; // Icon的左边
		int top = getMeasuredHeight() / 2 - (mTextRect.height() + iconWidth)
				/ 2; // Icon的顶边

		mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth); // 底部Icon的矩形区域
	}

	// 重写onDraw方法（自定义View的第五步）
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawBitmap(mIconBitmap, null, mIconRect, null); // 绘制白色的Icon（原图）

		int alpha = (int) Math.ceil(255 * mAlpha); // 设置透明度(float->int)

		// 在内存中去准备mBitmap(setAlpha , 纯色 , xfermode , 图标)
		setupTargetBitmap(alpha);

		// 1、绘制原文本 ； 2、绘制变色的文本
		drawSourceText(canvas, alpha);
		drawTargetText(canvas, alpha);

		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	// 在内存中绘制可变色的Icon
	private void setupTargetBitmap(int alpha) {
		mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);

		mPaint = new Paint();
		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setAlpha(alpha);

		mCanvas.drawRect(mIconRect, mPaint);

		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

		mPaint.setAlpha(255);
		mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
	}

	// 绘制原文本
	private void drawSourceText(Canvas canvas, int alpha) {
		mTextPaint.setColor(0Xf333333);
		mTextPaint.setAlpha(255 - alpha);   //与变色文本透明度对应
		int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;   //x The x-coordinate of the origin of the text being drawn 
		int y = mIconRect.bottom + mTextRect.height();		// y The y-coordinate of the origin of the text being drawn

		canvas.drawText(mTetx, x, y, mTextPaint);
	}

	// 绘制变色的文本
	private void drawTargetText(Canvas canvas, int alpha) {
		mTextPaint.setColor(mColor);
		mTextPaint.setAlpha(alpha);   //与原文本透明度对应
		int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;   //x The x-coordinate of the origin of the text being drawn 
		int y = mIconRect.bottom + mTextRect.height();		// y The y-coordinate of the origin of the text being drawn

		canvas.drawText(mTetx, x, y, mTextPaint);
	}

	// 外界设置Aplha的接口
	public void setIconAlpha(float alpha){
		this.mAlpha=alpha;
		invalidateView(); //重绘
	}

	//重绘
	private void invalidateView() {
		// TODO Auto-generated method stub
		if(Looper.getMainLooper()==Looper.myLooper()){
			//UI线程
			invalidate();
		}else{
			//非UI线程
			postInvalidate();
		}
	}
	
	
	/*当遇到Activity被回收（横竖屏、内存不足）时，Activity会重建，
	而去调用onCreate()方法，在onCreate()方法中调用了设置首项透明度的方法。
	这样就会出现，选中的View和内容Fragment的不对应的Bug*/	
	
	//Bundle的键，作用：自定义的VIew继承的有可能不是View，有可能是TextView、ImageView，
	//重写下面两个方法，以便记住原本的Bundle（不能抹掉原来的XXView的恢复和销毁的过程）
	private static final String INSTANCE_STATUS="instance_status";  
	private static final String STATUS_ALPHA="status_alpha";  //Bundle的键
	
	//当Activity重建的时候，恢复Alpha值
	@Override
	protected Parcelable onSaveInstanceState() {
		
		Bundle bundle=new Bundle();
		bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());  //把父级存储的变量放到INSTANCE_STATUS中
		bundle.putFloat(STATUS_ALPHA, mAlpha);  //存储自己需要保存的东西
		
		return bundle;
	}
	
	@Override
	protected void onRestoreInstanceState(Parcelable state) {

		if(state instanceof Bundle){
			Bundle bundle=(Bundle) state;
			mAlpha=bundle.getFloat(STATUS_ALPHA);   //取出自己保存的东西
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));  //取出系统保存的东西，并调用系统的恢复
			return;
		}
		super.onRestoreInstanceState(state);
	}
}
