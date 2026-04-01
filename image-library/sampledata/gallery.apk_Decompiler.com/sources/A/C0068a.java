package a;

import D1.f;
import Dd.C0730a;
import Ee.a;
import Ee.d;
import Ge.e;
import He.C0748d;
import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Hf.Y;
import Hf.a0;
import Ke.E0;
import Ke.v0;
import Le.C;
import Le.g;
import Ne.i;
import Qe.C0812b;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0821k;
import Qe.C0822l;
import Qe.O;
import Re.b;
import Re.h;
import Te.K;
import Te.Q;
import Te.u;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import c0.C0086a;
import df.C0937F;
import ef.C0993a;
import i.C0212a;
import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.c;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import mf.C1178a;
import ne.C1194l;
import ne.C1196n;
import ne.y;
import qf.C1236c;
import qf.C1240g;
import sf.C1290q;
import tf.C1301e;
import tf.C1305i;
import xf.C1353d;

/* renamed from: a.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0068a {
    public static String b;

    /* renamed from: c  reason: collision with root package name */
    public static PackageInfo f970c;

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f971a;

    public /* synthetic */ C0068a(int i2) {
        this.f971a = i2;
    }

    public static final Class A(C0748d dVar) {
        j.e(dVar, "<this>");
        Class b5 = ((c) dVar).b();
        j.c(b5, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return b5;
    }

    public static final Class B(C0748d dVar) {
        j.e(dVar, "<this>");
        Class b5 = ((c) dVar).b();
        if (!b5.isPrimitive()) {
            return b5;
        }
        String name = b5.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (!name.equals("double")) {
                    return b5;
                }
                return Double.class;
            case 104431:
                if (!name.equals("int")) {
                    return b5;
                }
                return Integer.class;
            case 3039496:
                if (!name.equals("byte")) {
                    return b5;
                }
                return Byte.class;
            case 3052374:
                if (!name.equals("char")) {
                    return b5;
                }
                return Character.class;
            case 3327612:
                if (!name.equals("long")) {
                    return b5;
                }
                return Long.class;
            case 3625364:
                if (!name.equals("void")) {
                    return b5;
                }
                return Void.class;
            case 64711720:
                if (!name.equals("boolean")) {
                    return b5;
                }
                return Boolean.class;
            case 97526364:
                if (!name.equals("float")) {
                    return b5;
                }
                return Float.class;
            case 109413500:
                if (!name.equals("short")) {
                    return b5;
                }
                return Short.class;
            default:
                return b5;
        }
    }

    public static final Class C(C0748d dVar) {
        j.e(dVar, "<this>");
        Class b5 = ((c) dVar).b();
        if (b5.isPrimitive()) {
            return b5;
        }
        String name = b5.getName();
        switch (name.hashCode()) {
            case -2056817302:
                if (!name.equals("java.lang.Integer")) {
                    return null;
                }
                return Integer.TYPE;
            case -527879800:
                if (!name.equals("java.lang.Float")) {
                    return null;
                }
                return Float.TYPE;
            case -515992664:
                if (!name.equals("java.lang.Short")) {
                    return null;
                }
                return Short.TYPE;
            case 155276373:
                if (!name.equals("java.lang.Character")) {
                    return null;
                }
                return Character.TYPE;
            case 344809556:
                if (!name.equals("java.lang.Boolean")) {
                    return null;
                }
                return Boolean.TYPE;
            case 398507100:
                if (!name.equals("java.lang.Byte")) {
                    return null;
                }
                return Byte.TYPE;
            case 398795216:
                if (!name.equals("java.lang.Long")) {
                    return null;
                }
                return Long.TYPE;
            case 399092968:
                if (!name.equals("java.lang.Void")) {
                    return null;
                }
                return Void.TYPE;
            case 761287205:
                if (!name.equals("java.lang.Double")) {
                    return null;
                }
                return Double.TYPE;
            default:
                return null;
        }
    }

    public static final C0748d D(Class cls) {
        j.e(cls, "<this>");
        return v.f4727a.b(cls);
    }

    public static final ArrayList E(B b5) {
        ArrayList F4 = F(C0754c.b(b5));
        if (F4 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(C1196n.w0(F4, 10));
        Iterator it = F4.iterator();
        while (it.hasNext()) {
            arrayList.add("unbox-impl-" + ((String) it.next()));
        }
        C0819i g = b5.s0().g();
        j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        Class k = E0.k((C0816f) g);
        j.b(k);
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(k.getDeclaredMethod((String) it2.next(), (Class[]) null));
        }
        return arrayList2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r7v4, types: [Qe.W] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.ArrayList F(Hf.B r7) {
        /*
            boolean r0 = tf.C1305i.h(r7)
            r1 = 0
            if (r0 == 0) goto L_0x0091
            Hf.M r7 = r7.s0()
            Qe.i r7 = r7.g()
            java.lang.String r0 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            kotlin.jvm.internal.j.c(r7, r0)
            Qe.f r7 = (Qe.C0816f) r7
            int r0 = xf.C1353d.f5167a
            Qe.W r7 = r7.N()
            boolean r0 = r7 instanceof Qe.D
            if (r0 == 0) goto L_0x0023
            r1 = r7
            Qe.D r1 = (Qe.D) r1
        L_0x0023:
            kotlin.jvm.internal.j.b(r1)
            java.util.ArrayList r7 = r1.f3656a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        L_0x0031:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0090
            java.lang.Object r1 = r7.next()
            me.i r1 = (me.i) r1
            java.lang.Object r2 = r1.d
            qf.g r2 = (qf.C1240g) r2
            java.lang.Object r1 = r1.e
            Hf.B r1 = (Hf.B) r1
            java.util.ArrayList r1 = F(r1)
            if (r1 == 0) goto L_0x0082
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = ne.C1196n.w0(r1, r4)
            r3.<init>(r4)
            java.util.Iterator r1 = r1.iterator()
        L_0x005a:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x008a
            java.lang.Object r4 = r1.next()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r2.c()
            r5.append(r6)
            r6 = 45
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r3.add(r4)
            goto L_0x005a
        L_0x0082:
            java.lang.String r1 = r2.c()
            java.util.List r3 = o1.C0246a.e0(r1)
        L_0x008a:
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            ne.C1200r.A0(r3, r0)
            goto L_0x0031
        L_0x0090:
            return r0
        L_0x0091:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a.C0068a.F(Hf.B):java.util.ArrayList");
    }

    public static String G(Context context) {
        if (b == null) {
            PackageInfo H5 = H(context);
            if (H5 != null) {
                b = H5.versionName;
            } else {
                b = "";
            }
        }
        return b;
    }

    public static PackageInfo H(Context context) {
        if (f970c == null) {
            String packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                try {
                    f970c = packageManager.getPackageInfo(packageName, 4096);
                } catch (PackageManager.NameNotFoundException unused) {
                    f.o(packageName + " is not found");
                }
            }
        }
        return f970c;
    }

    public static boolean I(h hVar, C1236c cVar) {
        j.e(cVar, "fqName");
        if (hVar.m(cVar) != null) {
            return true;
        }
        return false;
    }

    public static void J(String str) {
        Log.e("SamsungAnalytics605082", "[LOGWING]" + str);
    }

    public static void K(String str) {
        Log.w("SamsungAnalytics605082", "[LOGWING]" + str);
    }

    public static final int L(e eVar) {
        a aVar = d.d;
        int i2 = eVar.d;
        if (!eVar.isEmpty()) {
            int i7 = eVar.e;
            if (i7 < Integer.MAX_VALUE) {
                return d.d.c(i2, i7 + 1);
            } else if (i2 <= Integer.MIN_VALUE) {
                return d.d.b();
            } else {
                return d.d.c(i2 - 1, i7) + 1;
            }
        } else {
            throw new IllegalArgumentException("Cannot get random in empty range: " + eVar);
        }
    }

    public static boolean M(Parcel parcel, int i2) {
        h0(i2, 4, parcel);
        if (parcel.readInt() != 0) {
            return true;
        }
        return false;
    }

    public static float N(Parcel parcel, int i2) {
        h0(i2, 4, parcel);
        return parcel.readFloat();
    }

    public static C1178a O(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        Ge.c cVar = new Ge.c(1, dataInputStream.readInt(), 1);
        ArrayList arrayList = new ArrayList(C1196n.w0(cVar, 10));
        Iterator it = cVar.iterator();
        while (((Ge.d) it).f) {
            ((y) it).nextInt();
            arrayList.add(Integer.valueOf(dataInputStream.readInt()));
        }
        int[] j12 = C1194l.j1(arrayList);
        return new C1178a(Arrays.copyOf(j12, j12.length));
    }

    public static IBinder P(Parcel parcel, int i2) {
        int R = R(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (R == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(dataPosition + R);
        return readStrongBinder;
    }

    public static int Q(Parcel parcel, int i2) {
        h0(i2, 4, parcel);
        return parcel.readInt();
    }

    public static int R(Parcel parcel, int i2) {
        if ((i2 & -65536) != -65536) {
            return (char) (i2 >> 16);
        }
        return parcel.readInt();
    }

    public static final String S(C1240g gVar) {
        j.e(gVar, "<this>");
        String b5 = gVar.b();
        j.d(b5, "asString(...)");
        if (!C1290q.f5108a.contains(b5)) {
            int i2 = 0;
            while (true) {
                if (i2 < b5.length()) {
                    char charAt = b5.charAt(i2);
                    if (!Character.isLetterOrDigit(charAt) && charAt != '_') {
                        break;
                    }
                    i2++;
                } else if (b5.length() != 0 && Character.isJavaIdentifierStart(b5.codePointAt(0))) {
                    String b8 = gVar.b();
                    j.d(b8, "asString(...)");
                    return b8;
                }
            }
        }
        StringBuilder sb2 = new StringBuilder();
        String b10 = gVar.b();
        j.d(b10, "asString(...)");
        sb2.append("`".concat(b10));
        sb2.append('`');
        return sb2.toString();
    }

    public static final String T(List list) {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C1240g gVar = (C1240g) it.next();
            if (sb2.length() > 0) {
                sb2.append(".");
            }
            sb2.append(S(gVar));
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public static final String U(String str, String str2, String str3, String str4, String str5) {
        j.e(str, "lowerRendered");
        j.e(str2, "lowerPrefix");
        j.e(str3, "upperRendered");
        j.e(str4, "upperPrefix");
        j.e(str5, "foldedPrefix");
        if (!Tf.v.t0(str, str2) || !Tf.v.t0(str3, str4)) {
            return null;
        }
        String substring = str.substring(str2.length());
        j.d(substring, "substring(...)");
        String substring2 = str3.substring(str4.length());
        j.d(substring2, "substring(...)");
        String concat = str5.concat(substring);
        if (substring.equals(substring2)) {
            return concat;
        }
        if (!b0(substring, substring2)) {
            return null;
        }
        return concat + '!';
    }

    public static int V(double d) {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        } else if (d > 2.147483647E9d) {
            return Integer.MAX_VALUE;
        } else {
            if (d < -2.147483648E9d) {
                return Integer.MIN_VALUE;
            }
            return (int) Math.round(d);
        }
    }

    public static int W(float f) {
        if (!Float.isNaN(f)) {
            return Math.round(f);
        }
        throw new IllegalArgumentException("Cannot round NaN value.");
    }

    public static void X(Parcel parcel, int i2) {
        parcel.setDataPosition(parcel.dataPosition() + R(parcel, i2));
    }

    public static C0993a Y(Y y, boolean z, C0937F f, int i2) {
        boolean z3;
        boolean z7 = false;
        if ((i2 & 1) != 0) {
            z3 = false;
        } else {
            z3 = z;
        }
        if ((i2 & 2) == 0) {
            z7 = true;
        }
        boolean z9 = z7;
        int i7 = i2 & 4;
        Set set = null;
        if (i7 != 0) {
            f = null;
        }
        j.e(y, "<this>");
        if (f != null) {
            set = B1.a.S(f);
        }
        return new C0993a(y, z9, z3, set, 34);
    }

    public static final Class Z(C0774x xVar) {
        B i2;
        Class a0 = a0(xVar.s0().g());
        if (a0 == null) {
            return null;
        }
        if (a0.e(xVar) && ((i2 = C1305i.i(xVar)) == null || a0.e(i2) || i.F(i2))) {
            return null;
        }
        return a0;
    }

    public static void a(Parcel parcel, Bundle bundle) {
        if (bundle != null) {
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public static final Class a0(C0822l lVar) {
        if (!(lVar instanceof C0816f) || !C1305i.b(lVar)) {
            return null;
        }
        C0816f fVar = (C0816f) lVar;
        Class k = E0.k(fVar);
        if (k != null) {
            return k;
        }
        throw new v0("Class object for the class " + fVar.getName() + " cannot be found (classId=" + C1353d.f((C0819i) lVar) + ')', 0);
    }

    public static void b(String str) {
        Log.d("SamsungAnalytics605082", str);
    }

    public static final boolean b0(String str, String str2) {
        j.e(str, "lower");
        j.e(str2, "upper");
        if (str.equals(Tf.v.s0(str2, "?", ""))) {
            return true;
        }
        if (Tf.v.o0(str2, "?") && j.a(str.concat("?"), str2)) {
            return true;
        }
        if (j.a("(" + str + ")?", str2)) {
            return true;
        }
        return false;
    }

    public static void c(String str, String str2) {
        b("[" + str + "] " + str2);
    }

    public static int c0(int i2) {
        int i7 = i2 >> 7;
        int i8 = 0;
        while (i7 != 0) {
            i7 >>= 7;
            i8++;
        }
        return i8 + 1;
    }

    public static void d(String str) {
        Log.e("SamsungAnalytics605082", str);
    }

    public static int d0(Parcel parcel) {
        int readInt = parcel.readInt();
        int R = R(parcel, readInt);
        char c5 = (char) readInt;
        int dataPosition = parcel.dataPosition();
        if (c5 == 20293) {
            int i2 = R + dataPosition;
            if (i2 >= dataPosition && i2 <= parcel.dataSize()) {
                return i2;
            }
            throw new C0730a(N2.j.b(dataPosition, i2, "Size read is invalid start=", " end="), parcel);
        }
        throw new C0730a("Expected object header. Got 0x".concat(String.valueOf(Integer.toHexString(readInt))), parcel);
    }

    public static void e(String str) {
        if (!Build.TYPE.equals("user")) {
            N2.j.w("[DEBUG ONLY] ", str, "SamsungAnalytics605082");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void e0(java.lang.String r3, java.lang.String r4) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r3 = r0.isFile()
            if (r3 != 0) goto L_0x001a
            boolean r3 = r0.isDirectory()
            if (r3 == 0) goto L_0x0012
            goto L_0x001a
        L_0x0012:
            java.lang.Exception r3 = new java.lang.Exception
            java.lang.String r4 = "not found"
            r3.<init>(r4)
            throw r3
        L_0x001a:
            r3 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x004a }
            r1.<init>(r4)     // Catch:{ all -> 0x004a }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0045 }
            r4.<init>(r1)     // Catch:{ all -> 0x0045 }
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x0041 }
            r2.<init>(r4)     // Catch:{ all -> 0x0041 }
            r3 = 8
            r2.setLevel(r3)     // Catch:{ all -> 0x003f }
            f0(r0, r2)     // Catch:{ all -> 0x003f }
            r2.finish()     // Catch:{ all -> 0x003f }
            r2.close()
            r4.close()
            r1.close()
            return
        L_0x003f:
            r3 = move-exception
            goto L_0x004e
        L_0x0041:
            r0 = move-exception
            r2 = r3
            r3 = r0
            goto L_0x004e
        L_0x0045:
            r4 = move-exception
            r2 = r3
        L_0x0047:
            r3 = r4
            r4 = r2
            goto L_0x004e
        L_0x004a:
            r4 = move-exception
            r1 = r3
            r2 = r1
            goto L_0x0047
        L_0x004e:
            if (r2 == 0) goto L_0x0053
            r2.close()
        L_0x0053:
            if (r4 == 0) goto L_0x0058
            r4.close()
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()
        L_0x005d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a.C0068a.e0(java.lang.String, java.lang.String):void");
    }

    public static void f(Class cls, Exception exc) {
        Log.w("SamsungAnalytics605082", "[" + cls.getSimpleName() + "] " + exc.getClass().getSimpleName() + " " + exc.getMessage());
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void f0(java.io.File r5, java.util.zip.ZipOutputStream r6) {
        /*
            boolean r0 = r5.isDirectory()
            r1 = 0
            if (r0 == 0) goto L_0x0024
            java.lang.String r0 = r5.getName()
            java.lang.String r2 = ".metadata"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0014
            goto L_0x0023
        L_0x0014:
            java.io.File[] r5 = r5.listFiles()
        L_0x0018:
            int r0 = r5.length
            if (r1 >= r0) goto L_0x0023
            r0 = r5[r1]
            f0(r0, r6)
            int r1 = r1 + 1
            goto L_0x0018
        L_0x0023:
            return
        L_0x0024:
            r0 = 0
            java.lang.String r2 = r5.getPath()     // Catch:{ all -> 0x0046 }
            java.lang.String r3 = ae.C0902a.f3987a     // Catch:{ all -> 0x0046 }
            android.util.Log.d(r3, r2)     // Catch:{ all -> 0x0046 }
            java.util.StringTokenizer r3 = new java.util.StringTokenizer     // Catch:{ all -> 0x0046 }
            java.lang.String r4 = "/"
            r3.<init>(r2, r4)     // Catch:{ all -> 0x0046 }
            int r2 = r3.countTokens()     // Catch:{ all -> 0x0046 }
            java.lang.String r4 = r3.toString()     // Catch:{ all -> 0x0046 }
        L_0x003d:
            if (r2 == 0) goto L_0x0048
            int r2 = r2 + -1
            java.lang.String r4 = r3.nextToken()     // Catch:{ all -> 0x0046 }
            goto L_0x003d
        L_0x0046:
            r5 = move-exception
            goto L_0x007a
        L_0x0048:
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0046 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0046 }
            r3.<init>(r5)     // Catch:{ all -> 0x0046 }
            r2.<init>(r3)     // Catch:{ all -> 0x0046 }
            java.util.zip.ZipEntry r0 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x0070 }
            r0.<init>(r4)     // Catch:{ all -> 0x0070 }
            long r3 = r5.lastModified()     // Catch:{ all -> 0x0070 }
            r0.setTime(r3)     // Catch:{ all -> 0x0070 }
            r6.putNextEntry(r0)     // Catch:{ all -> 0x0070 }
            r5 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r5]     // Catch:{ all -> 0x0070 }
        L_0x0065:
            int r3 = r2.read(r0, r1, r5)     // Catch:{ all -> 0x0070 }
            r4 = -1
            if (r3 == r4) goto L_0x0073
            r6.write(r0, r1, r3)     // Catch:{ all -> 0x0070 }
            goto L_0x0065
        L_0x0070:
            r5 = move-exception
            r0 = r2
            goto L_0x007a
        L_0x0073:
            r6.closeEntry()     // Catch:{ all -> 0x0070 }
            r2.close()
            return
        L_0x007a:
            if (r0 == 0) goto L_0x007f
            r0.close()
        L_0x007f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: a.C0068a.f0(java.io.File, java.util.zip.ZipOutputStream):void");
    }

    public static void g(String str) {
        Log.i("SamsungAnalytics605082", str);
    }

    public static /* synthetic */ String g0(int i2, int i7, byte b5, String str, String str2) {
        StringBuilder sb2 = new StringBuilder(String.valueOf(i7).length() + b5 + String.valueOf(i2).length());
        sb2.append(str);
        sb2.append(i7);
        sb2.append(str2);
        sb2.append(i2);
        return sb2.toString();
    }

    public static void h0(int i2, int i7, Parcel parcel) {
        int R = R(parcel, i2);
        if (R != i7) {
            throw new C0730a(C0212a.p(A.a.h(i7, R, "Expected size ", " got ", " (0x"), Integer.toHexString(R), ")"), parcel);
        }
    }

    public static void i(String str, long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException(str + " (" + j2 + ") must be >= 0");
        }
    }

    public static void j(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    public static final Object k(Object obj, C0814d dVar) {
        C0774x y;
        Class Z;
        Method z;
        if (((dVar instanceof O) && C1305i.e((Qe.Y) dVar)) || (y = y(dVar)) == null || (Z = Z(y)) == null || (z = z(Z, dVar)) == null) {
            return obj;
        }
        return z.invoke(obj, (Object[]) null);
    }

    public static int l(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00aa, code lost:
        if ((r6 instanceof Te.I) == false) goto L_0x00ac;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m(Qe.C0831v r6, int r7) {
        /*
            r0 = r7 & 1
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0008
            r0 = r2
            goto L_0x0009
        L_0x0008:
            r0 = r1
        L_0x0009:
            r7 = r7 & 2
            if (r7 == 0) goto L_0x000e
            r1 = r2
        L_0x000e:
            java.lang.String r7 = "<this>"
            kotlin.jvm.internal.j.e(r6, r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            if (r1 == 0) goto L_0x0034
            boolean r1 = r6 instanceof Qe.C0821k
            if (r1 == 0) goto L_0x0021
            java.lang.String r1 = "<init>"
            goto L_0x0031
        L_0x0021:
            r1 = r6
            Te.m r1 = (Te.C0852m) r1
            qf.g r1 = r1.getName()
            java.lang.String r1 = r1.b()
            java.lang.String r2 = "asString(...)"
            kotlin.jvm.internal.j.d(r1, r2)
        L_0x0031:
            r7.append(r1)
        L_0x0034:
            java.lang.String r1 = "("
            r7.append(r1)
            Te.u r1 = r6.H()
            Qf.c r2 = Qf.c.d
            java.lang.String r3 = "getType(...)"
            if (r1 == 0) goto L_0x0055
            Hf.x r1 = r1.getType()
            kotlin.jvm.internal.j.d(r1, r3)
            jf.v r4 = jf.v.k
            java.lang.Object r1 = Gd.a.R(r1, r4, r2)
            jf.m r1 = (jf.C1113m) r1
            r7.append(r1)
        L_0x0055:
            java.util.List r1 = r6.B()
            java.util.Iterator r1 = r1.iterator()
        L_0x005d:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x007e
            java.lang.Object r4 = r1.next()
            Te.Q r4 = (Te.Q) r4
            Te.S r4 = (Te.S) r4
            Hf.x r4 = r4.getType()
            kotlin.jvm.internal.j.d(r4, r3)
            jf.v r5 = jf.v.k
            java.lang.Object r4 = Gd.a.R(r4, r5, r2)
            jf.m r4 = (jf.C1113m) r4
            r7.append(r4)
            goto L_0x005d
        L_0x007e:
            java.lang.String r1 = ")"
            r7.append(r1)
            if (r0 == 0) goto L_0x00c4
            boolean r0 = r6 instanceof Qe.C0821k
            if (r0 == 0) goto L_0x008a
            goto L_0x00ac
        L_0x008a:
            Hf.x r0 = r6.getReturnType()
            kotlin.jvm.internal.j.b(r0)
            qf.g r1 = Ne.i.e
            qf.e r1 = Ne.p.d
            boolean r0 = Ne.i.D(r0, r1)
            if (r0 == 0) goto L_0x00b2
            Hf.x r0 = r6.getReturnType()
            kotlin.jvm.internal.j.b(r0)
            boolean r0 = Hf.a0.e(r0)
            if (r0 != 0) goto L_0x00b2
            boolean r0 = r6 instanceof Te.I
            if (r0 != 0) goto L_0x00b2
        L_0x00ac:
            java.lang.String r6 = "V"
            r7.append(r6)
            goto L_0x00c4
        L_0x00b2:
            Hf.x r6 = r6.getReturnType()
            kotlin.jvm.internal.j.b(r6)
            jf.v r0 = jf.v.k
            java.lang.Object r6 = Gd.a.R(r6, r0, r2)
            jf.m r6 = (jf.C1113m) r6
            r7.append(r6)
        L_0x00c4:
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "toString(...)"
            kotlin.jvm.internal.j.d(r6, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: a.C0068a.m(Qe.v, int):java.lang.String");
    }

    public static final String n(C0812b bVar) {
        C0816f fVar;
        K k;
        j.e(bVar, "<this>");
        if (!C1301e.o(bVar)) {
            C0822l g = bVar.g();
            if (g instanceof C0816f) {
                fVar = (C0816f) g;
            } else {
                fVar = null;
            }
            if (fVar != null && !fVar.getName().e) {
                C0812b a7 = bVar.a();
                if (a7 instanceof K) {
                    k = (K) a7;
                } else {
                    k = null;
                }
                if (k != null) {
                    return L2.a.z(fVar, m(k, 3));
                }
            }
        }
        return null;
    }

    public static Parcelable o(Parcel parcel, int i2, Parcelable.Creator creator) {
        int R = R(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (R == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + R);
        return parcelable;
    }

    public static String p(Parcel parcel, int i2) {
        int R = R(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (R == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + R);
        return readString;
    }

    public static Object[] q(Parcel parcel, int i2, Parcelable.Creator creator) {
        int R = R(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (R == 0) {
            return null;
        }
        Object[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + R);
        return createTypedArray;
    }

    public static ArrayList r(Parcel parcel, int i2, Parcelable.Creator creator) {
        int R = R(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (R == 0) {
            return null;
        }
        ArrayList createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(dataPosition + R);
        return createTypedArrayList;
    }

    public static final g s(g gVar, C0814d dVar, boolean z) {
        C0774x y;
        j.e(dVar, "descriptor");
        if (!C1305i.a(dVar)) {
            List i02 = dVar.i0();
            j.d(i02, "getContextReceiverParameters(...)");
            Iterable iterable = i02;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (C1305i.g(((u) it.next()).getType())) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            List B = dVar.B();
            j.d(B, "getValueParameters(...)");
            Iterable iterable2 = B;
            if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                Iterator it2 = iterable2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    C0774x type = ((Q) it2.next()).getType();
                    j.d(type, "getType(...)");
                    if (C1305i.g(type)) {
                        break;
                    }
                }
            }
            C0774x returnType = dVar.getReturnType();
            if ((returnType == null || !C1305i.c(returnType)) && ((y = y(dVar)) == null || !C1305i.g(y))) {
                return gVar;
            }
        }
        return new C(gVar, dVar, z);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [se.c] */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0081, code lost:
        if (r2.emit(r10, r0) == r1) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008d, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008e, code lost:
        if (r9 != false) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0092, code lost:
        if ((r7 instanceof java.util.concurrent.CancellationException) != false) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0094, code lost:
        r3 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0097, code lost:
        if (r3 == null) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0099, code lost:
        r3 = new java.util.concurrent.CancellationException("Channel was consumed, consumer had failed");
        r3.initCause(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a3, code lost:
        r8.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a6, code lost:
        throw r10;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A[Catch:{ all -> 0x008d }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A[Catch:{ all -> 0x008d }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006f A[Catch:{ all -> 0x008d }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object t(Yf.h r7, Xf.i r8, boolean r9, se.C1271c r10) {
        /*
            boolean r0 = r10 instanceof Yf.i
            if (r0 == 0) goto L_0x0013
            r0 = r10
            Yf.i r0 = (Yf.i) r0
            int r1 = r0.f3921i
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f3921i = r1
            goto L_0x0018
        L_0x0013:
            Yf.i r0 = new Yf.i
            r0.<init>(r10)
        L_0x0018:
            java.lang.Object r10 = r0.f3920h
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f3921i
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004b
            if (r2 == r5) goto L_0x003f
            if (r2 != r4) goto L_0x0037
            boolean r9 = r0.g
            Xf.b r7 = r0.f
            Xf.t r8 = r0.e
            Yf.h r2 = r0.d
            L2.a.A(r10)     // Catch:{ all -> 0x0035 }
        L_0x0032:
            r10 = r7
            r7 = r2
            goto L_0x0052
        L_0x0035:
            r7 = move-exception
            goto L_0x008c
        L_0x0037:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003f:
            boolean r9 = r0.g
            Xf.b r7 = r0.f
            Xf.t r8 = r0.e
            Yf.h r2 = r0.d
            L2.a.A(r10)     // Catch:{ all -> 0x0035 }
            goto L_0x0067
        L_0x004b:
            L2.a.A(r10)
            Xf.b r10 = r8.iterator()     // Catch:{ all -> 0x0035 }
        L_0x0052:
            r0.d = r7     // Catch:{ all -> 0x0035 }
            r0.e = r8     // Catch:{ all -> 0x0035 }
            r0.f = r10     // Catch:{ all -> 0x0035 }
            r0.g = r9     // Catch:{ all -> 0x0035 }
            r0.f3921i = r5     // Catch:{ all -> 0x0035 }
            java.lang.Object r2 = r10.b(r0)     // Catch:{ all -> 0x0035 }
            if (r2 != r1) goto L_0x0063
            goto L_0x0083
        L_0x0063:
            r6 = r2
            r2 = r7
            r7 = r10
            r10 = r6
        L_0x0067:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x0035 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x0035 }
            if (r10 == 0) goto L_0x0084
            java.lang.Object r10 = r7.c()     // Catch:{ all -> 0x0035 }
            r0.d = r2     // Catch:{ all -> 0x0035 }
            r0.e = r8     // Catch:{ all -> 0x0035 }
            r0.f = r7     // Catch:{ all -> 0x0035 }
            r0.g = r9     // Catch:{ all -> 0x0035 }
            r0.f3921i = r4     // Catch:{ all -> 0x0035 }
            java.lang.Object r10 = r2.emit(r10, r0)     // Catch:{ all -> 0x0035 }
            if (r10 != r1) goto L_0x0032
        L_0x0083:
            return r1
        L_0x0084:
            if (r9 == 0) goto L_0x0089
            r8.a(r3)
        L_0x0089:
            me.x r7 = me.x.f4917a
            return r7
        L_0x008c:
            throw r7     // Catch:{ all -> 0x008d }
        L_0x008d:
            r10 = move-exception
            if (r9 == 0) goto L_0x00a6
            boolean r9 = r7 instanceof java.util.concurrent.CancellationException
            if (r9 == 0) goto L_0x0097
            r3 = r7
            java.util.concurrent.CancellationException r3 = (java.util.concurrent.CancellationException) r3
        L_0x0097:
            if (r3 != 0) goto L_0x00a3
            java.util.concurrent.CancellationException r3 = new java.util.concurrent.CancellationException
            java.lang.String r9 = "Channel was consumed, consumer had failed"
            r3.<init>(r9)
            r3.initCause(r7)
        L_0x00a3:
            r8.a(r3)
        L_0x00a6:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: a.C0068a.t(Yf.h, Xf.i, boolean, se.c):java.lang.Object");
    }

    public static void u(Parcel parcel, int i2) {
        if (parcel.dataPosition() != i2) {
            throw new C0730a(C0086a.i(i2, "Overread allowed size end="), parcel);
        }
    }

    public static b v(h hVar, C1236c cVar) {
        Object obj;
        j.e(cVar, "fqName");
        Iterator it = hVar.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(((b) obj).b(), cVar)) {
                break;
            }
        }
        return (b) obj;
    }

    public static final C0748d w(Annotation annotation) {
        j.e(annotation, "<this>");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        j.d(annotationType, "annotationType(...)");
        return D(annotationType);
    }

    public static int x(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.sec.android.diagmonagent", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            f.o("DMA Client is not exist");
            return 0;
        }
    }

    public static final C0774x y(C0814d dVar) {
        C0816f fVar;
        u H5 = dVar.H();
        u E = dVar.E();
        if (H5 != null) {
            return H5.getType();
        }
        if (E != null) {
            if (dVar instanceof C0821k) {
                return E.getType();
            }
            C0822l g = dVar.g();
            if (g instanceof C0816f) {
                fVar = (C0816f) g;
            } else {
                fVar = null;
            }
            if (fVar != null) {
                return fVar.i();
            }
        }
        return null;
    }

    public static final Method z(Class cls, C0814d dVar) {
        j.e(dVar, "descriptor");
        try {
            return cls.getDeclaredMethod("unbox-impl", (Class[]) null);
        } catch (NoSuchMethodException unused) {
            throw new v0("No unbox method found in inline class: " + cls + " (calling " + dVar + ')', 0);
        }
    }

    public abstract String h();

    public int hashCode() {
        switch (this.f971a) {
            case 15:
                return toString().hashCode();
            default:
                return super.hashCode();
        }
    }

    public String toString() {
        switch (this.f971a) {
            case 15:
                String o2 = v.f4727a.b(getClass()).o();
                j.b(o2);
                return o2;
            default:
                return super.toString();
        }
    }
}
