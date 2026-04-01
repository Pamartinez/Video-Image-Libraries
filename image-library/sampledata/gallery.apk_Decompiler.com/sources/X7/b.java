package X7;

import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowViewerHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SlideshowViewerHolder e;

    public /* synthetic */ b(SlideshowViewerHolder slideshowViewerHolder, int i2) {
        this.d = i2;
        this.e = slideshowViewerHolder;
    }

    public final void run() {
        int i2 = this.d;
        SlideshowViewerHolder slideshowViewerHolder = this.e;
        switch (i2) {
            case 0:
                slideshowViewerHolder.lambda$setContentDescription$3();
                return;
            case 1:
                slideshowViewerHolder.lambda$onImageLoaded$1();
                return;
            default:
                slideshowViewerHolder.lambda$onImageLoaded$0();
                return;
        }
    }
}
