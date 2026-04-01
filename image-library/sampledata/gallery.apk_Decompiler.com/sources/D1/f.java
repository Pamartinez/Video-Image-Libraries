package D1;

import Af.o;
import Af.p;
import C1.b;
import Df.C0736b;
import E1.a;
import Gf.n;
import He.t;
import Hf.C0756e;
import Hf.C0774x;
import Hf.M;
import Hf.P;
import Hf.X;
import Hf.a0;
import Hf.c0;
import Hf.d0;
import K1.g;
import L1.e;
import Ne.d;
import Ne.i;
import Qe.B;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0822l;
import Qe.C0831v;
import Qe.N;
import Qe.O;
import Te.K;
import Vf.C0875l;
import Ze.C0896c;
import Ze.C0897d;
import Ze.C0898e;
import Ze.C0899f;
import Ze.H;
import a.C0068a;
import android.content.Context;
import android.graphics.Typeface;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import bf.C0918c;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.F;
import java.io.BufferedReader;
import java.io.StringWriter;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import jf.s;
import jg.c;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import lf.G;
import ne.C1194l;
import ne.C1196n;
import ne.C1200r;
import ne.z;
import nf.C1209f;
import o1.C0246a;
import of.k;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import qf.C1243j;
import re.C1245a;
import rf.C1258h;
import rf.C1260j;
import rf.C1264n;
import rf.C1265o;
import rf.C1266p;
import rf.D;
import se.C1271c;
import t1.h;
import tf.C1301e;
import w1.r;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    public static ClassLoader f119a = null;
    public static Thread b = null;

    /* renamed from: c  reason: collision with root package name */
    public static Context f120c = null;
    public static e d = null;
    public static String e = "";
    public static B f;

    public static void A(String str) {
        B b5 = f;
        if (b5 == null) {
            Log.i("DIAGMON_SDK", str);
            return;
        }
        try {
            b5.a(e);
            Log.i("DIAGMON_SDK" + b5.b, str);
        } catch (Exception e7) {
            Log.e("DIAGMON_SDK", e7.getMessage());
        }
    }

    public static void B(Context context, String str) {
        try {
            if (f == null && context != null && !TextUtils.isEmpty(str)) {
                e = str;
                f = new B();
            }
        } catch (Exception e7) {
            Log.e("DIAGMON_SDK", e7.getMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:87:0x0119 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean C(He.C0747c r4) {
        /*
            boolean r0 = r4 instanceof He.k
            r1 = 1
            if (r0 == 0) goto L_0x003e
            r0 = r4
            He.t r0 = (He.t) r0
            java.lang.reflect.Field r2 = He.F.G(r0)
            if (r2 == 0) goto L_0x0013
            boolean r2 = r2.isAccessible()
            goto L_0x0014
        L_0x0013:
            r2 = r1
        L_0x0014:
            if (r2 == 0) goto L_0x011a
            He.o r0 = r0.getGetter()
            java.lang.reflect.Method r0 = He.F.H(r0)
            if (r0 == 0) goto L_0x0025
            boolean r0 = r0.isAccessible()
            goto L_0x0026
        L_0x0025:
            r0 = r1
        L_0x0026:
            if (r0 == 0) goto L_0x011a
            He.k r4 = (He.k) r4
            He.h r4 = r4.getSetter()
            java.lang.reflect.Method r4 = He.F.H(r4)
            if (r4 == 0) goto L_0x0039
            boolean r4 = r4.isAccessible()
            goto L_0x003a
        L_0x0039:
            r4 = r1
        L_0x003a:
            if (r4 == 0) goto L_0x011a
            goto L_0x0119
        L_0x003e:
            boolean r0 = r4 instanceof He.t
            if (r0 == 0) goto L_0x0066
            He.t r4 = (He.t) r4
            java.lang.reflect.Field r0 = He.F.G(r4)
            if (r0 == 0) goto L_0x004f
            boolean r0 = r0.isAccessible()
            goto L_0x0050
        L_0x004f:
            r0 = r1
        L_0x0050:
            if (r0 == 0) goto L_0x011a
            He.o r4 = r4.getGetter()
            java.lang.reflect.Method r4 = He.F.H(r4)
            if (r4 == 0) goto L_0x0061
            boolean r4 = r4.isAccessible()
            goto L_0x0062
        L_0x0061:
            r4 = r1
        L_0x0062:
            if (r4 == 0) goto L_0x011a
            goto L_0x0119
        L_0x0066:
            boolean r0 = r4 instanceof He.o
            if (r0 == 0) goto L_0x0091
            r0 = r4
            He.o r0 = (He.o) r0
            He.t r0 = r0.b()
            java.lang.reflect.Field r0 = He.F.G(r0)
            if (r0 == 0) goto L_0x007c
            boolean r0 = r0.isAccessible()
            goto L_0x007d
        L_0x007c:
            r0 = r1
        L_0x007d:
            if (r0 == 0) goto L_0x011a
            He.g r4 = (He.C0751g) r4
            java.lang.reflect.Method r4 = He.F.H(r4)
            if (r4 == 0) goto L_0x008c
            boolean r4 = r4.isAccessible()
            goto L_0x008d
        L_0x008c:
            r4 = r1
        L_0x008d:
            if (r4 == 0) goto L_0x011a
            goto L_0x0119
        L_0x0091:
            boolean r0 = r4 instanceof He.h
            if (r0 == 0) goto L_0x00bc
            r0 = r4
            He.h r0 = (He.h) r0
            He.t r0 = r0.b()
            java.lang.reflect.Field r0 = He.F.G(r0)
            if (r0 == 0) goto L_0x00a7
            boolean r0 = r0.isAccessible()
            goto L_0x00a8
        L_0x00a7:
            r0 = r1
        L_0x00a8:
            if (r0 == 0) goto L_0x011a
            He.g r4 = (He.C0751g) r4
            java.lang.reflect.Method r4 = He.F.H(r4)
            if (r4 == 0) goto L_0x00b7
            boolean r4 = r4.isAccessible()
            goto L_0x00b8
        L_0x00b7:
            r4 = r1
        L_0x00b8:
            if (r4 == 0) goto L_0x011a
            goto L_0x0119
        L_0x00bc:
            boolean r0 = r4 instanceof He.C0751g
            if (r0 == 0) goto L_0x011c
            r0 = r4
            He.g r0 = (He.C0751g) r0
            java.lang.reflect.Method r2 = He.F.H(r0)
            if (r2 == 0) goto L_0x00ce
            boolean r2 = r2.isAccessible()
            goto L_0x00cf
        L_0x00ce:
            r2 = r1
        L_0x00cf:
            if (r2 == 0) goto L_0x011a
            Ke.s r4 = Ke.E0.a(r4)
            r2 = 0
            if (r4 == 0) goto L_0x00e3
            Le.g r4 = r4.i()
            if (r4 == 0) goto L_0x00e3
            java.lang.reflect.Member r4 = r4.b()
            goto L_0x00e4
        L_0x00e3:
            r4 = r2
        L_0x00e4:
            boolean r3 = r4 instanceof java.lang.reflect.AccessibleObject
            if (r3 == 0) goto L_0x00eb
            java.lang.reflect.AccessibleObject r4 = (java.lang.reflect.AccessibleObject) r4
            goto L_0x00ec
        L_0x00eb:
            r4 = r2
        L_0x00ec:
            if (r4 == 0) goto L_0x00f3
            boolean r4 = r4.isAccessible()
            goto L_0x00f4
        L_0x00f3:
            r4 = r1
        L_0x00f4:
            if (r4 == 0) goto L_0x011a
            Ke.s r4 = Ke.E0.a(r0)
            if (r4 == 0) goto L_0x0107
            Le.g r4 = r4.g()
            if (r4 == 0) goto L_0x0107
            java.lang.reflect.Member r4 = r4.b()
            goto L_0x0108
        L_0x0107:
            r4 = r2
        L_0x0108:
            boolean r0 = r4 instanceof java.lang.reflect.Constructor
            if (r0 == 0) goto L_0x010f
            r2 = r4
            java.lang.reflect.Constructor r2 = (java.lang.reflect.Constructor) r2
        L_0x010f:
            if (r2 == 0) goto L_0x0116
            boolean r4 = r2.isAccessible()
            goto L_0x0117
        L_0x0116:
            r4 = r1
        L_0x0117:
            if (r4 == 0) goto L_0x011a
        L_0x0119:
            return r1
        L_0x011a:
            r4 = 0
            return r4
        L_0x011c:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown callable: "
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r2 = " ("
            r1.append(r2)
            java.lang.Class r4 = r4.getClass()
            r1.append(r4)
            r4 = 41
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.f.C(He.c):boolean");
    }

    public static final boolean D(C0816f fVar) {
        C1235b bVar;
        LinkedHashSet linkedHashSet = d.f3545a;
        if (!C1301e.l(fVar)) {
            return false;
        }
        LinkedHashSet linkedHashSet2 = d.f3545a;
        C1235b f5 = C1353d.f(fVar);
        if (f5 != null) {
            bVar = f5.e();
        } else {
            bVar = null;
        }
        if (C1194l.G0(linkedHashSet2, bVar)) {
            return true;
        }
        return false;
    }

    public static boolean E(byte b5) {
        if (b5 > -65) {
            return true;
        }
        return false;
    }

    public static int H(byte[] bArr) {
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        return (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    public static int I(byte[] bArr, int i2, ByteOrder byteOrder) {
        byte b5;
        int i7;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i8 = (bArr[i2 + 1] & 255) << 16;
            b5 = i8 | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 2] & 255) << 8);
            i7 = bArr[i2 + 3] & 255;
        } else {
            int i10 = (bArr[i2 + 1] & 255) << 8;
            b5 = i10 | (bArr[i2] & 255) | ((bArr[i2 + 2] & 255) << 16);
            i7 = (bArr[i2 + 3] & 255) << 24;
        }
        return i7 | b5;
    }

    public static int J(byte[] bArr, int i2, ByteOrder byteOrder) {
        int i7;
        int i8;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i10 = i2 + 1;
            i7 = (bArr[i2] & 255) << 8;
            i8 = bArr[i10] & 255;
        } else {
            int i11 = i2 + 1;
            i7 = bArr[i2] & 255;
            i8 = (bArr[i11] & 255) << 8;
        }
        return i8 | i7;
    }

    public static final String K(BufferedReader bufferedReader) {
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[SerializeOptions.SORT];
        int read = bufferedReader.read(cArr);
        while (read >= 0) {
            stringWriter.write(cArr, 0, read);
            read = bufferedReader.read(cArr);
        }
        String stringWriter2 = stringWriter.toString();
        j.d(stringWriter2, "toString(...)");
        return stringWriter2;
    }

    public static void L(String str) {
        B b5 = f;
        if (b5 == null) {
            Log.w("DIAGMON_SDK", str);
            return;
        }
        try {
            b5.a(e);
            Log.w("DIAGMON_SDK" + b5.b, str);
        } catch (Exception e7) {
            Log.e("DIAGMON_SDK", e7.getMessage());
        }
    }

    public static e M(Context context) {
        r.b(context);
        Log.d("f", "preferredRenderer: ".concat("null"));
        e eVar = d;
        if (eVar != null) {
            return eVar;
        }
        AtomicBoolean atomicBoolean = h.f1924a;
        int a7 = h.a(context, 13400000);
        if (a7 == 0) {
            e P6 = P(context, (g) null);
            d = P6;
            try {
                Parcel b5 = P6.b(P6.c(), 9);
                int readInt = b5.readInt();
                b5.recycle();
                if (readInt == 2) {
                    try {
                        e eVar2 = d;
                        b bVar = new b(O(context, (g) null));
                        Parcel c5 = eVar2.c();
                        H1.d.d(c5, bVar);
                        eVar2.d(c5, 11);
                    } catch (UnsatisfiedLinkError unused) {
                        Log.w("f", "Caught UnsatisfiedLinkError attempting to load the LATEST renderer's native library. Attempting to use the LEGACY renderer instead.");
                        f120c = null;
                        d = P(context, g.LEGACY);
                    } catch (RemoteException e7) {
                        throw new RuntimeException(e7);
                    }
                }
                try {
                    e eVar3 = d;
                    Context O4 = O(context, (g) null);
                    Objects.requireNonNull(O4);
                    b bVar2 = new b(O4.getResources());
                    Parcel c6 = eVar3.c();
                    H1.d.d(c6, bVar2);
                    c6.writeInt(19000000);
                    eVar3.d(c6, 6);
                    return d;
                } catch (RemoteException e8) {
                    throw new RuntimeException(e8);
                }
            } catch (RemoteException e9) {
                throw new RuntimeException(e9);
            }
        } else {
            throw new t1.g(a7);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a4, code lost:
        if (r1 == null) goto L_0x00d1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.ClassLoader N() {
        /*
            java.lang.Class<D1.f> r0 = D1.f.class
            monitor-enter(r0)
            java.lang.ClassLoader r1 = f119a     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x00d6
            java.lang.Thread r1 = b     // Catch:{ all -> 0x00a7 }
            r2 = 0
            if (r1 != 0) goto L_0x00ab
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x00a7 }
            java.lang.Thread r1 = r1.getThread()     // Catch:{ all -> 0x00a7 }
            java.lang.ThreadGroup r1 = r1.getThreadGroup()     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x001d
            r1 = r2
            goto L_0x00a2
        L_0x001d:
            java.lang.Class<java.lang.Void> r3 = java.lang.Void.class
            monitor-enter(r3)     // Catch:{ all -> 0x00a7 }
            int r4 = r1.activeGroupCount()     // Catch:{ SecurityException -> 0x0042 }
            java.lang.ThreadGroup[] r5 = new java.lang.ThreadGroup[r4]     // Catch:{ SecurityException -> 0x0042 }
            r1.enumerate(r5)     // Catch:{ SecurityException -> 0x0042 }
            r6 = 0
            r7 = r6
        L_0x002b:
            if (r7 >= r4) goto L_0x0044
            r8 = r5[r7]     // Catch:{ SecurityException -> 0x0042 }
            java.lang.String r9 = "dynamiteLoader"
            java.lang.String r10 = r8.getName()     // Catch:{ SecurityException -> 0x0042 }
            boolean r9 = r9.equals(r10)     // Catch:{ SecurityException -> 0x0042 }
            if (r9 == 0) goto L_0x003c
            goto L_0x0045
        L_0x003c:
            int r7 = r7 + 1
            goto L_0x002b
        L_0x003f:
            r1 = move-exception
            goto L_0x00a9
        L_0x0042:
            r1 = move-exception
            goto L_0x0084
        L_0x0044:
            r8 = r2
        L_0x0045:
            if (r8 != 0) goto L_0x004e
            java.lang.ThreadGroup r8 = new java.lang.ThreadGroup     // Catch:{ SecurityException -> 0x0042 }
            java.lang.String r4 = "dynamiteLoader"
            r8.<init>(r1, r4)     // Catch:{ SecurityException -> 0x0042 }
        L_0x004e:
            int r1 = r8.activeCount()     // Catch:{ SecurityException -> 0x0042 }
            java.lang.Thread[] r4 = new java.lang.Thread[r1]     // Catch:{ SecurityException -> 0x0042 }
            r8.enumerate(r4)     // Catch:{ SecurityException -> 0x0042 }
        L_0x0057:
            if (r6 >= r1) goto L_0x006b
            r5 = r4[r6]     // Catch:{ SecurityException -> 0x0042 }
            java.lang.String r7 = "GmsDynamite"
            java.lang.String r9 = r5.getName()     // Catch:{ SecurityException -> 0x0042 }
            boolean r7 = r7.equals(r9)     // Catch:{ SecurityException -> 0x0042 }
            if (r7 == 0) goto L_0x0068
            goto L_0x006c
        L_0x0068:
            int r6 = r6 + 1
            goto L_0x0057
        L_0x006b:
            r5 = r2
        L_0x006c:
            if (r5 != 0) goto L_0x00a0
            D1.e r1 = new D1.e     // Catch:{ SecurityException -> 0x0082 }
            java.lang.String r4 = "GmsDynamite"
            r1.<init>(r8, r4)     // Catch:{ SecurityException -> 0x0082 }
            r1.setContextClassLoader(r2)     // Catch:{ SecurityException -> 0x007d }
            r1.start()     // Catch:{ SecurityException -> 0x007d }
            r5 = r1
            goto L_0x00a0
        L_0x007d:
            r4 = move-exception
            r5 = r1
            goto L_0x0086
        L_0x0080:
            r4 = r1
            goto L_0x0086
        L_0x0082:
            r1 = move-exception
            goto L_0x0080
        L_0x0084:
            r4 = r1
            r5 = r2
        L_0x0086:
            java.lang.String r1 = "DynamiteLoaderV2CL"
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x003f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x003f }
            r6.<init>()     // Catch:{ all -> 0x003f }
            java.lang.String r7 = "Failed to enumerate thread/threadgroup "
            r6.append(r7)     // Catch:{ all -> 0x003f }
            r6.append(r4)     // Catch:{ all -> 0x003f }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x003f }
            android.util.Log.w(r1, r4)     // Catch:{ all -> 0x003f }
        L_0x00a0:
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            r1 = r5
        L_0x00a2:
            b = r1     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x00ab
            goto L_0x00d1
        L_0x00a7:
            r1 = move-exception
            goto L_0x00da
        L_0x00a9:
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            throw r1     // Catch:{ all -> 0x00a7 }
        L_0x00ab:
            monitor-enter(r1)     // Catch:{ all -> 0x00a7 }
            java.lang.Thread r3 = b     // Catch:{ SecurityException -> 0x00b5 }
            java.lang.ClassLoader r2 = r3.getContextClassLoader()     // Catch:{ SecurityException -> 0x00b5 }
            goto L_0x00d0
        L_0x00b3:
            r2 = move-exception
            goto L_0x00d4
        L_0x00b5:
            r3 = move-exception
            java.lang.String r4 = "DynamiteLoaderV2CL"
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r5.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r6 = "Failed to get thread context classloader "
            r5.append(r6)     // Catch:{ all -> 0x00b3 }
            r5.append(r3)     // Catch:{ all -> 0x00b3 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x00b3 }
            android.util.Log.w(r4, r3)     // Catch:{ all -> 0x00b3 }
        L_0x00d0:
            monitor-exit(r1)     // Catch:{ all -> 0x00b3 }
        L_0x00d1:
            f119a = r2     // Catch:{ all -> 0x00a7 }
            goto L_0x00d6
        L_0x00d4:
            monitor-exit(r1)     // Catch:{ all -> 0x00b3 }
            throw r2     // Catch:{ all -> 0x00a7 }
        L_0x00d6:
            java.lang.ClassLoader r1 = f119a     // Catch:{ all -> 0x00a7 }
            monitor-exit(r0)
            return r1
        L_0x00da:
            monitor-exit(r0)     // Catch:{ all -> 0x00a7 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.f.N():java.lang.ClassLoader");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        r0 = "com.google.android.gms.maps_core_dynamite";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r8 != K1.g.LEGACY) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r8 != 1) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Context O(android.content.Context r7, K1.g r8) {
        /*
            java.lang.String r0 = "com.google.android.gms.maps_legacy_dynamite"
            java.lang.String r1 = "com.google.android.gms.maps_core_dynamite"
            java.lang.String r2 = "com.google.android.gms.maps_dynamite"
            android.content.Context r3 = f120c
            if (r3 != 0) goto L_0x0062
            java.lang.String r3 = "com.google.android.gms.maps.internal.UseLegacyRendererAsDefault"
            java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x001f }
            if (r8 == 0) goto L_0x001d
            int r8 = r8.ordinal()
            if (r8 == 0) goto L_0x0023
            r0 = 1
            if (r8 == r0) goto L_0x001b
            goto L_0x001d
        L_0x001b:
            r0 = r1
            goto L_0x0023
        L_0x001d:
            r0 = r2
            goto L_0x0023
        L_0x001f:
            K1.g r3 = K1.g.LEGACY
            if (r8 != r3) goto L_0x001b
        L_0x0023:
            x2.e r8 = D1.d.b     // Catch:{ Exception -> 0x002c }
            D1.d r8 = D1.d.a(r7, r8, r0)     // Catch:{ Exception -> 0x002c }
            android.content.Context r7 = r8.f118a     // Catch:{ Exception -> 0x002c }
            goto L_0x005f
        L_0x002c:
            r8 = move-exception
            boolean r0 = r0.equals(r2)
            r1 = 0
            r3 = 3
            java.lang.String r4 = "com.google.android.gms"
            java.lang.String r5 = "Failed to load maps module, use pre-Chimera"
            java.lang.String r6 = "f"
            if (r0 != 0) goto L_0x0056
            java.lang.String r8 = "Attempting to load maps_dynamite again."
            android.util.Log.d(r6, r8)     // Catch:{ Exception -> 0x0049 }
            x2.e r8 = D1.d.b     // Catch:{ Exception -> 0x0049 }
            D1.d r8 = D1.d.a(r7, r8, r2)     // Catch:{ Exception -> 0x0049 }
            android.content.Context r7 = r8.f118a     // Catch:{ Exception -> 0x0049 }
            goto L_0x005f
        L_0x0049:
            r8 = move-exception
            android.util.Log.e(r6, r5, r8)
            java.util.concurrent.atomic.AtomicBoolean r8 = t1.h.f1924a
            android.content.Context r7 = r7.createPackageContext(r4, r3)     // Catch:{ NameNotFoundException -> 0x0054 }
            goto L_0x005f
        L_0x0054:
            r7 = r1
            goto L_0x005f
        L_0x0056:
            android.util.Log.e(r6, r5, r8)
            java.util.concurrent.atomic.AtomicBoolean r8 = t1.h.f1924a
            android.content.Context r7 = r7.createPackageContext(r4, r3)     // Catch:{ NameNotFoundException -> 0x0054 }
        L_0x005f:
            f120c = r7
            return r7
        L_0x0062:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.f.O(android.content.Context, K1.g):android.content.Context");
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [E1.a, L1.e] */
    public static e P(Context context, g gVar) {
        Class<?> loadClass;
        Log.i("f", "Making Creator dynamically");
        ClassLoader classLoader = O(context, gVar).getClassLoader();
        try {
            r.b(classLoader);
            loadClass = classLoader.loadClass("com.google.android.gms.maps.internal.CreatorImpl");
            IBinder iBinder = (IBinder) loadClass.newInstance();
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            if (queryLocalInterface instanceof e) {
                return (e) queryLocalInterface;
            }
            return new a(iBinder, "com.google.android.gms.maps.internal.ICreator", 2);
        } catch (InstantiationException e7) {
            throw new IllegalStateException("Unable to instantiate the dynamic class ".concat(loadClass.getName()), e7);
        } catch (IllegalAccessException e8) {
            throw new IllegalStateException("Unable to call the default constructor of ".concat(loadClass.getName()), e8);
        } catch (ClassNotFoundException e9) {
            throw new IllegalStateException("Unable to find dynamic class com.google.android.gms.maps.internal.CreatorImpl", e9);
        }
    }

    public static void a(byte b5, byte b8, byte b10, byte b11, char[] cArr, int i2) {
        if (!E(b8)) {
            if ((((b8 + 112) + (b5 << 28)) >> 30) == 0 && !E(b10) && !E(b11)) {
                byte b12 = ((b5 & 7) << 18) | ((b8 & 63) << 12) | ((b10 & 63) << 6) | (b11 & 63);
                cArr[i2] = (char) ((b12 >>> 10) + 55232);
                cArr[i2 + 1] = (char) ((b12 & 1023) + 56320);
                return;
            }
        }
        throw F.b();
    }

    public static void b(byte b5, byte b8, char[] cArr, int i2) {
        if (b5 < -62 || E(b8)) {
            throw F.b();
        }
        cArr[i2] = (char) (((b5 & 31) << 6) | (b8 & 63));
    }

    public static void c(byte b5, byte b8, byte b10, char[] cArr, int i2) {
        if (E(b8) || ((b5 == -32 && b8 < -96) || ((b5 == -19 && b8 >= -96) || E(b10)))) {
            throw F.b();
        }
        cArr[i2] = (char) (((b5 & 15) << 12) | ((b8 & 63) << 6) | (b10 & 63));
    }

    public static final C1235b d(String str) {
        C1236c cVar = C1243j.f5043a;
        return new C1235b(C1243j.f5043a, C1240g.e(str));
    }

    public static final C1235b e(String str) {
        C1236c cVar = C1243j.f5043a;
        return new C1235b(C1243j.f5044c, C1240g.e(str));
    }

    public static final void f(LinkedHashMap linkedHashMap) {
        Set<Map.Entry> entrySet = linkedHashMap.entrySet();
        int Z = z.Z(C1196n.w0(entrySet, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(Z);
        for (Map.Entry entry : entrySet) {
            linkedHashMap2.put(entry.getValue(), entry.getKey());
        }
    }

    public static final boolean g(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (j.f(charAt, 128) >= 0 || Character.isLetter(charAt)) {
                return true;
            }
        }
        return false;
    }

    public static final C1235b h(C1240g gVar) {
        C1236c cVar = C1243j.f5043a;
        C1235b bVar = C1243j.k;
        return new C1235b(bVar.f5033a, C1240g.e(gVar.c().concat(bVar.f().c())));
    }

    public static final C1235b i(String str) {
        C1236c cVar = C1243j.f5043a;
        return new C1235b(C1243j.b, C1240g.e(str));
    }

    public static final C1235b j(C1235b bVar) {
        C1236c cVar = C1243j.f5043a;
        return new C1235b(C1243j.f5043a, C1240g.e("U".concat(bVar.f().c())));
    }

    public static final lg.j k(c cVar) {
        lg.j jVar;
        j.e(cVar, "<this>");
        if (cVar instanceof lg.j) {
            jVar = (lg.j) cVar;
        } else {
            jVar = null;
        }
        if (jVar != null) {
            return jVar;
        }
        StringBuilder sb2 = new StringBuilder("This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got ");
        throw new IllegalStateException(A.a.g(v.f4727a, cVar.getClass(), sb2));
    }

    public static final Object l(ListenableFuture listenableFuture, C1271c cVar) {
        try {
            if (listenableFuture.isDone()) {
                return C0246a.Z(listenableFuture);
            }
            C0875l lVar = new C0875l(1, L1.d.m(cVar));
            lVar.r();
            listenableFuture.addListener(new P1.e(listenableFuture, lVar, false, 5), com.google.common.util.concurrent.r.INSTANCE);
            lVar.t(new C0736b(15, listenableFuture));
            Object q = lVar.q();
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            return q;
        } catch (ExecutionException e7) {
            Throwable cause = e7.getCause();
            j.b(cause);
            throw cause;
        }
    }

    public static p m(String str, Iterable iterable) {
        o oVar;
        j.e(str, "debugName");
        Qf.f fVar = new Qf.f();
        Iterator it = iterable.iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            oVar = o.b;
            if (!hasNext) {
                break;
            }
            p pVar = (p) it.next();
            if (pVar != oVar) {
                if (pVar instanceof Af.a) {
                    C1200r.B0(fVar, ((Af.a) pVar).f3303c);
                } else {
                    fVar.add(pVar);
                }
            }
        }
        int i2 = fVar.d;
        if (i2 == 0) {
            return oVar;
        }
        if (i2 != 1) {
            return new Af.a(str, (p[]) fVar.toArray(new p[0]));
        }
        return (p) fVar.get(0);
    }

    public static void n(String str) {
        B b5 = f;
        if (b5 == null) {
            Log.d("DIAGMON_SDK", str);
            return;
        }
        try {
            b5.a(e);
            Log.d("DIAGMON_SDK" + b5.b, str);
        } catch (Exception e7) {
            Log.e("DIAGMON_SDK", e7.getMessage());
        }
    }

    public static void o(String str) {
        B b5 = f;
        if (b5 == null) {
            Log.e("DIAGMON_SDK", str);
            return;
        }
        try {
            b5.a(e);
            Log.e("DIAGMON_SDK" + b5.b, str);
        } catch (Exception e7) {
            Log.e("DIAGMON_SDK", e7.getMessage());
        }
    }

    public static boolean p(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    public static final Object q(C1264n nVar, C1266p pVar) {
        j.e(nVar, "<this>");
        j.e(pVar, "extension");
        if (nVar.k(pVar)) {
            return nVar.j(pVar);
        }
        return null;
    }

    public static final Object r(C1264n nVar, C1266p pVar, int i2) {
        int i7;
        j.e(nVar, "<this>");
        j.e(pVar, "extension");
        nVar.n(pVar);
        C1260j jVar = nVar.d;
        C1265o oVar = pVar.d;
        jVar.getClass();
        D d2 = jVar.f5066a;
        if (oVar.f) {
            Object obj = d2.get(oVar);
            if (obj == null) {
                i7 = 0;
            } else {
                i7 = ((List) obj).size();
            }
            if (i2 >= i7) {
                return null;
            }
            nVar.n(pVar);
            if (oVar.f) {
                Object obj2 = d2.get(oVar);
                if (obj2 != null) {
                    return pVar.a(((List) obj2).get(i2));
                }
                throw new IndexOutOfBoundsException();
            }
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [java.util.Map, java.lang.Object] */
    public static final String s(C0831v vVar) {
        C0814d dVar;
        C1240g gVar;
        C1240g gVar2;
        if (i.z(vVar)) {
            dVar = u(vVar);
        } else {
            dVar = null;
        }
        if (dVar != null) {
            C0814d k = C1353d.k(dVar);
            if (k instanceof O) {
                i.z(k);
                C0814d b5 = C1353d.b(C1353d.k(k), C0897d.g);
                if (!(b5 == null || (gVar2 = (C1240g) C0899f.f3942a.get(C1353d.g(b5))) == null)) {
                    return gVar2.b();
                }
            } else if (k instanceof K) {
                int i2 = C0896c.l;
                LinkedHashMap linkedHashMap = H.f3935i;
                String n = C0068a.n((K) k);
                if (n == null) {
                    gVar = null;
                } else {
                    gVar = (C1240g) linkedHashMap.get(n);
                }
                if (gVar != null) {
                    return gVar.b();
                }
            }
        }
        return null;
    }

    public static final gg.a t(gg.a aVar) {
        j.e(aVar, "<this>");
        if (aVar.getDescriptor().c()) {
            return aVar;
        }
        return new kg.O(aVar);
    }

    public static final C0814d u(C0814d dVar) {
        j.e(dVar, "<this>");
        if (!H.f3936j.contains(dVar.getName()) && !C0899f.d.contains(C1353d.k(dVar).getName())) {
            return null;
        }
        if ((dVar instanceof O) || (dVar instanceof N)) {
            return C1353d.b(dVar, C0897d.f3940i);
        }
        if (dVar instanceof K) {
            return C1353d.b(dVar, C0897d.f3941j);
        }
        return null;
    }

    public static final C0814d v(C0814d dVar) {
        j.e(dVar, "<this>");
        C0814d u = u(dVar);
        if (u != null) {
            return u;
        }
        int i2 = C0898e.l;
        C1240g name = dVar.getName();
        j.d(name, "getName(...)");
        if (!C0898e.b(name)) {
            return null;
        }
        return C1353d.b(dVar, C0897d.k);
    }

    public static final s w(G g, C1209f fVar, B1.b bVar, boolean z, boolean z3, boolean z7) {
        j.e(g, "proto");
        j.e(fVar, "nameResolver");
        C1266p pVar = k.d;
        j.d(pVar, "propertySignature");
        of.e eVar = (of.e) q(g, pVar);
        if (eVar != null) {
            if (z) {
                C1258h hVar = pf.i.f5029a;
                pf.d b5 = pf.i.b(g, fVar, bVar, z7);
                if (b5 != null) {
                    return L1.d.h(b5);
                }
            } else if (z3 && (eVar.e & 2) == 2) {
                of.c cVar = eVar.g;
                j.d(cVar, "getSyntheticMethod(...)");
                return new s(fVar.getString(cVar.f).concat(fVar.getString(cVar.g)));
            }
        }
        return null;
    }

    public static /* synthetic */ s x(G g, C1209f fVar, B1.b bVar, int i2) {
        boolean z;
        boolean z3;
        if ((i2 & 8) != 0) {
            z = false;
        } else {
            z = true;
        }
        if ((i2 & 16) != 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        return w(g, fVar, bVar, z, z3, true);
    }

    public static final Object y(n nVar, t tVar) {
        j.e(nVar, "<this>");
        j.e(tVar, "p");
        return nVar.invoke();
    }

    public static final boolean z(C0816f fVar, C0814d dVar) {
        j.e(fVar, "<this>");
        j.e(dVar, "specialCallableDescriptor");
        C0822l g = dVar.g();
        j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        Hf.B i2 = ((C0816f) g).i();
        j.d(i2, "getDefaultType(...)");
        for (C0816f j2 = C1301e.j(fVar); j2 != null; j2 = C1301e.j(j2)) {
            if (!(j2 instanceof C0918c)) {
                Hf.B i7 = j2.i();
                if (i7 != null) {
                    ArrayDeque arrayDeque = new ArrayDeque();
                    c0 c0Var = null;
                    arrayDeque.add(new If.n(i7, (If.n) null));
                    M s0 = i2.s0();
                    while (true) {
                        if (arrayDeque.isEmpty()) {
                            break;
                        }
                        If.n nVar = (If.n) arrayDeque.poll();
                        C0774x xVar = nVar.f3470a;
                        M s02 = xVar.s0();
                        if (s02 == null) {
                            If.g.a(3);
                            throw null;
                        } else if (s0 == null) {
                            If.g.a(4);
                            throw null;
                        } else if (s02.equals(s0)) {
                            boolean u02 = xVar.u0();
                            for (If.n nVar2 = nVar.b; nVar2 != null; nVar2 = nVar2.b) {
                                C0774x xVar2 = nVar2.f3470a;
                                Iterable e02 = xVar2.e0();
                                boolean z = e02 instanceof Collection;
                                C0756e eVar = Hf.N.b;
                                if (!z || !((Collection) e02).isEmpty()) {
                                    Iterator it = e02.iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        d0 a7 = ((P) it.next()).a();
                                        d0 d0Var = d0.INVARIANT;
                                        if (a7 != d0Var) {
                                            xVar = (C0774x) og.k.g(new X(L2.a.I(eVar.f(xVar2.s0(), xVar2.e0()))).g(xVar, d0Var)).b;
                                            break;
                                        }
                                    }
                                }
                                xVar = new X(eVar.f(xVar2.s0(), xVar2.e0())).g(xVar, d0.INVARIANT);
                                if (u02 || xVar2.u0()) {
                                    u02 = true;
                                } else {
                                    u02 = false;
                                }
                            }
                            M s03 = xVar.s0();
                            if (s03 == null) {
                                If.g.a(3);
                                throw null;
                            } else if (s03.equals(s0)) {
                                c0Var = a0.g(xVar, u02);
                            } else {
                                throw new AssertionError("Type constructors should be equals!\nsubstitutedSuperType: " + If.g.n(s03) + ", \n\nsupertype: " + If.g.n(s0) + " \n" + s03.equals(s0));
                            }
                        } else {
                            for (C0774x xVar3 : s02.h()) {
                                j.b(xVar3);
                                arrayDeque.add(new If.n(xVar3, nVar));
                            }
                        }
                    }
                    if (c0Var != null) {
                        return !i.z(j2);
                    }
                } else {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[]{"subtype", "kotlin/reflect/jvm/internal/impl/types/checker/TypeCheckingProcedure", "findCorrespondingSupertype"}));
                }
            }
        }
        return false;
    }

    public abstract void F(int i2);

    public abstract void G(Typeface typeface, boolean z);
}
