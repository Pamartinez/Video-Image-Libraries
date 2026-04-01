package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.SettingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class T implements Preference.OnPreferenceChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingFragment e;
    public final /* synthetic */ SettingPreference f;

    public /* synthetic */ T(SettingFragment settingFragment, SettingPreference settingPreference, int i2) {
        this.d = i2;
        this.e = settingFragment;
        this.f = settingPreference;
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        switch (this.d) {
            case 0:
                return this.e.lambda$initViewingPreference$8(this.f, preference, obj);
            case 1:
                return this.e.lambda$initAdvancedPreference$45(this.f, preference, obj);
            case 2:
                return this.e.lambda$initAlbumsPreference$36(this.f, preference, obj);
            case 3:
                return this.e.lambda$initViewingPreference$6(this.f, preference, obj);
            case 4:
                return this.e.lambda$initAlbumsPreference$34(this.f, preference, obj);
            case 5:
                return this.e.lambda$initAdvancedPreference$49(this.f, preference, obj);
            case 6:
                return this.e.lambda$initViewingPreference$12(this.f, preference, obj);
            case 7:
                return this.e.lambda$initAdvancedPreference$51(this.f, preference, obj);
            case 8:
                return this.e.lambda$initStoryPreference$24(this.f, preference, obj);
            case 9:
                return this.e.lambda$initViewingPreference$10(this.f, preference, obj);
            default:
                return this.e.lambda$initStoryPreference$27(this.f, preference, obj);
        }
    }
}
