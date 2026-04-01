package com.samsung.android.gallery.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CenteredImageSpan extends ImageSpan {
    private WeakReference<Drawable> mDrawableRef;

    public CenteredImageSpan(Drawable drawable) {
        super(drawable);
    }

    private Drawable getCachedDrawable() {
        Drawable drawable;
        WeakReference<Drawable> weakReference = this.mDrawableRef;
        if (weakReference != null) {
            drawable = weakReference.get();
        } else {
            drawable = null;
        }
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = getDrawable();
        this.mDrawableRef = new WeakReference<>(drawable2);
        return drawable2;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i7, float f, int i8, int i10, int i11, Paint paint) {
        Drawable cachedDrawable = getCachedDrawable();
        if (cachedDrawable != null) {
            canvas.save();
            canvas.translate(f, (float) ((((paint.getFontMetricsInt().descent - paint.getFontMetricsInt().ascent) - (cachedDrawable.getBounds().bottom - cachedDrawable.getBounds().top)) / 2) + i8));
            cachedDrawable.draw(canvas);
            canvas.restore();
        }
    }

    public int getSize(Paint paint, CharSequence charSequence, int i2, int i7, Paint.FontMetricsInt fontMetricsInt) {
        Drawable cachedDrawable = getCachedDrawable();
        if (cachedDrawable == null) {
            return 0;
        }
        Rect bounds = cachedDrawable.getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            int i8 = fontMetricsInt2.bottom - fontMetricsInt2.top;
            int i10 = (bounds.bottom - bounds.top) / 2;
            int i11 = i8 / 4;
            int i12 = i10 - i11;
            int i13 = -(i10 + i11);
            fontMetricsInt.ascent = i13;
            fontMetricsInt.top = i13;
            fontMetricsInt.bottom = i12;
            fontMetricsInt.descent = i12;
        }
        return bounds.right;
    }
}
