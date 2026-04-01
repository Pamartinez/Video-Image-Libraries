package E2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class v {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f176a;

    static {
        Object obj;
        Method method = null;
        try {
            obj = Class.forName("sun.misc.SharedSecrets", false, (ClassLoader) null).getMethod("getJavaLangAccess", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            obj = null;
        }
        f176a = obj;
        Class<Throwable> cls = Throwable.class;
        if (obj != null) {
            try {
                Class.forName("sun.misc.JavaLangAccess", false, (ClassLoader) null).getMethod("getStackTraceElement", new Class[]{cls, Integer.TYPE});
            } catch (ThreadDeath e7) {
                throw e7;
            } catch (Throwable unused2) {
            }
        }
        if (obj != null) {
            try {
                method = Class.forName("sun.misc.JavaLangAccess", false, (ClassLoader) null).getMethod("getStackTraceDepth", new Class[]{cls});
            } catch (ThreadDeath e8) {
                throw e8;
            } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused3) {
                return;
            } catch (Throwable unused4) {
            }
            if (method != null) {
                method.invoke(obj, new Object[]{new Throwable()});
            }
        }
    }
}
