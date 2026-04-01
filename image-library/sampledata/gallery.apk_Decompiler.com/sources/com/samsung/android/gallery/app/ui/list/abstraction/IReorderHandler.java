package com.samsung.android.gallery.app.ui.list.abstraction;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IReorderHandler {
    void folderCreatedAt(int i2, MediaItem mediaItem, int i7, String str);

    MediaItem getSyncedItem(int i2);

    void moveFolderToPosition(int i2, int i7);

    void moveItemTo(int i2, int i7);

    void orderChanged(int i2, Object obj);

    void removeItemAt(int i2);

    void resetDrag();

    void setDraggedIndex(int i2);

    void setDragging(boolean z);

    void updateOrder(EventContext eventContext);
}
