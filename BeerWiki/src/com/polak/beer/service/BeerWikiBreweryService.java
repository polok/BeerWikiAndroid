package com.polak.beer.service;

import com.polak.beer.R;
import com.polak.beer.entity.Brewery;
import com.polak.beer.entity.BreweryPage;
import com.polak.beer.service.contract.BreweryService;
import com.polak.beer.util.BeerWikiStringUtils;
import roboguice.inject.InjectResource;

import java.util.List;

public class BeerWikiBreweryService extends WebServiceManager implements BreweryService {

    @InjectResource(R.string.ws_host_url)
    private String wsHostUrl;

    @InjectResource(R.string.ws_breweries_get_url)
    private String wsBreweriesUrl;

    @InjectResource(R.string.ws_brewery_get_by_id_url)
    private String wsBreweryByIdUrl;

    @InjectResource(R.string.ws_beers_get_by_query_url)
    private String wsBreweriesByQueryUrl;

    @Override
    public List<Brewery> getAllBreweries() {
        String url = BeerWikiStringUtils.linkStrings(wsHostUrl, wsBreweriesUrl);
        return getForEntity(url, BreweryPage.class).getBreweries();
    }

    @Override
    public Brewery getBreweryById(long id) {
        return null;
    }

    @Override
    public List<Brewery> getBreweriesByQuery(String query) {
        return null;
    }

}
