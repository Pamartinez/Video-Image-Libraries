package O3;

import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements FixDateTimeCmd.Updater {
    public final /* synthetic */ long d;

    public /* synthetic */ n(long j2) {
        this.d = j2;
    }

    public final boolean update(MediaItem mediaItem) {
        return FileUtils.setDateModified(mediaItem.getPath(), this.d);
    }
}
