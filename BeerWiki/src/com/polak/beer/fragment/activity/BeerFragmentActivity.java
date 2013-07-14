package com.polak.beer.fragment.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.polak.beer.R;
import com.polak.beer.fragment.BeerDetailsFragment;
import com.polak.beer.fragment.BeerListFragment;
import roboguice.inject.ContentView;

/**
 * User: marcin
 * Date: 10.07.13
 * Time: 21:00
 */
@ContentView(R.layout.a_fragment_beer)
public class BeerFragmentActivity extends RoboSherlockFragmentActivity implements BeerListFragment.OnBeerSelectionListener{

//    @Nullable @InjectView(R.id.ll_beer_fragment_container)
    private LinearLayout fragmentContainer;

//    @InjectFragment(R.id.beer_details)
//    private BeerDetailsFragment beerDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentContainer = (LinearLayout) findViewById(R.id.ll_beer_fragment_container);

        if(fragmentContainer != null) {
            BeerListFragment beerListFragment = BeerListFragment.buildBeerListFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.ll_beer_fragment_container, beerListFragment).commit();
        }
    }

    @Override
    public void onBeerSelected(String beerDesc) {
        if(fragmentContainer != null) {
            BeerDetailsFragment beerDetailsFragment = BeerDetailsFragment.buildBeerDetailsFragmentWithDesc(beerDesc);
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.ll_beer_fragment_container, beerDetailsFragment).commit();
        } else {
            BeerDetailsFragment beerDetailsFragment = (BeerDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.beer_details);
            beerDetailsFragment.updateBeerDescription(beerDesc);
        }
    }
}
