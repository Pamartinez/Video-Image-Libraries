package com.samsung.android.sum.core.message;

import java.util.List;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4123a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f4123a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4123a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((ContentsInfo) obj2).lambda$toString$0((String) obj);
            case 1:
                return ((MessageChannelRouter) obj2).queryMessageChannel(((Integer) obj).intValue());
            default:
                return ((Response) obj2).lambda$getBuffer$1((List) obj);
        }
    }
}
