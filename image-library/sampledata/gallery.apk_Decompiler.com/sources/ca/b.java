package Ca;

import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PanoramaActivity e;

    public /* synthetic */ b(PanoramaActivity panoramaActivity, int i2) {
        this.d = i2;
        this.e = panoramaActivity;
    }

    public final void run() {
        int i2 = this.d;
        PanoramaActivity panoramaActivity = this.e;
        switch (i2) {
            case 0:
                panoramaActivity.lambda$onCreate$1();
                return;
            case 1:
                panoramaActivity.lambda$new$8();
                return;
            case 2:
                panoramaActivity.lambda$showInitProgress$6();
                return;
            default:
                panoramaActivity.onBackPressed();
                return;
        }
    }
}
