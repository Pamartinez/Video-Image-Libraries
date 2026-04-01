package com.samsung.android.gallery.module.data;

import android.util.Pair;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2930a;
    public final /* synthetic */ Object b;

    public /* synthetic */ g(int i2, Object obj) {
        this.f2930a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2930a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((MediaCache) obj2).isEnabled(((Integer) ((Pair) obj).first).intValue());
            default:
                return ((List) obj2).contains((String) obj);
        }
    }
}
