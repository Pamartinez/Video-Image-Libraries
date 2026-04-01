package X8;

import com.samsung.android.gallery.module.dataset.chapter.DeployCluster;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2895a;
    public final /* synthetic */ DeployCluster b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ LayoutInfo f2896c;

    public /* synthetic */ b(DeployCluster deployCluster, LayoutInfo layoutInfo, int i2) {
        this.f2895a = i2;
        this.b = deployCluster;
        this.f2896c = layoutInfo;
    }

    public final boolean test(Object obj) {
        switch (this.f2895a) {
            case 0:
                return this.b.lambda$handle$0(this.f2896c, (Boolean) obj);
            case 1:
                return this.b.lambda$handle$1(this.f2896c, (Boolean) obj);
            case 2:
                return this.b.lambda$handle$2(this.f2896c, (Boolean) obj);
            case 3:
                return this.b.lambda$handle$4(this.f2896c, (Boolean) obj);
            default:
                return this.b.lambda$handle$6(this.f2896c, (Boolean) obj);
        }
    }
}
