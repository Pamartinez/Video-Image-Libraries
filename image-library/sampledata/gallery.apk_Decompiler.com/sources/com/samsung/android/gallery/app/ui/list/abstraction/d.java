package com.samsung.android.gallery.app.ui.list.abstraction;

import android.content.res.Configuration;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbsListFragment f2527a;
    public final /* synthetic */ Configuration b;

    public /* synthetic */ d(AbsListFragment absListFragment, Configuration configuration) {
        this.f2527a = absListFragment;
        this.b = configuration;
    }

    public final boolean getAsBoolean() {
        return this.f2527a.lambda$updateAppbarScroll$2(this.b);
    }
}
