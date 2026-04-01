package H0;

import D0.e;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class s {

    /* renamed from: a  reason: collision with root package name */
    public static final e f326a = e.S("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", ShareApi.HD_1280_SIZE_IMAGE, "ao", "bm");
    public static final e b = e.S("d", "a");

    /* renamed from: c  reason: collision with root package name */
    public static final e f327c = e.S("ty", "nm");

    /* JADX WARNING: type inference failed for: r6v57, types: [H0.i, java.lang.Object] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02b4, code lost:
        r15 = r41;
        r2 = r49;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d9, code lost:
        r15 = r41;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00db, code lost:
        r14 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0499, code lost:
        r39 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x052a, code lost:
        r3 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x0575, code lost:
        r14 = r3;
        r3 = r39;
        r15 = r41;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x057a, code lost:
        r2 = r49;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x05af, code lost:
        r3 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x05c2, code lost:
        r3 = r39;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static F0.i a(I0.d r52, x0.C0332j r53) {
        /*
            r0 = r52
            r1 = r53
            r7 = 0
            java.lang.Float r2 = java.lang.Float.valueOf(r7)
            r3 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r8 = java.lang.Float.valueOf(r3)
            F0.h r4 = F0.h.NONE
            E0.h r5 = E0.h.NORMAL
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r0.c()
            java.lang.String r6 = "UNSET"
            r12 = 0
            r14 = 0
            r15 = -1
            r22 = r4
            r27 = r5
            r18 = r7
            r19 = r18
            r28 = r19
            r29 = r28
            r30 = r29
            r37 = r30
            r4 = r14
            r24 = r4
            r25 = r24
            r26 = r25
            r31 = r26
            r20 = 0
            r21 = 0
            r23 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r50 = r15
            r15 = r3
            r16 = r12
            r3 = 0
            r12 = r6
            r13 = r8
            r7 = r50
        L_0x005a:
            boolean r5 = r0.h()
            if (r5 == 0) goto L_0x063f
            D0.e r5 = f326a
            int r5 = r0.q(r5)
            r38 = -1
            r11 = 1
            switch(r5) {
                case 0: goto L_0x0632;
                case 1: goto L_0x0623;
                case 2: goto L_0x0617;
                case 3: goto L_0x05fc;
                case 4: goto L_0x05ef;
                case 5: goto L_0x05da;
                case 6: goto L_0x05c5;
                case 7: goto L_0x05b3;
                case 8: goto L_0x05a4;
                case 9: goto L_0x0552;
                case 10: goto L_0x043c;
                case 11: goto L_0x041d;
                case 12: goto L_0x02ba;
                case 13: goto L_0x00fe;
                case 14: goto L_0x00f8;
                case 15: goto L_0x00ee;
                case 16: goto L_0x00de;
                case 17: goto L_0x00ca;
                case 18: goto L_0x00c2;
                case 19: goto L_0x00ba;
                case 20: goto L_0x00b5;
                case 21: goto L_0x00b0;
                case 22: goto L_0x00ab;
                case 23: goto L_0x00a1;
                case 24: goto L_0x007b;
                default: goto L_0x006c;
            }
        L_0x006c:
            r0.r()
            r0.s()
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            goto L_0x0575
        L_0x007b:
            int r5 = r0.l()
            E0.h[] r6 = E0.h.values()
            int r6 = r6.length
            if (r5 < r6) goto L_0x009a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r11 = "Unsupported Blend Mode: "
            r6.<init>(r11)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r1.a(r5)
            E0.h r27 = E0.h.NORMAL
            goto L_0x005a
        L_0x009a:
            E0.h[] r6 = E0.h.values()
            r27 = r6[r5]
            goto L_0x005a
        L_0x00a1:
            int r4 = r0.l()
            if (r4 != r11) goto L_0x00a9
            r4 = r11
            goto L_0x005a
        L_0x00a9:
            r4 = r14
            goto L_0x005a
        L_0x00ab:
            boolean r31 = r0.i()
            goto L_0x005a
        L_0x00b0:
            java.lang.String r3 = r0.m()
            goto L_0x005a
        L_0x00b5:
            D0.b r36 = Gd.a.V(r0, r1, r14)
            goto L_0x005a
        L_0x00ba:
            double r5 = r0.j()
            float r5 = (float) r5
            r19 = r5
            goto L_0x005a
        L_0x00c2:
            double r5 = r0.j()
            float r5 = (float) r5
            r18 = r5
            goto L_0x005a
        L_0x00ca:
            double r5 = r0.j()
            float r11 = J0.g.c()
            r41 = r15
            double r14 = (double) r11
            double r5 = r5 * r14
            float r5 = (float) r5
            r29 = r5
        L_0x00d9:
            r15 = r41
        L_0x00db:
            r14 = 0
            goto L_0x005a
        L_0x00de:
            r41 = r15
            double r5 = r0.j()
            float r11 = J0.g.c()
            double r14 = (double) r11
            double r5 = r5 * r14
            float r5 = (float) r5
            r28 = r5
            goto L_0x00d9
        L_0x00ee:
            r41 = r15
            double r5 = r0.j()
            float r5 = (float) r5
            r30 = r5
            goto L_0x00db
        L_0x00f8:
            double r5 = r0.j()
            float r15 = (float) r5
            goto L_0x00db
        L_0x00fe:
            r41 = r15
            r0.a()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
        L_0x0108:
            boolean r14 = r0.h()
            if (r14 == 0) goto L_0x029e
            r0.c()
        L_0x0111:
            boolean r14 = r0.h()
            if (r14 == 0) goto L_0x0296
            D0.e r14 = f327c
            int r14 = r0.q(r14)
            if (r14 == 0) goto L_0x0133
            if (r14 == r11) goto L_0x012b
            r0.r()
            r0.s()
        L_0x0127:
            r49 = r2
            goto L_0x0291
        L_0x012b:
            java.lang.String r14 = r0.m()
            r5.add(r14)
            goto L_0x0127
        L_0x0133:
            int r14 = r0.l()
            r15 = 29
            if (r14 != r15) goto L_0x019f
            D0.e r14 = H0.C0048d.f307a
            r32 = 0
        L_0x013f:
            boolean r14 = r0.h()
            if (r14 == 0) goto L_0x0111
            D0.e r14 = H0.C0048d.f307a
            int r14 = r0.q(r14)
            if (r14 == 0) goto L_0x0154
            r0.r()
            r0.s()
            goto L_0x013f
        L_0x0154:
            r0.a()
        L_0x0157:
            boolean r14 = r0.h()
            if (r14 == 0) goto L_0x019b
            r0.c()
            r14 = 0
            r15 = 0
        L_0x0162:
            boolean r42 = r0.h()
            if (r42 == 0) goto L_0x0193
            D0.e r6 = H0.C0048d.b
            int r6 = r0.q(r6)
            if (r6 == 0) goto L_0x0189
            if (r6 == r11) goto L_0x0179
            r0.r()
            r0.s()
            goto L_0x0162
        L_0x0179:
            if (r14 == 0) goto L_0x0185
            G0.e r15 = new G0.e
            D0.b r6 = Gd.a.V(r0, r1, r11)
            r15.<init>((java.lang.Object) r6)
            goto L_0x0162
        L_0x0185:
            r0.s()
            goto L_0x0162
        L_0x0189:
            int r6 = r0.l()
            if (r6 != 0) goto L_0x0191
            r14 = r11
            goto L_0x0162
        L_0x0191:
            r14 = 0
            goto L_0x0162
        L_0x0193:
            r0.g()
            if (r15 == 0) goto L_0x0157
            r32 = r15
            goto L_0x0157
        L_0x019b:
            r0.f()
            goto L_0x013f
        L_0x019f:
            r6 = 25
            if (r14 != r6) goto L_0x0127
            H0.i r6 = new H0.i
            r6.<init>()
        L_0x01a8:
            boolean r14 = r0.h()
            if (r14 == 0) goto L_0x0265
            D0.e r14 = H0.i.f
            int r14 = r0.q(r14)
            if (r14 == 0) goto L_0x01bd
            r0.r()
            r0.s()
            goto L_0x01a8
        L_0x01bd:
            r0.a()
        L_0x01c0:
            boolean r14 = r0.h()
            if (r14 == 0) goto L_0x025f
            r0.c()
            java.lang.String r14 = ""
        L_0x01cb:
            boolean r15 = r0.h()
            if (r15 == 0) goto L_0x0259
            D0.e r15 = H0.i.g
            int r15 = r0.q(r15)
            if (r15 == 0) goto L_0x0254
            if (r15 == r11) goto L_0x01e3
            r0.r()
            r0.s()
            goto L_0x0251
        L_0x01e3:
            r14.getClass()
            int r15 = r14.hashCode()
            switch(r15) {
                case 353103893: goto L_0x021c;
                case 397447147: goto L_0x0211;
                case 1041377119: goto L_0x0206;
                case 1379387491: goto L_0x01fb;
                case 1383710113: goto L_0x01f0;
                default: goto L_0x01ed;
            }
        L_0x01ed:
            r15 = r38
            goto L_0x0226
        L_0x01f0:
            java.lang.String r15 = "Softness"
            boolean r15 = r14.equals(r15)
            if (r15 != 0) goto L_0x01f9
            goto L_0x01ed
        L_0x01f9:
            r15 = 4
            goto L_0x0226
        L_0x01fb:
            java.lang.String r15 = "Shadow Color"
            boolean r15 = r14.equals(r15)
            if (r15 != 0) goto L_0x0204
            goto L_0x01ed
        L_0x0204:
            r15 = 3
            goto L_0x0226
        L_0x0206:
            java.lang.String r15 = "Direction"
            boolean r15 = r14.equals(r15)
            if (r15 != 0) goto L_0x020f
            goto L_0x01ed
        L_0x020f:
            r15 = 2
            goto L_0x0226
        L_0x0211:
            java.lang.String r15 = "Opacity"
            boolean r15 = r14.equals(r15)
            if (r15 != 0) goto L_0x021a
            goto L_0x01ed
        L_0x021a:
            r15 = r11
            goto L_0x0226
        L_0x021c:
            java.lang.String r15 = "Distance"
            boolean r15 = r14.equals(r15)
            if (r15 != 0) goto L_0x0225
            goto L_0x01ed
        L_0x0225:
            r15 = 0
        L_0x0226:
            switch(r15) {
                case 0: goto L_0x024b;
                case 1: goto L_0x0243;
                case 2: goto L_0x023b;
                case 3: goto L_0x0234;
                case 4: goto L_0x022d;
                default: goto L_0x0229;
            }
        L_0x0229:
            r0.s()
            goto L_0x0251
        L_0x022d:
            D0.b r15 = Gd.a.V(r0, r1, r11)
            r6.e = r15
            goto L_0x0251
        L_0x0234:
            D0.a r15 = Gd.a.U(r52, r53)
            r6.f313a = r15
            goto L_0x0251
        L_0x023b:
            r15 = 0
            D0.b r11 = Gd.a.V(r0, r1, r15)
            r6.f314c = r11
            goto L_0x0251
        L_0x0243:
            r15 = 0
            D0.b r11 = Gd.a.V(r0, r1, r15)
            r6.b = r11
            goto L_0x0251
        L_0x024b:
            D0.b r15 = Gd.a.V(r0, r1, r11)
            r6.d = r15
        L_0x0251:
            r11 = 1
            goto L_0x01cb
        L_0x0254:
            java.lang.String r14 = r0.m()
            goto L_0x0251
        L_0x0259:
            r0.g()
            r11 = 1
            goto L_0x01c0
        L_0x025f:
            r0.f()
            r11 = 1
            goto L_0x01a8
        L_0x0265:
            D0.a r11 = r6.f313a
            if (r11 == 0) goto L_0x028d
            D0.b r14 = r6.b
            if (r14 == 0) goto L_0x028d
            D0.b r15 = r6.f314c
            if (r15 == 0) goto L_0x028d
            r49 = r2
            D0.b r2 = r6.d
            if (r2 == 0) goto L_0x028f
            D0.b r6 = r6.e
            if (r6 == 0) goto L_0x028f
            B0.a r43 = new B0.a
            r47 = r2
            r48 = r6
            r44 = r11
            r45 = r14
            r46 = r15
            r43.<init>((D0.a) r44, (Bf.a) r45, (D0.b) r46, (D0.b) r47, (Bf.a) r48)
            r33 = r43
            goto L_0x0291
        L_0x028d:
            r49 = r2
        L_0x028f:
            r33 = 0
        L_0x0291:
            r2 = r49
            r11 = 1
            goto L_0x0111
        L_0x0296:
            r49 = r2
            r0.g()
            r11 = 1
            goto L_0x0108
        L_0x029e:
            r49 = r2
            r0.f()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: "
            r2.<init>(r6)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.a(r2)
        L_0x02b4:
            r15 = r41
            r2 = r49
            goto L_0x00db
        L_0x02ba:
            r49 = r2
            r41 = r15
            r0.c()
        L_0x02c1:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x0418
            D0.e r2 = b
            int r2 = r0.q(r2)
            if (r2 == 0) goto L_0x0402
            r11 = 1
            if (r2 == r11) goto L_0x02d9
            r0.r()
            r0.s()
            goto L_0x02c1
        L_0x02d9:
            r0.a()
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x03f2
            D0.e r2 = H0.C0046b.f304a
            r0.c()
            r2 = 0
            r5 = 0
        L_0x02e9:
            boolean r6 = r0.h()
            if (r6 == 0) goto L_0x03e5
            D0.e r6 = H0.C0046b.f304a
            int r6 = r0.q(r6)
            if (r6 == 0) goto L_0x0358
            r11 = 1
            if (r6 == r11) goto L_0x0301
            r0.r()
            r0.s()
            goto L_0x02e9
        L_0x0301:
            r0.c()
            r44 = 0
            r45 = 0
            r46 = 0
            r47 = 0
            r48 = 0
        L_0x030e:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x034c
            D0.e r2 = H0.C0046b.f305c
            int r2 = r0.q(r2)
            if (r2 == 0) goto L_0x0346
            if (r2 == r11) goto L_0x033f
            r6 = 2
            if (r2 == r6) goto L_0x0339
            r6 = 3
            if (r2 == r6) goto L_0x0333
            r6 = 4
            if (r2 == r6) goto L_0x032e
            r0.r()
            r0.s()
            goto L_0x030e
        L_0x032e:
            D0.a r48 = Gd.a.X(r52, r53)
            goto L_0x030e
        L_0x0333:
            r6 = 4
            D0.b r47 = Gd.a.V(r0, r1, r11)
            goto L_0x030e
        L_0x0339:
            r6 = 4
            D0.b r46 = Gd.a.V(r0, r1, r11)
            goto L_0x030e
        L_0x033f:
            r6 = 4
            D0.a r45 = Gd.a.U(r52, r53)
        L_0x0344:
            r11 = 1
            goto L_0x030e
        L_0x0346:
            r6 = 4
            D0.a r44 = Gd.a.U(r52, r53)
            goto L_0x0344
        L_0x034c:
            r6 = 4
            r0.g()
            B0.a r43 = new B0.a
            r43.<init>((D0.a) r44, (Bf.a) r45, (D0.b) r46, (D0.b) r47, (Bf.a) r48)
            r2 = r43
            goto L_0x02e9
        L_0x0358:
            r6 = 4
            r0.c()
            r5 = 0
            r45 = 0
            r46 = 0
            r47 = 0
        L_0x0363:
            boolean r11 = r0.h()
            if (r11 == 0) goto L_0x03bb
            D0.e r11 = H0.C0046b.b
            int r11 = r0.q(r11)
            if (r11 == 0) goto L_0x03b5
            r14 = 1
            if (r11 == r14) goto L_0x03af
            r15 = 2
            if (r11 == r15) goto L_0x03a9
            r6 = 3
            if (r11 == r6) goto L_0x0382
            r0.r()
            r0.s()
        L_0x0380:
            r6 = 4
            goto L_0x0363
        L_0x0382:
            int r11 = r0.l()
            if (r11 == r14) goto L_0x039f
            if (r11 == r15) goto L_0x039e
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "Unsupported text range units: "
            r14.<init>(r15)
            r14.append(r11)
            java.lang.String r11 = r14.toString()
            r1.a(r11)
            E0.z r47 = E0.z.INDEX
            goto L_0x0380
        L_0x039e:
            r14 = 1
        L_0x039f:
            if (r11 != r14) goto L_0x03a6
            E0.z r11 = E0.z.PERCENT
        L_0x03a3:
            r47 = r11
            goto L_0x0380
        L_0x03a6:
            E0.z r11 = E0.z.INDEX
            goto L_0x03a3
        L_0x03a9:
            r6 = 3
            D0.a r46 = Gd.a.X(r52, r53)
            goto L_0x0380
        L_0x03af:
            r6 = 3
            D0.a r45 = Gd.a.X(r52, r53)
            goto L_0x0380
        L_0x03b5:
            r6 = 3
            D0.a r5 = Gd.a.X(r52, r53)
            goto L_0x0380
        L_0x03bb:
            r6 = 3
            r0.g()
            if (r5 != 0) goto L_0x03d8
            if (r45 == 0) goto L_0x03d8
            D0.a r5 = new D0.a
            K0.a r11 = new K0.a
            r40 = 0
            java.lang.Integer r14 = java.lang.Integer.valueOf(r40)
            r11.<init>(r14)
            java.util.List r11 = java.util.Collections.singletonList(r11)
            r15 = 2
            r5.<init>(r15, r11)
        L_0x03d8:
            r44 = r5
            D0.f r43 = new D0.f
            r48 = 0
            r43.<init>(r44, r45, r46, r47, r48)
            r5 = r43
            goto L_0x02e9
        L_0x03e5:
            r6 = 3
            r0.g()
            D0.e r11 = new D0.e
            r15 = 0
            r11.<init>((int) r15, (java.lang.Object) r2, (java.lang.Object) r5)
            r35 = r11
            goto L_0x03f3
        L_0x03f2:
            r6 = 3
        L_0x03f3:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x03fd
            r0.s()
            goto L_0x03f3
        L_0x03fd:
            r0.f()
            goto L_0x02c1
        L_0x0402:
            r6 = 3
            D0.a r2 = new D0.a
            float r5 = J0.g.c()
            H0.h r11 = H0.h.d
            r15 = 0
            java.util.ArrayList r5 = H0.q.a(r0, r1, r5, r11, r15)
            r11 = 6
            r2.<init>(r11, r5)
            r34 = r2
            goto L_0x02c1
        L_0x0418:
            r0.g()
            goto L_0x02b4
        L_0x041d:
            r49 = r2
            r41 = r15
            r0.a()
        L_0x0424:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x0434
            E0.b r2 = H0.g.a(r52, r53)
            if (r2 == 0) goto L_0x0424
            r9.add(r2)
            goto L_0x0424
        L_0x0434:
            r0.f()
            r39 = r3
            r3 = 0
            goto L_0x0575
        L_0x043c:
            r49 = r2
            r41 = r15
            r6 = 3
            r0.a()
        L_0x0444:
            boolean r2 = r0.h()
            if (r2 == 0) goto L_0x0542
            r0.c()
            r2 = 0
            r5 = 0
            r11 = 0
            r15 = 0
        L_0x0451:
            boolean r14 = r0.h()
            if (r14 == 0) goto L_0x052f
            java.lang.String r14 = r0.z()
            r14.getClass()
            int r39 = r14.hashCode()
            switch(r39) {
                case 111: goto L_0x0489;
                case 3588: goto L_0x047e;
                case 104433: goto L_0x0473;
                case 3357091: goto L_0x0468;
                default: goto L_0x0465;
            }
        L_0x0465:
            r6 = r38
            goto L_0x0493
        L_0x0468:
            java.lang.String r6 = "mode"
            boolean r6 = r14.equals(r6)
            if (r6 != 0) goto L_0x0471
            goto L_0x0465
        L_0x0471:
            r6 = 3
            goto L_0x0493
        L_0x0473:
            java.lang.String r6 = "inv"
            boolean r6 = r14.equals(r6)
            if (r6 != 0) goto L_0x047c
            goto L_0x0465
        L_0x047c:
            r6 = 2
            goto L_0x0493
        L_0x047e:
            java.lang.String r6 = "pt"
            boolean r6 = r14.equals(r6)
            if (r6 != 0) goto L_0x0487
            goto L_0x0465
        L_0x0487:
            r6 = 1
            goto L_0x0493
        L_0x0489:
            java.lang.String r6 = "o"
            boolean r6 = r14.equals(r6)
            if (r6 != 0) goto L_0x0492
            goto L_0x0465
        L_0x0492:
            r6 = 0
        L_0x0493:
            switch(r6) {
                case 0: goto L_0x0523;
                case 1: goto L_0x050f;
                case 2: goto L_0x0507;
                case 3: goto L_0x049e;
                default: goto L_0x0496;
            }
        L_0x0496:
            r0.s()
        L_0x0499:
            r39 = r3
        L_0x049b:
            r3 = 0
            goto L_0x052a
        L_0x049e:
            java.lang.String r2 = r0.m()
            r2.getClass()
            int r6 = r2.hashCode()
            switch(r6) {
                case 97: goto L_0x04d0;
                case 105: goto L_0x04c5;
                case 110: goto L_0x04ba;
                case 115: goto L_0x04af;
                default: goto L_0x04ac;
            }
        L_0x04ac:
            r2 = r38
            goto L_0x04da
        L_0x04af:
            java.lang.String r6 = "s"
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L_0x04b8
            goto L_0x04ac
        L_0x04b8:
            r2 = 3
            goto L_0x04da
        L_0x04ba:
            java.lang.String r6 = "n"
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L_0x04c3
            goto L_0x04ac
        L_0x04c3:
            r2 = 2
            goto L_0x04da
        L_0x04c5:
            java.lang.String r6 = "i"
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L_0x04ce
            goto L_0x04ac
        L_0x04ce:
            r2 = 1
            goto L_0x04da
        L_0x04d0:
            java.lang.String r6 = "a"
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L_0x04d9
            goto L_0x04ac
        L_0x04d9:
            r2 = 0
        L_0x04da:
            switch(r2) {
                case 0: goto L_0x0504;
                case 1: goto L_0x04fc;
                case 2: goto L_0x04f9;
                case 3: goto L_0x04f6;
                default: goto L_0x04dd;
            }
        L_0x04dd:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "Unknown mask mode "
            r2.<init>(r6)
            r2.append(r14)
            java.lang.String r6 = ". Defaulting to Add."
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            J0.b.b(r2)
            E0.i r2 = E0.i.MASK_MODE_ADD
            goto L_0x0499
        L_0x04f6:
            E0.i r2 = E0.i.MASK_MODE_SUBTRACT
            goto L_0x0499
        L_0x04f9:
            E0.i r2 = E0.i.MASK_MODE_NONE
            goto L_0x0499
        L_0x04fc:
            java.lang.String r2 = "Animation contains intersect masks. They are not supported but will be treated like add masks."
            r1.a(r2)
            E0.i r2 = E0.i.MASK_MODE_INTERSECT
            goto L_0x0499
        L_0x0504:
            E0.i r2 = E0.i.MASK_MODE_ADD
            goto L_0x0499
        L_0x0507:
            boolean r6 = r0.i()
            r39 = r3
            r15 = r6
            goto L_0x049b
        L_0x050f:
            D0.a r5 = new D0.a
            float r6 = J0.g.c()
            H0.z r14 = H0.z.d
            r39 = r3
            r3 = 0
            java.util.ArrayList r6 = H0.q.a(r0, r1, r6, r14, r3)
            r14 = 5
            r5.<init>(r14, r6)
            goto L_0x052a
        L_0x0523:
            r39 = r3
            r3 = 0
            D0.a r11 = Gd.a.X(r52, r53)
        L_0x052a:
            r3 = r39
            r6 = 3
            goto L_0x0451
        L_0x052f:
            r39 = r3
            r3 = 0
            r0.g()
            E0.j r6 = new E0.j
            r6.<init>(r2, r5, r11, r15)
            r10.add(r6)
            r3 = r39
            r6 = 3
            goto L_0x0444
        L_0x0542:
            r39 = r3
            r3 = 0
            int r2 = r10.size()
            int r5 = r1.f2061o
            int r5 = r5 + r2
            r1.f2061o = r5
            r0.f()
            goto L_0x0575
        L_0x0552:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            int r2 = r0.l()
            F0.h[] r5 = F0.h.values()
            int r5 = r5.length
            if (r2 < r5) goto L_0x057e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Unsupported matte type: "
            r5.<init>(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.a(r2)
        L_0x0575:
            r14 = r3
            r3 = r39
            r15 = r41
        L_0x057a:
            r2 = r49
            goto L_0x005a
        L_0x057e:
            F0.h[] r5 = F0.h.values()
            r22 = r5[r2]
            int[] r2 = H0.r.f325a
            int r5 = r22.ordinal()
            r2 = r2[r5]
            r11 = 1
            if (r2 == r11) goto L_0x0599
            r15 = 2
            if (r2 == r15) goto L_0x0593
            goto L_0x059e
        L_0x0593:
            java.lang.String r2 = "Unsupported matte type: Luma Inverted"
            r1.a(r2)
            goto L_0x059e
        L_0x0599:
            java.lang.String r2 = "Unsupported matte type: Luma"
            r1.a(r2)
        L_0x059e:
            int r2 = r1.f2061o
            int r2 = r2 + r11
            r1.f2061o = r2
            goto L_0x0575
        L_0x05a4:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            D0.g r20 = H0.C0047c.a(r52, r53)
        L_0x05af:
            r3 = r39
            goto L_0x005a
        L_0x05b3:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            java.lang.String r2 = r0.m()
            int r26 = android.graphics.Color.parseColor(r2)
        L_0x05c2:
            r3 = r39
            goto L_0x057a
        L_0x05c5:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            int r2 = r0.l()
            float r2 = (float) r2
            float r5 = J0.g.c()
            float r5 = r5 * r2
            int r2 = (int) r5
            r25 = r2
            goto L_0x05c2
        L_0x05da:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            int r2 = r0.l()
            float r2 = (float) r2
            float r5 = J0.g.c()
            float r5 = r5 * r2
            int r2 = (int) r5
            r24 = r2
            goto L_0x05c2
        L_0x05ef:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            int r2 = r0.l()
            long r7 = (long) r2
            goto L_0x05c2
        L_0x05fc:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            int r2 = r0.l()
            F0.g r21 = F0.g.UNKNOWN
            int r5 = r21.ordinal()
            if (r2 >= r5) goto L_0x0575
            F0.g[] r5 = F0.g.values()
            r21 = r5[r2]
            goto L_0x0575
        L_0x0617:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            java.lang.String r23 = r0.m()
            goto L_0x05af
        L_0x0623:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            int r2 = r0.l()
            long r5 = (long) r2
            r16 = r5
            goto L_0x05c2
        L_0x0632:
            r49 = r2
            r39 = r3
            r3 = r14
            r41 = r15
            java.lang.String r12 = r0.m()
            goto L_0x05af
        L_0x063f:
            r49 = r2
            r39 = r3
            r41 = r15
            r0.g()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            int r0 = (r18 > r37 ? 1 : (r18 == r37 ? 0 : -1))
            if (r0 <= 0) goto L_0x0668
            K0.a r0 = new K0.a
            r5 = 0
            java.lang.Float r6 = java.lang.Float.valueOf(r18)
            r14 = r4
            r4 = 0
            r3 = r49
            r15 = r14
            r14 = r39
            r2 = r49
            r0.<init>((x0.C0332j) r1, (java.lang.Object) r2, (java.lang.Object) r3, (android.view.animation.Interpolator) r4, (float) r5, (java.lang.Float) r6)
            r11.add(r0)
            goto L_0x066b
        L_0x0668:
            r15 = r4
            r14 = r39
        L_0x066b:
            int r0 = (r19 > r37 ? 1 : (r19 == r37 ? 0 : -1))
            if (r0 <= 0) goto L_0x0670
            goto L_0x0674
        L_0x0670:
            float r0 = r1.m
            r19 = r0
        L_0x0674:
            K0.a r0 = new K0.a
            r4 = 0
            java.lang.Float r6 = java.lang.Float.valueOf(r19)
            r3 = r13
            r2 = r13
            r5 = r18
            r0.<init>((x0.C0332j) r1, (java.lang.Object) r2, (java.lang.Object) r3, (android.view.animation.Interpolator) r4, (float) r5, (java.lang.Float) r6)
            r11.add(r0)
            K0.a r0 = new K0.a
            r1 = 2139095039(0x7f7fffff, float:3.4028235E38)
            java.lang.Float r6 = java.lang.Float.valueOf(r1)
            r3 = r49
            r1 = r53
            r5 = r19
            r2 = r49
            r0.<init>((x0.C0332j) r1, (java.lang.Object) r2, (java.lang.Object) r3, (android.view.animation.Interpolator) r4, (float) r5, (java.lang.Float) r6)
            r11.add(r0)
            java.lang.String r0 = ".ai"
            boolean r0 = r12.endsWith(r0)
            if (r0 != 0) goto L_0x06ac
            java.lang.String r0 = "ai"
            boolean r0 = r0.equals(r14)
            if (r0 == 0) goto L_0x06b1
        L_0x06ac:
            java.lang.String r0 = "Convert your Illustrator layers to shape layers."
            r1.a(r0)
        L_0x06b1:
            if (r15 == 0) goto L_0x06c0
            if (r20 != 0) goto L_0x06ba
            D0.g r20 = new D0.g
            r20.<init>()
        L_0x06ba:
            r0 = r20
            r0.f111j = r15
            r20 = r0
        L_0x06c0:
            F0.i r0 = new F0.i
            r2 = r1
            r1 = r9
            r3 = r12
            r4 = r16
            r6 = r21
            r9 = r23
            r12 = r24
            r13 = r25
            r14 = r26
            r17 = r28
            r18 = r29
            r16 = r30
            r24 = r31
            r25 = r32
            r26 = r33
            r19 = r34
            r23 = r36
            r15 = r41
            r21 = r11
            r11 = r20
            r20 = r35
            r0.<init>(r1, r2, r3, r4, r6, r7, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.s.a(I0.d, x0.j):F0.i");
    }
}
