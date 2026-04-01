package com.samsung.android.gallery.module.dataset.chapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ChunkCluster implements Cluster {
    private int count;

    private boolean chunkChanged(LayoutInfo layoutInfo, LayoutInfo layoutInfo2) {
        if (layoutInfo2 == null || layoutInfo.chunkId != layoutInfo2.chunkId) {
            return true;
        }
        return false;
    }

    private boolean handle(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, boolean z) {
        if (hasChunkId(layoutInfo)) {
            this.count++;
            if (z || chunkChanged(layoutInfo, layoutInfo2)) {
                if (this.count >= 3) {
                    layoutInfo.chunkDisplayable = true;
                } else if (layoutInfo.isSimilarChunk() && !layoutInfo.horizontal) {
                    layoutInfo.similarChunkDisplayable = true;
                }
                this.count = 0;
                return true;
            }
        } else {
            this.count = 0;
        }
        return false;
    }

    private boolean hasChunkId(LayoutInfo layoutInfo) {
        if (layoutInfo.chunkId > 0) {
            return true;
        }
        return false;
    }

    public boolean cluster(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, boolean z) {
        if (handle(layoutInfo, layoutInfo2, z) || z) {
            return true;
        }
        return false;
    }
}
