package com.samsung.android.sdk.mobileservice.social.share;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedContentDownloadSnapshot {
    private long mCurrentFileBytes;
    private long mCurrentFileBytesReceived;
    private int mCurrentFileIndex;
    private long mTotalBytes;
    private long mTotalBytesReceived;
    private int mTotalFileCount;
    private int mTotalFileCountReceived;

    public SharedContentDownloadSnapshot(long j2, long j3, int i2, int i7, long j8, long j10, int i8) {
        this.mTotalBytes = j2;
        this.mTotalBytesReceived = j3;
        this.mTotalFileCount = i2;
        this.mTotalFileCountReceived = i7;
        this.mCurrentFileBytes = j8;
        this.mCurrentFileBytesReceived = j10;
        this.mCurrentFileIndex = i8;
    }

    public long getCurrentFileBytes() {
        return this.mCurrentFileBytes;
    }

    public long getCurrentFileBytesReceived() {
        return this.mCurrentFileBytesReceived;
    }

    public int getCurrentFileIndex() {
        return this.mCurrentFileIndex;
    }

    public long getTotalBytes() {
        return this.mTotalBytes;
    }

    public long getTotalBytesReceived() {
        return this.mTotalBytesReceived;
    }

    public int getTotalFileCount() {
        return this.mTotalFileCount;
    }

    public int getTotalFileCountReceived() {
        return this.mTotalFileCountReceived;
    }
}
