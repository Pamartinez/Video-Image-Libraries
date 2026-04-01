package Fa;

import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsDeveloperFragment e;

    public /* synthetic */ B(LabsDeveloperFragment labsDeveloperFragment, int i2) {
        this.d = i2;
        this.e = labsDeveloperFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LabsDeveloperFragment labsDeveloperFragment = this.e;
        switch (i2) {
            case 0:
                labsDeveloperFragment.lambda$initPreferenceDevOptions$5((Integer) obj);
                return;
            default:
                labsDeveloperFragment.lambda$initPreferenceDevOptions$7((Boolean) obj);
                return;
        }
    }
}
