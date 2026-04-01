package rf;

/* renamed from: rf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1253c implements z {
    static {
        int i2 = C1258h.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        if (r2 >= 64) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0030, code lost:
        r3 = r6.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
        if (r3 == -1) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0038, code lost:
        if ((r3 & 128) != 0) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006d, code lost:
        r2 = r2 + 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0074, code lost:
        throw rf.u.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007c, code lost:
        throw new rf.u("CodedInputStream encountered a malformed varint.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final rf.C1252b b(java.io.ByteArrayInputStream r6, rf.C1258h r7) {
        /*
            r5 = this;
            int r0 = r6.read()     // Catch:{ IOException -> 0x007d }
            r1 = -1
            if (r0 != r1) goto L_0x0009
            r5 = 0
            goto L_0x004e
        L_0x0009:
            r2 = r0 & 128(0x80, float:1.794E-43)
            if (r2 != 0) goto L_0x000e
            goto L_0x003a
        L_0x000e:
            r0 = r0 & 127(0x7f, float:1.78E-43)
            r2 = 7
        L_0x0011:
            r3 = 32
            if (r2 >= r3) goto L_0x002c
            int r3 = r6.read()     // Catch:{ IOException -> 0x007d }
            if (r3 == r1) goto L_0x0027
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r4 = r4 << r2
            r0 = r0 | r4
            r3 = r3 & 128(0x80, float:1.794E-43)
            if (r3 != 0) goto L_0x0024
            goto L_0x003a
        L_0x0024:
            int r2 = r2 + 7
            goto L_0x0011
        L_0x0027:
            rf.u r5 = rf.u.a()     // Catch:{ IOException -> 0x007d }
            throw r5     // Catch:{ IOException -> 0x007d }
        L_0x002c:
            r3 = 64
            if (r2 >= r3) goto L_0x0075
            int r3 = r6.read()     // Catch:{ IOException -> 0x007d }
            if (r3 == r1) goto L_0x0070
            r3 = r3 & 128(0x80, float:1.794E-43)
            if (r3 != 0) goto L_0x006d
        L_0x003a:
            rf.a r1 = new rf.a
            r1.<init>(r6, r0)
            rf.f r6 = new rf.f
            r6.<init>(r1)
            java.lang.Object r5 = r5.a(r6, r7)
            rf.b r5 = (rf.C1252b) r5
            r7 = 0
            r6.a(r7)     // Catch:{ u -> 0x0069 }
        L_0x004e:
            if (r5 == 0) goto L_0x0068
            boolean r6 = r5.isInitialized()
            if (r6 == 0) goto L_0x0057
            goto L_0x0068
        L_0x0057:
            Dd.a r6 = new Dd.a
            r6.<init>()
            rf.u r7 = new rf.u
            java.lang.String r6 = r6.getMessage()
            r7.<init>(r6)
            r7.d = r5
            throw r7
        L_0x0068:
            return r5
        L_0x0069:
            r6 = move-exception
            r6.d = r5
            throw r6
        L_0x006d:
            int r2 = r2 + 7
            goto L_0x002c
        L_0x0070:
            rf.u r5 = rf.u.a()     // Catch:{ IOException -> 0x007d }
            throw r5     // Catch:{ IOException -> 0x007d }
        L_0x0075:
            rf.u r5 = new rf.u     // Catch:{ IOException -> 0x007d }
            java.lang.String r6 = "CodedInputStream encountered a malformed varint."
            r5.<init>(r6)     // Catch:{ IOException -> 0x007d }
            throw r5     // Catch:{ IOException -> 0x007d }
        L_0x007d:
            r5 = move-exception
            rf.u r6 = new rf.u
            java.lang.String r5 = r5.getMessage()
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: rf.C1253c.b(java.io.ByteArrayInputStream, rf.h):rf.b");
    }
}
