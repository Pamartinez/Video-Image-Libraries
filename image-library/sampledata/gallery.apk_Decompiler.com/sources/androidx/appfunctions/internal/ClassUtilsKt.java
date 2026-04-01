package androidx.appfunctions.internal;

import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"findImpl", "T", "", "Ljava/lang/Class;", "prefix", "", "suffix", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "appfunctions"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClassUtilsKt {
    public static final <T> T findImpl(Class<T> cls, String str, String str2) {
        j.e(cls, "<this>");
        j.e(str, "prefix");
        j.e(str2, "suffix");
        String l = cls.getPackageName();
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String substring = canonicalName.substring(l.length() + 1);
            j.d(substring, "substring(...)");
            String B = C0212a.B(str, substring, str2);
            try {
                Class<?> cls2 = Class.forName(l + '.' + B, true, cls.getClassLoader());
                j.c(cls2, "null cannot be cast to non-null type java.lang.Class<T of androidx.appfunctions.internal.ClassUtilsKt.findImpl>");
                T newInstance = cls2.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
                j.b(newInstance);
                return newInstance;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Cannot find implementation for " + cls.getCanonicalName() + ". " + B + " does not exist. Is AppFunction annotation processor correctly configured?", e);
            } catch (IllegalAccessException e7) {
                throw new RuntimeException("Cannot access the constructor " + cls.getCanonicalName(), e7);
            } catch (InstantiationException e8) {
                throw new RuntimeException("Failed to create an instance of " + cls.getCanonicalName(), e8);
            }
        } else {
            throw new IllegalArgumentException("Required value was null.");
        }
    }
}
