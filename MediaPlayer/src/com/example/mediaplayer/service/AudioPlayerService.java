package com.example.mediaplayer.service;

import com.example.mediaplayer.view.IUpdateDisplayState;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AudioPlayerService extends Service {

	private static IUpdateDisplayState iUpdateDisplayState;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 接收处理发送的Intent
	 */
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (iUpdateDisplayState != null) {
			iUpdateDisplayState.updateDisplayState();
		}

		return super.onStartCommand(intent, flags, startId);
	}

	public static void setIUpdateDisplayState(IUpdateDisplayState iUpdateDisplayState) {
		AudioPlayerService.iUpdateDisplayState = iUpdateDisplayState;
	}

}
