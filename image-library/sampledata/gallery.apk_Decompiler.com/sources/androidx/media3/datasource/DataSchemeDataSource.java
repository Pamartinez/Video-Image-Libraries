package androidx.media3.datasource;

import android.net.Uri;
import android.util.Base64;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DataSchemeDataSource extends BaseDataSource {
    private int bytesRemaining;
    private byte[] data;
    private DataSpec dataSpec;
    private int readPosition;

    public DataSchemeDataSource() {
        super(false);
    }

    public void close() {
        if (this.data != null) {
            this.data = null;
            transferEnded();
        }
        this.dataSpec = null;
    }

    public Uri getUri() {
        DataSpec dataSpec2 = this.dataSpec;
        if (dataSpec2 != null) {
            return dataSpec2.uri;
        }
        return null;
    }

    public long open(DataSpec dataSpec2) {
        transferInitializing(dataSpec2);
        this.dataSpec = dataSpec2;
        Uri normalizeScheme = dataSpec2.uri.normalizeScheme();
        String scheme = normalizeScheme.getScheme();
        boolean equals = "data".equals(scheme);
        Assertions.checkArgument(equals, "Unsupported scheme: " + scheme);
        String[] split = Util.split(normalizeScheme.getSchemeSpecificPart(), GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (split.length == 2) {
            String str = split[1];
            if (split[0].contains(";base64")) {
                try {
                    this.data = Base64.decode(str, 0);
                } catch (IllegalArgumentException e) {
                    throw ParserException.createForMalformedDataOfUnknownType("Error while parsing Base64 encoded string: " + str, e);
                }
            } else {
                this.data = Util.getUtf8Bytes(URLDecoder.decode(str, StandardCharsets.US_ASCII.name()));
            }
            long j2 = dataSpec2.position;
            byte[] bArr = this.data;
            if (j2 <= ((long) bArr.length)) {
                int i2 = (int) j2;
                this.readPosition = i2;
                int length = bArr.length - i2;
                this.bytesRemaining = length;
                long j3 = dataSpec2.length;
                if (j3 != -1) {
                    this.bytesRemaining = (int) Math.min((long) length, j3);
                }
                transferStarted(dataSpec2);
                long j8 = dataSpec2.length;
                if (j8 != -1) {
                    return j8;
                }
                return (long) this.bytesRemaining;
            }
            this.data = null;
            throw new DataSourceException(2008);
        }
        throw ParserException.createForMalformedDataOfUnknownType("Unexpected URI format: " + normalizeScheme, (Throwable) null);
    }

    public int read(byte[] bArr, int i2, int i7) {
        if (i7 == 0) {
            return 0;
        }
        int i8 = this.bytesRemaining;
        if (i8 == 0) {
            return -1;
        }
        int min = Math.min(i7, i8);
        System.arraycopy(Util.castNonNull(this.data), this.readPosition, bArr, i2, min);
        this.readPosition += min;
        this.bytesRemaining -= min;
        bytesTransferred(min);
        return min;
    }
}
