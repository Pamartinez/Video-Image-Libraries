package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterRetriever;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements MediaFilterRetriever.Predictor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4112a;

    public /* synthetic */ g(int i2) {
        this.f4112a = i2;
    }

    public final boolean predicate(MediaFilter mediaFilter) {
        switch (this.f4112a) {
            case 0:
                return GraphNodeBase.lambda$new$0(mediaFilter);
            default:
                return GraphNodeBase.lambda$applyGraphOption$12(mediaFilter);
        }
    }
}
