package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemClock implements Clock {
    public HandlerWrapper createHandler(Looper looper, Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }

    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    public long nanoTime() {
        return System.nanoTime();
    }

    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }

    public void onThreadBlocked() {
    }
}
