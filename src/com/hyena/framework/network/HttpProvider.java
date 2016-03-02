/*
 * Copyright (c) 2013 Baidu Inc.
 */
package com.hyena.framework.network;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;

import com.hyena.framework.network.HttpExecutor.HttpRequestParams;
import com.hyena.framework.network.HttpExecutor.OutputStreamHandler;
import com.hyena.framework.network.executor.DefaultHttpExecutor;
import com.hyena.framework.network.listener.DataHttpListener;

/**
 * HTTP 数据提供器
 * @author yangzc
 *
 */
public class HttpProvider {

	private HttpExecutor mHttpExecutor;
	
	private boolean isProxy = true;
	
	public void setFlowProxt(boolean isProxy){
		this.isProxy = isProxy;
	}
	
	public HttpProvider() {
		mHttpExecutor = new DefaultHttpExecutor();
	}
	
	public HttpResult doGet(String url, int timeout, HttpListener listener){
		return doGet(url, timeout, -1, listener);
	}
	
	public HttpResult doGet(String url, int timeout, long startPos, HttpListener listener, NameValuePair ...header){
		return doGet(url, null, timeout, startPos, listener, header);
	}
	
	public HttpResult doGet(String url, ArrayList<NameValuePair> params, int timeout, HttpListener listener){
		return doGet(url, params, -1, -1, listener);
	}
	
	/**
	 * 发起Post请求
	 * @param url
	 * @param params
	 * @param listener
	 * @return
	 */
	public HttpResult doPost(String url, ArrayList<? extends NameValuePair> params, HttpListener listener, NameValuePair ...header){
		return doPost(url, null, params, null, listener, header);
	}
	
	public HttpResult doPost(String url, OutputStreamHandler osHandler, HttpListener listener, NameValuePair ...header){
		return doPost(url, osHandler, null, null, listener, header);
	}
	
	public HttpResult doPost(String url, ArrayList<? extends NameValuePair> params, 
			HashMap<String, HttpExecutor.ByteFile> byteFileMap, HttpListener listener, NameValuePair ...header){
		return doPost(url, null, params, byteFileMap, listener, header);
	}
	
	/**
	 * 发起Get请求
	 * @param url
	 * @param params 请求参数
	 * @param timeout 超时
	 * @param startPos 断点开始位置
	 * @param listener
	 * @param header 请求头
	 * @return 返回结果
	 */
	private HttpResult doGet(String url, ArrayList<NameValuePair> params, int timeout, 
			long startPos, HttpListener listener, NameValuePair ...header){
		HttpRequestParams httpParams = new HttpRequestParams();
		httpParams.mParams = params;
		httpParams.mTimeout = timeout;
		httpParams.mStartPos = startPos;
		if(header != null && header.length > 0){
			HashMap<String, String> headerMap = new HashMap<String, String>();
			for(NameValuePair pair : header){
				headerMap.put(pair.getName(), pair.getValue());
			}
			httpParams.mHeader = headerMap;
		}
		httpParams.isProxy = isProxy;
		
		if(listener == null)
			listener = new DataHttpListener();
//		((DefaultHttpExecutor) mHttpExecutor).setFlowProxt(isProxy);
		return mHttpExecutor.doGet(url, httpParams, listener);
	}
	
	/**
	 * 发起Post请求
	 * @param url
	 * @param osHandler 上传流
	 * @param params 参数请求
	 * @param byteFileMap 提交的文件
	 * @param listener 
	 * @param header 请求头
	 * @return 返回结果
	 */
	private HttpResult doPost(String url, OutputStreamHandler osHandler, ArrayList<? extends NameValuePair> params, 
			HashMap<String, HttpExecutor.ByteFile> byteFileMap, HttpListener listener, NameValuePair ...header){
		HttpRequestParams httpParams = new HttpRequestParams();
		httpParams.mOsHandler = osHandler;
		httpParams.mParams = params;
		if(header != null && header.length > 0){
			HashMap<String, String> headerMap = new HashMap<String, String>();
			for(NameValuePair pair : header){
				headerMap.put(pair.getName(), pair.getValue());
			}
			httpParams.mHeader = headerMap;
		}
		if(byteFileMap != null && byteFileMap.size() > 0){
			httpParams.mByteFileMap = byteFileMap;
		}

		if(listener == null)
			listener = new DataHttpListener();
		
		return mHttpExecutor.doPost(url, httpParams, listener);
	}
	
}
