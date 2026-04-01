package androidx.media3.datasource;

import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataSourceException extends IOException {
    public final int reason;

    public DataSourceException(int i2) {
        this.reason = i2;
    }

    public static boolean isCausedByPositionOutOfRange(IOException iOException) {
        Throwable th;
        while (th != null) {
            if ((th instanceof DataSourceException) && ((DataSourceException) th).reason == 2008) {
                return true;
            }
            Throwable cause = th.getCause();
            th = iOException;
            th = cause;
        }
        return false;
    }

    public DataSourceException(Throwable th, int i2) {
        super(th);
        this.reason = i2;
    }

    public DataSourceException(String str, int i2) {
        super(str);
        this.reason = i2;
    }

    public DataSourceException(String str, Throwable th, int i2) {
        super(str, th);
        this.reason = i2;
    }
}
