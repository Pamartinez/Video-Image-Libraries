package com.samsung.android.gallery.widget.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemotePhotoView extends PhotoView {
    protected PhotoViewMotionControl mParentMotionControl;

    public RemotePhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void createDefaultMotionControl(PhotoViewMotionControl photoViewMotionControl) {
        this.mParentMotionControl = photoViewMotionControl;
        PhotoViewMotionControl createDefaultMotionControl = createDefaultMotionControl(getParent());
        this.mMotionControl = createDefaultMotionControl;
        photoViewMotionControl.setSecondaryControl(createDefaultMotionControl);
    }

    public void fitToBounds(boolean z) {
        super.fitToBounds(true);
    }

    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (IllegalStateException e) {
            Log.e((CharSequence) this.TAG, "oDraw failed", (Throwable) e);
        }
    }

    public void setImage(MediaItem mediaItem, Bitmap bitmap) {
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "setPresentationImage {preview=" + this.mImageProcessor.isPreviewMode() + ",loaded=" + this.mImageProcessor.isBaseLayerReady() + "}, " + Logger.toSimpleString(bitmap));
        resetRegionDecodingInfoDirectly();
        reset(true);
        if (bitmap != null && !bitmap.isRecycled()) {
            setSourceInfo(mediaItem, bitmap);
            onPreviewLoaded(bitmap);
            invalidatePreview();
        }
    }

    public RemotePhotoViewMotionControl createMotionControlInstance(PhotoViewMotionControl.SourceInfoGetter sourceInfoGetter) {
        return new RemotePhotoViewMotionControl(this, sourceInfoGetter, this.mPosCtrl, this.mParentMotionControl);
    }

    public void fitToBounds(boolean z, ScaleAndTranslate scaleAndTranslate) {
        super.fitToBounds(true, scaleAndTranslate);
    }

    public void clearBitmap() {
    }
}
