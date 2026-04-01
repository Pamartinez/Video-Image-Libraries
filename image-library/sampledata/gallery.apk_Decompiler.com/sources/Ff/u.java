package Ff;

import B1.b;
import Qe.A;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0826p;
import Qe.O;
import Re.h;
import Te.H;
import kotlin.jvm.internal.j;
import lf.G;
import nf.C1208e;
import nf.C1209f;
import nf.C1211h;
import qf.C1240g;
import rf.C1252b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends H implements b {
    public final G E;

    /* renamed from: F  reason: collision with root package name */
    public final C1209f f3400F;

    /* renamed from: G  reason: collision with root package name */
    public final b f3401G;

    /* renamed from: H  reason: collision with root package name */
    public final C1211h f3402H;

    /* renamed from: I  reason: collision with root package name */
    public final m f3403I;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public u(Qe.C0822l r17, Qe.O r18, Re.h r19, Qe.A r20, Qe.C0826p r21, boolean r22, qf.C1240g r23, Qe.C0813c r24, boolean r25, boolean r26, boolean r27, boolean r28, boolean r29, lf.G r30, nf.C1209f r31, B1.b r32, nf.C1211h r33, Ff.m r34) {
        /*
            r16 = this;
            r15 = r30
            r0 = r31
            r1 = r32
            r2 = r33
            java.lang.String r3 = "containingDeclaration"
            r4 = r17
            kotlin.jvm.internal.j.e(r4, r3)
            java.lang.String r3 = "annotations"
            r5 = r19
            kotlin.jvm.internal.j.e(r5, r3)
            java.lang.String r3 = "modality"
            r6 = r20
            kotlin.jvm.internal.j.e(r6, r3)
            java.lang.String r3 = "visibility"
            r7 = r21
            kotlin.jvm.internal.j.e(r7, r3)
            java.lang.String r3 = "name"
            r8 = r23
            kotlin.jvm.internal.j.e(r8, r3)
            java.lang.String r3 = "kind"
            r9 = r24
            kotlin.jvm.internal.j.e(r9, r3)
            java.lang.String r3 = "proto"
            kotlin.jvm.internal.j.e(r15, r3)
            java.lang.String r3 = "nameResolver"
            kotlin.jvm.internal.j.e(r0, r3)
            java.lang.String r3 = "typeTable"
            kotlin.jvm.internal.j.e(r1, r3)
            java.lang.String r3 = "versionRequirementTable"
            kotlin.jvm.internal.j.e(r2, r3)
            Qe.S r9 = Qe.Q.f3662a
            r0 = r16
            r2 = r18
            r10 = r25
            r11 = r26
            r13 = r27
            r14 = r28
            r12 = r29
            r1 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r7 = r8
            r6 = r22
            r8 = r24
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            r0.E = r15
            r1 = r31
            r0.f3400F = r1
            r1 = r32
            r0.f3401G = r1
            r2 = r33
            r0.f3402H = r2
            r1 = r34
            r0.f3403I = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.u.<init>(Qe.l, Qe.O, Re.h, Qe.A, Qe.p, boolean, qf.g, Qe.c, boolean, boolean, boolean, boolean, boolean, lf.G, nf.f, B1.b, nf.h, Ff.m):void");
    }

    public final C1209f C() {
        return this.f3400F;
    }

    public final m D() {
        return this.f3403I;
    }

    public final H G0(C0822l lVar, A a7, C0826p pVar, O o2, C0813c cVar, C1240g gVar) {
        C0822l lVar2 = lVar;
        j.e(lVar2, "newOwner");
        A a10 = a7;
        j.e(a10, "newModality");
        C0826p pVar2 = pVar;
        j.e(pVar2, "newVisibility");
        C0813c cVar2 = cVar;
        j.e(cVar2, "kind");
        C1240g gVar2 = gVar;
        j.e(gVar2, "newName");
        h annotations = getAnnotations();
        boolean isExternal = isExternal();
        C1211h hVar = this.f3402H;
        m mVar = this.f3403I;
        C1211h hVar2 = hVar;
        m mVar2 = mVar;
        return new u(lVar2, o2, annotations, a10, pVar2, this.f3758j, gVar2, cVar2, this.r, this.s, isExternal, this.v, this.t, this.E, this.f3400F, this.f3401G, hVar2, mVar2);
    }

    public final C1252b Y() {
        return this.E;
    }

    public final boolean isExternal() {
        return C1208e.E.c(this.E.g).booleanValue();
    }

    public final b z() {
        return this.f3401G;
    }
}
