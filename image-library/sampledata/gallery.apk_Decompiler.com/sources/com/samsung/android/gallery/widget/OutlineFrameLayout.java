package com.samsung.android.gallery.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.samsung.android.sum.core.descriptor.b;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OutlineFrameLayout extends FrameLayout {
    private Path mOutLinePath;
    private int[] mOutlineRadius;
    private final Paint mPaint;
    private final float[] mRadii;

    public OutlineFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void initPath(Canvas canvas) {
        if (this.mOutlineRadius != null && this.mOutLinePath == null) {
            this.mOutLinePath = new Path();
            for (int i2 = 0; i2 < 4; i2++) {
                float[] fArr = this.mRadii;
                int i7 = i2 * 2;
                int i8 = this.mOutlineRadius[i2];
                fArr[i7] = (float) i8;
                fArr[i7 + 1] = (float) i8;
            }
            this.mOutLinePath.addRoundRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), this.mRadii, Path.Direction.CW);
        }
    }

    public void onDraw(Canvas canvas) {
        initPath(canvas);
        Path path = this.mOutLinePath;
        if (path != null) {
            canvas.clipPath(path);
            canvas.drawPath(this.mOutLinePath, this.mPaint);
        }
        super.onDraw(canvas);
    }

    public void setOutlineRadius(int[] iArr, Integer num) {
        this.mOutlineRadius = iArr;
        this.mOutLinePath = null;
        Optional ofNullable = Optional.ofNullable(num);
        Paint paint = this.mPaint;
        Objects.requireNonNull(paint);
        ofNullable.ifPresent(new b(14, paint));
        invalidate();
    }

    public OutlineFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRadii = new float[8];
        this.mPaint = new Paint(1);
    }
}
