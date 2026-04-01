package com.samsung.android.sdk.mobileservice.social.share;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReminderSpace extends Space {
    private Integer mSyncColor;
    private Integer mSyncIcon;

    public ReminderSpace(String str, String str2, String str3, Bundle bundle) {
        super(str, str2, str3);
        Integer num;
        Integer num2 = null;
        if (bundle.containsKey(BundleKey.SYNC_COLOR)) {
            num = Integer.valueOf(bundle.getInt(BundleKey.SYNC_COLOR));
        } else {
            num = null;
        }
        this.mSyncColor = num;
        this.mSyncIcon = bundle.containsKey(BundleKey.SYNC_ICON) ? Integer.valueOf(bundle.getInt(BundleKey.SYNC_ICON)) : num2;
    }

    public Integer getSyncColor() {
        return this.mSyncColor;
    }

    public Integer getSyncIcon() {
        return this.mSyncIcon;
    }
}
