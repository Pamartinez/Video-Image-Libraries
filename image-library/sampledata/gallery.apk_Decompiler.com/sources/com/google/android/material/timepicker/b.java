package com.google.android.material.timepicker;

import android.view.ViewTreeObserver;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ ClockFaceView d;

    public b(ClockFaceView clockFaceView) {
        this.d = clockFaceView;
    }

    public final boolean onPreDraw() {
        ClockFaceView clockFaceView = this.d;
        if (!clockFaceView.isShown()) {
            return true;
        }
        clockFaceView.getViewTreeObserver().removeOnPreDrawListener(this);
        int height = ((clockFaceView.getHeight() / 2) - clockFaceView.g.g) - clockFaceView.f1551o;
        if (height != clockFaceView.e) {
            clockFaceView.e = height;
            clockFaceView.a();
            ClockHandView clockHandView = clockFaceView.g;
            clockHandView.f1556o = clockFaceView.e;
            clockHandView.invalidate();
        }
        return true;
    }
}
