package c0;

import E2.l;
import java.io.Serializable;

/* renamed from: c0.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0087b implements l {
    public final /* synthetic */ int d;
    public final /* synthetic */ Serializable e;

    public /* synthetic */ C0087b(Serializable serializable, int i2) {
        this.d = i2;
        this.e = serializable;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean apply(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.d
            java.io.Serializable r7 = r7.e
            switch(r0) {
                case 0: goto L_0x00ad;
                default: goto L_0x0007;
            }
        L_0x0007:
            F2.U r7 = (F2.U) r7
            android.content.pm.Signature r8 = (android.content.pm.Signature) r8
            int r0 = G2.d.f289a
            G2.f r0 = G2.c.f288a
            byte[] r8 = r8.toByteArray()
            r0.getClass()
            int r1 = r8.length
            int r2 = r8.length
            r3 = 0
            He.F.p(r3, r1, r2)
            r2 = 1
            if (r1 < 0) goto L_0x0021
            r4 = r2
            goto L_0x0022
        L_0x0021:
            r4 = r3
        L_0x0022:
            java.lang.String r5 = "expectedInputSize must be >= 0 but was %s"
            He.F.g(r1, r5, r4)
            int r4 = r0.f293i
            java.security.MessageDigest r5 = r0.f292h
            boolean r0 = r0.f294j
            if (r0 == 0) goto L_0x003b
            G2.e r0 = new G2.e     // Catch:{ CloneNotSupportedException -> 0x003b }
            java.lang.Object r6 = r5.clone()     // Catch:{ CloneNotSupportedException -> 0x003b }
            java.security.MessageDigest r6 = (java.security.MessageDigest) r6     // Catch:{ CloneNotSupportedException -> 0x003b }
            r0.<init>(r6, r4)     // Catch:{ CloneNotSupportedException -> 0x003b }
            goto L_0x0048
        L_0x003b:
            G2.e r0 = new G2.e
            java.lang.String r5 = r5.getAlgorithm()
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r5)     // Catch:{ NoSuchAlgorithmException -> 0x00a6 }
            r0.<init>(r5, r4)
        L_0x0048:
            java.lang.Object r4 = r0.f291c
            java.security.MessageDigest r4 = (java.security.MessageDigest) r4
            int r5 = r8.length
            He.F.p(r3, r1, r5)
            boolean r5 = r0.b
            r5 = r5 ^ r2
            java.lang.String r6 = "Cannot re-use a Hasher after calling hash() on it"
            He.F.t(r5, r6)
            r4.update(r8, r3, r1)
            boolean r8 = r0.b
            r8 = r8 ^ r2
            He.F.t(r8, r6)
            r0.b = r2
            int r8 = r4.getDigestLength()
            int r0 = r0.f290a
            if (r0 != r8) goto L_0x0077
            byte[] r8 = r4.digest()
            char[] r0 = G2.b.d
            G2.a r0 = new G2.a
            r0.<init>(r8)
            goto L_0x0086
        L_0x0077:
            byte[] r8 = r4.digest()
            byte[] r8 = java.util.Arrays.copyOf(r8, r0)
            char[] r0 = G2.b.d
            G2.a r0 = new G2.a
            r0.<init>(r8)
        L_0x0086:
            byte[] r8 = r0.e
            java.lang.Object r8 = r8.clone()
            byte[] r8 = (byte[]) r8
            java.util.Iterator r7 = r7.iterator()
        L_0x0092:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x00a5
            java.lang.Object r0 = r7.next()
            byte[] r0 = (byte[]) r0
            boolean r0 = java.util.Arrays.equals(r0, r8)
            if (r0 == 0) goto L_0x0092
            r3 = r2
        L_0x00a5:
            return r3
        L_0x00a6:
            r7 = move-exception
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>(r7)
            throw r8
        L_0x00ad:
            java.lang.String r7 = (java.lang.String) r7
            android.media.MediaCodecInfo r8 = (android.media.MediaCodecInfo) r8
            boolean r7 = androidx.media3.transformer.EncoderUtil.isHardwareAccelerated(r8, r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.C0087b.apply(java.lang.Object):boolean");
    }
}
