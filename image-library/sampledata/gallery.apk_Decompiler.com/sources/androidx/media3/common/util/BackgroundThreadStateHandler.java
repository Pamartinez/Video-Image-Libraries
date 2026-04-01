package androidx.media3.common.util;

import Da.f;
import E2.h;
import F.a;
import android.os.Handler;
import android.os.Looper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BackgroundThreadStateHandler<T> {
    private final HandlerWrapper backgroundHandler;
    private T backgroundState;
    private final HandlerWrapper foregroundHandler;
    private T foregroundState;
    private final StateChangeListener<T> onStateChanged;
    private int pendingOperations;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface StateChangeListener<T> {
        void onStateChanged(T t, T t3);
    }

    public BackgroundThreadStateHandler(T t, Looper looper, Looper looper2, Clock clock, StateChangeListener<T> stateChangeListener) {
        this.backgroundHandler = clock.createHandler(looper, (Handler.Callback) null);
        this.foregroundHandler = clock.createHandler(looper2, (Handler.Callback) null);
        this.foregroundState = t;
        this.backgroundState = t;
        this.onStateChanged = stateChangeListener;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setStateInBackground$2(Object obj) {
        if (this.pendingOperations == 0) {
            updateStateInForeground(obj);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateStateAsync$0(Object obj) {
        int i2 = this.pendingOperations - 1;
        this.pendingOperations = i2;
        if (i2 == 0) {
            updateStateInForeground(obj);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateStateAsync$1(h hVar) {
        T apply = hVar.apply(this.backgroundState);
        this.backgroundState = apply;
        runInForeground(new a(this, apply, 1));
    }

    private void runInForeground(Runnable runnable) {
        if (this.foregroundHandler.getLooper().getThread().isAlive()) {
            this.foregroundHandler.post(runnable);
        }
    }

    private void updateStateInForeground(T t) {
        T t3 = this.foregroundState;
        this.foregroundState = t;
        if (!t3.equals(t)) {
            this.onStateChanged.onStateChanged(t3, t);
        }
    }

    public T get() {
        boolean z;
        Looper myLooper = Looper.myLooper();
        if (myLooper == this.foregroundHandler.getLooper()) {
            return this.foregroundState;
        }
        if (myLooper == this.backgroundHandler.getLooper()) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        return this.backgroundState;
    }

    public void runInBackground(Runnable runnable) {
        if (this.backgroundHandler.getLooper().getThread().isAlive()) {
            this.backgroundHandler.post(runnable);
        }
    }

    public void setStateInBackground(T t) {
        this.backgroundState = t;
        runInForeground(new a(this, t, 0));
    }

    public void updateStateAsync(h hVar, h hVar2) {
        boolean z;
        if (Looper.myLooper() == this.foregroundHandler.getLooper()) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.pendingOperations++;
        runInBackground(new f(8, this, hVar2));
        updateStateInForeground(hVar.apply(this.foregroundState));
    }
}
