package Le;

import Ke.v0;
import a.C0068a;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.j;
import me.m;
import ne.C1192j;

public final class d implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    public final Class f3528a;
    public final Map b;

    /* renamed from: c  reason: collision with root package name */
    public final m f3529c;
    public final m d;
    public final List e;

    public d(Class cls, Map map, m mVar, m mVar2, List list) {
        this.f3528a = cls;
        this.b = map;
        this.f3529c = mVar;
        this.d = mVar2;
        this.e = list;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        Annotation annotation;
        Class cls;
        boolean z;
        boolean z3;
        String name = method.getName();
        Class cls2 = this.f3528a;
        if (name != null) {
            int hashCode = name.hashCode();
            if (hashCode != -1776922004) {
                if (hashCode != 147696667) {
                    if (hashCode == 1444986633 && name.equals("annotationType")) {
                        return cls2;
                    }
                } else if (name.equals("hashCode")) {
                    return Integer.valueOf(((Number) this.d.getValue()).intValue());
                }
            } else if (name.equals("toString")) {
                return (String) this.f3529c.getValue();
            }
        }
        boolean a7 = j.a(name, "equals");
        Map map = this.b;
        boolean z7 = false;
        if (a7 && objArr != null && objArr.length == 1) {
            Object v02 = C1192j.v0(objArr);
            if (v02 instanceof Annotation) {
                annotation = (Annotation) v02;
            } else {
                annotation = null;
            }
            if (annotation != null) {
                cls = C0068a.A(C0068a.w(annotation));
            } else {
                cls = null;
            }
            if (j.a(cls, cls2)) {
                Iterable iterable = this.e;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Method method2 = (Method) it.next();
                        Object obj2 = map.get(method2.getName());
                        Object invoke = method2.invoke(v02, (Object[]) null);
                        if (obj2 instanceof boolean[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.BooleanArray");
                            z3 = Arrays.equals((boolean[]) obj2, (boolean[]) invoke);
                            continue;
                        } else if (obj2 instanceof char[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.CharArray");
                            z3 = Arrays.equals((char[]) obj2, (char[]) invoke);
                            continue;
                        } else if (obj2 instanceof byte[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.ByteArray");
                            z3 = Arrays.equals((byte[]) obj2, (byte[]) invoke);
                            continue;
                        } else if (obj2 instanceof short[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.ShortArray");
                            z3 = Arrays.equals((short[]) obj2, (short[]) invoke);
                            continue;
                        } else if (obj2 instanceof int[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.IntArray");
                            z3 = Arrays.equals((int[]) obj2, (int[]) invoke);
                            continue;
                        } else if (obj2 instanceof float[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.FloatArray");
                            z3 = Arrays.equals((float[]) obj2, (float[]) invoke);
                            continue;
                        } else if (obj2 instanceof long[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.LongArray");
                            z3 = Arrays.equals((long[]) obj2, (long[]) invoke);
                            continue;
                        } else if (obj2 instanceof double[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.DoubleArray");
                            z3 = Arrays.equals((double[]) obj2, (double[]) invoke);
                            continue;
                        } else if (obj2 instanceof Object[]) {
                            j.c(invoke, "null cannot be cast to non-null type kotlin.Array<*>");
                            z3 = Arrays.equals((Object[]) obj2, (Object[]) invoke);
                            continue;
                        } else {
                            z3 = j.a(obj2, invoke);
                            continue;
                        }
                        if (!z3) {
                            z = false;
                            break;
                        }
                    }
                }
                z = true;
                if (z) {
                    z7 = true;
                }
            }
            return Boolean.valueOf(z7);
        } else if (map.containsKey(name)) {
            return map.get(name);
        } else {
            StringBuilder sb2 = new StringBuilder("Method is not supported: ");
            sb2.append(method);
            sb2.append(" (args: ");
            if (objArr == null) {
                objArr = new Object[0];
            }
            sb2.append(C1192j.x0(objArr));
            sb2.append(')');
            throw new v0(sb2.toString(), 0);
        }
    }
}
