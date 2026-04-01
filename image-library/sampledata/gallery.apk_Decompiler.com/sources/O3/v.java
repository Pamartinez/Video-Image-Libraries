package O3;

import com.samsung.android.gallery.app.controller.internals.SaveAsPdfCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements Comparator {
    public final /* synthetic */ int d;
    public final /* synthetic */ SaveAsPdfCmd e;

    public /* synthetic */ v(SaveAsPdfCmd saveAsPdfCmd, int i2) {
        this.d = i2;
        this.e = saveAsPdfCmd;
    }

    public final int compare(Object obj, Object obj2) {
        int i2 = this.d;
        MediaItem mediaItem = (MediaItem) obj;
        MediaItem mediaItem2 = (MediaItem) obj2;
        SaveAsPdfCmd saveAsPdfCmd = this.e;
        switch (i2) {
            case 0:
                return saveAsPdfCmd.lambda$showConfirmDialogAndSave$0(mediaItem, mediaItem2);
            default:
                return saveAsPdfCmd.lambda$showConfirmDialogAndSave$0(mediaItem, mediaItem2);
        }
    }
}
