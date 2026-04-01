package com.samsung.android.sum.core.buffer;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.sum.core.buffer.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0924b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4044a;
    public final /* synthetic */ List b;

    public /* synthetic */ C0924b(int i2, List list) {
        this.f4044a = i2;
        this.b = list;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4044a;
        List list = this.b;
        Map.Entry entry = (Map.Entry) obj;
        switch (i2) {
            case 0:
                return list.stream().anyMatch(new C0926d(1, entry));
            case 1:
                return list.stream().anyMatch(new C0926d(3, entry));
            case 2:
                return list.stream().anyMatch(new C0926d(2, entry));
            case 3:
                return list.stream().anyMatch(new C0926d(4, entry));
            default:
                return list.stream().anyMatch(new C0926d(0, entry));
        }
    }
}
