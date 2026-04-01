package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.view.DragEvent;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiWindowDragAndDrop extends ExtendedDragAndDrop {
    public boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        return false;
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        return false;
    }
}
