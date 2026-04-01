package com.samsung.android.gallery.module.fileio.compat;

import java.util.List;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f3016a;
    public final /* synthetic */ long b;

    public /* synthetic */ e(List list, long j2) {
        this.f3016a = list;
        this.b = j2;
    }

    public final boolean test(int i2) {
        return FileOpApiGroup.lambda$buildSubJobs$4(this.f3016a, this.b, i2);
    }
}
