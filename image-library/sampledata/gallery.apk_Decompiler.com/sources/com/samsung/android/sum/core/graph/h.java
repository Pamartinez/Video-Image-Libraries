package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterRetriever;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements MediaFilterRetriever.PredicateHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4113a;
    public final /* synthetic */ GraphNodeBase b;

    public /* synthetic */ h(GraphNodeBase graphNodeBase, int i2) {
        this.f4113a = i2;
        this.b = graphNodeBase;
    }

    public final void onPredicate(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        int i2 = this.f4113a;
        GraphNodeBase graphNodeBase = this.b;
        switch (i2) {
            case 0:
                graphNodeBase.lambda$new$1(mediaFilter, mediaFilter2);
                return;
            case 1:
                graphNodeBase.lambda$applyGraphOption$10(mediaFilter, mediaFilter2);
                return;
            default:
                graphNodeBase.lambda$applyGraphOption$13(mediaFilter, mediaFilter2);
                return;
        }
    }
}
