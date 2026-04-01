package Qb;

import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PreviewHdrVideo e;

    public /* synthetic */ b(PreviewHdrVideo previewHdrVideo, int i2) {
        this.d = i2;
        this.e = previewHdrVideo;
    }

    public final void run() {
        int i2 = this.d;
        PreviewHdrVideo previewHdrVideo = this.e;
        switch (i2) {
            case 0:
                previewHdrVideo.lambda$onVideoInfo$4();
                return;
            case 1:
                previewHdrVideo.lambda$onCompletion$7();
                return;
            default:
                previewHdrVideo.lambda$onPrepared$5();
                return;
        }
    }
}
