package com.samsung.android.sum.core.plugin;

import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4135a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f4135a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4135a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((Enum) obj).name().equals((String) obj2);
            case 1:
                return ((Enum) obj).name().equals((String) obj2);
            default:
                return StaplePluginStore.lambda$remove$2((PluginFixture) obj2, (Map.Entry) obj);
        }
    }
}
