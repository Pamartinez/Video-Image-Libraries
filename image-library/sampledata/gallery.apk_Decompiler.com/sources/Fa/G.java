package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class G implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Boolean e;

    public /* synthetic */ G(int i2, Boolean bool) {
        this.d = i2;
        this.e = bool;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Boolean bool = this.e;
        switch (i2) {
            case 0:
                LabsFragment.lambda$initPreferenceForOneUi30$19(bool, (Preference) obj);
                return;
            default:
                CreatureData.of((MediaItem) obj).isCreatureHide = bool.booleanValue();
                return;
        }
    }
}
