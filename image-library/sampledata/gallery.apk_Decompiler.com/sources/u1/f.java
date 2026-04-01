package u1;

import A0.l;
import E1.e;
import android.content.Context;
import androidx.collection.ArraySet;
import ge.W0;
import java.util.Collections;
import java.util.Set;
import t1.i;
import v1.C0296a;
import v1.C0298c;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    public final Context f1934a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final W0 f1935c;
    public final C0284b d;
    public final C0296a e;
    public final int f;
    public final i g;

    /* renamed from: h  reason: collision with root package name */
    public final C0298c f1936h;

    public f(Context context, W0 w02, C0284b bVar, e eVar) {
        r.c(context, "Null context is not permitted.");
        r.c(w02, "Api must not be null.");
        r.c(eVar, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        r.c(applicationContext, "The provided context did not have an application context.");
        this.f1934a = applicationContext;
        String attributionTag = context.getAttributionTag();
        this.b = attributionTag;
        this.f1935c = w02;
        this.d = bVar;
        this.e = new C0296a(w02, bVar, attributionTag);
        C0298c e7 = C0298c.e(applicationContext);
        this.f1936h = e7;
        this.f = e7.f1960h.getAndIncrement();
        this.g = eVar.f1933a;
        e eVar2 = e7.m;
        eVar2.sendMessage(eVar2.obtainMessage(7, this));
    }

    public final l a() {
        l lVar = new l(21);
        Set set = Collections.EMPTY_SET;
        if (((ArraySet) lVar.e) == null) {
            lVar.e = new ArraySet();
        }
        ((ArraySet) lVar.e).addAll(set);
        Context context = this.f1934a;
        lVar.g = context.getClass().getName();
        lVar.f = context.getPackageName();
        return lVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final P1.h b(int r17, Y1.f r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            P1.c r2 = new P1.c
            r2.<init>()
            P1.h r3 = r2.f571a
            t1.i r4 = r0.g
            v1.c r6 = r0.f1936h
            E1.e r13 = r6.m
            int r7 = r1.f951c
            if (r7 == 0) goto L_0x0090
            v1.a r8 = r0.e
            boolean r5 = r6.a()
            if (r5 != 0) goto L_0x001e
            goto L_0x005b
        L_0x001e:
            w1.g r5 = w1.g.b()
            java.lang.Object r5 = r5.f2007a
            w1.h r5 = (w1.h) r5
            r9 = 1
            if (r5 == 0) goto L_0x005d
            boolean r10 = r5.e
            if (r10 == 0) goto L_0x005b
            boolean r5 = r5.f
            java.util.concurrent.ConcurrentHashMap r10 = r6.f1962j
            java.lang.Object r10 = r10.get(r8)
            v1.k r10 = (v1.k) r10
            if (r10 == 0) goto L_0x0059
            u1.c r11 = r10.b
            boolean r12 = r11 instanceof com.google.android.gms.common.internal.a
            if (r12 == 0) goto L_0x005b
            com.google.android.gms.common.internal.a r11 = (com.google.android.gms.common.internal.a) r11
            w1.x r12 = r11.u
            if (r12 == 0) goto L_0x0059
            boolean r12 = r11.b()
            if (r12 != 0) goto L_0x0059
            w1.c r5 = v1.o.a(r10, r11, r7)
            if (r5 == 0) goto L_0x005b
            int r11 = r10.l
            int r11 = r11 + r9
            r10.l = r11
            boolean r9 = r5.f
            goto L_0x005d
        L_0x0059:
            r9 = r5
            goto L_0x005d
        L_0x005b:
            r5 = 0
            goto L_0x0074
        L_0x005d:
            v1.o r5 = new v1.o
            r10 = 0
            if (r9 == 0) goto L_0x0068
            long r14 = java.lang.System.currentTimeMillis()
            goto L_0x0069
        L_0x0068:
            r14 = r10
        L_0x0069:
            if (r9 == 0) goto L_0x006f
            long r10 = android.os.SystemClock.elapsedRealtime()
        L_0x006f:
            r11 = r10
            r9 = r14
            r5.<init>(r6, r7, r8, r9, r11)
        L_0x0074:
            if (r5 == 0) goto L_0x0090
            r13.getClass()
            P1.g r7 = new P1.g
            r7.<init>(r13)
            r3.getClass()
            P1.f r8 = new P1.f
            r8.<init>((java.util.concurrent.Executor) r7, (P1.b) r5)
            java.lang.Object r5 = r3.d
            Kd.a r5 = (Kd.a) r5
            r5.h(r8)
            r3.o()
        L_0x0090:
            v1.s r5 = new v1.s
            r7 = r17
            r5.<init>(r7, r1, r2, r4)
            java.util.concurrent.atomic.AtomicInteger r1 = r6.f1961i
            v1.q r2 = new v1.q
            int r1 = r1.get()
            r2.<init>(r5, r1, r0)
            r0 = 4
            android.os.Message r0 = r13.obtainMessage(r0, r2)
            r13.sendMessage(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: u1.f.b(int, Y1.f):P1.h");
    }
}
