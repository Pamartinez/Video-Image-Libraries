package Ke;

import Gd.a;
import He.C0747c;
import He.l;
import He.m;
import He.u;
import He.z;
import Hf.C0754c;
import L1.d;
import Le.g;
import Qe.A;
import Qe.C0814d;
import Qe.C0826p;
import Qe.C0827q;
import a.C0068a;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.j;
import me.h;
import ne.C1196n;
import o1.C0246a;
import qe.C1227c;
import qf.C1236c;

/* renamed from: Ke.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0800s implements C0747c, u0 {
    public final x0 d = C0246a.d0((C0814d) null, new C0798p(this, 0));
    public final x0 e = C0246a.d0((C0814d) null, new C0798p(this, 1));
    public final x0 f = C0246a.d0((C0814d) null, new C0798p(this, 2));
    public final x0 g = C0246a.d0((C0814d) null, new C0798p(this, 3));

    /* renamed from: h  reason: collision with root package name */
    public final x0 f3510h = C0246a.d0((C0814d) null, new C0798p(this, 4));

    /* renamed from: i  reason: collision with root package name */
    public final Object f3511i = d.p(h.PUBLICATION, new C0798p(this, 5));

    public static Object f(r0 r0Var) {
        Class A10 = C0068a.A(a.B(r0Var));
        if (A10.isArray()) {
            Object newInstance = Array.newInstance(A10.getComponentType(), 0);
            j.d(newInstance, "run(...)");
            return newInstance;
        }
        throw new v0("Cannot instantiate the default empty array of type " + A10.getSimpleName() + ", because it is not an array type", 0);
    }

    public final Object call(Object... objArr) {
        j.e(objArr, "args");
        try {
            return g().call(objArr);
        } catch (IllegalAccessException e7) {
            throw new Exception(e7);
        }
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [me.f, java.lang.Object] */
    public final Object callBy(Map map) {
        int i2;
        boolean z;
        C1227c[] cVarArr;
        Object obj;
        Map map2 = map;
        j.e(map2, "args");
        boolean z3 = false;
        if (p()) {
            Iterable<m> parameters = getParameters();
            ArrayList arrayList = new ArrayList(C1196n.w0(parameters, 10));
            for (m mVar : parameters) {
                if (map2.containsKey(mVar)) {
                    obj = map2.get(mVar);
                    if (obj == null) {
                        throw new IllegalArgumentException("Annotation argument value cannot be null (" + mVar + ')');
                    }
                } else {
                    X x9 = (X) mVar;
                    if (x9.g()) {
                        obj = null;
                    } else if (x9.h()) {
                        obj = f(x9.f());
                    } else {
                        throw new IllegalArgumentException("No argument provided for a required parameter: " + x9);
                    }
                }
                arrayList.add(obj);
            }
            g i7 = i();
            if (i7 != null) {
                try {
                    return i7.call(arrayList.toArray(new Object[0]));
                } catch (IllegalAccessException e7) {
                    throw new Exception(e7);
                }
            } else {
                throw new v0("This callable does not support a default call: " + j(), 0);
            }
        } else {
            List<m> parameters2 = getParameters();
            if (parameters2.isEmpty()) {
                try {
                    g g3 = g();
                    if (isSuspend()) {
                        cVarArr = new C1227c[]{null};
                    } else {
                        cVarArr = new C1227c[0];
                    }
                    return g3.call(cVarArr);
                } catch (IllegalAccessException e8) {
                    throw new Exception(e8);
                }
            } else {
                int isSuspend = isSuspend() + parameters2.size();
                Object[] objArr = (Object[]) ((Object[]) this.f3510h.invoke()).clone();
                if (isSuspend()) {
                    objArr[parameters2.size()] = null;
                }
                boolean booleanValue = ((Boolean) this.f3511i.getValue()).booleanValue();
                int i8 = 0;
                for (m mVar2 : parameters2) {
                    if (booleanValue) {
                        i2 = k(mVar2);
                    } else {
                        i2 = 1;
                    }
                    if (map2.containsKey(mVar2)) {
                        objArr[((X) mVar2).e] = map2.get(mVar2);
                    } else {
                        X x10 = (X) mVar2;
                        if (x10.g()) {
                            if (booleanValue) {
                                int i10 = i8 + i2;
                                for (int i11 = i8; i11 < i10; i11++) {
                                    int i12 = (i11 / 32) + isSuspend;
                                    Object obj2 = objArr[i12];
                                    j.c(obj2, "null cannot be cast to non-null type kotlin.Int");
                                    objArr[i12] = Integer.valueOf(((Integer) obj2).intValue() | (1 << (i11 % 32)));
                                }
                                z = true;
                            } else {
                                z = true;
                                int i13 = (i8 / 32) + isSuspend;
                                Object obj3 = objArr[i13];
                                j.c(obj3, "null cannot be cast to non-null type kotlin.Int");
                                objArr[i13] = Integer.valueOf(((Integer) obj3).intValue() | (1 << (i8 % 32)));
                            }
                            z3 = z;
                        } else if (!x10.h()) {
                            throw new IllegalArgumentException("No argument provided for a required parameter: " + x10);
                        }
                    }
                    if (((X) mVar2).f == l.VALUE) {
                        i8 += i2;
                    }
                }
                if (!z3) {
                    try {
                        g g10 = g();
                        Object[] copyOf = Arrays.copyOf(objArr, isSuspend);
                        j.d(copyOf, "copyOf(...)");
                        return g10.call(copyOf);
                    } catch (IllegalAccessException e9) {
                        throw new Exception(e9);
                    }
                } else {
                    g i14 = i();
                    if (i14 != null) {
                        try {
                            return i14.call(objArr);
                        } catch (IllegalAccessException e10) {
                            throw new Exception(e10);
                        }
                    } else {
                        throw new v0("This callable does not support a default call: " + j(), 0);
                    }
                }
            }
        }
    }

    public abstract g g();

    public final List getAnnotations() {
        Object invoke = this.d.invoke();
        j.d(invoke, "invoke(...)");
        return (List) invoke;
    }

    public final List getParameters() {
        Object invoke = this.e.invoke();
        j.d(invoke, "invoke(...)");
        return (List) invoke;
    }

    public final u getReturnType() {
        Object invoke = this.f.invoke();
        j.d(invoke, "invoke(...)");
        return (u) invoke;
    }

    public final List getTypeParameters() {
        Object invoke = this.g.invoke();
        j.d(invoke, "invoke(...)");
        return (List) invoke;
    }

    public final z getVisibility() {
        C0826p visibility = j().getVisibility();
        j.d(visibility, "getVisibility(...)");
        C1236c cVar = E0.f3489a;
        if (visibility.equals(C0827q.e)) {
            return z.PUBLIC;
        }
        if (visibility.equals(C0827q.f3676c)) {
            return z.PROTECTED;
        }
        if (visibility.equals(C0827q.d)) {
            return z.INTERNAL;
        }
        if (visibility.equals(C0827q.f3675a) || visibility.equals(C0827q.b)) {
            return z.PRIVATE;
        }
        return null;
    }

    public abstract F h();

    public abstract g i();

    public final boolean isAbstract() {
        if (j().k() == A.ABSTRACT) {
            return true;
        }
        return false;
    }

    public final boolean isFinal() {
        if (j().k() == A.FINAL) {
            return true;
        }
        return false;
    }

    public final boolean isOpen() {
        if (j().k() == A.OPEN) {
            return true;
        }
        return false;
    }

    public abstract C0814d j();

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final int k(m mVar) {
        if (((Boolean) this.f3511i.getValue()).booleanValue()) {
            X x9 = (X) mVar;
            if (!E0.h(x9.f())) {
                return 1;
            }
            ArrayList E = C0068a.E(C0754c.b(x9.f().d));
            j.b(E);
            return E.size();
        }
        throw new IllegalArgumentException("Check if parametersNeedMFVCFlattening is true before");
    }

    public final boolean p() {
        if (!j.a(getName(), "<init>") || !h().b().isAnnotation()) {
            return false;
        }
        return true;
    }

    public abstract boolean q();
}
