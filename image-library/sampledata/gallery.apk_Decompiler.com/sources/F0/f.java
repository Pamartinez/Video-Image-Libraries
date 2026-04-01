package F0;

import A0.h;
import A0.s;
import B0.a;
import D0.e;
import J0.g;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import java.util.HashMap;
import x0.C0319A;
import x0.C0332j;
import x0.w;
import x0.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends c {
    public final /* synthetic */ int D;
    public final l E;

    /* renamed from: F  reason: collision with root package name */
    public s f197F;

    /* renamed from: G  reason: collision with root package name */
    public s f198G;

    /* renamed from: H  reason: collision with root package name */
    public final Parcelable f199H;

    /* renamed from: I  reason: collision with root package name */
    public final Object f200I;

    /* renamed from: J  reason: collision with root package name */
    public final Object f201J;

    /* renamed from: K  reason: collision with root package name */
    public final Object f202K;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(w wVar, i iVar, int i2) {
        super(wVar, iVar);
        y yVar;
        this.D = i2;
        switch (i2) {
            case 1:
                super(wVar, iVar);
                this.f199H = new RectF();
                l lVar = new l();
                this.E = lVar;
                this.f200I = new float[8];
                this.f201J = new Path();
                this.f202K = iVar;
                lVar.setAlpha(0);
                lVar.setStyle(Paint.Style.FILL);
                lVar.setColor(iVar.l);
                return;
            default:
                this.E = new l(3, 2);
                this.f199H = new Rect();
                this.f200I = new Rect();
                String str = iVar.g;
                C0332j jVar = wVar.d;
                if (jVar == null) {
                    yVar = null;
                } else {
                    yVar = (y) ((HashMap) jVar.d()).get(str);
                }
                this.f201J = yVar;
                a aVar = this.f189p.f210x;
                if (aVar != null) {
                    this.f202K = new h(this, this, aVar);
                    return;
                }
                return;
        }
    }

    public final void d(e eVar, Object obj) {
        switch (this.D) {
            case 0:
                super.d(eVar, obj);
                if (obj == C0319A.f2028F) {
                    this.f197F = new s(eVar, (Object) null);
                    return;
                } else if (obj == C0319A.f2031I) {
                    this.f198G = new s(eVar, (Object) null);
                    return;
                } else {
                    return;
                }
            default:
                super.d(eVar, obj);
                if (obj == C0319A.f2028F) {
                    this.f197F = new s(eVar, (Object) null);
                    return;
                } else if (obj == 1) {
                    this.f198G = new s(eVar, (Object) null);
                    return;
                } else {
                    return;
                }
        }
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        switch (this.D) {
            case 0:
                super.e(rectF, matrix, z);
                y yVar = (y) this.f201J;
                if (yVar != null) {
                    float c5 = g.c();
                    rectF.set(0.0f, 0.0f, ((float) yVar.f2099a) * c5, ((float) yVar.b) * c5);
                    this.n.mapRect(rectF);
                    return;
                }
                return;
            default:
                super.e(rectF, matrix, z);
                RectF rectF2 = (RectF) this.f199H;
                i iVar = (i) this.f202K;
                rectF2.set(0.0f, 0.0f, (float) iVar.f207j, (float) iVar.k);
                this.n.mapRect(rectF2);
                rectF.set(rectF2);
                return;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:112:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0220 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void i(android.graphics.Canvas r19, android.graphics.Matrix r20, int r21) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            int r0 = r1.D
            switch(r0) {
                case 0: goto L_0x00c2;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.Object r0 = r1.f200I
            float[] r0 = (float[]) r0
            java.lang.Object r5 = r1.f201J
            android.graphics.Path r5 = (android.graphics.Path) r5
            java.lang.Object r6 = r1.f202K
            F0.i r6 = (F0.i) r6
            int r7 = r6.l
            int r7 = android.graphics.Color.alpha(r7)
            if (r7 != 0) goto L_0x0023
            goto L_0x00c1
        L_0x0023:
            A0.s r8 = r1.f198G
            if (r8 != 0) goto L_0x0029
            r8 = 0
            goto L_0x002f
        L_0x0029:
            java.lang.Object r8 = r8.f()
            java.lang.Integer r8 = (java.lang.Integer) r8
        L_0x002f:
            F0.l r9 = r1.E
            if (r8 == 0) goto L_0x003b
            int r8 = r8.intValue()
            r9.setColor(r8)
            goto L_0x0040
        L_0x003b:
            int r8 = r6.l
            r9.setColor(r8)
        L_0x0040:
            A0.r r8 = r1.w
            A0.e r8 = r8.f25j
            if (r8 != 0) goto L_0x0049
            r8 = 100
            goto L_0x0053
        L_0x0049:
            java.lang.Object r8 = r8.f()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
        L_0x0053:
            float r4 = (float) r4
            r10 = 1132396544(0x437f0000, float:255.0)
            float r4 = r4 / r10
            float r7 = (float) r7
            float r7 = r7 / r10
            float r8 = (float) r8
            float r7 = r7 * r8
            r8 = 1120403456(0x42c80000, float:100.0)
            float r7 = r7 / r8
            float r7 = r7 * r4
            float r7 = r7 * r10
            int r4 = (int) r7
            r9.setAlpha(r4)
            A0.s r1 = r1.f197F
            if (r1 == 0) goto L_0x0071
            java.lang.Object r1 = r1.f()
            android.graphics.ColorFilter r1 = (android.graphics.ColorFilter) r1
            r9.setColorFilter(r1)
        L_0x0071:
            if (r4 <= 0) goto L_0x00c1
            r1 = 0
            r4 = 0
            r0[r1] = r4
            r7 = 1
            r0[r7] = r4
            int r8 = r6.f207j
            float r8 = (float) r8
            r10 = 2
            r0[r10] = r8
            r11 = 3
            r0[r11] = r4
            r12 = 4
            r0[r12] = r8
            int r6 = r6.k
            float r6 = (float) r6
            r8 = 5
            r0[r8] = r6
            r13 = 6
            r0[r13] = r4
            r4 = 7
            r0[r4] = r6
            r3.mapPoints(r0)
            r5.reset()
            r3 = r0[r1]
            r6 = r0[r7]
            r5.moveTo(r3, r6)
            r3 = r0[r10]
            r6 = r0[r11]
            r5.lineTo(r3, r6)
            r3 = r0[r12]
            r6 = r0[r8]
            r5.lineTo(r3, r6)
            r3 = r0[r13]
            r4 = r0[r4]
            r5.lineTo(r3, r4)
            r1 = r0[r1]
            r0 = r0[r7]
            r5.lineTo(r1, r0)
            r5.close()
            r2.drawPath(r5, r9)
        L_0x00c1:
            return
        L_0x00c2:
            android.os.Parcelable r0 = r1.f199H
            r5 = r0
            android.graphics.Rect r5 = (android.graphics.Rect) r5
            java.lang.Object r0 = r1.f200I
            r6 = r0
            android.graphics.Rect r6 = (android.graphics.Rect) r6
            java.lang.Object r0 = r1.f201J
            r7 = r0
            x0.y r7 = (x0.y) r7
            A0.s r0 = r1.f198G
            x0.w r8 = r1.f188o
            if (r0 == 0) goto L_0x00e1
            java.lang.Object r0 = r0.f()
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            if (r0 == 0) goto L_0x00e1
            goto L_0x0218
        L_0x00e1:
            F0.i r0 = r1.f189p
            java.lang.String r10 = r0.g
            B0.b r0 = r8.k
            r11 = 0
            if (r0 == 0) goto L_0x010b
            android.graphics.drawable.Drawable$Callback r12 = r8.getCallback()
            if (r12 != 0) goto L_0x00f2
        L_0x00f0:
            r12 = r11
            goto L_0x00fc
        L_0x00f2:
            boolean r13 = r12 instanceof android.view.View
            if (r13 == 0) goto L_0x00f0
            android.view.View r12 = (android.view.View) r12
            android.content.Context r12 = r12.getContext()
        L_0x00fc:
            android.content.Context r0 = r0.f35a
            boolean r13 = r0 instanceof android.app.Application
            if (r13 == 0) goto L_0x0106
            android.content.Context r12 = r12.getApplicationContext()
        L_0x0106:
            if (r12 != r0) goto L_0x0109
            goto L_0x010b
        L_0x0109:
            r8.k = r11
        L_0x010b:
            B0.b r0 = r8.k
            if (r0 != 0) goto L_0x0124
            B0.b r0 = new B0.b
            android.graphics.drawable.Drawable$Callback r12 = r8.getCallback()
            java.lang.String r13 = r8.l
            x0.c r14 = r8.m
            x0.j r15 = r8.d
            java.util.Map r15 = r15.d()
            r0.<init>(r12, r13, r14, r15)
            r8.k = r0
        L_0x0124:
            B0.b r0 = r8.k
            if (r0 == 0) goto L_0x01d9
            java.lang.String r12 = r0.b
            java.util.Map r13 = r0.d
            java.lang.Object r13 = r13.get(r10)
            x0.y r13 = (x0.y) r13
            if (r13 != 0) goto L_0x0136
            goto L_0x01d9
        L_0x0136:
            int r14 = r13.b
            int r15 = r13.f2099a
            android.graphics.Bitmap r11 = r13.f
            if (r11 == 0) goto L_0x0142
        L_0x013e:
            r0 = r11
        L_0x013f:
            r11 = 0
            goto L_0x020f
        L_0x0142:
            x0.c r11 = r0.f36c
            if (r11 == 0) goto L_0x0150
            android.graphics.Bitmap r11 = r11.fetchBitmap(r13)
            if (r11 == 0) goto L_0x013e
            r0.a(r10, r11)
            goto L_0x013e
        L_0x0150:
            android.content.Context r11 = r0.f35a
            if (r11 != 0) goto L_0x0157
        L_0x0154:
            r11 = 0
            goto L_0x01d9
        L_0x0157:
            java.lang.String r13 = r13.d
            android.graphics.BitmapFactory$Options r9 = new android.graphics.BitmapFactory$Options
            r9.<init>()
            r16 = r11
            r11 = 1
            r9.inScaled = r11
            r17 = r11
            r11 = 160(0xa0, float:2.24E-43)
            r9.inDensity = r11
            java.lang.String r11 = "data:"
            boolean r11 = r13.startsWith(r11)
            if (r11 == 0) goto L_0x019f
            java.lang.String r11 = "base64,"
            int r11 = r13.indexOf(r11)
            if (r11 <= 0) goto L_0x019f
            r11 = 44
            int r11 = r13.indexOf(r11)     // Catch:{ IllegalArgumentException -> 0x0198 }
            int r11 = r11 + 1
            java.lang.String r11 = r13.substring(r11)     // Catch:{ IllegalArgumentException -> 0x0198 }
            r12 = 0
            byte[] r11 = android.util.Base64.decode(r11, r12)     // Catch:{ IllegalArgumentException -> 0x0198 }
            int r13 = r11.length
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeByteArray(r11, r12, r13, r9)
            android.graphics.Bitmap r9 = J0.g.e(r9, r15, r14)
            r0.a(r10, r9)
            r0 = r9
            goto L_0x013f
        L_0x0198:
            r0 = move-exception
            java.lang.String r9 = "data URL did not have correct base64 format."
            J0.b.c(r9, r0)
            goto L_0x0154
        L_0x019f:
            boolean r11 = android.text.TextUtils.isEmpty(r12)     // Catch:{ IOException -> 0x01fc }
            if (r11 != 0) goto L_0x01ff
            android.content.res.AssetManager r11 = r16.getAssets()     // Catch:{ IOException -> 0x01fc }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01fc }
            r2.<init>()     // Catch:{ IOException -> 0x01fc }
            r2.append(r12)     // Catch:{ IOException -> 0x01fc }
            r2.append(r13)     // Catch:{ IOException -> 0x01fc }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x01fc }
            java.io.InputStream r2 = r11.open(r2)     // Catch:{ IOException -> 0x01fc }
            r11 = 0
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r2, r11, r9)     // Catch:{ IllegalArgumentException -> 0x01e4 }
            if (r2 != 0) goto L_0x01db
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Decoded image `"
            r0.<init>(r2)
            r0.append(r10)
            java.lang.String r2 = "` is null."
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            J0.b.b(r0)
        L_0x01d9:
            r0 = r11
            goto L_0x020f
        L_0x01db:
            android.graphics.Bitmap r2 = J0.g.e(r2, r15, r14)
            r0.a(r10, r2)
            r0 = r2
            goto L_0x020f
        L_0x01e4:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r9 = "Unable to decode image `"
            r2.<init>(r9)
            r2.append(r10)
            java.lang.String r9 = "`."
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            J0.b.c(r2, r0)
            goto L_0x01d9
        L_0x01fc:
            r0 = move-exception
            r11 = 0
            goto L_0x0209
        L_0x01ff:
            r11 = 0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ IOException -> 0x0208 }
            java.lang.String r2 = "You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder"
            r0.<init>(r2)     // Catch:{ IOException -> 0x0208 }
            throw r0     // Catch:{ IOException -> 0x0208 }
        L_0x0208:
            r0 = move-exception
        L_0x0209:
            java.lang.String r2 = "Unable to open asset."
            J0.b.c(r2, r0)
            goto L_0x01d9
        L_0x020f:
            if (r0 == 0) goto L_0x0212
            goto L_0x0218
        L_0x0212:
            if (r7 == 0) goto L_0x0217
            android.graphics.Bitmap r0 = r7.f
            goto L_0x0218
        L_0x0217:
            r0 = r11
        L_0x0218:
            if (r0 == 0) goto L_0x027f
            boolean r2 = r0.isRecycled()
            if (r2 != 0) goto L_0x027f
            if (r7 != 0) goto L_0x0223
            goto L_0x027f
        L_0x0223:
            float r2 = J0.g.c()
            F0.l r9 = r1.E
            r9.setAlpha(r4)
            A0.s r10 = r1.f197F
            if (r10 == 0) goto L_0x0239
            java.lang.Object r10 = r10.f()
            android.graphics.ColorFilter r10 = (android.graphics.ColorFilter) r10
            r9.setColorFilter(r10)
        L_0x0239:
            r19.save()
            r19.concat(r20)
            int r10 = r0.getWidth()
            int r11 = r0.getHeight()
            r12 = 0
            r5.set(r12, r12, r10, r11)
            boolean r8 = r8.s
            if (r8 == 0) goto L_0x025d
            int r8 = r7.f2099a
            float r8 = (float) r8
            float r8 = r8 * r2
            int r8 = (int) r8
            int r7 = r7.b
            float r7 = (float) r7
            float r7 = r7 * r2
            int r2 = (int) r7
            r6.set(r12, r12, r8, r2)
            goto L_0x026e
        L_0x025d:
            int r7 = r0.getWidth()
            float r7 = (float) r7
            float r7 = r7 * r2
            int r7 = (int) r7
            int r8 = r0.getHeight()
            float r8 = (float) r8
            float r8 = r8 * r2
            int r2 = (int) r8
            r6.set(r12, r12, r7, r2)
        L_0x026e:
            java.lang.Object r1 = r1.f202K
            A0.h r1 = (A0.h) r1
            if (r1 == 0) goto L_0x0277
            r1.b(r9, r3, r4)
        L_0x0277:
            r2 = r19
            r2.drawBitmap(r0, r5, r6, r9)
            r2.restore()
        L_0x027f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.f.i(android.graphics.Canvas, android.graphics.Matrix, int):void");
    }
}
