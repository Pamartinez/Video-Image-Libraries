package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CropHandler {
    protected final String TAG = getClass().getSimpleName();
    Bitmap mBitmap;
    int mBitmapHeight;
    int mBitmapWidth;
    final Intent mIntent;
    final MediaItem mMediaItem;

    public CropHandler(Intent intent, MediaItem mediaItem) {
        this.mIntent = intent;
        this.mMediaItem = mediaItem;
    }

    public abstract Intent buildIntent();

    public abstract boolean process(Rect rect);

    public CropHandler setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        return this;
    }

    public void recycle() {
    }
}
