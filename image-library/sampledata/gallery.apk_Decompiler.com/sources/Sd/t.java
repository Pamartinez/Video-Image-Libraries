package Sd;

import D0.e;
import android.content.Context;
import java.io.Closeable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements Closeable {
    public final /* synthetic */ int d;
    public final /* synthetic */ e e;
    public final /* synthetic */ u f;

    public /* synthetic */ t(e eVar, u uVar, int i2) {
        this.d = i2;
        this.e = eVar;
        this.f = uVar;
    }

    public final void close() {
        switch (this.d) {
            case 0:
                e eVar = this.e;
                try {
                    ((Context) eVar.e).unregisterReceiver(this.f);
                    return;
                } catch (Throwable unused) {
                    return;
                }
            default:
                e eVar2 = this.e;
                try {
                    ((Context) eVar2.e).unregisterReceiver(this.f);
                    return;
                } catch (Throwable unused2) {
                    return;
                }
        }
    }
}
