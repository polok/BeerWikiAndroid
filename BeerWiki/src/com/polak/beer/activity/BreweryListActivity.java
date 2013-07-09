package com.polak.beer.activity;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.polak.beer.R;
import com.polak.beer.activity.robosherlock.RoboSherlockListActivity;
import com.polak.beer.adapter.BreweryAdapter;
import com.polak.beer.entity.Brewery;
import com.polak.beer.service.contract.BreweryService;
import com.polak.beer.service.contract.ToastService;

@ContentView(R.layout.a_brewery_list)
public class BreweryListActivity extends RoboSherlockListActivity {

	@Inject
	private BreweryService breweryService;

	@Inject
	private ToastService toastService;

	@InjectView(android.R.id.list)
	private ListView listView;

	@InjectResource(R.string.dialog_loading_txt)
	private String dialogLoadingTxt;

	private BreweryAdapter breweryAdapter;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		breweryAdapter = new BreweryAdapter(this);
		listView.setAdapter(breweryAdapter);
		progressDialog = ProgressDialog.show(this, "", dialogLoadingTxt);

		new BreweryTask(this).execute();

	}

	private final class BreweryTask extends RoboAsyncTask<List<Brewery>> {

		private static final String TAG = "BREWERY_TASK";
		private static final String ERROR_TOAST_MSG = "Error during beers data download";

		protected BreweryTask(Context context) {
			super(context);
		}

		@Override
		public List<Brewery> call() throws Exception {
			Log.d(TAG, "Call for braweries data");
			progressDialog.show();
			return breweryService.getAllBreweries();
		}

		@Override
		protected void onSuccess(List<Brewery> beers) throws Exception {
			super.onSuccess(beers);
			breweryAdapter.updateDataEntries(beers);
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
