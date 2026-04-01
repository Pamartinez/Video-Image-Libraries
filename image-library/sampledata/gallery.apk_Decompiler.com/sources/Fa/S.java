package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.helper.KnoxRestrictions;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class S implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2806a;
    public final /* synthetic */ SettingFragment b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ KnoxRestrictions f2807c;

    public /* synthetic */ S(SettingFragment settingFragment, KnoxRestrictions knoxRestrictions, int i2) {
        this.f2806a = i2;
        this.b = settingFragment;
        this.f2807c = knoxRestrictions;
    }

    public final void accept(Object obj, Object obj2) {
        Preference preference = (Preference) obj;
        SettingPreference settingPreference = (SettingPreference) obj2;
        switch (this.f2806a) {
            case 0:
                this.b.lambda$initEditingPreference$15(this.f2807c, preference, settingPreference);
                return;
            default:
                this.b.lambda$initEditingPreference$17(this.f2807c, preference, settingPreference);
                return;
        }
    }
}
