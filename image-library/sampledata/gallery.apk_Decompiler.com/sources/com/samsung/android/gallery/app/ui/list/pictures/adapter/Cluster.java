package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import android.util.Pair;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Cluster {
    int[] findDataPositionRange(MediaItem mediaItem) {
        return new int[]{0, 1};
    }

    ClusterItem getClusterItem(int i2);

    int[] getClusterItemRange(MediaItem mediaItem) {
        return null;
    }

    int getColumnSpan(int i2, int i7);

    int getCount();

    int getDataPosition(int i2);

    int getDataPosition(int i2, float f, float f5, int i7) {
        return getDataPosition(i2);
    }

    ArrayList<Integer> getDataPositionList(int i2) {
        return new ArrayList<>();
    }

    Integer getDateCount(int i2) {
        return null;
    }

    String getDateIds(int i2) {
        return null;
    }

    int getDividerIndex(int i2);

    int getDividerIndex(long j2);

    int getDividerIndex(MediaItem mediaItem) {
        return -1;
    }

    ArrayList<Pair<String, Integer>> getDividerScroll(int i2, int i7, int i8, int i10, int i11) {
        return null;
    }

    ArrayList<Integer> getDividers();

    int getExtraOffset(int i2, int i7, int i8) {
        return 0;
    }

    int getItemHeight(int i2, int i7) {
        return 0;
    }

    int getItemViewType(int i2);

    int getMaxScroll(int i2, float f, int i7, int i8);

    int getNextDividerIndex(int i2) {
        return 0;
    }

    int getPrevDividerIndex(int i2) {
        return 0;
    }

    int getRowCount(int i2) {
        return 0;
    }

    Integer[] getRowInfo(int i2, int i7) {
        return null;
    }

    int getRowSpan(int i2, int i7) {
        return 1;
    }

    int getScrollIndex(int i2) {
        return 0;
    }

    String[] getScrollIndexDates() {
        return null;
    }

    ScrollText getScrollText(int i2);

    int getSpanCount() {
        return 1;
    }

    SpanInfo getSpanInfo(int i2, int i7) {
        return null;
    }

    int getStartSpan(int i2, int i7);

    int getViewCount() {
        return getCount();
    }

    int getViewPosition(int i2);

    ArrayList<MediaItem> getYearItemList(int i2) {
        return null;
    }

    boolean isDivider(int i2);

    void recalculatePosition(int i2) {
    }

    boolean supportExpand(int i2) {
        return false;
    }

    int getDividerIndex(ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap, String str) {
        for (Map.Entry next : concurrentHashMap.entrySet()) {
            if (((ClusterItem) next.getValue()).getDate().equals(str)) {
                return ((Integer) next.getKey()).intValue();
            }
        }
        return -1;
    }

    void recalculatePosition(int i2, int i7) {
    }

    void clear() {
    }

    void onGridSizeChanged(int i2) {
    }

    void setAllDates(HashMap<String, String> hashMap, LinkedHashMap<String, Integer> linkedHashMap) {
    }
}
