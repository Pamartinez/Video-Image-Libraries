package androidx.media3.exoplayer.analytics;

import android.media.metrics.LogSessionId;
import android.os.Build;
import androidx.media3.common.util.Assertions;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlayerId {
    public static final PlayerId UNSET = new PlayerId("");
    private final Object equalityToken;
    private final LogSessionIdApi31 logSessionIdApi31;
    public final String name;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LogSessionIdApi31 {
        public LogSessionId logSessionId = LogSessionId.LOG_SESSION_ID_NONE;

        public void setLogSessionId(LogSessionId logSessionId2) {
            LogSessionId logSessionId3 = this.logSessionId;
            LogSessionId unused = LogSessionId.LOG_SESSION_ID_NONE;
            Assertions.checkState(logSessionId3.equals(LogSessionId.LOG_SESSION_ID_NONE));
            this.logSessionId = logSessionId2;
        }
    }

    public PlayerId(String str) {
        LogSessionIdApi31 logSessionIdApi312;
        this.name = str;
        if (Build.VERSION.SDK_INT >= 31) {
            logSessionIdApi312 = new LogSessionIdApi31();
        } else {
            logSessionIdApi312 = null;
        }
        this.logSessionIdApi31 = logSessionIdApi312;
        this.equalityToken = new Object();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerId)) {
            return false;
        }
        PlayerId playerId = (PlayerId) obj;
        if (!Objects.equals(this.name, playerId.name) || !Objects.equals(this.logSessionIdApi31, playerId.logSessionIdApi31) || !Objects.equals(this.equalityToken, playerId.equalityToken)) {
            return false;
        }
        return true;
    }

    public synchronized LogSessionId getLogSessionId() {
        return ((LogSessionIdApi31) Assertions.checkNotNull(this.logSessionIdApi31)).logSessionId;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.logSessionIdApi31, this.equalityToken});
    }

    public synchronized void setLogSessionId(LogSessionId logSessionId) {
        ((LogSessionIdApi31) Assertions.checkNotNull(this.logSessionIdApi31)).setLogSessionId(logSessionId);
    }
}
