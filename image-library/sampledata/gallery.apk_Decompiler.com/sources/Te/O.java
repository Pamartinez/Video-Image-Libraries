package Te;

import Gf.p;
import Hf.B;
import Hf.C0754c;
import Hf.d0;
import Qe.C0822l;
import Re.g;
import Re.h;
import java.util.ArrayList;
import java.util.List;
import qf.C1240g;
import tf.C1301e;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class O extends C0847h {

    /* renamed from: o  reason: collision with root package name */
    public final ArrayList f3767o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f3768p;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public O(Qe.C0822l r11, Re.h r12, boolean r13, Hf.d0 r14, qf.C1240g r15, int r16, Gf.p r17) {
        /*
            r10 = this;
            Qe.S r8 = Qe.S.f
            r9 = 0
            if (r11 == 0) goto L_0x003e
            if (r12 == 0) goto L_0x0038
            if (r14 == 0) goto L_0x0032
            if (r15 == 0) goto L_0x002c
            if (r17 == 0) goto L_0x0026
            r0 = r10
            r2 = r11
            r3 = r12
            r6 = r13
            r5 = r14
            r4 = r15
            r7 = r16
            r1 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            java.util.ArrayList r11 = new java.util.ArrayList
            r12 = 1
            r11.<init>(r12)
            r10.f3767o = r11
            r11 = 0
            r10.f3768p = r11
            return
        L_0x0026:
            r10 = 25
            w0(r10)
            throw r9
        L_0x002c:
            r10 = 22
            w0(r10)
            throw r9
        L_0x0032:
            r10 = 21
            w0(r10)
            throw r9
        L_0x0038:
            r10 = 20
            w0(r10)
            throw r9
        L_0x003e:
            r10 = 19
            w0(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.O.<init>(Qe.l, Re.h, boolean, Hf.d0, qf.g, int, Gf.p):void");
    }

    public static O G0(C0822l lVar, h hVar, boolean z, d0 d0Var, C1240g gVar, int i2, p pVar) {
        if (lVar == null) {
            w0(6);
            throw null;
        } else if (hVar == null) {
            w0(7);
            throw null;
        } else if (d0Var == null) {
            w0(8);
            throw null;
        } else if (gVar == null) {
            w0(9);
            throw null;
        } else if (pVar != null) {
            return new O(lVar, hVar, z, d0Var, gVar, i2, pVar);
        } else {
            w0(11);
            throw null;
        }
    }

    public static O H0(C0841b bVar, d0 d0Var, C1240g gVar, int i2, p pVar) {
        if (d0Var == null) {
            w0(2);
            throw null;
        } else if (pVar != null) {
            C0841b bVar2 = bVar;
            O G02 = G0(bVar2, g.f3692a, false, d0Var, gVar, i2, pVar);
            B m = C1353d.e(bVar2).m();
            if (!G02.f3768p) {
                if (!C0754c.k(m)) {
                    G02.f3767o.add(m);
                }
                if (!G02.f3768p) {
                    G02.f3768p = true;
                    return G02;
                }
                throw new IllegalStateException("Type parameter descriptor is already initialized: " + G02.I0());
            }
            throw new IllegalStateException("Type parameter descriptor is already initialized: " + G02.I0());
        } else {
            w0(4);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 5 || i2 == 28) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 5 || i2 == 28) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 7:
            case 13:
            case 20:
                objArr[0] = "annotations";
                break;
            case 2:
            case 8:
            case 14:
            case 21:
                objArr[0] = "variance";
                break;
            case 3:
            case 9:
            case 15:
            case 22:
                objArr[0] = "name";
                break;
            case 4:
            case 11:
            case 18:
            case 25:
                objArr[0] = "storageManager";
                break;
            case 5:
            case 28:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
                break;
            case 10:
            case 16:
            case 23:
                objArr[0] = "source";
                break;
            case 17:
                objArr[0] = "supertypeLoopsResolver";
                break;
            case 24:
                objArr[0] = "supertypeLoopsChecker";
                break;
            case 26:
                objArr[0] = "bound";
                break;
            case 27:
                objArr[0] = "type";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 5) {
            objArr[1] = "createWithDefaultBound";
        } else if (i2 != 28) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
        } else {
            objArr[1] = "resolveUpperBounds";
        }
        switch (i2) {
            case 5:
            case 28:
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createForFurtherModification";
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                objArr[2] = "<init>";
                break;
            case 26:
                objArr[2] = "addUpperBound";
                break;
            case 27:
                objArr[2] = "reportSupertypeLoopError";
                break;
            default:
                objArr[2] = "createWithDefaultBound";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 5 || i2 == 28) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final List F0() {
        if (this.f3768p) {
            ArrayList arrayList = this.f3767o;
            if (arrayList != null) {
                return arrayList;
            }
            w0(28);
            throw null;
        }
        throw new IllegalStateException("Type parameter descriptor is not initialized: " + I0());
    }

    public final String I0() {
        return getName() + " declared in " + C1301e.g(g());
    }
}
