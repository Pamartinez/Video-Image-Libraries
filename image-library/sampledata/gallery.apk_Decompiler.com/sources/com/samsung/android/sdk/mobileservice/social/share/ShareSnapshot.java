package com.samsung.android.sdk.mobileservice.social.share;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareSnapshot {
    private long mCurrentFileBytes;
    private long mCurrentFileBytesTransferred;
    private int mCurrentFileIndex;
    private long mTotalBytes;
    private long mTotalBytesTransferred;
    private int mTotalFileCount;
    private int mTotalFileCountTransferred;

    public ShareSnapshot(long j2, long j3, int i2, int i7, long j8, long j10, int i8) {
        this.mTotalBytes = j2;
        this.mTotalBytesTransferred = j3;
        this.mTotalFileCount = i2;
        this.mTotalFileCountTransferred = i7;
        this.mCurrentFileBytes = j8;
        this.mCurrentFileBytesTransferred = j10;
        this.mCurrentFileIndex = i8;
    }

    public long getCurrentFileBytes() {
        return this.mCurrentFileBytes;
    }

    public long getCurrentFileBytesTransferred() {
        return this.mCurrentFileBytesTransferred;
    }

    public int getCurrentFileIndex() {
        return this.mCurrentFileIndex;
    }

    public long getTotalBytes() {
        return this.mTotalBytes;
    }

    public long getTotalBytesTransferred() {
        return this.mTotalBytesTransferred;
    }

    public int getTotalFileCount() {
        return this.mTotalFileCount;
    }

    public int getTotalFileCountTransferred() {
        return this.mTotalFileCountTransferred;
    }
}
