package com.samsung.android.gallery.module.dataset.tables;

import A.a;
import c0.C0086a;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListRealRatioIndexer extends RealRatioIndexer {
    private final ArrayList<MediaItem> mItems;

    public ListRealRatioIndexer(ArrayList<MediaItem> arrayList, ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap, int i2) {
        super(arrayList);
        this.mItems = arrayList;
        onConstruct(concurrentHashMap, i2, arrayList.size());
    }

    private float getRectRatio(MediaItem mediaItem) {
        return RealRatioIndexer.getRectRatio(mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getOrientation());
    }

    public void close() {
        super.close();
        this.mItems.clear();
    }

    public boolean hasNoData() {
        return this.mItems.isEmpty();
    }

    public boolean isLastItem() {
        if (this.mDataPosition == this.mLoadedDataCount - 1) {
            return true;
        }
        return false;
    }

    public boolean loadOriginalWidth(int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i7 = 0; i7 < this.mItems.size(); i7++) {
            this.mItemInfo[i7].ratioData = getRectRatio(this.mItems.get(i7));
        }
        a.x(C0086a.o(i2, "RealRatio load {", "} +"), currentTimeMillis, this.TAG);
        return true;
    }

    public String tag() {
        return "ListRealRatioIndexer";
    }
}
