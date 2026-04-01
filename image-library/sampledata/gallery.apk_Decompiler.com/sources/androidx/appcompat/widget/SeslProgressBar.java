package androidx.appcompat.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.IntProperty;
import android.view.View;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.ViewCompat;
import androidx.reflect.graphics.drawable.SeslStateListDrawableReflector;
import androidx.reflect.view.SeslViewReflector;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

@RemoteViews.RemoteView
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslProgressBar extends View {
    /* access modifiers changed from: private */
    public static final DecelerateInterpolator PROGRESS_ANIM_INTERPOLATOR = new DecelerateInterpolator();
    private final FloatProperty<SeslProgressBar> VISUAL_PROGRESS;
    private AccessibilityEventSender mAccessibilityEventSender;
    private boolean mAggregatedIsVisible;
    private AlphaAnimation mAnimation;
    private boolean mAttached;
    private int mBehavior;
    private Locale mCachedLocale;
    private CircleAnimationCallback mCircleAnimationCallback;
    /* access modifiers changed from: private */
    public int[] mCircleGradientColors;
    /* access modifiers changed from: private */
    public float[] mCircleGradientPositions;
    private int mCircleGradientStyle;
    /* access modifiers changed from: private */
    public int mCirclePadding;
    private Drawable mCurrentDrawable;
    protected int mCurrentMode;
    protected float mDensity;
    private int mDuration;
    private boolean mHasAnimation;
    private boolean mInDrawing;
    private boolean mIndeterminate;
    /* access modifiers changed from: private */
    public Drawable mIndeterminateDrawable;
    private Drawable mIndeterminateHorizontalLarge;
    private Drawable mIndeterminateHorizontalMedium;
    private Drawable mIndeterminateHorizontalSmall;
    private Drawable mIndeterminateHorizontalXlarge;
    private Drawable mIndeterminateHorizontalXsmall;
    private Interpolator mInterpolator;
    /* access modifiers changed from: private */
    public boolean mIsGradient;
    /* access modifiers changed from: private */
    public int mMax;
    int mMaxHeight;
    private boolean mMaxInitialized;
    int mMaxWidth;
    /* access modifiers changed from: private */
    public int mMin;
    int mMinHeight;
    private boolean mMinInitialized;
    int mMinWidth;
    boolean mMirrorForRtl;
    private boolean mNoInvalidate;
    private boolean mOnlyIndeterminate;
    private NumberFormat mPercentFormat;
    private int mProgress;
    private Drawable mProgressDrawable;
    private ProgressTintInfo mProgressTintInfo;
    /* access modifiers changed from: private */
    public final ArrayList<RefreshData> mRefreshData;
    /* access modifiers changed from: private */
    public boolean mRefreshIsPosted;
    private RefreshProgressRunnable mRefreshProgressRunnable;
    /* access modifiers changed from: private */
    public int mRoundStrokeWidth;
    int mSampleWidth;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;
    private boolean mUseCustomWidthForCircleMode;
    private boolean mUseHorizontalProgress;
    /* access modifiers changed from: private */
    public float mVisualProgress;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AccessibilityEventSender implements Runnable {
        private AccessibilityEventSender() {
        }

        public void run() {
            SeslProgressBar.this.sendAccessibilityEvent(4);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CirCleProgressDrawable extends Drawable {
        private final IntProperty<CirCleProgressDrawable> VISUAL_CIRCLE_PROGRESS = new IntProperty<CirCleProgressDrawable>("visual_progress") {
            public Integer get(CirCleProgressDrawable cirCleProgressDrawable) {
                return Integer.valueOf(cirCleProgressDrawable.mProgress);
            }

            public void setValue(CirCleProgressDrawable cirCleProgressDrawable, int i2) {
                cirCleProgressDrawable.mProgress = i2;
                CirCleProgressDrawable.this.invalidateSelf();
            }
        };
        int mAlpha = ScoverState.TYPE_NFC_SMART_COVER;
        private RectF mArcRect = new RectF();
        int mColor;
        ColorStateList mColorStateList;
        private SweepGradient mGradientShader;
        private boolean mIsBackground;
        private final Paint mPaint;
        public int mProgress;
        private final ProgressState mState = new ProgressState();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public class ProgressState extends Drawable.ConstantState {
            private ProgressState() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return CirCleProgressDrawable.this;
            }
        }

        public CirCleProgressDrawable(boolean z, ColorStateList colorStateList) {
            Paint paint = new Paint();
            this.mPaint = paint;
            this.mIsBackground = z;
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            this.mColorStateList = colorStateList;
            int defaultColor = colorStateList.getDefaultColor();
            this.mColor = defaultColor;
            paint.setColor(defaultColor);
            this.mProgress = 0;
        }

        private int modulateAlpha(int i2, int i7) {
            return ((i7 + (i7 >>> 7)) * i2) >>> 8;
        }

        public void draw(Canvas canvas) {
            float f;
            this.mPaint.setStrokeWidth((float) SeslProgressBar.this.mRoundStrokeWidth);
            int alpha = this.mPaint.getAlpha();
            this.mPaint.setAlpha(modulateAlpha(alpha, this.mAlpha));
            this.mPaint.setAntiAlias(true);
            this.mArcRect.set((((float) SeslProgressBar.this.mRoundStrokeWidth) / 2.0f) + ((float) SeslProgressBar.this.mCirclePadding), (((float) SeslProgressBar.this.mRoundStrokeWidth) / 2.0f) + ((float) SeslProgressBar.this.mCirclePadding), (((float) SeslProgressBar.this.getWidth()) - (((float) SeslProgressBar.this.mRoundStrokeWidth) / 2.0f)) - ((float) SeslProgressBar.this.mCirclePadding), (((float) SeslProgressBar.this.getWidth()) - (((float) SeslProgressBar.this.mRoundStrokeWidth) / 2.0f)) - ((float) SeslProgressBar.this.mCirclePadding));
            if (SeslProgressBar.this.mIsGradient && !this.mIsBackground) {
                if (this.mGradientShader == null) {
                    float centerX = this.mArcRect.centerX();
                    float centerY = this.mArcRect.centerY();
                    this.mGradientShader = new SweepGradient(centerX, centerY, SeslProgressBar.this.mCircleGradientColors, SeslProgressBar.this.mCircleGradientPositions);
                    Matrix matrix = new Matrix();
                    matrix.preRotate(-90.0f, centerX, centerY);
                    this.mGradientShader.setLocalMatrix(matrix);
                }
                this.mPaint.setShader(this.mGradientShader);
            } else if (SeslProgressBar.this.mIsGradient) {
                this.mPaint.setShader((Shader) null);
            }
            int access$1100 = SeslProgressBar.this.mMax - SeslProgressBar.this.mMin;
            if (access$1100 > 0) {
                f = ((float) (this.mProgress - SeslProgressBar.this.mMin)) / ((float) access$1100);
            } else {
                f = 0.0f;
            }
            canvas.save();
            if (this.mIsBackground) {
                canvas.drawArc(this.mArcRect, 270.0f, 360.0f, false, this.mPaint);
            } else {
                canvas.drawArc(this.mArcRect, 270.0f, f * 360.0f, false, this.mPaint);
            }
            canvas.restore();
            this.mPaint.setAlpha(alpha);
        }

        public Drawable.ConstantState getConstantState() {
            return this.mState;
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

        public void setProgress(int i2, boolean z) {
            if (z) {
                ObjectAnimator ofInt = ObjectAnimator.ofInt(this, this.VISUAL_CIRCLE_PROGRESS, new int[]{i2});
                ofInt.setAutoCancel(true);
                ofInt.setDuration(80);
                ofInt.setInterpolator(SeslProgressBar.PROGRESS_ANIM_INTERPOLATOR);
                ofInt.start();
                return;
            }
            this.mProgress = i2;
            SeslProgressBar.this.invalidate();
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
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CircleAnimationCallback extends Animatable2.AnimationCallback {
        final Handler mHandler = new Handler(Looper.getMainLooper());
        /* access modifiers changed from: private */
        public WeakReference<SeslProgressBar> mProgressBar;

        public CircleAnimationCallback(SeslProgressBar seslProgressBar) {
            this.mProgressBar = new WeakReference<>(seslProgressBar);
        }

        public void onAnimationEnd(Drawable drawable) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    SeslProgressBar seslProgressBar = (SeslProgressBar) CircleAnimationCallback.this.mProgressBar.get();
                    if (seslProgressBar != null) {
                        ((AnimatedVectorDrawable) seslProgressBar.mIndeterminateDrawable).start();
                    }
                }
            });
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProgressTintInfo {
        boolean mHasIndeterminateTint;
        boolean mHasIndeterminateTintMode;
        boolean mHasProgressBackgroundTint;
        boolean mHasProgressBackgroundTintMode;
        boolean mHasProgressTint;
        boolean mHasProgressTintMode;
        boolean mHasSecondaryProgressTint;
        boolean mHasSecondaryProgressTintMode;
        ColorStateList mIndeterminateTintList;
        PorterDuff.Mode mIndeterminateTintMode;
        ColorStateList mProgressBackgroundTintList;
        PorterDuff.Mode mProgressBackgroundTintMode;
        ColorStateList mProgressTintList;
        PorterDuff.Mode mProgressTintMode;
        ColorStateList mSecondaryProgressTintList;
        PorterDuff.Mode mSecondaryProgressTintMode;

        private ProgressTintInfo() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RefreshData {
        private static final Pools$SynchronizedPool<RefreshData> sPool = new Pools$SynchronizedPool<>(24);
        public boolean animate;
        public boolean fromUser;
        public int id;
        public int progress;

        private RefreshData() {
        }

        public static RefreshData obtain(int i2, int i7, boolean z, boolean z3) {
            RefreshData acquire = sPool.acquire();
            if (acquire == null) {
                acquire = new RefreshData();
            }
            acquire.id = i2;
            acquire.progress = i7;
            acquire.fromUser = z;
            acquire.animate = z3;
            return acquire;
        }

        public void recycle() {
            sPool.release(this);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class RefreshProgressRunnable implements Runnable {
        private RefreshProgressRunnable() {
        }

        public void run() {
            synchronized (SeslProgressBar.this) {
                try {
                    int size = SeslProgressBar.this.mRefreshData.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        RefreshData refreshData = (RefreshData) SeslProgressBar.this.mRefreshData.get(i2);
                        SeslProgressBar.this.doRefreshProgress(refreshData.id, refreshData.progress, refreshData.fromUser, true, refreshData.animate);
                        refreshData.recycle();
                    }
                    SeslProgressBar.this.mRefreshData.clear();
                    boolean unused = SeslProgressBar.this.mRefreshIsPosted = false;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        int progress;
        int secondaryProgress;

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.progress);
            parcel.writeInt(this.secondaryProgress);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.progress = parcel.readInt();
            this.secondaryProgress = parcel.readInt();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StateListDrawableCompat {
        private static final boolean IS_BASE_SDK_VERSION = false;

        public static int getStateCount(StateListDrawable stateListDrawable) {
            if (!IS_BASE_SDK_VERSION) {
                return 0;
            }
            SeslStateListDrawableReflector.getStateCount(stateListDrawable);
            return 0;
        }

        public static Drawable getStateDrawable(StateListDrawable stateListDrawable, int i2) {
            if (IS_BASE_SDK_VERSION) {
                return SeslStateListDrawableReflector.getStateDrawable(stateListDrawable, i2);
            }
            return null;
        }

        public static int[] getStateSet(StateListDrawable stateListDrawable, int i2) {
            if (IS_BASE_SDK_VERSION) {
                return SeslStateListDrawableReflector.getStateSet(stateListDrawable, i2);
            }
            return null;
        }
    }

    public SeslProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    private void applyIndeterminateTint() {
        ProgressTintInfo progressTintInfo;
        Drawable drawable = this.mIndeterminateDrawable;
        if (drawable != null && (progressTintInfo = this.mProgressTintInfo) != null) {
            if (progressTintInfo.mHasIndeterminateTint || progressTintInfo.mHasIndeterminateTintMode) {
                Drawable mutate = drawable.mutate();
                this.mIndeterminateDrawable = mutate;
                if (progressTintInfo.mHasIndeterminateTint) {
                    DrawableCompat.setTintList(mutate, progressTintInfo.mIndeterminateTintList);
                }
                if (progressTintInfo.mHasIndeterminateTintMode) {
                    DrawableCompat.setTintMode(this.mIndeterminateDrawable, progressTintInfo.mIndeterminateTintMode);
                }
                if (this.mIndeterminateDrawable.isStateful()) {
                    this.mIndeterminateDrawable.setState(getDrawableState());
                }
            }
        }
    }

    private void applyPrimaryProgressTint() {
        Drawable tintTarget;
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if ((progressTintInfo.mHasProgressTint || progressTintInfo.mHasProgressTintMode) && (tintTarget = getTintTarget(16908301, true)) != null) {
            ProgressTintInfo progressTintInfo2 = this.mProgressTintInfo;
            if (progressTintInfo2.mHasProgressTint) {
                DrawableCompat.setTintList(tintTarget, progressTintInfo2.mProgressTintList);
            }
            ProgressTintInfo progressTintInfo3 = this.mProgressTintInfo;
            if (progressTintInfo3.mHasProgressTintMode) {
                DrawableCompat.setTintMode(tintTarget, progressTintInfo3.mProgressTintMode);
            }
            if (tintTarget.isStateful()) {
                tintTarget.setState(getDrawableState());
            }
        }
    }

    private void applyProgressBackgroundTint() {
        Drawable tintTarget;
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if ((progressTintInfo.mHasProgressBackgroundTint || progressTintInfo.mHasProgressBackgroundTintMode) && (tintTarget = getTintTarget(16908288, false)) != null) {
            ProgressTintInfo progressTintInfo2 = this.mProgressTintInfo;
            if (progressTintInfo2.mHasProgressBackgroundTint) {
                DrawableCompat.setTintList(tintTarget, progressTintInfo2.mProgressBackgroundTintList);
            }
            ProgressTintInfo progressTintInfo3 = this.mProgressTintInfo;
            if (progressTintInfo3.mHasProgressBackgroundTintMode) {
                DrawableCompat.setTintMode(tintTarget, progressTintInfo3.mProgressBackgroundTintMode);
            }
            if (tintTarget.isStateful()) {
                tintTarget.setState(getDrawableState());
            }
        }
    }

    private void applyProgressTints() {
        if (this.mProgressDrawable != null && this.mProgressTintInfo != null) {
            applyPrimaryProgressTint();
            applyProgressBackgroundTint();
            applySecondaryProgressTint();
        }
    }

    private void applySecondaryProgressTint() {
        Drawable tintTarget;
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if ((progressTintInfo.mHasSecondaryProgressTint || progressTintInfo.mHasSecondaryProgressTintMode) && (tintTarget = getTintTarget(16908303, false)) != null) {
            ProgressTintInfo progressTintInfo2 = this.mProgressTintInfo;
            if (progressTintInfo2.mHasSecondaryProgressTint) {
                DrawableCompat.setTintList(tintTarget, progressTintInfo2.mSecondaryProgressTintList);
            }
            ProgressTintInfo progressTintInfo3 = this.mProgressTintInfo;
            if (progressTintInfo3.mHasSecondaryProgressTintMode) {
                DrawableCompat.setTintMode(tintTarget, progressTintInfo3.mSecondaryProgressTintMode);
            }
            if (tintTarget.isStateful()) {
                tintTarget.setState(getDrawableState());
            }
        }
    }

    private ColorStateList colorToColorStateList(int i2) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{i2});
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c3, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void doRefreshProgress(int r12, int r13, boolean r14, boolean r15, boolean r16) {
        /*
            r11 = this;
            monitor-enter(r11)
            int r0 = r11.mMax     // Catch:{ all -> 0x0019 }
            int r1 = r11.mMin     // Catch:{ all -> 0x0019 }
            int r0 = r0 - r1
            r2 = 0
            if (r0 <= 0) goto L_0x000f
            int r3 = r13 - r1
            float r3 = (float) r3     // Catch:{ all -> 0x0019 }
            float r4 = (float) r0     // Catch:{ all -> 0x0019 }
            float r3 = r3 / r4
            goto L_0x0010
        L_0x000f:
            r3 = r2
        L_0x0010:
            if (r0 <= 0) goto L_0x001d
            float r2 = r11.mVisualProgress     // Catch:{ all -> 0x0019 }
            float r1 = (float) r1     // Catch:{ all -> 0x0019 }
            float r2 = r2 - r1
            float r0 = (float) r0     // Catch:{ all -> 0x0019 }
            float r2 = r2 / r0
            goto L_0x001d
        L_0x0019:
            r0 = move-exception
            r12 = r0
            goto L_0x00c4
        L_0x001d:
            r0 = 16908301(0x102000d, float:2.3877265E-38)
            r1 = 0
            r4 = 1
            if (r12 != r0) goto L_0x0026
            r0 = r4
            goto L_0x0027
        L_0x0026:
            r0 = r1
        L_0x0027:
            android.graphics.drawable.Drawable r5 = r11.mCurrentDrawable     // Catch:{ all -> 0x0019 }
            if (r5 == 0) goto L_0x0093
            r6 = 1176256512(0x461c4000, float:10000.0)
            float r6 = r6 * r3
            int r6 = (int) r6     // Catch:{ all -> 0x0019 }
            boolean r7 = r5 instanceof android.graphics.drawable.LayerDrawable     // Catch:{ all -> 0x0019 }
            if (r7 == 0) goto L_0x0051
            r7 = r5
            android.graphics.drawable.LayerDrawable r7 = (android.graphics.drawable.LayerDrawable) r7     // Catch:{ all -> 0x0019 }
            android.graphics.drawable.Drawable r7 = r7.findDrawableByLayerId(r12)     // Catch:{ all -> 0x0019 }
            if (r7 == 0) goto L_0x004a
            boolean r8 = r11.canResolveLayoutDirection()     // Catch:{ all -> 0x0019 }
            if (r8 == 0) goto L_0x004a
            int r8 = androidx.core.view.ViewCompat.getLayoutDirection(r11)     // Catch:{ all -> 0x0019 }
            androidx.core.graphics.drawable.DrawableCompat.setLayoutDirection(r7, r8)     // Catch:{ all -> 0x0019 }
        L_0x004a:
            if (r7 == 0) goto L_0x004d
            r5 = r7
        L_0x004d:
            r5.setLevel(r6)     // Catch:{ all -> 0x0019 }
            goto L_0x0096
        L_0x0051:
            boolean r7 = r5 instanceof android.graphics.drawable.StateListDrawable     // Catch:{ all -> 0x0019 }
            if (r7 == 0) goto L_0x008f
            r7 = r5
            android.graphics.drawable.StateListDrawable r7 = (android.graphics.drawable.StateListDrawable) r7     // Catch:{ all -> 0x0019 }
            int r7 = androidx.appcompat.widget.SeslProgressBar.StateListDrawableCompat.getStateCount(r7)     // Catch:{ all -> 0x0019 }
            r8 = r1
        L_0x005d:
            if (r8 >= r7) goto L_0x0096
            r9 = r5
            android.graphics.drawable.StateListDrawable r9 = (android.graphics.drawable.StateListDrawable) r9     // Catch:{ all -> 0x0019 }
            android.graphics.drawable.Drawable r9 = androidx.appcompat.widget.SeslProgressBar.StateListDrawableCompat.getStateDrawable(r9, r8)     // Catch:{ all -> 0x0019 }
            if (r9 != 0) goto L_0x006a
            monitor-exit(r11)
            return
        L_0x006a:
            boolean r10 = r9 instanceof android.graphics.drawable.LayerDrawable     // Catch:{ all -> 0x0019 }
            if (r10 == 0) goto L_0x0084
            android.graphics.drawable.LayerDrawable r9 = (android.graphics.drawable.LayerDrawable) r9     // Catch:{ all -> 0x0019 }
            android.graphics.drawable.Drawable r9 = r9.findDrawableByLayerId(r12)     // Catch:{ all -> 0x0019 }
            if (r9 == 0) goto L_0x0085
            boolean r10 = r11.canResolveLayoutDirection()     // Catch:{ all -> 0x0019 }
            if (r10 == 0) goto L_0x0085
            int r10 = androidx.core.view.ViewCompat.getLayoutDirection(r11)     // Catch:{ all -> 0x0019 }
            androidx.core.graphics.drawable.DrawableCompat.setLayoutDirection(r9, r10)     // Catch:{ all -> 0x0019 }
            goto L_0x0085
        L_0x0084:
            r9 = 0
        L_0x0085:
            if (r9 == 0) goto L_0x0088
            goto L_0x0089
        L_0x0088:
            r9 = r5
        L_0x0089:
            r9.setLevel(r6)     // Catch:{ all -> 0x0019 }
            int r8 = r8 + 1
            goto L_0x005d
        L_0x008f:
            r5.setLevel(r6)     // Catch:{ all -> 0x0019 }
            goto L_0x0096
        L_0x0093:
            r11.invalidate()     // Catch:{ all -> 0x0019 }
        L_0x0096:
            if (r0 == 0) goto L_0x00b8
            if (r16 == 0) goto L_0x00b8
            android.util.FloatProperty<androidx.appcompat.widget.SeslProgressBar> r12 = r11.VISUAL_PROGRESS     // Catch:{ all -> 0x0019 }
            r5 = 2
            float[] r5 = new float[r5]     // Catch:{ all -> 0x0019 }
            r5[r1] = r2     // Catch:{ all -> 0x0019 }
            r5[r4] = r3     // Catch:{ all -> 0x0019 }
            android.animation.ObjectAnimator r12 = android.animation.ObjectAnimator.ofFloat(r11, r12, r5)     // Catch:{ all -> 0x0019 }
            r12.setAutoCancel(r4)     // Catch:{ all -> 0x0019 }
            r1 = 80
            r12.setDuration(r1)     // Catch:{ all -> 0x0019 }
            android.view.animation.DecelerateInterpolator r1 = PROGRESS_ANIM_INTERPOLATOR     // Catch:{ all -> 0x0019 }
            r12.setInterpolator(r1)     // Catch:{ all -> 0x0019 }
            r12.start()     // Catch:{ all -> 0x0019 }
            goto L_0x00bb
        L_0x00b8:
            r11.setVisualProgress(r12, r3)     // Catch:{ all -> 0x0019 }
        L_0x00bb:
            if (r0 == 0) goto L_0x00c2
            if (r15 == 0) goto L_0x00c2
            r11.onProgressRefresh(r3, r14, r13)     // Catch:{ all -> 0x0019 }
        L_0x00c2:
            monitor-exit(r11)
            return
        L_0x00c4:
            monitor-exit(r11)     // Catch:{ all -> 0x0019 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslProgressBar.doRefreshProgress(int, int, boolean, boolean, boolean):void");
    }

    private CharSequence formatStateDescription(int i2) {
        Locale locale = getResources().getConfiguration().locale;
        if (!locale.equals(this.mCachedLocale) || this.mPercentFormat == null) {
            this.mCachedLocale = locale;
            this.mPercentFormat = NumberFormat.getPercentInstance(locale);
        }
        return this.mPercentFormat.format((double) getPercent(i2));
    }

    private float getPercent(int i2) {
        float min = (float) getMin();
        float max = ((float) getMax()) - min;
        if (max <= 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp((((float) i2) - min) / max, 0.0f, 1.0f);
    }

    private static String getStringById(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "string", "android");
        if (identifier <= 0) {
            return "";
        }
        try {
            return context.getResources().getString(identifier);
        } catch (Resources.NotFoundException unused) {
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private Drawable getTintTarget(int i2, boolean z) {
        Drawable drawable = this.mProgressDrawable;
        Drawable drawable2 = null;
        if (drawable != null) {
            this.mProgressDrawable = drawable.mutate();
            if (drawable instanceof LayerDrawable) {
                drawable2 = ((LayerDrawable) drawable).findDrawableByLayerId(i2);
            }
            if (!z || drawable2 != null) {
                return drawable2;
            }
            return drawable;
        }
        return drawable2;
    }

    private void initCirCleStrokeWidth(int i2) {
        Resources resources = getResources();
        int i7 = R$dimen.sesl_progress_bar_size_small;
        if (resources.getDimensionPixelSize(i7) == i2) {
            this.mRoundStrokeWidth = getResources().getDimensionPixelSize(R$dimen.sesl_progress_circle_size_small_width);
            this.mCirclePadding = getResources().getDimensionPixelOffset(R$dimen.sesl_progress_circle_size_small_padding);
        } else if (getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_size_small_title) == i2) {
            this.mRoundStrokeWidth = getResources().getDimensionPixelSize(R$dimen.sesl_progress_circle_size_small_title_width);
            this.mCirclePadding = getResources().getDimensionPixelOffset(R$dimen.sesl_progress_circle_size_small_title_padding);
        } else if (getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_size_large) == i2) {
            this.mRoundStrokeWidth = getResources().getDimensionPixelSize(R$dimen.sesl_progress_circle_size_large_width);
            this.mCirclePadding = getResources().getDimensionPixelOffset(R$dimen.sesl_progress_circle_size_large_padding);
        } else if (getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_size_xlarge) == i2) {
            this.mRoundStrokeWidth = getResources().getDimensionPixelSize(R$dimen.sesl_progress_circle_size_xlarge_width);
            this.mCirclePadding = getResources().getDimensionPixelOffset(R$dimen.sesl_progress_circle_size_xlarge_padding);
        } else {
            this.mRoundStrokeWidth = (getResources().getDimensionPixelSize(R$dimen.sesl_progress_circle_size_small_width) * i2) / getResources().getDimensionPixelSize(i7);
            this.mCirclePadding = (getResources().getDimensionPixelOffset(R$dimen.sesl_progress_circle_size_small_padding) * i2) / getResources().getDimensionPixelSize(i7);
        }
    }

    private void initProgressBar() {
        this.mMin = 0;
        this.mMax = 100;
        this.mProgress = 0;
        this.mSecondaryProgress = 0;
        this.mIndeterminate = false;
        this.mOnlyIndeterminate = false;
        this.mDuration = TextToSpeechConst.MAX_SPEECH_INPUT;
        this.mBehavior = 1;
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = 24;
        this.mMaxHeight = 48;
    }

    private void initializeGradientHorizontalMode() {
        this.mOnlyIndeterminate = false;
        setIndeterminate(false);
        GradientHorizontalProgressDrawable gradientHorizontalProgressDrawable = new GradientHorizontalProgressDrawable(false, new int[]{-13075713, -12727161, -13075713, -12727161}, new float[]{0.0f, 0.19f, 0.37f, 1.0f});
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new GradientHorizontalProgressDrawable(true, getResources().getColor(R$color.sesl_progress_control_color_background)), gradientHorizontalProgressDrawable});
        layerDrawable.setPaddingMode(1);
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908301);
        setProgressDrawable(layerDrawable);
    }

    private void initializeRoundCircleMode() {
        this.mOnlyIndeterminate = false;
        setIndeterminate(false);
        CirCleProgressDrawable cirCleProgressDrawable = new CirCleProgressDrawable(false, colorToColorStateList(getResources().getColor(R$color.sesl_progress_control_color_activated_light)));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new CirCleProgressDrawable(true, colorToColorStateList(getResources().getColor(R$color.sesl_progress_control_color_background))), cirCleProgressDrawable});
        layerDrawable.setPaddingMode(1);
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908301);
        setProgressDrawable(layerDrawable);
    }

    private static boolean needsTileify(Drawable drawable) {
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                if (needsTileify(layerDrawable.getDrawable(i2))) {
                    return true;
                }
            }
            return false;
        } else if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            int stateCount = StateListDrawableCompat.getStateCount(stateListDrawable);
            for (int i7 = 0; i7 < stateCount; i7++) {
                Drawable stateDrawable = StateListDrawableCompat.getStateDrawable(stateListDrawable, i7);
                if (stateDrawable != null && needsTileify(stateDrawable)) {
                    return true;
                }
            }
            return false;
        } else if (drawable instanceof BitmapDrawable) {
            return true;
        } else {
            return false;
        }
    }

    private synchronized void refreshProgress(int i2, int i7, boolean z, boolean z3) {
        SeslProgressBar seslProgressBar;
        try {
            if (this.mUiThreadId == Thread.currentThread().getId()) {
                seslProgressBar = this;
                try {
                    seslProgressBar.doRefreshProgress(i2, i7, z, true, z3);
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    throw th2;
                }
            } else {
                seslProgressBar = this;
                int i8 = i2;
                int i10 = i7;
                boolean z7 = z;
                boolean z9 = z3;
                if (seslProgressBar.mRefreshProgressRunnable == null) {
                    seslProgressBar.mRefreshProgressRunnable = new RefreshProgressRunnable();
                }
                seslProgressBar.mRefreshData.add(RefreshData.obtain(i8, i10, z7, z9));
                if (seslProgressBar.mAttached && !seslProgressBar.mRefreshIsPosted) {
                    seslProgressBar.post(seslProgressBar.mRefreshProgressRunnable);
                    seslProgressBar.mRefreshIsPosted = true;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            seslProgressBar = this;
            Throwable th22 = th;
            throw th22;
        }
    }

    private void scheduleAccessibilityEventSender() {
        AccessibilityEventSender accessibilityEventSender = this.mAccessibilityEventSender;
        if (accessibilityEventSender == null) {
            this.mAccessibilityEventSender = new AccessibilityEventSender();
        } else {
            removeCallbacks(accessibilityEventSender);
        }
        postDelayed(this.mAccessibilityEventSender, 200);
    }

    private void seslSetIndeterminateProgressDrawable(int i2) {
        if (getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_indeterminate_xsmall) >= i2) {
            setIndeterminateDrawable(this.mIndeterminateHorizontalXsmall);
        } else if (getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_indeterminate_small) >= i2) {
            setIndeterminateDrawable(this.mIndeterminateHorizontalSmall);
        } else if (getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_indeterminate_medium) >= i2) {
            setIndeterminateDrawable(this.mIndeterminateHorizontalMedium);
        } else if (getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_indeterminate_large) >= i2) {
            setIndeterminateDrawable(this.mIndeterminateHorizontalLarge);
        } else {
            setIndeterminateDrawable(this.mIndeterminateHorizontalXlarge);
        }
    }

    private void setGradientColorsForCircle() {
        if (this.mCircleGradientStyle == 0) {
            this.mCircleGradientColors = new int[]{-432227934, -432157561, -432506113, -1724144701, -1724003193, -1724351745, -432227934};
        } else {
            this.mCircleGradientColors = new int[]{-855835393, 1727855871, -855835393, 872217855, 1727855871, 1727855871, -855835393};
        }
        this.mCircleGradientPositions = new float[]{0.0f, 0.12222222f, 0.20555556f, 0.47777778f, 0.5888889f, 0.69722223f, 0.8277778f};
    }

    /* access modifiers changed from: private */
    public void setVisualProgress(int i2, float f) {
        this.mVisualProgress = f;
        Drawable drawable = this.mCurrentDrawable;
        if ((drawable instanceof LayerDrawable) && (drawable = ((LayerDrawable) drawable).findDrawableByLayerId(i2)) == null) {
            drawable = this.mCurrentDrawable;
        }
        if (drawable != null) {
            drawable.setLevel((int) (10000.0f * f));
        } else {
            invalidate();
        }
        onVisualProgressChanged(i2, f);
    }

    private void startAnimation() {
        if (getVisibility() == 0) {
            Drawable drawable = this.mIndeterminateDrawable;
            if (drawable instanceof Animatable) {
                this.mShouldStartAnimationDrawable = true;
                this.mHasAnimation = false;
                if (drawable instanceof AnimatedVectorDrawable) {
                    ((AnimatedVectorDrawable) drawable).registerAnimationCallback(this.mCircleAnimationCallback);
                }
            } else {
                this.mHasAnimation = true;
                if (this.mInterpolator == null) {
                    this.mInterpolator = new LinearInterpolator();
                }
                Transformation transformation = this.mTransformation;
                if (transformation == null) {
                    this.mTransformation = new Transformation();
                } else {
                    transformation.clear();
                }
                AlphaAnimation alphaAnimation = this.mAnimation;
                if (alphaAnimation == null) {
                    this.mAnimation = new AlphaAnimation(0.0f, 1.0f);
                } else {
                    alphaAnimation.reset();
                }
                this.mAnimation.setRepeatMode(this.mBehavior);
                this.mAnimation.setRepeatCount(-1);
                this.mAnimation.setDuration((long) this.mDuration);
                this.mAnimation.setInterpolator(this.mInterpolator);
                this.mAnimation.setStartTime(-1);
            }
            postInvalidate();
        }
    }

    private void stopAnimation() {
        this.mHasAnimation = false;
        Drawable drawable = this.mIndeterminateDrawable;
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
            Drawable drawable2 = this.mIndeterminateDrawable;
            if (drawable2 instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) drawable2).unregisterAnimationCallback(this.mCircleAnimationCallback);
            }
            this.mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    private void swapCurrentDrawable(Drawable drawable) {
        boolean z;
        Drawable drawable2 = this.mCurrentDrawable;
        this.mCurrentDrawable = drawable;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setVisible(false, false);
            }
            Drawable drawable3 = this.mCurrentDrawable;
            if (drawable3 != null) {
                if (getWindowVisibility() != 0 || !isShown()) {
                    z = false;
                } else {
                    z = true;
                }
                drawable3.setVisible(z, false);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX WARNING: type inference failed for: r8v1, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable tileify(android.graphics.drawable.Drawable r8, boolean r9) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof android.graphics.drawable.LayerDrawable
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0081
            android.graphics.drawable.LayerDrawable r8 = (android.graphics.drawable.LayerDrawable) r8
            int r9 = r8.getNumberOfLayers()
            android.graphics.drawable.Drawable[] r0 = new android.graphics.drawable.Drawable[r9]
            r3 = r1
        L_0x000f:
            if (r3 >= r9) goto L_0x0030
            int r4 = r8.getId(r3)
            android.graphics.drawable.Drawable r5 = r8.getDrawable(r3)
            r6 = 16908301(0x102000d, float:2.3877265E-38)
            if (r4 == r6) goto L_0x0026
            r6 = 16908303(0x102000f, float:2.387727E-38)
            if (r4 != r6) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r4 = r1
            goto L_0x0027
        L_0x0026:
            r4 = r2
        L_0x0027:
            android.graphics.drawable.Drawable r4 = r7.tileify(r5, r4)
            r0[r3] = r4
            int r3 = r3 + 1
            goto L_0x000f
        L_0x0030:
            android.graphics.drawable.LayerDrawable r7 = new android.graphics.drawable.LayerDrawable
            r7.<init>(r0)
        L_0x0035:
            if (r1 >= r9) goto L_0x0080
            int r0 = r8.getId(r1)
            r7.setId(r1, r0)
            int r0 = r8.getLayerGravity(r1)
            r7.setLayerGravity(r1, r0)
            int r0 = r8.getLayerWidth(r1)
            r7.setLayerWidth(r1, r0)
            int r0 = r8.getLayerHeight(r1)
            r7.setLayerHeight(r1, r0)
            int r0 = r8.getLayerInsetLeft(r1)
            r7.setLayerInsetLeft(r1, r0)
            int r0 = r8.getLayerInsetRight(r1)
            r7.setLayerInsetRight(r1, r0)
            int r0 = r8.getLayerInsetTop(r1)
            r7.setLayerInsetTop(r1, r0)
            int r0 = r8.getLayerInsetBottom(r1)
            r7.setLayerInsetBottom(r1, r0)
            int r0 = r8.getLayerInsetStart(r1)
            r7.setLayerInsetStart(r1, r0)
            int r0 = r8.getLayerInsetEnd(r1)
            r7.setLayerInsetEnd(r1, r0)
            int r1 = r1 + 1
            goto L_0x0035
        L_0x0080:
            return r7
        L_0x0081:
            boolean r0 = r8 instanceof android.graphics.drawable.StateListDrawable
            if (r0 == 0) goto L_0x00a7
            android.graphics.drawable.StateListDrawable r8 = (android.graphics.drawable.StateListDrawable) r8
            android.graphics.drawable.StateListDrawable r0 = new android.graphics.drawable.StateListDrawable
            r0.<init>()
            int r2 = androidx.appcompat.widget.SeslProgressBar.StateListDrawableCompat.getStateCount(r8)
        L_0x0090:
            if (r1 >= r2) goto L_0x00a6
            int[] r3 = androidx.appcompat.widget.SeslProgressBar.StateListDrawableCompat.getStateSet(r8, r1)
            android.graphics.drawable.Drawable r4 = androidx.appcompat.widget.SeslProgressBar.StateListDrawableCompat.getStateDrawable(r8, r1)
            if (r4 == 0) goto L_0x00a3
            android.graphics.drawable.Drawable r4 = r7.tileify(r4, r9)
            r0.addState(r3, r4)
        L_0x00a3:
            int r1 = r1 + 1
            goto L_0x0090
        L_0x00a6:
            return r0
        L_0x00a7:
            boolean r0 = r8 instanceof android.graphics.drawable.BitmapDrawable
            if (r0 == 0) goto L_0x00d3
            android.graphics.drawable.Drawable$ConstantState r8 = r8.getConstantState()
            android.content.res.Resources r0 = r7.getResources()
            android.graphics.drawable.Drawable r8 = r8.newDrawable(r0)
            android.graphics.drawable.BitmapDrawable r8 = (android.graphics.drawable.BitmapDrawable) r8
            android.graphics.Shader$TileMode r0 = android.graphics.Shader.TileMode.REPEAT
            android.graphics.Shader$TileMode r1 = android.graphics.Shader.TileMode.CLAMP
            r8.setTileModeXY(r0, r1)
            int r0 = r7.mSampleWidth
            if (r0 > 0) goto L_0x00ca
            int r0 = r8.getIntrinsicWidth()
            r7.mSampleWidth = r0
        L_0x00ca:
            if (r9 == 0) goto L_0x00d3
            android.graphics.drawable.ClipDrawable r7 = new android.graphics.drawable.ClipDrawable
            r9 = 3
            r7.<init>(r8, r9, r2)
            return r7
        L_0x00d3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslProgressBar.tileify(android.graphics.drawable.Drawable, boolean):android.graphics.drawable.Drawable");
    }

    private Drawable tileifyIndeterminate(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i2 = 0; i2 < numberOfFrames; i2++) {
            Drawable tileify = tileify(animationDrawable.getFrame(i2), true);
            tileify.setLevel(10000);
            animationDrawable2.addFrame(tileify, animationDrawable.getDuration(i2));
        }
        animationDrawable2.setLevel(10000);
        return animationDrawable2;
    }

    private void updateDrawableState() {
        boolean z;
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mProgressDrawable;
        if (drawable == null || !drawable.isStateful()) {
            z = false;
        } else {
            z = drawable.setState(drawableState);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    /* JADX INFO: finally extract failed */
    public void drawTrack(Canvas canvas) {
        Drawable drawable = this.mCurrentDrawable;
        if (drawable != null) {
            int save = canvas.save();
            if (this.mCurrentMode == 3 || !this.mMirrorForRtl || !ViewUtils.isLayoutRtl(this)) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            } else {
                canvas.translate((float) (getWidth() - getPaddingRight()), (float) getPaddingTop());
                canvas.scale(-1.0f, 1.0f);
            }
            long drawingTime = getDrawingTime();
            if (this.mHasAnimation) {
                this.mAnimation.getTransformation(drawingTime, this.mTransformation);
                float alpha = this.mTransformation.getAlpha();
                try {
                    this.mInDrawing = true;
                    drawable.setLevel((int) (alpha * 10000.0f));
                    this.mInDrawing = false;
                    ViewCompat.postInvalidateOnAnimation(this);
                } catch (Throwable th) {
                    this.mInDrawing = false;
                    throw th;
                }
            }
            drawable.draw(canvas);
            canvas.restoreToCount(save);
            if (this.mShouldStartAnimationDrawable && (drawable instanceof Animatable)) {
                ((Animatable) drawable).start();
                this.mShouldStartAnimationDrawable = false;
            }
        }
    }

    public void drawableHotspotChanged(float f, float f5) {
        super.drawableHotspotChanged(f, f5);
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f5);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            DrawableCompat.setHotspot(drawable2, f, f5);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    public CharSequence getAccessibilityClassName() {
        return ProgressBar.class.getName();
    }

    public Drawable getCurrentDrawable() {
        return this.mCurrentDrawable;
    }

    public Drawable getIndeterminateDrawable() {
        return this.mIndeterminateDrawable;
    }

    public ColorStateList getIndeterminateTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mIndeterminateTintList;
        }
        return null;
    }

    public PorterDuff.Mode getIndeterminateTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mIndeterminateTintMode;
        }
        return null;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getMax() {
        return this.mMax;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getMin() {
        return this.mMin;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public boolean getMirrorForRtl() {
        return this.mMirrorForRtl;
    }

    public int getPaddingLeft() {
        return SeslViewReflector.getField_mPaddingLeft(this);
    }

    public int getPaddingRight() {
        return SeslViewReflector.getField_mPaddingRight(this);
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getProgress() {
        int i2;
        if (this.mIndeterminate) {
            i2 = 0;
        } else {
            i2 = this.mProgress;
        }
        return i2;
    }

    public ColorStateList getProgressBackgroundTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressBackgroundTintList;
        }
        return null;
    }

    public PorterDuff.Mode getProgressBackgroundTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressBackgroundTintMode;
        }
        return null;
    }

    public Drawable getProgressDrawable() {
        return this.mProgressDrawable;
    }

    public ColorStateList getProgressTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressTintList;
        }
        return null;
    }

    public PorterDuff.Mode getProgressTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressTintMode;
        }
        return null;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getSecondaryProgress() {
        int i2;
        if (this.mIndeterminate) {
            i2 = 0;
        } else {
            i2 = this.mSecondaryProgress;
        }
        return i2;
    }

    public ColorStateList getSecondaryProgressTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mSecondaryProgressTintList;
        }
        return null;
    }

    public PorterDuff.Mode getSecondaryProgressTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mSecondaryProgressTintMode;
        }
        return null;
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mInDrawing) {
            return;
        }
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            int paddingLeft = getPaddingLeft() + getScrollX();
            int paddingTop = getPaddingTop() + getScrollY();
            invalidate(bounds.left + paddingLeft, bounds.top + paddingTop, bounds.right + paddingLeft, bounds.bottom + paddingTop);
            return;
        }
        super.invalidateDrawable(drawable);
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized boolean isIndeterminate() {
        return this.mIndeterminate;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    public void onAttachedToWindow() {
        SeslProgressBar seslProgressBar;
        super.onAttachedToWindow();
        if (this.mIndeterminate) {
            startAnimation();
        }
        synchronized (this) {
            try {
                int size = this.mRefreshData.size();
                int i2 = 0;
                while (i2 < size) {
                    RefreshData refreshData = this.mRefreshData.get(i2);
                    seslProgressBar = this;
                    seslProgressBar.doRefreshProgress(refreshData.id, refreshData.progress, refreshData.fromUser, true, refreshData.animate);
                    refreshData.recycle();
                    i2++;
                    this = seslProgressBar;
                }
                SeslProgressBar seslProgressBar2 = this;
                seslProgressBar2.mRefreshData.clear();
                seslProgressBar2.mAttached = true;
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void onDetachedFromWindow() {
        if (this.mIndeterminate) {
            stopAnimation();
        } else {
            this.mCircleAnimationCallback = null;
        }
        RefreshProgressRunnable refreshProgressRunnable = this.mRefreshProgressRunnable;
        if (refreshProgressRunnable != null) {
            removeCallbacks(refreshProgressRunnable);
            this.mRefreshIsPosted = false;
        }
        AccessibilityEventSender accessibilityEventSender = this.mAccessibilityEventSender;
        if (accessibilityEventSender != null) {
            removeCallbacks(accessibilityEventSender);
        }
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTrack(canvas);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setItemCount(this.mMax - this.mMin);
        accessibilityEvent.setCurrentItemIndex(this.mProgress);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (!isIndeterminate()) {
            accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, (float) getMin(), (float) getMax(), (float) getProgress()));
        }
        if (getStateDescription() != null) {
            return;
        }
        if (isIndeterminate()) {
            accessibilityNodeInfo.setStateDescription(getStringById(getContext(), "in_progress"));
        } else {
            accessibilityNodeInfo.setStateDescription(formatStateDescription(getProgress()));
        }
    }

    public synchronized void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        try {
            Drawable drawable = this.mCurrentDrawable;
            if (drawable != null) {
                i8 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, drawable.getIntrinsicWidth()));
                i10 = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, drawable.getIntrinsicHeight()));
            } else {
                i10 = 0;
                i8 = 0;
            }
            updateDrawableState();
            int resolveSizeAndState = View.resolveSizeAndState(getPaddingLeft() + getPaddingRight() + i8, i2, 0);
            int resolveSizeAndState2 = View.resolveSizeAndState(getPaddingTop() + getPaddingBottom() + i10, i7, 0);
            if (!this.mUseCustomWidthForCircleMode) {
                initCirCleStrokeWidth((resolveSizeAndState - getPaddingLeft()) - getPaddingRight());
            }
            if (this.mUseHorizontalProgress && this.mIndeterminate) {
                seslSetIndeterminateProgressDrawable((resolveSizeAndState - getPaddingLeft()) - getPaddingRight());
            }
            setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void onProgressRefresh(float f, boolean z, int i2) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            scheduleAccessibilityEventSender();
        }
        int i7 = this.mSecondaryProgress;
        if (i7 > this.mProgress && !z) {
            refreshProgress(16908303, i7, false, false);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setProgress(savedState.progress);
        setSecondaryProgress(savedState.secondaryProgress);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.progress = this.mProgress;
        savedState.secondaryProgress = this.mSecondaryProgress;
        return savedState;
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        updateDrawableBounds(i2, i7);
    }

    public void onSlidingRefresh(int i2) {
        Drawable drawable;
        Drawable drawable2 = this.mCurrentDrawable;
        if (drawable2 != null) {
            if (drawable2 instanceof LayerDrawable) {
                drawable = ((LayerDrawable) drawable2).findDrawableByLayerId(16908301);
            } else {
                drawable = null;
            }
            if (drawable != null) {
                drawable.setLevel(i2);
            }
        }
    }

    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        if (z != this.mAggregatedIsVisible) {
            this.mAggregatedIsVisible = z;
            if (this.mIndeterminate) {
                if (z) {
                    startAnimation();
                } else {
                    stopAnimation();
                }
            }
            Drawable drawable = this.mCurrentDrawable;
            if (drawable != null) {
                drawable.setVisible(z, false);
            }
        }
    }

    public void postInvalidate() {
        if (!this.mNoInvalidate) {
            super.postInvalidate();
        }
    }

    public synchronized void setIndeterminate(boolean z) {
        try {
            if (this.mOnlyIndeterminate) {
                if (!this.mIndeterminate) {
                }
            }
            if (z != this.mIndeterminate) {
                this.mIndeterminate = z;
                if (z) {
                    swapCurrentDrawable(this.mIndeterminateDrawable);
                    startAnimation();
                } else {
                    swapCurrentDrawable(this.mProgressDrawable);
                    stopAnimation();
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                if (this.mUseHorizontalProgress) {
                    stopAnimation();
                }
                this.mIndeterminateDrawable.setCallback((Drawable.Callback) null);
                unscheduleDrawable(this.mIndeterminateDrawable);
            }
            this.mIndeterminateDrawable = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                DrawableCompat.setLayoutDirection(drawable, ViewCompat.getLayoutDirection(this));
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                applyIndeterminateTint();
            }
            if (this.mIndeterminate) {
                if (this.mUseHorizontalProgress) {
                    startAnimation();
                }
                swapCurrentDrawable(drawable);
                postInvalidate();
            }
        }
    }

    public void setIndeterminateDrawableTiled(Drawable drawable) {
        if (drawable != null) {
            drawable = tileifyIndeterminate(drawable);
        }
        setIndeterminateDrawable(drawable);
    }

    public void setIndeterminateTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mIndeterminateTintList = colorStateList;
        progressTintInfo.mHasIndeterminateTint = true;
        applyIndeterminateTint();
    }

    public void setIndeterminateTintMode(PorterDuff.Mode mode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mIndeterminateTintMode = mode;
        progressTintInfo.mHasIndeterminateTintMode = true;
        applyIndeterminateTint();
    }

    public void setInterpolator(Context context, int i2) {
        setInterpolator(AnimationUtils.loadInterpolator(context, i2));
    }

    public synchronized void setMax(int i2) {
        int i7;
        try {
            boolean z = this.mMinInitialized;
            if (z && i2 < (i7 = this.mMin)) {
                i2 = i7;
            }
            this.mMaxInitialized = true;
            if (!z || i2 == this.mMax) {
                this.mMax = i2;
            } else {
                this.mMax = i2;
                postInvalidate();
                if (this.mProgress > i2) {
                    this.mProgress = i2;
                }
                refreshProgress(16908301, this.mProgress, false, false);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setMaxHeight(int i2) {
        this.mMaxHeight = i2;
        requestLayout();
    }

    public void setMaxWidth(int i2) {
        this.mMaxWidth = i2;
        requestLayout();
    }

    public synchronized void setMin(int i2) {
        int i7;
        try {
            boolean z = this.mMaxInitialized;
            if (z && i2 > (i7 = this.mMax)) {
                i2 = i7;
            }
            this.mMinInitialized = true;
            if (!z || i2 == this.mMin) {
                this.mMin = i2;
            } else {
                this.mMin = i2;
                postInvalidate();
                if (this.mProgress < i2) {
                    this.mProgress = i2;
                }
                refreshProgress(16908301, this.mProgress, false, false);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setMinHeight(int i2) {
        this.mMinHeight = i2;
        requestLayout();
    }

    public void setMinWidth(int i2) {
        this.mMinWidth = i2;
        requestLayout();
    }

    public void setMode(int i2) {
        Drawable drawable;
        this.mCurrentMode = i2;
        if (i2 == 3) {
            drawable = ContextCompat.getDrawable(getContext(), R$drawable.sesl_scrubber_progress_vertical);
        } else if (i2 != 4) {
            if (i2 == 7) {
                initializeRoundCircleMode();
            } else if (i2 == 9) {
                initializeGradientHorizontalMode();
            } else if (i2 == 10) {
                this.mIsGradient = true;
                setGradientColorsForCircle();
                initializeRoundCircleMode();
            }
            drawable = null;
        } else {
            drawable = ContextCompat.getDrawable(getContext(), R$drawable.sesl_split_seekbar_background_progress);
        }
        if (drawable != null) {
            setProgressDrawableTiled(drawable);
        }
    }

    public synchronized void setProgress(int i2) {
        setProgressInternal(i2, false, false);
    }

    public void setProgressBackgroundTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressBackgroundTintList = colorStateList;
        progressTintInfo.mHasProgressBackgroundTint = true;
        if (this.mProgressDrawable != null) {
            applyProgressBackgroundTint();
        }
    }

    public void setProgressBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressBackgroundTintMode = mode;
        progressTintInfo.mHasProgressBackgroundTintMode = true;
        if (this.mProgressDrawable != null) {
            applyProgressBackgroundTint();
        }
    }

    public void setProgressDrawable(Drawable drawable) {
        Drawable drawable2 = this.mProgressDrawable;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
                unscheduleDrawable(this.mProgressDrawable);
            }
            this.mProgressDrawable = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                DrawableCompat.setLayoutDirection(drawable, ViewCompat.getLayoutDirection(this));
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.mCurrentMode == 3) {
                    int minimumWidth = drawable.getMinimumWidth();
                    if (this.mMaxWidth < minimumWidth) {
                        this.mMaxWidth = minimumWidth;
                        requestLayout();
                    }
                } else {
                    int minimumHeight = drawable.getMinimumHeight();
                    if (this.mMaxHeight < minimumHeight) {
                        this.mMaxHeight = minimumHeight;
                        requestLayout();
                    }
                }
                applyProgressTints();
            }
            if (!this.mIndeterminate) {
                swapCurrentDrawable(drawable);
                postInvalidate();
            }
            updateDrawableBounds(getWidth(), getHeight());
            updateDrawableState();
            doRefreshProgress(16908301, this.mProgress, false, false, false);
            doRefreshProgress(16908303, this.mSecondaryProgress, false, false, false);
            if (getImportantForAccessibility() == 0) {
                setImportantForAccessibility(1);
            }
        }
    }

    public void setProgressDrawableTiled(Drawable drawable) {
        if (drawable != null) {
            drawable = tileify(drawable, false);
        }
        setProgressDrawable(drawable);
    }

    public synchronized boolean setProgressInternal(int i2, boolean z, boolean z3) {
        Drawable findDrawableByLayerId;
        Drawable findDrawableByLayerId2;
        if (this.mIndeterminate) {
            return false;
        }
        int clamp = MathUtils.clamp(i2, this.mMin, this.mMax);
        int i7 = this.mProgress;
        if (clamp == i7) {
            return false;
        }
        this.mVisualProgress = (float) i7;
        this.mProgress = clamp;
        if (this.mCurrentMode != 9 || !(getProgressDrawable() instanceof LayerDrawable) || (findDrawableByLayerId2 = ((LayerDrawable) getProgressDrawable()).findDrawableByLayerId(16908301)) == null || !(findDrawableByLayerId2 instanceof GradientHorizontalProgressDrawable)) {
            int i8 = this.mCurrentMode;
            if ((i8 == 7 || i8 == 10) && (getProgressDrawable() instanceof LayerDrawable) && (findDrawableByLayerId = ((LayerDrawable) getProgressDrawable()).findDrawableByLayerId(16908301)) != null && (findDrawableByLayerId instanceof CirCleProgressDrawable)) {
                ((CirCleProgressDrawable) findDrawableByLayerId).setProgress(clamp, z3);
            }
            refreshProgress(16908301, this.mProgress, z, z3);
            return true;
        }
        ((GradientHorizontalProgressDrawable) findDrawableByLayerId2).setProgress(clamp, z3);
        return true;
    }

    public void setProgressTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressTintList = colorStateList;
        progressTintInfo.mHasProgressTint = true;
        if (this.mProgressDrawable != null) {
            applyPrimaryProgressTint();
        }
    }

    public void setProgressTintMode(PorterDuff.Mode mode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressTintMode = mode;
        progressTintInfo.mHasProgressTintMode = true;
        if (this.mProgressDrawable != null) {
            applyPrimaryProgressTint();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setSecondaryProgress(int r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.mIndeterminate     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            int r0 = r2.mMin     // Catch:{ all -> 0x001f }
            if (r3 >= r0) goto L_0x000c
            r3 = r0
        L_0x000c:
            int r0 = r2.mMax     // Catch:{ all -> 0x001f }
            if (r3 <= r0) goto L_0x0011
            r3 = r0
        L_0x0011:
            int r0 = r2.mSecondaryProgress     // Catch:{ all -> 0x001f }
            if (r3 == r0) goto L_0x0021
            r2.mSecondaryProgress = r3     // Catch:{ all -> 0x001f }
            r0 = 16908303(0x102000f, float:2.387727E-38)
            r1 = 0
            r2.refreshProgress(r0, r3, r1, r1)     // Catch:{ all -> 0x001f }
            goto L_0x0021
        L_0x001f:
            r3 = move-exception
            goto L_0x0023
        L_0x0021:
            monitor-exit(r2)
            return
        L_0x0023:
            monitor-exit(r2)     // Catch:{ all -> 0x001f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslProgressBar.setSecondaryProgress(int):void");
    }

    public void setSecondaryProgressTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mSecondaryProgressTintList = colorStateList;
        progressTintInfo.mHasSecondaryProgressTint = true;
        if (this.mProgressDrawable != null) {
            applySecondaryProgressTint();
        }
    }

    public void setSecondaryProgressTintMode(PorterDuff.Mode mode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mSecondaryProgressTintMode = mode;
        progressTintInfo.mHasSecondaryProgressTintMode = true;
        if (this.mProgressDrawable != null) {
            applySecondaryProgressTint();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateDrawableBounds(int r10, int r11) {
        /*
            r9 = this;
            int r0 = r9.getPaddingRight()
            int r1 = r9.getPaddingLeft()
            int r1 = r1 + r0
            int r10 = r10 - r1
            int r0 = r9.getPaddingTop()
            int r1 = r9.getPaddingBottom()
            int r1 = r1 + r0
            int r11 = r11 - r1
            android.graphics.drawable.Drawable r0 = r9.mIndeterminateDrawable
            r1 = 0
            if (r0 == 0) goto L_0x0078
            boolean r2 = r9.mOnlyIndeterminate
            if (r2 == 0) goto L_0x0060
            boolean r2 = r0 instanceof android.graphics.drawable.AnimationDrawable
            if (r2 != 0) goto L_0x0060
            int r0 = r0.getIntrinsicWidth()
            android.graphics.drawable.Drawable r2 = r9.mIndeterminateDrawable
            int r2 = r2.getIntrinsicHeight()
            float r0 = (float) r0
            float r2 = (float) r2
            float r0 = r0 / r2
            float r2 = (float) r10
            float r3 = (float) r11
            float r4 = r2 / r3
            float r5 = r0 - r4
            float r5 = java.lang.Math.abs(r5)
            double r5 = (double) r5
            r7 = 4502148214488346440(0x3e7ad7f29abcaf48, double:1.0E-7)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L_0x0060
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 <= 0) goto L_0x0051
            float r3 = r3 * r0
            int r0 = (int) r3
            int r2 = r10 - r0
            int r2 = r2 / 2
            int r0 = r0 + r2
            r3 = r2
            r2 = r0
            r0 = r1
            goto L_0x0063
        L_0x0051:
            r3 = 1065353216(0x3f800000, float:1.0)
            float r3 = r3 / r0
            float r3 = r3 * r2
            int r0 = (int) r3
            int r11 = r11 - r0
            int r11 = r11 / 2
            int r0 = r0 + r11
            r2 = r0
            r0 = r11
            r11 = r2
            r2 = r10
            r3 = r1
            goto L_0x0063
        L_0x0060:
            r2 = r10
            r0 = r1
            r3 = r0
        L_0x0063:
            boolean r4 = r9.mMirrorForRtl
            if (r4 == 0) goto L_0x0072
            boolean r4 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r9)
            if (r4 == 0) goto L_0x0072
            int r2 = r10 - r2
            int r10 = r10 - r3
            r3 = r2
            goto L_0x0073
        L_0x0072:
            r10 = r2
        L_0x0073:
            android.graphics.drawable.Drawable r2 = r9.mIndeterminateDrawable
            r2.setBounds(r3, r0, r10, r11)
        L_0x0078:
            android.graphics.drawable.Drawable r9 = r9.mProgressDrawable
            if (r9 == 0) goto L_0x007f
            r9.setBounds(r1, r1, r10, r11)
        L_0x007f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslProgressBar.updateDrawableBounds(int, int):void");
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (drawable == this.mProgressDrawable || drawable == this.mIndeterminateDrawable || super.verifyDrawable(drawable)) {
            return true;
        }
        return false;
    }

    public SeslProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842871);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public SeslProgressBar(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeslProgressBar(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        boolean z = false;
        this.mCurrentMode = 0;
        this.mUseCustomWidthForCircleMode = false;
        this.mIsGradient = false;
        this.mCircleGradientStyle = 0;
        this.mUseHorizontalProgress = false;
        this.mSampleWidth = 0;
        this.mMirrorForRtl = false;
        this.mRefreshData = new ArrayList<>();
        this.VISUAL_PROGRESS = new FloatProperty<SeslProgressBar>("visual_progress") {
            public Float get(SeslProgressBar seslProgressBar) {
                return Float.valueOf(seslProgressBar.mVisualProgress);
            }

            public void setValue(SeslProgressBar seslProgressBar, float f) {
                seslProgressBar.setVisualProgress(16908301, f);
                float unused = seslProgressBar.mVisualProgress = f;
            }
        };
        this.mUiThreadId = Thread.currentThread().getId();
        initProgressBar();
        int[] iArr = R$styleable.ProgressBar;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, i7);
        Context context2 = context;
        try {
            saveAttributeDataForStyleable(context2, iArr, attributeSet, obtainStyledAttributes, i2, i7);
            this.mNoInvalidate = true;
            Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.ProgressBar_android_progressDrawable);
            if (drawable != null) {
                if (needsTileify(drawable)) {
                    setProgressDrawableTiled(drawable);
                } else {
                    setProgressDrawable(drawable);
                }
            }
            this.mDuration = obtainStyledAttributes.getInt(R$styleable.ProgressBar_android_indeterminateDuration, this.mDuration);
            this.mCircleGradientStyle = obtainStyledAttributes.getInt(R$styleable.ProgressBar_sesl_progressCircleGradientStyle, this.mCircleGradientStyle);
            this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ProgressBar_android_minWidth, this.mMinWidth);
            this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ProgressBar_android_maxWidth, this.mMaxWidth);
            this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ProgressBar_android_minHeight, this.mMinHeight);
            this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ProgressBar_android_maxHeight, this.mMaxHeight);
            this.mBehavior = obtainStyledAttributes.getInt(R$styleable.ProgressBar_android_indeterminateBehavior, this.mBehavior);
            int resourceId = obtainStyledAttributes.getResourceId(R$styleable.ProgressBar_android_interpolator, 17432587);
            if (resourceId > 0) {
                setInterpolator(context2, resourceId);
            }
            setMin(obtainStyledAttributes.getInt(R$styleable.ProgressBar_android_min, this.mMin));
            setMax(obtainStyledAttributes.getInt(R$styleable.ProgressBar_android_max, this.mMax));
            setProgress(obtainStyledAttributes.getInt(R$styleable.ProgressBar_android_progress, this.mProgress));
            setSecondaryProgress(obtainStyledAttributes.getInt(R$styleable.ProgressBar_android_secondaryProgress, this.mSecondaryProgress));
            Drawable drawable2 = obtainStyledAttributes.getDrawable(R$styleable.ProgressBar_android_indeterminateDrawable);
            if (drawable2 != null) {
                if (needsTileify(drawable2)) {
                    setIndeterminateDrawableTiled(drawable2);
                } else {
                    setIndeterminateDrawable(drawable2);
                }
            }
            boolean z3 = obtainStyledAttributes.getBoolean(R$styleable.ProgressBar_android_indeterminateOnly, this.mOnlyIndeterminate);
            this.mOnlyIndeterminate = z3;
            this.mNoInvalidate = false;
            setIndeterminate((z3 || obtainStyledAttributes.getBoolean(R$styleable.ProgressBar_android_indeterminate, this.mIndeterminate)) ? true : z);
            this.mMirrorForRtl = obtainStyledAttributes.getBoolean(R$styleable.ProgressBar_android_mirrorForRtl, this.mMirrorForRtl);
            int i8 = R$styleable.ProgressBar_android_progressTintMode;
            if (obtainStyledAttributes.hasValue(i8)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mProgressTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i8, -1), (PorterDuff.Mode) null);
                this.mProgressTintInfo.mHasProgressTintMode = true;
            }
            int i10 = R$styleable.ProgressBar_android_progressTint;
            if (obtainStyledAttributes.hasValue(i10)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mProgressTintList = obtainStyledAttributes.getColorStateList(i10);
                this.mProgressTintInfo.mHasProgressTint = true;
            }
            int i11 = R$styleable.ProgressBar_android_progressBackgroundTintMode;
            if (obtainStyledAttributes.hasValue(i11)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mProgressBackgroundTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i11, -1), (PorterDuff.Mode) null);
                this.mProgressTintInfo.mHasProgressBackgroundTintMode = true;
            }
            int i12 = R$styleable.ProgressBar_android_progressBackgroundTint;
            if (obtainStyledAttributes.hasValue(i12)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mProgressBackgroundTintList = obtainStyledAttributes.getColorStateList(i12);
                this.mProgressTintInfo.mHasProgressBackgroundTint = true;
            }
            int i13 = R$styleable.ProgressBar_android_secondaryProgressTintMode;
            if (obtainStyledAttributes.hasValue(i13)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mSecondaryProgressTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i13, -1), (PorterDuff.Mode) null);
                this.mProgressTintInfo.mHasSecondaryProgressTintMode = true;
            }
            int i14 = R$styleable.ProgressBar_android_secondaryProgressTint;
            if (obtainStyledAttributes.hasValue(i14)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mSecondaryProgressTintList = obtainStyledAttributes.getColorStateList(i14);
                this.mProgressTintInfo.mHasSecondaryProgressTint = true;
            }
            int i15 = R$styleable.ProgressBar_android_indeterminateTintMode;
            if (obtainStyledAttributes.hasValue(i15)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mIndeterminateTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i15, -1), (PorterDuff.Mode) null);
                this.mProgressTintInfo.mHasIndeterminateTintMode = true;
            }
            int i16 = R$styleable.ProgressBar_android_indeterminateTint;
            if (obtainStyledAttributes.hasValue(i16)) {
                if (this.mProgressTintInfo == null) {
                    this.mProgressTintInfo = new ProgressTintInfo();
                }
                this.mProgressTintInfo.mIndeterminateTintList = obtainStyledAttributes.getColorStateList(i16);
                this.mProgressTintInfo.mHasIndeterminateTint = true;
            }
            boolean z7 = obtainStyledAttributes.getBoolean(R$styleable.ProgressBar_seslUseCustomWidthForCircleMode, this.mUseCustomWidthForCircleMode);
            this.mUseCustomWidthForCircleMode = z7;
            if (z7) {
                this.mRoundStrokeWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ProgressBar_seslCircleModeStrokeWidth, getResources().getDimensionPixelSize(R$dimen.sesl_progress_circle_size_small_width));
                this.mCirclePadding = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ProgressBar_seslCircleModePadding, getResources().getDimensionPixelOffset(R$dimen.sesl_progress_circle_size_small_padding));
            }
            this.mUseHorizontalProgress = obtainStyledAttributes.getBoolean(R$styleable.ProgressBar_useHorizontalProgress, this.mUseHorizontalProgress);
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context2, R$style.Base_V7_Theme_AppCompat_Light);
            this.mIndeterminateHorizontalXsmall = getResources().getDrawable(R$drawable.sesl_progress_bar_indeterminate_xsmall_transition, contextThemeWrapper.getTheme());
            this.mIndeterminateHorizontalSmall = getResources().getDrawable(R$drawable.sesl_progress_bar_indeterminate_small_transition, contextThemeWrapper.getTheme());
            this.mIndeterminateHorizontalMedium = getResources().getDrawable(R$drawable.sesl_progress_bar_indeterminate_medium_transition, contextThemeWrapper.getTheme());
            this.mIndeterminateHorizontalLarge = getResources().getDrawable(R$drawable.sesl_progress_bar_indeterminate_large_transition, contextThemeWrapper.getTheme());
            this.mIndeterminateHorizontalXlarge = getResources().getDrawable(R$drawable.sesl_progress_bar_indeterminate_xlarge_transition, contextThemeWrapper.getTheme());
            obtainStyledAttributes.recycle();
            applyProgressTints();
            applyIndeterminateTint();
            if (ViewCompat.getImportantForAccessibility(this) == 0) {
                ViewCompat.setImportantForAccessibility(this, 1);
            }
            this.mDensity = context2.getResources().getDisplayMetrics().density;
            this.mCircleAnimationCallback = new CircleAnimationCallback(this);
        } catch (Throwable th) {
            Throwable th2 = th;
            obtainStyledAttributes.recycle();
            throw th2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class GradientHorizontalProgressDrawable extends Drawable {
        private final IntProperty<GradientHorizontalProgressDrawable> VISUAL_HORIZONTAL_PROGRESS = new IntProperty<GradientHorizontalProgressDrawable>("visual_progress") {
            public Integer get(GradientHorizontalProgressDrawable gradientHorizontalProgressDrawable) {
                return Integer.valueOf(gradientHorizontalProgressDrawable.mProgress);
            }

            public void setValue(GradientHorizontalProgressDrawable gradientHorizontalProgressDrawable, int i2) {
                gradientHorizontalProgressDrawable.mProgress = i2;
                GradientHorizontalProgressDrawable.this.invalidateSelf();
            }
        };
        private int mAlpha = ScoverState.TYPE_NFC_SMART_COVER;
        int mColor;
        private int[] mGradientColors;
        private float[] mGradientPositions;
        private final boolean mIsBackground;
        private final Paint mPaint;
        public int mProgress = 0;

        public GradientHorizontalProgressDrawable(boolean z, int i2) {
            Paint paint = new Paint();
            this.mPaint = paint;
            this.mIsBackground = z;
            this.mColor = i2;
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.mColor);
        }

        private int modulateAlpha(int i2, int i7) {
            return ((i7 + (i7 >>> 7)) * i2) >>> 8;
        }

        public void draw(Canvas canvas) {
            float f;
            Rect bounds = getBounds();
            if (bounds.width() > 0 && bounds.height() > 0) {
                float dimensionPixelSize = ((float) SeslProgressBar.this.getResources().getDimensionPixelSize(R$dimen.sesl_progress_bar_height)) / 2.0f;
                float exactCenterY = bounds.exactCenterY();
                float f5 = exactCenterY - dimensionPixelSize;
                float f8 = exactCenterY + dimensionPixelSize;
                int alpha = this.mPaint.getAlpha();
                this.mPaint.setAlpha(modulateAlpha(alpha, this.mAlpha));
                if (this.mIsBackground) {
                    this.mPaint.setColor(this.mColor);
                    this.mPaint.setShader((Shader) null);
                    canvas.drawRoundRect(new RectF((float) bounds.left, f5, (float) bounds.right, f8), dimensionPixelSize, dimensionPixelSize, this.mPaint);
                } else {
                    int access$1100 = SeslProgressBar.this.mMax - SeslProgressBar.this.mMin;
                    if (access$1100 > 0) {
                        f = ((float) (this.mProgress - SeslProgressBar.this.mMin)) / ((float) access$1100);
                    } else {
                        f = 0.0f;
                    }
                    float width = ((float) bounds.width()) * f;
                    if (width > 0.0f) {
                        this.mPaint.setShader(new LinearGradient((float) bounds.left, f5, (float) bounds.right, f5, this.mGradientColors, this.mGradientPositions, Shader.TileMode.CLAMP));
                        int i2 = bounds.left;
                        canvas.drawRoundRect(new RectF((float) i2, f5, ((float) i2) + width, f8), dimensionPixelSize, dimensionPixelSize, this.mPaint);
                    }
                }
                this.mPaint.setAlpha(alpha);
            }
        }

        public int getOpacity() {
            if (this.mPaint.getXfermode() != null) {
                return -3;
            }
            int alpha = this.mPaint.getAlpha();
            if (alpha == 0) {
                return -2;
            }
            if (alpha == 255) {
                return -1;
            }
            return -3;
        }

        public boolean isStateful() {
            return true;
        }

        public void setAlpha(int i2) {
            if (this.mAlpha != i2) {
                this.mAlpha = i2;
                invalidateSelf();
            }
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        public void setProgress(int i2, boolean z) {
            if (z) {
                ObjectAnimator ofInt = ObjectAnimator.ofInt(this, this.VISUAL_HORIZONTAL_PROGRESS, new int[]{i2});
                ofInt.setAutoCancel(true);
                ofInt.setDuration(80);
                ofInt.setInterpolator(SeslProgressBar.PROGRESS_ANIM_INTERPOLATOR);
                ofInt.start();
                return;
            }
            this.mProgress = i2;
            invalidateSelf();
        }

        public GradientHorizontalProgressDrawable(boolean z, int[] iArr, float[] fArr) {
            Paint paint = new Paint();
            this.mPaint = paint;
            this.mIsBackground = z;
            this.mGradientColors = iArr;
            this.mGradientPositions = fArr;
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeCap(Paint.Cap.ROUND);
        }
    }

    public void onVisualProgressChanged(int i2, float f) {
    }
}
