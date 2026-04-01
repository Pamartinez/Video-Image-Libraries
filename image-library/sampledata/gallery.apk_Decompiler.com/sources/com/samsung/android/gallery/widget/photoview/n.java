package com.samsung.android.gallery.widget.photoview;

import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.gallery.widget.photoview.PhotoViewPositionControl;
import com.samsung.android.gallery.widget.photoview.ScaleAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements PhotoViewPositionControl.OnScaleChangeListener, ScaleAnimation.ScaleAnimationListener {
    public final /* synthetic */ Object d;

    public /* synthetic */ n(Object obj) {
        this.d = obj;
    }

    public void onAnimationUpdate() {
        ((PhotoViewMotionControl.MotionControlGestureListener) this.d).lambda$onFling$0();
    }
}
