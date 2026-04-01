package Id;

import Dd.C0731b;
import Dd.C0732c;
import Fd.C0744a;
import a.C0068a;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.samsung.context.sdk.samsunganalytics.internal.sender.a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.b;
import com.samsung.context.sdk.samsunganalytics.internal.sender.d;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import o1.C0246a;
import og.k;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends a {
    public final C0744a e;

    public c(Context context, C0732c cVar) {
        super(context, cVar);
        this.e = C0744a.j(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b1 A[LOOP:0: B:28:0x00b1->B:31:0x00c1, LOOP_START, PHI: r9 
      PHI: (r9v3 int) = (r9v2 int), (r9v7 int) binds: [B:25:0x00a4, B:31:0x00c1] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int c(java.util.Map r9) {
        /*
            r8 = this;
            java.lang.String r0 = "connectivity"
            android.content.Context r1 = r8.f4194a
            java.lang.Object r0 = r1.getSystemService(r0)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            r2 = -4
            if (r0 == 0) goto L_0x001d
            boolean r3 = r0.isConnected()
            if (r3 != 0) goto L_0x0018
            goto L_0x001d
        L_0x0018:
            int r0 = r0.getType()
            goto L_0x001e
        L_0x001d:
            r0 = r2
        L_0x001e:
            r3 = -6
            java.lang.String r4 = "DLS Sender"
            if (r0 != r2) goto L_0x0029
            java.lang.String r5 = "Network unavailable."
            a.C0068a.c(r4, r5)
            goto L_0x0037
        L_0x0029:
            boolean r2 = og.k.F(r1)
            if (r2 == 0) goto L_0x0036
            java.lang.String r2 = "policy expired. request policy"
            a.C0068a.c(r4, r2)
            r2 = r3
            goto L_0x0037
        L_0x0036:
            r2 = 0
        L_0x0037:
            Kd.a r4 = r8.f4195c
            if (r2 == 0) goto L_0x0071
            r8.b(r9)
            if (r2 != r3) goto L_0x0070
            t1.i r9 = r8.d
            Fd.a r0 = r8.e
            Dd.c r8 = r8.b
            r3 = 0
            og.k.V(r1, r8, r9, r0, r3)
            boolean r8 = r4.d
            if (r8 == 0) goto L_0x0070
            java.lang.Object r8 = r4.e
            D0.e r8 = (D0.e) r8
            long r0 = java.lang.System.currentTimeMillis()
            r9 = 5
            long r4 = (long) r9
            r6 = 86400000(0x5265c00, double:4.2687272E-316)
            long r4 = r4 * r6
            long r0 = r0 - r4
            java.lang.Object r8 = r8.e
            Ld.a r8 = (Ld.a) r8
            android.database.sqlite.SQLiteDatabase r8 = r8.getWritableDatabase()
            java.lang.String r9 = "timestamp <= "
            java.lang.String r9 = A.a.f(r9, r0)
            java.lang.String r0 = "logs_v2"
            r8.delete(r0, r9, r3)
        L_0x0070:
            return r2
        L_0x0071:
            Id.b r1 = new Id.b
            r1.<init>(r8, r0)
            com.samsung.context.sdk.samsunganalytics.internal.sender.d r2 = new com.samsung.context.sdk.samsunganalytics.internal.sender.d
            java.lang.String r3 = "ts"
            java.lang.Object r3 = r9.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            long r5 = java.lang.Long.parseLong(r3)
            r8.d(r9)
            Od.d r3 = Od.d.ONE_DEPTH
            java.lang.String r3 = o1.C0246a.f0(r9, r3)
            com.samsung.context.sdk.samsunganalytics.internal.sender.b r9 = com.samsung.context.sdk.samsunganalytics.internal.sender.a.a(r9)
            r2.<init>(r5, r3, r9)
            int r9 = r8.f(r0, r2, r1)
            r2 = -1
            if (r9 != r2) goto L_0x009c
            return r9
        L_0x009c:
            r3 = 200(0xc8, float:2.8E-43)
            java.util.concurrent.LinkedBlockingQueue r3 = r4.b(r3)
            boolean r4 = r4.d
            if (r4 == 0) goto L_0x00b1
            com.samsung.context.sdk.samsunganalytics.internal.sender.b r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.b.UIX
            r8.e(r0, r2, r3, r1)
            com.samsung.context.sdk.samsunganalytics.internal.sender.b r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.b.DEVICE
            r8.e(r0, r2, r3, r1)
            return r9
        L_0x00b1:
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x00c3
            java.lang.Object r9 = r3.poll()
            com.samsung.context.sdk.samsunganalytics.internal.sender.d r9 = (com.samsung.context.sdk.samsunganalytics.internal.sender.d) r9
            int r9 = r8.f(r0, r9, r1)
            if (r9 != r2) goto L_0x00b1
        L_0x00c3:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: Id.c.c(java.util.Map):int");
    }

    public final Map d(Map map) {
        C0744a aVar = this.e;
        map.put("la", (String) aVar.d);
        if (!TextUtils.isEmpty((String) aVar.f3375h)) {
            map.put(HeaderSetup.Key.MCC, (String) aVar.f3375h);
        }
        if (!TextUtils.isEmpty((String) aVar.f3376i)) {
            map.put(HeaderSetup.Key.MNC, (String) aVar.f3376i);
        }
        map.put("dm", (String) aVar.f);
        C0732c cVar = this.b;
        cVar.getClass();
        map.put("auid", (Object) null);
        map.put("do", (String) aVar.e);
        map.put("av", C0068a.G(this.f4194a));
        map.put("uv", cVar.f3332c);
        map.put("v", C0731b.b);
        map.put("at", String.valueOf(cVar.e));
        map.put("fv", (String) aVar.g);
        map.put("tid", cVar.f3331a);
        map.put("tz", String.valueOf(C0246a.Y()));
        return map;
    }

    public final void e(int i2, b bVar, LinkedBlockingQueue linkedBlockingQueue, b bVar2) {
        int i7;
        int i8;
        Kd.a aVar;
        ArrayList arrayList = new ArrayList();
        Iterator it = linkedBlockingQueue.iterator();
        while (it.hasNext()) {
            LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
            Context context = this.f4194a;
            SharedPreferences E = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context);
            int i10 = 0;
            if (i2 == 1) {
                i7 = E.getInt("dq-w", 0);
                i8 = E.getInt("wifi_used", 0);
            } else if (i2 == 0) {
                i7 = E.getInt("dq-3g", 0);
                i8 = E.getInt("data_used", 0);
            } else {
                i8 = 0;
                i7 = 0;
            }
            int min = Math.min(51200, i7 - i8);
            while (true) {
                boolean hasNext = it.hasNext();
                aVar = this.f4195c;
                if (!hasNext) {
                    break;
                }
                d dVar = (d) it.next();
                if (dVar.d == bVar) {
                    if (dVar.f4199c.getBytes().length + i10 > min) {
                        break;
                    }
                    i10 += dVar.f4199c.getBytes().length;
                    linkedBlockingQueue2.add(dVar);
                    it.remove();
                    arrayList.add(dVar.f4198a);
                    if (linkedBlockingQueue.isEmpty()) {
                        aVar.e(arrayList);
                        linkedBlockingQueue = aVar.b(200);
                        it = linkedBlockingQueue.iterator();
                    }
                }
            }
            if (!linkedBlockingQueue2.isEmpty()) {
                aVar.e(arrayList);
                k.W(context, i2, i10);
                a aVar2 = new a(bVar, linkedBlockingQueue2, this.b.f3331a, bVar2);
                this.d.getClass();
                i.d(aVar2);
                C0068a.c("DLSLogSender", "send packet : num(" + linkedBlockingQueue2.size() + ") size(" + i10 + ")");
            } else {
                return;
            }
        }
    }

    public final int f(int i2, d dVar, b bVar) {
        int i7;
        int i8;
        int i10;
        int i11;
        if (dVar == null) {
            return -100;
        }
        int length = dVar.f4199c.getBytes().length;
        Context context = this.f4194a;
        SharedPreferences E = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context);
        if (i2 == 1) {
            i8 = E.getInt("dq-w", 0);
            i7 = E.getInt("wifi_used", 0);
            i10 = E.getInt("oq-w", 0);
        } else if (i2 == 0) {
            i8 = E.getInt("dq-3g", 0);
            i7 = E.getInt("data_used", 0);
            i10 = E.getInt("oq-3g", 0);
        } else {
            i10 = 0;
            i8 = 0;
            i7 = 0;
        }
        StringBuilder h5 = A.a.h(i8, i7, "Quota : ", "/ Uploaded : ", "/ limit : ");
        h5.append(i10);
        h5.append("/ size : ");
        h5.append(length);
        C0068a.e(h5.toString());
        if (i8 < i7 + length) {
            StringBuilder h6 = A.a.h(i8, i7, "send result fail : Over daily quota (quota: ", "/ uploaded: ", "/ size: ");
            h6.append(length);
            h6.append(")");
            C0068a.c("DLS Sender", h6.toString());
            i11 = -1;
        } else if (i10 < length) {
            C0068a.c("DLS Sender", A.a.d(i10, length, "send result fail : Over once quota (limit: ", "/ size: ", ")"));
            i11 = -11;
        } else {
            i11 = 0;
        }
        if (i11 != 0) {
            return i11;
        }
        k.W(context, i2, length);
        a aVar = new a(dVar, this.b.f3331a, bVar);
        this.d.getClass();
        i.d(aVar);
        return 0;
    }
}
