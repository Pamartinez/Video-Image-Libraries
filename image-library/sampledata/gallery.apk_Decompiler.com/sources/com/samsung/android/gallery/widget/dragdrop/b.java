package com.samsung.android.gallery.widget.dragdrop;

import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ SplitDnDAnimation d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ ArrayList g;

    public /* synthetic */ b(SplitDnDAnimation splitDnDAnimation, float f5, float f8, ArrayList arrayList) {
        this.d = splitDnDAnimation;
        this.e = f5;
        this.f = f8;
        this.g = arrayList;
    }

    public final void accept(Object obj) {
        this.d.lambda$createGatherAnimators$3(this.e, this.f, this.g, (DragViewSet) obj);
    }
}
