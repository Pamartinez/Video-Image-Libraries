package com.samsung.android.gallery.settings.ui;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class G implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3114a;
    public final /* synthetic */ SettingCloud b;

    public /* synthetic */ G(SettingCloud settingCloud, int i2) {
        this.f3114a = i2;
        this.b = settingCloud;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f3114a;
        Preference preference = (Preference) obj;
        SettingPreference settingPreference = (SettingPreference) obj2;
        SettingCloud settingCloud = this.b;
        switch (i2) {
            case 0:
                settingCloud.lambda$initVendorCloud$5(preference, settingPreference);
                return;
            case 1:
                settingCloud.lambda$initVendorCloud$7(preference, settingPreference);
                return;
            default:
                settingCloud.lambda$initVendorCloud$9(preference, settingPreference);
                return;
        }
    }
}
