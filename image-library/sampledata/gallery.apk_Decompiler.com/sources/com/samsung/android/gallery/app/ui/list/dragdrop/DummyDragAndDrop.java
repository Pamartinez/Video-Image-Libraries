package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DummyDragAndDrop extends DragAndDrop {
    public boolean dragStart(GalleryListView galleryListView, View view, ClipData clipData, ListViewHolder listViewHolder, int i2) {
        return false;
    }

    public boolean handleDrop(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        return false;
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        return false;
    }
}
