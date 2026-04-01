package com.samsung.android.sum.core.buffer;

import java.util.Map;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.sum.core.buffer.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0926d implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4045a;
    public final /* synthetic */ Map.Entry b;

    public /* synthetic */ C0926d(int i2, Map.Entry entry) {
        this.f4045a = i2;
        this.b = entry;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4045a;
        Map.Entry entry = this.b;
        String str = (String) obj;
        switch (i2) {
            case 0:
                return str.equals(entry.getKey());
            case 1:
                return str.equals(entry.getKey());
            case 2:
                return str.equals(entry.getKey());
            case 3:
                return str.equals(entry.getKey());
            default:
                return str.equals(entry.getKey());
        }
    }
}
