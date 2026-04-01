package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RoundedRelativeLayout extends RelativeLayout {
    public RoundedRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private int getRadius(Context context, AttributeSet attributeSet) {
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.rounded_corner_radius);
        if (attributeSet == null) {
            return dimensionPixelOffset;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RoundedFrameLayout);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.RoundedRelativeLayout_cornerRadius, dimensionPixelOffset);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public RoundedRelativeLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        final float radius = (float) getRadius(context, attributeSet);
        setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
            }
        });
        setClipToOutline(true);
    }
}
