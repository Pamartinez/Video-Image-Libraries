package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.IntelligentFeaturesFragment;

/* renamed from: Fa.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0556j implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ IntelligentFeaturesFragment e;

    public /* synthetic */ C0556j(IntelligentFeaturesFragment intelligentFeaturesFragment, int i2) {
        this.d = i2;
        this.e = intelligentFeaturesFragment;
    }

    public final boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        IntelligentFeaturesFragment intelligentFeaturesFragment = this.e;
        switch (i2) {
            case 0:
                return intelligentFeaturesFragment.lambda$initPreference$2(preference);
            case 1:
                return intelligentFeaturesFragment.lambda$initPreference$4(preference);
            default:
                return intelligentFeaturesFragment.lambda$initPreference$0(preference);
        }
    }
}
