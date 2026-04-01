package w5;

import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2737a;

    public /* synthetic */ b(int i2) {
        this.f2737a = i2;
    }

    public final void accept(Object obj, Object obj2) {
        ((ClusterResult) obj2).handleOrientationChange(this.f2737a);
    }
}
