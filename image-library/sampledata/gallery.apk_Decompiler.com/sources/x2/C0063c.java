package X2;

/* renamed from: X2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0063c extends C0061a {
    public final /* synthetic */ int f;

    public /* synthetic */ C0063c(int i2) {
        this.f = i2;
    }

    /* JADX WARNING: type inference failed for: r8v1, types: [X2.q, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v33, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X2.r e(D0.e r32) {
        /*
            r31 = this;
            r0 = r31
            int r0 = r0.f
            switch(r0) {
                case 0: goto L_0x0120;
                case 1: goto L_0x003e;
                default: goto L_0x0007;
            }
        L_0x0007:
            r0 = r32
            java.lang.Object r0 = r0.e
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "MEBKM:"
            boolean r1 = r0.startsWith(r1)
            r2 = 0
            if (r1 != 0) goto L_0x0017
            goto L_0x003d
        L_0x0017:
            java.lang.String r1 = "TITLE:"
            r3 = 59
            r4 = 1
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r4)
            r5 = 0
            if (r1 != 0) goto L_0x0025
            r1 = r2
            goto L_0x0027
        L_0x0025:
            r1 = r1[r5]
        L_0x0027:
            java.lang.String r6 = "URL:"
            java.lang.String[] r0 = X2.u.b(r6, r0, r3, r4)
            if (r0 != 0) goto L_0x0030
            goto L_0x003d
        L_0x0030:
            r0 = r0[r5]
            boolean r3 = X2.z.h(r0)
            if (r3 == 0) goto L_0x003d
            X2.y r2 = new X2.y
            r2.<init>(r0, r1)
        L_0x003d:
            return r2
        L_0x003e:
            r0 = r32
            java.lang.String r0 = X2.u.a(r0)
            java.lang.String r1 = "BIZCARD:"
            boolean r1 = r0.startsWith(r1)
            r2 = 0
            if (r1 != 0) goto L_0x004f
            goto L_0x011f
        L_0x004f:
            java.lang.String r1 = "N:"
            r3 = 59
            r4 = 1
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r4)
            r5 = 0
            if (r1 != 0) goto L_0x005d
            r1 = r2
            goto L_0x005f
        L_0x005d:
            r1 = r1[r5]
        L_0x005f:
            java.lang.String r6 = "X:"
            java.lang.String[] r6 = X2.u.b(r6, r0, r3, r4)
            if (r6 != 0) goto L_0x0069
            r6 = r2
            goto L_0x006b
        L_0x0069:
            r6 = r6[r5]
        L_0x006b:
            if (r1 != 0) goto L_0x006f
            r1 = r6
            goto L_0x0078
        L_0x006f:
            if (r6 != 0) goto L_0x0072
            goto L_0x0078
        L_0x0072:
            java.lang.String r7 = " "
            java.lang.String r1 = i.C0212a.B(r1, r7, r6)
        L_0x0078:
            java.lang.String r6 = "T:"
            java.lang.String[] r6 = X2.u.b(r6, r0, r3, r4)
            if (r6 != 0) goto L_0x0083
            r24 = r2
            goto L_0x0087
        L_0x0083:
            r6 = r6[r5]
            r24 = r6
        L_0x0087:
            java.lang.String r6 = "C:"
            java.lang.String[] r6 = X2.u.b(r6, r0, r3, r4)
            if (r6 != 0) goto L_0x0092
            r21 = r2
            goto L_0x0096
        L_0x0092:
            r6 = r6[r5]
            r21 = r6
        L_0x0096:
            java.lang.String r6 = "A:"
            java.lang.String[] r19 = X2.u.b(r6, r0, r3, r4)
            java.lang.String r6 = "B:"
            java.lang.String[] r6 = X2.u.b(r6, r0, r3, r4)
            if (r6 != 0) goto L_0x00a6
            r6 = r2
            goto L_0x00a8
        L_0x00a6:
            r6 = r6[r5]
        L_0x00a8:
            java.lang.String r7 = "M:"
            java.lang.String[] r7 = X2.u.b(r7, r0, r3, r4)
            if (r7 != 0) goto L_0x00b2
            r7 = r2
            goto L_0x00b4
        L_0x00b2:
            r7 = r7[r5]
        L_0x00b4:
            java.lang.String r8 = "F:"
            java.lang.String[] r8 = X2.u.b(r8, r0, r3, r4)
            if (r8 != 0) goto L_0x00be
            r8 = r2
            goto L_0x00c0
        L_0x00be:
            r8 = r8[r5]
        L_0x00c0:
            java.lang.String r9 = "E:"
            java.lang.String[] r0 = X2.u.b(r9, r0, r3, r4)
            if (r0 != 0) goto L_0x00ca
            r0 = r2
            goto L_0x00cc
        L_0x00ca:
            r0 = r0[r5]
        L_0x00cc:
            X2.d r3 = new X2.d
            java.lang.String[] r4 = X2.u.d(r1)
            java.lang.String[] r10 = X2.u.d(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r5 = 3
            r1.<init>(r5)
            if (r6 == 0) goto L_0x00e1
            r1.add(r6)
        L_0x00e1:
            if (r7 == 0) goto L_0x00e6
            r1.add(r7)
        L_0x00e6:
            if (r8 == 0) goto L_0x00eb
            r1.add(r8)
        L_0x00eb:
            int r5 = r1.size()
            if (r5 != 0) goto L_0x00f3
        L_0x00f1:
            r13 = r2
            goto L_0x00fd
        L_0x00f3:
            java.lang.String[] r2 = new java.lang.String[r5]
            java.lang.Object[] r1 = r1.toArray(r2)
            r2 = r1
            java.lang.String[] r2 = (java.lang.String[]) r2
            goto L_0x00f1
        L_0x00fd:
            java.lang.String[] r15 = X2.u.d(r0)
            r27 = 0
            r28 = 0
            r9 = 0
            r11 = 0
            r12 = 0
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r20 = 0
            r22 = 0
            r23 = 0
            r25 = 0
            r26 = 0
            r7 = r3
            r8 = r4
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            r2 = r7
        L_0x011f:
            return r2
        L_0x0120:
            r0 = r32
            java.lang.String r0 = X2.u.a(r0)
            java.lang.String r1 = "MECARD:"
            boolean r1 = r0.startsWith(r1)
            r2 = 0
            if (r1 != 0) goto L_0x0131
            goto L_0x0226
        L_0x0131:
            java.lang.String r1 = "N:"
            r3 = 59
            r4 = 1
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r4)
            if (r1 != 0) goto L_0x013e
            goto L_0x0226
        L_0x013e:
            r5 = 0
            r6 = r1[r5]
            r7 = 44
            int r8 = r6.indexOf(r7)
            if (r8 < 0) goto L_0x0159
            int r9 = r8 + 1
            java.lang.String r9 = r6.substring(r9)
            java.lang.String r6 = r6.substring(r5, r8)
            java.lang.String r8 = " "
            java.lang.String r6 = i.C0212a.B(r9, r8, r6)
        L_0x0159:
            r1 = r1[r5]
            int r7 = r1.indexOf(r7)
            X2.q r8 = new X2.q
            r8.<init>()
            if (r7 < 0) goto L_0x0174
            java.lang.String r9 = r1.substring(r5, r7)
            int r7 = r7 + r4
            java.lang.String r1 = r1.substring(r7)
            r8.f934a = r9
            r8.b = r1
            goto L_0x0176
        L_0x0174:
            r8.f934a = r1
        L_0x0176:
            java.lang.String r1 = "SOUND:"
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r4)
            if (r1 != 0) goto L_0x0180
            r14 = r2
            goto L_0x0183
        L_0x0180:
            r1 = r1[r5]
            r14 = r1
        L_0x0183:
            java.lang.String r1 = "TEL:"
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r4)
            java.lang.String r7 = "TEL-AV:"
            java.lang.String[] r7 = X2.u.b(r7, r0, r3, r4)
            if (r1 != 0) goto L_0x0193
            r15 = r7
            goto L_0x01a6
        L_0x0193:
            if (r7 != 0) goto L_0x0197
            r15 = r1
            goto L_0x01a6
        L_0x0197:
            int r9 = r1.length
            int r10 = r7.length
            int r9 = r9 + r10
            java.lang.String[] r9 = new java.lang.String[r9]
            int r10 = r1.length
            java.lang.System.arraycopy(r1, r5, r9, r5, r10)
            int r1 = r1.length
            int r10 = r7.length
            java.lang.System.arraycopy(r7, r5, r9, r1, r10)
            r15 = r9
        L_0x01a6:
            java.lang.String r1 = "EMAIL:"
            java.lang.String[] r17 = X2.u.b(r1, r0, r3, r4)
            java.lang.String r1 = "NOTE:"
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r5)
            if (r1 != 0) goto L_0x01b7
            r20 = r2
            goto L_0x01bb
        L_0x01b7:
            r1 = r1[r5]
            r20 = r1
        L_0x01bb:
            java.lang.String r1 = "ADR:"
            java.lang.String[] r21 = X2.u.b(r1, r0, r3, r4)
            java.lang.String r1 = "BDAY:"
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r4)
            if (r1 != 0) goto L_0x01cb
            r1 = r2
            goto L_0x01cd
        L_0x01cb:
            r1 = r1[r5]
        L_0x01cd:
            if (r1 == 0) goto L_0x01e6
            int r7 = r1.length()
            r9 = 8
            if (r9 != r7) goto L_0x01e6
            java.util.regex.Pattern r7 = X2.u.b
            java.util.regex.Matcher r7 = r7.matcher(r1)
            boolean r7 = r7.matches()
            if (r7 == 0) goto L_0x01e6
            r24 = r1
            goto L_0x01e8
        L_0x01e6:
            r24 = r2
        L_0x01e8:
            java.lang.String r1 = "URL:"
            java.lang.String[] r27 = X2.u.b(r1, r0, r3, r4)
            java.lang.String r1 = "ORG:"
            java.lang.String[] r1 = X2.u.b(r1, r0, r3, r4)
            if (r1 != 0) goto L_0x01f9
        L_0x01f6:
            r23 = r2
            goto L_0x01fc
        L_0x01f9:
            r2 = r1[r5]
            goto L_0x01f6
        L_0x01fc:
            java.lang.String r1 = "NICKNAME:"
            java.lang.String[] r13 = X2.u.b(r1, r0, r3, r4)
            X2.d r9 = new X2.d
            java.lang.String[] r10 = X2.u.d(r6)
            X2.q[] r11 = new X2.q[]{r8}
            java.lang.String[] r12 = X2.u.d(r6)
            r29 = 0
            r30 = 0
            r16 = 0
            r18 = 0
            r19 = 0
            r22 = 0
            r25 = 0
            r26 = 0
            r28 = 0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
            r2 = r9
        L_0x0226:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X2.C0063c.e(D0.e):X2.r");
    }
}
