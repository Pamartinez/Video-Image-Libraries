package com.samsung.android.gallery.module.service.support;

import N2.j;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteItemInfo {
    private int mBurstShotCount = 0;
    private boolean mCloudInvolved = false;
    private int mImageCount = 0;
    private final IDeleteInfo mInfo;
    private boolean mSdCardInvolved = false;
    private int mSimilarPhotoCount = 0;
    private int mSingleTakenImageCount = 0;
    private int mSingleTakenVideoCount = 0;
    private int mVideoCount = 0;

    public DeleteItemInfo(IDeleteInfo iDeleteInfo) {
        this.mInfo = iDeleteInfo;
    }

    public int getBurstShotCount() {
        return this.mBurstShotCount;
    }

    public int getImageCount() {
        return this.mImageCount;
    }

    public int getSimilarPhotoCount() {
        return this.mSimilarPhotoCount;
    }

    public int getSingleTakenCount() {
        return this.mSingleTakenImageCount + this.mSingleTakenVideoCount;
    }

    public int getTotalCount() {
        return this.mImageCount + this.mVideoCount + this.mBurstShotCount + this.mSimilarPhotoCount + this.mSingleTakenImageCount + this.mSingleTakenVideoCount;
    }

    public int getTotalImageCount() {
        return this.mImageCount + this.mBurstShotCount + this.mSimilarPhotoCount + this.mSingleTakenImageCount;
    }

    public int getTotalVideoCount() {
        return this.mVideoCount + this.mSingleTakenVideoCount;
    }

    public int getVideoCount() {
        return this.mVideoCount;
    }

    public void increaseBurstShot() {
        this.mBurstShotCount++;
    }

    public void increaseImage() {
        this.mImageCount++;
    }

    public void increaseSimilarPhoto() {
        this.mSimilarPhotoCount++;
    }

    public void increaseSingleTaken(boolean z) {
        if (z) {
            this.mSingleTakenImageCount++;
        } else {
            this.mSingleTakenVideoCount++;
        }
    }

    public void increaseVideo() {
        this.mVideoCount++;
    }

    public boolean isCloudInvolved() {
        return this.mCloudInvolved;
    }

    public boolean isSdCardInvolved() {
        return this.mSdCardInvolved;
    }

    public boolean isVirtual() {
        return this.mInfo.isVirtual();
    }

    public void setCloudInvolved(boolean z) {
        if (!this.mCloudInvolved && z) {
            this.mCloudInvolved = true;
        }
    }

    public void setSdCardInvolved(String str) {
        if (!this.mSdCardInvolved && FileUtils.isInRemovableStorage(str)) {
            this.mSdCardInvolved = true;
        }
    }

    public boolean showDeleteAllWarning() {
        return this.mInfo.showDeleteAllWarning();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mInfo.getBaseInfo());
        sb2.append("\n ItemCount{i:");
        sb2.append(this.mImageCount);
        sb2.append(",v:");
        sb2.append(this.mVideoCount);
        sb2.append(",b:");
        sb2.append(this.mBurstShotCount);
        sb2.append(",sp:");
        sb2.append(this.mSimilarPhotoCount);
        sb2.append(",sti:");
        sb2.append(this.mSingleTakenImageCount);
        sb2.append(",stv:");
        sb2.append(this.mSingleTakenVideoCount);
        sb2.append(",c:");
        sb2.append(this.mCloudInvolved);
        sb2.append(",sd:");
        return j.h(sb2, this.mSdCardInvolved, "}");
    }

    public boolean useTrash() {
        return this.mInfo.useTrash();
    }
}
