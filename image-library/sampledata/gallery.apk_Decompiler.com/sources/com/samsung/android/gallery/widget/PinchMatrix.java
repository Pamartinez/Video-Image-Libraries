package com.samsung.android.gallery.widget;

import android.graphics.Matrix;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchMatrix extends Matrix {
    private boolean isReady;

    public boolean isReady() {
        return this.isReady;
    }

    public PinchMatrix setReady(boolean z) {
        this.isReady = z;
        return this;
    }
}
