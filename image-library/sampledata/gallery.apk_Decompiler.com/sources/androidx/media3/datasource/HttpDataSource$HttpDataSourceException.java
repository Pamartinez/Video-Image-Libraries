package androidx.media3.datasource;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HttpDataSource$HttpDataSourceException extends DataSourceException {
    public final DataSpec dataSpec;
    public final int type;

    public HttpDataSource$HttpDataSourceException(DataSpec dataSpec2, int i2, int i7) {
        super(assignErrorCode(i2, i7));
        this.dataSpec = dataSpec2;
        this.type = i7;
    }

    private static int assignErrorCode(int i2, int i7) {
        if (i2 == 2000 && i7 == 1) {
            return 2001;
        }
        return i2;
    }

    public static HttpDataSource$HttpDataSourceException createForIOException(IOException iOException, DataSpec dataSpec2, int i2) {
        int i7;
        String message = iOException.getMessage();
        if (iOException instanceof SocketTimeoutException) {
            i7 = 2002;
        } else if (iOException instanceof InterruptedIOException) {
            i7 = 1004;
        } else if (message == null || !k.S(message).matches("cleartext.*not permitted.*")) {
            i7 = 2001;
        } else {
            i7 = 2007;
        }
        if (i7 == 2007) {
            return new HttpDataSource$CleartextNotPermittedException(iOException, dataSpec2);
        }
        return new HttpDataSource$HttpDataSourceException(iOException, dataSpec2, i7, i2);
    }

    public HttpDataSource$HttpDataSourceException(String str, DataSpec dataSpec2, int i2, int i7) {
        super(str, assignErrorCode(i2, i7));
        this.dataSpec = dataSpec2;
        this.type = i7;
    }

    public HttpDataSource$HttpDataSourceException(IOException iOException, DataSpec dataSpec2, int i2, int i7) {
        super((Throwable) iOException, assignErrorCode(i2, i7));
        this.dataSpec = dataSpec2;
        this.type = i7;
    }

    public HttpDataSource$HttpDataSourceException(String str, IOException iOException, DataSpec dataSpec2, int i2, int i7) {
        super(str, iOException, assignErrorCode(i2, i7));
        this.dataSpec = dataSpec2;
        this.type = i7;
    }
}
