package com.samsung.android.gallery.app.ui.container.menu;

import android.view.View;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ BottomTabMenu.BottomMenuRoundedViewHolder d;
    public final /* synthetic */ View e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    public /* synthetic */ g(BottomTabMenu.BottomMenuRoundedViewHolder bottomMenuRoundedViewHolder, View view, int i2, int i7) {
        this.d = bottomMenuRoundedViewHolder;
        this.e = view;
        this.f = i2;
        this.g = i7;
    }

    public final void run() {
        this.d.lambda$onLayoutChange$0(this.e, this.f, this.g);
    }
}
