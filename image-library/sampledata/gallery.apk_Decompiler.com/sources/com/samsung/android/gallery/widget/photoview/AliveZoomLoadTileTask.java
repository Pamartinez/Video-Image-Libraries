package com.samsung.android.gallery.widget.photoview;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.gallery.module.graphics.ImageRegionDecoder;
import com.samsung.android.gallery.widget.photoview.AliveZoomCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AliveZoomLoadTileTask extends LoadTileTask {
    private int mOrientation;
    private int mScale;
    private ZoomCompat mZoomCompat;
    public final int sourceHash;

    public AliveZoomLoadTileTask(PhotoView photoView, ImageRegionDecoder imageRegionDecoder, AliveZoomCompat.TileCompat tileCompat) {
        super(photoView, imageRegionDecoder, tileCompat);
        this.sourceHash = photoView.getSourceHash();
    }

    private Bitmap enhanceBitmap(Bitmap bitmap) {
        AliveZoomCompat.TileCompat tileCompat = (AliveZoomCompat.TileCompat) getTile();
        if (tileCompat == null || bitmap == null) {
            return null;
        }
        PhotoView photoView = getPhotoView();
        try {
            if (tryToEnhance(photoView, tileCompat)) {
                return AliveZoomScheduler.getInstance().zoomImage(photoView.getSourceHash(), bitmap, this.mScale, rotatePaddingRect(tileCompat.paddingRect, this.mOrientation), this.mZoomCompat.getSceneType());
            }
            tileCompat.loading = false;
            return null;
        } catch (Exception e) {
            Exception exc = e;
            a.s(exc, new StringBuilder("enhanceBitmap failed. e="), this.TAG);
            return null;
        }
    }

    private Rect rotatePaddingRect(Rect rect, int i2) {
        if (rect != null && i2 > 0) {
            if (i2 == 90) {
                return new Rect(rect.top, rect.right, rect.bottom, rect.left);
            }
            if (i2 == 180) {
                return new Rect(rect.right, rect.bottom, rect.left, rect.top);
            }
            if (i2 == 270) {
                return new Rect(rect.bottom, rect.left, rect.top, rect.right);
            }
        }
        return rect;
    }

    private boolean tryToEnhance(PhotoView photoView, Tile tile) {
        if (photoView == null || !photoView.isViewAndBitmapReady() || tile == null || !tile.visible) {
            return false;
        }
        return true;
    }

    public void fileSRect(PhotoView photoView, Tile tile) {
        photoView.mImageProcessor.fileSRect(((AliveZoomCompat.TileCompat) tile).fileRect, tile.fileSRect);
    }

    public AliveZoomLoadTileTask setExtra(ZoomCompat zoomCompat, int i2, int i7) {
        this.mZoomCompat = zoomCompat;
        this.mScale = i2;
        this.mOrientation = i7;
        return this;
    }

    public Bitmap doInBackground(Void... voidArr) {
        return enhanceBitmap(decodeRegion(getTile()));
    }

    public void verifyTiledBitmap(Bitmap bitmap, Tile tile) {
    }
}
