package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.utils.SystemEnvironment;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class S implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3172a;

    public /* synthetic */ S(int i2) {
        this.f3172a = i2;
    }

    public final boolean test(Object obj) {
        return SystemEnvironment.lambda$notifyObservers$0(this.f3172a, (SystemEnvironment.Observer) obj);
    }
}
