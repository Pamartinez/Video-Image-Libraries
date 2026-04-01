package com.samsung.android.sum.core.buffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4056a;
    public final /* synthetic */ String[] b;

    public /* synthetic */ s(String[] strArr, int i2) {
        this.f4056a = i2;
        this.b = strArr;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4056a;
        String[] strArr = this.b;
        HashMap hashMap = (HashMap) obj;
        switch (i2) {
            case 0:
                return Boolean.valueOf(Arrays.stream(strArr).allMatch(new v(0, hashMap)));
            default:
                return Boolean.valueOf(Arrays.stream(strArr).anyMatch(new v(1, hashMap)));
        }
    }
}
