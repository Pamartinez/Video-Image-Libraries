package com.samsung.android.gallery.app.ui.list.sharings.factory;

import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingPinchFragment;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SharedAlbumFactory {
    private static final boolean SUPPORT_SHARED_ALBUM_PINCH;

    static {
        boolean z;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SharedAlbumPinch) || !PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_SHARED_ALBUM_PINCH = z;
    }

    public static MvpBaseFragment create() {
        if (SUPPORT_SHARED_ALBUM_PINCH) {
            return new SharingPinchFragment();
        }
        return new SharingsFragment();
    }
}
