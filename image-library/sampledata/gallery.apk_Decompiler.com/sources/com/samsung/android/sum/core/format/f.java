package com.samsung.android.sum.core.format;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4102a;
    public final /* synthetic */ StapleMutableMediaFormat b;

    public /* synthetic */ f(StapleMutableMediaFormat stapleMutableMediaFormat, int i2) {
        this.f4102a = i2;
        this.b = stapleMutableMediaFormat;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4102a;
        StapleMutableMediaFormat stapleMutableMediaFormat = this.b;
        String str = (String) obj;
        switch (i2) {
            case 0:
                return stapleMutableMediaFormat.lambda$containsAllOf$7(str);
            default:
                return stapleMutableMediaFormat.lambda$containsAnyOf$6(str);
        }
    }
}
