package E3;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.YearQueryCluster;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2311a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2312c;

    public /* synthetic */ f(Object obj, int i2, int i7) {
        this.f2311a = i7;
        this.f2312c = obj;
        this.b = i2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2311a) {
            case 0:
                ((CameraQuickViewLauncher) this.f2312c).lambda$launchVideoQuickView$1(this.b, (MediaItem) obj, (Bitmap) obj2);
                return;
            default:
                ((YearQueryCluster) this.f2312c).lambda$getMaxScroll$4(this.b, (Integer) obj, (ArrayList) obj2);
                return;
        }
    }
}
