package com.samsung.android.gallery.database.dal.mp.helper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchRemoveInfo {
    private int mAlbumId;
    private long mBurstGroupId;
    private long mFaceGroupId;
    private long mFileId;
    private String mIdentityInfo;

    public int getAlbumId() {
        return this.mAlbumId;
    }

    public long getBurstGroupId() {
        return this.mBurstGroupId;
    }

    public long getFaceGroupId() {
        return this.mFaceGroupId;
    }

    public long getFileId() {
        return this.mFileId;
    }

    public String getIdentityInfo() {
        return this.mIdentityInfo;
    }

    public boolean isBurstShot() {
        if (this.mBurstGroupId != 0) {
            return true;
        }
        return false;
    }

    public SearchRemoveInfo setAlbumId(int i2) {
        this.mAlbumId = i2;
        return this;
    }

    public SearchRemoveInfo setBurstGroupId(long j2) {
        this.mBurstGroupId = j2;
        return this;
    }

    public SearchRemoveInfo setFaceGroupId(long j2) {
        this.mFaceGroupId = j2;
        return this;
    }

    public SearchRemoveInfo setFileId(long j2) {
        this.mFileId = j2;
        return this;
    }

    public SearchRemoveInfo setIdentityInfo(String str) {
        this.mIdentityInfo = str;
        return this;
    }
}
