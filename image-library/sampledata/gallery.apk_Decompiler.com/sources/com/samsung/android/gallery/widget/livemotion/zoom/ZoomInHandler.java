package com.samsung.android.gallery.widget.livemotion.zoom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ZoomInHandler extends AbsZoomHandler {
    private RectF mBound;

    /* JADX WARNING: Removed duplicated region for block: B:8:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float[] getTranslateXY(android.view.View r10, com.samsung.android.gallery.widget.livemotion.zoom.TouchCoordinates r11, float r12) {
        /*
            r9 = this;
            r0 = 9
            float[] r0 = new float[r0]
            android.graphics.Matrix r1 = r10.getMatrix()
            r1.getValues(r0)
            float r1 = r11.getInitialX()
            r2 = 2
            r3 = r0[r2]
            float r1 = r1 + r3
            float r3 = r11.getInitialY()
            r4 = 5
            r0 = r0[r4]
            float r3 = r3 + r0
            float r0 = r11.getDeltaX()
            float r11 = r11.getDeltaY()
            float r4 = r1 + r0
            float r5 = r3 + r11
            int r6 = r10.getWidth()
            float r6 = (float) r6
            float r6 = r6 * r12
            int r7 = r10.getHeight()
            float r7 = (float) r7
            float r7 = r7 * r12
            android.graphics.RectF r9 = r9.mBound
            float r12 = r9.left
            int r8 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r8 <= 0) goto L_0x003e
        L_0x003b:
            float r0 = r12 - r1
            goto L_0x0047
        L_0x003e:
            float r4 = r4 + r6
            float r12 = r9.right
            int r4 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r4 >= 0) goto L_0x0047
            float r1 = r1 + r6
            goto L_0x003b
        L_0x0047:
            float r12 = r9.top
            int r1 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r1 <= 0) goto L_0x0050
            float r11 = r12 - r3
            goto L_0x005a
        L_0x0050:
            float r5 = r5 + r7
            float r9 = r9.bottom
            int r12 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r12 >= 0) goto L_0x005a
            float r3 = r3 + r7
            float r11 = r9 - r3
        L_0x005a:
            float r9 = r10.getTranslationX()
            float r9 = r9 + r0
            float r10 = r10.getTranslationY()
            float r10 = r10 + r11
            float[] r11 = new float[r2]
            r12 = 0
            r11[r12] = r9
            r9 = 1
            r11[r9] = r10
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.livemotion.zoom.ZoomInHandler.getTranslateXY(android.view.View, com.samsung.android.gallery.widget.livemotion.zoom.TouchCoordinates, float):float[]");
    }

    private void initBound(View view) {
        initBound(view, 2.31f);
    }

    private void initPivot(View view, float f, float f5) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        float f8 = f - ((float) iArr[0]);
        float f10 = f5 - ((float) iArr[1]);
        RectF rectF = new RectF();
        float f11 = f - (f8 * 2.31f);
        rectF.left = f11;
        rectF.right = (((float) view.getWidth()) * 2.31f) + f11;
        float f12 = f5 - (f10 * 2.31f);
        rectF.top = f12;
        float height = (((float) view.getHeight()) * 2.31f) + f12;
        rectF.bottom = height;
        float f13 = rectF.left;
        RectF rectF2 = this.mBound;
        float f14 = rectF2.left;
        if (f13 > f14) {
            f8 += (f13 - f14) / 1.31f;
        }
        float f15 = rectF.right;
        float f16 = rectF2.right;
        if (f15 < f16) {
            f8 -= (f16 - f15) / 1.31f;
        }
        float f17 = rectF.top;
        float f18 = rectF2.top;
        if (f17 > f18) {
            f10 += (f17 - f18) / 1.31f;
        }
        float f19 = rectF2.bottom;
        if (height < f19) {
            f10 -= (f19 - height) / 1.31f;
        }
        view.setPivotX(f8);
        view.setPivotY(f10);
    }

    public AnimatorSet createRestoreAnimator(final View view, int i2, float f) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(view, "scaleX", new float[]{view.getScaleX(), 1.0f}));
        animatorSet.play(ObjectAnimator.ofFloat(view, "scaleY", new float[]{view.getScaleY(), 1.0f}));
        animatorSet.play(ObjectAnimator.ofFloat(view, "translationX", new float[]{view.getTranslationX(), 0.0f}));
        animatorSet.play(ObjectAnimator.ofFloat(view, "translationY", new float[]{view.getTranslationY(), 0.0f}));
        animatorSet.setDuration((long) i2);
        animatorSet.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                view.resetPivot();
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
            }
        });
        return animatorSet;
    }

    public void move(View view, TouchCoordinates touchCoordinates, float f) {
        float[] translateXY = getTranslateXY(view, touchCoordinates, f);
        view.setTranslationX(translateXY[0]);
        view.setTranslationY(translateXY[1]);
    }

    public void onScale(View view, float f) {
        initBound(view, f);
        view.setScaleX(f);
        view.setScaleY(f);
    }

    public void onScaleBegin(View view, float f, float f5) {
        initBound(view);
        initPivot(view, f, f5);
    }

    private void initBound(View view, float f) {
        int width = ((View) view.getParent()).getWidth();
        int height = ((View) view.getParent()).getHeight();
        float width2 = ((float) view.getWidth()) * f;
        float height2 = ((float) view.getHeight()) * f;
        RectF rectF = new RectF();
        this.mBound = rectF;
        float f5 = (float) width;
        rectF.left = Math.max(0.0f, (f5 - width2) / 2.0f);
        float f8 = (float) height;
        this.mBound.top = Math.max(0.0f, (f8 - height2) / 2.0f);
        RectF rectF2 = this.mBound;
        float f10 = rectF2.left;
        if (f10 != 0.0f) {
            f5 = f10 + width2;
        }
        rectF2.right = f5;
        float f11 = rectF2.top;
        if (f11 != 0.0f) {
            f8 = f11 + height2;
        }
        rectF2.bottom = f8;
    }
}
