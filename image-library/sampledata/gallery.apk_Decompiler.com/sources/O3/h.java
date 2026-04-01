package O3;

import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements FixDateTimeCmd.Updater {
    public final /* synthetic */ ChangeLocationCmd d;
    public final /* synthetic */ double e;
    public final /* synthetic */ double f;

    public /* synthetic */ h(ChangeLocationCmd changeLocationCmd, double d2, double d3) {
        this.d = changeLocationCmd;
        this.e = d2;
        this.f = d3;
    }

    public final boolean update(MediaItem mediaItem) {
        return this.d.lambda$changeLocationAsync$4(this.e, this.f, mediaItem);
    }
}
