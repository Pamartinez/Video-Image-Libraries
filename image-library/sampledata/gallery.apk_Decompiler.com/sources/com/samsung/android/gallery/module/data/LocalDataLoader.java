package com.samsung.android.gallery.module.data;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LocalDataLoader extends AbsMediaItemLoader {
    public MediaItem getMediaItemInternal(Cursor cursor) {
        return MediaItemBuilder.create(cursor);
    }

    public MediaItem getPrimitiveMediaItemInternal(Cursor cursor) {
        return MediaItemBuilder.createPrimitive(cursor);
    }

    public boolean support(Cursor cursor) {
        if (cursor.getColumnIndex("__absPath") != -1) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "LocalDataLoader";
    }
}
