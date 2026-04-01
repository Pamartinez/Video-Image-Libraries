package androidx.media3.exoplayer.upstream;

import androidx.media3.common.ParserException;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.HttpDataSource$CleartextNotPermittedException;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultLoadErrorHandlingPolicy implements LoadErrorHandlingPolicy {
    private final int minimumLoadableRetryCount;

    public DefaultLoadErrorHandlingPolicy() {
        this(-1);
    }

    public int getMinimumLoadableRetryCount(int i2) {
        int i7 = this.minimumLoadableRetryCount;
        if (i7 != -1) {
            return i7;
        }
        if (i2 == 7) {
            return 6;
        }
        return 3;
    }

    public long getRetryDelayMsFor(LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo) {
        IOException iOException = loadErrorInfo.exception;
        if ((iOException instanceof ParserException) || (iOException instanceof FileNotFoundException) || (iOException instanceof HttpDataSource$CleartextNotPermittedException) || (iOException instanceof Loader.UnexpectedLoaderException) || DataSourceException.isCausedByPositionOutOfRange(iOException)) {
            return -9223372036854775807L;
        }
        return (long) Math.min((loadErrorInfo.errorCount - 1) * 1000, 5000);
    }

    public DefaultLoadErrorHandlingPolicy(int i2) {
        this.minimumLoadableRetryCount = i2;
    }
}
