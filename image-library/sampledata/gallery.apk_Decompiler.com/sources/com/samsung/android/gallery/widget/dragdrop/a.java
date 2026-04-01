package com.samsung.android.gallery.widget.dragdrop;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ SplitDnDAnimation d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(SplitDnDAnimation splitDnDAnimation, int i2, int i7) {
        this.d = splitDnDAnimation;
        this.e = i2;
        this.f = i7;
    }

    public final void accept(Object obj) {
        this.d.lambda$startMoveAnim$4(this.e, this.f, (DragViewSet) obj);
    }
}
