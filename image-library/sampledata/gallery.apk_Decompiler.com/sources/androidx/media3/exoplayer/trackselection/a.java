package androidx.media3.exoplayer.trackselection;

import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                return DefaultTrackSelector.ImageTrackInfo.compareSelections((List) obj, (List) obj2);
            case 1:
                return DefaultTrackSelector.VideoTrackInfo.compareSelections((List) obj, (List) obj2);
            case 2:
                return DefaultTrackSelector.AudioTrackInfo.compareSelections((List) obj, (List) obj2);
            case 3:
                return DefaultTrackSelector.TextTrackInfo.compareSelections((List) obj, (List) obj2);
            case 4:
                return DefaultTrackSelector.VideoTrackInfo.compareNonQualityPreferences((DefaultTrackSelector.VideoTrackInfo) obj, (DefaultTrackSelector.VideoTrackInfo) obj2);
            default:
                return DefaultTrackSelector.VideoTrackInfo.compareQualityPreferences((DefaultTrackSelector.VideoTrackInfo) obj, (DefaultTrackSelector.VideoTrackInfo) obj2);
        }
    }
}
