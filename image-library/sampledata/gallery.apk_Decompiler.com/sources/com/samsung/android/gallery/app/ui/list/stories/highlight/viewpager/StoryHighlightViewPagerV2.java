package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animations.CollectAnimation;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import v7.w;
import x6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightViewPagerV2 extends StoryHighlightViewPager {
    private EndProgressHandler mEndProgressHandler;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class EndProgressHandler {
        /* access modifiers changed from: private */
        public CollectAnimation mAnimator;
        /* access modifiers changed from: private */
        public final Runnable mCancelRunnable;
        /* access modifiers changed from: private */
        public boolean mEndAlphaAnimEnd;
        /* access modifiers changed from: private */
        public boolean mEndAlphaAnimStart;
        private AlphaAnimator mEndEffectAnimator;
        /* access modifiers changed from: private */
        public final Runnable mEndRunnable;
        private final MediaItem mLastItem;
        private Float mLastTouchX;
        private float mMoveDelta;
        private final int mPos;
        /* access modifiers changed from: private */
        public View mView;
        private ViewConfiguration mViewConfiguration;

        public /* synthetic */ EndProgressHandler(StoryHighlightViewPagerV2 storyHighlightViewPagerV2, View view, int i2, MediaItem mediaItem, e eVar, e eVar2) {
            this(view, i2, mediaItem, (Runnable) eVar, (Runnable) eVar2);
        }

        /* access modifiers changed from: private */
        public void cancel() {
            CollectAnimation collectAnimation = this.mAnimator;
            if (collectAnimation != null) {
                collectAnimation.cancel();
            } else {
                this.mCancelRunnable.run();
            }
        }

        /* access modifiers changed from: private */
        public void cancelEndAlphaIfStarted() {
            AlphaAnimator alphaAnimator;
            if (this.mEndAlphaAnimStart && (alphaAnimator = this.mEndEffectAnimator) != null) {
                alphaAnimator.cancel();
                restore();
            }
        }

        /* access modifiers changed from: private */
        public EndProgressHandler create(boolean z, boolean z3) {
            final CollectAnimation collectAnimation = new CollectAnimation();
            if (z3) {
                collectAnimation.add(createFakeProgressAnimator(this.mLastItem));
            }
            if (z) {
                collectAnimation.add(createEndEffectAnimator());
            }
            collectAnimation.playSequentially();
            collectAnimation.setListener(new SimpleAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    EndProgressHandler.this.mCancelRunnable.run();
                    Log.d(StoryHighlightViewPagerV2.this.TAG, "onAnimationCancel");
                }

                public void onAnimationEnd(Animator animator) {
                    EndProgressHandler.this.mEndRunnable.run();
                    collectAnimation.setListener((Animator.AnimatorListener) null);
                    EndProgressHandler.this.mAnimator = null;
                    Log.d(StoryHighlightViewPagerV2.this.TAG, "onAnimationEnd");
                }
            });
            this.mAnimator = collectAnimation;
            return this;
        }

        private Animator createEndEffectAnimator() {
            View view = this.mView;
            ValueAnimator ignoreDurationScaleIfDisabled = SimpleAnimator.ignoreDurationScaleIfDisabled(new AlphaAnimator(view, view.getAlpha(), 0.0f));
            ignoreDurationScaleIfDisabled.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_STORY_END_ANIMATION));
            ignoreDurationScaleIfDisabled.setDuration(660);
            ignoreDurationScaleIfDisabled.setStartDelay(340);
            ignoreDurationScaleIfDisabled.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    EndProgressHandler.this.mEndAlphaAnimEnd = true;
                }

                public void onAnimationStart(Animator animator) {
                    EndProgressHandler.this.mEndAlphaAnimStart = true;
                    Optional.ofNullable(StoryHighlightViewPagerV2.this.mCallback).ifPresent(new w(9));
                    Log.d(StoryHighlightViewPagerV2.this.TAG, "start endView alpha", Integer.valueOf(EndProgressHandler.this.mView.hashCode()));
                }
            });
            this.mEndEffectAnimator = (AlphaAnimator) ignoreDurationScaleIfDisabled;
            return ignoreDurationScaleIfDisabled;
        }

        private Animator createFakeProgressAnimator(MediaItem mediaItem) {
            ValueAnimator ignoreDurationScaleIfDisabled = SimpleAnimator.ignoreDurationScaleIfDisabled(ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
            ignoreDurationScaleIfDisabled.setDuration((long) StoryHighlightViewPagerV2.this.mDurationMeasure.getDuration(mediaItem));
            ignoreDurationScaleIfDisabled.setInterpolator(new LinearInterpolator());
            ignoreDurationScaleIfDisabled.addUpdateListener(new d(this));
            return ignoreDurationScaleIfDisabled;
        }

        private ViewConfiguration getViewConfiguration() {
            if (this.mViewConfiguration == null) {
                this.mViewConfiguration = ViewConfiguration.get(AppResources.getAppContext());
            }
            return this.mViewConfiguration;
        }

        /* access modifiers changed from: private */
        public boolean isCompleted() {
            return this.mEndAlphaAnimEnd;
        }

        /* access modifiers changed from: private */
        public boolean isPaused() {
            CollectAnimation collectAnimation = this.mAnimator;
            if (collectAnimation == null || !collectAnimation.isPaused()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public boolean isRunning() {
            CollectAnimation collectAnimation = this.mAnimator;
            if (collectAnimation == null || !collectAnimation.isRunning()) {
                return false;
            }
            return true;
        }

        private boolean isSameView(View view) {
            if (this.mView == view) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$createFakeProgressAnimator$0(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            StoryHighlightViewPagerV2.super.onPageScrolled(this.mPos, animatedFraction, (int) (((float) this.mView.getWidth()) * animatedFraction));
        }

        /* access modifiers changed from: private */
        public void pause() {
            CollectAnimation collectAnimation = this.mAnimator;
            if (collectAnimation != null && !this.mEndAlphaAnimStart) {
                collectAnimation.pause();
            }
        }

        /* access modifiers changed from: private */
        public void restore() {
            ViewUtils.setAlpha(this.mView, 1.0f);
        }

        /* access modifiers changed from: private */
        public void resume() {
            CollectAnimation collectAnimation = this.mAnimator;
            if (collectAnimation != null && collectAnimation.isPaused()) {
                updateAnimator(StoryHighlightViewPagerV2.this.mViewPager);
                this.mAnimator.resume();
            }
        }

        /* access modifiers changed from: private */
        public void setOnTouchMove(float f, float f5) {
            Float f8 = this.mLastTouchX;
            if (f8 != null) {
                this.mMoveDelta = (f - f8.floatValue()) + this.mMoveDelta;
            }
            this.mLastTouchX = Float.valueOf(f);
        }

        /* access modifiers changed from: private */
        public void setOnTouchUp() {
            int i2;
            float f = this.mMoveDelta;
            if (Features.isEnabled(Features.IS_RTL)) {
                i2 = -1;
            } else {
                i2 = 1;
            }
            if (f * ((float) i2) > ((float) getViewConfiguration().getScaledPagingTouchSlop())) {
                cancel();
            }
            this.mLastTouchX = null;
            this.mMoveDelta = 0.0f;
        }

        /* access modifiers changed from: private */
        public void start() {
            CollectAnimation collectAnimation = this.mAnimator;
            if (collectAnimation != null) {
                collectAnimation.start();
            }
        }

        /* access modifiers changed from: private */
        public void updateAnimator(ViewPager2 viewPager2) {
            View transformParentLayout;
            ViewPagerHolder viewPagerHolder = (ViewPagerHolder) StoryHighlightViewPagerV2.this.getViewHolder(viewPager2.getCurrentItem());
            if (viewPagerHolder != null && (transformParentLayout = viewPagerHolder.getTransformParentLayout()) != null && !isSameView(transformParentLayout)) {
                Log.d(StoryHighlightViewPagerV2.this.TAG, "update endView for endAnimation", Integer.valueOf(transformParentLayout.hashCode()));
                this.mView = transformParentLayout;
                AlphaAnimator alphaAnimator = this.mEndEffectAnimator;
                if (alphaAnimator != null) {
                    alphaAnimator.updateView(transformParentLayout);
                }
            }
        }

        public int getPosition() {
            return this.mPos;
        }

        private EndProgressHandler(View view, int i2, MediaItem mediaItem, Runnable runnable, Runnable runnable2) {
            this.mView = view;
            this.mPos = i2;
            this.mLastItem = mediaItem;
            this.mEndRunnable = runnable;
            this.mCancelRunnable = runnable2;
        }
    }

    public StoryHighlightViewPagerV2(ViewGroup viewGroup, IDuration iDuration) {
        super(viewGroup, iDuration);
    }

    private boolean isEndProgressHandlerRunnable() {
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler == null || !endProgressHandler.isRunning()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEndAnimatorView$0() {
        if (this.mEndProgressHandler != null && this.mViewPager != null && getAdapter().isLast(this.mViewPager.getCurrentItem())) {
            this.mEndProgressHandler.updateAnimator(this.mViewPager);
        }
    }

    /* access modifiers changed from: private */
    public void onEndProgressCancel() {
        Log.d(this.TAG, "onEndAnimatorCancel");
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler != null) {
            endProgressHandler.restore();
            this.mEndProgressHandler = null;
        }
    }

    /* access modifiers changed from: private */
    public void onEndProgressEnd() {
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler != null && endProgressHandler.isCompleted()) {
            onSlideShowDone();
        }
    }

    private void startEndEffect(boolean z) {
        ViewPagerHolder viewPagerHolder = (ViewPagerHolder) getViewHolder(this.mViewPager.getCurrentItem());
        if (viewPagerHolder != null) {
            EndProgressHandler j2 = new EndProgressHandler(this, viewPagerHolder.getTransformParentLayout(), this.mViewPager.getCurrentItem(), viewPagerHolder.getMediaItem(), new e(this, 1), new e(this, 2)).create(this.mSupportEndEffect, z);
            this.mEndProgressHandler = j2;
            j2.start();
        }
    }

    private void updateEndAnimatorView(boolean z) {
        long j2;
        e eVar = new e(this, 0);
        if (z) {
            j2 = 50;
        } else {
            j2 = 0;
        }
        ThreadUtil.postOnUiThreadDelayed(eVar, j2);
    }

    public void escapeEndPositionInternal(int i2, boolean z) {
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler == null) {
            return;
        }
        if (i2 == -1) {
            endProgressHandler.cancelEndAlphaIfStarted();
            Log.d(this.TAG, "restoreEndAnimateProperty cancelEndAlpha");
        } else if (endProgressHandler.getPosition() != i2) {
            this.mEndProgressHandler.cancel();
            Log.d(this.TAG, "restoreEndAnimateProperty cancel");
        } else if (z) {
            Log.d(this.TAG, "restoreEndAnimateProperty replay");
            this.mEndProgressHandler.cancel();
            if (!this.mKeepPause) {
                onEndPosition();
            }
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        updateEndAnimatorView(true);
    }

    public void isTouchUpOrCancel() {
        if (this.mEndProgressHandler != null && this.mAdapter.getItemCount() == 1) {
            this.mEndProgressHandler.setOnTouchUp();
        }
        super.isTouchUpOrCancel();
    }

    public void onDataUpdated() {
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler == null) {
            return;
        }
        if (endProgressHandler.getPosition() != this.mAdapter.getItemCount() - 1) {
            this.mEndProgressHandler.cancel();
            Log.d(this.TAG, "updateData cancel");
            return;
        }
        updateEndAnimatorView(true);
        Log.d(this.TAG, "updateData animView");
    }

    public void onEndPosition() {
        boolean z;
        String str = this.TAG;
        if (this.mEndProgressHandler != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(str, "onEndPosition", Boolean.valueOf(z));
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler != null) {
            endProgressHandler.updateAnimator(this.mViewPager);
        } else {
            startEndEffect(true);
        }
    }

    public void onEscapeEndPosition(int i2, boolean z) {
        escapeEndPositionInternal(i2, z);
    }

    public boolean onMove(float f, float f5) {
        if (this.mEndProgressHandler != null && this.mAdapter.getItemCount() == 1) {
            this.mEndProgressHandler.setOnTouchMove(f, f5);
        }
        return super.onMove(f, f5);
    }

    public void onPageScrolled(int i2, float f, int i7) {
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler == null) {
            super.onPageScrolled(i2, f, i7);
        } else if (i2 != endProgressHandler.getPosition()) {
            escapeEndPositionInternal(i2, false);
        }
    }

    public void onPageSelected(int i2) {
        escapeEndPositionInternal(i2, false);
        super.onPageSelected(i2);
    }

    public void onStoryChanged() {
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler != null) {
            ThreadUtil.postOnUiThreadDelayed(new c(endProgressHandler), 100);
            this.mEndProgressHandler = null;
        }
    }

    public void pause() {
        super.pause();
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler != null) {
            endProgressHandler.pause();
        }
    }

    public void scrollToPosition(int i2, boolean z, boolean z3) {
        super.scrollToPosition(i2, z, z3);
        if (i2 == this.mAdapter.getItemCount() && isEndProgressHandlerRunnable()) {
            startEndEffect(false);
        }
    }

    public void startInternal(int i2) {
        EndProgressHandler endProgressHandler = this.mEndProgressHandler;
        if (endProgressHandler == null) {
            super.startInternal(i2);
        } else if (endProgressHandler.isPaused()) {
            this.mEndProgressHandler.resume();
        } else if (this.mEndProgressHandler.isCompleted()) {
            startEndEffect(false);
        }
    }
}
