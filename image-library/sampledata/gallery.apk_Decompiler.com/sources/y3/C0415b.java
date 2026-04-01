package Y3;

import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.function.BiConsumer;

/* renamed from: Y3.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0415b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2469a;
    public final /* synthetic */ boolean b;

    public /* synthetic */ C0415b(boolean z, int i2) {
        this.f2469a = i2;
        this.b = z;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2469a) {
            case 0:
                ((Blackboard) obj2).postBroadcastEvent(EventMessage.obtain(1076, r2 ? 1 : 0, 0, (Object) null));
                return;
            case 1:
                BlackboardUtils.refreshDataOnResume((Blackboard) obj2, this.b);
                return;
            default:
                ((ClusterResult) obj2).setEnabled(this.b);
                return;
        }
    }
}
