package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.widget.utils.ResourceUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnsupportedIconLoader extends ViewerObject {
    public void onViewAttached() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        Bitmap replacedThumbnail = ThumbnailLoader.getInstance().getReplacedThumbnail(this.mModel.getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
        if (this.mModel.setBitmap(replacedThumbnail, mediaItem)) {
            this.mActionInvoker.invoke(ViewerAction.BITMAP_LOADED, replacedThumbnail, mediaItem, Boolean.FALSE);
        }
    }
}
