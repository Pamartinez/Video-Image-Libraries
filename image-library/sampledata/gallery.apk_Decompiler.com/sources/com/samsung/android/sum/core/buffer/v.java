package com.samsung.android.sum.core.buffer;

import java.util.HashMap;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4058a;
    public final /* synthetic */ HashMap b;

    public /* synthetic */ v(int i2, HashMap hashMap) {
        this.f4058a = i2;
        this.b = hashMap;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4058a;
        HashMap hashMap = this.b;
        String str = (String) obj;
        switch (i2) {
            case 0:
                return MediaBufferBase.lambda$containsAllExtra$6(hashMap, str);
            default:
                return MediaBufferBase.lambda$containsAnyExtra$4(hashMap, str);
        }
    }
}
