/**
 * Copyright (C) 2015 The KnowboxFramework Project
 */
package com.hyena.framework.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.hyena.framework.app.fragment.BaseUIFragment;

/**
 * 空页面
 * @author yangzc
 *
 */
public abstract class EmptyView extends RelativeLayout {

	private BaseUIFragment<?> mBaseUIFragment;

	public EmptyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public EmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EmptyView(Context context) {
		super(context);
	}

	public void setBaseUIFragment(BaseUIFragment<?> baseUIFragment){
		this.mBaseUIFragment = baseUIFragment;
	}

	public BaseUIFragment<?> getBaseUIFragment(){
		return mBaseUIFragment;
	}
	
	/**
	 * 设置据上方距离
	 * @param topMargin
	 */
	public void setTopMargin(int topMargin){
		RelativeLayout.LayoutParams params = (LayoutParams) getLayoutParams();
		params.topMargin = topMargin;
		requestLayout();
	}

	/**
	 * 显示没有网络页面
	 */
	public abstract void showNoNetwork();

	/**
	 * 显示带提示的空页面
	 * @param hint
	 */
	public abstract void showEmpty(String hint);

    /**
     * 根据类型显示不同的Empty
     * @param hint
     * @param type
     */
    public void showEmptyByType(int type, String hint){
        showEmpty(hint);
    }

	/**
	 * 自定义空页面
	 * @param resId
	 * @param hint
	 * @param desc
	 * @param btnTxt
	 * @param btnClickListener
	 */
	public abstract void showEmpty(final int resId, final String hint, final String desc,
						  final String btnTxt, final OnClickListener btnClickListener);
}
