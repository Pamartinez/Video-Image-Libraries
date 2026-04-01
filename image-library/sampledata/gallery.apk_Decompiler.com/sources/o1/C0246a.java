package o1;

import B1.b;
import D0.f;
import Dd.C0731b;
import Dd.C0732c;
import Df.B;
import Fd.C0744a;
import Ff.j;
import H1.e;
import H1.g;
import He.F;
import Ke.x0;
import Od.d;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0826p;
import Qe.C0827q;
import Vf.C0875l;
import a.C0068a;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Trace;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import ee.a0;
import io.grpc.MethodDescriptor;
import io.grpc.stub.StreamObserver;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import jg.a;
import jg.c;
import kg.V;
import kotlin.jvm.internal.v;
import lf.C1172z;
import lf.f0;
import oe.C1214c;
import og.k;
import qe.C1227c;
import qe.C1230f;
import qe.C1231g;
import qe.C1232h;
import qe.C1233i;
import re.C1245a;
import u1.C0285c;
import u1.h;
import w1.r;

/* renamed from: o1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0246a implements c, a {
    public static g d = null;
    public static Od.c e = null;
    public static boolean f = false;
    public static f g;

    public static void H(Context context, ContentValues contentValues, C0732c cVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("av", C0068a.G(context));
        hashMap.put("uv", cVar.f3332c);
        hashMap.put("v", C0731b.b);
        d dVar = d.ONE_DEPTH;
        contentValues.put("appCommon_data", f0(hashMap, dVar));
        if (!TextUtils.isEmpty((CharSequence) null)) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("auid", (Object) null);
            hashMap2.put("at", String.valueOf(cVar.e));
            contentValues.put("appCommon_did", f0(hashMap2, dVar));
        }
    }

    public static void I(MethodDescriptor methodDescriptor, StreamObserver streamObserver) {
        F.n(methodDescriptor, "methodDescriptor");
        F.n(streamObserver, "responseObserver");
        a0 a0Var = a0.m;
        String str = methodDescriptor.b;
        streamObserver.a(a0Var.g("Method " + str + " is unimplemented").a());
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [ag.a, java.util.function.BiFunction, java.lang.Object] */
    public static final Object J(CompletableFuture completableFuture, C1227c cVar) {
        CompletableFuture completableFuture2 = completableFuture.toCompletableFuture();
        if (completableFuture2.isDone()) {
            try {
                return completableFuture2.get();
            } catch (ExecutionException e7) {
                e = e7;
                Throwable cause = e.getCause();
                if (cause != null) {
                    e = cause;
                }
                throw e;
            }
        } else {
            C0875l lVar = new C0875l(1, L1.d.m(cVar));
            lVar.r();
            ? obj = new Object();
            obj.cont = lVar;
            completableFuture.handle(obj);
            lVar.t(new j(1, completableFuture2, obj));
            Object q = lVar.q();
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            return q;
        }
    }

    public static C1214c K(List list) {
        kotlin.jvm.internal.j.e(list, "builder");
        C1214c cVar = (C1214c) list;
        cVar.u();
        cVar.f = true;
        if (cVar.e > 0) {
            return cVar;
        }
        return C1214c.g;
    }

    public static int N(long j2) {
        boolean z;
        int i2 = (int) j2;
        if (((long) i2) == j2) {
            z = true;
        } else {
            z = false;
        }
        F.k(z, "Out of range: %s", j2);
        return i2;
    }

    public static boolean O(int i2, Long l) {
        if (System.currentTimeMillis() > (((long) i2) * MediaApiContract.DAY_IN_MILLI) + l.longValue()) {
            return true;
        }
        return false;
    }

    public static final double P(double d2, Uf.c cVar, Uf.c cVar2) {
        kotlin.jvm.internal.j.e(cVar2, "targetUnit");
        long convert = cVar2.a().convert(1, cVar.a());
        if (convert > 0) {
            return d2 * ((double) convert);
        }
        return d2 / ((double) cVar.a().convert(1, cVar2.a()));
    }

    public static final long Q(long j2, Uf.c cVar, Uf.c cVar2) {
        kotlin.jvm.internal.j.e(cVar, "sourceUnit");
        kotlin.jvm.internal.j.e(cVar2, "targetUnit");
        return cVar2.a().convert(j2, cVar.a());
    }

    public static C1214c R() {
        return new C1214c(10);
    }

    public static final C0826p T(f0 f0Var) {
        int i2;
        if (f0Var == null) {
            i2 = -1;
        } else {
            i2 = B.b[f0Var.ordinal()];
        }
        switch (i2) {
            case 1:
                C0826p pVar = C0827q.d;
                kotlin.jvm.internal.j.d(pVar, "INTERNAL");
                return pVar;
            case 2:
                C0826p pVar2 = C0827q.f3675a;
                kotlin.jvm.internal.j.d(pVar2, "PRIVATE");
                return pVar2;
            case 3:
                C0826p pVar3 = C0827q.b;
                kotlin.jvm.internal.j.d(pVar3, "PRIVATE_TO_THIS");
                return pVar3;
            case 4:
                C0826p pVar4 = C0827q.f3676c;
                kotlin.jvm.internal.j.d(pVar4, "PROTECTED");
                return pVar4;
            case 5:
                C0826p pVar5 = C0827q.e;
                kotlin.jvm.internal.j.d(pVar5, "PUBLIC");
                return pVar5;
            case 6:
                C0826p pVar6 = C0827q.f;
                kotlin.jvm.internal.j.d(pVar6, "LOCAL");
                return pVar6;
            default:
                C0826p pVar7 = C0827q.f3675a;
                kotlin.jvm.internal.j.d(pVar7, "PRIVATE");
                return pVar7;
        }
    }

    public static b U(Bitmap bitmap) {
        r.c(bitmap, "image must not be null");
        try {
            g gVar = d;
            r.c(gVar, "IBitmapDescriptorFactory is not initialized");
            e eVar = (e) gVar;
            Parcel c5 = eVar.c();
            H1.d.c(c5, bitmap);
            Parcel b = eVar.b(c5, 6);
            C1.a d2 = C1.b.d(b.readStrongBinder());
            b.recycle();
            return new b(d2);
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    public static int V(byte b, byte b5, byte b8, byte b10) {
        return (b << 24) | ((b5 & 255) << 16) | ((b8 & 255) << 8) | (b10 & 255);
    }

    public static int W(int i2, View view) {
        Context context = view.getContext();
        TypedValue N6 = k.N(view.getContext(), i2, view.getClass().getCanonicalName());
        int i7 = N6.resourceId;
        if (i7 != 0) {
            return ContextCompat.getColor(context, i7);
        }
        return N6.data;
    }

    public static long Y() {
        return TimeUnit.MILLISECONDS.toMinutes(((long) TimeZone.getDefault().getRawOffset()) + ((long) android.icu.util.TimeZone.getDefault().getDSTSavings()));
    }

    public static Object Z(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static boolean a0(Context context, String str) {
        String G5 = C0068a.G(context);
        String string = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).getString(str, "");
        if (!TextUtils.isEmpty(string) && string.equals(G5)) {
            return false;
        }
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).edit().putString(str, G5).apply();
        return true;
    }

    public static boolean b0(Context context) {
        if (712601000 > C0068a.x(context)) {
            return true;
        }
        return false;
    }

    public static int c0(float f5, int i2, int i7) {
        return ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i7, Math.round(((float) Color.alpha(i7)) * f5)), i2);
    }

    public static x0 d0(C0814d dVar, Ae.a aVar) {
        if (aVar != null) {
            return new x0(dVar, aVar);
        }
        throw new IllegalArgumentException("Argument for @NotNull parameter 'initializer' of kotlin/reflect/jvm/internal/ReflectProperties.lazySoft must not be null");
    }

    public static List e0(Object obj) {
        List singletonList = Collections.singletonList(obj);
        kotlin.jvm.internal.j.d(singletonList, "singletonList(...)");
        return singletonList;
    }

    public static String f0(Map map, d dVar) {
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (sb2.length() > 0) {
                sb2.append(dVar.a());
            }
            sb2.append((String) entry.getKey());
            sb2.append(dVar.b());
            sb2.append((String) entry.getValue());
        }
        return sb2.toString();
    }

    public static final C0813c g0(C1172z zVar) {
        int i2;
        if (zVar == null) {
            i2 = -1;
        } else {
            i2 = B.f3336a[zVar.ordinal()];
        }
        if (i2 == 1) {
            return C0813c.DECLARATION;
        }
        if (i2 == 2) {
            return C0813c.FAKE_OVERRIDE;
        }
        if (i2 == 3) {
            return C0813c.DELEGATION;
        }
        if (i2 != 4) {
            return C0813c.DECLARATION;
        }
        return C0813c.SYNTHESIZED;
    }

    public static C1232h h0(C1230f fVar, C1231g gVar) {
        kotlin.jvm.internal.j.e(gVar, "key");
        if (kotlin.jvm.internal.j.a(fVar.getKey(), gVar)) {
            return C1233i.d;
        }
        return fVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r9v2, types: [ee.M, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v14, types: [ee.M, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static ee.M i0(android.os.Parcel r9, ee.C0969b r10) {
        /*
            int r0 = r9.readInt()
            if (r0 != 0) goto L_0x000c
            ee.M r9 = new ee.M
            r9.<init>()
            return r9
        L_0x000c:
            int r1 = r0 * 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            r3 = r2
        L_0x0012:
            if (r2 >= r0) goto L_0x0089
            int r4 = r9.readInt()
            int r3 = r3 + 4
            int r3 = r3 + r4
            java.lang.String r5 = "Metadata too large"
            r6 = 8192(0x2000, float:1.14794E-41)
            if (r3 > r6) goto L_0x007d
            byte[] r7 = new byte[r4]
            if (r4 <= 0) goto L_0x0028
            r9.readByteArray(r7)
        L_0x0028:
            int r4 = r2 * 2
            r1[r4] = r7
            int r7 = r9.readInt()
            int r3 = r3 + 4
            r8 = -1
            if (r7 == r8) goto L_0x0062
            if (r7 < 0) goto L_0x0054
            int r3 = r3 + r7
            if (r3 > r6) goto L_0x0048
            byte[] r5 = new byte[r7]
            if (r7 <= 0) goto L_0x0041
            r9.readByteArray(r5)
        L_0x0041:
            int r4 = r4 + 1
            r1[r4] = r5
            int r2 = r2 + 1
            goto L_0x0012
        L_0x0048:
            ee.a0 r9 = ee.a0.k
            ee.a0 r9 = r9.g(r5)
            ee.b0 r10 = new ee.b0
            r10.<init>(r9)
            throw r10
        L_0x0054:
            ee.a0 r9 = ee.a0.n
            java.lang.String r10 = "Unrecognized metadata sentinel"
            ee.a0 r9 = r9.g(r10)
            ee.b0 r10 = new ee.b0
            r10.<init>(r9)
            throw r10
        L_0x0062:
            ee.a r9 = fe.c.f4336x
            java.util.IdentityHashMap r10 = r10.f4292a
            java.lang.Object r9 = r10.get(r9)
            io.grpc.binder.e r9 = (io.grpc.binder.e) r9
            r9.getClass()
            ee.a0 r9 = ee.a0.f4287i
            java.lang.String r10 = "Parcelable metadata values not allowed"
            ee.a0 r9 = r9.g(r10)
            ee.b0 r10 = new ee.b0
            r10.<init>(r9)
            throw r10
        L_0x007d:
            ee.a0 r9 = ee.a0.k
            ee.a0 r9 = r9.g(r5)
            ee.b0 r10 = new ee.b0
            r10.<init>(r9)
            throw r10
        L_0x0089:
            int r9 = ee.C0991y.f4316a
            ee.M r9 = new ee.M
            r9.<init>()
            r9.b = r0
            r9.f4275a = r1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.C0246a.i0(android.os.Parcel, ee.b):ee.M");
    }

    public static int j0(long j2) {
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j2 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j2;
    }

    public static void k0(Context context, C0732c cVar) {
        int i2;
        Uri uri;
        Trace.beginSection("PropertyLogSender sendLog");
        boolean M2 = cVar.d.M();
        if (710000000 > C0068a.x(context) && !M2) {
            C0068a.b("user do not agree Property");
        } else {
            Map<String, ?> all = context.getSharedPreferences(com.samsung.context.sdk.samsunganalytics.internal.sender.c.D("SAProperties"), 0).getAll();
            if (all != null) {
                all.remove("guid");
            }
            if (all == null || all.isEmpty()) {
                C0068a.c("PropertyLogBuildClient", "No Property log");
            } else {
                String f02 = f0(Gd.a.f(all), d.TWO_DEPTH);
                String y = L2.a.y(f02);
                String string = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).getString("property_data", "");
                long j2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).getLong("property_sent_date", 0);
                if (!string.equals(y) || O(1, Long.valueOf(j2))) {
                    com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).edit().putString("property_data", y).apply();
                    com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context).edit().putLong("property_sent_date", System.currentTimeMillis()).apply();
                    C0068a.g("update property, send it");
                    C0068a.g("Send Property Log");
                    HashMap hashMap = new HashMap();
                    String valueOf = String.valueOf(System.currentTimeMillis());
                    hashMap.put("ts", valueOf);
                    hashMap.put("t", "pp");
                    hashMap.put("cp", f02);
                    int i7 = k.f;
                    if (i7 >= 3) {
                        hashMap.put("v", C0731b.b);
                        hashMap.put("tz", String.valueOf(Y()));
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("tcType", 0);
                        contentValues.put("tid", cVar.f3331a);
                        contentValues.put("logType", com.samsung.context.sdk.samsunganalytics.internal.sender.b.UIX.a());
                        contentValues.put("timeStamp", valueOf);
                        contentValues.put("agree", Integer.valueOf(M2 ? 1 : 0));
                        contentValues.put("body", f0(hashMap, d.ONE_DEPTH));
                        if (!b0(context)) {
                            H(context, contentValues, cVar);
                        }
                        if (!b0(context)) {
                            contentValues.put("networkType", -1);
                        }
                        try {
                            uri = context.getContentResolver().insert(Uri.parse("content://com.sec.android.log.diagmonagent.sa/log"), contentValues);
                        } catch (IllegalArgumentException e7) {
                            C0068a.J("failed to send properties" + e7.getMessage());
                            uri = null;
                        }
                        if (uri == null) {
                            C0068a.b("Property send fail");
                        } else {
                            i2 = Integer.parseInt(uri.getLastPathSegment());
                        }
                    } else {
                        i2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.w(context, i7, cVar).c(hashMap);
                    }
                    C0068a.g("Send Property Log Result = " + i2);
                } else {
                    C0068a.b("do not send property < 1day");
                }
            }
        }
        Trace.endSection();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v6, resolved type: int} */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x026a, code lost:
        r6 = r17;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x02d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void l0(android.content.Context r20, Dd.C0732c r21) {
        /*
            r1 = r20
            r0 = r21
            java.lang.String r2 = "SettingLogSender sendLog"
            android.os.Trace.beginSection(r2)
            G0.e r2 = r0.d
            boolean r2 = r2.M()
            r3 = 710000000(0x2a51bd80, float:1.8628675E-13)
            int r4 = a.C0068a.x(r1)
            if (r3 > r4) goto L_0x0019
            goto L_0x0023
        L_0x0019:
            if (r2 != 0) goto L_0x0023
            java.lang.String r0 = "user do not agree setting"
            a.C0068a.b(r0)
            goto L_0x02fb
        L_0x0023:
            android.content.SharedPreferences r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r1)
            java.lang.String r3 = "status_sent_date"
            r4 = 0
            long r6 = r2.getLong(r3, r4)
            java.lang.Long r2 = java.lang.Long.valueOf(r6)
            r6 = 1
            boolean r2 = O(r6, r2)
            if (r2 != 0) goto L_0x0042
            java.lang.String r0 = "do not send setting < 1day"
            a.C0068a.b(r0)
            goto L_0x02fb
        L_0x0042:
            android.content.SharedPreferences r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r1)
            java.util.HashSet r7 = new java.util.HashSet
            r7.<init>()
            java.lang.String r8 = "AppPrefs"
            java.util.Set r2 = r2.getStringSet(r8, r7)
            Od.d r7 = Od.d.TWO_DEPTH
            java.lang.String r8 = r7.b()
            java.lang.String r7 = r7.a()
            Od.d r9 = Od.d.THREE_DEPTH
            java.lang.String r9 = r9.a()
            boolean r10 = r2.isEmpty()
            r12 = 2
            r13 = 0
            if (r10 == 0) goto L_0x0070
            r17 = r6
            r16 = r13
            r10 = 0
            goto L_0x017a
        L_0x0070:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r2 = r2.iterator()
            java.lang.String r14 = ""
            r15 = r14
        L_0x007c:
            boolean r16 = r2.hasNext()
            if (r16 == 0) goto L_0x016d
            java.lang.Object r16 = r2.next()
            r17 = r6
            r6 = r16
            java.lang.String r6 = (java.lang.String) r6
            android.content.SharedPreferences r16 = r1.getSharedPreferences(r6, r13)
            java.util.Map r11 = r16.getAll()
            r16 = r13
            java.util.TreeMap r13 = new java.util.TreeMap
            r13.<init>()
            android.content.SharedPreferences r4 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r1)
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.Set r4 = r4.getStringSet(r6, r5)
            java.util.Iterator r4 = r4.iterator()
        L_0x00ac:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00cd
            java.lang.Object r5 = r4.next()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String[] r5 = r5.split(r9, r12)
            r6 = r5[r16]
            r18 = r2
            int r2 = r5.length
            if (r2 != r12) goto L_0x00c6
            r2 = r5[r17]
            goto L_0x00c7
        L_0x00c6:
            r2 = r14
        L_0x00c7:
            r13.put(r6, r2)
            r2 = r18
            goto L_0x00ac
        L_0x00cd:
            r18 = r2
            java.util.Set r2 = r13.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x00d7:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0163
            java.lang.Object r4 = r2.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            java.lang.Object r5 = r11.get(r5)
            r6 = 1024(0x400, float:1.435E-42)
            if (r5 != 0) goto L_0x00f6
            java.lang.Object r5 = r4.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x012c
        L_0x00f6:
            boolean r13 = r5 instanceof java.util.Set
            if (r13 != 0) goto L_0x00ff
            java.lang.String r5 = java.lang.String.valueOf(r5)
            goto L_0x012c
        L_0x00ff:
            java.util.Set r5 = (java.util.Set) r5
            java.util.Iterator r5 = r5.iterator()
            r13 = r14
        L_0x0106:
            boolean r19 = r5.hasNext()
            if (r19 == 0) goto L_0x0128
            java.lang.Object r19 = r5.next()
            r12 = r19
            java.lang.String r12 = (java.lang.String) r12
            boolean r19 = android.text.TextUtils.isEmpty(r13)
            if (r19 != 0) goto L_0x011e
            java.lang.String r13 = i.C0212a.A(r13, r9)
        L_0x011e:
            java.lang.String r13 = i.C0212a.A(r13, r12)
            int r12 = r13.length()
            if (r12 < r6) goto L_0x012a
        L_0x0128:
            r5 = r13
            goto L_0x012c
        L_0x012a:
            r12 = 2
            goto L_0x0106
        L_0x012c:
            java.lang.Object r4 = r4.getKey()
            java.lang.String r4 = (java.lang.String) r4
            r12 = 100
            java.lang.String r4 = Gd.a.e(r12, r4)
            java.lang.String r5 = Gd.a.e(r6, r5)
            java.lang.String r4 = i.C0212a.B(r4, r8, r5)
            boolean r5 = android.text.TextUtils.isEmpty(r15)
            if (r5 != 0) goto L_0x015c
            int r5 = r15.length()
            int r6 = r4.length()
            int r6 = r6 + r5
            r5 = 512(0x200, float:7.175E-43)
            if (r6 <= r5) goto L_0x0158
            r10.add(r15)
            r15 = r14
            goto L_0x015c
        L_0x0158:
            java.lang.String r15 = i.C0212a.A(r15, r7)
        L_0x015c:
            java.lang.String r15 = i.C0212a.A(r15, r4)
            r12 = 2
            goto L_0x00d7
        L_0x0163:
            r13 = r16
            r6 = r17
            r2 = r18
            r4 = 0
            goto L_0x007c
        L_0x016d:
            r17 = r6
            r16 = r13
            boolean r2 = r15.isEmpty()
            if (r2 != 0) goto L_0x017a
            r10.add(r15)
        L_0x017a:
            if (r10 == 0) goto L_0x02f4
            boolean r2 = r10.isEmpty()
            if (r2 == 0) goto L_0x0184
            goto L_0x02f4
        L_0x0184:
            java.lang.String r2 = "Send Setting Log"
            a.C0068a.b(r2)
            int r2 = og.k.f
            r4 = 3
            java.lang.String r5 = "st"
            java.lang.String r6 = "t"
            java.lang.String r7 = "ts"
            java.lang.String r8 = "sti"
            if (r2 != r4) goto L_0x026e
            long r11 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = java.lang.String.valueOf(r11)
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            java.lang.String r9 = "tcType"
            java.lang.Integer r11 = java.lang.Integer.valueOf(r16)
            r4.put(r9, r11)
            java.lang.String r9 = "tid"
            java.lang.String r11 = r0.f3331a
            r4.put(r9, r11)
            com.samsung.context.sdk.samsunganalytics.internal.sender.b r9 = com.samsung.context.sdk.samsunganalytics.internal.sender.b.UIX
            java.lang.String r9 = r9.a()
            java.lang.String r11 = "logType"
            r4.put(r11, r9)
            java.lang.String r9 = "timeStamp"
            r4.put(r9, r2)
            G0.e r9 = r0.d
            boolean r9 = r9.M()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r11 = "agree"
            r4.put(r11, r9)
            boolean r9 = b0(r1)
            if (r9 != 0) goto L_0x01e2
            H(r1, r4, r0)
        L_0x01e2:
            boolean r0 = b0(r1)
            if (r0 != 0) goto L_0x01f2
            r0 = -1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r9 = "networkType"
            r4.put(r9, r0)
        L_0x01f2:
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            r9.put(r7, r2)
            r9.put(r6, r5)
            java.lang.String r0 = "v"
            java.lang.String r2 = "6.05.082"
            r9.put(r0, r2)
            long r5 = Y()
            java.lang.String r0 = java.lang.String.valueOf(r5)
            java.lang.String r2 = "tz"
            r9.put(r2, r0)
            java.lang.String r0 = "content://com.sec.android.log.diagmonagent.sa/log"
            android.net.Uri r2 = android.net.Uri.parse(r0)
            java.util.Iterator r5 = r10.iterator()
        L_0x021d:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x026a
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            r9.put(r8, r0)
            Od.d r0 = Od.d.ONE_DEPTH
            java.lang.String r0 = f0(r9, r0)
            java.lang.String r6 = "body"
            r4.put(r6, r0)
            android.content.ContentResolver r0 = r1.getContentResolver()     // Catch:{ IllegalArgumentException -> 0x0240 }
            android.net.Uri r0 = r0.insert(r2, r4)     // Catch:{ IllegalArgumentException -> 0x0240 }
            goto L_0x0257
        L_0x0240:
            r0 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "failed to send setting log"
            r6.<init>(r7)
            java.lang.String r0 = r0.getMessage()
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            a.C0068a.K(r0)
            r0 = 0
        L_0x0257:
            if (r0 != 0) goto L_0x025c
        L_0x0259:
            r6 = r16
            goto L_0x026c
        L_0x025c:
            java.lang.String r0 = r0.getLastPathSegment()
            int r0 = java.lang.Integer.parseInt(r0)
            r11 = 2
            if (r0 == 0) goto L_0x021d
            if (r0 == r11) goto L_0x021d
            goto L_0x0259
        L_0x026a:
            r6 = r17
        L_0x026c:
            r13 = r6
            goto L_0x02bb
        L_0x026e:
            r11 = 2
            if (r2 == r11) goto L_0x0288
            if (r2 != 0) goto L_0x0274
            goto L_0x0288
        L_0x0274:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = "Sender type is invalid : "
            r0.<init>(r4)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            a.C0068a.K(r0)
            r13 = r16
            goto L_0x02bb
        L_0x0288:
            long r11 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = java.lang.String.valueOf(r11)
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            r4.put(r7, r2)
            r4.put(r6, r5)
            java.util.Iterator r2 = r10.iterator()
        L_0x029f:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x026a
            java.lang.Object r5 = r2.next()
            java.lang.String r5 = (java.lang.String) r5
            r4.put(r8, r5)
            int r5 = og.k.f
            com.samsung.context.sdk.samsunganalytics.internal.sender.a r5 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.w(r1, r5, r0)
            int r5 = r5.c(r4)
            if (r5 == 0) goto L_0x029f
            goto L_0x0259
        L_0x02bb:
            if (r13 == 0) goto L_0x02d1
            android.content.SharedPreferences r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r1)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            long r1 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r0 = r0.putLong(r3, r1)
            r0.apply()
            goto L_0x02e2
        L_0x02d1:
            android.content.SharedPreferences r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r1)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r1 = 0
            android.content.SharedPreferences$Editor r0 = r0.putLong(r3, r1)
            r0.apply()
        L_0x02e2:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Send Setting Log Result = "
            r0.<init>(r1)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            a.C0068a.b(r0)
            goto L_0x02fb
        L_0x02f4:
            java.lang.String r0 = "Setting Sender"
            java.lang.String r1 = "No status log"
            a.C0068a.c(r0, r1)
        L_0x02fb:
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.C0246a.l0(android.content.Context, Dd.c):void");
    }

    public static void m0(String str) {
        if (Build.TYPE.equals("user")) {
            C0068a.d(str);
            return;
        }
        throw new RuntimeException(str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v3, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v5, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.Long} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Integer n0(java.lang.String r19) {
        /*
            r0 = r19
            r0.getClass()
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x0010
        L_0x000b:
            r0 = 0
            r16 = 0
            goto L_0x0086
        L_0x0010:
            r1 = 0
            char r3 = r0.charAt(r1)
            r4 = 45
            if (r3 != r4) goto L_0x001a
            r1 = 1
        L_0x001a:
            int r3 = r0.length()
            if (r1 != r3) goto L_0x0021
            goto L_0x000b
        L_0x0021:
            int r3 = r1 + 1
            char r4 = r0.charAt(r1)
            r5 = -1
            r6 = 128(0x80, float:1.794E-43)
            if (r4 >= r6) goto L_0x0031
            byte[] r7 = J2.c.f367a
            byte r4 = r7[r4]
            goto L_0x0034
        L_0x0031:
            byte[] r4 = J2.c.f367a
            r4 = r5
        L_0x0034:
            if (r4 < 0) goto L_0x0060
            r7 = 10
            if (r4 < r7) goto L_0x003b
            goto L_0x0060
        L_0x003b:
            int r4 = -r4
            long r8 = (long) r4
            long r10 = (long) r7
            r12 = -9223372036854775808
            long r14 = r12 / r10
        L_0x0042:
            int r4 = r0.length()
            if (r3 >= r4) goto L_0x0073
            int r4 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 >= r6) goto L_0x0055
            byte[] r16 = J2.c.f367a
            byte r3 = r16[r3]
            goto L_0x0058
        L_0x0055:
            byte[] r3 = J2.c.f367a
            r3 = r5
        L_0x0058:
            if (r3 < 0) goto L_0x0060
            if (r3 >= r7) goto L_0x0060
            int r16 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x0063
        L_0x0060:
            r16 = 0
            goto L_0x006d
        L_0x0063:
            long r8 = r8 * r10
            r16 = 0
            long r2 = (long) r3
            long r17 = r2 + r12
            int r17 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r17 >= 0) goto L_0x0070
        L_0x006d:
            r0 = r16
            goto L_0x0086
        L_0x0070:
            long r8 = r8 - r2
            r3 = r4
            goto L_0x0042
        L_0x0073:
            r16 = 0
            if (r1 == 0) goto L_0x007c
            java.lang.Long r0 = java.lang.Long.valueOf(r8)
            goto L_0x0086
        L_0x007c:
            int r0 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r0 != 0) goto L_0x0081
            goto L_0x006d
        L_0x0081:
            long r0 = -r8
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
        L_0x0086:
            if (r0 == 0) goto L_0x009f
            long r1 = r0.longValue()
            int r3 = r0.intValue()
            long r3 = (long) r3
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x0096
            goto L_0x009f
        L_0x0096:
            int r0 = r0.intValue()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L_0x009f:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.C0246a.n0(java.lang.String):java.lang.Integer");
    }

    public char A(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return w();
    }

    public String B() {
        S();
        throw null;
    }

    public boolean D() {
        return true;
    }

    public c E(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return u(v.h(i2));
    }

    public abstract byte G();

    public C0285c L(Context context, Looper looper, C0744a aVar, Object obj, u1.g gVar, h hVar) {
        return M(context, looper, aVar, obj, (v1.k) gVar, (v1.k) hVar);
    }

    public C0285c M(Context context, Looper looper, C0744a aVar, Object obj, v1.k kVar, v1.k kVar2) {
        throw new UnsupportedOperationException("buildClient must be implemented");
    }

    public void S() {
        throw new IllegalArgumentException(v.f4727a.b(getClass()) + " can't retrieve untyped values");
    }

    public abstract void X(K0.b bVar, float f5, float f8);

    public a a(ig.f fVar) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return this;
    }

    public void b(ig.f fVar) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
    }

    public int c(ig.f fVar) {
        kotlin.jvm.internal.j.e(fVar, "enumDescriptor");
        S();
        throw null;
    }

    public abstract int f();

    public Object g(gg.a aVar) {
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        return aVar.deserialize(this);
    }

    public short h(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return o();
    }

    public abstract long j();

    public byte k(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return G();
    }

    public String l(ig.f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return B();
    }

    public double m(ig.f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return s();
    }

    public Object n(ig.f fVar, int i2, gg.a aVar, Object obj) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        return g(aVar);
    }

    public abstract short o();

    public float p() {
        S();
        throw null;
    }

    public Object q(ig.f fVar, int i2, gg.a aVar, Object obj) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        if (aVar.getDescriptor().c() || D()) {
            return g(aVar);
        }
        return null;
    }

    public boolean r(ig.f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return v();
    }

    public double s() {
        S();
        throw null;
    }

    public long t(ig.f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return j();
    }

    public c u(ig.f fVar) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return this;
    }

    public boolean v() {
        S();
        throw null;
    }

    public char w() {
        S();
        throw null;
    }

    public int x(ig.f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return f();
    }

    public float z(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return p();
    }
}
