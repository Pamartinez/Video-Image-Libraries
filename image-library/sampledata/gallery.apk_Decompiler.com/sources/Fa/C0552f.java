package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.DetailEnhancerFragment;

/* renamed from: Fa.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0552f implements Preference.OnPreferenceChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailEnhancerFragment e;

    public /* synthetic */ C0552f(DetailEnhancerFragment detailEnhancerFragment, int i2) {
        this.d = i2;
        this.e = detailEnhancerFragment;
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        int i2 = this.d;
        DetailEnhancerFragment detailEnhancerFragment = this.e;
        switch (i2) {
            case 0:
                return detailEnhancerFragment.lambda$initPreference$2(preference, obj);
            case 1:
                return detailEnhancerFragment.lambda$initPreference$3(preference, obj);
            default:
                return detailEnhancerFragment.lambda$initPreference$4(preference, obj);
        }
    }
}
