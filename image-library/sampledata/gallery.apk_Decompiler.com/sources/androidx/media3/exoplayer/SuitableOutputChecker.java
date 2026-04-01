package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Looper;
import androidx.media3.common.util.Clock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SuitableOutputChecker {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
    }

    void disable();

    void enable(Callback callback, Context context, Looper looper, Looper looper2, Clock clock);

    boolean isSelectedOutputSuitableForPlayback();
}
