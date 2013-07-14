package com.polak.beer.fragment.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.polak.beer.R;
import com.polak.beer.fragment.BreweryDetailsFragment;
import com.polak.beer.fragment.BreweryListFragment;
import roboguice.inject.ContentView;

/**
 * User: marcin
 * Date: 11.07.13
 * Time: 21:40
 */
@ContentView(R.layout.a_fragment_brewery)
public class BreweryFragmentActivity extends RoboSherlockFragmentActivity implements BreweryListFragment.OnBrewerySelectionListener {

    private LinearLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentContainer = (LinearLayout) findViewById(R.id.ll_brewery_fragment_container);

        if (fragmentContainer != null) {
            BreweryListFragment breweryListFragment = BreweryListFragment.buildBreweryListFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.ll_brewery_fragment_container, breweryListFragment).commit();
        }
    }

    @Override
    public void onBrewerySelected(String urlBrewery) {
        if(fragmentContainer != null) {
            BreweryDetailsFragment breweryDetailsFragment = BreweryDetailsFragment.buildBreweryDetailsFragment(urlBrewery);
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.ll_brewery_fragment_container, breweryDetailsFragment).commit();
        } else {
            BreweryDetailsFragment breweryDetailsFragment = (BreweryDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.brewery_details);
            breweryDetailsFragment.loadBreweryPage(urlBrewery);
        }


    }
}
