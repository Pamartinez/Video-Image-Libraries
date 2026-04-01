package i1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Iterator f1782a = Collections.emptyIterator();

    public static Object a() {
        Constructor<Z0.a> constructor;
        Class<Z0.a> cls = Z0.a.class;
        try {
            constructor = cls.getDeclaredConstructor((Class[]) null);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                throw new IllegalArgumentException("Default constructor for " + cls.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: cannot instantiate type");
            } else if (constructor != null) {
                try {
                    return constructor.newInstance((Object[]) null);
                } catch (Exception e) {
                    b(e, "Failed to instantiate class " + cls.getName() + ", problem: " + e.getMessage());
                    throw null;
                }
            } else {
                throw new IllegalArgumentException("Class " + cls.getName() + " has no default (no arg) constructor");
            }
        } catch (NoSuchMethodException unused) {
            constructor = null;
        } catch (Exception e7) {
            b(e7, "Failed to find default constructor of class " + cls.getName() + ", problem: " + e7.getMessage());
            throw null;
        }
    }

    public static void b(Exception exc, String str) {
        Throwable th;
        while (true) {
            Throwable cause = th.getCause();
            th = exc;
            if (cause == null) {
                break;
            }
            th = th.getCause();
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (!(th instanceof Error)) {
            throw new IllegalArgumentException(str, th);
        } else {
            throw ((Error) th);
        }
    }
}
