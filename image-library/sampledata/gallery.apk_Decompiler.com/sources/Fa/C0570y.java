package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;

/* renamed from: Fa.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0570y implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Preference e;

    public /* synthetic */ C0570y(Preference preference, int i2) {
        this.d = i2;
        this.e = preference;
    }

    public final void run() {
        int i2 = this.d;
        Preference preference = this.e;
        switch (i2) {
            case 0:
                new LabsDevManageFragment.CacheDumpWorker().execute(preference.getContext());
                return;
            default:
                new LabsDevManageFragment.TrashCopyWorker().execute(preference.getContext());
                return;
        }
    }
}
