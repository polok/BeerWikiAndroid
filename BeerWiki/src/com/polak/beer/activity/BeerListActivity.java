package com.polak.beer.activity;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.polak.beer.R;
import com.polak.beer.activity.robosherlock.RoboSherlockListActivity;
import com.polak.beer.adapter.BeerAdapter;
import com.polak.beer.entity.Beer;
import com.polak.beer.service.contract.BeerService;
import com.polak.beer.service.contract.ToastService;
import com.polak.beer.util.BeerWikiActivityUtil;

@ContentView(R.layout.a_beer_list)
public class BeerListActivity extends RoboSherlockListActivity {

	@Inject
	private BeerService beerService;
	
	@Inject
	private ToastService toastService;

	@InjectView(android.R.id.list)
	private ListView listView;

	@InjectResource(R.string.dialog_loading_txt)
	private String dialogLoadingTxt;

	private BeerAdapter beerAdapter;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		beerAdapter = new BeerAdapter(this);
		listView.setAdapter(beerAdapter);
		progressDialog = ProgressDialog.show(this, "", dialogLoadingTxt);

		new BeerTask(this).execute();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = BeerWikiActivityUtil.buildActivityIntent(this, BeerDetailsActivity.class);
		intent.putExtra(BeerDetailsActivity.BEER_DESC_KEY, beerAdapter.getItem(position).getDescription());
		startActivity(intent);
	}

	private final class BeerTask extends RoboAsyncTask<List<Beer>> {

		private static final String TAG = "BEER_TASK";
		private static final String ERROR_TOAST_MSG = "Error during breweries data download";

		protected BeerTask(Context context) {
			super(context);
		}

		@Override
		public List<Beer> call() throws Exception {
			Log.d(TAG, "Call for beer data");
			progressDialog.show();
			return beerService.getAllBeers();
		}

		@Override
		protected void onSuccess(List<Beer> beers) throws Exception {
			super.onSuccess(beers);
			beerAdapter.updateDataEntries(beers);
		}

		@Override
		protected void onException(Exception e) throws RuntimeException {
			super.onException(e);
			toastService.show(ERROR_TOAST_MSG, Toast.LENGTH_SHORT);
		}

		@Override
		protected void onFinally() throws RuntimeException {
			super.onFinally();
			progressDialog.dismiss();
		}
	}
}
