package com.samsung.android.gallery.app.ui.list.picker.albums;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumsPickerView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPickerAdapter<V extends IAlbumsPickerView> extends AlbumsBaseViewAdapter<V> {
    public AlbumsPickerAdapter(V v, String str) {
        super(v, str);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        if (isData(i2) && !((IAlbumsPickerView) this.mView).isItemEnabled(listViewHolder.getMediaItem())) {
            ViewUtils.setViewEnabled(listViewHolder.itemView, false);
        }
    }
}
