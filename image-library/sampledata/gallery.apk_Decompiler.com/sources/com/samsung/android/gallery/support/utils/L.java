package com.samsung.android.gallery.support.utils;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class L implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3169a;
    public final /* synthetic */ String b;

    public /* synthetic */ L(String str, int i2) {
        this.f3169a = i2;
        this.b = str;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3169a;
        String str = this.b;
        switch (i2) {
            case 0:
                return ((String) obj).startsWith(str);
            default:
                return str.equals(((PreferenceName) obj).mKey);
        }
    }
}
