package com.samsung.android.gallery.app.ui.container.menu;

import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabMenu.BottomMenuViewHolder e;

    public /* synthetic */ f(BottomTabMenu.BottomMenuViewHolder bottomMenuViewHolder, int i2) {
        this.d = i2;
        this.e = bottomMenuViewHolder;
    }

    public final void run() {
        int i2 = this.d;
        BottomTabMenu.BottomMenuViewHolder bottomMenuViewHolder = this.e;
        switch (i2) {
            case 0:
                BottomTabMenu.BottomMenuListAdapter.lambda$updateNewBadge$4(bottomMenuViewHolder);
                return;
            default:
                bottomMenuViewHolder.updateNewBadge(R.string.new_content_available);
                return;
        }
    }
}
