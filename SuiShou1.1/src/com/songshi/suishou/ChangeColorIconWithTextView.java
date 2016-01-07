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

//�Զ����View��
public class ChangeColorIconWithTextView extends View {

	// ����Զ������ԣ�ȥ������Ӧ�����ͱ���
	private int mColor = 0xFF45C01A;
	private Bitmap mIconBitmap;
	private String mTetx = "����";
	private int mTextSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	// ��ͼ����
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;
	private Paint mTextPaint;
	private float mAlpha;
	private Rect mIconRect;
	private Rect mTextRect;

	// �̳�View����д���캯��
	public ChangeColorIconWithTextView(Context context) {
		this(context, null);
	}

	public ChangeColorIconWithTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ChangeColorIconWithTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle); // ���캯��һ������������

		TypedArray types = context.obtainStyledAttributes(attrs,
				R.styleable.ChangeColorIconWithTextView);
		// ��ȡ�Զ������ԣ�����ӳ�䵽��ǰmIconBitmap��mColor��mTetx��mTextSize
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
		types.recycle(); // ����

		// ��ʼ����ͼ�ı���
		mTextRect = new Rect();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0Xff555555);
		mTextPaint.getTextBounds(mTetx, 0, mTetx.length(), mTextRect);

	}

	// ��дonMeasure�������Զ���View�ĵ��Ĳ���
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - mTextRect.height()); // �ײ�Icon�ı߳��������Σ�����ȡ
															// ��� �� �߶ȵ���Сֵ

		int left = getMeasuredWidth() / 2 - iconWidth / 2; // Icon�����
		int top = getMeasuredHeight() / 2 - (mTextRect.height() + iconWidth)
				/ 2; // Icon�Ķ���

		mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth); // �ײ�Icon�ľ�������
	}

	// ��дonDraw�������Զ���View�ĵ��岽��
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawBitmap(mIconBitmap, null, mIconRect, null); // ���ư�ɫ��Icon��ԭͼ��

		int alpha = (int) Math.ceil(255 * mAlpha); // ����͸����(float->int)

		// ���ڴ���ȥ׼��mBitmap(setAlpha , ��ɫ , xfermode , ͼ��)
		setupTargetBitmap(alpha);

		// 1������ԭ�ı� �� 2�����Ʊ�ɫ���ı�
		drawSourceText(canvas, alpha);
		drawTargetText(canvas, alpha);

		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	// ���ڴ��л��ƿɱ�ɫ��Icon
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

	// ����ԭ�ı�
	private void drawSourceText(Canvas canvas, int alpha) {
		mTextPaint.setColor(0Xf333333);
		mTextPaint.setAlpha(255 - alpha);   //���ɫ�ı�͸���ȶ�Ӧ
		int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;   //x The x-coordinate of the origin of the text being drawn 
		int y = mIconRect.bottom + mTextRect.height();		// y The y-coordinate of the origin of the text being drawn

		canvas.drawText(mTetx, x, y, mTextPaint);
	}

	// ���Ʊ�ɫ���ı�
	private void drawTargetText(Canvas canvas, int alpha) {
		mTextPaint.setColor(mColor);
		mTextPaint.setAlpha(alpha);   //��ԭ�ı�͸���ȶ�Ӧ
		int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;   //x The x-coordinate of the origin of the text being drawn 
		int y = mIconRect.bottom + mTextRect.height();		// y The y-coordinate of the origin of the text being drawn

		canvas.drawText(mTetx, x, y, mTextPaint);
	}

	// �������Aplha�Ľӿ�
	public void setIconAlpha(float alpha){
		this.mAlpha=alpha;
		invalidateView(); //�ػ�
	}

	//�ػ�
	private void invalidateView() {
		// TODO Auto-generated method stub
		if(Looper.getMainLooper()==Looper.myLooper()){
			//UI�߳�
			invalidate();
		}else{
			//��UI�߳�
			postInvalidate();
		}
	}
	
	
	/*������Activity�����գ����������ڴ治�㣩ʱ��Activity���ؽ���
	��ȥ����onCreate()��������onCreate()�����е�������������͸���ȵķ�����
	�����ͻ���֣�ѡ�е�View������Fragment�Ĳ���Ӧ��Bug*/	
	
	//Bundle�ļ������ã��Զ����VIew�̳е��п��ܲ���View���п�����TextView��ImageView��
	//��д���������������Ա��סԭ����Bundle������Ĩ��ԭ����XXView�Ļָ������ٵĹ��̣�
	private static final String INSTANCE_STATUS="instance_status";  
	private static final String STATUS_ALPHA="status_alpha";  //Bundle�ļ�
	
	//��Activity�ؽ���ʱ�򣬻ָ�Alphaֵ
	@Override
	protected Parcelable onSaveInstanceState() {
		
		Bundle bundle=new Bundle();
		bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());  //�Ѹ����洢�ı����ŵ�INSTANCE_STATUS��
		bundle.putFloat(STATUS_ALPHA, mAlpha);  //�洢�Լ���Ҫ����Ķ���
		
		return bundle;
	}
	
	@Override
	protected void onRestoreInstanceState(Parcelable state) {

		if(state instanceof Bundle){
			Bundle bundle=(Bundle) state;
			mAlpha=bundle.getFloat(STATUS_ALPHA);   //ȡ���Լ�����Ķ���
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));  //ȡ��ϵͳ����Ķ�����������ϵͳ�Ļָ�
			return;
		}
		super.onRestoreInstanceState(state);
	}
}
