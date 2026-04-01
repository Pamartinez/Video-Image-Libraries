package b3;

import A0.l;
import He.F;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.CountDownLatch;
import og.k;

/* renamed from: b3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0083a implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1049a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0083a(int i2, Object obj) {
        this.f1049a = i2;
        this.b = obj;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        switch (this.f1049a) {
            case 0:
                k.m("AsConnectionManager", "onServiceConnected", new Object[0]);
                ((E2.k) this.b).f(1, componentName, iBinder);
                return;
            default:
                F.K("LiveEffectServiceConnector", "onServiceConnected()");
                l lVar = (l) this.b;
                lVar.e = iBinder;
                ((CountDownLatch) lVar.f).countDown();
                return;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        switch (this.f1049a) {
            case 0:
                k.m("AsConnectionManager", "onServiceDisconnected " + componentName, new Object[0]);
                ((E2.k) this.b).f(2, (ComponentName) null, (IBinder) null);
                return;
            default:
                F.K("LiveEffectServiceConnector", "onServiceDisconnected()");
                ((l) this.b).e = null;
                return;
        }
    }
}
