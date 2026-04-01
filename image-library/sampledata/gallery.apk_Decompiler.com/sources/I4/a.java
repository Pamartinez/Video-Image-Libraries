package I4;

import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumCluster;
import com.samsung.android.gallery.module.data.MxClusterItem;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MxAlbumCluster e;
    public final /* synthetic */ AtomicInteger f;

    public /* synthetic */ a(MxAlbumCluster mxAlbumCluster, AtomicInteger atomicInteger, int i2) {
        this.d = i2;
        this.e = mxAlbumCluster;
        this.f = atomicInteger;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$getViewCount$2(this.f, (MxClusterItem) obj);
                return;
            default:
                this.e.lambda$setDividerTypePositionMap$3(this.f, (MxClusterItem) obj);
                return;
        }
    }
}
