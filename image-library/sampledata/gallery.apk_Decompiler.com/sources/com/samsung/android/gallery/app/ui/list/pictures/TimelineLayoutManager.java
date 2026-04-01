package com.samsung.android.gallery.app.ui.list.pictures;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.SpannableGridLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelineLayoutManager extends SpannableGridLayoutManager {
    public TimelineLayoutManager(Context context, int i2) {
        super(context, i2);
    }

    public void updateViewSize(View view, int i2, int i7) {
        if (i2 != 0 || !isSpannable()) {
            super.updateViewSize(view, i2, i7);
            return;
        }
        SpannableGridLayoutManager.LayoutParams layoutParams = (SpannableGridLayoutManager.LayoutParams) view.getLayoutParams();
        int hintWidthSpace = getHintWidthSpace(i7) / i7;
        int i8 = layoutParams.mRowSpan * hintWidthSpace;
        int i10 = layoutParams.mColumnSpan * hintWidthSpace;
        if (i10 != layoutParams.width || i8 != layoutParams.height) {
            layoutParams.width = i10;
            layoutParams.height = i8;
            view.setLayoutParams(layoutParams);
        }
    }
}
