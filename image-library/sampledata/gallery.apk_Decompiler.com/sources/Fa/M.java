package Fa;

import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.settings.ui.SearchSettingFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class M implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchSettingFragment e;
    public final /* synthetic */ MiscSettingPreference f;

    public /* synthetic */ M(SearchSettingFragment searchSettingFragment, MiscSettingPreference miscSettingPreference, int i2) {
        this.d = i2;
        this.e = searchSettingFragment;
        this.f = miscSettingPreference;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$initSuggestionOption$7(this.f, (SwitchPreferenceCompat) obj);
                return;
            default:
                this.e.lambda$initRecentOption$5(this.f, (SwitchPreferenceCompat) obj);
                return;
        }
    }
}
