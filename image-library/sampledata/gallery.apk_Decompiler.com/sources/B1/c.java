package B1;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {
    public static final c b;

    /* renamed from: a  reason: collision with root package name */
    public b f39a;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, B1.c] */
    static {
        ? obj = new Object();
        obj.f39a = null;
        b = obj;
    }

    public static b a(Context context) {
        b bVar;
        c cVar = b;
        synchronized (cVar) {
            try {
                if (cVar.f39a == null) {
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    cVar.f39a = new b(0, context);
                }
                bVar = cVar.f39a;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return bVar;
    }
}
