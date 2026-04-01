package com.samsung.android.gallery.module.dataset.tables;

import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutDuplicateClusterIndexer {
    private int mCount;
    private int[] mDeleteGroupIds;
    private final ArrayList<Integer> mDividerIndexList = new ArrayList<>();
    private final ConcurrentHashMap<Integer, ClusterItem> mDividerItemMap = new ConcurrentHashMap<>();
    private int[] mScrollIndex;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CleanOutDuplicateClusterItem extends AbsClusterItem {
        int count;
        int deleteGroupId;
        long fileSize;
        String fileTitle;

        public CleanOutDuplicateClusterItem(MediaItem mediaItem) {
            this.deleteGroupId = MediaItemSuggest.getDeleteGroupId(mediaItem);
            this.fileTitle = FileUtils.getBaseName(mediaItem.getDisplayName());
            this.fileSize = mediaItem.getFileSize();
        }

        /* access modifiers changed from: private */
        public void setCount(int i2) {
            this.count = i2;
        }

        public int getCount() {
            return this.count;
        }

        public String getDate() {
            return null;
        }

        public long getDateTaken() {
            return 0;
        }

        public int getDeleteGroupId() {
            return this.deleteGroupId;
        }

        public long getFileSize() {
            return this.fileSize;
        }

        public String getTitle() {
            return this.fileTitle;
        }
    }

    private CleanOutDuplicateClusterItem createClusterItem(MediaItem mediaItem) {
        return new CleanOutDuplicateClusterItem(mediaItem);
    }

    private MediaItem getCurrentItem(DefaultTable defaultTable, int i2) {
        MediaItem mediaItem = defaultTable.get(i2);
        if (mediaItem == null) {
            return defaultTable.loadAndGet(i2);
        }
        return mediaItem;
    }

    private boolean isDiffDeleteGroup(MediaItem mediaItem, MediaItem mediaItem2) {
        if (MediaItemSuggest.getDeleteGroupId(mediaItem) != MediaItemSuggest.getDeleteGroupId(mediaItem2)) {
            return true;
        }
        return false;
    }

    private void reset(int i2) {
        this.mCount = i2;
        this.mScrollIndex = new int[i2];
        this.mDeleteGroupIds = new int[i2];
    }

    public void calculate(DefaultTable defaultTable) {
        int i2;
        int i7;
        int i8;
        try {
            reset(defaultTable.getLoadedCount());
            TimeTickLog timeTickLog = new TimeTickLog("calculate duplicate");
            if (this.mCount > 0) {
                CleanOutDuplicateClusterItem cleanOutDuplicateClusterItem = null;
                int i10 = 0;
                int i11 = 0;
                i8 = 0;
                i2 = 0;
                MediaItem mediaItem = null;
                i7 = -1;
                while (i10 < this.mCount) {
                    MediaItem currentItem = getCurrentItem(defaultTable, i10);
                    if (mediaItem != null) {
                        if (!isDiffDeleteGroup(currentItem, mediaItem)) {
                            i11++;
                            i10++;
                            mediaItem = currentItem;
                        }
                    }
                    if (cleanOutDuplicateClusterItem != null) {
                        cleanOutDuplicateClusterItem.setCount(i11);
                    }
                    cleanOutDuplicateClusterItem = createClusterItem(currentItem);
                    int size = this.mDividerItemMap.size() + i10;
                    this.mDividerIndexList.add(Integer.valueOf(size));
                    this.mDividerItemMap.put(Integer.valueOf(size), cleanOutDuplicateClusterItem);
                    this.mScrollIndex[i2] = size;
                    i7 = (this.mCount - 1) - i2;
                    this.mDeleteGroupIds[i7] = cleanOutDuplicateClusterItem.getDeleteGroupId();
                    i8++;
                    i2++;
                    i11 = 0;
                    i11++;
                    i10++;
                    mediaItem = currentItem;
                }
                if (cleanOutDuplicateClusterItem != null) {
                    cleanOutDuplicateClusterItem.setCount(i11);
                }
            } else {
                i8 = 0;
                i2 = 0;
                i7 = -1;
            }
            timeTickLog.tock(0);
            this.mCount = i8;
            if (i7 != -1) {
                int[] iArr = this.mDeleteGroupIds;
                this.mDeleteGroupIds = Arrays.copyOfRange(iArr, i7, iArr.length - 1);
            }
            Log.d("CleanOutDuplicateClusterIndexer", "calculate count=" + this.mCount + ", map=" + this.mDividerItemMap.size() + ", list=" + this.mDividerIndexList.size());
            int[] iArr2 = this.mScrollIndex;
            if (iArr2.length > i2) {
                this.mScrollIndex = Arrays.copyOf(iArr2, i2);
            }
        } catch (NullPointerException e) {
            Log.e("CleanOutDuplicateClusterIndexer", e.getMessage());
            reset(0);
        }
    }

    public int getCount() {
        return this.mCount;
    }

    public int[] getDeleteGroupIds() {
        return this.mDeleteGroupIds;
    }

    public ArrayList<Integer> getDividerIndexList() {
        return this.mDividerIndexList;
    }

    public ConcurrentHashMap<Integer, ClusterItem> getDividerItemMap() {
        return this.mDividerItemMap;
    }

    public int[] getScrollIndex() {
        return this.mScrollIndex;
    }
}
