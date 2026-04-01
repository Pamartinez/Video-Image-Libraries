package com.samsung.android.gallery.app.ui.container.menu;

import android.view.View;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ BottomTabMenu.BottomMenuListAdapter d;
    public final /* synthetic */ BottomMenuItem e;

    public /* synthetic */ d(BottomTabMenu.BottomMenuListAdapter bottomMenuListAdapter, BottomMenuItem bottomMenuItem) {
        this.d = bottomMenuListAdapter;
        this.e = bottomMenuItem;
    }

    public final void onClick(View view) {
        this.d.lambda$onBindViewHolder$2(this.e, view);
    }
}
