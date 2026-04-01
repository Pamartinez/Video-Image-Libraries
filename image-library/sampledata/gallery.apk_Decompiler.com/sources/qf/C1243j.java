package qf;

import D1.f;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1182C;
import ne.C1192j;
import ne.C1196n;
import ne.z;

/* renamed from: qf.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1243j {

    /* renamed from: a  reason: collision with root package name */
    public static final C1236c f5043a;
    public static final C1236c b;

    /* renamed from: c  reason: collision with root package name */
    public static final C1236c f5044c;
    public static final C1236c d;
    public static final C1236c e;
    public static final C1236c f;
    public static final C1236c g;

    /* renamed from: h  reason: collision with root package name */
    public static final C1235b f5045h = f.d("Unit");

    /* renamed from: i  reason: collision with root package name */
    public static final C1235b f5046i = f.d("Any");

    /* renamed from: j  reason: collision with root package name */
    public static final C1235b f5047j = f.d("Enum");
    public static final C1235b k = f.d("Array");
    public static final C1235b l;
    public static final C1235b m;
    public static final C1235b n;

    /* renamed from: o  reason: collision with root package name */
    public static final C1235b f5048o;

    /* renamed from: p  reason: collision with root package name */
    public static final C1235b f5049p = f.d("String");
    public static final C1235b q = f.i("KFunction");
    public static final Set r;
    public static final Set s;
    public static final C1235b t = f.e("MutableList");
    public static final C1235b u = f.e("MutableSet");
    public static final C1235b v;
    public static final C1235b w = new C1235b(g, C1240g.e("EnumEntries"));

    static {
        C1236c cVar = new C1236c("kotlin");
        f5043a = cVar;
        C1236c c5 = cVar.c(C1240g.e("reflect"));
        b = c5;
        C1236c c6 = cVar.c(C1240g.e("collections"));
        f5044c = c6;
        C1236c c8 = cVar.c(C1240g.e("ranges"));
        d = c8;
        C1236c c10 = cVar.c(C1240g.e("jvm"));
        c10.c(C1240g.e("internal"));
        c10.c(C1240g.e("functions"));
        C1236c c11 = cVar.c(C1240g.e("annotation"));
        e = c11;
        C1236c c12 = cVar.c(C1240g.e("internal"));
        c12.c(C1240g.e("ir"));
        C1236c c13 = cVar.c(C1240g.e("coroutines"));
        f = c13;
        g = cVar.c(C1240g.e("enums"));
        cVar.c(C1240g.e("contracts"));
        cVar.c(C1240g.e("concurrent"));
        cVar.c(C1240g.e("test"));
        C1192j.z0(new C1236c[]{cVar, c6, c8, c11});
        C1192j.z0(new C1236c[]{cVar, c6, c8, c11, c5, c12, c13});
        f.d("Nothing");
        f.d("Annotation");
        C1235b d2 = f.d("Boolean");
        C1235b d3 = f.d("Char");
        C1235b d5 = f.d("Byte");
        C1235b d6 = f.d("Short");
        C1235b d7 = f.d("Int");
        C1235b d9 = f.d("Long");
        C1235b d10 = f.d("Float");
        C1235b d11 = f.d("Double");
        l = f.j(d5);
        m = f.j(d6);
        n = f.j(d7);
        f5048o = f.j(d9);
        f.d("CharSequence");
        f.d("Throwable");
        f.d("Cloneable");
        f.i("KProperty");
        f.i("KMutableProperty");
        f.i("KProperty0");
        f.i("KMutableProperty0");
        f.i("KProperty1");
        f.i("KMutableProperty1");
        f.i("KProperty2");
        f.i("KMutableProperty2");
        f.i("KClass");
        f.i("KCallable");
        f.i("KType");
        f.d("Comparable");
        f.d("Number");
        f.d("Function");
        Set z02 = C1192j.z0(new C1235b[]{d2, d3, d5, d6, d7, d9, d10, d11});
        r = z02;
        C1192j.z0(new C1235b[]{d5, d6, d7, d9});
        int Z = z.Z(C1196n.w0(z02, 10));
        int i2 = 16;
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        for (Object next : z02) {
            linkedHashMap.put(next, f.h(((C1235b) next).f()));
        }
        f.f(linkedHashMap);
        Set z03 = C1192j.z0(new C1235b[]{l, m, n, f5048o});
        s = z03;
        int Z4 = z.Z(C1196n.w0(z03, 10));
        if (Z4 >= 16) {
            i2 = Z4;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(i2);
        for (Object next2 : z03) {
            linkedHashMap2.put(next2, f.h(((C1235b) next2).f()));
        }
        f.f(linkedHashMap2);
        Set set = r;
        Set set2 = s;
        LinkedHashSet b0 = C1182C.b0(set, set2);
        C1235b bVar = f5049p;
        C1182C.c0(b0, bVar);
        C1236c cVar2 = f;
        C1240g e7 = C1240g.e("Continuation");
        j.e(cVar2, "packageFqName");
        C1236c.j(e7).d();
        f.e("Iterator");
        f.e("Iterable");
        f.e("Collection");
        f.e("List");
        f.e("ListIterator");
        f.e("Set");
        C1235b e8 = f.e("Map");
        f.e("MutableIterator");
        f.e("CharIterator");
        f.e("MutableIterable");
        f.e("MutableCollection");
        f.e("MutableListIterator");
        C1235b e9 = f.e("MutableMap");
        v = e9;
        e8.d(C1240g.e("Entry"));
        e9.d(C1240g.e("MutableEntry"));
        f.d("Result");
        C1236c cVar3 = d;
        C1240g e10 = C1240g.e("IntRange");
        j.e(cVar3, "packageFqName");
        C1236c.j(e10).d();
        C1240g e11 = C1240g.e("LongRange");
        j.e(cVar3, "packageFqName");
        C1236c.j(e11).d();
        C1240g e12 = C1240g.e("CharRange");
        j.e(cVar3, "packageFqName");
        C1236c.j(e12).d();
        C1236c cVar4 = e;
        C1240g e13 = C1240g.e("AnnotationRetention");
        j.e(cVar4, "packageFqName");
        C1236c.j(e13).d();
        C1240g e14 = C1240g.e("AnnotationTarget");
        j.e(cVar4, "packageFqName");
        C1236c.j(e14).d();
        f.d("DeprecationLevel");
        C1182C.c0(C1182C.c0(C1182C.c0(C1182C.c0(C1182C.b0(set, set2), bVar), f5045h), f5046i), f5047j);
    }
}
