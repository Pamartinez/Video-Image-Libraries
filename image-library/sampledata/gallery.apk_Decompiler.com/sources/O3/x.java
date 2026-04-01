package O3;

import com.samsung.android.gallery.app.controller.internals.ScanMediaFileCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ScanMediaFileCmd e;
    public final /* synthetic */ MediaItem[] f;

    public /* synthetic */ x(ScanMediaFileCmd scanMediaFileCmd, MediaItem[] mediaItemArr, int i2) {
        this.d = i2;
        this.e = scanMediaFileCmd;
        this.f = mediaItemArr;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onExecute$0(this.f);
                return;
            default:
                this.e.lambda$scanMediaFile$2(this.f);
                return;
        }
    }
}
