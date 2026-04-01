package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.RendererConfiguration;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TrackSelectorResult {
    public final Object info;
    public final int length;
    public final RendererConfiguration[] rendererConfigurations;
    public final ExoTrackSelection[] selections;
    public final Tracks tracks;

    public TrackSelectorResult(RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr, Tracks tracks2, Object obj) {
        boolean z;
        if (rendererConfigurationArr.length == exoTrackSelectionArr.length) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        this.rendererConfigurations = rendererConfigurationArr;
        this.selections = (ExoTrackSelection[]) exoTrackSelectionArr.clone();
        this.tracks = tracks2;
        this.info = obj;
        this.length = rendererConfigurationArr.length;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult) {
        if (trackSelectorResult == null || trackSelectorResult.selections.length != this.selections.length) {
            return false;
        }
        for (int i2 = 0; i2 < this.selections.length; i2++) {
            if (!isEquivalent(trackSelectorResult, i2)) {
                return false;
            }
        }
        return true;
    }

    public boolean isRendererEnabled(int i2) {
        if (this.rendererConfigurations[i2] != null) {
            return true;
        }
        return false;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult, int i2) {
        if (trackSelectorResult != null && Objects.equals(this.rendererConfigurations[i2], trackSelectorResult.rendererConfigurations[i2]) && Objects.equals(this.selections[i2], trackSelectorResult.selections[i2])) {
            return true;
        }
        return false;
    }
}
