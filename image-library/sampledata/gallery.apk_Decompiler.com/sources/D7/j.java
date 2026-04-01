package D7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.RemasterGifImageLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemasterGifImageLoader e;

    public /* synthetic */ j(RemasterGifImageLoader remasterGifImageLoader, int i2) {
        this.d = i2;
        this.e = remasterGifImageLoader;
    }

    public final void run() {
        int i2 = this.d;
        RemasterGifImageLoader remasterGifImageLoader = this.e;
        switch (i2) {
            case 0:
                remasterGifImageLoader.lambda$releaseGif$4();
                return;
            case 1:
                remasterGifImageLoader.lambda$playGif$2();
                return;
            default:
                remasterGifImageLoader.lambda$stopGif$3();
                return;
        }
    }
}
