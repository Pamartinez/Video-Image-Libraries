package N;

import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DefaultTrackSelector.TrackInfo.Factory, TrackSelector.Factory {
    public final /* synthetic */ DefaultTrackSelector.Parameters d;

    public /* synthetic */ a(DefaultTrackSelector.Parameters parameters) {
        this.d = parameters;
    }

    public List create(int i2, TrackGroup trackGroup, int[] iArr) {
        return DefaultTrackSelector.ImageTrackInfo.createForTrackGroup(i2, trackGroup, this.d, iArr);
    }
}
