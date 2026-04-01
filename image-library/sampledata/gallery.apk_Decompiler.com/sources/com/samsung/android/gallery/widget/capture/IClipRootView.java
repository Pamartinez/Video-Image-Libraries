package com.samsung.android.gallery.widget.capture;

import android.graphics.RectF;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClipRootView {
    int getBottomMarginFromSupplier();

    RectF getDisplayMinRect();

    RectF getDisplayRect();

    void getLocation(int[] iArr);

    int getTopMarginFromSupplier();

    void invalidate();

    boolean isAlreadyUp();
}
