package com.google.android.material.tabs;

import A2.b;
import android.content.Context;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import androidx.appcompat.util.SeslMisc;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslTabRoundRectIndicator extends View {
    public AnimationSet d;
    public final LinearInterpolator e = new LinearInterpolator();
    public final PathInterpolator f = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);

    public SeslTabRoundRectIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        int i2;
        if (SeslMisc.isLightTheme(context)) {
            i2 = R.drawable.sesl_tablayout_subtab_indicator_background_light;
        } else {
            i2 = R.drawable.sesl_tablayout_subtab_indicator_background_dark;
        }
        ViewCompat.setBackground(this, ContextCompat.getDrawable(context, i2));
    }

    public final void a() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        startAnimation(alphaAnimation);
        setAlpha(0.0f);
    }

    public final void b() {
        setAlpha(1.0f);
        AnimationSet animationSet = new AnimationSet(false);
        this.d = animationSet;
        animationSet.setFillAfter(true);
        this.d.setAnimationListener(new b(this));
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(250);
        scaleAnimation.setInterpolator(this.f);
        scaleAnimation.setFillAfter(true);
        this.d.addAnimation(scaleAnimation);
        if (!isSelected()) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(100);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setInterpolator(this.e);
            this.d.addAnimation(alphaAnimation);
        }
        startAnimation(this.d);
    }

    public final void c() {
        setAlpha(1.0f);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(350);
        scaleAnimation.setInterpolator(this.f);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        startAnimation(animationSet);
    }

    public final void d() {
        setAlpha(1.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        startAnimation(alphaAnimation);
    }

    public final void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (i2 != 0 && !isSelected()) {
            a();
        }
    }

    public void setSelectedIndicatorColor(int i2) {
        if (!(getBackground() instanceof NinePatchDrawable)) {
            getBackground().setTint(i2);
            if (!isSelected()) {
                a();
            }
        }
    }
}
