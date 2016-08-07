package com.example.mediaplayer.service;

import java.io.IOException;

import com.example.mediaplayer.activity.DisplayActivity;
import com.example.mediaplayer.util.Constants;
import com.example.mediaplayer.view.IUpdateDisplayState;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AudioPlayerService extends Service {

	private static IUpdateDisplayState iUpdateDisplayState;
	private static MediaPlayer mediaPlayer = null;
	private int displayState = Constants.DISPLAY_PAUSE;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 接收处理发送的Intent
	 */
	public int onStartCommand(Intent intent, int flags, int startId) {

		String action = intent.getStringExtra("action");
		if (Constants.ACTION_AUDIO_PLAY_STATE_SWITCH.equals(action)) {
			// 处理播放/暂停键点击事件
			if (displayState == Constants.DISPLAY_PAUSE) {
				if (mediaPlayer == null) {
					// 开始播放
					String audioPath = "/storage/sdcard/Music/nanpengyou.mp3";
					// 如果暂停 继续播放
					displayPlay(audioPath);
				} else {
					// 继续播放
					displayContinue();
				}
			} else if (displayState == Constants.DISPLAY_PLAY) {
				// 如果播放 暂停
				displayPause();
			}
		} else if (Constants.ACTION_AUDIO_RESTORE_INFO.equals(action)) {
			// 恢复播放显示信息
			restoreDisplayInfo();
		}

		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 重新打开播放器时，恢复上一次的播放显示信息
	 */
	private void restoreDisplayInfo() {
		// 更新界面显示
		if (iUpdateDisplayState != null) {
			iUpdateDisplayState.updateDisplayState(displayState);
		}
	}

	public static void setIUpdateDisplayState(IUpdateDisplayState iUpdateDisplayState) {
		AudioPlayerService.iUpdateDisplayState = iUpdateDisplayState;
	}

	/**
	 * 播放指定路径的音频
	 * 
	 * @param audioPath
	 */
	private void displayPlay(String audioPath) {
		if (mediaPlayer == null) {
			mediaPlayer = new MediaPlayer();
			// 注册异步加载的监听器
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
					displayState = Constants.DISPLAY_PLAY;
					if (iUpdateDisplayState != null) {
						iUpdateDisplayState.updateDisplayState(displayState);
					}
				}
			});
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					displayState = Constants.DISPLAY_PAUSE;
				}
			});
		}

		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(audioPath);
			mediaPlayer.prepareAsync(); // 异步加载
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 暂停
	 */
	private void displayPause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			displayState = Constants.DISPLAY_PAUSE;
			if (iUpdateDisplayState != null) {
				iUpdateDisplayState.updateDisplayState(displayState);
			}
		}
	}

	/**
	 * 继续播放
	 */
	private void displayContinue() {
		if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
			mediaPlayer.start();
			displayState = Constants.DISPLAY_PLAY;
			if (iUpdateDisplayState != null) {
				iUpdateDisplayState.updateDisplayState(displayState);
			}
		}
	}

	public static boolean isPlaying() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			return true;
		} else {
			return false;
		}
	}
}
