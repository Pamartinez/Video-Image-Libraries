package J0;

import android.util.Log;
import java.util.HashSet;
import x0.C0323a;
import x0.C0326d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public static final a f353a = new Object();

    public static void a() {
        f353a.getClass();
        C0323a aVar = C0326d.f2049a;
    }

    public static void b(String str) {
        f353a.getClass();
        HashSet hashSet = a.f352a;
        if (!hashSet.contains(str)) {
            Log.w("LOTTIE", str, (Throwable) null);
            hashSet.add(str);
        }
    }

    public static void c(String str, Throwable th) {
        f353a.getClass();
        HashSet hashSet = a.f352a;
        if (!hashSet.contains(str)) {
            Log.w("LOTTIE", str, th);
            hashSet.add(str);
        }
    }
}
