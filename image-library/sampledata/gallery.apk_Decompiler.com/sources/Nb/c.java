package Nb;

import com.samsung.android.gallery.widget.photoview.PhotoView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PhotoView e;

    public /* synthetic */ c(PhotoView photoView, int i2) {
        this.d = i2;
        this.e = photoView;
    }

    public final void run() {
        int i2 = this.d;
        PhotoView photoView = this.e;
        switch (i2) {
            case 0:
                photoView.lambda$resetRegionDecodingInfo$0();
                return;
            case 1:
                photoView.recycleTileMap();
                return;
            case 2:
                photoView.resetScaleAndCenter();
                return;
            default:
                photoView.refreshCaptureView();
                return;
        }
    }
}
