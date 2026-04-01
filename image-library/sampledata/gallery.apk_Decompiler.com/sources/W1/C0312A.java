package w1;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/* renamed from: w1.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0312A implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0313B f1991a;

    public /* synthetic */ C0312A(C0313B b) {
        this.f1991a = b;
    }

    public final boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 0) {
            synchronized (this.f1991a.f1994a) {
                try {
                    y yVar = (y) message.obj;
                    z zVar = (z) this.f1991a.f1994a.get(yVar);
                    if (zVar != null && zVar.f2022a.isEmpty()) {
                        if (zVar.f2023c) {
                            zVar.g.f1995c.removeMessages(1, zVar.e);
                            C0313B b = zVar.g;
                            b.d.a(b.b, zVar);
                            zVar.f2023c = false;
                            zVar.b = 2;
                        }
                        this.f1991a.f1994a.remove(yVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        } else if (i2 != 1) {
            return false;
        } else {
            synchronized (this.f1991a.f1994a) {
                try {
                    y yVar2 = (y) message.obj;
                    z zVar2 = (z) this.f1991a.f1994a.get(yVar2);
                    if (zVar2 != null && zVar2.b == 3) {
                        Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback ".concat(String.valueOf(yVar2)), new Exception());
                        ComponentName componentName = zVar2.f;
                        if (componentName == null) {
                            yVar2.getClass();
                            componentName = null;
                        }
                        if (componentName == null) {
                            String str = yVar2.b;
                            r.b(str);
                            componentName = new ComponentName(str, "unknown");
                        }
                        zVar2.onServiceDisconnected(componentName);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return true;
        }
    }
}
