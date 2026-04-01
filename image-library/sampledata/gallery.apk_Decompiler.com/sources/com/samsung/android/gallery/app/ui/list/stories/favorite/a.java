package com.samsung.android.gallery.app.ui.list.stories.favorite;

import android.view.MenuItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        MenuItem menuItem = (MenuItem) obj;
        switch (this.d) {
            case 0:
                menuItem.setVisible(false);
                return;
            default:
                menuItem.setVisible(false);
                return;
        }
    }
}
