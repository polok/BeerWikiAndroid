package com.polak.beer.provider;

import static com.polak.beer.provider.BeerWikiContract.BeersColumns.BEER_AVB;
import static com.polak.beer.provider.BeerWikiContract.BeersColumns.BEER_DESC;
import static com.polak.beer.provider.BeerWikiContract.BeersColumns.BEER_ID;
import static com.polak.beer.provider.BeerWikiContract.BeersColumns.BEER_NAME;
import static com.polak.beer.provider.BeerWikiContract.BreweriesColumns.BREWERY_DESC;
import static com.polak.beer.provider.BeerWikiContract.BreweriesColumns.BREWERY_ID;
import static com.polak.beer.provider.BeerWikiContract.BreweriesColumns.BREWERY_NAME;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class BeerWikiDatabase extends SQLiteOpenHelper {

	private static final String BEERWIKI_DATABASE_NAME = "beerwiki.db";
	private static final int BEERWIKI_DATABASE_VERSION = 1;

	interface Tables {
		String BEERS = "beer";
		String BREWERY = "brewery";
	}

	public BeerWikiDatabase(Context context) {
		super(context, BEERWIKI_DATABASE_NAME, null, BEERWIKI_DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + Tables.BEERS + " (" //
				+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," //
				+ BEER_ID + " TEXT NOT NULL," //
				+ BEER_NAME + "TEXT NOT NULL," //
				+ BEER_DESC + "TEXT NOT NULL," //
				+ BEER_AVB + "REAL NOT NULL)");

		db.execSQL("CREATE TABLE " + Tables.BREWERY + " (" //
				+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," //
				+ BREWERY_ID + " TEXT NOT NULL," //
				+ BREWERY_NAME + "TEXT NOT NULL," //
				+ BREWERY_DESC + "TEXT NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(BeerWikiDatabase.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + Tables.BEERS);
		db.execSQL("DROP TABLE IF EXISTS " + Tables.BREWERY);
		onCreate(db);
	}

}
