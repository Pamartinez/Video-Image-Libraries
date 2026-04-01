package com.samsung.android.gallery.module.crop;

import android.os.Bundle;
import com.samsung.android.gallery.support.utils.BundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CropRequestInfo {
    public static Bundle get(Bundle bundle) {
        boolean z = BundleWrapper.getBoolean(bundle, "FromStoryCover", false);
        boolean z3 = BundleWrapper.getBoolean(bundle, "FromSharedAlbumCover", false);
        boolean z7 = BundleWrapper.getBoolean(bundle, "FromAlbumCover", false);
        boolean z9 = BundleWrapper.getBoolean(bundle, "FromGalleryPicker", false);
        boolean z10 = BundleWrapper.getBoolean(bundle, "is-get-rect-result", false);
        Bundle bundle2 = new Bundle();
        if (z7 || z3) {
            bundle2.putInt("aspectX", BundleWrapper.getInt(bundle, "aspectX", 360));
            bundle2.putInt("aspectY", BundleWrapper.getInt(bundle, "aspectY", 360));
        } else if (z) {
            bundle2.putInt("aspectX", BundleWrapper.getInt(bundle, "aspectX", 360));
            bundle2.putInt("aspectY", BundleWrapper.getInt(bundle, "aspectY", 254));
        }
        bundle2.putString("key-shared-item-id", BundleWrapper.getString(bundle, "key-shared-item-id", (String) null));
        String string = BundleWrapper.getString(bundle, "key-shared-album-space-id", (String) null);
        if (string != null) {
            bundle2.putString("key-shared-album-space-id", string);
        }
        bundle2.putBoolean("FromStoryCover", z);
        bundle2.putBoolean("FromSharedAlbumCover", z3);
        bundle2.putBoolean("FromAlbumCover", z7);
        bundle2.putBoolean("FromGalleryPicker", z9);
        bundle2.putBoolean("is-get-rect-result", z10);
        return bundle2;
    }
}
