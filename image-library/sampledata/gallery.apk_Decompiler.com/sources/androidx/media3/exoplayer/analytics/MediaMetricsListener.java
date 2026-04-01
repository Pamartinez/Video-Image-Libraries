package androidx.media3.exoplayer.analytics;

import Ba.m;
import F2.G;
import F2.U;
import H.a;
import J.k;
import android.content.Context;
import android.media.DeniedByServerException;
import android.media.MediaCodec;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.NetworkEvent;
import android.media.metrics.PlaybackErrorEvent;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.PlaybackStateEvent;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.FileDataSource;
import androidx.media3.datasource.HttpDataSource$HttpDataSourceException;
import androidx.media3.datasource.HttpDataSource$InvalidContentTypeException;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.datasource.UdpDataSource;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlaybackSessionManager;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.UnsupportedDrmException;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaMetricsListener implements AnalyticsListener, PlaybackSessionManager.Listener {
    private String activeSessionId;
    private int audioUnderruns;
    private final Executor backgroundExecutor = BackgroundExecutor.get();
    private final HashMap<String, Long> bandwidthBytes = new HashMap<>();
    private final HashMap<String, Long> bandwidthTimeMs = new HashMap<>();
    private final Context context;
    private Format currentAudioFormat;
    private int currentNetworkType = 0;
    private int currentPlaybackState = 0;
    private Format currentTextFormat;
    private Format currentVideoFormat;
    private int discontinuityReason;
    private int droppedFrames;
    private boolean hasFatalError;
    private int ioErrorType;
    private boolean isSeeking;
    private PlaybackMetrics.Builder metricsBuilder;
    private PendingFormatUpdate pendingAudioFormat;
    private PlaybackException pendingPlayerError;
    private PendingFormatUpdate pendingTextFormat;
    private PendingFormatUpdate pendingVideoFormat;
    private final Timeline.Period period = new Timeline.Period();
    private final PlaybackSession playbackSession;
    private int playedFrames;
    private boolean reportedEventsForCurrentSession;
    private final PlaybackSessionManager sessionManager;
    private final long startTimeMs = SystemClock.elapsedRealtime();
    private final Timeline.Window window = new Timeline.Window();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ErrorInfo {
        public final int errorCode;
        public final int subErrorCode;

        public ErrorInfo(int i2, int i7) {
            this.errorCode = i2;
            this.subErrorCode = i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PendingFormatUpdate {
        public final Format format;
        public final int selectionReason;
        public final String sessionId;

        public PendingFormatUpdate(Format format2, int i2, String str) {
            this.format = format2;
            this.selectionReason = i2;
            this.sessionId = str;
        }
    }

    private MediaMetricsListener(Context context2, PlaybackSession playbackSession2) {
        this.context = context2.getApplicationContext();
        this.playbackSession = playbackSession2;
        DefaultPlaybackSessionManager defaultPlaybackSessionManager = new DefaultPlaybackSessionManager();
        this.sessionManager = defaultPlaybackSessionManager;
        defaultPlaybackSessionManager.setListener(this);
    }

    private boolean canReportPendingFormatUpdate(PendingFormatUpdate pendingFormatUpdate) {
        if (pendingFormatUpdate == null || !pendingFormatUpdate.sessionId.equals(this.sessionManager.getActiveSessionId())) {
            return false;
        }
        return true;
    }

    public static MediaMetricsListener create(Context context2) {
        MediaMetricsManager b = m.b(context2.getSystemService("media_metrics"));
        if (b == null) {
            return null;
        }
        return new MediaMetricsListener(context2, b.createPlaybackSession());
    }

    private void finishCurrentSession() {
        long j2;
        long j3;
        int i2;
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (builder != null && this.reportedEventsForCurrentSession) {
            builder.setAudioUnderrunCount(this.audioUnderruns);
            this.metricsBuilder.setVideoFramesDropped(this.droppedFrames);
            this.metricsBuilder.setVideoFramesPlayed(this.playedFrames);
            Long l = this.bandwidthTimeMs.get(this.activeSessionId);
            PlaybackMetrics.Builder builder2 = this.metricsBuilder;
            if (l == null) {
                j2 = 0;
            } else {
                j2 = l.longValue();
            }
            builder2.setNetworkTransferDurationMillis(j2);
            Long l8 = this.bandwidthBytes.get(this.activeSessionId);
            PlaybackMetrics.Builder builder3 = this.metricsBuilder;
            if (l8 == null) {
                j3 = 0;
            } else {
                j3 = l8.longValue();
            }
            builder3.setNetworkBytesRead(j3);
            PlaybackMetrics.Builder builder4 = this.metricsBuilder;
            if (l8 == null || l8.longValue() <= 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            builder4.setStreamSource(i2);
            this.backgroundExecutor.execute(new a(19, this, this.metricsBuilder.build()));
        }
        this.metricsBuilder = null;
        this.activeSessionId = null;
        this.audioUnderruns = 0;
        this.droppedFrames = 0;
        this.playedFrames = 0;
        this.currentVideoFormat = null;
        this.currentAudioFormat = null;
        this.currentTextFormat = null;
        this.reportedEventsForCurrentSession = false;
    }

    private static int getDrmErrorCode(int i2) {
        switch (Util.getErrorCodeForMediaDrmErrorCode(i2)) {
            case 6002:
                return 24;
            case 6003:
                return 28;
            case 6004:
                return 25;
            case 6005:
                return 26;
            default:
                return 27;
        }
    }

    private static DrmInitData getDrmInitData(U u) {
        DrmInitData drmInitData;
        G A10 = u.listIterator(0);
        while (A10.hasNext()) {
            Tracks.Group group = (Tracks.Group) A10.next();
            int i2 = 0;
            while (true) {
                if (i2 < group.length) {
                    if (group.isTrackSelected(i2) && (drmInitData = group.getTrackFormat(i2).drmInitData) != null) {
                        return drmInitData;
                    }
                    i2++;
                }
            }
        }
        return null;
    }

    private static int getDrmType(DrmInitData drmInitData) {
        for (int i2 = 0; i2 < drmInitData.schemeDataCount; i2++) {
            UUID uuid = drmInitData.get(i2).uuid;
            if (uuid.equals(C.WIDEVINE_UUID)) {
                return 3;
            }
            if (uuid.equals(C.PLAYREADY_UUID)) {
                return 2;
            }
            if (uuid.equals(C.CLEARKEY_UUID)) {
                return 6;
            }
        }
        return 1;
    }

    private static ErrorInfo getErrorInfo(PlaybackException playbackException, Context context2, boolean z) {
        boolean z3;
        int i2;
        int i7;
        if (playbackException.errorCode == 1001) {
            return new ErrorInfo(20, 0);
        }
        if (playbackException instanceof ExoPlaybackException) {
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) playbackException;
            if (exoPlaybackException.type == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            i2 = exoPlaybackException.rendererFormatSupport;
        } else {
            i2 = 0;
            z3 = false;
        }
        Throwable th = (Throwable) Assertions.checkNotNull(playbackException.getCause());
        if (th instanceof IOException) {
            if (th instanceof HttpDataSource$InvalidResponseCodeException) {
                return new ErrorInfo(5, ((HttpDataSource$InvalidResponseCodeException) th).responseCode);
            }
            if ((th instanceof HttpDataSource$InvalidContentTypeException) || (th instanceof ParserException)) {
                if (z) {
                    i7 = 10;
                } else {
                    i7 = 11;
                }
                return new ErrorInfo(i7, 0);
            }
            boolean z7 = th instanceof HttpDataSource$HttpDataSourceException;
            if (z7 || (th instanceof UdpDataSource.UdpDataSourceException)) {
                if (NetworkTypeObserver.getInstance(context2).getNetworkType() == 1) {
                    return new ErrorInfo(3, 0);
                }
                Throwable cause = th.getCause();
                if (cause instanceof UnknownHostException) {
                    return new ErrorInfo(6, 0);
                }
                if (cause instanceof SocketTimeoutException) {
                    return new ErrorInfo(7, 0);
                }
                if (!z7 || ((HttpDataSource$HttpDataSourceException) th).type != 1) {
                    return new ErrorInfo(8, 0);
                }
                return new ErrorInfo(4, 0);
            } else if (playbackException.errorCode == 1002) {
                return new ErrorInfo(21, 0);
            } else {
                if (th instanceof DrmSession.DrmSessionException) {
                    Throwable th2 = (Throwable) Assertions.checkNotNull(th.getCause());
                    if (th2 instanceof MediaDrm.MediaDrmStateException) {
                        int errorCodeFromPlatformDiagnosticsInfo = Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) th2).getDiagnosticInfo());
                        return new ErrorInfo(getDrmErrorCode(errorCodeFromPlatformDiagnosticsInfo), errorCodeFromPlatformDiagnosticsInfo);
                    } else if (th2 instanceof MediaDrmResetException) {
                        return new ErrorInfo(27, 0);
                    } else {
                        if (th2 instanceof NotProvisionedException) {
                            return new ErrorInfo(24, 0);
                        }
                        if (th2 instanceof DeniedByServerException) {
                            return new ErrorInfo(29, 0);
                        }
                        if (th2 instanceof UnsupportedDrmException) {
                            return new ErrorInfo(23, 0);
                        }
                        if (th2 instanceof DefaultDrmSessionManager.MissingSchemeDataException) {
                            return new ErrorInfo(28, 0);
                        }
                        return new ErrorInfo(30, 0);
                    }
                } else if (!(th instanceof FileDataSource.FileDataSourceException) || !(th.getCause() instanceof FileNotFoundException)) {
                    return new ErrorInfo(9, 0);
                } else {
                    Throwable cause2 = ((Throwable) Assertions.checkNotNull(th.getCause())).getCause();
                    if (!(cause2 instanceof ErrnoException) || ((ErrnoException) cause2).errno != OsConstants.EACCES) {
                        return new ErrorInfo(31, 0);
                    }
                    return new ErrorInfo(32, 0);
                }
            }
        } else if (z3 && (i2 == 0 || i2 == 1)) {
            return new ErrorInfo(35, 0);
        } else {
            if (z3 && i2 == 3) {
                return new ErrorInfo(15, 0);
            }
            if (z3 && i2 == 2) {
                return new ErrorInfo(23, 0);
            }
            if (th instanceof OutOfMemoryError) {
                return new ErrorInfo(14, 0);
            }
            if (!(th instanceof MediaCodec.CryptoException)) {
                return new ErrorInfo(22, 0);
            }
            int errorCode = ((MediaCodec.CryptoException) th).getErrorCode();
            return new ErrorInfo(getDrmErrorCode(errorCode), errorCode);
        }
    }

    private static Pair<String, String> getLanguageAndRegion(String str) {
        String str2;
        String[] split = Util.split(str, "-");
        String str3 = split[0];
        if (split.length >= 2) {
            str2 = split[1];
        } else {
            str2 = null;
        }
        return Pair.create(str3, str2);
    }

    private static int getNetworkType(Context context2) {
        switch (NetworkTypeObserver.getInstance(context2).getNetworkType()) {
            case 0:
                return 0;
            case 1:
                return 9;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 7:
                return 3;
            case 9:
                return 8;
            case 10:
                return 7;
            default:
                return 1;
        }
    }

    private static int getStreamType(MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        if (localConfiguration == null) {
            return 0;
        }
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType);
        if (inferContentTypeForUriAndMimeType == 0) {
            return 3;
        }
        if (inferContentTypeForUriAndMimeType == 1) {
            return 5;
        }
        if (inferContentTypeForUriAndMimeType != 2) {
            return 1;
        }
        return 4;
    }

    private static int getTrackChangeReason(int i2) {
        if (i2 == 1) {
            return 2;
        }
        if (i2 == 2) {
            return 3;
        }
        if (i2 != 3) {
            return 1;
        }
        return 4;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finishCurrentSession$4(PlaybackMetrics playbackMetrics) {
        this.playbackSession.reportPlaybackMetrics(playbackMetrics);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$maybeReportNetworkChange$1(NetworkEvent networkEvent) {
        this.playbackSession.reportNetworkEvent(networkEvent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$maybeReportPlaybackError$0(PlaybackErrorEvent playbackErrorEvent) {
        this.playbackSession.reportPlaybackErrorEvent(playbackErrorEvent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$maybeReportPlaybackStateChange$2(PlaybackStateEvent playbackStateEvent) {
        this.playbackSession.reportPlaybackStateEvent(playbackStateEvent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reportTrackChangeEvent$3(TrackChangeEvent trackChangeEvent) {
        this.playbackSession.reportTrackChangeEvent(trackChangeEvent);
    }

    private void maybeAddSessions(AnalyticsListener.Events events) {
        for (int i2 = 0; i2 < events.size(); i2++) {
            int i7 = events.get(i2);
            AnalyticsListener.EventTime eventTime = events.getEventTime(i7);
            if (i7 == 0) {
                this.sessionManager.updateSessionsWithTimelineChange(eventTime);
            } else if (i7 == 11) {
                this.sessionManager.updateSessionsWithDiscontinuity(eventTime, this.discontinuityReason);
            } else {
                this.sessionManager.updateSessions(eventTime);
            }
        }
    }

    private void maybeReportNetworkChange(long j2) {
        int networkType = getNetworkType(this.context);
        if (networkType != this.currentNetworkType) {
            this.currentNetworkType = networkType;
            this.backgroundExecutor.execute(new a(17, this, k.c().setNetworkType(networkType).setTimeSinceCreatedMillis(j2 - this.startTimeMs).build()));
        }
    }

    private void maybeReportPlaybackError(long j2) {
        boolean z;
        PlaybackException playbackException = this.pendingPlayerError;
        if (playbackException != null) {
            Context context2 = this.context;
            if (this.ioErrorType == 4) {
                z = true;
            } else {
                z = false;
            }
            ErrorInfo errorInfo = getErrorInfo(playbackException, context2, z);
            this.backgroundExecutor.execute(new a(18, this, k.d().setTimeSinceCreatedMillis(j2 - this.startTimeMs).setErrorCode(errorInfo.errorCode).setSubErrorCode(errorInfo.subErrorCode).setException(playbackException).build()));
            this.reportedEventsForCurrentSession = true;
            this.pendingPlayerError = null;
        }
    }

    private void maybeReportPlaybackStateChange(Player player, AnalyticsListener.Events events, long j2) {
        if (player.getPlaybackState() != 2) {
            this.isSeeking = false;
        }
        if (player.getPlayerError() == null) {
            this.hasFatalError = false;
        } else if (events.contains(10)) {
            this.hasFatalError = true;
        }
        int resolveNewPlaybackState = resolveNewPlaybackState(player);
        if (this.currentPlaybackState != resolveNewPlaybackState) {
            this.currentPlaybackState = resolveNewPlaybackState;
            this.reportedEventsForCurrentSession = true;
            this.backgroundExecutor.execute(new a(20, this, k.g().setState(this.currentPlaybackState).setTimeSinceCreatedMillis(j2 - this.startTimeMs).build()));
        }
    }

    private void maybeReportTrackChanges(Player player, AnalyticsListener.Events events, long j2) {
        if (events.contains(2)) {
            Tracks currentTracks = player.getCurrentTracks();
            boolean isTypeSelected = currentTracks.isTypeSelected(2);
            boolean isTypeSelected2 = currentTracks.isTypeSelected(1);
            boolean isTypeSelected3 = currentTracks.isTypeSelected(3);
            if (isTypeSelected || isTypeSelected2 || isTypeSelected3) {
                if (!isTypeSelected) {
                    maybeUpdateVideoFormat(j2, (Format) null, 0);
                }
                if (!isTypeSelected2) {
                    maybeUpdateAudioFormat(j2, (Format) null, 0);
                }
                if (!isTypeSelected3) {
                    maybeUpdateTextFormat(j2, (Format) null, 0);
                }
            }
        }
        if (canReportPendingFormatUpdate(this.pendingVideoFormat)) {
            PendingFormatUpdate pendingFormatUpdate = this.pendingVideoFormat;
            Format format = pendingFormatUpdate.format;
            if (format.height != -1) {
                maybeUpdateVideoFormat(j2, format, pendingFormatUpdate.selectionReason);
                this.pendingVideoFormat = null;
            }
        }
        if (canReportPendingFormatUpdate(this.pendingAudioFormat)) {
            PendingFormatUpdate pendingFormatUpdate2 = this.pendingAudioFormat;
            maybeUpdateAudioFormat(j2, pendingFormatUpdate2.format, pendingFormatUpdate2.selectionReason);
            this.pendingAudioFormat = null;
        }
        if (canReportPendingFormatUpdate(this.pendingTextFormat)) {
            PendingFormatUpdate pendingFormatUpdate3 = this.pendingTextFormat;
            maybeUpdateTextFormat(j2, pendingFormatUpdate3.format, pendingFormatUpdate3.selectionReason);
            this.pendingTextFormat = null;
        }
    }

    private void maybeUpdateAudioFormat(long j2, Format format, int i2) {
        if (!Objects.equals(this.currentAudioFormat, format)) {
            if (this.currentAudioFormat == null && i2 == 0) {
                i2 = 1;
            }
            this.currentAudioFormat = format;
            reportTrackChangeEvent(0, j2, format, i2);
        }
    }

    private void maybeUpdateMetricsBuilderValues(Player player, AnalyticsListener.Events events) {
        DrmInitData drmInitData;
        if (events.contains(0)) {
            AnalyticsListener.EventTime eventTime = events.getEventTime(0);
            if (this.metricsBuilder != null) {
                maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
            }
        }
        if (!(!events.contains(2) || this.metricsBuilder == null || (drmInitData = getDrmInitData(player.getCurrentTracks().getGroups())) == null)) {
            m.k(Util.castNonNull(this.metricsBuilder)).setDrmType(getDrmType(drmInitData));
        }
        if (events.contains(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN)) {
            this.audioUnderruns++;
        }
    }

    private void maybeUpdateTextFormat(long j2, Format format, int i2) {
        if (!Objects.equals(this.currentTextFormat, format)) {
            if (this.currentTextFormat == null && i2 == 0) {
                i2 = 1;
            }
            this.currentTextFormat = format;
            reportTrackChangeEvent(2, j2, format, i2);
        }
    }

    private void maybeUpdateTimelineMetadata(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        int indexOfPeriod;
        int i2;
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (mediaPeriodId != null && (indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid)) != -1) {
            timeline.getPeriod(indexOfPeriod, this.period);
            timeline.getWindow(this.period.windowIndex, this.window);
            builder.setStreamType(getStreamType(this.window.mediaItem));
            Timeline.Window window2 = this.window;
            if (window2.durationUs != -9223372036854775807L && !window2.isPlaceholder && !window2.isDynamic && !window2.isLive()) {
                builder.setMediaDurationMillis(this.window.getDurationMs());
            }
            if (this.window.isLive()) {
                i2 = 2;
            } else {
                i2 = 1;
            }
            builder.setPlaybackType(i2);
            this.reportedEventsForCurrentSession = true;
        }
    }

    private void maybeUpdateVideoFormat(long j2, Format format, int i2) {
        if (!Objects.equals(this.currentVideoFormat, format)) {
            if (this.currentVideoFormat == null && i2 == 0) {
                i2 = 1;
            }
            this.currentVideoFormat = format;
            reportTrackChangeEvent(1, j2, format, i2);
        }
    }

    private void reportTrackChangeEvent(int i2, long j2, Format format, int i7) {
        TrackChangeEvent.Builder m = k.k(i2).setTimeSinceCreatedMillis(j2 - this.startTimeMs);
        if (format != null) {
            m.setTrackState(1);
            m.setTrackChangeReason(getTrackChangeReason(i7));
            String str = format.containerMimeType;
            if (str != null) {
                m.setContainerMimeType(str);
            }
            String str2 = format.sampleMimeType;
            if (str2 != null) {
                m.setSampleMimeType(str2);
            }
            String str3 = format.codecs;
            if (str3 != null) {
                m.setCodecName(str3);
            }
            int i8 = format.bitrate;
            if (i8 != -1) {
                m.setBitrate(i8);
            }
            int i10 = format.width;
            if (i10 != -1) {
                m.setWidth(i10);
            }
            int i11 = format.height;
            if (i11 != -1) {
                m.setHeight(i11);
            }
            int i12 = format.channelCount;
            if (i12 != -1) {
                m.setChannelCount(i12);
            }
            int i13 = format.sampleRate;
            if (i13 != -1) {
                m.setAudioSampleRate(i13);
            }
            String str4 = format.language;
            if (str4 != null) {
                Pair<String, String> languageAndRegion = getLanguageAndRegion(str4);
                m.setLanguage((String) languageAndRegion.first);
                Object obj = languageAndRegion.second;
                if (obj != null) {
                    m.setLanguageRegion((String) obj);
                }
            }
            float f = format.frameRate;
            if (f != -1.0f) {
                m.setVideoFrameRate(f);
            }
        } else {
            m.setTrackState(0);
        }
        this.reportedEventsForCurrentSession = true;
        this.backgroundExecutor.execute(new a(16, this, m.build()));
    }

    private int resolveNewPlaybackState(Player player) {
        int playbackState = player.getPlaybackState();
        if (this.isSeeking) {
            return 5;
        }
        if (this.hasFatalError) {
            return 13;
        }
        if (playbackState == 4) {
            return 11;
        }
        if (playbackState == 2) {
            int i2 = this.currentPlaybackState;
            if (i2 == 0 || i2 == 2 || i2 == 12) {
                return 2;
            }
            if (!player.getPlayWhenReady()) {
                return 7;
            }
            if (player.getPlaybackSuppressionReason() != 0) {
                return 10;
            }
            return 6;
        } else if (playbackState == 3) {
            if (!player.getPlayWhenReady()) {
                return 4;
            }
            if (player.getPlaybackSuppressionReason() != 0) {
                return 9;
            }
            return 3;
        } else if (playbackState != 1 || this.currentPlaybackState == 0) {
            return this.currentPlaybackState;
        } else {
            return 12;
        }
    }

    public LogSessionId getLogSessionId() {
        return this.playbackSession.getSessionId();
    }

    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i2, long j2, long j3) {
        long j8;
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId != null) {
            String sessionForMediaPeriodId = this.sessionManager.getSessionForMediaPeriodId(eventTime.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(mediaPeriodId));
            Long l = this.bandwidthBytes.get(sessionForMediaPeriodId);
            Long l8 = this.bandwidthTimeMs.get(sessionForMediaPeriodId);
            HashMap<String, Long> hashMap = this.bandwidthBytes;
            long j10 = 0;
            if (l == null) {
                j8 = 0;
            } else {
                j8 = l.longValue();
            }
            hashMap.put(sessionForMediaPeriodId, Long.valueOf(j8 + j2));
            HashMap<String, Long> hashMap2 = this.bandwidthTimeMs;
            if (l8 != null) {
                j10 = l8.longValue();
            }
            hashMap2.put(sessionForMediaPeriodId, Long.valueOf(j10 + ((long) i2)));
        }
    }

    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        if (eventTime.mediaPeriodId != null) {
            PendingFormatUpdate pendingFormatUpdate = new PendingFormatUpdate((Format) Assertions.checkNotNull(mediaLoadData.trackFormat), mediaLoadData.trackSelectionReason, this.sessionManager.getSessionForMediaPeriodId(eventTime.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(eventTime.mediaPeriodId)));
            int i2 = mediaLoadData.trackType;
            if (i2 != 0) {
                if (i2 == 1) {
                    this.pendingAudioFormat = pendingFormatUpdate;
                    return;
                } else if (i2 != 2) {
                    if (i2 == 3) {
                        this.pendingTextFormat = pendingFormatUpdate;
                        return;
                    }
                    return;
                }
            }
            this.pendingVideoFormat = pendingFormatUpdate;
        }
    }

    public void onEvents(Player player, AnalyticsListener.Events events) {
        if (events.size() != 0) {
            maybeAddSessions(events);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            maybeUpdateMetricsBuilderValues(player, events);
            maybeReportPlaybackError(elapsedRealtime);
            maybeReportTrackChanges(player, events, elapsedRealtime);
            maybeReportNetworkChange(elapsedRealtime);
            maybeReportPlaybackStateChange(player, events, elapsedRealtime);
            if (events.contains(1028)) {
                this.sessionManager.finishAllSessions(events.getEventTime(1028));
            }
        }
    }

    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.ioErrorType = mediaLoadData.dataType;
    }

    public void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        this.pendingPlayerError = playbackException;
    }

    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) {
        if (i2 == 1) {
            this.isSeeking = true;
        }
        this.discontinuityReason = i2;
    }

    public void onSessionActive(AnalyticsListener.EventTime eventTime, String str) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId == null || !mediaPeriodId.isAd()) {
            finishCurrentSession();
            this.activeSessionId = str;
            this.metricsBuilder = k.e().setPlayerName("AndroidXMedia3").setPlayerVersion("1.8.0");
            maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
        }
    }

    public void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if ((mediaPeriodId == null || !mediaPeriodId.isAd()) && str.equals(this.activeSessionId)) {
            finishCurrentSession();
        }
        this.bandwidthTimeMs.remove(str);
        this.bandwidthBytes.remove(str);
    }

    public void onSessionCreated(AnalyticsListener.EventTime eventTime, String str) {
    }

    public void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2) {
    }
}
