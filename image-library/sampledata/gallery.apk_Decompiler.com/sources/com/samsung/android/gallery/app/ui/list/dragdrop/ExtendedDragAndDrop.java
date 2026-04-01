package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.content.ClipData;
import android.net.Uri;
import android.view.DragEvent;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExtendedDragAndDrop extends NormalDragAndDrop {
    public ArrayList<Uri> getUriListFromClipData(ClipData clipData) {
        if (clipData == null) {
            Log.w(this.TAG, "getUriListFromClipData skip. null data");
            return null;
        }
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
            Uri uri = clipData.getItemAt(i2).getUri();
            if (uri != null) {
                arrayList.add(uri);
            }
        }
        return arrayList;
    }

    public boolean handleDrop(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        if (isDragStartedFromGallery(dragEvent)) {
            return super.handleDrop(iBaseListView, dragEvent, mediaItem);
        }
        return handleDropFromExternal(iBaseListView, dragEvent, mediaItem);
    }

    public abstract boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem);
}
