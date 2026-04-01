package com.samsung.android.gallery.module.dataset.tables;

import android.database.Cursor;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractSorter {
    final String mLocationKey;
    Object mPriorityValue;
    final ArrayList<SortData> mSortedList = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SortData {
        final int mPosition;
        final Object mSortingValue;

        public SortData(int i2, Object obj) {
            this.mPosition = i2;
            this.mSortingValue = obj;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public Object getSortingValue() {
            return this.mSortingValue;
        }
    }

    public AbstractSorter(String str) {
        this.mLocationKey = str;
    }

    public int getIndex(int i2) {
        synchronized (this.mSortedList) {
            int i7 = 0;
            while (i7 < this.mSortedList.size()) {
                try {
                    if (this.mSortedList.get(i7).mPosition == i2) {
                        return i7;
                    }
                    i7++;
                } finally {
                }
            }
            return i2;
        }
    }

    public int getPosition(int i2) {
        synchronized (this.mSortedList) {
            try {
                if (!this.mSortedList.isEmpty()) {
                    if (i2 < this.mSortedList.size()) {
                        i2 = this.mSortedList.get(i2).getPosition();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public ArrayList<SortData> getSortedList() {
        return this.mSortedList;
    }

    public void setPriorityValues(Object obj) {
        this.mPriorityValue = obj;
    }

    public abstract void sort(Cursor cursor);
}
