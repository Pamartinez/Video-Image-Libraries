package e5;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV3;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PicturesPinchAnimationManagerV3 e;

    public /* synthetic */ h(PicturesPinchAnimationManagerV3 picturesPinchAnimationManagerV3, int i2) {
        this.d = i2;
        this.e = picturesPinchAnimationManagerV3;
    }

    public final void run() {
        int i2 = this.d;
        PicturesPinchAnimationManagerV3 picturesPinchAnimationManagerV3 = this.e;
        switch (i2) {
            case 0:
                picturesPinchAnimationManagerV3.lambda$addMonthXsAnimators$5();
                return;
            case 1:
                picturesPinchAnimationManagerV3.lambda$addItemAnimators$3();
                return;
            case 2:
                picturesPinchAnimationManagerV3.lambda$addStickDividerAnimators$4();
                return;
            default:
                picturesPinchAnimationManagerV3.lambda$addYearAnimators$6();
                return;
        }
    }
}
