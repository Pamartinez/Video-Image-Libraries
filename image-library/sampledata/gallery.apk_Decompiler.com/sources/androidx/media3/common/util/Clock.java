package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Clock {
    public static final Clock DEFAULT = new SystemClock();

    HandlerWrapper createHandler(Looper looper, Handler.Callback callback);

    long elapsedRealtime();

    long nanoTime();

    void onThreadBlocked();

    long uptimeMillis();
}
