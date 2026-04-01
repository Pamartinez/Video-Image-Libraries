package com.samsung.android.gallery.app.ui.list.search.category;

import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CategoryGroupItemAdapter extends CategoryItemAdapterV2 {
    private int mDividerHeight;
    protected int[] mDividerIndex;
    private int mFirstDividerHeight;
    protected ArrayList<String> mGroupItemList;
    protected boolean mIsClusterDisabled;
    protected ArrayList<Integer> mViewPositions;

    public CategoryGroupItemAdapter(ISearchView iSearchView, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(iSearchView, str, galleryListView, categoryPropertyHelper, z);
        updateCluster();
    }

    private boolean isFirstColumn(int i2, int i7) {
        if (getHintStartSpan(i2, i7) == 0) {
            return true;
        }
        return false;
    }

    public MediaItem getDividerMediaItem(int i2) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setTitle(this.mGroupItemList.get(getIndex(i2)));
        return mediaItem;
    }

    public int getHintStartSpan(int i2, int i7) {
        if (isDivider(i2)) {
            return 0;
        }
        if (this.mIsClusterDisabled) {
            return i2 % i7;
        }
        return ((i2 - this.mDividerIndex[getIndex(i2)]) - 1) % i7;
    }

    public int getIndex(int i2) {
        int binarySearch = Arrays.binarySearch(this.mDividerIndex, i2);
        if (binarySearch < 0) {
            return (-binarySearch) - 2;
        }
        return binarySearch;
    }

    public int getItemCount() {
        if (this.mIsClusterDisabled) {
            return super.getItemCount();
        }
        return this.mDataCount + this.mDividerIndex.length;
    }

    public int getItemHeight(int i2) {
        if (!isDivider(i2)) {
            return this.mItemHeight;
        }
        if (i2 == 0) {
            return this.mFirstDividerHeight;
        }
        return this.mDividerHeight;
    }

    public int getItemViewType(int i2) {
        if (!isDivider(i2)) {
            return 3;
        }
        if (i2 == 0) {
            return -1;
        }
        return -2;
    }

    public int getMaxScroll() {
        if (this.mIsClusterDisabled) {
            return super.getMaxScroll();
        }
        int gridSize = getGridSize();
        int i2 = 0;
        int i7 = 0;
        while (true) {
            int[] iArr = this.mDividerIndex;
            if (i2 < iArr.length - 1) {
                int i8 = i2 + 1;
                i7 += (getDataRowCount(gridSize, (iArr[i8] - iArr[i2]) - 1) * this.mItemHeight) + this.mDividerHeight;
                i2 = i8;
            } else {
                int itemCount = getItemCount();
                int[] iArr2 = this.mDividerIndex;
                return (getDataRowCount(gridSize, (itemCount - iArr2[iArr2.length - 1]) - 1) * this.mItemHeight) + this.mFirstDividerHeight + i7;
            }
        }
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return isDivider(i2) ? getDividerMediaItem(i2) : super.getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2) {
        return this.mViewPositions.get(i2).intValue();
    }

    public int getNextRowIndex(int i2, int i7) {
        int i8;
        int gridSize = getGridSize();
        int i10 = i2 + 1;
        if (isFirstColumn(i10, gridSize)) {
            return Math.min(i7 - 1, i10);
        }
        do {
            i10++;
            i8 = i7 - 1;
            if (i10 >= i8 || isFirstColumn(i10, gridSize)) {
            }
            i10++;
            i8 = i7 - 1;
            break;
        } while (isFirstColumn(i10, gridSize));
        return Math.min(i8, i10);
    }

    public int getPrevRowIndex(int i2) {
        int gridSize = getGridSize();
        int i7 = i2 - 1;
        if (isFirstColumn(i2, gridSize)) {
            return Math.max(0, i7);
        }
        while (true) {
            if (i7 <= 0) {
                break;
            }
            int i8 = i7 - 1;
            if (isFirstColumn(i7, gridSize)) {
                i7 = i8;
                break;
            }
            i7 = i8;
        }
        return Math.max(0, i7);
    }

    public boolean isDivider(int i2) {
        if (Arrays.binarySearch(this.mDividerIndex, i2) >= 0) {
            return true;
        }
        return false;
    }

    public void onDataChanged() {
        super.onDataChanged();
        updateCluster();
    }

    public abstract void updateCluster();

    public void updateItemHeight(ListViewHolder listViewHolder) {
        if (listViewHolder.getItemViewType() == -1) {
            this.mFirstDividerHeight = getHeight(listViewHolder);
        } else if (listViewHolder.getItemViewType() == -2) {
            this.mDividerHeight = getHeight(listViewHolder);
        } else {
            super.lambda$onBindViewHolder$0(listViewHolder);
        }
    }

    public void updateViewPositions(ArrayList<Integer> arrayList) {
        if (!this.mIsClusterDisabled) {
            this.mViewPositions.add(0);
        }
        this.mViewPositions.addAll(arrayList);
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        return getMediaItemFromCache(i2);
    }
}
