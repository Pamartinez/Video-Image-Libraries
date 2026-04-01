package com.samsung.android.sum.core.message;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4126a;
    public final /* synthetic */ Message b;

    public /* synthetic */ d(Message message, int i2) {
        this.f4126a = i2;
        this.b = message;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4126a;
        Message message = this.b;
        String str = (String) obj;
        switch (i2) {
            case 0:
                return message.lambda$containsAll$4(str);
            default:
                return message.lambda$containsAny$5(str);
        }
    }
}
