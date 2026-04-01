package com.samsung.android.sum.core.buffer;

import java.util.ArrayList;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BufferExtension f4049a;
    public final /* synthetic */ Class b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Class f4050c;
    public final /* synthetic */ String d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ ArrayList f;

    public /* synthetic */ l(BufferExtension bufferExtension, Class cls, Class cls2, String str, ArrayList arrayList, ArrayList arrayList2) {
        this.f4049a = bufferExtension;
        this.b = cls;
        this.f4050c = cls2;
        this.d = str;
        this.e = arrayList;
        this.f = arrayList2;
    }

    public final boolean test(Object obj) {
        return this.f4049a.lambda$findAvailableBinaryKey$45(this.b, this.f4050c, this.d, this.e, this.f, (String) obj);
    }
}
