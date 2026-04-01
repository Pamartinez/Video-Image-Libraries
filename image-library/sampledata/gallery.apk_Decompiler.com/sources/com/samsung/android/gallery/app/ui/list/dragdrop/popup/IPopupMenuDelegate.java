package com.samsung.android.gallery.app.ui.list.dragdrop.popup;

import android.view.View;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPopupMenuDelegate {
    void destroy();

    boolean isPopupMenuShowing();

    void release();

    void show(ListViewHolder listViewHolder, Consumer<View> consumer, int i2);
}
