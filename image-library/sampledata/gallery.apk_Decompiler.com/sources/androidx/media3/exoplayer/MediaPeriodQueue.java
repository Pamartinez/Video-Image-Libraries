package androidx.media3.exoplayer;

import F2.Q;
import F2.U;
import android.util.Pair;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.MediaPeriodHolder;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MediaPeriodQueue {
    private final AnalyticsCollector analyticsCollector;
    private final HandlerWrapper analyticsCollectorHandler;
    private int length;
    private MediaPeriodHolder loading;
    private final MediaPeriodHolder.Factory mediaPeriodHolderFactory;
    private long nextWindowSequenceNumber;
    private Object oldFrontPeriodUid;
    private long oldFrontPeriodWindowSequenceNumber;
    private final Timeline.Period period = new Timeline.Period();
    private MediaPeriodHolder playing;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private List<MediaPeriodHolder> preloadPriorityList = new ArrayList();
    private MediaPeriodHolder preloading;
    private MediaPeriodHolder prewarming;
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private final Timeline.Window window = new Timeline.Window();

    public MediaPeriodQueue(AnalyticsCollector analyticsCollector2, HandlerWrapper handlerWrapper, MediaPeriodHolder.Factory factory, ExoPlayer.PreloadConfiguration preloadConfiguration2) {
        this.analyticsCollector = analyticsCollector2;
        this.analyticsCollectorHandler = handlerWrapper;
        this.mediaPeriodHolderFactory = factory;
        this.preloadConfiguration = preloadConfiguration2;
    }

    public static boolean areDurationsCompatible(long j2, long j3) {
        if (j2 == -9223372036854775807L || j2 == j3) {
            return true;
        }
        return false;
    }

    private boolean canKeepMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo, MediaPeriodInfo mediaPeriodInfo2) {
        if (mediaPeriodInfo.startPositionUs != mediaPeriodInfo2.startPositionUs || !mediaPeriodInfo.id.equals(mediaPeriodInfo2.id)) {
            return false;
        }
        return true;
    }

    private Pair<Object, Long> getDefaultPeriodPositionOfNextWindow(Timeline timeline, Object obj, long j2) {
        int nextWindowIndex = timeline.getNextWindowIndex(timeline.getPeriodByUid(obj, this.period).windowIndex, this.repeatMode, this.shuffleModeEnabled);
        if (nextWindowIndex == -1) {
            return null;
        }
        return timeline.getPeriodPositionUs(this.window, this.period, nextWindowIndex, -9223372036854775807L, j2);
    }

    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.timeline, playbackInfo.periodId, playbackInfo.requestedContentPositionUs, playbackInfo.positionUs);
    }

    private MediaPeriodInfo getFirstMediaPeriodInfoOfNextPeriod(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j2) {
        long j3;
        Object obj;
        Timeline timeline2 = timeline;
        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder2.info;
        int nextPeriodIndex = timeline2.getNextPeriodIndex(timeline2.getIndexOfPeriod(mediaPeriodInfo.id.periodUid), this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
        if (nextPeriodIndex == -1) {
            return null;
        }
        int i2 = timeline2.getPeriod(nextPeriodIndex, this.period, true).windowIndex;
        Object checkNotNull = Assertions.checkNotNull(this.period.uid);
        long j8 = mediaPeriodInfo.id.windowSequenceNumber;
        long j10 = 0;
        if (timeline2.getWindow(i2, this.window).firstPeriodIndex == nextPeriodIndex) {
            Pair<Object, Long> periodPositionUs = timeline2.getPeriodPositionUs(this.window, this.period, i2, -9223372036854775807L, Math.max(0, j2));
            if (periodPositionUs == null) {
                return null;
            }
            Object obj2 = periodPositionUs.first;
            long longValue = ((Long) periodPositionUs.second).longValue();
            MediaPeriodHolder next = mediaPeriodHolder2.getNext();
            if (next == null || !next.uid.equals(obj2)) {
                long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(obj2);
                if (resolvePeriodUidToWindowSequenceNumberInPreloadPeriods == -1) {
                    resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = this.nextWindowSequenceNumber;
                    this.nextWindowSequenceNumber = 1 + resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
                }
                j8 = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
            } else {
                j8 = next.info.id.windowSequenceNumber;
            }
            obj = obj2;
            j3 = longValue;
            j10 = -9223372036854775807L;
        } else {
            obj = checkNotNull;
            j3 = 0;
        }
        Timeline timeline3 = timeline;
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAds = resolveMediaPeriodIdForAds(timeline3, obj, j3, j8, this.window, this.period);
        if (!(j10 == -9223372036854775807L || mediaPeriodInfo.requestedContentPositionUs == -9223372036854775807L)) {
            boolean hasServerSideInsertedAds = hasServerSideInsertedAds(mediaPeriodInfo.id.periodUid, timeline3);
            if (resolveMediaPeriodIdForAds.isAd() && hasServerSideInsertedAds) {
                j10 = mediaPeriodInfo.requestedContentPositionUs;
            } else if (hasServerSideInsertedAds) {
                j3 = mediaPeriodInfo.requestedContentPositionUs;
            }
        }
        return getMediaPeriodInfo(timeline3, resolveMediaPeriodIdForAds, j10, j3);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfo(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j2) {
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        long rendererOffset = (mediaPeriodHolder.getRendererOffset() + mediaPeriodInfo.durationUs) - j2;
        if (mediaPeriodInfo.isLastInTimelinePeriod) {
            return getFirstMediaPeriodInfoOfNextPeriod(timeline, mediaPeriodHolder, rendererOffset);
        }
        return getFollowingMediaPeriodInfoOfCurrentPeriod(timeline, mediaPeriodHolder, rendererOffset);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfoOfCurrentPeriod(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j2) {
        boolean z;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.id;
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        boolean z3 = mediaPeriodInfo.isFollowedByTransitionToSameStream;
        if (mediaPeriodId.isAd()) {
            boolean z7 = z3;
            int i2 = mediaPeriodId.adGroupIndex;
            int adCountInAdGroup = this.period.getAdCountInAdGroup(i2);
            if (adCountInAdGroup == -1) {
                return null;
            }
            int nextAdIndexToPlay = this.period.getNextAdIndexToPlay(i2, mediaPeriodId.adIndexInAdGroup);
            if (nextAdIndexToPlay < adCountInAdGroup) {
                return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, i2, nextAdIndexToPlay, mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber, z7);
            }
            boolean z9 = z7;
            long j3 = mediaPeriodInfo.requestedContentPositionUs;
            if (j3 == -9223372036854775807L) {
                Timeline.Window window2 = this.window;
                Timeline.Period period2 = this.period;
                Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window2, period2, period2.windowIndex, -9223372036854775807L, Math.max(0, j2));
                if (periodPositionUs == null) {
                    return null;
                }
                j3 = ((Long) periodPositionUs.second).longValue();
            }
            return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, Math.max(getMinStartPositionAfterAdGroupUs(timeline, mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex), j3), mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber, z9);
        }
        long j8 = j2;
        boolean z10 = z3;
        int i7 = mediaPeriodId.nextAdGroupIndex;
        if (i7 != -1 && this.period.isLivePostrollPlaceholder(i7)) {
            return getFirstMediaPeriodInfoOfNextPeriod(timeline, mediaPeriodHolder, j2);
        }
        int firstAdIndexToPlay = this.period.getFirstAdIndexToPlay(mediaPeriodId.nextAdGroupIndex);
        if (!this.period.isServerSideInsertedAdGroup(mediaPeriodId.nextAdGroupIndex) || this.period.getAdState(mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay) != 3) {
            z = false;
        } else {
            z = true;
        }
        if (firstAdIndexToPlay == this.period.getAdCountInAdGroup(mediaPeriodId.nextAdGroupIndex) || z) {
            return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, getMinStartPositionAfterAdGroupUs(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex), mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber, false);
        }
        return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay, mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber, z10);
    }

    private MediaPeriodInfo getMediaPeriodInfo(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3) {
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        if (mediaPeriodId.isAd()) {
            return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, j2, mediaPeriodId.windowSequenceNumber, false);
        }
        return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, j3, j2, mediaPeriodId.windowSequenceNumber, false);
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(Timeline timeline, Object obj, int i2, int i7, long j2, long j3, boolean z) {
        long j8;
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, i2, i7, j3);
        long adDurationUs = timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        if (i7 == this.period.getFirstAdIndexToPlay(i2)) {
            j8 = this.period.getAdResumePositionUs();
        } else {
            j8 = 0;
        }
        boolean isServerSideInsertedAdGroup = this.period.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex);
        if (adDurationUs != -9223372036854775807L && j8 >= adDurationUs) {
            j8 = Math.max(0, adDurationUs - 1);
        }
        return new MediaPeriodInfo(mediaPeriodId, j8, j2, -9223372036854775807L, adDurationUs, z, isServerSideInsertedAdGroup, false, false, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0094 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c4 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media3.exoplayer.MediaPeriodInfo getMediaPeriodInfoForContent(androidx.media3.common.Timeline r26, java.lang.Object r27, long r28, long r30, long r32, boolean r34) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            r3 = r28
            androidx.media3.common.Timeline$Period r5 = r0.period
            r1.getPeriodByUid(r2, r5)
            androidx.media3.common.Timeline$Period r5 = r0.period
            int r5 = r5.getAdGroupIndexAfterPositionUs(r3)
            r6 = 1
            r7 = 0
            r8 = -1
            if (r5 != r8) goto L_0x002e
            androidx.media3.common.Timeline$Period r9 = r0.period
            int r9 = r9.getAdGroupCount()
            if (r9 <= 0) goto L_0x004d
            androidx.media3.common.Timeline$Period r9 = r0.period
            int r10 = r9.getRemovedAdGroupCount()
            boolean r9 = r9.isServerSideInsertedAdGroup(r10)
            if (r9 == 0) goto L_0x004d
            r9 = r6
            goto L_0x004e
        L_0x002e:
            androidx.media3.common.Timeline$Period r9 = r0.period
            boolean r9 = r9.isServerSideInsertedAdGroup(r5)
            if (r9 == 0) goto L_0x004d
            androidx.media3.common.Timeline$Period r9 = r0.period
            long r9 = r9.getAdGroupTimeUs(r5)
            androidx.media3.common.Timeline$Period r11 = r0.period
            long r12 = r11.durationUs
            int r9 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x004d
            boolean r9 = r11.hasPlayedAdGroup(r5)
            if (r9 == 0) goto L_0x004d
            r9 = r6
            r5 = r8
            goto L_0x004e
        L_0x004d:
            r9 = r7
        L_0x004e:
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r11 = new androidx.media3.exoplayer.source.MediaSource$MediaPeriodId
            r12 = r32
            r11.<init>(r2, r12, r5)
            boolean r2 = r0.isLastInPeriod(r11)
            boolean r23 = r0.isLastInWindow(r1, r11)
            boolean r24 = r0.isLastInTimeline(r1, r11, r2)
            if (r5 == r8) goto L_0x0076
            androidx.media3.common.Timeline$Period r1 = r0.period
            boolean r1 = r1.isServerSideInsertedAdGroup(r5)
            if (r1 == 0) goto L_0x0076
            androidx.media3.common.Timeline$Period r1 = r0.period
            boolean r1 = r1.isLivePostrollPlaceholder(r5)
            if (r1 != 0) goto L_0x0076
            r21 = r6
            goto L_0x0078
        L_0x0076:
            r21 = r7
        L_0x0078:
            if (r5 == r8) goto L_0x008c
            androidx.media3.common.Timeline$Period r1 = r0.period
            boolean r1 = r1.isLivePostrollPlaceholder(r5)
            if (r1 == 0) goto L_0x008c
            androidx.media3.common.Timeline$Period r1 = r0.period
            boolean r1 = r1.isServerSideInsertedAdGroup(r5)
            if (r1 == 0) goto L_0x008c
            r1 = r6
            goto L_0x008d
        L_0x008c:
            r1 = r7
        L_0x008d:
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r5 == r8) goto L_0x009f
            if (r1 != 0) goto L_0x009f
            androidx.media3.common.Timeline$Period r1 = r0.period
            long r14 = r1.getAdGroupTimeUs(r5)
        L_0x009c:
            r16 = r14
            goto L_0x00a8
        L_0x009f:
            if (r9 == 0) goto L_0x00a6
            androidx.media3.common.Timeline$Period r1 = r0.period
            long r14 = r1.durationUs
            goto L_0x009c
        L_0x00a6:
            r16 = r12
        L_0x00a8:
            int r1 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r1 == 0) goto L_0x00b6
            r14 = -9223372036854775808
            int r1 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r1 != 0) goto L_0x00b3
            goto L_0x00b6
        L_0x00b3:
            r18 = r16
            goto L_0x00bc
        L_0x00b6:
            androidx.media3.common.Timeline$Period r0 = r0.period
            long r0 = r0.durationUs
            r18 = r0
        L_0x00bc:
            int r0 = (r18 > r12 ? 1 : (r18 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x00d5
            int r0 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r0 < 0) goto L_0x00d5
            if (r24 != 0) goto L_0x00ca
            if (r9 != 0) goto L_0x00c9
            goto L_0x00ca
        L_0x00c9:
            r6 = r7
        L_0x00ca:
            long r0 = (long) r6
            long r0 = r18 - r0
            r3 = 0
            long r0 = java.lang.Math.max(r3, r0)
            r12 = r0
            goto L_0x00d6
        L_0x00d5:
            r12 = r3
        L_0x00d6:
            androidx.media3.exoplayer.MediaPeriodInfo r10 = new androidx.media3.exoplayer.MediaPeriodInfo
            r14 = r30
            r20 = r34
            r22 = r2
            r10.<init>(r11, r12, r14, r16, r18, r20, r21, r22, r23, r24)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.MediaPeriodQueue.getMediaPeriodInfoForContent(androidx.media3.common.Timeline, java.lang.Object, long, long, long, boolean):androidx.media3.exoplayer.MediaPeriodInfo");
    }

    private MediaPeriodInfo getMediaPeriodInfoForPeriodPosition(Timeline timeline, Object obj, long j2, long j3) {
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAds = resolveMediaPeriodIdForAds(timeline, obj, j2, j3, this.window, this.period);
        if (resolveMediaPeriodIdForAds.isAd()) {
            return getMediaPeriodInfoForAd(timeline, resolveMediaPeriodIdForAds.periodUid, resolveMediaPeriodIdForAds.adGroupIndex, resolveMediaPeriodIdForAds.adIndexInAdGroup, j2, resolveMediaPeriodIdForAds.windowSequenceNumber, false);
        }
        return getMediaPeriodInfoForContent(timeline, resolveMediaPeriodIdForAds.periodUid, j2, -9223372036854775807L, resolveMediaPeriodIdForAds.windowSequenceNumber, false);
    }

    private long getMinStartPositionAfterAdGroupUs(Timeline timeline, Object obj, int i2) {
        timeline.getPeriodByUid(obj, this.period);
        long adGroupTimeUs = this.period.getAdGroupTimeUs(i2);
        if (adGroupTimeUs == Long.MIN_VALUE) {
            return this.period.durationUs;
        }
        return this.period.getContentResumeOffsetUs(i2) + adGroupTimeUs;
    }

    private boolean hasServerSideInsertedAds(Object obj, Timeline timeline) {
        int adGroupCount = timeline.getPeriodByUid(obj, this.period).getAdGroupCount();
        int removedAdGroupCount = this.period.getRemovedAdGroupCount();
        if (adGroupCount <= 0 || !this.period.isServerSideInsertedAdGroup(removedAdGroupCount)) {
            return false;
        }
        if (adGroupCount > 1 || this.period.getAdGroupTimeUs(removedAdGroupCount) != Long.MIN_VALUE) {
            return true;
        }
        return false;
    }

    private boolean isLastInPeriod(MediaSource.MediaPeriodId mediaPeriodId) {
        if (mediaPeriodId.isAd() || mediaPeriodId.nextAdGroupIndex != -1) {
            return false;
        }
        return true;
    }

    private boolean isLastInTimeline(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, boolean z) {
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        if (timeline.getWindow(timeline.getPeriod(indexOfPeriod, this.period).windowIndex, this.window).isDynamic) {
            return false;
        }
        if (!timeline.isLastPeriod(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) || !z) {
            return false;
        }
        return true;
    }

    private boolean isLastInWindow(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (!isLastInPeriod(mediaPeriodId)) {
            return false;
        }
        int i2 = timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex;
        if (timeline.getWindow(i2, this.window).lastPeriodIndex == timeline.getIndexOfPeriod(mediaPeriodId.periodUid)) {
            return true;
        }
        return false;
    }

    private static boolean isSkippableAdPeriod(Timeline.Period period2) {
        int i2;
        int adGroupCount = period2.getAdGroupCount();
        if (adGroupCount != 0 && ((adGroupCount != 1 || !period2.isLivePostrollPlaceholder(0)) && period2.isServerSideInsertedAdGroup(period2.getRemovedAdGroupCount()))) {
            long j2 = 0;
            if (period2.getAdGroupIndexForPositionUs(0) == -1) {
                if (period2.durationUs == 0) {
                    return true;
                }
                if (period2.isLivePostrollPlaceholder(adGroupCount - 1)) {
                    i2 = 2;
                } else {
                    i2 = 1;
                }
                int i7 = adGroupCount - i2;
                for (int i8 = 0; i8 <= i7; i8++) {
                    j2 += period2.getContentResumeOffsetUs(i8);
                }
                if (period2.durationUs <= j2) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyQueueUpdate$0(Q q, MediaSource.MediaPeriodId mediaPeriodId) {
        this.analyticsCollector.updateMediaPeriodQueueInfo(q.f(), mediaPeriodId);
    }

    private void notifyQueueUpdate() {
        MediaSource.MediaPeriodId mediaPeriodId;
        Q x9 = U.x();
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.getNext()) {
            x9.a(mediaPeriodHolder.info.id);
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.reading;
        if (mediaPeriodHolder2 == null) {
            mediaPeriodId = null;
        } else {
            mediaPeriodId = mediaPeriodHolder2.info.id;
        }
        this.analyticsCollectorHandler.post(new r(this, x9, mediaPeriodId, 0));
    }

    private void releaseAndResetPreloadPriorityList(List<MediaPeriodHolder> list) {
        for (int i2 = 0; i2 < this.preloadPriorityList.size(); i2++) {
            this.preloadPriorityList.get(i2).release();
        }
        this.preloadPriorityList = list;
        this.preloading = null;
        maybeUpdatePreloadMediaPeriodHolder();
    }

    private MediaPeriodHolder removePreloadedMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo) {
        for (int i2 = 0; i2 < this.preloadPriorityList.size(); i2++) {
            if (this.preloadPriorityList.get(i2).canBeUsedForMediaPeriodInfo(mediaPeriodInfo)) {
                return this.preloadPriorityList.remove(i2);
            }
        }
        return null;
    }

    private static MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline timeline, Object obj, long j2, long j3, Timeline.Window window2, Timeline.Period period2) {
        timeline.getPeriodByUid(obj, period2);
        timeline.getWindow(period2.windowIndex, window2);
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        while (isSkippableAdPeriod(period2) && indexOfPeriod <= window2.lastPeriodIndex) {
            timeline.getPeriod(indexOfPeriod, period2, true);
            obj = Assertions.checkNotNull(period2.uid);
            indexOfPeriod++;
        }
        timeline.getPeriodByUid(obj, period2);
        long j8 = j2;
        int adGroupIndexForPositionUs = period2.getAdGroupIndexForPositionUs(j8);
        if (adGroupIndexForPositionUs == -1) {
            return new MediaSource.MediaPeriodId(obj, j3, period2.getAdGroupIndexAfterPositionUs(j8));
        }
        return new MediaSource.MediaPeriodId(obj, adGroupIndexForPositionUs, period2.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j3);
    }

    private long resolvePeriodUidToWindowSequenceNumber(Timeline timeline, Object obj) {
        int indexOfPeriod;
        int i2 = timeline.getPeriodByUid(obj, this.period).windowIndex;
        Object obj2 = this.oldFrontPeriodUid;
        if (obj2 != null && (indexOfPeriod = timeline.getIndexOfPeriod(obj2)) != -1 && timeline.getPeriod(indexOfPeriod, this.period).windowIndex == i2) {
            return this.oldFrontPeriodWindowSequenceNumber;
        }
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.getNext()) {
            if (mediaPeriodHolder.uid.equals(obj)) {
                return mediaPeriodHolder.info.id.windowSequenceNumber;
            }
        }
        for (MediaPeriodHolder mediaPeriodHolder2 = this.playing; mediaPeriodHolder2 != null; mediaPeriodHolder2 = mediaPeriodHolder2.getNext()) {
            int indexOfPeriod2 = timeline.getIndexOfPeriod(mediaPeriodHolder2.uid);
            if (indexOfPeriod2 != -1 && timeline.getPeriod(indexOfPeriod2, this.period).windowIndex == i2) {
                return mediaPeriodHolder2.info.id.windowSequenceNumber;
            }
        }
        long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(obj);
        if (resolvePeriodUidToWindowSequenceNumberInPreloadPeriods != -1) {
            return resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
        }
        long j2 = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + j2;
        if (this.playing == null) {
            this.oldFrontPeriodUid = obj;
            this.oldFrontPeriodWindowSequenceNumber = j2;
        }
        return j2;
    }

    private long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(Object obj) {
        for (int i2 = 0; i2 < this.preloadPriorityList.size(); i2++) {
            MediaPeriodHolder mediaPeriodHolder = this.preloadPriorityList.get(i2);
            if (mediaPeriodHolder.uid.equals(obj)) {
                return mediaPeriodHolder.info.id.windowSequenceNumber;
            }
        }
        return -1;
    }

    private int updateForPlaybackModeChange(Timeline timeline) {
        Timeline timeline2;
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder == null) {
            return 0;
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodHolder.uid);
        while (true) {
            timeline2 = timeline;
            indexOfPeriod = timeline2.getNextPeriodIndex(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while (((MediaPeriodHolder) Assertions.checkNotNull(mediaPeriodHolder)).getNext() != null && !mediaPeriodHolder.info.isLastInTimelinePeriod) {
                mediaPeriodHolder = mediaPeriodHolder.getNext();
            }
            MediaPeriodHolder next = mediaPeriodHolder.getNext();
            if (indexOfPeriod == -1 || next == null || timeline2.getIndexOfPeriod(next.uid) != indexOfPeriod) {
                int removeAfter = removeAfter(mediaPeriodHolder);
                mediaPeriodHolder.info = getUpdatedMediaPeriodInfo(timeline2, mediaPeriodHolder.info);
            } else {
                mediaPeriodHolder = next;
                timeline = timeline2;
            }
        }
        int removeAfter2 = removeAfter(mediaPeriodHolder);
        mediaPeriodHolder.info = getUpdatedMediaPeriodInfo(timeline2, mediaPeriodHolder.info);
        return removeAfter2;
    }

    public MediaPeriodHolder advancePlayingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder == null) {
            return null;
        }
        if (mediaPeriodHolder == this.reading) {
            this.reading = mediaPeriodHolder.getNext();
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.playing;
        if (mediaPeriodHolder2 == this.prewarming) {
            this.prewarming = mediaPeriodHolder2.getNext();
        }
        this.playing.release();
        int i2 = this.length - 1;
        this.length = i2;
        if (i2 == 0) {
            this.loading = null;
            MediaPeriodHolder mediaPeriodHolder3 = this.playing;
            this.oldFrontPeriodUid = mediaPeriodHolder3.uid;
            this.oldFrontPeriodWindowSequenceNumber = mediaPeriodHolder3.info.id.windowSequenceNumber;
        }
        this.playing = this.playing.getNext();
        notifyQueueUpdate();
        return this.playing;
    }

    public MediaPeriodHolder advancePrewarmingPeriod() {
        this.prewarming = ((MediaPeriodHolder) Assertions.checkStateNotNull(this.prewarming)).getNext();
        notifyQueueUpdate();
        return (MediaPeriodHolder) Assertions.checkStateNotNull(this.prewarming);
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.prewarming;
        MediaPeriodHolder mediaPeriodHolder2 = this.reading;
        if (mediaPeriodHolder == mediaPeriodHolder2) {
            this.prewarming = ((MediaPeriodHolder) Assertions.checkStateNotNull(mediaPeriodHolder2)).getNext();
        }
        this.reading = ((MediaPeriodHolder) Assertions.checkStateNotNull(this.reading)).getNext();
        notifyQueueUpdate();
        return (MediaPeriodHolder) Assertions.checkStateNotNull(this.reading);
    }

    public void clear() {
        if (this.length != 0) {
            MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkStateNotNull(this.playing);
            this.oldFrontPeriodUid = mediaPeriodHolder.uid;
            this.oldFrontPeriodWindowSequenceNumber = mediaPeriodHolder.info.id.windowSequenceNumber;
            while (mediaPeriodHolder != null) {
                mediaPeriodHolder.release();
                mediaPeriodHolder = mediaPeriodHolder.getNext();
            }
            this.playing = null;
            this.loading = null;
            this.reading = null;
            this.prewarming = null;
            this.length = 0;
            notifyQueueUpdate();
        }
    }

    public MediaPeriodHolder enqueueNextMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo) {
        long j2;
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder == null) {
            j2 = 1000000000000L;
        } else {
            j2 = (mediaPeriodHolder.getRendererOffset() + this.loading.info.durationUs) - mediaPeriodInfo.startPositionUs;
        }
        MediaPeriodHolder removePreloadedMediaPeriodHolder = removePreloadedMediaPeriodHolder(mediaPeriodInfo);
        if (removePreloadedMediaPeriodHolder == null) {
            removePreloadedMediaPeriodHolder = ((ExoPlayerImplInternal) ((h) this.mediaPeriodHolderFactory).e).createMediaPeriodHolder(mediaPeriodInfo, j2);
        } else {
            removePreloadedMediaPeriodHolder.info = mediaPeriodInfo;
            removePreloadedMediaPeriodHolder.setRendererOffset(j2);
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.loading;
        if (mediaPeriodHolder2 != null) {
            mediaPeriodHolder2.setNext(removePreloadedMediaPeriodHolder);
        } else {
            this.playing = removePreloadedMediaPeriodHolder;
            this.reading = removePreloadedMediaPeriodHolder;
            this.prewarming = removePreloadedMediaPeriodHolder;
        }
        this.oldFrontPeriodUid = null;
        this.loading = removePreloadedMediaPeriodHolder;
        this.length++;
        notifyQueueUpdate();
        return removePreloadedMediaPeriodHolder;
    }

    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    public MediaPeriodInfo getNextMediaPeriodInfo(long j2, PlaybackInfo playbackInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder == null) {
            return getFirstMediaPeriodInfo(playbackInfo);
        }
        return getFollowingMediaPeriodInfo(playbackInfo.timeline, mediaPeriodHolder, j2);
    }

    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    public MediaPeriodHolder getPreloadHolderByMediaPeriod(MediaPeriod mediaPeriod) {
        for (int i2 = 0; i2 < this.preloadPriorityList.size(); i2++) {
            MediaPeriodHolder mediaPeriodHolder = this.preloadPriorityList.get(i2);
            if (mediaPeriodHolder.mediaPeriod == mediaPeriod) {
                return mediaPeriodHolder;
            }
        }
        return null;
    }

    public MediaPeriodHolder getPreloadingPeriod() {
        return this.preloading;
    }

    public MediaPeriodHolder getPrewarmingPeriod() {
        return this.prewarming;
    }

    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    public MediaPeriodInfo getUpdatedMediaPeriodInfo(Timeline timeline, MediaPeriodInfo mediaPeriodInfo) {
        long j2;
        long j3;
        boolean z;
        int i2;
        Timeline timeline2 = timeline;
        MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodInfo;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo2.id;
        boolean isLastInPeriod = isLastInPeriod(mediaPeriodId);
        boolean isLastInWindow = isLastInWindow(timeline2, mediaPeriodId);
        boolean isLastInTimeline = isLastInTimeline(timeline2, mediaPeriodId, isLastInPeriod);
        timeline2.getPeriodByUid(mediaPeriodInfo2.id.periodUid, this.period);
        if (mediaPeriodId.isAd() || (i2 = mediaPeriodId.nextAdGroupIndex) == -1) {
            j2 = -9223372036854775807L;
        } else {
            j2 = this.period.getAdGroupTimeUs(i2);
        }
        if (mediaPeriodId.isAd()) {
            j3 = this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        } else if (j2 == -9223372036854775807L || j2 == Long.MIN_VALUE) {
            j3 = this.period.getDurationUs();
        } else {
            j3 = j2;
        }
        if (mediaPeriodId.isAd()) {
            z = this.period.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex);
        } else {
            int i7 = mediaPeriodId.nextAdGroupIndex;
            if (i7 == -1 || !this.period.isServerSideInsertedAdGroup(i7)) {
                z = false;
            } else {
                z = true;
            }
        }
        boolean z3 = z;
        long j8 = j3;
        return new MediaPeriodInfo(mediaPeriodId, mediaPeriodInfo2.startPositionUs, mediaPeriodInfo2.requestedContentPositionUs, j2, j8, mediaPeriodInfo2.isPrecededByTransitionFromSameStream, z3, isLastInPeriod, isLastInWindow, isLastInTimeline);
    }

    public void invalidatePreloadPool(Timeline timeline) {
        MediaPeriodHolder mediaPeriodHolder;
        MediaPeriodQueue mediaPeriodQueue;
        if (this.preloadConfiguration.targetPreloadDurationUs == -9223372036854775807L || (mediaPeriodHolder = this.loading) == null) {
            releasePreloadPool();
            return;
        }
        ArrayList arrayList = new ArrayList();
        Pair<Object, Long> defaultPeriodPositionOfNextWindow = getDefaultPeriodPositionOfNextWindow(timeline, mediaPeriodHolder.info.id.periodUid, 0);
        if (defaultPeriodPositionOfNextWindow == null || timeline.getWindow(timeline.getPeriodByUid(defaultPeriodPositionOfNextWindow.first, this.period).windowIndex, this.window).isLive()) {
            mediaPeriodQueue = this;
        } else {
            long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(defaultPeriodPositionOfNextWindow.first);
            if (resolvePeriodUidToWindowSequenceNumberInPreloadPeriods == -1) {
                resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = this.nextWindowSequenceNumber;
                this.nextWindowSequenceNumber = 1 + resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
            }
            mediaPeriodQueue = this;
            Timeline timeline2 = timeline;
            MediaPeriodInfo mediaPeriodInfoForPeriodPosition = mediaPeriodQueue.getMediaPeriodInfoForPeriodPosition(timeline2, defaultPeriodPositionOfNextWindow.first, ((Long) defaultPeriodPositionOfNextWindow.second).longValue(), resolvePeriodUidToWindowSequenceNumberInPreloadPeriods);
            MediaPeriodHolder removePreloadedMediaPeriodHolder = mediaPeriodQueue.removePreloadedMediaPeriodHolder(mediaPeriodInfoForPeriodPosition);
            if (removePreloadedMediaPeriodHolder == null) {
                removePreloadedMediaPeriodHolder = ((ExoPlayerImplInternal) ((h) mediaPeriodQueue.mediaPeriodHolderFactory).e).createMediaPeriodHolder(mediaPeriodInfoForPeriodPosition, (mediaPeriodHolder.getRendererOffset() + mediaPeriodHolder.info.durationUs) - mediaPeriodInfoForPeriodPosition.startPositionUs);
            }
            arrayList.add(removePreloadedMediaPeriodHolder);
        }
        mediaPeriodQueue.releaseAndResetPreloadPriorityList(arrayList);
    }

    public boolean isLoading(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder == null || mediaPeriodHolder.mediaPeriod != mediaPeriod) {
            return false;
        }
        return true;
    }

    public boolean isPreloading(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.preloading;
        if (mediaPeriodHolder == null || mediaPeriodHolder.mediaPeriod != mediaPeriod) {
            return false;
        }
        return true;
    }

    public void maybeUpdatePreloadMediaPeriodHolder() {
        MediaPeriodHolder mediaPeriodHolder = this.preloading;
        if (mediaPeriodHolder == null || mediaPeriodHolder.isFullyPreloaded()) {
            this.preloading = null;
            for (int i2 = 0; i2 < this.preloadPriorityList.size(); i2++) {
                MediaPeriodHolder mediaPeriodHolder2 = this.preloadPriorityList.get(i2);
                if (!mediaPeriodHolder2.isFullyPreloaded()) {
                    this.preloading = mediaPeriodHolder2;
                    return;
                }
            }
        }
    }

    public void reevaluateBuffer(long j2) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.reevaluateBuffer(j2);
        }
    }

    public void releasePreloadPool() {
        if (!this.preloadPriorityList.isEmpty()) {
            releaseAndResetPreloadPriorityList(new ArrayList());
        }
    }

    public int removeAfter(MediaPeriodHolder mediaPeriodHolder) {
        Assertions.checkStateNotNull(mediaPeriodHolder);
        int i2 = 0;
        if (mediaPeriodHolder.equals(this.loading)) {
            return 0;
        }
        this.loading = mediaPeriodHolder;
        while (mediaPeriodHolder.getNext() != null) {
            mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(mediaPeriodHolder.getNext());
            if (mediaPeriodHolder == this.reading) {
                MediaPeriodHolder mediaPeriodHolder2 = this.playing;
                this.reading = mediaPeriodHolder2;
                this.prewarming = mediaPeriodHolder2;
                i2 = 3;
            }
            if (mediaPeriodHolder == this.prewarming) {
                this.prewarming = this.reading;
                i2 |= 2;
            }
            mediaPeriodHolder.release();
            this.length--;
        }
        ((MediaPeriodHolder) Assertions.checkNotNull(this.loading)).setNext((MediaPeriodHolder) null);
        notifyQueueUpdate();
        return i2;
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange(Timeline timeline, Object obj, long j2) {
        long resolvePeriodUidToWindowSequenceNumber = resolvePeriodUidToWindowSequenceNumber(timeline, obj);
        timeline.getPeriodByUid(obj, this.period);
        timeline.getWindow(this.period.windowIndex, this.window);
        boolean z = false;
        for (int indexOfPeriod = timeline.getIndexOfPeriod(obj); indexOfPeriod >= this.window.firstPeriodIndex; indexOfPeriod--) {
            boolean z3 = true;
            timeline.getPeriod(indexOfPeriod, this.period, true);
            if (this.period.getAdGroupCount() <= 0) {
                z3 = false;
            }
            z |= z3;
            Timeline.Period period2 = this.period;
            if (period2.getAdGroupIndexForPositionUs(period2.durationUs) != -1) {
                obj = Assertions.checkNotNull(this.period.uid);
            }
            if (z && (!z3 || this.period.durationUs != 0)) {
                break;
            }
        }
        return resolveMediaPeriodIdForAds(timeline, obj, j2, resolvePeriodUidToWindowSequenceNumber, this.window, this.period);
    }

    public boolean shouldLoadNextMediaPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder == null) {
            return true;
        }
        if (mediaPeriodHolder.info.isFinal || !mediaPeriodHolder.isFullyBuffered() || this.loading.info.durationUs == -9223372036854775807L || this.length >= 100) {
            return false;
        }
        return true;
    }

    public void updatePreloadConfiguration(Timeline timeline, ExoPlayer.PreloadConfiguration preloadConfiguration2) {
        this.preloadConfiguration = preloadConfiguration2;
        invalidatePreloadPool(timeline);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a5 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int updateQueuedPeriods(androidx.media3.common.Timeline r18, long r19, long r21, long r23) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            androidx.media3.exoplayer.MediaPeriodHolder r2 = r0.playing
            r3 = 0
        L_0x0007:
            if (r2 == 0) goto L_0x00b6
            androidx.media3.exoplayer.MediaPeriodInfo r5 = r2.info
            if (r3 != 0) goto L_0x0014
            androidx.media3.exoplayer.MediaPeriodInfo r3 = r0.getUpdatedMediaPeriodInfo(r1, r5)
            r6 = r19
            goto L_0x0025
        L_0x0014:
            r6 = r19
            androidx.media3.exoplayer.MediaPeriodInfo r8 = r0.getFollowingMediaPeriodInfo(r1, r3, r6)
            if (r8 == 0) goto L_0x00b1
            boolean r9 = r0.canKeepMediaPeriodHolder(r5, r8)
            if (r9 != 0) goto L_0x0024
            goto L_0x00b1
        L_0x0024:
            r3 = r8
        L_0x0025:
            long r8 = r5.requestedContentPositionUs
            androidx.media3.exoplayer.MediaPeriodInfo r8 = r3.copyWithRequestedContentPositionUs(r8)
            r2.info = r8
            long r8 = r5.durationUs
            long r10 = r3.durationUs
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x00a6
            r2.updateClipping()
            long r6 = r3.durationUs
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 != 0) goto L_0x0049
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            goto L_0x004d
        L_0x0049:
            long r6 = r2.toRendererTime(r6)
        L_0x004d:
            androidx.media3.exoplayer.MediaPeriodHolder r1 = r0.reading
            r10 = 1
            r11 = -9223372036854775808
            if (r2 != r1) goto L_0x0064
            androidx.media3.exoplayer.MediaPeriodInfo r1 = r2.info
            boolean r1 = r1.isFollowedByTransitionToSameStream
            if (r1 != 0) goto L_0x0064
            int r1 = (r21 > r11 ? 1 : (r21 == r11 ? 0 : -1))
            if (r1 == 0) goto L_0x0062
            int r1 = (r21 > r6 ? 1 : (r21 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0064
        L_0x0062:
            r1 = r10
            goto L_0x0065
        L_0x0064:
            r1 = 0
        L_0x0065:
            androidx.media3.exoplayer.MediaPeriodHolder r13 = r0.prewarming
            if (r2 != r13) goto L_0x0073
            int r13 = (r23 > r11 ? 1 : (r23 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x0071
            int r6 = (r23 > r6 ? 1 : (r23 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x0073
        L_0x0071:
            r6 = r10
            goto L_0x0074
        L_0x0073:
            r6 = 0
        L_0x0074:
            int r0 = r0.removeAfter(r2)
            if (r0 == 0) goto L_0x007b
            return r0
        L_0x007b:
            long r13 = r5.durationUs
            int r0 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            r15 = 0
            if (r0 != 0) goto L_0x0094
            long r4 = r5.endPositionUs
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x0094
            long r2 = r3.endPositionUs
            int r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0094
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0094
            r0 = r10
            goto L_0x0095
        L_0x0094:
            r0 = r15
        L_0x0095:
            if (r1 == 0) goto L_0x009f
            int r1 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r1 != 0) goto L_0x009d
            if (r0 == 0) goto L_0x009f
        L_0x009d:
            r4 = r10
            goto L_0x00a0
        L_0x009f:
            r4 = r15
        L_0x00a0:
            if (r6 == 0) goto L_0x00a5
            r0 = r4 | 2
            return r0
        L_0x00a5:
            return r4
        L_0x00a6:
            androidx.media3.exoplayer.MediaPeriodHolder r3 = r2.getNext()
            r16 = r3
            r3 = r2
            r2 = r16
            goto L_0x0007
        L_0x00b1:
            int r0 = r0.removeAfter(r3)
            return r0
        L_0x00b6:
            r15 = 0
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.MediaPeriodQueue.updateQueuedPeriods(androidx.media3.common.Timeline, long, long, long):int");
    }

    public int updateRepeatMode(Timeline timeline, int i2) {
        this.repeatMode = i2;
        return updateForPlaybackModeChange(timeline);
    }

    public int updateShuffleModeEnabled(Timeline timeline, boolean z) {
        this.shuffleModeEnabled = z;
        return updateForPlaybackModeChange(timeline);
    }
}
