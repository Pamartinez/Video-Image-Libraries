package com.samsung.android.sum.core.filter.factory;

import android.util.Pair;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4090a;
    public final /* synthetic */ Object b;

    public /* synthetic */ m(int i2, Object obj) {
        this.f4090a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4090a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((String) obj).equals((String) obj2);
            case 1:
                return ((Pattern) ((Pair) obj2).second).matcher((String) obj).find();
            default:
                return ((List) obj2).stream().anyMatch(new m(0, (String) obj));
        }
    }
}
