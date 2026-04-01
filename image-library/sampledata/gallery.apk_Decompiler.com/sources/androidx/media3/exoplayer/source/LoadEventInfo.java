package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.datasource.DataSpec;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LoadEventInfo {
    private static final AtomicLong idSource = new AtomicLong();
    public final long bytesLoaded;
    public final DataSpec dataSpec;
    public final long elapsedRealtimeMs;
    public final long loadDurationMs;
    public final long loadTaskId;
    public final Map<String, List<String>> responseHeaders;
    public final Uri uri;

    public LoadEventInfo(long j2, DataSpec dataSpec2, long j3) {
        this(j2, dataSpec2, dataSpec2.uri, Collections.EMPTY_MAP, j3, 0, 0);
    }

    public static long getNewId() {
        return idSource.getAndIncrement();
    }

    public LoadEventInfo(long j2, DataSpec dataSpec2, Uri uri2, Map<String, List<String>> map, long j3, long j8, long j10) {
        this.loadTaskId = j2;
        this.dataSpec = dataSpec2;
        this.uri = uri2;
        this.responseHeaders = map;
        this.elapsedRealtimeMs = j3;
        this.loadDurationMs = j8;
        this.bytesLoaded = j10;
    }
}
