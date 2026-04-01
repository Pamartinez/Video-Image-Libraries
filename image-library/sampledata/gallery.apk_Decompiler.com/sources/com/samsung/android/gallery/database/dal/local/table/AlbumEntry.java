package com.samsung.android.gallery.database.dal.local.table;

import N2.j;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumEntry {
    private long mAlbumEssOrder;
    private int mAlbumLevel;
    private long mAlbumOrder;
    private String mAlbumPath;
    private int mAlbumType;
    private int mBucketId;
    private String mCoverPath;
    private final String mCoverRect;
    private final String mDefaultCoverPath;
    private int mFolderId;
    private String mFolderName;
    private final boolean mHasOrderInfo;
    private final String mTitle;

    public AlbumEntry(Cursor cursor) {
        this.mAlbumPath = cursor.getString(cursor.getColumnIndex("__absPath"));
        this.mBucketId = cursor.getInt(cursor.getColumnIndex("__bucketID"));
        this.mCoverPath = cursor.getString(cursor.getColumnIndex("cover_path"));
        this.mDefaultCoverPath = cursor.getString(cursor.getColumnIndex("default_cover_path"));
        this.mCoverRect = cursor.getString(cursor.getColumnIndex("cover_rect"));
        this.mTitle = cursor.getString(cursor.getColumnIndex("__Title"));
        this.mFolderId = cursor.getInt(cursor.getColumnIndex("folder_id"));
        this.mFolderName = cursor.getString(cursor.getColumnIndex("folder_name"));
        this.mAlbumOrder = cursor.getLong(cursor.getColumnIndex("album_order"));
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            this.mAlbumType = cursor.getInt(cursor.getColumnIndex("__albumType"));
            this.mAlbumLevel = cursor.getInt(cursor.getColumnIndex("__albumLevel"));
            this.mAlbumEssOrder = cursor.getLong(cursor.getColumnIndex("essential_album_order"));
        }
        this.mHasOrderInfo = true;
    }

    public long getAlbumEssOrder() {
        return this.mAlbumEssOrder;
    }

    public int getAlbumLevel() {
        return this.mAlbumLevel;
    }

    public long getAlbumOrder() {
        return this.mAlbumOrder;
    }

    public String getAlbumPath() {
        return this.mAlbumPath;
    }

    public int getAlbumType() {
        return this.mAlbumType;
    }

    public int getBucketId() {
        return this.mBucketId;
    }

    public String getCoverPath() {
        return this.mCoverPath;
    }

    public String getCoverRect() {
        return this.mCoverRect;
    }

    public String getDefaultCoverPath() {
        return this.mDefaultCoverPath;
    }

    public int getFolderId() {
        return this.mFolderId;
    }

    public String getFolderName() {
        return this.mFolderName;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean hasCoverInfo() {
        return !TextUtils.isEmpty(this.mCoverPath);
    }

    public boolean hasCoverRect() {
        return !TextUtils.isEmpty(this.mCoverRect);
    }

    public boolean hasDefaultCoverInfo() {
        return !TextUtils.isEmpty(this.mDefaultCoverPath);
    }

    public boolean hasFolderInfo() {
        if (this.mFolderId != 0) {
            return true;
        }
        return false;
    }

    public boolean hasOrderInfo() {
        return this.mHasOrderInfo;
    }

    public boolean isFolder() {
        if (!TextUtils.isEmpty(this.mAlbumPath) || !TextUtils.isEmpty(this.mCoverPath) || !TextUtils.isEmpty(this.mDefaultCoverPath)) {
            return false;
        }
        return true;
    }

    public boolean isMergedAlbum() {
        return AlbumType.isMergedAlbum(this.mAlbumType);
    }

    public boolean needToBackup() {
        if (this.mAlbumOrder > 0 || hasFolderInfo() || hasCoverInfo() || isFolder()) {
            return true;
        }
        return false;
    }

    public void setAlbumCoverPath(String str) {
        this.mCoverPath = str;
    }

    public void setAlbumOrder(long j2) {
        this.mAlbumOrder = j2;
    }

    public void setAlbumPath(String str) {
        this.mAlbumPath = str;
    }

    public void setBucketId(int i2) {
        this.mBucketId = i2;
    }

    public void setFolderInfo(int i2, String str) {
        this.mFolderId = i2;
        this.mFolderName = str;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AlbumEntry{mBucketId=");
        sb2.append(this.mBucketId);
        sb2.append(", mAlbumPath='");
        sb2.append(this.mAlbumPath);
        sb2.append("', mCoverPath='");
        sb2.append(this.mCoverPath);
        sb2.append("', mDefaultCoverPath='");
        sb2.append(this.mDefaultCoverPath);
        sb2.append("', mCoverRect='");
        sb2.append(this.mCoverRect);
        sb2.append("', mTitle='");
        sb2.append(this.mTitle);
        sb2.append("', mFolderId=");
        sb2.append(this.mFolderId);
        sb2.append(", mFolderName='");
        sb2.append(this.mFolderName);
        sb2.append("', mAlbumOrder=");
        sb2.append(this.mAlbumOrder);
        sb2.append(", mAlbumType=");
        sb2.append(this.mAlbumType);
        sb2.append(", mAlbumLevel=");
        return j.e(sb2, this.mAlbumLevel, '}');
    }

    public AlbumEntry(int i2, String str, String str2, String str3, String str4, String str5, int i7, String str6, long j2, boolean z, int i8, int i10, long j3) {
        this.mAlbumPath = str;
        this.mBucketId = i2;
        this.mCoverPath = str2;
        this.mDefaultCoverPath = str3;
        this.mCoverRect = str4;
        this.mTitle = str5;
        this.mFolderId = i7;
        this.mFolderName = str6;
        this.mAlbumOrder = j2;
        this.mHasOrderInfo = z;
        this.mAlbumType = i8;
        this.mAlbumLevel = i10;
        this.mAlbumEssOrder = j3;
    }

    public AlbumEntry(int i2, String str, long j2, int i7, String str2, int i8, int i10, long j3) {
        this.mAlbumPath = null;
        this.mBucketId = i2;
        this.mCoverPath = null;
        this.mDefaultCoverPath = null;
        this.mCoverRect = null;
        this.mTitle = str;
        this.mFolderId = i7;
        this.mFolderName = str2;
        this.mAlbumOrder = j2;
        this.mHasOrderInfo = true;
        this.mAlbumType = i8;
        this.mAlbumLevel = i10;
        this.mAlbumEssOrder = j3;
    }
}
