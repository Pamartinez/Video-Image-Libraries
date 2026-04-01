package F0;

import A0.f;
import A0.i;
import A0.q;
import A0.s;
import C0.c;
import C0.d;
import D0.a;
import D0.b;
import D0.e;
import E0.z;
import J0.g;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import x0.C0319A;
import x0.C0332j;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends c {
    public final StringBuilder D = new StringBuilder(2);
    public final RectF E = new RectF();

    /* renamed from: F  reason: collision with root package name */
    public final Matrix f214F = new Matrix();

    /* renamed from: G  reason: collision with root package name */
    public final l f215G;

    /* renamed from: H  reason: collision with root package name */
    public final l f216H;

    /* renamed from: I  reason: collision with root package name */
    public final HashMap f217I;

    /* renamed from: J  reason: collision with root package name */
    public final LongSparseArray f218J;

    /* renamed from: K  reason: collision with root package name */
    public final ArrayList f219K;
    public final f L;

    /* renamed from: M  reason: collision with root package name */
    public final w f220M;

    /* renamed from: N  reason: collision with root package name */
    public final C0332j f221N;

    /* renamed from: O  reason: collision with root package name */
    public final z f222O;

    /* renamed from: P  reason: collision with root package name */
    public final f f223P;
    public s Q;
    public final f R;
    public s S;
    public final i T;
    public s U;
    public final i V;

    /* renamed from: W  reason: collision with root package name */
    public s f224W;

    /* renamed from: X  reason: collision with root package name */
    public final f f225X;
    public s Y;
    public s Z;
    public final f a0;
    public final f b0;

    /* renamed from: c0  reason: collision with root package name */
    public final f f226c0;

    public o(w wVar, i iVar) {
        super(wVar, iVar);
        D0.f fVar;
        D0.f fVar2;
        a aVar;
        D0.f fVar3;
        a aVar2;
        D0.f fVar4;
        a aVar3;
        B0.a aVar4;
        a aVar5;
        B0.a aVar6;
        b bVar;
        B0.a aVar7;
        b bVar2;
        B0.a aVar8;
        a aVar9;
        B0.a aVar10;
        a aVar11;
        l lVar = new l(1, 0);
        lVar.setStyle(Paint.Style.FILL);
        this.f215G = lVar;
        l lVar2 = new l(1, 1);
        lVar2.setStyle(Paint.Style.STROKE);
        this.f216H = lVar2;
        this.f217I = new HashMap();
        this.f218J = new LongSparseArray();
        this.f219K = new ArrayList();
        this.f222O = z.INDEX;
        this.f220M = wVar;
        this.f221N = iVar.b;
        f fVar5 = new f(2, (List) iVar.q.e);
        this.L = fVar5;
        fVar5.a(this);
        f(fVar5);
        e eVar = iVar.r;
        if (!(eVar == null || (aVar10 = (B0.a) eVar.e) == null || (aVar11 = (a) aVar10.d) == null)) {
            A0.e p02 = aVar11.p0();
            this.f223P = (f) p02;
            p02.a(this);
            f(p02);
        }
        if (!(eVar == null || (aVar8 = (B0.a) eVar.e) == null || (aVar9 = (a) aVar8.e) == null)) {
            A0.e p03 = aVar9.p0();
            this.R = (f) p03;
            p03.a(this);
            f(p03);
        }
        if (!(eVar == null || (aVar7 = (B0.a) eVar.e) == null || (bVar2 = (b) aVar7.f) == null)) {
            i C02 = bVar2.p0();
            this.T = C02;
            C02.a(this);
            f(C02);
        }
        if (!(eVar == null || (aVar6 = (B0.a) eVar.e) == null || (bVar = (b) aVar6.g) == null)) {
            i C03 = bVar.p0();
            this.V = C03;
            C03.a(this);
            f(C03);
        }
        if (!(eVar == null || (aVar4 = (B0.a) eVar.e) == null || (aVar5 = (a) aVar4.f34h) == null)) {
            A0.e p04 = aVar5.p0();
            this.f225X = (f) p04;
            p04.a(this);
            f(p04);
        }
        if (!(eVar == null || (fVar4 = (D0.f) eVar.f) == null || (aVar3 = (a) fVar4.e) == null)) {
            A0.e p05 = aVar3.p0();
            this.a0 = (f) p05;
            p05.a(this);
            f(p05);
        }
        if (!(eVar == null || (fVar3 = (D0.f) eVar.f) == null || (aVar2 = (a) fVar3.f) == null)) {
            A0.e p06 = aVar2.p0();
            this.b0 = (f) p06;
            p06.a(this);
            f(p06);
        }
        if (!(eVar == null || (fVar2 = (D0.f) eVar.f) == null || (aVar = (a) fVar2.g) == null)) {
            A0.e p07 = aVar.p0();
            this.f226c0 = (f) p07;
            p07.a(this);
            f(p07);
        }
        if (eVar != null && (fVar = (D0.f) eVar.f) != null) {
            this.f222O = (z) fVar.f106h;
        }
    }

    public static void s(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                Canvas canvas2 = canvas;
                canvas2.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
            }
        }
    }

    public static void t(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawPath(path, paint);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object, K0.b] */
    /* JADX WARNING: type inference failed for: r0v10, types: [java.lang.Object, C0.c] */
    public final void d(e eVar, Object obj) {
        super.d(eVar, obj);
        PointF pointF = C0319A.f2034a;
        if (obj == 1) {
            s sVar = this.Q;
            if (sVar != null) {
                n(sVar);
            }
            s sVar2 = new s(eVar, (Object) null);
            this.Q = sVar2;
            sVar2.a(this);
            f(this.Q);
        } else if (obj == 2) {
            s sVar3 = this.S;
            if (sVar3 != null) {
                n(sVar3);
            }
            s sVar4 = new s(eVar, (Object) null);
            this.S = sVar4;
            sVar4.a(this);
            f(this.S);
        } else if (obj == C0319A.n) {
            s sVar5 = this.U;
            if (sVar5 != null) {
                n(sVar5);
            }
            s sVar6 = new s(eVar, (Object) null);
            this.U = sVar6;
            sVar6.a(this);
            f(this.U);
        } else if (obj == C0319A.f2039o) {
            s sVar7 = this.f224W;
            if (sVar7 != null) {
                n(sVar7);
            }
            s sVar8 = new s(eVar, (Object) null);
            this.f224W = sVar8;
            sVar8.a(this);
            f(this.f224W);
        } else if (obj == C0319A.f2026A) {
            s sVar9 = this.Y;
            if (sVar9 != null) {
                n(sVar9);
            }
            s sVar10 = new s(eVar, (Object) null);
            this.Y = sVar10;
            sVar10.a(this);
            f(this.Y);
        } else if (obj == C0319A.f2030H) {
            s sVar11 = this.Z;
            if (sVar11 != null) {
                n(sVar11);
            }
            s sVar12 = new s(eVar, (Object) null);
            this.Z = sVar12;
            sVar12.a(this);
            f(this.Z);
        } else if (obj == C0319A.f2032J) {
            f fVar = this.L;
            fVar.getClass();
            fVar.k(new q(new Object(), eVar, new Object()));
        }
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        super.e(rectF, matrix, z);
        C0332j jVar = this.f221N;
        rectF.set(0.0f, 0.0f, (float) jVar.k.width(), (float) jVar.k.height());
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x02da  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0366  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void i(android.graphics.Canvas r28, android.graphics.Matrix r29, int r30) {
        /*
            r27 = this;
            r0 = r27
            r7 = r28
            r8 = r30
            A0.f r1 = r0.L
            java.lang.Object r1 = r1.f()
            r9 = r1
            C0.c r9 = (C0.c) r9
            x0.j r10 = r0.f221N
            java.util.HashMap r1 = r10.f
            java.lang.String r2 = r9.b
            java.lang.Object r1 = r1.get(r2)
            r3 = r1
            C0.d r3 = (C0.d) r3
            if (r3 != 0) goto L_0x001f
            return
        L_0x001f:
            java.lang.String r11 = r3.f93c
            java.lang.String r12 = r3.f92a
            r7.save()
            r28.concat(r29)
            r13 = 0
            r0.r(r9, r8, r13)
            x0.w r14 = r0.f220M
            java.util.Map r1 = r14.f2096o
            java.lang.String r2 = "\n"
            java.lang.String r4 = "\u0003"
            java.lang.String r5 = "\r"
            java.lang.String r6 = "\r\n"
            A0.i r15 = r0.V
            r17 = 1092616192(0x41200000, float:10.0)
            r18 = 1120403456(0x42c80000, float:100.0)
            F0.l r13 = r0.f215G
            r19 = r15
            F0.l r15 = r0.f216H
            r20 = r15
            if (r1 != 0) goto L_0x01fb
            x0.J r1 = r14.q
            if (r1 != 0) goto L_0x01fb
            x0.j r1 = r14.d
            androidx.collection.SparseArrayCompat r1 = r1.f2058h
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x01fb
            A0.s r1 = r0.Y
            if (r1 == 0) goto L_0x0066
            java.lang.Object r1 = r1.f()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            goto L_0x0068
        L_0x0066:
            float r1 = r9.f88c
        L_0x0068:
            float r1 = r1 / r18
            J0.g.d(r29)
            java.lang.String r15 = r9.f87a
            java.lang.String r6 = r15.replaceAll(r6, r5)
            java.lang.String r4 = r6.replaceAll(r4, r5)
            java.lang.String r2 = r4.replaceAll(r2, r5)
            java.lang.String[] r2 = r2.split(r5)
            java.util.List r15 = java.util.Arrays.asList(r2)
            int r2 = r15.size()
            int r4 = r9.e
            float r4 = (float) r4
            float r4 = r4 / r17
            A0.s r5 = r0.f224W
            if (r5 == 0) goto L_0x009d
            java.lang.Object r5 = r5.f()
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
        L_0x009a:
            float r4 = r4 + r5
        L_0x009b:
            r5 = r4
            goto L_0x00aa
        L_0x009d:
            if (r19 == 0) goto L_0x009b
            java.lang.Object r5 = r19.f()
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            goto L_0x009a
        L_0x00aa:
            r4 = 0
            r16 = -1
        L_0x00ad:
            if (r4 >= r2) goto L_0x01f8
            java.lang.Object r6 = r15.get(r4)
            java.lang.String r6 = (java.lang.String) r6
            android.graphics.PointF r0 = r9.m
            if (r0 != 0) goto L_0x00bf
            r0 = 0
        L_0x00ba:
            r17 = r4
            r4 = r1
            r1 = r6
            goto L_0x00c2
        L_0x00bf:
            float r0 = r0.x
            goto L_0x00ba
        L_0x00c2:
            r6 = 1
            r18 = r17
            r17 = r2
            r2 = r0
            r0 = r27
            java.util.List r1 = r0.x(r1, r2, r3, r4, r5, r6)
            r2 = 0
        L_0x00cf:
            int r6 = r1.size()
            if (r2 >= r6) goto L_0x01e7
            java.lang.Object r6 = r1.get(r2)
            F0.n r6 = (F0.n) r6
            r29 = r1
            int r1 = r16 + 1
            r7.save()
            r19 = r2
            float r2 = r6.b
            boolean r2 = r0.w(r7, r9, r1, r2)
            if (r2 == 0) goto L_0x01d3
            java.lang.String r2 = r6.f213a
            r16 = r1
            r6 = 0
        L_0x00f1:
            int r1 = r2.length()
            if (r6 >= r1) goto L_0x01cc
            char r1 = r2.charAt(r6)
            int r1 = C0.e.a(r1, r12, r11)
            r21 = r2
            androidx.collection.SparseArrayCompat r2 = r10.f2058h
            java.lang.Object r1 = r2.get(r1)
            C0.e r1 = (C0.e) r1
            if (r1 != 0) goto L_0x0115
            r22 = r5
            r23 = r6
            r24 = r15
            r8 = r20
            goto L_0x01be
        L_0x0115:
            r0.r(r9, r8, r6)
            java.util.HashMap r2 = r0.f217I
            boolean r22 = r2.containsKey(r1)
            if (r22 == 0) goto L_0x012d
            java.lang.Object r2 = r2.get(r1)
            java.util.List r2 = (java.util.List) r2
            r22 = r5
            r23 = r6
            r24 = r15
            goto L_0x0160
        L_0x012d:
            r22 = r5
            java.util.ArrayList r5 = r1.f94a
            r23 = r6
            int r6 = r5.size()
            r24 = r15
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>(r6)
            r8 = 0
        L_0x013f:
            if (r8 >= r6) goto L_0x015c
            java.lang.Object r25 = r5.get(r8)
            r26 = r5
            r5 = r25
            E0.s r5 = (E0.s) r5
            r25 = r6
            z0.d r6 = new z0.d
            r6.<init>(r14, r0, r5, r10)
            r15.add(r6)
            int r8 = r8 + 1
            r6 = r25
            r5 = r26
            goto L_0x013f
        L_0x015c:
            r2.put(r1, r15)
            r2 = r15
        L_0x0160:
            r5 = 0
        L_0x0161:
            int r6 = r2.size()
            if (r5 >= r6) goto L_0x01ad
            java.lang.Object r6 = r2.get(r5)
            z0.d r6 = (z0.d) r6
            android.graphics.Path r6 = r6.getPath()
            android.graphics.RectF r8 = r0.E
            r15 = 0
            r6.computeBounds(r8, r15)
            android.graphics.Matrix r8 = r0.f214F
            r8.reset()
            float r15 = r9.g
            float r15 = -r15
            float r25 = J0.g.c()
            float r15 = r15 * r25
            r25 = r2
            r2 = 0
            r8.preTranslate(r2, r15)
            r8.preScale(r4, r4)
            r6.transform(r8)
            boolean r2 = r9.k
            if (r2 == 0) goto L_0x019e
            t(r6, r13, r7)
            r8 = r20
            t(r6, r8, r7)
            goto L_0x01a6
        L_0x019e:
            r8 = r20
            t(r6, r8, r7)
            t(r6, r13, r7)
        L_0x01a6:
            int r5 = r5 + 1
            r20 = r8
            r2 = r25
            goto L_0x0161
        L_0x01ad:
            r8 = r20
            double r1 = r1.f95c
            float r1 = (float) r1
            float r1 = r1 * r4
            float r2 = J0.g.c()
            float r2 = r2 * r1
            float r2 = r2 + r22
            r1 = 0
            r7.translate(r2, r1)
        L_0x01be:
            int r6 = r23 + 1
            r20 = r8
            r2 = r21
            r5 = r22
            r15 = r24
            r8 = r30
            goto L_0x00f1
        L_0x01cc:
            r22 = r5
            r24 = r15
            r8 = r20
            goto L_0x01d6
        L_0x01d3:
            r16 = r1
            goto L_0x01cc
        L_0x01d6:
            r7.restore()
            int r2 = r19 + 1
            r1 = r29
            r20 = r8
            r5 = r22
            r15 = r24
            r8 = r30
            goto L_0x00cf
        L_0x01e7:
            r22 = r5
            r24 = r15
            r8 = r20
            int r1 = r18 + 1
            r2 = r4
            r4 = r1
            r1 = r2
            r2 = r17
            r8 = r30
            goto L_0x00ad
        L_0x01f8:
            r14 = r7
            goto L_0x04a9
        L_0x01fb:
            r8 = r20
            A0.s r1 = r0.Z
            if (r1 == 0) goto L_0x020f
            java.lang.Object r1 = r1.f()
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x020f
            r20 = r2
            r21 = r4
            goto L_0x02dc
        L_0x020f:
            java.util.Map r1 = r14.f2096o
            if (r1 == 0) goto L_0x0247
            boolean r10 = r1.containsKey(r12)
            if (r10 == 0) goto L_0x0225
            java.lang.Object r1 = r1.get(r12)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
        L_0x021f:
            r20 = r2
            r21 = r4
            goto L_0x02d7
        L_0x0225:
            java.lang.String r10 = r3.b
            boolean r15 = r1.containsKey(r10)
            if (r15 == 0) goto L_0x0234
            java.lang.Object r1 = r1.get(r10)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            goto L_0x021f
        L_0x0234:
            java.lang.String r10 = "-"
            java.lang.String r10 = i.C0212a.B(r12, r10, r11)
            boolean r15 = r1.containsKey(r10)
            if (r15 == 0) goto L_0x0247
            java.lang.Object r1 = r1.get(r10)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            goto L_0x021f
        L_0x0247:
            B0.a r1 = r14.h()
            if (r1 == 0) goto L_0x02d2
            java.lang.Object r10 = r1.d
            C0.j r10 = (C0.j) r10
            r10.b = r12
            r10.f102c = r11
            java.lang.Object r15 = r1.e
            java.util.HashMap r15 = (java.util.HashMap) r15
            java.lang.Object r20 = r15.get(r10)
            android.graphics.Typeface r20 = (android.graphics.Typeface) r20
            if (r20 == 0) goto L_0x0269
            r21 = r4
            r1 = r20
            r20 = r2
            goto L_0x02d7
        L_0x0269:
            java.lang.Object r7 = r1.f
            java.util.HashMap r7 = (java.util.HashMap) r7
            java.lang.Object r20 = r7.get(r12)
            android.graphics.Typeface r20 = (android.graphics.Typeface) r20
            if (r20 == 0) goto L_0x027c
            r21 = r4
            r1 = r20
            r20 = r2
            goto L_0x02a8
        L_0x027c:
            r20 = r2
            android.graphics.Typeface r2 = r3.d
            if (r2 == 0) goto L_0x0286
            r1 = r2
            r21 = r4
            goto L_0x02a8
        L_0x0286:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r21 = r4
            java.lang.String r4 = "fonts/"
            r2.<init>(r4)
            r2.append(r12)
            java.lang.Object r4 = r1.f34h
            java.lang.String r4 = (java.lang.String) r4
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            java.lang.Object r1 = r1.g
            android.content.res.AssetManager r1 = (android.content.res.AssetManager) r1
            android.graphics.Typeface r1 = android.graphics.Typeface.createFromAsset(r1, r2)
            r7.put(r12, r1)
        L_0x02a8:
            java.lang.String r2 = "Italic"
            boolean r2 = r11.contains(r2)
            java.lang.String r4 = "Bold"
            boolean r4 = r11.contains(r4)
            if (r2 == 0) goto L_0x02ba
            if (r4 == 0) goto L_0x02ba
            r2 = 3
            goto L_0x02c3
        L_0x02ba:
            if (r2 == 0) goto L_0x02be
            r2 = 2
            goto L_0x02c3
        L_0x02be:
            if (r4 == 0) goto L_0x02c2
            r2 = 1
            goto L_0x02c3
        L_0x02c2:
            r2 = 0
        L_0x02c3:
            int r4 = r1.getStyle()
            if (r4 != r2) goto L_0x02ca
            goto L_0x02ce
        L_0x02ca:
            android.graphics.Typeface r1 = android.graphics.Typeface.create(r1, r2)
        L_0x02ce:
            r15.put(r10, r1)
            goto L_0x02d7
        L_0x02d2:
            r20 = r2
            r21 = r4
            r1 = 0
        L_0x02d7:
            if (r1 == 0) goto L_0x02da
            goto L_0x02dc
        L_0x02da:
            android.graphics.Typeface r1 = r3.d
        L_0x02dc:
            if (r1 != 0) goto L_0x02e2
        L_0x02de:
            r14 = r28
            goto L_0x04a9
        L_0x02e2:
            java.lang.String r2 = r9.f87a
            x0.J r4 = r14.q
            if (r4 == 0) goto L_0x02f0
            F0.i r7 = r0.f189p
            java.lang.String r7 = r7.f204c
            java.lang.String r2 = r4.getTextInternal(r7, r2)
        L_0x02f0:
            r13.setTypeface(r1)
            A0.s r1 = r0.Y
            if (r1 == 0) goto L_0x0302
            java.lang.Object r1 = r1.f()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            goto L_0x0304
        L_0x0302:
            float r1 = r9.f88c
        L_0x0304:
            float r4 = J0.g.c()
            float r4 = r4 * r1
            r13.setTextSize(r4)
            android.graphics.Typeface r4 = r13.getTypeface()
            r8.setTypeface(r4)
            float r4 = r13.getTextSize()
            r8.setTextSize(r4)
            int r4 = r9.e
            float r4 = (float) r4
            float r4 = r4 / r17
            A0.s r7 = r0.f224W
            if (r7 == 0) goto L_0x032f
            java.lang.Object r7 = r7.f()
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
        L_0x032d:
            float r4 = r4 + r7
            goto L_0x033c
        L_0x032f:
            if (r19 == 0) goto L_0x033c
            java.lang.Object r7 = r19.f()
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
            goto L_0x032d
        L_0x033c:
            float r7 = J0.g.c()
            float r7 = r7 * r4
            float r7 = r7 * r1
            float r7 = r7 / r18
            java.lang.String r1 = r2.replaceAll(r6, r5)
            r2 = r21
            java.lang.String r1 = r1.replaceAll(r2, r5)
            r2 = r20
            java.lang.String r1 = r1.replaceAll(r2, r5)
            java.lang.String[] r1 = r1.split(r5)
            java.util.List r10 = java.util.Arrays.asList(r1)
            int r11 = r10.size()
            r12 = 0
            r15 = 0
            r16 = -1
        L_0x0364:
            if (r15 >= r11) goto L_0x02de
            java.lang.Object r1 = r10.get(r15)
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r2 = r9.m
            if (r2 != 0) goto L_0x0372
            r2 = 0
            goto L_0x0374
        L_0x0372:
            float r2 = r2.x
        L_0x0374:
            r4 = 0
            r6 = 0
            r5 = r7
            java.util.List r1 = r0.x(r1, r2, r3, r4, r5, r6)
            r2 = 0
        L_0x037c:
            int r4 = r1.size()
            if (r2 >= r4) goto L_0x0496
            java.lang.Object r4 = r1.get(r2)
            F0.n r4 = (F0.n) r4
            int r6 = r16 + 1
            r28.save()
            float r7 = r4.b
            r14 = r28
            boolean r7 = r0.w(r14, r9, r6, r7)
            if (r7 == 0) goto L_0x0479
            java.lang.String r7 = r4.f213a
            r29 = r1
            r17 = r2
            r1 = 0
        L_0x039e:
            int r2 = r7.length()
            if (r1 >= r2) goto L_0x046d
            int r2 = r7.codePointAt(r1)
            int r16 = java.lang.Character.charCount(r2)
            int r16 = r16 + r1
            r18 = r16
            r16 = r1
            r1 = r18
            r18 = r3
        L_0x03b6:
            int r3 = r7.length()
            if (r1 >= r3) goto L_0x03ff
            int r3 = r7.codePointAt(r1)
            r19 = r3
            int r3 = java.lang.Character.getType(r19)
            r20 = r5
            r5 = 16
            if (r3 == r5) goto L_0x03f3
            int r3 = java.lang.Character.getType(r19)
            r5 = 27
            if (r3 == r5) goto L_0x03f3
            int r3 = java.lang.Character.getType(r19)
            r5 = 6
            if (r3 == r5) goto L_0x03f3
            int r3 = java.lang.Character.getType(r19)
            r5 = 28
            if (r3 == r5) goto L_0x03f3
            int r3 = java.lang.Character.getType(r19)
            r5 = 8
            if (r3 == r5) goto L_0x03f3
            int r3 = java.lang.Character.getType(r19)
            r5 = 19
            if (r3 != r5) goto L_0x0401
        L_0x03f3:
            int r3 = java.lang.Character.charCount(r19)
            int r1 = r1 + r3
            int r2 = r2 * 31
            int r2 = r2 + r19
            r5 = r20
            goto L_0x03b6
        L_0x03ff:
            r20 = r5
        L_0x0401:
            long r2 = (long) r2
            androidx.collection.LongSparseArray r5 = r0.f218J
            boolean r19 = r5.containsKey(r2)
            if (r19 == 0) goto L_0x0415
            java.lang.Object r1 = r5.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            r19 = r6
            r21 = r10
            goto L_0x043b
        L_0x0415:
            r19 = r6
            java.lang.StringBuilder r6 = r0.D
            r21 = r10
            r10 = 0
            r6.setLength(r10)
            r10 = r16
        L_0x0421:
            if (r10 >= r1) goto L_0x0434
            r22 = r1
            int r1 = r7.codePointAt(r10)
            r6.appendCodePoint(r1)
            int r1 = java.lang.Character.charCount(r1)
            int r10 = r10 + r1
            r1 = r22
            goto L_0x0421
        L_0x0434:
            java.lang.String r1 = r6.toString()
            r5.put(r2, r1)
        L_0x043b:
            int r2 = r12 + r16
            r3 = r30
            r0.r(r9, r3, r2)
            boolean r2 = r9.k
            if (r2 == 0) goto L_0x044d
            s(r1, r13, r14)
            s(r1, r8, r14)
            goto L_0x0453
        L_0x044d:
            s(r1, r8, r14)
            s(r1, r13, r14)
        L_0x0453:
            float r2 = r13.measureText(r1)
            float r2 = r2 + r20
            r5 = 0
            r14.translate(r2, r5)
            int r1 = r1.length()
            int r1 = r1 + r16
            r3 = r18
            r6 = r19
            r5 = r20
            r10 = r21
            goto L_0x039e
        L_0x046d:
            r18 = r3
            r20 = r5
            r19 = r6
            r21 = r10
            r5 = 0
            r3 = r30
            goto L_0x047e
        L_0x0479:
            r29 = r1
            r17 = r2
            goto L_0x046d
        L_0x047e:
            java.lang.String r1 = r4.f213a
            int r1 = r1.length()
            int r12 = r12 + r1
            r14.restore()
            int r2 = r17 + 1
            r1 = r29
            r3 = r18
            r16 = r19
            r5 = r20
            r10 = r21
            goto L_0x037c
        L_0x0496:
            r14 = r28
            r18 = r3
            r20 = r5
            r21 = r10
            r5 = 0
            r3 = r30
            int r15 = r15 + 1
            r3 = r18
            r7 = r20
            goto L_0x0364
        L_0x04a9:
            r14.restore()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.o.i(android.graphics.Canvas, android.graphics.Matrix, int):void");
    }

    public final void r(c cVar, int i2, int i7) {
        int i8;
        s sVar = this.Q;
        l lVar = this.f215G;
        if (sVar != null) {
            lVar.setColor(((Integer) sVar.f()).intValue());
        } else {
            f fVar = this.f223P;
            if (fVar == null || !v(i7)) {
                lVar.setColor(cVar.f89h);
            } else {
                lVar.setColor(((Integer) fVar.f()).intValue());
            }
        }
        s sVar2 = this.S;
        l lVar2 = this.f216H;
        if (sVar2 != null) {
            lVar2.setColor(((Integer) sVar2.f()).intValue());
        } else {
            f fVar2 = this.R;
            if (fVar2 == null || !v(i7)) {
                lVar2.setColor(cVar.f90i);
            } else {
                lVar2.setColor(((Integer) fVar2.f()).intValue());
            }
        }
        A0.e eVar = this.w.f25j;
        int i10 = 100;
        if (eVar == null) {
            i8 = 100;
        } else {
            i8 = ((Integer) eVar.f()).intValue();
        }
        f fVar3 = this.f225X;
        if (fVar3 != null && v(i7)) {
            i10 = ((Integer) fVar3.f()).intValue();
        }
        int round = Math.round((((((float) i10) / 100.0f) * ((((float) i8) * 255.0f) / 100.0f)) * ((float) i2)) / 255.0f);
        lVar.setAlpha(round);
        lVar2.setAlpha(round);
        s sVar3 = this.U;
        if (sVar3 != null) {
            lVar2.setStrokeWidth(((Float) sVar3.f()).floatValue());
            return;
        }
        i iVar = this.T;
        if (iVar == null || !v(i7)) {
            lVar2.setStrokeWidth(g.c() * cVar.f91j);
            return;
        }
        lVar2.setStrokeWidth(((Float) iVar.f()).floatValue());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, F0.n] */
    public final n u(int i2) {
        ArrayList arrayList = this.f219K;
        for (int size = arrayList.size(); size < i2; size++) {
            ? obj = new Object();
            obj.f213a = "";
            obj.b = 0.0f;
            arrayList.add(obj);
        }
        return (n) arrayList.get(i2 - 1);
    }

    public final boolean v(int i2) {
        f fVar;
        int length = ((c) this.L.f()).f87a.length();
        f fVar2 = this.a0;
        if (fVar2 == null || (fVar = this.b0) == null) {
            return true;
        }
        int min = Math.min(((Integer) fVar2.f()).intValue(), ((Integer) fVar.f()).intValue());
        int max = Math.max(((Integer) fVar2.f()).intValue(), ((Integer) fVar.f()).intValue());
        f fVar3 = this.f226c0;
        if (fVar3 != null) {
            int intValue = ((Integer) fVar3.f()).intValue();
            min += intValue;
            max += intValue;
        }
        if (this.f222O != z.INDEX) {
            float f = (((float) i2) / ((float) length)) * 100.0f;
            if (f < ((float) min) || f >= ((float) max)) {
                return false;
            }
            return true;
        } else if (i2 < min || i2 >= max) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean w(Canvas canvas, c cVar, int i2, float f) {
        float f5;
        float f8;
        PointF pointF = cVar.l;
        PointF pointF2 = cVar.m;
        float c5 = g.c();
        float f10 = 0.0f;
        if (pointF == null) {
            f5 = 0.0f;
        } else {
            f5 = (cVar.f * c5) + pointF.y;
        }
        float f11 = (((float) i2) * cVar.f * c5) + f5;
        if (this.f220M.z && pointF2 != null && pointF != null && f11 >= pointF.y + pointF2.y + cVar.f88c) {
            return false;
        }
        if (pointF == null) {
            f8 = 0.0f;
        } else {
            f8 = pointF.x;
        }
        if (pointF2 != null) {
            f10 = pointF2.x;
        }
        int i7 = m.f212a[cVar.d.ordinal()];
        if (i7 == 1) {
            canvas.translate(f8, f11);
            return true;
        } else if (i7 == 2) {
            canvas.translate((f8 + f10) - f, f11);
            return true;
        } else if (i7 != 3) {
            return true;
        } else {
            canvas.translate(((f10 / 2.0f) + f8) - (f / 2.0f), f11);
            return true;
        }
    }

    public final List x(String str, float f, d dVar, float f5, float f8, boolean z) {
        float f10;
        String str2 = str;
        d dVar2 = dVar;
        int i2 = 0;
        int i7 = 0;
        boolean z3 = false;
        int i8 = 0;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        for (int i10 = 0; i10 < str2.length(); i10++) {
            char charAt = str2.charAt(i10);
            if (z) {
                C0.e eVar = (C0.e) this.f221N.f2058h.get(C0.e.a(charAt, dVar2.f92a, dVar2.f93c));
                if (eVar == null) {
                } else {
                    f10 = (g.c() * ((float) eVar.f95c) * f5) + f8;
                }
            } else {
                f10 = this.f215G.measureText(str2.substring(i10, i10 + 1)) + f8;
            }
            if (charAt == ' ') {
                z3 = true;
                f13 = f10;
            } else if (z3) {
                z3 = false;
                i8 = i10;
                f12 = f10;
            } else {
                f12 += f10;
            }
            f11 += f10;
            if (f > 0.0f && f11 >= f && charAt != ' ') {
                i2++;
                n u = u(i2);
                if (i8 == i7) {
                    String substring = str2.substring(i7, i10);
                    String trim = substring.trim();
                    u.f213a = trim;
                    u.b = (f11 - f10) - (((float) (trim.length() - substring.length())) * f13);
                    i7 = i10;
                    i8 = i7;
                    f11 = f10;
                    f12 = f11;
                } else {
                    String substring2 = str2.substring(i7, i8 - 1);
                    String trim2 = substring2.trim();
                    u.f213a = trim2;
                    u.b = ((f11 - f12) - (((float) (substring2.length() - trim2.length())) * f13)) - f13;
                    f11 = f12;
                    i7 = i8;
                }
            }
        }
        if (f11 > 0.0f) {
            i2++;
            n u3 = u(i2);
            u3.f213a = str2.substring(i7);
            u3.b = f11;
        }
        return this.f219K.subList(0, i2);
    }
}
