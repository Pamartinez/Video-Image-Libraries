package com.samsung.android.gallery.widget.animator;

import H7.x;
import android.animation.ValueAnimator;
import android.view.View;
import f4.a;
import h4.C0464a;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlphaAnimator extends PropertyAnimator {
    private float mDurationRelative;
    private float mFrom;
    private boolean mPlayback;
    private float mStartRelative;
    private float mTo;
    private final ArrayList<View> mViewList;

    public AlphaAnimator(View view) {
        super(view);
        this.mFrom = 0.0f;
        this.mTo = 0.0f;
        this.mStartRelative = 0.0f;
        this.mDurationRelative = 0.0f;
        this.mViewList = new ArrayList<>();
        setFloatValues(new float[]{0.0f, 1.0f});
    }

    public AlphaAnimator alpha(float f) {
        this.mFrom = this.mView.getAlpha();
        this.mTo = f;
        return this;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        super.onAnimationUpdate(valueAnimator);
        float f5 = this.mStartRelative;
        if (f5 > 0.0f) {
            float f8 = this.mCurrentValue;
            if (f5 > f8) {
                f = this.mFrom;
            } else {
                float f10 = this.mFrom;
                f = C0212a.a(this.mTo, f10, (f8 - f5) / (1.0f - f5), f10);
            }
        } else {
            float f11 = this.mDurationRelative;
            if (f11 > 0.0f) {
                float f12 = this.mCurrentValue;
                if (f11 >= f12) {
                    float f13 = this.mFrom;
                    f = C0212a.a(this.mTo, f13, f12 / f11, f13);
                } else {
                    if (this.mPlayback) {
                        float f14 = 1.0f - f11;
                        if (f14 <= f12) {
                            float f15 = this.mTo;
                            f = C0212a.a(this.mFrom, f15, (f12 - f11) / f14, f15);
                        }
                    }
                    f = this.mTo;
                }
            } else {
                float f16 = this.mFrom;
                f = C0212a.a(this.mTo, f16, this.mCurrentValue, f16);
            }
        }
        View view = this.mView;
        if (view != null) {
            view.setAlpha(f);
        }
        this.mViewList.forEach(new x(f, 3));
    }

    public AlphaAnimator playback(boolean z) {
        this.mPlayback = z;
        float f = this.mDurationRelative;
        if (f != 0.0f && f < 0.5f) {
            return this;
        }
        this.mDurationRelative = 0.5f;
        return this;
    }

    public AlphaAnimator setDurationRelative(float f) {
        this.mDurationRelative = f;
        this.mStartRelative = 0.0f;
        return this;
    }

    public AlphaAnimator setStartRelative(float f) {
        this.mStartRelative = f;
        this.mDurationRelative = 0.0f;
        return this;
    }

    public void updateView(View view) {
        this.mView = view;
    }

    public AlphaAnimator(View view, float f, float f5) {
        super(view);
        this.mFrom = 0.0f;
        this.mTo = 0.0f;
        this.mStartRelative = 0.0f;
        this.mDurationRelative = 0.0f;
        this.mViewList = new ArrayList<>();
        setFloatValues(new float[]{0.0f, 1.0f});
        this.mFrom = f;
        this.mTo = f5;
    }

    public AlphaAnimator(View[] viewArr, float f, float f5) {
        super(viewArr[0]);
        this.mFrom = 0.0f;
        this.mTo = 0.0f;
        this.mStartRelative = 0.0f;
        this.mDurationRelative = 0.0f;
        ArrayList<View> arrayList = new ArrayList<>();
        this.mViewList = arrayList;
        setFloatValues(new float[]{0.0f, 1.0f});
        this.mFrom = f;
        this.mTo = f5;
        if (viewArr.length > 1) {
            Arrays.stream(viewArr).skip(1).filter(new C0464a(7)).forEach(new a(arrayList, 15));
        }
    }
}
