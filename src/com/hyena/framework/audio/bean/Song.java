/**
 * Copyright (C) 2015 The AndroidPhoneTeacher Project
 */
package com.hyena.framework.audio.bean;

import java.io.File;

import com.hyena.framework.audio.MusicDir;
import com.hyena.framework.security.MD5Util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 歌曲信息
 * @author yangzc
 */
public class Song implements Parcelable, Cloneable {

	// 歌曲远程URL
	public String mUrl;

	public Song() {
	}

	public Song(Parcel in) {
		mUrl = in.readString();
	}

	/**
	 * 获得本地路径
	 * 
	 * @return
	 */
	public File getLocalFile() {
		return new File(MusicDir.getMusicDir(), MD5Util.encode(mUrl)
				+ ".mp3");
	}
	
	/**
	 * 是否是在线歌曲
	 * @return
	 */
	public boolean isOnline(){
		return false;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mUrl);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Song) {
			return ((Song) o).mUrl.equals(mUrl);
		}
		return super.equals(o);
	}

	public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
		public Song createFromParcel(Parcel in) {
			return new Song(in);
		}

		public Song[] newArray(int size) {
			return new Song[size];
		}
	};
}
