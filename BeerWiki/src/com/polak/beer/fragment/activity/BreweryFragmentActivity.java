package com.polak.beer.fragment.activity;

import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.polak.beer.R;
import com.polak.beer.fragment.BeerListFragment;
import com.polak.beer.fragment.BreweryListFragment;
import roboguice.inject.ContentView;

/**
 * User: marcin
 * Date: 11.07.13
 * Time: 21:40
 */
@ContentView(R.layout.a_fragment_brewery)
public class BreweryFragmentActivity extends RoboSherlockFragmentActivity implements BreweryListFragment.OnBrewerySelectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (findViewById(R.id.ll_brewery_fragment_container) != null) {
            BreweryListFragment breweryListFragment = BreweryListFragment.buildBreweryListFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.ll_brewery_fragment_container, breweryListFragment).commit();
        }
    }

    @Override
    public void onBrewerySelected(String url) {
    }
}
