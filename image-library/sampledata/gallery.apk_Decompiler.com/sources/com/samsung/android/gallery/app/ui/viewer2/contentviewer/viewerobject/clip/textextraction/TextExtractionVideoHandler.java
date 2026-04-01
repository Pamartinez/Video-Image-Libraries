package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.Optional;
import q8.a;
import v7.w;
import y7.m;
import y7.n;
import y7.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExtractionVideoHandler extends TextExtractionHandler {
    private boolean mIsVideoPlayMode;
    private IMediaPlayerView mMediaView;

    private void detect() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        long j2 = 0;
        if (isValid(mediaItem)) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("detect req");
            if (mediaItem != null) {
                j2 = mediaItem.getFileId();
            }
            sb2.append(Logger.v(Long.valueOf(j2), this.mTextHelper));
            Log.d(stringCompat, sb2.toString());
            TextExtractionTask textExtractionTask = getTextExtractionTask(TextExtractionTask.Mode.DETECT, new o(this, 0));
            this.mDetectTask = textExtractionTask;
            DeepSkyHelper.post(textExtractionTask);
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        StringBuilder sb3 = new StringBuilder("detect req skip");
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        }
        sb3.append(Logger.v(Long.valueOf(j2)));
        Log.d(stringCompat2, sb3.toString());
    }

    private boolean isVideoPause() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView == null || !iMediaPlayerView.isPaused()) {
            return false;
        }
        return true;
    }

    private boolean isVideoSeeking() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView == null || !iMediaPlayerView.isVisualSeeking()) {
            return false;
        }
        return true;
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
    public /* synthetic */ void lambda$refreshLayout$2() {
        this.mMediaView.refreshCaptureView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetAndDetect$3() {
        resetTextHelper();
        if (!this.mPaused && !this.mIsVideoPlayMode && !isVideoSeeking()) {
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
    public /* synthetic */ void lambda$resetToIdle$4() {
        disableTextExtractionView(new Object[0]);
        setButtonVisibility(false, false);
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
            resetAndDetect();
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
        if (this.mModel.isFragmentResumed() && isVideoPause()) {
            resetAndDetect();
        }
    }

    private void resetAndDetect() {
        this.mIsDirty = false;
        if (this.mDetectRunnable == null) {
            this.mDetectRunnable = new n(this, 3);
        }
        if (!DeepSkyHelper.hasCallbacks(this.mDetectRunnable)) {
            DeepSkyHelper.postDelayed(this.mDetectRunnable, 500);
        }
    }

    /* access modifiers changed from: private */
    public void resetTextHelper() {
        try {
            TextExtractionHelper textExtractionHelper = this.mTextHelper;
            if (textExtractionHelper != null) {
                textExtractionHelper.clearDetectType(false);
                this.mTextHelper.clearVariables();
                this.mTextHelper.finishTranslate();
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
        this.mThread.runOnUiThread(new n(this, 0));
        DeepSkyHelper.post(new n(this, 1));
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.REQUEST_PLAY_TIME_UPDATED, new m(this, 0));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new m(this, 1));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_FINISH, new m(this, 2));
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new m(this, 3));
        this.mActionInvoker.add(ViewerAction.VIDEO_STOPPED, new m(this, 4));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new m(this, 5));
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_VIEW_VISIBILITY_CHANGED, new m(this, 6));
    }

    public void bindTextExtractionView() {
        TextExtractionView textExtractionView;
        boolean z;
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            boolean z3 = this.mTextExtractionViewVisible;
            if (z3) {
                textExtractionView = this.mTextExtractionView;
            } else {
                textExtractionView = null;
            }
            if (!z3 || !this.mIsDirty) {
                z = false;
            } else {
                z = true;
            }
            iMediaPlayerView.bindCaptureView(textExtractionView, true, z);
        }
    }

    public void extract(boolean z) {
        TextExtractionTask.Mode mode;
        if (this.mPaused || this.mIsVideoPlayMode) {
            clearTextExtractionViewVisibilityChangeFlag();
        } else if (isExtracted()) {
            extractDone();
        } else {
            if (z) {
                mode = TextExtractionTask.Mode.EXTRACT_BY_LONG_PRESS;
            } else {
                mode = TextExtractionTask.Mode.EXTRACT_BY_BUTTON;
            }
            TextExtractionTask textExtractionTask = getTextExtractionTask(mode, new o(this, 1));
            this.mExtractTask = textExtractionTask;
            DeepSkyHelper.post(textExtractionTask);
        }
    }

    public String getAnalyticsLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_TYPE_VIDEO.toString();
    }

    public Bitmap getBitmap(boolean z) {
        return getLowBitmap();
    }

    public int getButtonBottomMargin() {
        int i2;
        if (!Features.isEnabled(Features.SUPPORT_AUDIO_ERASER) || !this.mModel.getContainerModel().isAudioEraserVisible()) {
            i2 = 0;
        } else {
            i2 = this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.play_button_view_bottom_margin) + this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.image_type_icon_height);
        }
        int buttonBottomMargin = super.getButtonBottomMargin();
        return this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.play_button_view_bottom_margin) + this.mTextExtractionButton.getResources().getDimensionPixelOffset(R.dimen.play_button_view_height) + buttonBottomMargin + i2;
    }

    public RectF getDisplayRect() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getDisplayRect();
        }
        return null;
    }

    public Bitmap getLowBitmap() {
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
            this.mBitmapHolder = new WeakReference<>(captureFrameInBackground);
        }
        return captureFrameInBackground;
    }

    public int getTopMargin() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getTopMarginFromSupplier();
        }
        return 0;
    }

    public boolean isButtonViewable() {
        if (!super.isButtonViewable() || this.mIsVideoPlayMode || this.mModel.isSingleTakenShot()) {
            return false;
        }
        return true;
    }

    public boolean isExtractable() {
        return !this.mIsVideoPlayMode;
    }

    public void onPause() {
        super.onPause();
        setButtonVisibility(false, false);
    }

    public boolean onViewLongPress(float f, float f5) {
        if (this.mTextExtractionViewVisible || this.mModel.isObjectCaptureState()) {
            return true;
        }
        return super.onViewLongPress(f, f5);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mIsVideoPlayMode = false;
    }

    public void refreshLayout() {
        updateMargin();
        if (!isTextHelperReady()) {
            return;
        }
        if (!this.mTextExtractionViewVisible) {
            this.mIsDirty = true;
        } else if (this.mMediaView != null) {
            ThreadUtil.postOnUiThread(new n(this, 2));
        }
    }
}
