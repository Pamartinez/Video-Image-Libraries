package com.samsung.android.gallery.app.ui.list.dragdrop.abstraction;

import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DragAndDropDelegate {
    void destroy();

    boolean handleDrag(View view, DragEvent dragEvent);

    boolean isAutoDragPossible();

    boolean isDraggingOnGallery() {
        return false;
    }

    boolean isDummy() {
        return false;
    }

    boolean isPopupMenuShowing() {
        return false;
    }

    boolean onKeyDown(GalleryListView galleryListView, int i2, KeyEvent keyEvent) {
        return false;
    }

    void release();

    boolean startAutoDrag(int i2);

    void startDrag(MediaItem[] mediaItemArr, ListViewHolder listViewHolder);

    void startDragAndDropOnAdvancedMouseAction(int i2, ListViewHolder listViewHolder, Runnable runnable);

    void cancelAnimation() {
    }

    void resetCurrentMode() {
    }
}
