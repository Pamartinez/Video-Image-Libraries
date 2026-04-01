package androidx.core.view.insets;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.Insets;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Protection {
    private static final Interpolator DEFAULT_INTERPOLATOR_FADE_IN = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
    private static final Interpolator DEFAULT_INTERPOLATOR_FADE_OUT = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
    private static final Interpolator DEFAULT_INTERPOLATOR_MOVE_IN = new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f);
    private static final Interpolator DEFAULT_INTERPOLATOR_MOVE_OUT = new PathInterpolator(0.6f, 0.0f, 1.0f, 1.0f);
    private final Attributes mAttributes = new Attributes();
    private Object mController;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;
    private final int mSide;
    private float mSystemAlpha;
    private float mSystemInsetAmount;
    private float mUserAlpha;
    private ValueAnimator mUserAlphaAnimator;
    private float mUserInsetAmount;
    private ValueAnimator mUserInsetAmountAnimator;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Attributes {
        private float mAlpha = 1.0f;
        private Callback mCallback;
        private Drawable mDrawable = null;
        /* access modifiers changed from: private */
        public int mHeight = -1;
        private Insets mMargin = Insets.NONE;
        private float mTranslationX = 0.0f;
        private float mTranslationY = 0.0f;
        private boolean mVisible = false;
        /* access modifiers changed from: private */
        public int mWidth = -1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface Callback {
            void onAlphaChanged(float f);

            void onDrawableChanged(Drawable drawable);

            void onHeightChanged(int i2);

            void onMarginChanged(Insets insets);

            void onTranslationXChanged(float f);

            void onTranslationYChanged(float f);

            void onVisibilityChanged(boolean z);

            void onWidthChanged(int i2);
        }

        /* access modifiers changed from: private */
        public void setAlpha(float f) {
            if (this.mAlpha != f) {
                this.mAlpha = f;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onAlphaChanged(f);
                }
            }
        }

        /* access modifiers changed from: private */
        public void setDrawable(Drawable drawable) {
            this.mDrawable = drawable;
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onDrawableChanged(drawable);
            }
        }

        /* access modifiers changed from: private */
        public void setHeight(int i2) {
            if (this.mHeight != i2) {
                this.mHeight = i2;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onHeightChanged(i2);
                }
            }
        }

        /* access modifiers changed from: private */
        public void setMargin(Insets insets) {
            if (!this.mMargin.equals(insets)) {
                this.mMargin = insets;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onMarginChanged(insets);
                }
            }
        }

        /* access modifiers changed from: private */
        public void setTranslationX(float f) {
            if (this.mTranslationX != f) {
                this.mTranslationX = f;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onTranslationXChanged(f);
                }
            }
        }

        /* access modifiers changed from: private */
        public void setTranslationY(float f) {
            if (this.mTranslationY != f) {
                this.mTranslationY = f;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onTranslationYChanged(f);
                }
            }
        }

        /* access modifiers changed from: private */
        public void setVisible(boolean z) {
            if (this.mVisible != z) {
                this.mVisible = z;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onVisibilityChanged(z);
                }
            }
        }

        /* access modifiers changed from: private */
        public void setWidth(int i2) {
            if (this.mWidth != i2) {
                this.mWidth = i2;
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.onWidthChanged(i2);
                }
            }
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public Insets getMargin() {
            return this.mMargin;
        }

        public float getTranslationX() {
            return this.mTranslationX;
        }

        public float getTranslationY() {
            return this.mTranslationY;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public boolean isVisible() {
            return this.mVisible;
        }

        public void setCallback(Callback callback) {
            if (this.mCallback == null || callback == null) {
                this.mCallback = callback;
                return;
            }
            throw new IllegalStateException("Trying to overwrite the existing callback. Did you send one protection to multiple ProtectionLayouts?");
        }
    }

    public Protection(int i2) {
        Insets insets = Insets.NONE;
        this.mInsets = insets;
        this.mInsetsIgnoringVisibility = insets;
        this.mSystemAlpha = 1.0f;
        this.mUserAlpha = 1.0f;
        this.mSystemInsetAmount = 1.0f;
        this.mUserInsetAmount = 1.0f;
        this.mController = null;
        this.mUserAlphaAnimator = null;
        this.mUserInsetAmountAnimator = null;
        if (i2 == 1 || i2 == 2 || i2 == 4 || i2 == 8) {
            this.mSide = i2;
            return;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Unexpected side: "));
    }

    private void updateAlpha() {
        this.mAttributes.setAlpha(this.mSystemAlpha * this.mUserAlpha);
    }

    private void updateInsetAmount() {
        float f = this.mUserInsetAmount * this.mSystemInsetAmount;
        int i2 = this.mSide;
        if (i2 == 1) {
            Attributes attributes = this.mAttributes;
            attributes.setTranslationX((-(1.0f - f)) * ((float) attributes.mWidth));
        } else if (i2 == 2) {
            Attributes attributes2 = this.mAttributes;
            attributes2.setTranslationY((-(1.0f - f)) * ((float) attributes2.mHeight));
        } else if (i2 == 4) {
            Attributes attributes3 = this.mAttributes;
            attributes3.setTranslationX((1.0f - f) * ((float) attributes3.mWidth));
        } else if (i2 == 8) {
            Attributes attributes4 = this.mAttributes;
            attributes4.setTranslationY((1.0f - f) * ((float) attributes4.mHeight));
        }
    }

    public abstract void dispatchColorHint(int i2);

    public Insets dispatchInsets(Insets insets, Insets insets2, Insets insets3) {
        this.mInsets = insets;
        this.mInsetsIgnoringVisibility = insets2;
        this.mAttributes.setMargin(insets3);
        return updateLayout();
    }

    public Attributes getAttributes() {
        return this.mAttributes;
    }

    public Object getController() {
        return this.mController;
    }

    public int getSide() {
        return this.mSide;
    }

    public abstract int getThickness(int i2);

    public boolean occupiesCorners() {
        return false;
    }

    public void setController(Object obj) {
        this.mController = obj;
    }

    public void setDrawable(Drawable drawable) {
        this.mAttributes.setDrawable(drawable);
    }

    public void setSystemAlpha(float f) {
        this.mSystemAlpha = f;
        updateAlpha();
    }

    public void setSystemInsetAmount(float f) {
        this.mSystemInsetAmount = f;
        updateInsetAmount();
    }

    public void setSystemVisible(boolean z) {
        this.mAttributes.setVisible(z);
    }

    public Insets updateLayout() {
        int i2;
        float f;
        Insets insets = Insets.NONE;
        int i7 = this.mSide;
        boolean z = false;
        if (i7 == 1) {
            i2 = this.mInsets.left;
            this.mAttributes.setWidth(getThickness(this.mInsetsIgnoringVisibility.left));
            if (occupiesCorners()) {
                insets = Insets.of(getThickness(i2), 0, 0, 0);
            }
        } else if (i7 == 2) {
            i2 = this.mInsets.top;
            this.mAttributes.setHeight(getThickness(this.mInsetsIgnoringVisibility.top));
            if (occupiesCorners()) {
                insets = Insets.of(0, getThickness(i2), 0, 0);
            }
        } else if (i7 == 4) {
            i2 = this.mInsets.right;
            this.mAttributes.setWidth(getThickness(this.mInsetsIgnoringVisibility.right));
            if (occupiesCorners()) {
                insets = Insets.of(0, 0, getThickness(i2), 0);
            }
        } else if (i7 != 8) {
            i2 = 0;
        } else {
            i2 = this.mInsets.bottom;
            this.mAttributes.setHeight(getThickness(this.mInsetsIgnoringVisibility.bottom));
            if (occupiesCorners()) {
                insets = Insets.of(0, 0, 0, getThickness(i2));
            }
        }
        if (i2 > 0) {
            z = true;
        }
        setSystemVisible(z);
        float f5 = 0.0f;
        if (i2 > 0) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        setSystemAlpha(f);
        if (i2 > 0) {
            f5 = 1.0f;
        }
        setSystemInsetAmount(f5);
        return insets;
    }
}
