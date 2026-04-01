package M;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.CompositeMediaSource;
import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaSource.MediaSourceCaller {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CompositeMediaSource f415a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(CompositeMediaSource compositeMediaSource, Object obj) {
        this.f415a = compositeMediaSource;
        this.b = obj;
    }

    public final void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        this.f415a.lambda$prepareChildSource$0(this.b, mediaSource, timeline);
    }
}
