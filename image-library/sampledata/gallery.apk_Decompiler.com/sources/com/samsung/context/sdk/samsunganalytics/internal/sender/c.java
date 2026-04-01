package com.samsung.context.sdk.samsunganalytics.internal.sender;

import B1.a;
import Dd.C0732c;
import Df.p;
import Hf.B;
import Hf.C0754c;
import Hf.C0768q;
import Hf.C0774x;
import Hf.G;
import Hf.I;
import Hf.M;
import Hf.P;
import Hf.a0;
import Hf.c0;
import Hf.d0;
import Ke.i0;
import Ke.n0;
import Ne.i;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0820j;
import Qe.V;
import Re.h;
import Sf.r;
import Te.C0848i;
import Te.Q;
import Tf.n;
import Tf.v;
import Uf.b;
import We.C0889a;
import Ze.C0899f;
import a.C0068a;
import af.C0903a;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sum.core.types.NumericEnum;
import df.C0946i;
import i.C0212a;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import jf.C1108h;
import jf.C1112l;
import jf.C1113m;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.x;
import nf.C1209f;
import o1.C0246a;
import og.k;
import qf.C1235b;
import qf.C1240g;
import te.C1296b;
import tf.C1311o;
import xf.C1353d;
import yf.C1358b;
import yf.C1359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c {
    public static a b;

    /* renamed from: c  reason: collision with root package name */
    public static C0889a f4196c;

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4197a;

    public /* synthetic */ c(int i2) {
        this.f4197a = i2;
    }

    public static final C1235b A(C1209f fVar, int i2) {
        j.e(fVar, "<this>");
        return k.y(fVar.X(i2), fVar.F(i2));
    }

    public static ColorStateList B(Drawable drawable) {
        if (drawable instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (drawable instanceof ColorStateListDrawable) {
            return ((ColorStateListDrawable) drawable).getColorStateList();
        }
        return null;
    }

    public static final C1240g C(C1209f fVar, int i2) {
        j.e(fVar, "<this>");
        return C1240g.d(fVar.getString(i2));
    }

    public static String D(String str) {
        String[] split = Application.getProcessName().split(NumericEnum.SEP, 2);
        if (split.length != 2) {
            return str;
        }
        StringBuilder t = C0212a.t(str, "_");
        t.append(split[1]);
        return t.toString();
    }

    public static SharedPreferences E(Context context) {
        return context.getSharedPreferences(D("SamsungAnalyticsPrefs"), 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: Qe.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: Qe.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: Hf.x} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: Qe.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: Qe.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: Qe.f} */
    /* JADX WARNING: type inference failed for: r4v3, types: [Qe.i] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final Hf.C0774x F(Qe.V r6) {
        /*
            java.util.List r0 = r6.getUpperBounds()
            java.lang.String r1 = "getUpperBounds(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            java.util.Collection r0 = (java.util.Collection) r0
            r0.isEmpty()
            java.util.List r0 = r6.getUpperBounds()
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x001b:
            boolean r2 = r0.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x004c
            java.lang.Object r2 = r0.next()
            r4 = r2
            Hf.x r4 = (Hf.C0774x) r4
            Hf.M r4 = r4.s0()
            Qe.i r4 = r4.g()
            boolean r5 = r4 instanceof Qe.C0816f
            if (r5 == 0) goto L_0x0038
            r3 = r4
            Qe.f r3 = (Qe.C0816f) r3
        L_0x0038:
            if (r3 != 0) goto L_0x003b
            goto L_0x001b
        L_0x003b:
            Qe.g r4 = r3.b()
            Qe.g r5 = Qe.C0817g.INTERFACE
            if (r4 == r5) goto L_0x001b
            Qe.g r3 = r3.b()
            Qe.g r4 = Qe.C0817g.ANNOTATION_CLASS
            if (r3 == r4) goto L_0x001b
            r3 = r2
        L_0x004c:
            Hf.x r3 = (Hf.C0774x) r3
            if (r3 != 0) goto L_0x0063
            java.util.List r6 = r6.getUpperBounds()
            kotlin.jvm.internal.j.d(r6, r1)
            java.lang.Object r6 = ne.C1194l.L0(r6)
            java.lang.String r0 = "first(...)"
            kotlin.jvm.internal.j.d(r6, r0)
            Hf.x r6 = (Hf.C0774x) r6
            return r6
        L_0x0063:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.context.sdk.samsunganalytics.internal.sender.c.F(Qe.V):Hf.x");
    }

    public static boolean G(C0814d dVar) {
        j.e(dVar, "callableMemberDescriptor");
        if (!C0899f.d.contains(dVar.getName())) {
            return false;
        }
        if (C1194l.G0(C0899f.f3943c, C1353d.c(dVar)) && dVar.B().isEmpty()) {
            return true;
        }
        if (!i.z(dVar)) {
            return false;
        }
        Collection h5 = dVar.h();
        j.d(h5, "getOverriddenDescriptors(...)");
        Iterable<C0814d> iterable = h5;
        if (((Collection) iterable).isEmpty()) {
            return false;
        }
        for (C0814d dVar2 : iterable) {
            j.b(dVar2);
            if (G(dVar2)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean H(V v, M m, Set set) {
        j.e(v, "typeParameter");
        List upperBounds = v.getUpperBounds();
        j.d(upperBounds, "getUpperBounds(...)");
        Iterable<C0774x> iterable = upperBounds;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (C0774x xVar : iterable) {
            j.b(xVar);
            if (n(xVar, v.i().s0(), set) && (m == null || j.a(xVar.s0(), m))) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean I(V v, M m, int i2) {
        if ((i2 & 2) != 0) {
            m = null;
        }
        return H(v, m, (Set) null);
    }

    public static final c0 J(C0774x xVar) {
        j.e(xVar, "<this>");
        c0 g = a0.g(xVar, true);
        j.d(g, "makeNullable(...)");
        return g;
    }

    public static final long K(String str) {
        int i2;
        char charAt;
        int length = str.length();
        if (length <= 0 || !n.v0("+-", str.charAt(0))) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (length - i2 > 16) {
            int i7 = i2;
            while (true) {
                if (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 != '0') {
                        if ('1' > charAt2 || charAt2 >= ':') {
                            break;
                        }
                    } else if (i7 == i2) {
                        i7++;
                    }
                    i2++;
                } else if (length - i7 > 16) {
                    if (str.charAt(0) == '-') {
                        return Long.MIN_VALUE;
                    }
                    return Long.MAX_VALUE;
                }
            }
        }
        if (!v.t0(str, "+") || length <= 1 || '0' > (charAt = str.charAt(1)) || charAt >= ':') {
            return Long.parseLong(str);
        }
        return Long.parseLong(n.w0(1, str));
    }

    public static final C0774x L(C0774x xVar, h hVar) {
        if (!xVar.getAnnotations().isEmpty() || !hVar.isEmpty()) {
            return xVar.x0().A0(C0754c.s(xVar.p0(), hVar));
        }
        return xVar;
    }

    public static final c0 M(C0774x xVar) {
        B b5;
        j.e(xVar, "<this>");
        c0 x02 = xVar.x0();
        if (x02 instanceof C0768q) {
            C0768q qVar = (C0768q) x02;
            B b8 = qVar.e;
            if (!b8.s0().getParameters().isEmpty() && b8.s0().g() != null) {
                List parameters = b8.s0().getParameters();
                j.d(parameters, "getParameters(...)");
                Iterable<V> iterable = parameters;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (V g : iterable) {
                    arrayList.add(new G(g));
                }
                b8 = C0754c.r(b8, arrayList, (I) null, 2);
            }
            B b10 = qVar.f;
            if (!b10.s0().getParameters().isEmpty() && b10.s0().g() != null) {
                List parameters2 = b10.s0().getParameters();
                j.d(parameters2, "getParameters(...)");
                Iterable<V> iterable2 = parameters2;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
                for (V g3 : iterable2) {
                    arrayList2.add(new G(g3));
                }
                b10 = C0754c.r(b10, arrayList2, (I) null, 2);
            }
            b5 = C0754c.f(b8, b10);
        } else if (x02 instanceof B) {
            B b11 = (B) x02;
            boolean isEmpty = b11.s0().getParameters().isEmpty();
            b5 = b11;
            if (!isEmpty) {
                C0819i g10 = b11.s0().g();
                b5 = b11;
                if (g10 != null) {
                    List parameters3 = b11.s0().getParameters();
                    j.d(parameters3, "getParameters(...)");
                    Iterable<V> iterable3 = parameters3;
                    ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable3, 10));
                    for (V g11 : iterable3) {
                        arrayList3.add(new G(g11));
                    }
                    b5 = C0754c.r(b11, arrayList3, (I) null, 2);
                }
            }
        } else {
            throw new RuntimeException();
        }
        return C0754c.i(b5, x02);
    }

    public static LinkedHashSet N(C1240g gVar, Collection collection, Collection collection2, C0816f fVar, p pVar, C1311o oVar, boolean z) {
        if (gVar == null) {
            a(12);
            throw null;
        } else if (collection == null) {
            a(13);
            throw null;
        } else if (fVar == null) {
            a(15);
            throw null;
        } else if (pVar == null) {
            a(16);
            throw null;
        } else if (oVar != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            p pVar2 = pVar;
            C0816f fVar2 = fVar;
            Collection collection3 = collection2;
            Collection collection4 = collection;
            oVar.h(gVar, collection4, collection3, fVar2, new C0903a(pVar2, linkedHashSet, z));
            return linkedHashSet;
        } else {
            a(17);
            throw null;
        }
    }

    public static LinkedHashSet O(C1240g gVar, AbstractCollection abstractCollection, Collection collection, C0816f fVar, p pVar, C1311o oVar) {
        if (gVar == null) {
            a(0);
            throw null;
        } else if (fVar == null) {
            a(3);
            throw null;
        } else if (pVar == null) {
            a(4);
            throw null;
        } else if (oVar != null) {
            return N(gVar, abstractCollection, collection, fVar, pVar, oVar, false);
        } else {
            a(5);
            throw null;
        }
    }

    public static LinkedHashSet P(C1240g gVar, Collection collection, AbstractCollection abstractCollection, C0946i iVar, p pVar, C1311o oVar) {
        if (gVar == null) {
            a(6);
            throw null;
        } else if (collection == null) {
            a(7);
            throw null;
        } else if (iVar == null) {
            a(9);
            throw null;
        } else if (pVar == null) {
            a(10);
            throw null;
        } else if (oVar != null) {
            return N(gVar, collection, abstractCollection, iVar, pVar, oVar, true);
        } else {
            a(11);
            throw null;
        }
    }

    public static final long Q(long j2, Uf.c cVar) {
        j.e(cVar, "unit");
        Uf.c cVar2 = Uf.c.NANOSECONDS;
        long Q = C0246a.Q(4611686018426999999L, cVar2, cVar);
        if ((-Q) <= j2 && j2 <= Q) {
            return s(C0246a.Q(j2, cVar, cVar2));
        }
        Uf.c cVar3 = Uf.c.MILLISECONDS;
        j.e(cVar3, "targetUnit");
        return q(a.m(cVar3.a().convert(j2, cVar.a()), -4611686018427387903L, 4611686018427387903L));
    }

    public static void R(Parcel parcel, int i2, IBinder iBinder) {
        if (iBinder != null) {
            int W6 = W(parcel, i2);
            parcel.writeStrongBinder(iBinder);
            X(parcel, W6);
        }
    }

    public static void S(Parcel parcel, int i2, Parcelable parcelable, int i7) {
        if (parcelable != null) {
            int W6 = W(parcel, i2);
            parcelable.writeToParcel(parcel, i7);
            X(parcel, W6);
        }
    }

    public static void T(Parcel parcel, int i2, String str) {
        if (str != null) {
            int W6 = W(parcel, i2);
            parcel.writeString(str);
            X(parcel, W6);
        }
    }

    public static void U(Parcel parcel, int i2, Parcelable[] parcelableArr, int i7) {
        if (parcelableArr != null) {
            int W6 = W(parcel, i2);
            parcel.writeInt(r0);
            for (Parcelable parcelable : parcelableArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    int dataPosition = parcel.dataPosition();
                    parcel.writeInt(1);
                    int dataPosition2 = parcel.dataPosition();
                    parcelable.writeToParcel(parcel, i7);
                    int dataPosition3 = parcel.dataPosition();
                    parcel.setDataPosition(dataPosition);
                    parcel.writeInt(dataPosition3 - dataPosition2);
                    parcel.setDataPosition(dataPosition3);
                }
            }
            X(parcel, W6);
        }
    }

    public static void V(Parcel parcel, List list, int i2) {
        if (list != null) {
            int W6 = W(parcel, i2);
            int size = list.size();
            parcel.writeInt(size);
            for (int i7 = 0; i7 < size; i7++) {
                Parcelable parcelable = (Parcelable) list.get(i7);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    int dataPosition = parcel.dataPosition();
                    parcel.writeInt(1);
                    int dataPosition2 = parcel.dataPosition();
                    parcelable.writeToParcel(parcel, 0);
                    int dataPosition3 = parcel.dataPosition();
                    parcel.setDataPosition(dataPosition);
                    parcel.writeInt(dataPosition3 - dataPosition2);
                    parcel.setDataPosition(dataPosition3);
                }
            }
            X(parcel, W6);
        }
    }

    public static int W(Parcel parcel, int i2) {
        parcel.writeInt(i2 | -65536);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void X(Parcel parcel, int i2) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i2 - 4);
        parcel.writeInt(dataPosition - i2);
        parcel.setDataPosition(dataPosition);
    }

    public static void Y(int i2, int i7, Parcel parcel) {
        parcel.writeInt(i2 | (i7 << 16));
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 18) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i2 != 18) {
            i7 = 3;
        } else {
            i7 = 2;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 7:
            case 13:
                objArr[0] = "membersFromSupertypes";
                break;
            case 2:
            case 8:
            case 14:
                objArr[0] = "membersFromCurrent";
                break;
            case 3:
            case 9:
            case 15:
                objArr[0] = "classDescriptor";
                break;
            case 4:
            case 10:
            case 16:
                objArr[0] = "errorReporter";
                break;
            case 5:
            case 11:
            case 17:
                objArr[0] = "overridingUtil";
                break;
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
                break;
            case 20:
                objArr[0] = "annotationClass";
                break;
            default:
                objArr[0] = "name";
                break;
        }
        if (i2 != 18) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
        } else {
            objArr[1] = "resolveOverrides";
        }
        switch (i2) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "resolveOverridesForStaticMembers";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "resolveOverrides";
                break;
            case 18:
                break;
            case 19:
            case 20:
                objArr[2] = "getAnnotationParameterByName";
                break;
            default:
                objArr[2] = "resolveOverridesForNonStaticMembers";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 != 18) {
            th = new IllegalArgumentException(format);
        } else {
            th = new IllegalStateException(format);
        }
        throw th;
    }

    public static void b(Parcel parcel, Bundle bundle) {
        if (bundle != null) {
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public static float c(float f) {
        if (f <= 0.04045f) {
            return f / 12.92f;
        }
        return (float) Math.pow((double) ((f + 0.055f) / 1.055f), 2.4000000953674316d);
    }

    public static float d(float f) {
        if (f <= 0.0031308f) {
            return f * 12.92f;
        }
        return (float) ((Math.pow((double) f, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    /* JADX WARNING: type inference failed for: r0v41, types: [me.f, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final Le.g e(Ke.i0 r7, boolean r8) {
        /*
            Tf.m r0 = Ke.F.d
            Ke.n0 r1 = r7.s()
            java.lang.String r1 = r1.l
            r0.getClass()
            java.lang.String r2 = "input"
            kotlin.jvm.internal.j.e(r1, r2)
            java.io.Serializable r0 = r0.e
            java.util.regex.Pattern r0 = (java.util.regex.Pattern) r0
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L_0x0021
            Le.A r7 = Le.A.f3521a
            return r7
        L_0x0021:
            qf.b r0 = Ke.C0.f3487a
            Ke.n0 r0 = r7.s()
            Qe.O r0 = r0.j()
            a.a r0 = Ke.C0.b(r0)
            boolean r1 = r0 instanceof Ke.C0796n
            r2 = 0
            if (r1 == 0) goto L_0x0177
            Ke.n r0 = (Ke.C0796n) r0
            nf.f r1 = r0.g
            of.e r0 = r0.f
            r3 = 4
            r4 = 0
            if (r8 == 0) goto L_0x0048
            int r5 = r0.e
            r5 = r5 & r3
            if (r5 != r3) goto L_0x0046
            of.c r0 = r0.f4994h
            goto L_0x0051
        L_0x0046:
            r0 = r4
            goto L_0x0051
        L_0x0048:
            int r5 = r0.e
            r6 = 8
            r5 = r5 & r6
            if (r5 != r6) goto L_0x0046
            of.c r0 = r0.f4995i
        L_0x0051:
            if (r0 == 0) goto L_0x0069
            Ke.n0 r4 = r7.s()
            Ke.F r4 = r4.f3506j
            int r5 = r0.f
            java.lang.String r5 = r1.getString(r5)
            int r0 = r0.g
            java.lang.String r0 = r1.getString(r0)
            java.lang.reflect.Method r4 = r4.h(r5, r0)
        L_0x0069:
            if (r4 != 0) goto L_0x0116
            Ke.n0 r0 = r7.s()
            Qe.O r0 = r0.j()
            boolean r0 = tf.C1305i.e(r0)
            if (r0 == 0) goto L_0x00ea
            Ke.n0 r0 = r7.s()
            Qe.O r0 = r0.j()
            Qe.p r0 = r0.getVisibility()
            Qe.p r1 = Qe.C0827q.d
            boolean r0 = kotlin.jvm.internal.j.a(r0, r1)
            if (r0 == 0) goto L_0x00ea
            Ke.n0 r8 = r7.s()
            Qe.O r8 = r8.j()
            Qe.l r8 = r8.g()
            java.lang.Class r8 = a.C0068a.a0(r8)
            if (r8 == 0) goto L_0x00cd
            Ke.n0 r0 = r7.s()
            Qe.O r0 = r0.j()
            java.lang.reflect.Method r8 = a.C0068a.z(r8, r0)
            if (r8 == 0) goto L_0x00cd
            boolean r0 = r7.q()
            if (r0 == 0) goto L_0x00be
            Le.x r0 = new Le.x
            java.lang.Object r1 = y(r7)
            r0.<init>(r8, r1)
            goto L_0x01aa
        L_0x00be:
            Le.y r0 = new Le.y
            java.lang.Class r1 = r8.getDeclaringClass()
            java.util.List r1 = o1.C0246a.e0(r1)
            r0.<init>(r8, r1)
            goto L_0x01aa
        L_0x00cd:
            Ke.v0 r8 = new Ke.v0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Underlying property of inline class "
            r0.<init>(r1)
            Ke.n0 r7 = r7.s()
            r0.append(r7)
            java.lang.String r7 = " should have a field"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7, r2)
            throw r8
        L_0x00ea:
            Ke.n0 r0 = r7.s()
            java.lang.Object r0 = r0.n
            java.lang.Object r0 = r0.getValue()
            java.lang.reflect.Field r0 = (java.lang.reflect.Field) r0
            if (r0 == 0) goto L_0x00fe
            Le.w r0 = l(r7, r8, r0)
            goto L_0x01aa
        L_0x00fe:
            Ke.v0 r8 = new Ke.v0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No accessors or field is found for property "
            r0.<init>(r1)
            Ke.n0 r7 = r7.s()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7, r2)
            throw r8
        L_0x0116:
            int r8 = r4.getModifiers()
            boolean r8 = java.lang.reflect.Modifier.isStatic(r8)
            if (r8 != 0) goto L_0x0138
            boolean r8 = r7.q()
            if (r8 == 0) goto L_0x0132
            Le.r r8 = new Le.r
            java.lang.Object r0 = y(r7)
            r8.<init>(r4, r0)
        L_0x012f:
            r0 = r8
            goto L_0x01aa
        L_0x0132:
            Le.v r8 = new Le.v
            r8.<init>(r4)
            goto L_0x012f
        L_0x0138:
            Ke.n0 r8 = r7.s()
            Qe.O r8 = r8.j()
            Re.h r8 = r8.getAnnotations()
            qf.c r0 = Ke.E0.f3489a
            boolean r8 = r8.g(r0)
            if (r8 == 0) goto L_0x015f
            boolean r8 = r7.q()
            if (r8 == 0) goto L_0x0158
            Le.s r8 = new Le.s
            r8.<init>((java.lang.reflect.Method) r4, (boolean) r2, (int) r3)
            goto L_0x012f
        L_0x0158:
            Le.v r8 = new Le.v
            r0 = 1
            r8.<init>(r4, r0, r3, r0)
            goto L_0x012f
        L_0x015f:
            boolean r8 = r7.q()
            if (r8 == 0) goto L_0x016f
            Le.t r8 = new Le.t
            java.lang.Object r0 = y(r7)
            r8.<init>(r4, r0)
            goto L_0x012f
        L_0x016f:
            Le.v r8 = new Le.v
            r0 = 6
            r1 = 2
            r8.<init>(r4, r2, r0, r1)
            goto L_0x012f
        L_0x0177:
            boolean r1 = r0 instanceof Ke.C0794l
            if (r1 == 0) goto L_0x0184
            Ke.l r0 = (Ke.C0794l) r0
            java.lang.reflect.Field r0 = r0.d
            Le.w r0 = l(r7, r8, r0)
            goto L_0x01aa
        L_0x0184:
            boolean r1 = r0 instanceof Ke.C0795m
            if (r1 == 0) goto L_0x01c9
            if (r8 == 0) goto L_0x018f
            Ke.m r0 = (Ke.C0795m) r0
            java.lang.reflect.Method r8 = r0.d
            goto L_0x0195
        L_0x018f:
            Ke.m r0 = (Ke.C0795m) r0
            java.lang.reflect.Method r8 = r0.e
            if (r8 == 0) goto L_0x01b3
        L_0x0195:
            boolean r0 = r7.q()
            if (r0 == 0) goto L_0x01a5
            Le.r r0 = new Le.r
            java.lang.Object r1 = y(r7)
            r0.<init>(r8, r1)
            goto L_0x01aa
        L_0x01a5:
            Le.v r0 = new Le.v
            r0.<init>(r8)
        L_0x01aa:
            Qe.N r7 = r7.r()
            Le.g r7 = a.C0068a.s(r0, r7, r2)
            return r7
        L_0x01b3:
            Ke.v0 r7 = new Ke.v0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "No source found for setter of Java method property: "
            r8.<init>(r1)
            java.lang.reflect.Method r0 = r0.d
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8, r2)
            throw r7
        L_0x01c9:
            boolean r1 = r0 instanceof Ke.C0797o
            if (r1 == 0) goto L_0x0239
            if (r8 == 0) goto L_0x01d4
            Ke.o r0 = (Ke.C0797o) r0
            Ke.k r8 = r0.d
            goto L_0x01da
        L_0x01d4:
            Ke.o r0 = (Ke.C0797o) r0
            Ke.k r8 = r0.e
            if (r8 == 0) goto L_0x0221
        L_0x01da:
            Ke.n0 r0 = r7.s()
            Ke.F r0 = r0.f3506j
            pf.e r8 = r8.d
            java.lang.String r1 = r8.d
            java.lang.String r8 = r8.e
            java.lang.reflect.Method r8 = r0.h(r1, r8)
            if (r8 == 0) goto L_0x0209
            int r0 = r8.getModifiers()
            java.lang.reflect.Modifier.isStatic(r0)
            boolean r0 = r7.q()
            if (r0 == 0) goto L_0x0203
            Le.r r0 = new Le.r
            java.lang.Object r7 = y(r7)
            r0.<init>(r8, r7)
            return r0
        L_0x0203:
            Le.v r7 = new Le.v
            r7.<init>(r8)
            return r7
        L_0x0209:
            Ke.v0 r8 = new Ke.v0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No accessor found for property "
            r0.<init>(r1)
            Ke.n0 r7 = r7.s()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7, r2)
            throw r8
        L_0x0221:
            Ke.v0 r8 = new Ke.v0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No setter found for property "
            r0.<init>(r1)
            Ke.n0 r7 = r7.s()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7, r2)
            throw r8
        L_0x0239:
            Dd.a r7 = new Dd.a
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.context.sdk.samsunganalytics.internal.sender.c.e(Ke.i0, boolean):Le.g");
    }

    public static final long f(String str) {
        int i2;
        boolean z;
        Uf.c cVar;
        long j2;
        String str2 = str;
        int length = str2.length();
        if (length != 0) {
            int i7 = Uf.a.g;
            char charAt = str2.charAt(0);
            if (charAt == '+' || charAt == '-') {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (i2 <= 0 || !n.L0(str2, '-')) {
                z = false;
            } else {
                z = true;
            }
            if (length <= i2) {
                throw new IllegalArgumentException("No components");
            } else if (str2.charAt(i2) == 'P') {
                int i8 = i2 + 1;
                if (i8 != length) {
                    Uf.c cVar2 = null;
                    long j3 = 0;
                    boolean z3 = false;
                    while (i8 < length) {
                        if (str2.charAt(i8) != 'T') {
                            int i10 = i8;
                            while (i10 < str2.length() && (('0' <= (r11 = str2.charAt(i10)) && r11 < ':') || n.v0("+-.", r11))) {
                                i10++;
                            }
                            String substring = str2.substring(i8, i10);
                            j.d(substring, "substring(...)");
                            if (substring.length() != 0) {
                                int length2 = substring.length() + i8;
                                if (length2 < 0 || length2 >= str2.length()) {
                                    throw new IllegalArgumentException("Missing unit for value ".concat(substring));
                                }
                                char charAt2 = str2.charAt(length2);
                                int i11 = length2 + 1;
                                if (!z3) {
                                    if (charAt2 == 'D') {
                                        cVar = Uf.c.DAYS;
                                    } else {
                                        throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + charAt2);
                                    }
                                } else if (charAt2 == 'H') {
                                    cVar = Uf.c.HOURS;
                                } else if (charAt2 == 'M') {
                                    cVar = Uf.c.MINUTES;
                                } else if (charAt2 == 'S') {
                                    cVar = Uf.c.SECONDS;
                                } else {
                                    throw new IllegalArgumentException("Invalid duration ISO time unit: " + charAt2);
                                }
                                if (cVar2 == null || cVar2.compareTo(cVar) > 0) {
                                    int A02 = n.A0(substring, '.', 0, 6);
                                    if (cVar != Uf.c.SECONDS || A02 <= 0) {
                                        j3 = Uf.a.d(j3, Q(K(substring), cVar));
                                    } else {
                                        String substring2 = substring.substring(0, A02);
                                        j.d(substring2, "substring(...)");
                                        long d = Uf.a.d(j3, Q(K(substring2), cVar));
                                        String substring3 = substring.substring(A02);
                                        j.d(substring3, "substring(...)");
                                        double parseDouble = Double.parseDouble(substring3);
                                        double P6 = C0246a.P(parseDouble, cVar, Uf.c.NANOSECONDS);
                                        if (Double.isNaN(P6)) {
                                            throw new IllegalArgumentException("Duration value cannot be NaN.");
                                        } else if (!Double.isNaN(P6)) {
                                            long round = Math.round(P6);
                                            if (-4611686018426999999L > round || round >= 4611686018427000000L) {
                                                double P7 = C0246a.P(parseDouble, cVar, Uf.c.MILLISECONDS);
                                                if (!Double.isNaN(P7)) {
                                                    j2 = r(Math.round(P7));
                                                } else {
                                                    throw new IllegalArgumentException("Cannot round NaN value.");
                                                }
                                            } else {
                                                j2 = s(round);
                                            }
                                            j3 = Uf.a.d(d, j2);
                                        } else {
                                            throw new IllegalArgumentException("Cannot round NaN value.");
                                        }
                                    }
                                    cVar2 = cVar;
                                    i8 = i11;
                                } else {
                                    throw new IllegalArgumentException("Unexpected order of duration components");
                                }
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } else if (z3 || (i8 = i8 + 1) == length) {
                            throw new IllegalArgumentException();
                        } else {
                            z3 = true;
                        }
                    }
                    if (!z) {
                        return j3;
                    }
                    long j8 = ((-(j3 >> 1)) << 1) + ((long) (((int) j3) & 1));
                    int i12 = b.f3828a;
                    return j8;
                }
                throw new IllegalArgumentException();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException("The string is empty");
        }
    }

    public static final G h(C0774x xVar) {
        j.e(xVar, "<this>");
        return new G(xVar);
    }

    public static final Object i(Object obj, boolean z) {
        C1359c cVar;
        j.e(obj, "possiblyPrimitiveType");
        if (!z) {
            return obj;
        }
        C1113m mVar = (C1113m) obj;
        if (!(mVar instanceof C1112l) || (cVar = ((C1112l) mVar).f4647i) == null) {
            return mVar;
        }
        String d = C1358b.b(cVar.g()).d();
        j.d(d, "getInternalName(...)");
        return C1108h.d(d);
    }

    public static final h k(h hVar, h hVar2) {
        j.e(hVar, "first");
        j.e(hVar2, "second");
        if (hVar.isEmpty()) {
            return hVar2;
        }
        if (hVar2.isEmpty()) {
            return hVar;
        }
        return new Re.i(new h[]{hVar, hVar2});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x002b, code lost:
        if (tf.C1301e.n(r1, Qe.C0817g.ANNOTATION_CLASS) == false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        if (pf.i.d(((Ff.u) r0).E) != false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final Le.w l(Ke.i0 r4, boolean r5, java.lang.reflect.Field r6) {
        /*
            Ke.n0 r0 = r4.s()
            Qe.O r0 = r0.j()
            Qe.l r1 = r0.g()
            java.lang.String r2 = "getContainingDeclaration(...)"
            kotlin.jvm.internal.j.d(r1, r2)
            boolean r2 = tf.C1301e.l(r1)
            r3 = 1
            if (r2 != 0) goto L_0x0019
            goto L_0x003c
        L_0x0019:
            Qe.l r1 = r1.g()
            Qe.g r2 = Qe.C0817g.INTERFACE
            boolean r2 = tf.C1301e.n(r1, r2)
            if (r2 != 0) goto L_0x002d
            Qe.g r2 = Qe.C0817g.ANNOTATION_CLASS
            boolean r1 = tf.C1301e.n(r1, r2)
            if (r1 == 0) goto L_0x0046
        L_0x002d:
            boolean r1 = r0 instanceof Ff.u
            if (r1 == 0) goto L_0x003c
            Ff.u r0 = (Ff.u) r0
            lf.G r0 = r0.E
            boolean r0 = pf.i.d(r0)
            if (r0 == 0) goto L_0x003c
            goto L_0x0046
        L_0x003c:
            int r0 = r6.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 != 0) goto L_0x0086
        L_0x0046:
            java.lang.String r0 = "field"
            if (r5 == 0) goto L_0x0064
            boolean r5 = r4.q()
            if (r5 == 0) goto L_0x005a
            Le.j r5 = new Le.j
            java.lang.Object r4 = y(r4)
            r5.<init>(r4, r6)
            return r5
        L_0x005a:
            Le.l r4 = new Le.l
            kotlin.jvm.internal.j.e(r6, r0)
            r5 = 0
            r4.<init>(r6, r3, r5)
            return r4
        L_0x0064:
            boolean r5 = r4.q()
            if (r5 == 0) goto L_0x0078
            Le.n r5 = new Le.n
            boolean r0 = m(r4)
            java.lang.Object r4 = y(r4)
            r5.<init>(r6, r0, r4)
            return r5
        L_0x0078:
            Le.p r5 = new Le.p
            boolean r4 = m(r4)
            kotlin.jvm.internal.j.e(r6, r0)
            r0 = 0
            r5.<init>(r6, r4, r3, r0)
            return r5
        L_0x0086:
            Ke.n0 r0 = r4.s()
            Qe.O r0 = r0.j()
            Re.h r0 = r0.getAnnotations()
            qf.c r1 = Ke.E0.f3489a
            boolean r0 = r0.g(r1)
            r1 = 0
            if (r0 == 0) goto L_0x00cb
            if (r5 == 0) goto L_0x00b0
            boolean r4 = r4.q()
            if (r4 == 0) goto L_0x00a9
            Le.k r4 = new Le.k
            r4.<init>(r6, r1)
            return r4
        L_0x00a9:
            Le.l r4 = new Le.l
            r5 = 1
            r4.<init>(r6, r3, r5)
            return r4
        L_0x00b0:
            boolean r5 = r4.q()
            if (r5 == 0) goto L_0x00c0
            Le.o r5 = new Le.o
            boolean r4 = m(r4)
            r5.<init>((java.lang.reflect.Field) r6, (boolean) r4, (boolean) r1)
            return r5
        L_0x00c0:
            Le.p r5 = new Le.p
            boolean r4 = m(r4)
            r0 = 1
            r5.<init>(r6, r4, r3, r0)
            return r5
        L_0x00cb:
            if (r5 == 0) goto L_0x00d4
            Le.l r4 = new Le.l
            r5 = 2
            r4.<init>(r6, r1, r5)
            return r4
        L_0x00d4:
            Le.p r5 = new Le.p
            boolean r4 = m(r4)
            r0 = 2
            r5.<init>(r6, r4, r1, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.context.sdk.samsunganalytics.internal.sender.c.l(Ke.i0, boolean, java.lang.reflect.Field):Le.w");
    }

    public static final boolean m(i0 i0Var) {
        return !a0.e(i0Var.s().j().getType());
    }

    public static final boolean n(C0774x xVar, M m, Set set) {
        C0820j jVar;
        List list;
        V v;
        boolean z;
        if (j.a(xVar.s0(), m)) {
            return true;
        }
        C0819i g = xVar.s0().g();
        if (g instanceof C0820j) {
            jVar = (C0820j) g;
        } else {
            jVar = null;
        }
        if (jVar != null) {
            list = jVar.j();
        } else {
            list = null;
        }
        r q12 = C1194l.q1(xVar.e0());
        if (!(q12 instanceof Collection) || !((Collection) q12).isEmpty()) {
            Iterator it = q12.iterator();
            do {
                Sf.b bVar = (Sf.b) it;
                if (bVar.e.hasNext()) {
                    x xVar2 = (x) bVar.next();
                    int i2 = xVar2.f4950a;
                    P p6 = (P) xVar2.b;
                    if (list != null) {
                        v = (V) C1194l.O0(i2, list);
                    } else {
                        v = null;
                    }
                    if ((v == null || set == null || !set.contains(v)) && !p6.c()) {
                        C0774x b5 = p6.b();
                        j.d(b5, "getType(...)");
                        z = n(b5, m, set);
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                }
            } while (!z);
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        if (r1 <= r2) goto L_0x002a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Ef.d o(qf.C1236c r7, Gf.m r8, Qe.C r9, java.io.InputStream r10) {
        /*
            java.lang.String r0 = "fqName"
            kotlin.jvm.internal.j.e(r7, r0)
            java.lang.String r0 = "module"
            kotlin.jvm.internal.j.e(r9, r0)
            mf.a r0 = mf.C1178a.f     // Catch:{ all -> 0x0066 }
            mf.a r6 = a.C0068a.O(r10)     // Catch:{ all -> 0x0066 }
            mf.a r0 = mf.C1178a.f     // Catch:{ all -> 0x0066 }
            int r1 = r6.f4952c     // Catch:{ all -> 0x0066 }
            java.lang.String r2 = "ourVersion"
            kotlin.jvm.internal.j.e(r0, r2)     // Catch:{ all -> 0x0066 }
            int r2 = r0.f4952c     // Catch:{ all -> 0x0066 }
            int r3 = r0.b     // Catch:{ all -> 0x0066 }
            int r4 = r6.b     // Catch:{ all -> 0x0066 }
            if (r4 != 0) goto L_0x0026
            if (r3 != 0) goto L_0x0069
            if (r1 != r2) goto L_0x0069
            goto L_0x002a
        L_0x0026:
            if (r4 != r3) goto L_0x0069
            if (r1 > r2) goto L_0x0069
        L_0x002a:
            rf.h r1 = new rf.h     // Catch:{ all -> 0x0066 }
            r1.<init>()     // Catch:{ all -> 0x0066 }
            mf.C1179b.a(r1)     // Catch:{ all -> 0x0066 }
            lf.a r2 = lf.E.n     // Catch:{ all -> 0x0066 }
            r2.getClass()     // Catch:{ all -> 0x0066 }
            rf.f r3 = new rf.f     // Catch:{ all -> 0x0066 }
            r3.<init>(r10)     // Catch:{ all -> 0x0066 }
            java.lang.Object r1 = r2.a(r3, r1)     // Catch:{ all -> 0x0066 }
            rf.b r1 = (rf.C1252b) r1     // Catch:{ all -> 0x0066 }
            r2 = 0
            r3.a(r2)     // Catch:{ u -> 0x0061 }
            boolean r2 = r1.isInitialized()     // Catch:{ all -> 0x0066 }
            if (r2 == 0) goto L_0x0050
            lf.E r1 = (lf.E) r1     // Catch:{ all -> 0x0066 }
        L_0x004e:
            r5 = r1
            goto L_0x006b
        L_0x0050:
            Dd.a r7 = new Dd.a     // Catch:{ all -> 0x0066 }
            r7.<init>()     // Catch:{ all -> 0x0066 }
            rf.u r8 = new rf.u     // Catch:{ all -> 0x0066 }
            java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x0066 }
            r8.<init>(r7)     // Catch:{ all -> 0x0066 }
            r8.d = r1     // Catch:{ all -> 0x0066 }
            throw r8     // Catch:{ all -> 0x0066 }
        L_0x0061:
            r0 = move-exception
            r7 = r0
            r7.d = r1     // Catch:{ all -> 0x0066 }
            throw r7     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r0 = move-exception
            r7 = r0
            goto L_0x009a
        L_0x0069:
            r1 = 0
            goto L_0x004e
        L_0x006b:
            r10.close()
            if (r5 == 0) goto L_0x0079
            Ef.d r1 = new Ef.d
            r2 = r7
            r3 = r8
            r4 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return r1
        L_0x0079:
            java.lang.UnsupportedOperationException r7 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Kotlin built-in definition format version is not supported: expected "
            r8.<init>(r9)
            r8.append(r0)
            java.lang.String r9 = ", actual "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r9 = ". Please update Kotlin"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x009a:
            throw r7     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            r8 = r0
            B1.a.g(r10, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.context.sdk.samsunganalytics.internal.sender.c.o(qf.c, Gf.m, Qe.C, java.io.InputStream):Ef.d");
    }

    public static final G p(C0774x xVar, d0 d0Var, V v) {
        d0 d0Var2;
        j.e(xVar, "type");
        j.e(d0Var, "projectionKind");
        if (v != null) {
            d0Var2 = v.t();
        } else {
            d0Var2 = null;
        }
        if (d0Var2 == d0Var) {
            d0Var = d0.INVARIANT;
        }
        return new G(xVar, d0Var);
    }

    public static final long q(long j2) {
        long j3 = (j2 << 1) + 1;
        int i2 = Uf.a.g;
        int i7 = b.f3828a;
        return j3;
    }

    public static final long r(long j2) {
        if (-4611686018426L > j2 || j2 >= 4611686018427L) {
            return q(a.m(j2, -4611686018427387903L, 4611686018427387903L));
        }
        return s(j2 * ((long) 1000000));
    }

    public static final long s(long j2) {
        long j3 = j2 << 1;
        int i2 = Uf.a.g;
        int i7 = b.f3828a;
        return j3;
    }

    public static final C1296b t(Enum[] enumArr) {
        j.e(enumArr, "entries");
        return new C1296b(enumArr);
    }

    public static int u(float f, int i2, int i7) {
        if (i2 == i7 || f <= 0.0f) {
            return i2;
        }
        if (f >= 1.0f) {
            return i7;
        }
        float f5 = ((float) ((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f;
        float c5 = c(((float) ((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f);
        float c6 = c(((float) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f);
        float c8 = c(((float) (i2 & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f);
        float c10 = c(((float) ((i7 >> 16) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f);
        float c11 = c(((float) ((i7 >> 8) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f);
        float c12 = c(((float) (i7 & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f);
        float a7 = C0212a.a(((float) ((i7 >> 24) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, f5, f, f5);
        float a10 = C0212a.a(c10, c5, f, c5);
        float a11 = C0212a.a(c11, c6, f, c6);
        float a12 = C0212a.a(c12, c8, f, c8);
        int round = Math.round(d(a10) * 255.0f) << 16;
        return Math.round(d(a12) * 255.0f) | round | (Math.round(a7 * 255.0f) << 24) | (Math.round(d(a11) * 255.0f) << 8);
    }

    public static final void v(C0774x xVar, B b5, LinkedHashSet linkedHashSet, Set set) {
        C0820j jVar;
        List list;
        V v;
        C0819i g = xVar.s0().g();
        if (!(g instanceof V)) {
            C0819i g3 = xVar.s0().g();
            if (g3 instanceof C0820j) {
                jVar = (C0820j) g3;
            } else {
                jVar = null;
            }
            if (jVar != null) {
                list = jVar.j();
            } else {
                list = null;
            }
            int i2 = 0;
            for (P p6 : xVar.e0()) {
                int i7 = i2 + 1;
                if (list != null) {
                    v = (V) C1194l.O0(i2, list);
                } else {
                    v = null;
                }
                if ((v == null || set == null || !set.contains(v)) && !p6.c() && !C1194l.G0(linkedHashSet, p6.b().s0().g()) && !j.a(p6.b().s0(), b5.s0())) {
                    C0774x b8 = p6.b();
                    j.d(b8, "getType(...)");
                    v(b8, b5, linkedHashSet, set);
                }
                i2 = i7;
            }
        } else if (!j.a(xVar.s0(), b5.s0())) {
            linkedHashSet.add(g);
        } else {
            for (C0774x xVar2 : ((V) g).getUpperBounds()) {
                j.b(xVar2);
                v(xVar2, b5, linkedHashSet, set);
            }
        }
    }

    public static a w(Context context, int i2, C0732c cVar) {
        if (b == null) {
            synchronized (c.class) {
                try {
                    if (b == null) {
                        if (i2 == 0) {
                            b = new Id.c(context, cVar);
                        } else if (i2 == 2 || i2 == 3) {
                            b = new Jd.c(context, cVar);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return b;
    }

    public static Q x(C1240g gVar, C0816f fVar) {
        if (gVar == null) {
            a(19);
            throw null;
        } else if (fVar != null) {
            Collection d = fVar.d();
            if (d.size() != 1) {
                return null;
            }
            for (Q q : ((C0848i) d.iterator().next()).B()) {
                if (q.getName().equals(gVar)) {
                    return q;
                }
            }
            return null;
        } else {
            a(20);
            throw null;
        }
    }

    public static final Object y(i0 i0Var) {
        n0 s = i0Var.s();
        return C0068a.k(s.m, s.j());
    }

    public static final i z(C0774x xVar) {
        j.e(xVar, "<this>");
        i f = xVar.s0().f();
        j.d(f, "getBuiltIns(...)");
        return f;
    }

    public abstract String g();

    public abstract ee.a0 j(int i2);

    public String toString() {
        switch (this.f4197a) {
            case 18:
                return g();
            default:
                return super.toString();
        }
    }
}
