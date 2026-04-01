package v1;

import E1.e;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: v1.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0297b implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* renamed from: h  reason: collision with root package name */
    public static final C0297b f1955h = new C0297b();
    public final AtomicBoolean d = new AtomicBoolean();
    public final AtomicBoolean e = new AtomicBoolean();
    public final ArrayList f = new ArrayList();
    public boolean g = false;

    public final void a(boolean z) {
        synchronized (f1955h) {
            try {
                Iterator it = this.f.iterator();
                while (it.hasNext()) {
                    e eVar = ((j) it.next()).f1963a.m;
                    eVar.sendMessage(eVar.obtainMessage(1, Boolean.valueOf(z)));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.d.compareAndSet(true, false);
        this.e.set(true);
        if (compareAndSet) {
            a(false);
        }
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.d.compareAndSet(true, false);
        this.e.set(true);
        if (compareAndSet) {
            a(false);
        }
    }

    public final void onTrimMemory(int i2) {
        if (i2 == 20 && this.d.compareAndSet(false, true)) {
            this.e.set(true);
            a(true);
        }
    }

    public final void onLowMemory() {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
