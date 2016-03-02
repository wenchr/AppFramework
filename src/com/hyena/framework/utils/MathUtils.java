/**
 * Copyright (C) 2015 The AppFramework Project
 */
package com.hyena.framework.utils;

/**
 * 数学相关通用类
 * @author yangzc
 */
public class MathUtils {

	/**
	 * 字符串转Integer
	 * @param value
	 * @return
	 */
	public static int valueOfInt(String value){
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
		}
		return -1;
	}
	
	/**
	 * 字符串转Long
	 * @param value
	 * @return
	 */
	public static long valueOfLong(String value){
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
		}
		return -1;
	}
	
	/**
	 * 字符串转Float
	 * @param value
	 * @return
	 */
	public static float valueOfFloat(String value){
		try {
			return Float.valueOf(value);
		} catch (Exception e) {
		}
		return -1;
	}
	
}
