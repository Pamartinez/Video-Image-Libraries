package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.GlShaderProgram;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FrameConsumptionManager implements GlShaderProgram.InputListener {
    private final Queue<TimedGlTextureInfo> availableFrames = new ArrayDeque();
    private final GlShaderProgram consumingGlShaderProgram;
    private int consumingGlShaderProgramInputCapacity;
    private final GlObjectsProvider glObjectsProvider;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public FrameConsumptionManager(GlObjectsProvider glObjectsProvider2, GlShaderProgram glShaderProgram, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2) {
        this.glObjectsProvider = glObjectsProvider2;
        this.consumingGlShaderProgram = glShaderProgram;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onReadyToAcceptInputFrame$0(TimedGlTextureInfo timedGlTextureInfo) {
        this.consumingGlShaderProgram.queueInputFrame(this.glObjectsProvider, timedGlTextureInfo.glTextureInfo, timedGlTextureInfo.presentationTimeUs);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$queueInputFrame$1(GlTextureInfo glTextureInfo, long j2) {
        this.consumingGlShaderProgram.queueInputFrame(this.glObjectsProvider, glTextureInfo, j2);
    }

    public synchronized int getPendingFrameCount() {
        return this.availableFrames.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadyToAcceptInputFrame() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Queue<androidx.media3.effect.TimedGlTextureInfo> r0 = r4.availableFrames     // Catch:{ all -> 0x0013 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0013 }
            androidx.media3.effect.TimedGlTextureInfo r0 = (androidx.media3.effect.TimedGlTextureInfo) r0     // Catch:{ all -> 0x0013 }
            if (r0 != 0) goto L_0x0015
            int r0 = r4.consumingGlShaderProgramInputCapacity     // Catch:{ all -> 0x0013 }
            int r0 = r0 + 1
            r4.consumingGlShaderProgramInputCapacity = r0     // Catch:{ all -> 0x0013 }
            monitor-exit(r4)
            return
        L_0x0013:
            r0 = move-exception
            goto L_0x0049
        L_0x0015:
            androidx.media3.effect.VideoFrameProcessingTaskExecutor r1 = r4.videoFrameProcessingTaskExecutor     // Catch:{ all -> 0x0013 }
            androidx.media3.effect.c r2 = new androidx.media3.effect.c     // Catch:{ all -> 0x0013 }
            r3 = 4
            r2.<init>(r3, r4, r0)     // Catch:{ all -> 0x0013 }
            r1.submit(r2)     // Catch:{ all -> 0x0013 }
            java.util.Queue<androidx.media3.effect.TimedGlTextureInfo> r0 = r4.availableFrames     // Catch:{ all -> 0x0013 }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x0013 }
            androidx.media3.effect.TimedGlTextureInfo r0 = (androidx.media3.effect.TimedGlTextureInfo) r0     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0047
            long r0 = r0.presentationTimeUs     // Catch:{ all -> 0x0013 }
            r2 = -9223372036854775808
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0047
            androidx.media3.effect.VideoFrameProcessingTaskExecutor r0 = r4.videoFrameProcessingTaskExecutor     // Catch:{ all -> 0x0013 }
            androidx.media3.effect.GlShaderProgram r1 = r4.consumingGlShaderProgram     // Catch:{ all -> 0x0013 }
            java.util.Objects.requireNonNull(r1)     // Catch:{ all -> 0x0013 }
            androidx.media3.effect.s r2 = new androidx.media3.effect.s     // Catch:{ all -> 0x0013 }
            r3 = 0
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0013 }
            r0.submit(r2)     // Catch:{ all -> 0x0013 }
            java.util.Queue<androidx.media3.effect.TimedGlTextureInfo> r0 = r4.availableFrames     // Catch:{ all -> 0x0013 }
            r0.remove()     // Catch:{ all -> 0x0013 }
        L_0x0047:
            monitor-exit(r4)
            return
        L_0x0049:
            monitor-exit(r4)     // Catch:{ all -> 0x0013 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.FrameConsumptionManager.onReadyToAcceptInputFrame():void");
    }

    public synchronized void queueInputFrame(GlTextureInfo glTextureInfo, long j2) {
        FrameConsumptionManager frameConsumptionManager;
        try {
            if (this.consumingGlShaderProgramInputCapacity > 0) {
                frameConsumptionManager = this;
                this.videoFrameProcessingTaskExecutor.submit(new d(frameConsumptionManager, glTextureInfo, j2, 1));
                frameConsumptionManager.consumingGlShaderProgramInputCapacity--;
            } else {
                frameConsumptionManager = this;
                frameConsumptionManager.availableFrames.add(new TimedGlTextureInfo(glTextureInfo, j2));
            }
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
    }

    public synchronized void signalEndOfCurrentStream() {
        try {
            if (!this.availableFrames.isEmpty()) {
                this.availableFrames.add(new TimedGlTextureInfo(GlTextureInfo.UNSET, Long.MIN_VALUE));
            } else {
                VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2 = this.videoFrameProcessingTaskExecutor;
                GlShaderProgram glShaderProgram = this.consumingGlShaderProgram;
                Objects.requireNonNull(glShaderProgram);
                videoFrameProcessingTaskExecutor2.submit(new s(0, glShaderProgram));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
