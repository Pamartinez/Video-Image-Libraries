package H0;

import D0.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public static final e f312a = e.S("ty", "d");

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x033e, code lost:
        r7 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x057f, code lost:
        r7 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x072c, code lost:
        if (r0.h() == false) goto L_0x0732;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x072e, code lost:
        r0.s();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x0732, code lost:
        r0.g();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:410:0x0735, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0105, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0172, code lost:
        r7 = r17;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static E0.b a(I0.d r33, x0.C0332j r34) {
        /*
            r0 = r33
            r1 = r34
            r2 = 100
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.c()
            r3 = 2
            r4 = r3
        L_0x000f:
            boolean r5 = r0.h()
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x0032
            D0.e r5 = f312a
            int r5 = r0.q(r5)
            if (r5 == 0) goto L_0x002d
            if (r5 == r6) goto L_0x0028
            r0.r()
            r0.s()
            goto L_0x000f
        L_0x0028:
            int r4 = r0.l()
            goto L_0x000f
        L_0x002d:
            java.lang.String r5 = r0.m()
            goto L_0x0033
        L_0x0032:
            r5 = r7
        L_0x0033:
            if (r5 != 0) goto L_0x0036
            return r7
        L_0x0036:
            int r8 = r5.hashCode()
            r10 = 5
            r11 = 4
            r12 = 3
            r13 = 0
            switch(r8) {
                case 3239: goto L_0x00e6;
                case 3270: goto L_0x00da;
                case 3295: goto L_0x00ce;
                case 3307: goto L_0x00c2;
                case 3308: goto L_0x00b7;
                case 3488: goto L_0x00ac;
                case 3633: goto L_0x00a1;
                case 3634: goto L_0x0096;
                case 3646: goto L_0x0089;
                case 3669: goto L_0x007c;
                case 3679: goto L_0x006e;
                case 3681: goto L_0x0060;
                case 3705: goto L_0x0052;
                case 3710: goto L_0x0044;
                default: goto L_0x0041;
            }
        L_0x0041:
            r8 = -1
            goto L_0x00f1
        L_0x0044:
            java.lang.String r8 = "tr"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x004e
            goto L_0x0041
        L_0x004e:
            r8 = 13
            goto L_0x00f1
        L_0x0052:
            java.lang.String r8 = "tm"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x005c
            goto L_0x0041
        L_0x005c:
            r8 = 12
            goto L_0x00f1
        L_0x0060:
            java.lang.String r8 = "st"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x006a
            goto L_0x0041
        L_0x006a:
            r8 = 11
            goto L_0x00f1
        L_0x006e:
            java.lang.String r8 = "sr"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x0078
            goto L_0x0041
        L_0x0078:
            r8 = 10
            goto L_0x00f1
        L_0x007c:
            java.lang.String r8 = "sh"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x0085
            goto L_0x0041
        L_0x0085:
            r8 = 9
            goto L_0x00f1
        L_0x0089:
            java.lang.String r8 = "rp"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x0092
            goto L_0x0041
        L_0x0092:
            r8 = 8
            goto L_0x00f1
        L_0x0096:
            java.lang.String r8 = "rd"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x009f
            goto L_0x0041
        L_0x009f:
            r8 = 7
            goto L_0x00f1
        L_0x00a1:
            java.lang.String r8 = "rc"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x00aa
            goto L_0x0041
        L_0x00aa:
            r8 = 6
            goto L_0x00f1
        L_0x00ac:
            java.lang.String r8 = "mm"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x00b5
            goto L_0x0041
        L_0x00b5:
            r8 = r10
            goto L_0x00f1
        L_0x00b7:
            java.lang.String r8 = "gs"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x00c0
            goto L_0x0041
        L_0x00c0:
            r8 = r11
            goto L_0x00f1
        L_0x00c2:
            java.lang.String r8 = "gr"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x00cc
            goto L_0x0041
        L_0x00cc:
            r8 = r12
            goto L_0x00f1
        L_0x00ce:
            java.lang.String r8 = "gf"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x00d8
            goto L_0x0041
        L_0x00d8:
            r8 = r3
            goto L_0x00f1
        L_0x00da:
            java.lang.String r8 = "fl"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x00e4
            goto L_0x0041
        L_0x00e4:
            r8 = r6
            goto L_0x00f1
        L_0x00e6:
            java.lang.String r8 = "el"
            boolean r8 = r5.equals(r8)
            if (r8 != 0) goto L_0x00f0
            goto L_0x0041
        L_0x00f0:
            r8 = r13
        L_0x00f1:
            java.lang.String r14 = "o"
            java.lang.String r15 = "g"
            java.lang.String r7 = "d"
            r17 = 0
            switch(r8) {
                case 0: goto L_0x06d3;
                case 1: goto L_0x0669;
                case 2: goto L_0x05c9;
                case 3: goto L_0x0583;
                case 4: goto L_0x0455;
                case 5: goto L_0x0401;
                case 6: goto L_0x03bb;
                case 7: goto L_0x0387;
                case 8: goto L_0x0341;
                case 9: goto L_0x02fc;
                case 10: goto L_0x0280;
                case 11: goto L_0x0176;
                case 12: goto L_0x010e;
                case 13: goto L_0x0108;
                default: goto L_0x00fc;
            }
        L_0x00fc:
            java.lang.String r1 = "Unknown shape type "
            java.lang.String r1 = r1.concat(r5)
            J0.b.b(r1)
        L_0x0105:
            r7 = 0
            goto L_0x0728
        L_0x0108:
            D0.g r7 = H0.C0047c.a(r33, r34)
            goto L_0x0728
        L_0x010e:
            D0.e r2 = H0.E.f302a
            r23 = r13
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
        L_0x011c:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x016d
            D0.e r2 = H0.E.f302a
            int r2 = r0.q(r2)
            if (r2 == 0) goto L_0x0168
            if (r2 == r6) goto L_0x0163
            if (r2 == r3) goto L_0x015e
            if (r2 == r12) goto L_0x0159
            if (r2 == r11) goto L_0x013d
            if (r2 == r10) goto L_0x0138
            r0.s()
            goto L_0x011c
        L_0x0138:
            boolean r23 = r0.i()
            goto L_0x011c
        L_0x013d:
            int r2 = r0.l()
            if (r2 == r6) goto L_0x0156
            if (r2 != r3) goto L_0x014a
            E0.y r2 = E0.y.INDIVIDUALLY
        L_0x0147:
            r19 = r2
            goto L_0x011c
        L_0x014a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unknown trim path type "
            java.lang.String r1 = c0.C0086a.i(r2, r1)
            r0.<init>(r1)
            throw r0
        L_0x0156:
            E0.y r2 = E0.y.SIMULTANEOUSLY
            goto L_0x0147
        L_0x0159:
            java.lang.String r18 = r0.m()
            goto L_0x011c
        L_0x015e:
            D0.b r22 = Gd.a.V(r0, r1, r13)
            goto L_0x011c
        L_0x0163:
            D0.b r21 = Gd.a.V(r0, r1, r13)
            goto L_0x011c
        L_0x0168:
            D0.b r20 = Gd.a.V(r0, r1, r13)
            goto L_0x011c
        L_0x016d:
            E0.o r17 = new E0.o
            r17.<init>(r18, r19, r20, r21, r22, r23)
        L_0x0172:
            r7 = r17
            goto L_0x0728
        L_0x0176:
            D0.e r4 = H0.D.f301a
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r28 = r13
            r27 = r17
            r5 = 0
            r8 = 0
            r10 = 0
            r19 = 0
            r20 = 0
            r22 = 0
            r24 = 0
        L_0x018c:
            boolean r11 = r0.h()
            if (r11 == 0) goto L_0x0257
            D0.e r11 = H0.D.f301a
            int r11 = r0.q(r11)
            switch(r11) {
                case 0: goto L_0x0251;
                case 1: goto L_0x024b;
                case 2: goto L_0x0245;
                case 3: goto L_0x023f;
                case 4: goto L_0x0232;
                case 5: goto L_0x0225;
                case 6: goto L_0x021c;
                case 7: goto L_0x0216;
                case 8: goto L_0x019f;
                default: goto L_0x019b;
            }
        L_0x019b:
            r0.s()
            goto L_0x018c
        L_0x019f:
            r0.a()
        L_0x01a2:
            boolean r11 = r0.h()
            if (r11 == 0) goto L_0x0202
            r0.c()
            r11 = 0
            r12 = 0
        L_0x01ad:
            boolean r17 = r0.h()
            if (r17 == 0) goto L_0x01ce
            D0.e r9 = H0.D.b
            int r9 = r0.q(r9)
            if (r9 == 0) goto L_0x01c9
            if (r9 == r6) goto L_0x01c4
            r0.r()
            r0.s()
            goto L_0x01ad
        L_0x01c4:
            D0.b r12 = Gd.a.V(r0, r1, r6)
            goto L_0x01ad
        L_0x01c9:
            java.lang.String r11 = r0.m()
            goto L_0x01ad
        L_0x01ce:
            r0.g()
            r11.getClass()
            int r9 = r11.hashCode()
            switch(r9) {
                case 100: goto L_0x01ef;
                case 103: goto L_0x01e6;
                case 111: goto L_0x01dd;
                default: goto L_0x01db;
            }
        L_0x01db:
            r9 = -1
            goto L_0x01f7
        L_0x01dd:
            boolean r9 = r11.equals(r14)
            if (r9 != 0) goto L_0x01e4
            goto L_0x01db
        L_0x01e4:
            r9 = r3
            goto L_0x01f7
        L_0x01e6:
            boolean r9 = r11.equals(r15)
            if (r9 != 0) goto L_0x01ed
            goto L_0x01db
        L_0x01ed:
            r9 = r6
            goto L_0x01f7
        L_0x01ef:
            boolean r9 = r11.equals(r7)
            if (r9 != 0) goto L_0x01f6
            goto L_0x01db
        L_0x01f6:
            r9 = r13
        L_0x01f7:
            switch(r9) {
                case 0: goto L_0x01fe;
                case 1: goto L_0x01fe;
                case 2: goto L_0x01fb;
                default: goto L_0x01fa;
            }
        L_0x01fa:
            goto L_0x01a2
        L_0x01fb:
            r20 = r12
            goto L_0x01a2
        L_0x01fe:
            r4.add(r12)
            goto L_0x01a2
        L_0x0202:
            r0.f()
            int r9 = r4.size()
            if (r9 != r6) goto L_0x018c
            java.lang.Object r9 = r4.get(r13)
            D0.b r9 = (D0.b) r9
            r4.add(r9)
            goto L_0x018c
        L_0x0216:
            boolean r28 = r0.i()
            goto L_0x018c
        L_0x021c:
            double r11 = r0.j()
            float r9 = (float) r11
            r27 = r9
            goto L_0x018c
        L_0x0225:
            E0.w[] r9 = E0.w.values()
            int r10 = r0.l()
            int r10 = r10 - r6
            r10 = r9[r10]
            goto L_0x018c
        L_0x0232:
            E0.v[] r8 = E0.v.values()
            int r9 = r0.l()
            int r9 = r9 - r6
            r8 = r8[r9]
            goto L_0x018c
        L_0x023f:
            D0.a r5 = Gd.a.X(r33, r34)
            goto L_0x018c
        L_0x0245:
            D0.b r24 = Gd.a.V(r0, r1, r6)
            goto L_0x018c
        L_0x024b:
            D0.a r22 = Gd.a.U(r33, r34)
            goto L_0x018c
        L_0x0251:
            java.lang.String r19 = r0.m()
            goto L_0x018c
        L_0x0257:
            if (r5 != 0) goto L_0x0267
            D0.a r5 = new D0.a
            K0.a r1 = new K0.a
            r1.<init>(r2)
            java.util.List r1 = java.util.Collections.singletonList(r1)
            r5.<init>(r3, r1)
        L_0x0267:
            r23 = r5
            if (r8 != 0) goto L_0x026d
            E0.v r8 = E0.v.BUTT
        L_0x026d:
            r25 = r8
            if (r10 != 0) goto L_0x0273
            E0.w r10 = E0.w.MITER
        L_0x0273:
            r26 = r10
            E0.x r18 = new E0.x
            r21 = r4
            r18.<init>(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            r7 = r18
            goto L_0x0728
        L_0x0280:
            D0.e r2 = H0.v.f331a
            if (r4 != r12) goto L_0x0286
            r2 = r6
            goto L_0x0287
        L_0x0286:
            r2 = r13
        L_0x0287:
            r28 = r2
            r27 = r13
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
        L_0x029d:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x02f5
            D0.e r2 = H0.v.f331a
            int r2 = r0.q(r2)
            switch(r2) {
                case 0: goto L_0x02f0;
                case 1: goto L_0x02e7;
                case 2: goto L_0x02e2;
                case 3: goto L_0x02dd;
                case 4: goto L_0x02d8;
                case 5: goto L_0x02d3;
                case 6: goto L_0x02ce;
                case 7: goto L_0x02c9;
                case 8: goto L_0x02c4;
                case 9: goto L_0x02bf;
                case 10: goto L_0x02b3;
                default: goto L_0x02ac;
            }
        L_0x02ac:
            r0.r()
            r0.s()
            goto L_0x029d
        L_0x02b3:
            int r2 = r0.l()
            if (r2 != r12) goto L_0x02bc
            r28 = r6
            goto L_0x029d
        L_0x02bc:
            r28 = r13
            goto L_0x029d
        L_0x02bf:
            boolean r27 = r0.i()
            goto L_0x029d
        L_0x02c4:
            D0.b r25 = Gd.a.V(r0, r1, r13)
            goto L_0x029d
        L_0x02c9:
            D0.b r23 = Gd.a.V(r0, r1, r6)
            goto L_0x029d
        L_0x02ce:
            D0.b r26 = Gd.a.V(r0, r1, r13)
            goto L_0x029d
        L_0x02d3:
            D0.b r24 = Gd.a.V(r0, r1, r6)
            goto L_0x029d
        L_0x02d8:
            D0.b r22 = Gd.a.V(r0, r1, r13)
            goto L_0x029d
        L_0x02dd:
            D0.h r21 = H0.C0045a.b(r33, r34)
            goto L_0x029d
        L_0x02e2:
            D0.b r20 = Gd.a.V(r0, r1, r13)
            goto L_0x029d
        L_0x02e7:
            int r2 = r0.l()
            E0.m r19 = E0.m.a(r2)
            goto L_0x029d
        L_0x02f0:
            java.lang.String r18 = r0.m()
            goto L_0x029d
        L_0x02f5:
            E0.n r17 = new E0.n
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0172
        L_0x02fc:
            D0.e r2 = H0.C.f300a
            r4 = r13
            r5 = r4
            r2 = 0
            r7 = 0
        L_0x0302:
            boolean r8 = r0.h()
            if (r8 == 0) goto L_0x0339
            D0.e r8 = H0.C.f300a
            int r8 = r0.q(r8)
            if (r8 == 0) goto L_0x0334
            if (r8 == r6) goto L_0x032f
            if (r8 == r3) goto L_0x031f
            if (r8 == r12) goto L_0x031a
            r0.s()
            goto L_0x0302
        L_0x031a:
            boolean r5 = r0.i()
            goto L_0x0302
        L_0x031f:
            D0.a r2 = new D0.a
            float r8 = J0.g.c()
            H0.z r9 = H0.z.d
            java.util.ArrayList r8 = H0.q.a(r0, r1, r8, r9, r13)
            r2.<init>(r10, r8)
            goto L_0x0302
        L_0x032f:
            int r4 = r0.l()
            goto L_0x0302
        L_0x0334:
            java.lang.String r7 = r0.m()
            goto L_0x0302
        L_0x0339:
            E0.t r1 = new E0.t
            r1.<init>(r7, r4, r2, r5)
        L_0x033e:
            r7 = r1
            goto L_0x0728
        L_0x0341:
            D0.e r2 = H0.x.f333a
            r22 = r13
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
        L_0x034d:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x0380
            D0.e r2 = H0.x.f333a
            int r2 = r0.q(r2)
            if (r2 == 0) goto L_0x037b
            if (r2 == r6) goto L_0x0376
            if (r2 == r3) goto L_0x0371
            if (r2 == r12) goto L_0x036c
            if (r2 == r11) goto L_0x0367
            r0.s()
            goto L_0x034d
        L_0x0367:
            boolean r22 = r0.i()
            goto L_0x034d
        L_0x036c:
            D0.g r21 = H0.C0047c.a(r33, r34)
            goto L_0x034d
        L_0x0371:
            D0.b r20 = Gd.a.V(r0, r1, r13)
            goto L_0x034d
        L_0x0376:
            D0.b r19 = Gd.a.V(r0, r1, r13)
            goto L_0x034d
        L_0x037b:
            java.lang.String r18 = r0.m()
            goto L_0x034d
        L_0x0380:
            E0.o r17 = new E0.o
            r17.<init>((java.lang.String) r18, (D0.b) r19, (D0.b) r20, (D0.g) r21, (boolean) r22)
            goto L_0x0172
        L_0x0387:
            D0.e r2 = H0.y.f334a
            r2 = 0
            r4 = 0
        L_0x038b:
            boolean r5 = r0.h()
            if (r5 == 0) goto L_0x03b0
            D0.e r5 = H0.y.f334a
            int r5 = r0.q(r5)
            if (r5 == 0) goto L_0x03ab
            if (r5 == r6) goto L_0x03a6
            if (r5 == r3) goto L_0x03a1
            r0.s()
            goto L_0x038b
        L_0x03a1:
            boolean r13 = r0.i()
            goto L_0x038b
        L_0x03a6:
            D0.b r4 = Gd.a.V(r0, r1, r6)
            goto L_0x038b
        L_0x03ab:
            java.lang.String r2 = r0.m()
            goto L_0x038b
        L_0x03b0:
            if (r13 == 0) goto L_0x03b4
            goto L_0x0105
        L_0x03b4:
            E0.p r7 = new E0.p
            r7.<init>(r2, r4)
            goto L_0x0728
        L_0x03bb:
            D0.e r2 = H0.w.f332a
            r22 = r13
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
        L_0x03c7:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x03fa
            D0.e r2 = H0.w.f332a
            int r2 = r0.q(r2)
            if (r2 == 0) goto L_0x03f5
            if (r2 == r6) goto L_0x03f0
            if (r2 == r3) goto L_0x03eb
            if (r2 == r12) goto L_0x03e6
            if (r2 == r11) goto L_0x03e1
            r0.s()
            goto L_0x03c7
        L_0x03e1:
            boolean r22 = r0.i()
            goto L_0x03c7
        L_0x03e6:
            D0.b r21 = Gd.a.V(r0, r1, r6)
            goto L_0x03c7
        L_0x03eb:
            D0.a r20 = Gd.a.Y(r33, r34)
            goto L_0x03c7
        L_0x03f0:
            D0.h r19 = H0.C0045a.b(r33, r34)
            goto L_0x03c7
        L_0x03f5:
            java.lang.String r18 = r0.m()
            goto L_0x03c7
        L_0x03fa:
            E0.o r17 = new E0.o
            r17.<init>((java.lang.String) r18, (D0.h) r19, (D0.a) r20, (D0.b) r21, (boolean) r22)
            goto L_0x0172
        L_0x0401:
            D0.e r2 = H0.u.f330a
            r2 = 0
            r7 = 0
        L_0x0405:
            boolean r4 = r0.h()
            if (r4 == 0) goto L_0x0448
            D0.e r4 = H0.u.f330a
            int r4 = r0.q(r4)
            if (r4 == 0) goto L_0x0443
            if (r4 == r6) goto L_0x0423
            if (r4 == r3) goto L_0x041e
            r0.r()
            r0.s()
            goto L_0x0405
        L_0x041e:
            boolean r13 = r0.i()
            goto L_0x0405
        L_0x0423:
            int r2 = r0.l()
            if (r2 == r6) goto L_0x0440
            if (r2 == r3) goto L_0x043d
            if (r2 == r12) goto L_0x043a
            if (r2 == r11) goto L_0x0437
            if (r2 == r10) goto L_0x0434
            E0.k r2 = E0.k.MERGE
            goto L_0x0405
        L_0x0434:
            E0.k r2 = E0.k.EXCLUDE_INTERSECTIONS
            goto L_0x0405
        L_0x0437:
            E0.k r2 = E0.k.INTERSECT
            goto L_0x0405
        L_0x043a:
            E0.k r2 = E0.k.SUBTRACT
            goto L_0x0405
        L_0x043d:
            E0.k r2 = E0.k.ADD
            goto L_0x0405
        L_0x0440:
            E0.k r2 = E0.k.MERGE
            goto L_0x0405
        L_0x0443:
            java.lang.String r7 = r0.m()
            goto L_0x0405
        L_0x0448:
            E0.l r3 = new E0.l
            r3.<init>(r7, r2, r13)
            java.lang.String r2 = "Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove()."
            r1.a(r2)
            r7 = r3
            goto L_0x0728
        L_0x0455:
            D0.e r4 = H0.m.f318a
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r32 = r13
            r29 = r17
            r5 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r31 = 0
        L_0x0473:
            boolean r8 = r0.h()
            if (r8 == 0) goto L_0x0566
            D0.e r8 = H0.m.f318a
            int r8 = r0.q(r8)
            switch(r8) {
                case 0: goto L_0x0560;
                case 1: goto L_0x0536;
                case 2: goto L_0x0530;
                case 3: goto L_0x0521;
                case 4: goto L_0x051b;
                case 5: goto L_0x0515;
                case 6: goto L_0x050f;
                case 7: goto L_0x0502;
                case 8: goto L_0x04f5;
                case 9: goto L_0x04ec;
                case 10: goto L_0x04e7;
                case 11: goto L_0x0489;
                default: goto L_0x0482;
            }
        L_0x0482:
            r0.r()
            r0.s()
            goto L_0x0473
        L_0x0489:
            r0.a()
        L_0x048c:
            boolean r8 = r0.h()
            if (r8 == 0) goto L_0x04d4
            r0.c()
            r8 = 0
            r9 = 0
        L_0x0497:
            boolean r10 = r0.h()
            if (r10 == 0) goto L_0x04b8
            D0.e r10 = H0.m.f319c
            int r10 = r0.q(r10)
            if (r10 == 0) goto L_0x04b3
            if (r10 == r6) goto L_0x04ae
            r0.r()
            r0.s()
            goto L_0x0497
        L_0x04ae:
            D0.b r9 = Gd.a.V(r0, r1, r6)
            goto L_0x0497
        L_0x04b3:
            java.lang.String r8 = r0.m()
            goto L_0x0497
        L_0x04b8:
            r0.g()
            boolean r10 = r8.equals(r14)
            if (r10 == 0) goto L_0x04c4
            r31 = r9
            goto L_0x048c
        L_0x04c4:
            boolean r10 = r8.equals(r7)
            if (r10 != 0) goto L_0x04d0
            boolean r8 = r8.equals(r15)
            if (r8 == 0) goto L_0x048c
        L_0x04d0:
            r4.add(r9)
            goto L_0x048c
        L_0x04d4:
            r0.f()
            int r8 = r4.size()
            if (r8 != r6) goto L_0x0473
            java.lang.Object r8 = r4.get(r13)
            D0.b r8 = (D0.b) r8
            r4.add(r8)
            goto L_0x0473
        L_0x04e7:
            boolean r32 = r0.i()
            goto L_0x0473
        L_0x04ec:
            double r8 = r0.j()
            float r8 = (float) r8
            r29 = r8
            goto L_0x0473
        L_0x04f5:
            E0.w[] r8 = E0.w.values()
            int r9 = r0.l()
            int r9 = r9 - r6
            r28 = r8[r9]
            goto L_0x0473
        L_0x0502:
            E0.v[] r8 = E0.v.values()
            int r9 = r0.l()
            int r9 = r9 - r6
            r27 = r8[r9]
            goto L_0x0473
        L_0x050f:
            D0.b r26 = Gd.a.V(r0, r1, r6)
            goto L_0x0473
        L_0x0515:
            D0.a r25 = Gd.a.Y(r33, r34)
            goto L_0x0473
        L_0x051b:
            D0.a r24 = Gd.a.Y(r33, r34)
            goto L_0x0473
        L_0x0521:
            int r8 = r0.l()
            if (r8 != r6) goto L_0x052d
            E0.f r8 = E0.f.LINEAR
        L_0x0529:
            r21 = r8
            goto L_0x0473
        L_0x052d:
            E0.f r8 = E0.f.RADIAL
            goto L_0x0529
        L_0x0530:
            D0.a r5 = Gd.a.X(r33, r34)
            goto L_0x0473
        L_0x0536:
            r0.c()
            r8 = -1
        L_0x053a:
            boolean r9 = r0.h()
            if (r9 == 0) goto L_0x055b
            D0.e r9 = H0.m.b
            int r9 = r0.q(r9)
            if (r9 == 0) goto L_0x0556
            if (r9 == r6) goto L_0x0551
            r0.r()
            r0.s()
            goto L_0x053a
        L_0x0551:
            D0.a r22 = Gd.a.W(r0, r1, r8)
            goto L_0x053a
        L_0x0556:
            int r8 = r0.l()
            goto L_0x053a
        L_0x055b:
            r0.g()
            goto L_0x0473
        L_0x0560:
            java.lang.String r20 = r0.m()
            goto L_0x0473
        L_0x0566:
            if (r5 != 0) goto L_0x0576
            D0.a r5 = new D0.a
            K0.a r1 = new K0.a
            r1.<init>(r2)
            java.util.List r1 = java.util.Collections.singletonList(r1)
            r5.<init>(r3, r1)
        L_0x0576:
            r23 = r5
            E0.e r19 = new E0.e
            r30 = r4
            r19.<init>(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
        L_0x057f:
            r7 = r19
            goto L_0x0728
        L_0x0583:
            D0.e r2 = H0.B.f299a
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r7 = 0
        L_0x058b:
            boolean r4 = r0.h()
            if (r4 == 0) goto L_0x05c2
            D0.e r4 = H0.B.f299a
            int r4 = r0.q(r4)
            if (r4 == 0) goto L_0x05bd
            if (r4 == r6) goto L_0x05b8
            if (r4 == r3) goto L_0x05a1
            r0.s()
            goto L_0x058b
        L_0x05a1:
            r0.a()
        L_0x05a4:
            boolean r4 = r0.h()
            if (r4 == 0) goto L_0x05b4
            E0.b r4 = a(r33, r34)
            if (r4 == 0) goto L_0x05a4
            r2.add(r4)
            goto L_0x05a4
        L_0x05b4:
            r0.f()
            goto L_0x058b
        L_0x05b8:
            boolean r13 = r0.i()
            goto L_0x058b
        L_0x05bd:
            java.lang.String r7 = r0.m()
            goto L_0x058b
        L_0x05c2:
            E0.s r1 = new E0.s
            r1.<init>(r7, r2, r13)
            goto L_0x033e
        L_0x05c9:
            D0.e r4 = H0.l.f317a
            android.graphics.Path$FillType r4 = android.graphics.Path.FillType.WINDING
            r22 = r4
            r27 = r13
            r7 = 0
            r20 = 0
            r21 = 0
            r23 = 0
            r25 = 0
            r26 = 0
        L_0x05dc:
            boolean r4 = r0.h()
            if (r4 == 0) goto L_0x0650
            D0.e r4 = H0.l.f317a
            int r4 = r0.q(r4)
            switch(r4) {
                case 0: goto L_0x064b;
                case 1: goto L_0x0622;
                case 2: goto L_0x061d;
                case 3: goto L_0x060f;
                case 4: goto L_0x060a;
                case 5: goto L_0x0605;
                case 6: goto L_0x05f7;
                case 7: goto L_0x05f2;
                default: goto L_0x05eb;
            }
        L_0x05eb:
            r0.r()
            r0.s()
            goto L_0x05dc
        L_0x05f2:
            boolean r27 = r0.i()
            goto L_0x05dc
        L_0x05f7:
            int r4 = r0.l()
            if (r4 != r6) goto L_0x0602
            android.graphics.Path$FillType r4 = android.graphics.Path.FillType.WINDING
        L_0x05ff:
            r22 = r4
            goto L_0x05dc
        L_0x0602:
            android.graphics.Path$FillType r4 = android.graphics.Path.FillType.EVEN_ODD
            goto L_0x05ff
        L_0x0605:
            D0.a r26 = Gd.a.Y(r33, r34)
            goto L_0x05dc
        L_0x060a:
            D0.a r25 = Gd.a.Y(r33, r34)
            goto L_0x05dc
        L_0x060f:
            int r4 = r0.l()
            if (r4 != r6) goto L_0x061a
            E0.f r4 = E0.f.LINEAR
        L_0x0617:
            r21 = r4
            goto L_0x05dc
        L_0x061a:
            E0.f r4 = E0.f.RADIAL
            goto L_0x0617
        L_0x061d:
            D0.a r7 = Gd.a.X(r33, r34)
            goto L_0x05dc
        L_0x0622:
            r0.c()
            r4 = -1
        L_0x0626:
            boolean r5 = r0.h()
            if (r5 == 0) goto L_0x0647
            D0.e r5 = H0.l.b
            int r5 = r0.q(r5)
            if (r5 == 0) goto L_0x0642
            if (r5 == r6) goto L_0x063d
            r0.r()
            r0.s()
            goto L_0x0626
        L_0x063d:
            D0.a r23 = Gd.a.W(r0, r1, r4)
            goto L_0x0626
        L_0x0642:
            int r4 = r0.l()
            goto L_0x0626
        L_0x0647:
            r0.g()
            goto L_0x05dc
        L_0x064b:
            java.lang.String r20 = r0.m()
            goto L_0x05dc
        L_0x0650:
            if (r7 != 0) goto L_0x0660
            D0.a r7 = new D0.a
            K0.a r1 = new K0.a
            r1.<init>(r2)
            java.util.List r1 = java.util.Collections.singletonList(r1)
            r7.<init>(r3, r1)
        L_0x0660:
            r24 = r7
            E0.d r19 = new E0.d
            r19.<init>(r20, r21, r22, r23, r24, r25, r26, r27)
            goto L_0x057f
        L_0x0669:
            D0.e r4 = H0.A.f298a
            r4 = r6
            r15 = r13
            r19 = r15
            r7 = 0
            r14 = 0
            r17 = 0
        L_0x0673:
            boolean r5 = r0.h()
            if (r5 == 0) goto L_0x06b0
            D0.e r5 = H0.A.f298a
            int r5 = r0.q(r5)
            if (r5 == 0) goto L_0x06ab
            if (r5 == r6) goto L_0x06a6
            if (r5 == r3) goto L_0x06a1
            if (r5 == r12) goto L_0x069c
            if (r5 == r11) goto L_0x0697
            if (r5 == r10) goto L_0x0692
            r0.r()
            r0.s()
            goto L_0x0673
        L_0x0692:
            boolean r19 = r0.i()
            goto L_0x0673
        L_0x0697:
            int r4 = r0.l()
            goto L_0x0673
        L_0x069c:
            boolean r15 = r0.i()
            goto L_0x0673
        L_0x06a1:
            D0.a r7 = Gd.a.X(r33, r34)
            goto L_0x0673
        L_0x06a6:
            D0.a r17 = Gd.a.U(r33, r34)
            goto L_0x0673
        L_0x06ab:
            java.lang.String r14 = r0.m()
            goto L_0x0673
        L_0x06b0:
            if (r7 != 0) goto L_0x06c0
            D0.a r7 = new D0.a
            K0.a r1 = new K0.a
            r1.<init>(r2)
            java.util.List r1 = java.util.Collections.singletonList(r1)
            r7.<init>(r3, r1)
        L_0x06c0:
            r18 = r7
            if (r4 != r6) goto L_0x06c9
            android.graphics.Path$FillType r1 = android.graphics.Path.FillType.WINDING
        L_0x06c6:
            r16 = r1
            goto L_0x06cc
        L_0x06c9:
            android.graphics.Path$FillType r1 = android.graphics.Path.FillType.EVEN_ODD
            goto L_0x06c6
        L_0x06cc:
            E0.r r13 = new E0.r
            r13.<init>(r14, r15, r16, r17, r18, r19)
            r7 = r13
            goto L_0x0728
        L_0x06d3:
            D0.e r2 = H0.C0049e.f308a
            if (r4 != r12) goto L_0x06d9
            r2 = r6
            goto L_0x06da
        L_0x06d9:
            r2 = r13
        L_0x06da:
            r21 = r2
            r22 = r13
            r18 = 0
            r19 = 0
            r20 = 0
        L_0x06e4:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x0721
            D0.e r2 = H0.C0049e.f308a
            int r2 = r0.q(r2)
            if (r2 == 0) goto L_0x071c
            if (r2 == r6) goto L_0x0717
            if (r2 == r3) goto L_0x0712
            if (r2 == r12) goto L_0x070d
            if (r2 == r11) goto L_0x0701
            r0.r()
            r0.s()
            goto L_0x06e4
        L_0x0701:
            int r2 = r0.l()
            if (r2 != r12) goto L_0x070a
            r21 = r6
            goto L_0x06e4
        L_0x070a:
            r21 = r13
            goto L_0x06e4
        L_0x070d:
            boolean r22 = r0.i()
            goto L_0x06e4
        L_0x0712:
            D0.a r20 = Gd.a.Y(r33, r34)
            goto L_0x06e4
        L_0x0717:
            D0.h r19 = H0.C0045a.b(r33, r34)
            goto L_0x06e4
        L_0x071c:
            java.lang.String r18 = r0.m()
            goto L_0x06e4
        L_0x0721:
            E0.a r17 = new E0.a
            r17.<init>(r18, r19, r20, r21, r22)
            goto L_0x0172
        L_0x0728:
            boolean r1 = r0.h()
            if (r1 == 0) goto L_0x0732
            r0.s()
            goto L_0x0728
        L_0x0732:
            r0.g()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.g.a(I0.d, x0.j):E0.b");
    }
}
