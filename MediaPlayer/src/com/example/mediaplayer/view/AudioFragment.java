package com.example.mediaplayer.view;

import com.example.mediaplayer.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AudioFragment extends Fragment {

	private View root; // 界面根元素

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.inc_audio_fragment, null);
		return root;
	}
}
