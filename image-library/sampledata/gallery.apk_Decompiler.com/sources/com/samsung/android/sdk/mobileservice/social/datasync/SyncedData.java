package com.samsung.android.sdk.mobileservice.social.datasync;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SyncedData {
    private final String TIMESTAMP = "timestamp";
    private final IData mData;
    private final long mTimestamp;

    public SyncedData(String str, Bundle bundle) {
        if (TextUtils.equals(str, ProfileCard.SYNC_SERVICE_NAME)) {
            this.mData = new ProfileCard(bundle);
        } else if (TextUtils.equals(str, GallerySetting.SYNC_SERVICE_NAME)) {
            this.mData = new GallerySetting(bundle);
        } else if (TextUtils.equals(str, GallerySpace.SYNC_SERVICE_NAME)) {
            this.mData = new GallerySpace(bundle);
        } else if (TextUtils.equals(str, ReminderSpace.SYNC_SERVICE_NAME)) {
            this.mData = new ReminderSpace(bundle);
        } else {
            this.mData = null;
        }
        this.mTimestamp = bundle.getLong("timestamp");
    }

    public IData getData() {
        return this.mData;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }
}
