package qa;

import com.samsung.android.gallery.module.thumbnail.VideoThumbnailAsyncLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoThumbnailAsyncLoader e;

    public /* synthetic */ d(VideoThumbnailAsyncLoader videoThumbnailAsyncLoader, int i2) {
        this.d = i2;
        this.e = videoThumbnailAsyncLoader;
    }

    public final void run() {
        int i2 = this.d;
        VideoThumbnailAsyncLoader videoThumbnailAsyncLoader = this.e;
        switch (i2) {
            case 0:
                videoThumbnailAsyncLoader.run2ndPhase();
                return;
            case 1:
                videoThumbnailAsyncLoader.lambda$run2ndPhase$0();
                return;
            default:
                videoThumbnailAsyncLoader.lambda$onError$1();
                return;
        }
    }
}
