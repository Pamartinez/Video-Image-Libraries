package androidx.media3.common.util;

import android.os.Looper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface HandlerWrapper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Message {
        void sendToTarget();
    }

    Looper getLooper();

    boolean hasMessages(int i2);

    Message obtainMessage(int i2);

    Message obtainMessage(int i2, int i7, int i8);

    Message obtainMessage(int i2, int i7, int i8, Object obj);

    Message obtainMessage(int i2, Object obj);

    boolean post(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j2);

    void removeCallbacksAndMessages(Object obj);

    void removeMessages(int i2);

    boolean sendEmptyMessage(int i2);

    boolean sendEmptyMessageAtTime(int i2, long j2);

    boolean sendEmptyMessageDelayed(int i2, int i7);

    boolean sendMessageAtFrontOfQueue(Message message);
}
