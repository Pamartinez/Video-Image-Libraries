package com.samsung.android.gallery.widget.animations.textexpand;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.databinding.ViewerAiEditItemLayoutBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExpandAnimView extends RelativeLayout {
    private final TextExpandAnimation mAnimation;
    AnimatedVectorDrawable mAvd;
    private int mExpandWidth;
    private int mExpandXPosition;
    private ItemViewUpdateListener mListener;
    private final ViewerAiEditItemLayoutBinding mViewBinding;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ItemViewUpdateListener {
    }

    public TextExpandAnimView(Context context) {
        this(context, (AttributeSet) null);
    }

    private void resetAnimationWidthValue() {
        this.mAnimation.resetBgExpandWidth();
        this.mExpandWidth = 0;
    }

    /* access modifiers changed from: private */
    public void updateBgWidth(int i2) {
        if (this.mViewBinding.aiEditItemText.getAlpha() == 1.0f) {
            LinearLayout linearLayout = this.mViewBinding.aiEditItemBg;
            linearLayout.getLayoutParams().width = i2;
            linearLayout.requestLayout();
            if (ViewUtils.isVisible(this.mViewBinding.effectView)) {
                ViewStub viewStub = this.mViewBinding.effectView;
                viewStub.getLayoutParams().width = i2;
                viewStub.requestLayout();
            }
        }
        ItemViewUpdateListener itemViewUpdateListener = this.mListener;
        if (itemViewUpdateListener != null) {
            ((a) itemViewUpdateListener).f3201a.onViewTextUpdate(this);
        }
    }

    public int getExpandWidth() {
        if (this.mExpandWidth == 0) {
            measure(0, 0);
            this.mExpandWidth = getMeasuredWidth();
        }
        return this.mExpandWidth;
    }

    public TextExpandAnimation getItemAnimation() {
        return this.mAnimation;
    }

    public int getXPosition() {
        return this.mExpandXPosition;
    }

    public boolean isAnimationRunning() {
        return this.mAnimation.isRunning();
    }

    public boolean isAvdRunning() {
        if (this.mAvd != null) {
            return true;
        }
        return false;
    }

    public void setIconAndTitle(int i2, int i7) {
        updateIcon(i2);
        updateText(i7);
    }

    public void setListener(ItemViewUpdateListener itemViewUpdateListener) {
        this.mListener = itemViewUpdateListener;
    }

    public void startAvd() {
        if (!isAvdRunning()) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) this.mViewBinding.aiEditItemImage.getDrawable();
            this.mAvd = animatedVectorDrawable;
            animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                public void onAnimationEnd(Drawable drawable) {
                    super.onAnimationEnd(drawable);
                    AnimatedVectorDrawable animatedVectorDrawable = TextExpandAnimView.this.mAvd;
                    if (animatedVectorDrawable != null) {
                        animatedVectorDrawable.start();
                    }
                }
            });
            this.mAvd.start();
        }
    }

    public void stopAvd() {
        AnimatedVectorDrawable animatedVectorDrawable = this.mAvd;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.clearAnimationCallbacks();
            this.mAvd.stop();
            this.mAvd = null;
        }
    }

    public void updateIcon(Drawable drawable) {
        stopAvd();
        this.mViewBinding.aiEditItemImage.setImageDrawable(drawable);
        if (drawable instanceof AnimatedVectorDrawable) {
            startAvd();
        }
    }

    public void updateText(int i2) {
        updateText(getResources().getString(i2));
    }

    public void updateXPosition(int i2) {
        this.mExpandXPosition = i2;
    }

    public TextExpandAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mExpandWidth = 0;
        this.mExpandXPosition = 0;
        ViewerAiEditItemLayoutBinding inflate = ViewerAiEditItemLayoutBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.mViewBinding = inflate;
        this.mAnimation = new TextExpandAnimation(inflate.aiEditItemLayout);
        setVisibility(8);
    }

    public void updateText(String str) {
        this.mViewBinding.aiEditItemText.setText(str);
        LinearLayout linearLayout = this.mViewBinding.aiEditTextLayout;
        StringBuilder t = C0212a.t(str, ArcCommonLog.TAG_COMMA);
        t.append(getResources().getString(R$string.speak_button));
        linearLayout.setContentDescription(t.toString());
        resetAnimationWidthValue();
        if (getVisibility() == 0 && getAlpha() == 1.0f) {
            this.mViewBinding.aiEditTextLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
                    int i15 = i8 - i2;
                    if (i13 - i11 != i15) {
                        view.removeOnLayoutChangeListener(this);
                        if (TextExpandAnimView.this.getVisibility() == 0 && TextExpandAnimView.this.getAlpha() == 1.0f) {
                            TextExpandAnimView.this.updateBgWidth(Math.abs(i15));
                        }
                    }
                }
            });
        }
    }

    public void updateIcon(int i2) {
        updateIcon(getResources().getDrawable(i2, (Resources.Theme) null));
    }
}
