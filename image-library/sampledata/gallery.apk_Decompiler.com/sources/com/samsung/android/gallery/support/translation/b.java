package com.samsung.android.gallery.support.translation;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Bundle f3157a;
    public final /* synthetic */ ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ConcurrentHashMap f3158c;

    public /* synthetic */ b(Bundle bundle, ArrayList arrayList, ConcurrentHashMap concurrentHashMap) {
        this.f3157a = bundle;
        this.b = arrayList;
        this.f3158c = concurrentHashMap;
    }

    public final void accept(Object obj, Object obj2) {
        IntelSearchTranslation.lambda$translateFromApiCallMultiple$0(this.f3157a, this.b, this.f3158c, (String) obj, (Integer) obj2);
    }
}
