package Ca;

import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ PanoramaActivity e;

    public /* synthetic */ a(PanoramaActivity panoramaActivity, int i2) {
        this.d = i2;
        this.e = panoramaActivity;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        PanoramaActivity panoramaActivity = this.e;
        switch (i2) {
            case 0:
                panoramaActivity.lambda$onBackPressed$7((Boolean) obj);
                return;
            default:
                panoramaActivity.lambda$onCreate$0((FoldStateManager) obj);
                return;
        }
    }
}
