package W7;

import com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenViewerHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SingleTakenViewerHolder e;

    public /* synthetic */ b(SingleTakenViewerHolder singleTakenViewerHolder, int i2) {
        this.d = i2;
        this.e = singleTakenViewerHolder;
    }

    public final void run() {
        int i2 = this.d;
        SingleTakenViewerHolder singleTakenViewerHolder = this.e;
        switch (i2) {
            case 0:
                singleTakenViewerHolder.lambda$onVideoStarted$1();
                return;
            case 1:
                singleTakenViewerHolder.lambda$onBottomSheetSlide$7();
                return;
            case 2:
                singleTakenViewerHolder.lambda$attachView$5();
                return;
            case 3:
                singleTakenViewerHolder.lambda$onImageLoaded$0();
                return;
            default:
                singleTakenViewerHolder.lambda$onTransitionEnd$6();
                return;
        }
    }
}
