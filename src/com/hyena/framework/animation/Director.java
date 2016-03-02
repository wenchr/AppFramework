package com.hyena.framework.animation;

import java.util.Stack;

import com.hyena.framework.animation.utils.UIUtils;
import com.hyena.framework.clientlog.LogUtil;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 导演
 * @author yangzc
 */
public class Director implements RenderView.SizeChangeListener, OnTouchListener {

	private static Director _instance = null;
	
	private MainLoop mMainLooper;
	private volatile boolean mPaused = true;
	protected RenderView mAttachView = null;
	private Rect mRect = null;
	private Stack<CScene> mScenes;
	
	private Director(){
		mScenes = new Stack<CScene>();
	}
	
	public static Director getSharedDirector(){
		if(_instance == null)
			_instance = new Director();
		return _instance;
	}
	
	/**
	 * 释放实例
	 */
	public void releaseInstance(){
		_instance = null;
	}
	
	/**
	 * 设置关联的View
	 * @param view
	 */
	public void setAttachView(RenderView view){
		mAttachView = view;
		pause();
		if(view == null)
			return;
		
		mAttachView.setSizeChangeListener(this);
		mAttachView.setOnTouchListener(this);
	}
	
	private int mStartCnt = 0;
	
	/**
	 * 开启引擎
	 */
	public boolean start(){
		if(mStartCnt > 0) {
			LogUtil.v("yangzc", "start fail, reason: running");
			mStartCnt ++;
			return false;
		}
		LogUtil.v("yangzc", "start");
		reset();
		
		mPaused = false;
		mMainLooper = new MainLoop();
		mMainLooper.mStarted = true;
		mMainLooper.start();
		mStartCnt ++;
		return true;
	}
	
	/**
	 * 暂停引擎
	 */
	public void pause(){
		mPaused = true;
	}
	
	/**
	 * 重启引擎
	 */
	public void resume(){
		mPaused = false;
	}
	
	/**
	 * 引擎是否暂停
	 * @return
	 */
	public boolean isPaused(){
		return mPaused;
	}
	
	/**
	 * 关闭引擎
	 */
	public void stop(){
		mStartCnt --;
		if(mStartCnt < 0)
			mStartCnt = 0;
		if(mStartCnt > 0)
			return;
		LogUtil.v("yangzc", "stop");
		reset();
		release();
	}
	
	/**
	 * 释放资源
	 */
	private void release(){
		if(mAttachView != null){
			mAttachView.stopReferesh();
			mAttachView = null;
		}
		if(mScenes != null){
			mScenes.clear();
		}
		mCurrentScene = null;
	}
	
	private void reset(){
		mPaused = true;
		mStartCnt = 0;
		if(mMainLooper != null){
			mMainLooper.mStarted = false;
			mMainLooper.interrupt();
//			while (true) {
//				try {
//					mMainLooper.join();
//				} catch (InterruptedException e) {
//					continue;
//				}
//				break;
//			}
		}
		mMainLooper = null;
	}
	
	private CScene mCurrentScene;
	
	/**
	 * 显示场景
	 * @param scene
	 */
	public synchronized void showScene(CScene scene){
		if(scene == null)
			return;
		
		if(mCurrentScene != null){
			mCurrentScene.onScenePause();
		}
		
		this.mCurrentScene = scene;
		mScenes.push(mCurrentScene);
		
		mCurrentScene.onSceneStart();
		mCurrentScene.onSceneResume();
	}
	
	/**
	 * 关闭场景
	 */
	public synchronized void closeScene(){
		if(mScenes != null && mScenes.size() > 0){
			mScenes.pop();
			if(mCurrentScene != null){
				mCurrentScene.onScenePause();
				mCurrentScene.onSceneStop();
			}
			if(mScenes.size() > 0)
				mCurrentScene = mScenes.peek();
		}
	}
	
	/**
	 * 暂停场景
	 */
	public void pauseScene(){
		if(mCurrentScene != null){
			mCurrentScene.onScenePause();
		}
	}
	
	/**
	 * 重启场景
	 */
	public void resumeScene(){
		if(mCurrentScene != null){
			mCurrentScene.onSceneResume();
		}
	}
	
	/**
	 * 获得活跃的场景
	 * @return
	 */
	public CScene getActiveScene(){
		return mCurrentScene;
	}
	
	/**
	 * 类型转换
	 * @param dpValue
	 * @return
	 */
	public int dp2px(float dpValue) {
		if(mAttachView == null){
			return 0;
		}
		return UIUtils.dp2px(mAttachView.getContext(), dpValue);
    }
	
	/**
	 * 获得当前view大小
	 * @return
	 */
	public Rect getViewSize(){
		return mRect;
	}
	
	/**
	 * 设置当前窗口size
	 * @param rect
	 */
	public void setViewSize(Rect rect){
		this.mRect = rect;
		if(mCurrentScene != null){
			mCurrentScene.onSizeChange(mAttachView, rect);
		}
	}
	
	/**
	 * 重构是否显示
	 * @return
	 */
	public boolean isViewVisable(){
		return (mRect != null && mRect.width() > 0 && mRect.height() > 0 
				&& mAttachView != null && getActiveScene() != null && mAttachView.isShown());
	}

	@Override
	public void onSizeChange(Rect rect) {
		setViewSize(rect);
		resume();
		CScene scene = getActiveScene();
		if(scene != null){
			scene.refresh();
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		CScene scene = getActiveScene();
		if(scene != null){
			return scene.onTouch(v, event);
		}
		return false;
	}
	
	private class MainLoop extends Thread {
		
		public volatile boolean mStarted = true;
		
		@Override
		public void run() {
			while(mStarted){
				CScene scene = getActiveScene();
				if(scene != null){
					scene.refresh();
				}
				try {
					Thread.sleep(EngineConfig.MIN_REFRESH_SPAN);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
