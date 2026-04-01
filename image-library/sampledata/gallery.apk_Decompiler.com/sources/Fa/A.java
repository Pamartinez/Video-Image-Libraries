package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsDeveloperFragment e;

    public /* synthetic */ A(LabsDeveloperFragment labsDeveloperFragment, int i2) {
        this.d = i2;
        this.e = labsDeveloperFragment;
    }

    public final boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        LabsDeveloperFragment labsDeveloperFragment = this.e;
        switch (i2) {
            case 0:
                return labsDeveloperFragment.lambda$initPreferenceDevOptions$6(preference);
            case 1:
                return labsDeveloperFragment.lambda$initPreferenceDevOptions$14(preference);
            case 2:
                return labsDeveloperFragment.lambda$initPreferenceDevOptions$18(preference);
            case 3:
                return labsDeveloperFragment.lambda$initPreferenceManager$0(preference);
            case 4:
                return labsDeveloperFragment.lambda$initPreferenceManager$1(preference);
            case 5:
                return labsDeveloperFragment.lambda$initPreferenceManager$2(preference);
            default:
                return labsDeveloperFragment.lambda$initPreferenceManager$3(preference);
        }
    }
}
