package androidx.media3.exoplayer;

import A.a;
import F2.G;
import F2.N;
import F2.U;
import F2.y0;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.exoplayer.AudioFocusManager;
import androidx.media3.exoplayer.DefaultMediaClock;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSourceList.MediaSourceListInfoRefreshListener, DefaultMediaClock.PlaybackParametersListener, PlayerMessage.Sender, AudioFocusManager.PlayerControl, VideoFrameMetadataListener {
    private static final long BUFFERING_MAXIMUM_INTERVAL_MS = Util.usToMs(10000);
    private final AnalyticsCollector analyticsCollector;
    private final HandlerWrapper applicationLooperHandler;
    private final AudioFocusManager audioFocusManager;
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private boolean deliverPendingMessageAtStartPositionRequired;
    private final boolean dynamicSchedulingEnabled;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private int enabledRendererCount;
    private boolean foregroundMode;
    private final HandlerWrapper handler;
    private final boolean hasSecondaryRenderers;
    private boolean isPrewarmingDisabledUntilNextTransition;
    private boolean isRebuffering;
    private Timeline lastPreloadPoolInvalidationTimeline;
    private long lastRebufferRealtimeMs;
    private final LivePlaybackSpeedControl livePlaybackSpeedControl;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private final MediaSourceList mediaSourceList;
    private int nextPendingMessageIndexHint;
    private boolean offloadSchedulingEnabled;
    private boolean pauseAtEndOfWindow;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private boolean pendingPauseAtEndOfPeriod;
    private ExoPlaybackException pendingRecoverableRendererError;
    private final Timeline.Period period;
    private PlaybackInfo playbackInfo;
    private PlaybackInfoUpdate playbackInfoUpdate;
    private final PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private final Looper playbackLooper;
    private final PlaybackLooperProvider playbackLooperProvider;
    private long playbackMaybeBecameStuckAtMs;
    private final PlayerId playerId;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private long prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
    private final MediaPeriodQueue queue;
    private SeekPosition queuedSeekWhileScrubbing;
    private final long releaseTimeoutMs;
    private boolean releasedOnApplicationThread;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionElapsedRealtimeUs;
    private long rendererPositionUs;
    private final boolean[] rendererReportedReady;
    private final RendererHolder[] renderers;
    private int repeatMode;
    private boolean requestForRendererSleep;
    private final boolean retainBackBufferFromKeyframe;
    private boolean scrubbingModeEnabled;
    private ScrubbingModeParameters scrubbingModeParameters;
    private SeekParameters scrubbingModeSeekParameters;
    private boolean seekIsPendingWhileScrubbing;
    private SeekParameters seekParameters;
    private long setForegroundModeTimeoutMs;
    private boolean shouldContinueLoading;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private float volume;
    private final Timeline.Window window;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaSourceListUpdateMessage {
        /* access modifiers changed from: private */
        public final List<MediaSourceList.MediaSourceHolder> mediaSourceHolders;
        /* access modifiers changed from: private */
        public final long positionUs;
        /* access modifiers changed from: private */
        public final ShuffleOrder shuffleOrder;
        /* access modifiers changed from: private */
        public final int windowIndex;

        private MediaSourceListUpdateMessage(List<MediaSourceList.MediaSourceHolder> list, ShuffleOrder shuffleOrder2, int i2, long j2) {
            this.mediaSourceHolders = list;
            this.shuffleOrder = shuffleOrder2;
            this.windowIndex = i2;
            this.positionUs = j2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MoveMediaItemsMessage {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i2, long j2, Object obj) {
            this.resolvedPeriodIndex = i2;
            this.resolvedPeriodTimeUs = j2;
            this.resolvedPeriodUid = obj;
        }

        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            Object obj = this.resolvedPeriodUid;
            if ((obj == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                return obj != null ? -1 : 1;
            }
            if (obj == null) {
                return 0;
            }
            int i2 = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
            if (i2 != 0) {
                return i2;
            }
            return Long.compare(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PlaybackInfoUpdate {
        public int discontinuityReason;
        /* access modifiers changed from: private */
        public boolean hasPendingChange;
        public int operationAcks;
        public PlaybackInfo playbackInfo;
        public boolean positionDiscontinuity;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo2) {
            this.playbackInfo = playbackInfo2;
        }

        public void incrementPendingOperationAcks(int i2) {
            boolean z;
            boolean z3 = this.hasPendingChange;
            if (i2 > 0) {
                z = true;
            } else {
                z = false;
            }
            this.hasPendingChange = z3 | z;
            this.operationAcks += i2;
        }

        public void setPlaybackInfo(PlaybackInfo playbackInfo2) {
            boolean z;
            boolean z3 = this.hasPendingChange;
            if (this.playbackInfo != playbackInfo2) {
                z = true;
            } else {
                z = false;
            }
            this.hasPendingChange = z3 | z;
            this.playbackInfo = playbackInfo2;
        }

        public void setPositionDiscontinuity(int i2) {
            boolean z = true;
            if (!this.positionDiscontinuity || this.discontinuityReason == 5) {
                this.hasPendingChange = true;
                this.positionDiscontinuity = true;
                this.discontinuityReason = i2;
                return;
            }
            if (i2 != 5) {
                z = false;
            }
            Assertions.checkArgument(z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PlaybackInfoUpdateListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PositionUpdateForPlaylistChange {
        public final boolean endPlayback;
        public final boolean forceBufferingState;
        public final MediaSource.MediaPeriodId periodId;
        public final long periodPositionUs;
        public final long requestedContentPositionUs;
        public final boolean setTargetLiveOffset;

        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, boolean z, boolean z3, boolean z7) {
            this.periodId = mediaPeriodId;
            this.periodPositionUs = j2;
            this.requestedContentPositionUs = j3;
            this.forceBufferingState = z;
            this.endPlayback = z3;
            this.setTargetLiveOffset = z7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline2, int i2, long j2) {
            this.timeline = timeline2;
            this.windowIndex = i2;
            this.windowPositionUs = j2;
        }
    }

    public ExoPlayerImplInternal(Context context, Renderer[] rendererArr, Renderer[] rendererArr2, TrackSelector trackSelector2, TrackSelectorResult trackSelectorResult, LoadControl loadControl2, BandwidthMeter bandwidthMeter2, int i2, boolean z, AnalyticsCollector analyticsCollector2, SeekParameters seekParameters2, LivePlaybackSpeedControl livePlaybackSpeedControl2, long j2, boolean z3, boolean z7, Looper looper, Clock clock2, PlaybackInfoUpdateListener playbackInfoUpdateListener2, PlayerId playerId2, PlaybackLooperProvider playbackLooperProvider2, ExoPlayer.PreloadConfiguration preloadConfiguration2, VideoFrameMetadataListener videoFrameMetadataListener) {
        Renderer[] rendererArr3 = rendererArr;
        TrackSelector trackSelector3 = trackSelector2;
        LoadControl loadControl3 = loadControl2;
        BandwidthMeter bandwidthMeter3 = bandwidthMeter2;
        AnalyticsCollector analyticsCollector3 = analyticsCollector2;
        long j3 = j2;
        Clock clock3 = clock2;
        PlayerId playerId3 = playerId2;
        ExoPlayer.PreloadConfiguration preloadConfiguration3 = preloadConfiguration2;
        this.playbackInfoUpdateListener = playbackInfoUpdateListener2;
        this.trackSelector = trackSelector3;
        TrackSelectorResult trackSelectorResult2 = trackSelectorResult;
        this.emptyTrackSelectorResult = trackSelectorResult2;
        this.loadControl = loadControl3;
        this.bandwidthMeter = bandwidthMeter3;
        this.repeatMode = i2;
        this.shuffleModeEnabled = z;
        this.seekParameters = seekParameters2;
        this.livePlaybackSpeedControl = livePlaybackSpeedControl2;
        this.releaseTimeoutMs = j3;
        this.setForegroundModeTimeoutMs = j3;
        this.pauseAtEndOfWindow = z3;
        this.dynamicSchedulingEnabled = z7;
        this.clock = clock3;
        this.playerId = playerId3;
        this.preloadConfiguration = preloadConfiguration3;
        this.analyticsCollector = analyticsCollector3;
        this.volume = 1.0f;
        this.scrubbingModeParameters = ScrubbingModeParameters.DEFAULT;
        this.playbackMaybeBecameStuckAtMs = -9223372036854775807L;
        this.lastRebufferRealtimeMs = -9223372036854775807L;
        this.backBufferDurationUs = loadControl3.getBackBufferDurationUs(playerId3);
        this.retainBackBufferFromKeyframe = loadControl3.retainBackBufferFromKeyframe(playerId3);
        this.lastPreloadPoolInvalidationTimeline = Timeline.EMPTY;
        PlaybackInfo createDummy = PlaybackInfo.createDummy(trackSelectorResult2);
        this.playbackInfo = createDummy;
        this.playbackInfoUpdate = new PlaybackInfoUpdate(createDummy);
        this.rendererCapabilities = new RendererCapabilities[rendererArr3.length];
        this.rendererReportedReady = new boolean[rendererArr3.length];
        RendererCapabilities.Listener rendererCapabilitiesListener = trackSelector3.getRendererCapabilitiesListener();
        this.renderers = new RendererHolder[rendererArr3.length];
        boolean z9 = false;
        for (int i7 = 0; i7 < rendererArr3.length; i7++) {
            rendererArr3[i7].init(i7, playerId3, clock3);
            this.rendererCapabilities[i7] = rendererArr3[i7].getCapabilities();
            if (rendererCapabilitiesListener != null) {
                this.rendererCapabilities[i7].setListener(rendererCapabilitiesListener);
            }
            Renderer renderer = rendererArr2[i7];
            if (renderer != null) {
                renderer.init(i7, playerId3, clock3);
                z9 = true;
            }
            this.renderers[i7] = new RendererHolder(rendererArr3[i7], rendererArr2[i7], i7);
        }
        this.hasSecondaryRenderers = z9;
        this.mediaClock = new DefaultMediaClock(this, clock3);
        this.pendingMessages = new ArrayList<>();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector3.init(this, bandwidthMeter3);
        this.deliverPendingMessageAtStartPositionRequired = true;
        HandlerWrapper createHandler = clock3.createHandler(looper, (Handler.Callback) null);
        this.applicationLooperHandler = createHandler;
        this.queue = new MediaPeriodQueue(analyticsCollector3, createHandler, new h(2, this), preloadConfiguration3);
        this.mediaSourceList = new MediaSourceList(this, analyticsCollector3, createHandler, playerId3);
        PlaybackLooperProvider playbackLooperProvider3 = playbackLooperProvider2 == null ? new PlaybackLooperProvider() : playbackLooperProvider2;
        this.playbackLooperProvider = playbackLooperProvider3;
        Looper obtainLooper = playbackLooperProvider3.obtainLooper();
        this.playbackLooper = obtainLooper;
        HandlerWrapper createHandler2 = clock3.createHandler(obtainLooper, this);
        this.handler = createHandler2;
        this.audioFocusManager = new AudioFocusManager(context, obtainLooper, this);
        createHandler2.obtainMessage(35, new Object()).sendToTarget();
    }

    private void addMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage, int i2) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        MediaSourceList mediaSourceList2 = this.mediaSourceList;
        if (i2 == -1) {
            i2 = mediaSourceList2.getSize();
        }
        handleMediaSourceListInfoRefreshed(mediaSourceList2.addMediaSources(i2, mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    private void allowRenderersToRenderStartOfStreams() {
        TrackSelectorResult trackSelectorResult = this.queue.getPlayingPeriod().getTrackSelectorResult();
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            if (trackSelectorResult.isRendererEnabled(i2)) {
                this.renderers[i2].enableMayRenderStartOfStream();
            }
        }
    }

    private void applyScrubbingModeParameters() {
        ScrubbingModeParameters scrubbingModeParameters2;
        for (RendererHolder rendererHolder : this.renderers) {
            if (this.scrubbingModeEnabled) {
                scrubbingModeParameters2 = this.scrubbingModeParameters;
            } else {
                scrubbingModeParameters2 = null;
            }
            rendererHolder.setScrubbingMode(scrubbingModeParameters2);
        }
    }

    private boolean areRenderersPrewarming() {
        if (!this.hasSecondaryRenderers) {
            return false;
        }
        for (RendererHolder isPrewarming : this.renderers) {
            if (isPrewarming.isPrewarming()) {
                return true;
            }
        }
        return false;
    }

    private void attemptRendererErrorRecovery() {
        reselectTracksInternalAndSeek();
    }

    /* access modifiers changed from: private */
    public MediaPeriodHolder createMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo, long j2) {
        return new MediaPeriodHolder(this.rendererCapabilities, j2, this.trackSelector, this.loadControl.getAllocator(), this.mediaSourceList, mediaPeriodInfo, this.emptyTrackSelectorResult, this.preloadConfiguration.targetPreloadDurationUs);
    }

    private void deliverMessage(PlayerMessage playerMessage) {
        if (!playerMessage.isCanceled()) {
            try {
                playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
            } finally {
                playerMessage.markAsProcessed(true);
            }
        }
    }

    private void disableAndResetPrewarmingRenderers() {
        if (this.hasSecondaryRenderers && areRenderersPrewarming()) {
            for (RendererHolder rendererHolder : this.renderers) {
                int enabledRendererCount2 = rendererHolder.getEnabledRendererCount();
                rendererHolder.disablePrewarming(this.mediaClock);
                this.enabledRendererCount -= enabledRendererCount2 - rendererHolder.getEnabledRendererCount();
            }
            this.prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
        }
    }

    private void disableRenderer(int i2) {
        int enabledRendererCount2 = this.renderers[i2].getEnabledRendererCount();
        this.renderers[i2].disable(this.mediaClock);
        maybeTriggerOnRendererReadyChanged(i2, false);
        this.enabledRendererCount -= enabledRendererCount2;
    }

    private void disableRenderers() {
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            disableRenderer(i2);
        }
        this.prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ad, code lost:
        if (r11 <= r0.playbackInfo.positionUs) goto L_0x00b1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0196  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doSomeWork() {
        /*
            r17 = this;
            r0 = r17
            androidx.media3.common.util.Clock r1 = r0.clock
            long r1 = r1.uptimeMillis()
            androidx.media3.common.util.HandlerWrapper r3 = r0.handler
            r4 = 2
            r3.removeMessages(r4)
            r0.updatePeriods()
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r5 = 1
            if (r3 == r5) goto L_0x01cc
            r6 = 4
            if (r3 != r6) goto L_0x001d
            goto L_0x01cc
        L_0x001d:
            androidx.media3.exoplayer.MediaPeriodQueue r3 = r0.queue
            androidx.media3.exoplayer.MediaPeriodHolder r3 = r3.getPlayingPeriod()
            if (r3 != 0) goto L_0x0029
            r0.scheduleNextWork(r1)
            return
        L_0x0029:
            java.lang.String r7 = "doSomeWork"
            androidx.media3.common.util.TraceUtil.beginSection(r7)
            r0.updatePlaybackPositions()
            boolean r7 = r3.prepared
            r8 = 0
            if (r7 == 0) goto L_0x008c
            androidx.media3.common.util.Clock r7 = r0.clock
            long r9 = r7.elapsedRealtime()
            long r9 = androidx.media3.common.util.Util.msToUs(r9)
            r0.rendererPositionElapsedRealtimeUs = r9
            androidx.media3.exoplayer.source.MediaPeriod r7 = r3.mediaPeriod
            androidx.media3.exoplayer.PlaybackInfo r9 = r0.playbackInfo
            long r9 = r9.positionUs
            long r11 = r0.backBufferDurationUs
            long r9 = r9 - r11
            boolean r11 = r0.retainBackBufferFromKeyframe
            r7.discardBuffer(r9, r11)
            r9 = r5
            r10 = r9
            r7 = r8
        L_0x0053:
            androidx.media3.exoplayer.RendererHolder[] r11 = r0.renderers
            int r12 = r11.length
            if (r7 >= r12) goto L_0x0093
            r11 = r11[r7]
            int r12 = r11.getEnabledRendererCount()
            if (r12 != 0) goto L_0x0064
            r0.maybeTriggerOnRendererReadyChanged(r7, r8)
            goto L_0x0089
        L_0x0064:
            long r12 = r0.rendererPositionUs
            long r14 = r0.rendererPositionElapsedRealtimeUs
            r11.render(r12, r14)
            if (r9 == 0) goto L_0x0075
            boolean r9 = r11.isEnded()
            if (r9 == 0) goto L_0x0075
            r9 = r5
            goto L_0x0076
        L_0x0075:
            r9 = r8
        L_0x0076:
            boolean r11 = r11.allowsPlayback(r3)
            r0.maybeTriggerOnRendererReadyChanged(r7, r11)
            if (r10 == 0) goto L_0x0083
            if (r11 == 0) goto L_0x0083
            r10 = r5
            goto L_0x0084
        L_0x0083:
            r10 = r8
        L_0x0084:
            if (r11 != 0) goto L_0x0089
            r0.maybeThrowRendererStreamError(r7)
        L_0x0089:
            int r7 = r7 + 1
            goto L_0x0053
        L_0x008c:
            androidx.media3.exoplayer.source.MediaPeriod r7 = r3.mediaPeriod
            r7.maybeThrowPrepareError()
            r9 = r5
            r10 = r9
        L_0x0093:
            androidx.media3.exoplayer.MediaPeriodInfo r7 = r3.info
            long r11 = r7.durationUs
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r9 == 0) goto L_0x00b3
            boolean r7 = r3.prepared
            if (r7 == 0) goto L_0x00b3
            int r7 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x00b0
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            r15 = r13
            long r13 = r7.positionUs
            int r7 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r7 > 0) goto L_0x00b4
            goto L_0x00b1
        L_0x00b0:
            r15 = r13
        L_0x00b1:
            r7 = r5
            goto L_0x00b5
        L_0x00b3:
            r15 = r13
        L_0x00b4:
            r7 = r8
        L_0x00b5:
            if (r7 == 0) goto L_0x00c5
            boolean r9 = r0.pendingPauseAtEndOfPeriod
            if (r9 == 0) goto L_0x00c5
            r0.pendingPauseAtEndOfPeriod = r8
            androidx.media3.exoplayer.PlaybackInfo r9 = r0.playbackInfo
            int r9 = r9.playbackSuppressionReason
            r11 = 5
            r0.setPlayWhenReadyInternal(r8, r9, r8, r11)
        L_0x00c5:
            r9 = 3
            if (r7 == 0) goto L_0x00d5
            androidx.media3.exoplayer.MediaPeriodInfo r7 = r3.info
            boolean r7 = r7.isFinal
            if (r7 == 0) goto L_0x00d5
            r0.setState(r6)
            r0.stopRenderers()
            goto L_0x0125
        L_0x00d5:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            int r7 = r7.playbackState
            if (r7 != r4) goto L_0x00f9
            boolean r7 = r0.shouldTransitionToReadyState(r10)
            if (r7 == 0) goto L_0x00f9
            r0.setState(r9)
            r7 = 0
            r0.pendingRecoverableRendererError = r7
            boolean r7 = r0.shouldPlayWhenReady()
            if (r7 == 0) goto L_0x0125
            r0.updateRebufferingState(r8, r8)
            androidx.media3.exoplayer.DefaultMediaClock r7 = r0.mediaClock
            r7.start()
            r0.startRenderers()
            goto L_0x0125
        L_0x00f9:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            int r7 = r7.playbackState
            if (r7 != r9) goto L_0x0125
            int r7 = r0.enabledRendererCount
            if (r7 != 0) goto L_0x010a
            boolean r7 = r0.isTimelineReady()
            if (r7 == 0) goto L_0x010c
            goto L_0x0125
        L_0x010a:
            if (r10 != 0) goto L_0x0125
        L_0x010c:
            boolean r7 = r0.shouldPlayWhenReady()
            r0.updateRebufferingState(r7, r8)
            r0.setState(r4)
            boolean r7 = r0.isRebuffering
            if (r7 == 0) goto L_0x0122
            r0.notifyTrackSelectionRebuffer()
            androidx.media3.exoplayer.LivePlaybackSpeedControl r7 = r0.livePlaybackSpeedControl
            r7.notifyRebuffer()
        L_0x0122:
            r0.stopRenderers()
        L_0x0125:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            int r7 = r7.playbackState
            if (r7 != r4) goto L_0x0187
            r7 = r8
        L_0x012c:
            androidx.media3.exoplayer.RendererHolder[] r10 = r0.renderers
            int r11 = r10.length
            if (r7 >= r11) goto L_0x013f
            r10 = r10[r7]
            boolean r10 = r10.isReadingFromPeriod(r3)
            if (r10 == 0) goto L_0x013c
            r0.maybeThrowRendererStreamError(r7)
        L_0x013c:
            int r7 = r7 + 1
            goto L_0x012c
        L_0x013f:
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            boolean r7 = r3.isLoading
            if (r7 != 0) goto L_0x0187
            long r10 = r3.totalBufferedDurationUs
            r12 = 500000(0x7a120, double:2.47033E-318)
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 >= 0) goto L_0x0187
            androidx.media3.exoplayer.MediaPeriodQueue r3 = r0.queue
            androidx.media3.exoplayer.MediaPeriodHolder r3 = r3.getLoadingPeriod()
            boolean r3 = r0.isLoadingPossible(r3)
            if (r3 == 0) goto L_0x0187
            boolean r3 = r0.shouldPlayWhenReady()
            if (r3 == 0) goto L_0x0187
            long r10 = r0.playbackMaybeBecameStuckAtMs
            int r3 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r3 != 0) goto L_0x016f
            androidx.media3.common.util.Clock r3 = r0.clock
            long r10 = r3.elapsedRealtime()
            r0.playbackMaybeBecameStuckAtMs = r10
            goto L_0x018a
        L_0x016f:
            androidx.media3.common.util.Clock r3 = r0.clock
            long r10 = r3.elapsedRealtime()
            long r12 = r0.playbackMaybeBecameStuckAtMs
            long r10 = r10 - r12
            r12 = 4000(0xfa0, double:1.9763E-320)
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 >= 0) goto L_0x017f
            goto L_0x018a
        L_0x017f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Playback stuck buffering and not loading"
            r0.<init>(r1)
            throw r0
        L_0x0187:
            r10 = r15
            r0.playbackMaybeBecameStuckAtMs = r10
        L_0x018a:
            boolean r3 = r0.shouldPlayWhenReady()
            if (r3 == 0) goto L_0x0198
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 != r9) goto L_0x0198
            r3 = r5
            goto L_0x0199
        L_0x0198:
            r3 = r8
        L_0x0199:
            boolean r7 = r0.offloadSchedulingEnabled
            if (r7 == 0) goto L_0x01a4
            boolean r7 = r0.requestForRendererSleep
            if (r7 == 0) goto L_0x01a4
            if (r3 == 0) goto L_0x01a4
            goto L_0x01a5
        L_0x01a4:
            r5 = r8
        L_0x01a5:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            boolean r10 = r7.sleepingForOffload
            if (r10 == r5) goto L_0x01b1
            androidx.media3.exoplayer.PlaybackInfo r7 = r7.copyWithSleepingForOffload(r5)
            r0.playbackInfo = r7
        L_0x01b1:
            r0.requestForRendererSleep = r8
            if (r5 != 0) goto L_0x01c9
            androidx.media3.exoplayer.PlaybackInfo r5 = r0.playbackInfo
            int r5 = r5.playbackState
            if (r5 != r6) goto L_0x01bc
            goto L_0x01c9
        L_0x01bc:
            if (r3 != 0) goto L_0x01c6
            if (r5 == r4) goto L_0x01c6
            if (r5 != r9) goto L_0x01c9
            int r3 = r0.enabledRendererCount
            if (r3 == 0) goto L_0x01c9
        L_0x01c6:
            r0.scheduleNextWork(r1)
        L_0x01c9:
            androidx.media3.common.util.TraceUtil.endSection()
        L_0x01cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.doSomeWork():void");
    }

    private void enableRenderer(MediaPeriodHolder mediaPeriodHolder, int i2, boolean z, long j2) {
        boolean z3;
        boolean z7;
        boolean z9;
        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder;
        RendererHolder rendererHolder = this.renderers[i2];
        if (!rendererHolder.isRendererEnabled()) {
            if (mediaPeriodHolder2 == this.queue.getPlayingPeriod()) {
                z3 = true;
            } else {
                z3 = false;
            }
            TrackSelectorResult trackSelectorResult = mediaPeriodHolder2.getTrackSelectorResult();
            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i2];
            ExoTrackSelection exoTrackSelection = trackSelectorResult.selections[i2];
            if (!shouldPlayWhenReady() || this.playbackInfo.playbackState != 3) {
                z7 = false;
            } else {
                z7 = true;
            }
            if (z || !z7) {
                z9 = false;
            } else {
                z9 = true;
            }
            this.enabledRendererCount++;
            ExoTrackSelection exoTrackSelection2 = exoTrackSelection;
            rendererHolder.enable(rendererConfiguration, exoTrackSelection2, mediaPeriodHolder2.sampleStreams[i2], this.rendererPositionUs, z9, z3, j2, mediaPeriodHolder2.getRendererOffset(), mediaPeriodHolder2.info.id, this.mediaClock);
            rendererHolder.handleMessage(11, new Object() {
            }, mediaPeriodHolder2);
            if (z7 && z3) {
                rendererHolder.start();
            }
        }
    }

    private void enableRenderers() {
        enableRenderers(new boolean[this.renderers.length], this.queue.getReadingPeriod().getStartPositionRendererTime());
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [F2.N, F2.Q] */
    private U extractMetadataFromTrackSelectionArray(ExoTrackSelection[] exoTrackSelectionArr) {
        ? n = new N(4);
        boolean z = false;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                Metadata metadata = exoTrackSelection.getFormat(0).metadata;
                if (metadata == null) {
                    n.a(new Metadata(new Metadata.Entry[0]));
                } else {
                    n.a(metadata);
                    z = true;
                }
            }
        }
        if (z) {
            return n.f();
        }
        G g = U.e;
        return y0.f278h;
    }

    private long getCurrentLiveOffsetUs() {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        return getLiveOffsetUs(playbackInfo2.timeline, playbackInfo2.periodId.periodUid, playbackInfo2.positionUs);
    }

    private long getDynamicSchedulingWakeUpIntervalMs() {
        long j2;
        MediaPeriodHolder mediaPeriodHolder;
        if (this.playbackInfo.playbackState == 3) {
            j2 = 1000;
        } else {
            j2 = BUFFERING_MAXIMUM_INTERVAL_MS;
        }
        for (RendererHolder minDurationToProgressUs : this.renderers) {
            j2 = Math.min(j2, Util.usToMs(minDurationToProgressUs.getMinDurationToProgressUs(this.rendererPositionUs, this.rendererPositionElapsedRealtimeUs)));
        }
        if (!this.playbackInfo.isPlaying()) {
            return j2;
        }
        if (this.queue.getPlayingPeriod() != null) {
            mediaPeriodHolder = this.queue.getPlayingPeriod().getNext();
        } else {
            mediaPeriodHolder = null;
        }
        if (mediaPeriodHolder == null) {
            return j2;
        }
        if ((((float) Util.msToUs(j2)) * this.playbackInfo.playbackParameters.speed) + ((float) this.rendererPositionUs) >= ((float) mediaPeriodHolder.getStartPositionRendererTime())) {
            return Math.min(j2, BUFFERING_MAXIMUM_INTERVAL_MS);
        }
        return j2;
    }

    private long getLiveOffsetUs(Timeline timeline, Object obj, long j2) {
        timeline.getWindow(timeline.getPeriodByUid(obj, this.period).windowIndex, this.window);
        Timeline.Window window2 = this.window;
        if (window2.windowStartTimeMs != -9223372036854775807L && window2.isLive()) {
            Timeline.Window window3 = this.window;
            if (window3.isDynamic) {
                return Util.msToUs(window3.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - (this.period.getPositionInWindowUs() + j2);
            }
        }
        return -9223372036854775807L;
    }

    private long getMaxRendererReadPositionUs(MediaPeriodHolder mediaPeriodHolder) {
        if (mediaPeriodHolder == null) {
            return 0;
        }
        long rendererOffset = mediaPeriodHolder.getRendererOffset();
        if (!mediaPeriodHolder.prepared) {
            return rendererOffset;
        }
        int i2 = 0;
        while (true) {
            RendererHolder[] rendererHolderArr = this.renderers;
            if (i2 >= rendererHolderArr.length) {
                return rendererOffset;
            }
            if (rendererHolderArr[i2].isReadingFromPeriod(mediaPeriodHolder)) {
                long readingPositionUs = this.renderers[i2].getReadingPositionUs(mediaPeriodHolder);
                if (readingPositionUs == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                rendererOffset = Math.max(readingPositionUs, rendererOffset);
            }
            i2++;
        }
    }

    private Pair<MediaSource.MediaPeriodId, Long> getPlaceholderFirstMediaPeriodPositionUs(Timeline timeline) {
        long j2 = 0;
        if (timeline.isEmpty()) {
            return Pair.create(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L);
        }
        Timeline timeline2 = timeline;
        Pair<Object, Long> periodPositionUs = timeline2.getPeriodPositionUs(this.window, this.period, timeline.getFirstWindowIndex(this.shuffleModeEnabled), -9223372036854775807L);
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline2, periodPositionUs.first, 0);
        long longValue = ((Long) periodPositionUs.second).longValue();
        if (resolveMediaPeriodIdForAdsAfterPeriodPositionChange.isAd()) {
            timeline2.getPeriodByUid(resolveMediaPeriodIdForAdsAfterPeriodPositionChange.periodUid, this.period);
            if (resolveMediaPeriodIdForAdsAfterPeriodPositionChange.adIndexInAdGroup == this.period.getFirstAdIndexToPlay(resolveMediaPeriodIdForAdsAfterPeriodPositionChange.adGroupIndex)) {
                j2 = this.period.getAdResumePositionUs();
            }
            longValue = j2;
        }
        return Pair.create(resolveMediaPeriodIdForAdsAfterPeriodPositionChange, Long.valueOf(longValue));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        r0 = r5.scrubbingModeParameters;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media3.exoplayer.SeekParameters getSeekParameters(long r6) {
        /*
            r5 = this;
            boolean r0 = r5.scrubbingModeEnabled
            if (r0 == 0) goto L_0x004b
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x004b
            androidx.media3.exoplayer.ScrubbingModeParameters r0 = r5.scrubbingModeParameters
            java.lang.Double r1 = r0.fractionalSeekToleranceBefore
            if (r1 == 0) goto L_0x004b
            java.lang.Double r0 = r0.fractionalSeekToleranceAfter
            if (r0 != 0) goto L_0x0018
            goto L_0x004b
        L_0x0018:
            double r0 = r1.doubleValue()
            double r6 = (double) r6
            double r0 = r0 * r6
            java.math.RoundingMode r2 = java.math.RoundingMode.FLOOR
            long r0 = I2.b.d(r0, r2)
            androidx.media3.exoplayer.ScrubbingModeParameters r3 = r5.scrubbingModeParameters
            java.lang.Double r3 = r3.fractionalSeekToleranceAfter
            double r3 = r3.doubleValue()
            double r3 = r3 * r6
            long r6 = I2.b.d(r3, r2)
            androidx.media3.exoplayer.SeekParameters r2 = r5.scrubbingModeSeekParameters
            if (r2 == 0) goto L_0x0041
            long r3 = r2.toleranceBeforeUs
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 != 0) goto L_0x0041
            long r2 = r2.toleranceAfterUs
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x0048
        L_0x0041:
            androidx.media3.exoplayer.SeekParameters r2 = new androidx.media3.exoplayer.SeekParameters
            r2.<init>(r0, r6)
            r5.scrubbingModeSeekParameters = r2
        L_0x0048:
            androidx.media3.exoplayer.SeekParameters r5 = r5.scrubbingModeSeekParameters
            return r5
        L_0x004b:
            androidx.media3.exoplayer.SeekParameters r5 = r5.seekParameters
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.getSeekParameters(long):androidx.media3.exoplayer.SeekParameters");
    }

    private long getStaticSchedulingWakeUpIntervalMs() {
        if (this.playbackInfo.playbackState != 3 || shouldPlayWhenReady()) {
            return BUFFERING_MAXIMUM_INTERVAL_MS;
        }
        return 1000;
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private void handleAudioFocusPlayerCommandInternal(int i2) {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        updatePlayWhenReadyWithAudioFocus(playbackInfo2.playWhenReady, i2, playbackInfo2.playbackSuppressionReason, playbackInfo2.playWhenReadyChangeReason);
    }

    private void handleAudioFocusVolumeMultiplierChange() {
        setVolumeInternal(this.volume);
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        } else if (this.queue.isPreloading(mediaPeriod)) {
            maybeContinuePreloading();
        }
    }

    private void handleIoException(IOException iOException, int i2) {
        ExoPlaybackException createForSource = ExoPlaybackException.createForSource(iOException, i2);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            createForSource = createForSource.copyWithMediaPeriodId(playingPeriod.info.id);
        }
        Log.e("ExoPlayerImplInternal", "Playback error", createForSource);
        stopInternal(false, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(createForSource);
    }

    private void handleLoadingMediaPeriodChanged(boolean z) {
        MediaSource.MediaPeriodId mediaPeriodId;
        long j2;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            mediaPeriodId = this.playbackInfo.periodId;
        } else {
            mediaPeriodId = loadingPeriod.info.id;
        }
        boolean equals = this.playbackInfo.loadingMediaPeriodId.equals(mediaPeriodId);
        if (!equals) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(mediaPeriodId);
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (loadingPeriod == null) {
            j2 = playbackInfo2.positionUs;
        } else {
            j2 = loadingPeriod.getBufferedPositionUs();
        }
        playbackInfo2.bufferedPositionUs = j2;
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((!equals || z) && loadingPeriod != null && loadingPeriod.prepared) {
            updateLoadControlTrackSelection(loadingPeriod.info.id, loadingPeriod.getTrackGroups(), loadingPeriod.getTrackSelectorResult());
        }
    }

    private void handleLoadingPeriodPrepared(MediaPeriodHolder mediaPeriodHolder) {
        ExoPlayerImplInternal exoPlayerImplInternal;
        if (!mediaPeriodHolder.prepared) {
            float f = this.mediaClock.getPlaybackParameters().speed;
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            mediaPeriodHolder.handlePrepared(f, playbackInfo2.timeline, playbackInfo2.playWhenReady);
        }
        updateLoadControlTrackSelection(mediaPeriodHolder.info.id, mediaPeriodHolder.getTrackGroups(), mediaPeriodHolder.getTrackSelectorResult());
        if (mediaPeriodHolder == this.queue.getPlayingPeriod()) {
            resetRendererPosition(mediaPeriodHolder.info.startPositionUs);
            enableRenderers();
            mediaPeriodHolder.allRenderersInCorrectState = true;
            PlaybackInfo playbackInfo3 = this.playbackInfo;
            MediaSource.MediaPeriodId mediaPeriodId = playbackInfo3.periodId;
            long j2 = mediaPeriodHolder.info.startPositionUs;
            exoPlayerImplInternal = this;
            exoPlayerImplInternal.playbackInfo = exoPlayerImplInternal.handlePositionDiscontinuity(mediaPeriodId, j2, playbackInfo3.requestedContentPositionUs, j2, false, 5);
        } else {
            exoPlayerImplInternal = this;
        }
        exoPlayerImplInternal.maybeContinueLoading();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01e5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x022c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline r29, boolean r30) {
        /*
            r28 = this;
            r1 = r28
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            androidx.media3.exoplayer.ExoPlayerImplInternal$SeekPosition r4 = r1.pendingInitialSeekPosition
            androidx.media3.exoplayer.MediaPeriodQueue r5 = r1.queue
            int r6 = r1.repeatMode
            boolean r7 = r1.shuffleModeEnabled
            androidx.media3.common.Timeline$Window r8 = r1.window
            androidx.media3.common.Timeline$Period r9 = r1.period
            r2 = r29
            androidx.media3.exoplayer.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r10 = resolvePositionForPlaylistChange(r2, r3, r4, r5, r6, r7, r8, r9)
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r11 = r10.periodId
            long r12 = r10.requestedContentPositionUs
            boolean r0 = r10.forceBufferingState
            long r14 = r10.periodPositionUs
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r3.periodId
            boolean r3 = r3.equals(r11)
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x0036
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            long r6 = r3.positionUs
            int r3 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x0033
            goto L_0x0036
        L_0x0033:
            r16 = r5
            goto L_0x0038
        L_0x0036:
            r16 = r4
        L_0x0038:
            r17 = 3
            r6 = -1
            r18 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7 = 2
            r8 = 4
            boolean r9 = r10.endPlayback     // Catch:{ all -> 0x0050 }
            if (r9 == 0) goto L_0x0063
            androidx.media3.exoplayer.PlaybackInfo r9 = r1.playbackInfo     // Catch:{ all -> 0x0050 }
            int r9 = r9.playbackState     // Catch:{ all -> 0x0050 }
            if (r9 == r4) goto L_0x0060
            r1.setState(r8)     // Catch:{ all -> 0x0050 }
            goto L_0x0060
        L_0x0050:
            r0 = move-exception
            r20 = r11
            r11 = r2
            r2 = r20
            r20 = r4
            r23 = r8
            r21 = r12
            r12 = r5
        L_0x005d:
            r13 = r6
            goto L_0x01bc
        L_0x0060:
            r1.resetInternal(r5, r5, r5, r4)     // Catch:{ all -> 0x0050 }
        L_0x0063:
            androidx.media3.exoplayer.RendererHolder[] r9 = r1.renderers     // Catch:{ all -> 0x0050 }
            int r3 = r9.length     // Catch:{ all -> 0x0050 }
            r4 = r5
        L_0x0067:
            if (r4 >= r3) goto L_0x0080
            r5 = r9[r4]     // Catch:{ all -> 0x0072 }
            r5.setTimeline(r2)     // Catch:{ all -> 0x0072 }
            int r4 = r4 + 1
            r5 = 0
            goto L_0x0067
        L_0x0072:
            r0 = move-exception
            r20 = r11
            r11 = r2
            r2 = r20
            r23 = r8
            r21 = r12
            r12 = 0
            r20 = 1
            goto L_0x005d
        L_0x0080:
            if (r16 != 0) goto L_0x0106
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue     // Catch:{ all -> 0x0104 }
            androidx.media3.exoplayer.MediaPeriodHolder r0 = r0.getReadingPeriod()     // Catch:{ all -> 0x0104 }
            r3 = 0
            if (r0 != 0) goto L_0x008f
            r22 = r3
            goto L_0x0099
        L_0x008f:
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue     // Catch:{ all -> 0x0104 }
            androidx.media3.exoplayer.MediaPeriodHolder r0 = r0.getReadingPeriod()     // Catch:{ all -> 0x0104 }
            long r22 = r1.getMaxRendererReadPositionUs(r0)     // Catch:{ all -> 0x0104 }
        L_0x0099:
            boolean r0 = r1.areRenderersPrewarming()     // Catch:{ all -> 0x0104 }
            if (r0 == 0) goto L_0x00b2
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue     // Catch:{ all -> 0x0072 }
            androidx.media3.exoplayer.MediaPeriodHolder r0 = r0.getPrewarmingPeriod()     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x00a8
            goto L_0x00b2
        L_0x00a8:
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue     // Catch:{ all -> 0x0072 }
            androidx.media3.exoplayer.MediaPeriodHolder r0 = r0.getPrewarmingPeriod()     // Catch:{ all -> 0x0072 }
            long r3 = r1.getMaxRendererReadPositionUs(r0)     // Catch:{ all -> 0x0072 }
        L_0x00b2:
            androidx.media3.exoplayer.MediaPeriodQueue r2 = r1.queue     // Catch:{ all -> 0x00fc }
            r25 = r3
            r3 = r8
            r8 = r25
            long r4 = r1.rendererPositionUs     // Catch:{ all -> 0x00ee }
            r24 = r7
            r20 = 1
            r25 = r3
            r3 = r29
            r26 = r22
            r23 = r25
            r21 = r12
            r12 = 0
            r13 = r6
            r6 = r26
            int r0 = r2.updateQueuedPeriods(r3, r4, r6, r8)     // Catch:{ all -> 0x00eb }
            r2 = r3
            r3 = r0 & 1
            if (r3 == 0) goto L_0x00e3
            r1.seekToCurrentPosition(r12)     // Catch:{ all -> 0x00db }
            goto L_0x013e
        L_0x00db:
            r0 = move-exception
        L_0x00dc:
            r25 = r11
            r11 = r2
            r2 = r25
            goto L_0x01bc
        L_0x00e3:
            r0 = r0 & 2
            if (r0 == 0) goto L_0x013e
            r1.disableAndResetPrewarmingRenderers()     // Catch:{ all -> 0x00db }
            goto L_0x013e
        L_0x00eb:
            r0 = move-exception
            r2 = r3
            goto L_0x00dc
        L_0x00ee:
            r0 = move-exception
            r2 = r29
            r23 = r3
            r24 = r7
        L_0x00f5:
            r21 = r12
            r12 = 0
            r20 = 1
            r13 = r6
            goto L_0x00dc
        L_0x00fc:
            r0 = move-exception
            r2 = r29
        L_0x00ff:
            r24 = r7
            r23 = r8
            goto L_0x00f5
        L_0x0104:
            r0 = move-exception
            goto L_0x00ff
        L_0x0106:
            r24 = r7
            r23 = r8
            r21 = r12
            r12 = 0
            r20 = 1
            r13 = r6
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x00db }
            if (r3 != 0) goto L_0x013e
            androidx.media3.exoplayer.MediaPeriodQueue r3 = r1.queue     // Catch:{ all -> 0x00db }
            androidx.media3.exoplayer.MediaPeriodHolder r3 = r3.getPlayingPeriod()     // Catch:{ all -> 0x00db }
        L_0x011c:
            if (r3 == 0) goto L_0x013a
            androidx.media3.exoplayer.MediaPeriodInfo r4 = r3.info     // Catch:{ all -> 0x00db }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r4 = r4.id     // Catch:{ all -> 0x00db }
            boolean r4 = r4.equals(r11)     // Catch:{ all -> 0x00db }
            if (r4 == 0) goto L_0x0135
            androidx.media3.exoplayer.MediaPeriodQueue r4 = r1.queue     // Catch:{ all -> 0x00db }
            androidx.media3.exoplayer.MediaPeriodInfo r5 = r3.info     // Catch:{ all -> 0x00db }
            androidx.media3.exoplayer.MediaPeriodInfo r4 = r4.getUpdatedMediaPeriodInfo(r2, r5)     // Catch:{ all -> 0x00db }
            r3.info = r4     // Catch:{ all -> 0x00db }
            r3.updateClipping()     // Catch:{ all -> 0x00db }
        L_0x0135:
            androidx.media3.exoplayer.MediaPeriodHolder r3 = r3.getNext()     // Catch:{ all -> 0x00db }
            goto L_0x011c
        L_0x013a:
            long r14 = r1.seekToPeriodPosition(r11, r14, r0)     // Catch:{ all -> 0x00db }
        L_0x013e:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.common.Timeline r4 = r0.timeline
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = r0.periodId
            boolean r0 = r10.setTargetLiveOffset
            if (r0 == 0) goto L_0x014a
            r6 = r14
            goto L_0x014c
        L_0x014a:
            r6 = r18
        L_0x014c:
            r8 = 0
            r3 = r11
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6, r8)
            r11 = r2
            r2 = r3
            if (r16 != 0) goto L_0x015d
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            long r3 = r0.requestedContentPositionUs
            int r0 = (r21 > r3 ? 1 : (r21 == r3 ? 0 : -1))
            if (r0 == 0) goto L_0x0196
        L_0x015d:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.periodId
            java.lang.Object r3 = r3.periodUid
            androidx.media3.common.Timeline r0 = r0.timeline
            if (r16 == 0) goto L_0x017c
            if (r30 == 0) goto L_0x017c
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L_0x017c
            androidx.media3.common.Timeline$Period r4 = r1.period
            androidx.media3.common.Timeline$Period r0 = r0.getPeriodByUid(r3, r4)
            boolean r0 = r0.isPlaceholder
            if (r0 != 0) goto L_0x017c
            r9 = r20
            goto L_0x017d
        L_0x017c:
            r9 = r12
        L_0x017d:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            long r7 = r0.discontinuityStartPositionUs
            int r0 = r11.getIndexOfPeriod(r3)
            if (r0 != r13) goto L_0x018d
            r10 = r23
        L_0x0189:
            r3 = r14
            r5 = r21
            goto L_0x0190
        L_0x018d:
            r10 = r17
            goto L_0x0189
        L_0x0190:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r1.playbackInfo = r0
        L_0x0196:
            r1.resetPendingPauseAtEndOfPeriod()
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            r1.resolvePendingMessagePositions(r11, r0)
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.exoplayer.PlaybackInfo r0 = r0.copyWithTimeline(r11)
            r1.playbackInfo = r0
            boolean r0 = r11.isEmpty()
            if (r0 != 0) goto L_0x01b1
            r2 = 0
            r1.pendingInitialSeekPosition = r2
        L_0x01b1:
            r1.handleLoadingMediaPeriodChanged(r12)
            androidx.media3.common.util.HandlerWrapper r0 = r1.handler
            r1 = r24
            r0.sendEmptyMessage(r1)
            return
        L_0x01bc:
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            androidx.media3.common.Timeline r4 = r3.timeline
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = r3.periodId
            boolean r3 = r10.setTargetLiveOffset
            if (r3 == 0) goto L_0x01c8
            r6 = r14
            goto L_0x01ca
        L_0x01c8:
            r6 = r18
        L_0x01ca:
            r8 = 0
            r3 = r2
            r2 = r11
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6, r8)
            r2 = r3
            if (r16 != 0) goto L_0x01db
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            long r3 = r3.requestedContentPositionUs
            int r3 = (r21 > r3 ? 1 : (r21 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x0214
        L_0x01db:
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r4 = r3.periodId
            java.lang.Object r4 = r4.periodUid
            androidx.media3.common.Timeline r3 = r3.timeline
            if (r16 == 0) goto L_0x01fa
            if (r30 == 0) goto L_0x01fa
            boolean r5 = r3.isEmpty()
            if (r5 != 0) goto L_0x01fa
            androidx.media3.common.Timeline$Period r5 = r1.period
            androidx.media3.common.Timeline$Period r3 = r3.getPeriodByUid(r4, r5)
            boolean r3 = r3.isPlaceholder
            if (r3 != 0) goto L_0x01fa
            r9 = r20
            goto L_0x01fb
        L_0x01fa:
            r9 = r12
        L_0x01fb:
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            long r7 = r3.discontinuityStartPositionUs
            int r3 = r11.getIndexOfPeriod(r4)
            if (r3 != r13) goto L_0x020b
            r10 = r23
        L_0x0207:
            r3 = r14
            r5 = r21
            goto L_0x020e
        L_0x020b:
            r10 = r17
            goto L_0x0207
        L_0x020e:
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r1.playbackInfo = r2
        L_0x0214:
            r1.resetPendingPauseAtEndOfPeriod()
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            androidx.media3.common.Timeline r2 = r2.timeline
            r1.resolvePendingMessagePositions(r11, r2)
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            androidx.media3.exoplayer.PlaybackInfo r2 = r2.copyWithTimeline(r11)
            r1.playbackInfo = r2
            boolean r2 = r11.isEmpty()
            if (r2 != 0) goto L_0x022f
            r2 = 0
            r1.pendingInitialSeekPosition = r2
        L_0x022f:
            r1.handleLoadingMediaPeriodChanged(r12)
            androidx.media3.common.util.HandlerWrapper r1 = r1.handler
            r2 = 2
            r1.sendEmptyMessage(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline, boolean):void");
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            handleLoadingPeriodPrepared((MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod()));
            return;
        }
        MediaPeriodHolder preloadHolderByMediaPeriod = this.queue.getPreloadHolderByMediaPeriod(mediaPeriod);
        if (preloadHolderByMediaPeriod != null) {
            Assertions.checkState(!preloadHolderByMediaPeriod.prepared);
            float f = this.mediaClock.getPlaybackParameters().speed;
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            preloadHolderByMediaPeriod.handlePrepared(f, playbackInfo2.timeline, playbackInfo2.playWhenReady);
            if (this.queue.isPreloading(mediaPeriod)) {
                maybeContinuePreloading();
            }
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, boolean z) {
        handlePlaybackParameters(playbackParameters, playbackParameters.speed, true, z);
    }

    private PlaybackInfo handlePositionDiscontinuity(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, long j8, boolean z, int i2) {
        boolean z3;
        U u;
        TrackSelectorResult trackSelectorResult;
        TrackGroupArray trackGroupArray;
        TrackGroupArray trackGroupArray2;
        TrackSelectorResult trackSelectorResult2;
        long j10 = j3;
        if (this.deliverPendingMessageAtStartPositionRequired || j2 != this.playbackInfo.positionUs || !mediaPeriodId.equals(this.playbackInfo.periodId)) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.deliverPendingMessageAtStartPositionRequired = z3;
        resetPendingPauseAtEndOfPeriod();
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        TrackGroupArray trackGroupArray3 = playbackInfo2.trackGroups;
        TrackSelectorResult trackSelectorResult3 = playbackInfo2.trackSelectorResult;
        List list = playbackInfo2.staticMetadata;
        if (this.mediaSourceList.isPrepared()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            if (playingPeriod == null) {
                trackGroupArray2 = TrackGroupArray.EMPTY;
            } else {
                trackGroupArray2 = playingPeriod.getTrackGroups();
            }
            if (playingPeriod == null) {
                trackSelectorResult2 = this.emptyTrackSelectorResult;
            } else {
                trackSelectorResult2 = playingPeriod.getTrackSelectorResult();
            }
            U extractMetadataFromTrackSelectionArray = extractMetadataFromTrackSelectionArray(trackSelectorResult2.selections);
            if (playingPeriod != null) {
                MediaPeriodInfo mediaPeriodInfo = playingPeriod.info;
                if (mediaPeriodInfo.requestedContentPositionUs != j10) {
                    playingPeriod.info = mediaPeriodInfo.copyWithRequestedContentPositionUs(j10);
                }
            }
            maybeUpdateOffloadScheduling();
            trackGroupArray = trackGroupArray2;
            trackSelectorResult = trackSelectorResult2;
            u = extractMetadataFromTrackSelectionArray;
        } else {
            if (!mediaPeriodId.equals(this.playbackInfo.periodId)) {
                trackGroupArray3 = TrackGroupArray.EMPTY;
                trackSelectorResult3 = this.emptyTrackSelectorResult;
                G g = U.e;
                list = y0.f278h;
            }
            u = list;
            trackGroupArray = trackGroupArray3;
            trackSelectorResult = trackSelectorResult3;
        }
        if (z) {
            this.playbackInfoUpdate.setPositionDiscontinuity(i2);
        }
        return this.playbackInfo.copyWithNewPosition(mediaPeriodId, j2, j10, j8, getTotalBufferedDurationUs(), trackGroupArray, trackSelectorResult, u);
    }

    private boolean hasReadingPeriodFinishedReading() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (!readingPeriod.prepared) {
            return false;
        }
        int i2 = 0;
        while (true) {
            RendererHolder[] rendererHolderArr = this.renderers;
            if (i2 >= rendererHolderArr.length) {
                return true;
            }
            if (!rendererHolderArr[i2].hasFinishedReadingFromPeriod(readingPeriod)) {
                return false;
            }
            i2++;
        }
    }

    private boolean isDynamicSchedulingEnabled() {
        if (this.dynamicSchedulingEnabled) {
            return true;
        }
        if (!this.scrubbingModeEnabled || !this.scrubbingModeParameters.shouldEnableDynamicScheduling) {
            return false;
        }
        return true;
    }

    private static boolean isIgnorableServerSideAdInsertionPeriodChange(boolean z, MediaSource.MediaPeriodId mediaPeriodId, long j2, MediaSource.MediaPeriodId mediaPeriodId2, Timeline.Period period2, long j3) {
        if (!z && j2 == j3 && mediaPeriodId.periodUid.equals(mediaPeriodId2.periodUid)) {
            if (!mediaPeriodId.isAd() || !period2.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex)) {
                if (!mediaPeriodId2.isAd() || !period2.isServerSideInsertedAdGroup(mediaPeriodId2.adGroupIndex)) {
                    return false;
                }
                return true;
            } else if (period2.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 4 || period2.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 2) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean isLoadingPossible(MediaPeriodHolder mediaPeriodHolder) {
        if (mediaPeriodHolder == null || mediaPeriodHolder.hasLoadingError() || mediaPeriodHolder.getNextLoadPositionUs() == Long.MIN_VALUE) {
            return false;
        }
        return true;
    }

    private boolean isRendererPrewarmingMediaPeriod(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        if (this.queue.getPrewarmingPeriod() == null || !this.queue.getPrewarmingPeriod().info.id.equals(mediaPeriodId)) {
            return false;
        }
        return this.renderers[i2].isPrewarmingPeriod(this.queue.getPrewarmingPeriod());
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j2 = playingPeriod.info.durationUs;
        if (!playingPeriod.prepared) {
            return false;
        }
        if (j2 == -9223372036854775807L || this.playbackInfo.positionUs < j2 || !shouldPlayWhenReady()) {
            return true;
        }
        return false;
    }

    private static boolean isUsingPlaceholderPeriod(PlaybackInfo playbackInfo2, Timeline.Period period2) {
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo2.periodId;
        Timeline timeline = playbackInfo2.timeline;
        if (timeline.isEmpty() || timeline.getPeriodByUid(mediaPeriodId.periodUid, period2).isPlaceholder) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$maybeTriggerOnRendererReadyChanged$1(int i2, boolean z) {
        this.analyticsCollector.onRendererReadyChanged(i2, this.renderers[i2].getTrackType(), z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendMessageToTargetThread$2(PlayerMessage playerMessage) {
        try {
            deliverMessage(playerMessage);
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void maybeContinueLoading() {
        boolean shouldContinueLoading2 = shouldContinueLoading();
        this.shouldContinueLoading = shouldContinueLoading2;
        if (shouldContinueLoading2) {
            MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod());
            mediaPeriodHolder.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(mediaPeriodHolder.toPeriodTime(this.rendererPositionUs)).setPlaybackSpeed(this.mediaClock.getPlaybackParameters().speed).setLastRebufferRealtimeMs(this.lastRebufferRealtimeMs).build());
        }
        updateIsLoading();
    }

    private void maybeContinuePreloading() {
        long j2;
        this.queue.maybeUpdatePreloadMediaPeriodHolder();
        MediaPeriodHolder preloadingPeriod = this.queue.getPreloadingPeriod();
        if (preloadingPeriod == null) {
            return;
        }
        if ((!preloadingPeriod.prepareCalled || preloadingPeriod.prepared) && !preloadingPeriod.mediaPeriod.isLoading()) {
            LoadControl loadControl2 = this.loadControl;
            Timeline timeline = this.playbackInfo.timeline;
            MediaSource.MediaPeriodId mediaPeriodId = preloadingPeriod.info.id;
            if (preloadingPeriod.prepared) {
                j2 = preloadingPeriod.mediaPeriod.getBufferedPositionUs();
            } else {
                j2 = 0;
            }
            if (loadControl2.shouldContinuePreloading(timeline, mediaPeriodId, j2)) {
                if (!preloadingPeriod.prepareCalled) {
                    preloadingPeriod.prepare(this, preloadingPeriod.info.startPositionUs);
                } else {
                    preloadingPeriod.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(preloadingPeriod.toPeriodTime(this.rendererPositionUs)).setPlaybackSpeed(this.mediaClock.getPlaybackParameters().speed).setLastRebufferRealtimeMs(this.lastRebufferRealtimeMs).build());
                }
            }
        }
    }

    private void maybeHandlePrewarmingTransition() {
        for (RendererHolder maybeHandlePrewarmingTransition : this.renderers) {
            maybeHandlePrewarmingTransition.maybeHandlePrewarmingTransition();
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        this.playbackInfoUpdate.setPlaybackInfo(this.playbackInfo);
        if (this.playbackInfoUpdate.hasPendingChange) {
            PlaybackInfoUpdateListener playbackInfoUpdateListener2 = this.playbackInfoUpdateListener;
            ((k) playbackInfoUpdateListener2).d.lambda$new$2(this.playbackInfoUpdate);
            this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        }
    }

    private void maybePrewarmRenderers() {
        ExoPlayerImplInternal exoPlayerImplInternal;
        MediaPeriodHolder prewarmingPeriod = this.queue.getPrewarmingPeriod();
        if (prewarmingPeriod != null) {
            TrackSelectorResult trackSelectorResult = prewarmingPeriod.getTrackSelectorResult();
            int i2 = 0;
            while (i2 < this.renderers.length) {
                if (!trackSelectorResult.isRendererEnabled(i2) || !this.renderers[i2].hasSecondary() || this.renderers[i2].isPrewarming()) {
                    exoPlayerImplInternal = this;
                } else {
                    this.renderers[i2].startPrewarming();
                    exoPlayerImplInternal = this;
                    exoPlayerImplInternal.enableRenderer(prewarmingPeriod, i2, false, prewarmingPeriod.getStartPositionRendererTime());
                }
                i2++;
                this = exoPlayerImplInternal;
            }
            ExoPlayerImplInternal exoPlayerImplInternal2 = this;
            if (exoPlayerImplInternal2.areRenderersPrewarming()) {
                exoPlayerImplInternal2.prewarmingMediaPeriodDiscontinuity = prewarmingPeriod.mediaPeriod.readDiscontinuity();
                if (!prewarmingPeriod.isFullyBuffered()) {
                    exoPlayerImplInternal2.queue.removeAfter(prewarmingPeriod);
                    exoPlayerImplInternal2.handleLoadingMediaPeriodChanged(false);
                    exoPlayerImplInternal2.maybeContinueLoading();
                }
            }
        }
    }

    private void maybeThrowRendererStreamError(int i2) {
        RendererHolder rendererHolder = this.renderers[i2];
        try {
            rendererHolder.maybeThrowStreamError((MediaPeriodHolder) Assertions.checkNotNull(this.queue.getPlayingPeriod()));
        } catch (IOException | RuntimeException e) {
            int trackType = rendererHolder.getTrackType();
            if (trackType == 3 || trackType == 5) {
                TrackSelectorResult trackSelectorResult = this.queue.getPlayingPeriod().getTrackSelectorResult();
                Log.e("ExoPlayerImplInternal", "Disabling track due to error: " + Format.toLogString(trackSelectorResult.selections[i2].getSelectedFormat()), e);
                TrackSelectorResult trackSelectorResult2 = new TrackSelectorResult((RendererConfiguration[]) trackSelectorResult.rendererConfigurations.clone(), (ExoTrackSelection[]) trackSelectorResult.selections.clone(), trackSelectorResult.tracks, trackSelectorResult.info);
                trackSelectorResult2.rendererConfigurations[i2] = null;
                trackSelectorResult2.selections[i2] = null;
                disableRenderer(i2);
                this.queue.getPlayingPeriod().applyTrackSelection(trackSelectorResult2, this.playbackInfo.positionUs, false);
                return;
            }
            throw e;
        }
    }

    private void maybeTriggerOnRendererReadyChanged(int i2, boolean z) {
        boolean[] zArr = this.rendererReportedReady;
        if (zArr[i2] != z) {
            zArr[i2] = z;
            this.applicationLooperHandler.post(new q(this, i2, z));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0078 A[EDGE_INSN: B:72:0x0078->B:25:0x0078 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeTriggerPendingMessages(long r9, long r11) {
        /*
            r8 = this;
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r0 = r8.pendingMessages
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00fc
            androidx.media3.exoplayer.PlaybackInfo r0 = r8.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r0.isAd()
            if (r0 == 0) goto L_0x0014
            goto L_0x00fc
        L_0x0014:
            boolean r0 = r8.deliverPendingMessageAtStartPositionRequired
            if (r0 == 0) goto L_0x001e
            r0 = 1
            long r9 = r9 - r0
            r0 = 0
            r8.deliverPendingMessageAtStartPositionRequired = r0
        L_0x001e:
            androidx.media3.exoplayer.PlaybackInfo r0 = r8.playbackInfo
            androidx.media3.common.Timeline r1 = r0.timeline
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            java.lang.Object r0 = r0.periodUid
            int r0 = r1.getIndexOfPeriod(r0)
            int r1 = r8.nextPendingMessageIndexHint
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r2 = r8.pendingMessages
            int r2 = r2.size()
            int r1 = java.lang.Math.min(r1, r2)
            r2 = 0
            if (r1 <= 0) goto L_0x0044
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r4 = r1 + -1
            java.lang.Object r3 = r3.get(r4)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0045
        L_0x0044:
            r3 = r2
        L_0x0045:
            if (r3 == 0) goto L_0x0067
            int r4 = r3.resolvedPeriodIndex
            if (r4 > r0) goto L_0x0053
            if (r4 != r0) goto L_0x0067
            long r3 = r3.resolvedPeriodTimeUs
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x0067
        L_0x0053:
            int r3 = r1 + -1
            if (r3 <= 0) goto L_0x0062
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r4 = r8.pendingMessages
            int r1 = r1 + -2
            java.lang.Object r1 = r4.get(r1)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r1 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x0063
        L_0x0062:
            r1 = r2
        L_0x0063:
            r7 = r3
            r3 = r1
            r1 = r7
            goto L_0x0045
        L_0x0067:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0078
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0079
        L_0x0078:
            r3 = r2
        L_0x0079:
            if (r3 == 0) goto L_0x009e
            java.lang.Object r4 = r3.resolvedPeriodUid
            if (r4 == 0) goto L_0x009e
            int r4 = r3.resolvedPeriodIndex
            if (r4 < r0) goto L_0x008b
            if (r4 != r0) goto L_0x009e
            long r4 = r3.resolvedPeriodTimeUs
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 > 0) goto L_0x009e
        L_0x008b:
            int r1 = r1 + 1
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0078
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0079
        L_0x009e:
            if (r3 == 0) goto L_0x00fa
            java.lang.Object r4 = r3.resolvedPeriodUid
            if (r4 == 0) goto L_0x00fa
            int r4 = r3.resolvedPeriodIndex
            if (r4 != r0) goto L_0x00fa
            long r4 = r3.resolvedPeriodTimeUs
            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x00fa
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 > 0) goto L_0x00fa
            androidx.media3.exoplayer.PlayerMessage r4 = r3.message     // Catch:{ all -> 0x00e3 }
            r8.sendMessageToTarget(r4)     // Catch:{ all -> 0x00e3 }
            androidx.media3.exoplayer.PlayerMessage r4 = r3.message
            boolean r4 = r4.getDeleteAfterDelivery()
            if (r4 != 0) goto L_0x00cb
            androidx.media3.exoplayer.PlayerMessage r3 = r3.message
            boolean r3 = r3.isCanceled()
            if (r3 == 0) goto L_0x00c8
            goto L_0x00cb
        L_0x00c8:
            int r1 = r1 + 1
            goto L_0x00d0
        L_0x00cb:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            r3.remove(r1)
        L_0x00d0:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x00e1
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x009e
        L_0x00e1:
            r3 = r2
            goto L_0x009e
        L_0x00e3:
            r9 = move-exception
            androidx.media3.exoplayer.PlayerMessage r10 = r3.message
            boolean r10 = r10.getDeleteAfterDelivery()
            if (r10 != 0) goto L_0x00f4
            androidx.media3.exoplayer.PlayerMessage r10 = r3.message
            boolean r10 = r10.isCanceled()
            if (r10 == 0) goto L_0x00f9
        L_0x00f4:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r8 = r8.pendingMessages
            r8.remove(r1)
        L_0x00f9:
            throw r9
        L_0x00fa:
            r8.nextPendingMessageIndexHint = r1
        L_0x00fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private boolean maybeUpdateLoadingPeriod() {
        MediaPeriodInfo nextMediaPeriodInfo;
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        boolean z = false;
        if (this.queue.shouldLoadNextMediaPeriod() && (nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo)) != null) {
            MediaPeriodHolder enqueueNextMediaPeriodHolder = this.queue.enqueueNextMediaPeriodHolder(nextMediaPeriodInfo);
            if (!enqueueNextMediaPeriodHolder.prepareCalled) {
                enqueueNextMediaPeriodHolder.prepare(this, nextMediaPeriodInfo.startPositionUs);
            } else if (enqueueNextMediaPeriodHolder.prepared) {
                this.handler.obtainMessage(8, enqueueNextMediaPeriodHolder.mediaPeriod).sendToTarget();
            }
            if (this.queue.getPlayingPeriod() == enqueueNextMediaPeriodHolder) {
                resetRendererPosition(nextMediaPeriodInfo.startPositionUs);
            }
            handleLoadingMediaPeriodChanged(false);
            z = true;
        }
        if (this.shouldContinueLoading) {
            this.shouldContinueLoading = isLoadingPossible(this.queue.getLoadingPeriod());
            updateIsLoading();
            return z;
        }
        maybeContinueLoading();
        return z;
    }

    private void maybeUpdateOffloadScheduling() {
        MediaPeriodHolder playingPeriod;
        boolean z;
        if (this.queue.getPlayingPeriod() == this.queue.getReadingPeriod() && (playingPeriod = this.queue.getPlayingPeriod()) != null) {
            TrackSelectorResult trackSelectorResult = playingPeriod.getTrackSelectorResult();
            boolean z3 = false;
            int i2 = 0;
            boolean z7 = false;
            while (true) {
                if (i2 >= this.renderers.length) {
                    z = true;
                    break;
                }
                if (trackSelectorResult.isRendererEnabled(i2)) {
                    if (this.renderers[i2].getTrackType() != 1) {
                        z = false;
                        break;
                    } else if (trackSelectorResult.rendererConfigurations[i2].offloadModePreferred != 0) {
                        z7 = true;
                    }
                }
                i2++;
            }
            if (z7 && z) {
                z3 = true;
            }
            setOffloadSchedulingEnabled(z3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeUpdatePlayingPeriod() {
        /*
            r15 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            boolean r2 = r15.shouldAdvancePlayingPeriod()
            if (r2 == 0) goto L_0x0082
            if (r1 == 0) goto L_0x000d
            r15.maybeNotifyPlaybackInfoChanged()
        L_0x000d:
            r15.isPrewarmingDisabledUntilNextTransition = r0
            androidx.media3.exoplayer.MediaPeriodQueue r1 = r15.queue
            androidx.media3.exoplayer.MediaPeriodHolder r1 = r1.advancePlayingPeriod()
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)
            androidx.media3.exoplayer.MediaPeriodHolder r1 = (androidx.media3.exoplayer.MediaPeriodHolder) r1
            androidx.media3.exoplayer.PlaybackInfo r2 = r15.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r2.periodId
            java.lang.Object r2 = r2.periodUid
            androidx.media3.exoplayer.MediaPeriodInfo r3 = r1.info
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r3.id
            java.lang.Object r3 = r3.periodUid
            boolean r2 = r2.equals(r3)
            r3 = 1
            if (r2 == 0) goto L_0x0047
            androidx.media3.exoplayer.PlaybackInfo r2 = r15.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r2.periodId
            int r4 = r2.adGroupIndex
            r5 = -1
            if (r4 != r5) goto L_0x0047
            androidx.media3.exoplayer.MediaPeriodInfo r4 = r1.info
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r4 = r4.id
            int r6 = r4.adGroupIndex
            if (r6 != r5) goto L_0x0047
            int r2 = r2.nextAdGroupIndex
            int r4 = r4.nextAdGroupIndex
            if (r2 == r4) goto L_0x0047
            r2 = r3
            goto L_0x0048
        L_0x0047:
            r2 = r0
        L_0x0048:
            androidx.media3.exoplayer.MediaPeriodInfo r4 = r1.info
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r6 = r4.id
            long r7 = r4.startPositionUs
            long r9 = r4.requestedContentPositionUs
            r13 = r2 ^ 1
            r14 = 0
            r11 = r7
            r5 = r15
            androidx.media3.exoplayer.PlaybackInfo r15 = r5.handlePositionDiscontinuity(r6, r7, r9, r11, r13, r14)
            r5.playbackInfo = r15
            r5.resetPendingPauseAtEndOfPeriod()
            r5.updatePlaybackPositions()
            boolean r15 = r5.areRenderersPrewarming()
            if (r15 == 0) goto L_0x0072
            androidx.media3.exoplayer.MediaPeriodQueue r15 = r5.queue
            androidx.media3.exoplayer.MediaPeriodHolder r15 = r15.getPrewarmingPeriod()
            if (r1 != r15) goto L_0x0072
            r5.maybeHandlePrewarmingTransition()
        L_0x0072:
            androidx.media3.exoplayer.PlaybackInfo r15 = r5.playbackInfo
            int r15 = r15.playbackState
            r1 = 3
            if (r15 != r1) goto L_0x007c
            r5.startRenderers()
        L_0x007c:
            r5.allowRenderersToRenderStartOfStreams()
            r1 = r3
            r15 = r5
            goto L_0x0002
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.maybeUpdatePlayingPeriod():void");
    }

    private void maybeUpdatePreloadPeriods(boolean z) {
        if (this.preloadConfiguration.targetPreloadDurationUs != -9223372036854775807L) {
            if (z || !this.playbackInfo.timeline.equals(this.lastPreloadPoolInvalidationTimeline)) {
                Timeline timeline = this.playbackInfo.timeline;
                this.lastPreloadPoolInvalidationTimeline = timeline;
                this.queue.invalidatePreloadPool(timeline);
            }
            maybeContinuePreloading();
        }
    }

    private void maybeUpdatePrewarmingPeriod() {
        MediaPeriodHolder prewarmingPeriod;
        if (!this.pendingPauseAtEndOfPeriod && this.hasSecondaryRenderers && !this.isPrewarmingDisabledUntilNextTransition && !areRenderersPrewarming() && (prewarmingPeriod = this.queue.getPrewarmingPeriod()) != null && prewarmingPeriod == this.queue.getReadingPeriod() && prewarmingPeriod.getNext() != null && prewarmingPeriod.getNext().prepared) {
            this.queue.advancePrewarmingPeriod();
            maybePrewarmRenderers();
        }
    }

    private void maybeUpdateReadingPeriod() {
        long j2;
        boolean z;
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null) {
            int i2 = 0;
            if (readingPeriod.getNext() == null || this.pendingPauseAtEndOfPeriod) {
                if (readingPeriod.info.isFinal || this.pendingPauseAtEndOfPeriod) {
                    RendererHolder[] rendererHolderArr = this.renderers;
                    int length = rendererHolderArr.length;
                    while (i2 < length) {
                        RendererHolder rendererHolder = rendererHolderArr[i2];
                        if (rendererHolder.isReadingFromPeriod(readingPeriod) && rendererHolder.hasReadPeriodToEnd(readingPeriod)) {
                            long j3 = readingPeriod.info.durationUs;
                            if (j3 == -9223372036854775807L || j3 == Long.MIN_VALUE) {
                                j2 = -9223372036854775807L;
                            } else {
                                j2 = readingPeriod.getRendererOffset() + readingPeriod.info.durationUs;
                            }
                            rendererHolder.setCurrentStreamFinal(readingPeriod, j2);
                        }
                        i2++;
                    }
                }
            } else if (hasReadingPeriodFinishedReading()) {
                if (areRenderersPrewarming() && this.queue.getPrewarmingPeriod() == this.queue.getReadingPeriod()) {
                    return;
                }
                if (readingPeriod.getNext().prepared || this.rendererPositionUs >= readingPeriod.getNext().getStartPositionRendererTime()) {
                    TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
                    MediaPeriodHolder advanceReadingPeriod = this.queue.advanceReadingPeriod();
                    TrackSelectorResult trackSelectorResult2 = advanceReadingPeriod.getTrackSelectorResult();
                    Timeline timeline = this.playbackInfo.timeline;
                    MediaSource.MediaPeriodId mediaPeriodId = advanceReadingPeriod.info.id;
                    MediaSource.MediaPeriodId mediaPeriodId2 = readingPeriod.info.id;
                    Timeline timeline2 = timeline;
                    updatePlaybackSpeedSettingsForNewPeriod(timeline2, mediaPeriodId, timeline2, mediaPeriodId2, -9223372036854775807L, false);
                    if (advanceReadingPeriod.prepared && ((this.hasSecondaryRenderers && this.prewarmingMediaPeriodDiscontinuity != -9223372036854775807L) || advanceReadingPeriod.mediaPeriod.readDiscontinuity() != -9223372036854775807L)) {
                        this.prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
                        if (!this.hasSecondaryRenderers || this.isPrewarmingDisabledUntilNextTransition) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (z) {
                            int i7 = 0;
                            while (true) {
                                if (i7 < this.renderers.length) {
                                    if (trackSelectorResult2.isRendererEnabled(i7) && this.renderers[i7].getTrackType() != -2 && !MimeTypes.allSamplesAreSyncSamples(trackSelectorResult2.selections[i7].getSelectedFormat().sampleMimeType, trackSelectorResult2.selections[i7].getSelectedFormat().codecs) && !this.renderers[i7].isPrewarming()) {
                                        z = false;
                                        break;
                                    }
                                    i7++;
                                } else {
                                    break;
                                }
                            }
                        }
                        if (!z) {
                            setAllNonPrewarmingRendererStreamsFinal(advanceReadingPeriod.getStartPositionRendererTime());
                            if (!advanceReadingPeriod.isFullyBuffered()) {
                                this.queue.removeAfter(advanceReadingPeriod);
                                handleLoadingMediaPeriodChanged(false);
                                maybeContinueLoading();
                                return;
                            }
                            return;
                        }
                    }
                    RendererHolder[] rendererHolderArr2 = this.renderers;
                    int length2 = rendererHolderArr2.length;
                    while (i2 < length2) {
                        rendererHolderArr2[i2].maybeSetOldStreamToFinal(trackSelectorResult, trackSelectorResult2, advanceReadingPeriod.getStartPositionRendererTime());
                        i2++;
                    }
                }
            }
        }
    }

    private void maybeUpdateReadingRenderers() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null && this.queue.getPlayingPeriod() != readingPeriod && !readingPeriod.allRenderersInCorrectState && updateRenderersForTransition()) {
            this.queue.getReadingPeriod().allRenderersInCorrectState = true;
        }
    }

    private void mediaSourceListUpdateRequestedInternal() {
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.createTimeline(), true);
    }

    private void moveMediaItemsInternal(MoveMediaItemsMessage moveMediaItemsMessage) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        throw null;
    }

    private void notifyTrackSelectionDiscontinuity() {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onDiscontinuity();
                }
            }
        }
    }

    private void notifyTrackSelectionPlayWhenReadyChanged(boolean z) {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlayWhenReadyChanged(z);
                }
            }
        }
    }

    private void notifyTrackSelectionRebuffer() {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onRebuffer();
                }
            }
        }
    }

    private void prepareInternal() {
        int i2;
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        resetInternal(false, false, false, true);
        this.loadControl.onPrepared(this.playerId);
        if (this.playbackInfo.timeline.isEmpty()) {
            i2 = 4;
        } else {
            i2 = 2;
        }
        setState(i2);
        updatePlayWhenReadyWithAudioFocus();
        this.mediaSourceList.prepare(this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void releaseInternal(ConditionVariable conditionVariable) {
        try {
            resetInternal(true, false, true, false);
            releaseRenderers();
            this.loadControl.onReleased(this.playerId);
            this.audioFocusManager.release();
            this.trackSelector.release();
            setState(1);
        } finally {
            this.handler.removeCallbacksAndMessages((Object) null);
            this.playbackLooperProvider.releaseLooper();
            conditionVariable.open();
        }
    }

    private void releaseRenderers() {
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            this.rendererCapabilities[i2].clearListener();
            this.renderers[i2].release();
        }
    }

    private void removeMediaItemsInternal(int i2, int i7, ShuffleOrder shuffleOrder) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.removeMediaSourceRange(i2, i7, shuffleOrder), false);
    }

    private void reselectTracksInternal() {
        boolean z;
        boolean z3;
        float f = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = null;
        boolean z7 = true;
        while (playingPeriod != null && playingPeriod.prepared) {
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            TrackSelectorResult selectTracks = playingPeriod.selectTracks(f, playbackInfo2.timeline, playbackInfo2.playWhenReady);
            if (playingPeriod == this.queue.getPlayingPeriod()) {
                trackSelectorResult = selectTracks;
            }
            if (!selectTracks.isEquivalent(playingPeriod.getTrackSelectorResult())) {
                if (z7) {
                    MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                    if ((this.queue.removeAfter(playingPeriod2) & 1) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean[] zArr = new boolean[this.renderers.length];
                    long applyTrackSelection = playingPeriod2.applyTrackSelection((TrackSelectorResult) Assertions.checkNotNull(trackSelectorResult), this.playbackInfo.positionUs, z, zArr);
                    PlaybackInfo playbackInfo3 = this.playbackInfo;
                    if (playbackInfo3.playbackState == 4 || applyTrackSelection == playbackInfo3.positionUs) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    PlaybackInfo playbackInfo4 = this.playbackInfo;
                    this.playbackInfo = handlePositionDiscontinuity(playbackInfo4.periodId, applyTrackSelection, playbackInfo4.requestedContentPositionUs, playbackInfo4.discontinuityStartPositionUs, z3, 5);
                    if (z3) {
                        resetRendererPosition(applyTrackSelection);
                    }
                    disableAndResetPrewarmingRenderers();
                    boolean[] zArr2 = new boolean[this.renderers.length];
                    int i2 = 0;
                    while (true) {
                        RendererHolder[] rendererHolderArr = this.renderers;
                        if (i2 >= rendererHolderArr.length) {
                            break;
                        }
                        int enabledRendererCount2 = rendererHolderArr[i2].getEnabledRendererCount();
                        zArr2[i2] = this.renderers[i2].isRendererEnabled();
                        this.renderers[i2].maybeDisableOrResetPosition(playingPeriod2.sampleStreams[i2], this.mediaClock, this.rendererPositionUs, zArr[i2]);
                        if (enabledRendererCount2 - this.renderers[i2].getEnabledRendererCount() > 0) {
                            maybeTriggerOnRendererReadyChanged(i2, false);
                        }
                        this.enabledRendererCount -= enabledRendererCount2 - this.renderers[i2].getEnabledRendererCount();
                        i2++;
                    }
                    enableRenderers(zArr2, this.rendererPositionUs);
                    playingPeriod2.allRenderersInCorrectState = true;
                } else {
                    this.queue.removeAfter(playingPeriod);
                    if (playingPeriod.prepared) {
                        long max = Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs));
                        if (this.hasSecondaryRenderers && areRenderersPrewarming() && this.queue.getPrewarmingPeriod() == playingPeriod) {
                            disableAndResetPrewarmingRenderers();
                        }
                        playingPeriod.applyTrackSelection(selectTracks, max, false);
                    }
                }
                handleLoadingMediaPeriodChanged(true);
                if (this.playbackInfo.playbackState != 4) {
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                return;
            }
            if (playingPeriod == readingPeriod) {
                z7 = false;
            }
            playingPeriod = playingPeriod.getNext();
        }
    }

    private void reselectTracksInternalAndSeek() {
        reselectTracksInternal();
        seekToCurrentPosition(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008e, code lost:
        if (r2.equals(r1.playbackInfo.periodId) == false) goto L_0x0090;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void resetInternal(boolean r34, boolean r35, boolean r36, boolean r37) {
        /*
            r33 = this;
            r1 = r33
            java.lang.String r2 = "ExoPlayerImplInternal"
            androidx.media3.common.util.HandlerWrapper r0 = r1.handler
            r3 = 2
            r0.removeMessages(r3)
            r3 = 0
            r1.seekIsPendingWhileScrubbing = r3
            r4 = 0
            r1.queuedSeekWhileScrubbing = r4
            r1.pendingRecoverableRendererError = r4
            r5 = 1
            r1.updateRebufferingState(r3, r5)
            androidx.media3.exoplayer.DefaultMediaClock r0 = r1.mediaClock
            r0.stop()
            r6 = 1000000000000(0xe8d4a51000, double:4.94065645841E-312)
            r1.rendererPositionUs = r6
            r1.disableRenderers()     // Catch:{ ExoPlaybackException | RuntimeException -> 0x0026 }
            goto L_0x002c
        L_0x0026:
            r0 = move-exception
            java.lang.String r6 = "Disable failed."
            androidx.media3.common.util.Log.e(r2, r6, r0)
        L_0x002c:
            if (r34 == 0) goto L_0x0043
            androidx.media3.exoplayer.RendererHolder[] r6 = r1.renderers
            int r7 = r6.length
            r8 = r3
        L_0x0032:
            if (r8 >= r7) goto L_0x0043
            r0 = r6[r8]
            r0.reset()     // Catch:{ RuntimeException -> 0x003a }
            goto L_0x0040
        L_0x003a:
            r0 = move-exception
            java.lang.String r9 = "Reset failed."
            androidx.media3.common.util.Log.e(r2, r9, r0)
        L_0x0040:
            int r8 = r8 + 1
            goto L_0x0032
        L_0x0043:
            r1.enabledRendererCount = r3
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r0.periodId
            long r6 = r0.positionUs
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r0.isAd()
            if (r0 != 0) goto L_0x0065
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.common.Timeline$Period r8 = r1.period
            boolean r0 = isUsingPlaceholderPeriod(r0, r8)
            if (r0 == 0) goto L_0x0060
            goto L_0x0065
        L_0x0060:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            long r8 = r0.positionUs
            goto L_0x0069
        L_0x0065:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            long r8 = r0.requestedContentPositionUs
        L_0x0069:
            if (r35 == 0) goto L_0x0093
            r1.pendingInitialSeekPosition = r4
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            android.util.Pair r0 = r1.getPlaceholderFirstMediaPeriodPositionUs(r0)
            java.lang.Object r2 = r0.first
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = (androidx.media3.exoplayer.source.MediaSource.MediaPeriodId) r2
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r6 = r0.longValue()
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r2.equals(r0)
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 != 0) goto L_0x0093
        L_0x0090:
            r11 = r6
            r9 = r8
            goto L_0x0095
        L_0x0093:
            r5 = r3
            goto L_0x0090
        L_0x0095:
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue
            r0.clear()
            r1.shouldContinueLoading = r3
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            if (r36 == 0) goto L_0x00da
            boolean r3 = r0 instanceof androidx.media3.exoplayer.PlaylistTimeline
            if (r3 == 0) goto L_0x00da
            androidx.media3.exoplayer.PlaylistTimeline r0 = (androidx.media3.exoplayer.PlaylistTimeline) r0
            androidx.media3.exoplayer.MediaSourceList r3 = r1.mediaSourceList
            androidx.media3.exoplayer.source.ShuffleOrder r3 = r3.getShuffleOrder()
            androidx.media3.exoplayer.PlaylistTimeline r0 = r0.copyWithPlaceholderTimeline(r3)
            int r3 = r2.adGroupIndex
            r6 = -1
            if (r3 == r6) goto L_0x00da
            java.lang.Object r3 = r2.periodUid
            androidx.media3.common.Timeline$Period r6 = r1.period
            r0.getPeriodByUid(r3, r6)
            androidx.media3.common.Timeline$Period r3 = r1.period
            int r3 = r3.windowIndex
            androidx.media3.common.Timeline$Window r6 = r1.window
            androidx.media3.common.Timeline$Window r3 = r0.getWindow(r3, r6)
            boolean r3 = r3.isLive()
            if (r3 == 0) goto L_0x00da
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = new androidx.media3.exoplayer.source.MediaSource$MediaPeriodId
            java.lang.Object r6 = r2.periodUid
            long r7 = r2.windowSequenceNumber
            r3.<init>(r6, r7)
            r7 = r0
            r8 = r3
            goto L_0x00dc
        L_0x00da:
            r7 = r0
            r8 = r2
        L_0x00dc:
            androidx.media3.exoplayer.PlaybackInfo r6 = new androidx.media3.exoplayer.PlaybackInfo
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            int r13 = r0.playbackState
            if (r37 == 0) goto L_0x00e6
        L_0x00e4:
            r14 = r4
            goto L_0x00e9
        L_0x00e6:
            androidx.media3.exoplayer.ExoPlaybackException r4 = r0.playbackError
            goto L_0x00e4
        L_0x00e9:
            if (r5 == 0) goto L_0x00f0
            androidx.media3.exoplayer.source.TrackGroupArray r2 = androidx.media3.exoplayer.source.TrackGroupArray.EMPTY
        L_0x00ed:
            r16 = r2
            goto L_0x00f3
        L_0x00f0:
            androidx.media3.exoplayer.source.TrackGroupArray r2 = r0.trackGroups
            goto L_0x00ed
        L_0x00f3:
            if (r5 == 0) goto L_0x00fa
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r2 = r1.emptyTrackSelectorResult
        L_0x00f7:
            r17 = r2
            goto L_0x00fd
        L_0x00fa:
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r2 = r0.trackSelectorResult
            goto L_0x00f7
        L_0x00fd:
            if (r5 == 0) goto L_0x0106
            F2.G r2 = F2.U.e
            F2.y0 r2 = F2.y0.f278h
        L_0x0103:
            r18 = r2
            goto L_0x0109
        L_0x0106:
            java.util.List<androidx.media3.common.Metadata> r2 = r0.staticMetadata
            goto L_0x0103
        L_0x0109:
            boolean r2 = r0.playWhenReady
            int r3 = r0.playWhenReadyChangeReason
            int r4 = r0.playbackSuppressionReason
            androidx.media3.common.PlaybackParameters r0 = r0.playbackParameters
            r30 = 0
            r32 = 0
            r15 = 0
            r26 = 0
            r19 = r8
            r24 = r11
            r28 = r11
            r23 = r0
            r20 = r2
            r21 = r3
            r22 = r4
            r6.<init>(r7, r8, r9, r11, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r26, r28, r30, r32)
            r1.playbackInfo = r6
            if (r36 == 0) goto L_0x0137
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue
            r0.releasePreloadPool()
            androidx.media3.exoplayer.MediaSourceList r0 = r1.mediaSourceList
            r0.release()
        L_0x0137:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.resetInternal(boolean, boolean, boolean, boolean):void");
    }

    private void resetPendingPauseAtEndOfPeriod() {
        boolean z;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod == null || !playingPeriod.info.isLastInTimelineWindow || !this.pauseAtEndOfWindow) {
            z = false;
        } else {
            z = true;
        }
        this.pendingPauseAtEndOfPeriod = z;
    }

    private void resetRendererPosition(long j2) {
        long j3;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod == null) {
            j3 = j2 + 1000000000000L;
        } else {
            j3 = playingPeriod.toRendererTime(j2);
        }
        this.rendererPositionUs = j3;
        this.mediaClock.resetPosition(j3);
        for (RendererHolder resetPosition : this.renderers) {
            resetPosition.resetPosition(playingPeriod, this.rendererPositionUs);
        }
        notifyTrackSelectionDiscontinuity();
    }

    private static void resolvePendingMessageEndOfStreamPosition(Timeline timeline, PendingMessageInfo pendingMessageInfo, Timeline.Window window2, Timeline.Period period2) {
        long j2;
        int i2 = timeline.getWindow(timeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period2).windowIndex, window2).lastPeriodIndex;
        Object obj = timeline.getPeriod(i2, period2, true).uid;
        long j3 = period2.durationUs;
        if (j3 != -9223372036854775807L) {
            j2 = j3 - 1;
        } else {
            j2 = Long.MAX_VALUE;
        }
        pendingMessageInfo.setResolvedPosition(i2, j2, obj);
    }

    private static boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo, Timeline timeline, Timeline timeline2, int i2, boolean z, Timeline.Window window2, Timeline.Period period2) {
        long j2;
        Object obj = pendingMessageInfo.resolvedPeriodUid;
        if (obj == null) {
            if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
                j2 = -9223372036854775807L;
            } else {
                j2 = Util.msToUs(pendingMessageInfo.message.getPositionMs());
            }
            Timeline.Window window3 = window2;
            Timeline.Period period3 = period2;
            Pair<Object, Long> resolveSeekPositionUs = resolveSeekPositionUs(timeline, new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getMediaItemIndex(), j2), false, i2, z, window3, period3);
            Timeline.Window window4 = window3;
            Timeline.Period period4 = period3;
            if (resolveSeekPositionUs == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(timeline.getIndexOfPeriod(resolveSeekPositionUs.first), ((Long) resolveSeekPositionUs.second).longValue(), resolveSeekPositionUs.first);
            if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
                resolvePendingMessageEndOfStreamPosition(timeline, pendingMessageInfo, window4, period4);
            }
            return true;
        }
        Timeline.Window window5 = window2;
        Timeline.Period period5 = period2;
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        if (indexOfPeriod == -1) {
            return false;
        }
        if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
            resolvePendingMessageEndOfStreamPosition(timeline, pendingMessageInfo, window5, period5);
            return true;
        }
        pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        timeline2.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period5);
        if (period5.isPlaceholder && timeline2.getWindow(period5.windowIndex, window5).firstPeriodIndex == timeline2.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid)) {
            Timeline timeline3 = timeline;
            Pair<Object, Long> periodPositionUs = timeline3.getPeriodPositionUs(window5, period5, timeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period5).windowIndex, period5.getPositionInWindowUs() + pendingMessageInfo.resolvedPeriodTimeUs);
            pendingMessageInfo.setResolvedPosition(timeline.getIndexOfPeriod(periodPositionUs.first), ((Long) periodPositionUs.second).longValue(), periodPositionUs.first);
        }
        return true;
    }

    private void resolvePendingMessagePositions(Timeline timeline, Timeline timeline2) {
        if (!timeline.isEmpty() || !timeline2.isEmpty()) {
            int size = this.pendingMessages.size() - 1;
            while (size >= 0) {
                Timeline timeline3 = timeline;
                Timeline timeline4 = timeline2;
                if (!resolvePendingMessagePosition(this.pendingMessages.get(size), timeline3, timeline4, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                    this.pendingMessages.get(size).message.markAsProcessed(false);
                    this.pendingMessages.remove(size);
                }
                size--;
                timeline = timeline3;
                timeline2 = timeline4;
            }
            Collections.sort(this.pendingMessages);
        }
    }

    private static PositionUpdateForPlaylistChange resolvePositionForPlaylistChange(Timeline timeline, PlaybackInfo playbackInfo2, SeekPosition seekPosition, MediaPeriodQueue mediaPeriodQueue, int i2, boolean z, Timeline.Window window2, Timeline.Period period2) {
        long j2;
        boolean z3;
        boolean z7;
        boolean z9;
        long j3;
        Object obj;
        int i7;
        int i8;
        Timeline.Period period3;
        Timeline timeline2;
        long j8;
        long j10;
        boolean z10;
        long j11;
        int i10;
        Timeline.Period period4;
        int i11;
        int i12;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        long j12;
        PlaybackInfo playbackInfo3 = playbackInfo2;
        Timeline.Period period5 = period2;
        if (timeline.isEmpty()) {
            return new PositionUpdateForPlaylistChange(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0, -9223372036854775807L, false, true, false);
        }
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo3.periodId;
        Object obj2 = mediaPeriodId.periodUid;
        boolean isUsingPlaceholderPeriod = isUsingPlaceholderPeriod(playbackInfo3, period5);
        if (playbackInfo3.periodId.isAd() || isUsingPlaceholderPeriod) {
            j2 = playbackInfo3.requestedContentPositionUs;
        } else {
            j2 = playbackInfo3.positionUs;
        }
        long j13 = j2;
        boolean z15 = false;
        if (seekPosition != null) {
            timeline2 = timeline;
            SeekPosition seekPosition2 = seekPosition;
            boolean z16 = z;
            Timeline.Period period6 = period2;
            Pair<Object, Long> resolveSeekPositionUs = resolveSeekPositionUs(timeline2, seekPosition2, true, i2, z16, window2, period6);
            boolean z17 = z16;
            if (resolveSeekPositionUs == null) {
                i8 = timeline2.getFirstWindowIndex(z17);
                j12 = j13;
                z14 = false;
                z13 = false;
                z12 = true;
            } else {
                if (seekPosition2.windowPositionUs == -9223372036854775807L) {
                    i8 = timeline2.getPeriodByUid(resolveSeekPositionUs.first, period6).windowIndex;
                    j12 = j13;
                    z14 = false;
                } else {
                    obj2 = resolveSeekPositionUs.first;
                    j12 = ((Long) resolveSeekPositionUs.second).longValue();
                    i8 = -1;
                    z14 = true;
                }
                if (playbackInfo3.playbackState == 4) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                z12 = false;
            }
            z3 = z14;
            z9 = z13;
            z7 = z12;
            period3 = period6;
            i7 = -1;
            j3 = j12;
        } else {
            timeline2 = timeline;
            boolean z18 = z;
            Timeline.Period period7 = period5;
            if (playbackInfo3.timeline.isEmpty()) {
                i12 = timeline2.getFirstWindowIndex(z18);
            } else if (timeline2.getIndexOfPeriod(obj2) == -1) {
                Timeline.Period period8 = period7;
                Object obj3 = obj2;
                Timeline timeline3 = timeline2;
                int resolveSubsequentPeriod = resolveSubsequentPeriod(window2, period8, i2, z18, obj3, playbackInfo3.timeline, timeline3);
                timeline2 = timeline3;
                Timeline.Period period9 = period8;
                if (resolveSubsequentPeriod == -1) {
                    resolveSubsequentPeriod = timeline2.getFirstWindowIndex(z18);
                    z11 = true;
                } else {
                    z11 = false;
                }
                i12 = resolveSubsequentPeriod;
                obj2 = obj3;
                period3 = period9;
                i7 = -1;
                z7 = z11;
                z9 = false;
                z3 = false;
                j3 = j13;
            } else {
                Object obj4 = obj2;
                if (j13 == -9223372036854775807L) {
                    i12 = timeline2.getPeriodByUid(obj4, period7).windowIndex;
                    obj2 = obj4;
                } else if (isUsingPlaceholderPeriod) {
                    playbackInfo3.timeline.getPeriodByUid(mediaPeriodId.periodUid, period7);
                    Timeline.Window window3 = window2;
                    if (playbackInfo3.timeline.getWindow(period7.windowIndex, window3).firstPeriodIndex == playbackInfo3.timeline.getIndexOfPeriod(mediaPeriodId.periodUid)) {
                        period4 = period7;
                        Pair<Object, Long> periodPositionUs = timeline2.getPeriodPositionUs(window3, period4, timeline2.getPeriodByUid(obj4, period7).windowIndex, period7.getPositionInWindowUs() + j13);
                        obj = periodPositionUs.first;
                        i11 = -1;
                        j3 = ((Long) periodPositionUs.second).longValue();
                    } else {
                        period4 = period7;
                        if (timeline2.getPeriodByUid(obj4, period4).durationUs != -9223372036854775807L) {
                            i11 = -1;
                            j3 = Util.constrainValue(j13, 0, period4.durationUs - 1);
                            obj = obj4;
                        } else {
                            i11 = -1;
                            obj = obj4;
                            j3 = j13;
                        }
                    }
                    i8 = i7;
                    z9 = false;
                    z7 = false;
                    z3 = true;
                } else {
                    period3 = period7;
                    i7 = -1;
                    obj = obj4;
                    i8 = -1;
                    j3 = j13;
                    z9 = false;
                    z7 = false;
                    z3 = false;
                }
            }
            period3 = period7;
            i7 = -1;
            z9 = false;
            z7 = false;
            z3 = false;
            j3 = j13;
        }
        if (i8 != i7) {
            Pair<Object, Long> periodPositionUs2 = timeline2.getPeriodPositionUs(window2, period3, i8, -9223372036854775807L);
            obj = periodPositionUs2.first;
            j8 = ((Long) periodPositionUs2.second).longValue();
            j10 = -9223372036854775807L;
        } else {
            j8 = j3;
            j10 = j8;
        }
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange = mediaPeriodQueue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline2, obj, j8);
        int i13 = resolveMediaPeriodIdForAdsAfterPeriodPositionChange.nextAdGroupIndex;
        if (i13 == i7 || ((i10 = mediaPeriodId.nextAdGroupIndex) != i7 && i13 >= i10)) {
            z10 = true;
        } else {
            z10 = false;
        }
        if (mediaPeriodId.periodUid.equals(obj) && !mediaPeriodId.isAd() && !resolveMediaPeriodIdForAdsAfterPeriodPositionChange.isAd() && z10) {
            z15 = true;
        }
        MediaSource.MediaPeriodId mediaPeriodId2 = resolveMediaPeriodIdForAdsAfterPeriodPositionChange;
        boolean isIgnorableServerSideAdInsertionPeriodChange = isIgnorableServerSideAdInsertionPeriodChange(isUsingPlaceholderPeriod, mediaPeriodId, j13, mediaPeriodId2, timeline2.getPeriodByUid(obj, period3), j10);
        if (z15 || isIgnorableServerSideAdInsertionPeriodChange) {
            mediaPeriodId2 = mediaPeriodId;
        }
        if (mediaPeriodId2.isAd()) {
            if (mediaPeriodId2.equals(mediaPeriodId)) {
                j8 = playbackInfo3.positionUs;
            } else {
                timeline2.getPeriodByUid(mediaPeriodId2.periodUid, period3);
                if (mediaPeriodId2.adIndexInAdGroup == period3.getFirstAdIndexToPlay(mediaPeriodId2.adGroupIndex)) {
                    j11 = period3.getAdResumePositionUs();
                } else {
                    j11 = 0;
                }
                j8 = j11;
            }
        }
        return new PositionUpdateForPlaylistChange(mediaPeriodId2, j8, j10, z9, z7, z3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
        r0 = r14;
        r1 = r15;
        r5 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair<java.lang.Object, java.lang.Long> resolveSeekPositionUs(androidx.media3.common.Timeline r9, androidx.media3.exoplayer.ExoPlayerImplInternal.SeekPosition r10, boolean r11, int r12, boolean r13, androidx.media3.common.Timeline.Window r14, androidx.media3.common.Timeline.Period r15) {
        /*
            androidx.media3.common.Timeline r2 = r10.timeline
            boolean r3 = r9.isEmpty()
            r8 = 0
            if (r3 == 0) goto L_0x000a
            return r8
        L_0x000a:
            boolean r3 = r2.isEmpty()
            if (r3 == 0) goto L_0x0011
            r2 = r9
        L_0x0011:
            int r5 = r10.windowIndex     // Catch:{ IndexOutOfBoundsException -> 0x0076 }
            long r6 = r10.windowPositionUs     // Catch:{ IndexOutOfBoundsException -> 0x0076 }
            r3 = r14
            r4 = r15
            android.util.Pair r5 = r2.getPeriodPositionUs(r3, r4, r5, r6)     // Catch:{ IndexOutOfBoundsException -> 0x0076 }
            r3 = r2
            boolean r4 = r9.equals(r3)
            if (r4 == 0) goto L_0x0023
            return r5
        L_0x0023:
            java.lang.Object r4 = r5.first
            int r4 = r9.getIndexOfPeriod(r4)
            r7 = -1
            if (r4 == r7) goto L_0x0059
            java.lang.Object r4 = r5.first
            androidx.media3.common.Timeline$Period r4 = r3.getPeriodByUid(r4, r15)
            boolean r4 = r4.isPlaceholder
            if (r4 == 0) goto L_0x0058
            int r4 = r15.windowIndex
            androidx.media3.common.Timeline$Window r4 = r3.getWindow(r4, r14)
            int r4 = r4.firstPeriodIndex
            java.lang.Object r7 = r5.first
            int r3 = r3.getIndexOfPeriod(r7)
            if (r4 != r3) goto L_0x0058
            java.lang.Object r3 = r5.first
            androidx.media3.common.Timeline$Period r3 = r9.getPeriodByUid(r3, r15)
            int r3 = r3.windowIndex
            long r4 = r10.windowPositionUs
            r0 = r9
            r1 = r14
            r2 = r15
            android.util.Pair r0 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            return r0
        L_0x0058:
            return r5
        L_0x0059:
            if (r11 == 0) goto L_0x0076
            java.lang.Object r4 = r5.first
            r6 = r9
            r2 = r12
            r0 = r14
            r1 = r15
            r5 = r3
            r3 = r13
            int r3 = resolveSubsequentPeriod(r0, r1, r2, r3, r4, r5, r6)
            if (r3 == r7) goto L_0x0076
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0 = r9
            r1 = r14
            r2 = r15
            android.util.Pair r0 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            return r0
        L_0x0076:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.resolveSeekPositionUs(androidx.media3.common.Timeline, androidx.media3.exoplayer.ExoPlayerImplInternal$SeekPosition, boolean, int, boolean, androidx.media3.common.Timeline$Window, androidx.media3.common.Timeline$Period):android.util.Pair");
    }

    public static int resolveSubsequentPeriod(Timeline.Window window2, Timeline.Period period2, int i2, boolean z, Object obj, Timeline timeline, Timeline timeline2) {
        Timeline.Period period3;
        Object obj2 = timeline.getWindow(timeline.getPeriodByUid(obj, period2).windowIndex, window2).uid;
        int i7 = 0;
        for (int i8 = 0; i8 < timeline2.getWindowCount(); i8++) {
            if (timeline2.getWindow(i8, window2).uid.equals(obj2)) {
                return i8;
            }
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int i10 = indexOfPeriod;
        int i11 = -1;
        while (true) {
            if (i7 >= periodCount || i11 != -1) {
                period3 = period2;
            } else {
                Timeline.Window window3 = window2;
                period3 = period2;
                int i12 = i2;
                boolean z3 = z;
                Timeline timeline3 = timeline;
                i10 = timeline3.getNextPeriodIndex(i10, period3, window3, i12, z3);
                if (i10 == -1) {
                    break;
                }
                i11 = timeline2.getIndexOfPeriod(timeline3.getUidOfPeriod(i10));
                i7++;
                timeline = timeline3;
                period2 = period3;
                window2 = window3;
                i2 = i12;
                z = z3;
            }
        }
        period3 = period2;
        if (i11 == -1) {
            return -1;
        }
        return timeline2.getPeriod(i11, period3).windowIndex;
    }

    private void scheduleNextWork(long j2) {
        long j3;
        if (isDynamicSchedulingEnabled()) {
            j3 = getDynamicSchedulingWakeUpIntervalMs();
        } else {
            j3 = getStaticSchedulingWakeUpIntervalMs();
        }
        this.handler.sendEmptyMessageAtTime(2, j2 + j3);
    }

    private void seekToCurrentPosition(boolean z) {
        MediaSource.MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long seekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true, false);
        if (seekToPeriodPosition != this.playbackInfo.positionUs) {
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, seekToPeriodPosition, playbackInfo2.requestedContentPositionUs, playbackInfo2.discontinuityStartPositionUs, z, 5);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00aa A[Catch:{ all -> 0x00ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b2 A[Catch:{ all -> 0x00ad }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void seekToInternal(androidx.media3.exoplayer.ExoPlayerImplInternal.SeekPosition r18, boolean r19) {
        /*
            r17 = this;
            r1 = r17
            r3 = r18
            androidx.media3.exoplayer.ExoPlayerImplInternal$PlaybackInfoUpdate r0 = r1.playbackInfoUpdate
            r2 = r19
            r0.incrementPendingOperationAcks(r2)
            boolean r0 = r1.seekIsPendingWhileScrubbing
            if (r0 == 0) goto L_0x0012
            r1.queuedSeekWhileScrubbing = r3
            return
        L_0x0012:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.common.Timeline r2 = r0.timeline
            int r5 = r1.repeatMode
            boolean r6 = r1.shuffleModeEnabled
            androidx.media3.common.Timeline$Window r7 = r1.window
            androidx.media3.common.Timeline$Period r8 = r1.period
            r4 = 1
            android.util.Pair r0 = resolveSeekPositionUs(r2, r3, r4, r5, r6, r7, r8)
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r2 = 0
            r8 = 1
            if (r0 != 0) goto L_0x004d
            androidx.media3.exoplayer.PlaybackInfo r9 = r1.playbackInfo
            androidx.media3.common.Timeline r9 = r9.timeline
            android.util.Pair r9 = r1.getPlaceholderFirstMediaPeriodPositionUs(r9)
            java.lang.Object r10 = r9.first
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r10 = (androidx.media3.exoplayer.source.MediaSource.MediaPeriodId) r10
            java.lang.Object r9 = r9.second
            java.lang.Long r9 = (java.lang.Long) r9
            long r11 = r9.longValue()
            androidx.media3.exoplayer.PlaybackInfo r9 = r1.playbackInfo
            androidx.media3.common.Timeline r9 = r9.timeline
            boolean r9 = r9.isEmpty()
            r9 = r9 ^ r8
            r5 = r6
        L_0x004a:
            r15 = 0
            goto L_0x00a0
        L_0x004d:
            java.lang.Object r9 = r0.first
            java.lang.Object r10 = r0.second
            java.lang.Long r10 = (java.lang.Long) r10
            long r11 = r10.longValue()
            long r13 = r3.windowPositionUs
            int r10 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r10 != 0) goto L_0x005f
            r13 = r6
            goto L_0x0060
        L_0x005f:
            r13 = r11
        L_0x0060:
            androidx.media3.exoplayer.MediaPeriodQueue r10 = r1.queue
            androidx.media3.exoplayer.PlaybackInfo r15 = r1.playbackInfo
            androidx.media3.common.Timeline r15 = r15.timeline
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r10 = r10.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r15, r9, r11)
            boolean r9 = r10.isAd()
            if (r9 == 0) goto L_0x0094
            androidx.media3.exoplayer.PlaybackInfo r6 = r1.playbackInfo
            androidx.media3.common.Timeline r6 = r6.timeline
            java.lang.Object r7 = r10.periodUid
            androidx.media3.common.Timeline$Period r9 = r1.period
            r6.getPeriodByUid(r7, r9)
            androidx.media3.common.Timeline$Period r6 = r1.period
            int r7 = r10.adGroupIndex
            int r6 = r6.getFirstAdIndexToPlay(r7)
            int r7 = r10.adIndexInAdGroup
            if (r6 != r7) goto L_0x008f
            androidx.media3.common.Timeline$Period r6 = r1.period
            long r6 = r6.getAdResumePositionUs()
            r11 = r6
            goto L_0x0091
        L_0x008f:
            r11 = 0
        L_0x0091:
            r9 = r8
            r5 = r13
            goto L_0x004a
        L_0x0094:
            r15 = 0
            long r4 = r3.windowPositionUs
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x009e
            r9 = r8
            goto L_0x009f
        L_0x009e:
            r9 = r2
        L_0x009f:
            r5 = r13
        L_0x00a0:
            androidx.media3.exoplayer.PlaybackInfo r4 = r1.playbackInfo     // Catch:{ all -> 0x00ad }
            androidx.media3.common.Timeline r4 = r4.timeline     // Catch:{ all -> 0x00ad }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x00ad }
            if (r4 == 0) goto L_0x00b2
            r1.pendingInitialSeekPosition = r3     // Catch:{ all -> 0x00ad }
            goto L_0x00c1
        L_0x00ad:
            r0 = move-exception
        L_0x00ae:
            r2 = r10
            r3 = r11
            goto L_0x0157
        L_0x00b2:
            r3 = 4
            if (r0 != 0) goto L_0x00c5
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x00ad }
            int r0 = r0.playbackState     // Catch:{ all -> 0x00ad }
            if (r0 == r8) goto L_0x00be
            r1.setState(r3)     // Catch:{ all -> 0x00ad }
        L_0x00be:
            r1.resetInternal(r2, r8, r2, r8)     // Catch:{ all -> 0x00ad }
        L_0x00c1:
            r2 = r10
            r3 = r11
            goto L_0x014a
        L_0x00c5:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x00ad }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId     // Catch:{ all -> 0x00ad }
            boolean r0 = r10.equals(r0)     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x0121
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue     // Catch:{ all -> 0x00ad }
            androidx.media3.exoplayer.MediaPeriodHolder r0 = r0.getPlayingPeriod()     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x00ee
            boolean r4 = r0.prepared     // Catch:{ all -> 0x00ad }
            if (r4 == 0) goto L_0x00ee
            int r4 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x00ee
            androidx.media3.exoplayer.source.MediaPeriod r0 = r0.mediaPeriod     // Catch:{ all -> 0x00ad }
            androidx.media3.common.Timeline$Window r4 = r1.window     // Catch:{ all -> 0x00ad }
            long r13 = r4.durationUs     // Catch:{ all -> 0x00ad }
            androidx.media3.exoplayer.SeekParameters r4 = r1.getSeekParameters(r13)     // Catch:{ all -> 0x00ad }
            long r13 = r0.getAdjustedSeekPositionUs(r11, r4)     // Catch:{ all -> 0x00ad }
            goto L_0x00ef
        L_0x00ee:
            r13 = r11
        L_0x00ef:
            long r15 = androidx.media3.common.util.Util.usToMs(r13)     // Catch:{ all -> 0x00ad }
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x00ad }
            r19 = r9
            long r8 = r0.positionUs     // Catch:{ all -> 0x011d }
            long r7 = androidx.media3.common.util.Util.usToMs(r8)     // Catch:{ all -> 0x011d }
            int r0 = (r15 > r7 ? 1 : (r15 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x010c
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x011d }
            int r7 = r0.playbackState     // Catch:{ all -> 0x011d }
            r8 = 2
            if (r7 == r8) goto L_0x010f
            r8 = 3
            if (r7 != r8) goto L_0x010c
            goto L_0x010f
        L_0x010c:
            r9 = r19
            goto L_0x0122
        L_0x010f:
            long r3 = r0.positionUs     // Catch:{ all -> 0x011d }
            r2 = r10
            r10 = 2
            r7 = r3
            r9 = r19
        L_0x0116:
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r1.playbackInfo = r0
            return
        L_0x011d:
            r0 = move-exception
            r9 = r19
            goto L_0x00ae
        L_0x0121:
            r13 = r11
        L_0x0122:
            boolean r0 = r1.scrubbingModeEnabled     // Catch:{ all -> 0x00ad }
            r1.seekIsPendingWhileScrubbing = r0     // Catch:{ all -> 0x00ad }
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x00ad }
            int r0 = r0.playbackState     // Catch:{ all -> 0x00ad }
            if (r0 != r3) goto L_0x012e
            r0 = 1
            goto L_0x012f
        L_0x012e:
            r0 = r2
        L_0x012f:
            long r13 = r1.seekToPeriodPosition(r10, r13, r0)     // Catch:{ all -> 0x00ad }
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0138
            r2 = 1
        L_0x0138:
            r9 = r9 | r2
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x0154 }
            androidx.media3.common.Timeline r2 = r0.timeline     // Catch:{ all -> 0x0154 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId     // Catch:{ all -> 0x0154 }
            r8 = 1
            r4 = r2
            r6 = r5
            r3 = r10
            r5 = r0
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6, r8)     // Catch:{ all -> 0x014f }
            r2 = r3
            r5 = r6
            r3 = r13
        L_0x014a:
            r10 = 2
            r7 = r3
            r1 = r17
            goto L_0x0116
        L_0x014f:
            r0 = move-exception
            r2 = r3
            r5 = r6
        L_0x0152:
            r3 = r13
            goto L_0x0157
        L_0x0154:
            r0 = move-exception
            r2 = r10
            goto L_0x0152
        L_0x0157:
            r10 = 2
            r7 = r3
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r1.playbackInfo = r2
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.seekToInternal(androidx.media3.exoplayer.ExoPlayerImplInternal$SeekPosition, boolean):void");
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j2, boolean z) {
        return seekToPeriodPosition(mediaPeriodId, j2, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod(), z);
    }

    private void sendMessageInternal(PlayerMessage playerMessage) {
        if (playerMessage.getPositionMs() == -9223372036854775807L) {
            sendMessageToTarget(playerMessage);
        } else if (this.playbackInfo.timeline.isEmpty()) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
        } else {
            PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
            Timeline timeline = this.playbackInfo.timeline;
            if (resolvePendingMessagePosition(pendingMessageInfo, timeline, timeline, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                this.pendingMessages.add(pendingMessageInfo);
                Collections.sort(this.pendingMessages);
                return;
            }
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) {
        if (playerMessage.getLooper() == this.playbackLooper) {
            deliverMessage(playerMessage);
            int i2 = this.playbackInfo.playbackState;
            if (i2 == 3 || i2 == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(PlayerMessage playerMessage) {
        Looper looper = playerMessage.getLooper();
        if (!looper.getThread().isAlive()) {
            Log.w("TAG", "Trying to send message on a dead thread.");
            playerMessage.markAsProcessed(false);
            return;
        }
        this.clock.createHandler(looper, (Handler.Callback) null).post(new f(3, this, playerMessage));
    }

    private void setAllNonPrewarmingRendererStreamsFinal(long j2) {
        for (RendererHolder allNonPrewarmingRendererStreamsFinal : this.renderers) {
            allNonPrewarmingRendererStreamsFinal.setAllNonPrewarmingRendererStreamsFinal(j2);
        }
    }

    private void setAudioAttributesInternal(AudioAttributes audioAttributes, boolean z) {
        this.trackSelector.setAudioAttributes(audioAttributes);
        AudioFocusManager audioFocusManager2 = this.audioFocusManager;
        if (!z) {
            audioAttributes = null;
        }
        audioFocusManager2.setAudioAttributes(audioAttributes);
        updatePlayWhenReadyWithAudioFocus();
    }

    private void setForegroundModeInternal(boolean z, ConditionVariable conditionVariable) {
        if (this.foregroundMode != z) {
            this.foregroundMode = z;
            if (!z) {
                for (RendererHolder reset : this.renderers) {
                    reset.reset();
                }
            }
        }
        if (conditionVariable != null) {
            conditionVariable.open();
        }
    }

    private void setMediaClockPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.removeMessages(16);
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void setMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        if (mediaSourceListUpdateMessage.windowIndex != -1) {
            this.pendingInitialSeekPosition = new SeekPosition(new PlaylistTimeline(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), mediaSourceListUpdateMessage.windowIndex, mediaSourceListUpdateMessage.positionUs);
        }
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setMediaSources(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    private void setOffloadSchedulingEnabled(boolean z) {
        if (z != this.offloadSchedulingEnabled) {
            this.offloadSchedulingEnabled = z;
            if (!z && this.playbackInfo.sleepingForOffload) {
                this.handler.sendEmptyMessage(2);
            }
        }
    }

    private void setPauseAtEndOfWindowInternal(boolean z) {
        this.pauseAtEndOfWindow = z;
        resetPendingPauseAtEndOfPeriod();
        if (this.pendingPauseAtEndOfPeriod && this.queue.getReadingPeriod() != this.queue.getPlayingPeriod()) {
            seekToCurrentPosition(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void setPlayWhenReadyInternal(boolean z, int i2, boolean z3, int i7) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(z3 ? 1 : 0);
        updatePlayWhenReadyWithAudioFocus(z, i2, i7);
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        setMediaClockPlaybackParameters(playbackParameters);
        handlePlaybackParameters(this.mediaClock.getPlaybackParameters(), true);
    }

    private void setPreloadConfigurationInternal(ExoPlayer.PreloadConfiguration preloadConfiguration2) {
        this.preloadConfiguration = preloadConfiguration2;
        this.queue.updatePreloadConfiguration(this.playbackInfo.timeline, preloadConfiguration2);
    }

    private void setRepeatModeInternal(int i2) {
        this.repeatMode = i2;
        int updateRepeatMode = this.queue.updateRepeatMode(this.playbackInfo.timeline, i2);
        if ((updateRepeatMode & 1) != 0) {
            seekToCurrentPosition(true);
        } else if ((updateRepeatMode & 2) != 0) {
            disableAndResetPrewarmingRenderers();
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setScrubbingModeEnabledInternal(boolean z) {
        if (!z) {
            this.seekIsPendingWhileScrubbing = false;
            this.handler.removeMessages(37);
            SeekPosition seekPosition = this.queuedSeekWhileScrubbing;
            if (seekPosition != null) {
                seekToInternal(seekPosition, false);
                this.queuedSeekWhileScrubbing = null;
            }
        }
        this.scrubbingModeEnabled = z;
        applyScrubbingModeParameters();
    }

    private void setScrubbingModeParametersInternal(ScrubbingModeParameters scrubbingModeParameters2) {
        this.scrubbingModeParameters = scrubbingModeParameters2;
        applyScrubbingModeParameters();
    }

    private void setSeekParametersInternal(SeekParameters seekParameters2) {
        this.seekParameters = seekParameters2;
    }

    private void setShuffleModeEnabledInternal(boolean z) {
        this.shuffleModeEnabled = z;
        int updateShuffleModeEnabled = this.queue.updateShuffleModeEnabled(this.playbackInfo.timeline, z);
        if ((updateShuffleModeEnabled & 1) != 0) {
            seekToCurrentPosition(true);
        } else if ((updateShuffleModeEnabled & 2) != 0) {
            disableAndResetPrewarmingRenderers();
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleOrderInternal(ShuffleOrder shuffleOrder) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setShuffleOrder(shuffleOrder), false);
    }

    private void setState(int i2) {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playbackState != i2) {
            if (i2 != 2) {
                this.playbackMaybeBecameStuckAtMs = -9223372036854775807L;
            }
            if (i2 != 3 && playbackInfo2.sleepingForOffload) {
                this.playbackInfo = playbackInfo2.copyWithSleepingForOffload(false);
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i2);
        }
    }

    private void setVideoFrameMetadataListenerInternal(VideoFrameMetadataListener videoFrameMetadataListener) {
        for (RendererHolder videoFrameMetadataListener2 : this.renderers) {
            videoFrameMetadataListener2.setVideoFrameMetadataListener(videoFrameMetadataListener);
        }
    }

    private void setVideoOutputInternal(Object obj, ConditionVariable conditionVariable) {
        for (RendererHolder videoOutput : this.renderers) {
            videoOutput.setVideoOutput(obj);
        }
        int i2 = this.playbackInfo.playbackState;
        if (i2 == 3 || i2 == 2) {
            this.handler.sendEmptyMessage(2);
        }
        if (conditionVariable != null) {
            conditionVariable.open();
        }
    }

    private void setVolumeInternal(float f) {
        this.volume = f;
        float volumeMultiplier = f * this.audioFocusManager.getVolumeMultiplier();
        for (RendererHolder volume2 : this.renderers) {
            volume2.setVolume(volumeMultiplier);
        }
    }

    private boolean shouldAdvancePlayingPeriod() {
        MediaPeriodHolder playingPeriod;
        MediaPeriodHolder next;
        if (shouldPlayWhenReady() && !this.pendingPauseAtEndOfPeriod && (playingPeriod = this.queue.getPlayingPeriod()) != null && (next = playingPeriod.getNext()) != null && this.rendererPositionUs >= next.getStartPositionRendererTime() && next.allRenderersInCorrectState) {
            return true;
        }
        return false;
    }

    private boolean shouldContinueLoading() {
        long periodTime;
        long j2;
        if (!isLoadingPossible(this.queue.getLoadingPeriod())) {
            return false;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long totalBufferedDurationUs = getTotalBufferedDurationUs(loadingPeriod.getNextLoadPositionUs());
        if (loadingPeriod == this.queue.getPlayingPeriod()) {
            periodTime = loadingPeriod.toPeriodTime(this.rendererPositionUs);
        } else {
            periodTime = loadingPeriod.toPeriodTime(this.rendererPositionUs) - loadingPeriod.info.startPositionUs;
        }
        long j3 = periodTime;
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, loadingPeriod.info.id)) {
            j2 = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
        } else {
            j2 = -9223372036854775807L;
        }
        LoadControl.Parameters parameters = new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, loadingPeriod.info.id, j3, totalBufferedDurationUs, this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, j2, this.lastRebufferRealtimeMs);
        boolean shouldContinueLoading2 = this.loadControl.shouldContinueLoading(parameters);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (shouldContinueLoading2 || !playingPeriod.prepared || totalBufferedDurationUs >= 500000 || (this.backBufferDurationUs <= 0 && !this.retainBackBufferFromKeyframe)) {
            return shouldContinueLoading2;
        }
        playingPeriod.mediaPeriod.discardBuffer(this.playbackInfo.positionUs, false);
        return this.loadControl.shouldContinueLoading(parameters);
    }

    private boolean shouldPlayWhenReady() {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (!playbackInfo2.playWhenReady || playbackInfo2.playbackSuppressionReason != 0) {
            return false;
        }
        return true;
    }

    private boolean shouldTransitionToReadyState(boolean z) {
        long j2;
        boolean z3;
        if (this.enabledRendererCount == 0) {
            return isTimelineReady();
        }
        boolean z7 = false;
        if (!z) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, playingPeriod.info.id)) {
            j2 = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
        } else {
            j2 = -9223372036854775807L;
        }
        long j3 = j2;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (!loadingPeriod.isFullyBuffered() || !loadingPeriod.info.isFinal) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (loadingPeriod.info.id.isAd() && !loadingPeriod.prepared) {
            z7 = true;
        }
        if (z3 || z7) {
            return true;
        }
        return this.loadControl.shouldStartPlayback(new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, playingPeriod.info.id, playingPeriod.toPeriodTime(this.rendererPositionUs), getTotalBufferedDurationUs(loadingPeriod.getBufferedPositionUs()), this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, j3, this.lastRebufferRealtimeMs));
    }

    private boolean shouldUseLivePlaybackSpeedControl(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (!mediaPeriodId.isAd() && !timeline.isEmpty()) {
            timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
            if (this.window.isLive()) {
                Timeline.Window window2 = this.window;
                if (!window2.isDynamic || window2.windowStartTimeMs == -9223372036854775807L) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private void startRenderers() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            TrackSelectorResult trackSelectorResult = playingPeriod.getTrackSelectorResult();
            for (int i2 = 0; i2 < this.renderers.length; i2++) {
                if (trackSelectorResult.isRendererEnabled(i2)) {
                    this.renderers[i2].start();
                }
            }
        }
    }

    private void stopInternal(boolean z, boolean z3) {
        boolean z7;
        if (z || !this.foregroundMode) {
            z7 = true;
        } else {
            z7 = false;
        }
        resetInternal(z7, false, true, false);
        this.playbackInfoUpdate.incrementPendingOperationAcks(z3 ? 1 : 0);
        this.loadControl.onStopped(this.playerId);
        this.audioFocusManager.updateAudioFocus(this.playbackInfo.playWhenReady, 1);
        setState(1);
    }

    private void stopRenderers() {
        this.mediaClock.stop();
        for (RendererHolder stop : this.renderers) {
            stop.stop();
        }
    }

    private void updateIsLoading() {
        boolean z;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (this.shouldContinueLoading || (loadingPeriod != null && loadingPeriod.mediaPeriod.isLoading())) {
            z = true;
        } else {
            z = false;
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (z != playbackInfo2.isLoading) {
            this.playbackInfo = playbackInfo2.copyWithIsLoading(z);
        }
    }

    private void updateLoadControlTrackSelection(MediaSource.MediaPeriodId mediaPeriodId, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        long periodTime;
        long j2;
        MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod());
        if (mediaPeriodHolder == this.queue.getPlayingPeriod()) {
            periodTime = mediaPeriodHolder.toPeriodTime(this.rendererPositionUs);
        } else {
            periodTime = mediaPeriodHolder.toPeriodTime(this.rendererPositionUs) - mediaPeriodHolder.info.startPositionUs;
        }
        long j3 = periodTime;
        long totalBufferedDurationUs = getTotalBufferedDurationUs(mediaPeriodHolder.getBufferedPositionUs());
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, mediaPeriodHolder.info.id)) {
            j2 = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
        } else {
            j2 = -9223372036854775807L;
        }
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        this.loadControl.onTracksSelected(new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, mediaPeriodId, j3, totalBufferedDurationUs, this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, j2, this.lastRebufferRealtimeMs), trackGroupArray2, trackSelectorResult.selections);
    }

    private void updateMediaSourcesWithMediaItemsInternal(int i2, int i7, List<MediaItem> list) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.updateMediaSourcesWithMediaItems(i2, i7, list), false);
    }

    private void updatePeriods() {
        if (!this.playbackInfo.timeline.isEmpty() && this.mediaSourceList.isPrepared()) {
            boolean maybeUpdateLoadingPeriod = maybeUpdateLoadingPeriod();
            maybeUpdatePrewarmingPeriod();
            maybeUpdateReadingPeriod();
            maybeUpdateReadingRenderers();
            maybeUpdatePlayingPeriod();
            maybeUpdatePreloadPeriods(maybeUpdateLoadingPeriod);
        }
    }

    private static int updatePlayWhenReadyChangeReason(int i2, int i7) {
        if (i2 == -1) {
            return 2;
        }
        if (i7 == 2) {
            return 1;
        }
        return i7;
    }

    private void updatePlayWhenReadyWithAudioFocus() {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        updatePlayWhenReadyWithAudioFocus(playbackInfo2.playWhenReady, playbackInfo2.playbackSuppressionReason, playbackInfo2.playWhenReadyChangeReason);
    }

    private void updatePlaybackPositions() {
        long j2;
        boolean z;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            if (playingPeriod.prepared) {
                j2 = playingPeriod.mediaPeriod.readDiscontinuity();
            } else {
                j2 = -9223372036854775807L;
            }
            if (j2 != -9223372036854775807L) {
                if (!playingPeriod.isFullyBuffered()) {
                    this.queue.removeAfter(playingPeriod);
                    handleLoadingMediaPeriodChanged(false);
                    maybeContinueLoading();
                }
                resetRendererPosition(j2);
                if (j2 != this.playbackInfo.positionUs) {
                    PlaybackInfo playbackInfo2 = this.playbackInfo;
                    long j3 = j2;
                    this.playbackInfo = handlePositionDiscontinuity(playbackInfo2.periodId, j3, playbackInfo2.requestedContentPositionUs, j3, true, 5);
                }
            } else {
                DefaultMediaClock defaultMediaClock = this.mediaClock;
                if (playingPeriod != this.queue.getReadingPeriod()) {
                    z = true;
                } else {
                    z = false;
                }
                long syncAndGetPositionUs = defaultMediaClock.syncAndGetPositionUs(z);
                this.rendererPositionUs = syncAndGetPositionUs;
                long periodTime = playingPeriod.toPeriodTime(syncAndGetPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                if (this.mediaClock.hasSkippedSilenceSinceLastCall()) {
                    boolean z3 = !this.playbackInfoUpdate.positionDiscontinuity;
                    PlaybackInfo playbackInfo3 = this.playbackInfo;
                    this.playbackInfo = handlePositionDiscontinuity(playbackInfo3.periodId, periodTime, playbackInfo3.requestedContentPositionUs, periodTime, z3, 6);
                } else {
                    this.playbackInfo.updatePositionUs(periodTime);
                }
            }
            this.playbackInfo.bufferedPositionUs = this.queue.getLoadingPeriod().getBufferedPositionUs();
            this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
            PlaybackInfo playbackInfo4 = this.playbackInfo;
            if (playbackInfo4.playWhenReady && playbackInfo4.playbackState == 3 && shouldUseLivePlaybackSpeedControl(playbackInfo4.timeline, playbackInfo4.periodId) && this.playbackInfo.playbackParameters.speed == 1.0f) {
                float adjustedPlaybackSpeed = this.livePlaybackSpeedControl.getAdjustedPlaybackSpeed(getCurrentLiveOffsetUs(), this.playbackInfo.totalBufferedDurationUs);
                if (this.mediaClock.getPlaybackParameters().speed != adjustedPlaybackSpeed) {
                    setMediaClockPlaybackParameters(this.playbackInfo.playbackParameters.withSpeed(adjustedPlaybackSpeed));
                    handlePlaybackParameters(this.playbackInfo.playbackParameters, this.mediaClock.getPlaybackParameters().speed, false, false);
                }
            }
        }
    }

    private void updatePlaybackSpeedSettingsForNewPeriod(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId2, long j2, boolean z) {
        Object obj;
        PlaybackParameters playbackParameters;
        if (!shouldUseLivePlaybackSpeedControl(timeline, mediaPeriodId)) {
            if (mediaPeriodId.isAd()) {
                playbackParameters = PlaybackParameters.DEFAULT;
            } else {
                playbackParameters = this.playbackInfo.playbackParameters;
            }
            if (!this.mediaClock.getPlaybackParameters().equals(playbackParameters)) {
                setMediaClockPlaybackParameters(playbackParameters);
                handlePlaybackParameters(this.playbackInfo.playbackParameters, playbackParameters.speed, false, false);
                return;
            }
            return;
        }
        timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
        this.livePlaybackSpeedControl.setLiveConfiguration((MediaItem.LiveConfiguration) Util.castNonNull(this.window.liveConfiguration));
        if (j2 != -9223372036854775807L) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(getLiveOffsetUs(timeline, mediaPeriodId.periodUid, j2));
            return;
        }
        Object obj2 = this.window.uid;
        if (!timeline2.isEmpty()) {
            obj = timeline2.getWindow(timeline2.getPeriodByUid(mediaPeriodId2.periodUid, this.period).windowIndex, this.window).uid;
        } else {
            obj = null;
        }
        if (!Objects.equals(obj, obj2) || z) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(-9223372036854775807L);
        }
    }

    private static int updatePlaybackSuppressionReason(int i2, int i7) {
        if (i2 == 0) {
            return 1;
        }
        if (i7 == 1) {
            return 0;
        }
        return i7;
    }

    private void updateRebufferingState(boolean z, boolean z3) {
        long j2;
        this.isRebuffering = z;
        if (!z || z3) {
            j2 = -9223372036854775807L;
        } else {
            j2 = this.clock.elapsedRealtime();
        }
        this.lastRebufferRealtimeMs = j2;
    }

    private boolean updateRenderersForTransition() {
        ExoPlayerImplInternal exoPlayerImplInternal;
        boolean z;
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
        int i2 = 0;
        boolean z3 = true;
        int i7 = 0;
        while (true) {
            RendererHolder[] rendererHolderArr = this.renderers;
            if (i7 >= rendererHolderArr.length) {
                break;
            }
            int enabledRendererCount2 = rendererHolderArr[i7].getEnabledRendererCount();
            int replaceStreamsOrDisableRendererForTransition = this.renderers[i7].replaceStreamsOrDisableRendererForTransition(readingPeriod, trackSelectorResult, this.mediaClock);
            if ((replaceStreamsOrDisableRendererForTransition & 2) != 0 && this.offloadSchedulingEnabled) {
                setOffloadSchedulingEnabled(false);
            }
            this.enabledRendererCount -= enabledRendererCount2 - this.renderers[i7].getEnabledRendererCount();
            if ((replaceStreamsOrDisableRendererForTransition & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            z3 &= z;
            i7++;
        }
        if (z3) {
            while (i2 < this.renderers.length) {
                if (!trackSelectorResult.isRendererEnabled(i2) || this.renderers[i2].isReadingFromPeriod(readingPeriod)) {
                    exoPlayerImplInternal = this;
                } else {
                    exoPlayerImplInternal = this;
                    exoPlayerImplInternal.enableRenderer(readingPeriod, i2, false, readingPeriod.getStartPositionRendererTime());
                }
                i2++;
                this = exoPlayerImplInternal;
            }
        }
        return z3;
    }

    private void updateTrackSelectionPlaybackSpeed(float f) {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlaybackSpeed(f);
                }
            }
        }
    }

    public void executePlayerCommand(int i2) {
        this.handler.obtainMessage(33, i2, 0).sendToTarget();
    }

    public void experimentalSetForegroundModeTimeoutMs(long j2) {
        this.setForegroundModeTimeoutMs = j2;
    }

    public Looper getPlaybackLooper() {
        return this.playbackLooper;
    }

    public boolean handleMessage(Message message) {
        int i2;
        MediaSource.MediaPeriodId mediaPeriodId;
        MediaPeriodHolder readingPeriod;
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        Message message2 = message;
        int i7 = 1000;
        try {
            switch (message2.what) {
                case 1:
                    if (message2.arg1 != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    int i8 = message2.arg2;
                    setPlayWhenReadyInternal(z, i8 >> 4, true, i8 & 15);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message2.obj, true);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message2.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message2.obj);
                    break;
                case 6:
                    stopInternal(false, true);
                    break;
                case 7:
                    releaseInternal((ConditionVariable) message2.obj);
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) message2.obj);
                    break;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) message2.obj);
                    break;
                case 10:
                    reselectTracksInternal();
                    break;
                case 11:
                    setRepeatModeInternal(message2.arg1);
                    break;
                case 12:
                    if (message2.arg1 != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    setShuffleModeEnabledInternal(z3);
                    break;
                case 13:
                    if (message2.arg1 != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    setForegroundModeInternal(z7, (ConditionVariable) message2.obj);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message2.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message2.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) message2.obj, false);
                    break;
                case 17:
                    setMediaItemsInternal((MediaSourceListUpdateMessage) message2.obj);
                    break;
                case 18:
                    addMediaItemsInternal((MediaSourceListUpdateMessage) message2.obj, message2.arg1);
                    break;
                case 19:
                    a.t(message2.obj);
                    moveMediaItemsInternal((MoveMediaItemsMessage) null);
                    break;
                case 20:
                    removeMediaItemsInternal(message2.arg1, message2.arg2, (ShuffleOrder) message2.obj);
                    break;
                case 21:
                    setShuffleOrderInternal((ShuffleOrder) message2.obj);
                    break;
                case 22:
                    mediaSourceListUpdateRequestedInternal();
                    break;
                case 23:
                    if (message2.arg1 != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    setPauseAtEndOfWindowInternal(z9);
                    break;
                case 25:
                    attemptRendererErrorRecovery();
                    break;
                case 26:
                    reselectTracksInternalAndSeek();
                    break;
                case 27:
                    updateMediaSourcesWithMediaItemsInternal(message2.arg1, message2.arg2, (List) message2.obj);
                    break;
                case 28:
                    setPreloadConfigurationInternal((ExoPlayer.PreloadConfiguration) message2.obj);
                    break;
                case 29:
                    prepareInternal();
                    break;
                case 30:
                    Pair pair = (Pair) message2.obj;
                    setVideoOutputInternal(pair.first, (ConditionVariable) pair.second);
                    break;
                case 31:
                    AudioAttributes audioAttributes = (AudioAttributes) message2.obj;
                    if (message2.arg1 != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    setAudioAttributesInternal(audioAttributes, z10);
                    break;
                case 32:
                    setVolumeInternal(((Float) message2.obj).floatValue());
                    break;
                case 33:
                    handleAudioFocusPlayerCommandInternal(message2.arg1);
                    break;
                case 34:
                    handleAudioFocusVolumeMultiplierChange();
                    break;
                case 35:
                    setVideoFrameMetadataListenerInternal((VideoFrameMetadataListener) message2.obj);
                    break;
                case 36:
                    setScrubbingModeEnabledInternal(((Boolean) message2.obj).booleanValue());
                    break;
                case 37:
                    this.seekIsPendingWhileScrubbing = false;
                    SeekPosition seekPosition = this.queuedSeekWhileScrubbing;
                    if (seekPosition != null) {
                        seekToInternal(seekPosition, false);
                        this.queuedSeekWhileScrubbing = null;
                        break;
                    }
                    break;
                case 38:
                    setScrubbingModeParametersInternal((ScrubbingModeParameters) message2.obj);
                    break;
                default:
                    return false;
            }
        } catch (ExoPlaybackException e) {
            e = e;
            if (e.type == 1 && (readingPeriod = this.queue.getReadingPeriod()) != null && e.mediaPeriodId == null) {
                e = e.copyWithMediaPeriodId(readingPeriod.info.id);
            }
            if (e.type != 1 || (mediaPeriodId = e.mediaPeriodId) == null || !isRendererPrewarmingMediaPeriod(e.rendererIndex, mediaPeriodId)) {
                ExoPlaybackException exoPlaybackException = this.pendingRecoverableRendererError;
                if (exoPlaybackException != null) {
                    exoPlaybackException.addSuppressed(e);
                    e = this.pendingRecoverableRendererError;
                }
                if (e.type == 1 && this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                    while (this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                        this.queue.advancePlayingPeriod();
                    }
                    maybeNotifyPlaybackInfoChanged();
                    MediaPeriodInfo mediaPeriodInfo = ((MediaPeriodHolder) Assertions.checkNotNull(this.queue.getPlayingPeriod())).info;
                    MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodInfo.id;
                    long j2 = mediaPeriodInfo.startPositionUs;
                    this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId2, j2, mediaPeriodInfo.requestedContentPositionUs, j2, true, 0);
                }
                if (!e.isRecoverable || !(this.pendingRecoverableRendererError == null || (i2 = e.errorCode) == 5004 || i2 == 5003)) {
                    Log.e("ExoPlayerImplInternal", "Playback error", e);
                    stopInternal(true, false);
                    this.playbackInfo = this.playbackInfo.copyWithPlaybackError(e);
                } else {
                    Log.w("ExoPlayerImplInternal", "Recoverable renderer error", e);
                    if (this.pendingRecoverableRendererError == null) {
                        this.pendingRecoverableRendererError = e;
                    }
                    HandlerWrapper handlerWrapper = this.handler;
                    handlerWrapper.sendMessageAtFrontOfQueue(handlerWrapper.obtainMessage(25, e));
                }
            } else {
                this.isPrewarmingDisabledUntilNextTransition = true;
                disableAndResetPrewarmingRenderers();
                MediaPeriodHolder prewarmingPeriod = this.queue.getPrewarmingPeriod();
                MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
                if (this.queue.getPlayingPeriod() != prewarmingPeriod) {
                    while (playingPeriod != null && playingPeriod.getNext() != prewarmingPeriod) {
                        playingPeriod = playingPeriod.getNext();
                    }
                }
                this.queue.removeAfter(playingPeriod);
                if (this.playbackInfo.playbackState != 4) {
                    maybeContinueLoading();
                    this.handler.sendEmptyMessage(2);
                }
            }
        } catch (DrmSession.DrmSessionException e7) {
            handleIoException(e7, e7.errorCode);
        } catch (ParserException e8) {
            int i10 = e8.dataType;
            if (i10 == 1) {
                if (e8.contentIsMalformed) {
                    i7 = 3001;
                } else {
                    i7 = LiveTranslationTask.ERRORTYPE.ERR_INVALID_PARSING_DATA;
                }
            } else if (i10 == 4) {
                if (e8.contentIsMalformed) {
                    i7 = 3002;
                } else {
                    i7 = LiveTranslationTask.ERRORTYPE.ERR_SAME_LANGUAGE;
                }
            }
            handleIoException(e8, i7);
        } catch (DataSourceException e9) {
            handleIoException(e9, e9.reason);
        } catch (IOException e10) {
            handleIoException(e10, 2000);
        } catch (RuntimeException e11) {
            if ((e11 instanceof IllegalStateException) || (e11 instanceof IllegalArgumentException)) {
                i7 = 1004;
            }
            ExoPlaybackException createForUnexpected = ExoPlaybackException.createForUnexpected(e11, i7);
            Log.e("ExoPlayerImplInternal", "Playback error", createForUnexpected);
            stopInternal(true, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(createForUnexpected);
        }
        maybeNotifyPlaybackInfoChanged();
        return true;
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(16, playbackParameters).sendToTarget();
    }

    public void onPlaylistUpdateRequested() {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessage(22);
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(8, mediaPeriod).sendToTarget();
    }

    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    public void prepare() {
        this.handler.obtainMessage(29).sendToTarget();
    }

    public boolean release() {
        if (this.releasedOnApplicationThread || !this.playbackLooper.getThread().isAlive()) {
            return true;
        }
        this.releasedOnApplicationThread = true;
        ConditionVariable conditionVariable = new ConditionVariable(this.clock);
        this.handler.obtainMessage(7, conditionVariable).sendToTarget();
        return conditionVariable.blockUninterruptible(this.releaseTimeoutMs);
    }

    public void sendMessage(PlayerMessage playerMessage) {
        if (this.releasedOnApplicationThread || !this.playbackLooper.getThread().isAlive()) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            playerMessage.markAsProcessed(false);
            return;
        }
        this.handler.obtainMessage(14, playerMessage).sendToTarget();
    }

    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        this.handler.obtainMessage(31, z ? 1 : 0, 0, audioAttributes).sendToTarget();
    }

    public void setMediaSources(List<MediaSourceList.MediaSourceHolder> list, int i2, long j2, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(17, new MediaSourceListUpdateMessage(list, shuffleOrder, i2, j2)).sendToTarget();
    }

    public void setPlayWhenReady(boolean z, int i2, int i7) {
        this.handler.obtainMessage(1, z ? 1 : 0, i2 | (i7 << 4)).sendToTarget();
    }

    public void setScrubbingModeParameters(ScrubbingModeParameters scrubbingModeParameters2) {
        this.handler.obtainMessage(38, scrubbingModeParameters2).sendToTarget();
    }

    public boolean setVideoOutput(Object obj, long j2) {
        if (!this.releasedOnApplicationThread && this.playbackLooper.getThread().isAlive()) {
            ConditionVariable conditionVariable = new ConditionVariable(this.clock);
            this.handler.obtainMessage(30, new Pair(obj, conditionVariable)).sendToTarget();
            if (j2 != -9223372036854775807L) {
                return conditionVariable.blockUninterruptible(j2);
            }
        }
        return true;
    }

    public void setVolumeMultiplier(float f) {
        this.handler.sendEmptyMessage(34);
    }

    public void stop() {
        this.handler.obtainMessage(6).sendToTarget();
    }

    private long getTotalBufferedDurationUs(long j2) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            return 0;
        }
        return Math.max(0, j2 - loadingPeriod.toPeriodTime(this.rendererPositionUs));
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, float f, boolean z, boolean z3) {
        if (z) {
            if (z3) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(1);
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
        }
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (RendererHolder playbackSpeed : this.renderers) {
            playbackSpeed.setPlaybackSpeed(f, playbackParameters.speed);
        }
    }

    private void updatePlayWhenReadyWithAudioFocus(boolean z, int i2, int i7) {
        updatePlayWhenReadyWithAudioFocus(z, this.audioFocusManager.updateAudioFocus(z, this.playbackInfo.playbackState), i2, i7);
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    private void enableRenderers(boolean[] zArr, long j2) {
        long j3;
        ExoPlayerImplInternal exoPlayerImplInternal;
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            if (!trackSelectorResult.isRendererEnabled(i2)) {
                this.renderers[i2].reset();
            }
        }
        int i7 = 0;
        while (i7 < this.renderers.length) {
            if (!trackSelectorResult.isRendererEnabled(i7) || this.renderers[i7].isReadingFromPeriod(readingPeriod)) {
                exoPlayerImplInternal = this;
                j3 = j2;
            } else {
                exoPlayerImplInternal = this;
                j3 = j2;
                exoPlayerImplInternal.enableRenderer(readingPeriod, i7, zArr[i7], j3);
            }
            i7++;
            this = exoPlayerImplInternal;
            j2 = j3;
        }
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j2, boolean z, boolean z3) {
        stopRenderers();
        updateRebufferingState(false, true);
        if (z3 || this.playbackInfo.playbackState == 3) {
            setState(2);
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolder = playingPeriod;
        while (mediaPeriodHolder != null && !mediaPeriodId.equals(mediaPeriodHolder.info.id)) {
            mediaPeriodHolder = mediaPeriodHolder.getNext();
        }
        if (z || playingPeriod != mediaPeriodHolder || (mediaPeriodHolder != null && mediaPeriodHolder.toRendererTime(j2) < 0)) {
            disableRenderers();
            if (mediaPeriodHolder != null) {
                while (this.queue.getPlayingPeriod() != mediaPeriodHolder) {
                    this.queue.advancePlayingPeriod();
                }
                this.queue.removeAfter(mediaPeriodHolder);
                mediaPeriodHolder.setRendererOffset(1000000000000L);
                enableRenderers();
                mediaPeriodHolder.allRenderersInCorrectState = true;
            }
        }
        disableAndResetPrewarmingRenderers();
        if (mediaPeriodHolder != null) {
            this.queue.removeAfter(mediaPeriodHolder);
            if (!mediaPeriodHolder.prepared) {
                mediaPeriodHolder.info = mediaPeriodHolder.info.copyWithStartPositionUs(j2);
            } else if (mediaPeriodHolder.hasEnabledTracks) {
                j2 = mediaPeriodHolder.mediaPeriod.seekToUs(j2);
                mediaPeriodHolder.mediaPeriod.discardBuffer(j2 - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }
            resetRendererPosition(j2);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(j2);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j2;
    }

    private void updatePlayWhenReadyWithAudioFocus(boolean z, int i2, int i7, int i8) {
        boolean z3 = z && i2 != -1;
        int updatePlayWhenReadyChangeReason = updatePlayWhenReadyChangeReason(i2, i8);
        int updatePlaybackSuppressionReason = updatePlaybackSuppressionReason(i2, i7);
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playWhenReady != z3 || playbackInfo2.playbackSuppressionReason != updatePlaybackSuppressionReason || playbackInfo2.playWhenReadyChangeReason != updatePlayWhenReadyChangeReason) {
            this.playbackInfo = playbackInfo2.copyWithPlayWhenReady(z3, updatePlayWhenReadyChangeReason, updatePlaybackSuppressionReason);
            updateRebufferingState(false, false);
            notifyTrackSelectionPlayWhenReadyChanged(z3);
            if (!shouldPlayWhenReady()) {
                stopRenderers();
                updatePlaybackPositions();
                PlaybackInfo playbackInfo3 = this.playbackInfo;
                if (playbackInfo3.sleepingForOffload) {
                    this.playbackInfo = playbackInfo3.copyWithSleepingForOffload(false);
                }
                this.queue.reevaluateBuffer(this.rendererPositionUs);
                return;
            }
            int i10 = this.playbackInfo.playbackState;
            if (i10 == 3) {
                this.mediaClock.start();
                startRenderers();
                this.handler.sendEmptyMessage(2);
            } else if (i10 == 2) {
                this.handler.sendEmptyMessage(2);
            }
        }
    }
}
