package com.samsung.android.gallery.module.map.clustering;

import com.samsung.android.gallery.module.map.clustering.IClusterItem;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClusteringListener<T extends IClusterItem> {
    void onClustersChanged(Set<ICluster<T>> set);
}
