package com.samsung.android.gallery.module.receiver;

import android.net.Uri;
import android.provider.Settings;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingSharedAlbumBlockObserver extends SettingObserver {
    public String getGlobalKey() {
        return "global://setting/secure/shared_album_blocker_changed";
    }

    public Uri getObservingUri() {
        return Settings.Secure.getUriFor(getUriString());
    }

    public String getUriString() {
        return "rampart_blocked_shared_album_gallery";
    }

    public boolean isSettingEnabled() {
        return SeApiCompat.isSharedAlbumBlocked(getAppContext());
    }

    public void updateCommonData(boolean z, boolean z3) {
        Features.recycle(Features.IS_SHARED_ALBUM_BLOCKED, z3);
    }
}
