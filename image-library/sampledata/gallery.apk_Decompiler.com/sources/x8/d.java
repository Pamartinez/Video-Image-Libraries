package X8;

import com.samsung.android.gallery.module.dataset.chapter.DeployCluster;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DeployCluster f2899a;
    public final /* synthetic */ LayoutInfo b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ LayoutInfo f2900c;

    public /* synthetic */ d(DeployCluster deployCluster, LayoutInfo layoutInfo, LayoutInfo layoutInfo2) {
        this.f2899a = deployCluster;
        this.b = layoutInfo;
        this.f2900c = layoutInfo2;
    }

    public final boolean test(Object obj) {
        return this.f2899a.lambda$handle$5(this.b, this.f2900c, (Boolean) obj);
    }
}
