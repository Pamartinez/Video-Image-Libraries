package tf;

/* renamed from: tf.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1310n {

    /* renamed from: c  reason: collision with root package name */
    public static final C1310n f5140c = new C1310n(C1309m.OVERRIDABLE, "SUCCESS");

    /* renamed from: a  reason: collision with root package name */
    public final C1309m f5141a;
    public final String b;

    public C1310n(C1309m mVar, String str) {
        if (mVar != null) {
            this.f5141a = mVar;
            this.b = str;
            return;
        }
        a(3);
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void a(int r10) {
        /*
            r0 = 4
            r1 = 3
            r2 = 2
            r3 = 1
            if (r10 == r3) goto L_0x000f
            if (r10 == r2) goto L_0x000f
            if (r10 == r1) goto L_0x000f
            if (r10 == r0) goto L_0x000f
            java.lang.String r4 = "@NotNull method %s.%s must not return null"
            goto L_0x0011
        L_0x000f:
            java.lang.String r4 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        L_0x0011:
            if (r10 == r3) goto L_0x001b
            if (r10 == r2) goto L_0x001b
            if (r10 == r1) goto L_0x001b
            if (r10 == r0) goto L_0x001b
            r5 = r2
            goto L_0x001c
        L_0x001b:
            r5 = r1
        L_0x001c:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "success"
            java.lang.String r7 = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo"
            r8 = 0
            if (r10 == r3) goto L_0x0031
            if (r10 == r2) goto L_0x0031
            if (r10 == r1) goto L_0x002e
            if (r10 == r0) goto L_0x0031
            r5[r8] = r7
            goto L_0x0035
        L_0x002e:
            r5[r8] = r6
            goto L_0x0035
        L_0x0031:
            java.lang.String r9 = "debugMessage"
            r5[r8] = r9
        L_0x0035:
            switch(r10) {
                case 1: goto L_0x0045;
                case 2: goto L_0x0045;
                case 3: goto L_0x0045;
                case 4: goto L_0x0045;
                case 5: goto L_0x0040;
                case 6: goto L_0x003b;
                default: goto L_0x0038;
            }
        L_0x0038:
            r5[r3] = r6
            goto L_0x0047
        L_0x003b:
            java.lang.String r6 = "getDebugMessage"
            r5[r3] = r6
            goto L_0x0047
        L_0x0040:
            java.lang.String r6 = "getResult"
            r5[r3] = r6
            goto L_0x0047
        L_0x0045:
            r5[r3] = r7
        L_0x0047:
            if (r10 == r3) goto L_0x005a
            if (r10 == r2) goto L_0x0055
            if (r10 == r1) goto L_0x0050
            if (r10 == r0) goto L_0x0050
            goto L_0x005e
        L_0x0050:
            java.lang.String r6 = "<init>"
            r5[r2] = r6
            goto L_0x005e
        L_0x0055:
            java.lang.String r6 = "conflict"
            r5[r2] = r6
            goto L_0x005e
        L_0x005a:
            java.lang.String r6 = "incompatible"
            r5[r2] = r6
        L_0x005e:
            java.lang.String r4 = java.lang.String.format(r4, r5)
            if (r10 == r3) goto L_0x0070
            if (r10 == r2) goto L_0x0070
            if (r10 == r1) goto L_0x0070
            if (r10 == r0) goto L_0x0070
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r4)
            goto L_0x0075
        L_0x0070:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>(r4)
        L_0x0075:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: tf.C1310n.a(int):void");
    }

    public static C1310n c(String str) {
        return new C1310n(C1309m.INCOMPATIBLE, str);
    }

    public final C1309m b() {
        C1309m mVar = this.f5141a;
        if (mVar != null) {
            return mVar;
        }
        a(5);
        throw null;
    }

    public final String toString() {
        return this.f5141a + ": " + this.b;
    }
}
