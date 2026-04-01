package androidx.media3.exoplayer.source;

import E2.h;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.SampleQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements h, Consumer {
    public void accept(Object obj) {
        ((SampleQueue.SharedSampleMetadata) obj).drmSessionReference.release();
    }

    public Object apply(Object obj) {
        return ((MediaPeriod) obj).getTrackGroups().getTrackTypes();
    }
}
