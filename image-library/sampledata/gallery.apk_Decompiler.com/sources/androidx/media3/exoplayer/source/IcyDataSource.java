package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class IcyDataSource implements DataSource {
    private int bytesUntilMetadata;
    private final Listener listener;
    private final int metadataIntervalBytes;
    private final byte[] metadataLengthByteHolder;
    private final DataSource upstream;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onIcyMetadata(ParsableByteArray parsableByteArray);
    }

    public IcyDataSource(DataSource dataSource, int i2, Listener listener2) {
        boolean z;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        this.upstream = dataSource;
        this.metadataIntervalBytes = i2;
        this.listener = listener2;
        this.metadataLengthByteHolder = new byte[1];
        this.bytesUntilMetadata = i2;
    }

    private boolean readMetadata() {
        if (this.upstream.read(this.metadataLengthByteHolder, 0, 1) == -1) {
            return false;
        }
        int i2 = (this.metadataLengthByteHolder[0] & 255) << 4;
        if (i2 == 0) {
            return true;
        }
        byte[] bArr = new byte[i2];
        int i7 = i2;
        int i8 = 0;
        while (i7 > 0) {
            int read = this.upstream.read(bArr, i8, i7);
            if (read == -1) {
                return false;
            }
            i8 += read;
            i7 -= read;
        }
        while (i2 > 0 && bArr[i2 - 1] == 0) {
            i2--;
        }
        if (i2 > 0) {
            this.listener.onIcyMetadata(new ParsableByteArray(bArr, i2));
        }
        return true;
    }

    public void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        this.upstream.addTransferListener(transferListener);
    }

    public void close() {
        throw new UnsupportedOperationException();
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    public Uri getUri() {
        return this.upstream.getUri();
    }

    public long open(DataSpec dataSpec) {
        throw new UnsupportedOperationException();
    }

    public int read(byte[] bArr, int i2, int i7) {
        if (this.bytesUntilMetadata == 0) {
            if (!readMetadata()) {
                return -1;
            }
            this.bytesUntilMetadata = this.metadataIntervalBytes;
        }
        int read = this.upstream.read(bArr, i2, Math.min(this.bytesUntilMetadata, i7));
        if (read != -1) {
            this.bytesUntilMetadata -= read;
        }
        return read;
    }
}
