package com.samsung.android.sum.core.message;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4127a;
    public final /* synthetic */ Object b;

    public /* synthetic */ e(int i2, Object obj) {
        this.f4127a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4127a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((String) obj).equals((String) obj2);
            default:
                return ((MessageChannelRouter) obj2).lambda$addMessageSubscriber$1((Message) obj);
        }
    }
}
