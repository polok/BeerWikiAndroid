package com.polak.beer.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.google.inject.Inject;
import com.polak.beer.R;
import com.polak.beer.adapter.BeerAdapter;
import com.polak.beer.adapter.BreweryAdapter;
import com.polak.beer.entity.Beer;
import com.polak.beer.entity.Brewery;
import com.polak.beer.service.contract.BeerService;
import com.polak.beer.service.contract.BreweryService;
import roboguice.RoboGuice;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * User: marcin
 * Date: 11.07.13
 * Time: 21:42
 */
public class BreweryListFragment extends RoboSherlockListFragment implements LoaderManager.LoaderCallbacks<List<Brewery>> {

    @InjectView(android.R.id.list)
    private ListView listView;

    private BreweryAdapter breweryAdapter;
    private OnBrewerySelectionListener brewerySelectionListener;

    public static BreweryListFragment buildBreweryListFragment() {
        return new BreweryListFragment();
    }

    public interface OnBrewerySelectionListener {
        public void onBrewerySelected(String url);
    }

    @Override
    public Loader<List<Brewery>> onCreateLoader(int i, Bundle bundle) {
        return new BreweryLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Brewery>> listLoader, List<Brewery> breweries) {
        breweryAdapter.updateDataEntries(breweries);
    }

    @Override
    public void onLoaderReset(Loader<List<Brewery>> listLoader) {
        breweryAdapter.updateDataEntries(null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            brewerySelectionListener = (OnBrewerySelectionListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString() + " must implement OnBrewerySelectionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a_brewery_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        breweryAdapter = new BreweryAdapter(getActivity());

        listView.setAdapter(breweryAdapter);

        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        brewerySelectionListener.onBrewerySelected(breweryAdapter.getItem(position).getUrl());
    }

    public static class BreweryLoader extends AsyncTaskLoader<List<Brewery>> {

        @Inject
        private BreweryService breweryService;

        public BreweryLoader(Context context) {
            super(context);
            RoboGuice.getInjector(context).injectMembers(this);
        }

        @Override
        public List<Brewery> loadInBackground() {
            return breweryService.getAllBreweries();
        }
    }

}
