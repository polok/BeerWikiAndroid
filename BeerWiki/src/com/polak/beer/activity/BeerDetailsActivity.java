package com.polak.beer.activity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.TextView;

import com.polak.beer.R;
import com.polak.beer.activity.robosherlock.RoboSherlockActivity;

@ContentView(R.layout.a_beer_details)
public class BeerDetailsActivity extends RoboSherlockActivity {

	public static final String BEER_DESC_KEY = "beear_desc";
	private static final String NO_DESCRIPTION_TXT = "No description";

	@InjectView(R.id.tv_beer_desc)
	private TextView tvBeerDesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tvBeerDesc.setText(getBeearDescFromIntent());
	}

	private String getBeearDescFromIntent() {
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return NO_DESCRIPTION_TXT;
		}
		return extras.getString(BEER_DESC_KEY);
	}
}
