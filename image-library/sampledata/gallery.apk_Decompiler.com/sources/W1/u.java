package w1;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final int f2018a;
    public final /* synthetic */ a b;

    public u(a aVar, int i2) {
        this.b = aVar;
        this.f2018a = i2;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        q qVar;
        int i2;
        int i7;
        if (iBinder == null) {
            a aVar = this.b;
            synchronized (aVar.f) {
                i2 = aVar.m;
            }
            if (i2 == 3) {
                aVar.t = true;
                i7 = 5;
            } else {
                i7 = 4;
            }
            s sVar = aVar.e;
            sVar.sendMessage(sVar.obtainMessage(i7, aVar.v.get(), 16));
            return;
        }
        synchronized (this.b.g) {
            try {
                a aVar2 = this.b;
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof q)) {
                    qVar = new q(iBinder);
                } else {
                    qVar = (q) queryLocalInterface;
                }
                aVar2.f1340h = qVar;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        a aVar3 = this.b;
        int i8 = this.f2018a;
        w wVar = new w(aVar3, 0);
        s sVar2 = aVar3.e;
        sVar2.sendMessage(sVar2.obtainMessage(7, i8, -1, wVar));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        a aVar;
        synchronized (this.b.g) {
            aVar = this.b;
            aVar.f1340h = null;
        }
        int i2 = this.f2018a;
        s sVar = aVar.e;
        sVar.sendMessage(sVar.obtainMessage(6, i2, 1));
    }
}
