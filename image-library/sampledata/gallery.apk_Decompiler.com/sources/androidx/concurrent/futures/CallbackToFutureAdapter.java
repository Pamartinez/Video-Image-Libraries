package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CallbackToFutureAdapter {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Completer<T> {
        private boolean attemptedSetting;
        private ResolvableFuture<Void> cancellationFuture = ResolvableFuture.create();
        SafeFuture<T> future;
        Object tag;

        private void setCompletedNormally() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture = null;
        }

        public void addCancellationListener(Runnable runnable, Executor executor) {
            ResolvableFuture<Void> resolvableFuture = this.cancellationFuture;
            if (resolvableFuture != null) {
                resolvableFuture.addListener(runnable, executor);
            }
        }

        public void finalize() {
            ResolvableFuture<Void> resolvableFuture;
            SafeFuture<T> safeFuture = this.future;
            if (safeFuture != null && !safeFuture.isDone()) {
                safeFuture.setException(new FutureGarbageCollectedException("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.tag));
            }
            if (!this.attemptedSetting && (resolvableFuture = this.cancellationFuture) != null) {
                resolvableFuture.set(null);
            }
        }

        public void fireCancellationListeners() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture.set(null);
        }

        public boolean set(T t) {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture<T> safeFuture = this.future;
            if (safeFuture == null || !safeFuture.set(t)) {
                z = false;
            }
            if (z) {
                setCompletedNormally();
            }
            return z;
        }

        public boolean setCancelled() {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture<T> safeFuture = this.future;
            if (safeFuture == null || !safeFuture.cancelWithoutNotifyingCompleter(true)) {
                z = false;
            }
            if (z) {
                setCompletedNormally();
            }
            return z;
        }

        public boolean setException(Throwable th) {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture<T> safeFuture = this.future;
            if (safeFuture == null || !safeFuture.setException(th)) {
                z = false;
            }
            if (z) {
                setCompletedNormally();
            }
            return z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FutureGarbageCollectedException extends Throwable {
        public FutureGarbageCollectedException(String str) {
            super(str);
        }

        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Resolver<T> {
        Object attachCompleter(Completer<T> completer);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SafeFuture<T> implements ListenableFuture {
        final WeakReference<Completer<T>> completerWeakReference;
        private final AbstractResolvableFuture<T> delegate = new AbstractResolvableFuture<T>() {
            public String pendingToString() {
                Completer completer = SafeFuture.this.completerWeakReference.get();
                if (completer == null) {
                    return "Completer object has been garbage collected, future will fail soon";
                }
                return "tag=[" + completer.tag + "]";
            }
        };

        public SafeFuture(Completer<T> completer) {
            this.completerWeakReference = new WeakReference<>(completer);
        }

        public void addListener(Runnable runnable, Executor executor) {
            this.delegate.addListener(runnable, executor);
        }

        public boolean cancel(boolean z) {
            Completer completer = this.completerWeakReference.get();
            boolean cancel = this.delegate.cancel(z);
            if (cancel && completer != null) {
                completer.fireCancellationListeners();
            }
            return cancel;
        }

        public boolean cancelWithoutNotifyingCompleter(boolean z) {
            return this.delegate.cancel(z);
        }

        public T get() {
            return this.delegate.get();
        }

        public boolean isCancelled() {
            return this.delegate.isCancelled();
        }

        public boolean isDone() {
            return this.delegate.isDone();
        }

        public boolean set(T t) {
            return this.delegate.set(t);
        }

        public boolean setException(Throwable th) {
            return this.delegate.setException(th);
        }

        public String toString() {
            return this.delegate.toString();
        }

        public T get(long j2, TimeUnit timeUnit) {
            return this.delegate.get(j2, timeUnit);
        }
    }

    public static <T> ListenableFuture getFuture(Resolver<T> resolver) {
        Completer completer = new Completer();
        SafeFuture<T> safeFuture = new SafeFuture<>(completer);
        completer.future = safeFuture;
        completer.tag = resolver.getClass();
        try {
            Object attachCompleter = resolver.attachCompleter(completer);
            if (attachCompleter == null) {
                return safeFuture;
            }
            completer.tag = attachCompleter;
            return safeFuture;
        } catch (Exception e) {
            safeFuture.setException(e);
            return safeFuture;
        }
    }
}
