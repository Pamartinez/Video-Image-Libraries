package androidx.media3.exoplayer.drm;

import android.net.Uri;
import androidx.media3.datasource.DataSpec;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaDrmCallbackException extends IOException {
    public final long bytesLoaded;
    public final DataSpec dataSpec;
    public final Map<String, List<String>> responseHeaders;
    public final Uri uriAfterRedirects;

    public MediaDrmCallbackException(DataSpec dataSpec2, Uri uri, Map<String, List<String>> map, long j2, Throwable th) {
        super(th);
        this.dataSpec = dataSpec2;
        this.uriAfterRedirects = uri;
        this.responseHeaders = map;
        this.bytesLoaded = j2;
    }
}
