package com.samsung.android.gallery.app.ui.list.abstraction;

import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ AbsListFragment d;

    public /* synthetic */ f(AbsListFragment absListFragment) {
        this.d = absListFragment;
    }

    public final void accept(Object obj) {
        this.d.lambda$initFloatingToolbarLayout$0((FloatingToolbarLayout) obj);
    }
}
