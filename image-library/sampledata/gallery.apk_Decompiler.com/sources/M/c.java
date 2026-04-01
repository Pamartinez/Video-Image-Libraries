package M;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher e;
    public final /* synthetic */ LoadEventInfo f;
    public final /* synthetic */ MediaLoadData g;

    public /* synthetic */ c(MediaSourceEventListener.EventDispatcher eventDispatcher, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2) {
        this.d = i2;
        this.e = eventDispatcher;
        this.f = loadEventInfo;
        this.g = mediaLoadData;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$loadCompleted$1(this.f, this.g, (MediaSourceEventListener) obj);
                return;
            default:
                this.e.lambda$loadCanceled$2(this.f, this.g, (MediaSourceEventListener) obj);
                return;
        }
    }
}
