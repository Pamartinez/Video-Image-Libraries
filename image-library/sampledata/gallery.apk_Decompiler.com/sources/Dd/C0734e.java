package Dd;

import D0.e;
import Ed.a;
import Ed.b;
import G0.c;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.samsung.context.sdk.samsunganalytics.internal.sender.d;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import o1.C0246a;

/* renamed from: Dd.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0734e implements a {
    public static C0734e e;
    public final b d;

    public C0734e(Application application, C0732c cVar) {
        this.d = null;
        if (application == null) {
            C0246a.m0("context cannot be null");
        } else if (cVar == null) {
            C0246a.m0("Configuration cannot be null");
        } else if (TextUtils.isEmpty(cVar.f3331a)) {
            C0246a.m0("TrackingId is empty, set TrackingId");
        } else if (TextUtils.isEmpty((CharSequence) null) && !cVar.b) {
            C0246a.m0("Device Id is empty, set Device Id or enable auto device id");
        } else if (!TextUtils.isEmpty((CharSequence) null)) {
            C0246a.m0("This mode is not allowed to set device Id");
        } else if (TextUtils.isEmpty(cVar.f3332c)) {
            C0246a.m0("you should set the UI version");
        } else {
            this.d = new b(application, cVar);
        }
    }

    public static C0734e a() {
        if (e == null) {
            C0246a.m0("call after setConfiguration() method");
            if (Build.TYPE.equals("user")) {
                synchronized (C0734e.class) {
                    try {
                        if (e == null) {
                            e = new C0734e((Application) null, (C0732c) null);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
        return e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0018 A[Catch:{ IllegalArgumentException -> 0x0043, all -> 0x0052 }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0019 A[Catch:{ IllegalArgumentException -> 0x0043, all -> 0x0052 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0058 A[Catch:{ IllegalArgumentException -> 0x0043, all -> 0x0052 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0060 A[Catch:{ IllegalArgumentException -> 0x0043, all -> 0x0052 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(android.app.Application r6, Dd.C0732c r7) {
        /*
            java.lang.String r0 = "SamsungAnalytics setConfiguration"
            android.os.Trace.beginSection(r0)
            java.lang.Class<Dd.e> r0 = Dd.C0734e.class
            monitor-enter(r0)
            Dd.e r1 = e     // Catch:{ all -> 0x0052 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0015
            Ed.b r1 = r1.d     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r1 = r3
            goto L_0x0016
        L_0x0015:
            r1 = r2
        L_0x0016:
            if (r1 == 0) goto L_0x0019
            goto L_0x0054
        L_0x0019:
            android.content.Context r1 = r6.getApplicationContext()     // Catch:{ all -> 0x0052 }
            Dd.e r4 = e     // Catch:{ all -> 0x0052 }
            Ed.b r4 = r4.d     // Catch:{ all -> 0x0052 }
            java.lang.Object r4 = r4.d     // Catch:{ all -> 0x0052 }
            Dd.c r4 = (Dd.C0732c) r4     // Catch:{ all -> 0x0052 }
            boolean r1 = o1.C0246a.b0(r1)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x002c
            goto L_0x0054
        L_0x002c:
            if (r4 != 0) goto L_0x0054
            Dd.e r1 = e     // Catch:{ all -> 0x0052 }
            Ed.b r1 = r1.d     // Catch:{ all -> 0x0052 }
            java.lang.Object r1 = r1.e     // Catch:{ all -> 0x0052 }
            android.content.Context r1 = (android.content.Context) r1     // Catch:{ all -> 0x0052 }
            r4 = 0
            if (r1 == 0) goto L_0x004a
            boolean r5 = o1.C0246a.f     // Catch:{ all -> 0x0052 }
            if (r5 == 0) goto L_0x004a
            Od.c r5 = o1.C0246a.e     // Catch:{ IllegalArgumentException -> 0x0043 }
            r1.unregisterReceiver(r5)     // Catch:{ IllegalArgumentException -> 0x0043 }
            goto L_0x0048
        L_0x0043:
            java.lang.String r1 = "unregisterReceiver failed"
            a.C0068a.K(r1)     // Catch:{ all -> 0x0052 }
        L_0x0048:
            o1.C0246a.f = r3     // Catch:{ all -> 0x0052 }
        L_0x004a:
            r1 = -1
            og.k.f = r1     // Catch:{ all -> 0x0052 }
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.b = r4     // Catch:{ all -> 0x0052 }
            e = r4     // Catch:{ all -> 0x0052 }
            goto L_0x0054
        L_0x0052:
            r6 = move-exception
            goto L_0x006c
        L_0x0054:
            Dd.e r1 = e     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x005e
            Ed.b r1 = r1.d     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x005d
            goto L_0x005e
        L_0x005d:
            r2 = r3
        L_0x005e:
            if (r2 == 0) goto L_0x0067
            Dd.e r1 = new Dd.e     // Catch:{ all -> 0x0052 }
            r1.<init>(r6, r7)     // Catch:{ all -> 0x0052 }
            e = r1     // Catch:{ all -> 0x0052 }
        L_0x0067:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            android.os.Trace.endSection()
            return
        L_0x006c:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: Dd.C0734e.b(android.app.Application, Dd.c):void");
    }

    public void onResult(Object obj) {
        b bVar = this.d;
        C0732c cVar = (C0732c) bVar.d;
        if (((Boolean) obj).booleanValue()) {
            Context context = (Context) bVar.e;
            cVar.getClass();
            Kd.a c5 = Kd.a.c(context, cVar);
            c5.getClass();
            e eVar = new e(context, 12);
            c5.d = true;
            c5.e = eVar;
            LinkedBlockingQueue linkedBlockingQueue = (LinkedBlockingQueue) ((c) c5.f).e;
            if (!linkedBlockingQueue.isEmpty()) {
                Iterator it = linkedBlockingQueue.iterator();
                while (it.hasNext()) {
                    ((e) c5.e).M((d) it.next());
                }
                linkedBlockingQueue.clear();
            }
        }
    }

    public C0734e(b bVar) {
        this.d = bVar;
    }
}
