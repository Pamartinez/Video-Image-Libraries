package com.samsung.android.gallery.module.settings;

import com.samsung.android.gallery.module.settings.SettingPreference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingPreference e;

    public /* synthetic */ b(SettingPreference settingPreference, int i2) {
        this.d = i2;
        this.e = settingPreference;
    }

    public final void run() {
        int i2 = this.d;
        SettingPreference settingPreference = this.e;
        switch (i2) {
            case 0:
                ((SettingPreference.AnonymousClass23) settingPreference).lambda$restoreChanged$1();
                return;
            default:
                ((SettingPreference.AnonymousClass24) settingPreference).lambda$restoreChanged$1();
                return;
        }
    }
}
