package D7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.GifImageLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GifImageLoader e;

    public /* synthetic */ c(GifImageLoader gifImageLoader, int i2) {
        this.d = i2;
        this.e = gifImageLoader;
    }

    public final void run() {
        int i2 = this.d;
        GifImageLoader gifImageLoader = this.e;
        switch (i2) {
            case 0:
                gifImageLoader.lambda$releaseGif$4();
                return;
            case 1:
                gifImageLoader.lambda$playGif$2();
                return;
            case 2:
                gifImageLoader.lambda$stopGif$3();
                return;
            default:
                gifImageLoader.lambda$onAnimationFrameStart$5();
                return;
        }
    }
}
