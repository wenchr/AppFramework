package com.hyena.framework.animation;

import com.hyena.framework.animation.nodes.CTextNode;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * 场景
 * @author yangzc
 */
public class CScene extends CLayer {
	
	private long mLastRefreshTs = -1;
	
	private CTextNode mTextNode;
	protected CScene(){
		super();
		if(EngineConfig.DEBUG_ABLE){
			mTextNode = CTextNode.create();
			addNode(mTextNode, Integer.MAX_VALUE);
		}
	}
	
	public static CScene create(){
		return new CScene();
	}
	
	/**
	 * 刷新场景
	 */
	public void refresh(){
		if(!Director.getSharedDirector().isViewVisible())
			return;
		
		if(Director.getSharedDirector().isPaused()){
			mLastRefreshTs = -1;
			return;
		}
		
		long ts = System.currentTimeMillis();
		if(mLastRefreshTs > 0){
			update(ts - mLastRefreshTs);
			
			if(EngineConfig.DEBUG_ABLE && mTextNode!= null && ts != mLastRefreshTs){
				mTextNode.setText(1000/(ts - mLastRefreshTs) + " FPS");
			}
		}
		mLastRefreshTs = ts;
	}
	
	@Override
	public synchronized void update(float dt) {
		if(!Director.getSharedDirector().isViewVisible())
			return;
		
		if(mTextNode != null)
			mTextNode.setPosition(new Point(0, getHeight() - dip2px(20)));

//		DebugUtils.debug("yangzc", "scene: update");
//		long start = System.currentTimeMillis();
		super.update(dt);
//		DebugUtils.debug("yangzc", "cost: " + (System.currentTimeMillis() - start));
	}	

	public void onSceneStart(){}
	public void onSceneStop(){}
	
	public void onSceneResume(){}
	public void onScenePause(){}
	
	/**
	 * View大小改变
	 * @param view
	 * @param rect
	 */
	public void onSizeChange(RenderView view, Rect rect){}
	
	@Override
	public boolean isActived() {
		return super.isActived();
	}
}
