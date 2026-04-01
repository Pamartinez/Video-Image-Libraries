package com.google.android.material.datepicker;

import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearSmoothScroller;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends LinearSmoothScroller {
    public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }
}
