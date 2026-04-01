package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.filter.factory.MediaFilterFactory;
import java.util.Comparator;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaFilterFactory.Builder f4087a;

    public /* synthetic */ j(MediaFilterFactory.Builder builder) {
        this.f4087a = builder;
    }

    public final void accept(Object obj, Object obj2) {
        this.f4087a.lambda$build$1((Class) obj, (Comparator) obj2);
    }
}
