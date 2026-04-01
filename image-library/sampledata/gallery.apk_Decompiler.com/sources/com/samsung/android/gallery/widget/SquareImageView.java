package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SquareImageView extends AppCompatImageView {
    protected boolean mAspectBaseHeight = false;
    protected float mAspectRatio = 1.0f;

    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAttrs(context, attributeSet);
    }

    private void setAttrs(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SquareImage);
            this.mAspectRatio = obtainStyledAttributes.getFloat(R$styleable.SquareImage_aspect_ratio, 1.0f);
            this.mAspectBaseHeight = obtainStyledAttributes.getBoolean(R$styleable.SquareImage_aspect_base_height, false);
            obtainStyledAttributes.recycle();
        }
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        if (this.mAspectRatio >= 0.0f) {
            if (this.mAspectBaseHeight) {
                int measuredHeight = getMeasuredHeight();
                setMeasuredDimension(Math.round(((float) measuredHeight) * this.mAspectRatio), measuredHeight);
                return;
            }
            int measuredWidth = getMeasuredWidth();
            setMeasuredDimension(measuredWidth, Math.round(((float) measuredWidth) * this.mAspectRatio));
        }
    }
}
