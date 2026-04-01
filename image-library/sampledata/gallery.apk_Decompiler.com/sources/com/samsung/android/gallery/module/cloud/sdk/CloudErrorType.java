package com.samsung.android.gallery.module.cloud.sdk;

import android.net.Uri;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum CloudErrorType {
    None,
    GDPR,
    Network,
    Server,
    Storage,
    NotStart,
    MHS,
    Migrating,
    ETC;

    public static boolean isError(int i2) {
        if (isGdprError(i2) || isNetworkError(i2) || isServerError(i2) || isStorageError(i2)) {
            return true;
        }
        return false;
    }

    public static boolean isGdprError(int i2) {
        if (i2 == 101901 || i2 == 101902 || i2 == 101903) {
            return true;
        }
        return false;
    }

    public static boolean isNetworkError(int i2) {
        if (i2 == 60000004 || i2 == 60000000) {
            return true;
        }
        return false;
    }

    public static boolean isServerError(int i2) {
        if (i2 == 108305 || i2 == 364102 || i2 == 364201 || i2 == 364104) {
            return true;
        }
        return false;
    }

    public static boolean isStorageError(int i2) {
        if (i2 == 507 || i2 == 364106) {
            return true;
        }
        return false;
    }

    public static CloudErrorType parseOf(int i2) {
        if (i2 == 0) {
            return None;
        }
        if (isStorageError(i2)) {
            return Storage;
        }
        if (isGdprError(i2)) {
            return GDPR;
        }
        if (isServerError(i2)) {
            return Server;
        }
        if (isNetworkError(i2)) {
            return Network;
        }
        if (i2 == 101503) {
            return Migrating;
        }
        return ETC;
    }

    public static CloudErrorType parseOf(String str) {
        if (str == null) {
            return ETC;
        }
        return str.startsWith("content://") ? None : parseOf(UnsafeCast.toInt(str, 0));
    }

    public static CloudErrorType parseOf(Uri uri) {
        return uri == null ? ETC : parseOf(uri.toString());
    }
}
