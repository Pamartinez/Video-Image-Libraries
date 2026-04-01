package O3;

import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ExifUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements FixDateTimeCmd.Updater {
    public final /* synthetic */ int d;
    public final /* synthetic */ String[] e;

    public /* synthetic */ f(String[] strArr, int i2) {
        this.d = i2;
        this.e = strArr;
    }

    public final boolean update(MediaItem mediaItem) {
        int i2 = this.d;
        String[] strArr = this.e;
        switch (i2) {
            case 0:
                return ExifUtils.changeDateTime(mediaItem.getPath(), strArr);
            default:
                return ExifUtils.changeDateTime(mediaItem.getPath(), strArr);
        }
    }
}
