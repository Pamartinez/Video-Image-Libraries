package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.view.animation.Transformation;
import android.widget.CompoundButton;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.text.AllCapsTransformationMethod;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.reflect.view.SeslHapticFeedbackConstantsReflector;
import androidx.reflect.view.SeslViewReflector;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SwitchCompat extends CompoundButton {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final boolean SUPPORT_TOUCH_FEEDBACK = true;
    private static final Property<SwitchCompat, Float> THUMB_POS = new Property<SwitchCompat, Float>(Float.class, "thumbPos") {
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.mThumbPosition);
        }

        public void set(SwitchCompat switchCompat, Float f) {
            switchCompat.setThumbPosition(f.floatValue());
        }
    };
    private CharSequence mAccessibilityTextOff;
    private CharSequence mAccessibilityTextOn;
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private EmojiCompatInitCallback mEmojiCompatInitCallback;
    private boolean mEnforceSwitchWidth;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private PathInterpolator mInterpolator;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    ThumbAnimation mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private final AppCompatTextHelper mTextHelper;
    private CharSequence mTextOff;
    private CharSequence mTextOffTransformed;
    private CharSequence mTextOn;
    private CharSequence mTextOnTransformed;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private PorterDuff.Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private int mTrackMargin;
    private Drawable mTrackOffDrawable;
    private Drawable mTrackOnDrawable;
    private ColorStateList mTrackTintList;
    private PorterDuff.Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmojiCompatInitCallback extends EmojiCompat.InitCallback {
        private final Reference<SwitchCompat> mOuterWeakRef;

        public EmojiCompatInitCallback(SwitchCompat switchCompat) {
            this.mOuterWeakRef = new WeakReference(switchCompat);
        }

        public void onFailed(Throwable th) {
            SwitchCompat switchCompat = this.mOuterWeakRef.get();
            if (switchCompat != null) {
                switchCompat.onEmojiCompatInitializedForSwitchText();
            }
        }

        public void onInitialized() {
            SwitchCompat switchCompat = this.mOuterWeakRef.get();
            if (switchCompat != null) {
                switchCompat.onEmojiCompatInitializedForSwitchText();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ThumbAnimation extends Animation {
        final float mDiff;
        final float mEndPosition;
        final float mStartPosition;

        public ThumbAnimation(float f, float f5) {
            this.mStartPosition = f;
            this.mEndPosition = f5;
            this.mDiff = f5 - f;
        }

        public void applyTransformation(float f, Transformation transformation) {
            SwitchCompat.this.setThumbPosition((this.mDiff * f) + this.mStartPosition);
        }
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    private void animateThumbToCheckedState(final boolean z) {
        float f;
        if (this.mPositionAnimator != null) {
            cancelPositionAnimator();
        }
        float f5 = this.mThumbPosition;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        ThumbAnimation thumbAnimation = new ThumbAnimation(f5, f);
        this.mPositionAnimator = thumbAnimation;
        thumbAnimation.setDuration(150);
        this.mPositionAnimator.setDuration(300);
        this.mPositionAnimator.setInterpolator(this.mInterpolator);
        this.mPositionAnimator.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                float f;
                SwitchCompat switchCompat = SwitchCompat.this;
                if (switchCompat.mPositionAnimator == animation) {
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    switchCompat.setThumbPosition(f);
                    SwitchCompat.this.mPositionAnimator = null;
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        startAnimation(this.mPositionAnimator);
    }

    private void applyThumbTint() {
        Drawable drawable = this.mThumbDrawable;
        if (drawable == null) {
            return;
        }
        if (this.mHasThumbTint || this.mHasThumbTintMode) {
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            this.mThumbDrawable = mutate;
            if (this.mHasThumbTint) {
                DrawableCompat.setTintList(mutate, this.mThumbTintList);
            }
            if (this.mHasThumbTintMode) {
                DrawableCompat.setTintMode(this.mThumbDrawable, this.mThumbTintMode);
            }
            if (this.mThumbDrawable.isStateful()) {
                this.mThumbDrawable.setState(getDrawableState());
            }
        }
    }

    private void applyTrackTint() {
        Drawable drawable = this.mTrackDrawable;
        if (drawable == null) {
            return;
        }
        if (this.mHasTrackTint || this.mHasTrackTintMode) {
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            this.mTrackDrawable = mutate;
            if (this.mHasTrackTint) {
                DrawableCompat.setTintList(mutate, this.mTrackTintList);
            }
            if (this.mHasTrackTintMode) {
                DrawableCompat.setTintMode(this.mTrackDrawable, this.mTrackTintMode);
            }
            if (this.mTrackDrawable.isStateful()) {
                this.mTrackDrawable.setState(getDrawableState());
            }
        }
    }

    private void cancelPositionAnimator() {
        if (this.mPositionAnimator != null) {
            clearAnimation();
            this.mPositionAnimator = null;
        }
    }

    private void cancelSuperTouch(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private static float constrain(float f, float f5, float f8) {
        if (f < f5) {
            return f5;
        }
        if (f > f8) {
            return f8;
        }
        return f;
    }

    private CharSequence doTransformForOnOffText(CharSequence charSequence) {
        TransformationMethod wrapTransformationMethod = getEmojiTextViewHelper().wrapTransformationMethod(this.mSwitchTransformationMethod);
        if (wrapTransformationMethod != null) {
            return wrapTransformationMethod.getTransformation(charSequence, this);
        }
        return charSequence;
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper == null) {
            this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
        }
        return this.mAppCompatEmojiTextHelper;
    }

    private boolean getTargetCheckedState() {
        if (this.mThumbPosition > 0.5f) {
            return true;
        }
        return false;
    }

    private int getThumbOffset() {
        float f;
        if (ViewUtils.isLayoutRtl(this)) {
            f = 1.0f - this.mThumbPosition;
        } else {
            f = this.mThumbPosition;
        }
        return (int) ((f * ((float) getThumbScrollRange())) + 0.5f);
    }

    private int getThumbScrollRange() {
        Rect rect;
        Drawable drawable = this.mTrackDrawable;
        if (drawable == null) {
            return 0;
        }
        Rect rect2 = this.mTempRect;
        drawable.getPadding(rect2);
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable2 != null) {
            rect = DrawableUtils.getOpticalBounds(drawable2);
        } else {
            rect = DrawableUtils.INSETS_NONE;
        }
        return (((((this.mSwitchWidth + this.mTrackMargin) - this.mThumbWidth) - rect2.left) - rect2.right) - rect.left) - rect.right;
    }

    private boolean hitThumb(float f, float f5) {
        if (this.mThumbDrawable == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.mThumbDrawable.getPadding(this.mTempRect);
        int i2 = this.mSwitchTop;
        int i7 = this.mTouchSlop;
        int i8 = i2 - i7;
        int i10 = (this.mSwitchLeft + thumbOffset) - i7;
        Rect rect = this.mTempRect;
        int i11 = this.mThumbWidth + i10 + rect.left + rect.right + i7;
        int i12 = this.mSwitchBottom + i7;
        if (f <= ((float) i10) || f >= ((float) i11) || f5 <= ((float) i8) || f5 >= ((float) i12)) {
            return false;
        }
        return true;
    }

    private Layout makeLayout(CharSequence charSequence) {
        int i2;
        TextPaint textPaint = this.mTextPaint;
        if (charSequence != null) {
            i2 = (int) Math.ceil((double) Layout.getDesiredWidth(charSequence, textPaint));
        } else {
            i2 = 0;
        }
        return new StaticLayout(charSequence, textPaint, i2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private void setOffStateDescriptionOnRAndAbove() {
        CharSequence charSequence = this.mAccessibilityTextOff;
        if (charSequence == null) {
            charSequence = getResources().getString(R$string.abc_capital_off);
        }
        ViewCompat.setStateDescription(this, charSequence);
    }

    private void setOnStateDescriptionOnRAndAbove() {
        CharSequence charSequence = this.mAccessibilityTextOn;
        if (charSequence == null) {
            charSequence = getResources().getString(R$string.abc_capital_on);
        }
        ViewCompat.setStateDescription(this, charSequence);
    }

    private void setSwitchTypefaceByIndex(int i2, int i7) {
        Typeface typeface;
        if (i2 == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (i2 == 2) {
            typeface = Typeface.SERIF;
        } else if (i2 != 3) {
            typeface = null;
        } else {
            typeface = Typeface.MONOSPACE;
        }
        setSwitchTypeface(typeface, i7);
    }

    private void setTextOffInternal(CharSequence charSequence) {
        this.mTextOff = charSequence;
        this.mTextOffTransformed = doTransformForOnOffText(charSequence);
        this.mOffLayout = null;
        if (this.mShowText) {
            setupEmojiCompatLoadCallback();
        }
    }

    private void setTextOnInternal(CharSequence charSequence) {
        this.mTextOn = charSequence;
        this.mTextOnTransformed = doTransformForOnOffText(charSequence);
        this.mOnLayout = null;
        if (this.mShowText) {
            setupEmojiCompatLoadCallback();
        }
    }

    private void setupEmojiCompatLoadCallback() {
        if (this.mEmojiCompatInitCallback == null && this.mAppCompatEmojiTextHelper.isEnabled() && EmojiCompat.isConfigured()) {
            EmojiCompat emojiCompat = EmojiCompat.get();
            int loadState = emojiCompat.getLoadState();
            if (loadState == 3 || loadState == 0) {
                EmojiCompatInitCallback emojiCompatInitCallback = new EmojiCompatInitCallback(this);
                this.mEmojiCompatInitCallback = emojiCompatInitCallback;
                emojiCompat.registerInitCallback(emojiCompatInitCallback);
            }
        }
    }

    private void stopDrag(MotionEvent motionEvent) {
        boolean z;
        boolean z3;
        this.mTouchMode = 0;
        boolean z7 = true;
        if (motionEvent.getAction() != 1 || !isEnabled()) {
            z = false;
        } else {
            z = true;
        }
        boolean isChecked = isChecked();
        if (z) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float xVelocity = this.mVelocityTracker.getXVelocity();
            if (Math.abs(xVelocity) <= 2000.0f && Math.abs(xVelocity) <= 500.0f) {
                float f = this.mThumbPosition;
                if (f == 0.0f || f == 1.0f) {
                    z3 = getTargetCheckedState();
                }
            }
            if (!ViewUtils.isLayoutRtl(this) ? xVelocity <= 0.0f : xVelocity >= 0.0f) {
                z7 = false;
            }
            z3 = z7;
        } else {
            z3 = isChecked;
        }
        if (z3 != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z3);
        cancelSuperTouch(motionEvent);
    }

    public boolean canHapticFeedback(boolean z) {
        if (!SUPPORT_TOUCH_FEEDBACK || z == isChecked() || !hasWindowFocus() || !SeslViewReflector.isVisibleToUser(this) || isTemporarilyDetached()) {
            return false;
        }
        return true;
    }

    public void draw(Canvas canvas) {
        Rect rect;
        int i2;
        int i7;
        Rect rect2 = this.mTempRect;
        int i8 = this.mSwitchLeft;
        int i10 = this.mSwitchTop;
        int i11 = this.mSwitchRight;
        int i12 = this.mSwitchBottom;
        int thumbOffset = getThumbOffset() + i8;
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            rect = DrawableUtils.getOpticalBounds(drawable);
        } else {
            rect = DrawableUtils.INSETS_NONE;
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.getPadding(rect2);
            int i13 = rect2.left;
            thumbOffset += i13;
            int i14 = this.mTrackMargin;
            int i15 = (i14 / 2) + i8;
            int i16 = i11 - (i14 / 2);
            if (rect != null) {
                int i17 = rect.left;
                if (i17 > i13) {
                    i15 += i17 - i13;
                }
                int i18 = rect.top;
                int i19 = rect2.top;
                if (i18 > i19) {
                    i7 = (i18 - i19) + i10;
                } else {
                    i7 = i10;
                }
                int i20 = rect.right;
                int i21 = rect2.right;
                if (i20 > i21) {
                    i16 -= i20 - i21;
                }
                int i22 = rect.bottom;
                int i23 = rect2.bottom;
                if (i22 > i23) {
                    i2 = i12 - (i22 - i23);
                    this.mTrackDrawable.setBounds(i15, i7, i16, i2);
                }
            } else {
                i7 = i10;
            }
            i2 = i12;
            this.mTrackDrawable.setBounds(i15, i7, i16, i2);
        }
        Drawable drawable3 = this.mThumbDrawable;
        if (drawable3 != null) {
            drawable3.getPadding(rect2);
            int i24 = thumbOffset - rect2.left;
            int i25 = thumbOffset + this.mThumbWidth + rect2.right;
            this.mThumbDrawable.setBounds(i24, i10, i25, i12);
            Drawable background = getBackground();
            if (background != null) {
                DrawableCompat.setHotspotBounds(background, i24, i10, i25, i12);
            }
        }
        super.draw(canvas);
    }

    public void drawableHotspotChanged(float f, float f5) {
        super.drawableHotspotChanged(f, f5);
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f5);
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            DrawableCompat.setHotspot(drawable2, f, f5);
        }
    }

    public void drawableStateChanged() {
        boolean z;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mThumbDrawable;
        if (drawable == null || !drawable.isStateful()) {
            z = false;
        } else {
            z = drawable.setState(drawableState);
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    public int getCompoundPaddingLeft() {
        if (!ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.mSwitchWidth + this.mTrackMargin;
        if (!TextUtils.isEmpty(getText())) {
            return compoundPaddingLeft + this.mSwitchPadding;
        }
        return compoundPaddingLeft;
    }

    public int getCompoundPaddingRight() {
        if (ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.mSwitchWidth + this.mTrackMargin;
        if (!TextUtils.isEmpty(getText())) {
            return compoundPaddingRight + this.mSwitchPadding;
        }
        return compoundPaddingRight;
    }

    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    public boolean getShowText() {
        return this.mShowText;
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public final float getThumbPosition() {
        return this.mThumbPosition;
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }

    public void jumpDrawablesToCurrentState() {
        float f;
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        cancelPositionAnimator();
        if (isChecked()) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        setThumbPosition(f);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mSwitchWidth = getResources().getDimensionPixelSize(R$dimen.sesl_switch_width);
        this.mAccessibilityTextOn = getResources().getString(R$string.sesl_switch_on);
        this.mAccessibilityTextOff = getResources().getString(R$string.sesl_switch_off);
    }

    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public void onDraw(Canvas canvas) {
        Layout layout;
        int i2;
        Drawable drawable;
        super.onDraw(canvas);
        Rect rect = this.mTempRect;
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i7 = this.mSwitchTop;
        int i8 = this.mSwitchBottom;
        int i10 = i7 + rect.top;
        int i11 = i8 - rect.bottom;
        Drawable drawable3 = this.mThumbDrawable;
        if (drawable2 != null) {
            if (!this.mSplitTrack || drawable3 == null) {
                if (isChecked()) {
                    drawable = this.mTrackOffDrawable;
                } else {
                    drawable = this.mTrackOnDrawable;
                }
                drawable.setBounds(drawable2.getBounds());
                int i12 = (int) (this.mThumbPosition * 255.0f);
                if (i12 > 255) {
                    i12 = 255;
                } else if (i12 < 0) {
                    i12 = 0;
                }
                int i13 = 255 - i12;
                if (isChecked()) {
                    drawable2.setAlpha(i12);
                    drawable.setAlpha(i13);
                } else {
                    drawable2.setAlpha(i13);
                    drawable.setAlpha(i12);
                }
                drawable2.draw(canvas);
                drawable.draw(canvas);
            } else {
                Rect opticalBounds = DrawableUtils.getOpticalBounds(drawable3);
                drawable3.copyBounds(rect);
                rect.left += opticalBounds.left;
                rect.right -= opticalBounds.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable2.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable3 != null) {
            drawable3.draw(canvas);
        }
        if (getTargetCheckedState()) {
            layout = this.mOnLayout;
        } else {
            layout = this.mOffLayout;
        }
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.mTextColors;
            if (colorStateList != null) {
                this.mTextPaint.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.mTextPaint.drawableState = drawableState;
            if (drawable3 != null) {
                Rect bounds = drawable3.getBounds();
                i2 = bounds.left + bounds.right;
            } else {
                i2 = getWidth();
            }
            canvas.translate((float) ((i2 / 2) - (layout.getWidth() / 2)), (float) (((i10 + i11) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public void onEmojiCompatInitializedForSwitchText() {
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        requestLayout();
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        super.onLayout(z, i2, i7, i8, i10);
        int i16 = 0;
        if (this.mThumbDrawable != null) {
            Rect rect = this.mTempRect;
            Drawable drawable = this.mTrackDrawable;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect opticalBounds = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            i11 = Math.max(0, opticalBounds.left - rect.left);
            i16 = Math.max(0, opticalBounds.right - rect.right);
        } else {
            i11 = 0;
        }
        if (ViewUtils.isLayoutRtl(this)) {
            i13 = getPaddingLeft() + i11;
            i12 = (((this.mSwitchWidth + i13) + this.mTrackMargin) - i11) - i16;
        } else {
            i12 = (getWidth() - getPaddingRight()) - i16;
            i13 = ((i12 - this.mSwitchWidth) - this.mTrackMargin) + i11 + i16;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop = getPaddingTop();
            int i17 = this.mSwitchHeight;
            int height = (((getHeight() + paddingTop) - getPaddingBottom()) / 2) - (i17 / 2);
            int i18 = height;
            i14 = i17 + height;
            i15 = i18;
        } else if (gravity != 80) {
            i15 = getPaddingTop();
            i14 = this.mSwitchHeight + i15;
        } else {
            i14 = getHeight() - getPaddingBottom();
            i15 = i14 - this.mSwitchHeight;
        }
        this.mSwitchLeft = i13;
        this.mSwitchTop = i15;
        this.mSwitchBottom = i14;
        this.mSwitchRight = i12;
    }

    public void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12;
        if (this.mShowText) {
            if (this.mOnLayout == null) {
                this.mOnLayout = makeLayout(this.mTextOnTransformed);
            }
            if (this.mOffLayout == null) {
                this.mOffLayout = makeLayout(this.mTextOffTransformed);
            }
        }
        Rect rect = this.mTempRect;
        Drawable drawable = this.mThumbDrawable;
        int i13 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            i10 = (this.mThumbDrawable.getIntrinsicWidth() - rect.left) - rect.right;
            i8 = this.mThumbDrawable.getIntrinsicHeight();
        } else {
            i10 = 0;
            i8 = 0;
        }
        if (this.mShowText) {
            i11 = (this.mThumbTextPadding * 2) + Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth());
        } else {
            i11 = 0;
        }
        this.mThumbWidth = Math.max(i11, i10);
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i12 = this.mTrackDrawable.getIntrinsicHeight();
        } else {
            rect.setEmpty();
            i12 = 0;
        }
        int i14 = rect.left;
        int i15 = rect.right;
        Drawable drawable3 = this.mThumbDrawable;
        if (drawable3 != null) {
            Rect opticalBounds = DrawableUtils.getOpticalBounds(drawable3);
            Math.max(i14, opticalBounds.left);
            Math.max(i15, opticalBounds.right);
        }
        int max = Math.max(i12, i8);
        this.mSwitchHeight = max;
        int i16 = this.mThumbWidth;
        int i17 = this.mSwitchWidth;
        if (((float) i16) / ((float) i17) > 0.625f) {
            i13 = (int) Math.ceil((double) (((float) i16) - (((float) i17) * 0.625f)));
        }
        this.mTrackMargin = i13;
        super.onMeasure(i2, i7);
        if (getMeasuredHeight() < max) {
            setMeasuredDimension(getMeasuredWidthAndState(), max);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        CharSequence charSequence;
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        if (isChecked()) {
            charSequence = this.mAccessibilityTextOn;
        } else {
            charSequence = this.mAccessibilityTextOff;
        }
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if (r0 != 3) goto L_0x00b7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            android.view.VelocityTracker r0 = r6.mVelocityTracker
            r0.addMovement(r7)
            int r0 = r7.getActionMasked()
            r1 = 1
            if (r0 == 0) goto L_0x009d
            r2 = 2
            if (r0 == r1) goto L_0x0089
            if (r0 == r2) goto L_0x0016
            r3 = 3
            if (r0 == r3) goto L_0x0089
            goto L_0x00b7
        L_0x0016:
            int r0 = r6.mTouchMode
            if (r0 == r1) goto L_0x0055
            if (r0 == r2) goto L_0x001e
            goto L_0x00b7
        L_0x001e:
            float r7 = r7.getX()
            int r0 = r6.getThumbScrollRange()
            float r2 = r6.mTouchX
            float r2 = r7 - r2
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            if (r0 == 0) goto L_0x0032
            float r0 = (float) r0
            float r2 = r2 / r0
            goto L_0x003b
        L_0x0032:
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0038
            r2 = r3
            goto L_0x003b
        L_0x0038:
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r2 = r0
        L_0x003b:
            boolean r0 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r6)
            if (r0 == 0) goto L_0x0042
            float r2 = -r2
        L_0x0042:
            float r0 = r6.mThumbPosition
            float r0 = r0 + r2
            float r0 = constrain(r0, r4, r3)
            float r2 = r6.mThumbPosition
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0054
            r6.mTouchX = r7
            r6.setThumbPosition(r0)
        L_0x0054:
            return r1
        L_0x0055:
            float r0 = r7.getX()
            float r3 = r7.getY()
            float r4 = r6.mTouchX
            float r4 = r0 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.mTouchSlop
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x007b
            float r4 = r6.mTouchY
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.mTouchSlop
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x00b7
        L_0x007b:
            r6.mTouchMode = r2
            android.view.ViewParent r7 = r6.getParent()
            r7.requestDisallowInterceptTouchEvent(r1)
            r6.mTouchX = r0
            r6.mTouchY = r3
            return r1
        L_0x0089:
            int r0 = r6.mTouchMode
            if (r0 != r2) goto L_0x0094
            r6.stopDrag(r7)
            super.onTouchEvent(r7)
            return r1
        L_0x0094:
            r0 = 0
            r6.mTouchMode = r0
            android.view.VelocityTracker r0 = r6.mVelocityTracker
            r0.clear()
            goto L_0x00b7
        L_0x009d:
            float r0 = r7.getX()
            float r2 = r7.getY()
            boolean r3 = r6.isEnabled()
            if (r3 == 0) goto L_0x00b7
            boolean r3 = r6.hitThumb(r0, r2)
            if (r3 == 0) goto L_0x00b7
            r6.mTouchMode = r1
            r6.mTouchX = r0
            r6.mTouchY = r2
        L_0x00b7:
            boolean r6 = super.onTouchEvent(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().setAllCaps(z);
    }

    public void setChecked(boolean z) {
        float f;
        if (canHapticFeedback(z)) {
            performHapticFeedback(SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(27));
        }
        super.setChecked(z);
        boolean isChecked = isChecked();
        if (isChecked) {
            setOnStateDescriptionOnRAndAbove();
        } else {
            setOffStateDescriptionOnRAndAbove();
        }
        if (getWindowToken() == null || !isLaidOut()) {
            cancelPositionAnimator();
            if (isChecked) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            setThumbPosition(f);
            return;
        }
        animateThumbToCheckedState(isChecked);
    }

    public void setCheckedWithoutAnimation(boolean z) {
        float f;
        super.setChecked(z);
        boolean isChecked = isChecked();
        if (isChecked) {
            setOnStateDescriptionOnRAndAbove();
        } else {
            setOffStateDescriptionOnRAndAbove();
        }
        cancelPositionAnimator();
        if (isChecked) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        setThumbPosition(f);
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().setEnabled(z);
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        requestLayout();
    }

    public final void setEnforceSwitchWidth(boolean z) {
        this.mEnforceSwitchWidth = z;
        invalidate();
    }

    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().getFilters(inputFilterArr));
    }

    public void setShowText(boolean z) {
        if (this.mShowText != z) {
            this.mShowText = z;
            requestLayout();
            if (z) {
                setupEmojiCompatLoadCallback();
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.mSplitTrack = z;
        invalidate();
    }

    public void setSwitchMinWidth(int i2) {
        this.mSwitchMinWidth = i2;
        requestLayout();
    }

    public void setSwitchPadding(int i2) {
        this.mSwitchPadding = i2;
        requestLayout();
    }

    public void setSwitchTextAppearance(Context context, int i2) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i2, R$styleable.TextAppearance);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R$styleable.TextAppearance_android_textColor);
        if (colorStateList != null) {
            this.mTextColors = colorStateList;
        } else {
            this.mTextColors = getTextColors();
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, 0);
        if (dimensionPixelSize != 0) {
            float f = (float) dimensionPixelSize;
            if (f != this.mTextPaint.getTextSize()) {
                this.mTextPaint.setTextSize(f);
                requestLayout();
            }
        }
        setSwitchTypefaceByIndex(obtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, -1), obtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, -1));
        if (obtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false)) {
            this.mSwitchTransformationMethod = new AllCapsTransformationMethod(getContext());
        } else {
            this.mSwitchTransformationMethod = null;
        }
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        obtainStyledAttributes.recycle();
    }

    public void setSwitchTypeface(Typeface typeface, int i2) {
        Typeface typeface2;
        float f = 0.0f;
        boolean z = false;
        if (i2 > 0) {
            if (typeface == null) {
                typeface2 = Typeface.defaultFromStyle(i2);
            } else {
                typeface2 = Typeface.create(typeface, i2);
            }
            setSwitchTypeface(typeface2);
            int i7 = (~(typeface2 != null ? typeface2.getStyle() : 0)) & i2;
            TextPaint textPaint = this.mTextPaint;
            if ((i7 & 1) != 0) {
                z = true;
            }
            textPaint.setFakeBoldText(z);
            TextPaint textPaint2 = this.mTextPaint;
            if ((i7 & 2) != 0) {
                f = -0.25f;
            }
            textPaint2.setTextSkewX(f);
            return;
        }
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public void setTextOff(CharSequence charSequence) {
        setTextOffInternal(charSequence);
        requestLayout();
        if (!isChecked()) {
            setOffStateDescriptionOnRAndAbove();
        }
    }

    public void setTextOn(CharSequence charSequence) {
        setTextOnInternal(charSequence);
        requestLayout();
        if (isChecked()) {
            setOnStateDescriptionOnRAndAbove();
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.mThumbDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f) {
        this.mThumbPosition = f;
        invalidate();
    }

    public void setThumbResource(int i2) {
        setThumbDrawable(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setThumbTextPadding(int i2) {
        this.mThumbTextPadding = i2;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.mThumbTintList = colorStateList;
        this.mHasThumbTint = true;
        applyThumbTint();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.mThumbTintMode = mode;
        this.mHasThumbTintMode = true;
        applyThumbTint();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.mTrackDrawable = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                this.mTrackOnDrawable = constantState.newDrawable();
                this.mTrackOffDrawable = constantState.newDrawable();
            } else {
                this.mTrackOnDrawable = drawable;
                this.mTrackOffDrawable = drawable;
            }
            this.mTrackOnDrawable.setState(new int[]{16842910, 16842912});
            this.mTrackOffDrawable.setState(new int[]{16842910, -16842912});
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i2) {
        setTrackDrawable(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.mTrackTintList = colorStateList;
        this.mHasTrackTint = true;
        applyTrackTint();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.mTrackTintMode = mode;
        this.mHasTrackTintMode = true;
        applyTrackTint();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.mThumbDrawable || drawable == this.mTrackDrawable) {
            return true;
        }
        return false;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mTrackTintList = null;
        this.mTrackTintMode = null;
        this.mHasTrackTint = false;
        this.mHasTrackTintMode = false;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mEnforceSwitchWidth = true;
        this.mTempRect = new Rect();
        this.mTrackMargin = 0;
        ThemeUtils.checkAppCompatTheme(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.mTextPaint = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        int i7 = Settings.System.getString(context.getContentResolver(), "current_sec_active_themepackage") != null ? R$attr.themeSwitchStyle : i2;
        int[] iArr = R$styleable.SwitchCompat;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i7, 0);
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        ViewCompat.saveAttributeDataForStyleable(this, context2, iArr, attributeSet2, obtainStyledAttributes.getWrappedTypeArray(), i7, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.SwitchCompat_android_thumb);
        this.mThumbDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R$styleable.SwitchCompat_track);
        this.mTrackDrawable = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback(this);
            Drawable.ConstantState constantState = this.mTrackDrawable.getConstantState();
            if (constantState != null) {
                this.mTrackOnDrawable = constantState.newDrawable();
                this.mTrackOffDrawable = constantState.newDrawable();
            } else {
                Drawable drawable3 = this.mTrackDrawable;
                this.mTrackOnDrawable = drawable3;
                this.mTrackOffDrawable = drawable3;
            }
            this.mTrackOnDrawable.setState(new int[]{16842910, 16842912});
            this.mTrackOffDrawable.setState(new int[]{16842910, -16842912});
        }
        setTextOnInternal(obtainStyledAttributes.getText(R$styleable.SwitchCompat_android_textOn));
        setTextOffInternal(obtainStyledAttributes.getText(R$styleable.SwitchCompat_android_textOff));
        this.mShowText = obtainStyledAttributes.getBoolean(R$styleable.SwitchCompat_showText, true);
        this.mThumbTextPadding = obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchCompat_thumbTextPadding, 0);
        this.mSwitchMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchCompat_switchMinWidth, 0);
        this.mSwitchPadding = obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchCompat_switchPadding, 0);
        this.mSplitTrack = obtainStyledAttributes.getBoolean(R$styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R$styleable.SwitchCompat_thumbTint);
        if (colorStateList != null) {
            this.mThumbTintList = colorStateList;
            this.mHasThumbTint = true;
        }
        PorterDuff.Mode parseTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R$styleable.SwitchCompat_thumbTintMode, -1), (PorterDuff.Mode) null);
        if (this.mThumbTintMode != parseTintMode) {
            this.mThumbTintMode = parseTintMode;
            this.mHasThumbTintMode = true;
        }
        if (this.mHasThumbTint || this.mHasThumbTintMode) {
            applyThumbTint();
        }
        ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(R$styleable.SwitchCompat_trackTint);
        if (colorStateList2 != null) {
            this.mTrackTintList = colorStateList2;
            this.mHasTrackTint = true;
        }
        PorterDuff.Mode parseTintMode2 = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R$styleable.SwitchCompat_trackTintMode, -1), (PorterDuff.Mode) null);
        if (this.mTrackTintMode != parseTintMode2) {
            this.mTrackTintMode = parseTintMode2;
            this.mHasTrackTintMode = true;
        }
        if (this.mHasTrackTint || this.mHasTrackTintMode) {
            applyTrackTint();
        }
        int resourceId = obtainStyledAttributes.getResourceId(R$styleable.SwitchCompat_switchTextAppearance, 0);
        if (resourceId != 0) {
            setSwitchTextAppearance(context2, resourceId);
        }
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.loadFromAttributes(attributeSet2, i2);
        obtainStyledAttributes.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context2);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        getEmojiTextViewHelper().loadFromAttributes(attributeSet2, i2);
        this.mSwitchWidth = getResources().getDimensionPixelSize(R$dimen.sesl_switch_width);
        this.mAccessibilityTextOn = getResources().getString(R$string.sesl_switch_on);
        this.mAccessibilityTextOff = getResources().getString(R$string.sesl_switch_off);
        this.mInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.mTextPaint.getTypeface() != null && !this.mTextPaint.getTypeface().equals(typeface)) || (this.mTextPaint.getTypeface() == null && typeface != null)) {
            this.mTextPaint.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }
}
