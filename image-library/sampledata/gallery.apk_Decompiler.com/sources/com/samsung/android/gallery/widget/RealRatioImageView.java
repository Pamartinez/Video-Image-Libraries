package com.samsung.android.gallery.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RealRatioImageView extends AppCompatImageView {
    private float mAspectRatio = 1.0f;

    public RealRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mAspectRatio = ((float) bitmap.getHeight()) / ((float) bitmap.getWidth());
        super.setImageBitmap(bitmap);
    }
}
