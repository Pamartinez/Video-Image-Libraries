package u7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: u7.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0524e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ContentViewerHolder e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ C0524e(ContentViewerHolder contentViewerHolder, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = contentViewerHolder;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setPhotoView$12(this.f);
                return;
            default:
                this.e.lambda$onImageLoaded$10(this.f);
                return;
        }
    }
}
