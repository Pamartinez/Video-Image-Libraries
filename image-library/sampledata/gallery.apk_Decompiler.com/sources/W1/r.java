package w1;

import N2.j;
import android.os.Handler;
import android.os.Looper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class r {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f2014a = new Object();
    public static boolean b;

    /* renamed from: c  reason: collision with root package name */
    public static int f2015c;

    public static void a(Handler handler) {
        String str;
        Looper myLooper = Looper.myLooper();
        if (myLooper != handler.getLooper()) {
            if (myLooper != null) {
                str = myLooper.getThread().getName();
            } else {
                str = "null current looper";
            }
            throw new IllegalStateException(j.d("Must be called on ", handler.getLooper().getThread().getName(), " thread, but got ", str, "."));
        }
    }

    public static void b(Object obj) {
        if (obj == null) {
            throw new NullPointerException("null reference");
        }
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static boolean d(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }
}
