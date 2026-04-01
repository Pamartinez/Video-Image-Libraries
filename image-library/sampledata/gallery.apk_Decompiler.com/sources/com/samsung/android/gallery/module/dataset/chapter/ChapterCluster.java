package com.samsung.android.gallery.module.dataset.chapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ChapterCluster implements Cluster {
    public boolean cluster(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, boolean z) {
        if (handle(layoutInfo, layoutInfo2) || z) {
            return true;
        }
        return false;
    }

    public boolean handle(LayoutInfo layoutInfo, LayoutInfo layoutInfo2) {
        if (layoutInfo2 == null || layoutInfo.chapterId != layoutInfo2.chapterId) {
            return true;
        }
        return false;
    }
}
