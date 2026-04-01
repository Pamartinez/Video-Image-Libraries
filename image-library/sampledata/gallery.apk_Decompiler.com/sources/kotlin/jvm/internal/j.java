package kotlin.jvm.internal;

import c0.C0086a;
import i.C0212a;
import ig.i;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class j {
    public static boolean a(Object obj, Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        if (obj2 == null) {
            return true;
        }
        return false;
    }

    public static void b(Object obj) {
        if (obj == null) {
            NullPointerException nullPointerException = new NullPointerException();
            i(nullPointerException, j.class.getName());
            throw nullPointerException;
        }
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            NullPointerException nullPointerException = new NullPointerException(str);
            i(nullPointerException, j.class.getName());
            throw nullPointerException;
        }
    }

    public static void d(Object obj, String str) {
        if (obj == null) {
            NullPointerException nullPointerException = new NullPointerException(str.concat(" must not be null"));
            i(nullPointerException, j.class.getName());
            throw nullPointerException;
        }
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            Class<j> cls = j.class;
            String name = cls.getName();
            int i2 = 0;
            while (!stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            while (stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            StackTraceElement stackTraceElement = stackTrace[i2];
            StringBuilder q = C0086a.q("Parameter specified as non-null is null: method ", stackTraceElement.getClassName(), ".", stackTraceElement.getMethodName(), ", parameter ");
            q.append(str);
            NullPointerException nullPointerException = new NullPointerException(q.toString());
            i(nullPointerException, cls.getName());
            throw nullPointerException;
        }
    }

    public static int f(int i2, int i7) {
        if (i2 < i7) {
            return -1;
        }
        if (i2 == i7) {
            return 0;
        }
        return 1;
    }

    public static final i g(Object[] objArr) {
        e(objArr, "array");
        return new i(objArr);
    }

    public static void h() {
        throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void i(RuntimeException runtimeException, String str) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        for (int i7 = 0; i7 < length; i7++) {
            if (str.equals(stackTrace[i7].getClassName())) {
                i2 = i7;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i2 + 1, length));
    }

    public static String j(Object obj, String str) {
        return str + obj;
    }

    public static void k(String str) {
        RuntimeException runtimeException = new RuntimeException(C0212a.m("lateinit property ", str, " has not been initialized"));
        i(runtimeException, j.class.getName());
        throw runtimeException;
    }
}
