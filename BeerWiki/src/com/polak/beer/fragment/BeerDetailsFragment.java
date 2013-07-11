package com.polak.beer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.polak.beer.R;

/**
 * User: marcin
 * Date: 10.07.13
 * Time: 21:08
 */
public class BeerDetailsFragment extends RoboSherlockFragment {

    public static BeerDetailsFragment buildBeerDetailsFragment() {
        return new BeerDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a_beer_details, container, false);
    }
}
