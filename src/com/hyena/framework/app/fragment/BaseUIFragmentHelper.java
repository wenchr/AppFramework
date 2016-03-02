/**
 * Copyright (C) 2015 The AndroidRCStudent Project
 */
package com.hyena.framework.app.fragment;

/**
 * BaseUIFragment帮助接口
 * @author yangzc on 15/8/22.
 */
public abstract class BaseUIFragmentHelper {

    private BaseUIFragment<?> mBaseUIFragment;

    public BaseUIFragmentHelper(BaseUIFragment<?> fragment){
        this.mBaseUIFragment = fragment;
    }

    public BaseUIFragment<?> getBaseUIFragment(){
        return mBaseUIFragment;
    }

    /**
     * 用户可见
     * @param visible
     */
    public void setVisibleToUser(boolean visible) {}

    /**
     * 默认背景
     * @return
     */
    public int getBackGroundColor(){
    	return 0xfff6f6f6;
    }
}
