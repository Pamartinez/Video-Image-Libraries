package rf;

import N2.j;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: rf.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1266p {

    /* renamed from: a  reason: collision with root package name */
    public final C1252b f5068a;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public final C1252b f5069c;
    public final C1265o d;
    public final Method e;

    public C1266p(C1252b bVar, Object obj, C1252b bVar2, C1265o oVar, Class cls) {
        if (bVar == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        } else if (oVar.e == Q.MESSAGE && bVar2 == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        } else {
            this.f5068a = bVar;
            this.b = obj;
            this.f5069c = bVar2;
            this.d = oVar;
            if (r.class.isAssignableFrom(cls)) {
                try {
                    this.e = cls.getMethod("valueOf", new Class[]{Integer.TYPE});
                } catch (NoSuchMethodException e7) {
                    String name = cls.getName();
                    throw new RuntimeException(j.f(new StringBuilder(name.length() + 52), "Generated message class \"", name, "\" missing method \"valueOf\"."), e7);
                }
            } else {
                this.e = null;
            }
        }
    }

    public final Object a(Object obj) {
        if (this.d.e.a() != S.ENUM) {
            return obj;
        }
        try {
            return this.e.invoke((Object) null, new Object[]{(Integer) obj});
        } catch (IllegalAccessException e7) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e7);
        } catch (InvocationTargetException e8) {
            Throwable cause = e8.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public final Object b(Object obj) {
        if (this.d.e.a() == S.ENUM) {
            return Integer.valueOf(((r) obj).a());
        }
        return obj;
    }
}
