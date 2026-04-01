package androidx.media3.datasource;

import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HttpDataSource$CleartextNotPermittedException extends HttpDataSource$HttpDataSourceException {
    public HttpDataSource$CleartextNotPermittedException(IOException iOException, DataSpec dataSpec) {
        super("Cleartext HTTP traffic not permitted. See https://developer.android.com/guide/topics/media/issues/cleartext-not-permitted", iOException, dataSpec, 2007, 1);
    }
}
