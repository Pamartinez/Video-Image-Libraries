package androidx.media3.exoplayer.analytics;

import E2.r;
import E2.t;
import android.util.Base64;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlaybackSessionManager;
import androidx.media3.exoplayer.source.MediaSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultPlaybackSessionManager implements PlaybackSessionManager {
    public static final r DEFAULT_SESSION_ID_GENERATOR = new t(3);
    private static final Random RANDOM = new Random();
    private String currentSessionId;
    private Timeline currentTimeline;
    private long lastRemovedCurrentWindowSequenceNumber;
    private PlaybackSessionManager.Listener listener;
    /* access modifiers changed from: private */
    public final Timeline.Period period;
    private final r sessionIdGenerator;
    private final HashMap<String, SessionDescriptor> sessions;
    /* access modifiers changed from: private */
    public final Timeline.Window window;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class SessionDescriptor {
        /* access modifiers changed from: private */
        public MediaSource.MediaPeriodId adMediaPeriodId;
        /* access modifiers changed from: private */
        public boolean isActive;
        /* access modifiers changed from: private */
        public boolean isCreated;
        /* access modifiers changed from: private */
        public final String sessionId;
        /* access modifiers changed from: private */
        public int windowIndex;
        /* access modifiers changed from: private */
        public long windowSequenceNumber;

        public SessionDescriptor(String str, int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            long j2;
            this.sessionId = str;
            this.windowIndex = i2;
            if (mediaPeriodId == null) {
                j2 = -1;
            } else {
                j2 = mediaPeriodId.windowSequenceNumber;
            }
            this.windowSequenceNumber = j2;
            if (mediaPeriodId != null && mediaPeriodId.isAd()) {
                this.adMediaPeriodId = mediaPeriodId;
            }
        }

        private int resolveWindowIndexToNewTimeline(Timeline timeline, Timeline timeline2, int i2) {
            if (i2 < timeline.getWindowCount()) {
                timeline.getWindow(i2, DefaultPlaybackSessionManager.this.window);
                for (int i7 = DefaultPlaybackSessionManager.this.window.firstPeriodIndex; i7 <= DefaultPlaybackSessionManager.this.window.lastPeriodIndex; i7++) {
                    int indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i7));
                    if (indexOfPeriod != -1) {
                        return timeline2.getPeriod(indexOfPeriod, DefaultPlaybackSessionManager.this.period).windowIndex;
                    }
                }
                return -1;
            } else if (i2 < timeline2.getWindowCount()) {
                return i2;
            } else {
                return -1;
            }
        }

        public boolean belongsToSession(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            if (mediaPeriodId != null) {
                MediaSource.MediaPeriodId mediaPeriodId2 = this.adMediaPeriodId;
                if (mediaPeriodId2 == null) {
                    if (mediaPeriodId.isAd() || mediaPeriodId.windowSequenceNumber != this.windowSequenceNumber) {
                        return false;
                    }
                    return true;
                } else if (mediaPeriodId.windowSequenceNumber == mediaPeriodId2.windowSequenceNumber && mediaPeriodId.adGroupIndex == mediaPeriodId2.adGroupIndex && mediaPeriodId.adIndexInAdGroup == mediaPeriodId2.adIndexInAdGroup) {
                    return true;
                } else {
                    return false;
                }
            } else if (i2 == this.windowIndex) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isFinishedAtEventTime(AnalyticsListener.EventTime eventTime) {
            MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
            if (mediaPeriodId != null) {
                long j2 = this.windowSequenceNumber;
                if (j2 == -1) {
                    return false;
                }
                if (mediaPeriodId.windowSequenceNumber > j2) {
                    return true;
                }
                if (this.adMediaPeriodId == null) {
                    return false;
                }
                int indexOfPeriod = eventTime.timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
                int indexOfPeriod2 = eventTime.timeline.getIndexOfPeriod(this.adMediaPeriodId.periodUid);
                MediaSource.MediaPeriodId mediaPeriodId2 = eventTime.mediaPeriodId;
                if (mediaPeriodId2.windowSequenceNumber < this.adMediaPeriodId.windowSequenceNumber || indexOfPeriod < indexOfPeriod2) {
                    return false;
                }
                if (indexOfPeriod > indexOfPeriod2) {
                    return true;
                }
                if (mediaPeriodId2.isAd()) {
                    MediaSource.MediaPeriodId mediaPeriodId3 = eventTime.mediaPeriodId;
                    int i2 = mediaPeriodId3.adGroupIndex;
                    int i7 = mediaPeriodId3.adIndexInAdGroup;
                    MediaSource.MediaPeriodId mediaPeriodId4 = this.adMediaPeriodId;
                    int i8 = mediaPeriodId4.adGroupIndex;
                    if (i2 > i8 || (i2 == i8 && i7 > mediaPeriodId4.adIndexInAdGroup)) {
                        return true;
                    }
                    return false;
                }
                int i10 = eventTime.mediaPeriodId.nextAdGroupIndex;
                if (i10 == -1 || i10 > this.adMediaPeriodId.adGroupIndex) {
                    return true;
                }
                return false;
            } else if (this.windowIndex != eventTime.windowIndex) {
                return true;
            } else {
                return false;
            }
        }

        public void maybeSetWindowSequenceNumber(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            if (this.windowSequenceNumber == -1 && i2 == this.windowIndex && mediaPeriodId != null && mediaPeriodId.windowSequenceNumber >= DefaultPlaybackSessionManager.this.getMinWindowSequenceNumber()) {
                this.windowSequenceNumber = mediaPeriodId.windowSequenceNumber;
            }
        }

        public boolean tryResolvingToNewTimeline(Timeline timeline, Timeline timeline2) {
            int resolveWindowIndexToNewTimeline = resolveWindowIndexToNewTimeline(timeline, timeline2, this.windowIndex);
            this.windowIndex = resolveWindowIndexToNewTimeline;
            if (resolveWindowIndexToNewTimeline == -1) {
                return false;
            }
            MediaSource.MediaPeriodId mediaPeriodId = this.adMediaPeriodId;
            if (mediaPeriodId != null && timeline2.getIndexOfPeriod(mediaPeriodId.periodUid) == -1) {
                return false;
            }
            return true;
        }
    }

    public DefaultPlaybackSessionManager() {
        this(DEFAULT_SESSION_ID_GENERATOR);
    }

    private void clearCurrentSession(SessionDescriptor sessionDescriptor) {
        if (sessionDescriptor.windowSequenceNumber != -1) {
            this.lastRemovedCurrentWindowSequenceNumber = sessionDescriptor.windowSequenceNumber;
        }
        this.currentSessionId = null;
    }

    /* access modifiers changed from: private */
    public static String generateDefaultSessionId() {
        byte[] bArr = new byte[12];
        RANDOM.nextBytes(bArr);
        return Base64.encodeToString(bArr, 10);
    }

    /* access modifiers changed from: private */
    public long getMinWindowSequenceNumber() {
        SessionDescriptor sessionDescriptor = this.sessions.get(this.currentSessionId);
        if (sessionDescriptor == null || sessionDescriptor.windowSequenceNumber == -1) {
            return this.lastRemovedCurrentWindowSequenceNumber + 1;
        }
        return sessionDescriptor.windowSequenceNumber;
    }

    private SessionDescriptor getOrAddSession(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
        int i7;
        SessionDescriptor sessionDescriptor = null;
        long j2 = Long.MAX_VALUE;
        for (SessionDescriptor next : this.sessions.values()) {
            next.maybeSetWindowSequenceNumber(i2, mediaPeriodId);
            if (next.belongsToSession(i2, mediaPeriodId)) {
                long access$100 = next.windowSequenceNumber;
                if (access$100 == -1 || access$100 < j2) {
                    sessionDescriptor = next;
                    j2 = access$100;
                } else if (!(i7 != 0 || ((SessionDescriptor) Util.castNonNull(sessionDescriptor)).adMediaPeriodId == null || next.adMediaPeriodId == null)) {
                    sessionDescriptor = next;
                }
            }
        }
        if (sessionDescriptor != null) {
            return sessionDescriptor;
        }
        String str = (String) this.sessionIdGenerator.get();
        SessionDescriptor sessionDescriptor2 = new SessionDescriptor(str, i2, mediaPeriodId);
        this.sessions.put(str, sessionDescriptor2);
        return sessionDescriptor2;
    }

    private void updateCurrentSession(AnalyticsListener.EventTime eventTime) {
        if (eventTime.timeline.isEmpty()) {
            String str = this.currentSessionId;
            if (str != null) {
                clearCurrentSession((SessionDescriptor) Assertions.checkNotNull(this.sessions.get(str)));
                return;
            }
            return;
        }
        SessionDescriptor sessionDescriptor = this.sessions.get(this.currentSessionId);
        SessionDescriptor orAddSession = getOrAddSession(eventTime.windowIndex, eventTime.mediaPeriodId);
        this.currentSessionId = orAddSession.sessionId;
        updateSessions(eventTime);
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId != null && mediaPeriodId.isAd()) {
            if (sessionDescriptor == null || sessionDescriptor.windowSequenceNumber != eventTime.mediaPeriodId.windowSequenceNumber || sessionDescriptor.adMediaPeriodId == null || sessionDescriptor.adMediaPeriodId.adGroupIndex != eventTime.mediaPeriodId.adGroupIndex || sessionDescriptor.adMediaPeriodId.adIndexInAdGroup != eventTime.mediaPeriodId.adIndexInAdGroup) {
                MediaSource.MediaPeriodId mediaPeriodId2 = eventTime.mediaPeriodId;
                this.listener.onAdPlaybackStarted(eventTime, getOrAddSession(eventTime.windowIndex, new MediaSource.MediaPeriodId(mediaPeriodId2.periodUid, mediaPeriodId2.windowSequenceNumber)).sessionId, orAddSession.sessionId);
            }
        }
    }

    public synchronized void finishAllSessions(AnalyticsListener.EventTime eventTime) {
        PlaybackSessionManager.Listener listener2;
        try {
            String str = this.currentSessionId;
            if (str != null) {
                clearCurrentSession((SessionDescriptor) Assertions.checkNotNull(this.sessions.get(str)));
            }
            Iterator<SessionDescriptor> it = this.sessions.values().iterator();
            while (it.hasNext()) {
                SessionDescriptor next = it.next();
                it.remove();
                if (next.isCreated && (listener2 = this.listener) != null) {
                    listener2.onSessionFinished(eventTime, next.sessionId, false);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized String getActiveSessionId() {
        return this.currentSessionId;
    }

    public synchronized String getSessionForMediaPeriodId(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        return getOrAddSession(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, mediaPeriodId).sessionId;
    }

    public void setListener(PlaybackSessionManager.Listener listener2) {
        this.listener = listener2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0103, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void updateSessions(androidx.media3.exoplayer.analytics.AnalyticsListener.EventTime r22) {
        /*
            r21 = this;
            r1 = r21
            r0 = r22
            monitor-enter(r21)
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x0044 }
            androidx.media3.common.util.Assertions.checkNotNull(r2)     // Catch:{ all -> 0x0044 }
            androidx.media3.common.Timeline r2 = r0.timeline     // Catch:{ all -> 0x0044 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r21)
            return
        L_0x0014:
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r0.mediaPeriodId     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047
            long r2 = r2.windowSequenceNumber     // Catch:{ all -> 0x0044 }
            long r4 = r1.getMinWindowSequenceNumber()     // Catch:{ all -> 0x0044 }
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0024
            monitor-exit(r21)
            return
        L_0x0024:
            java.util.HashMap<java.lang.String, androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor> r2 = r1.sessions     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x0044 }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor r2 = (androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager.SessionDescriptor) r2     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047
            long r3 = r2.windowSequenceNumber     // Catch:{ all -> 0x0044 }
            r5 = -1
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0047
            int r2 = r2.windowIndex     // Catch:{ all -> 0x0044 }
            int r3 = r0.windowIndex     // Catch:{ all -> 0x0044 }
            if (r2 == r3) goto L_0x0047
            monitor-exit(r21)
            return
        L_0x0044:
            r0 = move-exception
            goto L_0x0104
        L_0x0047:
            int r2 = r0.windowIndex     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor r2 = r1.getOrAddSession(r2, r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x0059
            java.lang.String r3 = r2.sessionId     // Catch:{ all -> 0x0044 }
            r1.currentSessionId = r3     // Catch:{ all -> 0x0044 }
        L_0x0059:
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0044 }
            r4 = 1
            if (r3 == 0) goto L_0x00d0
            boolean r3 = r3.isAd()     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x00d0
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r10 = new androidx.media3.exoplayer.source.MediaSource$MediaPeriodId     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0044 }
            java.lang.Object r5 = r3.periodUid     // Catch:{ all -> 0x0044 }
            long r6 = r3.windowSequenceNumber     // Catch:{ all -> 0x0044 }
            int r3 = r3.adGroupIndex     // Catch:{ all -> 0x0044 }
            r10.<init>(r5, r6, r3)     // Catch:{ all -> 0x0044 }
            int r3 = r0.windowIndex     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor r3 = r1.getOrAddSession(r3, r10)     // Catch:{ all -> 0x0044 }
            boolean r5 = r3.isCreated     // Catch:{ all -> 0x0044 }
            if (r5 != 0) goto L_0x00d0
            boolean unused = r3.isCreated = r4     // Catch:{ all -> 0x0044 }
            androidx.media3.common.Timeline r5 = r0.timeline     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r6 = r0.mediaPeriodId     // Catch:{ all -> 0x0044 }
            java.lang.Object r6 = r6.periodUid     // Catch:{ all -> 0x0044 }
            androidx.media3.common.Timeline$Period r7 = r1.period     // Catch:{ all -> 0x0044 }
            r5.getPeriodByUid(r6, r7)     // Catch:{ all -> 0x0044 }
            androidx.media3.common.Timeline$Period r5 = r1.period     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r6 = r0.mediaPeriodId     // Catch:{ all -> 0x0044 }
            int r6 = r6.adGroupIndex     // Catch:{ all -> 0x0044 }
            long r5 = r5.getAdGroupTimeUs(r6)     // Catch:{ all -> 0x0044 }
            long r5 = androidx.media3.common.util.Util.usToMs(r5)     // Catch:{ all -> 0x0044 }
            androidx.media3.common.Timeline$Period r7 = r1.period     // Catch:{ all -> 0x0044 }
            long r7 = r7.getPositionInWindowMs()     // Catch:{ all -> 0x0044 }
            long r5 = r5 + r7
            r7 = 0
            long r11 = java.lang.Math.max(r7, r5)     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.analytics.AnalyticsListener$EventTime r5 = new androidx.media3.exoplayer.analytics.AnalyticsListener$EventTime     // Catch:{ all -> 0x0044 }
            long r6 = r0.realtimeMs     // Catch:{ all -> 0x0044 }
            androidx.media3.common.Timeline r8 = r0.timeline     // Catch:{ all -> 0x0044 }
            int r9 = r0.windowIndex     // Catch:{ all -> 0x0044 }
            androidx.media3.common.Timeline r13 = r0.currentTimeline     // Catch:{ all -> 0x0044 }
            int r14 = r0.currentWindowIndex     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r15 = r0.currentMediaPeriodId     // Catch:{ all -> 0x0044 }
            r16 = r5
            long r4 = r0.currentPlaybackPositionMs     // Catch:{ all -> 0x0044 }
            r20 = r3
            r17 = r4
            long r3 = r0.totalBufferedDurationMs     // Catch:{ all -> 0x0044 }
            r5 = r16
            r16 = r17
            r18 = r3
            r5.<init>(r6, r8, r9, r10, r11, r13, r14, r15, r16, r18)     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r3 = r1.listener     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = r20.sessionId     // Catch:{ all -> 0x0044 }
            r3.onSessionCreated(r5, r4)     // Catch:{ all -> 0x0044 }
        L_0x00d0:
            boolean r3 = r2.isCreated     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x00e3
            r3 = 1
            boolean unused = r2.isCreated = r3     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r3 = r1.listener     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = r2.sessionId     // Catch:{ all -> 0x0044 }
            r3.onSessionCreated(r0, r4)     // Catch:{ all -> 0x0044 }
        L_0x00e3:
            java.lang.String r3 = r2.sessionId     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = r1.currentSessionId     // Catch:{ all -> 0x0044 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x0102
            boolean r3 = r2.isActive     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x0102
            r3 = 1
            boolean unused = r2.isActive = r3     // Catch:{ all -> 0x0044 }
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r3 = r1.listener     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = r2.sessionId     // Catch:{ all -> 0x0044 }
            r3.onSessionActive(r0, r2)     // Catch:{ all -> 0x0044 }
        L_0x0102:
            monitor-exit(r21)
            return
        L_0x0104:
            monitor-exit(r21)     // Catch:{ all -> 0x0044 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager.updateSessions(androidx.media3.exoplayer.analytics.AnalyticsListener$EventTime):void");
    }

    public synchronized void updateSessionsWithDiscontinuity(AnalyticsListener.EventTime eventTime, int i2) {
        boolean z;
        boolean z3;
        try {
            Assertions.checkNotNull(this.listener);
            if (i2 == 0) {
                z = true;
            } else {
                z = false;
            }
            Iterator<SessionDescriptor> it = this.sessions.values().iterator();
            while (it.hasNext()) {
                SessionDescriptor next = it.next();
                if (next.isFinishedAtEventTime(eventTime)) {
                    it.remove();
                    if (next.isCreated) {
                        boolean equals = next.sessionId.equals(this.currentSessionId);
                        if (!z || !equals || !next.isActive) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                        if (equals) {
                            clearCurrentSession(next);
                        }
                        this.listener.onSessionFinished(eventTime, next.sessionId, z3);
                    }
                }
            }
            updateCurrentSession(eventTime);
        } finally {
            while (true) {
            }
        }
    }

    public synchronized void updateSessionsWithTimelineChange(AnalyticsListener.EventTime eventTime) {
        try {
            Assertions.checkNotNull(this.listener);
            Timeline timeline = this.currentTimeline;
            this.currentTimeline = eventTime.timeline;
            Iterator<SessionDescriptor> it = this.sessions.values().iterator();
            while (it.hasNext()) {
                SessionDescriptor next = it.next();
                if (next.tryResolvingToNewTimeline(timeline, this.currentTimeline)) {
                    if (next.isFinishedAtEventTime(eventTime)) {
                    }
                }
                it.remove();
                if (next.isCreated) {
                    if (next.sessionId.equals(this.currentSessionId)) {
                        clearCurrentSession(next);
                    }
                    this.listener.onSessionFinished(eventTime, next.sessionId, false);
                }
            }
            updateCurrentSession(eventTime);
        } finally {
            while (true) {
            }
        }
    }

    public DefaultPlaybackSessionManager(r rVar) {
        this.sessionIdGenerator = rVar;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.sessions = new HashMap<>();
        this.currentTimeline = Timeline.EMPTY;
        this.lastRemovedCurrentWindowSequenceNumber = -1;
    }
}
