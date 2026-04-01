package com.samsung.android.gallery.app.activity;

import android.view.MenuItem;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2492a;

    public /* synthetic */ b(List list) {
        this.f2492a = list;
    }

    public final boolean test(Object obj) {
        return this.f2492a.contains(Integer.valueOf(((MenuItem) obj).getItemId()));
    }
}
