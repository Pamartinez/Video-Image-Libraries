package x0;

import B0.a;
import B0.b;
import B2.h;
import C0.f;
import C0.g;
import F0.i;
import F0.l;
import H0.s;
import J0.c;
import J0.d;
import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Choreographer;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w extends Drawable implements Drawable.Callback, Animatable {
    public static final List T = Arrays.asList(new String[]{"reduced motion", "reduced_motion", "reduced-motion", "reducedmotion"});
    public static final ThreadPoolExecutor U = new ThreadPoolExecutor(0, 2, 35, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new c());

    /* renamed from: A  reason: collision with root package name */
    public H f2081A = H.AUTOMATIC;
    public boolean B = false;

    /* renamed from: C  reason: collision with root package name */
    public final Matrix f2082C = new Matrix();
    public Bitmap D;
    public Canvas E;

    /* renamed from: F  reason: collision with root package name */
    public Rect f2083F;

    /* renamed from: G  reason: collision with root package name */
    public RectF f2084G;

    /* renamed from: H  reason: collision with root package name */
    public l f2085H;

    /* renamed from: I  reason: collision with root package name */
    public Rect f2086I;

    /* renamed from: J  reason: collision with root package name */
    public Rect f2087J;

    /* renamed from: K  reason: collision with root package name */
    public RectF f2088K;
    public RectF L;

    /* renamed from: M  reason: collision with root package name */
    public Matrix f2089M;

    /* renamed from: N  reason: collision with root package name */
    public Matrix f2090N;

    /* renamed from: O  reason: collision with root package name */
    public boolean f2091O = false;

    /* renamed from: P  reason: collision with root package name */
    public C0323a f2092P;
    public final Semaphore Q;
    public final e R;
    public float S;
    public C0332j d;
    public final d e;
    public boolean f = true;
    public boolean g = false;

    /* renamed from: h  reason: collision with root package name */
    public boolean f2093h = false;

    /* renamed from: i  reason: collision with root package name */
    public v f2094i = v.NONE;

    /* renamed from: j  reason: collision with root package name */
    public final ArrayList f2095j = new ArrayList();
    public b k;
    public String l;
    public C0325c m;
    public a n;

    /* renamed from: o  reason: collision with root package name */
    public Map f2096o;

    /* renamed from: p  reason: collision with root package name */
    public String f2097p;
    public J q;
    public final G0.c r = new G0.c(18);
    public boolean s = false;
    public boolean t = true;
    public F0.e u;
    public int v = ScoverState.TYPE_NFC_SMART_COVER;
    public boolean w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f2098x;
    public boolean y;
    public boolean z = false;

    public w() {
        d dVar = new d();
        this.e = dVar;
        h hVar = new h(18, this);
        this.Q = new Semaphore(1);
        this.R = new e(22, this);
        this.S = -3.4028235E38f;
        dVar.addUpdateListener(hVar);
    }

    public static void f(Rect rect, RectF rectF) {
        rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
    }

    public final void a(f fVar, Object obj, D0.e eVar) {
        F0.e eVar2 = this.u;
        if (eVar2 == null) {
            this.f2095j.add(new q(this, fVar, obj, eVar));
            return;
        }
        boolean z3 = true;
        if (fVar == f.f96c) {
            eVar2.d(eVar, obj);
        } else {
            g gVar = fVar.b;
            if (gVar != null) {
                gVar.d(eVar, obj);
            } else {
                ArrayList arrayList = new ArrayList();
                this.u.c(fVar, 0, arrayList, new f(new String[0]));
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    ((f) arrayList.get(i2)).b.d(eVar, obj);
                }
                z3 = true ^ arrayList.isEmpty();
            }
        }
        if (z3) {
            invalidateSelf();
            if (obj == C0319A.z) {
                u(this.e.a());
            }
        }
    }

    public final boolean b() {
        if (this.f || this.g) {
            return true;
        }
        return false;
    }

    public final void c() {
        C0332j jVar = this.d;
        if (jVar != null) {
            D0.e eVar = s.f326a;
            Rect rect = jVar.k;
            List list = Collections.EMPTY_LIST;
            F0.g gVar = F0.g.PRE_COMP;
            D0.g gVar2 = new D0.g();
            float height = (float) rect.height();
            List list2 = list;
            F0.e eVar2 = new F0.e(this, new i(list, jVar, "__container", -1, gVar, -1, (String) null, list2, gVar2, 0, 0, 0, 0.0f, 0.0f, (float) rect.width(), height, (D0.a) null, (D0.e) null, list, F0.h.NONE, (D0.b) null, false, (G0.e) null, (a) null, E0.h.NORMAL), jVar.f2060j, jVar);
            this.u = eVar2;
            if (this.f2098x) {
                eVar2.p(true);
            }
            this.u.f196J = this.t;
        }
    }

    public final void d() {
        d dVar = this.e;
        if (dVar.f360p) {
            dVar.cancel();
            if (!isVisible()) {
                this.f2094i = v.NONE;
            }
        }
        this.d = null;
        this.u = null;
        this.k = null;
        this.S = -3.4028235E38f;
        dVar.f359o = null;
        dVar.m = -2.14748365E9f;
        dVar.n = 2.14748365E9f;
        invalidateSelf();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:30|31) */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        J0.b.f353a.getClass();
        r11 = x0.C0326d.f2049a;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x005d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void draw(android.graphics.Canvas r11) {
        /*
            r10 = this;
            F0.e r0 = r10.u
            if (r0 != 0) goto L_0x0006
            goto L_0x00ac
        L_0x0006:
            x0.a r1 = r10.f2092P
            if (r1 == 0) goto L_0x000b
            goto L_0x000d
        L_0x000b:
            x0.a r1 = x0.C0326d.f2049a
        L_0x000d:
            x0.a r2 = x0.C0323a.ENABLED
            r3 = 0
            if (r1 != r2) goto L_0x0014
            r1 = 1
            goto L_0x0015
        L_0x0014:
            r1 = r3
        L_0x0015:
            t8.e r2 = r10.R
            java.util.concurrent.ThreadPoolExecutor r4 = U
            java.util.concurrent.Semaphore r5 = r10.Q
            J0.d r6 = r10.e
            if (r1 == 0) goto L_0x0025
            r5.acquire()     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            goto L_0x0025
        L_0x0023:
            r10 = move-exception
            goto L_0x0085
        L_0x0025:
            x0.a r7 = x0.C0326d.f2049a     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            if (r1 == 0) goto L_0x004d
            x0.j r7 = r10.d     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            if (r7 != 0) goto L_0x002e
            goto L_0x004d
        L_0x002e:
            float r8 = r10.S     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            float r9 = r6.a()     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            r10.S = r9     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            float r7 = r7.b()     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            float r9 = r9 - r8
            float r8 = java.lang.Math.abs(r9)     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            float r8 = r8 * r7
            r7 = 1112014848(0x42480000, float:50.0)
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x004d
            float r7 = r6.a()     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            r10.u(r7)     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
        L_0x004d:
            boolean r7 = r10.f2093h     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            if (r7 == 0) goto L_0x0065
            boolean r7 = r10.B     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x0059
            r10.l(r11, r0)     // Catch:{ all -> 0x005d }
            goto L_0x0070
        L_0x0059:
            r10.g(r11)     // Catch:{ all -> 0x005d }
            goto L_0x0070
        L_0x005d:
            J0.a r11 = J0.b.f353a     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            r11.getClass()     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            x0.a r11 = x0.C0326d.f2049a     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            goto L_0x0070
        L_0x0065:
            boolean r7 = r10.B     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            if (r7 == 0) goto L_0x006d
            r10.l(r11, r0)     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            goto L_0x0070
        L_0x006d:
            r10.g(r11)     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
        L_0x0070:
            r10.f2091O = r3     // Catch:{ InterruptedException -> 0x009a, all -> 0x0023 }
            if (r1 == 0) goto L_0x00ac
            r5.release()
            float r10 = r0.f195I
            float r11 = r6.a()
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 == 0) goto L_0x00ac
        L_0x0081:
            r4.execute(r2)
            goto L_0x00ac
        L_0x0085:
            x0.a r11 = x0.C0326d.f2049a
            if (r1 == 0) goto L_0x0099
            r5.release()
            float r11 = r0.f195I
            float r0 = r6.a()
            int r11 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r11 == 0) goto L_0x0099
            r4.execute(r2)
        L_0x0099:
            throw r10
        L_0x009a:
            x0.a r10 = x0.C0326d.f2049a
            if (r1 == 0) goto L_0x00ac
            r5.release()
            float r10 = r0.f195I
            float r11 = r6.a()
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 == 0) goto L_0x00ac
            goto L_0x0081
        L_0x00ac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.w.draw(android.graphics.Canvas):void");
    }

    public final void e() {
        C0332j jVar = this.d;
        if (jVar != null) {
            H h5 = this.f2081A;
            int i2 = jVar.f2061o;
            h5.getClass();
            int i7 = G.f2048a[h5.ordinal()];
            boolean z3 = false;
            if (i7 != 1 && (i7 == 2 || i2 > 4)) {
                z3 = true;
            }
            this.B = z3;
        }
    }

    public final void g(Canvas canvas) {
        F0.e eVar = this.u;
        C0332j jVar = this.d;
        if (eVar != null && jVar != null) {
            Matrix matrix = this.f2082C;
            matrix.reset();
            Rect bounds = getBounds();
            if (!bounds.isEmpty()) {
                matrix.preScale(((float) bounds.width()) / ((float) jVar.k.width()), ((float) bounds.height()) / ((float) jVar.k.height()));
                matrix.preTranslate((float) bounds.left, (float) bounds.top);
            }
            eVar.g(canvas, matrix, this.v);
        }
    }

    public final int getAlpha() {
        return this.v;
    }

    public final int getIntrinsicHeight() {
        C0332j jVar = this.d;
        if (jVar == null) {
            return -1;
        }
        return jVar.k.height();
    }

    public final int getIntrinsicWidth() {
        C0332j jVar = this.d;
        if (jVar == null) {
            return -1;
        }
        return jVar.k.width();
    }

    public final int getOpacity() {
        return -3;
    }

    public final a h() {
        if (getCallback() == null) {
            return null;
        }
        if (this.n == null) {
            a aVar = new a(getCallback());
            this.n = aVar;
            String str = this.f2097p;
            if (str != null) {
                aVar.f34h = str;
            }
        }
        return this.n;
    }

    public final boolean i() {
        d dVar = this.e;
        if (dVar == null) {
            return false;
        }
        return dVar.f360p;
    }

    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public final void invalidateSelf() {
        if (!this.f2091O) {
            this.f2091O = true;
            Drawable.Callback callback = getCallback();
            if (callback != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    public final boolean isRunning() {
        return i();
    }

    public final void j() {
        this.f2095j.clear();
        d dVar = this.e;
        dVar.g(true);
        Iterator it = dVar.f.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorPauseListener) it.next()).onAnimationPause(dVar);
        }
        if (!isVisible()) {
            this.f2094i = v.NONE;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083 A[LOOP:1: B:26:0x0083->B:29:0x0095, LOOP_START, PHI: r3 
      PHI: (r3v1 C0.i) = (r3v0 C0.i), (r3v6 C0.i) binds: [B:25:0x007c, B:29:0x0095] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void k() {
        /*
            r5 = this;
            F0.e r0 = r5.u
            if (r0 != 0) goto L_0x0010
            x0.t r0 = new x0.t
            r1 = 1
            r0.<init>(r5, r1)
            java.util.ArrayList r5 = r5.f2095j
            r5.add(r0)
            return
        L_0x0010:
            r5.e()
            boolean r0 = r5.b()
            r1 = 1
            J0.d r2 = r5.e
            if (r0 != 0) goto L_0x0022
            int r0 = r2.getRepeatCount()
            if (r0 != 0) goto L_0x0076
        L_0x0022:
            boolean r0 = r5.isVisible()
            if (r0 == 0) goto L_0x0072
            r2.f360p = r1
            boolean r0 = r2.d()
            java.util.concurrent.CopyOnWriteArraySet r3 = r2.e
            java.util.Iterator r3 = r3.iterator()
        L_0x0034:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0044
            java.lang.Object r4 = r3.next()
            android.animation.Animator$AnimatorListener r4 = (android.animation.Animator.AnimatorListener) r4
            r4.onAnimationStart(r2, r0)
            goto L_0x0034
        L_0x0044:
            boolean r0 = r2.d()
            if (r0 == 0) goto L_0x004f
            float r0 = r2.b()
            goto L_0x0053
        L_0x004f:
            float r0 = r2.c()
        L_0x0053:
            int r0 = (int) r0
            float r0 = (float) r0
            r2.h(r0)
            r3 = 0
            r2.f357i = r3
            r0 = 0
            r2.l = r0
            boolean r3 = r2.f360p
            if (r3 == 0) goto L_0x006d
            r2.g(r0)
            android.view.Choreographer r0 = android.view.Choreographer.getInstance()
            r0.postFrameCallback(r2)
        L_0x006d:
            x0.v r0 = x0.v.NONE
            r5.f2094i = r0
            goto L_0x0076
        L_0x0072:
            x0.v r0 = x0.v.PLAY
            r5.f2094i = r0
        L_0x0076:
            boolean r0 = r5.b()
            if (r0 != 0) goto L_0x00c8
            java.util.List r0 = T
            java.util.Iterator r0 = r0.iterator()
            r3 = 0
        L_0x0083:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0097
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            x0.j r4 = r5.d
            C0.i r3 = r4.e(r3)
            if (r3 == 0) goto L_0x0083
        L_0x0097:
            if (r3 == 0) goto L_0x00a0
            float r0 = r3.b
            int r0 = (int) r0
            r5.o(r0)
            goto L_0x00b4
        L_0x00a0:
            float r0 = r2.g
            r3 = 0
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ac
            float r0 = r2.c()
            goto L_0x00b0
        L_0x00ac:
            float r0 = r2.b()
        L_0x00b0:
            int r0 = (int) r0
            r5.o(r0)
        L_0x00b4:
            r2.g(r1)
            boolean r0 = r2.d()
            r2.e(r0)
            boolean r0 = r5.isVisible()
            if (r0 != 0) goto L_0x00c8
            x0.v r0 = x0.v.NONE
            r5.f2094i = r0
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.w.k():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void l(android.graphics.Canvas r10, F0.e r11) {
        /*
            r9 = this;
            x0.j r0 = r9.d
            if (r0 == 0) goto L_0x01a9
            if (r11 != 0) goto L_0x0008
            goto L_0x01a9
        L_0x0008:
            android.graphics.Canvas r0 = r9.E
            if (r0 == 0) goto L_0x000d
            goto L_0x0053
        L_0x000d:
            android.graphics.Canvas r0 = new android.graphics.Canvas
            r0.<init>()
            r9.E = r0
            android.graphics.RectF r0 = new android.graphics.RectF
            r0.<init>()
            r9.L = r0
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            r9.f2089M = r0
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            r9.f2090N = r0
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r9.f2083F = r0
            android.graphics.RectF r0 = new android.graphics.RectF
            r0.<init>()
            r9.f2084G = r0
            F0.l r0 = new F0.l
            r0.<init>()
            r9.f2085H = r0
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r9.f2086I = r0
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r9.f2087J = r0
            android.graphics.RectF r0 = new android.graphics.RectF
            r0.<init>()
            r9.f2088K = r0
        L_0x0053:
            android.graphics.Matrix r0 = r9.f2089M
            r10.getMatrix(r0)
            android.graphics.Rect r0 = r9.f2083F
            r10.getClipBounds(r0)
            android.graphics.Rect r0 = r9.f2083F
            android.graphics.RectF r1 = r9.f2084G
            int r2 = r0.left
            float r2 = (float) r2
            int r3 = r0.top
            float r3 = (float) r3
            int r4 = r0.right
            float r4 = (float) r4
            int r0 = r0.bottom
            float r0 = (float) r0
            r1.set(r2, r3, r4, r0)
            android.graphics.Matrix r0 = r9.f2089M
            android.graphics.RectF r1 = r9.f2084G
            r0.mapRect(r1)
            android.graphics.RectF r0 = r9.f2084G
            android.graphics.Rect r1 = r9.f2083F
            f(r1, r0)
            boolean r0 = r9.t
            r1 = 0
            if (r0 == 0) goto L_0x0094
            android.graphics.RectF r0 = r9.L
            int r2 = r9.getIntrinsicWidth()
            float r2 = (float) r2
            int r3 = r9.getIntrinsicHeight()
            float r3 = (float) r3
            r4 = 0
            r0.set(r4, r4, r2, r3)
            goto L_0x009a
        L_0x0094:
            android.graphics.RectF r0 = r9.L
            r2 = 0
            r11.e(r0, r2, r1)
        L_0x009a:
            android.graphics.Matrix r0 = r9.f2089M
            android.graphics.RectF r2 = r9.L
            r0.mapRect(r2)
            android.graphics.Rect r0 = r9.getBounds()
            int r2 = r0.width()
            float r2 = (float) r2
            int r3 = r9.getIntrinsicWidth()
            float r3 = (float) r3
            float r2 = r2 / r3
            int r0 = r0.height()
            float r0 = (float) r0
            int r3 = r9.getIntrinsicHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            android.graphics.RectF r3 = r9.L
            float r4 = r3.left
            float r4 = r4 * r2
            float r5 = r3.top
            float r5 = r5 * r0
            float r6 = r3.right
            float r6 = r6 * r2
            float r7 = r3.bottom
            float r7 = r7 * r0
            r3.set(r4, r5, r6, r7)
            android.graphics.drawable.Drawable$Callback r3 = r9.getCallback()
            boolean r4 = r3 instanceof android.view.View
            r5 = 1
            if (r4 != 0) goto L_0x00d7
        L_0x00d5:
            r3 = r1
            goto L_0x00e8
        L_0x00d7:
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r3 = r3.getParent()
            boolean r4 = r3 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x00d5
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            boolean r3 = r3.getClipChildren()
            r3 = r3 ^ r5
        L_0x00e8:
            if (r3 != 0) goto L_0x00fd
            android.graphics.RectF r3 = r9.L
            android.graphics.Rect r4 = r9.f2083F
            int r6 = r4.left
            float r6 = (float) r6
            int r7 = r4.top
            float r7 = (float) r7
            int r8 = r4.right
            float r8 = (float) r8
            int r4 = r4.bottom
            float r4 = (float) r4
            r3.intersect(r6, r7, r8, r4)
        L_0x00fd:
            android.graphics.RectF r3 = r9.L
            float r3 = r3.width()
            double r3 = (double) r3
            double r3 = java.lang.Math.ceil(r3)
            int r3 = (int) r3
            android.graphics.RectF r4 = r9.L
            float r4 = r4.height()
            double r6 = (double) r4
            double r6 = java.lang.Math.ceil(r6)
            int r4 = (int) r6
            if (r3 <= 0) goto L_0x01a9
            if (r4 > 0) goto L_0x011b
            goto L_0x01a9
        L_0x011b:
            android.graphics.Bitmap r6 = r9.D
            if (r6 == 0) goto L_0x014e
            int r6 = r6.getWidth()
            if (r6 < r3) goto L_0x014e
            android.graphics.Bitmap r6 = r9.D
            int r6 = r6.getHeight()
            if (r6 >= r4) goto L_0x012e
            goto L_0x014e
        L_0x012e:
            android.graphics.Bitmap r6 = r9.D
            int r6 = r6.getWidth()
            if (r6 > r3) goto L_0x013e
            android.graphics.Bitmap r6 = r9.D
            int r6 = r6.getHeight()
            if (r6 <= r4) goto L_0x015d
        L_0x013e:
            android.graphics.Bitmap r6 = r9.D
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r6, r1, r1, r3, r4)
            r9.D = r6
            android.graphics.Canvas r7 = r9.E
            r7.setBitmap(r6)
            r9.f2091O = r5
            goto L_0x015d
        L_0x014e:
            android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r3, r4, r6)
            r9.D = r6
            android.graphics.Canvas r7 = r9.E
            r7.setBitmap(r6)
            r9.f2091O = r5
        L_0x015d:
            boolean r5 = r9.f2091O
            if (r5 == 0) goto L_0x0199
            android.graphics.Matrix r5 = r9.f2089M
            android.graphics.Matrix r6 = r9.f2082C
            r6.set(r5)
            r6.preScale(r2, r0)
            android.graphics.RectF r0 = r9.L
            float r2 = r0.left
            float r2 = -r2
            float r0 = r0.top
            float r0 = -r0
            r6.postTranslate(r2, r0)
            android.graphics.Bitmap r0 = r9.D
            r0.eraseColor(r1)
            android.graphics.Canvas r0 = r9.E
            int r2 = r9.v
            r11.g(r0, r6, r2)
            android.graphics.Matrix r11 = r9.f2089M
            android.graphics.Matrix r0 = r9.f2090N
            r11.invert(r0)
            android.graphics.Matrix r11 = r9.f2090N
            android.graphics.RectF r0 = r9.f2088K
            android.graphics.RectF r2 = r9.L
            r11.mapRect(r0, r2)
            android.graphics.RectF r11 = r9.f2088K
            android.graphics.Rect r0 = r9.f2087J
            f(r0, r11)
        L_0x0199:
            android.graphics.Rect r11 = r9.f2086I
            r11.set(r1, r1, r3, r4)
            android.graphics.Bitmap r11 = r9.D
            android.graphics.Rect r0 = r9.f2086I
            android.graphics.Rect r1 = r9.f2087J
            F0.l r9 = r9.f2085H
            r10.drawBitmap(r11, r0, r1, r9)
        L_0x01a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.w.l(android.graphics.Canvas, F0.e):void");
    }

    public final void m() {
        float f5;
        if (this.u == null) {
            this.f2095j.add(new t(this, 0));
            return;
        }
        e();
        boolean b = b();
        d dVar = this.e;
        if (b || dVar.getRepeatCount() == 0) {
            if (isVisible()) {
                dVar.f360p = true;
                dVar.g(false);
                Choreographer.getInstance().postFrameCallback(dVar);
                dVar.f357i = 0;
                if (dVar.d() && dVar.k == dVar.c()) {
                    dVar.h(dVar.b());
                } else if (!dVar.d() && dVar.k == dVar.b()) {
                    dVar.h(dVar.c());
                }
                Iterator it = dVar.f.iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorPauseListener) it.next()).onAnimationResume(dVar);
                }
                this.f2094i = v.NONE;
            } else {
                this.f2094i = v.RESUME;
            }
        }
        if (!b()) {
            if (dVar.g < 0.0f) {
                f5 = dVar.c();
            } else {
                f5 = dVar.b();
            }
            o((int) f5);
            dVar.g(true);
            dVar.e(dVar.d());
            if (!isVisible()) {
                this.f2094i = v.NONE;
            }
        }
    }

    public final boolean n(C0332j jVar) {
        boolean z3 = false;
        if (this.d == jVar) {
            return false;
        }
        this.f2091O = true;
        d();
        this.d = jVar;
        c();
        d dVar = this.e;
        if (dVar.f359o == null) {
            z3 = true;
        }
        dVar.f359o = jVar;
        if (z3) {
            dVar.j(Math.max(dVar.m, jVar.l), Math.min(dVar.n, jVar.m));
        } else {
            dVar.j((float) ((int) jVar.l), (float) ((int) jVar.m));
        }
        float f5 = dVar.k;
        dVar.k = 0.0f;
        dVar.f358j = 0.0f;
        dVar.h((float) ((int) f5));
        dVar.f();
        u(dVar.getAnimatedFraction());
        ArrayList arrayList = this.f2095j;
        Iterator it = new ArrayList(arrayList).iterator();
        while (it.hasNext()) {
            u uVar = (u) it.next();
            if (uVar != null) {
                uVar.run();
            }
            it.remove();
        }
        arrayList.clear();
        jVar.f2056a.f2045a = this.w;
        e();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ImageView imageView = (ImageView) callback;
            imageView.setImageDrawable((Drawable) null);
            imageView.setImageDrawable(this);
        }
        return true;
    }

    public final void o(int i2) {
        if (this.d == null) {
            this.f2095j.add(new p(this, i2, 2));
            return;
        }
        this.e.h((float) i2);
    }

    public final void p(int i2) {
        if (this.d == null) {
            this.f2095j.add(new p(this, i2, 0));
            return;
        }
        d dVar = this.e;
        dVar.j(dVar.m, ((float) i2) + 0.99f);
    }

    public final void q(String str) {
        C0332j jVar = this.d;
        if (jVar == null) {
            this.f2095j.add(new o(this, str, 1));
            return;
        }
        C0.i e7 = jVar.e(str);
        if (e7 != null) {
            p((int) (e7.b + e7.f100c));
            return;
        }
        throw new IllegalArgumentException(C0212a.m("Cannot find marker with name ", str, "."));
    }

    public final void r(String str) {
        C0332j jVar = this.d;
        ArrayList arrayList = this.f2095j;
        if (jVar == null) {
            arrayList.add(new o(this, str, 0));
            return;
        }
        C0.i e7 = jVar.e(str);
        if (e7 != null) {
            int i2 = (int) e7.b;
            int i7 = ((int) e7.f100c) + i2;
            if (this.d == null) {
                arrayList.add(new s(this, i2, i7));
                return;
            }
            this.e.j((float) i2, ((float) i7) + 0.99f);
            return;
        }
        throw new IllegalArgumentException(C0212a.m("Cannot find marker with name ", str, "."));
    }

    public final void s(int i2) {
        if (this.d == null) {
            this.f2095j.add(new p(this, i2, 1));
            return;
        }
        d dVar = this.e;
        dVar.j((float) i2, (float) ((int) dVar.n));
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    public final void setAlpha(int i2) {
        this.v = i2;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        J0.b.b("Use addColorFilter instead.");
    }

    public final boolean setVisible(boolean z3, boolean z7) {
        boolean isVisible = isVisible();
        boolean visible = super.setVisible(z3, z7);
        if (z3) {
            v vVar = this.f2094i;
            if (vVar == v.PLAY) {
                k();
                return visible;
            } else if (vVar == v.RESUME) {
                m();
                return visible;
            }
        } else if (this.e.f360p) {
            j();
            this.f2094i = v.RESUME;
            return visible;
        } else if (isVisible) {
            this.f2094i = v.NONE;
        }
        return visible;
    }

    public final void start() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View) || !((View) callback).isInEditMode()) {
            k();
        }
    }

    public final void stop() {
        this.f2095j.clear();
        d dVar = this.e;
        dVar.g(true);
        dVar.e(dVar.d());
        if (!isVisible()) {
            this.f2094i = v.NONE;
        }
    }

    public final void t(String str) {
        C0332j jVar = this.d;
        if (jVar == null) {
            this.f2095j.add(new o(this, str, 2));
            return;
        }
        C0.i e7 = jVar.e(str);
        if (e7 != null) {
            s((int) e7.b);
            return;
        }
        throw new IllegalArgumentException(C0212a.m("Cannot find marker with name ", str, "."));
    }

    public final void u(float f5) {
        C0332j jVar = this.d;
        if (jVar == null) {
            this.f2095j.add(new r(this, f5, 2));
            return;
        }
        C0323a aVar = C0326d.f2049a;
        this.e.h(J0.f.e(jVar.l, jVar.m, f5));
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }
}
