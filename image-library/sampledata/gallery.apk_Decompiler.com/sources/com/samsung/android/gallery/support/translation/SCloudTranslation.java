package com.samsung.android.gallery.support.translation;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SCloudTranslation implements ITranslation {
    private final HashMap<String, String> mTranslationMap = new HashMap<>();

    private static String getLanguageList() {
        return Locale.ENGLISH + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Locale.getDefault().toString();
    }

    private static Uri getUriForEng(String str) {
        return new Uri.Builder().scheme("content").authority("com.samsung.android.scloud.media.multilingual").appendPath("text_search").appendPath(str).appendPath(Locale.getDefault().toString()).build();
    }

    private static Uri getUriForMap() {
        return new Uri.Builder().scheme("content").authority("com.samsung.android.scloud.media.multilingual").appendPath("get_map").appendPath("2").appendPath(getLanguageList()).build();
    }

    public String getEnglish(String str) {
        Cursor query;
        Throwable th;
        Context appContext = AppResources.getAppContext();
        if (appContext != null) {
            try {
                query = appContext.getContentResolver().query(getUriForEng(str), (String[]) null, (String) null, (String[]) null, (String) null);
                StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (query != null && query.moveToFirst()) {
                    do {
                        stringJoiner.add(query.getString(0));
                    } while (query.moveToNext());
                }
                String stringJoiner2 = stringJoiner.toString();
                if (query == null) {
                    return stringJoiner2;
                }
                query.close();
                return stringJoiner2;
            } catch (Exception e) {
                a.s(e, new StringBuilder("getEnglish failed. e="), "SCloudTranslation");
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        return str;
        throw th;
    }

    public void loadMap(Context context) {
        Cursor query;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            query = context.getContentResolver().query(getUriForMap(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        this.mTranslationMap.put(query.getString(0), query.getString(1));
                    } while (query.moveToNext());
                }
            }
            Log.d("SCloudTranslation", "loadMap {" + Locale.getDefault() + ',' + this.mTranslationMap.size() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            Exception exc = e;
            a.s(exc, new StringBuilder("loadMap failed. e="), "SCloudTranslation");
            if (exc instanceof SecurityException) {
                new InternalException("loadTranslationMap failed").post();
                return;
            }
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void releaseMap() {
        this.mTranslationMap.clear();
    }

    public String translate(String str) {
        return this.mTranslationMap.getOrDefault(str, str);
    }
}
