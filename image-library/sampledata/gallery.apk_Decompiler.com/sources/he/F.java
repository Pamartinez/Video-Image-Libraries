package He;

import Af.m;
import Af.o;
import Af.p;
import Af.r;
import B0.a;
import Dd.C0730a;
import Df.E;
import Ke.C0800s;
import Ke.E0;
import Ke.n0;
import L1.d;
import Le.g;
import Sf.k;
import Sf.n;
import Ve.b;
import Xf.e;
import Xf.h;
import Xf.i;
import a.C0068a;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SharedMemory;
import android.util.Log;
import c0.C0086a;
import cf.c;
import com.adobe.internal.xmp.XMPConst;
import gf.C1071b;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.j;
import lf.e0;
import me.q;
import ne.C1194l;
import ne.C1196n;
import nf.C1211h;
import pf.f;
import qf.C1235b;
import t1.C0280e;
import ue.C1315a;
import ve.C1319a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class F {
    public static SharedMemory A(Bitmap bitmap) {
        Bitmap bitmap2;
        Bitmap.Config config = bitmap.getConfig();
        Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
        if (config == config2) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config2);
            new Canvas(bitmap2).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        int byteCount = bitmap2.getByteCount();
        ByteBuffer allocate = ByteBuffer.allocate(byteCount);
        bitmap2.copyPixelsToBuffer(allocate);
        allocate.rewind();
        SharedMemory create = SharedMemory.create("bitmap_shared_memory", byteCount);
        ByteBuffer mapReadWrite = create.mapReadWrite();
        mapReadWrite.put(allocate);
        SharedMemory.unmap(mapReadWrite);
        if (bitmap2 != bitmap) {
            bitmap2.recycle();
        }
        return create;
    }

    public static SharedMemory B(String str, byte[] bArr) {
        try {
            SharedMemory create = SharedMemory.create(str, bArr.length);
            ByteBuffer mapReadWrite = create.mapReadWrite();
            mapReadWrite.put(bArr);
            SharedMemory.unmap(mapReadWrite);
            return create;
        } catch (Exception e) {
            throw new C0730a("Failed to create shared memory", e);
        }
    }

    public static void C(String str, String str2) {
        Log.d("LE-".concat(str), str2);
    }

    public static void D(String str, String str2) {
        Log.e("LE-".concat(str), str2);
    }

    public static final b E(B1.b bVar, C1235b bVar2, f fVar) {
        j.e(bVar, "<this>");
        j.e(bVar2, "classId");
        j.e(fVar, "jvmMetadataVersion");
        B1.b d = bVar.d(bVar2, fVar);
        if (d != null) {
            return (b) d.e;
        }
        return null;
    }

    public static /* synthetic */ Collection F(r rVar, Af.f fVar, int i2) {
        if ((i2 & 1) != 0) {
            fVar = Af.f.m;
        }
        p.f3319a.getClass();
        return rVar.d(fVar, m.e);
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [me.f, java.lang.Object] */
    public static final Field G(t tVar) {
        j.e(tVar, "<this>");
        n0 c5 = E0.c(tVar);
        if (c5 != null) {
            return (Field) c5.n.getValue();
        }
        return null;
    }

    public static final Method H(C0751g gVar) {
        Member member;
        g g;
        j.e(gVar, "<this>");
        C0800s a7 = E0.a(gVar);
        if (a7 == null || (g = a7.g()) == null) {
            member = null;
        } else {
            member = g.b();
        }
        if (member instanceof Method) {
            return (Method) member;
        }
        return null;
    }

    public static final Type I(x xVar) {
        y yVar = xVar.f3422a;
        if (yVar == null) {
            return G.f;
        }
        u uVar = xVar.b;
        j.b(uVar);
        int i2 = D.f3419a[yVar.ordinal()];
        if (i2 == 1) {
            return new G((Type) null, v(uVar, true));
        }
        if (i2 == 2) {
            return v(uVar, true);
        }
        if (i2 == 3) {
            return new G(v(uVar, true), (Type) null);
        }
        throw new RuntimeException();
    }

    public static long J(double d) {
        i("not a normal value", L(d));
        int exponent = Math.getExponent(d);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d) & 4503599627370495L;
        if (exponent == -1023) {
            return doubleToRawLongBits << 1;
        }
        return doubleToRawLongBits | 4503599627370496L;
    }

    public static void K(String str, String str2) {
        Log.i("LE-".concat(str), str2);
    }

    public static boolean L(double d) {
        if (Math.getExponent(d) <= 1023) {
            return true;
        }
        return false;
    }

    public static float M(float f, float f5, float f8) {
        return (f8 * f5) + ((1.0f - f8) * f);
    }

    public static final Qf.f N(ArrayList arrayList) {
        Qf.f fVar = new Qf.f();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            p pVar = (p) next;
            if (!(pVar == null || pVar == o.b)) {
                fVar.add(next);
            }
        }
        return fVar;
    }

    public static final c O(a aVar, C1071b bVar) {
        j.e(aVar, "<this>");
        j.e(bVar, "annotationsOwner");
        return new c(aVar, bVar, false);
    }

    public static String P(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String stringWriter2 = stringWriter.toString();
        j.d(stringWriter2, "toString(...)");
        return stringWriter2;
    }

    public static final q Q(String str) {
        int i2;
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i7 = 0;
        char charAt = str.charAt(0);
        if (j.f(charAt, 48) < 0) {
            i2 = 1;
            if (length == 1 || charAt != '+') {
                return null;
            }
        } else {
            i2 = 0;
        }
        int i8 = 119304647;
        while (i2 < length) {
            int digit = Character.digit(str.charAt(i2), 10);
            if (digit < 0) {
                return null;
            }
            if (Integer.compareUnsigned(i7, i8) > 0) {
                if (i8 != 119304647) {
                    return null;
                }
                i8 = Integer.divideUnsigned(-1, 10);
                if (Integer.compareUnsigned(i7, i8) > 0) {
                    return null;
                }
            }
            int i10 = i7 * 10;
            int i11 = digit + i10;
            if (Integer.compareUnsigned(i11, i10) < 0) {
                return null;
            }
            i2++;
            i7 = i11;
        }
        return new q(i7);
    }

    public static final Class R(ClassLoader classLoader, String str) {
        j.e(str, "fqName");
        try {
            return Class.forName(str, false, classLoader);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static Object a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static void b(Parcel parcel, Parcelable parcelable) {
        if (parcelable != null) {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public static e c(int i2, Xf.a aVar, int i7) {
        if ((i7 & 1) != 0) {
            i2 = 0;
        }
        if ((i7 & 2) != 0) {
            aVar = Xf.a.SUSPEND;
        }
        if (i2 != -2) {
            if (i2 != -1) {
                if (i2 != 0) {
                    if (i2 == Integer.MAX_VALUE) {
                        return new e(Integer.MAX_VALUE);
                    }
                    if (aVar == Xf.a.SUSPEND) {
                        return new e(i2);
                    }
                    return new Xf.o(i2, aVar);
                } else if (aVar == Xf.a.SUSPEND) {
                    return new e(0);
                } else {
                    return new Xf.o(1, aVar);
                }
            } else if (aVar == Xf.a.SUSPEND) {
                return new Xf.o(1, Xf.a.DROP_OLDEST);
            } else {
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
            }
        } else if (aVar != Xf.a.SUSPEND) {
            return new Xf.o(1, aVar);
        } else {
            i.b.getClass();
            return new e(h.b);
        }
    }

    public static final String d(Type type) {
        String str;
        if (!(type instanceof Class)) {
            return type.toString();
        }
        Class cls = (Class) type;
        if (!cls.isArray()) {
            return cls.getName();
        }
        k s0 = n.s0(E.d, type);
        StringBuilder sb2 = new StringBuilder();
        Iterator it = s0.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = it.next();
            }
            sb2.append(((Class) next).getName());
            int o0 = n.o0(s0);
            if (o0 >= 0) {
                if (o0 != 0) {
                    str = XMPConst.ARRAY_ITEM_NAME;
                    int i2 = 1;
                    if (o0 != 1) {
                        StringBuilder sb3 = new StringBuilder(2 * o0);
                        if (1 <= o0) {
                            while (true) {
                                sb3.append(str);
                                if (i2 == o0) {
                                    break;
                                }
                                i2++;
                            }
                        }
                        str = sb3.toString();
                        j.b(str);
                    }
                } else {
                    str = "";
                }
                sb2.append(str);
                return sb2.toString();
            }
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + o0 + '.').toString());
        }
        throw new NoSuchElementException("Sequence is empty.");
    }

    public static void e(Throwable th, Throwable th2) {
        j.e(th, "<this>");
        j.e(th2, "exception");
        if (th != th2) {
            Integer num = C1319a.f5156a;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = C1315a.f5145a;
            if (method != null) {
                method.invoke(th, new Object[]{th2});
            }
        }
    }

    public static String f(int i2, int i7, String str) {
        if (i2 < 0) {
            return d.r("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i7 >= 0) {
            return d.r("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i7));
        }
        throw new IllegalArgumentException(C0086a.i(i7, "negative size: "));
    }

    public static void g(int i2, String str, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(d.r(str, Integer.valueOf(i2)));
        }
    }

    public static void h(String str, Object obj, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(d.r(str, obj));
        }
    }

    public static void i(String str, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void j(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void k(boolean z, String str, long j2) {
        if (!z) {
            throw new IllegalArgumentException(d.r(str, Long.valueOf(j2)));
        }
    }

    public static void l(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(d.r(str, obj, obj2));
        }
    }

    public static void m(int i2, int i7) {
        String str;
        if (i2 < 0 || i2 >= i7) {
            if (i2 < 0) {
                str = d.r("%s (%s) must not be negative", "index", Integer.valueOf(i2));
            } else if (i7 >= 0) {
                str = d.r("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i2), Integer.valueOf(i7));
            } else {
                throw new IllegalArgumentException(C0086a.i(i7, "negative size: "));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    public static void n(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void o(int i2, int i7) {
        if (i2 < 0 || i2 > i7) {
            throw new IndexOutOfBoundsException(f(i2, i7, "index"));
        }
    }

    public static void p(int i2, int i7, int i8) {
        String str;
        if (i2 < 0 || i7 < i2 || i7 > i8) {
            if (i2 < 0 || i2 > i8) {
                str = f(i2, i8, "start index");
            } else if (i7 < 0 || i7 > i8) {
                str = f(i7, i8, "end index");
            } else {
                str = d.r("end index (%s) must not be less than start index (%s)", Integer.valueOf(i7), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    public static void q(String str, Object obj, boolean z) {
        if (!z) {
            throw new IllegalStateException(d.r(str, obj));
        }
    }

    public static void r(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void s(boolean z, Object obj, fe.j jVar) {
        if (!z) {
            throw new IllegalStateException(d.r("%s -> %s", obj, jVar));
        }
    }

    public static void t(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(str));
        }
    }

    public static final void u(AutoCloseable autoCloseable, Throwable th) {
        boolean isTerminated;
        if (autoCloseable == null) {
            return;
        }
        if (th != null) {
            try {
                C0280e.f(autoCloseable);
            } catch (Throwable th2) {
                e(th, th2);
            }
        } else if (autoCloseable instanceof AutoCloseable) {
            autoCloseable.close();
        } else if (autoCloseable instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) autoCloseable;
            if (executorService != ForkJoinPool.commonPool() && !(isTerminated = executorService.isTerminated())) {
                executorService.shutdown();
                boolean z = false;
                while (!isTerminated) {
                    try {
                        isTerminated = executorService.awaitTermination(1, TimeUnit.DAYS);
                    } catch (InterruptedException unused) {
                        if (!z) {
                            executorService.shutdownNow();
                            z = true;
                        }
                    }
                }
                if (z) {
                    Thread.currentThread().interrupt();
                }
            }
        } else if (autoCloseable instanceof TypedArray) {
            ((TypedArray) autoCloseable).recycle();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static final Type v(u uVar, boolean z) {
        Class cls;
        int i2;
        C0749e a7 = uVar.a();
        if (a7 instanceof v) {
            return new C((v) a7);
        }
        if (a7 instanceof C0748d) {
            C0748d dVar = (C0748d) a7;
            if (z) {
                cls = C0068a.B(dVar);
            } else {
                cls = C0068a.A(dVar);
            }
            List c5 = uVar.c();
            if (c5.isEmpty()) {
                return cls;
            }
            if (!cls.isArray()) {
                return z(cls, c5);
            }
            if (cls.getComponentType().isPrimitive()) {
                return cls;
            }
            x xVar = (x) C1194l.d1(c5);
            if (xVar != null) {
                y yVar = xVar.f3422a;
                u uVar2 = xVar.b;
                if (yVar == null) {
                    i2 = -1;
                } else {
                    i2 = D.f3419a[yVar.ordinal()];
                }
                if (i2 == -1 || i2 == 1) {
                    return cls;
                }
                if (i2 == 2 || i2 == 3) {
                    j.b(uVar2);
                    Type v = v(uVar2, false);
                    if (v instanceof Class) {
                        return cls;
                    }
                    return new C0745a(v);
                }
                throw new RuntimeException();
            }
            throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + uVar);
        }
        throw new UnsupportedOperationException("Unsupported type classifier: " + uVar);
    }

    public static final Collection w(Collection collection, Collection collection2) {
        j.e(collection2, "collection");
        if (collection2.isEmpty()) {
            return collection;
        }
        if (collection == null) {
            return collection2;
        }
        if (collection instanceof LinkedHashSet) {
            ((LinkedHashSet) collection).addAll(collection2);
            return collection;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        linkedHashSet.addAll(collection2);
        return linkedHashSet;
    }

    public static C1211h x(e0 e0Var) {
        j.e(e0Var, "table");
        if (e0Var.e.size() == 0) {
            return C1211h.b;
        }
        List list = e0Var.e;
        j.d(list, "getRequirementList(...)");
        return new C1211h(list);
    }

    public static final Object y(Class cls, Map map, List list) {
        j.e(cls, "annotationClass");
        j.e(list, "methods");
        me.m q = d.q(new Af.g(16, map));
        Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new Le.d(cls, map, d.q(new E(6, (Object) cls, (Object) map)), q, list));
        j.c(newProxyInstance, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance");
        return newProxyInstance;
    }

    public static final B z(Class cls, List list) {
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            Iterable<x> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (x I6 : iterable) {
                arrayList.add(I(I6));
            }
            return new B(cls, (Type) null, arrayList);
        } else if (Modifier.isStatic(cls.getModifiers())) {
            Iterable<x> iterable2 = list;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
            for (x I8 : iterable2) {
                arrayList2.add(I(I8));
            }
            return new B(cls, declaringClass, arrayList2);
        } else {
            int length = cls.getTypeParameters().length;
            B z = z(declaringClass, list.subList(length, list.size()));
            Iterable<x> subList = list.subList(0, length);
            ArrayList arrayList3 = new ArrayList(C1196n.w0(subList, 10));
            for (x I9 : subList) {
                arrayList3.add(I(I9));
            }
            return new B(cls, z, arrayList3);
        }
    }
}
