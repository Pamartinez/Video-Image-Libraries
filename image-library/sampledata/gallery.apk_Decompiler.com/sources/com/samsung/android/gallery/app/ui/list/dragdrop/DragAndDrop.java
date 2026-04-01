package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.content.ClipData;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DragAndDrop {
    protected final String TAG = getClass().getSimpleName();

    private boolean isInvalidParams(GalleryListView galleryListView, ListViewHolder listViewHolder, int i2) {
        if (listViewHolder == null || galleryListView == null || i2 == 0) {
            Log.d(this.TAG, "start drag failed. invalid param.");
            return true;
        } else if (listViewHolder.getMediaItem() == null) {
            Log.d(this.TAG, "start drag failed. invalid currentItem");
            return true;
        } else if (listViewHolder.getImage() != null) {
            return false;
        } else {
            Log.d(this.TAG, "start drag failed. invalid currentView");
            return true;
        }
    }

    public abstract boolean dragStart(GalleryListView galleryListView, View view, ClipData clipData, ListViewHolder listViewHolder, int i2);

    public abstract boolean handleDrop(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem);

    public boolean isDragStartedFromGallery(DragEvent dragEvent) {
        CharSequence label = ClipDataUtils.getLabel(dragEvent.getClipDescription());
        if (TextUtils.equals("galleryUri", label) || TextUtils.equals("selectedUri", label)) {
            return true;
        }
        return false;
    }

    public boolean isObjectCapture(DragEvent dragEvent) {
        return ClipDataUtils.getBooleanExtra(dragEvent.getClipData(), "gallery_object_capture");
    }

    public abstract boolean isValidDropEvent(DragEvent dragEvent);

    public final boolean start(GalleryListView galleryListView, View view, ClipData clipData, ListViewHolder listViewHolder, int i2) {
        if (isInvalidParams(galleryListView, listViewHolder, i2)) {
            return false;
        }
        return dragStart(galleryListView, view, clipData, listViewHolder, i2);
    }
}
