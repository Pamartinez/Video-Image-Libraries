package com.samsung.android.sdk.mobileservice.social.social;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareLimit {
    private final int maxCountOfDelete;
    private final int maxCountOfDeleteFromTrashBin;
    private final int maxCountOfDownload;
    private final int maxCountOfMoveToTrashBin;
    private final int maxCountOfRestoreFromTrashBin;
    private final int maxCountOfUpload;

    public ShareLimit(int i2, int i7, int i8, int i10, int i11, int i12) {
        this.maxCountOfUpload = i2;
        this.maxCountOfDownload = i7;
        this.maxCountOfDelete = i8;
        this.maxCountOfMoveToTrashBin = i10;
        this.maxCountOfRestoreFromTrashBin = i11;
        this.maxCountOfDeleteFromTrashBin = i12;
    }

    public int getMaxCountOfDelete() {
        return this.maxCountOfDelete;
    }

    public int getMaxCountOfDeleteFromTrashBin() {
        return this.maxCountOfDeleteFromTrashBin;
    }

    public int getMaxCountOfDownload() {
        return this.maxCountOfDownload;
    }

    public int getMaxCountOfMoveToTrashBin() {
        return this.maxCountOfMoveToTrashBin;
    }

    public int getMaxCountOfRestoreFromTrashBin() {
        return this.maxCountOfRestoreFromTrashBin;
    }

    public int getMaxCountOfUpload() {
        return this.maxCountOfUpload;
    }
}
