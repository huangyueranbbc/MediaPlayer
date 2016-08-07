package com.example.mediaplayer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Utils {

	/**
	 * 保持当前的播放类型
	 * 
	 * @param context
	 * @param displayType
	 */
	public static void setDisplayType(Context context, int displayType) {
		SharedPreferences sp = context.getSharedPreferences(Constants.DISPLAY_TYPE, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(Constants.DISPLAY_TYPE, displayType);
		editor.commit();
	}

	// 获取之前保存的播放类型
	public static int getDisplayType(Context context) {
		SharedPreferences sp = context.getSharedPreferences(Constants.DISPLAY_TYPE, Context.MODE_PRIVATE);
		return sp.getInt(Constants.DISPLAY_TYPE, Constants.DISPLAY_TYPE_AUDIO);
	}

}
