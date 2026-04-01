package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;

/* renamed from: Fa.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0566u implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsDevManageFragment e;
    public final /* synthetic */ Preference f;

    public /* synthetic */ C0566u(LabsDevManageFragment labsDevManageFragment, Preference preference, int i2) {
        this.d = i2;
        this.e = labsDevManageFragment;
        this.f = preference;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$addCategoryPreferenceManager$4(this.f);
                return;
            default:
                this.e.lambda$addCategoryPreferenceManager$5(this.f);
                return;
        }
    }
}
