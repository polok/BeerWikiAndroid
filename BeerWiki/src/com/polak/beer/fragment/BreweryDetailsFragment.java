package com.polak.beer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.polak.beer.R;
import com.polak.beer.util.DataUtils;
import roboguice.inject.InjectView;

/**
 * User: marcin
 * Date: 11.07.13
 * Time: 21:42
 */
public class BreweryDetailsFragment extends RoboSherlockFragment {

    private static final String NO_PAGE_ADDRESS = "UNKNOWN ADDRESS";
    private static final String BREWERY_PAGE_URL = "beear_desc";

    @InjectView(R.id.wv_brewery)
    private WebView webView;

    public static BreweryDetailsFragment buildBreweryDetailsFragment(String urlBrewery) {
        BreweryDetailsFragment breweryDetailsFragment = new BreweryDetailsFragment();
        breweryDetailsFragment.setArguments(DataUtils.createBundleWithStringData(BREWERY_PAGE_URL, urlBrewery));
        return breweryDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_brewery_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadBreweryPage(null);
    }

    public void loadBreweryPage(String url) {
        webView.clearView();
        if(url != null) {
            webView.loadUrl(url);
        } else {
            webView.loadUrl(extractBreweryUrlFromArguments());
        }
    }

    private String extractBreweryUrlFromArguments() {
        Bundle bundle = getArguments();
        if(bundle == null) {
            return NO_PAGE_ADDRESS;
        } else {
            return bundle.getString(BREWERY_PAGE_URL);
        }
    }
}
