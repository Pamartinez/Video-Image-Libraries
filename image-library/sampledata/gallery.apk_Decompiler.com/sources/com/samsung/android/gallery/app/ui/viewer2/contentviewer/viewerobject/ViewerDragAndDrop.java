package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadow;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewerDragAndDrop {
    private Bitmap getDragBitmap(MediaItem mediaItem, Bitmap bitmap, int i2, int i7, int i8) {
        boolean z;
        if (bitmap == null) {
            return null;
        }
        if (mediaItem.isCreature() || mediaItem.isPanoramic() || mediaItem.isCustomCover()) {
            z = false;
        } else {
            z = true;
        }
        return BitmapUtils.getRoundedCornerBitmap(new BitmapOperator(bitmap).resize(i7).stretchable(z).rotate(i2).apply(), (float) i8);
    }

    private Bitmap getDragShadowBitmapForViewer(Context context, Bitmap bitmap, MediaItem mediaItem) {
        int orientation;
        if (bitmap == null) {
            return null;
        }
        SeApiCompat.performHapticFeedback(context, 14);
        if (mediaItem.isVideo()) {
            orientation = 0;
        } else {
            orientation = mediaItem.getOrientation();
        }
        return getDragBitmap(mediaItem, bitmap, orientation, getThumbSize(context, bitmap), context.getResources().getDimensionPixelSize(R.dimen.drag_and_drop_min_radius));
    }

    private int getThumbSize(Context context, Bitmap bitmap) {
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        return Math.min(context.getResources().getDimensionPixelSize(R.dimen.drag_and_drop_thumb_size_max), Math.max(context.getResources().getDimensionPixelSize(R.dimen.drag_and_drop_thumb_size_min), min));
    }

    public void DexOnPCDragAndDrop_sendPermissionBroadcastForDexOnPC(Context context, ClipData clipData) {
        Intent intent = new Intent();
        intent.setAction("com.sec.android.app.dexonpc.START_DRAG");
        intent.setClipData(clipData);
        intent.addFlags(1);
        context.sendBroadcast(intent, "dexonpc.app.action.permission.KMS_FILETRANSFER_DRAG_FILEINFO");
        Log.i("ViewerDragAndDrop", "Dex on PC send intent : START_DRAG");
    }

    public boolean isDexOnPc(Context context) {
        return DeviceInfo.isDexOnPc(context);
    }

    public void startDragAndDrop(Context context, ImageView imageView, Bitmap bitmap, MediaItem mediaItem) {
        if (mediaItem == null || imageView == null) {
            Log.d("ViewerDragAndDrop", "startDragAndDrop failed. invalid data. curItem[" + mediaItem + "] curView[" + imageView + "]");
            return;
        }
        ClipData clipDataForViewer = ClipDataManager.getInstance().getClipDataForViewer(context, new MediaItem[]{mediaItem}, DeviceInfo.isDexMode(context));
        if (clipDataForViewer == null) {
            Log.d("ViewerDragAndDrop", "startDragAndDrop failed. invalid clipData");
            return;
        }
        if (isDexOnPc(context)) {
            DexOnPCDragAndDrop_sendPermissionBroadcastForDexOnPC(context, clipDataForViewer);
        }
        Log.d("ViewerDragAndDrop", "startDragAndDrop#start");
        imageView.startDragAndDrop(clipDataForViewer, new DragShadow(new ImageView(context), context, getDragShadowBitmapForViewer(context, bitmap, mediaItem)), (Object) null, ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8);
    }
}
