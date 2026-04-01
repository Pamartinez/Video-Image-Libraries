package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LayerEffectViewContainer extends ConstraintLayout {
    private Bitmap mMaskBitmap;
    private float mMaskX;
    private float mMaskY;
    private Paint mPorterDuffPaint;

    public LayerEffectViewContainer(Context context) {
        super(context);
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Bitmap bitmap = this.mMaskBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(this.mMaskBitmap, this.mMaskX, this.mMaskY, this.mPorterDuffPaint);
        }
    }

    public void init() {
        setWillNotDraw(false);
        Paint paint = new Paint(1);
        this.mPorterDuffPaint = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        setLayerType(1, (Paint) null);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }

    public void release() {
        this.mPorterDuffPaint = null;
        Bitmap bitmap = this.mMaskBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mMaskBitmap = null;
        }
    }

    public void setMaskBitmap(Bitmap bitmap, float f, float f5) {
        this.mMaskBitmap = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
        bitmap.recycle();
        this.mMaskX = f;
        this.mMaskY = f5;
        invalidate();
    }

    public void setMaskLayer(boolean z) {
        if (z) {
            this.mPorterDuffPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            this.mPorterDuffPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        }
    }

    public LayerEffectViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LayerEffectViewContainer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public LayerEffectViewContainer(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
    }
}
