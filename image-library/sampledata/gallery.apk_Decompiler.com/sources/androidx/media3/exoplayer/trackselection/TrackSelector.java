package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrackSelector {
    private BandwidthMeter bandwidthMeter;
    private InvalidationListener listener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface InvalidationListener {
        void onTrackSelectionsInvalidated();
    }

    public final BandwidthMeter getBandwidthMeter() {
        return (BandwidthMeter) Assertions.checkStateNotNull(this.bandwidthMeter);
    }

    public abstract RendererCapabilities.Listener getRendererCapabilitiesListener();

    public void init(InvalidationListener invalidationListener, BandwidthMeter bandwidthMeter2) {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.listener = invalidationListener;
        this.bandwidthMeter = bandwidthMeter2;
    }

    public final void invalidate() {
        InvalidationListener invalidationListener = this.listener;
        if (invalidationListener != null) {
            invalidationListener.onTrackSelectionsInvalidated();
        }
    }

    public abstract boolean isSetParametersSupported();

    public abstract void onSelectionActivated(Object obj);

    public void release() {
        this.listener = null;
        this.bandwidthMeter = null;
    }

    public abstract TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline);

    public abstract void setAudioAttributes(AudioAttributes audioAttributes);
}
