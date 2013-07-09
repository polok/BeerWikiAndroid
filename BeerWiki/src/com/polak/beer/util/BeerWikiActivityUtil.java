package com.polak.beer.util;

import android.content.Context;
import android.content.Intent;

public final class BeerWikiActivityUtil {

	public static <T> Intent buildActivityIntent(Context context, Class<T> activityClass) {
		Intent intent = new Intent(context, activityClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return intent;
	}
}
