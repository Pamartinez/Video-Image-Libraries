package com.samsung.android.gallery.support.utils;

import java.util.Set;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class J implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3165a;
    public final /* synthetic */ Set b;

    public /* synthetic */ J(int i2, Set set) {
        this.f3165a = i2;
        this.b = set;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3165a;
        Set set = this.b;
        switch (i2) {
            case 0:
                return set.contains(((PreferenceAnalytics) obj).key);
            case 1:
                return set.contains(((PreferenceCache) obj).key);
            case 2:
                return set.contains(((BooleanFeatures) obj).getKey());
            default:
                return set.contains((String) obj);
        }
    }
}
