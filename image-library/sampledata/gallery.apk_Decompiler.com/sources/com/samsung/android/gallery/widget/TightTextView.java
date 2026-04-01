package com.samsung.android.gallery.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TightTextView extends TextView {
    public TightTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i2, int i7) {
        int lineCount;
        super.onMeasure(i2, i7);
        if (View.MeasureSpec.getMode(i2) != 1073741824 && (lineCount = getLayout().getLineCount()) > 1) {
            float f = 0.0f;
            for (int i8 = 0; i8 < lineCount; i8++) {
                f = Math.max(f, getLayout().getLineWidth(i8));
            }
            int paddingEnd = getPaddingEnd() + getPaddingStart() + ((int) Math.ceil((double) f));
            if (paddingEnd < getMeasuredWidth()) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(paddingEnd, Integer.MIN_VALUE), i7);
            }
        }
    }
}
