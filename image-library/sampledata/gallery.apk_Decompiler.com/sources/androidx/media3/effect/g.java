package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.VideoCompositor;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements VideoFrameProcessingTaskExecutor.ErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1006a;
    public final /* synthetic */ Object b;

    public /* synthetic */ g(int i2, Object obj) {
        this.f1006a = i2;
        this.b = obj;
    }

    public final void onError(VideoFrameProcessingException videoFrameProcessingException) {
        int i2 = this.f1006a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((VideoCompositor.Listener) obj).onError(videoFrameProcessingException);
                return;
            default:
                ((VideoFrameProcessor.Listener) obj).onError(videoFrameProcessingException);
                return;
        }
    }
}
