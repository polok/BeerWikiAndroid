package com.polak.beer.activity;

import roboguice.inject.ContentView;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.view.Menu;
import com.polak.beer.R;
import com.polak.beer.activity.robosherlock.RoboSherlockActivity;
import com.polak.beer.util.BeerWikiActivityUtil;

@ContentView(R.layout.a_dashboard)
public class DashboardActivity extends RoboSherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.a_dashboard, menu);
		menu.findItem(R.id.menu_about).setIntent(BeerWikiActivityUtil.buildActivityIntent(this, AboutActivity.class));
		return true;
	}
	
	public void onBeerClicked(View view) {
		startActivity(BeerWikiActivityUtil.buildActivityIntent(this, BeerListActivity.class));
	}

	public void onBreweryClicked(View view) {
		startActivity(BeerWikiActivityUtil.buildActivityIntent(this, BreweryListActivity.class));
	}

}
