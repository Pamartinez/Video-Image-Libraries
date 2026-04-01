package B1;

import Df.x;
import Df.z;
import Ge.c;
import Ge.d;
import Ge.e;
import He.F;
import Hf.C0774x;
import Hf.d0;
import Kf.h;
import Kf.i;
import N2.f;
import N2.g;
import N2.y;
import Qe.C0831v;
import R2.k;
import R2.l;
import S2.m;
import S2.n;
import S2.t;
import Te.Q;
import Tf.v;
import Ve.b;
import a.C0068a;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;
import com.google.protobuf.ByteString;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import jf.C1109i;
import jf.r;
import kotlin.jvm.internal.j;
import lf.C1156i;
import ne.C1182C;
import ne.C1194l;
import ne.C1196n;
import oe.C1217f;
import oe.C1220i;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import xf.C1353d;
import yf.C1358b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {
    public static Context d;
    public static Boolean e;
    public static Boolean f;
    public static Boolean g;

    /* renamed from: h  reason: collision with root package name */
    public static Boolean f37h;

    /* renamed from: i  reason: collision with root package name */
    public static Boolean f38i;

    public static boolean E(Context context) {
        if (context.getResources().getConfiguration().fontScale >= 1.3f) {
            return true;
        }
        return false;
    }

    public static boolean F(l lVar) {
        int length = lVar.e.length;
        if (length < 2) {
            return true;
        }
        int i2 = ((k) lVar.d(0)).d;
        for (int i7 = 0; i7 < length; i7++) {
            k kVar = (k) lVar.d(i7);
            if (kVar.d != i2) {
                return false;
            }
            i2 += kVar.c();
        }
        return true;
    }

    public static final boolean G(int i2, String str) {
        char charAt = str.charAt(i2);
        if ('A' > charAt || charAt >= '[') {
            return false;
        }
        return true;
    }

    public static boolean H(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (f == null) {
            f = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        f.booleanValue();
        if (g == null) {
            g = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
        }
        if (g.booleanValue()) {
            return true;
        }
        return false;
    }

    public static boolean I(char c5) {
        if (Character.isWhitespace(c5) || Character.isSpaceChar(c5)) {
            return true;
        }
        return false;
    }

    public static String J(n nVar, int i2) {
        long j2;
        StringBuffer stringBuffer = new StringBuffer(20);
        stringBuffer.append("#");
        if (nVar instanceof m) {
            j2 = ((m) nVar).d;
        } else {
            j2 = (long) nVar.g();
        }
        if (i2 == 4) {
            stringBuffer.append(new String(new char[]{Character.forDigit(((int) j2) & 15, 16)}));
        } else if (i2 == 8) {
            stringBuffer.append(L2.a.B((int) j2));
        } else if (i2 == 16) {
            stringBuffer.append(L2.a.D((int) j2));
        } else if (i2 == 32) {
            stringBuffer.append(L2.a.E((int) j2));
        } else if (i2 == 64) {
            stringBuffer.append(L2.a.F(j2));
        } else {
            throw new RuntimeException("shouldn't happen");
        }
        return stringBuffer.toString();
    }

    public static String K(n nVar) {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append('#');
        if (nVar instanceof S2.k) {
            stringBuffer.append("null");
        } else {
            stringBuffer.append(nVar.e());
            stringBuffer.append(' ');
            stringBuffer.append(nVar.a());
        }
        return stringBuffer.toString();
    }

    public static int L(int i2, int i7) {
        if ((i2 & 15) != i2) {
            throw new IllegalArgumentException("low out of range 0..15");
        } else if ((i7 & 15) == i7) {
            return i2 | (i7 << 4);
        } else {
            throw new IllegalArgumentException("high out of range 0..15");
        }
    }

    public static short M(g gVar, int i2) {
        if ((i2 & ScoverState.TYPE_NFC_SMART_COVER) == i2) {
            int i7 = gVar.b.f428a;
            if ((i7 & ScoverState.TYPE_NFC_SMART_COVER) == i7) {
                return (short) (i7 | (i2 << 8));
            }
            throw new IllegalArgumentException("opcode out of range 0..255");
        }
        throw new IllegalArgumentException("arg out of range 0..255");
    }

    public static short N(N2.l lVar) {
        int i2 = lVar.b.f428a;
        if (i2 >= 255 && i2 <= 65535) {
            return (short) i2;
        }
        throw new IllegalArgumentException("extended opcode out of range 255..65535");
    }

    public static C1240g O(C1240g gVar, String str, String str2, int i2) {
        boolean z;
        char charAt;
        char charAt2;
        Object obj;
        if ((i2 & 4) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((i2 & 8) != 0) {
            str2 = null;
        }
        if (!gVar.e) {
            String c5 = gVar.c();
            if (v.t0(c5, str) && c5.length() != str.length() && ('a' > (charAt = c5.charAt(str.length())) || charAt >= '{')) {
                if (str2 != null) {
                    return C1240g.e(str2.concat(Tf.n.H0(c5, str)));
                }
                if (!z) {
                    return gVar;
                }
                String H02 = Tf.n.H0(c5, str);
                if (H02.length() != 0 && G(0, H02)) {
                    if (H02.length() != 1 && G(1, H02)) {
                        Iterator it = new c(0, H02.length() - 1, 1).iterator();
                        while (true) {
                            if (!((d) it).f) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            if (!G(((Number) obj).intValue(), H02)) {
                                break;
                            }
                        }
                        Integer num = (Integer) obj;
                        if (num != null) {
                            int intValue = num.intValue() - 1;
                            String substring = H02.substring(0, intValue);
                            j.d(substring, "substring(...)");
                            String U = U(substring);
                            String substring2 = H02.substring(intValue);
                            j.d(substring2, "substring(...)");
                            H02 = U.concat(substring2);
                        } else {
                            H02 = U(H02);
                        }
                    } else if (H02.length() != 0 && 'A' <= (charAt2 = H02.charAt(0)) && charAt2 < '[') {
                        char lowerCase = Character.toLowerCase(charAt2);
                        String substring3 = H02.substring(1);
                        j.d(substring3, "substring(...)");
                        H02 = lowerCase + substring3;
                    }
                }
                if (C1240g.f(H02)) {
                    return C1240g.e(H02);
                }
            }
        }
        return null;
    }

    public static int P(e eVar) {
        Ee.a aVar = Ee.d.d;
        try {
            return C0068a.L(eVar);
        } catch (IllegalArgumentException e7) {
            throw new NoSuchElementException(e7.getMessage());
        }
    }

    public static String Q(l lVar) {
        int i2;
        int i7;
        int length = lVar.e.length;
        StringBuilder sb2 = new StringBuilder(30);
        sb2.append("{");
        if (length != 0) {
            if (length != 1) {
                k kVar = (k) lVar.d(length - 1);
                if (kVar.c() == 2 && (i2 = kVar.d) != (i7 = i2 + 1)) {
                    kVar = k.d(i7, kVar.e);
                }
                sb2.append(((k) lVar.d(0)).e());
                sb2.append("..");
                sb2.append(kVar.e());
            } else {
                sb2.append(((k) lVar.d(0)).e());
            }
        }
        sb2.append("}");
        return sb2.toString();
    }

    public static final Object R(Set set, Enum enumR, Enum enumR2, Enum enumR3, boolean z) {
        Enum enumR4;
        if (z) {
            if (set.contains(enumR)) {
                enumR4 = enumR;
            } else if (set.contains(enumR2)) {
                enumR4 = enumR2;
            } else {
                enumR4 = null;
            }
            if (j.a(enumR4, enumR) && j.a(enumR3, enumR2)) {
                return null;
            }
            if (enumR3 == null) {
                return enumR4;
            }
            return enumR3;
        }
        if (enumR3 != null) {
            set = C1194l.p1(C1182C.c0(set, enumR3));
        }
        return C1194l.c1(set);
    }

    public static Set S(Object obj) {
        Set singleton = Collections.singleton(obj);
        j.d(singleton, "singleton(...)");
        return singleton;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0030, code lost:
        if (r0.charAt(r1.length()) == '.') goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final qf.C1236c T(qf.C1236c r3, qf.C1236c r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "prefix"
            kotlin.jvm.internal.j.e(r4, r0)
            boolean r0 = r3.equals(r4)
            if (r0 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            boolean r0 = r4.d()
            if (r0 == 0) goto L_0x0018
            goto L_0x0032
        L_0x0018:
            java.lang.String r0 = r3.b()
            java.lang.String r1 = r4.b()
            boolean r2 = Tf.v.t0(r0, r1)
            if (r2 == 0) goto L_0x0065
            int r1 = r1.length()
            char r0 = r0.charAt(r1)
            r1 = 46
            if (r0 != r1) goto L_0x0065
        L_0x0032:
            boolean r0 = r4.d()
            if (r0 == 0) goto L_0x0039
            goto L_0x0065
        L_0x0039:
            boolean r0 = r3.equals(r4)
            if (r0 == 0) goto L_0x0047
            qf.c r3 = qf.C1236c.f5035c
            java.lang.String r4 = "ROOT"
            kotlin.jvm.internal.j.d(r3, r4)
            return r3
        L_0x0047:
            qf.c r0 = new qf.c
            java.lang.String r3 = r3.b()
            java.lang.String r4 = r4.b()
            int r4 = r4.length()
            int r4 = r4 + 1
            java.lang.String r3 = r3.substring(r4)
            java.lang.String r4 = "substring(...)"
            kotlin.jvm.internal.j.d(r3, r4)
            r0.<init>((java.lang.String) r3)
            return r0
        L_0x0065:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: B1.a.T(qf.c, qf.c):qf.c");
    }

    public static final String U(String str) {
        j.e(str, "<this>");
        StringBuilder sb2 = new StringBuilder(str.length());
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if ('A' <= charAt && charAt < '[') {
                charAt = Character.toLowerCase(charAt);
            }
            sb2.append(charAt);
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public static E2.k V(Object obj) {
        return new E2.k(obj.getClass().getSimpleName());
    }

    public static boolean W(int i2) {
        if (i2 == (i2 & ScoverState.TYPE_NFC_SMART_COVER)) {
            return true;
        }
        return false;
    }

    public static boolean X(int i2) {
        if (i2 == (i2 & 15)) {
            return true;
        }
        return false;
    }

    public static boolean Y(int i2) {
        if (i2 == (65535 & i2)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [Ge.c, Ge.e] */
    public static e Z(int i2, int i7) {
        if (i7 > Integer.MIN_VALUE) {
            return new c(i2, i7 - 1, 1);
        }
        e eVar = e.g;
        return e.g;
    }

    public static String a(N2.l lVar) {
        int l = ((y) lVar).l();
        int i2 = 0;
        if (l == ((short) l)) {
            char[] cArr = new char[5];
            if (l < 0) {
                cArr[0] = '-';
                l = -l;
            } else {
                cArr[0] = '+';
            }
            while (i2 < 4) {
                cArr[4 - i2] = Character.forDigit(l & 15, 16);
                l >>= 4;
                i2++;
            }
            return new String(cArr);
        }
        char[] cArr2 = new char[9];
        if (l < 0) {
            cArr2[0] = '-';
            l = -l;
        } else {
            cArr2[0] = '+';
        }
        while (i2 < 8) {
            cArr2[8 - i2] = Character.forDigit(l & 15, 16);
            l >>= 4;
            i2++;
        }
        return new String(cArr2);
    }

    public static String c(N2.l lVar) {
        int c5 = ((y) lVar).f.c();
        if (c5 == ((char) c5)) {
            return L2.a.D(c5);
        }
        return L2.a.E(c5);
    }

    public static C1220i d(C1220i iVar) {
        C1217f fVar = iVar.d;
        fVar.b();
        if (fVar.l > 0) {
            return iVar;
        }
        return C1220i.e;
    }

    public static final String e(String str) {
        char charAt;
        j.e(str, "<this>");
        if (str.length() == 0 || 'a' > (charAt = str.charAt(0)) || charAt >= '{') {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(str.length());
        sb2.append(Character.toUpperCase(charAt));
        sb2.append(str, 1, str.length());
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public static byte f(long j2) {
        boolean z;
        if ((j2 >> 8) == 0) {
            z = true;
        } else {
            z = false;
        }
        F.k(z, "out of range: %s", j2);
        return (byte) ((int) j2);
    }

    public static final void g(Closeable closeable, Throwable th) {
        if (closeable == null) {
            return;
        }
        if (th == null) {
            closeable.close();
            return;
        }
        try {
            closeable.close();
        } catch (Throwable th2) {
            F.e(th, th2);
        }
    }

    public static short j(int i2, int i7) {
        if ((i2 & ScoverState.TYPE_NFC_SMART_COVER) != i2) {
            throw new IllegalArgumentException("low out of range 0..255");
        } else if ((i7 & ScoverState.TYPE_NFC_SMART_COVER) == i7) {
            return (short) (i2 | (i7 << 8));
        } else {
            throw new IllegalArgumentException("high out of range 0..255");
        }
    }

    public static float k(float f5, float f8, float f10) {
        if (f8 > f10) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f10 + " is less than minimum " + f8 + '.');
        } else if (f5 < f8) {
            return f8;
        } else {
            if (f5 > f10) {
                return f10;
            }
            return f5;
        }
    }

    public static int l(int i2, int i7, int i8) {
        if (i7 > i8) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i8 + " is less than minimum " + i7 + '.');
        } else if (i2 < i7) {
            return i7;
        } else {
            if (i2 > i8) {
                return i8;
            }
            return i2;
        }
    }

    public static long m(long j2, long j3, long j8) {
        if (j3 > j8) {
            StringBuilder j10 = N2.j.j(j8, "Cannot coerce value to an empty range: maximum ", " is less than minimum ");
            j10.append(j3);
            j10.append('.');
            throw new IllegalArgumentException(j10.toString());
        } else if (j2 < j3) {
            return j3;
        } else {
            if (j2 > j8) {
                return j8;
            }
            return j2;
        }
    }

    public static final i o(d0 d0Var) {
        int i2 = h.f3520a[d0Var.ordinal()];
        if (i2 == 1) {
            return i.INV;
        }
        if (i2 == 2) {
            return i.IN;
        }
        if (i2 == 3) {
            return i.OUT;
        }
        throw new RuntimeException();
    }

    public static final ArrayList p(ArrayList arrayList, Collection collection, C0831v vVar) {
        C0774x xVar;
        Collection collection2 = collection;
        j.e(collection2, "oldValueParameters");
        arrayList.size();
        collection2.size();
        ArrayList r1 = C1194l.r1(arrayList, collection2);
        ArrayList arrayList2 = new ArrayList(C1196n.w0(r1, 10));
        Iterator it = r1.iterator();
        while (it.hasNext()) {
            me.i iVar = (me.i) it.next();
            C0774x xVar2 = (C0774x) iVar.d;
            Q q = (Q) iVar.e;
            int i2 = q.f3770j;
            Re.h annotations = q.getAnnotations();
            C1240g name = q.getName();
            j.d(name, "getName(...)");
            boolean F02 = q.F0();
            boolean z = q.l;
            boolean z3 = q.m;
            if (q.n != null) {
                xVar = C1353d.j(vVar).f().f(xVar2);
            } else {
                xVar = null;
            }
            C0774x xVar3 = xVar;
            Qe.Q source = q.getSource();
            j.d(source, "getSource(...)");
            arrayList2.add(new Q(vVar, (Q) null, i2, annotations, name, xVar2, F02, z, z3, xVar3, source));
        }
        return arrayList2;
    }

    public static String q(N2.l lVar) {
        f fVar = (f) lVar;
        if (fVar.g < 0) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder(20);
        int l = fVar.l();
        sb2.append(fVar.f.e());
        sb2.append('@');
        if (l < 65536) {
            sb2.append(L2.a.D(l));
        } else {
            sb2.append(L2.a.E(l));
        }
        return sb2.toString();
    }

    public static String r(N2.l lVar) {
        S2.a aVar = ((f) lVar).f;
        if (aVar instanceof t) {
            return ((t) aVar).f();
        }
        return aVar.a();
    }

    public static final boolean s(char c5, char c6, boolean z) {
        if (c5 == c6) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c5);
        char upperCase2 = Character.toUpperCase(c6);
        if (upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)) {
            return true;
        }
        return false;
    }

    public static String t(ByteString byteString) {
        StringBuilder sb2 = new StringBuilder(byteString.size());
        for (int i2 = 0; i2 < byteString.size(); i2++) {
            byte p6 = byteString.p(i2);
            if (p6 == 34) {
                sb2.append("\\\"");
            } else if (p6 == 39) {
                sb2.append("\\'");
            } else if (p6 != 92) {
                switch (p6) {
                    case 7:
                        sb2.append("\\a");
                        break;
                    case 8:
                        sb2.append("\\b");
                        break;
                    case 9:
                        sb2.append("\\t");
                        break;
                    case 10:
                        sb2.append("\\n");
                        break;
                    case 11:
                        sb2.append("\\v");
                        break;
                    case 12:
                        sb2.append("\\f");
                        break;
                    case 13:
                        sb2.append("\\r");
                        break;
                    default:
                        if (p6 >= 32 && p6 <= 126) {
                            sb2.append((char) p6);
                            break;
                        } else {
                            sb2.append('\\');
                            sb2.append((char) (((p6 >>> 6) & 3) + 48));
                            sb2.append((char) (((p6 >>> 3) & 7) + 48));
                            sb2.append((char) ((p6 & 7) + 48));
                            break;
                        }
                        break;
                }
            } else {
                sb2.append("\\\\");
            }
        }
        return sb2.toString();
    }

    public static ColorStateList u(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        ColorStateList colorStateList;
        if (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) == null) {
            return typedArray.getColorStateList(i2);
        }
        return colorStateList;
    }

    public static ColorStateList v(Context context, TintTypedArray tintTypedArray, int i2) {
        int resourceId;
        ColorStateList colorStateList;
        if (!tintTypedArray.hasValue(i2) || (resourceId = tintTypedArray.getResourceId(i2, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) == null) {
            return tintTypedArray.getColorStateList(i2);
        }
        return colorStateList;
    }

    public static Drawable w(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        Drawable drawable;
        if (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (drawable = AppCompatResources.getDrawable(context, resourceId)) == null) {
            return typedArray.getDrawable(i2);
        }
        return drawable;
    }

    /* JADX WARNING: type inference failed for: r3v5, types: [Af.p] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final df.C0935D x(Qe.C0816f r3) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.j.e(r3, r0)
            int r0 = xf.C1353d.f5167a
            Hf.B r3 = r3.i()
            Hf.M r3 = r3.s0()
            java.util.Collection r3 = r3.h()
            java.util.Iterator r3 = r3.iterator()
        L_0x0017:
            boolean r0 = r3.hasNext()
            r1 = 0
            if (r0 == 0) goto L_0x004c
            java.lang.Object r0 = r3.next()
            Hf.x r0 = (Hf.C0774x) r0
            boolean r2 = Ne.i.x(r0)
            if (r2 != 0) goto L_0x0017
            Hf.M r0 = r0.s0()
            Qe.i r0 = r0.g()
            int r2 = tf.C1301e.f5137a
            Qe.g r2 = Qe.C0817g.CLASS
            boolean r2 = tf.C1301e.n(r0, r2)
            if (r2 != 0) goto L_0x0044
            Qe.g r2 = Qe.C0817g.ENUM_CLASS
            boolean r2 = tf.C1301e.n(r0, r2)
            if (r2 == 0) goto L_0x0017
        L_0x0044:
            java.lang.String r3 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            kotlin.jvm.internal.j.c(r0, r3)
            Qe.f r0 = (Qe.C0816f) r0
            goto L_0x004d
        L_0x004c:
            r0 = r1
        L_0x004d:
            if (r0 != 0) goto L_0x0050
            return r1
        L_0x0050:
            Af.p r3 = r0.c0()
            boolean r2 = r3 instanceof df.C0935D
            if (r2 == 0) goto L_0x005b
            r1 = r3
            df.D r1 = (df.C0935D) r1
        L_0x005b:
            if (r1 != 0) goto L_0x0062
            df.D r3 = x(r0)
            return r3
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: B1.a.x(Qe.f):df.D");
    }

    public static b y(z zVar, boolean z, boolean z3, Boolean bool, boolean z7, b bVar, pf.f fVar) {
        x xVar;
        C1156i iVar;
        r rVar;
        C1109i iVar2;
        C1358b bVar2;
        j.e(zVar, "container");
        Qe.Q q = zVar.f3370c;
        j.e(fVar, "jvmMetadataVersion");
        if (z) {
            if (bool != null) {
                if (zVar instanceof x) {
                    x xVar2 = (x) zVar;
                    if (xVar2.g == C1156i.INTERFACE) {
                        return F.E(bVar, xVar2.f.d(C1240g.e("DefaultImpls")), fVar);
                    }
                }
                if (bool.booleanValue() && (zVar instanceof Df.y)) {
                    if (q instanceof C1109i) {
                        iVar2 = (C1109i) q;
                    } else {
                        iVar2 = null;
                    }
                    if (iVar2 != null) {
                        bVar2 = iVar2.e;
                    } else {
                        bVar2 = null;
                    }
                    if (bVar2 != null) {
                        String d2 = bVar2.d();
                        j.d(d2, "getInternalName(...)");
                        C1236c cVar = new C1236c(v.r0(d2, '/', '.'));
                        C1236c e7 = cVar.e();
                        C1240g f5 = cVar.f();
                        j.d(f5, "shortName(...)");
                        return F.E(bVar, new C1235b(e7, f5), fVar);
                    }
                }
            } else {
                throw new IllegalStateException(("isConst should not be null for property (container=" + zVar + ')').toString());
            }
        }
        if (z3 && (zVar instanceof x)) {
            x xVar3 = (x) zVar;
            if (xVar3.g == C1156i.COMPANION_OBJECT && (xVar = xVar3.e) != null && ((iVar = xVar.g) == C1156i.CLASS || iVar == C1156i.ENUM_CLASS || (z7 && (iVar == C1156i.INTERFACE || iVar == C1156i.ANNOTATION_CLASS)))) {
                Qe.Q q10 = xVar.f3370c;
                if (q10 instanceof r) {
                    rVar = (r) q10;
                } else {
                    rVar = null;
                }
                if (rVar != null) {
                    return rVar.d;
                }
                return null;
            }
        }
        if ((zVar instanceof Df.y) && (q instanceof C1109i)) {
            C1109i iVar3 = (C1109i) q;
            b bVar3 = iVar3.f;
            if (bVar3 == null) {
                return F.E(bVar, iVar3.b(), fVar);
            }
            return bVar3;
        }
        return null;
    }

    public static boolean z(String str) {
        boolean z;
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        try {
            de.f fVar = new de.e(fileInputStream.getFD()).f4230a;
            if (fVar == null || !fVar.c()) {
                z = false;
            } else {
                z = true;
            }
            fileInputStream.close();
            return z;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public abstract String A(N2.l lVar);

    public abstract String B(N2.l lVar);

    public abstract boolean D(g gVar);

    public abstract void a0(P0.b bVar, N2.l lVar);

    public boolean b(y yVar) {
        return this instanceof O2.b;
    }

    public abstract int h();

    public BitSet n(g gVar) {
        return new BitSet();
    }
}
