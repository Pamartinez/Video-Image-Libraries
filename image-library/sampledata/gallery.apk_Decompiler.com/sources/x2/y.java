package X2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y extends r {
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f945c;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003a, code lost:
        if (X2.u.b.matcher(r4.subSequence(r0, r1)).matches() == false) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public y(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            X2.s r0 = X2.s.URI
            r3.<init>(r0)
            java.lang.String r4 = r4.trim()
            r0 = 58
            int r0 = r4.indexOf(r0)
            if (r0 < 0) goto L_0x003c
            int r0 = r0 + 1
            r1 = 47
            int r1 = r4.indexOf(r1, r0)
            if (r1 >= 0) goto L_0x001f
            int r1 = r4.length()
        L_0x001f:
            int r1 = r1 - r0
            X2.u[] r2 = X2.u.f940a
            if (r1 > 0) goto L_0x0025
            goto L_0x0042
        L_0x0025:
            int r1 = r1 + r0
            int r2 = r4.length()
            if (r2 < r1) goto L_0x0042
            java.util.regex.Pattern r2 = X2.u.b
            java.lang.CharSequence r0 = r4.subSequence(r0, r1)
            java.util.regex.Matcher r0 = r2.matcher(r0)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L_0x0042
        L_0x003c:
            java.lang.String r0 = "http://"
            java.lang.String r4 = r0.concat(r4)
        L_0x0042:
            r3.b = r4
            r3.f945c = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X2.y.<init>(java.lang.String, java.lang.String):void");
    }

    public final String a() {
        StringBuilder sb2 = new StringBuilder(30);
        r.b(sb2, this.f945c);
        r.b(sb2, this.b);
        return sb2.toString();
    }
}
