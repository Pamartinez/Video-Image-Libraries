package N;

import E2.l;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements l {
    public final /* synthetic */ DefaultTrackSelector d;
    public final /* synthetic */ DefaultTrackSelector.Parameters e;

    public /* synthetic */ c(DefaultTrackSelector defaultTrackSelector, DefaultTrackSelector.Parameters parameters) {
        this.d = defaultTrackSelector;
        this.e = parameters;
    }

    public final boolean apply(Object obj) {
        return this.d.lambda$selectAudioTrack$2(this.e, (Format) obj);
    }
}
