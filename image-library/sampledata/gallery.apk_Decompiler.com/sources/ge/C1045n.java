package ge;

import A0.l;
import ee.C0979l;

/* renamed from: ge.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1045n extends C1068z {
    public final /* synthetic */ int e = 0;
    public final /* synthetic */ Object f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1045n(A0 a0, C0979l lVar) {
        super(lVar);
        this.f = a0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r3 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        if (r3.hasNext() == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        ((java.lang.Runnable) r3.next()).run();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r3 = this;
            int r0 = r3.e
            switch(r0) {
                case 0: goto L_0x004d;
                case 1: goto L_0x0045;
                default: goto L_0x0005;
            }
        L_0x0005:
            java.lang.Object r3 = r3.f
            r0 = r3
            ge.C r0 = (ge.C1002C) r0
            r0.getClass()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
        L_0x0012:
            monitor-enter(r0)
            java.util.List r1 = r0.e     // Catch:{ all -> 0x0023 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x0025
            r3 = 0
            r0.e = r3     // Catch:{ all -> 0x0023 }
            r3 = 1
            r0.d = r3     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            return
        L_0x0023:
            r3 = move-exception
            goto L_0x0043
        L_0x0025:
            java.util.List r1 = r0.e     // Catch:{ all -> 0x0023 }
            r0.e = r3     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            java.util.Iterator r3 = r1.iterator()
        L_0x002e:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x003e
            java.lang.Object r2 = r3.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L_0x002e
        L_0x003e:
            r1.clear()
            r3 = r1
            goto L_0x0012
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            throw r3
        L_0x0045:
            java.lang.Object r3 = r3.f
            ge.D r3 = (ge.C1003D) r3
            r3.o()
            return
        L_0x004d:
            java.lang.Object r3 = r3.f
            A0.l r3 = (A0.l) r3
            ie.b.b()
            ie.b.a()     // Catch:{ all -> 0x0089 }
            ie.a r0 = ie.b.f4600a     // Catch:{ all -> 0x0089 }
            r0.getClass()     // Catch:{ all -> 0x0089 }
            java.lang.Object r0 = r3.f     // Catch:{ all -> 0x0089 }
            ee.a0 r0 = (ee.a0) r0     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0063
            goto L_0x0083
        L_0x0063:
            java.lang.Object r0 = r3.e     // Catch:{ all -> 0x006b }
            ee.d r0 = (ee.C0971d) r0     // Catch:{ all -> 0x006b }
            r0.h()     // Catch:{ all -> 0x006b }
            goto L_0x0083
        L_0x006b:
            r0 = move-exception
            ee.a0 r1 = ee.a0.f     // Catch:{ all -> 0x0089 }
            ee.a0 r0 = r1.f(r0)     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = "Failed to call onReady."
            ee.a0 r0 = r0.g(r1)     // Catch:{ all -> 0x0089 }
            r3.f = r0     // Catch:{ all -> 0x0089 }
            java.lang.Object r3 = r3.g     // Catch:{ all -> 0x0089 }
            ge.q r3 = (ge.C1051q) r3     // Catch:{ all -> 0x0089 }
            ge.r r3 = r3.l     // Catch:{ all -> 0x0089 }
            r3.v(r0)     // Catch:{ all -> 0x0089 }
        L_0x0083:
            ie.a r3 = ie.b.f4600a
            r3.getClass()
            return
        L_0x0089:
            r3 = move-exception
            ie.a r0 = ie.b.f4600a     // Catch:{ all -> 0x0090 }
            r0.getClass()     // Catch:{ all -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x0094:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1045n.a():void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1045n(C1003D d, C1002C c5) {
        super(d.f);
        this.f = c5;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1045n(l lVar) {
        super(((C1051q) lVar.g).f4536h);
        this.f = lVar;
    }
}
