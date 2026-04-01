package Fa;

import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class U implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingPreference e;
    public final /* synthetic */ SwitchPreferenceCompat f;

    public /* synthetic */ U(SettingPreference settingPreference, SwitchPreferenceCompat switchPreferenceCompat, int i2) {
        this.d = i2;
        this.e = settingPreference;
        this.f = switchPreferenceCompat;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                SettingFragment.lambda$initStoryPreference$25(this.e, this.f, (Boolean) obj);
                return;
            default:
                SettingFragment.lambda$initStoryPreference$28(this.e, this.f, (Boolean) obj);
                return;
        }
    }
}
