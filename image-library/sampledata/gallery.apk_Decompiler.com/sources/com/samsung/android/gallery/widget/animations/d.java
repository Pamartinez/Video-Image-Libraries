package com.samsung.android.gallery.widget.animations;

import android.view.MotionEvent;
import com.samsung.android.gallery.widget.gesture.OnShrinkGestureListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements OnShrinkGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ NewPinchShrinkHandler f3194a;

    public /* synthetic */ d(NewPinchShrinkHandler newPinchShrinkHandler) {
        this.f3194a = newPinchShrinkHandler;
    }

    public final boolean onShrinkGesture(MotionEvent motionEvent) {
        return this.f3194a.onShrinkGesture(motionEvent);
    }
}
