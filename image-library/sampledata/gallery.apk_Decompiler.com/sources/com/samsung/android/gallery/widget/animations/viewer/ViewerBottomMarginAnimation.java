package com.samsung.android.gallery.widget.animations.viewer;

import A.a;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerBottomMarginAnimation extends ViewerBaseAnimation {
    private Consumer<Void> mAnimationCallback;
    private int mSourceBotMargin;
    private int mSourceRightMargin;
    private int mSourceSideMargin;
    private int mSourceTopMargin;
    int mTargetBotMargin;
    int mTargetRightMargin = -1;
    int mTargetTopMargin;

    public void applyManualTransformation(View view, int i2, int i7, int i8) {
        if (view != null && !isAnimationRunning()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.bottomMargin = i7;
            marginLayoutParams.rightMargin = i8;
            marginLayoutParams.topMargin = i2;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public void applyTransformation(float f, Transformation transformation) {
        View view = this.mView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int i2 = this.mSourceBotMargin;
            marginLayoutParams.bottomMargin = i2 + ((int) (((float) (this.mTargetBotMargin - i2)) * f));
            int i7 = this.mSourceTopMargin;
            marginLayoutParams.topMargin = i7 + ((int) (((float) (this.mTargetTopMargin - i7)) * f));
            int i8 = this.mTargetRightMargin;
            if (i8 > 0) {
                int i10 = this.mSourceRightMargin;
                marginLayoutParams.rightMargin = i10 + ((int) (((float) (i8 - i10)) * f));
            } else {
                int i11 = this.mSourceSideMargin;
                marginLayoutParams.rightMargin = (int) (((float) i11) - (((float) i11) * f));
            }
            int i12 = this.mSourceSideMargin;
            marginLayoutParams.leftMargin = (int) (((float) i12) - (((float) i12) * f));
            this.mView.setLayoutParams(marginLayoutParams);
            Consumer<Void> consumer = this.mAnimationCallback;
            if (consumer != null) {
                consumer.accept((Object) null);
            }
        }
    }

    public boolean isAnimationRunning() {
        boolean z;
        View view;
        if (!this.mRunning || (view = this.mView) == null || view.getAnimation() == null) {
            z = false;
        } else {
            z = true;
        }
        this.mRunning = z;
        return z;
    }

    public void startAnimation(View view, int i2, int i7, int i8) {
        if (view != null) {
            this.mView = view;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int i10 = marginLayoutParams.bottomMargin;
            this.mSourceBotMargin = i10;
            this.mSourceTopMargin = marginLayoutParams.topMargin;
            this.mSourceSideMargin = marginLayoutParams.leftMargin;
            this.mSourceRightMargin = marginLayoutParams.rightMargin;
            this.mTargetTopMargin = i2;
            this.mTargetBotMargin = i7;
            this.mTargetRightMargin = i8;
            if (i10 == i7) {
                String str = this.TAG;
                StringBuilder sb2 = new StringBuilder("Animation abort, src=tgt : ");
                sb2.append(this.mSourceBotMargin);
                sb2.append(" = ");
                a.w(sb2, this.mTargetBotMargin, str);
                return;
            }
            this.mRunning = true;
            this.mView.startAnimation(this);
        }
    }

    public void withAnimationCallback(Consumer<Void> consumer) {
        this.mAnimationCallback = consumer;
    }
}
