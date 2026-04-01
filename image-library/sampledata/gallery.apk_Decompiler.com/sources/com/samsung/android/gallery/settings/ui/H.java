package com.samsung.android.gallery.settings.ui;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.cloud.SamsungCloudManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class H implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener, SamsungCloudManager.OnCloudSyncStatusListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingCloud e;

    public /* synthetic */ H(SettingCloud settingCloud, int i2) {
        this.d = i2;
        this.e = settingCloud;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return this.e.lambda$initCloudSync$10(preference, obj);
    }

    public boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        SettingCloud settingCloud = this.e;
        switch (i2) {
            case 0:
                return settingCloud.lambda$initVendorCloud$6(preference);
            case 1:
                return settingCloud.lambda$initVendorCloud$4(preference);
            case 2:
                return settingCloud.lambda$initVendorCloud$8(preference);
            case 4:
                return settingCloud.lambda$initCloudSync$11(preference);
            default:
                return settingCloud.lambda$initBackUpSdCard$12(preference);
        }
    }

    public void onUpdateCloudSyncStatus(int i2) {
        this.e.onUpdateCloudSyncOnStatus(i2);
    }
}
