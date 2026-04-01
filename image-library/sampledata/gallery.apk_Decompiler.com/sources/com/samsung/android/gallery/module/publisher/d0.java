package com.samsung.android.gallery.module.publisher;

import java.util.List;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d0 implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3065a;
    public final /* synthetic */ Object b;

    public /* synthetic */ d0(int i2, Object obj) {
        this.f3065a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3065a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return Integer.valueOf(Integer.parseInt(((Object[]) obj)[((List) obj2).indexOf("__count")].toString()));
            default:
                return ((SearchDataPublisherV2) obj2).getSorter((Object[]) obj);
        }
    }
}
