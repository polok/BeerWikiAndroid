package com.polak.beer.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * User: marcin
 * Date: 14.07.13
 * Time: 15:33
 */
public class BeerWikiProvider extends ContentProvider {

    private static final String TAG = "BeerWikiProvider";

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

        switch (match) {
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
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        long id = 0;
        Uri uriTmp = null;

        switch (match) {
            case BEERS_CODE:
                id = sqLiteDatabase.insert(BeerWikiDatabase.Tables.BEERS, null, contentValues);
                uriTmp = Uri.parse(BeerWikiContract.Beers.CONTENT_URI + "/" + id);
                break;
            case BREWERIES_CODE:
                id = sqLiteDatabase.insert(BeerWikiDatabase.Tables.BREWERY, null, contentValues);
                uriTmp = Uri.parse(BeerWikiContract.Breweries.CONTENT_URI + "/" + id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        Log.d(TAG, "Added item with id " + id);
        return uriTmp;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int rowsDeleted = 0;

        switch (match) {
            case BEERS_CODE:
                rowsDeleted = sqLiteDatabase.delete(BeerWikiDatabase.Tables.BEERS, selection, selectionArgs);
                break;
            case BEERS_ID_CODE:
                String beerId = uri.getLastPathSegment();
                rowsDeleted = sqLiteDatabase.delete(BeerWikiDatabase.Tables.BEERS, BeerWikiContract.Beers.BEER_ID + "=" + beerId, null);
                break;
            case BREWERIES_CODE:
                rowsDeleted = sqLiteDatabase.delete(BeerWikiDatabase.Tables.BREWERY, selection, selectionArgs);
                break;
            case BREWERIES_ID_CODE:
                String brerweryId = uri.getLastPathSegment();
                rowsDeleted = sqLiteDatabase.delete(BeerWikiDatabase.Tables.BREWERY, BeerWikiContract.Breweries.BREWERY_ID + "=" + brerweryId, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        Log.d(TAG, "Deleted rows count: " + rowsDeleted);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        int updateCount = 0;

        switch (match) {
            case BEERS_CODE:
                updateCount = sqLiteDatabase.update(BeerWikiDatabase.Tables.BEERS, contentValues, selection, selectionArgs);
                break;
            case BEERS_ID_CODE:
                String beerId = uri.getLastPathSegment();
                updateCount = sqLiteDatabase.update(BeerWikiDatabase.Tables.BEERS, contentValues, BeerWikiContract.Beers.BEER_ID + "" + beerId, null);
                break;
            case BREWERIES_CODE:
                updateCount = sqLiteDatabase.update(BeerWikiDatabase.Tables.BREWERY, contentValues, selection, selectionArgs);
                break;
            case BREWERIES_ID_CODE:
                String breweryId = uri.getLastPathSegment();
                updateCount = sqLiteDatabase.update(BeerWikiDatabase.Tables.BREWERY, contentValues, BeerWikiContract.Breweries.BREWERY_ID + "" + breweryId, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        Log.d(TAG, "Updated rows count: " + updateCount);

        return updateCount;
    }
}
