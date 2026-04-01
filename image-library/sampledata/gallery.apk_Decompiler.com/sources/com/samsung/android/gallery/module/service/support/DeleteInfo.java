package com.samsung.android.gallery.module.service.support;

import N2.j;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteInfo implements IDeleteInfo {
    private DeleteAlbumInfo mAlbumInfo = null;
    private final boolean mIsAlbum;
    private final boolean mIsVirtual;
    private DeleteItemInfo mItemInfo = null;
    private final boolean mShowDeleteAllWarning;
    private final boolean mUseTrash;

    public DeleteInfo(boolean z, boolean z3, boolean z7, boolean z9) {
        this.mIsAlbum = z;
        this.mIsVirtual = z3;
        this.mUseTrash = z7;
        this.mShowDeleteAllWarning = z9;
    }

    private void calculateAlbumCount(MediaItem[] mediaItemArr) {
        this.mAlbumInfo = new DeleteAlbumInfo(this);
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                calculatedSubAlbumCount(mediaItem);
            }
        }
        this.mAlbumInfo.setCloudInvolved();
        Log.d("DeleteInfo", "selected info : " + this.mAlbumInfo.toString());
    }

    private void calculateItemCount(MediaItem[] mediaItemArr) {
        this.mItemInfo = new DeleteItemInfo(this);
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                if (mediaItem.isSimilarShot()) {
                    this.mItemInfo.increaseSimilarPhoto();
                } else if (mediaItem.isSingleTakenShot()) {
                    this.mItemInfo.increaseSingleTaken(mediaItem.isImage());
                } else if (mediaItem.isBurstShot()) {
                    this.mItemInfo.increaseBurstShot();
                } else if (mediaItem.isVideo()) {
                    this.mItemInfo.increaseVideo();
                } else {
                    this.mItemInfo.increaseImage();
                }
                this.mItemInfo.setCloudInvolved(mediaItem.isCloud());
                this.mItemInfo.setSdCardInvolved(mediaItem.getPath());
            }
        }
        Log.d("DeleteInfo", "selected info : " + this.mItemInfo.toString());
    }

    private void calculatedSubAlbumCount(MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            this.mAlbumInfo.increaseGroup();
            MediaItem[] itemsInFolder = mediaItem.getItemsInFolder();
            if (itemsInFolder.length == 0) {
                this.mAlbumInfo.increaseEmptyGroup();
                return;
            }
            for (MediaItem mediaItem2 : itemsInFolder) {
                if (mediaItem2 != null) {
                    calculatedSubAlbumCount(mediaItem2);
                }
            }
        } else if (BucketUtils.isRecent(mediaItem.getAlbumID())) {
            this.mAlbumInfo.setRecentAlbumInvolved();
        } else if (BucketUtils.isFavourite(mediaItem.getAlbumID())) {
            this.mAlbumInfo.setFavoriteAlbumInvolved();
        } else {
            this.mAlbumInfo.increaseAlbum();
            boolean isEmptyAlbum = mediaItem.isEmptyAlbum();
            if (isEmptyAlbum) {
                this.mAlbumInfo.increaseEmptyAlbum();
            } else {
                this.mAlbumInfo.increaseItem(mediaItem.getCount());
                if (mediaItem.isAutoAlbum()) {
                    this.mAlbumInfo.increaseAutoAlbum();
                    this.mAlbumInfo.increaseAutoAlbumItem(mediaItem.getCount());
                }
            }
            this.mAlbumInfo.addAlbumId(mediaItem.getAlbumID());
            this.mAlbumInfo.setSdCardInvolved(!isEmptyAlbum, mediaItem.getPath());
        }
    }

    public void calculateCount(MediaItem[] mediaItemArr) {
        if (this.mIsAlbum) {
            calculateAlbumCount(mediaItemArr);
        } else {
            calculateItemCount(mediaItemArr);
        }
    }

    public DeleteAlbumInfo getAlbumInfo() {
        return this.mAlbumInfo;
    }

    public String getBaseInfo() {
        StringBuilder sb2 = new StringBuilder("BaseInfo{a:");
        sb2.append(this.mIsAlbum);
        sb2.append(",v:");
        sb2.append(this.mIsVirtual);
        sb2.append(",t:");
        return j.h(sb2, this.mUseTrash, "}");
    }

    public DeleteItemInfo getItemInfo() {
        return this.mItemInfo;
    }

    public boolean isVirtual() {
        return this.mIsVirtual;
    }

    public boolean showDeleteAllWarning() {
        return this.mShowDeleteAllWarning;
    }

    public boolean useTrash() {
        return this.mUseTrash;
    }
}
