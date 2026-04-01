package D8;

import com.samsung.android.gallery.module.analyticsquery.SasCount;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2791a;
    public final /* synthetic */ SasCount b;

    public /* synthetic */ c(SasCount sasCount, int i2) {
        this.f2791a = i2;
        this.b = sasCount;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2791a;
        String str = (String) obj;
        Integer num = (Integer) obj2;
        SasCount sasCount = this.b;
        switch (i2) {
            case 0:
                sasCount.lambda$logScreenshot$4(str, num);
                return;
            default:
                sasCount.lambda$logPackageStatus$9(str, num);
                return;
        }
    }
}
