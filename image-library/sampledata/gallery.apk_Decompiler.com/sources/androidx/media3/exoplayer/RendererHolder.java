package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RendererHolder {
    private final int index;
    private int prewarmingState = 0;
    private final Renderer primaryRenderer;
    private boolean primaryRequiresReset = false;
    private final Renderer secondaryRenderer;
    private boolean secondaryRequiresReset = false;

    public RendererHolder(Renderer renderer, Renderer renderer2, int i2) {
        this.primaryRenderer = renderer;
        this.index = i2;
        this.secondaryRenderer = renderer2;
    }

    private void disableRenderer(Renderer renderer, DefaultMediaClock defaultMediaClock) {
        boolean z;
        if (this.primaryRenderer == renderer || this.secondaryRenderer == renderer) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        if (isRendererEnabled(renderer)) {
            defaultMediaClock.onRendererDisabled(renderer);
            ensureStopped(renderer);
            renderer.disable();
        }
    }

    private void ensureStopped(Renderer renderer) {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private static Format[] getFormats(ExoTrackSelection exoTrackSelection) {
        int i2;
        if (exoTrackSelection != null) {
            i2 = exoTrackSelection.length();
        } else {
            i2 = 0;
        }
        Format[] formatArr = new Format[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            formatArr[i7] = ((ExoTrackSelection) Assertions.checkNotNull(exoTrackSelection)).getFormat(i7);
        }
        return formatArr;
    }

    private Renderer getRendererReadingFromPeriod(MediaPeriodHolder mediaPeriodHolder) {
        if (!(mediaPeriodHolder == null || mediaPeriodHolder.sampleStreams[this.index] == null)) {
            if (this.primaryRenderer.getStream() == mediaPeriodHolder.sampleStreams[this.index]) {
                return this.primaryRenderer;
            }
            Renderer renderer = this.secondaryRenderer;
            if (renderer != null && renderer.getStream() == mediaPeriodHolder.sampleStreams[this.index]) {
                return this.secondaryRenderer;
            }
        }
        return null;
    }

    private boolean hasFinishedReadingFromPeriodInternal(MediaPeriodHolder mediaPeriodHolder, Renderer renderer) {
        if (renderer == null) {
            return true;
        }
        SampleStream sampleStream = mediaPeriodHolder.sampleStreams[this.index];
        if (renderer.getStream() == null || (renderer.getStream() == sampleStream && (sampleStream == null || renderer.hasReadStreamToEnd() || hasReachedServerSideInsertedAdsTransition(renderer, mediaPeriodHolder)))) {
            return true;
        }
        MediaPeriodHolder next = mediaPeriodHolder.getNext();
        if (next == null || next.sampleStreams[this.index] != renderer.getStream()) {
            return false;
        }
        return true;
    }

    private boolean hasReachedServerSideInsertedAdsTransition(Renderer renderer, MediaPeriodHolder mediaPeriodHolder) {
        MediaPeriodHolder next = mediaPeriodHolder.getNext();
        if (!mediaPeriodHolder.info.isFollowedByTransitionToSameStream || next == null || !next.prepared || renderer.getReadingPositionUs() < next.getStartPositionRendererTime()) {
            return false;
        }
        return true;
    }

    private boolean isPrimaryRendererPrewarming() {
        int i2 = this.prewarmingState;
        if (i2 == 2 || i2 == 4) {
            return true;
        }
        return false;
    }

    private boolean isSecondaryRendererPrewarming() {
        if (this.prewarmingState == 3) {
            return true;
        }
        return false;
    }

    private void maybeDisableOrResetPositionInternal(Renderer renderer, SampleStream sampleStream, DefaultMediaClock defaultMediaClock, long j2, boolean z) {
        if (!isRendererEnabled(renderer)) {
            return;
        }
        if (sampleStream != renderer.getStream()) {
            disableRenderer(renderer, defaultMediaClock);
        } else if (z) {
            renderer.resetPosition(j2);
        }
    }

    private void maybeResetRenderer(boolean z) {
        if (z) {
            if (this.primaryRequiresReset) {
                this.primaryRenderer.reset();
                this.primaryRequiresReset = false;
            }
        } else if (this.secondaryRequiresReset) {
            ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).reset();
            this.secondaryRequiresReset = false;
        }
    }

    private int replaceStreamsOrDisableRendererForTransitionInternal(Renderer renderer, MediaPeriodHolder mediaPeriodHolder, TrackSelectorResult trackSelectorResult, DefaultMediaClock defaultMediaClock) {
        boolean z;
        if (renderer == null || !isRendererEnabled(renderer) || ((renderer == this.primaryRenderer && isPrimaryRendererPrewarming()) || (renderer == this.secondaryRenderer && isSecondaryRendererPrewarming()))) {
            return 1;
        }
        SampleStream stream = renderer.getStream();
        SampleStream[] sampleStreamArr = mediaPeriodHolder.sampleStreams;
        int i2 = this.index;
        boolean z3 = false;
        if (stream != sampleStreamArr[i2]) {
            z = true;
        } else {
            z = false;
        }
        boolean isRendererEnabled = trackSelectorResult.isRendererEnabled(i2);
        if (isRendererEnabled && !z) {
            return 1;
        }
        if (!renderer.isCurrentStreamFinal()) {
            renderer.replaceStream(getFormats(trackSelectorResult.selections[this.index]), (SampleStream) Assertions.checkNotNull(mediaPeriodHolder.sampleStreams[this.index]), mediaPeriodHolder.getStartPositionRendererTime(), mediaPeriodHolder.getRendererOffset(), mediaPeriodHolder.info.id);
            return 3;
        } else if (!renderer.isEnded()) {
            return 0;
        } else {
            disableRenderer(renderer, defaultMediaClock);
            if (!isRendererEnabled || isPrewarming()) {
                if (renderer == this.primaryRenderer) {
                    z3 = true;
                }
                maybeResetRenderer(z3);
            }
            return 1;
        }
    }

    private void setCurrentStreamFinalInternal(Renderer renderer, long j2) {
        renderer.setCurrentStreamFinal();
    }

    private void transferResources(boolean z) {
        if (z) {
            ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).handleMessage(17, this.primaryRenderer);
        } else {
            this.primaryRenderer.handleMessage(17, Assertions.checkNotNull(this.secondaryRenderer));
        }
    }

    public boolean allowsPlayback(MediaPeriodHolder mediaPeriodHolder) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        if (rendererReadingFromPeriod == null || rendererReadingFromPeriod.hasReadStreamToEnd() || rendererReadingFromPeriod.isReady() || rendererReadingFromPeriod.isEnded()) {
            return true;
        }
        return false;
    }

    public void disable(DefaultMediaClock defaultMediaClock) {
        boolean z;
        disableRenderer(this.primaryRenderer, defaultMediaClock);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            if (!isRendererEnabled(renderer) || this.prewarmingState == 3) {
                z = false;
            } else {
                z = true;
            }
            disableRenderer(this.secondaryRenderer, defaultMediaClock);
            maybeResetRenderer(false);
            if (z) {
                transferResources(true);
            }
        }
        this.prewarmingState = 0;
    }

    public void disablePrewarming(DefaultMediaClock defaultMediaClock) {
        boolean z;
        Renderer renderer;
        if (isPrewarming()) {
            int i2 = this.prewarmingState;
            int i7 = 1;
            if (i2 == 4 || i2 == 2) {
                z = true;
            } else {
                z = false;
            }
            if (i2 != 4) {
                i7 = 0;
            }
            if (z) {
                renderer = this.primaryRenderer;
            } else {
                renderer = (Renderer) Assertions.checkNotNull(this.secondaryRenderer);
            }
            disableRenderer(renderer, defaultMediaClock);
            maybeResetRenderer(z);
            this.prewarmingState = i7;
        }
    }

    public void enable(RendererConfiguration rendererConfiguration, ExoTrackSelection exoTrackSelection, SampleStream sampleStream, long j2, boolean z, boolean z3, long j3, long j8, MediaSource.MediaPeriodId mediaPeriodId, DefaultMediaClock defaultMediaClock) {
        DefaultMediaClock defaultMediaClock2 = defaultMediaClock;
        Format[] formats = getFormats(exoTrackSelection);
        int i2 = this.prewarmingState;
        if (i2 == 0 || i2 == 2 || i2 == 4) {
            this.primaryRequiresReset = true;
            this.primaryRenderer.enable(rendererConfiguration, formats, sampleStream, j2, z, z3, j3, j8, mediaPeriodId);
            defaultMediaClock2.onRendererEnabled(this.primaryRenderer);
            return;
        }
        this.secondaryRequiresReset = true;
        ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).enable(rendererConfiguration, formats, sampleStream, j2, z, z3, j3, j8, mediaPeriodId);
        defaultMediaClock2.onRendererEnabled(this.secondaryRenderer);
    }

    public void enableMayRenderStartOfStream() {
        if (isRendererEnabled(this.primaryRenderer)) {
            this.primaryRenderer.enableMayRenderStartOfStream();
            return;
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null && isRendererEnabled(renderer)) {
            this.secondaryRenderer.enableMayRenderStartOfStream();
        }
    }

    public int getEnabledRendererCount() {
        boolean z;
        boolean isRendererEnabled = isRendererEnabled(this.primaryRenderer);
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || !isRendererEnabled(renderer)) {
            z = false;
        } else {
            z = true;
        }
        return (isRendererEnabled ? 1 : 0) + (z ? 1 : 0);
    }

    public long getMinDurationToProgressUs(long j2, long j3) {
        long j8;
        if (isRendererEnabled(this.primaryRenderer)) {
            j8 = this.primaryRenderer.getDurationToProgressUs(j2, j3);
        } else {
            j8 = Long.MAX_VALUE;
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || !isRendererEnabled(renderer)) {
            return j8;
        }
        return Math.min(j8, this.secondaryRenderer.getDurationToProgressUs(j2, j3));
    }

    public long getReadingPositionUs(MediaPeriodHolder mediaPeriodHolder) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        Objects.requireNonNull(rendererReadingFromPeriod);
        return rendererReadingFromPeriod.getReadingPositionUs();
    }

    public int getTrackType() {
        return this.primaryRenderer.getTrackType();
    }

    public void handleMessage(int i2, Object obj, MediaPeriodHolder mediaPeriodHolder) {
        ((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(mediaPeriodHolder))).handleMessage(i2, obj);
    }

    public boolean hasFinishedReadingFromPeriod(MediaPeriodHolder mediaPeriodHolder) {
        if (!hasFinishedReadingFromPeriodInternal(mediaPeriodHolder, this.primaryRenderer) || !hasFinishedReadingFromPeriodInternal(mediaPeriodHolder, this.secondaryRenderer)) {
            return false;
        }
        return true;
    }

    public boolean hasReadPeriodToEnd(MediaPeriodHolder mediaPeriodHolder) {
        return ((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(mediaPeriodHolder))).hasReadStreamToEnd();
    }

    public boolean hasSecondary() {
        if (this.secondaryRenderer != null) {
            return true;
        }
        return false;
    }

    public boolean isEnded() {
        boolean z;
        if (isRendererEnabled(this.primaryRenderer)) {
            z = this.primaryRenderer.isEnded();
        } else {
            z = true;
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || !isRendererEnabled(renderer)) {
            return z;
        }
        return this.secondaryRenderer.isEnded() & z;
    }

    public boolean isPrewarming() {
        if (isPrimaryRendererPrewarming() || isSecondaryRendererPrewarming()) {
            return true;
        }
        return false;
    }

    public boolean isPrewarmingPeriod(MediaPeriodHolder mediaPeriodHolder) {
        boolean z;
        boolean z3;
        if (!isPrimaryRendererPrewarming() || getRendererReadingFromPeriod(mediaPeriodHolder) != this.primaryRenderer) {
            z = false;
        } else {
            z = true;
        }
        if (!isSecondaryRendererPrewarming() || getRendererReadingFromPeriod(mediaPeriodHolder) != this.secondaryRenderer) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z || z3) {
            return true;
        }
        return false;
    }

    public boolean isReadingFromPeriod(MediaPeriodHolder mediaPeriodHolder) {
        if (getRendererReadingFromPeriod(mediaPeriodHolder) != null) {
            return true;
        }
        return false;
    }

    public boolean isRendererEnabled() {
        int i2 = this.prewarmingState;
        if (i2 == 0 || i2 == 2 || i2 == 4) {
            return isRendererEnabled(this.primaryRenderer);
        }
        return isRendererEnabled((Renderer) Assertions.checkNotNull(this.secondaryRenderer));
    }

    public void maybeDisableOrResetPosition(SampleStream sampleStream, DefaultMediaClock defaultMediaClock, long j2, boolean z) {
        SampleStream sampleStream2 = sampleStream;
        DefaultMediaClock defaultMediaClock2 = defaultMediaClock;
        long j3 = j2;
        boolean z3 = z;
        maybeDisableOrResetPositionInternal(this.primaryRenderer, sampleStream2, defaultMediaClock2, j3, z3);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            boolean z7 = z3;
            long j8 = j3;
            maybeDisableOrResetPositionInternal(renderer, sampleStream2, defaultMediaClock2, j8, z7);
        }
    }

    public void maybeHandlePrewarmingTransition() {
        boolean z;
        int i2 = this.prewarmingState;
        int i7 = 0;
        if (i2 == 3 || i2 == 4) {
            if (i2 == 4) {
                z = true;
            } else {
                z = false;
            }
            transferResources(z);
            if (this.prewarmingState != 4) {
                i7 = 1;
            }
            this.prewarmingState = i7;
        } else if (i2 == 2) {
            this.prewarmingState = 0;
        }
    }

    public void maybeSetOldStreamToFinal(TrackSelectorResult trackSelectorResult, TrackSelectorResult trackSelectorResult2, long j2) {
        Renderer renderer;
        boolean z;
        int i2;
        boolean isRendererEnabled = trackSelectorResult.isRendererEnabled(this.index);
        boolean isRendererEnabled2 = trackSelectorResult2.isRendererEnabled(this.index);
        if (this.secondaryRenderer == null || (i2 = this.prewarmingState) == 3 || (i2 == 0 && isRendererEnabled(this.primaryRenderer))) {
            renderer = this.primaryRenderer;
        } else {
            renderer = (Renderer) Assertions.checkNotNull(this.secondaryRenderer);
        }
        if (isRendererEnabled && !renderer.isCurrentStreamFinal()) {
            if (getTrackType() == -2) {
                z = true;
            } else {
                z = false;
            }
            RendererConfiguration[] rendererConfigurationArr = trackSelectorResult.rendererConfigurations;
            int i7 = this.index;
            RendererConfiguration rendererConfiguration = rendererConfigurationArr[i7];
            RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i7];
            if (!isRendererEnabled2 || !Objects.equals(rendererConfiguration2, rendererConfiguration) || z || isPrewarming()) {
                setCurrentStreamFinalInternal(renderer, j2);
            }
        }
    }

    public void maybeThrowStreamError(MediaPeriodHolder mediaPeriodHolder) {
        ((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(mediaPeriodHolder))).maybeThrowStreamError();
    }

    public void release() {
        this.primaryRenderer.release();
        this.primaryRequiresReset = false;
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.release();
            this.secondaryRequiresReset = false;
        }
    }

    public void render(long j2, long j3) {
        if (isRendererEnabled(this.primaryRenderer)) {
            this.primaryRenderer.render(j2, j3);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null && isRendererEnabled(renderer)) {
            this.secondaryRenderer.render(j2, j3);
        }
    }

    public int replaceStreamsOrDisableRendererForTransition(MediaPeriodHolder mediaPeriodHolder, TrackSelectorResult trackSelectorResult, DefaultMediaClock defaultMediaClock) {
        int replaceStreamsOrDisableRendererForTransitionInternal = replaceStreamsOrDisableRendererForTransitionInternal(this.primaryRenderer, mediaPeriodHolder, trackSelectorResult, defaultMediaClock);
        int replaceStreamsOrDisableRendererForTransitionInternal2 = replaceStreamsOrDisableRendererForTransitionInternal(this.secondaryRenderer, mediaPeriodHolder, trackSelectorResult, defaultMediaClock);
        if (replaceStreamsOrDisableRendererForTransitionInternal == 1) {
            return replaceStreamsOrDisableRendererForTransitionInternal2;
        }
        return replaceStreamsOrDisableRendererForTransitionInternal;
    }

    public void reset() {
        if (!isRendererEnabled(this.primaryRenderer)) {
            maybeResetRenderer(true);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null && !isRendererEnabled(renderer)) {
            maybeResetRenderer(false);
        }
    }

    public void resetPosition(MediaPeriodHolder mediaPeriodHolder, long j2) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        if (rendererReadingFromPeriod != null) {
            rendererReadingFromPeriod.resetPosition(j2);
        }
    }

    public void setAllNonPrewarmingRendererStreamsFinal(long j2) {
        int i2;
        if (!(!isRendererEnabled(this.primaryRenderer) || (i2 = this.prewarmingState) == 4 || i2 == 2)) {
            setCurrentStreamFinalInternal(this.primaryRenderer, j2);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null && isRendererEnabled(renderer) && this.prewarmingState != 3) {
            setCurrentStreamFinalInternal(this.secondaryRenderer, j2);
        }
    }

    public void setCurrentStreamFinal(MediaPeriodHolder mediaPeriodHolder, long j2) {
        setCurrentStreamFinalInternal((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(mediaPeriodHolder)), j2);
    }

    public void setPlaybackSpeed(float f, float f5) {
        this.primaryRenderer.setPlaybackSpeed(f, f5);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.setPlaybackSpeed(f, f5);
        }
    }

    public void setScrubbingMode(ScrubbingModeParameters scrubbingModeParameters) {
        this.primaryRenderer.handleMessage(18, scrubbingModeParameters);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.handleMessage(18, scrubbingModeParameters);
        }
    }

    public void setTimeline(Timeline timeline) {
        this.primaryRenderer.setTimeline(timeline);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.setTimeline(timeline);
        }
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        if (getTrackType() == 2) {
            this.primaryRenderer.handleMessage(7, videoFrameMetadataListener);
            Renderer renderer = this.secondaryRenderer;
            if (renderer != null) {
                renderer.handleMessage(7, videoFrameMetadataListener);
            }
        }
    }

    public void setVideoOutput(Object obj) {
        if (getTrackType() == 2) {
            int i2 = this.prewarmingState;
            if (i2 == 4 || i2 == 1) {
                ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).handleMessage(1, obj);
            } else {
                this.primaryRenderer.handleMessage(1, obj);
            }
        }
    }

    public void setVolume(float f) {
        if (getTrackType() == 1) {
            this.primaryRenderer.handleMessage(2, Float.valueOf(f));
            Renderer renderer = this.secondaryRenderer;
            if (renderer != null) {
                renderer.handleMessage(2, Float.valueOf(f));
            }
        }
    }

    public void start() {
        if (this.primaryRenderer.getState() != 1 || this.prewarmingState == 4) {
            Renderer renderer = this.secondaryRenderer;
            if (renderer != null && renderer.getState() == 1 && this.prewarmingState != 3) {
                this.secondaryRenderer.start();
                return;
            }
            return;
        }
        this.primaryRenderer.start();
    }

    public void startPrewarming() {
        int i2;
        Assertions.checkState(!isPrewarming());
        if (isRendererEnabled(this.primaryRenderer)) {
            i2 = 3;
        } else {
            Renderer renderer = this.secondaryRenderer;
            if (renderer == null || !isRendererEnabled(renderer)) {
                i2 = 2;
            } else {
                i2 = 4;
            }
        }
        this.prewarmingState = i2;
    }

    public void stop() {
        if (isRendererEnabled(this.primaryRenderer)) {
            ensureStopped(this.primaryRenderer);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null && isRendererEnabled(renderer)) {
            ensureStopped(this.secondaryRenderer);
        }
    }

    private static boolean isRendererEnabled(Renderer renderer) {
        return renderer.getState() != 0;
    }
}
