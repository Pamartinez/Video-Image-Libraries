package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.IntelligentFeaturesFragment;
import java.util.function.BiConsumer;

/* renamed from: Fa.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0555i implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2809a;
    public final /* synthetic */ IntelligentFeaturesFragment b;

    public /* synthetic */ C0555i(IntelligentFeaturesFragment intelligentFeaturesFragment, int i2) {
        this.f2809a = i2;
        this.b = intelligentFeaturesFragment;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2809a;
        Preference preference = (Preference) obj;
        SettingPreference settingPreference = (SettingPreference) obj2;
        IntelligentFeaturesFragment intelligentFeaturesFragment = this.b;
        switch (i2) {
            case 0:
                intelligentFeaturesFragment.lambda$initPreference$1(preference, settingPreference);
                return;
            case 1:
                intelligentFeaturesFragment.lambda$initPreference$3(preference, settingPreference);
                return;
            default:
                intelligentFeaturesFragment.lambda$initPreference$5(preference, settingPreference);
                return;
        }
    }
}
