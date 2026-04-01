package fe;

import G0.a;
import a6.C0418a;
import android.os.IBinder;
import ge.F1;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends o {

    /* renamed from: c  reason: collision with root package name */
    public final F1 f4360c;

    public m(IBinder iBinder, Executor executor) {
        super(iBinder);
        this.f4360c = new F1(executor);
    }

    public final void a(int i2, a aVar) {
        this.f4360c.execute(new C0418a((Object) this, i2, (Object) aVar.c(), 5));
        aVar.c();
        aVar.e = null;
    }
}
