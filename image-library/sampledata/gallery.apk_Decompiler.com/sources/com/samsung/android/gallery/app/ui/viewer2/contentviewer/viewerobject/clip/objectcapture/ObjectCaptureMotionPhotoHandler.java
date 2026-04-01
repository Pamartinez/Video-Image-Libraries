package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.PlayState;
import java.util.Optional;
import q8.a;
import t8.e;
import x7.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureMotionPhotoHandler extends ObjectCaptureImageHandler {
    private boolean mIsVideoPlayMode;
    private IMediaPlayerView mMediaPlayerView;

    private boolean isImageCaptureMode() {
        return this.mModel.getMotionPlayViewer().isPhoto;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        resetObjectCaptureView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addAdditionalEventListener$2(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoStarted$3() {
        this.mIsVideoPlayMode = true;
        if (this.mIsObjectCapturing) {
            this.mIsPlayModeChanged = true;
        }
        resetObjectCaptureView();
    }

    /* access modifiers changed from: private */
    public void onMotionPlayViewerChanged(Object... objArr) {
        if (objArr[0].isVideo != objArr[1].isVideo) {
            if (this.mIsObjectCapturing) {
                this.mIsPlayModeChanged = true;
            }
            if (this.mObjectCaptureViewVisible) {
                disableObjectCaptureView(new Object[0]);
            }
            resetObjectCaptureView();
        }
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
        this.mThread.runOnUiThread(new e(29, this));
    }

    /* access modifiers changed from: private */
    public void onVideoStopped(Object... objArr) {
        this.mIsVideoPlayMode = false;
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.REQUEST_PLAY_TIME_UPDATED, new h(this, 0));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new h(this, 1));
        this.mActionInvoker.add(ViewerAction.PLAY_PAUSE_CLICKED, new h(this, 2));
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new h(this, 3));
        this.mActionInvoker.add(ViewerAction.VIDEO_STOPPED, new h(this, 4));
        this.mActionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new h(this, 5));
        addAdditionalEventListener();
    }

    public void addAdditionalEventListener() {
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new h(this, 6));
    }

    public void bindObjectCaptureVideoView() {
        ObjectCaptureView objectCaptureView;
        boolean z;
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
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
            iMediaPlayerView.bindCaptureView(objectCaptureView, false, z);
        }
    }

    public void bindObjectCaptureView() {
        if (isImageCaptureMode()) {
            super.bindObjectCaptureView();
        } else {
            bindObjectCaptureVideoView();
        }
    }

    public void capture(float f, float f5) {
        if (isImageCaptureMode() || isVideoStopped()) {
            onCapture(f, f5);
            return;
        }
        Log.e(this.TAG, "unable to capture object, motion photo is playing");
        sendCaptureResult(false);
    }

    public int getCurrentFrame() {
        if (isImageCaptureMode()) {
            Long l = (Long) Optional.ofNullable(this.mModel.getMediaItem()).map(new a(27)).orElse(-1L);
            if (l.longValue() == -1) {
                return -1;
            }
            return Math.floorDiv(l.intValue(), 1000);
        }
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getCurrentPosition();
        }
        return 0;
    }

    public RectF getDisplayRect() {
        if (isImageCaptureMode()) {
            return super.getDisplayRect();
        }
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getDisplayRect();
        }
        return null;
    }

    public Bitmap getLowBitmap() {
        if (isImageCaptureMode()) {
            return super.getLowBitmap();
        }
        return getVideoBitmap();
    }

    public int getTopMargin() {
        if (isImageCaptureMode()) {
            return super.getTopMargin();
        }
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getTopMarginFromSupplier();
        }
        return 0;
    }

    public Bitmap getVideoBitmap() {
        Bitmap bitmap = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
        if (bitmap == null || bitmap.isRecycled()) {
            IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
            if (iMediaPlayerView == null || iMediaPlayerView.isPlaying()) {
                return null;
            }
            return this.mMediaPlayerView.captureFrameInBackground(1920, false);
        }
        this.mBitmapHolder.clear();
        return bitmap;
    }

    public boolean isAlreadyUp() {
        if (isMotionPhotoVideoMode()) {
            return this.mMediaPlayerView.isAlreadyUp();
        }
        return super.isAlreadyUp();
    }

    public boolean isMotionPhotoVideoMode() {
        if (this.mMediaPlayerView == null || !this.mModel.getMotionPlayViewer().isVideo) {
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
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mIsVideoPlayMode = false;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mIsVideoPlayMode = false;
    }

    public void postAnalyticsDetailLogPressLog() {
        String str;
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            AnalyticsLogger instance = AnalyticsLogger.getInstance();
            String screenId = eventContext.getScreenId();
            String analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_LONG_PRESS_CAPTURE.toString();
            if (isSegmentSelected()) {
                str = AnalyticsDetail.EVENT_DETAIL_CAPTURE_LONG_PRESS_TYPE_NONE_REPRESENTATIVE_CLIP.toString();
            } else {
                str = AnalyticsDetail.EVENT_DETAIL_CAPTURE_LONG_PRESS_TYPE_MOTION_PHOTO_CLIP.toString();
            }
            instance.postLog(screenId, analyticsEventId, str);
        }
    }

    public void refreshLayout() {
        if (isImageCaptureMode()) {
            super.refreshLayout();
        } else {
            refreshVideoLayout();
        }
    }

    public void refreshVideoLayout() {
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

    public void resetObjectCaptureView() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper != null) {
            objectCaptureHelper.clearVariables();
        }
        disableObjectCaptureView(new Object[0]);
    }
}
