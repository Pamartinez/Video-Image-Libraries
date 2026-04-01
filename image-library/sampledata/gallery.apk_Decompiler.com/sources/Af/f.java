package Af;

import Ae.b;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f {

    /* renamed from: c  reason: collision with root package name */
    public static final n f3307c = new Object();
    public static final int d;
    public static final int e;
    public static final int f;
    public static final int g;

    /* renamed from: h  reason: collision with root package name */
    public static final int f3308h;

    /* renamed from: i  reason: collision with root package name */
    public static final int f3309i;

    /* renamed from: j  reason: collision with root package name */
    public static final int f3310j;
    public static final int k;
    public static final int l;
    public static final f m;
    public static final f n;

    /* renamed from: o  reason: collision with root package name */
    public static final f f3311o;

    /* renamed from: p  reason: collision with root package name */
    public static final f f3312p;
    public static final f q;
    public static final ArrayList r;
    public static final ArrayList s;

    /* renamed from: a  reason: collision with root package name */
    public final List f3313a;
    public final int b;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, Af.n] */
    static {
        e eVar;
        f fVar;
        int i2 = d;
        int i7 = i2 << 1;
        e = i2;
        int i8 = i2 << 2;
        f = i7;
        int i10 = i2 << 3;
        g = i8;
        int i11 = i2 << 4;
        f3308h = i10;
        int i12 = i2 << 5;
        f3309i = i11;
        f3310j = i12;
        d = i2 << 7;
        int i13 = (i2 << 6) - 1;
        k = i13;
        int i14 = i2 | i7 | i8;
        l = i14;
        m = new f(i13);
        n = new f(i11 | i12);
        new f(i2);
        new f(i7);
        new f(i8);
        f3311o = new f(i14);
        new f(i10);
        f3312p = new f(i11);
        q = new f(i12);
        new f(i7 | i11 | i12);
        Class<f> cls = f.class;
        Field[] fields = cls.getFields();
        j.d(fields, "getFields(...)");
        ArrayList arrayList = new ArrayList();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            e eVar2 = null;
            if (!it.hasNext()) {
                break;
            }
            Field field2 = (Field) it.next();
            Object obj = field2.get((Object) null);
            if (obj instanceof f) {
                fVar = (f) obj;
            } else {
                fVar = null;
            }
            if (fVar != null) {
                int i15 = fVar.b;
                String name = field2.getName();
                j.d(name, "getName(...)");
                eVar2 = new e(i15, name);
            }
            if (eVar2 != null) {
                arrayList2.add(eVar2);
            }
        }
        r = arrayList2;
        Field[] fields2 = cls.getFields();
        j.d(fields2, "getFields(...)");
        ArrayList arrayList3 = new ArrayList();
        for (Field field3 : fields2) {
            if (Modifier.isStatic(field3.getModifiers())) {
                arrayList3.add(field3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            if (j.a(((Field) next).getType(), Integer.TYPE)) {
                arrayList4.add(next);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            Field field4 = (Field) it3.next();
            Object obj2 = field4.get((Object) null);
            j.c(obj2, "null cannot be cast to non-null type kotlin.Int");
            int intValue = ((Integer) obj2).intValue();
            if (intValue == ((-intValue) & intValue)) {
                String name2 = field4.getName();
                j.d(name2, "getName(...)");
                eVar = new e(intValue, name2);
            } else {
                eVar = null;
            }
            if (eVar != null) {
                arrayList5.add(eVar);
            }
        }
        s = arrayList5;
    }

    public f(int i2, List list) {
        j.e(list, "excludes");
        this.f3313a = list;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            i2 &= ~((d) it.next()).a();
        }
        this.b = i2;
    }

    public final boolean a(int i2) {
        if ((this.b & i2) != 0) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        Class<?> cls;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            cls = obj.getClass();
        } else {
            cls = null;
        }
        if (!f.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter");
        f fVar = (f) obj;
        if (j.a(this.f3313a, fVar.f3313a) && this.b == fVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.f3313a.hashCode() * 31) + this.b;
    }

    public final String toString() {
        Object obj;
        String str;
        String str2;
        Iterator it = r.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((e) obj).f3306a == this.b) {
                break;
            }
        }
        e eVar = (e) obj;
        if (eVar != null) {
            str = eVar.b;
        } else {
            str = null;
        }
        if (str == null) {
            ArrayList arrayList = new ArrayList();
            Iterator it2 = s.iterator();
            while (it2.hasNext()) {
                e eVar2 = (e) it2.next();
                if (a(eVar2.f3306a)) {
                    str2 = eVar2.b;
                } else {
                    str2 = null;
                }
                if (str2 != null) {
                    arrayList.add(str2);
                }
            }
            str = C1194l.R0(arrayList, " | ", (String) null, (String) null, (b) null, 62);
        }
        return C0212a.r(N2.j.k("DescriptorKindFilter(", str, ArcCommonLog.TAG_COMMA), this.f3313a, ')');
    }

    public /* synthetic */ f(int i2) {
        this(i2, C1202t.d);
    }
}
