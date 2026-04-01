package Hf;

import If.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Z extends C0765n {
    public final String e;

    public Z(String str) {
        this.e = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void G0(int r9) {
        /*
            r0 = 4
            r1 = 1
            if (r9 == r1) goto L_0x0009
            if (r9 == r0) goto L_0x0009
            java.lang.String r2 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto L_0x000b
        L_0x0009:
            java.lang.String r2 = "@NotNull method %s.%s must not return null"
        L_0x000b:
            r3 = 3
            r4 = 2
            if (r9 == r1) goto L_0x0013
            if (r9 == r0) goto L_0x0013
            r5 = r3
            goto L_0x0014
        L_0x0013:
            r5 = r4
        L_0x0014:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType"
            r7 = 0
            if (r9 == r1) goto L_0x0030
            if (r9 == r4) goto L_0x002b
            if (r9 == r3) goto L_0x0026
            if (r9 == r0) goto L_0x0030
            java.lang.String r8 = "newAttributes"
            r5[r7] = r8
            goto L_0x0032
        L_0x0026:
            java.lang.String r8 = "kotlinTypeRefiner"
            r5[r7] = r8
            goto L_0x0032
        L_0x002b:
            java.lang.String r8 = "delegate"
            r5[r7] = r8
            goto L_0x0032
        L_0x0030:
            r5[r7] = r6
        L_0x0032:
            java.lang.String r7 = "refine"
            if (r9 == r1) goto L_0x003e
            if (r9 == r0) goto L_0x003b
            r5[r1] = r6
            goto L_0x0042
        L_0x003b:
            r5[r1] = r7
            goto L_0x0042
        L_0x003e:
            java.lang.String r6 = "toString"
            r5[r1] = r6
        L_0x0042:
            if (r9 == r1) goto L_0x0056
            if (r9 == r4) goto L_0x0052
            if (r9 == r3) goto L_0x004f
            if (r9 == r0) goto L_0x0056
            java.lang.String r3 = "replaceAttributes"
            r5[r4] = r3
            goto L_0x0056
        L_0x004f:
            r5[r4] = r7
            goto L_0x0056
        L_0x0052:
            java.lang.String r3 = "replaceDelegate"
            r5[r4] = r3
        L_0x0056:
            java.lang.String r2 = java.lang.String.format(r2, r5)
            if (r9 == r1) goto L_0x0064
            if (r9 == r0) goto L_0x0064
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r2)
            goto L_0x0069
        L_0x0064:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r2)
        L_0x0069:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: Hf.Z.G0(int):void");
    }

    public final /* bridge */ /* synthetic */ c0 A0(I i2) {
        C0(i2);
        throw null;
    }

    public final B B0(boolean z) {
        throw new IllegalStateException(this.e);
    }

    public final B C0(I i2) {
        if (i2 == null) {
            G0(0);
            throw null;
        }
        throw new IllegalStateException(this.e);
    }

    public final B D0() {
        throw new IllegalStateException(this.e);
    }

    public final B E0(f fVar) {
        if (fVar != null) {
            return this;
        }
        G0(3);
        throw null;
    }

    public final C0765n F0(B b) {
        throw new IllegalStateException(this.e);
    }

    public final String toString() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        G0(1);
        throw null;
    }

    public final C0774x w0(f fVar) {
        if (fVar != null) {
            return this;
        }
        G0(3);
        throw null;
    }

    public final /* bridge */ /* synthetic */ c0 y0(boolean z) {
        B0(z);
        throw null;
    }

    public final c0 z0(f fVar) {
        if (fVar != null) {
            return this;
        }
        G0(3);
        throw null;
    }
}
