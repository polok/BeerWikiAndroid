package com.polak.beer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.polak.beer.R;
import com.polak.beer.util.DataUtils;
import roboguice.inject.InjectView;

/**
 * User: marcin
 * Date: 10.07.13
 * Time: 21:08
 */
public class BeerDetailsFragment extends RoboSherlockFragment {

    public static final String BEER_DESC_KEY = "beear_desc";
    private static final String NO_DESCRIPTION_TXT = "No description";

    @InjectView(R.id.tv_beer_desc)
    private TextView tvBeerDesc;

    public static BeerDetailsFragment buildBeerDetailsFragmentWithDesc(String beerDesc) {
        BeerDetailsFragment fragment = new BeerDetailsFragment();
        fragment.setArguments(DataUtils.createBundleWithStringData(BEER_DESC_KEY, beerDesc));
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a_beer_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateBeerDescription(null);
    }

    public void updateBeerDescription(String beerDesc) {
        if(beerDesc != null) {
            tvBeerDesc.setText(beerDesc);
        } else {
            tvBeerDesc.setText(getBeerDescFromIntent());
        }
    }

    private String getBeerDescFromIntent() {
        Bundle extras = getArguments();
        if (extras == null) {
            return NO_DESCRIPTION_TXT;
        }
        return extras.getString(BEER_DESC_KEY);
    }
}
