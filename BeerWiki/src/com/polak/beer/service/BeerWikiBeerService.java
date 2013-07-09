package com.polak.beer.service;

import java.util.List;

import roboguice.inject.InjectResource;

import com.polak.beer.R;
import com.polak.beer.entity.Beer;
import com.polak.beer.entity.BeerPage;
import com.polak.beer.service.contract.BeerService;
import com.polak.beer.util.BeerWikiStringUtils;

public class BeerWikiBeerService extends WebServiceManager implements BeerService {

	@InjectResource(R.string.ws_host_url)
	private String wsHostUrl;

	@InjectResource(R.string.ws_beers_get_url)
	private String wsBeersUrl;

	@InjectResource(R.string.ws_beer_get_by_id_url)
	private String wsBeersByIdUrl;

	@InjectResource(R.string.ws_beers_get_by_query_url)
	private String wsBeersByQueryUrl;

	@Override
	public List<Beer> getAllBeers() {
		String url = BeerWikiStringUtils.linkStrings(wsHostUrl, wsBeersUrl);
		return getForEntity(url, BeerPage.class).getBeers();
	}

	@Override
	public Beer getBeerById(long id) {
		String url = BeerWikiStringUtils.linkStrings(wsHostUrl, wsBeersByIdUrl);
		return getForEntity(url, Beer.class);
	}

	@Override
	public List<Beer> getBeersByQuery(String query) {
		String url = BeerWikiStringUtils.linkStrings(wsHostUrl, wsBeersByQueryUrl, query);
		return getForEntity(url, BeerPage.class).getBeers();
	}

}
