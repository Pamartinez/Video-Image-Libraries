package t1;

import E1.e;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends e {

    /* renamed from: a  reason: collision with root package name */
    public final Context f1926a;
    public final /* synthetic */ C0279d b;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public k(t1.C0279d r2, android.content.Context r3) {
        /*
            r1 = this;
            r1.b = r2
            android.os.Looper r2 = android.os.Looper.myLooper()
            if (r2 != 0) goto L_0x000d
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            goto L_0x0011
        L_0x000d:
            android.os.Looper r2 = android.os.Looper.myLooper()
        L_0x0011:
            r0 = 0
            r1.<init>(r2, r0)
            android.content.Context r2 = r3.getApplicationContext()
            r1.f1926a = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.k.<init>(t1.d, android.content.Context):void");
    }

    public final void handleMessage(Message message) {
        PendingIntent pendingIntent;
        int i2 = message.what;
        if (i2 != 1) {
            Log.w("GoogleApiAvailability", "Don't know how to handle this message: " + i2);
            return;
        }
        int i7 = f.f1923a;
        C0279d dVar = this.b;
        Context context = this.f1926a;
        int b5 = dVar.b(context, i7);
        AtomicBoolean atomicBoolean = h.f1924a;
        if (b5 == 1 || b5 == 2 || b5 == 3 || b5 == 9) {
            Intent a7 = dVar.a(context, b5, "n");
            if (a7 == null) {
                pendingIntent = null;
            } else {
                pendingIntent = PendingIntent.getActivity(context, 0, a7, 201326592);
            }
            dVar.f(context, b5, pendingIntent);
        }
    }
}
