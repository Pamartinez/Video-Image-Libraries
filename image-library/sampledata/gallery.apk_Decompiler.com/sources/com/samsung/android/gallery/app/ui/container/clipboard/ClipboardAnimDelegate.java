package com.samsung.android.gallery.app.ui.container.clipboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ClipboardAnimDelegate {
    private PropertyAnimator mHideAlphaAnimator;
    /* access modifiers changed from: private */
    public boolean mIsRunning;
    /* access modifiers changed from: private */
    public View mSelectedLayout;
    private PropertyAnimator mShowAlphaAnimator;
    private ImageView mToggle;
    private TranslationAnimator mToggleBarAnimator;
    private View mToggleLayout;

    public ClipboardAnimDelegate(View view, View view2) {
        setTargetView(view, view2);
    }

    private float calcTranslateY(boolean z) {
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen.clipboard_selected_layout_margin_bottom) + getContext().getResources().getDimensionPixelOffset(R.dimen.clipboard_selected_layout_height);
        if (z) {
            return (float) dimensionPixelOffset;
        }
        return (float) (-dimensionPixelOffset);
    }

    private ValueAnimator createRotateAnimator(boolean z) {
        long j2;
        float rotation = this.mToggle.getRotation();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        if (z) {
            j2 = 0;
        } else {
            j2 = 300;
        }
        ofFloat.setDuration(j2);
        ofFloat.addUpdateListener(new a(this, rotation));
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public Context getContext() {
        return this.mSelectedLayout.getContext();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createRotateAnimator$0(float f, ValueAnimator valueAnimator) {
        this.mToggle.setRotation(((valueAnimator.getAnimatedFraction() * 180.0f) + f) % 360.0f);
    }

    private void prepareAnimator() {
        TranslationAnimator translationAnimator = new TranslationAnimator(this.mToggleLayout);
        this.mToggleBarAnimator = translationAnimator;
        translationAnimator.setDuration(500).setInterpolator(new PathInterpolator(0.22f, 0.25f, 0.0f, 1.1f));
        this.mShowAlphaAnimator = new AlphaAnimator(this.mSelectedLayout, 0.0f, 1.0f).setInterpolator(new LinearInterpolator());
        this.mHideAlphaAnimator = new AlphaAnimator(this.mSelectedLayout, 1.0f, 0.0f).setInterpolator(new LinearInterpolator());
    }

    private void setDurationAndDelay(boolean z, boolean z3) {
        int i2;
        long j2;
        TranslationAnimator translationAnimator = this.mToggleBarAnimator;
        int i7 = 0;
        if (z3) {
            i2 = 0;
        } else {
            i2 = 500;
        }
        translationAnimator.setDuration(i2);
        TranslationAnimator translationAnimator2 = this.mToggleBarAnimator;
        long j3 = 0;
        if (z || z3) {
            j2 = 0;
        } else {
            j2 = 100;
        }
        translationAnimator2.setStartDelay(j2);
        if (z) {
            PropertyAnimator propertyAnimator = this.mShowAlphaAnimator;
            if (!z3) {
                i7 = 100;
            }
            propertyAnimator.setDuration(i7);
            PropertyAnimator propertyAnimator2 = this.mShowAlphaAnimator;
            if (!z3) {
                j3 = 100;
            }
            propertyAnimator2.setStartDelay(j3);
            return;
        }
        PropertyAnimator propertyAnimator3 = this.mHideAlphaAnimator;
        if (!z3) {
            i7 = 100;
        }
        propertyAnimator3.setDuration(i7);
    }

    private void start(boolean z, boolean z3, Animator.AnimatorListener animatorListener) {
        PropertyAnimator propertyAnimator;
        AnimatorSet animatorSet = new AnimatorSet();
        this.mToggleBarAnimator.removeAllListeners();
        this.mToggleBarAnimator.setBy(this.mToggleLayout);
        this.mToggleBarAnimator.translateYRelative(calcTranslateY(z));
        this.mToggleBarAnimator.addListener(animatorListener);
        setDurationAndDelay(z, z3);
        TranslationAnimator translationAnimator = this.mToggleBarAnimator;
        if (z) {
            propertyAnimator = this.mShowAlphaAnimator;
        } else {
            propertyAnimator = this.mHideAlphaAnimator;
        }
        animatorSet.playTogether(new Animator[]{translationAnimator, propertyAnimator, createRotateAnimator(z3)});
        animatorSet.start();
    }

    public void doClose(boolean z) {
        start(false, !z, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator, boolean z) {
                ClipboardAnimDelegate.this.mSelectedLayout.setVisibility(4);
                ClipboardAnimDelegate.this.mIsRunning = false;
            }

            public void onAnimationStart(Animator animator) {
                ClipboardAnimDelegate.this.mIsRunning = true;
                ClipboardAnimDelegate.this.mSelectedLayout.setBackgroundResource(17170445);
            }
        });
    }

    public void doOpen(final boolean z, boolean z3) {
        start(true, !z3, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ClipboardAnimDelegate.this.mIsRunning = false;
            }

            public void onAnimationStart(Animator animator) {
                int i2;
                ClipboardAnimDelegate.this.mIsRunning = true;
                ClipboardAnimDelegate.this.mSelectedLayout.setVisibility(0);
                View b = ClipboardAnimDelegate.this.mSelectedLayout;
                Context d = ClipboardAnimDelegate.this.getContext();
                if (z) {
                    i2 = R.color.default_fw_background;
                } else {
                    i2 = R.color.default_background;
                }
                b.setBackgroundColor(d.getColor(i2));
            }
        });
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public void setTargetView(View view, View view2) {
        this.mSelectedLayout = view;
        this.mToggleLayout = view2;
        this.mToggle = (ImageView) view2.findViewById(R.id.toggle);
        prepareAnimator();
    }
}
