package com.samsung.android.gallery.module.download;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DownloadHelper {
    public static Uri convertToMediaProviderUri(Context context, Uri uri) {
        Cursor query;
        Throwable th;
        if (context == null) {
            Log.e("DownloadHelper", "ctx null");
            return null;
        }
        try {
            query = context.getContentResolver().query(uri, new String[]{"mediaprovider_uri"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    if (TextUtils.isEmpty(string)) {
                        query.close();
                        return null;
                    }
                    Uri parse = Uri.parse(string);
                    if (!MediaUri.getInstance().matches(string)) {
                        parse = null;
                    }
                    query.close();
                    return parse;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (SQLiteException e) {
            SQLiteException sQLiteException = e;
            Log.d("DownloadHelper", "convert to uri failed e=" + sQLiteException.getMessage());
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return null;
        throw th;
    }

    public static boolean isDownloadUri(Uri uri) {
        return uri.toString().startsWith("content://downloads/");
    }

    public static boolean isIncludePendingStateUri(Uri uri) {
        if (!MediaUri.getInstance().matches(uri.toString()) || !uri.toString().contains("?includePending=")) {
            return false;
        }
        return true;
    }

    public static Uri removePendingArguments(Uri uri) {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf("?includePending=");
        if (indexOf == -1) {
            return uri;
        }
        String substring = uri2.substring(0, indexOf);
        if (!TextUtils.isEmpty(substring)) {
            return Uri.parse(substring);
        }
        return uri;
    }
}
