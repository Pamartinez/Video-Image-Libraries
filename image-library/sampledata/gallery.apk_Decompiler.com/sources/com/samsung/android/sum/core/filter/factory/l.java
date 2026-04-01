package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.filter.AsyncFilter;
import java.util.List;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f4089a;

    public /* synthetic */ l(List list) {
        this.f4089a = list;
    }

    public final void accept(int i2) {
        ((AsyncFilter) this.f4089a.get(i2)).setId(i2);
    }
}
