package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.graphics.RectF;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScaleCoordinateInfo {
    public float currentScale;
    public boolean fixedFocusX;
    public boolean fixedFocusY;
    public boolean onceScaledUp;
    public final RectF parentRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
    public boolean setDefaultPosition;
    public float startFocusX;
    public float startFocusY;
    public float startH;
    public float startScale = 1.0f;
    public float startW;
    public float startX;
    public float startY;

    public float calculateX(float f, float f5) {
        float f8 = this.startX;
        float f10 = this.startFocusX;
        float f11 = this.startScale;
        return (f - f10) + (f8 - ((f5 - f11) * (((f10 - f8) - (this.startW / 2.0f)) / f11)));
    }

    public float calculateY(float f, float f5) {
        float f8 = this.startY;
        float f10 = this.startFocusY;
        float f11 = this.startScale;
        return (f - f10) + (f8 - ((f5 - f11) * (((f10 - f8) - (this.startH / 2.0f)) / f11)));
    }
}
