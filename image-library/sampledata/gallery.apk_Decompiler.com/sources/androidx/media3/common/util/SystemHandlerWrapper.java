package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.util.HandlerWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SystemHandlerWrapper implements HandlerWrapper {
    private static final List<SystemMessage> messagePool = new ArrayList(50);
    private final Handler handler;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SystemMessage implements HandlerWrapper.Message {
        private SystemHandlerWrapper handler;
        private Message message;

        private SystemMessage() {
        }

        private void recycle() {
            this.message = null;
            this.handler = null;
            SystemHandlerWrapper.recycleMessage(this);
        }

        public boolean sendAtFrontOfQueue(Handler handler2) {
            boolean sendMessageAtFrontOfQueue = handler2.sendMessageAtFrontOfQueue((Message) Assertions.checkNotNull(this.message));
            recycle();
            return sendMessageAtFrontOfQueue;
        }

        public void sendToTarget() {
            ((Message) Assertions.checkNotNull(this.message)).sendToTarget();
            recycle();
        }

        public SystemMessage setMessage(Message message2, SystemHandlerWrapper systemHandlerWrapper) {
            this.message = message2;
            this.handler = systemHandlerWrapper;
            return this;
        }
    }

    public SystemHandlerWrapper(Handler handler2) {
        this.handler = handler2;
    }

    private static SystemMessage obtainSystemMessage() {
        SystemMessage systemMessage;
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                if (list.isEmpty()) {
                    systemMessage = new SystemMessage();
                } else {
                    systemMessage = list.remove(list.size() - 1);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return systemMessage;
    }

    /* access modifiers changed from: private */
    public static void recycleMessage(SystemMessage systemMessage) {
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                if (list.size() < 50) {
                    list.add(systemMessage);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Looper getLooper() {
        return this.handler.getLooper();
    }

    public boolean hasMessages(int i2) {
        boolean z;
        if (i2 != 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        return this.handler.hasMessages(i2);
    }

    public HandlerWrapper.Message obtainMessage(int i2) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i2), this);
    }

    public boolean post(Runnable runnable) {
        return this.handler.post(runnable);
    }

    public boolean postDelayed(Runnable runnable, long j2) {
        return this.handler.postDelayed(runnable, j2);
    }

    public void removeCallbacksAndMessages(Object obj) {
        this.handler.removeCallbacksAndMessages(obj);
    }

    public void removeMessages(int i2) {
        boolean z;
        if (i2 != 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        this.handler.removeMessages(i2);
    }

    public boolean sendEmptyMessage(int i2) {
        return this.handler.sendEmptyMessage(i2);
    }

    public boolean sendEmptyMessageAtTime(int i2, long j2) {
        return this.handler.sendEmptyMessageAtTime(i2, j2);
    }

    public boolean sendEmptyMessageDelayed(int i2, int i7) {
        return this.handler.sendEmptyMessageDelayed(i2, (long) i7);
    }

    public boolean sendMessageAtFrontOfQueue(HandlerWrapper.Message message) {
        return ((SystemMessage) message).sendAtFrontOfQueue(this.handler);
    }

    public HandlerWrapper.Message obtainMessage(int i2, Object obj) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i2, obj), this);
    }

    public HandlerWrapper.Message obtainMessage(int i2, int i7, int i8) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i2, i7, i8), this);
    }

    public HandlerWrapper.Message obtainMessage(int i2, int i7, int i8, Object obj) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i2, i7, i8, obj), this);
    }
}
