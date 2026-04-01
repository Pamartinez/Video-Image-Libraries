package u7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;

/* renamed from: u7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0521b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ContentViewerHolder e;

    public /* synthetic */ C0521b(ContentViewerHolder contentViewerHolder, int i2) {
        this.d = i2;
        this.e = contentViewerHolder;
    }

    public final void run() {
        int i2 = this.d;
        ContentViewerHolder contentViewerHolder = this.e;
        switch (i2) {
            case 0:
                contentViewerHolder.lambda$new$8();
                return;
            default:
                contentViewerHolder.lambda$setPhotoView$11();
                return;
        }
    }
}
