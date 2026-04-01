package Te;

import Hf.C0774x;
import Qe.C0824n;
import Qe.N;
import Qe.Q;
import Re.h;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import qf.C1242i;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J extends F implements N {
    public Q q;
    public final J r;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public J(Qe.O r13, Re.h r14, Qe.A r15, Qe.C0826p r16, boolean r17, boolean r18, boolean r19, Qe.C0813c r20, Te.J r21, Qe.Q r22) {
        /*
            r12 = this;
            r0 = 0
            if (r14 == 0) goto L_0x0056
            if (r15 == 0) goto L_0x0051
            if (r16 == 0) goto L_0x004c
            if (r20 == 0) goto L_0x0047
            if (r22 == 0) goto L_0x0042
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "<set-"
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
        throw new UnsupportedOperationException("Method not decompiled: Te.J.<init>(Qe.O, Re.h, Qe.A, Qe.p, boolean, boolean, boolean, Qe.c, Te.J, Qe.Q):void");
    }

    public static Q G0(J j2, C0774x xVar, h hVar) {
        if (xVar == null) {
            w0(8);
            throw null;
        } else if (hVar != null) {
            return new Q(j2, (Q) null, 0, hVar, C1242i.g, xVar, false, false, false, (C0774x) null, Q.f3662a);
        } else {
            w0(9);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 10:
            case 11:
            case 12:
            case 13:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 10:
            case 11:
            case 12:
            case 13:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 9:
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
                objArr[0] = "parameter";
                break;
            case 7:
                objArr[0] = "setterDescriptor";
                break;
            case 8:
                objArr[0] = "type";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
                break;
            default:
                objArr[0] = "correspondingProperty";
                break;
        }
        switch (i2) {
            case 10:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 11:
                objArr[1] = "getValueParameters";
                break;
            case 12:
                objArr[1] = "getReturnType";
                break;
            case 13:
                objArr[1] = "getOriginal";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
                break;
        }
        switch (i2) {
            case 6:
                objArr[2] = "initialize";
                break;
            case 7:
            case 8:
            case 9:
                objArr[2] = "createSetterParameter";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 10:
            case 11:
            case 12:
            case 13:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final List B() {
        Q q10 = this.q;
        if (q10 != null) {
            List singletonList = Collections.singletonList(q10);
            if (singletonList != null) {
                return singletonList;
            }
            w0(11);
            throw null;
        }
        throw new IllegalStateException();
    }

    /* renamed from: H0 */
    public final J a() {
        J j2 = this.r;
        if (j2 != null) {
            return j2;
        }
        w0(13);
        throw null;
    }

    public final C0774x getReturnType() {
        return C1353d.e(this).w();
    }

    public final Collection h() {
        return F0(false);
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.g(this, obj);
    }
}
