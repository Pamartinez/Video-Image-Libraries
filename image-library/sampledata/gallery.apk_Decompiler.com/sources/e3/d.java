package E3;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2308a;
    public final /* synthetic */ CameraQuickViewLauncher b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f2309c;

    public /* synthetic */ d(CameraQuickViewLauncher cameraQuickViewLauncher, long j2, int i2) {
        this.f2308a = i2;
        this.b = cameraQuickViewLauncher;
        this.f2309c = j2;
    }

    public final void accept(Object obj, Object obj2) {
        MediaItem mediaItem = (MediaItem) obj;
        Bitmap bitmap = (Bitmap) obj2;
        switch (this.f2308a) {
            case 0:
                this.b.lambda$loadCameraQuickViewVideoItem$6(this.f2309c, mediaItem, bitmap);
                return;
            default:
                this.b.lambda$loadCameraQuickViewItem$4(this.f2309c, mediaItem, bitmap);
                return;
        }
    }
}
