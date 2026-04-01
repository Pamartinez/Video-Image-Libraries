package Te;

import Hf.C0774x;
import Qe.C0824n;
import Qe.N;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class I extends F implements N {
    public C0774x q;
    public final I r;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public I(Qe.O r13, Re.h r14, Qe.A r15, Qe.C0826p r16, boolean r17, boolean r18, boolean r19, Qe.C0813c r20, Te.I r21, Qe.Q r22) {
        /*
            r12 = this;
            r0 = 0
            if (r14 == 0) goto L_0x0056
            if (r15 == 0) goto L_0x0051
            if (r16 == 0) goto L_0x004c
            if (r20 == 0) goto L_0x0047
            if (r22 == 0) goto L_0x0042
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "<get-"
            r0.<init>(r1)
            qf.g r1 = r13.getName()
            r0.append(r1)
            java.lang.String r1 = ">"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            qf.g r6 = qf.C1240g.g(r0)
            r1 = r12
            r4 = r13
            r5 = r14
            r2 = r15
            r3 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            r10 = r20
            r11 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r21 == 0) goto L_0x003e
            r13 = r21
            goto L_0x003f
        L_0x003e:
            r13 = r12
        L_0x003f:
            r12.r = r13
            return
        L_0x0042:
            r12 = 5
            w0(r12)
            throw r0
        L_0x0047:
            r12 = 4
            w0(r12)
            throw r0
        L_0x004c:
            r12 = 3
            w0(r12)
            throw r0
        L_0x0051:
            r12 = 2
            w0(r12)
            throw r0
        L_0x0056:
            r12 = 1
            w0(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.I.<init>(Qe.O, Re.h, Qe.A, Qe.p, boolean, boolean, boolean, Qe.c, Te.I, Qe.Q):void");
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 6 || i2 == 7 || i2 == 8) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 6 || i2 == 7 || i2 == 8) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "modality";
                break;
            case 3:
                objArr[0] = "visibility";
                break;
            case 4:
                objArr[0] = "kind";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
                break;
            default:
                objArr[0] = "correspondingProperty";
                break;
        }
        if (i2 == 6) {
            objArr[1] = "getOverriddenDescriptors";
        } else if (i2 == 7) {
            objArr[1] = "getValueParameters";
        } else if (i2 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
        } else {
            objArr[1] = "getOriginal";
        }
        if (!(i2 == 6 || i2 == 7 || i2 == 8)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 6 || i2 == 7 || i2 == 8) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final List B() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        w0(7);
        throw null;
    }

    /* renamed from: G0 */
    public final I a() {
        I i2 = this.r;
        if (i2 != null) {
            return i2;
        }
        w0(8);
        throw null;
    }

    public final void H0(C0774x xVar) {
        if (xVar == null) {
            xVar = E0().getType();
        }
        this.q = xVar;
    }

    public final C0774x getReturnType() {
        return this.q;
    }

    public final Collection h() {
        return F0(true);
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.G(this, obj);
    }
}
