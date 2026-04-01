package com.samsung.android.gallery.app.remote.v2;

import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.remote.v2.GalleryBasePresentation;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GalleryPresentation extends AutoCloseable {
    void clearController();

    void dismiss();

    View getMediaView(int i2);

    void hide();

    void hideMediaView();

    void onDataChanged();

    void post(Runnable runnable);

    void setOnDisplayRemovedListener(GalleryBasePresentation.DisplayRemovedListener displayRemovedListener);

    void setPresentationView(IPresentationView iPresentationView);

    void show();

    void showMediaView();

    void updateData(Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, MediaItem mediaItem, int i2, boolean z);
}
