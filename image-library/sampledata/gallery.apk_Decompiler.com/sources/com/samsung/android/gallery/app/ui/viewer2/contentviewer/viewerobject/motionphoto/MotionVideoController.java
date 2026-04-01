package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto;

import D7.g;
import E7.b;
import E9.a;
import F7.e;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestDownloadImageCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.CloudDownloader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.PlayButtonTextView;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionVideoController extends VideoController implements FragmentLifeCycle {
    private ValueAnimator mImageToVideoAnim;
    private View mShotModeButton;
    private final Animator.AnimatorListener mToImageAnimatorListener = new VideoControllerViewAnimatorListener() {
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            MotionVideoController.this.updateVisibility();
        }
    };
    private ValueAnimator mVideoToImageAnim;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class VideoControllerViewAnimatorListener implements Animator.AnimatorListener {
        public /* synthetic */ VideoControllerViewAnimatorListener(MotionVideoController motionVideoController, int i2) {
            this();
        }

        public void onAnimationEnd(Animator animator) {
            if (MotionVideoController.this.mVideoControllerView != null) {
                ViewGroup.LayoutParams layoutParams = MotionVideoController.this.mVideoControllerView.getLayoutParams();
                layoutParams.width = -2;
                MotionVideoController.this.mVideoControllerView.setLayoutParams(layoutParams);
            }
        }

        private VideoControllerViewAnimatorListener() {
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }
    }

    private void changeControllerWithAnim(boolean z) {
        boolean z3;
        if (!skipControllerVisibility(z)) {
            View view = this.mVideoControllerView;
            if (view == null || view.getVisibility() != 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3 != z) {
                if (z) {
                    updateVisibility();
                    ValueAnimator animator = getAnimator(false);
                    if (animator != null) {
                        animator.start();
                    }
                } else {
                    ValueAnimator animator2 = getAnimator(true);
                    if (animator2 != null) {
                        animator2.start();
                    } else {
                        updateVisibility();
                    }
                }
                updateTouchArea();
                return;
            }
            Log.w((CharSequence) this.TAG, "updateControllerVisibility skip", Boolean.valueOf(z));
        }
    }

    private ValueAnimator createAnimator(View view, int i2, int i7) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i2, i7});
        ofInt.setDuration(300);
        ofInt.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60));
        ofInt.addUpdateListener(new b(view, 1));
        ofInt.setTarget(view);
        return ofInt;
    }

    /* access modifiers changed from: private */
    public void downloadCompleted(Object obj) {
        if (this.mModel.isViewConfirmed()) {
            this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, MotionPlayViewer.VIDEO_BUTTON, Integer.valueOf(this.mModel.getPosition()));
        } else {
            Log.v(this.TAG, "downloadCompleted skip");
        }
    }

    private ValueAnimator getAnimator(boolean z) {
        View view;
        ValueAnimator valueAnimator;
        ValueAnimator valueAnimator2;
        if (z && (valueAnimator2 = this.mVideoToImageAnim) != null) {
            return valueAnimator2;
        }
        if (!z && (valueAnimator = this.mImageToVideoAnim) != null) {
            return valueAnimator;
        }
        if (this.mVideoControllerView == null || (view = this.mShotModeButton) == null) {
            return null;
        }
        int measuredWidth = ViewUtils.getMeasuredWidth(view);
        int dimensionPixelOffset = this.mVideoControllerView.getResources().getDimensionPixelOffset(R.dimen.play_button_extra_margin) + ViewUtils.getMeasuredWidth(this.mVideoControllerView);
        if (measuredWidth == 0 || dimensionPixelOffset == 0) {
            return null;
        }
        if (z) {
            ValueAnimator createAnimator = createAnimator(this.mVideoControllerView, dimensionPixelOffset, measuredWidth);
            this.mVideoToImageAnim = createAnimator;
            createAnimator.addListener(this.mToImageAnimatorListener);
            return this.mVideoToImageAnim;
        }
        ValueAnimator createAnimator2 = createAnimator(this.mVideoControllerView, measuredWidth, dimensionPixelOffset);
        this.mImageToVideoAnim = createAnimator2;
        createAnimator2.addListener(new VideoControllerViewAnimatorListener(this, 0));
        return this.mImageToVideoAnim;
    }

    private boolean isNecessarySharedDownloadMotionPhoto(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isSharing() || !mediaItem.isMotionPhoto() || MediaItemMde.hasDownloadMotionPhotoPath(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        if (this.mShotModeButton == null) {
            this.mShotModeButton = objArr[0].findViewById(R.id.shot_mode_button);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        MotionPlayViewer motionPlayViewer = objArr[1];
        if (MotionPlayViewer.isViewModeChanged(objArr[0], motionPlayViewer)) {
            updateVisibility();
        } else {
            changeControllerWithAnim(motionPlayViewer.isVideo);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createAnimator$3(View view, ValueAnimator valueAnimator) {
        view.getLayoutParams().width = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        view.requestLayout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBind$2(View view) {
        onShotModeClicked();
    }

    /* access modifiers changed from: private */
    public void onShotModeClicked() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_MOTION_PHOTO.toString());
        }
        if (isNecessarySharedDownloadMotionPhoto(mediaItem)) {
            new RequestDownloadImageCmd().execute(this.mModel.getContainerModel().getEventContext(), mediaItem, Boolean.TRUE);
        } else if (!supportCloudOnly(mediaItem) && CloudDownloader.acceptable(mediaItem)) {
            new CloudDownloader(this.mModel, this.mThread).exec(new a(2, this));
        } else if (mediaItem == null || mediaItem.isBroken()) {
            Log.v(this.TAG, "onShotModeClicked broken");
        } else if (this.mModel.getMotionPlayViewer() != MotionPlayViewer.PHOTO_PREVIEW) {
            updateTextView(mediaItem.getFileDuration(), 0);
            this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, MotionPlayViewer.VIDEO_BUTTON, Integer.valueOf(this.mModel.getPosition()));
        }
    }

    private boolean skipControllerVisibility(boolean z) {
        if (this.mModel.isInstantSlowMoPlayEnabled() && !z) {
            Log.w(this.TAG, "skipControllerVisibility : still long pressed instant slow-mo");
            return true;
        } else if (z || !MediaItemUtil.isMotionPhotoAutoPlayViewMode(this.mModel.getMediaItem())) {
            return false;
        } else {
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "skipControllerVisibility : viewMode = " + MediaItemUtil.getMotionPhotoViewMode(this.mModel.getMediaItem()));
            return true;
        }
    }

    private boolean supportCloudOnly(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isCloudOnly() || !MediaItemUtil.supportCloudPreviewPlay(mediaItem)) {
            return false;
        }
        return true;
    }

    private void updateShotModeEnabled() {
        boolean z;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            View view = this.mShotModeButton;
            if (!TrashData.isTrash(mediaItem) || !mediaItem.isCloudOnly()) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.setViewEnabled(view, z);
        }
    }

    /* access modifiers changed from: private */
    public void updateVisibility() {
        boolean z;
        int i2;
        MotionPlayViewer motionPlayViewer = this.mModel.getMotionPlayViewer();
        boolean z3 = false;
        if (!motionPlayViewer.isPhoto || motionPlayViewer == MotionPlayViewer.PHOTO_MOTION_OFF) {
            z = false;
        } else {
            z = true;
        }
        boolean z7 = motionPlayViewer.isVideo;
        ViewUtils.setVisibleOrGone(this.mShotModeButton, z);
        ViewUtils.setVisibleOrGone(this.mVideoControllerView, z7);
        updateShotModeEnabled();
        if (z7) {
            if (motionPlayViewer == MotionPlayViewer.BOOMERANG || motionPlayViewer == MotionPlayViewer.SLOW_MO) {
                z3 = true;
            }
            ViewUtils.setVisibleOrGone(this.mPlayVideoTextView, z3);
            ViewUtils.setVisibleOrGone(this.mTimeTextLayout, !z3);
            if (z3) {
                TextView textView = this.mPlayVideoTextView;
                if (this.mIsPlaying) {
                    i2 = R.string.pause_video;
                } else {
                    i2 = R.string.play_video;
                }
                ViewUtils.setText(textView, i2);
            }
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.DECOR_LAYOUT, new e(this, 0));
        this.mActionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new e(this, 1));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3050) {
            return false;
        }
        this.mThread.runOnUiThread(new g(8, this), 300);
        return true;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        if (ViewUtils.isViewStub(this.mShotModeButton)) {
            this.mShotModeButton = ((ViewStub) this.mShotModeButton).inflate();
        }
        this.mShotModeButton.setOnClickListener(new Ab.a(20, this));
        ((PlayButtonTextView) this.mShotModeButton).setText((int) R.string.view_motion_photo);
        ((PlayButtonTextView) this.mShotModeButton).setButtonContentDescription(R.string.view_motion_photo);
        ((PlayButtonTextView) this.mShotModeButton).resizeFontLarge((float) this.mModel.getContext().getResources().getDimensionPixelSize(R.dimen.play_button_view_font_size));
        ViewUtils.setVisibility(this.mDurationTextView, 8);
        ViewUtils.setVisibility(this.mSlashView, 8);
        updateTouchArea();
    }

    public void onVideoStarted(Object... objArr) {
        super.onVideoStarted(objArr);
    }

    public void onViewAttached() {
        super.onViewAttached();
        updateVisibility();
    }

    public void onViewDetached() {
        super.onViewDetached();
        ValueAnimator valueAnimator = this.mVideoToImageAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mVideoToImageAnim.cancel();
        }
        ValueAnimator valueAnimator2 = this.mImageToVideoAnim;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.mImageToVideoAnim.cancel();
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mImageToVideoAnim = null;
        this.mVideoToImageAnim = null;
    }

    public void setPlayPauseButton(boolean z) {
        int i2;
        super.lambda$onVideoStarted$1(z);
        if (ViewUtils.isVisible(this.mVideoControllerView)) {
            MotionPlayViewer motionPlayViewer = this.mModel.getMotionPlayViewer();
            if (motionPlayViewer == MotionPlayViewer.BOOMERANG || motionPlayViewer == MotionPlayViewer.SLOW_MO) {
                TextView textView = this.mPlayVideoTextView;
                if (this.mIsPlaying) {
                    i2 = R.string.pause_video;
                } else {
                    i2 = R.string.play_video;
                }
                ViewUtils.setText(textView, i2);
            }
        }
    }

    public void updateTouchArea() {
        super.updateTouchArea();
        ViewUtils.setTouchAreaComposite(this.mShotModeButton, R.dimen.decor_button_touch_area);
    }
}
