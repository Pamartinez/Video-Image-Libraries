package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements Runnable {
    public final /* synthetic */ VideoFrameProcessingTaskExecutor d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ VideoFrameProcessingTaskExecutor.Task f;

    public /* synthetic */ x(VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, boolean z, VideoFrameProcessingTaskExecutor.Task task) {
        this.d = videoFrameProcessingTaskExecutor;
        this.e = z;
        this.f = task;
    }

    public final void run() {
        this.d.lambda$wrapTaskAndSubmitToExecutorService$3(this.e, this.f);
    }
}
