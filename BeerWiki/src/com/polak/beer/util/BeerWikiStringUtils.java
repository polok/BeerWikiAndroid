package com.polak.beer.util;

public final class BeerWikiStringUtils {

	public static String linkStrings(String... strings) {
		StringBuilder builder = new StringBuilder();
		for (String s : strings) {
			builder.append(s);
		}
		return builder.toString();
	}
}
