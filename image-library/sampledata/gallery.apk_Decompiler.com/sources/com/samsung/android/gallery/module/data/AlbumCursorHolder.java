package com.samsung.android.gallery.module.data;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumCursorHolder {
    public int indexAlbumCount;
    public int indexAlbumLevel;
    public int indexAlbumOrder;
    public int indexAlbumShowInfo;
    public int indexAlbumType;
    public int indexBucketId;
    public int indexCoverPath;
    public int indexCoverRect;
    public int indexDateModified;
    public int indexDefaultCoverPath;
    public int indexFolderId;
    public int indexSyncStatus;
    public int indexTitle;
    private final Cursor mCursor;

    public AlbumCursorHolder(Cursor cursor) {
        this.mCursor = cursor;
        this.indexAlbumCount = cursor.getColumnIndex("album_count");
        this.indexFolderId = cursor.getColumnIndex("folder_id");
        this.indexBucketId = cursor.getColumnIndex("__bucketID");
        this.indexTitle = cursor.getColumnIndex("__Title");
        this.indexAlbumOrder = cursor.getColumnIndex("album_order");
        this.indexAlbumLevel = cursor.getColumnIndex("__albumLevel");
        this.indexAlbumType = cursor.getColumnIndex("__albumType");
        this.indexAlbumShowInfo = cursor.getColumnIndex("__albumShowInfo");
        this.indexCoverRect = cursor.getColumnIndex("cover_rect");
        this.indexCoverPath = cursor.getColumnIndex("cover_path");
        this.indexDateModified = cursor.getColumnIndex("__dateModified");
        this.indexDefaultCoverPath = cursor.getColumnIndex("default_cover_path");
        this.indexSyncStatus = cursor.getColumnIndex("album_sync_status");
    }

    public int getInt(int i2, int i7) {
        if (i2 < 0) {
            return i7;
        }
        return this.mCursor.getInt(i2);
    }

    public long getLong(int i2, long j2) {
        if (i2 < 0) {
            return j2;
        }
        return this.mCursor.getLong(i2);
    }

    public String getString(int i2, String str) {
        if (i2 < 0) {
            return str;
        }
        return this.mCursor.getString(i2);
    }
}
