package com.samsung.android.gallery.widget.photoview;

import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AliveZoomCompat f3209a;

    public /* synthetic */ a(AliveZoomCompat aliveZoomCompat) {
        this.f3209a = aliveZoomCompat;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3209a.onSceneDetected(((Boolean) obj).booleanValue(), ((Integer) obj2).intValue());
    }
}
