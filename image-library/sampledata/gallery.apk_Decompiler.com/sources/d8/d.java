package D8;

import com.samsung.android.gallery.module.analyticsquery.SasCount;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2792a;
    public final /* synthetic */ SasCount b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ HashMap f2793c;

    public /* synthetic */ d(SasCount sasCount, HashMap hashMap, int i2) {
        this.f2792a = i2;
        this.b = sasCount;
        this.f2793c = hashMap;
    }

    public final void accept(Object obj, Object obj2) {
        String str = (String) obj;
        Integer num = (Integer) obj2;
        switch (this.f2792a) {
            case 0:
                this.b.lambda$logCluster$2(this.f2793c, str, num);
                return;
            case 1:
                this.b.lambda$logSearchAnalysisStatus$6(this.f2793c, str, num);
                return;
            case 2:
                this.b.lambda$logShotMode$1(this.f2793c, str, num);
                return;
            default:
                this.b.lambda$logDocument$5(this.f2793c, str, num);
                return;
        }
    }
}
