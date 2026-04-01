package x0;

import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements z {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2064a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2065c;

    public /* synthetic */ l(int i2, Object obj, Object obj2) {
        this.f2064a = i2;
        this.b = obj;
        this.f2065c = obj2;
    }

    public final void onResult(Object obj) {
        switch (this.f2064a) {
            case 0:
                C0332j jVar = (C0332j) obj;
                HashMap hashMap = n.f2068a;
                hashMap.remove((String) this.b);
                ((AtomicBoolean) this.f2065c).set(true);
                if (hashMap.size() == 0) {
                    n.i();
                    return;
                }
                return;
            case 1:
                Throwable th = (Throwable) obj;
                HashMap hashMap2 = n.f2068a;
                hashMap2.remove((String) this.b);
                ((AtomicBoolean) this.f2065c).set(true);
                if (hashMap2.size() == 0) {
                    n.i();
                    return;
                }
                return;
            default:
                ((RecapTemplate) this.b).lambda$load$0((Consumer) this.f2065c, (C0332j) obj);
                return;
        }
    }
}
