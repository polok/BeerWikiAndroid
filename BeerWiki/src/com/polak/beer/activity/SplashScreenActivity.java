package com.polak.beer.activity;

import com.polak.beer.R;

import roboguice.activity.RoboSplashActivity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends RoboSplashActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.a_splashscreen);
	}

	@Override
	protected void startNextActivity() {
		Intent intent = new Intent(this, DashboardActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
