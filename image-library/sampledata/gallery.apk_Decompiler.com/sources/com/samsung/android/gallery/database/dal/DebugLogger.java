package com.samsung.android.gallery.database.dal;

import android.content.ContentResolver;
import android.content.ContentValues;
import bc.d;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import i4.C0468a;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DebugLogger {
    private static final ConcurrentHashMap<Integer, DebugLogger> mHashMap = new ConcurrentHashMap<>();
    private final int mCategory;
    private final ContentResolver mContentResolver = AppResources.getAppContext().getContentResolver();

    private DebugLogger(int i2) {
        this.mCategory = i2;
    }

    public static /* synthetic */ DebugLogger b(int i2) {
        return new DebugLogger(i2);
    }

    public static DebugLogger getDecodeInstance() {
        return getInstance(1);
    }

    public static DebugLogger getDeleteInstance() {
        return getInstance(2);
    }

    public static DebugLogger getEditDateAndTimeInstance() {
        return getInstance(8);
    }

    public static DebugLogger getEditLocationInstance() {
        return getInstance(7);
    }

    public static DebugLogger getInstance(int i2) {
        return mHashMap.computeIfAbsent(Integer.valueOf(i2), new C0468a(22));
    }

    public static DebugLogger getMdeInstance() {
        return getInstance(4);
    }

    public static DebugLogger getMtpInstance() {
        return getInstance(6);
    }

    public static DebugLogger getPppInstance() {
        return getInstance(9);
    }

    public static DebugLogger getRecapInstance() {
        return getInstance(10);
    }

    public void apply(String str, String str2) {
        ThreadUtil.postOnBgThreadNoDebug(new d((Object) this, (Object) str, (Object) str2, 27));
    }

    /* renamed from: insertLog */
    public void lambda$apply$0(String str, String str2) {
        insertLog(str + ": " + str2);
    }

    public static DebugLogger getInstance() {
        return getInstance(5);
    }

    public void insertLog(String str, String str2, String str3, String str4) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("__category", Integer.valueOf(this.mCategory));
            contentValues.put("__timestamp", TimeUtil.getIsoLocalDateTimeWithMillis(System.currentTimeMillis()));
            contentValues.put("__log", str + ": " + str2);
            if (str3 != null) {
                contentValues.put("volume", str3);
            }
            if (str4 != null) {
                contentValues.put("hash", str4);
            }
            this.mContentResolver.insert(LocalDatabase.LOG_URI, contentValues);
        } catch (Exception unused) {
        }
    }

    public void insertLog(String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("__category", Integer.valueOf(this.mCategory));
            contentValues.put("__timestamp", TimeUtil.getIsoLocalDateTimeWithMillis(System.currentTimeMillis()));
            contentValues.put("__log", str);
            this.mContentResolver.insert(LocalDatabase.LOG_URI, contentValues);
        } catch (Exception unused) {
        }
    }
}
