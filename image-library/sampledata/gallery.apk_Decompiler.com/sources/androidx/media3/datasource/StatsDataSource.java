package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StatsDataSource implements DataSource {
    private long bytesRead;
    private final DataSource dataSource;
    private Uri lastOpenedUri = Uri.EMPTY;
    private Map<String, List<String>> lastResponseHeaders = Collections.EMPTY_MAP;

    public StatsDataSource(DataSource dataSource2) {
        this.dataSource = (DataSource) Assertions.checkNotNull(dataSource2);
    }

    public void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        this.dataSource.addTransferListener(transferListener);
    }

    public void close() {
        this.dataSource.close();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public Uri getLastOpenedUri() {
        return this.lastOpenedUri;
    }

    public Map<String, List<String>> getLastResponseHeaders() {
        return this.lastResponseHeaders;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getResponseHeaders();
    }

    public Uri getUri() {
        return this.dataSource.getUri();
    }

    public long open(DataSpec dataSpec) {
        this.lastOpenedUri = dataSpec.uri;
        this.lastResponseHeaders = Collections.EMPTY_MAP;
        try {
            return this.dataSource.open(dataSpec);
        } finally {
            Uri uri = getUri();
            if (uri != null) {
                this.lastOpenedUri = uri;
            }
            this.lastResponseHeaders = getResponseHeaders();
        }
    }

    public int read(byte[] bArr, int i2, int i7) {
        int read = this.dataSource.read(bArr, i2, i7);
        if (read != -1) {
            this.bytesRead += (long) read;
        }
        return read;
    }

    public void resetBytesRead() {
        this.bytesRead = 0;
    }
}
