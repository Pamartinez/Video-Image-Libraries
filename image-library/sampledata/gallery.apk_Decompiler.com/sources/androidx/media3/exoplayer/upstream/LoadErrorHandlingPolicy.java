package androidx.media3.exoplayer.upstream;

import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface LoadErrorHandlingPolicy {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LoadErrorInfo {
        public final int errorCount;
        public final IOException exception;
        public final LoadEventInfo loadEventInfo;
        public final MediaLoadData mediaLoadData;

        public LoadErrorInfo(LoadEventInfo loadEventInfo2, MediaLoadData mediaLoadData2, IOException iOException, int i2) {
            this.loadEventInfo = loadEventInfo2;
            this.mediaLoadData = mediaLoadData2;
            this.exception = iOException;
            this.errorCount = i2;
        }
    }

    int getMinimumLoadableRetryCount(int i2);

    long getRetryDelayMsFor(LoadErrorInfo loadErrorInfo);

    void onLoadTaskConcluded(long j2) {
    }
}
