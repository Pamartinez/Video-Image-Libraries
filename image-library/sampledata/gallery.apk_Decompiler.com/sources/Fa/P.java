package Fa;

import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class P implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2805a;
    public final /* synthetic */ SettingFragment b;

    public /* synthetic */ P(SettingFragment settingFragment, int i2) {
        this.f2805a = i2;
        this.b = settingFragment;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2805a) {
            case 0:
                this.b.lambda$initAlbumsPreference$42((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 1:
                this.b.lambda$initAdvancedPreference$44((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 2:
                this.b.lambda$initAdvancedPreference$46((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 3:
                this.b.lambda$initAdvancedPreference$48((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 4:
                this.b.lambda$initAdvancedPreference$50((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 5:
                this.b.lambda$initAdvancedPreference$52((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 6:
                this.b.lambda$initStoryPreference$26((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 7:
                this.b.lambda$initStoryPreference$29((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 8:
                this.b.lambda$initStoryPreference$31((Preference) obj, (SettingPreference) obj2);
                return;
            case 9:
                this.b.lambda$initPrivacyPreference$55((Preference) obj, (SettingPreference) obj2);
                return;
            case 10:
                this.b.lambda$initPrivacyPreference$57((Preference) obj, (SettingPreference) obj2);
                return;
            case 11:
                this.b.lambda$initAboutPreference$59((Preference) obj, (SettingPreference) obj2);
                return;
            case 12:
                this.b.lambda$initAboutPreference$61((Preference) obj, (SettingPreference) obj2);
                return;
            case 13:
                this.b.lambda$initIntelligentFeaturesPreference$5((Preference) obj, (SettingPreference) obj2);
                return;
            case 14:
                this.b.lambda$initEditingPreference$19((Preference) obj, (SettingPreference) obj2);
                return;
            case 15:
                this.b.lambda$initEditingPreference$21((Preference) obj, (SettingPreference) obj2);
                return;
            case 16:
                this.b.lambda$initEditingPreference$23((Preference) obj, (SettingPreference) obj2);
                return;
            case 17:
                this.b.lambda$initViewingPreference$7((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 18:
                this.b.lambda$initViewingPreference$9((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 19:
                this.b.lambda$initViewingPreference$11((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 20:
                this.b.lambda$initViewingPreference$13((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 21:
                this.b.lambda$initAlbumsPreference$35((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            default:
                this.b.lambda$initAlbumsPreference$37((SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
        }
    }
}
