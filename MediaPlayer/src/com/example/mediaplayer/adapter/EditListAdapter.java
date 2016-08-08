package com.example.mediaplayer.adapter;

import com.example.mediaplayer.R;
import com.example.mediaplayer.R.layout;
import com.example.mediaplayer.util.Constants;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EditListAdapter extends BaseAdapter {

	private Context context;
	private int displayType;

	public EditListAdapter(Context context, int displayType) {
		super();
		this.context = context;
		this.displayType = displayType;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = View.inflate(context, layout.inc_edit_item_list, null);
		TextView textView = (TextView) convertView.findViewById(R.id.name);

		if (displayType == Constants.DISPLAY_TYPE_AUDIO) {
			textView.setText("audio");
		} else if (displayType == Constants.DISPLAY_TYPE_VIDEO) {
			textView.setText("video");
		}

		return convertView;
	}

}
