package com.samsung.android.gallery.widget.photoview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface ZoomCompat {
    int getSceneType() {
        return -1;
    }

    boolean support() {
        return false;
    }

    void release() {
    }

    void recycleTileMap(PhotoView photoView) {
    }

    void reset(boolean z) {
    }

    void setAliveZoomEnabled(boolean z) {
    }

    void tryRegionDecoding(PhotoView photoView) {
    }

    void initTileMap(PhotoView photoView, Point point) {
    }

    void refreshRequiredTiles(PhotoView photoView, boolean z) {
    }

    void renderTileOnDraw(PhotoView photoView, Canvas canvas) {
    }

    void setImage(PhotoView photoView, MediaItem mediaItem, Bitmap bitmap) {
    }
}
