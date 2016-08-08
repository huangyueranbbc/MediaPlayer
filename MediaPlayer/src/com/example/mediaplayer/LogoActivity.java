package com.example.mediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.mediaplayer.activity.DisplayActivity;
import com.example.mediaplayer.service.AudioPlayerService;

public class LogoActivity extends Activity implements AnimationListener {

	ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		intiView();

		if (AudioPlayerService.isPlaying()) {
			startActivity(new Intent(this, DisplayActivity.class));
			finish();
			return;
		}

		// 实现图片的淡入效果
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fadein);
		animation.setAnimationListener(this);
		mImageView.setAnimation(animation);
	}

	private void intiView() {
		mImageView = (ImageView) findViewById(R.id.iv_welcome);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		startActivity(new Intent(this, DisplayActivity.class));
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
