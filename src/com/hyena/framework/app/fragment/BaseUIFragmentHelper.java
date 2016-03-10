/**
 * Copyright (C) 2015 The AndroidRCStudent Project
 */
package com.hyena.framework.app.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.hyena.framework.servcie.BaseService;

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

    /**
     * replace fragment
     * @param id layoutId
     * @param fragment fragment
     */
    public void replaceFragment(int id, BaseUIFragment fragment) {
        if (mBaseUIFragment == null && mBaseUIFragment.getActivity() == null
                && mBaseUIFragment.getActivity().isFinishing())
            return;

        FragmentTransaction transaction = getBaseUIFragment()
                .getChildFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * showPushFragment
     * @param clz class
     */
    public void showPushFragment(Class<? extends BaseUIFragment> clz) {
        showPushFragment(clz, null);
    }

    /**
     * showPopFragment
     * @param clz class
     */
    public void showPopFragment(Class<? extends BaseUIFragment> clz) {
        showPopFragment(clz, null);
    }

    /**
     * showPushFragment
     * @param clz class
     * @param bundle arguments
     */
    public void showPushFragment(Class<? extends BaseUIFragment> clz, Bundle bundle) {
        if (mBaseUIFragment == null && mBaseUIFragment.getActivity() == null
                && mBaseUIFragment.getActivity().isFinishing())
            return;

        BaseUIFragment fragment = BaseUIFragment.newFragment(
                getBaseUIFragment().getActivity(), clz, bundle);
        getBaseUIFragment().showPushFragment(fragment);
    }

    /**
     * showPopFragment
     * @param clz class
     * @param bundle arguments
     */
    public void showPopFragment(Class<? extends BaseUIFragment> clz, Bundle bundle) {
        if (mBaseUIFragment == null && mBaseUIFragment.getActivity() == null
                && mBaseUIFragment.getActivity().isFinishing())
            return;

        BaseUIFragment fragment = BaseUIFragment.newFragment(
                getBaseUIFragment().getActivity(), clz, bundle);
        getBaseUIFragment().showPopFragment(fragment);

    }

    public <T extends BaseService> T getService(String serviceName) {
        if (mBaseUIFragment == null && mBaseUIFragment.getActivity() == null
                && mBaseUIFragment.getActivity().isFinishing())
            return null;
        return (T) mBaseUIFragment.getSystemService(serviceName);
    }
}
