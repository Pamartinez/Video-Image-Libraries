package com.samsung.android.sum.core.buffer;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Supplier {
    public final /* synthetic */ BufferExtension d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Map f4052h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Class f4053i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Class f4054j;

    public /* synthetic */ n(BufferExtension bufferExtension, ArrayList arrayList, ArrayList arrayList2, String str, Map map, Class cls, Class cls2) {
        this.d = bufferExtension;
        this.e = arrayList;
        this.f = arrayList2;
        this.g = str;
        this.f4052h = map;
        this.f4053i = cls;
        this.f4054j = cls2;
    }

    public final Object get() {
        return this.d.lambda$findAvailableBinaryKey$50(this.e, this.f, this.g, this.f4052h, this.f4053i, this.f4054j);
    }
}
