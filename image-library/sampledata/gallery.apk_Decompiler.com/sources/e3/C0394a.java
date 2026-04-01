package E3;

import com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher;
import java.util.concurrent.CountDownLatch;

/* renamed from: E3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0394a implements Runnable {
    public final /* synthetic */ CameraQuickViewLauncher d;
    public final /* synthetic */ long e;
    public final /* synthetic */ String f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ CountDownLatch f2307h;

    public /* synthetic */ C0394a(CameraQuickViewLauncher cameraQuickViewLauncher, long j2, String str, int i2, CountDownLatch countDownLatch) {
        this.d = cameraQuickViewLauncher;
        this.e = j2;
        this.f = str;
        this.g = i2;
        this.f2307h = countDownLatch;
    }

    public final void run() {
        this.d.lambda$launchVideoQuickView$2(this.e, this.f, this.g, this.f2307h);
    }
}
