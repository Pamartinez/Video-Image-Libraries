package com.samsung.android.gallery.widget.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.HeightAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TipCardDismissAnimation {
    private void addAlphaAnimator(View view, ArrayList<Animator> arrayList) {
        AlphaAnimator alphaAnimator = new AlphaAnimator(view, 1.0f, 0.0f);
        alphaAnimator.setDuration(150);
        arrayList.add(alphaAnimator);
    }

    private void addHeightAnimator(View view, ArrayList<Animator> arrayList) {
        HeightAnimator heightAnimator = new HeightAnimator(view, view.getMeasuredHeight(), 0);
        heightAnimator.setInterpolator(PathInterpolator.create(0.17f, 0.17f, 0.3f, 1.0f));
        heightAnimator.setDuration(400);
        heightAnimator.setDelay(100);
        arrayList.add(heightAnimator);
    }

    private void addScaleAnimator(View view, ArrayList<Animator> arrayList) {
        ScaleAnimator scaleAnimator = new ScaleAnimator(view, 1.0f, 0.95f);
        scaleAnimator.setInterpolator(PathInterpolator.create(0.33f, 0.0f, 0.2f, 1.0f));
        scaleAnimator.setDuration(250);
        arrayList.add(scaleAnimator);
    }

    public void start(View view, final Runnable runnable) {
        if (view != null) {
            ArrayList arrayList = new ArrayList();
            addAlphaAnimator(view, arrayList);
            addHeightAnimator(view, arrayList);
            addScaleAnimator(view, arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            animatorSet.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    runnable.run();
                }
            });
            animatorSet.start();
        }
    }
}
