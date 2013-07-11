package com.polak.beer.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.polak.beer.R;
import com.polak.beer.activity.robosherlock.RoboSherlockActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.a_beer_details)
public class BeerDetailsActivity extends RoboSherlockActivity {

	public static final String BEER_DESC_KEY = "beear_desc";
	private static final String NO_DESCRIPTION_TXT = "No description";

	@InjectView(R.id.tv_beer_desc)
	private TextView tvBeerDesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tvBeerDesc.setText(getBeerDescFromIntent());
	}

	private String getBeerDescFromIntent() {
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return NO_DESCRIPTION_TXT;
		}
		return extras.getString(BEER_DESC_KEY);
	}
}
