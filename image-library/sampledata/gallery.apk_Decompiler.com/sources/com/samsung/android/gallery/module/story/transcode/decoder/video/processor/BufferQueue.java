package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferQueue {
    private final CopyOnWriteArrayList<Buffer> bufferQueue = new CopyOnWriteArrayList<>();
    protected Consumer<Boolean> enqueueListener;

    public void enqueueListener(Consumer<Boolean> consumer) {
        this.enqueueListener = consumer;
    }

    public boolean isEmpty() {
        if (this.bufferQueue.size() == 0) {
            return true;
        }
        return false;
    }

    public Buffer peek() {
        if (!this.bufferQueue.isEmpty()) {
            return this.bufferQueue.get(0);
        }
        return null;
    }

    public Buffer pop() {
        if (!this.bufferQueue.isEmpty()) {
            return this.bufferQueue.remove(0);
        }
        return null;
    }

    public void push(Buffer buffer) {
        this.bufferQueue.add(buffer);
        Consumer<Boolean> consumer = this.enqueueListener;
        if (consumer != null) {
            consumer.accept(Boolean.TRUE);
        }
    }

    public int size() {
        return this.bufferQueue.size();
    }
}
