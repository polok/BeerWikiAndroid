package com.polak.beer.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

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
    
    private static final String PATH_BEER = "beers";
    private static final String PATH_BREWERY = "breweries";

    public static class Beers implements BeersColumns, BaseColumns {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.beerwiki.beer";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.beerwiki.beer";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_BEER).build();

        public static Uri buildBeerUri(String beerId) {
            return CONTENT_URI.buildUpon().appendPath(beerId).build();
        }

        public static String getBeerId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    public static class Breweries implements BreweriesColumns, BaseColumns {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.beerwiki.brewery";
        public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.beerwiki.brewery";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_BREWERY).build();

        public static Uri buildBrewery(String breweryId) {
            return CONTENT_URI.buildUpon().appendPath(breweryId).build();
        }

        public static String getBreweryId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }

}
