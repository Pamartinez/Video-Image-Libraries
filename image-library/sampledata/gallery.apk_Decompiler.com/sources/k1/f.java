package K1;

import A0.l;
import C1.c;
import L1.g;
import Y6.a;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.os.StrictMode;
import android.widget.FrameLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends FrameLayout {
    public final j d;

    public f(Context context) {
        super(context);
        this.d = new j(this, context);
        setClickable(true);
    }

    public final void a(a aVar) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            j jVar = this.d;
            l lVar = jVar.f383a;
            if (lVar != null) {
                lVar.l(aVar);
            } else {
                jVar.f385h.add(aVar);
            }
        } else {
            throw new IllegalStateException("getMapAsync() must be called on the main thread");
        }
    }

    public final void b(Bundle bundle) {
        j jVar = this.d;
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            jVar.getClass();
            jVar.c(bundle, new c(jVar, bundle));
            if (jVar.f383a == null) {
                j.a(this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void c() {
        j jVar = this.d;
        l lVar = jVar.f383a;
        if (lVar != null) {
            try {
                g gVar = (g) lVar.f;
                gVar.d(gVar.c(), 5);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            jVar.b(1);
        }
    }

    public final void d() {
        j jVar = this.d;
        l lVar = jVar.f383a;
        if (lVar != null) {
            try {
                g gVar = (g) lVar.f;
                gVar.d(gVar.c(), 4);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            jVar.b(5);
        }
    }

    public final void e() {
        j jVar = this.d;
        l lVar = jVar.f383a;
        if (lVar != null) {
            try {
                g gVar = (g) lVar.f;
                gVar.d(gVar.c(), 13);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            jVar.b(4);
        }
    }
}
