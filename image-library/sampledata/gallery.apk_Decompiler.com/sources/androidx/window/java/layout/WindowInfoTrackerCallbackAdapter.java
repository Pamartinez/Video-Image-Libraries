package androidx.window.java.layout;

import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import Yf.g;
import android.app.Activity;
import androidx.core.util.Consumer;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J9\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\f2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0001¢\u0006\u0004\b\u0014\u0010\u0015J+\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00130\b¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00130\b¢\u0006\u0004\b\u0018\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR$\u0010\u001f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Landroidx/window/java/layout/WindowInfoTrackerCallbackAdapter;", "Landroidx/window/layout/WindowInfoTracker;", "tracker", "<init>", "(Landroidx/window/layout/WindowInfoTracker;)V", "T", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/core/util/Consumer;", "consumer", "LYf/g;", "flow", "Lme/x;", "addListener", "(Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;LYf/g;)V", "removeListener", "(Landroidx/core/util/Consumer;)V", "Landroid/app/Activity;", "activity", "Landroidx/window/layout/WindowLayoutInfo;", "windowLayoutInfo", "(Landroid/app/Activity;)LYf/g;", "addWindowLayoutInfoListener", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "removeWindowLayoutInfoListener", "Landroidx/window/layout/WindowInfoTracker;", "Ljava/util/concurrent/locks/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "", "LVf/e0;", "consumerToJobMap", "Ljava/util/Map;", "window-java_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WindowInfoTrackerCallbackAdapter implements WindowInfoTracker {
    private final Map<Consumer<?>, C0867e0> consumerToJobMap = new LinkedHashMap();
    private final ReentrantLock lock = new ReentrantLock();
    private final WindowInfoTracker tracker;

    public WindowInfoTrackerCallbackAdapter(WindowInfoTracker windowInfoTracker) {
        j.e(windowInfoTracker, "tracker");
        this.tracker = windowInfoTracker;
    }

    private final <T> void addListener(Executor executor, Consumer<T> consumer, g gVar) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.consumerToJobMap.get(consumer) == null) {
                this.consumerToJobMap.put(consumer, D.n(D.a(D.h(executor)), (C0886x) null, (C) null, new WindowInfoTrackerCallbackAdapter$addListener$1$1(gVar, consumer, (C1227c) null), 3));
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private final void removeListener(Consumer<?> consumer) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            C0867e0 e0Var = this.consumerToJobMap.get(consumer);
            if (e0Var != null) {
                e0Var.a((CancellationException) null);
            }
            C0867e0 remove = this.consumerToJobMap.remove(consumer);
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void addWindowLayoutInfoListener(Activity activity, Executor executor, Consumer<WindowLayoutInfo> consumer) {
        j.e(activity, "activity");
        j.e(executor, "executor");
        j.e(consumer, "consumer");
        addListener(executor, consumer, this.tracker.windowLayoutInfo(activity));
    }

    public final void removeWindowLayoutInfoListener(Consumer<WindowLayoutInfo> consumer) {
        j.e(consumer, "consumer");
        removeListener(consumer);
    }

    public g windowLayoutInfo(Activity activity) {
        j.e(activity, "activity");
        return this.tracker.windowLayoutInfo(activity);
    }
}
