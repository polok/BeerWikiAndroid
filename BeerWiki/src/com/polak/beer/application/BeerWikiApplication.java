package com.polak.beer.application;

import roboguice.RoboGuice;
import android.app.Application;
import android.content.Context;

import com.google.inject.Module;

public class BeerWikiApplication extends Application {

	private static Context instance;

	@Override
	public void onCreate() {
		super.onCreate();

		instance = this;
		initRoboGuice();
	}

	private void initRoboGuice() {
		Module defaultRobomodule = RoboGuice.newDefaultRoboModule(this);
		Module beerWikiRoboModule = new BeerWikiRoboModule();

		RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, defaultRobomodule, beerWikiRoboModule);
	}

	public static Context getContext() {
		return instance.getApplicationContext();
	}

}
