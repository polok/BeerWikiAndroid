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
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.google.inject.Inject;
import com.polak.beer.R;
import com.polak.beer.adapter.BeerAdapter;
import com.polak.beer.entity.Beer;
import com.polak.beer.service.contract.BeerService;
import com.polak.beer.service.contract.ToastService;
import roboguice.RoboGuice;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * User: marcin
 * Date: 10.07.13
 * Time: 21:07
 */
public class BeerListFragment extends RoboSherlockListFragment implements LoaderManager.LoaderCallbacks<List<Beer>> {

    @Inject
    private ToastService toastService;

    @InjectView(android.R.id.list)
    private ListView listView;

    private OnBeerSelectionListener beerSelectionListener;
    private BeerAdapter beerAdapter;

    public interface OnBeerSelectionListener {
        public void onBeerSelected(String beerDesc);
    }

    public static BeerListFragment buildBeerDetailsFragment() {
        return new BeerListFragment();
    }

    @Override
    public Loader<List<Beer>> onCreateLoader(int i, Bundle bundle) {
        return new BeerLoader(getActivity());

    }

    @Override
    public void onLoadFinished(Loader<List<Beer>> listLoader, List<Beer> beers) {
        beerAdapter.updateDataEntries(beers);
    }

    @Override
    public void onLoaderReset(Loader<List<Beer>> listLoader) {
        beerAdapter.updateDataEntries(null);

        if(isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            beerSelectionListener = (OnBeerSelectionListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString() + " must implement OnBeerSelectionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a_beer_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        beerAdapter = new BeerAdapter(getActivity());

        listView.setAdapter(beerAdapter);

        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    public static class BeerLoader extends AsyncTaskLoader<List<Beer>> {

        @Inject
        private BeerService beerService;

        public BeerLoader(Context context) {
            super(context);
            RoboGuice.getInjector(context).injectMembers(this);
        }

        @Override
        public List<Beer> loadInBackground() {
            return beerService.getAllBeers();
        }
    }

}
