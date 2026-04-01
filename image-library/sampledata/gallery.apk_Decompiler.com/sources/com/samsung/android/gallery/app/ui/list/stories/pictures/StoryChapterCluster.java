package com.samsung.android.gallery.app.ui.list.stories.pictures;

import A.a;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.Cluster;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.dataset.tables.ChapterIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoryChapterCluster implements Cluster {
    private int mCount;
    private final ArrayList<Integer> mDividerIndexList;
    private final HashMap<Integer, ClusterItem> mDividerItemMap;
    private final ChapterIndexer mIndexer;
    private final LayoutInfo[] mLayoutInfo;
    private final ChapterIndexer.LayoutLookup mLayoutLookup;
    private final int[] mScrollIndex;
    private final SpecProvider mSpecProvider;

    public StoryChapterCluster(MediaData mediaData, ChapterIndexer.LayoutLookup layoutLookup, SpecProvider specProvider) {
        ChapterIndexer chapterIndexer = mediaData.getChapterIndexer();
        this.mDividerIndexList = chapterIndexer.getChapterIdxList();
        this.mDividerItemMap = chapterIndexer.getChapterItemMap();
        this.mScrollIndex = chapterIndexer.getScrollIndex();
        this.mCount = chapterIndexer.getCount();
        this.mIndexer = chapterIndexer;
        this.mLayoutLookup = layoutLookup;
        this.mSpecProvider = specProvider;
        int maxWidth = getMaxWidth(specProvider.getWidthSpec(1));
        if (maxWidth > 0) {
            chapterIndexer.init(maxWidth, layoutLookup);
        }
        this.mLayoutInfo = chapterIndexer.getLayoutInfo();
        Log.d("StoryChapterCluster", "MemoryCluster create count=" + this.mCount + ", maxWidth=" + maxWidth);
    }

    private int getDividerDelta(int i2) {
        int i7;
        int[] iArr = this.mScrollIndex;
        if (iArr != null) {
            i7 = Arrays.binarySearch(iArr, i2);
        } else {
            i7 = -1;
        }
        if (i7 < 0) {
            return (-i7) - 1;
        }
        return 0;
    }

    private int getMaxWidth(int i2) {
        if (i2 <= 0) {
            return this.mSpecProvider.getWidthSpec(1);
        }
        return i2;
    }

    public ClusterItem getClusterItem(int i2) {
        return this.mDividerItemMap.get(Integer.valueOf(i2));
    }

    public int getColumnSpan(int i2, int i7) {
        if (isDivider(i2)) {
            if (i7 == 1) {
                return getSpanCount();
            }
            return i7;
        } else if (i7 == 1) {
            return this.mIndexer.getItemWidth(i2);
        } else {
            return 1;
        }
    }

    public int getCount() {
        return this.mCount;
    }

    public int getDataPosition(int i2) {
        int dividerDelta = i2 - getDividerDelta(i2);
        if (dividerDelta >= 0) {
            return dividerDelta;
        }
        Log.e("StoryChapterCluster", a.d(i2, dividerDelta, "getDataPosition {viewPosition=", ",dataPosition=", "}"));
        return 0;
    }

    public int getDividerIndex(long j2) {
        return -1;
    }

    public ArrayList<Integer> getDividers() {
        return this.mDividerIndexList;
    }

    public int getItemHeight(int i2, int i7) {
        return this.mIndexer.getItemHeight(i2);
    }

    public int getItemViewType(int i2) {
        if (!isDivider(i2)) {
            return 0;
        }
        if (i2 == 0) {
            return -1;
        }
        return -2;
    }

    public LayoutInfo getLayoutInfo(int i2) {
        return this.mLayoutInfo[i2];
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        int size = ((this.mDividerItemMap.size() - 1) * i7) + this.mIndexer.getMaxScroll();
        if (i8 != 0) {
            return (i8 - i7) + size;
        }
        return size;
    }

    public ScrollText getScrollText(int i2) {
        return null;
    }

    public int getSpanCount() {
        return this.mIndexer.getMaxWidth();
    }

    public int getStartSpan(int i2, int i7) {
        if (i2 < 0) {
            return -1;
        }
        LayoutInfo[] layoutInfoArr = this.mLayoutInfo;
        if (i2 >= layoutInfoArr.length) {
            return -1;
        }
        LayoutInfo layoutInfo = layoutInfoArr[i2];
        if (layoutInfo.groupType == 1 || layoutInfo.align.start()) {
            return 0;
        }
        return -1;
    }

    public int getViewPosition(int i2) {
        Iterator<Integer> it = this.mDividerIndexList.iterator();
        int i7 = 0;
        while (it.hasNext() && i2 >= it.next().intValue() - i7) {
            i7++;
        }
        return i2 + i7;
    }

    public boolean isDivider(int i2) {
        return this.mDividerItemMap.containsKey(Integer.valueOf(i2));
    }

    public void recalculatePosition(int i2) {
        int maxWidth = getMaxWidth(i2);
        if (maxWidth > 0) {
            this.mIndexer.init(maxWidth, this.mLayoutLookup);
        } else {
            a.B(maxWidth, "recalculatePosition maxWidth=", "StoryChapterCluster");
        }
    }

    public int getDividerIndex(int i2) {
        int[] iArr = this.mScrollIndex;
        if (iArr == null) {
            return -1;
        }
        int binarySearch = Arrays.binarySearch(iArr, i2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        return this.mDividerIndexList.get(binarySearch).intValue();
    }
}
