package com.samsung.android.gallery.widget.photoview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$styleable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoViewCompat extends PhotoView {
    private final ZoomCompat mZoomCompat;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FeatureHolder {
        static final boolean SUPPORT_ALIVE_ZOOM = Features.isEnabled(Features.SUPPORT_ALIVE_ZOOM);
    }

    public PhotoViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ZoomCompat zoomCompat;
        if (supportAliveZoom(context, attributeSet)) {
            zoomCompat = new AliveZoomCompat();
        } else {
            zoomCompat = new ZoomCompat() {
            };
        }
        this.mZoomCompat = zoomCompat;
    }

    private boolean supportAliveZoom(Context context, AttributeSet attributeSet) {
        if (attributeSet == null || !FeatureHolder.SUPPORT_ALIVE_ZOOM) {
            return false;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PhotoView);
        boolean z = obtainStyledAttributes.getBoolean(R$styleable.PhotoView_aliveZoom, true);
        obtainStyledAttributes.recycle();
        return z;
    }

    public int getSceneType() {
        return this.mZoomCompat.getSceneType();
    }

    public void initTileMap(Point point) {
        super.initTileMap(point);
        this.mZoomCompat.initTileMap(this, point);
    }

    public void recycleTileMap() {
        super.recycleTileMap();
        this.mZoomCompat.recycleTileMap(this);
    }

    public void refreshRequiredTiles(boolean z, PhotoViewPositionControl photoViewPositionControl) {
        super.refreshRequiredTiles(z, photoViewPositionControl);
        this.mZoomCompat.refreshRequiredTiles(this, z);
    }

    public void release() {
        this.mZoomCompat.release();
    }

    public void renderTileOnDraw(Canvas canvas) {
        super.renderTileOnDraw(canvas);
        this.mZoomCompat.renderTileOnDraw(this, canvas);
    }

    public void reset(boolean z) {
        super.reset(z);
        this.mZoomCompat.reset(z);
    }

    public void setAliveZoomEnabled(boolean z) {
        this.mZoomCompat.setAliveZoomEnabled(z);
    }

    public void setImage(MediaItem mediaItem, Bitmap bitmap) {
        super.setImage(mediaItem, bitmap);
        this.mZoomCompat.setImage(this, mediaItem, bitmap);
    }

    public boolean supportZoomCompat() {
        return this.mZoomCompat.support();
    }

    public void tryRegionDecoding() {
        if (!this.mConfig.mBlockRegionDecoding) {
            super.tryRegionDecoding();
            this.mZoomCompat.tryRegionDecoding(this);
            return;
        }
        Log.w(this.TAG, "block region decoding");
    }
}
