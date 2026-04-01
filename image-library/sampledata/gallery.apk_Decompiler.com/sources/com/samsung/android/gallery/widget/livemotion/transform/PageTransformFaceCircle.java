package com.samsung.android.gallery.widget.livemotion.transform;

import N2.j;
import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.support.utils.RectUtils;

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

    public void transformPageInternal(View view, float f) {
        if (this.mDelayProgress < f) {
            RectF faceRectF = this.mPeopleData.getFaceRectF();
            if (this.mCircularRevealAnim == null && RectUtils.isValidRect(faceRectF)) {
                try {
                    Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, (int) faceRectF.centerX(), (int) faceRectF.centerY(), (float) Math.hypot((double) (faceRectF.width() / 2.0f), (double) (faceRectF.height() / 2.0f)), (float) Math.hypot((double) view.getWidth(), (double) view.getHeight()));
                    this.mCircularRevealAnim = createCircularReveal;
                    createCircularReveal.setDuration((long) this.mDurationMs);
                    this.mCircularRevealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                    this.mCircularRevealAnim.start();
                } catch (Exception e) {
                    j.s(e, new StringBuilder("start fail"), this.TAG);
                }
            }
        }
    }

    public void transformStart(View view, float f) {
        this.mPeopleData.setFaceRectF(getDisplayRectF(view), this.mOrientation);
    }

    public KenBurnsInfo.Property copyProperty(KenBurnsInfo.Property property) {
        return property;
    }
}
