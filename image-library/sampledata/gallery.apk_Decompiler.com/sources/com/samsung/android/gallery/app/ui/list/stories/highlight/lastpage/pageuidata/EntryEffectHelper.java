package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import r6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EntryEffectHelper {
    private AnimatorSet mCollageItemEffect;
    private boolean mEnabled = true;
    private AnimatorSet mEntryEndEffect;
    private AnimatorSet mEntryEndScrollEffect;
    private final AtomicInteger mState = new AtomicInteger(0);

    private void cancelAnimations() {
        Optional.ofNullable(this.mCollageItemEffect).ifPresent(new e(6));
        Optional.ofNullable(this.mEntryEndEffect).ifPresent(new e(6));
        Optional.ofNullable(this.mEntryEndScrollEffect).ifPresent(new e(6));
        this.mCollageItemEffect = null;
        this.mEntryEndEffect = null;
        this.mEntryEndScrollEffect = null;
    }

    /* access modifiers changed from: private */
    public boolean isStarted() {
        if (this.mState.get() == 1) {
            return true;
        }
        return false;
    }

    private boolean notSupport(View view) {
        if (isDisabled() || ResourceCompat.isLandscape(view)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void startEndEffect() {
        AnimatorSet animatorSet = this.mEntryEndEffect;
        if (animatorSet != null && !animatorSet.getChildAnimations().isEmpty()) {
            this.mEntryEndEffect.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    Log.d("EntryEffectHelper", "startEndScrollEffect done");
                    if (EntryEffectHelper.this.isStarted()) {
                        EntryEffectHelper.this.startEndScrollEffect();
                    }
                }
            });
            this.mEntryEndEffect.start();
        }
    }

    /* access modifiers changed from: private */
    public void startEndScrollEffect() {
        AnimatorSet animatorSet = this.mEntryEndScrollEffect;
        if (animatorSet != null && !animatorSet.getChildAnimations().isEmpty()) {
            this.mEntryEndScrollEffect.start();
        }
        this.mState.set(-1);
    }

    private void startEntryEffect(ValueAnimator valueAnimator) {
        this.mState.set(1);
        AnimatorSet animatorSet = this.mCollageItemEffect;
        if (animatorSet != null && !animatorSet.getChildAnimations().isEmpty()) {
            valueAnimator.addListener(new SimpleAnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    EntryEffectHelper.this.startEndEffect();
                }
            });
            this.mCollageItemEffect.start();
        }
    }

    public void addCollageItemEffect(final View view, int i2, int i7) {
        if (!notSupport(view)) {
            if (this.mCollageItemEffect == null) {
                this.mCollageItemEffect = new AnimatorSet();
            }
            ViewUtils.setAlpha(view, 0.0f);
            PropertyAnimator delay = new AlphaAnimator(view, 0.0f, 1.0f).setDuration(400).setDelay((long) (i2 * 50));
            delay.addListener(new SimpleAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    ViewUtils.setAlpha(view, 1.0f);
                }
            });
            this.mCollageItemEffect.play(delay);
            if (i2 == i7 - 1) {
                startEntryEffect(delay);
                Log.d("EntryEffectHelper", "startEntryEffect", Integer.valueOf(i2), Integer.valueOf(i7));
            }
        }
    }

    public void addEntryEndEffect(final View view) {
        if (!notSupport(view)) {
            if (this.mEntryEndEffect == null) {
                this.mEntryEndEffect = new AnimatorSet();
            }
            ViewUtils.setAlpha(view, 0.0f);
            PropertyAnimator delay = new AlphaAnimator(view, 0.0f, 1.0f).setDuration(400).setDelay(50);
            delay.addListener(new SimpleAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    ViewUtils.setAlpha(view, 1.0f);
                }
            });
            this.mEntryEndEffect.play(delay);
        }
    }

    public void addEntryEndScrollEffect(final View view, float f) {
        if (!notSupport(view)) {
            if (this.mEntryEndScrollEffect == null) {
                this.mEntryEndScrollEffect = new AnimatorSet();
            }
            AnimatorSet animatorSet = this.mEntryEndScrollEffect;
            float f5 = -f;
            PropertyAnimator duration = new TranslationAnimator(view).translateX(0.0f, f5).setDelay(50).setDuration(400);
            PathInterpolator.Type type = PathInterpolator.Type.TYPE_SINE_IN_OUT_60;
            animatorSet.playSequentially(new Animator[]{duration.setInterpolator(PathInterpolator.create(type)), new TranslationAnimator(view).translateX(f5, 0.0f).setDelay(200).setDuration(400).setInterpolator(PathInterpolator.create(type))});
            this.mEntryEndScrollEffect.addListener(new SimpleAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    ViewUtils.setTranslationX(view, 0.0f);
                }
            });
        }
    }

    public boolean effectRequired() {
        if (this.mState.get() == 0) {
            return true;
        }
        return false;
    }

    public boolean isDisabled() {
        if (this.mState.get() == -1) {
            return true;
        }
        return false;
    }

    public void reset() {
        cancelAnimations();
        this.mState.set(0);
    }

    public void setComplete() {
        cancelAnimations();
        this.mState.set(-1);
    }

    public void setEnable(boolean z) {
        this.mEnabled = z;
        if (z) {
            reset();
            return;
        }
        cancelAnimations();
        this.mState.set(-1);
    }
}
