package com.polak.beer.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.view.Menu;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.polak.beer.R;
import com.polak.beer.activity.AboutActivity;
import com.polak.beer.activity.BeerListActivity;
import com.polak.beer.activity.BreweryListActivity;
import com.polak.beer.util.BeerWikiActivityUtil;
import roboguice.inject.ContentView;

/**
 * User: marcin
 * Date: 10.07.13
 * Time: 20:42
 */
@ContentView(R.layout.a_dashboard)
public class DashboardFragmentActivity extends RoboSherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        startActivity(BeerWikiActivityUtil.buildActivityIntent(this, BeerFragmentActivity.class));
    }

    public void onBreweryClicked(View view) {
        startActivity(BeerWikiActivityUtil.buildActivityIntent(this, BreweryFragmentActivity.class));
    }

}
