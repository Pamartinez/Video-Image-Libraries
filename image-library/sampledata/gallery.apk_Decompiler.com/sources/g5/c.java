package g5;

import com.samsung.android.gallery.app.ui.list.reorder.ReorderItemController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureProcessingHandler;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ Object g;

    public /* synthetic */ c(Object obj, float f5, float f8, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = f5;
        this.f = f8;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((ReorderItemController) this.g).lambda$setInitialPosition$1(this.e, this.f);
                return;
            case 1:
                ((MediaPlayerDelegate) this.g).lambda$setVolume$11(this.e, this.f);
                return;
            case 2:
                ((ObjectCaptureHandler) this.g).lambda$showPasteIfExist$9(this.e, this.f);
                return;
            default:
                ((ObjectCaptureProcessingHandler) this.g).lambda$onProgressStart$4(this.e, this.f);
                return;
        }
    }
}
