package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.graphics.PointF;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PopupMenuData {
    public final MediaItem mMediaItem;
    public final PointF mPosition;
    public final ViewGroup mViewGroup;

    public PopupMenuData(ViewGroup viewGroup, PointF pointF, MediaItem mediaItem) {
        this.mViewGroup = viewGroup;
        this.mPosition = pointF;
        this.mMediaItem = mediaItem;
    }
}
