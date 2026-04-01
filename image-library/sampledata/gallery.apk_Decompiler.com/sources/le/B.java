package Le;

import Hf.C0754c;
import Hf.C0774x;
import Ke.E0;
import Ke.F;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0831v;
import Qe.M;
import Tf.n;
import We.C0892d;
import a.C0068a;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1195m;
import ne.C1196n;
import ne.C1200r;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B implements g {

    /* renamed from: a  reason: collision with root package name */
    public final Method f3522a;
    public final Method b;

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList f3523c;
    public final ArrayList d;
    public final ArrayList e;

    public B(C0831v vVar, F f, String str, List list) {
        ArrayList arrayList;
        Method z;
        j.e(f, "container");
        j.e(str, "constructorDesc");
        Method h5 = f.h("constructor-impl", str);
        j.b(h5);
        this.f3522a = h5;
        Method h6 = f.h("box-impl", n.I0(str, "V") + C0892d.b(f.b()));
        j.b(h6);
        this.b = h6;
        Iterable iterable = list;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
        Iterator it = iterable.iterator();
        while (true) {
            List list2 = null;
            if (!it.hasNext()) {
                break;
            }
            C0774x type = ((M) it.next()).getType();
            j.d(type, "getType(...)");
            Hf.B b5 = C0754c.b(type);
            ArrayList E = C0068a.E(b5);
            if (E == null) {
                Class Z = C0068a.Z(b5);
                if (!(Z == null || (z = C0068a.z(Z, vVar)) == null)) {
                    list2 = C0246a.e0(z);
                }
            } else {
                list2 = E;
            }
            arrayList2.add(list2);
        }
        this.f3523c = arrayList2;
        ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable, 10));
        int i2 = 0;
        for (Object next : iterable) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                C0819i g = ((M) next).getType().s0().g();
                j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                C0816f fVar = (C0816f) g;
                List list3 = (List) this.f3523c.get(i2);
                if (list3 != null) {
                    Iterable<Method> iterable2 = list3;
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(iterable2, 10));
                    for (Method returnType : iterable2) {
                        arrayList4.add(returnType.getReturnType());
                    }
                    arrayList = arrayList4;
                } else {
                    Class k = E0.k(fVar);
                    j.b(k);
                    arrayList = C0246a.e0(k);
                }
                arrayList3.add(arrayList);
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        this.d = arrayList3;
        this.e = C1196n.x0(arrayList3);
    }

    public final List a() {
        return this.e;
    }

    public final /* bridge */ /* synthetic */ Member b() {
        return null;
    }

    public final Object call(Object[] objArr) {
        ArrayList arrayList;
        j.e(objArr, "args");
        ArrayList arrayList2 = this.f3523c;
        j.e(arrayList2, "other");
        int length = objArr.length;
        ArrayList arrayList3 = new ArrayList(Math.min(C1196n.w0(arrayList2, 10), length));
        Iterator it = arrayList2.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (i2 >= length) {
                break;
            }
            arrayList3.add(new i(objArr[i2], next));
            i2++;
        }
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            i iVar = (i) it2.next();
            Object obj = iVar.d;
            List list = (List) iVar.e;
            if (list != null) {
                Iterable<Method> iterable = list;
                ArrayList arrayList5 = new ArrayList(C1196n.w0(iterable, 10));
                for (Method invoke : iterable) {
                    arrayList5.add(invoke.invoke(obj, (Object[]) null));
                }
                arrayList = arrayList5;
            } else {
                arrayList = C0246a.e0(obj);
            }
            C1200r.A0(arrayList, arrayList4);
        }
        Object[] array = arrayList4.toArray(new Object[0]);
        this.f3522a.invoke((Object) null, Arrays.copyOf(array, array.length));
        return this.b.invoke((Object) null, Arrays.copyOf(array, array.length));
    }

    public final Type getReturnType() {
        Class<?> returnType = this.b.getReturnType();
        j.d(returnType, "getReturnType(...)");
        return returnType;
    }
}
