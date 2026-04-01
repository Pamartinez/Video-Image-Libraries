package com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe;

import android.util.SparseArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecoderFrameManager {
    private final SparseArray<DecodedFrameQueue> mDecodedFrameQueues = new SparseArray<>();

    public synchronized void clear() {
        this.mDecodedFrameQueues.clear();
    }

    public synchronized void clearDecodedFrame(int i2) {
        this.mDecodedFrameQueues.remove(i2);
    }

    public synchronized DecodedFrame dequeueFrame(int i2) {
        DecodedFrame decodedFrame;
        DecodedFrameQueue decodedFrameQueue = this.mDecodedFrameQueues.get(i2);
        if (decodedFrameQueue != null) {
            decodedFrame = decodedFrameQueue.dequeue();
        } else {
            decodedFrame = null;
        }
        return decodedFrame;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean isDecodedFramesPrepared(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.util.SparseArray<com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrameQueue> r0 = r1.mDecodedFrameQueues     // Catch:{ all -> 0x0015 }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0015 }
            com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrameQueue r2 = (com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrameQueue) r2     // Catch:{ all -> 0x0015 }
            if (r2 == 0) goto L_0x0017
            com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame r2 = r2.peekHead()     // Catch:{ all -> 0x0015 }
            if (r2 != 0) goto L_0x0012
            goto L_0x0017
        L_0x0012:
            monitor-exit(r1)
            r1 = 1
            return r1
        L_0x0015:
            r2 = move-exception
            goto L_0x001a
        L_0x0017:
            monitor-exit(r1)
            r1 = 0
            return r1
        L_0x001a:
            monitor-exit(r1)     // Catch:{ all -> 0x0015 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecoderFrameManager.isDecodedFramesPrepared(int):boolean");
    }

    public synchronized boolean isEndOfStream(int i2) {
        DecodedFrame decodedFrame;
        boolean z;
        try {
            DecodedFrameQueue decodedFrameQueue = this.mDecodedFrameQueues.get(i2);
            if (decodedFrameQueue != null) {
                decodedFrame = decodedFrameQueue.peekHead();
            } else {
                decodedFrame = null;
            }
            if (decodedFrame == null || decodedFrame.presentationTimeUS != -4) {
                z = false;
            } else {
                z = true;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return z;
    }

    public synchronized void notifyFrameDecoded(DecodedFrame decodedFrame) {
        if (decodedFrame != null) {
            try {
                DecodedFrameQueue decodedFrameQueue = this.mDecodedFrameQueues.get(decodedFrame.videoID);
                if (decodedFrameQueue == null) {
                    decodedFrameQueue = new DecodedFrameQueue();
                }
                decodedFrameQueue.enqueue(decodedFrame);
                this.mDecodedFrameQueues.put(decodedFrame.videoID, decodedFrameQueue);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public synchronized DecodedFrame peakHead(int i2) {
        DecodedFrame decodedFrame;
        DecodedFrameQueue decodedFrameQueue = this.mDecodedFrameQueues.get(i2);
        if (decodedFrameQueue != null) {
            decodedFrame = decodedFrameQueue.peekHead();
        } else {
            decodedFrame = null;
        }
        return decodedFrame;
    }
}
