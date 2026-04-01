package com.samsung.android.gallery.module.settings;

import com.samsung.android.gallery.module.settings.SettingPreference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ SettingPreference f;

    public /* synthetic */ a(SettingPreference settingPreference, boolean z, int i2) {
        this.d = i2;
        this.f = settingPreference;
        this.e = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SettingPreference.AnonymousClass23) this.f).lambda$notifyChanged$0(this.e);
                return;
            default:
                ((SettingPreference.AnonymousClass24) this.f).lambda$notifyChanged$0(this.e);
                return;
        }
    }
}
