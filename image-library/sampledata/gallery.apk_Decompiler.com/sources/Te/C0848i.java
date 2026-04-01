package Te;

import Hf.X;
import Qe.A;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0821k;
import Qe.C0822l;
import Qe.C0824n;
import Qe.C0826p;
import Qe.C0831v;
import Qe.Q;
import Re.h;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import qf.C1240g;

/* renamed from: Te.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0848i extends t implements C0821k {

    /* renamed from: H  reason: collision with root package name */
    public final boolean f3779H;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0848i(Qe.C0816f r9, Qe.C0821k r10, Re.h r11, boolean r12, Qe.C0813c r13, Qe.Q r14) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x0026
            if (r11 == 0) goto L_0x0021
            if (r13 == 0) goto L_0x001c
            if (r14 == 0) goto L_0x0017
            qf.g r7 = qf.C1242i.e
            r1 = r8
            r3 = r9
            r4 = r10
            r6 = r11
            r2 = r13
            r5 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r1.f3779H = r12
            return
        L_0x0017:
            r8 = 3
            w0(r8)
            throw r0
        L_0x001c:
            r8 = 2
            w0(r8)
            throw r0
        L_0x0021:
            r8 = 1
            w0(r8)
            throw r0
        L_0x0026:
            r8 = 0
            w0(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.C0848i.<init>(Qe.f, Qe.k, Re.h, boolean, Qe.c, Qe.Q):void");
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (!(i2 == 21 || i2 == 27)) {
            switch (i2) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i2 == 21 || i2 == 27)) {
            switch (i2) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    break;
                default:
                    i7 = 3;
                    break;
            }
        }
        i7 = 2;
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 5:
            case 8:
            case 25:
                objArr[0] = "annotations";
                break;
            case 2:
            case 24:
                objArr[0] = "kind";
                break;
            case 3:
            case 6:
            case 9:
            case 26:
                objArr[0] = "source";
                break;
            case 10:
            case 13:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 11:
            case 14:
                objArr[0] = "visibility";
                break;
            case 12:
                objArr[0] = "typeParameterDescriptors";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 27:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassConstructorDescriptorImpl";
                break;
            case 20:
                objArr[0] = "originalSubstitutor";
                break;
            case 22:
                objArr[0] = "overriddenDescriptors";
                break;
            case 23:
                objArr[0] = "newOwner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 21) {
            objArr[1] = "getOverriddenDescriptors";
        } else if (i2 != 27) {
            switch (i2) {
                case 15:
                case 16:
                    objArr[1] = "calculateContextReceiverParameters";
                    break;
                case 17:
                    objArr[1] = "getContainingDeclaration";
                    break;
                case 18:
                    objArr[1] = "getConstructedClass";
                    break;
                case 19:
                    objArr[1] = "getOriginal";
                    break;
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassConstructorDescriptorImpl";
                    break;
            }
        } else {
            objArr[1] = "copy";
        }
        switch (i2) {
            case 4:
            case 5:
            case 6:
                objArr[2] = "create";
                break;
            case 7:
            case 8:
            case 9:
                objArr[2] = "createSynthesized";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                objArr[2] = "initialize";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 27:
                break;
            case 20:
                objArr[2] = "substitute";
                break;
            case 22:
                objArr[2] = "setOverriddenDescriptors";
                break;
            case 23:
            case 24:
            case 25:
            case 26:
                objArr[2] = "createSubstitutedCopy";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i2 == 21 || i2 == 27)) {
            switch (i2) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    break;
                default:
                    th = new IllegalArgumentException(format);
                    break;
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    /* renamed from: P0 */
    public C0848i G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        if (lVar == null) {
            w0(23);
            throw null;
        } else if (cVar == null) {
            w0(24);
            throw null;
        } else if (hVar != null) {
            C0813c cVar2 = C0813c.DECLARATION;
            if (cVar == cVar2 || cVar == C0813c.SYNTHESIZED) {
                return new C0848i((C0816f) lVar, this, hVar, this.f3779H, cVar2, q);
            }
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + lVar + "\nkind: " + cVar);
        } else {
            w0(25);
            throw null;
        }
    }

    /* renamed from: Q0 */
    public final C0816f g() {
        C0816f fVar = (C0816f) super.g();
        if (fVar != null) {
            return fVar;
        }
        w0(17);
        throw null;
    }

    /* renamed from: R0 */
    public final C0848i a() {
        C0848i iVar = (C0848i) super.a();
        if (iVar != null) {
            return iVar;
        }
        w0(19);
        throw null;
    }

    public final C0814d S(C0816f fVar, A a7, C0826p pVar, C0813c cVar) {
        return (C0848i) E0(fVar, a7, pVar, cVar);
    }

    public final void S0(List list, C0826p pVar) {
        if (list == null) {
            w0(13);
            throw null;
        } else if (pVar != null) {
            T0(list, pVar, g().j());
        } else {
            w0(14);
            throw null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void T0(java.util.List r12, Qe.C0826p r13, java.util.List r14) {
        /*
            r11 = this;
            r0 = 0
            if (r12 == 0) goto L_0x0061
            if (r13 == 0) goto L_0x005b
            if (r14 == 0) goto L_0x0055
            Qe.f r1 = r11.g()
            boolean r2 = r1.s()
            if (r2 == 0) goto L_0x0021
            Qe.l r1 = r1.g()
            boolean r2 = r1 instanceof Qe.C0816f
            if (r2 == 0) goto L_0x0021
            Qe.f r1 = (Qe.C0816f) r1
            Te.u r1 = r1.v0()
            r4 = r1
            goto L_0x0022
        L_0x0021:
            r4 = r0
        L_0x0022:
            Qe.f r1 = r11.g()
            java.util.List r2 = r1.R()
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x003e
            java.util.List r1 = r1.R()
            if (r1 == 0) goto L_0x0038
        L_0x0036:
            r5 = r1
            goto L_0x0043
        L_0x0038:
            r11 = 15
            w0(r11)
            throw r0
        L_0x003e:
            java.util.List r1 = java.util.Collections.EMPTY_LIST
            if (r1 == 0) goto L_0x004f
            goto L_0x0036
        L_0x0043:
            r8 = 0
            Qe.A r9 = Qe.A.FINAL
            r3 = 0
            r2 = r11
            r7 = r12
            r10 = r13
            r6 = r14
            r2.J0(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        L_0x004f:
            r11 = 16
            w0(r11)
            throw r0
        L_0x0055:
            r11 = 12
            w0(r11)
            throw r0
        L_0x005b:
            r11 = 11
            w0(r11)
            throw r0
        L_0x0061:
            r11 = 10
            w0(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.C0848i.T0(java.util.List, Qe.p, java.util.List):void");
    }

    /* renamed from: U0 */
    public final C0848i c(X x9) {
        if (x9 != null) {
            return (C0848i) super.c(x9);
        }
        w0(20);
        throw null;
    }

    public final boolean V() {
        return this.f3779H;
    }

    public final C0816f W() {
        C0816f Q02 = g();
        if (Q02 != null) {
            return Q02;
        }
        w0(18);
        throw null;
    }

    public final Collection h() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        w0(21);
        throw null;
    }

    public final void m0(Collection collection) {
        if (collection == null) {
            w0(22);
            throw null;
        }
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.c(this, obj);
    }
}
