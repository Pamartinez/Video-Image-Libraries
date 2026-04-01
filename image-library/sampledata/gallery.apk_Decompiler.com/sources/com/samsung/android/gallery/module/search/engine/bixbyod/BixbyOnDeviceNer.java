package com.samsung.android.gallery.module.search.engine.bixbyod;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BixbyOnDeviceNer {
    private static final Uri URI = Uri.parse("content://com.samsung.android.bixby.odner");

    public static String load(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String string = AppResources.getAppContext().getContentResolver().call(URI, "ner://load", str, (Bundle) null).getString("output", (String) null);
            Log.s("BixbyOnDeviceNer", "load +" + (System.currentTimeMillis() - currentTimeMillis) + " " + Logger.getEncodedString(string));
            return string;
        } catch (Error | Exception e) {
            Log.se("BixbyOnDeviceNer", "load failed. e=" + e.getMessage());
            return null;
        }
    }

    public static boolean support(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        if ("en".equals(language) || "ko".equals(language)) {
            return true;
        }
        return false;
    }
}
