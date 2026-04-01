package com.samsung.android.gallery.app.ui.list.abstraction;

import S1.e;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements e {
    public final /* synthetic */ AbsListFragment d;

    public /* synthetic */ c(AbsListFragment absListFragment) {
        this.d = absListFragment;
    }

    public final void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
        this.d.onAppbarOffsetChanged(appBarLayout, i2);
    }
}
