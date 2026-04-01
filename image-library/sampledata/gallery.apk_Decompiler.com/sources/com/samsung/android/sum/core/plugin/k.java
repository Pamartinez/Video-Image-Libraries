package com.samsung.android.sum.core.plugin;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4139a;

    public /* synthetic */ k(int i2) {
        this.f4139a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4139a) {
            case 0:
                return ((String) obj).startsWith("com.samsung.android");
            case 1:
                return PluginStore.lambda$of$5((PluginStore) obj);
            case 2:
                return PluginStore.lambda$getPluginName$0((Type) obj);
            default:
                return Objects.nonNull((Optional) obj);
        }
    }
}
