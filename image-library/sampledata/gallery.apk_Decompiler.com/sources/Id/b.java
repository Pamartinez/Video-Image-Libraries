package Id;

import D1.f;
import Kd.a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.d;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends f {
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ c f3457h;

    public b(c cVar, int i2) {
        this.f3457h = cVar;
        this.g = i2;
    }

    public final void Q(String str, String str2, String str3) {
        c cVar = this.f3457h;
        a aVar = cVar.f4195c;
        long parseLong = Long.parseLong(str);
        com.samsung.context.sdk.samsunganalytics.internal.sender.b bVar = com.samsung.context.sdk.samsunganalytics.internal.sender.b.DEVICE;
        if (!str3.equals(bVar.a())) {
            bVar = com.samsung.context.sdk.samsunganalytics.internal.sender.b.UIX;
        }
        aVar.getClass();
        aVar.d(new d(parseLong, str2, bVar));
        k.W(cVar.f4194a, this.g, str2.getBytes().length * -1);
    }
}
