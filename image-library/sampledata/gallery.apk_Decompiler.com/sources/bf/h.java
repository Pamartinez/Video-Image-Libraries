package bf;

import Ae.a;
import Hf.C0774x;
import Hf.a0;
import If.g;
import Ne.u;
import Qe.A;
import Qe.C0811a;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0826p;
import Qe.O;
import Qe.Q;
import Te.F;
import Te.H;
import Te.I;
import Te.J;
import Ve.f;
import Ze.x;
import cf.c;
import hf.C1096r;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1202t;
import qf.C1236c;
import qf.C1240g;
import tf.C1312p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class h extends H implements C0916a {
    public final boolean E;

    /* renamed from: F  reason: collision with root package name */
    public final i f4005F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(C0822l lVar, Re.h hVar, A a7, C0826p pVar, boolean z, C1240g gVar, Q q, O o2, C0813c cVar, boolean z3, i iVar) {
        super(lVar, o2, hVar, a7, pVar, z, gVar, cVar, q, false, false, false, false, false);
        if (lVar == null) {
            w0(0);
            throw null;
        } else if (hVar == null) {
            w0(1);
            throw null;
        } else if (a7 == null) {
            w0(2);
            throw null;
        } else if (pVar == null) {
            w0(3);
            throw null;
        } else if (gVar == null) {
            w0(4);
            throw null;
        } else if (q == null) {
            w0(5);
            throw null;
        } else if (cVar != null) {
            this.E = z3;
            this.f4005F = iVar;
        } else {
            w0(6);
            throw null;
        }
    }

    public static h M0(C0822l lVar, c cVar, A a7, C0826p pVar, boolean z, C1240g gVar, f fVar, boolean z3) {
        if (lVar == null) {
            w0(7);
            throw null;
        } else if (a7 == null) {
            w0(9);
            throw null;
        } else if (gVar != null) {
            return new h(lVar, cVar, a7, pVar, z, gVar, fVar, (O) null, C0813c.DECLARATION, z3, (i) null);
        } else {
            w0(11);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 21) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i2 != 21) {
            i7 = 3;
        } else {
            i7 = 2;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 8:
                objArr[0] = "annotations";
                break;
            case 2:
            case 9:
                objArr[0] = "modality";
                break;
            case 3:
            case 10:
                objArr[0] = "visibility";
                break;
            case 4:
            case 11:
                objArr[0] = "name";
                break;
            case 5:
            case 12:
            case 18:
                objArr[0] = "source";
                break;
            case 6:
            case 16:
                objArr[0] = "kind";
                break;
            case 13:
                objArr[0] = "newOwner";
                break;
            case 14:
                objArr[0] = "newModality";
                break;
            case 15:
                objArr[0] = "newVisibility";
                break;
            case 17:
                objArr[0] = "newName";
                break;
            case 19:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 20:
                objArr[0] = "enhancedReturnType";
                break;
            case 21:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
                break;
            case 22:
                objArr[0] = "inType";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 != 21) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i2) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "create";
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 19:
            case 20:
                objArr[2] = "enhance";
                break;
            case 21:
                break;
            case 22:
                objArr[2] = "setInType";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 != 21) {
            th = new IllegalArgumentException(format);
        } else {
            th = new IllegalStateException(format);
        }
        throw th;
    }

    public final H G0(C0822l lVar, A a7, C0826p pVar, O o2, C0813c cVar, C1240g gVar) {
        if (lVar == null) {
            w0(13);
            throw null;
        } else if (a7 == null) {
            w0(14);
            throw null;
        } else if (pVar == null) {
            w0(15);
            throw null;
        } else if (cVar == null) {
            w0(16);
            throw null;
        } else if (gVar != null) {
            return new h(lVar, getAnnotations(), a7, pVar, this.f3758j, gVar, Q.f3662a, o2, cVar, this.E, this.f4005F);
        } else {
            w0(17);
            throw null;
        }
    }

    public final boolean U() {
        C0774x type = getType();
        if (!this.E) {
            return false;
        }
        j.e(type, "type");
        if (((!Ne.i.F(type) && !u.a(type)) || a0.e(type)) && !Ne.i.G(type)) {
            return false;
        }
        Re.i iVar = C1096r.f4598a;
        C1236c cVar = x.f3969p;
        j.d(cVar, "ENHANCED_NULLABILITY_ANNOTATION");
        if (!g.u(type, cVar) || Ne.i.G(type)) {
            return true;
        }
        return false;
    }

    public final boolean Z() {
        return false;
    }

    public final C0916a p(C0774x xVar, ArrayList arrayList, C0774x xVar2, i iVar) {
        O o2;
        C0774x xVar3;
        I i2;
        J j2;
        J j3;
        I i7;
        C0774x xVar4 = xVar;
        Te.u uVar = null;
        if (a() == this) {
            o2 = null;
        } else {
            o2 = a();
        }
        h hVar = new h(g(), getAnnotations(), k(), getVisibility(), this.f3758j, getName(), getSource(), o2, b(), this.E, iVar);
        I i8 = this.f3756A;
        if (i8 != null) {
            Re.h annotations = i8.getAnnotations();
            A k = i8.k();
            C0826p visibility = i8.getVisibility();
            boolean z = i8.f3747i;
            boolean z3 = i8.f3748j;
            boolean z7 = i8.m;
            C0813c b = b();
            if (o2 == null) {
                i7 = null;
            } else {
                i7 = o2.getGetter();
            }
            I i10 = new I(hVar, annotations, k, visibility, z, z3, z7, b, i7, i8.getSource());
            i10.f3750p = i8.f3750p;
            xVar3 = xVar2;
            i10.q = xVar3;
            i2 = i10;
        } else {
            xVar3 = xVar2;
            i2 = null;
        }
        J j8 = this.B;
        if (j8 != null) {
            Re.h annotations2 = j8.getAnnotations();
            F f = j8;
            A k10 = f.k();
            C0826p visibility2 = f.getVisibility();
            boolean z9 = f.f3747i;
            boolean z10 = f.f3748j;
            boolean z11 = f.m;
            C0813c b5 = b();
            if (o2 == null) {
                j3 = null;
            } else {
                j3 = o2.getSetter();
            }
            j2 = new J(hVar, annotations2, k10, visibility2, z9, z10, z11, b5, j3, j8.getSource());
            j2.f3750p = j2.f3750p;
            Te.Q q = (Te.Q) j8.B().get(0);
            if (q != null) {
                j2.q = q;
            } else {
                J.w0(6);
                throw null;
            }
        } else {
            j2 = null;
        }
        hVar.I0(i2, j2, this.f3757C, this.D);
        a aVar = this.l;
        if (aVar != null) {
            hVar.J0(this.k, aVar);
        }
        hVar.m0(h());
        if (xVar4 != null) {
            uVar = C1312p.k(this, xVar4, Re.g.f3692a);
        }
        hVar.L0(xVar3, getTypeParameters(), this.f3761x, uVar, C1202t.d);
        return hVar;
    }

    public final Object r0(C0811a aVar) {
        i iVar = this.f4005F;
        if (iVar == null || !((C0811a) iVar.d).equals(aVar)) {
            return null;
        }
        return iVar.e;
    }

    public final void K0(C0774x xVar) {
    }
}
