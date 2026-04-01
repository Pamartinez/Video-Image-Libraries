package com.samsung.android.sum.core.message;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4128a;
    public final /* synthetic */ int b;

    public /* synthetic */ f(int i2, int i7) {
        this.f4128a = i7;
        this.b = i2;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4128a;
        int i7 = this.b;
        Integer num = (Integer) obj;
        switch (i2) {
            case 0:
                return Message.lambda$isValidCode$2(i7, num);
            default:
                return Message.lambda$isValidCode$3(i7, num);
        }
    }
}
