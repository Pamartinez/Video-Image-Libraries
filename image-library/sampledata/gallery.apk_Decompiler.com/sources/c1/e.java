package C1;

import A0.l;
import K1.j;
import L1.g;
import android.os.RemoteException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements f {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f104a;
    public final /* synthetic */ j b;

    public /* synthetic */ e(j jVar, int i2) {
        this.f104a = i2;
        this.b = jVar;
    }

    public final int a() {
        switch (this.f104a) {
            case 0:
                return 4;
            default:
                return 5;
        }
    }

    public final void b() {
        switch (this.f104a) {
            case 0:
                l lVar = this.b.f383a;
                lVar.getClass();
                try {
                    g gVar = (g) lVar.f;
                    gVar.d(gVar.c(), 12);
                    return;
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            default:
                l lVar2 = this.b.f383a;
                lVar2.getClass();
                try {
                    g gVar2 = (g) lVar2.f;
                    gVar2.d(gVar2.c(), 3);
                    return;
                } catch (RemoteException e7) {
                    throw new RuntimeException(e7);
                }
        }
    }
}
