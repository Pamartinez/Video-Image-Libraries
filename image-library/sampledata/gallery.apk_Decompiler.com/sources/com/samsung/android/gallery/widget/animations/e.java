package com.samsung.android.gallery.widget.animations;

import com.samsung.android.gallery.widget.gesture.OnRotationGestureListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements OnRotationGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ NewPinchShrinkHandler f3195a;

    public /* synthetic */ e(NewPinchShrinkHandler newPinchShrinkHandler) {
        this.f3195a = newPinchShrinkHandler;
    }

    public final void a(float f) {
        this.f3195a.onRotationGesture(f);
    }
}
