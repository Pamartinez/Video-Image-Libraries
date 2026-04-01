package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.settings.ui.SearchSettingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class N implements Preference.OnPreferenceChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchSettingFragment e;
    public final /* synthetic */ MiscSettingPreference f;

    public /* synthetic */ N(SearchSettingFragment searchSettingFragment, MiscSettingPreference miscSettingPreference, int i2) {
        this.d = i2;
        this.e = searchSettingFragment;
        this.f = miscSettingPreference;
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        switch (this.d) {
            case 0:
                return this.e.lambda$initRecentOption$4(this.f, preference, obj);
            default:
                return this.e.lambda$initSuggestionOption$6(this.f, preference, obj);
        }
    }
}
