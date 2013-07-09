package com.polak.beer.provider;

import android.net.Uri;

public class BeerWikiContract {

	interface BeersColumns {
		String BEER_ID = "beer_id";
		String BEER_NAME = "beer_name";
		String BEER_AVB = "beer_avb";
		String BEER_DESC = "beer_desc";
	}

	interface BreweriesColumns {
		String BREWERY_ID = "brewery_id";
		String BREWERY_NAME = "brewery_name";
		String BREWERY_DESC = "brewery_desc";
	}
	
    public static final String CONTENT_AUTHORITY = "com.polak.beer.wiki";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    
    
    
}
