package com.samsung.android.gallery.widget.listview.noitem;

import H3.l;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoItemVI {
    protected TextView mDescription;
    protected TextView mLabel;
    protected View mRootView;

    public NoItemVI(View view) {
        setRootView(view);
    }

    private String isHighText() {
        return Locale.getDefault().getLanguage();
    }

    private boolean isLabelVisible() {
        TextView textView = this.mLabel;
        if (textView == null || textView.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    private void startDescriptionAnimation() {
        long j2;
        if (this.mDescription != null) {
            setDescriptionBringToFront();
            TextView textView = this.mDescription;
            if (isLabelVisible()) {
                j2 = 100;
            } else {
                j2 = -1;
            }
            composeAnimation(textView, j2);
        }
    }

    private void startLabelAnimation() {
        if (this.mLabel != null) {
            setLabelBringToFront();
            composeAnimation(this.mLabel, -1);
        }
    }

    public final void composeAnimation(View view, long j2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f});
        ofFloat.setDuration(500);
        ofFloat.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_90));
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f});
        ofFloat2.setDuration(333);
        ofFloat2.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_33));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat2, ofFloat});
        if (j2 != -1) {
            animatorSet.setStartDelay(100);
        }
        animatorSet.start();
    }

    public final int getPixelOffset(int i2) {
        View view = this.mRootView;
        if (view != null) {
            return view.getResources().getDimensionPixelOffset(i2);
        }
        return 0;
    }

    public View getRootView() {
        return this.mRootView;
    }

    public void inflateChildView() {
        initLabelView(false);
        initDescriptionView(false);
    }

    public void initDescriptionView(boolean z) {
        float f;
        TextView textView = (TextView) this.mRootView.findViewById(R$id.description);
        this.mDescription = textView;
        if (textView != null) {
            if (TextUtils.isEmpty(textView.getText())) {
                this.mDescription.setVisibility(8);
                return;
            }
            this.mDescription.setVisibility(0);
            if (!z) {
                TextView textView2 = this.mDescription;
                float pixelOffset = (float) getPixelOffset(R$dimen.noitem_line_spacing);
                if (isHighText().equals("km")) {
                    f = 1.125f;
                } else {
                    f = 1.0f;
                }
                textView2.setLineSpacing(pixelOffset, f);
                this.mDescription.setTranslationY((float) getPixelOffset(R$dimen.noitem_initial_translation_y));
                this.mDescription.setAlpha(0.0f);
            }
        }
    }

    public void initLabelView(boolean z) {
        TextView textView = (TextView) this.mRootView.findViewById(R$id.label);
        this.mLabel = textView;
        if (textView != null) {
            if (TextUtils.isEmpty(textView.getText())) {
                this.mLabel.setVisibility(8);
                return;
            }
            this.mLabel.setVisibility(0);
            if (!z) {
                this.mLabel.setTranslationY((float) getPixelOffset(R$dimen.noitem_initial_translation_y));
                this.mLabel.setAlpha(0.0f);
            }
        }
    }

    public void setDescriptionBringToFront() {
        TextView textView = this.mDescription;
        if (textView != null) {
            textView.bringToFront();
            this.mDescription.setVisibility(0);
        }
    }

    public void setLabelBringToFront() {
        TextView textView = this.mLabel;
        if (textView != null) {
            textView.bringToFront();
            this.mLabel.setVisibility(0);
        }
    }

    public void setRootView(View view) {
        this.mRootView = view;
    }

    public void showInitAnimation() {
        startLabelAnimation();
        startDescriptionAnimation();
    }

    public void startAnimation() {
        inflateChildView();
        new Handler().post(new l(7, this));
    }
}
