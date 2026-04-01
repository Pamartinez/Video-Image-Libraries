package E3;

import com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher;
import com.samsung.android.gallery.app.ui.list.search.CollectionFragment;
import com.samsung.android.gallery.app.ui.list.search.SearchFragment;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.viewer.QuickCropHelper;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(Object obj, long j2, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((CameraQuickViewLauncher) this.f).lambda$loadCameraQuickViewItem$5(this.e);
                return;
            case 1:
                ((TroubleshootingFragment) this.f).lambda$executeDiagnose$2(this.e);
                return;
            case 2:
                ((CollectionFragment) this.f).lambda$preloadViewPool$2(this.e);
                return;
            case 3:
                ((SearchFragment) this.f).lambda$preloadViewPool$1(this.e);
                return;
            case 4:
                ((AtomicLong) this.f).set(new FilesApi().getSecFileId(this.e));
                return;
            default:
                QuickCropHelper.lambda$isSupported$0((Runnable) this.f, this.e);
                return;
        }
    }
}
