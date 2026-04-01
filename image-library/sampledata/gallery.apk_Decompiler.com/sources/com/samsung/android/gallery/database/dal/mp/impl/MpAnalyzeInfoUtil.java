package com.samsung.android.gallery.database.dal.mp.impl;

import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MpAnalyzeInfoUtil {
    public static final Uri URI = Uri.parse("content://secmedia/cmh/analyze_info");

    public static Cursor query(String[] strArr, String str, String[] strArr2, String str2) {
        return new SecMpQueryExecutor().query(URI, strArr, str, strArr2, str2);
    }
}
