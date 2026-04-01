package e5;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PicturesPinchAnimationManagerV2 e;

    public /* synthetic */ g(PicturesPinchAnimationManagerV2 picturesPinchAnimationManagerV2, int i2) {
        this.d = i2;
        this.e = picturesPinchAnimationManagerV2;
    }

    public final void run() {
        int i2 = this.d;
        PicturesPinchAnimationManagerV2 picturesPinchAnimationManagerV2 = this.e;
        switch (i2) {
            case 0:
                picturesPinchAnimationManagerV2.lambda$setFakeViewLayoutAlpha$3();
                return;
            default:
                picturesPinchAnimationManagerV2.lambda$startYearOrMonthForViewingClickedAnimation$4();
                return;
        }
    }
}
