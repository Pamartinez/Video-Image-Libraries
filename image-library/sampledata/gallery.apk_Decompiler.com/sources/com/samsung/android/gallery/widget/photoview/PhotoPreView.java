package com.samsung.android.gallery.widget.photoview;

import N3.c;
import Nb.a;
import Nb.b;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.Optional;
import java.util.Stack;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoPreView extends AppCompatImageView {
    protected final StringCompat TAG;
    private float mCurrentScale;
    private final PhotoPreViewFrameCounterDelegate mFrameCounter;
    private BooleanSupplier mIsCenterCrop;
    private boolean mIsVideo;
    private int mItemHeight;
    private int mItemWidth;
    protected MarginParams mMargin = new MarginParams();
    private int mOrientation;
    private int mOrientationTag;
    private View mParentView;
    private int mSampleSize;
    private PointF mScaledPosition;

    public PhotoPreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        StringCompat stringCompat = new StringCompat(getClass().getSimpleName());
        this.TAG = stringCompat;
        this.mFrameCounter = new PhotoPreViewFrameCounterDelegate(stringCompat);
        if (getId() == R$id.viewer_container_preview) {
            stringCompat.setTag("C");
        }
    }

    private boolean isRotated(Drawable drawable) {
        int i2;
        boolean z;
        boolean z3;
        int i7 = this.mOrientation;
        if (!(i7 == 90 || i7 == 270)) {
            int i8 = this.mItemWidth;
            if (i8 > 0 && (i2 = this.mItemHeight) > 0) {
                if (i8 > i2) {
                    z = true;
                } else {
                    z = false;
                }
                if (drawable.getIntrinsicWidth() > drawable.getIntrinsicHeight()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z ^ z3) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private boolean isViewScaled() {
        if (this.mCurrentScale <= 0.0f || this.mScaledPosition == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$calculateParentViewSize$1(Size size, PhotoPreViewDebugDelegate photoPreViewDebugDelegate) {
        throw null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDisplayMinScale$2(float f, float f5, PhotoPreViewDebugDelegate photoPreViewDebugDelegate) {
        getParentViewSize();
        getMaxViewSize();
        throw null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDraw$3(Canvas canvas, PhotoPreViewDebugDelegate photoPreViewDebugDelegate) {
        throw null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBasicInfo$0(Bitmap bitmap) {
        setImageBitmap(bitmap);
        updateLayoutSize();
    }

    private void setImageMatrix() {
        Matrix matrix;
        if (isViewScaled()) {
            matrix = ImageMatrix.createScaledImageMatrix(ImageMatrix.MatrixParam.create(this, false).withOrientation(this.mOrientation).withOrientationTag(this.mOrientationTag), this.mCurrentScale * ((float) this.mSampleSize), this.mScaledPosition);
        } else {
            matrix = ImageMatrix.create(ImageMatrix.MatrixParam.create(this, true).withOrientation(this.mOrientation).withOrientationTag(this.mOrientationTag));
        }
        if (matrix.isIdentity()) {
            float[] fArr = new float[9];
            matrix.getValues(fArr);
            fArr[8] = 1.000001f;
            matrix.setValues(fArr);
        }
        setImageMatrix(matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    private void setLayoutParams(int i2, int i7, float f) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.width = getViewWidth(i2, f);
        layoutParams.height = getViewHeight(i7, f);
        setLayoutRuleAndMargins(layoutParams);
        setLayoutParams(layoutParams);
    }

    public Size calculateParentViewSize() {
        int i2;
        int i7;
        int i8;
        int i10;
        View findViewById = getRootView().findViewById(R$id.content);
        View view = this.mParentView;
        Stack stack = new Stack();
        while (view != null && view != findViewById) {
            stack.push(view);
            if (view.getParent() instanceof View) {
                view = (View) view.getParent();
            } else {
                view = null;
            }
        }
        int i11 = 0;
        if (SeApiCompat.isDexMode(getContext())) {
            Rect captionBarInsets = WindowUtils.getCaptionBarInsets(getRootWindowInsets());
            i8 = captionBarInsets.left;
            i7 = captionBarInsets.top;
            i2 = captionBarInsets.right;
            i10 = captionBarInsets.bottom;
        } else {
            i10 = 0;
            i8 = 0;
            i7 = 0;
            i2 = 0;
        }
        int size = stack.size();
        while (i11 < size) {
            View view2 = (View) stack.pop();
            int paddingLeft = getPaddingLeft() + i8;
            int paddingTop = getPaddingTop() + i7;
            int paddingRight = getPaddingRight() + i2;
            int paddingBottom = getPaddingBottom() + i10;
            if (view2.getFitsSystemWindows()) {
                Rect systemInsets = WindowUtils.getSystemInsets(getRootWindowInsets());
                paddingLeft = Math.max(paddingLeft, systemInsets.left);
                paddingTop = Math.max(paddingTop, systemInsets.top);
                paddingRight = Math.max(paddingRight, systemInsets.right);
                paddingBottom = Math.max(paddingBottom, systemInsets.bottom);
            }
            int leftMargin = ViewMarginUtils.getLeftMargin(view2) + paddingLeft;
            i11++;
            int topMargin = paddingTop + ViewMarginUtils.getTopMargin(view2);
            i8 = leftMargin;
            i10 = paddingBottom + ViewMarginUtils.getBottomMargin(view2);
            i2 = paddingRight + ViewMarginUtils.getRightMargin(view2);
            i7 = topMargin;
        }
        Size size2 = new Size((ViewUtils.getWidth(getRootView()) - i8) - i2, (ViewUtils.getHeight(getRootView()) - i7) - i10);
        Optional.ofNullable((Object) null).ifPresent(new c(4, this, size2));
        return size2;
    }

    public void destroy() {
        setImageBitmap((Bitmap) null);
        setImageDrawable((Drawable) null);
    }

    public float getDisplayMinScale(int i2, int i7) {
        float width = ((float) getMaxViewSize().getWidth()) / ((float) i2);
        float height = ((float) getMaxViewSize().getHeight()) / ((float) i7);
        Optional.ofNullable((Object) null).ifPresent(new b(this, width, height));
        BooleanSupplier booleanSupplier = this.mIsCenterCrop;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            return Math.min(width, height);
        }
        return Math.max(width, height);
    }

    public int getFrameCount() {
        return this.mFrameCounter.getFrameCount();
    }

    public Size getMaxViewSize() {
        int height = getParentViewSize().getHeight();
        MarginParams marginParams = this.mMargin;
        int i2 = (height - marginParams.bottomMargin) - marginParams.topMargin;
        int width = getParentViewSize().getWidth();
        MarginParams marginParams2 = this.mMargin;
        return new Size((width - marginParams2.leftMargin) - marginParams2.rightMargin, i2);
    }

    public Size getParentViewSize() {
        View view = this.mParentView;
        if (view == null || view.getWidth() == 0 || this.mParentView.getHeight() == 0) {
            return calculateParentViewSize();
        }
        return new Size(ViewUtils.getWidth(this.mParentView), ViewUtils.getHeight(this.mParentView));
    }

    public float getScale(int i2, int i7) {
        float displayMinScale = getDisplayMinScale(i2, i7);
        if (this.mIsVideo) {
            return displayMinScale;
        }
        return Math.min(displayMinScale, 5.0f);
    }

    public int getViewHeight(int i2, float f) {
        return Math.min(getMaxViewSize().getHeight(), Math.round(((float) i2) * f));
    }

    public int getViewWidth(int i2, float f) {
        return Math.min(getMaxViewSize().getWidth(), Math.round(((float) i2) * f));
    }

    public boolean isWindowMode() {
        Resources resources;
        boolean z;
        boolean z3;
        Context context = getContext();
        Configuration configuration = null;
        if (context != null) {
            resources = getContext().getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            configuration = resources.getConfiguration();
        }
        if (!SdkConfig.atLeast(SdkConfig.SEM.S) || configuration == null || !configuration.semIsPopOver()) {
            z = false;
        } else {
            z = true;
        }
        if (!(context instanceof Activity) || !SystemUi.isInMultiWindowMode((Activity) getContext())) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z || z3) {
            return true;
        }
        return false;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Optional.ofNullable((Object) null).ifPresent(new a(canvas, 0));
    }

    public void setBasicInfo(Bitmap bitmap, MediaItem mediaItem, MarginParams marginParams) {
        int i2;
        int i7;
        int i8 = 0;
        if (mediaItem.isVideo() || mediaItem.isBroken()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        this.mOrientation = i2;
        this.mOrientationTag = mediaItem.getOrientationTag();
        this.mMargin = marginParams;
        if (mediaItem.isMtp()) {
            i7 = 0;
        } else {
            i7 = mediaItem.getWidth();
        }
        this.mItemWidth = i7;
        if (!mediaItem.isMtp()) {
            i8 = mediaItem.getHeight();
        }
        this.mItemHeight = i8;
        this.mIsVideo = mediaItem.isVideo();
        ViewParent parent = getParent();
        if (parent instanceof View) {
            this.mParentView = (View) parent;
        }
        if (isAttachedToWindow()) {
            setImageBitmap(bitmap);
            updateLayoutSize();
            return;
        }
        ViewUtils.post(this.mParentView, new M5.a(11, this, bitmap));
    }

    public void setBottomMargin(int i2) {
        this.mMargin.bottomMargin = i2;
        updateLayoutSize();
    }

    public void setCenterCrop(BooleanSupplier booleanSupplier) {
        this.mIsCenterCrop = booleanSupplier;
    }

    public boolean setFrame(int i2, int i7, int i8, int i10) {
        boolean frame = super.setFrame(i2, i7, i8, i10);
        this.mFrameCounter.execFrameCounter();
        if (frame) {
            updateLayoutSize();
        }
        return frame;
    }

    public void setImageBitmap(Bitmap bitmap) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "setImageBitmap " + Logger.toString(bitmap));
        super.setImageBitmap(bitmap);
    }

    public void setLayoutRuleAndMargins(RelativeLayout.LayoutParams layoutParams) {
        boolean z;
        MarginParams marginParams = this.mMargin;
        boolean z3 = true;
        if (marginParams.bottomMargin == 0 && marginParams.topMargin == 0) {
            z = false;
        } else {
            z = true;
        }
        if (marginParams.leftMargin == 0 && marginParams.rightMargin == 0) {
            z3 = false;
        }
        layoutParams.addRule(14);
        layoutParams.addRule(15);
        layoutParams.setMargins(0, 0, 0, 0);
        if (z) {
            int height = (getMaxViewSize().getHeight() - layoutParams.height) / 2;
            layoutParams.removeRule(15);
            int i2 = layoutParams.leftMargin;
            MarginParams marginParams2 = this.mMargin;
            layoutParams.setMargins(i2, marginParams2.topMargin + height, layoutParams.rightMargin, marginParams2.bottomMargin + height);
        }
        if (z3) {
            int width = (getMaxViewSize().getWidth() - layoutParams.width) / 2;
            layoutParams.removeRule(14);
            MarginParams marginParams3 = this.mMargin;
            layoutParams.setMargins(marginParams3.leftMargin + width, layoutParams.topMargin, marginParams3.rightMargin + width, layoutParams.bottomMargin);
        }
    }

    public void setLogTag(int i2) {
        this.TAG.setTag(Integer.valueOf(i2));
    }

    public void setScaledTransitionInfo(float f, PointF pointF) {
        this.mCurrentScale = f;
        this.mScaledPosition = pointF;
    }

    public void setVisibility(int i2) {
        String str;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        StringCompat stringCompat = this.TAG;
        String visibilityValue = Logger.toVisibilityValue(i2);
        if (layoutParams != null) {
            str = layoutParams.width + "x" + layoutParams.height;
        } else {
            str = "null";
        }
        Log.d(stringCompat, "setVisibility", visibilityValue, str, getMaxViewSize());
        super.setVisibility(i2);
    }

    public String toString() {
        return Logger.v(this, getDrawable());
    }

    public void updateLayoutSize() {
        int i2;
        float f;
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int i7 = this.mItemWidth;
            if (i7 <= 0) {
                i7 = drawable.getIntrinsicWidth();
            }
            int i8 = this.mItemHeight;
            if (i8 <= 0) {
                i8 = drawable.getIntrinsicHeight();
            }
            this.mSampleSize = BitmapOptions.computeInSampleSize(i7, i8, BitmapSizeHolder.size());
            if (isRotated(drawable)) {
                i2 = i8;
            } else {
                i2 = i7;
            }
            int i10 = i2 / this.mSampleSize;
            if (!isRotated(drawable)) {
                i7 = i8;
            }
            int i11 = i7 / this.mSampleSize;
            float scale = getScale(i10, i11);
            if (isViewScaled()) {
                f = this.mCurrentScale * ((float) this.mSampleSize);
            } else {
                f = scale;
            }
            setLayoutParams(i10, i11, f);
            applyCenterCrop(i10, i11, scale);
            setImageMatrix();
        }
    }

    public void applyCenterCrop(int i2, int i7, float f) {
    }
}
