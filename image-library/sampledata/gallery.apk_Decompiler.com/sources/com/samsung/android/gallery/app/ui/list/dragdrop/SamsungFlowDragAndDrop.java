package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungFlowDragAndDrop extends ExtendedDragAndDrop {
    public boolean dragStart(GalleryListView galleryListView, View view, ClipData clipData, ListViewHolder listViewHolder, int i2) {
        Intent intent = new Intent();
        intent.setAction("com.sec.android.samsungflow.source.START_DRAG");
        intent.setClipData(clipData);
        galleryListView.getContext().sendBroadcast(intent, "com.sec.android.permission.SAMSUNG_FLOW_RECEIVER_PERMISSION");
        Log.i(this.TAG, "SamsungFlow send Intent : START_DRAG");
        return false;
    }

    public boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        String str;
        int i2;
        boolean z;
        Context context = iBaseListView.getContext();
        if (context == null) {
            Log.d(this.TAG, "handleDropFromExternal failed.  Context is null");
            return false;
        }
        ClipData clipData = dragEvent.getClipData();
        if (clipData == null) {
            clipData = new SafeClipboard(context).getPrimaryClip();
            String str2 = this.TAG;
            if (clipData != null) {
                z = true;
            } else {
                z = false;
            }
            Log.d(str2, "handleDropFromExternal load data", Boolean.valueOf(z));
        }
        if (isValidDropEvent(dragEvent)) {
            if (mediaItem != null) {
                str = FileUtils.getDirectoryFromPath(mediaItem.getReferencePath(), false);
            } else {
                str = null;
            }
            Intent intent = new Intent("com.sec.android.samsungflow.sink.fileUri");
            intent.putExtra("dstFolderUri", str);
            context.sendBroadcast(intent, "com.sec.android.permission.SAMSUNG_FLOW_RECEIVER_PERMISSION");
            String str3 = this.TAG;
            if (clipData != null) {
                i2 = clipData.getItemCount();
            } else {
                i2 = 0;
            }
            Log.d(str3, "handleDropFromExternal", Integer.valueOf(i2), Logger.getEncodedString(str));
        }
        return false;
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        return TextUtils.equals("startSamsungFlowDrag", ClipDataUtils.getLabel(dragEvent.getClipDescription()));
    }
}
