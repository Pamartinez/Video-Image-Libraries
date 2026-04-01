package X8;

import com.samsung.android.gallery.module.dataset.chapter.DeployCluster;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DeployCluster f2897a;
    public final /* synthetic */ LayoutInfo b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f2898c;

    public /* synthetic */ c(DeployCluster deployCluster, LayoutInfo layoutInfo, boolean z) {
        this.f2897a = deployCluster;
        this.b = layoutInfo;
        this.f2898c = z;
    }

    public final boolean test(Object obj) {
        return this.f2897a.lambda$handle$3(this.b, this.f2898c, (Boolean) obj);
    }
}
