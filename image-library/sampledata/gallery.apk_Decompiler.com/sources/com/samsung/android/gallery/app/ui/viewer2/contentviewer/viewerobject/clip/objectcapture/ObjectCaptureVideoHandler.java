package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.PlayState;
import java.lang.ref.WeakReference;
import java.util.Optional;
import q8.a;
import x7.k;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureVideoHandler extends ObjectCaptureHandler {
    private boolean mIsVideoPlayMode;
    private WeakReference<Bitmap> mLastSyncedBitmap;
    private IMediaPlayerView mMediaPlayerView;
    private PhotoView mPhotoView;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        resetObjectCaptureView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addAdditionalEventListener$1(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addAdditionalEventListener$2(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addAdditionalEventListener$3(Object[] objArr) {
        this.mLastSyncedBitmap = new WeakReference<>(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoStarted$4() {
        this.mIsVideoPlayMode = true;
        if (this.mIsObjectCapturing) {
            this.mIsPlayModeChanged = true;
        }
        resetObjectCaptureView();
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeek(Object... objArr) {
        resetObjectCaptureView();
    }

    /* access modifiers changed from: private */
    public void onVideoPlayPauseClicked(Object... objArr) {
        this.mIsVideoPlayMode = objArr[0].booleanValue();
    }

    /* access modifiers changed from: private */
    public void onVideoStarted(Object... objArr) {
        this.mThread.runOnUiThread(new l(0, this));
    }

    /* access modifiers changed from: private */
    public void onVideoStopped(Object... objArr) {
        this.mIsVideoPlayMode = false;
    }

    private void resetObjectCaptureView() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper != null) {
            objectCaptureHelper.clearVariables();
        }
        disableObjectCaptureView(new Object[0]);
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.PLAY_PAUSE_CLICKED, new k(this, 0));
        this.mActionInvoker.add(ViewerAction.REQUEST_PLAY_TIME_UPDATED, new k(this, 1));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new k(this, 2));
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new k(this, 3));
        this.mActionInvoker.add(ViewerAction.VIDEO_STOPPED, new k(this, 4));
        addAdditionalEventListener();
    }

    public void addAdditionalEventListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new k(this, 5));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new k(this, 6));
        this.mActionInvoker.add(ViewerAction.VIDEO_PREVIEW_BITMAP_CHANGED, new k(this, 7));
    }

    public void bindObjectCaptureView() {
        ObjectCaptureView objectCaptureView;
        ObjectCaptureView objectCaptureView2;
        boolean z = true;
        if (ViewUtils.isVisible(this.mPhotoView)) {
            PhotoView photoView = this.mPhotoView;
            boolean z3 = this.mObjectCaptureViewVisible;
            if (z3) {
                objectCaptureView2 = this.mObjectCaptureView;
            } else {
                objectCaptureView2 = null;
            }
            if (!z3 || !this.mIsDirty) {
                z = false;
            }
            photoView.bindCaptureView(objectCaptureView2, false, z);
            IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
            if (iMediaPlayerView != null) {
                iMediaPlayerView.bindCaptureView((View) null, false, false);
                return;
            }
            return;
        }
        IMediaPlayerView iMediaPlayerView2 = this.mMediaPlayerView;
        if (iMediaPlayerView2 != null) {
            boolean z7 = this.mObjectCaptureViewVisible;
            if (z7) {
                objectCaptureView = this.mObjectCaptureView;
            } else {
                objectCaptureView = null;
            }
            if (!z7 || !this.mIsDirty) {
                z = false;
            }
            iMediaPlayerView2.bindCaptureView(objectCaptureView, false, z);
            PhotoView photoView2 = this.mPhotoView;
            if (photoView2 != null) {
                photoView2.bindCaptureView((View) null, false, false);
            }
        }
    }

    public void capture(float f, float f5) {
        if (isVideoStopped()) {
            onCapture(f, f5);
            return;
        }
        Log.e(this.TAG, "unable to capture object, video play mode");
        sendCaptureResult(false);
    }

    public RectF getDisplayRect() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getDisplayRect();
        }
        return null;
    }

    public Bitmap getLowBitmap() {
        Bitmap bitmap;
        Bitmap bitmap2 = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
        if (bitmap2 == null || bitmap2.isRecycled()) {
            if (ViewUtils.isVisible(this.mPhotoView)) {
                WeakReference<Bitmap> weakReference = this.mLastSyncedBitmap;
                if (weakReference != null) {
                    bitmap = weakReference.get();
                } else {
                    bitmap = null;
                }
                if (bitmap != null && !bitmap.isRecycled()) {
                    return bitmap;
                }
            }
            IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
            if (iMediaPlayerView == null || iMediaPlayerView.isPlaying()) {
                return null;
            }
            return this.mMediaPlayerView.captureFrameInBackground(1920, false);
        }
        this.mBitmapHolder.clear();
        return bitmap2;
    }

    public int getTopMargin() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getTopMarginFromSupplier();
        }
        return 0;
    }

    public boolean isAlreadyUp() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView == null || !iMediaPlayerView.isAlreadyUp()) {
            return false;
        }
        return true;
    }

    public boolean isVideoStopped() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView == null) {
            return !this.mIsVideoPlayMode;
        }
        if (iMediaPlayerView.getPlayState() == PlayState.PAUSE || this.mMediaPlayerView.getPlayState() == PlayState.COMPLETE) {
            return true;
        }
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mIsVideoPlayMode = false;
        WeakReference<Bitmap> weakReference = this.mLastSyncedBitmap;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.mLastSyncedBitmap = null;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mIsVideoPlayMode = false;
        WeakReference<Bitmap> weakReference = this.mLastSyncedBitmap;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.mLastSyncedBitmap = null;
    }

    public void refreshLayout() {
        IMediaPlayerView iMediaPlayerView;
        if (!isObjectCaptured()) {
            return;
        }
        if (!this.mObjectCaptureViewVisible || (iMediaPlayerView = this.mMediaPlayerView) == null) {
            this.mIsDirty = true;
        } else {
            iMediaPlayerView.refreshCaptureView();
        }
    }
}
