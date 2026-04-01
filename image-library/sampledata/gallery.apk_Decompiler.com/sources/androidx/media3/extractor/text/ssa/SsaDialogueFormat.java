package androidx.media3.extractor.text.ssa;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int layerIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int i2, int i7, int i8, int i10, int i11, int i12) {
        this.layerIndex = i2;
        this.startTimeIndex = i7;
        this.endTimeIndex = i8;
        this.styleIndex = i10;
        this.textIndex = i11;
        this.length = i12;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.extractor.text.ssa.SsaDialogueFormat fromFormatLine(java.lang.String r10) {
        /*
            java.lang.String r0 = "Format:"
            boolean r0 = r10.startsWith(r0)
            androidx.media3.common.util.Assertions.checkArgument(r0)
            r0 = 7
            java.lang.String r10 = r10.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r10 = android.text.TextUtils.split(r10, r0)
            r0 = -1
            r1 = 0
            r4 = r0
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r2 = r1
        L_0x001c:
            int r3 = r10.length
            if (r2 >= r3) goto L_0x007e
            r3 = r10[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = og.k.S(r3)
            r3.getClass()
            int r9 = r3.hashCode()
            switch(r9) {
                case 100571: goto L_0x0064;
                case 3556653: goto L_0x0058;
                case 102749521: goto L_0x004d;
                case 109757538: goto L_0x0041;
                case 109780401: goto L_0x0035;
                default: goto L_0x0033;
            }
        L_0x0033:
            r3 = r0
            goto L_0x006e
        L_0x0035:
            java.lang.String r9 = "style"
            boolean r3 = r3.equals(r9)
            if (r3 != 0) goto L_0x003f
            goto L_0x0033
        L_0x003f:
            r3 = 4
            goto L_0x006e
        L_0x0041:
            java.lang.String r9 = "start"
            boolean r3 = r3.equals(r9)
            if (r3 != 0) goto L_0x004b
            goto L_0x0033
        L_0x004b:
            r3 = 3
            goto L_0x006e
        L_0x004d:
            java.lang.String r9 = "layer"
            boolean r3 = r3.equals(r9)
            if (r3 != 0) goto L_0x0056
            goto L_0x0033
        L_0x0056:
            r3 = 2
            goto L_0x006e
        L_0x0058:
            java.lang.String r9 = "text"
            boolean r3 = r3.equals(r9)
            if (r3 != 0) goto L_0x0062
            goto L_0x0033
        L_0x0062:
            r3 = 1
            goto L_0x006e
        L_0x0064:
            java.lang.String r9 = "end"
            boolean r3 = r3.equals(r9)
            if (r3 != 0) goto L_0x006d
            goto L_0x0033
        L_0x006d:
            r3 = r1
        L_0x006e:
            switch(r3) {
                case 0: goto L_0x007a;
                case 1: goto L_0x0078;
                case 2: goto L_0x0076;
                case 3: goto L_0x0074;
                case 4: goto L_0x0072;
                default: goto L_0x0071;
            }
        L_0x0071:
            goto L_0x007b
        L_0x0072:
            r7 = r2
            goto L_0x007b
        L_0x0074:
            r5 = r2
            goto L_0x007b
        L_0x0076:
            r4 = r2
            goto L_0x007b
        L_0x0078:
            r8 = r2
            goto L_0x007b
        L_0x007a:
            r6 = r2
        L_0x007b:
            int r2 = r2 + 1
            goto L_0x001c
        L_0x007e:
            if (r5 == r0) goto L_0x008b
            if (r6 == r0) goto L_0x008b
            if (r8 == r0) goto L_0x008b
            androidx.media3.extractor.text.ssa.SsaDialogueFormat r3 = new androidx.media3.extractor.text.ssa.SsaDialogueFormat
            int r9 = r10.length
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r3
        L_0x008b:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ssa.SsaDialogueFormat.fromFormatLine(java.lang.String):androidx.media3.extractor.text.ssa.SsaDialogueFormat");
    }
}
