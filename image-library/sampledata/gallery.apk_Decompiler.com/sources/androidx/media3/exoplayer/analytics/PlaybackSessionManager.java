package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PlaybackSessionManager {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2);

        void onSessionActive(AnalyticsListener.EventTime eventTime, String str);

        void onSessionCreated(AnalyticsListener.EventTime eventTime, String str);

        void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z);
    }

    void finishAllSessions(AnalyticsListener.EventTime eventTime);

    String getActiveSessionId();

    String getSessionForMediaPeriodId(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId);

    void setListener(Listener listener);

    void updateSessions(AnalyticsListener.EventTime eventTime);

    void updateSessionsWithDiscontinuity(AnalyticsListener.EventTime eventTime, int i2);

    void updateSessionsWithTimelineChange(AnalyticsListener.EventTime eventTime);
}
