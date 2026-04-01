package com.samsung.android.gallery.module.dataset;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderCoverItem extends FolderItem {
    public FolderCoverItem() {
    }

    private boolean hasValidCover() {
        return !TextUtils.isEmpty(getAlbumCover());
    }

    public /* bridge */ /* synthetic */ void addItem(MediaItem mediaItem) {
        super.addItem(mediaItem);
    }

    public String buildThumbCacheKey() {
        return "folderCover/" + getFolderID() + '/' + getPath();
    }

    public /* bridge */ /* synthetic */ int getAlbumID() {
        return super.getAlbumID();
    }

    public /* bridge */ /* synthetic */ MediaItem[] getAlbumsInFolder() {
        return super.getAlbumsInFolder();
    }

    public /* bridge */ /* synthetic */ List getChildAlbums() {
        return super.getChildAlbums();
    }

    public /* bridge */ /* synthetic */ List getChildItems() {
        return super.getChildItems();
    }

    public /* bridge */ /* synthetic */ List getChildList() {
        return super.getChildList();
    }

    public /* bridge */ /* synthetic */ String getCloudServerPath() {
        return super.getCloudServerPath();
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ long getDateModified() {
        return super.getDateModified();
    }

    public /* bridge */ /* synthetic */ String getDisplayName() {
        return super.getDisplayName();
    }

    public /* bridge */ /* synthetic */ MediaItem getFirst() {
        return super.getFirst();
    }

    public /* bridge */ /* synthetic */ int getItemCount() {
        return super.getItemCount();
    }

    public /* bridge */ /* synthetic */ MediaItem[] getItemsInFolder() {
        return super.getItemsInFolder();
    }

    public String getPath() {
        if (hasValidCover()) {
            return getAlbumCover();
        }
        return super.getPath();
    }

    public /* bridge */ /* synthetic */ StorageType getStorageType() {
        return super.getStorageType();
    }

    public /* bridge */ /* synthetic */ String getTitle() {
        return super.getTitle();
    }

    public boolean isCustomCover() {
        return hasValidCover();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ boolean isFolder() {
        return super.isFolder();
    }

    public /* bridge */ /* synthetic */ void removeItem(MediaItem mediaItem) {
        super.removeItem(mediaItem);
    }

    public /* bridge */ /* synthetic */ void setAlbumLevel(int i2) {
        super.setAlbumLevel(i2);
    }

    public /* bridge */ /* synthetic */ void setDisplayName(String str) {
        super.setDisplayName(str);
    }

    public /* bridge */ /* synthetic */ void setTranslatedName(String str) {
        super.setTranslatedName(str);
    }

    public void updateMergedAlbum(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!hasValidCover()) {
            super.updateMergedAlbum(mediaItem, mediaItem2);
        }
    }

    public FolderCoverItem(Comparator<MediaItem> comparator) {
        super(comparator);
    }

    public /* bridge */ /* synthetic */ MediaItem[] getAlbumsInFolder(boolean z) {
        return super.getAlbumsInFolder(z);
    }

    public /* bridge */ /* synthetic */ MediaItem[] getItemsInFolder(boolean z) {
        return super.getItemsInFolder(z);
    }
}
