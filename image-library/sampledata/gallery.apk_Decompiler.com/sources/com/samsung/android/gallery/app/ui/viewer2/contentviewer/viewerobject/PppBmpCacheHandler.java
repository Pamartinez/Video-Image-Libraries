package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.viewer.LastPreviewData;
import com.samsung.android.gallery.support.utils.Log;
import u7.C0520a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PppBmpCacheHandler extends ViewerObject {
    /* access modifiers changed from: private */
    public void onBitmapLoaded(Object... objArr) {
        Bitmap bitmap = objArr[0];
        MediaItem mediaItem = objArr[1];
        if (mediaItem.isPostProcessing()) {
            Log.d(this.TAG, "save ppp original to last");
            this.mModel.getContainerModel().setLastPreviewData(new LastPreviewData(bitmap, mediaItem, this.mModel.getPosition()).setPostCandidate(), "po");
            return;
        }
        LastPreviewData lastPreviewData = this.mModel.getContainerModel().getLastPreviewData();
        if (lastPreviewData != null && lastPreviewData.isPostCandidate()) {
            Log.d(this.TAG, "overwrite old ppp");
            this.mModel.getContainerModel().setLastPreviewData(new LastPreviewData(bitmap, mediaItem, this.mModel.getPosition()), "op");
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new C0520a(4, this));
    }
}
