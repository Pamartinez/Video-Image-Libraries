package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsFragment e;

    public /* synthetic */ D(LabsFragment labsFragment, int i2) {
        this.d = i2;
        this.e = labsFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LabsFragment labsFragment = this.e;
        switch (i2) {
            case 0:
                labsFragment.lambda$initPreferences$0((Boolean) obj);
                return;
            case 1:
                labsFragment.lambda$initPreferenceForOneUi30$20((Boolean) obj);
                return;
            case 2:
                labsFragment.lambda$initPreferences$2((Preference) obj);
                return;
            default:
                labsFragment.lambda$onPrivateAlbumRestoreClicked$32((Integer) obj);
                return;
        }
    }
}
