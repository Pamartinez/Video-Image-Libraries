package q1;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MarginLayoutParamsCompat;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public final C0265a f1861a;
    public boolean[] b;

    /* renamed from: c  reason: collision with root package name */
    public int[] f1862c;
    public long[] d;
    public long[] e;

    public f(C0265a aVar) {
        this.f1861a = aVar;
    }

    public static ArrayList e(List list, int i2, int i7) {
        ArrayList arrayList = new ArrayList();
        c cVar = new c();
        cVar.g = (i2 - i7) / 2;
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            if (i8 == 0) {
                arrayList.add(cVar);
            }
            arrayList.add((c) list.get(i8));
            if (i8 == list.size() - 1) {
                arrayList.add(cVar);
            }
        }
        return arrayList;
    }

    public static int[] r(int i2, ArrayList arrayList, SparseIntArray sparseIntArray) {
        Collections.sort(arrayList);
        sparseIntArray.clear();
        int[] iArr = new int[i2];
        Iterator it = arrayList.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            e eVar = (e) it.next();
            int i8 = eVar.d;
            iArr[i7] = i8;
            sparseIntArray.append(i8, eVar.e);
            i7++;
        }
        return iArr;
    }

    public final void a(List list, c cVar, int i2, int i7) {
        cVar.m = i7;
        this.f1861a.onNewFlexLineAdded(cVar);
        cVar.f1859p = i2;
        list.add(cVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01f6, code lost:
        if (r8 < (r9 + r14)) goto L_0x01f8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x029f  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x02a7  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x02ee  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02f9  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x02fe  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0305  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x030a  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0322  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0385  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x038b A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(q1.d r28, int r29, int r30, int r31, int r32, int r33, java.util.List r34) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r33
            q1.a r5 = r0.f1861a
            boolean r6 = r5.isMainAxisDirectionHorizontal()
            int r7 = android.view.View.MeasureSpec.getMode(r2)
            int r8 = android.view.View.MeasureSpec.getSize(r2)
            if (r34 != 0) goto L_0x0020
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            goto L_0x0022
        L_0x0020:
            r9 = r34
        L_0x0022:
            r1.f1860a = r9
            r10 = -1
            if (r4 != r10) goto L_0x0029
            r13 = 1
            goto L_0x002a
        L_0x0029:
            r13 = 0
        L_0x002a:
            if (r6 == 0) goto L_0x0031
            int r14 = r5.getPaddingStart()
            goto L_0x0035
        L_0x0031:
            int r14 = r5.getPaddingTop()
        L_0x0035:
            if (r6 == 0) goto L_0x003c
            int r15 = r5.getPaddingEnd()
            goto L_0x0040
        L_0x003c:
            int r15 = r5.getPaddingBottom()
        L_0x0040:
            if (r6 == 0) goto L_0x0047
            int r16 = r5.getPaddingTop()
            goto L_0x004b
        L_0x0047:
            int r16 = r5.getPaddingStart()
        L_0x004b:
            if (r6 == 0) goto L_0x0052
            int r17 = r5.getPaddingBottom()
            goto L_0x0056
        L_0x0052:
            int r17 = r5.getPaddingEnd()
        L_0x0056:
            q1.c r12 = new q1.c
            r12.<init>()
            r11 = r32
            r18 = 1
            r12.f1858o = r11
            int r14 = r14 + r15
            r12.e = r14
            int r15 = r5.getFlexItemCount()
            r19 = -2147483648(0xffffffff80000000, float:-0.0)
            r20 = r6
            r32 = r13
            r21 = r19
            r6 = 0
            r10 = 0
            r13 = 0
        L_0x0073:
            if (r11 >= r15) goto L_0x038d
            r22 = r15
            android.view.View r15 = r5.getReorderedFlexItemAt(r11)
            if (r15 != 0) goto L_0x008b
            int r15 = r22 + -1
            if (r11 != r15) goto L_0x00ac
            int r15 = r12.a()
            if (r15 == 0) goto L_0x00ac
            r0.a(r9, r12, r11, r10)
            goto L_0x00ac
        L_0x008b:
            int r1 = r15.getVisibility()
            r4 = 8
            if (r1 != r4) goto L_0x00b4
            int r1 = r12.f1856i
            int r1 = r1 + 1
            r12.f1856i = r1
            int r1 = r12.f1855h
            int r1 = r1 + 1
            r12.f1855h = r1
            int r15 = r22 + -1
            if (r11 != r15) goto L_0x00ac
            int r1 = r12.a()
            if (r1 == 0) goto L_0x00ac
            r0.a(r9, r12, r11, r10)
        L_0x00ac:
            r15 = r31
            r2 = r32
            r4 = r33
            goto L_0x0392
        L_0x00b4:
            boolean r1 = r15 instanceof android.widget.CompoundButton
            if (r1 == 0) goto L_0x00f6
            r1 = r15
            android.widget.CompoundButton r1 = (android.widget.CompoundButton) r1
            android.view.ViewGroup$LayoutParams r4 = r1.getLayoutParams()
            q1.b r4 = (q1.b) r4
            r23 = r1
            int r1 = r4.f()
            r24 = r14
            int r14 = r4.v()
            android.graphics.drawable.Drawable r23 = androidx.core.widget.CompoundButtonCompat.getButtonDrawable(r23)
            if (r23 != 0) goto L_0x00d6
            r25 = 0
            goto L_0x00da
        L_0x00d6:
            int r25 = r23.getMinimumWidth()
        L_0x00da:
            if (r23 != 0) goto L_0x00e2
            r23 = 0
        L_0x00de:
            r26 = r9
            r9 = -1
            goto L_0x00e7
        L_0x00e2:
            int r23 = r23.getMinimumHeight()
            goto L_0x00de
        L_0x00e7:
            if (r1 != r9) goto L_0x00eb
            r1 = r25
        L_0x00eb:
            r4.p(r1)
            if (r14 != r9) goto L_0x00f2
            r14 = r23
        L_0x00f2:
            r4.h(r14)
            goto L_0x00fa
        L_0x00f6:
            r26 = r9
            r24 = r14
        L_0x00fa:
            android.view.ViewGroup$LayoutParams r1 = r15.getLayoutParams()
            q1.b r1 = (q1.b) r1
            int r4 = r1.a()
            r9 = 4
            if (r4 != r9) goto L_0x0110
            java.util.ArrayList r4 = r12.n
            java.lang.Integer r9 = java.lang.Integer.valueOf(r11)
            r4.add(r9)
        L_0x0110:
            if (r20 == 0) goto L_0x0117
            int r4 = r1.getWidth()
            goto L_0x011b
        L_0x0117:
            int r4 = r1.getHeight()
        L_0x011b:
            float r9 = r1.l()
            r14 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r9 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r9 == 0) goto L_0x0133
            r9 = 1073741824(0x40000000, float:2.0)
            if (r7 != r9) goto L_0x0133
            float r4 = (float) r8
            float r9 = r1.l()
            float r9 = r9 * r4
            int r4 = java.lang.Math.round(r9)
        L_0x0133:
            if (r20 == 0) goto L_0x0160
            int r9 = r1.r()
            int r9 = r9 + r24
            int r14 = r1.t()
            int r14 = r14 + r9
            int r4 = r5.getChildWidthMeasureSpec(r2, r14, r4)
            int r9 = r16 + r17
            int r14 = r1.g()
            int r14 = r14 + r9
            int r9 = r1.q()
            int r9 = r9 + r14
            int r9 = r9 + r10
            int r14 = r1.getHeight()
            int r9 = r5.getChildHeightMeasureSpec(r3, r9, r14)
            r15.measure(r4, r9)
            r0.v(r11, r4, r9, r15)
            goto L_0x018b
        L_0x0160:
            int r9 = r16 + r17
            int r14 = r1.r()
            int r14 = r14 + r9
            int r9 = r1.t()
            int r9 = r9 + r14
            int r9 = r9 + r10
            int r14 = r1.getWidth()
            int r9 = r5.getChildWidthMeasureSpec(r3, r9, r14)
            int r14 = r1.g()
            int r14 = r14 + r24
            int r23 = r1.q()
            int r14 = r23 + r14
            int r4 = r5.getChildHeightMeasureSpec(r2, r14, r4)
            r15.measure(r9, r4)
            r0.v(r11, r9, r4, r15)
        L_0x018b:
            r5.updateViewCache(r11, r15)
            r0.c(r11, r15)
            int r9 = r15.getMeasuredState()
            int r6 = android.view.View.combineMeasuredStates(r6, r9)
            int r9 = r12.e
            if (r20 == 0) goto L_0x01a2
            int r14 = r15.getMeasuredWidth()
            goto L_0x01a6
        L_0x01a2:
            int r14 = r15.getMeasuredHeight()
        L_0x01a6:
            if (r20 == 0) goto L_0x01ad
            int r23 = r1.r()
            goto L_0x01b1
        L_0x01ad:
            int r23 = r1.g()
        L_0x01b1:
            int r14 = r14 + r23
            if (r20 == 0) goto L_0x01ba
            int r23 = r1.t()
            goto L_0x01be
        L_0x01ba:
            int r23 = r1.q()
        L_0x01be:
            int r14 = r14 + r23
            int r23 = r26.size()
            int r25 = r5.getFlexWrap()
            if (r25 != 0) goto L_0x01d4
        L_0x01ca:
            r25 = r1
        L_0x01cc:
            r1 = r18
            r14 = r24
            r9 = r26
            goto L_0x0278
        L_0x01d4:
            boolean r25 = r1.m()
            if (r25 == 0) goto L_0x01dd
            r25 = r1
            goto L_0x01f8
        L_0x01dd:
            if (r7 != 0) goto L_0x01e0
            goto L_0x01ca
        L_0x01e0:
            r25 = r1
            int r1 = r5.getMaxLine()
            r2 = -1
            if (r1 == r2) goto L_0x01ee
            int r2 = r23 + 1
            if (r1 > r2) goto L_0x01ee
            goto L_0x01cc
        L_0x01ee:
            int r1 = r5.getDecorationLengthMainAxis(r15, r11, r13)
            if (r1 <= 0) goto L_0x01f5
            int r14 = r14 + r1
        L_0x01f5:
            int r9 = r9 + r14
            if (r8 >= r9) goto L_0x01cc
        L_0x01f8:
            int r1 = r12.a()
            if (r1 <= 0) goto L_0x020e
            if (r11 <= 0) goto L_0x0205
            int r1 = r11 + -1
        L_0x0202:
            r9 = r26
            goto L_0x0207
        L_0x0205:
            r1 = 0
            goto L_0x0202
        L_0x0207:
            r0.a(r9, r12, r1, r10)
            int r1 = r12.g
            int r10 = r10 + r1
            goto L_0x0210
        L_0x020e:
            r9 = r26
        L_0x0210:
            if (r20 == 0) goto L_0x023c
            int r1 = r25.getHeight()
            r2 = -1
            if (r1 != r2) goto L_0x0265
            int r1 = r5.getPaddingTop()
            int r2 = r5.getPaddingBottom()
            int r2 = r2 + r1
            int r1 = r25.g()
            int r1 = r1 + r2
            int r2 = r25.q()
            int r2 = r2 + r1
            int r2 = r2 + r10
            int r1 = r25.getHeight()
            int r1 = r5.getChildHeightMeasureSpec(r3, r2, r1)
            r15.measure(r4, r1)
            r0.c(r11, r15)
            goto L_0x0265
        L_0x023c:
            int r1 = r25.getWidth()
            r2 = -1
            if (r1 != r2) goto L_0x0265
            int r1 = r5.getPaddingLeft()
            int r2 = r5.getPaddingRight()
            int r2 = r2 + r1
            int r1 = r25.r()
            int r1 = r1 + r2
            int r2 = r25.t()
            int r2 = r2 + r1
            int r2 = r2 + r10
            int r1 = r25.getWidth()
            int r1 = r5.getChildWidthMeasureSpec(r3, r2, r1)
            r15.measure(r1, r4)
            r0.c(r11, r15)
        L_0x0265:
            q1.c r12 = new q1.c
            r12.<init>()
            r1 = r18
            r12.f1855h = r1
            r14 = r24
            r12.e = r14
            r12.f1858o = r11
            r1 = r19
            r13 = 0
            goto L_0x0281
        L_0x0278:
            int r2 = r12.f1855h
            int r2 = r2 + r1
            r12.f1855h = r2
            int r13 = r13 + 1
            r1 = r21
        L_0x0281:
            boolean r2 = r12.q
            float r4 = r25.j()
            r21 = 0
            int r4 = (r4 > r21 ? 1 : (r4 == r21 ? 0 : -1))
            if (r4 == 0) goto L_0x028f
            r4 = 1
            goto L_0x0290
        L_0x028f:
            r4 = 0
        L_0x0290:
            r2 = r2 | r4
            r12.q = r2
            boolean r2 = r12.r
            float r4 = r25.c()
            int r4 = (r4 > r21 ? 1 : (r4 == r21 ? 0 : -1))
            if (r4 == 0) goto L_0x029f
            r4 = 1
            goto L_0x02a0
        L_0x029f:
            r4 = 0
        L_0x02a0:
            r2 = r2 | r4
            r12.r = r2
            int[] r2 = r0.f1862c
            if (r2 == 0) goto L_0x02ad
            int r4 = r9.size()
            r2[r11] = r4
        L_0x02ad:
            int r2 = r12.e
            if (r20 == 0) goto L_0x02b6
            int r4 = r15.getMeasuredWidth()
            goto L_0x02ba
        L_0x02b6:
            int r4 = r15.getMeasuredHeight()
        L_0x02ba:
            if (r20 == 0) goto L_0x02c1
            int r21 = r25.r()
            goto L_0x02c5
        L_0x02c1:
            int r21 = r25.g()
        L_0x02c5:
            int r4 = r4 + r21
            if (r20 == 0) goto L_0x02ce
            int r21 = r25.t()
            goto L_0x02d2
        L_0x02ce:
            int r21 = r25.q()
        L_0x02d2:
            int r4 = r4 + r21
            int r4 = r4 + r2
            r12.e = r4
            float r2 = r12.f1857j
            float r4 = r25.j()
            float r4 = r4 + r2
            r12.f1857j = r4
            float r2 = r12.k
            float r4 = r25.c()
            float r4 = r4 + r2
            r12.k = r4
            r5.onNewFlexItemAdded(r15, r11, r13, r12)
            if (r20 == 0) goto L_0x02f3
            int r2 = r15.getMeasuredHeight()
            goto L_0x02f7
        L_0x02f3:
            int r2 = r15.getMeasuredWidth()
        L_0x02f7:
            if (r20 == 0) goto L_0x02fe
            int r4 = r25.g()
            goto L_0x0302
        L_0x02fe:
            int r4 = r25.r()
        L_0x0302:
            int r2 = r2 + r4
            if (r20 == 0) goto L_0x030a
            int r4 = r25.q()
            goto L_0x030e
        L_0x030a:
            int r4 = r25.t()
        L_0x030e:
            int r2 = r2 + r4
            int r4 = r5.getDecorationLengthCrossAxis(r15)
            int r4 = r4 + r2
            int r1 = java.lang.Math.max(r1, r4)
            int r2 = r12.g
            int r2 = java.lang.Math.max(r2, r1)
            r12.g = r2
            if (r20 == 0) goto L_0x0351
            int r2 = r5.getFlexWrap()
            r4 = 2
            if (r2 == r4) goto L_0x033b
            int r2 = r12.l
            int r4 = r15.getBaseline()
            int r15 = r25.g()
            int r15 = r15 + r4
            int r2 = java.lang.Math.max(r2, r15)
            r12.l = r2
            goto L_0x0351
        L_0x033b:
            int r2 = r12.l
            int r4 = r15.getMeasuredHeight()
            int r15 = r15.getBaseline()
            int r4 = r4 - r15
            int r15 = r25.q()
            int r15 = r15 + r4
            int r2 = java.lang.Math.max(r2, r15)
            r12.l = r2
        L_0x0351:
            int r15 = r22 + -1
            if (r11 != r15) goto L_0x0361
            int r2 = r12.a()
            if (r2 == 0) goto L_0x0361
            r0.a(r9, r12, r11, r10)
            int r2 = r12.g
            int r10 = r10 + r2
        L_0x0361:
            r4 = r33
            r2 = -1
            if (r4 == r2) goto L_0x0385
            int r15 = r9.size()
            if (r15 <= 0) goto L_0x0385
            r15 = 1
            java.lang.Object r18 = c0.C0086a.f(r15, r9)
            r2 = r18
            q1.c r2 = (q1.c) r2
            int r2 = r2.f1859p
            if (r2 < r4) goto L_0x0386
            if (r11 < r4) goto L_0x0386
            if (r32 != 0) goto L_0x0386
            int r2 = r12.g
            int r2 = -r2
            r10 = r2
            r2 = r15
        L_0x0382:
            r15 = r31
            goto L_0x0389
        L_0x0385:
            r15 = 1
        L_0x0386:
            r2 = r32
            goto L_0x0382
        L_0x0389:
            if (r10 <= r15) goto L_0x0390
            if (r2 == 0) goto L_0x0390
        L_0x038d:
            r1 = r28
            goto L_0x03a0
        L_0x0390:
            r21 = r1
        L_0x0392:
            int r11 = r11 + 1
            r1 = r28
            r32 = r2
            r15 = r22
            r18 = 1
            r2 = r29
            goto L_0x0073
        L_0x03a0:
            r1.b = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.f.b(q1.d, int, int, int, int, int, java.util.List):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(int r7, android.view.View r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r8.getLayoutParams()
            q1.b r0 = (q1.b) r0
            int r1 = r8.getMeasuredWidth()
            int r2 = r8.getMeasuredHeight()
            int r3 = r0.f()
            r4 = 1
            if (r1 >= r3) goto L_0x001b
            int r1 = r0.f()
        L_0x0019:
            r3 = r4
            goto L_0x0027
        L_0x001b:
            int r3 = r0.n()
            if (r1 <= r3) goto L_0x0026
            int r1 = r0.n()
            goto L_0x0019
        L_0x0026:
            r3 = 0
        L_0x0027:
            int r5 = r0.v()
            if (r2 >= r5) goto L_0x0032
            int r2 = r0.v()
            goto L_0x003e
        L_0x0032:
            int r5 = r0.w()
            if (r2 <= r5) goto L_0x003d
            int r2 = r0.w()
            goto L_0x003e
        L_0x003d:
            r4 = r3
        L_0x003e:
            if (r4 == 0) goto L_0x0055
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r8.measure(r1, r0)
            r6.v(r7, r1, r0, r8)
            q1.a r6 = r6.f1861a
            r6.updateViewCache(r7, r8)
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.f.c(int, android.view.View):void");
    }

    public final void d(int i2, List list) {
        int i7 = this.f1862c[i2];
        if (i7 == -1) {
            i7 = 0;
        }
        if (list.size() > i7) {
            list.subList(i7, list.size()).clear();
        }
        int[] iArr = this.f1862c;
        int length = iArr.length - 1;
        if (i2 > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i2, length, -1);
        }
        long[] jArr = this.d;
        int length2 = jArr.length - 1;
        if (i2 > length2) {
            Arrays.fill(jArr, 0);
        } else {
            Arrays.fill(jArr, i2, length2, 0);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [q1.e, java.lang.Object] */
    public final ArrayList f(int i2) {
        ArrayList arrayList = new ArrayList(i2);
        for (int i7 = 0; i7 < i2; i7++) {
            ? obj = new Object();
            obj.e = ((b) this.f1861a.getFlexItemAt(i7).getLayoutParams()).getOrder();
            obj.d = i7;
            arrayList.add(obj);
        }
        return arrayList;
    }

    public final void g(int i2, int i7, int i8) {
        int i10;
        int i11;
        C0265a aVar = this.f1861a;
        int flexDirection = aVar.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            int mode = View.MeasureSpec.getMode(i7);
            int size = View.MeasureSpec.getSize(i7);
            i10 = mode;
            i11 = size;
        } else if (flexDirection == 2 || flexDirection == 3) {
            i10 = View.MeasureSpec.getMode(i2);
            i11 = View.MeasureSpec.getSize(i2);
        } else {
            throw new IllegalArgumentException(C0086a.i(flexDirection, "Invalid flex direction: "));
        }
        List<c> flexLinesInternal = aVar.getFlexLinesInternal();
        if (i10 == 1073741824) {
            int sumOfCrossSize = aVar.getSumOfCrossSize() + i8;
            int i12 = 0;
            if (flexLinesInternal.size() == 1) {
                ((c) flexLinesInternal.get(0)).g = i11 - i8;
            } else if (flexLinesInternal.size() >= 2) {
                int alignContent = aVar.getAlignContent();
                if (alignContent == 1) {
                    c cVar = new c();
                    cVar.g = i11 - sumOfCrossSize;
                    flexLinesInternal.add(0, cVar);
                } else if (alignContent == 2) {
                    aVar.setFlexLines(e(flexLinesInternal, i11, sumOfCrossSize));
                } else if (alignContent != 3) {
                    if (alignContent != 4) {
                        if (alignContent == 5 && sumOfCrossSize < i11) {
                            float size2 = ((float) (i11 - sumOfCrossSize)) / ((float) flexLinesInternal.size());
                            int size3 = flexLinesInternal.size();
                            float f = 0.0f;
                            while (i12 < size3) {
                                c cVar2 = (c) flexLinesInternal.get(i12);
                                float f5 = ((float) cVar2.g) + size2;
                                if (i12 == flexLinesInternal.size() - 1) {
                                    f5 += f;
                                    f = 0.0f;
                                }
                                int round = Math.round(f5);
                                float f8 = (f5 - ((float) round)) + f;
                                if (f8 > 1.0f) {
                                    round++;
                                    f8 -= 1.0f;
                                } else if (f8 < -1.0f) {
                                    round--;
                                    f8 += 1.0f;
                                }
                                f = f8;
                                cVar2.g = round;
                                i12++;
                            }
                        }
                    } else if (sumOfCrossSize >= i11) {
                        aVar.setFlexLines(e(flexLinesInternal, i11, sumOfCrossSize));
                    } else {
                        int size4 = (i11 - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                        ArrayList arrayList = new ArrayList();
                        c cVar3 = new c();
                        cVar3.g = size4;
                        for (c add : flexLinesInternal) {
                            arrayList.add(cVar3);
                            arrayList.add(add);
                            arrayList.add(cVar3);
                        }
                        aVar.setFlexLines(arrayList);
                    }
                } else if (sumOfCrossSize < i11) {
                    float size5 = ((float) (i11 - sumOfCrossSize)) / ((float) (flexLinesInternal.size() - 1));
                    ArrayList arrayList2 = new ArrayList();
                    int size6 = flexLinesInternal.size();
                    float f10 = 0.0f;
                    while (i12 < size6) {
                        arrayList2.add((c) flexLinesInternal.get(i12));
                        if (i12 != flexLinesInternal.size() - 1) {
                            c cVar4 = new c();
                            if (i12 == flexLinesInternal.size() - 2) {
                                cVar4.g = Math.round(f10 + size5);
                                f10 = 0.0f;
                            } else {
                                cVar4.g = Math.round(size5);
                            }
                            int i13 = cVar4.g;
                            float f11 = (size5 - ((float) i13)) + f10;
                            if (f11 > 1.0f) {
                                cVar4.g = i13 + 1;
                                f11 -= 1.0f;
                            } else if (f11 < -1.0f) {
                                cVar4.g = i13 - 1;
                                f11 += 1.0f;
                            }
                            f10 = f11;
                            arrayList2.add(cVar4);
                        }
                        i12++;
                    }
                    aVar.setFlexLines(arrayList2);
                }
            }
        }
    }

    public final void h(int i2, int i7, int i8) {
        int i10;
        int paddingLeft;
        int paddingRight;
        int i11;
        int i12;
        f fVar;
        C0265a aVar = this.f1861a;
        int flexItemCount = aVar.getFlexItemCount();
        boolean[] zArr = this.b;
        int i13 = 0;
        if (zArr == null) {
            this.b = new boolean[Math.max(flexItemCount, 10)];
        } else if (zArr.length < flexItemCount) {
            this.b = new boolean[Math.max(zArr.length * 2, flexItemCount)];
        } else {
            Arrays.fill(zArr, false);
        }
        if (i8 < aVar.getFlexItemCount()) {
            int flexDirection = aVar.getFlexDirection();
            int flexDirection2 = aVar.getFlexDirection();
            if (flexDirection2 == 0 || flexDirection2 == 1) {
                int mode = View.MeasureSpec.getMode(i2);
                int size = View.MeasureSpec.getSize(i2);
                int largestMainSize = aVar.getLargestMainSize();
                if (mode != 1073741824) {
                    size = Math.min(largestMainSize, size);
                }
                paddingLeft = aVar.getPaddingLeft();
                paddingRight = aVar.getPaddingRight();
            } else if (flexDirection2 == 2 || flexDirection2 == 3) {
                int mode2 = View.MeasureSpec.getMode(i7);
                i10 = View.MeasureSpec.getSize(i7);
                if (mode2 != 1073741824) {
                    i10 = aVar.getLargestMainSize();
                }
                paddingLeft = aVar.getPaddingTop();
                paddingRight = aVar.getPaddingBottom();
            } else {
                throw new IllegalArgumentException(C0086a.i(flexDirection, "Invalid flex direction: "));
            }
            int i14 = i10;
            int i15 = paddingRight + paddingLeft;
            int[] iArr = this.f1862c;
            if (iArr != null) {
                i13 = iArr[i8];
            }
            List flexLinesInternal = aVar.getFlexLinesInternal();
            int size2 = flexLinesInternal.size();
            while (i13 < size2) {
                c cVar = (c) flexLinesInternal.get(i13);
                int i16 = cVar.e;
                if (i16 >= i14 || !cVar.q) {
                    fVar = this;
                    i12 = i2;
                    i11 = i7;
                    if (i16 > i14 && cVar.r) {
                        fVar.q(i12, i11, cVar, i14, i15, false);
                    }
                } else {
                    fVar = this;
                    i12 = i2;
                    i11 = i7;
                    fVar.l(i12, i11, cVar, i14, i15, false);
                }
                i13++;
                this = fVar;
                i2 = i12;
                i7 = i11;
            }
        }
    }

    public final void i(int i2) {
        int[] iArr = this.f1862c;
        if (iArr == null) {
            this.f1862c = new int[Math.max(i2, 10)];
        } else if (iArr.length < i2) {
            this.f1862c = Arrays.copyOf(this.f1862c, Math.max(iArr.length * 2, i2));
        }
    }

    public final void j(int i2) {
        long[] jArr = this.d;
        if (jArr == null) {
            this.d = new long[Math.max(i2, 10)];
        } else if (jArr.length < i2) {
            this.d = Arrays.copyOf(this.d, Math.max(jArr.length * 2, i2));
        }
    }

    public final void k(int i2) {
        long[] jArr = this.e;
        if (jArr == null) {
            this.e = new long[Math.max(i2, 10)];
        } else if (jArr.length < i2) {
            this.e = Arrays.copyOf(this.e, Math.max(jArr.length * 2, i2));
        }
    }

    public final void l(int i2, int i7, c cVar, int i8, int i10, boolean z) {
        int i11;
        float f;
        boolean z3;
        int i12;
        float f5;
        int i13;
        int i14;
        double d2;
        boolean z7;
        float f8;
        boolean z9;
        double d3;
        c cVar2 = cVar;
        int i15 = i8;
        float f10 = cVar2.f1857j;
        float f11 = 0.0f;
        if (f10 > 0.0f && i15 >= (i11 = cVar2.e)) {
            float f12 = ((float) (i15 - i11)) / f10;
            cVar2.e = i10 + cVar2.f;
            if (!z) {
                cVar2.g = Integer.MIN_VALUE;
            }
            int i16 = 0;
            boolean z10 = false;
            int i17 = 0;
            float f13 = 0.0f;
            while (i16 < cVar2.f1855h) {
                int i18 = cVar2.f1858o + i16;
                C0265a aVar = this.f1861a;
                View reorderedFlexItemAt = aVar.getReorderedFlexItemAt(i18);
                if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                    f5 = f11;
                    i12 = i11;
                    f = f12;
                    z3 = z10;
                    int i19 = i2;
                    int i20 = i7;
                    i13 = i16;
                } else {
                    b bVar = (b) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = aVar.getFlexDirection();
                    f5 = f11;
                    if (flexDirection == 0 || flexDirection == 1) {
                        i12 = i11;
                        float f14 = f12;
                        z3 = z10;
                        int i21 = i2;
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr = this.e;
                        if (jArr != null) {
                            measuredWidth = (int) jArr[i18];
                        }
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr2 = this.e;
                        if (jArr2 != null) {
                            measuredHeight = (int) (jArr2[i18] >> 32);
                        }
                        if (this.b[i18] || bVar.j() <= f5) {
                            i13 = i16;
                            f = f14;
                            int i22 = i7;
                        } else {
                            float j2 = (bVar.j() * f14) + ((float) measuredWidth);
                            if (i16 == cVar2.f1855h - 1) {
                                j2 += f13;
                                f13 = f5;
                            }
                            int round = Math.round(j2);
                            if (round > bVar.n()) {
                                round = bVar.n();
                                this.b[i18] = true;
                                cVar2.f1857j -= bVar.j();
                                z3 = true;
                                i13 = i16;
                                f = f14;
                            } else {
                                float f15 = (j2 - ((float) round)) + f13;
                                i13 = i16;
                                f = f14;
                                double d5 = (double) f15;
                                if (d5 > 1.0d) {
                                    round++;
                                    d2 = d5 - 1.0d;
                                } else if (d5 < -1.0d) {
                                    round--;
                                    d2 = d5 + 1.0d;
                                } else {
                                    f13 = f15;
                                }
                                f13 = (float) d2;
                            }
                            int m = m(i7, bVar, cVar2.m);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                            reorderedFlexItemAt.measure(makeMeasureSpec, m);
                            int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                            v(i18, makeMeasureSpec, m, reorderedFlexItemAt);
                            aVar.updateViewCache(i18, reorderedFlexItemAt);
                            measuredWidth = measuredWidth2;
                            measuredHeight = measuredHeight2;
                        }
                        int max = Math.max(i17, aVar.getDecorationLengthCrossAxis(reorderedFlexItemAt) + bVar.q() + bVar.g() + measuredHeight);
                        cVar2.e = bVar.t() + bVar.r() + measuredWidth + cVar2.e;
                        i14 = max;
                    } else {
                        int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr3 = this.e;
                        if (jArr3 != null) {
                            long j3 = jArr3[i18];
                            f8 = f12;
                            z7 = z10;
                            measuredHeight3 = (int) (j3 >> 32);
                        } else {
                            f8 = f12;
                            z7 = z10;
                        }
                        int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr4 = this.e;
                        if (jArr4 != null) {
                            measuredWidth3 = (int) jArr4[i18];
                        }
                        if (this.b[i18] || bVar.j() <= f5) {
                            i12 = i11;
                            int i23 = i2;
                            z9 = z7;
                        } else {
                            float j8 = (bVar.j() * f8) + ((float) measuredHeight3);
                            if (i16 == cVar2.f1855h - 1) {
                                j8 += f13;
                                f13 = f5;
                            }
                            int round2 = Math.round(j8);
                            if (round2 > bVar.w()) {
                                round2 = bVar.w();
                                this.b[i18] = true;
                                cVar2.f1857j -= bVar.j();
                                z9 = true;
                                i12 = i11;
                            } else {
                                float f16 = (j8 - ((float) round2)) + f13;
                                i12 = i11;
                                double d6 = (double) f16;
                                if (d6 > 1.0d) {
                                    round2++;
                                    d3 = d6 - 1.0d;
                                } else if (d6 < -1.0d) {
                                    round2--;
                                    d3 = d6 + 1.0d;
                                } else {
                                    f13 = f16;
                                    z9 = z7;
                                }
                                f13 = (float) d3;
                                z9 = z7;
                            }
                            int n = n(i2, bVar, cVar2.m);
                            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                            reorderedFlexItemAt.measure(n, makeMeasureSpec2);
                            int measuredWidth4 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                            v(i18, n, makeMeasureSpec2, reorderedFlexItemAt);
                            aVar.updateViewCache(i18, reorderedFlexItemAt);
                            measuredWidth3 = measuredWidth4;
                            measuredHeight3 = measuredHeight4;
                        }
                        i14 = Math.max(i17, aVar.getDecorationLengthCrossAxis(reorderedFlexItemAt) + bVar.t() + bVar.r() + measuredWidth3);
                        cVar2.e = bVar.q() + bVar.g() + measuredHeight3 + cVar2.e;
                        f = f8;
                        z3 = z9;
                        int i24 = i7;
                        i13 = i16;
                    }
                    cVar2.g = Math.max(cVar2.g, i14);
                    i17 = i14;
                }
                i16 = i13 + 1;
                int i25 = i8;
                f12 = f;
                f11 = f5;
                i11 = i12;
                z10 = z3;
            }
            int i26 = i7;
            int i27 = i11;
            int i28 = i2;
            if (z10 && i27 != cVar2.e) {
                l(i28, i26, cVar2, i8, i10, true);
            }
        }
    }

    public final int m(int i2, b bVar, int i7) {
        C0265a aVar = this.f1861a;
        int childHeightMeasureSpec = aVar.getChildHeightMeasureSpec(i2, bVar.q() + bVar.g() + aVar.getPaddingBottom() + aVar.getPaddingTop() + i7, bVar.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (size > bVar.w()) {
            return View.MeasureSpec.makeMeasureSpec(bVar.w(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        if (size < bVar.v()) {
            return View.MeasureSpec.makeMeasureSpec(bVar.v(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return childHeightMeasureSpec;
    }

    public final int n(int i2, b bVar, int i7) {
        C0265a aVar = this.f1861a;
        int childWidthMeasureSpec = aVar.getChildWidthMeasureSpec(i2, bVar.t() + bVar.r() + aVar.getPaddingRight() + aVar.getPaddingLeft() + i7, bVar.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (size > bVar.n()) {
            return View.MeasureSpec.makeMeasureSpec(bVar.n(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        if (size < bVar.f()) {
            return View.MeasureSpec.makeMeasureSpec(bVar.f(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return childWidthMeasureSpec;
    }

    public final void o(View view, c cVar, int i2, int i7, int i8, int i10) {
        b bVar = (b) view.getLayoutParams();
        C0265a aVar = this.f1861a;
        int alignItems = aVar.getAlignItems();
        if (bVar.a() != -1) {
            alignItems = bVar.a();
        }
        int i11 = cVar.g;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    int g = ((bVar.g() + (i11 - view.getMeasuredHeight())) - bVar.q()) / 2;
                    if (aVar.getFlexWrap() != 2) {
                        int i12 = i7 + g;
                        view.layout(i2, i12, i8, view.getMeasuredHeight() + i12);
                        return;
                    }
                    int i13 = i7 - g;
                    view.layout(i2, i13, i8, view.getMeasuredHeight() + i13);
                    return;
                } else if (alignItems != 3) {
                    if (alignItems != 4) {
                        return;
                    }
                } else if (aVar.getFlexWrap() != 2) {
                    int max = Math.max(cVar.l - view.getBaseline(), bVar.g());
                    view.layout(i2, i7 + max, i8, i10 + max);
                    return;
                } else {
                    int max2 = Math.max(view.getBaseline() + (cVar.l - view.getMeasuredHeight()), bVar.q());
                    view.layout(i2, i7 - max2, i8, i10 - max2);
                    return;
                }
            } else if (aVar.getFlexWrap() != 2) {
                int i14 = i7 + i11;
                view.layout(i2, (i14 - view.getMeasuredHeight()) - bVar.q(), i8, i14 - bVar.q());
                return;
            } else {
                int measuredHeight = view.getMeasuredHeight();
                int g3 = bVar.g() + measuredHeight + (i7 - i11);
                int measuredHeight2 = view.getMeasuredHeight();
                view.layout(i2, g3, i8, bVar.g() + measuredHeight2 + (i10 - i11));
                return;
            }
        }
        if (aVar.getFlexWrap() != 2) {
            view.layout(i2, bVar.g() + i7, i8, bVar.g() + i10);
        } else {
            view.layout(i2, i7 - bVar.q(), i8, i10 - bVar.q());
        }
    }

    public final void p(View view, c cVar, boolean z, int i2, int i7, int i8, int i10) {
        b bVar = (b) view.getLayoutParams();
        int alignItems = this.f1861a.getAlignItems();
        if (bVar.a() != -1) {
            alignItems = bVar.a();
        }
        int i11 = cVar.g;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int marginStart = ((MarginLayoutParamsCompat.getMarginStart(marginLayoutParams) + (i11 - view.getMeasuredWidth())) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                    if (!z) {
                        view.layout(i2 + marginStart, i7, i8 + marginStart, i10);
                        return;
                    } else {
                        view.layout(i2 - marginStart, i7, i8 - marginStart, i10);
                        return;
                    }
                } else if (!(alignItems == 3 || alignItems == 4)) {
                    return;
                }
            } else if (!z) {
                view.layout(((i2 + i11) - view.getMeasuredWidth()) - bVar.t(), i7, ((i8 + i11) - view.getMeasuredWidth()) - bVar.t(), i10);
                return;
            } else {
                int measuredWidth = view.getMeasuredWidth();
                int r = bVar.r() + measuredWidth + (i2 - i11);
                int measuredWidth2 = view.getMeasuredWidth();
                view.layout(r, i7, bVar.r() + measuredWidth2 + (i8 - i11), i10);
                return;
            }
        }
        if (!z) {
            view.layout(bVar.r() + i2, i7, bVar.r() + i8, i10);
        } else {
            view.layout(i2 - bVar.t(), i7, i8 - bVar.t(), i10);
        }
    }

    public final void q(int i2, int i7, c cVar, int i8, int i10, boolean z) {
        float f;
        float f5;
        int i11;
        int i12;
        int i13;
        c cVar2 = cVar;
        int i14 = i8;
        int i15 = cVar2.e;
        float f8 = cVar2.k;
        float f10 = 0.0f;
        if (f8 > 0.0f && i14 <= i15) {
            float f11 = ((float) (i15 - i14)) / f8;
            cVar2.e = i10 + cVar2.f;
            if (!z) {
                cVar2.g = Integer.MIN_VALUE;
            }
            int i16 = 0;
            boolean z3 = false;
            int i17 = 0;
            float f12 = 0.0f;
            while (i16 < cVar2.f1855h) {
                int i18 = cVar2.f1858o + i16;
                C0265a aVar = this.f1861a;
                View reorderedFlexItemAt = aVar.getReorderedFlexItemAt(i18);
                if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                    f5 = f10;
                    f = f11;
                    int i19 = i7;
                } else {
                    b bVar = (b) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = aVar.getFlexDirection();
                    f5 = f10;
                    if (flexDirection == 0 || flexDirection == 1) {
                        f = f11;
                        int i20 = i2;
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr = this.e;
                        if (jArr != null) {
                            measuredWidth = (int) jArr[i18];
                        }
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr2 = this.e;
                        if (jArr2 != null) {
                            measuredHeight = (int) (jArr2[i18] >> 32);
                        }
                        if (this.b[i18] || bVar.c() <= f5) {
                            int i21 = i7;
                        } else {
                            float c5 = ((float) measuredWidth) - (bVar.c() * f);
                            if (i16 == cVar2.f1855h - 1) {
                                c5 += f12;
                                f12 = f5;
                            }
                            int round = Math.round(c5);
                            if (round < bVar.f()) {
                                i12 = bVar.f();
                                this.b[i18] = true;
                                cVar2.k -= bVar.c();
                                z3 = true;
                            } else {
                                float f13 = (c5 - ((float) round)) + f12;
                                int i22 = round;
                                double d2 = (double) f13;
                                if (d2 > 1.0d) {
                                    i12 = i22 + 1;
                                    f13 -= 1.0f;
                                } else if (d2 < -1.0d) {
                                    i12 = i22 - 1;
                                    f13 += 1.0f;
                                } else {
                                    i12 = i22;
                                }
                                f12 = f13;
                            }
                            int m = m(i7, bVar, cVar2.m);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i12, 1073741824);
                            reorderedFlexItemAt.measure(makeMeasureSpec, m);
                            int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                            v(i18, makeMeasureSpec, m, reorderedFlexItemAt);
                            aVar.updateViewCache(i18, reorderedFlexItemAt);
                            measuredWidth = measuredWidth2;
                            measuredHeight = measuredHeight2;
                        }
                        int max = Math.max(i17, aVar.getDecorationLengthCrossAxis(reorderedFlexItemAt) + bVar.q() + bVar.g() + measuredHeight);
                        cVar2.e = bVar.t() + bVar.r() + measuredWidth + cVar2.e;
                        i11 = max;
                    } else {
                        int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr3 = this.e;
                        if (jArr3 != null) {
                            measuredHeight3 = (int) (jArr3[i18] >> 32);
                        }
                        int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr4 = this.e;
                        if (jArr4 != null) {
                            measuredWidth3 = (int) jArr4[i18];
                        }
                        if (this.b[i18] || bVar.c() <= f5) {
                            f = f11;
                            int i23 = i2;
                        } else {
                            float c6 = ((float) measuredHeight3) - (bVar.c() * f11);
                            if (i16 == cVar2.f1855h - 1) {
                                c6 += f12;
                                f12 = f5;
                            }
                            int round2 = Math.round(c6);
                            if (round2 < bVar.v()) {
                                i13 = bVar.v();
                                this.b[i18] = true;
                                cVar2.k -= bVar.c();
                                z3 = true;
                                f = f11;
                            } else {
                                float f14 = (c6 - ((float) round2)) + f12;
                                int i24 = round2;
                                f = f11;
                                double d3 = (double) f14;
                                if (d3 > 1.0d) {
                                    i13 = i24 + 1;
                                    f14 -= 1.0f;
                                } else if (d3 < -1.0d) {
                                    i13 = i24 - 1;
                                    f14 += 1.0f;
                                } else {
                                    i13 = i24;
                                }
                                f12 = f14;
                            }
                            int n = n(i2, bVar, cVar2.m);
                            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i13, 1073741824);
                            reorderedFlexItemAt.measure(n, makeMeasureSpec2);
                            int measuredWidth4 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                            v(i18, n, makeMeasureSpec2, reorderedFlexItemAt);
                            aVar.updateViewCache(i18, reorderedFlexItemAt);
                            measuredWidth3 = measuredWidth4;
                            measuredHeight3 = measuredHeight4;
                        }
                        i11 = Math.max(i17, aVar.getDecorationLengthCrossAxis(reorderedFlexItemAt) + bVar.t() + bVar.r() + measuredWidth3);
                        cVar2.e = bVar.q() + bVar.g() + measuredHeight3 + cVar2.e;
                        int i25 = i7;
                    }
                    cVar2.g = Math.max(cVar2.g, i11);
                    i17 = i11;
                }
                i16++;
                int i26 = i8;
                f10 = f5;
                f11 = f;
            }
            int i27 = i7;
            if (z3 && i15 != cVar2.e) {
                q(i2, i7, cVar2, i8, i10, true);
            }
        }
    }

    public final void s(int i2, int i7, View view) {
        int i8;
        b bVar = (b) view.getLayoutParams();
        int r = (i2 - bVar.r()) - bVar.t();
        C0265a aVar = this.f1861a;
        int min = Math.min(Math.max(r - aVar.getDecorationLengthCrossAxis(view), bVar.f()), bVar.n());
        long[] jArr = this.e;
        if (jArr != null) {
            i8 = (int) (jArr[i7] >> 32);
        } else {
            i8 = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        v(i7, makeMeasureSpec2, makeMeasureSpec, view);
        aVar.updateViewCache(i7, view);
    }

    public final void t(int i2, int i7, View view) {
        int i8;
        b bVar = (b) view.getLayoutParams();
        int g = (i2 - bVar.g()) - bVar.q();
        C0265a aVar = this.f1861a;
        int min = Math.min(Math.max(g - aVar.getDecorationLengthCrossAxis(view), bVar.v()), bVar.w());
        long[] jArr = this.e;
        if (jArr != null) {
            i8 = (int) jArr[i7];
        } else {
            i8 = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        v(i7, makeMeasureSpec, makeMeasureSpec2, view);
        aVar.updateViewCache(i7, view);
    }

    public final void u(int i2) {
        int i7;
        View reorderedFlexItemAt;
        int i8 = i2;
        C0265a aVar = this.f1861a;
        if (i8 < aVar.getFlexItemCount()) {
            int flexDirection = aVar.getFlexDirection();
            if (aVar.getAlignItems() == 4) {
                int[] iArr = this.f1862c;
                if (iArr != null) {
                    i7 = iArr[i8];
                } else {
                    i7 = 0;
                }
                List flexLinesInternal = aVar.getFlexLinesInternal();
                int size = flexLinesInternal.size();
                while (i7 < size) {
                    c cVar = (c) flexLinesInternal.get(i7);
                    int i10 = cVar.f1855h;
                    for (int i11 = 0; i11 < i10; i11++) {
                        int i12 = cVar.f1858o + i11;
                        if (!(i11 >= aVar.getFlexItemCount() || (reorderedFlexItemAt = aVar.getReorderedFlexItemAt(i12)) == null || reorderedFlexItemAt.getVisibility() == 8)) {
                            b bVar = (b) reorderedFlexItemAt.getLayoutParams();
                            if (bVar.a() == -1 || bVar.a() == 4) {
                                if (flexDirection == 0 || flexDirection == 1) {
                                    t(cVar.g, i12, reorderedFlexItemAt);
                                } else if (flexDirection == 2 || flexDirection == 3) {
                                    s(cVar.g, i12, reorderedFlexItemAt);
                                } else {
                                    throw new IllegalArgumentException(C0086a.i(flexDirection, "Invalid flex direction: "));
                                }
                            }
                        }
                    }
                    i7++;
                }
                return;
            }
            for (c cVar2 : aVar.getFlexLinesInternal()) {
                Iterator it = cVar2.n.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Integer num = (Integer) it.next();
                        View reorderedFlexItemAt2 = aVar.getReorderedFlexItemAt(num.intValue());
                        if (flexDirection == 0 || flexDirection == 1) {
                            t(cVar2.g, num.intValue(), reorderedFlexItemAt2);
                        } else if (flexDirection == 2 || flexDirection == 3) {
                            s(cVar2.g, num.intValue(), reorderedFlexItemAt2);
                        } else {
                            throw new IllegalArgumentException(C0086a.i(flexDirection, "Invalid flex direction: "));
                        }
                    }
                }
            }
        }
    }

    public final void v(int i2, int i7, int i8, View view) {
        long[] jArr = this.d;
        if (jArr != null) {
            jArr[i2] = (((long) i7) & 4294967295L) | (((long) i8) << 32);
        }
        long[] jArr2 = this.e;
        if (jArr2 != null) {
            jArr2[i2] = (((long) view.getMeasuredHeight()) << 32) | (((long) view.getMeasuredWidth()) & 4294967295L);
        }
    }
}
