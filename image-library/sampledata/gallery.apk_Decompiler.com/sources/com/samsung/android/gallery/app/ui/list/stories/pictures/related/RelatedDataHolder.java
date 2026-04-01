package com.samsung.android.gallery.app.ui.list.stories.pictures.related;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedDataHolder {
    public RelatedInfo base;
    private ArrayList<RelatedInfo> mRelatedAlbumsInfo;
    public ArrayList<MediaItem> pickedStories;

    public RelatedDataHolder() {
        this.pickedStories = new ArrayList<>();
    }

    public void addRelatedAlbumInfo(RelatedInfo relatedInfo) {
        if (this.mRelatedAlbumsInfo == null) {
            this.mRelatedAlbumsInfo = new ArrayList<>();
        }
        this.mRelatedAlbumsInfo.add(relatedInfo);
    }

    public RelatedDataHolder cloneData() {
        ArrayList<RelatedInfo> arrayList;
        RelatedDataHolder relatedDataHolder = new RelatedDataHolder();
        relatedDataHolder.base = this.base;
        ArrayList<MediaItem> arrayList2 = null;
        if (this.mRelatedAlbumsInfo != null) {
            arrayList = new ArrayList<>(this.mRelatedAlbumsInfo);
        } else {
            arrayList = null;
        }
        relatedDataHolder.mRelatedAlbumsInfo = arrayList;
        if (this.pickedStories != null) {
            arrayList2 = new ArrayList<>(this.pickedStories);
        }
        relatedDataHolder.pickedStories = arrayList2;
        return relatedDataHolder;
    }

    public ArrayList<RelatedInfo> getRelatedAlbumsInfo() {
        return this.mRelatedAlbumsInfo;
    }

    public boolean isLoaded() {
        ArrayList<RelatedInfo> arrayList = this.mRelatedAlbumsInfo;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isValid() {
        ArrayList<MediaItem> arrayList = this.pickedStories;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean remove(RelatedInfo relatedInfo) {
        ArrayList<RelatedInfo> arrayList = this.mRelatedAlbumsInfo;
        if (arrayList == null || !arrayList.remove(relatedInfo)) {
            return false;
        }
        return true;
    }

    public void ruleOut(RelatedInfo relatedInfo) {
        remove(relatedInfo);
    }

    public RelatedDataHolder(ArrayList<MediaItem> arrayList) {
        new ArrayList();
        this.pickedStories = arrayList;
    }
}
