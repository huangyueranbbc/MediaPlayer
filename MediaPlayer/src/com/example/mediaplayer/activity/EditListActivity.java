package com.example.mediaplayer.activity;

import com.example.mediaplayer.R;
import com.example.mediaplayer.R.id;
import com.example.mediaplayer.R.layout;
import com.example.mediaplayer.R.menu;
import com.example.mediaplayer.adapter.EditListAdapter;
import com.example.mediaplayer.util.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class EditListActivity extends Activity {

	private ListView mEditListView;
	private EditListAdapter mEditListAdapter;
	private int displayType;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_list);
		// 获取当前的编辑播放列表类型
		Intent intent = getIntent();
		displayType = intent.getIntExtra(Constants.DISPLAY_TYPE, Constants.DISPLAY_TYPE_AUDIO);
		if (displayType == Constants.DISPLAY_TYPE_AUDIO) {
			
		} else if (displayType == Constants.DISPLAY_TYPE_VIDEO) {
			
		}

		initView();
		initData();

	}

	private void initView() {
		mEditListView = (ListView) findViewById(R.id.edit_list);
	}

	private void initData() {
		mEditListAdapter = new EditListAdapter(this, displayType);
		mEditListView.setAdapter(mEditListAdapter);
	}

}
