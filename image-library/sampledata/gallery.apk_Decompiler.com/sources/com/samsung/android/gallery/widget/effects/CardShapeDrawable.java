package com.samsung.android.gallery.widget.effects;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CardShapeDrawable extends Drawable {
    private final Paint mFillPaint;
    private final float mRadius;
    private int mStrokeColor;
    private final Paint mStrokePaint;
    private float mStrokeWidth;
    private boolean mUseGradientStroke;
    private final boolean mUseStroke;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public final int mColor;
        /* access modifiers changed from: private */
        public final float mRadius;
        /* access modifiers changed from: private */
        public int mStrokeColor;
        /* access modifiers changed from: private */
        public float mStrokeWidth;
        /* access modifiers changed from: private */
        public boolean mUseGradientStroke;
        /* access modifiers changed from: private */
        public boolean mUseStroke;

        public Builder(int i2, float f) {
            this.mColor = i2;
            this.mRadius = f;
        }

        public CardShapeDrawable build() {
            return new CardShapeDrawable(this, 0);
        }

        public Builder withGradientStroke(int i2, float f) {
            this.mStrokeColor = i2;
            this.mStrokeWidth = f;
            this.mUseStroke = true;
            this.mUseGradientStroke = true;
            return this;
        }
    }

    public /* synthetic */ CardShapeDrawable(Builder builder, int i2) {
        this(builder);
    }

    private Path getStrokePath(RectF rectF) {
        float centerY = rectF.centerY();
        Path path = new Path();
        path.moveTo(rectF.left, centerY);
        path.lineTo(rectF.left, rectF.top + this.mRadius);
        float f = rectF.left;
        float f5 = rectF.top;
        float f8 = this.mRadius;
        path.arcTo(new RectF(f, f5, (f8 * 2.0f) + f, (f8 * 2.0f) + f5), 180.0f, 90.0f, false);
        path.lineTo(rectF.right - this.mRadius, rectF.top);
        float f10 = rectF.right;
        float f11 = this.mRadius;
        float f12 = rectF.top;
        path.arcTo(new RectF(f10 - (f11 * 2.0f), f12, f10, (f11 * 2.0f) + f12), 270.0f, 90.0f, false);
        path.lineTo(rectF.right, centerY);
        return path;
    }

    public void draw(Canvas canvas) {
        RectF rectF = new RectF(getBounds());
        float f = this.mRadius;
        canvas.drawRoundRect(rectF, f, f, this.mFillPaint);
        if (this.mUseStroke) {
            RectF rectF2 = new RectF(rectF);
            float f5 = this.mStrokeWidth / 2.0f;
            rectF2.inset(f5, f5);
            if (this.mUseGradientStroke) {
                float centerX = rectF2.centerX();
                float f8 = rectF2.top;
                float centerY = rectF2.centerY();
                int i2 = this.mStrokeColor;
                this.mStrokePaint.setShader(new LinearGradient(centerX, f8, centerX, centerY, new int[]{i2, 16777215 & i2}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
            } else {
                this.mStrokePaint.setShader((Shader) null);
                this.mStrokePaint.setColor(this.mStrokeColor);
            }
            canvas.drawPath(getStrokePath(rectF2), this.mStrokePaint);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i2) {
        this.mFillPaint.setAlpha(i2);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mFillPaint.setColorFilter(colorFilter);
        this.mStrokePaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    private CardShapeDrawable(Builder builder) {
        Paint paint = new Paint(1);
        this.mFillPaint = paint;
        Paint paint2 = new Paint(1);
        this.mStrokePaint = paint2;
        this.mRadius = builder.mRadius;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(builder.mColor);
        boolean f = builder.mUseStroke;
        this.mUseStroke = f;
        if (f) {
            this.mStrokeWidth = builder.mStrokeWidth;
            this.mStrokeColor = builder.mStrokeColor;
            this.mUseGradientStroke = builder.mUseGradientStroke;
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(this.mStrokeWidth);
            paint2.setColor(this.mStrokeColor);
            paint2.setStrokeJoin(Paint.Join.ROUND);
            paint2.setStrokeCap(Paint.Cap.ROUND);
        }
    }
}
