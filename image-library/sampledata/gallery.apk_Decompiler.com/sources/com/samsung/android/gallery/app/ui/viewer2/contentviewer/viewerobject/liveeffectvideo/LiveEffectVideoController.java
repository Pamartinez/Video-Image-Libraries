package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo;

import D7.g;
import E7.a;
import E7.b;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ViewLiveEffectCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.PlayButtonTextView;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveEffectVideoController extends VideoController {
    private ValueAnimator mControllerToShotModeAnim;
    private View mShotModeButton;
    private ValueAnimator mShotModeToControllerAnim;
    private final Animator.AnimatorListener mToShotModeAnimatorListener = new VideoControllerViewAnimatorListener() {
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            LiveEffectVideoController.this.updateVisibility();
        }
    };
    private boolean mUseShotModeButton = true;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class VideoControllerViewAnimatorListener implements Animator.AnimatorListener {
        public /* synthetic */ VideoControllerViewAnimatorListener(LiveEffectVideoController liveEffectVideoController, int i2) {
            this();
        }

        public void onAnimationEnd(Animator animator) {
            if (LiveEffectVideoController.this.mVideoControllerView != null) {
                ViewGroup.LayoutParams layoutParams = LiveEffectVideoController.this.mVideoControllerView.getLayoutParams();
                layoutParams.width = -2;
                LiveEffectVideoController.this.mVideoControllerView.setLayoutParams(layoutParams);
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

    private ValueAnimator createAnimator(View view, int i2, int i7) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i2, i7});
        ofInt.setDuration(300);
        ofInt.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60));
        ofInt.addUpdateListener(new b(view, 0));
        ofInt.setTarget(view);
        return ofInt;
    }

    private ValueAnimator getAnimator(boolean z) {
        View view;
        ValueAnimator valueAnimator;
        ValueAnimator valueAnimator2;
        if (z && (valueAnimator2 = this.mControllerToShotModeAnim) != null) {
            return valueAnimator2;
        }
        if (!z && (valueAnimator = this.mShotModeToControllerAnim) != null) {
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
            this.mControllerToShotModeAnim = createAnimator;
            createAnimator.addListener(this.mToShotModeAnimatorListener);
            return this.mControllerToShotModeAnim;
        }
        ValueAnimator createAnimator2 = createAnimator(this.mVideoControllerView, measuredWidth, dimensionPixelOffset);
        this.mShotModeToControllerAnim = createAnimator2;
        createAnimator2.addListener(new VideoControllerViewAnimatorListener(this, 0));
        return this.mShotModeToControllerAnim;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        if (this.mShotModeButton == null) {
            this.mShotModeButton = objArr[0].findViewById(R.id.shot_mode_button);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        this.mUseShotModeButton = !booleanValue;
        changeControllerWithAnim(booleanValue);
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
    public void updateVisibility() {
        ViewUtils.setVisibleOrGone(this.mShotModeButton, this.mUseShotModeButton);
        ViewUtils.setVisibleOrGone(this.mVideoControllerView, !this.mUseShotModeButton);
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.DECOR_LAYOUT, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.FILM_STRIP_STATE_CHANGED, new a(this, 1));
    }

    public int getShotModeStringRes() {
        return R.string.view_live_effect;
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3050) {
            return false;
        }
        this.mThread.runOnUiThread(new g(2, this), 300);
        return true;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        if (ViewUtils.isViewStub(this.mShotModeButton)) {
            this.mShotModeButton = ((ViewStub) this.mShotModeButton).inflate();
        }
        this.mShotModeButton.setOnClickListener(new Ab.a(19, this));
        ((PlayButtonTextView) this.mShotModeButton).setText(getShotModeStringRes());
        ((PlayButtonTextView) this.mShotModeButton).resizeFontLarge((float) this.mModel.getContext().getResources().getDimensionPixelSize(R.dimen.play_button_view_font_size));
        ViewUtils.setVisibility(this.mDurationTextView, 8);
        ViewUtils.setVisibility(this.mSlashView, 8);
        updateTouchArea();
    }

    public void onShotModeClicked() {
        ViewLiveEffectCmd viewLiveEffectCmd = new ViewLiveEffectCmd();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        MediaItem mediaItem = this.mModel.getMediaItem();
        Boolean bool = Boolean.FALSE;
        viewLiveEffectCmd.execute(eventContext, mediaItem, bool, bool);
    }

    public void onViewAttached() {
        super.onViewAttached();
        this.mUseShotModeButton = !this.mModel.getStateHelper().isFilmExpanded(this.mModel.getPosition());
        updateVisibility();
    }

    public void onViewDetached() {
        super.onViewDetached();
        ValueAnimator valueAnimator = this.mControllerToShotModeAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mControllerToShotModeAnim.cancel();
        }
        ValueAnimator valueAnimator2 = this.mShotModeToControllerAnim;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.mShotModeToControllerAnim.cancel();
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mShotModeToControllerAnim = null;
        this.mControllerToShotModeAnim = null;
    }

    public void updateTouchArea() {
        super.updateTouchArea();
        ViewUtils.setTouchAreaComposite(this.mShotModeButton, R.dimen.decor_button_touch_area);
    }
}
