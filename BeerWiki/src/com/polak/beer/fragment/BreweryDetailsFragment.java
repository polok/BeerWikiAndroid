package com.polak.beer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.polak.beer.R;

/**
 * User: marcin
 * Date: 11.07.13
 * Time: 21:42
 */
public class BreweryDetailsFragment extends RoboSherlockFragment {

    public static BreweryDetailsFragment buildBreweryDetailsFragment() {
        return new BreweryDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_brewery_details, container, false);
    }

}
