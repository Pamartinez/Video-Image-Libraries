package com.samsung.android.gallery.module.aiedit;

import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f2918a;

    public /* synthetic */ a(String str) {
        this.f2918a = str;
    }

    public final boolean test(Object obj) {
        return this.f2918a.contains((CharSequence) ((Map.Entry) obj).getKey());
    }
}
