package gc;

import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DebugSmartCropRectInfo e;

    public /* synthetic */ c(DebugSmartCropRectInfo debugSmartCropRectInfo, int i2) {
        this.d = i2;
        this.e = debugSmartCropRectInfo;
    }

    public final void run() {
        int i2 = this.d;
        DebugSmartCropRectInfo debugSmartCropRectInfo = this.e;
        switch (i2) {
            case 0:
                debugSmartCropRectInfo.lambda$updateThumbnail$10();
                return;
            default:
                debugSmartCropRectInfo.lambda$updateThumbnail$9();
                return;
        }
    }
}
