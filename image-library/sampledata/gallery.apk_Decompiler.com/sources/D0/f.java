package D0;

import C0.j;
import Dd.C0731b;
import Dd.C0732c;
import Df.C0741g;
import Df.C0742h;
import Fd.C0744a;
import Gf.m;
import Hf.C0774x;
import N2.g;
import N2.i;
import N2.l;
import N2.t;
import N2.w;
import N2.y;
import Qe.C;
import Qe.C0816f;
import Qe.E;
import Qe.F;
import Qe.Q;
import Qe.U;
import R2.e;
import R2.h;
import R2.k;
import R2.n;
import Xd.a;
import Yd.b;
import a.C0068a;
import ae.C0902a;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ee.C0967D;
import ee.C0973f;
import ee.C0981n;
import ee.G;
import ee.M;
import ee.a0;
import ee.b0;
import ge.C1008a1;
import ge.C1011b1;
import ge.C1020e1;
import ge.C1056t;
import ge.F0;
import ge.Q0;
import ge.V0;
import ge.W0;
import ge.Z;
import ge.r;
import he.C1076a;
import i.C0212a;
import io.grpc.Deadline;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import jf.C1105e;
import jf.o;
import jf.p;
import jf.q;
import jf.s;
import lf.C1157j;
import lf.C1166t;
import mf.C1178a;
import ne.C1194l;
import ne.C1196n;
import ne.z;
import nf.C1209f;
import org.json.JSONException;
import org.json.JSONObject;
import qf.C1235b;
import qf.C1240g;
import vf.C1321a;
import vf.C1326f;
import vf.C1327g;
import vf.C1329i;
import vf.C1339s;
import vf.C1346z;
import z2.u;
import z2.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class f implements C0742h, e, a, r, q, o, p {

    /* renamed from: i  reason: collision with root package name */
    public static f f105i;
    public final /* synthetic */ int d;
    public Object e;
    public Object f;
    public Object g;

    /* renamed from: h  reason: collision with root package name */
    public Object f106h;

    public /* synthetic */ f(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f106h = obj4;
    }

    public static String A(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                String str2 = context.getFilesDir().getAbsolutePath() + "/zip";
                new File(str2).mkdir();
                String str3 = str2 + "/" + (System.currentTimeMillis() + ".zip");
                C0068a.e0(str, str3);
                return str3;
            } catch (Exception e7) {
                D1.f.L("Zipping failure");
                D1.f.L("Exception : " + e7.getMessage());
                throw e7;
            }
        } else {
            D1.f.L("No Log Path, You have to set LogPath to report logs");
            throw new IOException("Not found");
        }
    }

    public static f F() {
        if (f105i == null) {
            f105i = new f(17);
        }
        return f105i;
    }

    public static void R(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return;
        }
        if (file.delete()) {
            D1.f.n("Removed zipFile : ".concat(str));
        } else {
            D1.f.n("Couldn't removed zipFile : ".concat(str));
        }
    }

    public static void y(Bundle bundle, String str) {
        ParcelFileDescriptor parcelFileDescriptor;
        try {
            parcelFileDescriptor = ParcelFileDescriptor.open(new File(str), 268435456);
            D1.f.n("Zipping logs is completed");
            D1.f.n("Zipped file size : " + parcelFileDescriptor.getStatSize());
        } catch (Exception e7) {
            D1.f.o(e7.getMessage());
            parcelFileDescriptor = null;
        }
        bundle.putParcelable("fileDescriptor", parcelFileDescriptor);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031 A[Catch:{ Exception | NullPointerException -> 0x0064 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037 A[Catch:{ Exception | NullPointerException -> 0x0064 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean B(android.content.Context r4, Yd.b r5, C0.j r6) {
        /*
            r3 = this;
            r0 = 0
            java.lang.Object r1 = r5.f3915c     // Catch:{ Exception | NullPointerException -> 0x0064 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception | NullPointerException -> 0x0064 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            r2 = 1
            if (r1 == 0) goto L_0x0013
            java.lang.String r1 = "Service ID has to be set"
            D1.f.L(r1)     // Catch:{ Exception | NullPointerException -> 0x0064 }
        L_0x0011:
            r1 = r0
            goto L_0x002f
        L_0x0013:
            java.lang.Object r1 = r5.b     // Catch:{ Exception | NullPointerException -> 0x0064 }
            android.content.Context r1 = (android.content.Context) r1     // Catch:{ Exception | NullPointerException -> 0x0064 }
            int r1 = ae.C0902a.a(r1)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            if (r1 != r2) goto L_0x0024
            java.lang.Object r1 = r5.f     // Catch:{ Exception | NullPointerException -> 0x0064 }
            Yd.a r1 = (Yd.a) r1     // Catch:{ Exception | NullPointerException -> 0x0064 }
            boolean r1 = r1.b     // Catch:{ Exception | NullPointerException -> 0x0064 }
            goto L_0x0026
        L_0x0024:
            boolean r1 = r5.f3914a     // Catch:{ Exception | NullPointerException -> 0x0064 }
        L_0x0026:
            if (r1 != 0) goto L_0x002e
            java.lang.String r1 = "You have to agree to terms and conditions"
            D1.f.L(r1)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            goto L_0x0011
        L_0x002e:
            r1 = r2
        L_0x002f:
            if (r1 != 0) goto L_0x0037
            java.lang.String r3 = "Invalid DiagMonConfiguration"
            D1.f.L(r3)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            return r0
        L_0x0037:
            java.lang.String r1 = r6.f102c     // Catch:{ Exception | NullPointerException -> 0x0064 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            if (r1 == 0) goto L_0x0046
            java.lang.String r1 = "No Result code - you have to set"
            D1.f.L(r1)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            r1 = r0
            goto L_0x0047
        L_0x0046:
            r1 = r2
        L_0x0047:
            if (r1 != 0) goto L_0x004f
            java.lang.String r3 = "Invalid EventBuilder"
            D1.f.L(r3)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            return r0
        L_0x004f:
            java.lang.String r1 = "Valid EventBuilder"
            D1.f.n(r1)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            r3.U()     // Catch:{ Exception | NullPointerException -> 0x0064 }
            android.content.Intent r3 = r3.N(r4, r5, r6)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            r4.sendBroadcast(r3)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            java.lang.String r3 = "Report your logs"
            D1.f.n(r3)     // Catch:{ Exception | NullPointerException -> 0x0064 }
            return r2
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: D0.f.B(android.content.Context, Yd.b, C0.j):boolean");
    }

    public boolean C(Context context, b bVar, j jVar, Bundle bundle) {
        if (bVar == null) {
            try {
                D1.f.L("No Configuration");
                D1.f.L("You have to set DiagMonConfiguration");
                return false;
            } catch (Exception | NullPointerException unused) {
                return false;
            }
        } else {
            Bundle M2 = M(bVar, jVar);
            if (M2 == null) {
                D1.f.L("No EventObject");
                return false;
            } else if (!L2.a.t(bundle)) {
                D1.f.L("Invalid SR object");
                return false;
            } else if (!L2.a.t(M2)) {
                D1.f.L("Invalid ER object");
                return false;
            } else {
                D1.f.A("Valid SR, ER object");
                D1.f.A("Report your logs");
                D1.f.A("networkMode : " + bundle.getBoolean("wifiOnly", true));
                M2.putBoolean("wifiOnly", bundle.getBoolean("wifiOnly", true));
                String A10 = A(context, jVar.b);
                y(M2, A10);
                C0902a.c(context.getContentResolver().call(C0902a.b, "event_report", "eventReport", M2));
                if (!A10.isEmpty()) {
                    R(A10);
                }
                return true;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: N2.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v49, resolved type: U2.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: N2.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: N2.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v48, resolved type: R2.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v67, resolved type: U2.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v55, resolved type: R2.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v57, resolved type: R2.l} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x02ed A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void D() {
        /*
            r20 = this;
            r0 = r20
            java.lang.Object r1 = r0.f106h
            N2.h r1 = (N2.h) r1
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.Object r1 = r0.e
            N2.t r1 = (N2.t) r1
            int r2 = r1.e
            if (r2 >= 0) goto L_0x0578
            java.lang.Object r2 = r1.f
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r2 = r2.size()
            N2.i[] r3 = new N2.i[r2]
            r4 = 0
            r5 = r4
        L_0x001d:
            if (r5 >= r2) goto L_0x0030
            java.lang.Object r6 = r1.f
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r6 = r6.get(r5)
            N2.g r6 = (N2.g) r6
            N2.i r6 = r6.b
            r3[r5] = r6
            int r5 = r5 + 1
            goto L_0x001d
        L_0x0030:
            int r2 = r1.e
            if (r2 >= 0) goto L_0x0035
            r2 = r4
        L_0x0035:
            java.lang.Object r5 = r1.f
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r5 = r5.size()
            int r6 = r1.e
            r7 = r4
        L_0x0040:
            if (r7 >= r5) goto L_0x00a2
            java.lang.Object r8 = r1.f
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            java.lang.Object r8 = r8.get(r7)
            N2.g r8 = (N2.g) r8
            r9 = r3[r7]
            N2.i r10 = r1.f(r8, r9)
            if (r10 != 0) goto L_0x009a
            N2.i r9 = r1.e(r8)
            B1.a r9 = r9.d
            java.util.BitSet r9 = r9.n(r8)
            N2.i r11 = r8.b
            boolean r11 = r11.e
            R2.l r8 = r8.d
            java.lang.Object[] r12 = r8.e
            int r12 = r12.length
            if (r11 == 0) goto L_0x007a
            boolean r13 = r9.get(r4)
            if (r13 != 0) goto L_0x007a
            java.lang.Object r13 = r8.d(r4)
            R2.k r13 = (R2.k) r13
            int r13 = r13.c()
            goto L_0x007b
        L_0x007a:
            r13 = r4
        L_0x007b:
            r14 = r4
        L_0x007c:
            if (r11 >= r12) goto L_0x0092
            boolean r15 = r9.get(r11)
            if (r15 != 0) goto L_0x008f
            java.lang.Object r15 = r8.d(r11)
            R2.k r15 = (R2.k) r15
            int r15 = r15.c()
            int r14 = r14 + r15
        L_0x008f:
            int r11 = r11 + 1
            goto L_0x007c
        L_0x0092:
            int r8 = java.lang.Math.max(r14, r13)
            if (r8 <= r6) goto L_0x009d
            r6 = r8
            goto L_0x009d
        L_0x009a:
            if (r9 != r10) goto L_0x009d
            goto L_0x009f
        L_0x009d:
            r3[r7] = r10
        L_0x009f:
            int r7 = r7 + 1
            goto L_0x0040
        L_0x00a2:
            if (r2 < r6) goto L_0x0546
            r1.e = r2
            if (r2 != 0) goto L_0x00d1
            java.lang.Object r2 = r1.f
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r2 = r2.size()
            r6 = r4
        L_0x00b1:
            if (r6 >= r2) goto L_0x01af
            java.lang.Object r7 = r1.f
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            java.lang.Object r7 = r7.get(r6)
            N2.g r7 = (N2.g) r7
            N2.i r8 = r7.b
            r9 = r3[r6]
            if (r8 == r9) goto L_0x00ce
            java.lang.Object r8 = r1.f
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            N2.g r7 = r7.g(r9)
            r8.set(r6, r7)
        L_0x00ce:
            int r6 = r6 + 1
            goto L_0x00b1
        L_0x00d1:
            java.lang.Object r2 = r1.f
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r2 = r2.size()
            java.util.ArrayList r6 = new java.util.ArrayList
            int r7 = r2 * 2
            r6.<init>(r7)
            r7 = r4
        L_0x00e1:
            if (r7 >= r2) goto L_0x01ad
            java.lang.Object r8 = r1.f
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            java.lang.Object r8 = r8.get(r7)
            N2.g r8 = (N2.g) r8
            N2.i r9 = r8.b
            R2.p r10 = r8.f427c
            R2.l r11 = r8.d
            r12 = r3[r7]
            if (r12 == 0) goto L_0x0101
            r17 = r2
            r18 = r3
            r19 = r7
            r2 = 0
            r3 = 0
            goto L_0x0191
        L_0x0101:
            N2.i r12 = r1.e(r8)
            B1.a r13 = r12.d
            java.util.BitSet r13 = r13.n(r8)
            boolean r14 = r13.get(r4)
            boolean r15 = r9.e
            if (r15 == 0) goto L_0x0116
            r13.set(r4)
        L_0x0116:
            java.lang.Object[] r5 = r11.e
            int r4 = r5.length
            int r17 = r13.cardinality()
            int r4 = r4 - r17
            if (r4 != 0) goto L_0x012b
            R2.l r4 = R2.l.f
            r17 = r2
            r18 = r3
            r19 = r7
            r3 = 0
            goto L_0x0158
        L_0x012b:
            r17 = r2
            R2.l r2 = new R2.l
            r2.<init>(r4)
            r18 = r3
            r19 = r7
            r3 = 0
            r4 = 0
        L_0x0138:
            int r7 = r5.length
            if (r4 >= r7) goto L_0x014d
            boolean r7 = r13.get(r4)
            if (r7 != 0) goto L_0x014a
            java.lang.Object r7 = r11.d(r4)
            r2.e(r3, r7)
            int r3 = r3 + 1
        L_0x014a:
            int r4 = r4 + 1
            goto L_0x0138
        L_0x014d:
            boolean r3 = r11.d
            if (r3 != 0) goto L_0x0156
            r3 = 0
            r2.d = r3
        L_0x0154:
            r4 = r2
            goto L_0x0158
        L_0x0156:
            r3 = 0
            goto L_0x0154
        L_0x0158:
            if (r15 == 0) goto L_0x015d
            r13.set(r3, r14)
        L_0x015d:
            java.lang.Object[] r2 = r4.e
            int r2 = r2.length
            if (r2 != 0) goto L_0x0164
            r2 = 0
            goto L_0x0169
        L_0x0164:
            N2.m r2 = new N2.m
            r2.<init>(r10, r4)
        L_0x0169:
            if (r15 == 0) goto L_0x0188
            boolean r4 = r13.get(r3)
            if (r4 != 0) goto L_0x0188
            java.lang.Object r4 = r11.d(r3)
            R2.k r4 = (R2.k) r4
            int r5 = r4.d
            if (r5 != 0) goto L_0x017d
            r5 = r4
            goto L_0x0183
        L_0x017d:
            T2.d r5 = r4.e
            R2.k r5 = R2.k.d(r3, r5)
        L_0x0183:
            N2.x r3 = N2.g.f(r10, r4, r5)
            goto L_0x0189
        L_0x0188:
            r3 = 0
        L_0x0189:
            R2.l r4 = r11.j(r15, r13)
            N2.g r8 = r8.i(r4)
        L_0x0191:
            if (r2 == 0) goto L_0x0196
            r6.add(r2)
        L_0x0196:
            if (r12 == r9) goto L_0x019c
            N2.g r8 = r8.g(r12)
        L_0x019c:
            r6.add(r8)
            if (r3 == 0) goto L_0x01a4
            r6.add(r3)
        L_0x01a4:
            int r7 = r19 + 1
            r2 = r17
            r3 = r18
            r4 = 0
            goto L_0x00e1
        L_0x01ad:
            r1.f = r6
        L_0x01af:
            java.lang.Object r2 = r1.f
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r2 = r2.size()
            r3 = 0
            r4 = 0
        L_0x01b9:
            if (r3 >= r2) goto L_0x01dc
            java.lang.Object r5 = r1.f
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r5 = r5.get(r3)
            N2.g r5 = (N2.g) r5
            if (r4 < 0) goto L_0x01d1
            r5.f426a = r4
            int r5 = r5.b()
            int r4 = r4 + r5
            int r3 = r3 + 1
            goto L_0x01b9
        L_0x01d1:
            r5.getClass()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "address < 0"
            r0.<init>(r1)
            throw r0
        L_0x01dc:
            java.lang.Object r2 = r1.f
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r2 = r2.size()
            r3 = 0
            r4 = 0
        L_0x01e6:
            r5 = 1
            if (r3 >= r2) goto L_0x0269
            java.lang.Object r6 = r1.f
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r6 = r6.get(r3)
            N2.g r6 = (N2.g) r6
            boolean r7 = r6 instanceof N2.y
            if (r7 != 0) goto L_0x01f8
            goto L_0x0255
        L_0x01f8:
            N2.i r7 = r6.b
            r8 = r6
            N2.y r8 = (N2.y) r8
            B1.a r9 = r7.d
            boolean r9 = r9.b(r8)
            if (r9 == 0) goto L_0x0206
            goto L_0x0255
        L_0x0206:
            int r4 = r7.b
            r9 = 40
            if (r4 != r9) goto L_0x0226
            N2.i r4 = r1.f(r6, r7)
            if (r4 == 0) goto L_0x021e
            java.lang.Object r7 = r1.f
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            N2.g r4 = r6.g(r4)
            r7.set(r3, r4)
            goto L_0x0254
        L_0x021e:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "method too long"
            r0.<init>(r1)
            throw r0
        L_0x0226:
            java.lang.Object r4 = r1.f     // Catch:{ IndexOutOfBoundsException -> 0x0260, ClassCastException -> 0x0257 }
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ IndexOutOfBoundsException -> 0x0260, ClassCastException -> 0x0257 }
            int r6 = r3 + 1
            java.lang.Object r4 = r4.get(r6)     // Catch:{ IndexOutOfBoundsException -> 0x0260, ClassCastException -> 0x0257 }
            N2.e r4 = (N2.e) r4     // Catch:{ IndexOutOfBoundsException -> 0x0260, ClassCastException -> 0x0257 }
            N2.y r7 = new N2.y
            N2.i r9 = N2.k.f450I
            R2.p r10 = r8.f427c
            R2.l r11 = R2.l.f
            N2.e r12 = r8.f
            r7.<init>(r9, r10, r11, r12)
            java.lang.Object r9 = r1.f
            java.util.ArrayList r9 = (java.util.ArrayList) r9
            r9.set(r3, r7)
            java.lang.Object r7 = r1.f
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            N2.y r4 = r8.n(r4)
            r7.add(r3, r4)
            int r2 = r2 + 1
            r3 = r6
        L_0x0254:
            r4 = r5
        L_0x0255:
            int r3 = r3 + r5
            goto L_0x01e6
        L_0x0257:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "unpaired TargetInsn"
            r0.<init>(r1)
            throw r0
        L_0x0260:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "unpaired TargetInsn (dangling)"
            r0.<init>(r1)
            throw r0
        L_0x0269:
            if (r4 != 0) goto L_0x01af
            java.lang.Object r2 = r1.f
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r3 = r1.e
            int r1 = r1.d
            int r3 = r3 + r1
            int r1 = r2.size()
            N2.h r4 = new N2.h
            r4.<init>(r1, r3)
            r3 = 0
        L_0x027e:
            if (r3 >= r1) goto L_0x028c
            java.lang.Object r6 = r2.get(r3)
            N2.g r6 = (N2.g) r6
            r4.e(r3, r6)
            int r3 = r3 + 1
            goto L_0x027e
        L_0x028c:
            r3 = 0
            r4.d = r3
            r0.f106h = r4
            int r1 = N2.u.f
            int r1 = N2.q.f
            java.lang.Object[] r1 = r4.e
            int r1 = r1.length
            N2.p r2 = new N2.p
            r2.<init>(r1)
            r3 = 0
        L_0x029e:
            java.util.ArrayList r6 = r2.f546a
            if (r3 >= r1) goto L_0x037f
            java.lang.Object r7 = r4.d(r3)
            N2.g r7 = (N2.g) r7
            boolean r8 = r7 instanceof N2.r
            if (r8 != 0) goto L_0x0379
            boolean r8 = r7 instanceof N2.s
            if (r8 == 0) goto L_0x02e9
            int r7 = r7.c()
            r8 = 0
            int r9 = r8.d
            R2.k r10 = N2.p.d(r8)
            r2.a(r7, r9)
            R2.m r8 = r2.f547c
            R2.k r8 = r8.d(r9)
            if (r8 != 0) goto L_0x02ca
            r10.getClass()
            goto L_0x02e4
        L_0x02ca:
            T2.d r11 = r10.e
            T2.c r11 = r11.getType()
            T2.d r12 = r8.e
            T2.c r12 = r12.getType()
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x02e4
            int r11 = r10.d
            int r12 = r8.d
            if (r11 != r12) goto L_0x02e4
            r11 = r5
            goto L_0x02e5
        L_0x02e4:
            r11 = 0
        L_0x02e5:
            T2.d r12 = r10.e
            if (r11 == 0) goto L_0x02ed
        L_0x02e9:
            r16 = 0
            goto L_0x0375
        L_0x02ed:
            R2.m r0 = r2.f547c
            R2.k[] r0 = r0.e
            int r1 = r0.length
            r4 = 0
        L_0x02f3:
            if (r4 >= r1) goto L_0x030e
            r3 = r0[r4]
            if (r3 != 0) goto L_0x02fa
            goto L_0x030b
        L_0x02fa:
            T2.c r11 = r12.getType()
            T2.d r13 = r3.e
            T2.c r13 = r13.getType()
            boolean r11 = r11.equals(r13)
            if (r11 == 0) goto L_0x030b
            goto L_0x030f
        L_0x030b:
            int r4 = r4 + 1
            goto L_0x02f3
        L_0x030e:
            r3 = 0
        L_0x030f:
            if (r3 == 0) goto L_0x0316
            N2.n r0 = N2.n.END_MOVED
            r2.c(r7, r0, r3)
        L_0x0316:
            int[] r0 = r2.d
            r0 = r0[r9]
            if (r8 != 0) goto L_0x036d
            if (r0 < 0) goto L_0x032d
            java.lang.Object r0 = r6.get(r0)
            N2.o r0 = (N2.o) r0
            r0.getClass()
            if (r7 == 0) goto L_0x032a
            goto L_0x032d
        L_0x032a:
            r16 = 0
            throw r16
        L_0x032d:
            r0 = 7
            r1 = 4
            if (r9 <= 0) goto L_0x034d
            R2.m r3 = r2.f547c
            int r4 = r9 + -1
            R2.k r3 = r3.d(r4)
            if (r3 == 0) goto L_0x034d
            T2.d r4 = r3.e
            T2.c r4 = r4.getType()
            int r4 = r4.e
            if (r4 == r1) goto L_0x0348
            if (r4 == r0) goto L_0x0348
            goto L_0x034d
        L_0x0348:
            N2.n r4 = N2.n.END_CLOBBERED_BY_NEXT
            r2.c(r7, r4, r3)
        L_0x034d:
            T2.c r3 = r12.getType()
            int r3 = r3.e
            if (r3 == r1) goto L_0x0357
            if (r3 != r0) goto L_0x0365
        L_0x0357:
            R2.m r0 = r2.f547c
            int r9 = r9 + r5
            R2.k r0 = r0.d(r9)
            if (r0 == 0) goto L_0x0365
            N2.n r1 = N2.n.END_CLOBBERED_BY_PREV
            r2.c(r7, r1, r0)
        L_0x0365:
            N2.n r0 = N2.n.START
            N2.p.b(r7, r0, r10)
            r16 = 0
            throw r16
        L_0x036d:
            r16 = 0
            N2.n r0 = N2.n.END_REPLACED
            N2.p.b(r7, r0, r8)
            throw r16
        L_0x0375:
            int r3 = r3 + 1
            goto L_0x029e
        L_0x0379:
            r16 = 0
            r7.c()
            throw r16
        L_0x037f:
            r1 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            r2.a(r1, r3)
            int r1 = r6.size()
            int r2 = r2.b
            int r2 = r1 - r2
            if (r2 != 0) goto L_0x0393
            int r1 = N2.q.f
            goto L_0x03c2
        L_0x0393:
            N2.o[] r3 = new N2.o[r2]
            if (r1 != r2) goto L_0x039b
            r6.toArray(r3)
            goto L_0x03ac
        L_0x039b:
            java.util.Iterator r1 = r6.iterator()
        L_0x039f:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x03ac
            java.lang.Object r4 = r1.next()
            N2.o r4 = (N2.o) r4
            goto L_0x039f
        L_0x03ac:
            java.util.Arrays.sort(r3)
            N2.q r1 = new N2.q
            r1.<init>(r2)
            r4 = 0
        L_0x03b5:
            if (r4 >= r2) goto L_0x03bf
            r5 = r3[r4]
            r1.e(r4, r5)
            int r4 = r4 + 1
            goto L_0x03b5
        L_0x03bf:
            r4 = 0
            r1.d = r4
        L_0x03c2:
            java.lang.Object r1 = r0.f
            A0.l r1 = (A0.l) r1
            java.lang.Object r2 = r1.e
            A0.l r2 = (A0.l) r2
            java.lang.Object r3 = r1.f
            int[] r3 = (int[]) r3
            java.lang.Object r1 = r1.g
            A0.l r1 = (A0.l) r1
            int r4 = r3.length
            java.lang.Object r2 = r2.e
            R2.b r2 = (R2.b) r2
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r4)
            N2.b r6 = N2.b.f
            r7 = r6
            r6 = 0
            r8 = 0
            r9 = 0
        L_0x03e2:
            if (r6 >= r4) goto L_0x04f8
            r10 = r3[r6]
            R2.a r10 = r2.h(r10)
            R2.g r11 = r10.b
            R2.f r12 = r11.g()
            R2.n r12 = r12.d
            T2.e r12 = r12.d
            U2.d r12 = (U2.d) r12
            java.lang.Object[] r12 = r12.e
            int r12 = r12.length
            if (r12 == 0) goto L_0x04e8
            U2.f r12 = r10.f646c
            int r13 = r12.f
            int r14 = r10.d
            R2.f r11 = r11.g()
            T2.e r11 = r11.d()
            r15 = r11
            U2.d r15 = (U2.d) r15
            java.lang.Object[] r15 = r15.e
            int r15 = r15.length
            if (r15 != 0) goto L_0x041b
            N2.b r11 = N2.b.f
            r17 = r2
            r18 = r3
            r19 = r4
            goto L_0x0488
        L_0x041b:
            r17 = r2
            r2 = -1
            if (r14 != r2) goto L_0x0422
            if (r13 != r15) goto L_0x042f
        L_0x0422:
            if (r14 == r2) goto L_0x0437
            int r2 = r15 + 1
            if (r13 != r2) goto L_0x042f
            int r2 = r12.e(r15)
            if (r14 != r2) goto L_0x042f
            goto L_0x0437
        L_0x042f:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "shouldn't happen: weird successors list"
            r0.<init>(r1)
            throw r0
        L_0x0437:
            r2 = 0
        L_0x0438:
            if (r2 >= r15) goto L_0x044c
            T2.c r13 = r11.getType(r2)
            T2.c r14 = T2.c.v
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0449
            int r15 = r2 + 1
            goto L_0x044c
        L_0x0449:
            int r2 = r2 + 1
            goto L_0x0438
        L_0x044c:
            N2.b r2 = new N2.b
            r2.<init>(r15)
            r13 = 0
        L_0x0452:
            if (r13 >= r15) goto L_0x0480
            S2.u r14 = new S2.u
            r18 = r3
            T2.c r3 = r11.getType(r13)
            r14.<init>(r3)
            int r3 = r12.e(r13)
            r19 = r3
            java.lang.Object r3 = r1.e
            N2.e[] r3 = (N2.e[]) r3
            r3 = r3[r19]
            int r3 = r3.c()
            r19 = r4
            N2.a r4 = new N2.a
            r4.<init>(r14, r3)
            r2.e(r13, r4)
            int r13 = r13 + 1
            r3 = r18
            r4 = r19
            goto L_0x0452
        L_0x0480:
            r18 = r3
            r19 = r4
            r3 = 0
            r2.d = r3
            r11 = r2
        L_0x0488:
            java.lang.Object[] r2 = r7.e
            int r2 = r2.length
            if (r2 != 0) goto L_0x048e
            goto L_0x04e4
        L_0x048e:
            boolean r2 = r7.equals(r11)
            if (r2 == 0) goto L_0x04bf
            if (r8 == 0) goto L_0x04b6
            java.lang.Object r2 = r1.f
            N2.e[] r2 = (N2.e[]) r2
            int r3 = r8.f645a
            r2 = r2[r3]
            int r2 = r2.c()
            java.lang.Object r3 = r1.g
            N2.e[] r3 = (N2.e[]) r3
            int r4 = r10.f645a
            r3 = r3[r4]
            int r3 = r3.c()
            int r3 = r3 - r2
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r3 > r2) goto L_0x04bf
            r9 = r10
            goto L_0x04ee
        L_0x04b6:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "start == null"
            r0.<init>(r1)
            throw r0
        L_0x04bf:
            java.lang.Object[] r2 = r7.e
            int r2 = r2.length
            if (r2 == 0) goto L_0x04e4
            java.lang.Object r2 = r1.f
            N2.e[] r2 = (N2.e[]) r2
            int r3 = r8.f645a
            r2 = r2[r3]
            java.lang.Object r3 = r1.g
            N2.e[] r3 = (N2.e[]) r3
            int r4 = r9.f645a
            r3 = r3[r4]
            N2.c r4 = new N2.c
            int r2 = r2.c()
            int r3 = r3.c()
            r4.<init>(r2, r3, r7)
            r5.add(r4)
        L_0x04e4:
            r8 = r10
            r9 = r8
            r7 = r11
            goto L_0x04ee
        L_0x04e8:
            r17 = r2
            r18 = r3
            r19 = r4
        L_0x04ee:
            int r6 = r6 + 1
            r2 = r17
            r3 = r18
            r4 = r19
            goto L_0x03e2
        L_0x04f8:
            java.lang.Object[] r2 = r7.e
            int r2 = r2.length
            if (r2 == 0) goto L_0x051d
            java.lang.Object r2 = r1.f
            N2.e[] r2 = (N2.e[]) r2
            int r3 = r8.f645a
            r2 = r2[r3]
            java.lang.Object r1 = r1.g
            N2.e[] r1 = (N2.e[]) r1
            int r3 = r9.f645a
            r1 = r1[r3]
            N2.c r3 = new N2.c
            int r2 = r2.c()
            int r1 = r1.c()
            r3.<init>(r2, r1, r7)
            r5.add(r3)
        L_0x051d:
            int r1 = r5.size()
            if (r1 != 0) goto L_0x0526
            N2.d r1 = N2.d.f
            goto L_0x053e
        L_0x0526:
            N2.d r2 = new N2.d
            r2.<init>(r1)
            r3 = 0
        L_0x052c:
            if (r3 >= r1) goto L_0x053a
            java.lang.Object r4 = r5.get(r3)
            N2.c r4 = (N2.c) r4
            r2.e(r3, r4)
            int r3 = r3 + 1
            goto L_0x052c
        L_0x053a:
            r3 = 0
            r2.d = r3
            r1 = r2
        L_0x053e:
            r0.g = r1
            r8 = 0
            r0.e = r8
            r0.f = r8
            return
        L_0x0546:
            r18 = r3
            r3 = r4
            int r2 = r6 - r2
            java.lang.Object r4 = r1.f
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            int r4 = r4.size()
            r5 = r3
        L_0x0554:
            if (r5 >= r4) goto L_0x0572
            java.lang.Object r7 = r1.f
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            java.lang.Object r7 = r7.get(r5)
            N2.g r7 = (N2.g) r7
            boolean r8 = r7 instanceof N2.e
            if (r8 != 0) goto L_0x056f
            java.lang.Object r8 = r1.f
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            N2.g r7 = r7.h(r2)
            r8.set(r5, r7)
        L_0x056f:
            int r5 = r5 + 1
            goto L_0x0554
        L_0x0572:
            r4 = r3
            r2 = r6
            r3 = r18
            goto L_0x0035
        L_0x0578:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "already processed"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: D0.f.D():void");
    }

    public C0816f E(C1235b bVar, List list) {
        kotlin.jvm.internal.j.e(bVar, "classId");
        return (C0816f) ((Gf.e) this.f106h).invoke(new F(bVar, list));
    }

    public k G() {
        int i2 = ((R2.a) this.f).d;
        if (i2 < 0) {
            return null;
        }
        R2.f fVar = (R2.f) ((R2.b) ((w) this.f106h).f549a.e).h(i2).b.d(0);
        if (fVar.d.f652a != 56) {
            return null;
        }
        return fVar.f;
    }

    public boolean H(z2.k kVar) {
        v vVar = (v) this.g;
        if (vVar == null || kVar == null || vVar.f2233a.get() != kVar) {
            return false;
        }
        return true;
    }

    public void I(C1240g gVar, C1326f fVar) {
        ((C1105e) this.e).I(gVar, fVar);
    }

    public boolean J(U u) {
        boolean z;
        if (((U) this.f).equals(u)) {
            return true;
        }
        f fVar = (f) this.e;
        if (fVar != null) {
            z = fVar.J(u);
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public void K(C1326f fVar) {
        ((ArrayList) this.e).add(new C1327g(new C1339s(fVar)));
    }

    public void L(C1235b bVar, C1240g gVar) {
        ((ArrayList) this.e).add(new C1329i(bVar, gVar));
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0062 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00c8 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0081 A[Catch:{ Exception -> 0x00e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle M(Yd.b r5, C0.j r6) {
        /*
            r4 = this;
            java.lang.String r0 = ""
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r2 = "serviceId"
            java.lang.Object r3 = r5.f3915c     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x00e3 }
            r1.putString(r2, r3)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r2 = "serviceVersion"
            java.lang.Object r3 = r5.d     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x00e3 }
            r1.putString(r2, r3)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r2 = "serviceDefinedKey"
            r1.putString(r2, r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r2 = "errorCode"
            java.lang.String r6 = r6.f102c     // Catch:{ Exception -> 0x00e3 }
            r1.putString(r2, r6)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r6 = "errorDesc"
            r1.putString(r6, r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r6 = "relayClientVersion"
            r1.putString(r6, r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r6 = "relayClientType"
            r1.putString(r6, r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r6 = "extension"
            r1.putString(r6, r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r6 = "deviceId"
            r1.putString(r6, r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r6 = "serviceAgreeType"
            java.lang.Object r2 = r5.b     // Catch:{ Exception -> 0x00e3 }
            android.content.Context r2 = (android.content.Context) r2     // Catch:{ Exception -> 0x00e3 }
            int r2 = ae.C0902a.a(r2)     // Catch:{ Exception -> 0x00e3 }
            r3 = 1
            if (r2 != r3) goto L_0x0052
            java.lang.Object r5 = r5.f     // Catch:{ Exception -> 0x00e3 }
            Yd.a r5 = (Yd.a) r5     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r5 = r5.f3913a     // Catch:{ Exception -> 0x00e3 }
            goto L_0x0056
        L_0x0052:
            java.lang.Object r5 = r5.e     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x00e3 }
        L_0x0056:
            r1.putString(r6, r5)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r5 = "sdkVersion"
            r6 = 605082(0x93b9a, float:8.479E-40)
            java.lang.String r0 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0062 }
        L_0x0062:
            r1.putString(r5, r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r5 = "FOREGROUND"
            java.lang.Object r4 = r4.e     // Catch:{ Exception -> 0x00e3 }
            android.content.Context r4 = (android.content.Context) r4     // Catch:{ Exception -> 0x00e3 }
            java.lang.Class<android.app.ActivityManager> r6 = android.app.ActivityManager.class
            java.lang.Object r6 = r4.getSystemService(r6)     // Catch:{ Exception -> 0x00e3 }
            android.app.ActivityManager r6 = (android.app.ActivityManager) r6     // Catch:{ Exception -> 0x00e3 }
            java.util.List r6 = r6.getRunningAppProcesses()     // Catch:{ Exception -> 0x00e3 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x00e3 }
        L_0x007b:
            boolean r0 = r6.hasNext()     // Catch:{ Exception -> 0x00e3 }
            if (r0 == 0) goto L_0x009c
            java.lang.Object r0 = r6.next()     // Catch:{ Exception -> 0x00e3 }
            android.app.ActivityManager$RunningAppProcessInfo r0 = (android.app.ActivityManager.RunningAppProcessInfo) r0     // Catch:{ Exception -> 0x00e3 }
            int r2 = r0.importance     // Catch:{ Exception -> 0x00e3 }
            r3 = 100
            if (r2 != r3) goto L_0x007b
            java.lang.String r0 = r0.processName     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r2 = r4.getPackageName()     // Catch:{ Exception -> 0x00e3 }
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x00e3 }
            if (r0 == 0) goto L_0x007b
            java.lang.String r4 = "Yes"
            goto L_0x009e
        L_0x009c:
            java.lang.String r4 = "No"
        L_0x009e:
            r1.putString(r5, r4)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r4 = "sdkType"
            java.lang.String r5 = "S"
            r1.putString(r4, r5)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r4 = "memory"
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x00e3 }
            r5.<init>()     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r6 = "VM"
            org.json.JSONObject r0 = C0.j.c()     // Catch:{ JSONException -> 0x00c8 }
            r5.put(r6, r0)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r6 = "NATIVE"
            org.json.JSONObject r0 = C0.j.b()     // Catch:{ JSONException -> 0x00c8 }
            r5.put(r6, r0)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r6 = r5.toString()     // Catch:{ JSONException -> 0x00c8 }
            D1.f.n(r6)     // Catch:{ JSONException -> 0x00c8 }
        L_0x00c8:
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00e3 }
            r1.putString(r4, r5)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r4 = "storage"
            org.json.JSONObject r5 = C0.j.a()     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00e3 }
            r1.putString(r4, r5)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r4 = "Generated EventObject"
            D1.f.n(r4)     // Catch:{ Exception -> 0x00e3 }
            goto L_0x00e4
        L_0x00e3:
            r1 = 0
        L_0x00e4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: D0.f.M(Yd.b, C0.j):android.os.Bundle");
    }

    public Intent N(Context context, b bVar, j jVar) {
        Intent intent;
        String str;
        JSONObject jSONObject = new JSONObject();
        if (context.getApplicationInfo().uid == 1000) {
            intent = new Intent("com.sec.android.diagmonagent.intent.REPORT_ERROR_V2");
        } else {
            intent = new Intent("com.sec.android.diagmonagent.intent.REPORT_ERROR_APP");
        }
        Bundle bundle = new Bundle();
        intent.addFlags(32);
        bundle.putBundle("DiagMon", new Bundle());
        bundle.getBundle("DiagMon").putBundle("CFailLogUpload", new Bundle());
        bundle.getBundle("DiagMon").getBundle("CFailLogUpload").putString("ServiceID", (String) bVar.f3915c);
        bundle.getBundle("DiagMon").getBundle("CFailLogUpload").putBundle("Ext", new Bundle());
        bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("ClientV", C0068a.G(context));
        if (!TextUtils.isEmpty("")) {
            bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("RelayClient", "");
        }
        if (!TextUtils.isEmpty("")) {
            bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("RelayClientV", "");
        }
        bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("UiMode", "0");
        bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("ResultCode", jVar.f102c);
        if (!TextUtils.isEmpty("")) {
            bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("EventID", "");
        }
        try {
            jSONObject.put("SasdkV", C0731b.b);
            String str2 = C0902a.f3987a;
            try {
                str = String.valueOf(C0731b.f3330a);
            } catch (Exception unused) {
                str = "";
            }
            jSONObject.put("SdkV", str);
            jSONObject.put("TrackingID", "");
            jSONObject.put("Description", "");
        } catch (JSONException e7) {
            D1.f.o(e7.getMessage());
        }
        bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("Description", jSONObject.toString());
        if (((Bundle) this.g).getBoolean("wifiOnly", true)) {
            bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("WifiOnlyFeature", "1");
        } else {
            bundle.getBundle("DiagMon").getBundle("CFailLogUpload").getBundle("Ext").putString("WifiOnlyFeature", "0");
        }
        intent.putExtra("uploadMO", bundle);
        intent.setFlags(32);
        D1.f.A("EventObject is generated");
        return intent;
    }

    public o O(C1235b bVar) {
        ArrayList arrayList = new ArrayList();
        return new f(((C0744a) this.f).s(bVar, Q.f3662a, arrayList), this, arrayList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r1 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        ((ge.F0) r2.f106h).E.d(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void P(ee.a0 r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.e
            monitor-enter(r0)
            java.lang.Object r1 = r2.g     // Catch:{ all -> 0x000b }
            ee.a0 r1 = (ee.a0) r1     // Catch:{ all -> 0x000b }
            if (r1 == 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            return
        L_0x000b:
            r2 = move-exception
            goto L_0x0024
        L_0x000d:
            r2.g = r3     // Catch:{ all -> 0x000b }
            java.lang.Object r1 = r2.f     // Catch:{ all -> 0x000b }
            java.util.HashSet r1 = (java.util.HashSet) r1     // Catch:{ all -> 0x000b }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x000b }
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            if (r1 == 0) goto L_0x0023
            java.lang.Object r2 = r2.f106h
            ge.F0 r2 = (ge.F0) r2
            ge.G r2 = r2.E
            r2.d(r3)
        L_0x0023:
            return
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: D0.f.P(ee.a0):void");
    }

    public void Q(z2.k kVar) {
        synchronized (this.e) {
            try {
                if (H(kVar)) {
                    v vVar = (v) this.g;
                    if (!vVar.f2234c) {
                        vVar.f2234c = true;
                        ((Handler) this.f).removeCallbacksAndMessages(vVar);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void S(z2.k kVar) {
        synchronized (this.e) {
            try {
                if (H(kVar)) {
                    v vVar = (v) this.g;
                    if (vVar.f2234c) {
                        vVar.f2234c = false;
                        T(vVar);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void T(v vVar) {
        Handler handler = (Handler) this.f;
        int i2 = vVar.b;
        if (i2 != -2) {
            if (i2 <= 0) {
                if (i2 == -1) {
                    i2 = 1500;
                } else {
                    i2 = 2750;
                }
            }
            handler.removeCallbacksAndMessages(vVar);
            handler.sendMessageDelayed(Message.obtain(handler, 0, vVar), (long) i2);
        }
    }

    public void U() {
        try {
            String str = C0902a.f3987a;
            String concat = "com.sec.android.log.".concat((String) ((b) this.f).f3915c);
            ContentResolver contentResolver = ((Context) this.e).getContentResolver();
            contentResolver.call(Uri.parse("content://" + concat), "update_path", ((j) this.f106h).b, (Bundle) null);
        } catch (Exception e7) {
            D1.f.L("fail to send log path: " + e7.getMessage());
        }
    }

    public void V() {
        v vVar = (v) this.f106h;
        if (vVar != null) {
            this.g = vVar;
            this.f106h = null;
            z2.k kVar = (z2.k) vVar.f2233a.get();
            if (kVar != null) {
                Handler handler = z2.q.f2217C;
                handler.sendMessage(handler.obtainMessage(0, kVar.f2210a));
                return;
            }
            this.g = null;
        }
    }

    public C1105e W(int i2, C1235b bVar, Ve.a aVar) {
        s sVar = new s(((s) this.e).f4652a + '@' + i2);
        W0 w02 = (W0) this.f106h;
        HashMap hashMap = (HashMap) w02.f;
        List list = (List) hashMap.get(sVar);
        if (list == null) {
            list = new ArrayList();
            hashMap.put(sVar, list);
        }
        return ((C0744a) w02.e).t(bVar, aVar, list);
    }

    public o a(C1235b bVar, Ve.a aVar) {
        return ((C0744a) ((W0) this.g).e).t(bVar, aVar, (ArrayList) this.f);
    }

    public void c(h hVar) {
        R2.p pVar = hVar.e;
        S2.a aVar = hVar.f647h;
        k kVar = hVar.f;
        i a7 = N2.v.a(hVar);
        n nVar = hVar.d;
        int i2 = nVar.f652a;
        if (nVar.e != 1) {
            throw new RuntimeException("shouldn't happen");
        } else if (i2 == 3) {
            w wVar = (w) this.f106h;
            if (!wVar.f551h) {
                m(new l(a7, pVar, R2.l.i(kVar, k.d((wVar.e - wVar.g) + ((S2.j) aVar).d, kVar.e.getType()))));
            }
        } else {
            m(new N2.f(a7, pVar, w.a(hVar, kVar), aVar));
        }
    }

    public void d(R2.q qVar) {
        boolean z;
        g gVar;
        R2.p pVar = qVar.e;
        i a7 = N2.v.a(qVar);
        n nVar = qVar.d;
        S2.a aVar = qVar.f647h;
        int i2 = nVar.e;
        int i7 = nVar.f652a;
        if (i2 == 6) {
            m((N2.e) this.g);
            if (nVar.f) {
                m(new N2.f(a7, pVar, qVar.g, aVar));
                return;
            }
            k G5 = G();
            R2.l a10 = w.a(qVar, G5);
            boolean z3 = true;
            if (a7.e || i7 == 43) {
                z = true;
            } else {
                z = false;
            }
            if (G5 == null) {
                z3 = false;
            }
            if (z == z3) {
                if (i7 != 41 || a7.f428a == 35) {
                    gVar = new N2.f(a7, pVar, a10, aVar);
                } else {
                    gVar = new l(a7, pVar, a10);
                }
                m(gVar);
                return;
            }
            throw new RuntimeException("Insn with result/move-result-pseudo mismatch " + qVar);
        }
        throw new RuntimeException("shouldn't happen");
    }

    public void e0(Object obj) {
        ((ArrayList) this.e).add(C0744a.b((C0744a) this.f, (C1240g) this.g, obj));
    }

    public void f(int i2) {
        synchronized (((fe.i) this.e)) {
            fe.i iVar = (fe.i) this.e;
            iVar.f4356p += i2;
            iVar.c();
        }
    }

    public void i(R2.r rVar) {
        boolean z;
        R2.p pVar = rVar.e;
        i a7 = N2.v.a(rVar);
        if (rVar.d.e == 6) {
            k G5 = G();
            boolean z3 = a7.e;
            if (G5 != null) {
                z = true;
            } else {
                z = false;
            }
            if (z3 == z) {
                m((N2.e) this.g);
                m(new l(a7, pVar, w.a(rVar, G5)));
                return;
            }
            throw new RuntimeException("Insn with result/move-result-pseudo mismatch" + rVar);
        }
        throw new RuntimeException("shouldn't happen");
    }

    public boolean isReady() {
        return ((fe.q) this.f).f4363a.k();
    }

    public void k(C1056t tVar) {
        synchronized (((fe.i) this.e)) {
            fe.i iVar = (fe.i) this.e;
            fe.q qVar = (fe.q) this.f;
            iVar.d = qVar;
            iVar.e = qVar.f4364c;
            iVar.f = tVar;
            if (iVar.n != fe.j.CLOSED) {
                iVar.h(fe.j.INITIALIZED);
            }
        }
        if (((fe.q) this.f).f4363a.k()) {
            tVar.b0();
        }
    }

    public void l(R2.i iVar) {
        g gVar;
        n nVar = iVar.d;
        k kVar = iVar.f;
        int i2 = nVar.f652a;
        if (i2 != 54 && i2 != 56) {
            R2.p pVar = iVar.e;
            i a7 = N2.v.a(iVar);
            int i7 = nVar.e;
            if (!(i7 == 1 || i7 == 2)) {
                if (i7 == 3) {
                    return;
                }
                if (i7 == 4) {
                    gVar = new y(a7, pVar, w.a(iVar, kVar), ((N2.e[]) ((w) this.f106h).b.e)[((R2.a) this.f).f646c.e(1)]);
                    m(gVar);
                } else if (i7 != 6) {
                    throw new RuntimeException("shouldn't happen");
                }
            }
            gVar = new l(a7, pVar, w.a(iVar, kVar));
            m(gVar);
        }
    }

    public void m(g gVar) {
        ((t) ((e) this.e).e).a(gVar);
    }

    public void n() {
        switch (this.d) {
            case 14:
                ArrayList arrayList = (ArrayList) this.f;
                if (!arrayList.isEmpty()) {
                    ((HashMap) ((W0) this.g).f).put((s) this.e, arrayList);
                    return;
                }
                return;
            case 15:
                ((C1105e) this.f).n();
                ((ArrayList) ((f) this.g).e).add(new C1321a((Re.b) C1194l.b1((ArrayList) this.f106h)));
                return;
            default:
                C1105e eVar = (C1105e) this.f106h;
                C1240g gVar = (C1240g) this.g;
                ArrayList arrayList2 = (ArrayList) this.e;
                eVar.getClass();
                kotlin.jvm.internal.j.e(arrayList2, "elements");
                if (gVar != null) {
                    Te.Q x9 = c.x(gVar, eVar.g);
                    if (x9 != null) {
                        HashMap hashMap = eVar.e;
                        List d2 = Qf.k.d(arrayList2);
                        C0774x type = x9.getType();
                        kotlin.jvm.internal.j.d(type, "getType(...)");
                        hashMap.put(gVar, new C1346z(d2, type));
                        return;
                    } else if (eVar.f.k(eVar.f4639h) && kotlin.jvm.internal.j.a(gVar.b(), "value")) {
                        ArrayList arrayList3 = new ArrayList();
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            Object next = it.next();
                            if (next instanceof C1321a) {
                                arrayList3.add(next);
                            }
                        }
                        Collection collection = eVar.f4640i;
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            collection.add((Re.b) ((C1321a) it2.next()).f5158a);
                        }
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
        }
    }

    public p o(C1240g gVar) {
        return ((C1105e) this.e).o(gVar);
    }

    public int onFinish() {
        return 0;
    }

    public o p(C1235b bVar, C1240g gVar) {
        return ((C1105e) this.e).p(bVar, gVar);
    }

    public C0741g q(C1235b bVar) {
        kotlin.jvm.internal.j.e(bVar, "classId");
        C1157j jVar = (C1157j) ((LinkedHashMap) this.f106h).get(bVar);
        if (jVar == null) {
            return null;
        }
        ((Df.F) this.g).invoke(bVar);
        return new C0741g((W0) this.e, jVar, (C1178a) this.f, Q.f3662a);
    }

    public void r(C1240g gVar, C1235b bVar, C1240g gVar2) {
        ((C1105e) this.e).r(gVar, bVar, gVar2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0068 A[Catch:{ Exception -> 0x004b }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[Catch:{ Exception -> 0x004b }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r6 = this;
            java.lang.String r0 = "failed to customEventReport"
            java.lang.Object r1 = r6.f
            Yd.b r1 = (Yd.b) r1
            java.lang.Object r2 = r6.e
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Object r3 = r6.f106h
            C0.j r3 = (C0.j) r3
            boolean r4 = ae.C0902a.b()     // Catch:{ Exception -> 0x004b }
            if (r4 == 0) goto L_0x0015
            goto L_0x006b
        L_0x0015:
            java.lang.String r4 = r3.b     // Catch:{ Exception -> 0x004b }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x004b }
            if (r5 == 0) goto L_0x001e
            goto L_0x006c
        L_0x001e:
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x004b }
            r5.<init>(r4)     // Catch:{ Exception -> 0x004b }
            boolean r4 = r5.isDirectory()     // Catch:{ Exception -> 0x004b }
            if (r4 != 0) goto L_0x002a
            goto L_0x006c
        L_0x002a:
            java.io.File[] r4 = r5.listFiles()     // Catch:{ Exception -> 0x004b }
            if (r4 != 0) goto L_0x0031
            goto L_0x006c
        L_0x0031:
            int r4 = r4.length     // Catch:{ Exception -> 0x004b }
            r5 = 1
            if (r4 < r5) goto L_0x006c
            int r4 = ae.C0902a.a(r2)     // Catch:{ Exception -> 0x004b }
            if (r4 == 0) goto L_0x0060
            if (r4 == r5) goto L_0x0056
            r5 = 2
            if (r4 == r5) goto L_0x004d
            java.lang.String r6 = "Exceptional case"
            D1.f.L(r6)     // Catch:{ Exception -> 0x004b }
            java.lang.String r6 = "customEventReport is aborted"
            D1.f.L(r6)     // Catch:{ Exception -> 0x004b }
            goto L_0x0065
        L_0x004b:
            r6 = move-exception
            goto L_0x0072
        L_0x004d:
            java.lang.Object r4 = r6.g     // Catch:{ Exception -> 0x004b }
            android.os.Bundle r4 = (android.os.Bundle) r4     // Catch:{ Exception -> 0x004b }
            boolean r6 = r6.C(r2, r1, r3, r4)     // Catch:{ Exception -> 0x004b }
            goto L_0x0066
        L_0x0056:
            java.lang.String r4 = "LEGACY DMA"
            D1.f.n(r4)     // Catch:{ Exception -> 0x004b }
            boolean r6 = r6.B(r2, r1, r3)     // Catch:{ Exception -> 0x004b }
            goto L_0x0066
        L_0x0060:
            java.lang.String r6 = "not installed"
            D1.f.L(r6)     // Catch:{ Exception -> 0x004b }
        L_0x0065:
            r6 = 0
        L_0x0066:
            if (r6 != 0) goto L_0x006b
            D1.f.L(r0)     // Catch:{ Exception -> 0x004b }
        L_0x006b:
            return
        L_0x006c:
            java.lang.String r6 = "You have to properly set LogPath"
            D1.f.L(r6)     // Catch:{ Exception -> 0x004b }
            return
        L_0x0072:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            D1.f.L(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: D0.f.run():void");
    }

    public void s(C1240g gVar, Object obj) {
        ((C1105e) this.e).s(gVar, obj);
    }

    public void t() {
        try {
            synchronized (((fe.q) this.f)) {
                Deadline deadline = (Deadline) this.f106h;
                if (deadline != null) {
                    M m = ((fe.q) this.f).l;
                    G g3 = Z.f4490c;
                    m.a(g3);
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    m.c(g3, Long.valueOf(Math.max(0, deadline.b())));
                }
                fe.q qVar = (fe.q) this.f;
                qVar.e = true;
                C1076a aVar = (C1076a) this.g;
                if (aVar != null) {
                    qVar.e = true;
                    ConcurrentLinkedQueue concurrentLinkedQueue = qVar.g;
                    if (concurrentLinkedQueue != null) {
                        concurrentLinkedQueue.add(aVar);
                    } else if (qVar.f == null) {
                        qVar.f = aVar;
                    } else {
                        ConcurrentLinkedQueue concurrentLinkedQueue2 = new ConcurrentLinkedQueue();
                        qVar.g = concurrentLinkedQueue2;
                        concurrentLinkedQueue2.add(aVar);
                    }
                }
                qVar.f4365h = true;
                qVar.c();
            }
        } catch (b0 e7) {
            synchronized (((fe.i) this.e)) {
                a0 a0Var = e7.d;
                ((fe.i) this.e).b(a0Var, a0Var, false);
            }
        }
    }

    public String toString() {
        switch (this.d) {
            case 10:
                return "SingleMessageClientStream[" + ((fe.i) this.e) + "/" + ((fe.q) this.f) + "]";
            default:
                return super.toString();
        }
    }

    public void u(Deadline deadline) {
        this.f106h = deadline;
    }

    public void v(a0 a0Var) {
        synchronized (((fe.i) this.e)) {
            ((fe.i) this.e).b(a0.f, a0Var, false);
        }
    }

    public void x(C1076a aVar) {
        if (((C1076a) this.g) != null) {
            synchronized (((fe.i) this.e)) {
                a0 g3 = a0.n.g("too many messages");
                ((fe.i) this.e).b(g3, g3, false);
            }
            return;
        }
        this.g = aVar;
    }

    public boolean z(v vVar, int i2) {
        z2.k kVar = (z2.k) vVar.f2233a.get();
        if (kVar == null) {
            return false;
        }
        ((Handler) this.f).removeCallbacksAndMessages(vVar);
        Handler handler = z2.q.f2217C;
        handler.sendMessage(handler.obtainMessage(1, i2, 0, kVar.f2210a));
        return true;
    }

    public f(m mVar, C c5) {
        this.d = 7;
        kotlin.jvm.internal.j.e(c5, "module");
        this.e = mVar;
        this.f = c5;
        this.g = mVar.b(new E(this, 0));
        this.f106h = mVar.b(new E(this, 1));
    }

    public f(lf.E e7, W0 w02, C1178a aVar, Df.F f5) {
        this.d = 1;
        this.e = w02;
        this.f = aVar;
        this.g = f5;
        List list = e7.f4741j;
        kotlin.jvm.internal.j.d(list, "getClass_List(...)");
        Iterable iterable = list;
        int Z = z.Z(C1196n.w0(iterable, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z < 16 ? 16 : Z);
        for (Object next : iterable) {
            linkedHashMap.put(c.A((W0) this.e, ((C1157j) next).f4850h), next);
        }
        this.f106h = linkedHashMap;
    }

    public f(Application application, C0732c cVar, Nd.c cVar2) {
        this.d = 6;
        this.e = application;
        this.f = cVar;
        cVar.getClass();
        this.g = null;
        this.f106h = cVar2;
    }

    public void flush() {
    }

    public void j() {
    }

    public void b(C0981n nVar) {
    }

    public void e(G0.c cVar) {
    }

    public void g(int i2) {
    }

    public void h(int i2) {
    }

    public void w(C0973f fVar) {
    }

    public f(int i2) {
        Object obj;
        Object obj2;
        Object obj3;
        this.d = i2;
        switch (i2) {
            case 17:
                this.e = new Object();
                this.f = new Handler(Looper.getMainLooper(), new u(this));
                return;
            default:
                Q0 q0 = Q0.f;
                RuntimeException runtimeException = C1020e1.f4507h;
                if (runtimeException == null) {
                    obj = new C1020e1();
                } else {
                    obj = new G0.e(11);
                }
                this.f = obj;
                if (runtimeException == null) {
                    obj2 = new C1020e1();
                } else {
                    obj2 = new G0.e(11);
                }
                this.g = obj2;
                if (runtimeException == null) {
                    obj3 = new C1020e1();
                } else {
                    obj3 = new G0.e(11);
                }
                this.f106h = obj3;
                this.e = q0;
                return;
        }
    }

    public f(b bVar, Bundle bundle, j jVar) {
        this.d = 9;
        this.e = (Context) bVar.b;
        this.f = bVar;
        this.g = bundle;
        this.f106h = jVar;
    }

    public f(fe.i iVar, fe.q qVar) {
        this.d = 10;
        this.e = iVar;
        this.f = qVar;
    }

    public f(t tVar, A0.l lVar) {
        this.d = 4;
        if (tVar != null) {
            this.e = tVar;
            this.f = lVar;
            this.g = null;
            this.f106h = null;
            return;
        }
        throw new NullPointerException("unprocessedInsns == null");
    }

    public f(e eVar, e eVar2) {
        Object obj;
        this.d = 11;
        this.f106h = eVar;
        this.e = eVar2;
        String str = (String) eVar.f;
        C0967D a7 = ((ee.E) eVar.e).a(str);
        this.g = a7;
        if (a7 != null) {
            if (C1011b1.f4497a) {
                obj = new V0(eVar2);
            } else {
                obj = new C1008a1(eVar2);
            }
            this.f = obj;
            return;
        }
        throw new IllegalStateException(C0212a.m("Could not find policy '", str, "'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files."));
    }

    public f(W0 w02, s sVar) {
        this.d = 14;
        this.f106h = w02;
        this.d = 14;
        this.g = w02;
        this.e = sVar;
        this.f = new ArrayList();
    }

    public f(C0744a aVar, C1240g gVar, C1105e eVar) {
        this.d = 16;
        this.f = aVar;
        this.g = gVar;
        this.f106h = eVar;
        this.e = new ArrayList();
    }

    public f(C1105e eVar, f fVar, ArrayList arrayList) {
        this.d = 15;
        this.f = eVar;
        this.g = fVar;
        this.f106h = arrayList;
        this.e = eVar;
    }

    public f(Ff.k kVar) {
        this.d = 2;
        this.f106h = kVar;
        List list = kVar.f3386h.w;
        kotlin.jvm.internal.j.d(list, "getEnumEntryList(...)");
        Iterable iterable = list;
        int Z = z.Z(C1196n.w0(iterable, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z < 16 ? 16 : Z);
        for (Object next : iterable) {
            linkedHashMap.put(c.C((C1209f) kVar.f3389o.b, ((C1166t) next).g), next);
        }
        this.e = linkedHashMap;
        Ff.k kVar2 = (Ff.k) this.f106h;
        this.f = ((Df.l) kVar2.f3389o.f3358a).f3349a.c(new Ff.j(0, this, kVar2));
        m mVar = ((Df.l) ((Ff.k) this.f106h).f3389o.f3358a).f3349a;
        Af.g gVar = new Af.g(4, this);
        mVar.getClass();
        this.g = new Gf.h(mVar, gVar);
    }

    public f(w wVar, e eVar) {
        this.d = 5;
        this.f106h = wVar;
        this.e = eVar;
    }

    public f(F0 f02) {
        this.d = 13;
        this.f106h = f02;
        this.e = new Object();
        this.f = new HashSet();
    }
}
