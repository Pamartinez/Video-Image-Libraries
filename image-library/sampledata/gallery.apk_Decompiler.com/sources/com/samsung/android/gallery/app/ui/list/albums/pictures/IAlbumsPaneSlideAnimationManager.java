package com.samsung.android.gallery.app.ui.list.albums.pictures;

import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumsPaneSlideAnimationManager {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSlideAnimationListener {
        void onSlideEnd();

        void onSlideStart();
    }

    void cancelAnimation();

    PinchAnimationManager getAnimationManager();

    void onPrepareAnimation(int i2, int i7, int i8);

    void onPrepareNoItemAnimation();
}
