package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import D7.g;
import D7.h;
import D7.i;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.photoview.PhotoView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoImageLoader extends ImageLoader {
    private Bitmap mImageBmp;
    private Bitmap mMotionPreview;
    private PhotoView mPhotoView;

    private int getReverseOrientation(int i2) {
        if (i2 != 0) {
            return 360 - i2;
        }
        return i2;
    }

    private boolean isMotionPreviewEnabled() {
        return this.mModel.getStateHelper().isMotionPreviewEnabled(this.mModel);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBind$1(MediaItem mediaItem, Integer num, Bitmap bitmap) {
        onFrameBitmapLoaded(bitmap, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewAttached$2() {
        processLoadedBitmapInternal(this.mMotionPreview, this.mModel.getMediaItem(), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewAttached$3(MediaItem mediaItem, Integer num, Bitmap bitmap) {
        onFrameBitmapLoaded(bitmap, mediaItem);
    }

    private void onFrameBitmapLoaded(Bitmap bitmap, MediaItem mediaItem) {
        if (bitmap == null) {
            Log.e(this.TAG, "onFrameBitmapLoaded bitmap is null");
            super.processLoadedBitmapInternal(this.mImageBmp, this.mModel.getMediaItem(), false);
            return;
        }
        MediaItem mediaItem2 = this.mModel.getMediaItem();
        if (mediaItem2 != null && MediaItemUtil.equals(mediaItem2, mediaItem)) {
            this.mMotionPreview = new BitmapOperator(bitmap).rotate(getReverseOrientation(mediaItem2.getOrientation())).apply();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onFrameBitmapLoaded : " + bitmap, this.mMotionPreview);
            try {
                processLoadedBitmap(mediaItem2, this.mMotionPreview, false);
            } catch (NullPointerException unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void setToMotionPhotoOriginalImage(Object... objArr) {
        Bitmap bitmap;
        MediaItem mediaItem = objArr[0];
        if (this.mImageBmp == null) {
            this.mImageBmp = (Bitmap) getBlackboard().read(ArgumentsUtil.removeArgs(getDecodedImageKey(this.mModel.getMediaItem(), this.mModel.getPosition())));
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "setToMotionPhotoOriginalImage : read mImageBmp : " + this.mImageBmp);
        }
        if (this.mImageBmp != null && this.mModel.getBitmap() != (bitmap = this.mImageBmp)) {
            super.processLoadedBitmapInternal(bitmap, mediaItem, false);
        } else if (this.mImageBmp == null) {
            Log.e(this.TAG, "setToMotionPhotoOriginalImage : fail set original image");
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.SET_TO_MOTION_PHOTO_ORIGINAL_IMAGE, new i(this, 0));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new i(this, 1));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        this.mMotionPreview = null;
        this.mImageBmp = null;
        super.invalidate(mediaItem, i2, mediaItem2, i7);
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        if (isMotionPreviewEnabled()) {
            Log.d(this.TAG, "onBind : loadBitmap");
            MetadataManager.getInstance().loadMotionBitmap(mediaItem, new h(this, mediaItem, 1));
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        if (!isMotionPreviewEnabled()) {
            return;
        }
        if (this.mMotionPreview != null) {
            Log.d(this.TAG, "onViewAttached : mMotionPreview");
            this.mThread.runOnUiThread(new g(0, this), 1);
            return;
        }
        MediaItem mediaItem = this.mModel.getMediaItem();
        Log.d(this.TAG, "onViewAttached : loadBitmap");
        MetadataManager.getInstance().loadMotionBitmap(this.mModel.getMediaItem(), new h(this, mediaItem, 0));
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mMotionPreview = null;
        this.mImageBmp = null;
    }

    public void processLoadedBitmapInternal(Bitmap bitmap, MediaItem mediaItem, boolean z) {
        boolean z3;
        PhotoView photoView = this.mPhotoView;
        if (photoView == null || photoView.getBitmap() == null) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (bitmap == this.mMotionPreview || !isMotionPreviewEnabled() || !z3) {
            if (!z3) {
                Log.w(this.TAG, "photoview doesn't have bitmap");
            }
            super.processLoadedBitmapInternal(bitmap, mediaItem, z);
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "keep bitmap : " + bitmap);
        this.mImageBmp = bitmap;
    }
}
