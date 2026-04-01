package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BlockingBufferChannel implements BufferChannel {
    private static final String TAG = Def.tagOf((Class<?>) BlockingBufferChannel.class);
    private int capacity;
    private BlockingQueue<MediaBuffer> queue;

    public BlockingBufferChannel() {
        this.queue = new LinkedBlockingQueue();
    }

    public void cancel() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public void close() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isClosedForReceive() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public boolean isClosedForSend() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public void setCapacity(int i2) {
        this.capacity = i2;
        this.queue = new LinkedBlockingQueue(i2);
    }

    /* JADX INFO: finally extract failed */
    public MediaBuffer receive() {
        try {
            MediaBuffer take = this.queue.take();
            String str = TAG;
            SLog.d(str, "receive buffer[" + this.queue.size() + "]");
            return take;
        } catch (InterruptedException e) {
            e.printStackTrace();
            String str2 = TAG;
            SLog.d(str2, "receive buffer[" + this.queue.size() + "]");
            return null;
        } catch (Throwable th) {
            String str3 = TAG;
            SLog.d(str3, "receive buffer[" + this.queue.size() + "]");
            throw th;
        }
    }

    public void send(MediaBuffer mediaBuffer) {
        try {
            String str = TAG;
            SLog.d(str, "send buffer[" + this.queue.size() + "]: " + mediaBuffer);
            this.queue.put(mediaBuffer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BlockingBufferChannel(int i2) {
        this.capacity = i2;
        this.queue = new LinkedBlockingQueue(i2);
    }
}
