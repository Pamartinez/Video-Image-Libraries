package com.samsung.android.gallery.widget.crop;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.crop.api.CropImageView;
import com.samsung.android.gallery.widget.crop.api.CropView;
import com.samsung.android.gallery.widget.crop.config.CropViewConfig;
import java.util.Locale;
import o6.p;
import rb.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropViewImpl extends View implements CropView {
    private int mBgDimColor;
    private Bitmap mBitmap;
    private CropArea mCropArea;
    private final CropAreaPainter mCropAreaPainter = new CropAreaPainter();
    private CropImageView mImageView;
    private float mPrevX;
    private float mPrevY;
    private String mScreenId;

    public CropViewImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayerType(2, (Paint) null);
    }

    private boolean isFirstCall() {
        if (this.mBitmap == null) {
            return true;
        }
        return false;
    }

    private boolean isViewReady() {
        if (this.mBitmap == null || getWidth() <= 0 || getHeight() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onImageMatrixChanged(Matrix matrix) {
        if (isViewReady()) {
            this.mCropArea.onMatrixChanged(this.mBitmap, matrix);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r6 != 3) goto L_0x007a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            com.samsung.android.gallery.widget.crop.api.CropImageView r6 = r5.mImageView
            r0 = 0
            if (r6 != 0) goto L_0x0006
            return r0
        L_0x0006:
            int r6 = r7.getPointerCount()
            r1 = 1
            if (r6 > r1) goto L_0x007b
            int r6 = r7.getAction()
            if (r6 != 0) goto L_0x0030
            com.samsung.android.gallery.widget.crop.CropArea r2 = r5.mCropArea
            float r3 = r7.getX()
            float r4 = r7.getY()
            boolean r2 = r2.onActionDown(r3, r4)
            if (r2 == 0) goto L_0x0030
            float r6 = r7.getX()
            r5.mPrevX = r6
            float r6 = r7.getY()
            r5.mPrevY = r6
            return r1
        L_0x0030:
            com.samsung.android.gallery.widget.crop.CropArea r2 = r5.mCropArea
            boolean r2 = r2.isTouching()
            if (r2 == 0) goto L_0x0080
            if (r6 == r1) goto L_0x0061
            r2 = 2
            if (r6 == r2) goto L_0x0041
            r7 = 3
            if (r6 == r7) goto L_0x0061
            goto L_0x007a
        L_0x0041:
            com.samsung.android.gallery.widget.crop.CropArea r6 = r5.mCropArea
            float r0 = r7.getX()
            float r2 = r5.mPrevX
            float r0 = r0 - r2
            float r2 = r7.getY()
            float r3 = r5.mPrevY
            float r2 = r2 - r3
            r6.onActionMove(r0, r2)
            float r6 = r7.getX()
            r5.mPrevX = r6
            float r6 = r7.getY()
            r5.mPrevY = r6
            goto L_0x007a
        L_0x0061:
            com.samsung.android.gallery.widget.crop.CropArea r6 = r5.mCropArea
            r6.onTouchOut()
            com.samsung.android.gallery.widget.crop.api.CropImageView r6 = r5.mImageView
            r6.setIsZooming(r0)
            com.samsung.android.gallery.module.logger.AnalyticsLogger r6 = com.samsung.android.gallery.module.logger.AnalyticsLogger.getInstance()
            java.lang.String r5 = r5.mScreenId
            com.samsung.android.gallery.support.analytics.AnalyticsEventId r7 = com.samsung.android.gallery.support.analytics.AnalyticsEventId.EVENT_CROP_VIEW_CONTROL
            java.lang.String r7 = r7.toString()
            r6.postLog(r5, r7)
        L_0x007a:
            return r1
        L_0x007b:
            com.samsung.android.gallery.widget.crop.CropArea r6 = r5.mCropArea
            r6.onTouchOut()
        L_0x0080:
            com.samsung.android.gallery.widget.crop.api.CropImageView r5 = r5.mImageView
            boolean r5 = r5.onTouch(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.crop.CropViewImpl.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    private void setBitmap(Bitmap bitmap) {
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 == null || !bitmap2.equals(bitmap)) {
            this.mBitmap = bitmap;
        }
    }

    public RectF getRectToBeSaved() {
        return this.mCropArea.getResult(this.mBitmap, this.mImageView.getScaleRatio());
    }

    public boolean isCropAreaChanged() {
        if (this.mCropArea.isDirty()) {
            return true;
        }
        CropImageView cropImageView = this.mImageView;
        if (cropImageView == null || !cropImageView.isZoomed()) {
            return false;
        }
        return true;
    }

    public void onBitmapPrepared(Bitmap bitmap) {
        if (isFirstCall()) {
            startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.crop_view_fade_in));
        }
        setBitmap(bitmap);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mCropArea.onConfigurationChanged(this.mImageView.isZoomed());
    }

    public void onDraw(Canvas canvas) {
        if (this.mCropArea.isReady()) {
            super.onDraw(canvas);
            canvas.drawColor(this.mBgDimColor);
            this.mCropAreaPainter.draw(canvas);
        }
    }

    public void onViewCreated(CropViewConfig cropViewConfig, CropImageView cropImageView, String str) {
        this.mCropArea = new CropArea(this, cropViewConfig);
        this.mCropAreaPainter.onViewCreated(getContext(), this.mCropArea, cropViewConfig.mShapeType);
        this.mImageView = cropImageView;
        cropImageView.setOnCropImageMatrixChangedListener(new p(10, this));
        this.mBgDimColor = getContext().getColor(R$color.crop_view_background_color);
        this.mScreenId = str;
        setOnTouchListener(new a(0, this));
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "X,Y POS : (%.1f, %.1f)", new Object[]{Float.valueOf(this.mPrevX), Float.valueOf(this.mPrevY)});
    }
}
