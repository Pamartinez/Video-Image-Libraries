package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction;

import G7.e;
import Nb.c;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.PlayState;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.Optional;
import q8.a;
import v7.w;
import y7.j;
import y7.k;
import y7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExtractionMotionPhotoHandler extends TextExtractionImageHandler {
    private boolean mIsVideoPlayMode;
    private IMediaPlayerView mMediaView;

    private Bitmap getVideoBitmap() {
        Bitmap bitmap = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
        if (bitmap == null || bitmap.isRecycled()) {
            IMediaPlayerView iMediaPlayerView = this.mMediaView;
            if (iMediaPlayerView == null || iMediaPlayerView.isPlaying()) {
                return null;
            }
            return this.mMediaView.captureFrameInBackground(1920, false);
        }
        this.mBitmapHolder.clear();
        return bitmap;
    }

    private boolean isVideoStopped() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView == null) {
            return false;
        }
        if (iMediaPlayerView.getPlayState() == PlayState.PAUSE || this.mMediaView.getPlayState() == PlayState.COMPLETE) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        resetToIdle();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMotionPlayViewerChanged$6(Object obj, Bundle bundle) {
        requestDetect();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMotionPlayViewerChanged$7(EventContext eventContext) {
        eventContext.subscribeInstant("lifecycle://on_activity_resume", new e(9, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCaptureView$4() {
        this.mMediaView.refreshCaptureView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDetect$5() {
        resetTextHelper();
        if (!this.mPaused) {
            TextExtractionHelper textExtractionHelper = this.mTextHelper;
            if (textExtractionHelper == null || textExtractionHelper.isDisabled()) {
                this.mTextHelper = new TextExtractionHelper(this.mModel.getContext(), this);
            }
            Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
            detect();
        }
        this.mDetectRunnable = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetToIdle$8() {
        disableTextExtractionView(new Object[0]);
        setButtonVisibility(false, false);
    }

    /* access modifiers changed from: private */
    public void onMotionPlayViewerChanged(Object... objArr) {
        MotionPlayViewer motionPlayViewer = objArr[0];
        MotionPlayViewer motionPlayViewer2 = objArr[1];
        if (this.mTextExtractionViewVisible) {
            disableTextExtractionView(new Object[0]);
        }
        setButtonVisibility(false, false);
        if (motionPlayViewer.isVideo != motionPlayViewer2.isVideo) {
            updateMargin();
            if (this.mPaused) {
                Optional.ofNullable(this.mModel.getContainerModel().getEventContext()).ifPresent(new y5.a(1, this));
            } else {
                requestDetect();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onRequestUpdateMargin(Object... objArr) {
        if (Features.isEnabled(Features.SUPPORT_AUDIO_ERASER) && isButtonViewable() && ViewUtils.isVisible(this.mTextExtractionButton)) {
            updateMargin();
        }
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeek(Object... objArr) {
        if (this.mTextExtractionViewVisible) {
            disableTextExtractionView(new Object[0]);
        }
        setButtonVisibility(false, false);
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeekFinish(Object... objArr) {
        if (!this.mIsVideoPlayMode) {
            requestDetect();
        }
    }

    /* access modifiers changed from: private */
    public void onVideoStarted(Object... objArr) {
        this.mIsVideoPlayMode = true;
        resetToIdle();
    }

    /* access modifiers changed from: private */
    public void onVideoStopped(Object... objArr) {
        this.mIsVideoPlayMode = false;
        if (this.mModel.isFragmentResumed() && isVideoStopped() && !this.mModel.isInstantSlowMoPlayEnabled()) {
            requestDetect();
        }
    }

    /* access modifiers changed from: private */
    public void resetTextHelper() {
        try {
            TextExtractionHelper textExtractionHelper = this.mTextHelper;
            if (textExtractionHelper != null) {
                textExtractionHelper.clearDetectType(false);
                this.mTextHelper.clearVariables();
                clearTextSelection();
            }
        } catch (Exception unused) {
        }
    }

    private void resetToIdle() {
        Runnable runnable = this.mDetectRunnable;
        if (runnable != null) {
            DeepSkyHelper.removeCallbacks(runnable);
            this.mDetectRunnable = null;
        }
        this.mThread.runOnUiThread(new l(this, 1));
        DeepSkyHelper.post(new l(this, 2));
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new k(this, 1));
        this.mActionInvoker.add(ViewerAction.REQUEST_PLAY_TIME_UPDATED, new k(this, 2));
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new k(this, 3));
        this.mActionInvoker.add(ViewerAction.VIDEO_STOPPED, new k(this, 4));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new k(this, 5));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_FINISH, new k(this, 6));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new k(this, 7));
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_VIEW_VISIBILITY_CHANGED, new k(this, 0));
    }

    public void bindTextExtractionView() {
        boolean z = false;
        TextExtractionView textExtractionView = null;
        if (isMotionPhotoVideoMode()) {
            PhotoView photoView = this.mPhotoView;
            if (photoView != null) {
                photoView.bindCaptureView((View) null, true, false);
            }
            IMediaPlayerView iMediaPlayerView = this.mMediaView;
            boolean z3 = this.mTextExtractionViewVisible;
            if (z3) {
                textExtractionView = this.mTextExtractionView;
            }
            if (z3 && this.mIsDirty) {
                z = true;
            }
            iMediaPlayerView.bindCaptureView(textExtractionView, true, z);
            return;
        }
        IMediaPlayerView iMediaPlayerView2 = this.mMediaView;
        if (iMediaPlayerView2 != null) {
            iMediaPlayerView2.bindCaptureView((View) null, true, false);
        }
        super.bindTextExtractionView();
    }

    public void extract(boolean z) {
        if (this.mPaused || this.mIsVideoPlayMode) {
            clearTextExtractionViewVisibilityChangeFlag();
        } else {
            super.extract(z);
        }
    }

    public String getAnalyticsLogDetail() {
        if (isMotionPhotoVideoMode()) {
            return AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_TYPE_VIDEO.toString();
        }
        return super.getAnalyticsLogDetail();
    }

    public Bitmap getBitmap(boolean z) {
        if (!isMotionPhotoVideoMode()) {
            return super.getBitmap(z);
        }
        if (this.mMediaView.isPlaying()) {
            Log.d(this.TAG, "mediaView is playing");
            return null;
        }
        Bitmap bitmap = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
        if (bitmap != null && !bitmap.isRecycled()) {
            return bitmap;
        }
        Bitmap captureFrameInBackground = this.mMediaView.captureFrameInBackground(1920, false);
        if (captureFrameInBackground != null) {
            ObjectDictionary.setTag(captureFrameInBackground, "Recyclable", Boolean.TRUE);
            this.mBitmapHolder = new WeakReference<>(captureFrameInBackground.copy(Bitmap.Config.ARGB_8888, true));
        }
        return captureFrameInBackground;
    }

    public int getButtonBottomMargin() {
        int i2;
        int buttonBottomMargin = super.getButtonBottomMargin();
        if (!Features.isEnabled(Features.SUPPORT_AUDIO_ERASER) || !this.mModel.getContainerModel().isAudioEraserVisible()) {
            i2 = 0;
        } else {
            i2 = this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.play_button_view_bottom_margin) + this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.image_type_icon_height);
        }
        int dimensionPixelOffset = this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.play_button_view_bottom_margin) + this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.play_button_view_height) + i2;
        if (!this.mModel.getMotionPlayViewer().isVideo || MediaItemUtil.getMotionPhotoViewMode(this.mModel.getMediaItem()) != MotionPhotoViewMode.ON) {
            return buttonBottomMargin;
        }
        return buttonBottomMargin + dimensionPixelOffset;
    }

    public int getCurrentFrame() {
        if (isMotionPhotoVideoMode()) {
            return this.mMediaView.getCurrentPosition();
        }
        Long l = (Long) Optional.ofNullable(this.mModel.getMediaItem()).map(new j(0)).orElse(-1L);
        if (l.longValue() == -1) {
            return -1;
        }
        return Math.floorDiv(l.intValue(), 1000);
    }

    public RectF getDisplayRect() {
        if (isMotionPhotoVideoMode()) {
            return this.mMediaView.getDisplayRect();
        }
        return super.getDisplayRect();
    }

    public Bitmap getLowBitmap() {
        if (this.mModel.getMotionPlayViewer().isPhoto) {
            return super.getLowBitmap();
        }
        return getVideoBitmap();
    }

    public int getTopMargin() {
        if (isMotionPhotoVideoMode()) {
            return this.mMediaView.getTopMarginFromSupplier();
        }
        return super.getTopMargin();
    }

    public void handleDocumentScanSuccess() {
        super.handleDocumentScanSuccess();
        Optional.ofNullable(this.mModel.getMediaItem()).ifPresent(new w(28));
    }

    public boolean isButtonViewable() {
        if (!super.isButtonViewable() || this.mIsVideoPlayMode) {
            return false;
        }
        return true;
    }

    public boolean isCacheAvailable() {
        return false;
    }

    public boolean isExtractable() {
        return !this.mIsVideoPlayMode;
    }

    public boolean isMotionPhotoVideoMode() {
        if (this.mMediaView == null || !this.mModel.getMotionPlayViewer().isVideo) {
            return false;
        }
        return true;
    }

    public void onViewAttached() {
        super.onViewAttached();
        setButtonVisibility(false, false);
    }

    public boolean onViewLongPress(float f, float f5) {
        if (!isMotionPhotoVideoMode() || (!this.mTextExtractionViewVisible && !this.mModel.isObjectCaptureState())) {
            return super.onViewLongPress(f, f5);
        }
        return true;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mIsVideoPlayMode = false;
    }

    public void refreshCaptureView() {
        if (!this.mTextExtractionViewVisible) {
            this.mIsDirty = true;
        } else if (isMotionPhotoVideoMode()) {
            ThreadUtil.postOnUiThread(new l(this, 3));
        } else {
            PhotoView photoView = this.mPhotoView;
            if (photoView != null) {
                photoView.post(new c(photoView, 3));
            } else {
                this.mIsDirty = true;
            }
        }
    }

    public void requestDetect() {
        this.mIsDirty = false;
        if (this.mDetectRunnable == null) {
            this.mDetectRunnable = new l(this, 0);
        }
        if (!DeepSkyHelper.hasCallbacks(this.mDetectRunnable)) {
            DeepSkyHelper.postDelayed(this.mDetectRunnable, 500);
        }
    }

    public boolean supportDocumentScan() {
        if (!this.mModel.getMotionPlayViewer().isPhoto || !super.supportDocumentScan()) {
            return false;
        }
        return true;
    }

    public void clearPendedBitmapLoadAndDetect() {
    }
}
