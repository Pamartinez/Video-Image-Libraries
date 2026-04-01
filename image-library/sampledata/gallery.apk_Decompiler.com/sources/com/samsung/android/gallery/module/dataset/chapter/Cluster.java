package com.samsung.android.gallery.module.dataset.chapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Cluster {
    /* JADX WARNING: type inference failed for: r1v1, types: [com.samsung.android.gallery.module.dataset.chapter.Cluster, java.lang.Object] */
    static Cluster get(int i2) {
        if (i2 == 0) {
            return new ChapterCluster();
        }
        if (i2 == 1) {
            return new ChunkCluster();
        }
        return new Object();
    }

    /* access modifiers changed from: private */
    static /* synthetic */ boolean lambda$get$0(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, boolean z) {
        return false;
    }

    boolean cluster(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, boolean z);
}
