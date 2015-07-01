package com.seteam3.SyllabusMeter;


import com.seteam3.ukonnekt.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class SyllabusMeterView extends View {

	private Path mClippingPath;
	private Context mContext;
	private Bitmap mBitmap;
	private float mPivotX;
	private float mPivotY;
	
	public SyllabusMeterView(Context context) {
		super(context);
		mContext = context;
	    initilizeImage();
	}
	
	public SyllabusMeterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	    initilizeImage();
	}
	
	private void initilizeImage() {
		mClippingPath = new Path();
		
		//Top left coordinates of image.
		mPivotX = getScreenGridUnit();
		mPivotY = 0;
		
		//Adjust the image size to support different screen sizes
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.circle4);
	    int imageWidth = (int) (getScreenGridUnit() * 30);
	    int imageHeight = (int) (getScreenGridUnit() * 30);
	    mBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);
	}
	

	
	public void setClipping(float progress) {
		
		//Converting the progress in range of 0 to 100 to angle in range of 0-360.
		float angle = (progress * 360) / 100;
		mClippingPath.reset();
		//Defining a rectangle containing the image
		RectF oval = new RectF(mPivotX, mPivotY, mPivotX + mBitmap.getWidth(), mPivotY + mBitmap.getHeight());
		//Moving the current position to center of rectangle
		mClippingPath.moveTo(oval.centerX(), oval.centerY());
		//Drawing an arc from center to given angle
		mClippingPath.addArc(oval, 0, angle);
		//Drawing a line from end of arc to center
		mClippingPath.lineTo(oval.centerX(), oval.centerY());
		//Redrawing the the canvas
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		//Clip the canvas
		canvas.clipPath(mClippingPath);
		canvas.drawBitmap(mBitmap, mPivotX, mPivotY, null);
		
	}
	
	private float getScreenGridUnit() {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels / 32;
	}
	
}
