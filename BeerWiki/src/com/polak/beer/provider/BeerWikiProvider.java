package com.polak.beer.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * User: marcin
 * Date: 14.07.13
 * Time: 15:33
 */
public class BeerWikiProvider extends ContentProvider {

    private static final int BEERS_CODE = 100;
    private static final int BEERS_ID_CODE = 101;

    private static final int BREWERIES_CODE = 200;
    private static final int BREWERIES_ID_CODE = 201;

    private static UriMatcher uriMatcher = buildUriMatcher();

    private BeerWikiDatabase database;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = BeerWikiContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, "beers", BEERS_CODE);
        matcher.addURI(authority, "beers/*", BEERS_ID_CODE);

        matcher.addURI(authority, "breweries", BREWERIES_CODE);
        matcher.addURI(authority, "breweries/*", BREWERIES_ID_CODE);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        database = new BeerWikiDatabase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = buildSqLiteQueryBuilder(uri);

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        // Make sure that listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    private SQLiteQueryBuilder buildSqLiteQueryBuilder(Uri uri) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        int match = uriMatcher.match(uri);

        switch(match) {
            case BREWERIES_CODE:
                builder.setTables(BeerWikiDatabase.Tables.BREWERY);
                break;
            case BREWERIES_ID_CODE:
                String breweryId = BeerWikiContract.Breweries.getBreweryId(uri);
                builder.setTables(BeerWikiDatabase.Tables.BREWERY);
                builder.appendWhere(BeerWikiContract.Breweries.BREWERY_ID + "=" + breweryId);
                break;
            case BEERS_CODE:
                builder.setTables(BeerWikiDatabase.Tables.BEERS);
                break;
            case BEERS_ID_CODE:
                String beerId = BeerWikiContract.Beers.getBeerId(uri);
                builder.setTables(BeerWikiDatabase.Tables.BEERS);
                builder.appendWhere(BeerWikiContract.Beers.BEER_ID + "=" + beerId);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return builder;
    }

    @Override
    public String getType(Uri uri) {
        final int type = uriMatcher.match(uri);
        switch (type) {
            case BEERS_CODE:
                return BeerWikiContract.Beers.CONTENT_TYPE;
            case BEERS_ID_CODE:
                return BeerWikiContract.Beers.CONTENT_ITEM_TYPE;
            case BREWERIES_CODE:
                return BeerWikiContract.Breweries.CONTENT_TYPE;
            case BREWERIES_ID_CODE:
                return BeerWikiContract.Breweries.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = uriMatcher.match(uri);
        long id = 0;
        Uri uriTmp = null;

        switch (match) {
            case BEERS_CODE:
                id = database.getWritableDatabase().insert(BeerWikiDatabase.Tables.BEERS, null, contentValues);
                uriTmp = Uri.parse(BeerWikiContract.Beers.CONTENT_URI + "/" + id);
                break;
            case BREWERIES_CODE:
                id = database.getWritableDatabase().insert(BeerWikiDatabase.Tables.BREWERY, null, contentValues);
                uriTmp = Uri.parse(BeerWikiContract.Breweries.CONTENT_URI + "/" + id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uriTmp;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        getContext().getContentResolver().notifyChange(uri, null); //TODO
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        getContext().getContentResolver().notifyChange(uri, null);       //TODO
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
