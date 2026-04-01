package com.samsung.android.sum.core.message;

import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BlockingMessageChannel implements MessageChannel {
    private String id;
    protected BlockingQueue<Message> queue;
    private WeakReference<Thread> threadWeakReference;

    public BlockingMessageChannel(String str) {
        this.id = str;
        this.queue = new LinkedBlockingQueue();
    }

    public void cancel() {
        Thread thread = this.threadWeakReference.get();
        if (thread != null) {
            thread.interrupt();
        }
    }

    public String getId() {
        return this.id;
    }

    public boolean isClosedForReceive() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public boolean isClosedForSend() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setThreadWeakReference(WeakReference<Thread> weakReference) {
        this.threadWeakReference = weakReference;
    }

    public Message receive() {
        try {
            return this.queue.take();
        } catch (InterruptedException unused) {
            throw new CancellationException("BlockingMessageChannel is canceled");
        }
    }

    public void send(Message message) {
        try {
            this.queue.put(message);
        } catch (InterruptedException unused) {
            throw new CancellationException("BlockingMessageChannel is canceled");
        }
    }

    public BlockingMessageChannel(String str, int i2) {
        this.id = str;
        this.queue = new LinkedBlockingQueue(i2);
    }

    public void close() {
    }
}
