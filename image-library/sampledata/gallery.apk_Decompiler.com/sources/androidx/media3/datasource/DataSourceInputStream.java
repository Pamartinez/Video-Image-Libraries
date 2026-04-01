package androidx.media3.datasource;

import androidx.media3.common.util.Assertions;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DataSourceInputStream extends InputStream {
    private boolean closed = false;
    private final DataSource dataSource;
    private final DataSpec dataSpec;
    private boolean opened = false;
    private final byte[] singleByteArray;
    private long totalBytesRead;

    public DataSourceInputStream(DataSource dataSource2, DataSpec dataSpec2) {
        this.dataSource = dataSource2;
        this.dataSpec = dataSpec2;
        this.singleByteArray = new byte[1];
    }

    private void checkOpened() {
        if (!this.opened) {
            this.dataSource.open(this.dataSpec);
            this.opened = true;
        }
    }

    public void close() {
        if (!this.closed) {
            this.dataSource.close();
            this.closed = true;
        }
    }

    public int read() {
        if (read(this.singleByteArray) == -1) {
            return -1;
        }
        return this.singleByteArray[0] & 255;
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i2, int i7) {
        Assertions.checkState(!this.closed);
        checkOpened();
        int read = this.dataSource.read(bArr, i2, i7);
        if (read == -1) {
            return -1;
        }
        this.totalBytesRead += (long) read;
        return read;
    }
}
