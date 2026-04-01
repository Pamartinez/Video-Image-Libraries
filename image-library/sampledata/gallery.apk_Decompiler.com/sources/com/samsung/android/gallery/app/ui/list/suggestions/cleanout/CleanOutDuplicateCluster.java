package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import com.samsung.android.gallery.app.ui.list.pictures.adapter.DividerCluster;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.tables.CleanOutDuplicateClusterIndexer;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutDuplicateCluster extends DividerCluster {
    private final int[] mDeleteGroupIds;

    public CleanOutDuplicateCluster(CleanOutDuplicateClusterIndexer cleanOutDuplicateClusterIndexer, int i2) {
        super(i2);
        this.mScrollIndex = cleanOutDuplicateClusterIndexer.getScrollIndex();
        this.mDeleteGroupIds = cleanOutDuplicateClusterIndexer.getDeleteGroupIds();
        this.mDividerIndexList = cleanOutDuplicateClusterIndexer.getDividerIndexList();
        this.mDividerItemMapReadOnly = cleanOutDuplicateClusterIndexer.getDividerItemMap();
        this.mCount = cleanOutDuplicateClusterIndexer.getCount();
    }

    public int getDividerIndexInternal(long j2) {
        int binarySearch = Arrays.binarySearch(this.mDeleteGroupIds, (int) j2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        int length = (this.mDeleteGroupIds.length - 1) - binarySearch;
        if (length < 0) {
            return length;
        }
        return this.mDividerIndexList.get(length).intValue();
    }

    public ScrollText getScrollText(int i2) {
        return null;
    }

    public int getStartSpanInternal(int i2, int i7) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch > -2) {
            return 0;
        }
        return ((i2 - this.mScrollIndex[(-binarySearch) - 2]) - 1) % i7;
    }
}
