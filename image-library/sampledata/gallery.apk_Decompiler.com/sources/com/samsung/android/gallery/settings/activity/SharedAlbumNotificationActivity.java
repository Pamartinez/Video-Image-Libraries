package com.samsung.android.gallery.settings.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.SharingNotificationFragment;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedAlbumNotificationActivity extends BasePreferenceActivity {
    private int getTitleId() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R$string.shared_album_notification;
        }
        return R$string.share_notification;
    }

    public String getActivityTitle() {
        return getString(getTitleId());
    }

    public Fragment getFragment(String str) {
        return new SharingNotificationFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getTitleId());
        commitFragment();
    }
}
