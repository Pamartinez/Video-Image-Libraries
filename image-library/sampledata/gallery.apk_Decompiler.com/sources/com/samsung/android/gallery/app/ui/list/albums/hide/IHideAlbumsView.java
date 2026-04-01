package com.samsung.android.gallery.app.ui.list.albums.hide;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface IHideAlbumsView extends IAlbumsBaseView {
    boolean onSwitchClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, Consumer<Void> consumer);
}
