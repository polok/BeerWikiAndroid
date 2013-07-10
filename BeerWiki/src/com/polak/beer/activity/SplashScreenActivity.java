package com.polak.beer.activity;

import android.content.Intent;
import android.os.Bundle;
import com.polak.beer.R;
import com.polak.beer.fragment.activity.DashboardFragmentActivity;
import roboguice.activity.RoboSplashActivity;

public class SplashScreenActivity extends RoboSplashActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.a_splashscreen);
	}

	@Override
	protected void startNextActivity() {
		Intent intent = new Intent(this, DashboardFragmentActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
