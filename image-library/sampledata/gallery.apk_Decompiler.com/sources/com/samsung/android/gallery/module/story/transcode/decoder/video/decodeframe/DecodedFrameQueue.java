package com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecodedFrameQueue {
    protected int queueHeadIndex = 0;
    protected ArrayList<DecodedFrame> releaseIndexQueue = new ArrayList<>();

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame dequeue() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.ArrayList<com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame> r0 = r3.releaseIndexQueue     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0028
            int r0 = r0.size()     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0028
            int r0 = r3.queueHeadIndex     // Catch:{ all -> 0x0026 }
            java.util.ArrayList<com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame> r1 = r3.releaseIndexQueue     // Catch:{ all -> 0x0026 }
            int r1 = r1.size()     // Catch:{ all -> 0x0026 }
            if (r0 < r1) goto L_0x0016
            goto L_0x0028
        L_0x0016:
            java.util.ArrayList<com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame> r0 = r3.releaseIndexQueue     // Catch:{ all -> 0x0026 }
            int r1 = r3.queueHeadIndex     // Catch:{ all -> 0x0026 }
            int r2 = r1 + 1
            r3.queueHeadIndex = r2     // Catch:{ all -> 0x0026 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0026 }
            com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame r0 = (com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame) r0     // Catch:{ all -> 0x0026 }
            monitor-exit(r3)
            return r0
        L_0x0026:
            r0 = move-exception
            goto L_0x002b
        L_0x0028:
            monitor-exit(r3)
            r3 = 0
            return r3
        L_0x002b:
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrameQueue.dequeue():com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame");
    }

    public synchronized void enqueue(DecodedFrame decodedFrame) {
        this.releaseIndexQueue.add(decodedFrame);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame peekHead() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.ArrayList<com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame> r0 = r2.releaseIndexQueue     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0024
            int r0 = r0.size()     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0024
            int r0 = r2.queueHeadIndex     // Catch:{ all -> 0x0022 }
            java.util.ArrayList<com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame> r1 = r2.releaseIndexQueue     // Catch:{ all -> 0x0022 }
            int r1 = r1.size()     // Catch:{ all -> 0x0022 }
            if (r0 < r1) goto L_0x0016
            goto L_0x0024
        L_0x0016:
            java.util.ArrayList<com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame> r0 = r2.releaseIndexQueue     // Catch:{ all -> 0x0022 }
            int r1 = r2.queueHeadIndex     // Catch:{ all -> 0x0022 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0022 }
            com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame r0 = (com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame) r0     // Catch:{ all -> 0x0022 }
            monitor-exit(r2)
            return r0
        L_0x0022:
            r0 = move-exception
            goto L_0x0027
        L_0x0024:
            monitor-exit(r2)
            r2 = 0
            return r2
        L_0x0027:
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrameQueue.peekHead():com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame");
    }
}
