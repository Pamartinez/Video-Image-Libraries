package Fa;

import android.content.DialogInterface;
import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ AtomicInteger e;
    public final /* synthetic */ String[] f;
    public final /* synthetic */ Preference g;

    public /* synthetic */ C(AtomicInteger atomicInteger, Preference preference, String[] strArr) {
        this.e = atomicInteger;
        this.g = preference;
        this.f = strArr;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                LabsDeveloperFragment.lambda$initPreferenceDevOptions$9(this.e, this.g, this.f, dialogInterface, i2);
                return;
            default:
                String[] strArr = this.f;
                LabsFragment.lambda$onVideoOnFilmstripClicked$23(this.e, strArr, this.g, dialogInterface, i2);
                return;
        }
    }

    public /* synthetic */ C(AtomicInteger atomicInteger, String[] strArr, Preference preference) {
        this.e = atomicInteger;
        this.f = strArr;
        this.g = preference;
    }
}
