package com.samsung.android.gallery.widget.gesture;

import android.view.MotionEvent;
import com.samsung.android.gallery.widget.gesture.RotationGestureDetector;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ MotionEvent d;

    public /* synthetic */ b(MotionEvent motionEvent) {
        this.d = motionEvent;
    }

    public final void accept(Object obj) {
        ((RotationGestureDetector.TouchInfo) obj).setTouchInfo(this.d);
    }
}
