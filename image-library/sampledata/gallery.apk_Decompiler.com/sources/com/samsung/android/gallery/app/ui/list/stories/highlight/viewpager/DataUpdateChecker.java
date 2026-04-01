package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import A.a;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.Log;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataUpdateChecker {
    ArrayList<Data> mDataList = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Data {
        long fileId;
        String thumbCacheKey;
        String totalSmartCropDeviceRatio;
        String totalSmartCropRatio;

        public Data(MediaItem mediaItem) {
            this.fileId = mediaItem.getFileId();
            this.totalSmartCropRatio = MediaItemStory.getTotalSmartCropRatio(mediaItem);
            this.totalSmartCropDeviceRatio = MediaItemStory.getTotalSmartCropDeviceRatio(mediaItem);
            this.thumbCacheKey = mediaItem.getThumbCacheKey();
        }

        public boolean equals(Object obj) {
            Data data;
            if (obj instanceof Data) {
                data = (Data) obj;
            } else {
                data = null;
            }
            if (data == null || this.fileId != data.fileId || !TextUtils.equals(this.totalSmartCropRatio, data.totalSmartCropRatio) || !TextUtils.equals(this.totalSmartCropDeviceRatio, data.totalSmartCropDeviceRatio) || !TextUtils.equals(this.thumbCacheKey, data.thumbCacheKey)) {
                return false;
            }
            return true;
        }
    }

    private boolean checkUpdate(ArrayList<Data> arrayList, StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter) {
        int size = this.mDataList.size();
        int size2 = arrayList.size();
        if (size == 0 || (((size == 1 || size2 == 1) && size2 != size) || isRemovedCurrent(arrayList, storyHighlightViewPagerAdapter))) {
            return false;
        }
        if (handleKeepSlideshow(arrayList, storyHighlightViewPagerAdapter)) {
            return true;
        }
        if (!isLastItemFocused(storyHighlightViewPagerAdapter, arrayList) && handleAddOrRemoveOnly(arrayList, storyHighlightViewPagerAdapter)) {
            return true;
        }
        return false;
    }

    private void compareAndNotifyChanged(StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter, ArrayList<Data> arrayList) {
        Log.d("DataUpdateChecker", "compareAndNotifyChanged", Integer.valueOf(storyHighlightViewPagerAdapter.getItemCount()), Integer.valueOf(this.mDataList.size()), Integer.valueOf(arrayList.size()));
        if (arrayList.size() == this.mDataList.size()) {
            for (int i2 = 0; i2 < this.mDataList.size(); i2++) {
                if (!this.mDataList.get(i2).equals(arrayList.get(i2))) {
                    storyHighlightViewPagerAdapter.notifyItemChanged(i2);
                }
            }
        }
    }

    private ArrayList<Integer> getAddedPosition(ArrayList<Data> arrayList, ArrayList<Data> arrayList2) {
        ArrayList<Integer> arrayList3 = new ArrayList<>();
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            arrayList3.add(Integer.valueOf(arrayList.indexOf(arrayList2.get(i2))));
        }
        return arrayList3;
    }

    private ArrayList<Data> getItems(ArrayList<MediaItem> arrayList) {
        ArrayList<Data> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            Iterator<MediaItem> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(new Data(it.next()));
            }
        }
        return arrayList2;
    }

    private ArrayList<Integer> getRemovedPosition(ArrayList<Data> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList2.add(Integer.valueOf(this.mDataList.indexOf(arrayList.get(size))));
        }
        return arrayList2;
    }

    private boolean handleAddOrRemoveOnly(ArrayList<Data> arrayList, StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter) {
        int size = this.mDataList.size();
        int size2 = arrayList.size();
        ArrayList arrayList2 = new ArrayList(arrayList);
        arrayList2.removeAll(this.mDataList);
        int size3 = arrayList2.size();
        ArrayList arrayList3 = new ArrayList(this.mDataList);
        arrayList3.removeAll(arrayList);
        int size4 = arrayList3.size();
        if (size == size2) {
            return false;
        }
        if (size3 == 0 && size4 != 0) {
            ArrayList<Integer> removedPosition = getRemovedPosition(arrayList3);
            Iterator<Integer> it = removedPosition.iterator();
            while (it.hasNext()) {
                storyHighlightViewPagerAdapter.notifyItemRemoved(it.next().intValue());
            }
            Log.d("DataUpdateChecker", "remove=" + removedPosition);
            return true;
        } else if (size3 == 0 || size4 != 0) {
            return false;
        } else {
            ArrayList<Integer> addedPosition = getAddedPosition(arrayList, arrayList2);
            Iterator<Integer> it2 = addedPosition.iterator();
            while (it2.hasNext()) {
                storyHighlightViewPagerAdapter.notifyItemInserted(it2.next().intValue());
            }
            Log.d("DataUpdateChecker", "add=" + addedPosition);
            return true;
        }
    }

    private boolean handleKeepSlideshow(ArrayList<Data> arrayList, StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter) {
        if (storyHighlightViewPagerAdapter.getItemCount() <= 0 || this.mDataList.size() != arrayList.size()) {
            return false;
        }
        int focusDataPosition = storyHighlightViewPagerAdapter.getFocusDataPosition();
        try {
            if (!this.mDataList.get(focusDataPosition).equals(arrayList.get(focusDataPosition))) {
                return false;
            }
            compareAndNotifyChanged(storyHighlightViewPagerAdapter, arrayList);
            Log.d("DataUpdateChecker", "keep=", Integer.valueOf(focusDataPosition));
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("invalid focusIndex="), "DataUpdateChecker");
            return false;
        }
    }

    private boolean isLastItemFocused(StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter, ArrayList<Data> arrayList) {
        int focusDataPosition = storyHighlightViewPagerAdapter.getFocusDataPosition();
        if (focusDataPosition < 0 || focusDataPosition >= this.mDataList.size() || arrayList.size() <= 0) {
            return false;
        }
        return this.mDataList.get(focusDataPosition).equals(C0212a.i(arrayList, 1));
    }

    private boolean isRemovedCurrent(ArrayList<Data> arrayList, StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter) {
        try {
            return !arrayList.contains(this.mDataList.get(storyHighlightViewPagerAdapter.getFocusDataPosition()));
        } catch (Exception e) {
            a.s(e, new StringBuilder("isRemovedCurrent focusIndex="), "DataUpdateChecker");
            return false;
        }
    }

    private void swap(ArrayList<Data> arrayList) {
        this.mDataList = arrayList;
    }

    public boolean handleDataChanged(MediaData mediaData, StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter) {
        if (mediaData == null || storyHighlightViewPagerAdapter == null) {
            return false;
        }
        ArrayList<Data> items = getItems(mediaData.getAllData());
        boolean checkUpdate = checkUpdate(items, storyHighlightViewPagerAdapter);
        swap(items);
        return checkUpdate;
    }

    public void reset() {
        this.mDataList = new ArrayList<>();
    }
}
