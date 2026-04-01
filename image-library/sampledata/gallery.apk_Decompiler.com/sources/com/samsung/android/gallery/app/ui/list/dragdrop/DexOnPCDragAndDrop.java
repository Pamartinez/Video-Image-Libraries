package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexOnPCDragAndDrop extends DexDragAndDrop {
    private boolean isDexOnPCDropEvent(ClipDescription clipDescription) {
        return TextUtils.equals("startDoPDrag", ClipDataUtils.getLabel(clipDescription));
    }

    private boolean processDrop(IBaseListView iBaseListView, ClipData clipData, MediaItem mediaItem, boolean z) {
        int i2;
        String str;
        boolean z3;
        Context context = iBaseListView.getContext();
        if (context == null) {
            Log.d(this.TAG, "processDrop failed. Context is null");
            return false;
        }
        if (clipData == null) {
            clipData = new SafeClipboard(context).getPrimaryClip();
            String str2 = this.TAG;
            if (clipData != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            Log.d(str2, "processDrop load data", Boolean.valueOf(z3));
        }
        if (clipData == null || (!z && !isDexOnPCDropEvent(clipData.getDescription()))) {
            i2 = -1;
        } else {
            i2 = ClipDataUtils.getIntExtra(clipData, "id");
            if (mediaItem != null) {
                str = FileUtils.getDirectoryFromPath(mediaItem.getReferencePath(), false);
            } else {
                str = null;
            }
            if (str == null) {
                Log.d(this.TAG, "processDrop targetAlbumPath is null. set Default ");
                str = StorageInfo.getDefault().download;
            }
            Intent intent = new Intent();
            intent.setAction("com.sec.android.app.dexonpc.dstFolder");
            intent.putExtra("id", i2);
            intent.putExtra("dstFolderUri", str);
            context.sendBroadcast(intent, "dexonpc.app.action.permission.KMS_FILETRANSFER_DRAG_FILEINFO");
            Log.i(this.TAG, "processDropEvent", Integer.valueOf(clipData.getItemCount()), Integer.valueOf(i2), Logger.getEncodedString(str));
        }
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public boolean dragStart(GalleryListView galleryListView, View view, ClipData clipData, ListViewHolder listViewHolder, int i2) {
        Context context = galleryListView.getContext();
        if (i2 > 1000) {
            String str = this.TAG;
            Log.d(str, "dragStart skip. max count {" + i2 + "}");
            Toast.makeText(context, context.getString(R.string.file_limit_exceed, new Object[]{1000}), 0).show();
            return false;
        }
        sendPermissionBroadcastForDexOnPC(context, clipData);
        return super.dragStart(galleryListView, view, clipData, listViewHolder, i2);
    }

    public boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        if (!isDexOnPCDropEvent(dragEvent.getClipDescription())) {
            return super.handleDropFromExternal(iBaseListView, dragEvent, mediaItem);
        }
        return processDrop(iBaseListView, dragEvent.getClipData(), mediaItem, true);
    }

    public boolean handlePaste(IBaseListView iBaseListView, MediaItem mediaItem) {
        return processDrop(iBaseListView, (ClipData) null, mediaItem, false);
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        if (isDexOnPCDropEvent(dragEvent.getClipDescription()) || super.isValidDropEvent(dragEvent)) {
            return true;
        }
        return false;
    }

    public void sendPermissionBroadcastForDexOnPC(Context context, ClipData clipData) {
        Intent intent = new Intent();
        intent.setAction("com.sec.android.app.dexonpc.START_DRAG");
        intent.setClipData(clipData);
        intent.addFlags(1);
        context.sendBroadcast(intent, "dexonpc.app.action.permission.KMS_FILETRANSFER_DRAG_FILEINFO");
        Log.i(this.TAG, "Dex on PC send intent : START_DRAG");
    }
}
