package com.samsung.android.sdk.mobileservice.social.share;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySpace extends Space {
    private String mSyncSortValue;

    public GallerySpace(String str, String str2, String str3, Bundle bundle) {
        super(str, str2, str3);
        this.mSyncSortValue = bundle.getString(BundleKey.SYNC_SORT_VALUE);
    }

    public String getSyncSortValue() {
        return this.mSyncSortValue;
    }
}
