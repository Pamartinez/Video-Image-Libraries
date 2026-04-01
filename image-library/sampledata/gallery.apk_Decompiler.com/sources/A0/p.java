package A0;

import D0.e;
import K0.a;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends e {

    /* renamed from: i  reason: collision with root package name */
    public final PointF f17i = new PointF();

    /* renamed from: j  reason: collision with root package name */
    public final PointF f18j = new PointF();
    public final i k;
    public final i l;
    public e m;
    public e n;

    public p(i iVar, i iVar2) {
        super(Collections.EMPTY_LIST);
        this.k = iVar;
        this.l = iVar2;
        j(this.d);
    }

    public final Object f() {
        return l(0.0f);
    }

    public final /* bridge */ /* synthetic */ Object g(a aVar, float f) {
        return l(f);
    }

    public final void j(float f) {
        i iVar = this.k;
        iVar.j(f);
        i iVar2 = this.l;
        iVar2.j(f);
        this.f17i.set(((Float) iVar.f()).floatValue(), ((Float) iVar2.f()).floatValue());
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f1a;
            if (i2 < arrayList.size()) {
                ((a) arrayList.get(i2)).a();
                i2++;
            } else {
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Float} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.PointF l(float r12) {
        /*
            r11 = this;
            D0.e r0 = r11.m
            r1 = 0
            if (r0 == 0) goto L_0x0034
            A0.i r0 = r11.k
            K0.a r2 = r0.b()
            if (r2 == 0) goto L_0x0034
            float r10 = r0.d()
            java.lang.Float r0 = r2.f371h
            D0.e r3 = r11.m
            float r4 = r2.g
            if (r0 != 0) goto L_0x001b
            r5 = r4
            goto L_0x0020
        L_0x001b:
            float r0 = r0.floatValue()
            r5 = r0
        L_0x0020:
            java.lang.Object r0 = r2.b
            r6 = r0
            java.lang.Float r6 = (java.lang.Float) r6
            java.lang.Object r0 = r2.f370c
            r7 = r0
            java.lang.Float r7 = (java.lang.Float) r7
            r9 = r12
            r8 = r12
            java.lang.Object r12 = r3.L(r4, r5, r6, r7, r8, r9, r10)
            r7 = r8
            java.lang.Float r12 = (java.lang.Float) r12
            goto L_0x0036
        L_0x0034:
            r7 = r12
            r12 = r1
        L_0x0036:
            D0.e r0 = r11.n
            if (r0 == 0) goto L_0x0068
            A0.i r0 = r11.l
            K0.a r2 = r0.b()
            if (r2 == 0) goto L_0x0068
            float r9 = r0.d()
            java.lang.Float r0 = r2.f371h
            r1 = r2
            D0.e r2 = r11.n
            float r3 = r1.g
            if (r0 != 0) goto L_0x0051
            r4 = r3
            goto L_0x0056
        L_0x0051:
            float r0 = r0.floatValue()
            r4 = r0
        L_0x0056:
            java.lang.Object r0 = r1.b
            r5 = r0
            java.lang.Float r5 = (java.lang.Float) r5
            java.lang.Object r0 = r1.f370c
            r6 = r0
            java.lang.Float r6 = (java.lang.Float) r6
            r8 = r7
            java.lang.Object r0 = r2.L(r3, r4, r5, r6, r7, r8, r9)
            r1 = r0
            java.lang.Float r1 = (java.lang.Float) r1
        L_0x0068:
            r0 = 0
            android.graphics.PointF r2 = r11.f17i
            android.graphics.PointF r11 = r11.f18j
            if (r12 != 0) goto L_0x0075
            float r12 = r2.x
            r11.set(r12, r0)
            goto L_0x007c
        L_0x0075:
            float r12 = r12.floatValue()
            r11.set(r12, r0)
        L_0x007c:
            if (r1 != 0) goto L_0x0086
            float r12 = r11.x
            float r0 = r2.y
            r11.set(r12, r0)
            return r11
        L_0x0086:
            float r12 = r11.x
            float r0 = r1.floatValue()
            r11.set(r12, r0)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.p.l(float):android.graphics.PointF");
    }
}
