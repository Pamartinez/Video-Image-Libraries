package F2;

import E2.l;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: F2.i0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0022i0 extends O0 {
    public C0007b d;
    public Object e;
    public final /* synthetic */ int f;
    public final Iterator g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f265h;

    public C0022i0() {
        this.d = C0007b.NOT_READY;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r0 = r5.g;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r0.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r0 = r0.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        if (((F2.H0) r5.f265h).e.contains(r0) == false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        r5.d = F2.C0007b.DONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r0 = r5.g;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        if (r0.hasNext() == false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        r0 = r0.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        if (((E2.l) r5.f265h).apply(r0) == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        r5.d = F2.C0007b.DONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
        r5.e = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0061, code lost:
        if (r5.d == F2.C0007b.DONE) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        r5.d = F2.C0007b.READY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0067, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasNext() {
        /*
            r5 = this;
            F2.b r0 = r5.d
            F2.b r1 = F2.C0007b.FAILED
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x000a
            r0 = r3
            goto L_0x000b
        L_0x000a:
            r0 = r2
        L_0x000b:
            He.F.r(r0)
            F2.b r0 = r5.d
            int r0 = r0.ordinal()
            if (r0 == 0) goto L_0x0069
            r4 = 2
            if (r0 == r4) goto L_0x0068
            r5.d = r1
            int r0 = r5.f
            switch(r0) {
                case 0: goto L_0x003f;
                default: goto L_0x0020;
            }
        L_0x0020:
            java.util.Iterator r0 = r5.g
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0039
            java.lang.Object r0 = r0.next()
            java.lang.Object r1 = r5.f265h
            F2.H0 r1 = (F2.H0) r1
            java.util.Set r1 = r1.e
            boolean r1 = r1.contains(r0)
            if (r1 == 0) goto L_0x0020
            goto L_0x005b
        L_0x0039:
            F2.b r0 = F2.C0007b.DONE
            r5.d = r0
        L_0x003d:
            r0 = 0
            goto L_0x005b
        L_0x003f:
            java.util.Iterator r0 = r5.g
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0056
            java.lang.Object r0 = r0.next()
            java.lang.Object r1 = r5.f265h
            E2.l r1 = (E2.l) r1
            boolean r1 = r1.apply(r0)
            if (r1 == 0) goto L_0x003f
            goto L_0x005b
        L_0x0056:
            F2.b r0 = F2.C0007b.DONE
            r5.d = r0
            goto L_0x003d
        L_0x005b:
            r5.e = r0
            F2.b r0 = r5.d
            F2.b r1 = F2.C0007b.DONE
            if (r0 == r1) goto L_0x0068
            F2.b r0 = F2.C0007b.READY
            r5.d = r0
            return r3
        L_0x0068:
            return r2
        L_0x0069:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: F2.C0022i0.hasNext():boolean");
    }

    public final Object next() {
        if (hasNext()) {
            this.d = C0007b.NOT_READY;
            Object obj = this.e;
            this.e = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C0022i0(Iterator it, l lVar) {
        this();
        this.f = 0;
        this.g = it;
        this.f265h = lVar;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C0022i0(H0 h0) {
        this();
        this.f = 1;
        this.f265h = h0;
        this.g = h0.d.iterator();
    }
}
