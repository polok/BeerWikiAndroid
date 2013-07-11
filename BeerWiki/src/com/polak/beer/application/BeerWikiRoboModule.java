package com.polak.beer.application;

import com.google.inject.AbstractModule;
import com.polak.beer.service.BeerWikiBeerService;
import com.polak.beer.service.BeerWikiBreweryService;
import com.polak.beer.service.BeerWikiToastService;
import com.polak.beer.service.contract.BeerService;
import com.polak.beer.service.contract.BreweryService;
import com.polak.beer.service.contract.ToastService;
import com.polak.beer.service.mock.BeerWikiBeerServiceMock;

public class BeerWikiRoboModule extends AbstractModule {

	@Override
	protected void configure() {
//		bind(BeerService.class).to(BeerWikiBeerServiceMock.class);
		bind(BeerService.class).to(BeerWikiBeerService.class);
		bind(BreweryService.class).to(BeerWikiBreweryService.class);
		bind(ToastService.class).to(BeerWikiToastService.class);
	}

}
