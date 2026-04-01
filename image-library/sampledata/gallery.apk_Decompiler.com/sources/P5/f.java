package P5;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.settings.widget.ExSumSwitchPreference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ SharingPicturesSettingFragment d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ long f;
    public final /* synthetic */ String g;

    public /* synthetic */ f(SharingPicturesSettingFragment sharingPicturesSettingFragment, boolean z, long j2, String str) {
        this.d = sharingPicturesSettingFragment;
        this.e = z;
        this.f = j2;
        this.g = str;
    }

    public final void accept(Object obj) {
        this.d.lambda$updateLinkSwitchState$11(this.e, this.f, this.g, (ExSumSwitchPreference) obj);
    }
}
