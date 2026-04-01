package E3;

import com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CameraQuickViewLauncher e;
    public final /* synthetic */ long f;
    public final /* synthetic */ String g;

    public /* synthetic */ b(CameraQuickViewLauncher cameraQuickViewLauncher, long j2, String str, int i2) {
        this.d = i2;
        this.e = cameraQuickViewLauncher;
        this.f = j2;
        this.g = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$launchImageQuickView$3(this.f, this.g);
                return;
            default:
                this.e.lambda$loadCameraQuickViewVideoItem$7(this.f, this.g);
                return;
        }
    }
}
