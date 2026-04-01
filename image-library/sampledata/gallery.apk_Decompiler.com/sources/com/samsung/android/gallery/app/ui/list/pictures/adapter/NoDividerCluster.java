package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoDividerCluster implements Cluster {
    protected int mCount;

    public NoDividerCluster(MediaData mediaData) {
        int i2;
        if (mediaData != null) {
            i2 = mediaData.getCount();
        } else {
            i2 = 0;
        }
        this.mCount = i2;
    }

    public ClusterItem getClusterItem(int i2) {
        return null;
    }

    public int getColumnSpan(int i2, int i7) {
        return 1;
    }

    public int getCount() {
        return 0;
    }

    public int getDividerIndex(int i2) {
        return -1;
    }

    public ArrayList<Integer> getDividers() {
        return new ArrayList<>();
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        int i10;
        int i11 = this.mCount;
        int i12 = i11 / i2;
        if (i11 % i2 == 0) {
            i10 = 0;
        } else {
            i10 = 1;
        }
        return (int) (((float) (i12 + i10)) * f);
    }

    public ScrollText getScrollText(int i2) {
        return null;
    }

    public SpanInfo getSpanInfo(int i2, int i7) {
        return new SpanInfo(i2 / i7, i2 % i7, getItemViewType(i2));
    }

    public int getStartSpan(int i2, int i7) {
        return i2 % i7;
    }

    public boolean isDivider(int i2) {
        return false;
    }

    public int getDividerIndex(long j2) {
        return -1;
    }

    public int getDataPosition(int i2) {
        return i2;
    }

    public int getViewPosition(int i2) {
        return i2;
    }
}
