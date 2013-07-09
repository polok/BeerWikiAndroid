package com.polak.beer.service.contract;

import java.util.List;

import com.polak.beer.entity.Brewery;

public interface BreweryService {

	List<Brewery> getAllBreweries();

	Brewery getBreweryById(long id);

	List<Brewery> getBreweriesByQuery(String query);
}
