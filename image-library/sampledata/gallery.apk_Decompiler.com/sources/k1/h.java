package K1;

import C1.b;
import D1.f;
import H1.g;
import L1.a;
import L1.d;
import L1.e;
import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import o1.C0246a;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f381a = false;
    public static g b = g.LEGACY;

    public static synchronized int a(Context context) {
        synchronized (h.class) {
            try {
                r.c(context, "Context is null");
                Log.d("h", "preferredRenderer: ".concat("null"));
                if (!f381a) {
                    try {
                        e M2 = f.M(context);
                        a e = M2.e();
                        r.b(e);
                        d.d = e;
                        g g = M2.g();
                        if (C0246a.d == null) {
                            r.c(g, "delegate must not be null");
                            C0246a.d = g;
                        }
                        f381a = true;
                        Parcel b5 = M2.b(M2.c(), 9);
                        int readInt = b5.readInt();
                        b5.recycle();
                        if (readInt == 2) {
                            b = g.LATEST;
                        }
                        b bVar = new b(context);
                        Parcel c5 = M2.c();
                        H1.d.d(c5, bVar);
                        c5.writeInt(0);
                        M2.d(c5, 10);
                        Log.d("h", "loadedRenderer: ".concat(String.valueOf(b)));
                    } catch (t1.g e7) {
                        return e7.d;
                    }
                }
            } catch (RemoteException e8) {
                throw new RuntimeException(e8);
            } catch (RemoteException e9) {
                Log.e("h", "Failed to retrieve renderer type or log initialization.", e9);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return 0;
    }
}
