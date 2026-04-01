package com.google.android.appfunctions.schema.internal.dependencies;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0102l extends A {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Class<?>, C0102l> defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize = -1;
    protected O unknownFields = O.f;

    public C0102l() {
        this.memoizedHashCode = 0;
    }

    public static C0102l c(Class cls) {
        C0102l lVar = defaultInstanceMap.get(cls);
        if (lVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                lVar = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (lVar != null) {
            return lVar;
        }
        C0102l lVar2 = (C0102l) ((C0102l) W.i(cls)).b(C0101k.zzf);
        if (lVar2 != null) {
            defaultInstanceMap.put(cls, lVar2);
            return lVar2;
        }
        throw new IllegalStateException();
    }

    public static Object d(Method method, C0102l lVar, Object... objArr) {
        try {
            return method.invoke(lVar, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e7) {
            Throwable cause = e7.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public static void g(Class cls, C0102l lVar) {
        lVar.f();
        defaultInstanceMap.put(cls, lVar);
    }

    public final int a(M m) {
        int i2;
        int i7;
        if (e()) {
            if (m == null) {
                i7 = J.f1206c.a(getClass()).b(this);
            } else {
                i7 = m.b(this);
            }
            if (i7 >= 0) {
                return i7;
            }
            StringBuilder sb2 = new StringBuilder(String.valueOf(i7).length() + 42);
            sb2.append("serialized size must be non-negative, was ");
            sb2.append(i7);
            throw new IllegalStateException(sb2.toString());
        }
        int i8 = this.memoizedSerializedSize;
        if ((i8 & Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return i8 & Integer.MAX_VALUE;
        }
        if (m == null) {
            i2 = J.f1206c.a(getClass()).b(this);
        } else {
            i2 = m.b(this);
        }
        h(i2);
        return i2;
    }

    public abstract Object b(C0101k kVar);

    public final boolean e() {
        if ((this.memoizedSerializedSize & Integer.MIN_VALUE) != 0) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return J.f1206c.a(getClass()).g(this, (C0102l) obj);
    }

    public final void f() {
        this.memoizedSerializedSize &= Integer.MAX_VALUE;
    }

    public final void h(int i2) {
        if (i2 >= 0) {
            this.memoizedSerializedSize = i2 | (this.memoizedSerializedSize & Integer.MIN_VALUE);
            return;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(i2).length() + 42);
        sb2.append("serialized size must be non-negative, was ");
        sb2.append(i2);
        throw new IllegalStateException(sb2.toString());
    }

    public final int hashCode() {
        if (e()) {
            return J.f1206c.a(getClass()).c(this);
        }
        if (this.memoizedHashCode == 0) {
            this.memoizedHashCode = J.f1206c.a(getClass()).c(this);
        }
        return this.memoizedHashCode;
    }

    public final String toString() {
        String obj = super.toString();
        char[] cArr = D.f1199a;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("# ");
        sb2.append(obj);
        D.b(this, sb2, 0);
        return sb2.toString();
    }
}
