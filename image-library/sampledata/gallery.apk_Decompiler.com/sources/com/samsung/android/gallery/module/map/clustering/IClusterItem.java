package com.samsung.android.gallery.module.map.clustering;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClusterItem {
    double[] getPosition();

    boolean isEntryItem();

    boolean isItemIncluded(long j2);

    void setEntryItem(boolean z);
}
