package Te;

import Hf.C0774x;
import Hf.X;
import Qe.A;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0830u;
import Qe.C0831v;
import Qe.Q;
import Re.g;
import Re.h;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class K extends t {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public K(Qe.C0822l r3, Te.K r4, Re.h r5, qf.C1240g r6, Qe.C0813c r7, Qe.Q r8) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x002b
            if (r5 == 0) goto L_0x0026
            if (r6 == 0) goto L_0x0021
            if (r7 == 0) goto L_0x001c
            if (r8 == 0) goto L_0x0017
            r1 = r4
            r4 = r3
            r3 = r7
            r7 = r5
            r5 = r1
            r1 = r8
            r8 = r6
            r6 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        L_0x0017:
            r2 = 4
            w0(r2)
            throw r0
        L_0x001c:
            r2 = 3
            w0(r2)
            throw r0
        L_0x0021:
            r2 = 2
            w0(r2)
            throw r0
        L_0x0026:
            r2 = 1
            w0(r2)
            throw r0
        L_0x002b:
            r2 = 0
            w0(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.K.<init>(Qe.l, Te.K, Re.h, qf.g, Qe.c, Qe.Q):void");
    }

    public static K P0(C0841b bVar, C1240g gVar, C0813c cVar, Q q) {
        if (bVar == null) {
            w0(5);
            throw null;
        } else if (gVar == null) {
            w0(7);
            throw null;
        } else if (cVar == null) {
            w0(8);
            throw null;
        } else if (q != null) {
            return new K(bVar, (K) null, g.f3692a, gVar, cVar, q);
        } else {
            w0(9);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 13 || i2 == 18 || i2 == 23 || i2 == 24 || i2 == 29 || i2 == 30) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 13 || i2 == 18 || i2 == 23 || i2 == 24 || i2 == 29 || i2 == 30) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 6:
            case 27:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = "name";
                break;
            case 3:
            case 8:
            case 26:
                objArr[0] = "kind";
                break;
            case 4:
            case 9:
            case 28:
                objArr[0] = "source";
                break;
            case 10:
            case 15:
            case 20:
                objArr[0] = "typeParameters";
                break;
            case 11:
            case 16:
            case 21:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 12:
            case 17:
            case 22:
                objArr[0] = "visibility";
                break;
            case 13:
            case 18:
            case 23:
            case 24:
            case 29:
            case 30:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
                break;
            case 14:
            case 19:
                objArr[0] = "contextReceiverParameters";
                break;
            case 25:
                objArr[0] = "newOwner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 13 || i2 == 18 || i2 == 23) {
            objArr[1] = "initialize";
        } else if (i2 == 24) {
            objArr[1] = "getOriginal";
        } else if (i2 == 29) {
            objArr[1] = "copy";
        } else if (i2 != 30) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
        } else {
            objArr[1] = "newCopyBuilder";
        }
        switch (i2) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                objArr[2] = "create";
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
                objArr[2] = "initialize";
                break;
            case 13:
            case 18:
            case 23:
            case 24:
            case 29:
            case 30:
                break;
            case 25:
            case 26:
            case 27:
            case 28:
                objArr[2] = "createSubstitutedCopy";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 13 || i2 == 18 || i2 == 23 || i2 == 24 || i2 == 29 || i2 == 30) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        if (lVar == null) {
            w0(25);
            throw null;
        } else if (cVar == null) {
            w0(26);
            throw null;
        } else if (hVar != null) {
            K k = (K) vVar;
            if (gVar == null) {
                gVar = getName();
            }
            h hVar2 = hVar;
            C0813c cVar2 = cVar;
            return new K(lVar, k, hVar2, gVar, cVar2, q);
        } else {
            w0(27);
            throw null;
        }
    }

    /* renamed from: Q0 */
    public final K a() {
        K k = (K) super.a();
        if (k != null) {
            return k;
        }
        w0(24);
        throw null;
    }

    /* renamed from: R0 */
    public final K J0(u uVar, u uVar2, List list, List list2, List list3, C0774x xVar, A a7, C0826p pVar) {
        if (list == null) {
            w0(14);
            throw null;
        } else if (list2 == null) {
            w0(15);
            throw null;
        } else if (list3 == null) {
            w0(16);
            throw null;
        } else if (pVar != null) {
            return S0(uVar, uVar2, list, list2, list3, xVar, a7, pVar, (Map) null);
        } else {
            w0(17);
            throw null;
        }
    }

    public K S0(u uVar, u uVar2, List list, List list2, List list3, C0774x xVar, A a7, C0826p pVar, Map map) {
        if (list == null) {
            w0(19);
            throw null;
        } else if (list2 == null) {
            w0(20);
            throw null;
        } else if (list3 == null) {
            w0(21);
            throw null;
        } else if (pVar != null) {
            super.J0(uVar, uVar2, list, list2, list3, xVar, a7, pVar);
            if (map != null && !map.isEmpty()) {
                this.f3798G = new LinkedHashMap(map);
            }
            return this;
        } else {
            w0(22);
            throw null;
        }
    }

    public C0830u q0() {
        return K0(X.b);
    }
}
