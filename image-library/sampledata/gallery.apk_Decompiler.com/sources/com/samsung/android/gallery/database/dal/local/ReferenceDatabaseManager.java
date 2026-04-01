package com.samsung.android.gallery.database.dal.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReferenceDatabaseManager {
    private final ContentResolver mResolver;

    public ReferenceDatabaseManager(ContentResolver contentResolver) {
        this.mResolver = contentResolver;
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        try {
            return this.mResolver.bulkInsert(uri, contentValuesArr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        try {
            return this.mResolver.delete(uri, str, strArr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteMatchInclude(Uri uri, String str, String[] strArr) {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("android:query-arg-match-trashed", 1);
            bundle.putString("android:query-arg-sql-selection", str);
            bundle.putStringArray("android:query-arg-sql-selection-args", strArr);
            return this.mResolver.delete(uri, bundle);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            return this.mResolver.insert(uri, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            return this.mResolver.query(uri, strArr, str, strArr2, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            return this.mResolver.update(uri, contentValues, str, strArr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Cursor query(Uri uri, String[] strArr, Bundle bundle) {
        try {
            return this.mResolver.query(uri, strArr, bundle, (CancellationSignal) null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int update(Uri uri, ContentValues contentValues, Bundle bundle) {
        try {
            return this.mResolver.update(uri, contentValues, bundle);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
