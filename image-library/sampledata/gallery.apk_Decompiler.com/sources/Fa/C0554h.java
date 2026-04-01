package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.EditMenuOptionsFragment;

/* renamed from: Fa.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0554h implements Preference.OnPreferenceChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditMenuOptionsFragment e;
    public final /* synthetic */ SettingPreference f;

    public /* synthetic */ C0554h(EditMenuOptionsFragment editMenuOptionsFragment, SettingPreference settingPreference, int i2) {
        this.d = i2;
        this.e = editMenuOptionsFragment;
        this.f = settingPreference;
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        switch (this.d) {
            case 0:
                return this.e.lambda$initPreference$2(this.f, preference, obj);
            default:
                return this.e.lambda$initPreference$0(this.f, preference, obj);
        }
    }
}
