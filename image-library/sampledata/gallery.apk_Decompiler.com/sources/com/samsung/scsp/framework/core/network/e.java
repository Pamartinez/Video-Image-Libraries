package com.samsung.scsp.framework.core.network;

import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4221a;
    public final /* synthetic */ Integer b;

    public /* synthetic */ e(int i2, Integer num) {
        this.f4221a = i2;
        this.b = num;
    }

    public final boolean test(int i2) {
        int i7 = this.f4221a;
        Integer num = this.b;
        switch (i7) {
            case 0:
                return ResponseUtil.lambda$static$0(num, i2);
            default:
                return ResponseUtil.lambda$static$2(num, i2);
        }
    }
}
