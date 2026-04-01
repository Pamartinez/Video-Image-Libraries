package com.samsung.android.gallery.module.fileio.database.helper;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DatabaseOpObject {
    Object mDataArgs;
    boolean mImmediateMode;
    Object mSelectionArgs;
    Object mTagDataArgs;
    int mType;
    Uri mUri;

    private DatabaseOpObject(Uri uri, int i2) {
        this.mUri = uri;
        this.mType = i2;
    }

    public static DatabaseOpObject burkInsert(Uri uri) {
        return new DatabaseOpObject(uri, 1);
    }

    public static DatabaseOpObject delete(Uri uri) {
        return new DatabaseOpObject(uri, 4);
    }

    public static DatabaseOpObject insert(Uri uri) {
        return new DatabaseOpObject(uri, 2);
    }

    public static DatabaseOpObject update(Uri uri) {
        return new DatabaseOpObject(uri, 3);
    }

    public DatabaseOpObject addMyTag(Object... objArr) {
        this.mTagDataArgs = objArr;
        return this;
    }

    public DatabaseOpObject addValues(Object obj) {
        this.mDataArgs = obj;
        return this;
    }

    public DatabaseOpObject immediate() {
        this.mImmediateMode = true;
        return this;
    }

    public DatabaseOpObject setImmediate(boolean z) {
        this.mImmediateMode = z;
        return this;
    }

    public DatabaseOpObject withSelection(Object... objArr) {
        if (this.mType != 1) {
            this.mSelectionArgs = objArr;
            return this;
        }
        throw new RuntimeException("can't call this method");
    }
}
