package com.samsung.android.gallery.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShapeImageView extends AppCompatImageView {
    private final Paint mPaint = createPaint();
    private Drawable mShapeDrawable;

    public ShapeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private Paint createPaint() {
        Paint paint = new Paint(1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        return paint;
    }

    public void onDraw(Canvas canvas) {
        Bitmap bitmap;
        if (this.mShapeDrawable == null || !(getDrawable() instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) getDrawable()).getBitmap()) == null) {
            super.onDraw(canvas);
            return;
        }
        canvas.save();
        this.mShapeDrawable.setBounds(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()));
        this.mShapeDrawable.draw(canvas);
        canvas.drawBitmap(bitmap, getImageMatrix(), this.mPaint);
        canvas.restore();
    }

    public void setShape(Drawable drawable) {
        this.mShapeDrawable = drawable;
        if (drawable != null && getLayerType() != 1) {
            setLayerType(1, (Paint) null);
        }
    }
}
