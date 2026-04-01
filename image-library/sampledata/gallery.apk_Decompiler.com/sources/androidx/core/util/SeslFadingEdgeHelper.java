package androidx.core.util;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import androidx.core.R$dimen;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.reflect.graphics.SeslCanvasReflector;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import o.c;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslFadingEdgeHelper {
    private static final float[] INTERPOLATOR_BOTTOM = {0.35f, 0.0f, 0.4f, 1.0f};
    private static final float[] INTERPOLATOR_BOTTOM_EXTRA = {0.35f, 0.0f, 0.6f, 1.0f};
    private static final float[] INTERPOLATOR_BOTTOM_EXTRA_WITH_NAVI_BAR = {0.35f, 0.0f, 0.6f, 1.0f};
    private static final float[] INTERPOLATOR_BOTTOM_WITH_NAVI_BAR = {0.46f, 0.0f, 0.58f, 1.0f};
    private static final float[] INTERPOLATOR_TOP = {0.42f, 0.0f, 0.58f, 1.0f};
    private static final float[] INTERPOLATOR_TOP_EXTRA = {0.7f, 0.0f, 0.8f, 1.0f};
    private boolean mAllowTopFadingEdgeEdgeWithoutEdgeToEdge = false;
    private final ColorAnimationManager mAnimationManager = new ColorAnimationManager();
    private int mBottomSaveCount = -1;
    private RuntimeShader mBottomShader = null;
    private int mCanvasSaveCount;
    private int mColor = 0;
    private final Context mContext;
    private int mDistanceFromWindowBottom = 0;
    private boolean mExtendBottomFadingEdge = false;
    private boolean mExtendTopFadingEdge = false;
    private RuntimeShader mExtraBottomShader = null;
    private float mExtraTopRatio = -1.0f;
    private RuntimeShader mExtraTopShader = null;
    private int mFadingEdgeBottomHeight;
    private int mFadingEdgeBottomOffset = 0;
    private int mFadingEdgeBottomPadding = 0;
    private final Matrix mFadingEdgeMatrix = new Matrix();
    private int mFadingEdgeOnNaviBarBottomHeight;
    private final Paint mFadingEdgePaint;
    private int mFadingEdgeTopHeight;
    private int mForcedFadingEdgeBottomHeight = 0;
    private int mForcedFadingEdgeTopHeight = 0;
    private boolean mHideBottom = false;
    private boolean mHideTop = false;
    private boolean mIsAppCustomized = false;
    private boolean mIsFadingEdgeEnabled = false;
    private boolean mIsNaviBarOverlapped = false;
    private int mNaviBarTop = -1;
    private Rect mRectForFadingEffect;
    private View mTargetView;
    private int mTopSaveCount = -1;
    private RuntimeShader mTopShader = null;
    private boolean mWindowBottomAlignment = true;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ColorAnimationManager {
        private ValueAnimator mAnimator;
        private int mCurrentColor = 0;
        private int mTargetColor = 0;

        public ColorAnimationManager() {
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$startAnimation$0(int i2, int i7, Runnable runnable, ValueAnimator valueAnimator) {
            int blendARGB = ColorUtils.blendARGB(i2, i7, valueAnimator.getAnimatedFraction());
            this.mCurrentColor = blendARGB;
            SeslFadingEdgeHelper.this.applyColor(blendARGB);
            runnable.run();
        }

        public void cancelCurrentAnimation() {
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.mAnimator.cancel();
            }
        }

        public int getTargetColor() {
            return this.mTargetColor;
        }

        public void setTargetColorImmediate(int i2) {
            this.mTargetColor = i2;
            this.mCurrentColor = i2;
        }

        public void startAnimation(int i2, int i7, Runnable runnable) {
            cancelCurrentAnimation();
            this.mCurrentColor = i2;
            this.mTargetColor = i7;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mAnimator = ofFloat;
            ofFloat.setDuration(300);
            this.mAnimator.addUpdateListener(new a(this, i2, i7, runnable));
            this.mAnimator.start();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum EdgeType {
        TOP(false, 0.0f),
        BOTTOM(true, 180.0f);
        
        private final boolean mCanUseExtendedGradient;
        private final float mRotationDegrees;

        private EdgeType(boolean z, float f) {
            this.mCanUseExtendedGradient = z;
            this.mRotationDegrees = f;
        }

        public float getRotationDegrees() {
            return this.mRotationDegrees;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ScrollInfoProvider {
        int computeVerticalScrollExtent();

        int computeVerticalScrollOffset();

        int computeVerticalScrollRange();

        int getLastItemHeightIfVisible();

        boolean shouldNormalizeFadingEdge();
    }

    public SeslFadingEdgeHelper(Context context) {
        Paint paint = new Paint();
        this.mFadingEdgePaint = paint;
        this.mContext = context;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    private void animateColorChange(int i2, int i7, Runnable runnable) {
        this.mAnimationManager.startAnimation(i2, i7, runnable);
    }

    /* access modifiers changed from: private */
    public void applyColor(int i2) {
        int i7 = i2 & 16777215;
        this.mColor = i7;
        RuntimeShader runtimeShader = this.mTopShader;
        if (runtimeShader != null) {
            updateShaderColor(runtimeShader, i7);
        }
        RuntimeShader runtimeShader2 = this.mBottomShader;
        if (runtimeShader2 != null) {
            updateShaderColor(runtimeShader2, this.mColor);
        }
        RuntimeShader runtimeShader3 = this.mExtraTopShader;
        if (runtimeShader3 != null) {
            updateShaderColor(runtimeShader3, this.mColor);
        }
        RuntimeShader runtimeShader4 = this.mExtraBottomShader;
        if (runtimeShader4 != null) {
            updateShaderColor(runtimeShader4, this.mColor);
        }
    }

    private int calculateDynamicBottomHeight(ScrollInfoProvider scrollInfoProvider) {
        int i2;
        if (this.mTargetView == null) {
            return 0;
        }
        Rect rect = this.mRectForFadingEffect;
        int i7 = rect.top;
        int i8 = rect.bottom;
        if (this.mIsAppCustomized || !isNaviBarOverlapped()) {
            i2 = this.mFadingEdgeBottomHeight;
        } else {
            i2 = this.mFadingEdgeOnNaviBarBottomHeight;
        }
        if (i7 + i2 > i8 - i2) {
            i2 = (i8 - i7) / 2;
        }
        int max = Math.max(i2, 0);
        if (max == 0) {
            return 0;
        }
        int max2 = Math.max((Math.max(scrollInfoProvider.computeVerticalScrollRange() + this.mDistanceFromWindowBottom, 0) - Math.max(scrollInfoProvider.computeVerticalScrollExtent(), 0)) - Math.max(scrollInfoProvider.computeVerticalScrollOffset(), 0), 0);
        if (scrollInfoProvider.shouldNormalizeFadingEdge()) {
            int lastItemHeightIfVisible = scrollInfoProvider.getLastItemHeightIfVisible();
            if (lastItemHeightIfVisible <= 0) {
                max2 = max;
            } else if (lastItemHeightIfVisible < max) {
                max2 = (int) ((((float) max) / ((float) lastItemHeightIfVisible)) * ((float) max2));
            }
        }
        return Math.max(this.mForcedFadingEdgeBottomHeight, Math.min(max2, max));
    }

    private int calculateDynamicTopHeight(ScrollInfoProvider scrollInfoProvider) {
        if (this.mTargetView == null) {
            return 0;
        }
        Rect rect = this.mRectForFadingEffect;
        int i2 = rect.top;
        int i7 = rect.bottom;
        int i8 = this.mFadingEdgeTopHeight;
        if (i2 + i8 > i7 - i8) {
            i8 = (i7 - i2) / 2;
        }
        int max = Math.max(i8, 0);
        if (max == 0) {
            return 0;
        }
        int min = Math.min(Math.max(scrollInfoProvider.computeVerticalScrollOffset(), 0), max);
        if (!this.mAllowTopFadingEdgeEdgeWithoutEdgeToEdge) {
            int distanceFromWindowTop = getDistanceFromWindowTop();
            if (distanceFromWindowTop < 0 || distanceFromWindowTop > min) {
                return this.mForcedFadingEdgeTopHeight;
            }
            min -= distanceFromWindowTop;
        }
        return Math.min(Math.max(this.mForcedFadingEdgeTopHeight, min), this.mFadingEdgeTopHeight);
    }

    private RuntimeShader createRuntimeShader(int i2, float f, float[] fArr) {
        if (Build.VERSION.SDK_INT < 33) {
            return null;
        }
        RuntimeShader c5 = c.c();
        c5.setFloatUniform(EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION, 1.0f, 1.0f);
        updateShaderStartAlpha(c5, f);
        updateShaderInterpolator(c5, fArr);
        updateShaderColor(c5, i2);
        return c5;
    }

    private int getDistanceFromWindowBottom() {
        View view = this.mTargetView;
        if (view == null || !this.mWindowBottomAlignment) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i2 = iArr[1];
        int height = this.mTargetView.getRootView().getHeight();
        int height2 = (this.mTargetView.getHeight() + i2) - height;
        if (height2 > 0) {
            Rect rect = new Rect();
            this.mTargetView.getLocalVisibleRect(rect);
            height2 += Math.max(0, height - (i2 + rect.bottom));
        }
        return Math.max(0, height2);
    }

    private int getDistanceFromWindowTop() {
        View view = this.mTargetView;
        if (view == null) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    private Shader getGradientForEdge(EdgeType edgeType) {
        RuntimeShader runtimeShader;
        RuntimeShader runtimeShader2;
        if (edgeType == EdgeType.TOP) {
            if (!this.mExtendTopFadingEdge || (runtimeShader2 = this.mExtraTopShader) == null) {
                return this.mTopShader;
            }
            return runtimeShader2;
        } else if (!this.mExtendBottomFadingEdge || (runtimeShader = this.mExtraBottomShader) == null) {
            return this.mBottomShader;
        } else {
            return runtimeShader;
        }
    }

    private int getPreviousColor() {
        int targetColor = this.mAnimationManager.getTargetColor();
        if (targetColor != 0) {
            return targetColor;
        }
        return this.mColor;
    }

    private boolean isNaviBarOverlapped() {
        View view = this.mTargetView;
        if (view != null) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int height = this.mTargetView.getHeight() + iArr[1];
            int i2 = this.mNaviBarTop;
            if (i2 <= 0 || height <= i2) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat lambda$setOnApplyWindowInsetsListener$0(View view, WindowInsetsCompat windowInsetsCompat) {
        int i2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
        if (i2 > 0) {
            if (Settings.Secure.getInt(view.getContext().getContentResolver(), "navigation_mode", 0) != 2) {
                this.mNaviBarTop = Resources.getSystem().getDisplayMetrics().heightPixels - i2;
            }
            return windowInsetsCompat;
        }
        this.mNaviBarTop = -1;
        return windowInsetsCompat;
    }

    private void renderBottomFadingEdge(Canvas canvas, int i2) {
        if (!this.mHideBottom) {
            Rect rect = this.mRectForFadingEffect;
            Canvas canvas2 = canvas;
            int i7 = i2;
            renderFadingEdge(canvas2, EdgeType.BOTTOM, i7, this.mBottomSaveCount, (float) rect.left, (float) (rect.bottom - this.mFadingEdgeBottomPadding));
        }
    }

    private void renderFadingEdge(Canvas canvas, EdgeType edgeType, int i2, int i7, float f, float f5) {
        this.mFadingEdgeMatrix.setScale(1.0f, (float) i2);
        if (edgeType.getRotationDegrees() > 0.0f) {
            this.mFadingEdgeMatrix.postRotate(edgeType.getRotationDegrees());
        }
        this.mFadingEdgeMatrix.postTranslate(f, f5);
        Shader gradientForEdge = getGradientForEdge(edgeType);
        if (gradientForEdge != null) {
            gradientForEdge.setLocalMatrix(this.mFadingEdgeMatrix);
            this.mFadingEdgePaint.setShader(gradientForEdge);
            if (getPreviousColor() == 0) {
                if (i7 > 0) {
                    SeslCanvasReflector.restoreUnclippedLayer(canvas, i7, this.mFadingEdgePaint);
                }
            } else if (i2 <= 0) {
            } else {
                if (edgeType == EdgeType.TOP) {
                    Rect rect = this.mRectForFadingEffect;
                    float f8 = (float) rect.left;
                    int i8 = rect.top;
                    canvas.drawRect(f8, (float) i8, (float) rect.right, (float) (i8 + i2), this.mFadingEdgePaint);
                    return;
                }
                Canvas canvas2 = canvas;
                Rect rect2 = this.mRectForFadingEffect;
                int i10 = rect2.bottom;
                Paint paint = this.mFadingEdgePaint;
                float f10 = (float) ((i10 - i2) - this.mFadingEdgeBottomPadding);
                float f11 = (float) rect2.right;
                float f12 = f10;
                Canvas canvas3 = canvas2;
                canvas3.drawRect((float) rect2.left, f12, f11, (float) i10, paint);
            }
        }
    }

    private void renderTopFadingEdge(Canvas canvas, int i2) {
        if (!this.mHideTop) {
            Rect rect = this.mRectForFadingEffect;
            Canvas canvas2 = canvas;
            int i7 = i2;
            renderFadingEdge(canvas2, EdgeType.TOP, i7, this.mTopSaveCount, (float) rect.left, (float) rect.top);
        }
    }

    private void restoreCanvasState(Canvas canvas) {
        if (getPreviousColor() == 0) {
            canvas.restoreToCount(this.mCanvasSaveCount);
        }
        this.mRectForFadingEffect = null;
    }

    private void setColorImmediate(int i2) {
        this.mAnimationManager.cancelCurrentAnimation();
        this.mAnimationManager.setTargetColorImmediate(i2);
        applyColor(i2);
    }

    private void setOnApplyWindowInsetsListener() {
        p pVar;
        View view = this.mTargetView;
        if (view != null) {
            if (!this.mIsFadingEdgeEnabled || this.mIsAppCustomized) {
                pVar = null;
            } else {
                pVar = new p(21, this);
            }
            ViewCompat.setOnApplyWindowInsetsListener(view, pVar);
        }
    }

    private void updateShaderColor(RuntimeShader runtimeShader, int i2) {
        if (Build.VERSION.SDK_INT >= 33) {
            runtimeShader.setFloatUniform("color", ((float) Color.red(i2)) / 255.0f, ((float) Color.green(i2)) / 255.0f, ((float) Color.blue(i2)) / 255.0f, 1.0f);
        }
    }

    private void updateShaderInterpolator(RuntimeShader runtimeShader, float[] fArr) {
        if (Build.VERSION.SDK_INT >= 33) {
            runtimeShader.setFloatUniform("easing", fArr[0], fArr[1], fArr[2], fArr[3]);
        }
    }

    private void updateShaderStartAlpha(RuntimeShader runtimeShader, float f) {
        if (Build.VERSION.SDK_INT >= 33) {
            runtimeShader.setFloatUniform("startAlpha", f);
        }
    }

    private void updateShadersForNavBarType() {
        boolean z;
        float[] fArr;
        float[] fArr2;
        float f;
        if (this.mIsAppCustomized || !isNaviBarOverlapped()) {
            z = false;
        } else {
            z = true;
        }
        if (this.mIsNaviBarOverlapped != z) {
            this.mIsNaviBarOverlapped = z;
            RuntimeShader runtimeShader = this.mBottomShader;
            if (runtimeShader != null) {
                if (z) {
                    fArr2 = INTERPOLATOR_BOTTOM_WITH_NAVI_BAR;
                } else {
                    fArr2 = INTERPOLATOR_BOTTOM;
                }
                updateShaderInterpolator(runtimeShader, fArr2);
                RuntimeShader runtimeShader2 = this.mBottomShader;
                if (z) {
                    f = 0.04f;
                } else {
                    f = 0.2f;
                }
                updateShaderStartAlpha(runtimeShader2, f);
            }
            RuntimeShader runtimeShader3 = this.mExtraBottomShader;
            if (runtimeShader3 != null) {
                if (z) {
                    fArr = INTERPOLATOR_BOTTOM_EXTRA_WITH_NAVI_BAR;
                } else {
                    fArr = INTERPOLATOR_BOTTOM_EXTRA;
                }
                updateShaderInterpolator(runtimeShader3, fArr);
            }
        }
    }

    public void forceBottomFadingEdgeClamped(int i2) {
        this.mForcedFadingEdgeBottomHeight = Math.max(0, i2);
    }

    public void forceTopFadingEdgeClamped(int i2) {
        float f = this.mExtraTopRatio;
        if (f > 0.0f) {
            i2 = (int) (f * ((float) i2));
        }
        this.mForcedFadingEdgeTopHeight = Math.max(0, i2);
    }

    public int getFadingEdgeBottomOffset() {
        return this.mFadingEdgeBottomOffset;
    }

    public void hideBottomFadingEdge(boolean z) {
        if (this.mHideBottom != z) {
            this.mHideBottom = z;
            View view = this.mTargetView;
            if (view != null) {
                view.invalidate();
            }
        }
    }

    public void hideTopFadingEdge(boolean z) {
        if (this.mHideTop != z) {
            this.mHideTop = z;
            View view = this.mTargetView;
            if (view != null) {
                view.invalidate();
            }
        }
    }

    public boolean isFadingEdgeEnabled() {
        return this.mIsFadingEdgeEnabled;
    }

    public void prepareFadingEffect(Canvas canvas, int i2, int i7, int i8, int i10) {
        int i11;
        if (this.mIsFadingEdgeEnabled && Build.VERSION.SDK_INT >= 33) {
            updateShadersForNavBarType();
            int distanceFromWindowBottom = getDistanceFromWindowBottom();
            this.mDistanceFromWindowBottom = distanceFromWindowBottom;
            int i12 = i10 - distanceFromWindowBottom;
            int i13 = this.mFadingEdgeTopHeight;
            if (i7 + i13 > i12 - i13) {
                i13 = (i12 - i7) / 2;
            }
            if (this.mIsAppCustomized || !isNaviBarOverlapped()) {
                i11 = this.mFadingEdgeBottomHeight;
            } else {
                i11 = this.mFadingEdgeOnNaviBarBottomHeight;
            }
            if (i7 + i11 > i12 - i11) {
                i11 = (i12 - i7) / 2;
            }
            if (getPreviousColor() == 0) {
                this.mCanvasSaveCount = canvas.getSaveCount();
                this.mTopSaveCount = -1;
                this.mBottomSaveCount = -1;
                if (!this.mHideTop) {
                    this.mTopSaveCount = SeslCanvasReflector.saveUnclippedLayer(canvas, i2, i7, i8, i13 + i7);
                }
                if (!this.mHideBottom) {
                    this.mBottomSaveCount = SeslCanvasReflector.saveUnclippedLayer(canvas, i2, (i12 - i11) - this.mFadingEdgeBottomPadding, i8, i12);
                }
            }
            this.mRectForFadingEffect = new Rect(i2, i7, i8, i12);
        }
    }

    public void renderFadingEffect(Canvas canvas, ScrollInfoProvider scrollInfoProvider) {
        if (this.mIsFadingEdgeEnabled && this.mRectForFadingEffect != null && Build.VERSION.SDK_INT >= 33) {
            int calculateDynamicTopHeight = calculateDynamicTopHeight(scrollInfoProvider);
            renderBottomFadingEdge(canvas, calculateDynamicBottomHeight(scrollInfoProvider));
            renderTopFadingEdge(canvas, calculateDynamicTopHeight);
            restoreCanvasState(canvas);
        }
    }

    public void setAllowTopFadingEdgeWithoutEdgeToEdge(boolean z) {
        this.mAllowTopFadingEdgeEdgeWithoutEdgeToEdge = z;
    }

    public void setFadingEdgeBottomOffset(int i2) {
        this.mFadingEdgeBottomOffset = i2;
    }

    public void setFadingEdgeColor(int i2, Runnable runnable) {
        PorterDuff.Mode mode;
        int previousColor;
        Paint paint = this.mFadingEdgePaint;
        if (i2 == 0) {
            mode = PorterDuff.Mode.DST_OUT;
        } else {
            mode = PorterDuff.Mode.SRC_OVER;
        }
        paint.setXfermode(new PorterDuffXfermode(mode));
        if (runnable == null || (previousColor = getPreviousColor()) == 0 || i2 == 0) {
            setColorImmediate(i2);
        } else {
            animateColorChange(previousColor, i2, runnable);
        }
    }

    public void setFadingEdgeEnabled(boolean z, int i2, int i7) {
        this.mExtendTopFadingEdge = false;
        this.mExtendBottomFadingEdge = false;
        if (this.mIsFadingEdgeEnabled != z || (z && !(this.mFadingEdgeTopHeight == i2 && this.mFadingEdgeBottomHeight == i7))) {
            this.mIsFadingEdgeEnabled = z;
            if (z) {
                this.mFadingEdgeTopHeight = i2;
                this.mFadingEdgeBottomHeight = i7;
                this.mTopShader = createRuntimeShader(this.mColor, 0.04f, INTERPOLATOR_TOP);
                this.mBottomShader = createRuntimeShader(this.mColor, 0.04f, INTERPOLATOR_BOTTOM);
            } else {
                this.mTopShader = null;
                this.mBottomShader = null;
                this.mFadingEdgeBottomPadding = 0;
            }
        }
        setOnApplyWindowInsetsListener();
    }

    public void setTargetView(View view) {
        this.mTargetView = view;
    }

    public void setWindowBottomAlignment(boolean z) {
        this.mWindowBottomAlignment = z;
    }

    public void setFadingEdgeEnabled(boolean z, int i2, int i7, boolean z3) {
        this.mIsAppCustomized = z3;
        setFadingEdgeEnabled(z, i2, i7);
    }

    public void setFadingEdgeEnabled(boolean z) {
        Resources resources = this.mContext.getResources();
        setFadingEdgeEnabled(z, resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_top_height), resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_bottom_height));
        this.mFadingEdgeOnNaviBarBottomHeight = resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_on_navi_bar_bottom_height);
    }

    public void setFadingEdgeEnabled(boolean z, boolean z3, boolean z7) {
        int i2;
        int i7;
        Resources resources = this.mContext.getResources();
        if (z3) {
            this.mExtraTopShader = createRuntimeShader(this.mColor, 0.04f, INTERPOLATOR_TOP_EXTRA);
            i2 = resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_extra_top_height);
            this.mExtraTopRatio = ((float) i2) / ((float) resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_top_height));
        } else {
            this.mExtraTopShader = null;
            this.mExtraTopRatio = -1.0f;
            i2 = resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_top_height);
        }
        if (z7) {
            this.mExtraBottomShader = createRuntimeShader(this.mColor, 0.04f, INTERPOLATOR_BOTTOM_EXTRA);
            i7 = resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_extra_bottom_height);
            this.mFadingEdgeOnNaviBarBottomHeight = resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_on_navi_bar_extra_bottom_height);
        } else {
            this.mExtraBottomShader = null;
            i7 = resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_bottom_height);
            this.mFadingEdgeOnNaviBarBottomHeight = resources.getDimensionPixelSize(R$dimen.sesl_fading_edge_on_navi_bar_bottom_height);
        }
        setFadingEdgeEnabled(z, i2, i7);
        this.mExtendTopFadingEdge = z3;
        this.mExtendBottomFadingEdge = z7;
    }
}
