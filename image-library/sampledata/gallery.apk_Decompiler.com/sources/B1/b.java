package B1;

import Dd.C0730a;
import He.F;
import He.t;
import Hf.C0774x;
import L1.d;
import P1.c;
import Pe.q;
import Qe.C0816f;
import Qe.C0819i;
import Qf.a;
import Tf.v;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;
import c0.C0086a;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;
import h2.C0208c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import jf.o;
import jf.p;
import kf.C1117c;
import kotlin.jvm.internal.j;
import lf.P;
import lf.Q;
import lf.X;
import ne.C1195m;
import ne.C1196n;
import pf.f;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import u2.C0286a;
import v1.h;
import vf.C1326f;
import w1.i;
import w1.r;
import y1.C0354a;
import y1.C0356c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements De.b, a, OnApplyWindowInsetsListener, C0286a, o, h {
    public final /* synthetic */ int d;
    public Object e;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public Iterable a(Object obj) {
        C0819i iVar;
        C0816f fVar;
        q qVar = (q) this.e;
        Collection<C0774x> h5 = ((C0816f) obj).q().h();
        j.d(h5, "getSupertypes(...)");
        ArrayList arrayList = new ArrayList();
        for (C0774x s0 : h5) {
            C0819i g = s0.s0().g();
            C0816f fVar2 = null;
            if (g != null) {
                iVar = g.a();
            } else {
                iVar = null;
            }
            if (iVar instanceof C0816f) {
                fVar = (C0816f) iVar;
            } else {
                fVar = null;
            }
            if (fVar != null && (fVar2 = qVar.f(fVar)) == null) {
                fVar2 = fVar;
            }
            if (fVar2 != null) {
                arrayList.add(fVar2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: finally extract failed */
    public void accept(Object obj, Object obj2) {
        c cVar = (c) obj2;
        C0354a aVar = (C0354a) ((C0356c) obj).p();
        i iVar = (i) this.e;
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(aVar.f162c);
        int i2 = E1.b.f163a;
        if (iVar == null) {
            obtain.writeInt(0);
        } else {
            obtain.writeInt(1);
            iVar.writeToParcel(obtain, 0);
        }
        try {
            aVar.b.transact(1, obtain, (Parcel) null, 1);
            obtain.recycle();
            cVar.a((Location) null);
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    public void b(Typeface typeface) {
        C0208c cVar = (C0208c) this.e;
        if (cVar.m(typeface)) {
            cVar.i(false);
        }
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [X0.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(X0.c r9) {
        /*
            r8 = this;
            java.lang.Object r8 = r8.e
            V0.j r8 = (V0.j) r8
            X0.d r0 = r8.k
            X0.b r0 = r0.f893j
            W0.h r1 = r0.f892j
            X0.c[] r2 = r1.f
            java.lang.Object[] r9 = Gd.a.J(r2, r9)
            r5 = r9
            X0.c[] r5 = (X0.c[]) r5
            W0.h r2 = new W0.h
            o1.c[] r3 = r1.d
            W0.f[] r4 = r1.e
            og.k[] r6 = r1.g
            o1.d[] r7 = r1.f889h
            r2.<init>(r3, r4, r5, r6, r7)
            if (r1 != r2) goto L_0x0023
            goto L_0x002a
        L_0x0023:
            java.util.Iterator r9 = i1.a.f1782a
            X0.b r0 = new X0.b
            r0.<init>(r2)
        L_0x002a:
            X0.d r9 = r8.k
            X0.d r9 = r9.b0(r0)
            r8.k = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: B1.b.c(X0.c):void");
    }

    public b d(C1235b bVar, f fVar) {
        Ve.b e7;
        j.e(bVar, "classId");
        j.e(fVar, "jvmMetadataVersion");
        String r0 = v.r0(bVar.b.b(), '.', '$');
        C1236c cVar = bVar.f5033a;
        if (!cVar.d()) {
            r0 = cVar + '.' + r0;
        }
        Class R = F.R((ClassLoader) this.e, r0);
        if (R == null || (e7 = d.e(R)) == null) {
            return null;
        }
        return new b(14, e7);
    }

    public void e(Object obj, t tVar) {
        j.e(tVar, "property");
        this.e = (Long) obj;
    }

    public Object f(Object obj, t tVar) {
        j.e(tVar, "property");
        Long l = (Long) this.e;
        if (l != null) {
            return l;
        }
        throw new IllegalStateException("Property " + tVar.getName() + " should be initialized before get.");
    }

    public Q g(int i2) {
        return (Q) ((List) this.e).get(i2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: Q0.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: Q0.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: Q0.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: Q0.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: Q0.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: Q0.j} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h(Q0.j r4) {
        /*
            r3 = this;
            java.lang.Object r3 = r3.e
            V0.j r3 = (V0.j) r3
            V0.a r0 = r3.f869j
            W0.a r1 = r0.e
            Q0.j r2 = r1.f
            if (r2 != 0) goto L_0x000e
            r2 = r4
            goto L_0x0013
        L_0x000e:
            a1.b r2 = new a1.b
            r2.<init>()
        L_0x0013:
            W0.a r1 = r1.a(r2)
            W0.j r0 = r0.b(r1)
            V0.a r0 = (V0.a) r0
            r3.f869j = r0
            V0.l r0 = r3.f867h
            W0.a r1 = r0.e
            Q0.j r2 = r1.f
            if (r2 != 0) goto L_0x0028
            goto L_0x002d
        L_0x0028:
            a1.b r4 = new a1.b
            r4.<init>()
        L_0x002d:
            W0.a r4 = r1.a(r4)
            W0.j r4 = r0.b(r4)
            V0.l r4 = (V0.l) r4
            r3.f867h = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: B1.b.h(Q0.j):void");
    }

    public void i(boolean z) {
        try {
            L1.c cVar = (L1.c) this.e;
            Parcel c5 = cVar.c();
            int i2 = H1.d.f335a;
            c5.writeInt(z ? 1 : 0);
            cVar.d(c5, 2);
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    public void j(boolean z) {
        try {
            L1.c cVar = (L1.c) this.e;
            Parcel c5 = cVar.c();
            int i2 = H1.d.f335a;
            c5.writeInt(z ? 1 : 0);
            cVar.d(c5, 7);
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    public void k(boolean z) {
        try {
            L1.c cVar = (L1.c) this.e;
            Parcel c5 = cVar.c();
            int i2 = H1.d.f335a;
            c5.writeInt(z ? 1 : 0);
            cVar.d(c5, 1);
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    public p o(C1240g gVar) {
        if ("b".equals(gVar.b())) {
            return new C1117c(this, 2);
        }
        return null;
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        switch (this.d) {
            case 6:
                Insets insets = windowInsetsCompat.getInsets(AppBarLayout.SYSTEM_BARS);
                Insets insets2 = windowInsetsCompat.getInsets(AppBarLayout.TAPPABLE_ELEMENT);
                AppBarLayout appBarLayout = (AppBarLayout) this.e;
                if (!insets2.equals(appBarLayout.mLastTappableInsets) || !insets.equals(appBarLayout.mLastSysInsets)) {
                    String access$400 = AppBarLayout.TAG;
                    Log.d(access$400, "[onApplyWindowInsets] sysInsets : " + insets + ", tappableInsets : " + insets2);
                    if (appBarLayout.getImmBehavior() != null) {
                        SeslImmersiveScrollBehavior access$500 = appBarLayout.getImmBehavior();
                        if (access$500.f1372H != null) {
                            access$500.x();
                            access$500.J();
                            access$500.f1372H.onOffsetChanged(access$500.a());
                        }
                    }
                    Insets unused = appBarLayout.mLastSysInsets = insets;
                    Insets unused2 = appBarLayout.mLastTappableInsets = insets2;
                }
                return appBarLayout.onWindowInsetChanged(windowInsetsCompat);
            default:
                z2.q qVar = (z2.q) this.e;
                qVar.n = windowInsetsCompat.getSystemWindowInsetBottom();
                qVar.f2223o = windowInsetsCompat.getSystemWindowInsetLeft();
                qVar.f2224p = windowInsetsCompat.getSystemWindowInsetRight();
                qVar.i();
                return windowInsetsCompat;
        }
    }

    public o p(C1235b bVar, C1240g gVar) {
        return null;
    }

    public String toString() {
        String str;
        switch (this.d) {
            case 1:
                StringBuilder sb2 = new StringBuilder("NotNullProperty(");
                if (((Long) this.e) != null) {
                    str = "value=" + ((Long) this.e);
                } else {
                    str = "value not initialized yet";
                }
                return C0086a.m(sb2, str, ')');
            default:
                return super.toString();
        }
    }

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public b(C1.a aVar) {
        this.d = 4;
        r.b(aVar);
        this.e = aVar;
    }

    public b(X x9) {
        this.d = 16;
        j.e(x9, "typeTable");
        List list = x9.f;
        if ((x9.e & 1) == 1) {
            int i2 = x9.g;
            j.d(list, "getTypeList(...)");
            Iterable iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            int i7 = 0;
            for (Object next : iterable) {
                int i8 = i7 + 1;
                if (i7 >= 0) {
                    Q q = (Q) next;
                    if (i7 >= i2) {
                        q.getClass();
                        P p6 = Q.p(q);
                        p6.g |= 2;
                        p6.f4766i = true;
                        q = p6.e();
                        if (!q.isInitialized()) {
                            throw new C0730a();
                        }
                    }
                    arrayList.add(q);
                    i7 = i8;
                } else {
                    C1195m.v0();
                    throw null;
                }
            }
            list = arrayList;
        }
        j.d(list, "run(...)");
        this.e = list;
    }

    public void n() {
    }

    public b() {
        this.d = 12;
        this.e = new AtomicLong();
    }

    public void I(C1240g gVar, C1326f fVar) {
    }

    public void s(C1240g gVar, Object obj) {
    }

    public void r(C1240g gVar, C1235b bVar, C1240g gVar2) {
    }
}
