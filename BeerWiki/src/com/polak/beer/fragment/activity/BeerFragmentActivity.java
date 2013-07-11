package com.polak.beer.fragment.activity;

import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.polak.beer.R;
import com.polak.beer.fragment.BeerListFragment;
import roboguice.activity.RoboFragmentActivity;
import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;
import roboguice.inject.InjectFragment;

/**
 * User: marcin
 * Date: 10.07.13
 * Time: 21:00
 */
@ContentView(R.layout.a_fragment_beer)
public class BeerFragmentActivity extends RoboSherlockFragmentActivity implements BeerListFragment.OnBeerSelectionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(findViewById(R.id.ll_beer_fragment_container) != null) {
            BeerListFragment beerListFragment = BeerListFragment.buildBeerDetailsFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.ll_beer_fragment_container, beerListFragment).commit();
        }
    }

    @Override
    public void onBeerSelected(String beerDesc) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
