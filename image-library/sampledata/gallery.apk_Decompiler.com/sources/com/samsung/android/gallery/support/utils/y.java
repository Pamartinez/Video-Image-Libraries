package com.samsung.android.gallery.support.utils;

import java.util.HashMap;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ HashMap f3191a;

    public /* synthetic */ y(HashMap hashMap) {
        this.f3191a = hashMap;
    }

    public final boolean test(int i2) {
        return this.f3191a.containsKey(Integer.valueOf(i2));
    }
}
