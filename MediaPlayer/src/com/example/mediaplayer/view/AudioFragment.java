package com.example.mediaplayer.view;

import com.example.mediaplayer.R;
import com.example.mediaplayer.activity.DisplayActivity;
import com.example.mediaplayer.service.AudioPlayerService;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AudioFragment extends Fragment implements IUpdateDisplayState {

	private static final String Tag = "AudioFragment";

	private View root; // 界面根元素
	private DisplayActivity context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.inc_audio_fragment, null);
		context = (DisplayActivity) this.getActivity();
		AudioPlayerService.setIUpdateDisplayState(this);
		return root;
	}

	public void BtnOnClick(View view) {
		switch (view.getId()) {
		case R.id.button_audio_playstate:
			Intent intent = new Intent(context, AudioPlayerService.class);
			context.startService(intent);
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 */
	public void updateDisplayState() {
		Log.i(Tag, "updateDisplayState");
	}

}
