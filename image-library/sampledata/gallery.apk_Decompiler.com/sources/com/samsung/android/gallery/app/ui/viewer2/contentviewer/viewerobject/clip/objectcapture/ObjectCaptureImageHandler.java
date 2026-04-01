package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture;

import Nb.c;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import java.util.Optional;
import q8.a;
import x6.f;
import x7.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureImageHandler extends ObjectCaptureHandler {
    private boolean mIsGIFAnimationMode;
    private PhotoView mPhotoView;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCurrentItemChanged$2(Object[] objArr) {
        Boolean bool = objArr[2];
        boolean booleanValue = bool.booleanValue();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "item changed" + Logger.v(bool, this.mObjectCaptureHelper));
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper != null && !booleanValue) {
            objectCaptureHelper.clearVariables();
            this.mThread.runOnUiThread(new g(this, 1));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onGIFAnimationMode$3() {
        this.mIsGIFAnimationMode = true;
        if (this.mObjectCaptureViewVisible) {
            disableObjectCaptureView(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onCurrentItemChanged(Object... objArr) {
        DeepSkyHelper.post(new f(1, this, objArr));
    }

    /* access modifiers changed from: private */
    public void onGIFAnimationMode(Object... objArr) {
        this.mThread.runOnUiThread(new g(this, 0));
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new x7.f(this, 0));
        this.mActionInvoker.add(ViewerAction.GIF_ANIMATION_MODE, new x7.f(this, 1));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new x7.f(this, 2));
    }

    public void bindObjectCaptureView() {
        ObjectCaptureView objectCaptureView;
        boolean z;
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            boolean z3 = this.mObjectCaptureViewVisible;
            if (z3) {
                objectCaptureView = this.mObjectCaptureView;
            } else {
                objectCaptureView = null;
            }
            if (!z3 || !this.mIsDirty) {
                z = false;
            } else {
                z = true;
            }
            photoView.bindCaptureView(objectCaptureView, false, z);
        }
    }

    public void capture(float f, float f5) {
        if (!this.mIsGIFAnimationMode) {
            onCapture(f, f5);
            return;
        }
        Log.e(this.TAG, "unable to capture object, GIF animation mode");
        sendCaptureResult(false);
    }

    public RectF getDisplayRect() {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            return photoView.getDisplayRect();
        }
        return null;
    }

    public Bitmap getLowBitmap() {
        Bitmap bitmap = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
        if (bitmap == null || bitmap.isRecycled()) {
            Bitmap bitmap2 = this.mModel.getBitmap();
            MediaItem mediaItem = this.mModel.getMediaItem();
            if (bitmap2 == null || bitmap2.isRecycled() || mediaItem == null) {
                return null;
            }
            return new BitmapOperator(bitmap2).rotate(mediaItem.getOrientation(), mediaItem.getOrientationTag()).apply();
        }
        this.mBitmapHolder.clear();
        return bitmap;
    }

    public int getTopMargin() {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            return photoView.getTopMarginFromSupplier();
        }
        return 0;
    }

    public boolean isAlreadyUp() {
        PhotoView photoView = this.mPhotoView;
        if (photoView == null || photoView.isAlreadyUp()) {
            return true;
        }
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mIsGIFAnimationMode = false;
    }

    public boolean onViewLongPress(float f, float f5) {
        if (this.mIsGIFAnimationMode) {
            return false;
        }
        return super.onViewLongPress(f, f5);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mIsGIFAnimationMode = false;
    }

    public void refreshLayout() {
        PhotoView photoView;
        if (!isObjectCaptured()) {
            return;
        }
        if (!this.mObjectCaptureViewVisible || (photoView = this.mPhotoView) == null) {
            this.mIsDirty = true;
        } else {
            photoView.post(new c(photoView, 3));
        }
    }
}
