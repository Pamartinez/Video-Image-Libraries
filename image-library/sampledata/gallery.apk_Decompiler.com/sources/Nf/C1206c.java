package nf;

import rf.r;

/* renamed from: nf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1206c extends C1207d {

    /* renamed from: c  reason: collision with root package name */
    public final r[] f4953c;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C1206c(int r5, rf.r[] r6) {
        /*
            r4 = this;
            if (r6 == 0) goto L_0x0033
            int r0 = r6.length
            r1 = 1
            int r0 = r0 - r1
            if (r0 != 0) goto L_0x0008
            goto L_0x0012
        L_0x0008:
            r2 = 31
        L_0x000a:
            if (r2 < 0) goto L_0x001b
            int r3 = r1 << r2
            r3 = r3 & r0
            if (r3 == 0) goto L_0x0018
            int r1 = r1 + r2
        L_0x0012:
            r4.<init>(r5, r1)
            r4.f4953c = r6
            return
        L_0x0018:
            int r2 = r2 + -1
            goto L_0x000a
        L_0x001b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Empty enum: "
            r5.<init>(r0)
            java.lang.Class r6 = r6.getClass()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x0033:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Argument for @NotNull parameter 'enumEntries' of kotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags$EnumLiteFlagField.bitWidth must not be null"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: nf.C1206c.<init>(int, rf.r[]):void");
    }

    public final Object c(int i2) {
        int i7 = this.f4954a;
        int i8 = (i2 & (((1 << this.b) - 1) << i7)) >> i7;
        for (r rVar : this.f4953c) {
            if (rVar.a() == i8) {
                return rVar;
            }
        }
        return null;
    }
}
