package com.samsung.android.gallery.widget.animations;

import android.view.DragEvent;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements View.OnDragListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SimpleDragShrinkHandler f3197a;

    public /* synthetic */ i(SimpleDragShrinkHandler simpleDragShrinkHandler) {
        this.f3197a = simpleDragShrinkHandler;
    }

    public final boolean onDrag(View view, DragEvent dragEvent) {
        return this.f3197a.onDrag(view, dragEvent);
    }
}
