package androidx.core.widget;

import a6.g;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroupOverlay;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.SemBlurCompat;
import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.scs.base.StatusCodes;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslGoToTopController {
    private static final SemBlurCompat.CurveParameter DARK_THICK = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.5f, -15.0f, 0.0f, 255.0f, 33.8f, 153.7f);
    private static final SemBlurCompat.CurveParameter LIGHT_THICK = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.4f, 15.0f, 15.0f, 235.0f, 176.7f, 253.2f);
    private final Runnable mAutoHide = new Runnable() {
        public void run() {
            SeslGoToTopController.this.applyState(0);
        }
    };
    SeslGoToTopConfig mConfig;
    private boolean mEnableGoToTop = false;
    private ValueAnimator mFadeInAnimator;
    private final Runnable mFadeInRunnable = new Runnable() {
        public void run() {
            SeslGoToTopController.this.playFadeIn();
        }
    };
    private ValueAnimator mFadeOutAnimator;
    private final Runnable mFadeOutRunnable = new Runnable() {
        public void run() {
            SeslGoToTopController.this.playFadeOut();
        }
    };
    /* access modifiers changed from: private */
    public int mFadeOutState = 0;
    Drawable mGoToTopImage;
    private int mGoToTopLastState = 0;
    Rect mGoToTopRect = new Rect();
    int mGoToTopState = 0;
    SeslGoToTopImageView mGoToTopView;
    Host mHost;
    private int mImmersiveBottomPadding = 0;
    private boolean mIsBlurEnabled = false;
    private boolean mIsScrollRunning = false;
    OnGoToTopClickListener mOnClickListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends SeslGoToTopControllerBuilder<SeslGoToTopController, Builder> {
        public SeslGoToTopController build() {
            validate();
            return new SeslGoToTopController(this.host, this.config);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Host {
        boolean canScrollDown();

        boolean canScrollUp();

        Context getContext();

        int getHeight();

        void getLocationInWindow(int[] iArr);

        ViewGroupOverlay getOverlay();

        int getPaddingLeft();

        int getPaddingRight();

        int getScrollY();

        int getWidth();

        boolean isFastScrollerEnabled();

        void playSoundEffect(int i2);

        void post(Runnable runnable);

        void postDelayed(Runnable runnable, long j2);

        void removeCallbacks(Runnable runnable);

        void showTopEdgeEffect();

        void smoothScrollToTop();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnGoToTopClickListener {
        boolean onGoToTopClick();
    }

    public SeslGoToTopController(Host host, SeslGoToTopConfig seslGoToTopConfig) {
        this.mHost = host;
        this.mConfig = seslGoToTopConfig;
    }

    private boolean applyBlur(boolean z) {
        SemBlurCompat.CurveParameter curveParameter;
        SeslGoToTopImageView seslGoToTopImageView = this.mGoToTopView;
        if (z) {
            curveParameter = LIGHT_THICK;
        } else {
            curveParameter = DARK_THICK;
        }
        return SemBlurCompat.setBlurEffectPreset(seslGoToTopImageView, 2, curveParameter, (Integer) null, (Float) null, 1);
    }

    private void applyLayout() {
        SeslGoToTopImageView seslGoToTopImageView = this.mGoToTopView;
        Rect rect = this.mGoToTopRect;
        seslGoToTopImageView.layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    private void cleanupOnDisable() {
        this.mHost.removeCallbacks(this.mAutoHide);
        this.mHost.removeCallbacks(this.mFadeInRunnable);
        this.mHost.removeCallbacks(this.mFadeOutRunnable);
        ValueAnimator valueAnimator = this.mFadeInAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mFadeOutAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        if (this.mGoToTopView != null) {
            if (this.mIsBlurEnabled) {
                clearBlur();
            }
            try {
                this.mHost.getOverlay().remove(this.mGoToTopView);
            } catch (Exception unused) {
            }
            this.mGoToTopView.setImageDrawable((Drawable) null);
        }
        this.mGoToTopImage = null;
        this.mFadeOutState = 0;
        this.mGoToTopLastState = 0;
        this.mGoToTopState = 0;
        this.mGoToTopRect.set(0, 0, 0, 0);
        this.mGoToTopView = null;
    }

    private void clearBlur() {
        SemBlurCompat.setBlurInfoClear(this.mGoToTopView);
        this.mIsBlurEnabled = false;
    }

    private void computeTargetRect() {
        int paddingLeft = this.mHost.getPaddingLeft();
        int width = this.mHost.getWidth() - this.mHost.getPaddingRight();
        int paddingLeft2 = this.mConfig.getPaddingLeft() + paddingLeft;
        int paddingRight = width - this.mConfig.getPaddingRight();
        int size = this.mConfig.getSize() / 2;
        int i2 = paddingLeft2 + size;
        int i7 = paddingRight - size;
        if (i2 > i7) {
            i2 = paddingLeft + size;
            i7 = width - size;
        } else {
            paddingLeft = paddingLeft2;
            width = paddingRight;
        }
        int D = C0086a.D(width, paddingLeft, 2, paddingLeft);
        if (D >= i2) {
            i2 = D;
        }
        if (i2 <= i7) {
            i7 = i2;
        }
        int height = this.mHost.getHeight();
        this.mGoToTopRect.set(i7 - size, ((height - this.mConfig.getSize()) - this.mConfig.getPaddingBottom()) - this.mImmersiveBottomPadding, i7 + size, (height - this.mConfig.getPaddingBottom()) - this.mImmersiveBottomPadding);
    }

    private void disableBlurEffect(boolean z) {
        clearBlur();
        setSolidBackground(z);
    }

    private void enableBlurEffect(boolean z) {
        if (applyBlur(z)) {
            setBlurBackground();
        } else {
            setSolidBackground(z);
        }
    }

    private void ensureView(boolean z) {
        if (!isEnabled()) {
            this.mGoToTopImage = this.mConfig.getIcon(z);
            SeslGoToTopImageView seslGoToTopImageView = new SeslGoToTopImageView(this.mHost.getContext());
            this.mGoToTopView = seslGoToTopImageView;
            seslGoToTopImageView.setWindowLocationProvider(new g(4, this));
            this.mGoToTopView.setImageDrawable(this.mGoToTopImage);
        }
    }

    private void initAnimators() {
        float f;
        float f5 = 0.9f;
        if (this.mFadeInAnimator == null) {
            if (this.mIsBlurEnabled) {
                f = 1.0f;
            } else {
                f = 0.9f;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, f});
            this.mFadeInAnimator = ofFloat;
            ofFloat.setDuration(333);
            this.mFadeInAnimator.setInterpolator(this.mConfig.getFadeInInterpolator());
            this.mFadeInAnimator.addUpdateListener(new c(this, 0));
        }
        if (this.mFadeOutAnimator == null) {
            if (this.mIsBlurEnabled) {
                f5 = 1.0f;
            }
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{f5, 0.0f});
            this.mFadeOutAnimator = ofFloat2;
            ofFloat2.setDuration(150);
            this.mFadeOutAnimator.setInterpolator(this.mConfig.getFadeOutInterpolator());
            this.mFadeOutAnimator.addUpdateListener(new c(this, 1));
            this.mFadeOutAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    int unused = SeslGoToTopController.this.mFadeOutState = 2;
                    SeslGoToTopController.this.applyState(0);
                }

                public void onAnimationStart(Animator animator) {
                    int unused = SeslGoToTopController.this.mFadeOutState = 1;
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
        }
    }

    private boolean isBlurEnabled() {
        return this.mIsBlurEnabled;
    }

    private boolean isEnvironmentAvailable() {
        String string;
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.mHost.getContext().getSystemService("accessibility");
        if ((accessibilityManager == null || !accessibilityManager.isEnabled() || (string = Settings.Secure.getString(this.mHost.getContext().getContentResolver(), "enabled_accessibility_services")) == null || (!string.matches("(?i).*com.samsung.accessibility/com.samsung.android.app.talkback.TalkBackService.*") && !string.matches("(?i).*com.samsung.android.accessibility.talkback/com.samsung.android.marvin.talkback.TalkBackService.*") && !string.matches("(?i).*com.google.android.marvin.talkback.TalkBackService.*") && !string.matches("(?i).*com.samsung.accessibility/com.samsung.accessibility.universalswitch.UniversalSwitchService.*"))) && this.mHost.getHeight() > this.mConfig.getOverlayFeatureHiddenHeightPx()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureView$0(int[] iArr) {
        int[] iArr2 = new int[2];
        this.mHost.getLocationInWindow(iArr2);
        iArr[0] = iArr[0] + iArr2[0];
        iArr[1] = (iArr2[1] - this.mHost.getScrollY()) + iArr[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAnimators$1(ValueAnimator valueAnimator) {
        try {
            this.mGoToTopView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAnimators$2(ValueAnimator valueAnimator) {
        try {
            this.mGoToTopView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void playFadeIn() {
        float f;
        ValueAnimator valueAnimator = this.mFadeInAnimator;
        if (valueAnimator != null && this.mFadeOutAnimator != null && !valueAnimator.isRunning()) {
            if (this.mFadeOutAnimator.isRunning()) {
                this.mFadeOutAnimator.cancel();
            }
            if (this.mGoToTopImage.getAlpha() < 255) {
                this.mGoToTopImage.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
            }
            ValueAnimator valueAnimator2 = this.mFadeInAnimator;
            float alpha = this.mGoToTopView.getAlpha();
            if (this.mIsBlurEnabled) {
                f = 1.0f;
            } else {
                f = 0.9f;
            }
            valueAnimator2.setFloatValues(new float[]{alpha, f});
            this.mFadeInAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public void playFadeOut() {
        ValueAnimator valueAnimator = this.mFadeOutAnimator;
        if (valueAnimator != null && this.mFadeInAnimator != null && !valueAnimator.isRunning()) {
            if (this.mFadeInAnimator.isRunning()) {
                this.mFadeOutAnimator.cancel();
            }
            this.mFadeOutAnimator.setFloatValues(new float[]{this.mGoToTopView.getAlpha(), 0.0f});
            this.mFadeOutAnimator.start();
        }
    }

    private void setBlurBackground() {
        this.mGoToTopView.setBackground(this.mConfig.getBackgroundBlur());
        this.mGoToTopView.setElevation(this.mConfig.getElevation());
        this.mGoToTopView.setClipToOutline(true);
        this.mGoToTopView.setBackgroundTintList(ColorStateList.valueOf(0));
    }

    private void setBlurEnabledInternal(boolean z, boolean z3) {
        if (z) {
            enableBlurEffect(z3);
        } else {
            disableBlurEffect(z3);
        }
        this.mIsBlurEnabled = z;
    }

    private void setSolidBackground(boolean z) {
        Drawable drawable;
        SeslGoToTopImageView seslGoToTopImageView = this.mGoToTopView;
        if (z) {
            drawable = this.mConfig.getBackgroundLight();
        } else {
            drawable = this.mConfig.getBackgroundDark();
        }
        seslGoToTopImageView.setBackground(drawable);
        this.mGoToTopView.setElevation(this.mConfig.getElevation());
        this.mGoToTopView.setBackgroundTintList((ColorStateList) null);
        this.mGoToTopView.setAlpha(0.9f);
    }

    public void applyState(int i2) {
        if (isEnabled()) {
            if (!isEnvironmentAvailable()) {
                this.mFadeOutState = 2;
                i2 = 0;
            }
            this.mHost.removeCallbacks(this.mAutoHide);
            if (i2 == 1 && !this.mHost.canScrollUp()) {
                i2 = 0;
            }
            if (i2 != -1 || !this.mConfig.isSizeChanged()) {
                if (i2 == -1 && (this.mHost.canScrollUp() || this.mHost.canScrollDown())) {
                    i2 = 1;
                }
            } else if (this.mHost.canScrollUp() || this.mHost.canScrollDown()) {
                i2 = this.mGoToTopLastState;
            } else {
                i2 = 0;
            }
            if (i2 != 0) {
                this.mHost.removeCallbacks(this.mFadeOutRunnable);
            }
            if (i2 != 1) {
                this.mHost.removeCallbacks(this.mFadeInRunnable);
            }
            if (this.mFadeOutState == 0 && i2 == 0 && this.mGoToTopLastState != 0) {
                this.mHost.post(this.mFadeOutRunnable);
            }
            if (i2 != 2) {
                this.mGoToTopView.setPressed(false);
            }
            this.mGoToTopState = i2;
            if (i2 != 0) {
                if (i2 == 1 || i2 == 2) {
                    this.mHost.removeCallbacks(this.mFadeOutRunnable);
                    computeTargetRect();
                }
            } else if (this.mFadeOutState == 2) {
                this.mGoToTopRect.set(0, 0, 0, 0);
            }
            if (this.mFadeOutState == 2) {
                this.mFadeOutState = 0;
            }
            applyLayout();
            if (i2 == 1 && (this.mGoToTopLastState == 0 || this.mGoToTopView.getAlpha() == 0.0f || this.mConfig.isSizeChanged())) {
                this.mHost.post(this.mFadeInRunnable);
            }
            this.mConfig.setSizeChanged(false);
            this.mGoToTopLastState = this.mGoToTopState;
        }
    }

    public void autoHide(int i2) {
        if (isAvailable()) {
            if (i2 == 0) {
                if (!this.mHost.isFastScrollerEnabled()) {
                    this.mHost.removeCallbacks(this.mAutoHide);
                    this.mHost.postDelayed(this.mAutoHide, (long) getAutoHideDelayMs());
                }
            } else if (i2 == 1) {
                this.mHost.removeCallbacks(this.mAutoHide);
                this.mHost.postDelayed(this.mAutoHide, (long) getAutoHideDelayMs());
            }
        }
    }

    public boolean contains(int i2, int i7) {
        return this.mGoToTopRect.contains(i2, i7);
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!isAvailable()) {
            return false;
        }
        int action = motionEvent.getAction();
        int x9 = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (action == 7) {
            if (this.mGoToTopRect.contains(x9, y)) {
                return true;
            }
            if (this.mGoToTopState == 2) {
                this.mGoToTopState = 1;
                this.mGoToTopView.setPressed(false);
                autoHide(1);
            }
        }
        return false;
    }

    public void draw() {
        if (isEnabled()) {
            this.mGoToTopView.setTranslationY((float) this.mHost.getScrollY());
            if (this.mGoToTopState != 0 && !this.mHost.canScrollUp()) {
                applyState(0);
            }
            if (!isAvailable()) {
                this.mGoToTopView.setAlpha(0.0f);
            }
        }
    }

    public int getAutoHideDelayMs() {
        return 1500;
    }

    public int getBottomPadding() {
        return this.mConfig.getPaddingBottom();
    }

    public int getDefaultBottomPadding() {
        if (this.mConfig.hasDefaultBottomPadding()) {
            return this.mConfig.getDefaultPaddingBottom();
        }
        return 0;
    }

    public int getState() {
        return this.mGoToTopState;
    }

    public SeslGoToTopImageView getView() {
        return this.mGoToTopView;
    }

    public void invalidate() {
        if (isAvailable() && isBlurEnabled()) {
            this.mGoToTopView.invalidate();
        }
    }

    public boolean isAvailable() {
        if (!isEnvironmentAvailable() || !isEnabled()) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        if (!this.mEnableGoToTop || this.mGoToTopView == null) {
            return false;
        }
        return true;
    }

    public final boolean isScrollRunning() {
        return this.mIsScrollRunning;
    }

    public void onSizeChanged() {
        applyState(-1);
        autoHide(1);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isAvailable()) {
            int actionMasked = motionEvent.getActionMasked();
            int x9 = (int) (motionEvent.getX() + 0.5f);
            int y = (int) (motionEvent.getY() + 0.5f);
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        if (actionMasked != 3) {
                            switch (actionMasked) {
                                case 211:
                                    break;
                                case 212:
                                    break;
                                case 213:
                                    break;
                            }
                        } else {
                            int i2 = this.mGoToTopState;
                            if (i2 != 0) {
                                if (i2 == 2) {
                                    this.mGoToTopState = 1;
                                }
                                this.mGoToTopView.setPressed(false);
                            }
                        }
                    }
                    if (this.mGoToTopState == 2) {
                        if (!this.mGoToTopRect.contains(x9, y)) {
                            this.mGoToTopState = 1;
                            this.mGoToTopView.setPressed(false);
                            autoHide(1);
                            return true;
                        }
                        return true;
                    }
                }
                if (this.mGoToTopState == 2) {
                    if (this.mHost.canScrollUp()) {
                        OnGoToTopClickListener onGoToTopClickListener = this.mOnClickListener;
                        if (onGoToTopClickListener == null || !onGoToTopClickListener.onGoToTopClick()) {
                            setScrollRunning(true);
                            this.mHost.smoothScrollToTop();
                        }
                        return true;
                    }
                    autoHide(0);
                    this.mHost.playSoundEffect(0);
                    return true;
                }
            }
            if (this.mGoToTopState != 2 && this.mGoToTopRect.contains(x9, y)) {
                applyState(2);
                this.mGoToTopView.setPressed(true);
                return true;
            }
        }
        return false;
    }

    public void release() {
        if (this.mEnableGoToTop) {
            cleanupOnDisable();
            this.mEnableGoToTop = false;
        }
    }

    public void setBlurEnabled(boolean z, boolean z3) {
        if (Build.VERSION.SDK_INT >= 35 && isEnabled() && z != isBlurEnabled()) {
            setBlurEnabledInternal(z, z3);
        }
    }

    public void setBottomPadding(int i2) {
        if (i2 >= 0 && isEnabled() && i2 != this.mConfig.getPaddingBottom()) {
            this.mConfig.setPaddingBottom(i2);
            if (this.mGoToTopState != 0 || this.mFadeOutState == 1) {
                computeTargetRect();
                applyLayout();
            }
        }
    }

    public void setEnabled(boolean z, boolean z3) {
        ensureView(z3);
        SeslGoToTopImageView seslGoToTopImageView = this.mGoToTopView;
        if (seslGoToTopImageView != null && z != this.mEnableGoToTop) {
            this.mEnableGoToTop = z;
            if (z) {
                seslGoToTopImageView.setAlpha(0.0f);
                this.mHost.getOverlay().add(this.mGoToTopView);
                setBlurEnabled(true, z3);
                initAnimators();
                return;
            }
            cleanupOnDisable();
        }
    }

    public void setImmersiveBottomPadding(int i2) {
        if (i2 < 0 || !isEnabled()) {
            return;
        }
        if (((this.mHost.getHeight() - this.mConfig.getSize()) - this.mConfig.getPaddingBottom()) - i2 < 0) {
            this.mImmersiveBottomPadding = 0;
            Log.e("SeslGoToTopController", "The Immersive padding value (" + i2 + ") was too large to draw GoToTop.");
            return;
        }
        this.mImmersiveBottomPadding = i2;
        if (this.mGoToTopState != 0 || this.mFadeOutState == 1) {
            computeTargetRect();
            applyLayout();
        }
    }

    public void setOnGoToTopClickListener(OnGoToTopClickListener onGoToTopClickListener) {
        this.mOnClickListener = onGoToTopClickListener;
    }

    public void setOverlayFeatureHiddenHeightPx(int i2) {
        this.mConfig.setOverlayFeatureHiddenHeightPx(i2);
    }

    public void setPaddingHorizontal(int i2, int i7) {
        if (i2 >= 0 && i7 >= 0 && isEnabled()) {
            if (this.mConfig.getPaddingLeft() != i2) {
                this.mConfig.setPaddingLeft(i2);
            }
            if (this.mConfig.getPaddingRight() != i7) {
                this.mConfig.setPaddingRight(i7);
            }
            if (this.mGoToTopState != 0 || this.mFadeOutState == 1) {
                computeTargetRect();
                applyLayout();
            }
        }
    }

    public final void setScrollRunning(boolean z) {
        this.mIsScrollRunning = z;
    }

    public void setSizeChanged(boolean z) {
        this.mConfig.setSizeChanged(z);
    }

    public void setState(int i2) {
        if (this.mGoToTopState != i2) {
            this.mGoToTopState = i2;
        }
    }

    public void showIfNeeded() {
        if (this.mEnableGoToTop && this.mHost.canScrollUp() && this.mGoToTopState != 2) {
            applyState(1);
            autoHide(1);
        }
    }

    public void updateConfig(SeslGoToTopConfig seslGoToTopConfig) {
        if (seslGoToTopConfig != null) {
            this.mConfig = seslGoToTopConfig;
        }
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (this.mGoToTopImage == drawable) {
            return true;
        }
        return false;
    }
}
