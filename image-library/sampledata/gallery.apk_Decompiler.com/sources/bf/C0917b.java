package bf;

import B1.a;
import Hf.C0774x;
import Qe.C0811a;
import Qe.C0813c;
import Qe.C0816f;
import Qe.C0822l;
import Qe.C0831v;
import Qe.Q;
import Re.g;
import Re.h;
import Te.C0848i;
import Te.t;
import Te.u;
import Ve.f;
import java.util.ArrayList;
import me.i;
import ne.C1202t;
import qf.C1240g;
import tf.C1312p;

/* renamed from: bf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0917b extends C0848i implements C0916a {

    /* renamed from: I  reason: collision with root package name */
    public Boolean f3999I;

    /* renamed from: J  reason: collision with root package name */
    public Boolean f4000J;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0917b(C0816f fVar, C0917b bVar, h hVar, boolean z, C0813c cVar, Q q) {
        super(fVar, bVar, hVar, z, cVar, q);
        if (fVar == null) {
            w0(0);
            throw null;
        } else if (hVar == null) {
            w0(1);
            throw null;
        } else if (cVar == null) {
            w0(2);
            throw null;
        } else if (q != null) {
            this.f3999I = null;
            this.f4000J = null;
        } else {
            w0(3);
            throw null;
        }
    }

    public static C0917b V0(C0816f fVar, h hVar, boolean z, f fVar2) {
        if (fVar != null) {
            return new C0917b(fVar, (C0917b) null, hVar, z, C0813c.DECLARATION, fVar2);
        }
        w0(4);
        throw null;
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 11 || i2 == 18) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 11 || i2 == 18) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 5:
            case 9:
            case 15:
                objArr[0] = "annotations";
                break;
            case 2:
            case 8:
            case 13:
                objArr[0] = "kind";
                break;
            case 3:
            case 6:
            case 10:
                objArr[0] = "source";
                break;
            case 7:
            case 12:
                objArr[0] = "newOwner";
                break;
            case 11:
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
                break;
            case 14:
                objArr[0] = "sourceElement";
                break;
            case 16:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 17:
                objArr[0] = "enhancedReturnType";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 11) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i2 != 18) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i2) {
            case 4:
            case 5:
            case 6:
                objArr[2] = "createJavaConstructor";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 11:
            case 18:
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[2] = "createDescriptor";
                break;
            case 16:
            case 17:
                objArr[2] = "enhance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 11 || i2 == 18) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        C0831v vVar2 = vVar;
        C0813c cVar2 = cVar;
        return W0(lVar, vVar2, cVar2, hVar, q);
    }

    public final void M0(boolean z) {
        this.f3999I = Boolean.valueOf(z);
    }

    public final void N0(boolean z) {
        this.f4000J = Boolean.valueOf(z);
    }

    public final /* bridge */ /* synthetic */ C0848i P0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        C0831v vVar2 = vVar;
        C0813c cVar2 = cVar;
        return W0(lVar, vVar2, cVar2, hVar, q);
    }

    public final C0917b W0(C0822l lVar, C0831v vVar, C0813c cVar, h hVar, Q q) {
        if (lVar == null) {
            w0(7);
            throw null;
        } else if (cVar == null) {
            w0(8);
            throw null;
        } else if (hVar == null) {
            w0(9);
            throw null;
        } else if (q == null) {
            w0(10);
            throw null;
        } else if (cVar == C0813c.DECLARATION || cVar == C0813c.SYNTHESIZED) {
            C0917b bVar = new C0917b((C0816f) lVar, (C0917b) vVar, hVar, this.f3779H, cVar, q);
            Boolean bool = this.f3999I;
            bool.getClass();
            bVar.f3999I = bool;
            Boolean bool2 = this.f4000J;
            bool2.getClass();
            bVar.f4000J = bool2;
            return bVar;
        } else {
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + lVar + "\nkind: " + cVar);
        }
    }

    public final boolean Z() {
        return this.f4000J.booleanValue();
    }

    public final C0916a p(C0774x xVar, ArrayList arrayList, C0774x xVar2, i iVar) {
        u k;
        C0774x xVar3 = xVar;
        i iVar2 = iVar;
        C0917b W02 = W0(g(), (C0831v) null, b(), getAnnotations(), getSource());
        if (xVar3 == null) {
            k = null;
        } else {
            k = C1312p.k(W02, xVar3, g.f3692a);
        }
        u uVar = k;
        W02.J0(uVar, this.n, C1202t.d, getTypeParameters(), a.p(arrayList, B(), W02), xVar2, k(), getVisibility());
        if (iVar2 != null) {
            W02.L0((C0811a) iVar2.d, iVar2.e);
        }
        return W02;
    }
}
