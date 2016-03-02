/**
 * Copyright (C) 2015 The AppFramework Project
 */
package com.hyena.framework.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.hyena.framework.utils.UIUtils;

/**
 * 圆圈View </p>
 * 
 * @author yangzc
 *
 */
public class CircleHintView extends View {

	private Paint mBgPaint;
	private Paint mTxtPaint;
	private String mTipStr;

	public CircleHintView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public CircleHintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CircleHintView(Context context) {
		super(context);
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBgPaint.setColor(Color.RED);
		mBgPaint.setStyle(Style.FILL);

		mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTxtPaint.setColor(Color.WHITE);
		mTxtPaint.setTextAlign(Align.CENTER);
		mTxtPaint.setTextSize(UIUtils.dip2px(12));
	}

	/**
	 * 设置颜色
	 * 
	 * @param color
	 */
	public void setColor(int color) {
		mBgPaint.setColor(color);
		postInvalidate();
	}

	/**
	 * 设置文本
	 * 
	 * @param tip
	 */
	public void setText(String tip) {
		this.mTipStr = tip;
		postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int cx = getWidth() / 2;
		int cy = getHeight() / 2;
		canvas.drawCircle(cx, cy, cx, mBgPaint);

		if (!TextUtils.isEmpty(mTipStr)) {
			int x = getWidth() / 2;
			
			FontMetrics fontMetrics = mTxtPaint.getFontMetrics();
			float fontHeight = fontMetrics.bottom - fontMetrics.top;
			float textBaseY = getHeight() - (getHeight() - fontHeight) / 2
					- fontMetrics.bottom;
			canvas.drawText(mTipStr, x, textBaseY, mTxtPaint);
		}
	}
}
