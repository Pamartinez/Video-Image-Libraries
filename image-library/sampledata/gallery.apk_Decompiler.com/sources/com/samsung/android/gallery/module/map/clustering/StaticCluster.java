package com.samsung.android.gallery.module.map.clustering;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.map.clustering.IClusterItem;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaticCluster<T extends IClusterItem> implements ICluster<T> {
    private final double[] mCenter;
    private final List<T> mItems = new ArrayList();

    public StaticCluster(double[] dArr) {
        this.mCenter = dArr;
    }

    public boolean add(T t) {
        return this.mItems.add(t);
    }

    public void addFirst(T t) {
        this.mItems.add(0, t);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StaticCluster) || !obj.toString().equals(toString()) || !((StaticCluster) obj).mItems.equals(this.mItems)) {
            return false;
        }
        return true;
    }

    public List<T> getItems() {
        return this.mItems;
    }

    public double[] getPosition() {
        return this.mCenter;
    }

    public int getSize() {
        return this.mItems.size();
    }

    public T getTopItem() {
        if (this.mItems.size() == 0) {
            return null;
        }
        return (IClusterItem) this.mItems.get(0);
    }

    public boolean isEntryItem() {
        IClusterItem topItem = getTopItem();
        if (topItem == null || !topItem.isEntryItem()) {
            return false;
        }
        return true;
    }

    public boolean isItemIncluded(long j2) {
        for (T isItemIncluded : this.mItems) {
            if (isItemIncluded.isItemIncluded(j2)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(T t) {
        return this.mItems.remove(t);
    }

    public void setEntryItem(boolean z) {
        IClusterItem topItem = getTopItem();
        if (topItem != null) {
            topItem.setEntryItem(z);
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("StaticCluster{mCenter=");
        for (double append : this.mCenter) {
            sb2.append(append);
            sb2.append(ArcCommonLog.TAG_COMMA);
        }
        sb2.append("}");
        return sb2.toString();
    }
}
