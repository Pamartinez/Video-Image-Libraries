package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoHqPreviewLoader extends AbsImageLoader {
    public void onViewDetached() {
        MediaItem mediaItem;
        super.onViewDetached();
        if (this.mModel.isFirstView() && (mediaItem = this.mLastRequestedItem) != null) {
            eraseDecodedBitmap(mediaItem, this.mModel.getPosition(), true);
            requestBitmap("eraseDecodedBitmap for first item");
        }
    }

    public void processLoadedBitmap(MediaItem mediaItem, Bitmap bitmap, boolean z) {
        processLoadedBitmapInternal(bitmap, mediaItem, z);
    }

    public void processLoadedBitmapInternal(Bitmap bitmap, MediaItem mediaItem, boolean z) {
        if (z) {
            this.mActionInvoker.invoke(ViewerAction.ALREADY_VIDEO_PREVIEW_LOADED, new Object[0]);
        } else if (this.mModel.setPreViewBmp(bitmap, mediaItem)) {
            this.mActionInvoker.invoke(ViewerAction.PREVIEW_LOADED, bitmap, mediaItem);
        }
    }

    public void requestDecode(MediaItem mediaItem, int i2) {
        if ((PreferenceFeatures.OneUi6x.SUPPORT_EMBEDDED_THUMBNAIL_IN_VIDEO || !mediaItem.isLocal() || !mediaItem.is8K()) && getBlackboard().isEmpty("data://video_viewer_return_info") && getBlackboard().isEmpty("data://viewer_group_panel_video_current_info")) {
            super.requestDecode(mediaItem, i2);
        }
    }
}
