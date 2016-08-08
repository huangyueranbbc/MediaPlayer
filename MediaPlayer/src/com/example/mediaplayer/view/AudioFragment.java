package com.example.mediaplayer.view;

import com.example.mediaplayer.R;
import com.example.mediaplayer.activity.DisplayActivity;
import com.example.mediaplayer.activity.EditListActivity;
import com.example.mediaplayer.service.AudioPlayerService;
import com.example.mediaplayer.util.Constants;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AudioFragment extends Fragment implements IUpdateDisplayState {

	private static final String Tag = "AudioFragment";

	private View root; // 界面根元素
	private DisplayActivity context;
	private Button btnPlayState;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.inc_audio_fragment, null);
		initScreenView();
		context = (DisplayActivity) this.getActivity();
		AudioPlayerService.setIUpdateDisplayState(this);
		
		// 恢复播放状态
		excuteControlAction(Constants.ACTION_AUDIO_RESTORE_INFO);
		
		return root;
	}

	private void initScreenView() {
		btnPlayState = (Button) root.findViewById(R.id.btn_audio_playstate);
		
	}

	public void BtnOnClick(View view) {
		switch (view.getId()) {
		case R.id.btn_audio_playstate:
			excuteControlAction(Constants.ACTION_AUDIO_PLAY_STATE_SWITCH);
			break;
		case R.id.btn_edit_display:
			Intent intentEdit = new Intent(context, EditListActivity.class);
			intentEdit.putExtra(Constants.DISPLAY_TYPE, Constants.DISPLAY_TYPE_AUDIO);
			context.startActivityForResult(intentEdit, 0);
			break;
		default:
			break;
		}
	}

	/**
	 * 更新播放状态
	 */
	public void updateDisplayState(int displayState) {
		if (Constants.DISPLAY_PAUSE == displayState) {
			btnPlayState.setBackgroundResource(R.drawable.btn_playstate_play);
		} else if (Constants.DISPLAY_PLAY == displayState) {
			btnPlayState.setBackgroundResource(R.drawable.btn_playstate_pause);

		}
	}

	private void excuteControlAction(String action) {
		Intent intent = new Intent(context, AudioPlayerService.class);
		intent.putExtra("action", action);
		context.startService(intent);
	}

}
