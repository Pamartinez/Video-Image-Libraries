package com.samsung.android.gallery.app.ui.list.dragdrop.abstraction;

import android.view.DragEvent;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DummyDragAndDropDelegate implements DragAndDropDelegate {
    public boolean handleDrag(View view, DragEvent dragEvent) {
        return false;
    }

    public boolean isAutoDragPossible() {
        return false;
    }

    public boolean isDummy() {
        return true;
    }

    public boolean startAutoDrag(int i2) {
        return true;
    }

    public void destroy() {
    }

    public void release() {
    }

    public void startDrag(MediaItem[] mediaItemArr, ListViewHolder listViewHolder) {
    }

    public void startDragAndDropOnAdvancedMouseAction(int i2, ListViewHolder listViewHolder, Runnable runnable) {
    }
}
