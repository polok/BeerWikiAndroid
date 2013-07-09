package com.polak.beer.service;

import android.widget.Toast;

import com.polak.beer.application.BeerWikiApplication;
import com.polak.beer.service.contract.ToastService;

public class BeerWikiToastService implements ToastService {

	@Override
	public void show(String text, int lenght) {
		Toast.makeText(BeerWikiApplication.getContext(), text, lenght).show();
	}
}
