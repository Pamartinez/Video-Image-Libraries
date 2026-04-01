package Ke;

import Ae.c;
import Df.H;
import Df.l;
import Df.n;
import Df.w;
import Ff.m;
import He.C0747c;
import He.F;
import Hf.C0774x;
import Hf.P;
import Ne.p;
import Pe.d;
import Qe.C;
import Qe.C0812b;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import Qe.Q;
import Re.a;
import Re.b;
import Re.h;
import Te.u;
import Tf.v;
import Ve.e;
import Ve.f;
import We.C0892d;
import We.C0893e;
import We.o;
import We.s;
import a.C0068a;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jf.r;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.q;
import kotlin.jvm.internal.x;
import lf.C1171y;
import lf.G;
import me.i;
import ne.C1192j;
import ne.C1194l;
import ne.C1196n;
import ne.C1200r;
import ne.z;
import nf.C1204a;
import nf.C1209f;
import nf.C1211h;
import o1.C0246a;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;
import rf.C1264n;
import tf.C1305i;
import vf.C1321a;
import vf.C1322b;
import vf.C1326f;
import vf.C1327g;
import vf.C1329i;
import vf.C1330j;
import vf.C1338r;
import vf.C1339s;
import vf.C1340t;
import vf.C1341u;
import vf.C1343w;
import vf.C1346z;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class E0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C1236c f3489a = new C1236c("kotlin.jvm.JvmStatic");

    public static final C0800s a(C0747c cVar) {
        C0800s sVar;
        if (cVar instanceof C0800s) {
            sVar = (C0800s) cVar;
        } else {
            sVar = null;
        }
        if (sVar != null) {
            return sVar;
        }
        H b = b(cVar);
        if (b != null) {
            return b;
        }
        return c(cVar);
    }

    public static final H b(Object obj) {
        H h5;
        g gVar;
        C0747c cVar;
        if (obj instanceof H) {
            h5 = (H) obj;
        } else {
            h5 = null;
        }
        if (h5 != null) {
            return h5;
        }
        if (obj instanceof g) {
            gVar = (g) obj;
        } else {
            gVar = null;
        }
        if (gVar != null) {
            cVar = gVar.compute();
        } else {
            cVar = null;
        }
        if (cVar instanceof H) {
            return (H) cVar;
        }
        return null;
    }

    public static final n0 c(Object obj) {
        n0 n0Var;
        q qVar;
        C0747c cVar;
        if (obj instanceof n0) {
            n0Var = (n0) obj;
        } else {
            n0Var = null;
        }
        if (n0Var != null) {
            return n0Var;
        }
        if (obj instanceof q) {
            qVar = (q) obj;
        } else {
            qVar = null;
        }
        if (qVar != null) {
            cVar = qVar.compute();
        } else {
            cVar = null;
        }
        if (cVar instanceof n0) {
            return (n0) cVar;
        }
        return null;
    }

    public static final ArrayList d(a aVar) {
        List list;
        C0893e eVar;
        j.e(aVar, "<this>");
        h annotations = aVar.getAnnotations();
        ArrayList arrayList = new ArrayList();
        Iterator it = annotations.iterator();
        while (true) {
            Annotation annotation = null;
            if (!it.hasNext()) {
                break;
            }
            b bVar = (b) it.next();
            Q source = bVar.getSource();
            if (source instanceof Ve.a) {
                annotation = ((Ve.a) source).d;
            } else if (source instanceof f) {
                s sVar = ((f) source).d;
                if (sVar instanceof C0893e) {
                    eVar = (C0893e) sVar;
                } else {
                    eVar = null;
                }
                if (eVar != null) {
                    annotation = eVar.f3887a;
                }
            } else {
                annotation = j(bVar);
            }
            if (annotation != null) {
                arrayList.add(annotation);
            }
        }
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (C0068a.A(C0068a.w((Annotation) it2.next())).getSimpleName().equals("Container")) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        Annotation annotation2 = (Annotation) it3.next();
                        Class A10 = C0068a.A(C0068a.w(annotation2));
                        if (!A10.getSimpleName().equals("Container") || A10.getAnnotation(x.class) == null) {
                            list = C0246a.e0(annotation2);
                        } else {
                            Object invoke = A10.getDeclaredMethod("value", (Class[]) null).invoke(annotation2, (Object[]) null);
                            j.c(invoke, "null cannot be cast to non-null type kotlin.Array<out kotlin.Annotation>");
                            list = C1192j.a0((Annotation[]) invoke);
                        }
                        C1200r.A0(list, arrayList2);
                    }
                    return arrayList2;
                }
            }
        }
        return arrayList;
    }

    public static final Object e(Type type) {
        j.e(type, "type");
        if (!(type instanceof Class)) {
            return null;
        }
        Class cls = (Class) type;
        if (!cls.isPrimitive()) {
            return null;
        }
        if (cls.equals(Boolean.TYPE)) {
            return Boolean.FALSE;
        }
        if (cls.equals(Character.TYPE)) {
            return 0;
        }
        if (cls.equals(Byte.TYPE)) {
            return (byte) 0;
        }
        if (cls.equals(Short.TYPE)) {
            return (short) 0;
        }
        if (cls.equals(Integer.TYPE)) {
            return 0;
        }
        if (cls.equals(Float.TYPE)) {
            return Float.valueOf(0.0f);
        }
        if (cls.equals(Long.TYPE)) {
            return 0L;
        }
        if (cls.equals(Double.TYPE)) {
            return Double.valueOf(MapUtil.INVALID_LOCATION);
        }
        if (cls.equals(Void.TYPE)) {
            throw new IllegalStateException("Parameter with void type is illegal");
        }
        throw new UnsupportedOperationException("Unknown primitive: " + type);
    }

    public static final C0812b f(Class cls, C1264n nVar, C1209f fVar, B1.b bVar, C1204a aVar, c cVar) {
        List list;
        j.e(cls, "moduleAnchor");
        j.e(nVar, "proto");
        j.e(fVar, "nameResolver");
        j.e(aVar, "metadataVersion");
        e a7 = w0.a(cls);
        if (nVar instanceof C1171y) {
            list = ((C1171y) nVar).l;
        } else if (nVar instanceof G) {
            list = ((G) nVar).l;
        } else {
            throw new IllegalStateException(("Unsupported message: " + nVar).toString());
        }
        List list2 = list;
        l lVar = a7.f3832a;
        C c5 = lVar.b;
        C1211h hVar = C1211h.b;
        j.b(list2);
        return (C0812b) cVar.invoke(new w(new n(lVar, fVar, c5, bVar, hVar, aVar, (m) null, (H) null, list2)), nVar);
    }

    public static final u g(C0814d dVar) {
        j.e(dVar, "<this>");
        if (dVar.E() == null) {
            return null;
        }
        C0822l g = dVar.g();
        j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        return ((C0816f) g).v0();
    }

    public static final boolean h(r0 r0Var) {
        C0774x xVar = r0Var.d;
        if (xVar == null || !C1305i.h(xVar)) {
            return false;
        }
        return true;
    }

    public static final Class i(ClassLoader classLoader, C1235b bVar, int i2) {
        String str = d.f3633a;
        C1238e i7 = bVar.a().i();
        j.d(i7, "toUnsafe(...)");
        C1235b f = d.f(i7);
        if (f != null) {
            bVar = f;
        }
        String b = bVar.f5033a.b();
        String b5 = bVar.b.b();
        if (b.equals("kotlin")) {
            switch (b5.hashCode()) {
                case -901856463:
                    if (b5.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (b5.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (b5.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (b5.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (b5.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (b5.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (b5.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (b5.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (b5.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        if (i2 > 0) {
            for (int i8 = 0; i8 < i2; i8++) {
                sb2.append("[");
            }
            sb2.append("L");
        }
        if (b.length() > 0) {
            sb2.append(b.concat("."));
        }
        sb2.append(v.r0(b5, '.', '$'));
        if (i2 > 0) {
            sb2.append(";");
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return F.R(classLoader, sb3);
    }

    public static final Annotation j(b bVar) {
        Class cls;
        i iVar;
        C0816f d = C1353d.d(bVar);
        if (d != null) {
            cls = k(d);
        } else {
            cls = null;
        }
        if (cls == null) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        Set<Map.Entry> entrySet = bVar.a().entrySet();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : entrySet) {
            C1240g gVar = (C1240g) entry.getKey();
            ClassLoader classLoader = cls.getClassLoader();
            j.d(classLoader, "getClassLoader(...)");
            Object l = l((C1327g) entry.getValue(), classLoader);
            if (l != null) {
                iVar = new i(gVar.b(), l);
            } else {
                iVar = null;
            }
            if (iVar != null) {
                arrayList.add(iVar);
            }
        }
        Map e02 = z.e0(arrayList);
        Set<String> keySet = e02.keySet();
        ArrayList arrayList2 = new ArrayList(C1196n.w0(keySet, 10));
        for (String declaredMethod : keySet) {
            arrayList2.add(cls.getDeclaredMethod(declaredMethod, (Class[]) null));
        }
        return (Annotation) F.y(cls, e02, arrayList2);
    }

    public static final Class k(C0816f fVar) {
        j.e(fVar, "<this>");
        Q source = fVar.getSource();
        j.d(source, "getSource(...)");
        if (source instanceof r) {
            return ((r) source).d.f3829a;
        }
        if (source instanceof f) {
            s sVar = ((f) source).d;
            j.c(sVar, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass");
            return ((o) sVar).f3891a;
        }
        C1235b f = C1353d.f(fVar);
        if (f == null) {
            return null;
        }
        return i(C0892d.d(fVar.getClass()), f, 0);
    }

    public static final Object l(C1327g gVar, ClassLoader classLoader) {
        C0816f fVar;
        C1346z zVar;
        C0774x xVar;
        Ne.l lVar;
        int i2;
        C0816f fVar2;
        Class i7;
        if (gVar instanceof C1321a) {
            return j((b) ((C1321a) gVar).f5158a);
        }
        int i8 = 0;
        if (gVar instanceof C1322b) {
            C1322b bVar = (C1322b) gVar;
            if (bVar instanceof C1346z) {
                zVar = (C1346z) bVar;
            } else {
                zVar = null;
            }
            if (!(zVar == null || (xVar = zVar.f5164c) == null)) {
                Object obj = bVar.f5158a;
                Iterable<C1327g> iterable = (Iterable) obj;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (C1327g l : iterable) {
                    arrayList.add(l(l, classLoader));
                }
                C1240g gVar2 = Ne.i.e;
                C0819i g = xVar.s0().g();
                if (g == null) {
                    lVar = null;
                } else {
                    lVar = Ne.i.r(g);
                }
                if (lVar == null) {
                    i2 = -1;
                } else {
                    i2 = D0.f3488a[lVar.ordinal()];
                }
                switch (i2) {
                    case -1:
                        if (Ne.i.y(xVar)) {
                            C0774x b = ((P) C1194l.b1(xVar.e0())).b();
                            j.d(b, "getType(...)");
                            C0819i g3 = b.s0().g();
                            if (g3 instanceof C0816f) {
                                fVar2 = (C0816f) g3;
                            } else {
                                fVar2 = null;
                            }
                            if (fVar2 == null) {
                                throw new IllegalStateException(("Not a class type: " + b).toString());
                            } else if (Ne.i.G(b)) {
                                int size = ((List) obj).size();
                                Object[] objArr = new String[size];
                                while (i8 < size) {
                                    Object obj2 = arrayList.get(i8);
                                    j.c(obj2, "null cannot be cast to non-null type kotlin.String");
                                    objArr[i8] = obj2;
                                    i8++;
                                }
                                return objArr;
                            } else if (Ne.i.b(fVar2, p.Q)) {
                                int size2 = ((List) obj).size();
                                Object[] objArr2 = new Class[size2];
                                while (i8 < size2) {
                                    Object obj3 = arrayList.get(i8);
                                    j.c(obj3, "null cannot be cast to non-null type java.lang.Class<*>");
                                    objArr2[i8] = obj3;
                                    i8++;
                                }
                                return objArr2;
                            } else {
                                C1235b f = C1353d.f(fVar2);
                                if (!(f == null || (i7 = i(classLoader, f, 0)) == null)) {
                                    Object newInstance = Array.newInstance(i7, ((List) obj).size());
                                    j.c(newInstance, "null cannot be cast to non-null type kotlin.Array<in kotlin.Any?>");
                                    Object[] objArr3 = (Object[]) newInstance;
                                    int size3 = arrayList.size();
                                    while (i8 < size3) {
                                        objArr3[i8] = arrayList.get(i8);
                                        i8++;
                                    }
                                    return objArr3;
                                }
                            }
                        } else {
                            throw new IllegalStateException(("Not an array type: " + xVar).toString());
                        }
                    case 1:
                        int size4 = ((List) obj).size();
                        boolean[] zArr = new boolean[size4];
                        while (i8 < size4) {
                            Object obj4 = arrayList.get(i8);
                            j.c(obj4, "null cannot be cast to non-null type kotlin.Boolean");
                            zArr[i8] = ((Boolean) obj4).booleanValue();
                            i8++;
                        }
                        return zArr;
                    case 2:
                        int size5 = ((List) obj).size();
                        char[] cArr = new char[size5];
                        while (i8 < size5) {
                            Object obj5 = arrayList.get(i8);
                            j.c(obj5, "null cannot be cast to non-null type kotlin.Char");
                            cArr[i8] = ((Character) obj5).charValue();
                            i8++;
                        }
                        return cArr;
                    case 3:
                        int size6 = ((List) obj).size();
                        byte[] bArr = new byte[size6];
                        while (i8 < size6) {
                            Object obj6 = arrayList.get(i8);
                            j.c(obj6, "null cannot be cast to non-null type kotlin.Byte");
                            bArr[i8] = ((Byte) obj6).byteValue();
                            i8++;
                        }
                        return bArr;
                    case 4:
                        int size7 = ((List) obj).size();
                        short[] sArr = new short[size7];
                        while (i8 < size7) {
                            Object obj7 = arrayList.get(i8);
                            j.c(obj7, "null cannot be cast to non-null type kotlin.Short");
                            sArr[i8] = ((Short) obj7).shortValue();
                            i8++;
                        }
                        return sArr;
                    case 5:
                        int size8 = ((List) obj).size();
                        int[] iArr = new int[size8];
                        while (i8 < size8) {
                            Object obj8 = arrayList.get(i8);
                            j.c(obj8, "null cannot be cast to non-null type kotlin.Int");
                            iArr[i8] = ((Integer) obj8).intValue();
                            i8++;
                        }
                        return iArr;
                    case 6:
                        int size9 = ((List) obj).size();
                        float[] fArr = new float[size9];
                        while (i8 < size9) {
                            Object obj9 = arrayList.get(i8);
                            j.c(obj9, "null cannot be cast to non-null type kotlin.Float");
                            fArr[i8] = ((Float) obj9).floatValue();
                            i8++;
                        }
                        return fArr;
                    case 7:
                        int size10 = ((List) obj).size();
                        long[] jArr = new long[size10];
                        while (i8 < size10) {
                            Object obj10 = arrayList.get(i8);
                            j.c(obj10, "null cannot be cast to non-null type kotlin.Long");
                            jArr[i8] = ((Long) obj10).longValue();
                            i8++;
                        }
                        return jArr;
                    case 8:
                        int size11 = ((List) obj).size();
                        double[] dArr = new double[size11];
                        while (i8 < size11) {
                            Object obj11 = arrayList.get(i8);
                            j.c(obj11, "null cannot be cast to non-null type kotlin.Double");
                            dArr[i8] = ((Double) obj11).doubleValue();
                            i8++;
                        }
                        return dArr;
                    default:
                        throw new RuntimeException();
                }
            }
        } else if (gVar instanceof C1329i) {
            i iVar = (i) ((C1329i) gVar).f5158a;
            C1240g gVar3 = (C1240g) iVar.e;
            Class i10 = i(classLoader, (C1235b) iVar.d, 0);
            if (i10 != null) {
                return Enum.valueOf(i10, gVar3.b());
            }
        } else if (gVar instanceof C1341u) {
            C1340t tVar = (C1340t) ((C1341u) gVar).f5158a;
            if (tVar instanceof C1339s) {
                C1326f fVar3 = ((C1339s) tVar).f5163a;
                return i(classLoader, fVar3.f5157a, fVar3.b);
            } else if (tVar instanceof C1338r) {
                C0819i g10 = ((C1338r) tVar).f5162a.s0().g();
                if (g10 instanceof C0816f) {
                    fVar = (C0816f) g10;
                } else {
                    fVar = null;
                }
                if (fVar != null) {
                    return k(fVar);
                }
            } else {
                throw new RuntimeException();
            }
        } else if ((gVar instanceof C1330j) || (gVar instanceof C1343w)) {
            return null;
        } else {
            return gVar.b();
        }
        return null;
    }
}
