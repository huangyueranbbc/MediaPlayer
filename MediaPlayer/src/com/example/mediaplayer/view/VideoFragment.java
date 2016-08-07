package com.example.mediaplayer.view;

import com.example.mediaplayer.R;
import com.example.mediaplayer.activity.DisplayActivity;
import com.example.mediaplayer.util.Constants;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VideoFragment extends Fragment {

	private View root; // 界面根布局
	private DisplayActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		activity = (DisplayActivity) this.getActivity();
		root = inflater.inflate(R.layout.inc_video_fragment, null);
		return root;
	}

	// 更新播放状态
	public void updateDisplayState(int displayState) {
		switch (displayState) {
		case Constants.DISPLAY_PAUSE:

			break;
		case Constants.DISPLAY_PLAY:

			break;
		default:
			break;
		}
	}

	public void BtnOnClick(View view) {
		switch (view.getId()) {
		case R.id.button_video_playstate:
			// 切换播放状态
			activity.displayStateSwitch();
			break;

		default:
			break;
		}
	}
}
