package b4;

import com.samsung.android.gallery.app.remote.v2.PresentationController;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* renamed from: b4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0423c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ PresentationController e;

    public /* synthetic */ C0423c(PresentationController presentationController, int i2) {
        this.d = i2;
        this.e = presentationController;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        PresentationController presentationController = this.e;
        MediaItem mediaItem = (MediaItem) obj;
        switch (i2) {
            case 0:
                presentationController.internalVideoPlay(mediaItem);
                return;
            default:
                presentationController.internalGifPlay(mediaItem);
                return;
        }
    }
}
