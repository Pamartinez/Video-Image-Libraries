package com.sec.longexposure;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposureDBHandler {
    private final String TAG = "LongExposureDBHandler";
    private Context context;

    public LongExposureDBHandler(Context context2) {
        this.context = context2;
    }

    private Number[] queryClipInfo(String str) {
        Cursor query;
        Throwable th;
        Log.i("LongExposureDBHandler", "Query video clip info");
        Context context2 = this.context;
        if (context2 == null || context2.getContentResolver() == null) {
            Log.d("LongExposureDBHandler", "No context");
            return null;
        }
        try {
            query = this.context.getContentResolver().query(Uri.parse("content://com.samsung.videoscanprovider/mobile_video_clip"), new String[]{"sef_type", "longexposure_start_time", "longexposure_mode"}, "sec_media_id=?", new String[]{str}, (String) null);
            if (query != null) {
                if (query.moveToNext()) {
                    Log.i("LongExposureDBHandler", "query video clip count: " + query.getCount());
                    String string = query.getString(0);
                    if (string != null) {
                        if (string.contains("3441")) {
                            String string2 = query.getString(1);
                            String string3 = query.getString(2);
                            if (string2 != null) {
                                if (string3 != null) {
                                    Number[] numberArr = {Long.valueOf(Long.parseLong(string2)), Integer.valueOf(Integer.parseInt(string3))};
                                    query.close();
                                    return numberArr;
                                }
                            }
                            Log.d("LongExposureDBHandler", "Invalid recommended data");
                            query.close();
                            return null;
                        }
                    }
                    Log.d("LongExposureDBHandler", "No recommended data");
                    query.close();
                    return null;
                }
            }
            Log.d("LongExposureDBHandler", "No query data");
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (SecurityException unused) {
            Log.e("LongExposureDBHandler", "Need Permission to video scan provider");
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private String queryMediaId(String str) {
        Cursor query;
        Throwable th;
        Log.i("LongExposureDBHandler", "Query media id");
        Context context2 = this.context;
        if (context2 == null || context2.getContentResolver() == null) {
            Log.e("LongExposureDBHandler", "No context");
            return null;
        }
        try {
            query = this.context.getContentResolver().query(Uri.parse("content://secmedia/media?nonotify=1"), new String[]{"_id"}, "_data=?", new String[]{str}, (String) null);
            if (query != null) {
                if (query.moveToNext()) {
                    Log.i("LongExposureDBHandler", "query media count: " + query.getCount());
                    String string = query.getString(0);
                    query.close();
                    return string;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (SecurityException unused) {
            Log.e("LongExposureDBHandler", "Need permission");
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return null;
        throw th;
    }

    public Number[] getRecommendedInfo(String str) {
        Log.i("LongExposureDBHandler", "Skip to read recommended info");
        String queryMediaId = queryMediaId(str);
        if (queryMediaId != null) {
            return queryClipInfo(queryMediaId);
        }
        Log.i("LongExposureDBHandler", "Not matched media id");
        return null;
    }
}
