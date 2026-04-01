package androidx.media3.datasource;

import c0.C0086a;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HttpDataSource$InvalidResponseCodeException extends HttpDataSource$HttpDataSourceException {
    public final Map<String, List<String>> headerFields;
    public final byte[] responseBody;
    public final int responseCode;
    public final String responseMessage;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpDataSource$InvalidResponseCodeException(int i2, String str, IOException iOException, Map<String, List<String>> map, DataSpec dataSpec, byte[] bArr) {
        super(C0086a.i(i2, "Response code: "), iOException, dataSpec, 2004, 1);
        this.responseCode = i2;
        this.responseMessage = str;
        this.headerFields = map;
        this.responseBody = bArr;
    }
}
