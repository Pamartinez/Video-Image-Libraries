package androidx.media3.datasource;

import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HttpDataSource$InvalidContentTypeException extends HttpDataSource$HttpDataSourceException {
    public final String contentType;

    public HttpDataSource$InvalidContentTypeException(String str, DataSpec dataSpec) {
        super(C0212a.l("Invalid content type: ", str), dataSpec, 2003, 1);
        this.contentType = str;
    }
}
