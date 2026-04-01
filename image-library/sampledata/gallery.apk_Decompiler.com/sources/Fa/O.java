package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.SettingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class O implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingFragment e;

    public /* synthetic */ O(SettingFragment settingFragment, int i2) {
        this.d = i2;
        this.e = settingFragment;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i2 = this.d;
        SettingFragment settingFragment = this.e;
        switch (i2) {
            case 1:
                return settingFragment.lambda$initAlbumsPreference$39(preference, obj);
            case 9:
                return settingFragment.lambda$initAlbumsPreference$40(preference, obj);
            default:
                return settingFragment.lambda$initAdvancedPreference$43(preference, obj);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        SettingFragment settingFragment = this.e;
        switch (i2) {
            case 0:
                return settingFragment.lambda$initAlbumsPreference$38(preference);
            case 2:
                return settingFragment.lambda$initAdvancedPreference$53(preference);
            case 3:
                return settingFragment.lambda$initAboutPreference$62(preference);
            case 4:
                return settingFragment.lambda$initEditingPreference$18(preference);
            case 5:
                return settingFragment.lambda$initEditingPreference$16(preference);
            case 6:
                return settingFragment.lambda$initEditingPreference$14(preference);
            case 7:
                return settingFragment.lambda$initIntelligentFeaturesPreference$3(preference);
            case 8:
                return settingFragment.lambda$initEditingPreference$20(preference);
            case 10:
                return settingFragment.lambda$initAlbumsPreference$41(preference);
            case 11:
                return settingFragment.lambda$initAboutPreference$58(preference);
            case 12:
                return settingFragment.lambda$initStoryPreference$30(preference);
            case 13:
                return settingFragment.lambda$initPrivacyPreference$54(preference);
            case 14:
                return settingFragment.lambda$initAboutPreference$60(preference);
            case 15:
                return settingFragment.lambda$initEditingPreference$22(preference);
            default:
                return settingFragment.lambda$initPrivacyPreference$56(preference);
        }
    }
}
