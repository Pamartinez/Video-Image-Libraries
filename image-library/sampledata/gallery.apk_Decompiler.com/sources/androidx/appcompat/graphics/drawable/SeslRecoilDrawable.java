package androidx.appcompat.graphics.drawable;

import B2.h;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import androidx.appcompat.R$styleable;
import androidx.core.graphics.ColorUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslRecoilDrawable extends LayerDrawable {
    private static final Long PRESS_ANIMATION_DURATION = 100L;
    private static final Interpolator PRESS_INTERPOLATOR = new LinearInterpolator();
    private static final Long RELEASE_ANIMATION_DURATION = 350L;
    private static final Interpolator RELEASE_INTERPOLATOR = new PathInterpolator(0.17f, 0.17f, 0.67f, 1.0f);
    private static final String TAG = "SeslRecoilDrawable";
    private final ValueAnimator mAnimator;
    private float mHotspotPointX;
    private float mHotspotPointY;
    private boolean mIsActive;
    private boolean mIsPressed;
    private SeslRecoilDrawableListener mListener;
    private Drawable mMask;
    private long mPressDuration;
    private int mRadius;
    private long mReleaseDuration;
    private int mTintColor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeslRecoilDrawableListener {
        void onReleaseAnimationStart();
    }

    public SeslRecoilDrawable() {
        super(new Drawable[0]);
        this.mIsActive = false;
        this.mIsPressed = false;
        this.mAnimator = ValueAnimator.ofFloat(new float[]{0.0f});
        this.mListener = null;
        init();
    }

    private void drawHotspot(Canvas canvas) {
        float f = this.mHotspotPointX;
        float f5 = this.mHotspotPointY;
        Rect rect = new Rect();
        getHotspotBounds(rect);
        if (rect.height() > 0) {
            f = (float) rect.centerX();
            f5 = (float) rect.centerY();
        }
        canvas.translate(f, f5);
        Paint paint = new Paint();
        paint.setColor(getAnimatingTintColor());
        canvas.drawCircle(0.0f, 0.0f, getRadius(), paint);
        canvas.translate(-f, -f5);
    }

    private int getAnimatingTintColor() {
        return ColorUtils.setAlphaComponent(this.mTintColor, (int) (((Float) this.mAnimator.getAnimatedValue()).floatValue() * Color.valueOf(this.mTintColor).alpha() * 255.0f));
    }

    private float getRadius() {
        int i2 = this.mRadius;
        if (i2 > 0) {
            return (float) i2;
        }
        Rect rect = new Rect();
        getHotspotBounds(rect);
        int height = rect.height() / 2;
        if (height > 0) {
            return (float) height;
        }
        return (float) (getBounds().height() / 2);
    }

    private void init() {
        this.mPressDuration = PRESS_ANIMATION_DURATION.longValue();
        this.mReleaseDuration = RELEASE_ANIMATION_DURATION.longValue();
        initAnimator();
        setPaddingMode(1);
    }

    private void initAnimator() {
        this.mAnimator.addUpdateListener(new h(13, this));
    }

    private boolean isDrawHotspot() {
        if (getNumberOfLayers() <= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAnimator$0(ValueAnimator valueAnimator) {
        setTint();
        invalidateSelf();
    }

    private void setActive(boolean z, boolean z3, boolean z7) {
        boolean z9;
        if (z || z3 || z7) {
            z9 = true;
        } else {
            z9 = false;
        }
        if (z7) {
            this.mIsPressed = true;
            startEnterAnimation(1.0f);
        } else if (z3) {
            startEnterAnimation(0.6f);
        } else if (z) {
            startEnterAnimation(0.8f);
        } else if (this.mIsActive && !z9) {
            startExitAnimation();
        }
        this.mIsActive = z9;
        this.mIsPressed = z7;
    }

    private void setTint() {
        int animatingTintColor = getAnimatingTintColor();
        Drawable findDrawableByLayerId = findDrawableByLayerId(16908334);
        if (findDrawableByLayerId != null) {
            findDrawableByLayerId.setTint(animatingTintColor);
            return;
        }
        setTintBlendMode(BlendMode.HARD_LIGHT);
        setTint(animatingTintColor);
    }

    private void startEnterAnimation(float f) {
        if (this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        ValueAnimator valueAnimator = this.mAnimator;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), f});
        this.mAnimator.setInterpolator(PRESS_INTERPOLATOR);
        this.mAnimator.setDuration(this.mPressDuration);
        this.mAnimator.start();
    }

    private void startExitAnimation() {
        float f;
        if (this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (!this.mIsPressed) {
            f = ((Float) this.mAnimator.getAnimatedValue()).floatValue();
        } else {
            f = 1.0f;
        }
        this.mAnimator.setFloatValues(new float[]{f, 0.0f});
        this.mAnimator.setInterpolator(RELEASE_INTERPOLATOR);
        this.mAnimator.setDuration(this.mReleaseDuration);
        this.mAnimator.start();
        SeslRecoilDrawableListener seslRecoilDrawableListener = this.mListener;
        if (seslRecoilDrawableListener != null) {
            seslRecoilDrawableListener.onReleaseAnimationStart();
        }
    }

    private void updateMaskLayer() {
        Drawable findDrawableByLayerId = findDrawableByLayerId(16908334);
        if (findDrawableByLayerId != null) {
            findDrawableByLayerId.setTint(0);
            findDrawableByLayerId.setTintBlendMode(BlendMode.SRC_IN);
        }
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        for (int i2 = 0; i2 < typedArray.getIndexCount(); i2++) {
            int index = typedArray.getIndex(i2);
            if (index == R$styleable.SeslRecoil_seslRecoilColor) {
                this.mTintColor = typedArray.getColor(index, 419430400);
            } else if (index == R$styleable.SeslRecoil_seslRecoilRadius) {
                this.mRadius = typedArray.getDimensionPixelSize(index, -1);
            } else if (index == R$styleable.SeslRecoil_seslRecoilMask) {
                Drawable drawable = typedArray.getDrawable(index);
                this.mMask = drawable;
                if (drawable != null) {
                    setId(addLayer(drawable), 16908334);
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        int saveCount = canvas.getSaveCount();
        if (isDrawHotspot()) {
            drawHotspot(canvas);
        } else {
            super.draw(canvas);
        }
        canvas.restoreToCount(saveCount);
    }

    public Drawable.ConstantState getConstantState() {
        return null;
    }

    public boolean hasFocusStateSpecified() {
        return true;
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R$styleable.SeslRecoil);
        try {
            updateStateFromTypedArray(obtainAttributes);
        } catch (XmlPullParserException e) {
            Log.e(TAG, "Failed to parse!!", e);
        } catch (Throwable th) {
            obtainAttributes.recycle();
            throw th;
        }
        obtainAttributes.recycle();
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        updateMaskLayer();
    }

    public boolean isProjected() {
        return isDrawHotspot();
    }

    public boolean isStateful() {
        return true;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        if (this.mAnimator.isRunning()) {
            this.mAnimator.end();
        }
    }

    public boolean onStateChange(int[] iArr) {
        boolean z = false;
        boolean z3 = false;
        boolean z7 = false;
        for (int i2 : iArr) {
            if (i2 == 16842908) {
                z = true;
            } else if (i2 == 16842919) {
                z7 = true;
            } else if (i2 == 16843623) {
                z3 = true;
            }
        }
        setActive(z, z3, z7);
        return super.onStateChange(iArr);
    }

    public void removeListener() {
        if (this.mListener != null) {
            this.mListener = null;
        }
    }

    public void setCancel() {
        setState(new int[0]);
    }

    public void setHotspot(float f, float f5) {
        super.setHotspot(f, f5);
        this.mHotspotPointX = f;
        this.mHotspotPointY = f5;
    }

    public void setListener(SeslRecoilDrawableListener seslRecoilDrawableListener) {
        if (this.mListener == null) {
            this.mListener = seslRecoilDrawableListener;
        }
    }

    public void setTintBlendMode(BlendMode blendMode) {
        super.setTintBlendMode(blendMode);
        Drawable findDrawableByLayerId = findDrawableByLayerId(16908334);
        if (findDrawableByLayerId != null) {
            findDrawableByLayerId.setTintBlendMode(BlendMode.SRC_IN);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        Drawable findDrawableByLayerId = findDrawableByLayerId(16908334);
        if (findDrawableByLayerId != null) {
            findDrawableByLayerId.setTint(getAnimatingTintColor());
        }
    }

    public SeslRecoilDrawable(Drawable[] drawableArr) {
        super(drawableArr);
        this.mIsActive = false;
        this.mIsPressed = false;
        this.mAnimator = ValueAnimator.ofFloat(new float[]{0.0f});
        this.mListener = null;
        init();
    }

    public SeslRecoilDrawable(int i2, Drawable[] drawableArr, Drawable drawable) {
        this(drawableArr);
        init();
        this.mTintColor = i2;
        if (drawable != null) {
            this.mMask = drawable;
            setId(addLayer(drawable), 16908334);
        }
    }
}
