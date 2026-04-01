package com.samsung.android.gallery.support.utils;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ByteBufferHolder {
    private static final ConcurrentLinkedQueue<ByteBuffer> sByteBufferSet = new ConcurrentLinkedQueue<>();

    public static void clear() {
        sByteBufferSet.clear();
    }

    public static ByteBuffer computeIfAbsent() {
        ByteBuffer poll = sByteBufferSet.poll();
        if (poll == null) {
            return ByteBuffer.allocate(65536);
        }
        return poll;
    }

    public static void recycle(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            ConcurrentLinkedQueue<ByteBuffer> concurrentLinkedQueue = sByteBufferSet;
            if (concurrentLinkedQueue.size() < 3) {
                concurrentLinkedQueue.add(byteBuffer);
            }
        }
    }
}
