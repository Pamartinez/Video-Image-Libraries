package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MyQueryColumn {
    ID("_id"),
    FILTER_DATA("__filterData"),
    CREATE_TYPE("__createType"),
    MY_QUERY_NAME("__myQueryName"),
    DATE_ADDED("__dateAdded"),
    DATE_MODIFIED("__dateModified"),
    COUNT("__count"),
    SEC_MEDIA_ID("__secMediaId"),
    DATA("__data"),
    DATE_TAKEN("__dateTaken"),
    RECT("__rect"),
    MEDIA_TYPE("__mediaType"),
    WIDTH("__width"),
    HEIGHT("__height"),
    ORIENTATION("__orientation"),
    ORIENTATION_TAG("__orientationTag"),
    STORAGE_TYPE("__storageType"),
    SIZE("__size"),
    EXTRA(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    
    static boolean sInitRequired;
    public int index;
    final String name;

    static {
        sInitRequired = true;
    }

    private MyQueryColumn(String str) {
        this.name = str;
        this.index = -1;
    }

    public static void initialize(Cursor cursor) {
        if (sInitRequired) {
            sInitRequired = false;
            for (MyQueryColumn myQueryColumn : values()) {
                myQueryColumn.index = cursor.getColumnIndex(myQueryColumn.name);
            }
        }
    }
}
