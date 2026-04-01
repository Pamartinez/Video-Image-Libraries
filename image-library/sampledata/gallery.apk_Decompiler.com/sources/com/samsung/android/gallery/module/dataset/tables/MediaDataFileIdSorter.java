package com.samsung.android.gallery.module.dataset.tables;

import Z8.c;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataFileIdSorter {
    private final Comparator<Long> mComparator = new c(0, this);
    private final ArrayList<Long> mList = new ArrayList<>();
    private int mOrder;
    private final HashMap<Long, SortHolder> mSortHolderMap = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SortHolder {
        long mDateTime;
        long mFileId;

        public SortHolder(long j2, long j3) {
            this.mFileId = j2;
            this.mDateTime = j3;
        }
    }

    public MediaDataFileIdSorter(int i2) {
        this.mOrder = i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$new$0(Long l, Long l8) {
        SortHolder sortHolder = this.mSortHolderMap.get(l);
        SortHolder sortHolder2 = this.mSortHolderMap.get(l8);
        int compare = Long.compare(sortHolder.mDateTime, sortHolder2.mDateTime);
        if (compare == 0) {
            int compare2 = Long.compare(sortHolder.mFileId, sortHolder2.mFileId);
            if (this.mOrder == 2) {
                return -compare2;
            }
            return compare2;
        } else if (this.mOrder == 2) {
            return -compare;
        } else {
            return compare;
        }
    }

    public void add(long j2, long j3) {
        this.mList.add(Long.valueOf(j2));
        this.mSortHolderMap.put(Long.valueOf(j2), new SortHolder(j2, j3));
    }

    public ArrayList<Long> getSortedIds() {
        return this.mList;
    }

    public void sort() {
        this.mList.sort(this.mComparator);
    }
}
