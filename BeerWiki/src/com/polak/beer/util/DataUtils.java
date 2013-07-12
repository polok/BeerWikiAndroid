package com.polak.beer.util;

import android.os.Bundle;

/**
 * User: marcin
 * Date: 12.07.13
 * Time: 13:08
 */
public final class DataUtils {

    public static Bundle createBundleWithStringData(String bundleKey,String data) {
        Bundle bundle = new Bundle();
        bundle.putString(bundleKey, data);
        return bundle;
    }

}
