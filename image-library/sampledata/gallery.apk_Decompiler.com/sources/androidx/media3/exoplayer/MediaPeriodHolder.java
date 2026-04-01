package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.source.ClippingMediaPeriod;
import androidx.media3.exoplayer.source.EmptySampleStream;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MediaPeriodHolder {
    public boolean allRenderersInCorrectState;
    public boolean hasEnabledTracks;
    public MediaPeriodInfo info;
    private final boolean[] mayRetainStreamFlags;
    public final MediaPeriod mediaPeriod;
    private final MediaSourceList mediaSourceList;
    private MediaPeriodHolder next;
    public boolean prepareCalled;
    public boolean prepared;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionOffsetUs;
    public final SampleStream[] sampleStreams;
    public final long targetPreloadBufferDurationUs;
    private TrackGroupArray trackGroups = TrackGroupArray.EMPTY;
    private final TrackSelector trackSelector;
    private TrackSelectorResult trackSelectorResult;
    public final Object uid;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
    }

    public MediaPeriodHolder(RendererCapabilities[] rendererCapabilitiesArr, long j2, TrackSelector trackSelector2, Allocator allocator, MediaSourceList mediaSourceList2, MediaPeriodInfo mediaPeriodInfo, TrackSelectorResult trackSelectorResult2, long j3) {
        this.rendererCapabilities = rendererCapabilitiesArr;
        this.rendererPositionOffsetUs = j2;
        this.trackSelector = trackSelector2;
        this.mediaSourceList = mediaSourceList2;
        RendererCapabilities[] rendererCapabilitiesArr2 = rendererCapabilitiesArr;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.id;
        this.uid = mediaPeriodId.periodUid;
        this.info = mediaPeriodInfo;
        this.targetPreloadBufferDurationUs = j3;
        this.trackSelectorResult = trackSelectorResult2;
        this.sampleStreams = new SampleStream[rendererCapabilitiesArr2.length];
        this.mayRetainStreamFlags = new boolean[rendererCapabilitiesArr2.length];
        MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodInfo;
        this.mediaPeriod = createMediaPeriod(mediaPeriodId, mediaSourceList2, allocator, mediaPeriodInfo.startPositionUs, mediaPeriodInfo2.endPositionUs, mediaPeriodInfo2.isPrecededByTransitionFromSameStream);
    }

    private void associateNoSampleRenderersWithEmptySampleStream(SampleStream[] sampleStreamArr) {
        int i2 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.rendererCapabilities;
            if (i2 < rendererCapabilitiesArr.length) {
                if (rendererCapabilitiesArr[i2].getTrackType() == -2 && this.trackSelectorResult.isRendererEnabled(i2)) {
                    sampleStreamArr[i2] = new EmptySampleStream();
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private static MediaPeriod createMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, MediaSourceList mediaSourceList2, Allocator allocator, long j2, long j3, boolean z) {
        MediaPeriod createPeriod = mediaSourceList2.createPeriod(mediaPeriodId, allocator, j2);
        if (j3 != -9223372036854775807L) {
            return new ClippingMediaPeriod(createPeriod, !z, 0, j3);
        }
        return createPeriod;
    }

    private void disableTrackSelectionsInResult() {
        if (isLoadingMediaPeriod()) {
            int i2 = 0;
            while (true) {
                TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
                if (i2 < trackSelectorResult2.length) {
                    boolean isRendererEnabled = trackSelectorResult2.isRendererEnabled(i2);
                    ExoTrackSelection exoTrackSelection = this.trackSelectorResult.selections[i2];
                    if (isRendererEnabled && exoTrackSelection != null) {
                        exoTrackSelection.disable();
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    private void disassociateNoSampleRenderersWithEmptySampleStream(SampleStream[] sampleStreamArr) {
        int i2 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.rendererCapabilities;
            if (i2 < rendererCapabilitiesArr.length) {
                if (rendererCapabilitiesArr[i2].getTrackType() == -2) {
                    sampleStreamArr[i2] = null;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private void enableTrackSelectionsInResult() {
        if (isLoadingMediaPeriod()) {
            int i2 = 0;
            while (true) {
                TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
                if (i2 < trackSelectorResult2.length) {
                    boolean isRendererEnabled = trackSelectorResult2.isRendererEnabled(i2);
                    ExoTrackSelection exoTrackSelection = this.trackSelectorResult.selections[i2];
                    if (isRendererEnabled && exoTrackSelection != null) {
                        exoTrackSelection.enable();
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    private boolean isLoadingMediaPeriod() {
        if (this.next == null) {
            return true;
        }
        return false;
    }

    private static void releaseMediaPeriod(MediaSourceList mediaSourceList2, MediaPeriod mediaPeriod2) {
        try {
            if (mediaPeriod2 instanceof ClippingMediaPeriod) {
                mediaSourceList2.releasePeriod(((ClippingMediaPeriod) mediaPeriod2).mediaPeriod);
            } else {
                mediaSourceList2.releasePeriod(mediaPeriod2);
            }
        } catch (RuntimeException e) {
            Log.e("MediaPeriodHolder", "Period release failed.", e);
        }
    }

    public long applyTrackSelection(TrackSelectorResult trackSelectorResult2, long j2, boolean z) {
        return applyTrackSelection(trackSelectorResult2, j2, z, new boolean[this.rendererCapabilities.length]);
    }

    public boolean canBeUsedForMediaPeriodInfo(MediaPeriodInfo mediaPeriodInfo) {
        if (!MediaPeriodQueue.areDurationsCompatible(this.info.durationUs, mediaPeriodInfo.durationUs)) {
            return false;
        }
        MediaPeriodInfo mediaPeriodInfo2 = this.info;
        if (mediaPeriodInfo2.startPositionUs != mediaPeriodInfo.startPositionUs || !mediaPeriodInfo2.id.equals(mediaPeriodInfo.id)) {
            return false;
        }
        return true;
    }

    public void continueLoading(LoadingInfo loadingInfo) {
        Assertions.checkState(isLoadingMediaPeriod());
        this.mediaPeriod.continueLoading(loadingInfo);
    }

    public long getBufferedPositionUs() {
        long j2;
        if (!this.prepared) {
            return this.info.startPositionUs;
        }
        if (this.hasEnabledTracks) {
            j2 = this.mediaPeriod.getBufferedPositionUs();
        } else {
            j2 = Long.MIN_VALUE;
        }
        if (j2 == Long.MIN_VALUE) {
            return this.info.durationUs;
        }
        return j2;
    }

    public MediaPeriodHolder getNext() {
        return this.next;
    }

    public long getNextLoadPositionUs() {
        if (!this.prepared) {
            return 0;
        }
        return this.mediaPeriod.getNextLoadPositionUs();
    }

    public long getRendererOffset() {
        return this.rendererPositionOffsetUs;
    }

    public long getStartPositionRendererTime() {
        return this.info.startPositionUs + this.rendererPositionOffsetUs;
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public TrackSelectorResult getTrackSelectorResult() {
        return this.trackSelectorResult;
    }

    public void handlePrepared(float f, Timeline timeline, boolean z) {
        this.prepared = true;
        this.trackGroups = this.mediaPeriod.getTrackGroups();
        TrackSelectorResult selectTracks = selectTracks(f, timeline, z);
        MediaPeriodInfo mediaPeriodInfo = this.info;
        long j2 = mediaPeriodInfo.startPositionUs;
        long j3 = mediaPeriodInfo.durationUs;
        if (j3 != -9223372036854775807L && j2 >= j3) {
            j2 = Math.max(0, j3 - 1);
        }
        long applyTrackSelection = applyTrackSelection(selectTracks, j2, false);
        long j8 = this.rendererPositionOffsetUs;
        MediaPeriodInfo mediaPeriodInfo2 = this.info;
        this.rendererPositionOffsetUs = (mediaPeriodInfo2.startPositionUs - applyTrackSelection) + j8;
        this.info = mediaPeriodInfo2.copyWithStartPositionUs(applyTrackSelection);
    }

    public boolean hasLoadingError() {
        try {
            if (!this.prepared) {
                this.mediaPeriod.maybeThrowPrepareError();
            } else {
                for (SampleStream sampleStream : this.sampleStreams) {
                    if (sampleStream != null) {
                        sampleStream.maybeThrowError();
                    }
                }
            }
            return false;
        } catch (IOException unused) {
            return true;
        }
    }

    public boolean isFullyBuffered() {
        if (!this.prepared) {
            return false;
        }
        if (!this.hasEnabledTracks || this.mediaPeriod.getBufferedPositionUs() == Long.MIN_VALUE) {
            return true;
        }
        return false;
    }

    public boolean isFullyPreloaded() {
        if (!this.prepared) {
            return false;
        }
        if (isFullyBuffered() || getBufferedPositionUs() - this.info.startPositionUs >= this.targetPreloadBufferDurationUs) {
            return true;
        }
        return false;
    }

    public void prepare(MediaPeriod.Callback callback, long j2) {
        this.prepareCalled = true;
        this.mediaPeriod.prepare(callback, j2);
    }

    public void reevaluateBuffer(long j2) {
        Assertions.checkState(isLoadingMediaPeriod());
        if (this.prepared) {
            this.mediaPeriod.reevaluateBuffer(toPeriodTime(j2));
        }
    }

    public void release() {
        disableTrackSelectionsInResult();
        releaseMediaPeriod(this.mediaSourceList, this.mediaPeriod);
    }

    public TrackSelectorResult selectTracks(float f, Timeline timeline, boolean z) {
        TrackSelectorResult selectTracks = this.trackSelector.selectTracks(this.rendererCapabilities, getTrackGroups(), this.info.id, timeline);
        for (int i2 = 0; i2 < selectTracks.length; i2++) {
            boolean z3 = true;
            if (selectTracks.isRendererEnabled(i2)) {
                if (selectTracks.selections[i2] == null && this.rendererCapabilities[i2].getTrackType() != -2) {
                    z3 = false;
                }
                Assertions.checkState(z3);
            } else {
                if (selectTracks.selections[i2] != null) {
                    z3 = false;
                }
                Assertions.checkState(z3);
            }
        }
        for (ExoTrackSelection exoTrackSelection : selectTracks.selections) {
            if (exoTrackSelection != null) {
                exoTrackSelection.onPlaybackSpeed(f);
                exoTrackSelection.onPlayWhenReadyChanged(z);
            }
        }
        return selectTracks;
    }

    public void setNext(MediaPeriodHolder mediaPeriodHolder) {
        if (mediaPeriodHolder != this.next) {
            disableTrackSelectionsInResult();
            this.next = mediaPeriodHolder;
            enableTrackSelectionsInResult();
        }
    }

    public void setRendererOffset(long j2) {
        this.rendererPositionOffsetUs = j2;
    }

    public long toPeriodTime(long j2) {
        return j2 - getRendererOffset();
    }

    public long toRendererTime(long j2) {
        return j2 + getRendererOffset();
    }

    public void updateClipping() {
        MediaPeriod mediaPeriod2 = this.mediaPeriod;
        if (mediaPeriod2 instanceof ClippingMediaPeriod) {
            long j2 = this.info.endPositionUs;
            if (j2 == -9223372036854775807L) {
                j2 = Long.MIN_VALUE;
            }
            ((ClippingMediaPeriod) mediaPeriod2).updateClipping(0, j2);
        }
    }

    public long applyTrackSelection(TrackSelectorResult trackSelectorResult2, long j2, boolean z, boolean[] zArr) {
        int i2 = 0;
        while (true) {
            boolean z3 = true;
            if (i2 >= trackSelectorResult2.length) {
                break;
            }
            boolean[] zArr2 = this.mayRetainStreamFlags;
            if (z || !trackSelectorResult2.isEquivalent(this.trackSelectorResult, i2)) {
                z3 = false;
            }
            zArr2[i2] = z3;
            i2++;
        }
        disassociateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        disableTrackSelectionsInResult();
        this.trackSelectorResult = trackSelectorResult2;
        enableTrackSelectionsInResult();
        long selectTracks = this.mediaPeriod.selectTracks(trackSelectorResult2.selections, this.mayRetainStreamFlags, this.sampleStreams, zArr, j2);
        associateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        this.hasEnabledTracks = false;
        int i7 = 0;
        while (true) {
            SampleStream[] sampleStreamArr = this.sampleStreams;
            if (i7 >= sampleStreamArr.length) {
                return selectTracks;
            }
            if (sampleStreamArr[i7] != null) {
                Assertions.checkState(trackSelectorResult2.isRendererEnabled(i7));
                if (this.rendererCapabilities[i7].getTrackType() != -2) {
                    this.hasEnabledTracks = true;
                }
            } else {
                Assertions.checkState(trackSelectorResult2.selections[i7] == null);
            }
            i7++;
        }
    }
}
