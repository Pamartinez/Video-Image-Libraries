package com.samsung.android.gallery.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.flexbox.FlexboxLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlexBoxLazyDivider extends FlexboxLayout {
    public FlexBoxLazyDivider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i2, int i7) {
        Drawable dividerDrawableHorizontal = getDividerDrawableHorizontal();
        Drawable dividerDrawableVertical = getDividerDrawableVertical();
        int showDividerHorizontal = getShowDividerHorizontal();
        int showDividerVertical = getShowDividerVertical();
        setDividerDrawable((Drawable) null);
        setShowDivider(0);
        super.onMeasure(i2, i7);
        setDividerDrawableHorizontal(dividerDrawableHorizontal);
        setShowDividerHorizontal(showDividerHorizontal);
        setDividerDrawableVertical(dividerDrawableVertical);
        setShowDividerVertical(showDividerVertical);
    }
}
