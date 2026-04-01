package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import androidx.picker.R$color;
import androidx.picker.R$dimen;
import androidx.picker.R$drawable;
import androidx.picker.R$styleable;
import androidx.picker.util.SeslSleepTimePickerUtil;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslCircularSeekBarView extends View {
    private static final int DEFAULT_CIRCLE_STYLE = Paint.Cap.ROUND.ordinal();
    private static final int DEFAULT_FIRST_POINTER_COLOR = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, 133, 135, 254);
    private static final int DEFAULT_FIRST_POINTER_HALO_COLOR = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, 133, 135, 254);
    private static final int DEFAULT_MIDDLE_COLOR = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, 133, 135, 254);
    private static final int DEFAULT_SECOND_POINTER_COLOR = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER, 167, 0);
    private static final int DEFAULT_SECOND_POINTER_HALO_COLOR = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER, 167, 0);
    private final float DPTOPX_SCALE = getResources().getDisplayMetrics().density;
    private final PathInterpolator EXPAND_COLLAPSE_PATH_INTERPOLATOR = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
    private final AttributeSet mAttributeSet;
    private Drawable mBedTimeDrawable;
    private final RectF mBedTimeIconRectF = new RectF();
    private int mCircleColor;
    private int mCircleFillColor;
    private Paint mCircleFillPaint;
    private Path mCircleFirstPointerPath;
    private int mCircleGridMedium;
    private int mCircleGridSmall;
    private float mCircleHeight;
    private Paint mCircleLineProgressPaint;
    private Path mCircleLineProgressPath;
    private Paint mCirclePaint;
    private Path mCirclePath;
    private Paint mCircleProgressPaint;
    private Path mCircleProgressPath;
    private final RectF mCircleRectF = new RectF();
    private Path mCircleSecondPointerPath;
    private float mCircleStrokeWidth;
    private Paint.Cap mCircleStyle;
    private float mCircleWidth;
    private SeslCircularSeekBarRevealAnimation mCircularSeekBarRevealAnimation;
    private float mDashLineStrokeWidth;
    private final int mDefStyle;
    private float mEndAngle;
    private float mFirstPointerAngle;
    private int mFirstPointerColor;
    private int mFirstPointerHaloColor;
    private Paint mFirstPointerHaloPaint;
    private Paint mFirstPointerPaint;
    private float mFirstPointerPosition;
    private Paint mGridPaintMedium;
    private Paint mGridPaintSmall;
    private float mHandlerTouchPosition;
    private boolean mHideProgressWhenEmpty;
    private float mIconSize;
    private boolean mIsExpandCollapseAnimation = false;
    private int mLastPointerTouched;
    private boolean mLockAtEnd = false;
    private boolean mLockAtStart = true;
    private boolean mLockEnabled = true;
    private boolean mMaintainEqualCircle;
    private float mMax;
    private int mMiddleGradientColor;
    private boolean mMoveOutsideCircle;
    private int mPaddingHorizontal = 0;
    private float mPointerHaloWidth;
    private float mPointerStrokeWidth;
    private float mProgress;
    private float mProgressDegrees;
    private float mRadiusIn;
    private float mRadiusOut;
    private float mSecondPointerAngle;
    private int mSecondPointerColor;
    private int mSecondPointerHaloColor;
    private Paint mSecondPointerHaloPaint;
    private Paint mSecondPointerPaint;
    private float mSecondPointerPosition;
    private boolean mSleepGoalWheelEnable = false;
    private float mSleepGoalWheelExtendDegree;
    private float mSleepGoalWheelExtendStart;
    private Paint mSleepGoalWheelPaint;
    private Path mSleepGoalWheelPath;
    private final RectF mSleepGoalWheelRectF = new RectF();
    private float mSleepGoalWheelStrokeWidth;
    private float mStartAngle;
    private SweepGradientVariable mSweepGradientVariable;
    private float mTotalCircleDegrees;
    private float mTouchDistanceFromFirstPointer;
    private float mTouchDistanceFromSecondPointer;
    private TouchEventVariable mTouchEventVariable;
    private boolean mUserIsMovingFirstPointer = false;
    private boolean mUserIsMovingMiddleHandler = false;
    private boolean mUserIsMovingSecondPointer = false;
    private Drawable mWakeUpDrawable;
    private final RectF mWakeUpTimeIconRectF = new RectF();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SweepGradientVariable {
        final int[] color = new int[5];
        final float[] pos = new float[5];
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TouchEventVariable {
        float additionalRadius;
        float ccwDistanceFromEnd;
        float ccwDistanceFromStart;
        float cwDistanceFromEnd;
        float cwDistanceFromStart;
        float cwPointerFromStart;
        float distanceX;
        float distanceY;
        float innerRadius;
        float minimumTouchTarget;
        float outerRadius;
        boolean pointerNearEnd;
        boolean pointerNearStart;
        float smallInCircle;
        float touchAngle;
        float touchEventRadius;
        boolean touchOverEnd;
        boolean touchOverStart;

        /* renamed from: x  reason: collision with root package name */
        float f1031x;
        float y;
    }

    public SeslCircularSeekBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAttributeSet = attributeSet;
        this.mDefStyle = 0;
        init();
    }

    private void calculateHandlerPosition() {
        float f = this.mHandlerTouchPosition;
        float f5 = f - this.mTouchDistanceFromFirstPointer;
        this.mFirstPointerPosition = f5;
        if (f5 < 0.0f) {
            f5 += 360.0f;
        }
        this.mFirstPointerPosition = f5 % 360.0f;
        float f8 = f + this.mTouchDistanceFromSecondPointer;
        this.mSecondPointerPosition = f8;
        if (f8 < 0.0f) {
            f8 += 360.0f;
        }
        this.mSecondPointerPosition = f8 % 360.0f;
    }

    private void calculatePointerPosition(int i2) {
        float f = (this.mProgress / this.mMax) * 360.0f;
        if (i2 == 1) {
            float f5 = this.mSecondPointerPosition - f;
            this.mFirstPointerPosition = f5;
            if (f5 < 0.0f) {
                f5 += 360.0f;
            }
            this.mFirstPointerPosition = f5 % 360.0f;
        } else if (i2 == 0) {
            float f8 = this.mFirstPointerPosition + f;
            this.mSecondPointerPosition = f8;
            if (f8 < 0.0f) {
                f8 += 360.0f;
            }
            this.mSecondPointerPosition = f8 % 360.0f;
        }
    }

    private void calculateProgressDegrees() {
        float f = this.mSecondPointerPosition - this.mFirstPointerPosition;
        this.mProgressDegrees = f;
        if (f < 0.0f) {
            f += 360.0f;
        }
        this.mProgressDegrees = f;
    }

    private void calculateTotalDegrees() {
        float f = (360.0f - (this.mStartAngle - this.mEndAngle)) % 360.0f;
        this.mTotalCircleDegrees = f;
        if (f <= 0.0f) {
            this.mTotalCircleDegrees = 360.0f;
        }
    }

    private void drawFirstPointer(Canvas canvas) {
        RectF rectF;
        canvas.drawPath(this.mCircleFirstPointerPath, this.mFirstPointerPaint);
        if (this.mIsExpandCollapseAnimation || this.mUserIsMovingFirstPointer) {
            canvas.drawPath(this.mCircleFirstPointerPath, this.mFirstPointerHaloPaint);
        }
        Drawable drawable = this.mBedTimeDrawable;
        if (drawable != null && (rectF = this.mBedTimeIconRectF) != null) {
            drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            this.mBedTimeDrawable.draw(canvas);
        }
    }

    private void drawSecondPointer(Canvas canvas) {
        RectF rectF;
        canvas.drawPath(this.mCircleSecondPointerPath, this.mSecondPointerPaint);
        if (this.mIsExpandCollapseAnimation || this.mUserIsMovingSecondPointer) {
            canvas.drawPath(this.mCircleSecondPointerPath, this.mSecondPointerHaloPaint);
        }
        Drawable drawable = this.mWakeUpDrawable;
        if (drawable != null && (rectF = this.mWakeUpTimeIconRectF) != null) {
            drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            this.mWakeUpDrawable.draw(canvas);
        }
    }

    private void init() {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(this.mAttributeSet, R$styleable.seslCircularSeekBar, this.mDefStyle, 0);
        if (obtainStyledAttributes != null) {
            initAttributes(obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }
        initDrawableIcons();
        initPaints();
        initPaths();
        this.mTouchEventVariable = new TouchEventVariable();
        this.mSweepGradientVariable = new SweepGradientVariable();
    }

    private void initAttributes(TypedArray typedArray) {
        this.mPointerStrokeWidth = typedArray.getDimension(R$styleable.seslCircularSeekBar_csPointerStrokeWidth, 65.0f);
        this.mIconSize = typedArray.getDimension(R$styleable.seslCircularSeekBar_csIconWidth, 50.0f);
        this.mPointerHaloWidth = typedArray.getDimension(R$styleable.seslCircularSeekBar_csPointerHaloWidth, 15.0f);
        this.mCircleStrokeWidth = typedArray.getDimension(R$styleable.seslCircularSeekBar_csCircleStrokeWidth, 15.0f);
        this.mSleepGoalWheelStrokeWidth = getResources().getDimension(R$dimen.sesl_sleep_goal_wheel_width);
        this.mDashLineStrokeWidth = getResources().getDimension(R$dimen.sesl_dot_line_stroke_width);
        this.mCircleStyle = Paint.Cap.values()[typedArray.getInt(R$styleable.seslCircularSeekBar_CircleStyle, DEFAULT_CIRCLE_STYLE)];
        this.mMiddleGradientColor = typedArray.getColor(R$styleable.seslCircularSeekBar_csMiddleColor, DEFAULT_MIDDLE_COLOR);
        this.mFirstPointerColor = typedArray.getColor(R$styleable.seslCircularSeekBar_csFirstPointerColor, DEFAULT_FIRST_POINTER_COLOR);
        this.mFirstPointerHaloColor = typedArray.getColor(R$styleable.seslCircularSeekBar_csFirstPointerHaloColor, DEFAULT_FIRST_POINTER_HALO_COLOR);
        this.mSecondPointerColor = typedArray.getColor(R$styleable.seslCircularSeekBar_csSecondPointerColor, DEFAULT_SECOND_POINTER_COLOR);
        this.mSecondPointerHaloColor = typedArray.getColor(R$styleable.seslCircularSeekBar_csSecondPointerHaloColor, DEFAULT_SECOND_POINTER_HALO_COLOR);
        this.mCircleColor = typedArray.getColor(R$styleable.seslCircularSeekBar_csCircleColor, -3355444);
        this.mCircleFillColor = typedArray.getColor(R$styleable.seslCircularSeekBar_csCircleFill, 0);
        this.mCircleGridSmall = typedArray.getColor(R$styleable.seslCircularSeekBar_csCircleGridSmallColor, -3355444);
        this.mCircleGridMedium = typedArray.getColor(R$styleable.seslCircularSeekBar_csCircleGridMediumColor, -7829368);
        this.mMax = (float) typedArray.getInt(R$styleable.seslCircularSeekBar_csMax, 100);
        this.mProgress = (float) typedArray.getInt(R$styleable.seslCircularSeekBar_csProgress, 40);
        this.mMaintainEqualCircle = typedArray.getBoolean(R$styleable.seslCircularSeekBar_csMaintainEqualCircle, true);
        this.mMoveOutsideCircle = typedArray.getBoolean(R$styleable.seslCircularSeekBar_csMoveOutsideCircle, true);
        this.mLockEnabled = typedArray.getBoolean(R$styleable.seslCircularSeekBar_csLockEnabled, true);
        this.mHideProgressWhenEmpty = typedArray.getBoolean(R$styleable.seslCircularSeekBar_csHideProgressWhenEmpty, false);
        this.mSecondPointerPosition = 7.5f;
        this.mFirstPointerPosition = 225.0f;
        this.mStartAngle = ((typedArray.getFloat(R$styleable.seslCircularSeekBar_csStartAngle, 270.0f) % 360.0f) + 360.0f) % 360.0f;
        float f = ((typedArray.getFloat(R$styleable.seslCircularSeekBar_csEndAngle, 270.0f) % 360.0f) + 360.0f) % 360.0f;
        this.mEndAngle = f;
        if (this.mStartAngle % 360.0f == f % 360.0f) {
            this.mEndAngle = f - 0.1f;
        }
        int i2 = R$styleable.seslCircularSeekBar_csPointerAngle;
        float f5 = ((typedArray.getFloat(i2, 0.0f) % 360.0f) + 360.0f) % 360.0f;
        this.mSecondPointerAngle = f5;
        if (f5 == 0.0f) {
            this.mSecondPointerAngle = 0.1f;
        }
        float f8 = ((typedArray.getFloat(i2, 0.0f) % 360.0f) + 360.0f) % 360.0f;
        this.mFirstPointerAngle = f8;
        if (f8 == 0.0f) {
            this.mFirstPointerAngle = 0.1f;
        }
        this.mCircularSeekBarRevealAnimation = new SeslCircularSeekBarRevealAnimation(this);
    }

    private void initDrawableIcons() {
        Drawable drawable;
        Drawable.ConstantState constantState = getResources().getDrawable(R$drawable.sesl_bedtime, (Resources.Theme) null).mutate().getConstantState();
        Objects.requireNonNull(constantState);
        this.mBedTimeDrawable = constantState.newDrawable().mutate();
        Drawable.ConstantState constantState2 = getResources().getDrawable(R$drawable.sesl_wakeup, (Resources.Theme) null).mutate().getConstantState();
        Objects.requireNonNull(constantState2);
        this.mWakeUpDrawable = constantState2.newDrawable().mutate();
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(getContext().getResources().getColor(R$color.sesl_picker_thumb_icon_color), PorterDuff.Mode.SRC_ATOP);
        if (this.mBedTimeDrawable != null && (drawable = this.mWakeUpDrawable) != null) {
            drawable.setColorFilter(porterDuffColorFilter);
            this.mBedTimeDrawable.setColorFilter(porterDuffColorFilter);
        }
    }

    private void initPaints() {
        setCirclePaint();
        setCircleFillPaint();
        setCircleProgressPaint();
        setSleepGoalWheelPaint();
        setSecondPointerPaint();
        setFirstPointerPaint();
        setClockGridPaint();
        setDotLinePaint();
    }

    private void initPaths() {
        this.mCirclePath = new Path();
        this.mCircleProgressPath = new Path();
        this.mCircleLineProgressPath = new Path();
        this.mCircleSecondPointerPath = new Path();
        this.mCircleFirstPointerPath = new Path();
        this.mSleepGoalWheelPath = new Path();
    }

    private void initTouchOnMiddleHandler() {
        float f = this.mHandlerTouchPosition;
        this.mTouchDistanceFromFirstPointer = f - this.mFirstPointerPosition;
        this.mTouchDistanceFromSecondPointer = this.mSecondPointerPosition - f;
    }

    private boolean onActionMove(float f, float f5, float f8) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        TouchEventVariable touchEventVariable = this.mTouchEventVariable;
        float f10 = this.mTotalCircleDegrees;
        float f11 = f10 / 3.0f;
        touchEventVariable.smallInCircle = f11;
        float f12 = this.mSecondPointerPosition;
        float f13 = this.mFirstPointerPosition;
        float f14 = f12 - f13;
        touchEventVariable.cwPointerFromStart = f14;
        if (f14 < 0.0f) {
            f14 += 360.0f;
        }
        touchEventVariable.cwPointerFromStart = f14;
        boolean z10 = false;
        if (f14 < f11) {
            z = true;
        } else {
            z = false;
        }
        touchEventVariable.pointerNearStart = z;
        if (f14 > f10 - f11) {
            z3 = true;
        } else {
            z3 = false;
        }
        touchEventVariable.pointerNearEnd = z3;
        if (this.mUserIsMovingSecondPointer) {
            float f15 = f8 - ((f13 + 2.5f) % 360.0f);
            touchEventVariable.cwDistanceFromStart = f15;
            if (f15 < 0.0f) {
                f15 += 360.0f;
            }
            touchEventVariable.cwDistanceFromStart = f15;
            float f16 = 360.0f - f15;
            touchEventVariable.ccwDistanceFromStart = f16;
            float f17 = f8 - (((f13 - 2.5f) + 360.0f) % 360.0f);
            touchEventVariable.cwDistanceFromEnd = f17;
            if (f17 < 0.0f) {
                f17 += 360.0f;
            }
            touchEventVariable.cwDistanceFromEnd = f17;
            if (f16 < f11) {
                z9 = true;
            } else {
                z9 = false;
            }
            touchEventVariable.touchOverStart = z9;
            if (f17 < f11) {
                z10 = true;
            }
            touchEventVariable.touchOverEnd = z10;
            this.mLockEnabled = true;
        } else if (this.mUserIsMovingFirstPointer) {
            float f18 = f8 - (((f12 - 2.5f) + 360.0f) % 360.0f);
            touchEventVariable.cwDistanceFromStart = f18;
            if (f18 < 0.0f) {
                f18 += 360.0f;
            }
            touchEventVariable.cwDistanceFromStart = f18;
            float f19 = f8 - ((f12 + 2.5f) % 360.0f);
            touchEventVariable.cwDistanceFromEnd = f19;
            if (f19 < 0.0f) {
                f19 += 360.0f;
            }
            touchEventVariable.cwDistanceFromEnd = f19;
            float f20 = 360.0f - f19;
            touchEventVariable.ccwDistanceFromEnd = f20;
            if (f18 < f11) {
                z7 = true;
            } else {
                z7 = false;
            }
            touchEventVariable.touchOverStart = z7;
            if (f20 < f11) {
                z10 = true;
            }
            touchEventVariable.touchOverEnd = z10;
            this.mLockEnabled = true;
        } else if (!this.mUserIsMovingMiddleHandler) {
            return false;
        } else {
            this.mLockAtEnd = false;
            this.mLockAtStart = false;
            this.mLockEnabled = false;
        }
        if (z3) {
            this.mLockAtEnd = touchEventVariable.touchOverEnd;
        } else if (z) {
            this.mLockAtStart = touchEventVariable.touchOverStart;
        }
        if (!this.mLockAtStart || !this.mLockEnabled) {
            if (this.mLockAtEnd && this.mLockEnabled) {
                float f21 = this.mProgress;
                float f22 = this.mMax;
                if (f21 != f22 - 0.6944445f) {
                    this.mProgress = f22 - 0.6944445f;
                    recalculateAll();
                    invalidate();
                    addPointerTouchListener();
                    SeslSleepTimePickerUtil.performHapticFeedback(this, 49);
                }
            } else if (this.mMoveOutsideCircle || f5 <= f) {
                boolean z11 = this.mUserIsMovingFirstPointer;
                if (this.mUserIsMovingMiddleHandler) {
                    setProgressBasedOnAngle(f8, 2);
                } else {
                    setProgressBasedOnAngle(f8, z11 ? 1 : 0);
                }
                recalculateAll();
                invalidate();
                addPointerTouchListener();
            }
        } else if (this.mProgress != 0.6944445f) {
            this.mProgress = 0.6944445f;
            recalculateAll();
            invalidate();
            addPointerTouchListener();
            SeslSleepTimePickerUtil.performHapticFeedback(this, 49);
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    private boolean onActionUpCancel() {
        boolean z = this.mUserIsMovingSecondPointer;
        if (!z && !this.mUserIsMovingFirstPointer && !this.mUserIsMovingMiddleHandler) {
            return false;
        }
        if (z) {
            throw null;
        } else if (this.mUserIsMovingFirstPointer) {
            throw null;
        } else if (this.mUserIsMovingMiddleHandler) {
            throw null;
        } else {
            this.mUserIsMovingSecondPointer = false;
            this.mUserIsMovingFirstPointer = false;
            this.mUserIsMovingMiddleHandler = false;
            throw null;
        }
    }

    private void recalculateAll() {
        calculateTotalDegrees();
        if (this.mUserIsMovingSecondPointer) {
            calculatePointerPosition(0);
        } else if (this.mUserIsMovingFirstPointer) {
            calculatePointerPosition(1);
        } else if (this.mUserIsMovingMiddleHandler) {
            calculateHandlerPosition();
        }
        calculateProgressDegrees();
        resetRects();
        resetPaths();
        findBedTimeIconLocation();
        findWakeUpTimeIconLocation();
    }

    private void resetPaths() {
        this.mCirclePath.reset();
        this.mCirclePath.addArc(this.mCircleRectF, this.mStartAngle, this.mTotalCircleDegrees);
        float f = this.mFirstPointerPosition;
        float f5 = this.mSecondPointerAngle;
        float f8 = f - (f5 / 2.0f);
        float f10 = this.mSecondPointerPosition - (this.mFirstPointerAngle / 2.0f);
        float f11 = this.mProgressDegrees + f5;
        if (f11 >= 360.0f) {
            f11 = 359.9f;
        }
        this.mCircleProgressPath.reset();
        this.mCircleProgressPath.addArc(this.mCircleRectF, f8, f11);
        if (this.mSleepGoalWheelEnable) {
            this.mSleepGoalWheelPath.reset();
            this.mSleepGoalWheelPath.addArc(this.mSleepGoalWheelRectF, this.mSleepGoalWheelExtendStart, this.mSleepGoalWheelExtendDegree);
        }
        this.mCircleLineProgressPath.reset();
        if (((double) this.mProgress) > 6.5d) {
            if (this.mUserIsMovingSecondPointer) {
                this.mCircleLineProgressPath.addArc(this.mCircleRectF, f10, -f11);
            } else {
                this.mCircleLineProgressPath.addArc(this.mCircleRectF, f8, f11);
            }
        }
        float f12 = this.mSecondPointerPosition - (this.mSecondPointerAngle / 2.0f);
        this.mCircleSecondPointerPath.reset();
        this.mCircleSecondPointerPath.addArc(this.mCircleRectF, f12, this.mSecondPointerAngle);
        float f13 = this.mFirstPointerPosition - (this.mFirstPointerAngle / 2.0f);
        this.mCircleFirstPointerPath.reset();
        this.mCircleFirstPointerPath.addArc(this.mCircleRectF, f13, this.mFirstPointerAngle);
    }

    private void resetRects() {
        RectF rectF = this.mCircleRectF;
        float f = this.mCircleWidth;
        float f5 = this.mCircleHeight;
        rectF.set(-f, -f5, f, f5);
        this.mSleepGoalWheelRectF.left = this.mCircleRectF.centerX() - (this.mRadiusIn - 5.0f);
        this.mSleepGoalWheelRectF.top = this.mCircleRectF.centerY() - (this.mRadiusIn - 5.0f);
        this.mSleepGoalWheelRectF.right = (this.mRadiusIn - 5.0f) + this.mCircleRectF.centerY();
        this.mSleepGoalWheelRectF.bottom = (this.mRadiusIn - 5.0f) + this.mCircleRectF.centerY();
    }

    private void setCircleFillPaint() {
        Paint paint = new Paint();
        this.mCircleFillPaint = paint;
        paint.setAntiAlias(true);
        this.mCircleFillPaint.setDither(true);
        this.mCircleFillPaint.setColor(this.mCircleFillColor);
        this.mCircleFillPaint.setStyle(Paint.Style.FILL);
    }

    private void setCirclePaint() {
        Paint paint = new Paint();
        this.mCirclePaint = paint;
        paint.setAntiAlias(true);
        this.mCirclePaint.setDither(true);
        this.mCirclePaint.setColor(this.mCircleColor);
        this.mCirclePaint.setStrokeWidth(this.mCircleStrokeWidth);
        this.mCirclePaint.setStyle(Paint.Style.STROKE);
        this.mCirclePaint.setStrokeJoin(Paint.Join.ROUND);
        this.mCirclePaint.setStrokeCap(this.mCircleStyle);
    }

    private void setCircleProgressPaint() {
        Paint paint = new Paint();
        this.mCircleProgressPaint = paint;
        paint.setAntiAlias(true);
        this.mCircleProgressPaint.setDither(true);
        this.mCircleProgressPaint.setStrokeWidth(this.mCircleStrokeWidth);
        this.mCircleProgressPaint.setStyle(Paint.Style.STROKE);
        this.mCircleProgressPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mCircleProgressPaint.setStrokeCap(this.mCircleStyle);
    }

    private void setClockGridPaint() {
        Paint paint = new Paint(1);
        this.mGridPaintSmall = paint;
        paint.setStrokeWidth(this.DPTOPX_SCALE * 1.0f);
        this.mGridPaintSmall.setColor(this.mCircleGridSmall);
        this.mGridPaintSmall.setAlpha(76);
        Paint paint2 = this.mGridPaintSmall;
        Paint.Style style = Paint.Style.STROKE;
        paint2.setStyle(style);
        Paint paint3 = new Paint(1);
        this.mGridPaintMedium = paint3;
        paint3.setStrokeWidth(this.DPTOPX_SCALE * 1.0f);
        this.mGridPaintMedium.setColor(this.mCircleGridMedium);
        this.mGridPaintMedium.setAlpha(178);
        this.mGridPaintMedium.setStyle(style);
    }

    private void setDotLinePaint() {
        Path path = new Path();
        float f = this.mDashLineStrokeWidth / 2.0f;
        path.addCircle(f, 0.0f, f, Path.Direction.CW);
        Paint paint = new Paint();
        this.mCircleLineProgressPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mCircleLineProgressPaint.setStrokeWidth(this.mDashLineStrokeWidth);
        this.mCircleLineProgressPaint.setColor(getResources().getColor(R$color.sesl_dotted_line_color));
        this.mCircleLineProgressPaint.setAlpha(178);
        this.mCircleLineProgressPaint.setPathEffect(new PathDashPathEffect(path, getResources().getDimension(R$dimen.sesl_dot_line_gap_width) + this.mDashLineStrokeWidth, 0.0f, PathDashPathEffect.Style.ROTATE));
    }

    private void setFirstPointerPaint() {
        Paint paint = new Paint();
        this.mFirstPointerPaint = paint;
        paint.set(this.mSecondPointerPaint);
        this.mFirstPointerPaint.setColor(this.mFirstPointerColor);
        Paint paint2 = new Paint();
        this.mFirstPointerHaloPaint = paint2;
        paint2.set(this.mSecondPointerHaloPaint);
        this.mFirstPointerHaloPaint.setColor(this.mFirstPointerHaloColor);
        this.mFirstPointerHaloPaint.setStrokeWidth(this.mPointerStrokeWidth);
    }

    private void setProgressBasedOnAngle(float f, int i2) {
        if (i2 == 0) {
            this.mSecondPointerPosition = f;
        } else if (i2 == 1) {
            this.mFirstPointerPosition = f;
        } else if (i2 == 2) {
            this.mHandlerTouchPosition = f;
            float f5 = f - this.mTouchDistanceFromFirstPointer;
            this.mFirstPointerPosition = f5;
            if (f5 < 0.0f) {
                f5 += 360.0f;
            }
            this.mFirstPointerPosition = f5 % 360.0f;
            float f8 = f + this.mTouchDistanceFromSecondPointer;
            this.mSecondPointerPosition = f8;
            if (f8 < 0.0f) {
                f8 += 360.0f;
            }
            this.mSecondPointerPosition = f8 % 360.0f;
        }
        calculateProgressDegrees();
        this.mProgress = (this.mMax * this.mProgressDegrees) / this.mTotalCircleDegrees;
    }

    private void setSecondPointerPaint() {
        Paint paint = new Paint();
        this.mSecondPointerPaint = paint;
        paint.setAntiAlias(true);
        this.mSecondPointerPaint.setDither(true);
        this.mSecondPointerPaint.setColor(this.mSecondPointerColor);
        this.mSecondPointerPaint.setStrokeWidth(this.mPointerStrokeWidth);
        this.mSecondPointerPaint.setStyle(Paint.Style.STROKE);
        this.mSecondPointerPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mSecondPointerPaint.setStrokeCap(this.mCircleStyle);
        Paint paint2 = new Paint();
        this.mSecondPointerHaloPaint = paint2;
        paint2.set(this.mSecondPointerPaint);
        this.mSecondPointerHaloPaint.setColor(this.mSecondPointerHaloColor);
        this.mSecondPointerHaloPaint.setStrokeWidth(this.mPointerStrokeWidth);
    }

    private void setSleepGoalWheelPaint() {
        Paint paint = new Paint();
        this.mSleepGoalWheelPaint = paint;
        paint.setAntiAlias(true);
        this.mSleepGoalWheelPaint.setDither(true);
        this.mSleepGoalWheelPaint.setStrokeWidth(this.mSleepGoalWheelStrokeWidth);
        this.mSleepGoalWheelPaint.setStyle(Paint.Style.STROKE);
        this.mSleepGoalWheelPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mSleepGoalWheelPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mSleepGoalWheelPaint.setColor(getResources().getColor(R$color.sesl_sleep_goal_wheel_color));
    }

    public void drawClockGrid(Canvas canvas) {
        for (double d = 0.0d; d <= 360.0d; d += 2.5d) {
            double d2 = ((((double) this.mStartAngle) + d) / 180.0d) * 3.141592653589793d;
            float cos = (float) ((Math.cos(d2) * ((double) (this.mRadiusIn - (this.DPTOPX_SCALE * 2.5f)))) + ((double) this.mCircleRectF.centerX()));
            float sin = (float) ((Math.sin(d2) * ((double) (this.mRadiusIn - (this.DPTOPX_SCALE * 2.5f)))) + ((double) this.mCircleRectF.centerY()));
            float cos2 = (float) ((Math.cos(d2) * ((double) ((this.DPTOPX_SCALE * 2.5f) + this.mRadiusIn))) + ((double) this.mCircleRectF.centerX()));
            float sin2 = (float) ((Math.sin(d2) * ((double) ((this.DPTOPX_SCALE * 2.5f) + this.mRadiusIn))) + ((double) this.mCircleRectF.centerY()));
            double d3 = d % 90.0d;
            if (!(d3 == MapUtil.INVALID_LOCATION || d3 == 2.5d || d3 == 3.0d || d3 == 87.0d || d3 == 87.5d || d == 175.0d || d == 185.0d)) {
                if (d % 15.0d == MapUtil.INVALID_LOCATION) {
                    canvas.drawLine(cos, sin, cos2, sin2, this.mGridPaintMedium);
                } else {
                    canvas.drawLine(cos, sin, cos2, sin2, this.mGridPaintSmall);
                }
            }
        }
    }

    public void findBedTimeIconLocation() {
        double d = ((double) (this.mFirstPointerPosition / 180.0f)) * 3.141592653589793d;
        this.mBedTimeIconRectF.left = ((float) ((Math.cos(d) * ((double) this.mRadiusOut)) + ((double) this.mCircleRectF.centerX()))) - (this.mIconSize / 2.0f);
        RectF rectF = this.mBedTimeIconRectF;
        double sin = Math.sin(d) * ((double) this.mRadiusOut);
        float f = this.mIconSize;
        rectF.top = ((float) (sin + ((double) this.mCircleRectF.centerY()))) - (f / 2.0f);
        RectF rectF2 = this.mBedTimeIconRectF;
        rectF2.right = rectF2.left + f;
        rectF2.bottom = rectF2.top + f;
    }

    public void findWakeUpTimeIconLocation() {
        double d = ((double) (this.mSecondPointerPosition / 180.0f)) * 3.141592653589793d;
        this.mWakeUpTimeIconRectF.left = ((float) ((Math.cos(d) * ((double) this.mRadiusOut)) + ((double) this.mCircleRectF.centerX()))) - (this.mIconSize / 2.0f);
        RectF rectF = this.mWakeUpTimeIconRectF;
        double sin = Math.sin(d) * ((double) this.mRadiusOut);
        float f = this.mIconSize;
        rectF.top = ((float) (sin + ((double) this.mCircleRectF.centerY()))) - (f / 2.0f);
        RectF rectF2 = this.mWakeUpTimeIconRectF;
        rectF2.right = rectF2.left + f;
        rectF2.bottom = rectF2.top + f;
    }

    public boolean onActionDown(float f, float f5, float f8, float f10) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        float max = Math.max((float) (((double) (this.mPointerStrokeWidth * 180.0f)) / (((double) Math.max(this.mCircleHeight, this.mCircleWidth)) * 3.141592653589793d)), this.mSecondPointerAngle / 2.0f);
        float f11 = f - this.mSecondPointerPosition;
        if (f11 < 0.0f) {
            f11 += 360.0f;
        }
        float f12 = 360.0f - f11;
        float f13 = this.mFirstPointerPosition;
        float f14 = f - f13;
        if (f14 < 0.0f) {
            f14 += 360.0f;
        }
        float f15 = 360.0f - f14;
        if (f5 < f8 || f5 > f10) {
            z = false;
        } else {
            z = true;
        }
        if (f11 <= max || f12 <= max) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (f14 <= max || f15 <= max) {
            z7 = true;
        } else {
            z7 = false;
        }
        float convertToTime = SeslSleepTimePickerUtil.convertToTime(f13);
        float convertToTime2 = SeslSleepTimePickerUtil.convertToTime(this.mSecondPointerPosition);
        float convertToTime3 = SeslSleepTimePickerUtil.convertToTime(f);
        if (convertToTime >= convertToTime2 ? convertToTime <= convertToTime2 || ((convertToTime3 <= convertToTime || convertToTime3 > 1440.0f) && (convertToTime3 >= convertToTime2 || convertToTime3 <= 0.0f)) : convertToTime3 <= convertToTime || convertToTime3 >= convertToTime2) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (!z || !z3 || !z7) {
            if (z && z3) {
                initTouchOnSecondPointer();
            } else if (z && z7) {
                initTouchOnFirstPointer();
            } else if (!z || !z9) {
                this.mUserIsMovingSecondPointer = false;
                this.mUserIsMovingFirstPointer = false;
                this.mUserIsMovingMiddleHandler = false;
                return false;
            } else {
                this.mHandlerTouchPosition = f;
                initTouchOnMiddleHandler();
            }
        } else if (this.mLastPointerTouched == 0) {
            initTouchOnFirstPointer();
        } else {
            initTouchOnSecondPointer();
        }
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        requestLayout();
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
        if (this.mSleepGoalWheelEnable) {
            canvas.drawPath(this.mSleepGoalWheelPath, this.mSleepGoalWheelPaint);
        }
        canvas.drawPath(this.mCirclePath, this.mCircleFillPaint);
        canvas.drawPath(this.mCirclePath, this.mCirclePaint);
        drawClockGrid(canvas);
        SweepGradientVariable sweepGradientVariable = this.mSweepGradientVariable;
        int[] iArr = sweepGradientVariable.color;
        int i2 = this.mFirstPointerColor;
        iArr[0] = i2;
        iArr[1] = i2;
        iArr[2] = this.mMiddleGradientColor;
        int i7 = this.mSecondPointerColor;
        iArr[3] = i7;
        iArr[4] = i7;
        float[] fArr = sweepGradientVariable.pos;
        fArr[0] = 0.0f;
        float f = this.mProgress / this.mMax;
        fArr[1] = 0.1f * f;
        fArr[2] = 0.5f * f;
        fArr[3] = 0.9f * f;
        fArr[4] = f;
        float centerX = this.mCircleRectF.centerX();
        float centerY = this.mCircleRectF.centerY();
        SweepGradientVariable sweepGradientVariable2 = this.mSweepGradientVariable;
        SweepGradient sweepGradient = new SweepGradient(centerX, centerY, sweepGradientVariable2.color, sweepGradientVariable2.pos);
        Matrix matrix = new Matrix();
        matrix.setRotate(this.mFirstPointerPosition, this.mCircleRectF.centerX(), this.mCircleRectF.centerY());
        sweepGradient.setLocalMatrix(matrix);
        this.mCircleProgressPaint.setShader(sweepGradient);
        canvas.drawPath(this.mCircleProgressPath, this.mCircleProgressPaint);
        canvas.drawPath(this.mCircleLineProgressPath, this.mCircleLineProgressPaint);
        if (this.mLastPointerTouched == 0) {
            drawSecondPointer(canvas);
            drawFirstPointer(canvas);
            return;
        }
        drawFirstPointer(canvas);
        drawSecondPointer(canvas);
    }

    public void onMeasure(int i2, int i7) {
        int defaultSize = View.getDefaultSize(getSuggestedMinimumHeight(), i7);
        int defaultSize2 = View.getDefaultSize(getSuggestedMinimumWidth(), i2);
        if (defaultSize == 0) {
            defaultSize = defaultSize2;
        }
        if (defaultSize2 == 0) {
            defaultSize2 = defaultSize;
        }
        if (this.mMaintainEqualCircle) {
            int min = Math.min(defaultSize2, defaultSize);
            setMeasuredDimension(min, min);
        } else {
            setMeasuredDimension(defaultSize2, defaultSize);
        }
        this.mPointerStrokeWidth = getResources().getDimension(R$dimen.sesl_sleep_time_pointer_size);
        float dimension = getResources().getDimension(R$dimen.sesl_sleep_time_icon_touch_width);
        this.mPointerHaloWidth = dimension;
        float f = (this.mPointerStrokeWidth / 2.0f) + dimension;
        float f5 = ((float) getResources().getConfiguration().screenWidthDp) * getResources().getDisplayMetrics().density;
        float dimension2 = getResources().getDimension(R$dimen.sesl_sleep_visual_edit_outer_circle_size);
        if (SeslSleepTimePickerUtil.needBedTimePickerAdjustment((float) getResources().getConfiguration().screenHeightDp)) {
            dimension2 = (float) ((int) getResources().getDimension(R$dimen.sesl_sleep_visual_edit_outer_circle_min_size));
        }
        float f8 = (f5 / 2.0f) - (((float) this.mPaddingHorizontal) + f);
        this.mCircleWidth = f8;
        float f10 = (dimension2 / 2.0f) - f;
        this.mCircleHeight = f10;
        if (this.mMaintainEqualCircle) {
            float min2 = Math.min(f10, f8);
            this.mCircleHeight = min2;
            this.mCircleWidth = min2;
        }
        this.mRadiusOut = this.mCircleHeight;
        this.mRadiusIn = ((this.mCircleHeight - (this.mPointerStrokeWidth / 2.0f)) - this.mPointerHaloWidth) - getResources().getDimension(R$dimen.sesl_sleep_picker_inner_grid_offset);
        recalculateAll();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("PARENT"));
        this.mMax = bundle.getFloat("MAX");
        this.mProgress = bundle.getFloat("PROGRESS");
        this.mProgressDegrees = bundle.getFloat("mProgressDegrees");
        this.mSecondPointerPosition = bundle.getFloat("mSecondPointerPosition");
        this.mFirstPointerPosition = bundle.getFloat("mFirstPointerPosition");
        this.mSecondPointerAngle = bundle.getFloat("mSecondPointerAngle");
        this.mLockEnabled = bundle.getBoolean("mLockEnabled");
        this.mLockAtStart = bundle.getBoolean("mLockAtStart");
        this.mLockAtEnd = bundle.getBoolean("mLockAtEnd");
        this.mCircleStyle = Paint.Cap.values()[bundle.getInt("mCircleStyle")];
        this.mLastPointerTouched = bundle.getInt("mLastPointerTouched");
        this.mHideProgressWhenEmpty = bundle.getBoolean("mHideProgressWhenEmpty");
        initPaints();
        recalculateAll();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("PARENT", onSaveInstanceState);
        bundle.putFloat("MAX", this.mMax);
        bundle.putFloat("PROGRESS", this.mProgress);
        bundle.putFloat("mProgressDegrees", this.mProgressDegrees);
        bundle.putFloat("mSecondPointerPosition", this.mSecondPointerPosition);
        bundle.putFloat("mFirstPointerPosition", this.mFirstPointerPosition);
        bundle.putFloat("mSecondPointerAngle", this.mSecondPointerAngle);
        bundle.putBoolean("mLockEnabled", this.mLockEnabled);
        bundle.putBoolean("mLockAtStart", this.mLockAtStart);
        bundle.putBoolean("mLockAtEnd", this.mLockAtEnd);
        bundle.putInt("mCircleStyle", this.mCircleStyle.ordinal());
        bundle.putInt("mLastPointerTouched", this.mLastPointerTouched);
        bundle.putBoolean("mHideProgressWhenEmpty", this.mHideProgressWhenEmpty);
        return bundle;
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f;
        if (!isEnabled() || this.mCircularSeekBarRevealAnimation.isRevealAnimation()) {
            return false;
        }
        this.mTouchEventVariable.f1031x = motionEvent.getX() - (((float) getWidth()) / 2.0f);
        this.mTouchEventVariable.y = motionEvent.getY() - (((float) getHeight()) / 2.0f);
        TouchEventVariable touchEventVariable = this.mTouchEventVariable;
        float centerX = this.mCircleRectF.centerX();
        TouchEventVariable touchEventVariable2 = this.mTouchEventVariable;
        touchEventVariable.distanceX = centerX - touchEventVariable2.f1031x;
        float centerY = this.mCircleRectF.centerY();
        TouchEventVariable touchEventVariable3 = this.mTouchEventVariable;
        touchEventVariable2.distanceY = centerY - touchEventVariable3.y;
        touchEventVariable3.touchEventRadius = (float) Math.sqrt(Math.pow((double) this.mTouchEventVariable.distanceY, 2.0d) + Math.pow((double) touchEventVariable3.distanceX, 2.0d));
        TouchEventVariable touchEventVariable4 = this.mTouchEventVariable;
        float f5 = this.DPTOPX_SCALE * 48.0f;
        touchEventVariable4.minimumTouchTarget = f5;
        float f8 = this.mCircleStrokeWidth;
        if (f8 < f5) {
            f = f5 / 2.0f;
        } else {
            f = f8 / 2.0f;
        }
        touchEventVariable4.additionalRadius = f;
        float max = Math.max(this.mCircleHeight, this.mCircleWidth);
        TouchEventVariable touchEventVariable5 = this.mTouchEventVariable;
        touchEventVariable4.outerRadius = max + touchEventVariable5.additionalRadius;
        float min = Math.min(this.mCircleHeight, this.mCircleWidth);
        TouchEventVariable touchEventVariable6 = this.mTouchEventVariable;
        touchEventVariable5.innerRadius = min - touchEventVariable6.additionalRadius;
        touchEventVariable6.touchAngle = (float) (((Math.atan2((double) touchEventVariable6.y, (double) touchEventVariable6.f1031x) / 3.141592653589793d) * 180.0d) % 360.0d);
        TouchEventVariable touchEventVariable7 = this.mTouchEventVariable;
        float f10 = touchEventVariable7.touchAngle;
        if (f10 < 0.0f) {
            f10 += 360.0f;
        }
        touchEventVariable7.touchAngle = f10;
        int action = motionEvent.getAction();
        if (action == 0) {
            TouchEventVariable touchEventVariable8 = this.mTouchEventVariable;
            return onActionDown(touchEventVariable8.touchAngle, touchEventVariable8.touchEventRadius, touchEventVariable8.innerRadius, touchEventVariable8.outerRadius);
        } else if (action == 1) {
            return onActionUpCancel();
        } else {
            if (action == 2) {
                TouchEventVariable touchEventVariable9 = this.mTouchEventVariable;
                return onActionMove(touchEventVariable9.outerRadius, touchEventVariable9.touchEventRadius, touchEventVariable9.touchAngle);
            } else if (action != 3) {
                return true;
            } else {
                Log.d("CircularSeekBar", "MotionEvent.ACTION_CANCEL");
                return onActionUpCancel();
            }
        }
    }

    private void addPointerTouchListener() {
    }

    private void initTouchOnFirstPointer() {
    }

    private void initTouchOnSecondPointer() {
    }
}
