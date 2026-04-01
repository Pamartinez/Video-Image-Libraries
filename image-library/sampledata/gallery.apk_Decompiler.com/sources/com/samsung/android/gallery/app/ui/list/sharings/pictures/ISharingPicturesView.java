package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface ISharingPicturesView extends IPicturesView {
    void dismissAlbumLinkTip();

    SharingCoverView getCoverView();

    int getFamilyAlbumId();

    MediaItem getHeaderItem();

    void showAlbumLinkTip();

    void startEnlargeAnimation();

    void updateCoverItemCount(int i2, int i7);

    void updateEmptyView();

    void updateGroup(MediaItem mediaItem);
}
