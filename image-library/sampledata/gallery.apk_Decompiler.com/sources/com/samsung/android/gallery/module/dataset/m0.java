package com.samsung.android.gallery.module.dataset;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m0 implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2985a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2986c;

    public /* synthetic */ m0(int i2, Object obj, String str) {
        this.f2985a = i2;
        this.b = obj;
        this.f2986c = str;
    }

    public final Object apply(Object obj) {
        switch (this.f2985a) {
            case 0:
                return ((MediaDataSearchCreatureSelectForRelation) this.b).lambda$reopenChildMediaData$6(this.f2986c, (String) obj);
            case 1:
                return ((MediaDataSearchCreatureSelectForRelation) this.b).lambda$onCreate$1(this.f2986c, (String) obj);
            default:
                return ((MediaDataFactory) this.b).lambda$open$1(this.f2986c, (String) obj);
        }
    }
}
