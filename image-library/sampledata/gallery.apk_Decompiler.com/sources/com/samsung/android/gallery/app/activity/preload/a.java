package com.samsung.android.gallery.app.activity.preload;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f2498a;

    public /* synthetic */ a(String str) {
        this.f2498a = str;
    }

    public final boolean test(Object obj) {
        return ((DataRequesterAction) obj).getLocationKey().equals(this.f2498a);
    }
}
