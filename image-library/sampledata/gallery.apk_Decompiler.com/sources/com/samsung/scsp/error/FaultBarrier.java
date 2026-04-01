package com.samsung.scsp.error;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FaultBarrier {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ThrowableFunction<T, R> {
        R apply(T t);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ThrowableRunnable {
        void run();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ThrowableSupplier<T> {
        T get();
    }

    public static <T> Response<T> get(ThrowableSupplier<T> throwableSupplier, T t, boolean z) {
        try {
            return new Response<>(throwableSupplier.get());
        } catch (Throwable th) {
            if (!z) {
                th.printStackTrace();
            }
            return new Response<>(t, ErrorSupplier.get(th));
        }
    }

    public static Result run(ThrowableRunnable throwableRunnable) {
        return run(throwableRunnable, false);
    }

    public static Result run(ThrowableRunnable throwableRunnable, boolean z) {
        try {
            throwableRunnable.run();
            return new Result();
        } catch (Throwable th) {
            if (!z) {
                th.printStackTrace();
            }
            return ErrorSupplier.get(th);
        }
    }

    public static <T> Response<T> get(ThrowableSupplier<T> throwableSupplier, T t) {
        return get(throwableSupplier, t, false);
    }
}
