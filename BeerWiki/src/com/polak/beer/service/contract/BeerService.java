package com.polak.beer.service.contract;

import java.util.List;

import com.polak.beer.entity.Beer;

public interface BeerService {

	List<Beer> getAllBeers();
	
	Beer getBeerById(long id);
	
	List<Beer> getBeersByQuery(String query);

}
