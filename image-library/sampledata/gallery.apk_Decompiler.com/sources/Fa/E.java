package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class E implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsFragment e;

    public /* synthetic */ E(LabsFragment labsFragment, int i2) {
        this.d = i2;
        this.e = labsFragment;
    }

    public final boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        LabsFragment labsFragment = this.e;
        switch (i2) {
            case 0:
                return labsFragment.lambda$initPreferences$1(preference);
            case 1:
                return labsFragment.onHeapDumpClicked(preference);
            case 2:
                return labsFragment.lambda$initPreferenceForDeveloper$4(preference);
            case 3:
                return labsFragment.onVideoOnFilmstripClicked(preference);
            case 4:
                return labsFragment.lambda$initPreferenceForUtilities$10(preference);
            case 5:
                return labsFragment.onPrivateAlbumRestoreClicked(preference);
            default:
                return labsFragment.lambda$initPreferenceForUtilities$16(preference);
        }
    }
}
