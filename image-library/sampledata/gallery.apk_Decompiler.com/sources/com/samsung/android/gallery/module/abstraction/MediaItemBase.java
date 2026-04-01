package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemBase {
    public static Object getExtra(FileItemInterface fileItemInterface, ExtrasID extrasID) {
        if (fileItemInterface != null) {
            return fileItemInterface.getExtra(extrasID);
        }
        return null;
    }

    public static <T> T getOrElse(T t, T t3) {
        if (t != null) {
            return t;
        }
        return t3;
    }

    public static void setExtra(FileItemInterface fileItemInterface, ExtrasID extrasID, Object obj) {
        if (fileItemInterface != null) {
            fileItemInterface.setExtra(extrasID, obj);
        }
    }

    public static String toString(Object obj, String str) {
        if (obj == null || !(obj instanceof String)) {
            return str;
        }
        return (String) obj;
    }
}
