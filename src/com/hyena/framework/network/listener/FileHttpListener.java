/*
 * Copyright (c) 2013 Baidu Inc.
 */
package com.hyena.framework.network.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;

import com.hyena.framework.network.HttpListener;

import android.text.TextUtils;

/**
 * 保存网络数据为本地文件
 * @author yangzc
 *
 */
public class FileHttpListener implements HttpListener {

	private File mTargetFile = null;
	private FileOutputStream mFileOutputStream;
	
	public FileHttpListener(String filePath){
		if(!TextUtils.isEmpty(filePath)){
			File file = new File(filePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			mTargetFile = file;
		}
	}
	
	public File getTargetFile(){
		return mTargetFile;
	}
	
	@Override
	public boolean onStart(long startPos, long contentLength) {
		return true;
	}

	@Override
	public boolean onAdvance(byte[] buffer, int offset, int len) {
		if(mFileOutputStream != null){
			try {
				mFileOutputStream.write(buffer, offset, len);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean onCompleted() {
		return true;
	}

	@Override
	public void onError(int statusCode) {
		onCompleted();
	}

	@Override
	public boolean onReady(String url) {
		try {
			mFileOutputStream = new FileOutputStream(mTargetFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean onRelease() {
		try {
			if(mFileOutputStream != null)
				mFileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean onResponse(HttpResponse response) {
		return false;
	}

}
