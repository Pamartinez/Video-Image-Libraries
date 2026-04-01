package com.samsung.android.gallery.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastScrollerBgDrawable extends Drawable {
    private float mHeight = 0.0f;
    private final Paint mPaint;
    private float mWidth = 0.0f;

    public FastScrollerBgDrawable() {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(8);
        paint.setColor(-16777216);
    }

    public void draw(Canvas canvas) {
        this.mPaint.setStrokeWidth(this.mWidth);
        canvas.drawLine(0.0f, 0.0f, 0.0f, this.mHeight, this.mPaint);
    }

    public int getOpacity() {
        return -2;
    }

    public float getWidth() {
        return this.mWidth;
    }

    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
    }

    public void setArgb(int i2) {
        this.mPaint.setColor(i2);
    }

    public void setHeight(float f) {
        this.mHeight = f;
    }

    public void setWidth(float f) {
        this.mWidth = f;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
