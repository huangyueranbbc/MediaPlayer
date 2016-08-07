package com.example.mediaplayer.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;

import com.example.mediaplayer.R;
import com.example.mediaplayer.util.Constants;
import com.example.mediaplayer.view.VideoFragment;

public class DispalyActivity extends Activity {

	private int displayType = Constants.DISPLAY_TYPE_VIDEO; // 当前播放多媒体文件的类型
															// 音频/视频

	private View mvAudio, mvVideo;
	private SurfaceView mSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dispaly);
		initView(); // 初始化界面

		if (displayType == Constants.DISPLAY_TYPE_AUDIO) {
			mvAudio.setVisibility(View.VISIBLE);
			mvVideo.setVisibility(View.GONE);
			mSurfaceView.setVisibility(View.GONE);
		} else if (displayType == Constants.DISPLAY_TYPE_VIDEO) {
			mvVideo.setVisibility(View.VISIBLE);
			mvAudio.setVisibility(View.GONE);
			mSurfaceView.setVisibility(View.GONE);

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

}
