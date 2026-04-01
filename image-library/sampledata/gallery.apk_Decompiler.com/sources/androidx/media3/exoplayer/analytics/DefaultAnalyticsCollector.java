package androidx.media3.exoplayer.analytics;

import A4.H;
import Ad.j;
import Bb.k;
import Bb.m;
import F2.D0;
import F2.G;
import F2.U;
import F2.X;
import F2.Y;
import F2.y0;
import H3.l;
import J.a;
import J.b;
import J.c;
import J.d;
import J.e;
import J.f;
import J.g;
import J.h;
import J.i;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultAnalyticsCollector implements AnalyticsCollector {
    private final Clock clock;
    private final SparseArray<AnalyticsListener.EventTime> eventTimes;
    private HandlerWrapper handler;
    private boolean isSeeking;
    private ListenerSet<AnalyticsListener> listeners;
    private final MediaPeriodQueueTracker mediaPeriodQueueTracker;
    private final Timeline.Period period;
    private Player player;
    private final Timeline.Window window = new Timeline.Window();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaPeriodQueueTracker {
        private MediaSource.MediaPeriodId currentPlayerMediaPeriod;
        /* access modifiers changed from: private */
        public U mediaPeriodQueue = y0.f278h;
        private Y mediaPeriodTimelines = D0.k;
        private final Timeline.Period period;
        private MediaSource.MediaPeriodId playingMediaPeriod;
        private MediaSource.MediaPeriodId readingMediaPeriod;

        public MediaPeriodQueueTracker(Timeline.Period period2) {
            this.period = period2;
            G g = U.e;
        }

        private void addTimelineForMediaPeriodId(X x9, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
            if (mediaPeriodId != null) {
                if (timeline.getIndexOfPeriod(mediaPeriodId.periodUid) != -1) {
                    x9.b(mediaPeriodId, timeline);
                    return;
                }
                Timeline timeline2 = (Timeline) this.mediaPeriodTimelines.get(mediaPeriodId);
                if (timeline2 != null) {
                    x9.b(mediaPeriodId, timeline2);
                }
            }
        }

        private static MediaSource.MediaPeriodId findCurrentPlayerMediaPeriodInQueue(Player player, U u, MediaSource.MediaPeriodId mediaPeriodId, Timeline.Period period2) {
            Object obj;
            int i2;
            Timeline currentTimeline = player.getCurrentTimeline();
            int currentPeriodIndex = player.getCurrentPeriodIndex();
            if (currentTimeline.isEmpty()) {
                obj = null;
            } else {
                obj = currentTimeline.getUidOfPeriod(currentPeriodIndex);
            }
            if (player.isPlayingAd() || currentTimeline.isEmpty()) {
                i2 = -1;
            } else {
                i2 = currentTimeline.getPeriod(currentPeriodIndex, period2).getAdGroupIndexAfterPositionUs(Util.msToUs(player.getCurrentPosition()) - period2.getPositionInWindowUs());
            }
            int i7 = i2;
            for (int i8 = 0; i8 < u.size(); i8++) {
                MediaSource.MediaPeriodId mediaPeriodId2 = (MediaSource.MediaPeriodId) u.get(i8);
                if (isMatchingMediaPeriod(mediaPeriodId2, obj, player.isPlayingAd(), player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup(), i7)) {
                    return mediaPeriodId2;
                }
            }
            if (u.isEmpty() && mediaPeriodId != null) {
                MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId;
                if (isMatchingMediaPeriod(mediaPeriodId3, obj, player.isPlayingAd(), player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup(), i7)) {
                    return mediaPeriodId3;
                }
            }
            return null;
        }

        private static boolean isMatchingMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, Object obj, boolean z, int i2, int i7, int i8) {
            if (!mediaPeriodId.periodUid.equals(obj)) {
                return false;
            }
            if (z && mediaPeriodId.adGroupIndex == i2 && mediaPeriodId.adIndexInAdGroup == i7) {
                return true;
            }
            if (!z && mediaPeriodId.adGroupIndex == -1 && mediaPeriodId.nextAdGroupIndex == i8) {
                return true;
            }
            return false;
        }

        private void updateMediaPeriodTimelines(Timeline timeline) {
            X x9 = new X(4);
            if (this.mediaPeriodQueue.isEmpty()) {
                addTimelineForMediaPeriodId(x9, this.playingMediaPeriod, timeline);
                if (!Objects.equals(this.readingMediaPeriod, this.playingMediaPeriod)) {
                    addTimelineForMediaPeriodId(x9, this.readingMediaPeriod, timeline);
                }
                if (!Objects.equals(this.currentPlayerMediaPeriod, this.playingMediaPeriod) && !Objects.equals(this.currentPlayerMediaPeriod, this.readingMediaPeriod)) {
                    addTimelineForMediaPeriodId(x9, this.currentPlayerMediaPeriod, timeline);
                }
            } else {
                for (int i2 = 0; i2 < this.mediaPeriodQueue.size(); i2++) {
                    addTimelineForMediaPeriodId(x9, (MediaSource.MediaPeriodId) this.mediaPeriodQueue.get(i2), timeline);
                }
                if (!this.mediaPeriodQueue.contains(this.currentPlayerMediaPeriod)) {
                    addTimelineForMediaPeriodId(x9, this.currentPlayerMediaPeriod, timeline);
                }
            }
            this.mediaPeriodTimelines = x9.a();
        }

        public MediaSource.MediaPeriodId getCurrentPlayerMediaPeriod() {
            return this.currentPlayerMediaPeriod;
        }

        public MediaSource.MediaPeriodId getLoadingMediaPeriod() {
            Object obj;
            Object next;
            if (this.mediaPeriodQueue.isEmpty()) {
                return null;
            }
            U u = this.mediaPeriodQueue;
            if (u == null) {
                Iterator it = u.iterator();
                do {
                    next = it.next();
                } while (it.hasNext());
                obj = next;
            } else if (!u.isEmpty()) {
                obj = u.get(u.size() - 1);
            } else {
                throw new NoSuchElementException();
            }
            return (MediaSource.MediaPeriodId) obj;
        }

        public Timeline getMediaPeriodIdTimeline(MediaSource.MediaPeriodId mediaPeriodId) {
            return (Timeline) this.mediaPeriodTimelines.get(mediaPeriodId);
        }

        public MediaSource.MediaPeriodId getReadingMediaPeriod() {
            return this.readingMediaPeriod;
        }

        public void onPositionDiscontinuity(Player player) {
            this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(player, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
        }

        public void onQueueUpdated(List<MediaSource.MediaPeriodId> list, MediaSource.MediaPeriodId mediaPeriodId, Player player) {
            this.mediaPeriodQueue = U.y(list);
            if (!list.isEmpty()) {
                this.playingMediaPeriod = list.get(0);
                this.readingMediaPeriod = (MediaSource.MediaPeriodId) Assertions.checkNotNull(mediaPeriodId);
            }
            if (this.currentPlayerMediaPeriod == null) {
                this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(player, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
            }
            updateMediaPeriodTimelines(player.getCurrentTimeline());
        }

        public void onTimelineChanged(Player player) {
            this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(player, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
            updateMediaPeriodTimelines(player.getCurrentTimeline());
        }
    }

    public DefaultAnalyticsCollector(Clock clock2) {
        this.clock = (Clock) Assertions.checkNotNull(clock2);
        this.listeners = new ListenerSet<>(Util.getCurrentOrMainLooper(), clock2, new j(27));
        Timeline.Period period2 = new Timeline.Period();
        this.period = period2;
        this.mediaPeriodQueueTracker = new MediaPeriodQueueTracker(period2);
        this.eventTimes = new SparseArray<>();
    }

    private AnalyticsListener.EventTime generateLoadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getLoadingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateMediaPeriodEventTime(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        Assertions.checkNotNull(this.player);
        if (mediaPeriodId == null) {
            Timeline currentTimeline = this.player.getCurrentTimeline();
            if (i2 >= currentTimeline.getWindowCount()) {
                currentTimeline = Timeline.EMPTY;
            }
            return generateEventTime(currentTimeline, i2, (MediaSource.MediaPeriodId) null);
        } else if (this.mediaPeriodQueueTracker.getMediaPeriodIdTimeline(mediaPeriodId) != null) {
            return generateEventTime(mediaPeriodId);
        } else {
            return generateEventTime(Timeline.EMPTY, i2, mediaPeriodId);
        }
    }

    private AnalyticsListener.EventTime generateReadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getReadingMediaPeriod());
    }

    private AnalyticsListener.EventTime getEventTimeForErrorEvent(PlaybackException playbackException) {
        MediaSource.MediaPeriodId mediaPeriodId;
        if (!(playbackException instanceof ExoPlaybackException) || (mediaPeriodId = ((ExoPlaybackException) playbackException).mediaPeriodId) == null) {
            return generateCurrentPlayerMediaPeriodEventTime();
        }
        return generateEventTime(mediaPeriodId);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onBandwidthSample$63(AnalyticsListener.EventTime eventTime, int i2, long j2, long j3, AnalyticsListener analyticsListener) {
        int i7 = i2;
        long j8 = j2;
        analyticsListener.onBandwidthEstimate(eventTime, i7, j8, j3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDrmSessionAcquired$64(AnalyticsListener.EventTime eventTime, int i2, AnalyticsListener analyticsListener) {
        analyticsListener.onDrmSessionAcquired(eventTime);
        analyticsListener.onDrmSessionAcquired(eventTime, i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onIsLoadingChanged$35(AnalyticsListener.EventTime eventTime, boolean z, AnalyticsListener analyticsListener) {
        analyticsListener.onLoadingChanged(eventTime, z);
        analyticsListener.onIsLoadingChanged(eventTime, z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onLoadError$29(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z, AnalyticsListener analyticsListener) {
        LoadEventInfo loadEventInfo2 = loadEventInfo;
        AnalyticsListener.EventTime eventTime2 = eventTime;
        AnalyticsListener analyticsListener2 = analyticsListener;
        boolean z3 = z;
        IOException iOException2 = iOException;
        analyticsListener2.onLoadError(eventTime2, loadEventInfo2, mediaLoadData, iOException2, z3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onLoadStarted$26(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2, AnalyticsListener analyticsListener) {
        analyticsListener.onLoadStarted(eventTime, loadEventInfo, mediaLoadData);
        analyticsListener.onLoadStarted(eventTime, loadEventInfo, mediaLoadData, i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onPositionDiscontinuity$46(AnalyticsListener.EventTime eventTime, int i2, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, AnalyticsListener analyticsListener) {
        analyticsListener.onPositionDiscontinuity(eventTime, i2);
        analyticsListener.onPositionDiscontinuity(eventTime, positionInfo, positionInfo2, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlayer$1(Player player2, AnalyticsListener analyticsListener, FlagSet flagSet) {
        analyticsListener.onEvents(player2, new AnalyticsListener.Events(flagSet, this.eventTimes));
    }

    /* access modifiers changed from: private */
    public void releaseInternal() {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 1028, new f(generateCurrentPlayerMediaPeriodEventTime, 0));
        this.listeners.release();
    }

    public void addListener(AnalyticsListener analyticsListener) {
        Assertions.checkNotNull(analyticsListener);
        this.listeners.add(analyticsListener);
    }

    public final AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getCurrentPlayerMediaPeriod());
    }

    public final AnalyticsListener.EventTime generateEventTime(Timeline timeline, int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline2 = timeline;
        int i7 = i2;
        MediaSource.MediaPeriodId mediaPeriodId2 = timeline2.isEmpty() ? null : mediaPeriodId;
        long elapsedRealtime = this.clock.elapsedRealtime();
        boolean z = timeline2.equals(this.player.getCurrentTimeline()) && i7 == this.player.getCurrentMediaItemIndex();
        long j2 = 0;
        if (mediaPeriodId2 == null || !mediaPeriodId2.isAd()) {
            if (z) {
                j2 = this.player.getContentPosition();
            } else if (!timeline2.isEmpty()) {
                j2 = timeline2.getWindow(i7, this.window).getDefaultPositionMs();
            }
        } else if (z && this.player.getCurrentAdGroupIndex() == mediaPeriodId2.adGroupIndex && this.player.getCurrentAdIndexInAdGroup() == mediaPeriodId2.adIndexInAdGroup) {
            j2 = this.player.getCurrentPosition();
        }
        return new AnalyticsListener.EventTime(elapsedRealtime, timeline2, i7, mediaPeriodId2, j2, this.player.getCurrentTimeline(), this.player.getCurrentMediaItemIndex(), this.mediaPeriodQueueTracker.getCurrentPlayerMediaPeriod(), this.player.getCurrentPosition(), this.player.getTotalBufferedDuration());
    }

    public final void onAudioSessionIdChanged(int i2) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 21, new a(generateReadingMediaPeriodEventTime, i2, 3));
    }

    public void onAvailableCommandsChanged(Player.Commands commands) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 13, new H(22, (Object) generateCurrentPlayerMediaPeriodEventTime, (Object) commands));
    }

    public final void onBandwidthSample(int i2, long j2, long j3) {
        AnalyticsListener.EventTime generateLoadingMediaPeriodEventTime = generateLoadingMediaPeriodEventTime();
        sendEvent(generateLoadingMediaPeriodEventTime, ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, new i(generateLoadingMediaPeriodEventTime, i2, j2, j3));
    }

    public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 29, new H(19, (Object) generateCurrentPlayerMediaPeriodEventTime, (Object) deviceInfo));
    }

    public void onDeviceVolumeChanged(int i2, boolean z) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 30, new c(generateCurrentPlayerMediaPeriodEventTime, i2, z));
    }

    public final void onDownstreamFormatChanged(int i2, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1004, new H(18, (Object) generateMediaPeriodEventTime, (Object) mediaLoadData));
    }

    public final void onDrmKeysLoaded(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1023, new f(generateMediaPeriodEventTime, 4));
    }

    public final void onDrmKeysRemoved(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1026, new f(generateMediaPeriodEventTime, 2));
    }

    public final void onDrmKeysRestored(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1025, new f(generateMediaPeriodEventTime, 3));
    }

    public final void onDrmSessionAcquired(int i2, MediaSource.MediaPeriodId mediaPeriodId, int i7) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1022, new a(generateMediaPeriodEventTime, i7, 2));
    }

    public final void onDrmSessionManagerError(int i2, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1024, new H(20, (Object) generateMediaPeriodEventTime, (Object) exc));
    }

    public final void onDrmSessionReleased(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1027, new f(generateMediaPeriodEventTime, 1));
    }

    public final void onIsLoadingChanged(boolean z) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 3, new J.j(generateCurrentPlayerMediaPeriodEventTime, 0, z));
    }

    public void onIsPlayingChanged(boolean z) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 7, new J.j(generateCurrentPlayerMediaPeriodEventTime, 1, z));
    }

    public final void onLoadCanceled(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1002, new h(generateMediaPeriodEventTime, loadEventInfo, mediaLoadData, 0));
    }

    public final void onLoadCompleted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1001, new h(generateMediaPeriodEventTime, loadEventInfo, mediaLoadData, 1));
    }

    public final void onLoadError(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1003, new g(0, (Object) generateMediaPeriodEventTime, (Object) loadEventInfo, (Object) mediaLoadData, (Object) iOException, z));
    }

    public final void onLoadStarted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i7) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i2, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1000, new m((Object) generateMediaPeriodEventTime, (Object) loadEventInfo, (Object) mediaLoadData, i7, 4));
    }

    public final void onMediaItemTransition(MediaItem mediaItem, int i2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 1, new k(i2, (Object) generateCurrentPlayerMediaPeriodEventTime, (Object) mediaItem));
    }

    public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 14, new H(21, (Object) generateCurrentPlayerMediaPeriodEventTime, (Object) mediaMetadata));
    }

    public final void onPlayWhenReadyChanged(boolean z, int i2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 5, new c(generateCurrentPlayerMediaPeriodEventTime, i2, 0, z));
    }

    public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 12, new H(16, (Object) generateCurrentPlayerMediaPeriodEventTime, (Object) playbackParameters));
    }

    public final void onPlaybackStateChanged(int i2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 4, new a(generateCurrentPlayerMediaPeriodEventTime, i2, 1));
    }

    public final void onPlaybackSuppressionReasonChanged(int i2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 6, new a(generateCurrentPlayerMediaPeriodEventTime, i2, 0));
    }

    public final void onPlayerError(PlaybackException playbackException) {
        AnalyticsListener.EventTime eventTimeForErrorEvent = getEventTimeForErrorEvent(playbackException);
        sendEvent(eventTimeForErrorEvent, 10, new b(eventTimeForErrorEvent, playbackException, 1));
    }

    public void onPlayerErrorChanged(PlaybackException playbackException) {
        AnalyticsListener.EventTime eventTimeForErrorEvent = getEventTimeForErrorEvent(playbackException);
        sendEvent(eventTimeForErrorEvent, 10, new b(eventTimeForErrorEvent, playbackException, 0));
    }

    public final void onPlayerStateChanged(boolean z, int i2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, -1, new c(generateCurrentPlayerMediaPeriodEventTime, i2, 2, z));
    }

    public void onPositionDiscontinuity(int i2) {
    }

    public void onRendererReadyChanged(int i2, int i7, boolean z) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1033, new d(generateReadingMediaPeriodEventTime, i2, i7, z));
    }

    public final void onSurfaceSizeChanged(int i2, int i7) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 24, new e(generateReadingMediaPeriodEventTime, i2, i7));
    }

    public final void onTimelineChanged(Timeline timeline, int i2) {
        this.mediaPeriodQueueTracker.onTimelineChanged((Player) Assertions.checkNotNull(this.player));
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 0, new a(generateCurrentPlayerMediaPeriodEventTime, i2, 4));
    }

    public void onTracksChanged(Tracks tracks) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 2, new H(17, (Object) generateCurrentPlayerMediaPeriodEventTime, (Object) tracks));
    }

    public void release() {
        ((HandlerWrapper) Assertions.checkStateNotNull(this.handler)).post(new l(12, this));
    }

    public final void sendEvent(AnalyticsListener.EventTime eventTime, int i2, ListenerSet.Event<AnalyticsListener> event) {
        this.eventTimes.put(i2, eventTime);
        this.listeners.sendEvent(i2, event);
    }

    public void setPlayer(Player player2, Looper looper) {
        boolean z;
        if (this.player == null || this.mediaPeriodQueueTracker.mediaPeriodQueue.isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.player = (Player) Assertions.checkNotNull(player2);
        this.handler = this.clock.createHandler(looper, (Handler.Callback) null);
        this.listeners = this.listeners.copy(looper, new H(23, (Object) this, (Object) player2));
    }

    public final void updateMediaPeriodQueueInfo(List<MediaSource.MediaPeriodId> list, MediaSource.MediaPeriodId mediaPeriodId) {
        this.mediaPeriodQueueTracker.onQueueUpdated(list, mediaPeriodId, (Player) Assertions.checkNotNull(this.player));
    }

    public final void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) {
        if (i2 == 1) {
            this.isSeeking = false;
        }
        this.mediaPeriodQueueTracker.onPositionDiscontinuity((Player) Assertions.checkNotNull(this.player));
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 11, new m((Object) generateCurrentPlayerMediaPeriodEventTime, i2, (Object) positionInfo, (Object) positionInfo2, 3));
    }

    private AnalyticsListener.EventTime generateEventTime(MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline;
        Assertions.checkNotNull(this.player);
        if (mediaPeriodId == null) {
            timeline = null;
        } else {
            timeline = this.mediaPeriodQueueTracker.getMediaPeriodIdTimeline(mediaPeriodId);
        }
        if (mediaPeriodId != null && timeline != null) {
            return generateEventTime(timeline, timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, mediaPeriodId);
        }
        int currentMediaItemIndex = this.player.getCurrentMediaItemIndex();
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (currentMediaItemIndex >= currentTimeline.getWindowCount()) {
            currentTimeline = Timeline.EMPTY;
        }
        return generateEventTime(currentTimeline, currentMediaItemIndex, (MediaSource.MediaPeriodId) null);
    }

    public void onLoadingChanged(boolean z) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(AnalyticsListener analyticsListener, FlagSet flagSet) {
    }

    public void onEvents(Player player2, Player.Events events) {
    }
}
