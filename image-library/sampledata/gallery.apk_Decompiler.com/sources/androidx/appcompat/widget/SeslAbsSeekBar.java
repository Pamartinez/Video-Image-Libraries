package androidx.appcompat.widget;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AbsSeekBar;
import androidx.appcompat.R$bool;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$styleable;
import androidx.appcompat.animation.SeslAnimationUtils;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import androidx.appcompat.util.SeslMisc;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.reflect.view.SeslHapticFeedbackConstantsReflector;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslHoverPopupWindowReflector;
import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslAbsSeekBar extends SeslProgressBar {
    private boolean mAllowedSeekBarAnimation = false;
    /* access modifiers changed from: private */
    public int mCurrentProgressLevel;
    private ColorStateList mDefaultActivatedProgressColor;
    private ColorStateList mDefaultActivatedThumbColor;
    private ColorStateList mDefaultNormalProgressColor;
    private ColorStateList mDefaultSecondaryProgressColor;
    private float mDisabledAlpha;
    private Drawable mDivider;
    private final List<Rect> mGestureExclusionRects = new ArrayList();
    private boolean mHasThumbTint = false;
    private boolean mHasThumbTintMode = false;
    private boolean mHasTickMarkTint = false;
    private boolean mHasTickMarkTintMode = false;
    private int mHoveringLevel = 0;
    private boolean mIsDragging;
    private boolean mIsDraggingForSliding = false;
    private boolean mIsFirstSetProgress = false;
    private boolean mIsHapticEnabled;
    private boolean mIsLightTheme;
    boolean mIsSeamless = false;
    private boolean mIsSetModeCalled = false;
    private boolean mIsTouchDisabled = false;
    boolean mIsUserSeekable = true;
    private int mKeyProgressIncrement = 1;
    private boolean mLargeFont = false;
    private Drawable mLevelBarThumbDrawable;
    private float mLevelDrawPadding = 0.0f;
    private int mModeExpandThumbRadius;
    private int mModeExpandTrackMaxWidth;
    private int mModeExpandTrackMinWidth;
    private AnimatorSet mMuteAnimationSet;
    private ColorStateList mOverlapActivatedProgressColor;
    private Drawable mOverlapBackground;
    private ColorStateList mOverlapNormalProgressColor;
    private int mOverlapPoint = -1;
    private int mPreviousHoverPopupType = 0;
    private int mScaledTouchSlop;
    private boolean mSetDualColorMode = false;
    private Drawable mSplitProgress;
    private boolean mSplitTrack;
    private final Rect mTempRect = new Rect();
    private Drawable mThumb;
    private int mThumbOffset;
    /* access modifiers changed from: private */
    public int mThumbPosX;
    private int mThumbRadius;
    private final Rect mThumbRect = new Rect();
    private ColorStateList mThumbTintList = null;
    private PorterDuff.Mode mThumbTintMode = null;
    private Drawable mTickMark;
    private ColorStateList mTickMarkTintList = null;
    private PorterDuff.Mode mTickMarkTintMode = null;
    private float mTouchDownX;
    private float mTouchDownY;
    float mTouchProgressOffset;
    private int mTrackMaxWidth;
    private int mTrackMinWidth;
    private boolean mUseMuteAnimation = false;
    private List<Rect> mUserGestureExclusionRects = Collections.EMPTY_LIST;
    private ValueAnimator mValueAnimator;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SliderDrawable extends Drawable {
        int mAlpha;
        int mColor;
        ColorStateList mColorStateList;
        private boolean mIsStateChanged;
        private boolean mIsVertical;
        private final Paint mPaint;
        ValueAnimator mPressedAnimator;
        private float mRadius;
        ValueAnimator mReleasedAnimator;
        private final float mSliderMaxWidth;
        private final float mSliderMinWidth;
        private final SliderState mState;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public class SliderState extends Drawable.ConstantState {
            private SliderState() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return SliderDrawable.this;
            }
        }

        public SliderDrawable(SeslAbsSeekBar seslAbsSeekBar, float f, float f5, ColorStateList colorStateList) {
            this(f, f5, colorStateList, false);
        }

        private void initAnimator() {
            float f = this.mSliderMinWidth;
            float f5 = this.mSliderMaxWidth;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f5});
            this.mPressedAnimator = ofFloat;
            ofFloat.setDuration(250);
            ValueAnimator valueAnimator = this.mPressedAnimator;
            Interpolator interpolator = SeslAnimationUtils.SINE_IN_OUT_80;
            valueAnimator.setInterpolator(interpolator);
            this.mPressedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SliderDrawable.this.invalidateTrack(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{f5, f});
            this.mReleasedAnimator = ofFloat2;
            ofFloat2.setDuration(250);
            this.mReleasedAnimator.setInterpolator(interpolator);
            this.mReleasedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SliderDrawable.this.invalidateTrack(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
        }

        private int modulateAlpha(int i2, int i7) {
            return ((i7 + (i7 >>> 7)) * i2) >>> 8;
        }

        private void startPressedAnimation() {
            if (!this.mPressedAnimator.isRunning()) {
                if (this.mReleasedAnimator.isRunning()) {
                    this.mReleasedAnimator.cancel();
                }
                this.mPressedAnimator.setFloatValues(new float[]{this.mSliderMinWidth, this.mSliderMaxWidth});
                this.mPressedAnimator.start();
            }
        }

        private void startReleasedAnimation() {
            if (!this.mReleasedAnimator.isRunning()) {
                if (this.mPressedAnimator.isRunning()) {
                    this.mPressedAnimator.cancel();
                }
                this.mReleasedAnimator.setFloatValues(new float[]{this.mSliderMaxWidth, this.mSliderMinWidth});
                this.mReleasedAnimator.start();
            }
        }

        private void startSliderAnimation(boolean z) {
            if (this.mIsStateChanged != z) {
                if (z) {
                    startPressedAnimation();
                } else {
                    startReleasedAnimation();
                }
                this.mIsStateChanged = z;
            }
        }

        public void draw(Canvas canvas) {
            int alpha = this.mPaint.getAlpha();
            this.mPaint.setAlpha(modulateAlpha(alpha, this.mAlpha));
            canvas.save();
            if (!this.mIsVertical) {
                float f = this.mRadius;
                Canvas canvas2 = canvas;
                canvas2.drawLine(f, ((float) SeslAbsSeekBar.this.getHeight()) / 2.0f, ((float) ((SeslAbsSeekBar.this.getWidth() - SeslAbsSeekBar.this.getPaddingLeft()) - SeslAbsSeekBar.this.getPaddingRight())) - f, ((float) SeslAbsSeekBar.this.getHeight()) / 2.0f, this.mPaint);
            } else {
                float f5 = this.mRadius;
                float width = ((float) ((SeslAbsSeekBar.this.getWidth() - SeslAbsSeekBar.this.getPaddingLeft()) - SeslAbsSeekBar.this.getPaddingRight())) / 2.0f;
                Canvas canvas3 = canvas;
                canvas3.drawLine(width, ((float) ((SeslAbsSeekBar.this.getHeight() - SeslAbsSeekBar.this.getPaddingTop()) - SeslAbsSeekBar.this.getPaddingBottom())) - f5, width, f5, this.mPaint);
            }
            canvas.restore();
            this.mPaint.setAlpha(alpha);
        }

        public Drawable.ConstantState getConstantState() {
            return this.mState;
        }

        public int getIntrinsicHeight() {
            return (int) this.mSliderMaxWidth;
        }

        public int getIntrinsicWidth() {
            return (int) this.mSliderMaxWidth;
        }

        public int getOpacity() {
            Paint paint = this.mPaint;
            if (paint.getXfermode() != null) {
                return -3;
            }
            int alpha = paint.getAlpha();
            if (alpha == 0) {
                return -2;
            }
            if (alpha == 255) {
                return -1;
            }
            return -3;
        }

        public void invalidateTrack(float f) {
            setStrokeWidth(f);
            invalidateSelf();
        }

        public boolean isStateful() {
            return true;
        }

        public boolean onStateChange(int[] iArr) {
            boolean onStateChange = super.onStateChange(iArr);
            int colorForState = this.mColorStateList.getColorForState(iArr, this.mColor);
            if (this.mColor != colorForState) {
                this.mColor = colorForState;
                this.mPaint.setColor(colorForState);
                invalidateSelf();
            }
            boolean z = false;
            boolean z3 = false;
            boolean z7 = false;
            for (int i2 : iArr) {
                if (i2 == 16842910) {
                    z3 = true;
                } else if (i2 == 16842919) {
                    z7 = true;
                }
            }
            if (z3 && z7) {
                z = true;
            }
            startSliderAnimation(z);
            return onStateChange;
        }

        public void setAlpha(int i2) {
            this.mAlpha = i2;
            invalidateSelf();
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        public void setStrokeWidth(float f) {
            this.mPaint.setStrokeWidth(f);
            this.mRadius = f / 2.0f;
        }

        public void setTintList(ColorStateList colorStateList) {
            super.setTintList(colorStateList);
            if (colorStateList != null) {
                this.mColorStateList = colorStateList;
                int defaultColor = colorStateList.getDefaultColor();
                this.mColor = defaultColor;
                this.mPaint.setColor(defaultColor);
                invalidateSelf();
            }
        }

        public SliderDrawable(float f, float f5, ColorStateList colorStateList, boolean z) {
            Paint paint = new Paint();
            this.mPaint = paint;
            this.mIsStateChanged = false;
            this.mAlpha = ScoverState.TYPE_NFC_SMART_COVER;
            this.mState = new SliderState();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            this.mColorStateList = colorStateList;
            int defaultColor = colorStateList.getDefaultColor();
            this.mColor = defaultColor;
            paint.setColor(defaultColor);
            paint.setStrokeWidth(f);
            this.mSliderMinWidth = f;
            this.mSliderMaxWidth = f5;
            this.mRadius = f / 2.0f;
            this.mIsVertical = z;
            initAnimator();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ThumbDrawable extends Drawable {
        private int mAlpha = ScoverState.TYPE_NFC_SMART_COVER;
        int mColor;
        private ColorStateList mColorStateList;
        private boolean mIsStateChanged = false;
        private boolean mIsVertical = false;
        private final Paint mPaint;
        private final Paint mPaintInner;
        private final int mRadius;
        private int mRadiusForAni;
        private final int mStrokeWidth;
        private ValueAnimator mThumbPressed;
        private ValueAnimator mThumbReleased;

        public ThumbDrawable(int i2, ColorStateList colorStateList, boolean z) {
            Paint paint = new Paint(1);
            this.mPaint = paint;
            Paint paint2 = new Paint(1);
            this.mPaintInner = paint2;
            this.mStrokeWidth = SeslAbsSeekBar.this.getContext().getResources().getDimensionPixelSize(R$dimen.sesl_seekbar_thumb_stroke);
            this.mRadiusForAni = i2;
            this.mRadius = i2;
            this.mColorStateList = colorStateList;
            this.mColor = colorStateList.getDefaultColor();
            Paint.Style style = Paint.Style.FILL;
            paint.setStyle(style);
            paint2.setStyle(style);
            paint.setColor(this.mColor);
            paint2.setColor(SeslAbsSeekBar.this.getContext().getResources().getColor(R$color.sesl_thumb_control_fill_color_activated));
            this.mIsVertical = z;
            initAnimation();
        }

        private int modulateAlpha(int i2, int i7) {
            return ((i7 + (i7 >>> 7)) * i2) >>> 8;
        }

        /* access modifiers changed from: private */
        public void setRadius(int i2) {
            this.mRadiusForAni = i2;
        }

        private void startPressedAnimation() {
            if (!this.mThumbPressed.isRunning()) {
                if (this.mThumbReleased.isRunning()) {
                    this.mThumbReleased.cancel();
                }
                this.mThumbPressed.start();
            }
        }

        private void startReleasedAnimation() {
            if (!this.mThumbReleased.isRunning()) {
                if (this.mThumbPressed.isRunning()) {
                    this.mThumbPressed.cancel();
                }
                this.mThumbReleased.start();
            }
        }

        private void startThumbAnimation(boolean z) {
            if (this.mIsStateChanged != z) {
                if (z) {
                    startPressedAnimation();
                } else {
                    startReleasedAnimation();
                }
                this.mIsStateChanged = z;
            }
        }

        public void draw(Canvas canvas) {
            int alpha = this.mPaint.getAlpha();
            this.mPaint.setAlpha(modulateAlpha(alpha, this.mAlpha));
            this.mPaintInner.setAlpha(modulateAlpha(alpha, this.mAlpha));
            canvas.save();
            if (!this.mIsVertical) {
                canvas.drawCircle((float) SeslAbsSeekBar.this.mThumbPosX, ((float) SeslAbsSeekBar.this.getHeight()) / 2.0f, (float) this.mRadiusForAni, this.mPaint);
                canvas.drawCircle((float) SeslAbsSeekBar.this.mThumbPosX, ((float) SeslAbsSeekBar.this.getHeight()) / 2.0f, (float) (this.mRadiusForAni - this.mStrokeWidth), this.mPaintInner);
            } else {
                float width = ((float) ((SeslAbsSeekBar.this.getWidth() - SeslAbsSeekBar.this.getPaddingLeft()) - SeslAbsSeekBar.this.getPaddingRight())) / 2.0f;
                canvas.drawCircle(width, (float) (SeslAbsSeekBar.this.mThumbPosX - SeslAbsSeekBar.this.getPaddingLeft()), (float) this.mRadiusForAni, this.mPaint);
                canvas.drawCircle(width, (float) (SeslAbsSeekBar.this.mThumbPosX - SeslAbsSeekBar.this.getPaddingLeft()), (float) (this.mRadiusForAni - this.mStrokeWidth), this.mPaintInner);
            }
            canvas.restore();
            this.mPaint.setAlpha(alpha);
            this.mPaintInner.setAlpha(alpha);
        }

        public int getIntrinsicHeight() {
            return this.mRadius * 2;
        }

        public int getIntrinsicWidth() {
            return this.mRadius * 2;
        }

        public int getOpacity() {
            Paint paint = this.mPaint;
            if (paint.getXfermode() != null) {
                return -3;
            }
            int alpha = paint.getAlpha();
            if (alpha == 0) {
                return -2;
            }
            if (alpha == 255) {
                return -1;
            }
            return -3;
        }

        public void initAnimation() {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) this.mRadius, 0.0f});
            this.mThumbPressed = ofFloat;
            ofFloat.setDuration(100);
            this.mThumbPressed.setInterpolator(new LinearInterpolator());
            this.mThumbPressed.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ThumbDrawable.this.setRadius((int) ((Float) valueAnimator.getAnimatedValue()).floatValue());
                    ThumbDrawable.this.invalidateSelf();
                }
            });
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, (float) this.mRadius});
            this.mThumbReleased = ofFloat2;
            ofFloat2.setDuration(300);
            this.mThumbReleased.setInterpolator(SeslAnimationUtils.SINE_IN_OUT_90);
            this.mThumbReleased.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ThumbDrawable.this.setRadius((int) ((Float) valueAnimator.getAnimatedValue()).floatValue());
                    ThumbDrawable.this.invalidateSelf();
                }
            });
        }

        public boolean isStateful() {
            return true;
        }

        public boolean onStateChange(int[] iArr) {
            boolean onStateChange = super.onStateChange(iArr);
            int colorForState = this.mColorStateList.getColorForState(iArr, this.mColor);
            if (this.mColor != colorForState) {
                this.mColor = colorForState;
                this.mPaint.setColor(colorForState);
                invalidateSelf();
            }
            boolean z = false;
            boolean z3 = false;
            boolean z7 = false;
            for (int i2 : iArr) {
                if (i2 == 16842910) {
                    z3 = true;
                } else if (i2 == 16842919) {
                    z7 = true;
                }
            }
            if (z3 && z7) {
                z = true;
            }
            startThumbAnimation(z);
            return onStateChange;
        }

        public void setAlpha(int i2) {
            this.mAlpha = i2;
            invalidateSelf();
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        public void setTintList(ColorStateList colorStateList) {
            super.setTintList(colorStateList);
            if (colorStateList != null) {
                this.mColorStateList = colorStateList;
                int colorForState = colorStateList.getColorForState(SeslAbsSeekBar.this.getDrawableState(), this.mColor);
                this.mColor = colorForState;
                this.mPaint.setColor(colorForState);
                invalidateSelf();
            }
        }
    }

    public SeslAbsSeekBar(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        int i8;
        int i10;
        int i11;
        int i12;
        TypedArray obtainStyledAttributes;
        int[] iArr = R$styleable.AppCompatSeekBar;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, i2, i7);
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        try {
            saveAttributeDataForStyleable(context2, iArr, attributeSet2, obtainStyledAttributes2, i2, i7);
            Resources resources = context2.getResources();
            setThumb(obtainStyledAttributes2.getDrawable(R$styleable.AppCompatSeekBar_android_thumb));
            int i13 = R$styleable.AppCompatSeekBar_android_thumbTintMode;
            if (obtainStyledAttributes2.hasValue(i13)) {
                this.mThumbTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes2.getInt(i13, -1), this.mThumbTintMode);
                this.mHasThumbTintMode = true;
            }
            int i14 = R$styleable.AppCompatSeekBar_android_thumbTint;
            if (obtainStyledAttributes2.hasValue(i14)) {
                this.mThumbTintList = obtainStyledAttributes2.getColorStateList(i14);
                this.mHasThumbTint = true;
            }
            setTickMark(obtainStyledAttributes2.getDrawable(R$styleable.AppCompatSeekBar_tickMark));
            int i15 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
            if (obtainStyledAttributes2.hasValue(i15)) {
                this.mTickMarkTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes2.getInt(i15, -1), this.mTickMarkTintMode);
                this.mHasTickMarkTintMode = true;
            }
            int i16 = R$styleable.AppCompatSeekBar_tickMarkTint;
            if (obtainStyledAttributes2.hasValue(i16)) {
                this.mTickMarkTintList = obtainStyledAttributes2.getColorStateList(i16);
                this.mHasTickMarkTint = true;
            }
            this.mSplitTrack = obtainStyledAttributes2.getBoolean(R$styleable.AppCompatSeekBar_android_splitTrack, false);
            this.mIsHapticEnabled = obtainStyledAttributes2.getBoolean(R$styleable.AppCompatSeekBar_seslHapticEnabled, true);
            int i17 = R$styleable.AppCompatSeekBar_seslTrackMinWidth;
            this.mTrackMinWidth = obtainStyledAttributes2.getDimensionPixelSize(i17, Math.round(resources.getDimension(R$dimen.sesl_seekbar_track_height)));
            int i18 = R$styleable.AppCompatSeekBar_seslTrackMaxWidth;
            this.mTrackMaxWidth = obtainStyledAttributes2.getDimensionPixelSize(i18, Math.round(resources.getDimension(R$dimen.sesl_seekbar_track_height_expand)));
            this.mModeExpandTrackMinWidth = obtainStyledAttributes2.getDimensionPixelSize(i17, Math.round(resources.getDimension(R$dimen.sesl_seekbar_mode_expand_track_height)));
            this.mModeExpandTrackMaxWidth = obtainStyledAttributes2.getDimensionPixelSize(i18, Math.round(resources.getDimension(R$dimen.sesl_seekbar_mode_expand_track_height_expand)));
            int i19 = R$styleable.AppCompatSeekBar_seslThumbRadius;
            this.mThumbRadius = obtainStyledAttributes2.getDimensionPixelSize(i19, Math.round(resources.getDimension(R$dimen.sesl_seekbar_thumb_radius)));
            this.mModeExpandThumbRadius = obtainStyledAttributes2.getDimensionPixelSize(i19, Math.round(resources.getDimension(R$dimen.sesl_seekbar_mode_expand_thumb_radius)));
            setThumbOffset(obtainStyledAttributes2.getDimensionPixelOffset(R$styleable.AppCompatSeekBar_android_thumbOffset, getThumbOffset()));
            int i20 = R$styleable.AppCompatSeekBar_seslSeekBarMode;
            if (obtainStyledAttributes2.hasValue(i20)) {
                this.mCurrentMode = obtainStyledAttributes2.getInt(i20, 0);
            }
            if (obtainStyledAttributes2.getBoolean(R$styleable.AppCompatSeekBar_useDisabledAlpha, true)) {
                obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, R$styleable.AppCompatTheme, 0, 0);
                this.mDisabledAlpha = obtainStyledAttributes.getFloat(R$styleable.AppCompatTheme_android_disabledAlpha, 0.5f);
                obtainStyledAttributes.recycle();
            } else {
                this.mDisabledAlpha = 1.0f;
            }
            applyThumbTint();
            applyTickMarkTint();
            this.mScaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
            boolean isLightTheme = SeslMisc.isLightTheme(context2);
            this.mIsLightTheme = isLightTheme;
            if (isLightTheme) {
                i8 = R$color.sesl_seekbar_control_color_default;
            } else {
                i8 = R$color.sesl_seekbar_control_color_default_dark;
            }
            this.mDefaultNormalProgressColor = colorToColorStateList(resources.getColor(i8));
            this.mDefaultSecondaryProgressColor = colorToColorStateList(resources.getColor(R$color.sesl_seekbar_control_color_secondary));
            this.mDefaultActivatedProgressColor = colorToColorStateList(resources.getColor(R$color.sesl_seekbar_control_color_activated));
            if (this.mIsLightTheme) {
                i10 = R$color.sesl_seekbar_overlap_color_default_light;
            } else {
                i10 = R$color.sesl_seekbar_overlap_color_default_dark;
            }
            this.mOverlapNormalProgressColor = colorToColorStateList(resources.getColor(i10));
            if (this.mIsLightTheme) {
                i11 = R$color.sesl_seekbar_overlap_color_activated_light;
            } else {
                i11 = R$color.sesl_seekbar_overlap_color_activated_dark;
            }
            this.mOverlapActivatedProgressColor = colorToColorStateList(resources.getColor(i11));
            ColorStateList thumbTintList = getThumbTintList();
            this.mDefaultActivatedThumbColor = thumbTintList;
            if (thumbTintList == null) {
                int[][] iArr2 = {new int[]{16842910}, new int[]{-16842910}};
                int color = resources.getColor(R$color.sesl_thumb_control_color_activated);
                if (this.mIsLightTheme) {
                    i12 = R$color.sesl_seekbar_disable_color_activated_light;
                } else {
                    i12 = R$color.sesl_seekbar_disable_color_activated_dark;
                }
                this.mDefaultActivatedThumbColor = new ColorStateList(iArr2, new int[]{color, resources.getColor(i12)});
            }
            boolean z = resources.getBoolean(R$bool.sesl_seekbar_sliding_animation);
            this.mAllowedSeekBarAnimation = z;
            if (z) {
                initMuteAnimation();
            }
            int i21 = this.mCurrentMode;
            if (i21 != 0) {
                setMode(i21);
            } else {
                initializeExpandMode();
            }
            obtainStyledAttributes2.recycle();
        } catch (Throwable th) {
            Throwable th2 = th;
            obtainStyledAttributes2.recycle();
            throw th2;
        }
    }

    private void applyThumbTint() {
        Drawable drawable = this.mThumb;
        if (drawable == null) {
            return;
        }
        if (this.mHasThumbTint || this.mHasThumbTintMode) {
            Drawable mutate = drawable.mutate();
            this.mThumb = mutate;
            if (this.mHasThumbTint) {
                DrawableCompat.setTintList(mutate, this.mThumbTintList);
            }
            if (this.mHasThumbTintMode) {
                DrawableCompat.setTintMode(this.mThumb, this.mThumbTintMode);
            }
            if (this.mThumb.isStateful()) {
                this.mThumb.setState(getDrawableState());
            }
        }
    }

    private void applyTickMarkTint() {
        Drawable drawable = this.mTickMark;
        if (drawable == null) {
            return;
        }
        if (this.mHasTickMarkTint || this.mHasTickMarkTintMode) {
            Drawable mutate = drawable.mutate();
            this.mTickMark = mutate;
            if (this.mHasTickMarkTint) {
                DrawableCompat.setTintList(mutate, this.mTickMarkTintList);
            }
            if (this.mHasTickMarkTintMode) {
                DrawableCompat.setTintMode(this.mTickMark, this.mTickMarkTintMode);
            }
            if (this.mTickMark.isStateful()) {
                this.mTickMark.setState(getDrawableState());
            }
        }
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    /* access modifiers changed from: private */
    public void callSuperSetProgress(int i2) {
        super.setProgress(i2);
    }

    private void cancelMuteAnimation() {
        AnimatorSet animatorSet = this.mMuteAnimationSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mMuteAnimationSet.cancel();
        }
    }

    private boolean checkInvalidatedDualColorMode() {
        if (this.mOverlapPoint == -1 || this.mOverlapBackground == null) {
            return true;
        }
        return false;
    }

    private ColorStateList colorToColorStateList(int i2) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{i2});
    }

    private int getHoverPopupType() {
        return SeslViewReflector.semGetHoverPopupType(this);
    }

    private float getScale() {
        int min = getMin();
        int max = getMax() - min;
        if (max > 0) {
            return ((float) (getProgress() - min)) / ((float) max);
        }
        return 0.0f;
    }

    private void initDualOverlapDrawable() {
        int i2 = this.mCurrentMode;
        if (i2 == 5) {
            this.mOverlapBackground = new SliderDrawable(this, (float) this.mModeExpandTrackMinWidth, (float) this.mModeExpandTrackMaxWidth, this.mOverlapNormalProgressColor);
        } else if (i2 == 6) {
            this.mOverlapBackground = new SliderDrawable((float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mOverlapNormalProgressColor, true);
        } else {
            if (i2 == 0) {
                this.mOverlapBackground = new SliderDrawable((float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mOverlapNormalProgressColor, false);
            } else if (getProgressDrawable() != null && getProgressDrawable().getConstantState() != null) {
                this.mOverlapBackground = getProgressDrawable().getConstantState().newDrawable().mutate();
            }
        }
    }

    private void initMuteAnimation() {
        boolean z;
        ValueAnimator valueAnimator;
        this.mMuteAnimationSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        int i2 = 400;
        for (int i7 = 0; i7 < 8; i7++) {
            if (i7 % 2 == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                valueAnimator = ValueAnimator.ofInt(new int[]{0, i2});
            } else {
                valueAnimator = ValueAnimator.ofInt(new int[]{i2, 0});
            }
            valueAnimator.setDuration((long) 62);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int unused = SeslAbsSeekBar.this.mCurrentProgressLevel = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    SeslAbsSeekBar seslAbsSeekBar = SeslAbsSeekBar.this;
                    seslAbsSeekBar.onSlidingRefresh(seslAbsSeekBar.mCurrentProgressLevel);
                }
            });
            arrayList.add(valueAnimator);
            if (z) {
                i2 = (int) (((double) i2) * 0.6d);
            }
        }
        this.mMuteAnimationSet.playSequentially(arrayList);
    }

    private void initializeExpandMode() {
        SliderDrawable sliderDrawable = new SliderDrawable(this, (float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mDefaultNormalProgressColor);
        SliderDrawable sliderDrawable2 = new SliderDrawable(this, (float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mDefaultSecondaryProgressColor);
        SliderDrawable sliderDrawable3 = new SliderDrawable(this, (float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mDefaultActivatedProgressColor);
        DrawableWrapperCompat drawableWrapperCompat = new DrawableWrapperCompat(new ThumbDrawable(this.mThumbRadius, this.mDefaultActivatedThumbColor, false));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{sliderDrawable, new ClipDrawable(sliderDrawable2, 19, 1), new ClipDrawable(sliderDrawable3, 19, 1)});
        layerDrawable.setPaddingMode(1);
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        setProgressDrawable(layerDrawable);
        setThumb(drawableWrapperCompat);
        setBackgroundResource(R$drawable.sesl_seekbar_background_borderless_expand);
        int maxHeight = getMaxHeight();
        int i2 = this.mTrackMaxWidth;
        if (maxHeight > i2) {
            setMaxHeight(i2);
        }
    }

    private void initializeExpandModeForModeExpand() {
        SliderDrawable sliderDrawable = new SliderDrawable(this, (float) this.mModeExpandTrackMinWidth, (float) this.mModeExpandTrackMaxWidth, this.mDefaultNormalProgressColor);
        SliderDrawable sliderDrawable2 = new SliderDrawable(this, (float) this.mModeExpandTrackMinWidth, (float) this.mModeExpandTrackMaxWidth, this.mDefaultSecondaryProgressColor);
        SliderDrawable sliderDrawable3 = new SliderDrawable(this, (float) this.mModeExpandTrackMinWidth, (float) this.mModeExpandTrackMaxWidth, this.mDefaultActivatedProgressColor);
        DrawableWrapperCompat drawableWrapperCompat = new DrawableWrapperCompat(new ThumbDrawable(this.mModeExpandThumbRadius, this.mDefaultActivatedThumbColor, false));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{sliderDrawable, new ClipDrawable(sliderDrawable2, 19, 1), new ClipDrawable(sliderDrawable3, 19, 1)});
        layerDrawable.setPaddingMode(1);
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        setProgressDrawable(layerDrawable);
        setThumb(drawableWrapperCompat);
        setBackgroundResource(R$drawable.sesl_seekbar_background_borderless_expand);
        int maxHeight = getMaxHeight();
        int i2 = this.mModeExpandTrackMaxWidth;
        if (maxHeight > i2) {
            setMaxHeight(i2);
        }
    }

    private void initializeExpandVerticalMode() {
        SliderDrawable sliderDrawable = new SliderDrawable((float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mDefaultNormalProgressColor, true);
        SliderDrawable sliderDrawable2 = new SliderDrawable((float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mDefaultSecondaryProgressColor, true);
        SliderDrawable sliderDrawable3 = new SliderDrawable((float) this.mTrackMinWidth, (float) this.mTrackMaxWidth, this.mDefaultActivatedProgressColor, true);
        DrawableWrapperCompat drawableWrapperCompat = new DrawableWrapperCompat(new ThumbDrawable(this.mThumbRadius, this.mDefaultActivatedThumbColor, true));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{sliderDrawable, new ClipDrawable(sliderDrawable2, 81, 2), new ClipDrawable(sliderDrawable3, 81, 2)});
        layerDrawable.setPaddingMode(1);
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        setProgressDrawable(layerDrawable);
        setThumb(drawableWrapperCompat);
        setBackgroundResource(R$drawable.sesl_seekbar_background_borderless_expand);
        int maxWidth = getMaxWidth();
        int i2 = this.mTrackMaxWidth;
        if (maxWidth > i2) {
            setMaxWidth(i2);
        }
    }

    private boolean isHoverPopupTypeUserCustom(int i2) {
        if (i2 == SeslHoverPopupWindowReflector.getField_TYPE_USER_CUSTOM()) {
            return true;
        }
        return false;
    }

    private void setHotspot(float f, float f5) {
        Drawable background = getBackground();
        if (background != null) {
            DrawableCompat.setHotspot(background, f, f5);
        }
    }

    private void setHoverPopupDetectTime() {
        SeslHoverPopupWindowReflector.setHoverDetectTime(SeslViewReflector.semGetHoverPopup(this, true), 200);
    }

    private void setHoverPopupGravity(int i2) {
        SeslHoverPopupWindowReflector.setGravity(SeslViewReflector.semGetHoverPopup(this, true), i2);
    }

    private void setHoverPopupOffset(int i2, int i7) {
        SeslHoverPopupWindowReflector.setOffset(SeslViewReflector.semGetHoverPopup(this, true), i2, i7);
    }

    private void setHoveringPoint(int i2, int i7) {
        SeslHoverPopupWindowReflector.setHoveringPoint(SeslViewReflector.semGetHoverPopup(this, true), i2, i7);
    }

    private void setProgressOverlapTintList(ColorStateList colorStateList) {
        super.setProgressTintList(colorStateList);
    }

    private void setThumbOverlapTintList(ColorStateList colorStateList) {
        this.mThumbTintList = colorStateList;
        this.mHasThumbTint = true;
        applyThumbTint();
    }

    private void setThumbPos(int i2, Drawable drawable, float f, int i7) {
        int i8;
        int i10 = this.mCurrentMode;
        if (i10 == 3 || i10 == 6) {
            setThumbPosInVertical(getHeight(), drawable, f, i7);
            return;
        }
        int paddingLeft = ((i2 - getPaddingLeft()) - getPaddingRight()) - ((int) (this.mLevelDrawPadding * 2.0f));
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i11 = (this.mThumbOffset * 2) + (paddingLeft - intrinsicWidth);
        int i12 = (int) ((f * ((float) i11)) + 0.5f);
        if (i7 == Integer.MIN_VALUE) {
            Rect bounds = drawable.getBounds();
            i7 = bounds.top;
            i8 = bounds.bottom;
        } else {
            i8 = i7 + intrinsicHeight;
        }
        int i13 = (int) this.mLevelDrawPadding;
        if (ViewUtils.isLayoutRtl(this) && this.mMirrorForRtl) {
            i12 = i11 - i12;
        }
        int i14 = i13 + i12;
        int i15 = i14 + intrinsicWidth;
        Drawable background = getBackground();
        if (background != null) {
            int paddingLeft2 = getPaddingLeft() - this.mThumbOffset;
            int paddingTop = getPaddingTop();
            DrawableCompat.setHotspotBounds(background, i14 + paddingLeft2, i7 + paddingTop, paddingLeft2 + i15, paddingTop + i8);
        }
        drawable.setBounds(i14, i7, i15, i8);
        updateGestureExclusionRects();
        this.mThumbPosX = (getPaddingLeft() + i14) - (getPaddingLeft() - (intrinsicWidth / 2));
        updateSplitProgress();
    }

    private void setThumbPosInVertical(int i2, Drawable drawable, float f, int i7) {
        int i8;
        int paddingTop = (i2 - getPaddingTop()) - getPaddingBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i10 = (this.mThumbOffset * 2) + (paddingTop - intrinsicHeight);
        int i11 = (int) ((f * ((float) i10)) + 0.5f);
        if (i7 == Integer.MIN_VALUE) {
            Rect bounds = drawable.getBounds();
            i7 = bounds.left;
            i8 = bounds.right;
        } else {
            i8 = i7 + intrinsicWidth;
        }
        int i12 = i10 - i11;
        int i13 = intrinsicHeight + i12;
        Drawable background = getBackground();
        if (background != null) {
            int paddingLeft = getPaddingLeft();
            int paddingTop2 = getPaddingTop() - this.mThumbOffset;
            DrawableCompat.setHotspotBounds(background, i7 + paddingLeft, i12 + paddingTop2, paddingLeft + i8, paddingTop2 + i13);
        }
        drawable.setBounds(i7, i12, i8, i13);
        this.mThumbPosX = getPaddingLeft() + (intrinsicWidth / 2) + i12;
    }

    private void startDrag(MotionEvent motionEvent) {
        setPressed(true);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            invalidate(drawable.getBounds());
        }
        onStartTrackingTouch();
        trackTouchEvent(motionEvent);
        attemptClaimDrag();
    }

    private void startMuteAnimation() {
        cancelMuteAnimation();
        AnimatorSet animatorSet = this.mMuteAnimationSet;
        if (animatorSet != null) {
            animatorSet.start();
        }
    }

    private boolean supportIsHoveringUIEnabled() {
        return SeslViewReflector.isHoveringUIEnabled(this);
    }

    private boolean supportIsInScrollingContainer() {
        return SeslViewReflector.isInScrollingContainer(this);
    }

    private void trackHoverEvent(int i2) {
        float f;
        int width = getWidth();
        int paddingLeft = (width - getPaddingLeft()) - getPaddingRight();
        float f5 = 0.0f;
        if (i2 < getPaddingLeft()) {
            f = 0.0f;
        } else if (i2 > width - getPaddingRight()) {
            f = 1.0f;
        } else {
            float paddingLeft2 = ((float) (i2 - getPaddingLeft())) / ((float) paddingLeft);
            f5 = this.mTouchProgressOffset;
            f = paddingLeft2;
        }
        this.mHoveringLevel = (int) ((f * ((float) getMax())) + f5);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void trackTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r8.mCurrentMode
            r1 = 3
            if (r0 == r1) goto L_0x00d0
            r1 = 6
            if (r0 != r1) goto L_0x000a
            goto L_0x00d0
        L_0x000a:
            float r0 = r9.getX()
            int r0 = java.lang.Math.round(r0)
            float r9 = r9.getY()
            int r9 = java.lang.Math.round(r9)
            int r1 = r8.getWidth()
            int r2 = r8.getPaddingLeft()
            int r2 = r1 - r2
            int r3 = r8.getPaddingRight()
            int r2 = r2 - r3
            boolean r3 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r8)
            r4 = 0
            r5 = 1065353216(0x3f800000, float:1.0)
            if (r3 == 0) goto L_0x0056
            boolean r3 = r8.mMirrorForRtl
            if (r3 == 0) goto L_0x0056
            int r3 = r8.getPaddingRight()
            int r1 = r1 - r3
            if (r0 <= r1) goto L_0x0040
        L_0x003d:
            r1 = r4
            r2 = r1
            goto L_0x0070
        L_0x0040:
            int r1 = r8.getPaddingLeft()
            if (r0 >= r1) goto L_0x0049
        L_0x0046:
            r2 = r4
            r1 = r5
            goto L_0x0070
        L_0x0049:
            int r1 = r2 - r0
            int r3 = r8.getPaddingLeft()
            int r3 = r3 + r1
            float r1 = (float) r3
            float r2 = (float) r2
            float r1 = r1 / r2
            float r2 = r8.mTouchProgressOffset
            goto L_0x0070
        L_0x0056:
            int r3 = r8.getPaddingLeft()
            if (r0 >= r3) goto L_0x005d
            goto L_0x003d
        L_0x005d:
            int r3 = r8.getPaddingRight()
            int r1 = r1 - r3
            if (r0 <= r1) goto L_0x0065
            goto L_0x0046
        L_0x0065:
            int r1 = r8.getPaddingLeft()
            int r1 = r0 - r1
            float r1 = (float) r1
            float r2 = (float) r2
            float r1 = r1 / r2
            float r2 = r8.mTouchProgressOffset
        L_0x0070:
            boolean r3 = r8.mIsSeamless
            r6 = 1073741824(0x40000000, float:2.0)
            if (r3 == 0) goto L_0x009d
            int r3 = super.getMax()
            int r7 = super.getMin()
            int r3 = r3 - r7
            float r3 = (float) r3
            float r7 = r5 / r3
            int r4 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x0094
            int r4 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x0094
            float r4 = r1 % r7
            float r5 = r7 / r6
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x0094
            float r7 = r7 - r4
            float r1 = r1 + r7
        L_0x0094:
            float r1 = r1 * r3
            int r3 = super.getMin()
        L_0x0099:
            float r3 = (float) r3
            float r1 = r1 + r3
            float r1 = r1 + r2
            goto L_0x00c1
        L_0x009d:
            int r3 = r8.getMax()
            int r7 = r8.getMin()
            int r3 = r3 - r7
            float r3 = (float) r3
            float r7 = r5 / r3
            int r4 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x00bb
            int r4 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x00bb
            float r4 = r1 % r7
            float r5 = r7 / r6
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x00bb
            float r7 = r7 - r4
            float r1 = r1 + r7
        L_0x00bb:
            float r1 = r1 * r3
            int r3 = r8.getMin()
            goto L_0x0099
        L_0x00c1:
            float r0 = (float) r0
            float r9 = (float) r9
            r8.setHotspot(r0, r9)
            int r9 = java.lang.Math.round(r1)
            r0 = 1
            r1 = 0
            r8.setProgressInternal(r9, r0, r1)
            return
        L_0x00d0:
            r8.trackTouchEventInVertical(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslAbsSeekBar.trackTouchEvent(android.view.MotionEvent):void");
    }

    private void trackTouchEventInVertical(MotionEvent motionEvent) {
        float f;
        float f5;
        float f8;
        int min;
        int height = getHeight();
        int paddingTop = (height - getPaddingTop()) - getPaddingBottom();
        int round = Math.round(motionEvent.getX());
        int round2 = height - Math.round(motionEvent.getY());
        if (round2 < getPaddingBottom()) {
            f5 = 0.0f;
            f = 0.0f;
        } else if (round2 > height - getPaddingTop()) {
            f5 = 1.0f;
            f = 0.0f;
        } else {
            f5 = ((float) (round2 - getPaddingBottom())) / ((float) paddingTop);
            f = this.mTouchProgressOffset;
        }
        if (this.mIsSeamless) {
            float max = (float) (super.getMax() - super.getMin());
            float f10 = 1.0f / max;
            if (f5 > 0.0f && f5 < 1.0f) {
                float f11 = f5 % f10;
                if (f11 > f10 / 2.0f) {
                    f5 += f10 - f11;
                }
            }
            f8 = f5 * max;
            min = super.getMin();
        } else {
            float max2 = (float) (getMax() - getMin());
            float f12 = 1.0f / max2;
            if (f5 > 0.0f && f5 < 1.0f) {
                float f13 = f5 % f12;
                if (f13 > f12 / 2.0f) {
                    f5 += f12 - f13;
                }
            }
            f8 = f5 * max2;
            min = getMin();
        }
        float f14 = f8 + ((float) min) + f;
        setHotspot((float) round, (float) round2);
        setProgressInternal(Math.round(f14), true, false);
    }

    private void updateBoundsForDualColor() {
        if (getCurrentDrawable() != null && !checkInvalidatedDualColorMode()) {
            this.mOverlapBackground.setBounds(getCurrentDrawable().getBounds());
        }
    }

    private void updateDualColorMode() {
        if (!checkInvalidatedDualColorMode()) {
            DrawableCompat.setTintList(this.mOverlapBackground, this.mOverlapNormalProgressColor);
            if (!this.mLargeFont) {
                if ((!this.mIsSeamless || ((float) super.getProgress()) <= ((float) this.mOverlapPoint) * 1000.0f) && getProgress() <= this.mOverlapPoint) {
                    setProgressTintList(this.mDefaultActivatedProgressColor);
                    setThumbTintList(this.mDefaultActivatedThumbColor);
                } else {
                    setProgressOverlapTintList(this.mOverlapActivatedProgressColor);
                    setThumbOverlapTintList(this.mOverlapActivatedProgressColor);
                }
            }
            updateBoundsForDualColor();
        }
    }

    private void updateGestureExclusionRects() {
        Drawable drawable = this.mThumb;
        if (drawable == null) {
            super.setSystemGestureExclusionRects(this.mUserGestureExclusionRects);
            return;
        }
        this.mGestureExclusionRects.clear();
        drawable.copyBounds(this.mThumbRect);
        this.mGestureExclusionRects.add(this.mThumbRect);
        this.mGestureExclusionRects.addAll(this.mUserGestureExclusionRects);
        super.setSystemGestureExclusionRects(this.mGestureExclusionRects);
    }

    private void updateSplitProgress() {
        if (this.mCurrentMode == 4) {
            Drawable drawable = this.mSplitProgress;
            Rect bounds = getCurrentDrawable().getBounds();
            if (drawable != null) {
                if (!this.mMirrorForRtl || !ViewUtils.isLayoutRtl(this)) {
                    drawable.setBounds(getPaddingLeft(), bounds.top, this.mThumbPosX, bounds.bottom);
                } else {
                    drawable.setBounds(this.mThumbPosX, bounds.top, getWidth() - getPaddingRight(), bounds.bottom);
                }
            }
            int width = getWidth();
            int height = getHeight();
            Drawable drawable2 = this.mDivider;
            if (drawable2 != null) {
                float f = ((float) width) / 2.0f;
                float f5 = this.mDensity;
                float f8 = ((float) height) / 2.0f;
                drawable2.setBounds((int) (f - ((f5 * 4.0f) / 2.0f)), (int) (f8 - ((f5 * 22.0f) / 2.0f)), (int) (((4.0f * f5) / 2.0f) + f), (int) (((f5 * 22.0f) / 2.0f) + f8));
            }
        }
    }

    private void updateThumbAndTrackPos(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12 = this.mCurrentMode;
        if (i12 == 3 || i12 == 6) {
            updateThumbAndTrackPosInVertical(i2, i7);
            return;
        }
        int paddingTop = (i7 - getPaddingTop()) - getPaddingBottom();
        Drawable currentDrawable = getCurrentDrawable();
        Drawable drawable = this.mThumb;
        int min = Math.min(this.mMaxHeight, paddingTop);
        if (drawable == null) {
            i8 = 0;
        } else {
            i8 = drawable.getIntrinsicHeight();
        }
        if (i8 > min) {
            i10 = (paddingTop - i8) / 2;
            i11 = C0086a.D(i8, min, 2, i10);
        } else {
            int i13 = (paddingTop - min) / 2;
            int D = C0086a.D(min, i8, 2, i13);
            i11 = i13;
            i10 = D;
        }
        if (currentDrawable != null) {
            currentDrawable.setBounds(0, i11, (i2 - getPaddingRight()) - getPaddingLeft(), min + i11);
        }
        if (drawable != null) {
            setThumbPos(i2, drawable, getScale(), i10);
        }
        updateSplitProgress();
    }

    private void updateThumbAndTrackPosInVertical(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int paddingLeft = (i2 - getPaddingLeft()) - getPaddingRight();
        Drawable currentDrawable = getCurrentDrawable();
        Drawable drawable = this.mThumb;
        int min = Math.min(this.mMaxWidth, paddingLeft);
        if (drawable == null) {
            i8 = 0;
        } else {
            i8 = drawable.getIntrinsicWidth();
        }
        if (i8 > min) {
            i10 = (paddingLeft - i8) / 2;
            i11 = C0086a.D(i8, min, 2, i10);
        } else {
            int i12 = (paddingLeft - min) / 2;
            int i13 = i12;
            i10 = C0086a.D(min, i8, 2, i12);
            i11 = i13;
        }
        if (currentDrawable != null) {
            currentDrawable.setBounds(i11, 0, paddingLeft - i11, (i7 - getPaddingBottom()) - getPaddingTop());
        }
        if (drawable != null) {
            setThumbPosInVertical(i7, drawable, getScale(), i10);
        }
    }

    private void updateWarningMode(int i2) {
        if (this.mCurrentMode != 1) {
            return;
        }
        if (i2 == getMax()) {
            setProgressOverlapTintList(this.mOverlapActivatedProgressColor);
            setThumbOverlapTintList(this.mOverlapActivatedProgressColor);
            return;
        }
        setProgressTintList(this.mDefaultActivatedProgressColor);
        setThumbTintList(this.mDefaultActivatedThumbColor);
    }

    public boolean canUserSetProgress() {
        if (isIndeterminate() || !isEnabled()) {
            return false;
        }
        return true;
    }

    public void drawThumb(Canvas canvas) {
        if (this.mThumb != null) {
            int save = canvas.save();
            int i2 = this.mCurrentMode;
            if (i2 == 3 || i2 == 6) {
                canvas.translate((float) getPaddingLeft(), (float) (getPaddingTop() - this.mThumbOffset));
            } else {
                canvas.translate((float) (getPaddingLeft() - this.mThumbOffset), (float) getPaddingTop());
            }
            this.mThumb.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    public void drawTickMarks(Canvas canvas) {
        int i2;
        if (this.mTickMark != null) {
            int max = getMax() - getMin();
            int i7 = 1;
            if (max > 1) {
                int intrinsicWidth = this.mTickMark.getIntrinsicWidth();
                int intrinsicHeight = this.mTickMark.getIntrinsicHeight();
                if (intrinsicWidth >= 0) {
                    i2 = intrinsicWidth / 2;
                } else {
                    i2 = 1;
                }
                if (intrinsicHeight >= 0) {
                    i7 = intrinsicHeight / 2;
                }
                this.mTickMark.setBounds(-i2, -i7, i2, i7);
                float width = (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) - (this.mLevelDrawPadding * 2.0f)) / ((float) max);
                int save = canvas.save();
                canvas.translate(this.mLevelDrawPadding + ((float) getPaddingLeft()), ((float) getHeight()) / 2.0f);
                for (int i8 = 0; i8 <= max; i8++) {
                    this.mTickMark.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    public void drawTrack(Canvas canvas) {
        int i2;
        int i7;
        Drawable drawable = this.mThumb;
        if (drawable == null || !this.mSplitTrack) {
            super.drawTrack(canvas);
            drawTickMarks(canvas);
        } else {
            Rect opticalBounds = DrawableUtils.getOpticalBounds(drawable);
            Rect rect = this.mTempRect;
            drawable.copyBounds(rect);
            rect.offset(getPaddingLeft() - this.mThumbOffset, getPaddingTop());
            rect.left += opticalBounds.left;
            rect.right -= opticalBounds.right;
            int save = canvas.save();
            canvas.clipRect(rect, Region.Op.DIFFERENCE);
            super.drawTrack(canvas);
            drawTickMarks(canvas);
            canvas.restoreToCount(save);
        }
        if (!checkInvalidatedDualColorMode()) {
            canvas.save();
            if (!this.mMirrorForRtl || !ViewUtils.isLayoutRtl(this)) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            } else {
                canvas.translate((float) (getWidth() - getPaddingRight()), (float) getPaddingTop());
                canvas.scale(-1.0f, 1.0f);
            }
            Rect bounds = this.mOverlapBackground.getBounds();
            Rect rect2 = this.mTempRect;
            this.mOverlapBackground.copyBounds(rect2);
            if (this.mIsSeamless) {
                i7 = Math.max(super.getProgress(), (int) (((float) this.mOverlapPoint) * 1000.0f));
                i2 = super.getMax();
            } else {
                i7 = Math.max(getProgress(), this.mOverlapPoint);
                i2 = getMax();
            }
            int min = getMin();
            float f = ((float) (i7 - min)) / ((float) (i2 - min));
            int i8 = this.mCurrentMode;
            if (i8 == 3 || i8 == 6) {
                rect2.bottom = (int) (((float) bounds.bottom) - (((float) bounds.height()) * f));
            } else {
                rect2.left = (int) ((((float) bounds.width()) * f) + ((float) bounds.left));
            }
            canvas.clipRect(rect2);
            if (this.mDefaultNormalProgressColor.getDefaultColor() != this.mOverlapNormalProgressColor.getDefaultColor()) {
                this.mOverlapBackground.draw(canvas);
            }
            canvas.restore();
        }
    }

    public void drawableHotspotChanged(float f, float f5) {
        super.drawableHotspotChanged(f, f5);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f5);
        }
    }

    public void drawableStateChanged() {
        Drawable drawable;
        int i2;
        super.drawableStateChanged();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null && this.mDisabledAlpha < 1.0f) {
            if (isEnabled()) {
                i2 = ScoverState.TYPE_NFC_SMART_COVER;
            } else {
                i2 = (int) (this.mDisabledAlpha * 255.0f);
            }
            progressDrawable.setAlpha(i2);
            Drawable drawable2 = this.mOverlapBackground;
            if (drawable2 != null) {
                drawable2.setAlpha(i2);
            }
        }
        if (this.mThumb != null && this.mHasThumbTint) {
            if (!isEnabled()) {
                DrawableCompat.setTintList(this.mThumb, (ColorStateList) null);
            } else {
                DrawableCompat.setTintList(this.mThumb, this.mDefaultActivatedThumbColor);
                updateDualColorMode();
            }
        }
        if (this.mSetDualColorMode && progressDrawable != null && progressDrawable.isStateful() && (drawable = this.mOverlapBackground) != null) {
            drawable.setState(getDrawableState());
        }
        Drawable drawable3 = this.mThumb;
        if (drawable3 != null && drawable3.isStateful() && drawable3.setState(getDrawableState())) {
            invalidateDrawable(drawable3);
        }
        Drawable drawable4 = this.mTickMark;
        if (drawable4 != null && drawable4.isStateful() && drawable4.setState(getDrawableState())) {
            invalidateDrawable(drawable4);
        }
    }

    public CharSequence getAccessibilityClassName() {
        Log.d("SeslAbsSeekBar", "Stack:", new Throwable("stack dump"));
        return AbsSeekBar.class.getName();
    }

    public int getKeyProgressIncrement() {
        return this.mKeyProgressIncrement;
    }

    public synchronized int getMax() {
        int i2;
        try {
            if (this.mIsSeamless) {
                i2 = Math.round(((float) super.getMax()) / 1000.0f);
            } else {
                i2 = super.getMax();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i2;
    }

    public synchronized int getMin() {
        int i2;
        try {
            if (this.mIsSeamless) {
                i2 = Math.round(((float) super.getMin()) / 1000.0f);
            } else {
                i2 = super.getMin();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i2;
    }

    public synchronized int getProgress() {
        int i2;
        try {
            if (this.mIsSeamless) {
                i2 = Math.round(((float) super.getProgress()) / 1000.0f);
            } else {
                i2 = super.getProgress();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i2;
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public Drawable getThumb() {
        return this.mThumb;
    }

    public Rect getThumbBounds() {
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            return drawable.getBounds();
        }
        return null;
    }

    public int getThumbHeight() {
        return this.mThumb.getIntrinsicHeight();
    }

    public int getThumbOffset() {
        return this.mThumbOffset;
    }

    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    public Drawable getTickMark() {
        return this.mTickMark;
    }

    public ColorStateList getTickMarkTintList() {
        return this.mTickMarkTintList;
    }

    public PorterDuff.Mode getTickMarkTintMode() {
        return this.mTickMarkTintMode;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mTickMark;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    public synchronized void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (supportIsHoveringUIEnabled()) {
                int hoverPopupType = getHoverPopupType();
                if (isHoverPopupTypeUserCustom(hoverPopupType) && this.mPreviousHoverPopupType != hoverPopupType) {
                    this.mPreviousHoverPopupType = hoverPopupType;
                    setHoverPopupGravity(12849);
                    setHoverPopupOffset(0, getMeasuredHeight() / 2);
                    setHoverPopupDetectTime();
                }
            }
            if (this.mCurrentMode == 4) {
                this.mSplitProgress.draw(canvas);
                this.mDivider.draw(canvas);
            }
            if (!this.mIsTouchDisabled) {
                drawThumb(canvas);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (supportIsHoveringUIEnabled()) {
            int action = motionEvent.getAction();
            int x9 = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 7) {
                trackHoverEvent(x9);
                onHoverChanged(this.mHoveringLevel, x9, y);
                if (isHoverPopupTypeUserCustom(getHoverPopupType())) {
                    setHoveringPoint((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                    updateHoverPopup();
                }
            } else if (action == 9) {
                trackHoverEvent(x9);
                onStartTrackingHover(this.mHoveringLevel, x9, y);
            } else if (action == 10) {
                onStopTrackingHover();
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (isEnabled()) {
            int progress = getProgress();
            if (progress > getMin()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            }
            if (progress < getMax()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        if (r9 != 81) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005c, code lost:
        if (r9 != 81) goto L_0x0086;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r9, android.view.KeyEvent r10) {
        /*
            r8 = this;
            boolean r0 = r8.isEnabled()
            if (r0 == 0) goto L_0x0086
            int r0 = r8.mKeyProgressIncrement
            int r1 = r8.mCurrentMode
            r2 = 3
            r3 = 1148846080(0x447a0000, float:1000.0)
            r4 = 81
            r5 = 70
            r6 = 69
            r7 = 1
            if (r1 == r2) goto L_0x0050
            r2 = 6
            if (r1 != r2) goto L_0x001a
            goto L_0x0050
        L_0x001a:
            r1 = 21
            if (r9 == r1) goto L_0x0029
            r1 = 22
            if (r9 == r1) goto L_0x002a
            if (r9 == r6) goto L_0x0029
            if (r9 == r5) goto L_0x002a
            if (r9 == r4) goto L_0x002a
            goto L_0x0086
        L_0x0029:
            int r0 = -r0
        L_0x002a:
            boolean r1 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r8)
            if (r1 == 0) goto L_0x0031
            int r0 = -r0
        L_0x0031:
            boolean r1 = r8.mIsSeamless
            if (r1 == 0) goto L_0x0041
            int r1 = r8.getProgress()
            int r1 = r1 + r0
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = java.lang.Math.round(r0)
            goto L_0x0046
        L_0x0041:
            int r1 = r8.getProgress()
            int r0 = r0 + r1
        L_0x0046:
            boolean r0 = r8.setProgressInternal(r0, r7, r7)
            if (r0 == 0) goto L_0x0086
            r8.onKeyChange()
            return r7
        L_0x0050:
            r1 = 19
            if (r9 == r1) goto L_0x0060
            r1 = 20
            if (r9 == r1) goto L_0x005f
            if (r9 == r6) goto L_0x005f
            if (r9 == r5) goto L_0x0060
            if (r9 == r4) goto L_0x0060
            goto L_0x0086
        L_0x005f:
            int r0 = -r0
        L_0x0060:
            boolean r1 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r8)
            if (r1 == 0) goto L_0x0067
            int r0 = -r0
        L_0x0067:
            boolean r1 = r8.mIsSeamless
            if (r1 == 0) goto L_0x0077
            int r1 = r8.getProgress()
            int r1 = r1 + r0
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = java.lang.Math.round(r0)
            goto L_0x007c
        L_0x0077:
            int r1 = r8.getProgress()
            int r0 = r0 + r1
        L_0x007c:
            boolean r0 = r8.setProgressInternal(r0, r7, r7)
            if (r0 == 0) goto L_0x0086
            r8.onKeyChange()
            return r7
        L_0x0086:
            boolean r8 = super.onKeyDown(r9, r10)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslAbsSeekBar.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    public synchronized void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12;
        try {
            Drawable currentDrawable = getCurrentDrawable();
            if (currentDrawable != null) {
                int i13 = this.mCurrentMode;
                if (i13 != 3) {
                    if (i13 != 6) {
                        Drawable drawable = this.mThumb;
                        if (drawable == null) {
                            i12 = 0;
                        } else {
                            i12 = drawable.getIntrinsicHeight();
                        }
                        i8 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, currentDrawable.getIntrinsicWidth()));
                        i10 = Math.max(i12, Math.max(this.mMinHeight, Math.min(this.mMaxHeight, currentDrawable.getIntrinsicHeight())));
                    }
                }
                Drawable drawable2 = this.mThumb;
                if (drawable2 == null) {
                    i11 = 0;
                } else {
                    i11 = drawable2.getIntrinsicHeight();
                }
                int max = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, currentDrawable.getIntrinsicHeight()));
                i10 = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, currentDrawable.getIntrinsicWidth()));
                i8 = Math.max(i11, max);
            } else {
                i10 = 0;
                i8 = 0;
            }
            setMeasuredDimension(View.resolveSizeAndState(getPaddingLeft() + getPaddingRight() + i8, i2, 0), View.resolveSizeAndState(getPaddingTop() + getPaddingBottom() + i10, i7, 0));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void onProgressRefresh(float f, boolean z, int i2) {
        int i7 = (int) (10000.0f * f);
        if (!this.mUseMuteAnimation || this.mIsFirstSetProgress || this.mIsDraggingForSliding || this.mCurrentProgressLevel == 0 || i7 != 0) {
            cancelMuteAnimation();
            this.mIsFirstSetProgress = false;
            this.mCurrentProgressLevel = i7;
            super.onProgressRefresh(f, z, i2);
            Drawable drawable = this.mThumb;
            if (drawable != null) {
                setThumbPos(getWidth(), drawable, f, Integer.MIN_VALUE);
                invalidate();
            }
        } else {
            startMuteAnimation();
        }
        if (z && this.mCurrentMode == 8) {
            performHapticFeedback(SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(41));
        } else if (z && this.mIsHapticEnabled) {
            int i8 = this.mCurrentMode;
            if (i8 != 5 && i8 != 0 && i8 != 6 && i8 != 3) {
                return;
            }
            if (i2 == getMin() || i2 == getMax()) {
                performHapticFeedback(SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(41));
            }
        }
    }

    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            setThumbPos(getWidth(), drawable, getScale(), Integer.MIN_VALUE);
            invalidate();
        }
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
        updateThumbAndTrackPos(i2, i7);
    }

    public void onSlidingRefresh(int i2) {
        super.onSlidingRefresh(i2);
        float f = ((float) i2) / 10000.0f;
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            setThumbPos(getWidth(), drawable, f, Integer.MIN_VALUE);
            invalidate();
        }
    }

    public void onStartTrackingTouch() {
        this.mIsDragging = true;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public void onStopTrackingTouch() {
        this.mIsDragging = false;
        if (this.mIsSeamless && isPressed()) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{super.getProgress(), (int) (((float) Math.round(((float) super.getProgress()) / 1000.0f)) * 1000.0f)});
            this.mValueAnimator = ofInt;
            ofInt.setDuration(300);
            this.mValueAnimator.setInterpolator(SeslAnimationUtils.SINE_IN_OUT_90);
            this.mValueAnimator.start();
            this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SeslAbsSeekBar.this.callSuperSetProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
        } else if (this.mIsSeamless) {
            setProgress(Math.round(((float) super.getProgress()) / 1000.0f));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i2;
        if (!this.mIsUserSeekable || this.mIsTouchDisabled || !isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mIsDraggingForSliding = false;
            int i7 = this.mCurrentMode;
            if (i7 == 5 || i7 == 6 || i7 == 0 || supportIsInScrollingContainer()) {
                this.mTouchDownX = motionEvent.getX();
                this.mTouchDownY = motionEvent.getY();
            } else {
                startDrag(motionEvent);
            }
        } else if (action == 1) {
            if (this.mIsDraggingForSliding) {
                this.mIsDraggingForSliding = false;
            }
            if (this.mIsDragging) {
                trackTouchEvent(motionEvent);
                onStopTrackingTouch();
                setPressed(false);
            } else {
                onStartTrackingTouch();
                trackTouchEvent(motionEvent);
                onStopTrackingTouch();
            }
            invalidate();
        } else if (action == 2) {
            this.mIsDraggingForSliding = true;
            if (this.mIsDragging) {
                trackTouchEvent(motionEvent);
            } else {
                float x9 = motionEvent.getX();
                float y = motionEvent.getY();
                int i8 = this.mCurrentMode;
                if (!(i8 == 3 || i8 == 6 || Math.abs(x9 - this.mTouchDownX) <= ((float) this.mScaledTouchSlop)) || (((i2 = this.mCurrentMode) == 3 || i2 == 6) && Math.abs(y - this.mTouchDownY) > ((float) this.mScaledTouchSlop))) {
                    startDrag(motionEvent);
                }
            }
        } else if (action == 3) {
            this.mIsDraggingForSliding = false;
            if (this.mIsDragging) {
                onStopTrackingTouch();
                setPressed(false);
            }
            invalidate();
        }
        return true;
    }

    public void onVisualProgressChanged(int i2, float f) {
        Drawable drawable;
        super.onVisualProgressChanged(i2, f);
        if (i2 == 16908301 && (drawable = this.mThumb) != null) {
            setThumbPos(getWidth(), drawable, f, Integer.MIN_VALUE);
            invalidate();
        }
    }

    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        int i7;
        int i8;
        if (super.performAccessibilityAction(i2, bundle)) {
            return true;
        }
        if (!isEnabled()) {
            return false;
        }
        if (i2 == 4096 || i2 == 8192) {
            if (!canUserSetProgress()) {
                return false;
            }
            int max = Math.max(1, Math.round(((float) (getMax() - getMin())) / 20.0f));
            if (i2 == 8192) {
                max = -max;
            }
            if (this.mIsSeamless) {
                i7 = Math.round(((float) (getProgress() + max)) * 1000.0f);
            } else {
                i7 = getProgress() + max;
            }
            if (!setProgressInternal(i7, true, true)) {
                return false;
            }
            onKeyChange();
            return true;
        } else if (i2 != 16908349 || !canUserSetProgress() || bundle == null || !bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
            return false;
        } else {
            float f = bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
            if (this.mIsSeamless) {
                i8 = Math.round(f * 1000.0f);
            } else {
                i8 = (int) f;
            }
            return setProgressInternal(i8, true, true);
        }
    }

    public void setKeyProgressIncrement(int i2) {
        if (i2 < 0) {
            i2 = -i2;
        }
        this.mKeyProgressIncrement = i2;
    }

    public synchronized void setMax(int i2) {
        try {
            if (this.mIsSeamless) {
                i2 = Math.round(((float) i2) * 1000.0f);
            }
            super.setMax(i2);
            this.mIsFirstSetProgress = true;
            int max = getMax() - getMin();
            int i7 = this.mKeyProgressIncrement;
            if (i7 == 0 || max / i7 > 20) {
                setKeyProgressIncrement(Math.max(1, Math.round(((float) max) / 20.0f)));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void setMin(int i2) {
        try {
            if (this.mIsSeamless) {
                i2 = Math.round(((float) i2) * 1000.0f);
            }
            super.setMin(i2);
            int max = getMax() - getMin();
            int i7 = this.mKeyProgressIncrement;
            if (i7 == 0 || max / i7 > 20) {
                setKeyProgressIncrement(Math.max(1, Math.round(((float) max) / 20.0f)));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setMode(int i2) {
        int i7;
        if (this.mCurrentMode != i2 || !this.mIsSetModeCalled) {
            super.setMode(i2);
            this.mLevelDrawPadding = 0.0f;
            if (i2 == 0) {
                setProgressTintList(this.mDefaultActivatedProgressColor);
                setThumbTintList(this.mDefaultActivatedThumbColor);
            } else if (i2 == 1) {
                updateWarningMode(getProgress());
            } else if (i2 == 3) {
                Resources resources = getContext().getResources();
                if (this.mIsLightTheme) {
                    i7 = R$drawable.sesl_scrubber_control_anim_light;
                } else {
                    i7 = R$drawable.sesl_scrubber_control_anim_dark;
                }
                setThumb(resources.getDrawable(i7));
                setBackgroundResource(R$drawable.sesl_seek_bar_background_borderless);
            } else if (i2 == 4) {
                this.mSplitProgress = getContext().getResources().getDrawable(R$drawable.sesl_split_seekbar_primary_progress);
                this.mDivider = getContext().getResources().getDrawable(R$drawable.sesl_split_seekbar_vertical_bar);
                updateSplitProgress();
            } else if (i2 == 5) {
                initializeExpandModeForModeExpand();
                this.mLevelDrawPadding = getContext().getResources().getDimension(R$dimen.sesl_seekbar_level_progress_padding_start_end);
            } else if (i2 == 6) {
                initializeExpandVerticalMode();
            } else if (i2 == 8) {
                this.mLevelDrawPadding = getContext().getResources().getDimension(R$dimen.sesl_seekbar_level_progress_padding_start_end);
                setProgressDrawable(getContext().getResources().getDrawable(R$drawable.sesl_level_seekbar_progress));
                setTickMark(getContext().getResources().getDrawable(R$drawable.sesl_level_seekbar_tick_mark));
                Drawable drawable = getContext().getResources().getDrawable(R$drawable.sesl_level_seekbar_thumb);
                this.mLevelBarThumbDrawable = drawable;
                setThumb(drawable);
                setBackgroundResource(R$drawable.sesl_seek_bar_background_borderless);
            }
            invalidate();
            this.mIsSetModeCalled = true;
            return;
        }
        Log.w("SeslAbsSeekBar", "Seekbar mode is already set. Do not call this method redundant");
    }

    @Deprecated
    public void setOverlapBackgroundForDualColor(int i2) {
        ColorStateList colorToColorStateList = colorToColorStateList(i2);
        if (!colorToColorStateList.equals(this.mOverlapNormalProgressColor)) {
            this.mOverlapNormalProgressColor = colorToColorStateList;
        }
        this.mOverlapActivatedProgressColor = this.mOverlapNormalProgressColor;
        this.mLargeFont = true;
    }

    public void setOverlapPointForDualColor(int i2) {
        if (i2 < getMax()) {
            this.mSetDualColorMode = true;
            this.mOverlapPoint = i2;
            if (i2 == -1) {
                setProgressTintList(this.mDefaultActivatedProgressColor);
                setThumbTintList(this.mDefaultActivatedThumbColor);
            } else {
                if (this.mOverlapBackground == null) {
                    initDualOverlapDrawable();
                }
                updateDualColorMode();
            }
            invalidate();
        }
    }

    public synchronized void setProgress(int i2) {
        try {
            if (this.mIsSeamless) {
                i2 = Math.round(((float) i2) * 1000.0f);
            }
            super.setProgress(i2);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setProgressDrawable(Drawable drawable) {
        super.setProgressDrawable(drawable);
    }

    public boolean setProgressInternal(int i2, boolean z, boolean z3) {
        boolean progressInternal = super.setProgressInternal(i2, z, z3);
        updateWarningMode(i2);
        updateDualColorMode();
        return progressInternal;
    }

    public void setProgressTintList(ColorStateList colorStateList) {
        super.setProgressTintList(colorStateList);
        this.mDefaultActivatedProgressColor = colorStateList;
    }

    public void setSeamless(boolean z) {
        if (this.mIsSeamless != z) {
            this.mIsSeamless = z;
            if (z) {
                super.setMax(Math.round(((float) super.getMax()) * 1000.0f));
                super.setMin(Math.round(((float) super.getMin()) * 1000.0f));
                super.setProgress(Math.round(((float) super.getProgress()) * 1000.0f));
                super.setSecondaryProgress(Math.round(((float) super.getSecondaryProgress()) * 1000.0f));
                return;
            }
            super.setProgress(Math.round(((float) super.getProgress()) / 1000.0f));
            super.setSecondaryProgress(Math.round(((float) super.getSecondaryProgress()) / 1000.0f));
            super.setMax(Math.round(((float) super.getMax()) / 1000.0f));
            super.setMin(Math.round(((float) super.getMin()) / 1000.0f));
        }
    }

    public synchronized void setSecondaryProgress(int i2) {
        try {
            if (this.mIsSeamless) {
                i2 = Math.round(((float) i2) * 1000.0f);
            }
            super.setSecondaryProgress(i2);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.mSplitTrack = z;
        invalidate();
    }

    public void setSystemGestureExclusionRects(List<Rect> list) {
        Preconditions.checkNotNull(list, "rects must not be null");
        this.mUserGestureExclusionRects = list;
        updateGestureExclusionRects();
    }

    public void setThumb(Drawable drawable) {
        boolean z;
        Drawable drawable2 = this.mThumb;
        if (drawable2 == null || drawable == drawable2) {
            z = false;
        } else {
            drawable2.setCallback((Drawable.Callback) null);
            z = true;
        }
        if (drawable != null) {
            drawable.setCallback(this);
            if (canResolveLayoutDirection()) {
                DrawableCompat.setLayoutDirection(drawable, ViewCompat.getLayoutDirection(this));
            }
            int i2 = this.mCurrentMode;
            if (i2 == 3 || i2 == 6) {
                this.mThumbOffset = drawable.getIntrinsicHeight() / 2;
            } else {
                this.mThumbOffset = drawable.getIntrinsicWidth() / 2;
            }
            if (z && !(drawable.getIntrinsicWidth() == this.mThumb.getIntrinsicWidth() && drawable.getIntrinsicHeight() == this.mThumb.getIntrinsicHeight())) {
                requestLayout();
            }
        }
        this.mThumb = drawable;
        applyThumbTint();
        invalidate();
        if (z) {
            updateThumbAndTrackPos(getWidth(), getHeight());
            if (drawable != null && drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
    }

    public void setThumbOffset(int i2) {
        this.mThumbOffset = i2;
        invalidate();
    }

    public void setThumbTintColor(int i2) {
        ColorStateList colorToColorStateList = colorToColorStateList(i2);
        if (!colorToColorStateList.equals(this.mDefaultActivatedThumbColor)) {
            this.mDefaultActivatedThumbColor = colorToColorStateList;
        }
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.mThumbTintList = colorStateList;
        this.mHasThumbTint = true;
        applyThumbTint();
        this.mDefaultActivatedThumbColor = colorStateList;
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.mThumbTintMode = mode;
        this.mHasThumbTintMode = true;
        applyThumbTint();
    }

    public void setTickMark(Drawable drawable) {
        Drawable drawable2 = this.mTickMark;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.mTickMark = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            DrawableCompat.setLayoutDirection(drawable, ViewCompat.getLayoutDirection(this));
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
            applyTickMarkTint();
        }
        invalidate();
    }

    public void setTickMarkTintList(ColorStateList colorStateList) {
        this.mTickMarkTintList = colorStateList;
        this.mHasTickMarkTint = true;
        applyTickMarkTint();
    }

    public void setTickMarkTintMode(PorterDuff.Mode mode) {
        this.mTickMarkTintMode = mode;
        this.mHasTickMarkTintMode = true;
        applyTickMarkTint();
    }

    public void updateDrawableBounds(int i2, int i7) {
        super.updateDrawableBounds(i2, i7);
        updateThumbAndTrackPos(i2, i7);
        updateBoundsForDualColor();
    }

    public void updateHoverPopup() {
        SeslHoverPopupWindowReflector.update(SeslViewReflector.semGetHoverPopup(this, true));
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (drawable == this.mThumb || drawable == this.mTickMark || super.verifyDrawable(drawable)) {
            return true;
        }
        return false;
    }

    public void onKeyChange() {
    }

    public void onStopTrackingHover() {
    }

    public void onHoverChanged(int i2, int i7, int i8) {
    }

    public void onStartTrackingHover(int i2, int i7, int i8) {
    }
}
