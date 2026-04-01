package Zf;

import Ae.c;
import Vf.A;
import Yf.h;
import androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1;
import me.x;
import qe.C1227c;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends i implements c {
    public Xf.i d;
    public byte[] e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f3981h;

    /* renamed from: i  reason: collision with root package name */
    public /* synthetic */ Object f3982i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Yf.g[] f3983j;
    public final /* synthetic */ WorkConstraintsTracker$track$$inlined$combine$1.AnonymousClass2 k;
    public final /* synthetic */ WorkConstraintsTracker$track$$inlined$combine$1.AnonymousClass3 l;
    public final /* synthetic */ h m;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(Yf.g[] gVarArr, WorkConstraintsTracker$track$$inlined$combine$1.AnonymousClass2 r22, WorkConstraintsTracker$track$$inlined$combine$1.AnonymousClass3 r32, h hVar, C1227c cVar) {
        super(2, cVar);
        this.f3983j = gVarArr;
        this.k = r22;
        this.l = r32;
        this.m = hVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        g gVar = new g(this.f3983j, this.k, this.l, this.m, cVar);
        gVar.f3982i = obj;
        return gVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00ab, code lost:
        if (r13 == r1) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fc, code lost:
        if (r15.invoke(r14, r10, r0) == r1) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0114, code lost:
        if (r15.invoke(r14, r13, r0) == r1) goto L_0x0116;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            r19 = this;
            r0 = r19
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f3981h
            Qe.B r3 = Zf.k.b
            r4 = 3
            r5 = 2
            r6 = 0
            r7 = 0
            r8 = 1
            if (r2 == 0) goto L_0x0066
            if (r2 == r8) goto L_0x004a
            if (r2 == r5) goto L_0x0034
            if (r2 != r4) goto L_0x002c
            int r2 = r0.g
            int r9 = r0.f
            byte[] r10 = r0.e
            Xf.i r11 = r0.d
            java.lang.Object r12 = r0.f3982i
            java.lang.Object[] r12 = (java.lang.Object[]) r12
            L2.a.A(r20)
            r18 = r12
            r12 = r2
            r2 = r10
            r10 = r18
            goto L_0x0117
        L_0x002c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0034:
            int r2 = r0.g
            int r9 = r0.f
            byte[] r10 = r0.e
            Xf.i r11 = r0.d
            java.lang.Object r12 = r0.f3982i
            java.lang.Object[] r12 = (java.lang.Object[]) r12
            L2.a.A(r20)
            r18 = r12
            r12 = r2
            r2 = r10
            r10 = r18
            goto L_0x0099
        L_0x004a:
            int r2 = r0.g
            int r9 = r0.f
            byte[] r10 = r0.e
            Xf.i r11 = r0.d
            java.lang.Object r12 = r0.f3982i
            java.lang.Object[] r12 = (java.lang.Object[]) r12
            L2.a.A(r20)
            r13 = r20
            Xf.l r13 = (Xf.l) r13
            java.lang.Object r13 = r13.f3911a
            r18 = r12
            r12 = r2
            r2 = r10
            r10 = r18
            goto L_0x00ae
        L_0x0066:
            L2.a.A(r20)
            java.lang.Object r2 = r0.f3982i
            Vf.A r2 = (Vf.A) r2
            Yf.g[] r9 = r0.f3983j
            int r9 = r9.length
            if (r9 != 0) goto L_0x0073
            goto L_0x00b8
        L_0x0073:
            java.lang.Object[] r10 = new java.lang.Object[r9]
            java.util.Arrays.fill(r10, r7, r9, r3)
            r11 = 6
            Xf.e r16 = He.F.c(r9, r6, r11)
            java.util.concurrent.atomic.AtomicInteger r15 = new java.util.concurrent.atomic.AtomicInteger
            r15.<init>(r9)
            r14 = r7
        L_0x0083:
            if (r14 >= r9) goto L_0x0094
            Zf.f r12 = new Zf.f
            Yf.g[] r13 = r0.f3983j
            r17 = 0
            r12.<init>(r13, r14, r15, r16, r17)
            Vf.D.n(r2, r6, r6, r12, r4)
            int r14 = r14 + 1
            goto L_0x0083
        L_0x0094:
            byte[] r2 = new byte[r9]
            r12 = r7
            r11 = r16
        L_0x0099:
            int r12 = r12 + r8
            byte r12 = (byte) r12
            r0.f3982i = r10
            r0.d = r11
            r0.e = r2
            r0.f = r9
            r0.g = r12
            r0.f3981h = r8
            java.lang.Object r13 = r11.f(r0)
            if (r13 != r1) goto L_0x00ae
            goto L_0x0116
        L_0x00ae:
            boolean r14 = r13 instanceof Xf.k
            if (r14 != 0) goto L_0x00b3
            goto L_0x00b4
        L_0x00b3:
            r13 = r6
        L_0x00b4:
            ne.x r13 = (ne.x) r13
            if (r13 != 0) goto L_0x00bb
        L_0x00b8:
            me.x r0 = me.x.f4917a
            return r0
        L_0x00bb:
            int r14 = r13.f4950a
            r15 = r10[r14]
            java.lang.Object r13 = r13.b
            r10[r14] = r13
            if (r15 != r3) goto L_0x00c7
            int r9 = r9 + -1
        L_0x00c7:
            byte r13 = r2[r14]
            if (r13 == r12) goto L_0x00dc
            byte r13 = (byte) r12
            r2[r14] = r13
            java.lang.Object r13 = r11.h()
            boolean r14 = r13 instanceof Xf.k
            if (r14 != 0) goto L_0x00d7
            goto L_0x00d8
        L_0x00d7:
            r13 = r6
        L_0x00d8:
            ne.x r13 = (ne.x) r13
            if (r13 != 0) goto L_0x00bb
        L_0x00dc:
            if (r9 != 0) goto L_0x0099
            androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$2 r13 = r0.k
            java.lang.Object r13 = r13.invoke()
            java.lang.Object[] r13 = (java.lang.Object[]) r13
            Yf.h r14 = r0.m
            androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3 r15 = r0.l
            if (r13 != 0) goto L_0x00ff
            r0.f3982i = r10
            r0.d = r11
            r0.e = r2
            r0.f = r9
            r0.g = r12
            r0.f3981h = r5
            java.lang.Object r13 = r15.invoke(r14, r10, r0)
            if (r13 != r1) goto L_0x0099
            goto L_0x0116
        L_0x00ff:
            r5 = 14
            ne.C1192j.h0(r7, r7, r5, r10, r13)
            r0.f3982i = r10
            r0.d = r11
            r0.e = r2
            r0.f = r9
            r0.g = r12
            r0.f3981h = r4
            java.lang.Object r5 = r15.invoke(r14, r13, r0)
            if (r5 != r1) goto L_0x0117
        L_0x0116:
            return r1
        L_0x0117:
            r5 = 2
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: Zf.g.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
