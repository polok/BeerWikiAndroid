package com.polak.beer.service.mock;

import java.util.ArrayList;
import java.util.List;

import com.polak.beer.entity.Beer;
import com.polak.beer.service.contract.BeerService;

public class BeerWikiBeerServiceMock implements BeerService {

	@Override
	public List<Beer> getAllBeers() {
        List<Beer> beers = new ArrayList<Beer>();
        beers.add(new Beer("Name Test", "Name desc", 3));
		return beers;
	}

	@Override
	public Beer getBeerById(long id) {
		return new Beer();
	}

	@Override
	public List<Beer> getBeersByQuery(String query) {
		return new ArrayList<Beer>();
	}

}
