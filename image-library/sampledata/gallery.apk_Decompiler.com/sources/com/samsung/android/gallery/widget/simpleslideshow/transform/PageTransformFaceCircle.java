package com.samsung.android.gallery.widget.simpleslideshow.transform;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.samsung.android.gallery.module.creature.people.PeopleData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageTransformFaceCircle extends PageTransform {
    private Animator mCircularRevealAnim;
    public int mDurationMs = 1500;
    private final int mOrientation;
    private final PeopleData mPeopleData;

    public PageTransformFaceCircle(int i2, PeopleData peopleData, int i7) {
        super(i2);
        this.mPeopleData = peopleData;
        this.mOrientation = i7;
    }

    private RectF getDisplayRectF(View view) {
        return new RectF(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
    }

    public void onCancel() {
        Animator animator = this.mCircularRevealAnim;
        if (animator != null) {
            animator.cancel();
        }
    }

    public PageTransformFaceCircle setDurationTime(int i2) {
        this.mDurationMs = i2;
        return this;
    }

    public void transformPageInternal(View view, float f) {
        if (this.mDelayProgress < f) {
            RectF faceRectF = this.mPeopleData.getFaceRectF();
            if (this.mCircularRevealAnim == null) {
                Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, (int) faceRectF.centerX(), (int) faceRectF.centerY(), (float) Math.hypot((double) (faceRectF.width() / 2.0f), (double) (faceRectF.height() / 2.0f)), (float) Math.hypot((double) view.getWidth(), (double) view.getHeight()));
                this.mCircularRevealAnim = createCircularReveal;
                createCircularReveal.setDuration((long) this.mDurationMs);
                this.mCircularRevealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                this.mCircularRevealAnim.start();
            }
        }
    }

    public void transformStart(View view, float f) {
        this.mPeopleData.setFaceRectF(getDisplayRectF(view), this.mOrientation);
    }
}
