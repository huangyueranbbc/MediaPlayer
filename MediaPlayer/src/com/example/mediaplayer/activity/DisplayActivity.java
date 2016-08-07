package com.example.mediaplayer.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.mediaplayer.R;
import com.example.mediaplayer.util.Constants;
import com.example.mediaplayer.util.Utils;
import com.example.mediaplayer.view.AudioFragment;
import com.example.mediaplayer.view.VideoFragment;

public class DisplayActivity extends Activity implements SurfaceHolder.Callback {
	private static final String Tag = "DisplayActivity";

	private int displayType = Constants.DISPLAY_TYPE_VIDEO; // 当前播放多媒体文件的类型
															// 音频/视频
	private View mvAudio, mvVideo;
	private SurfaceView mSurfaceView;

	// 视频播放状态
	private int displayState = Constants.DISPLAY_PAUSE;

	private SurfaceHolder mHolder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dispaly);
		initView(); // 初始化界面

		// 从SharedPreference中获取之前保存的播放类型
		displayType = Utils.getDisplayType(this);

		if (displayType == Constants.DISPLAY_TYPE_AUDIO) {
			mvAudio.setVisibility(View.VISIBLE);
			mvVideo.setVisibility(View.GONE);
			mSurfaceView.setVisibility(View.GONE);
		} else if (displayType == Constants.DISPLAY_TYPE_VIDEO) {
			// 初始化SurfaceView
			initSurfaceView();

			mvVideo.setVisibility(View.VISIBLE);
			mvAudio.setVisibility(View.GONE);
			mSurfaceView.setVisibility(View.VISIBLE);

			FragmentTransaction ft = getFragmentManager().beginTransaction(); // 管理Fragment
			ft.replace(R.id.video_fragment, new VideoFragment());
			ft.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
			ft.commit();
		}

	}

	private void initView() {
		mvAudio = findViewById(R.id.audio_fragment);
		mvVideo = findViewById(R.id.video_fragment);
		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
	}

	private void initSurfaceView() {
		if (mHolder == null) {
			mHolder = mSurfaceView.getHolder();
			mHolder.addCallback(this);
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
	}

	public void BtnOnClick(View view) {
		FragmentManager fragmentManager = getFragmentManager();

		// 根据当前播放类型(视频/音频) 将按钮时间派发给相应的Fragment
		if (displayType == Constants.DISPLAY_TYPE_AUDIO) {
			AudioFragment audioFragment = (AudioFragment) fragmentManager.findFragmentById(R.id.audio_fragment);
			audioFragment.BtnOnClick(view);
		} else if (displayType == Constants.DISPLAY_TYPE_VIDEO) {
			VideoFragment videoFragment = (VideoFragment) fragmentManager.findFragmentById(R.id.video_fragment);
			videoFragment.BtnOnClick(view);
		}

		// 在Activity中，处理音频和视频切换按钮点击
		switch (view.getId()) {
		// 切换为音频播放界面
		case R.id.btn_switch_audio:
			displayType = Constants.DISPLAY_TYPE_AUDIO;
			// 保存当前的播放界面类型
			Utils.setDisplayType(this, displayType);

			mvAudio.setVisibility(View.VISIBLE);
			mvVideo.setVisibility(View.GONE);
			mSurfaceView.setVisibility(View.GONE);
			break;
		// 切换为视频播放界面
		case R.id.btn_switch_video:
			displayType = Constants.DISPLAY_TYPE_VIDEO;
			// 保存当前的播放界面类型
			Utils.setDisplayType(this, displayType);

			mvVideo.setVisibility(View.VISIBLE);
			mvAudio.setVisibility(View.GONE);
			mSurfaceView.setVisibility(View.VISIBLE);

			FragmentTransaction ft = getFragmentManager().beginTransaction(); // 管理Fragment
			ft.replace(R.id.video_fragment, new VideoFragment());
			ft.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
			ft.commit();
			break;
		default:
			break;
		}
	}

	// 切换播放状态
	public void displayStateSwitch() {
		if (displayState == Constants.DISPLAY_PAUSE) {
			displayContinue();
		} else if (displayState == Constants.DISPLAY_PLAY) {
			displayPause();
		}
	}

	// 暂停播放当前视频
	private void displayPause() {
		displayState = Constants.DISPLAY_PAUSE;
		// 更新Fragment的显示
		VideoFragment videoFragment = getVideoFragment();
		videoFragment.updateDisplayState(displayState);
	}

	// 继续播放当前视频
	private void displayContinue() {
		displayState = Constants.DISPLAY_PLAY;
		// 更新Fragment的显示
		VideoFragment videoFragment = getVideoFragment();
		videoFragment.updateDisplayState(displayState);
	}

	// 获取视频Fragment对象
	private VideoFragment getVideoFragment() {
		FragmentManager fr = getFragmentManager();
		VideoFragment videoFragment = (VideoFragment) fr.findFragmentById(R.id.video_fragment);
		return videoFragment;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	/**
	 * 处理列表编辑 并返回结果
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (displayType == Constants.DISPLAY_TYPE_AUDIO) {
			Log.i(Tag, "result for audio");
		} else if (displayType == Constants.DISPLAY_TYPE_VIDEO) {
			Log.i(Tag, "result for video");
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
