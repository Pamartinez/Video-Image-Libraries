package com.samsung.scsp.framework.core.identity;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4218a;
    public final /* synthetic */ PushInfo b;

    public /* synthetic */ i(PushInfo pushInfo, int i2) {
        this.f4218a = i2;
        this.b = pushInfo;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4218a;
        PushInfo pushInfo = this.b;
        PushInfo pushInfo2 = (PushInfo) obj;
        switch (i2) {
            case 0:
                return pushInfo2.getType().equals(pushInfo.getType());
            default:
                return pushInfo2.getType().equals(pushInfo.getType());
        }
    }
}
