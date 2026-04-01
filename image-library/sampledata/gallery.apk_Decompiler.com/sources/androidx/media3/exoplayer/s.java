package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements MediaSource.MediaSourceCaller {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaSourceList f1023a;

    public /* synthetic */ s(MediaSourceList mediaSourceList) {
        this.f1023a = mediaSourceList;
    }

    public final void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        this.f1023a.lambda$prepareChildSource$0(mediaSource, timeline);
    }
}
