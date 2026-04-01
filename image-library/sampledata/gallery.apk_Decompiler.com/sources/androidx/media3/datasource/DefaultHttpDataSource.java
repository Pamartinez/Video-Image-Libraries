package androidx.media3.datasource;

import E2.l;
import F2.C0040v;
import F2.D0;
import F2.J;
import H2.f;
import android.net.Uri;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import c0.C0086a;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.scsp.common.Header;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import i.C0212a;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultHttpDataSource extends BaseDataSource implements DataSource {
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesToRead;
    private final int connectTimeoutMillis;
    private HttpURLConnection connection;
    private final l contentTypePredicate;
    private final boolean crossProtocolRedirectsForceOriginal;
    private DataSpec dataSpec;
    private final HttpDataSource$RequestProperties defaultRequestProperties;
    private InputStream inputStream;
    private final boolean keepPostFor302Redirects;
    private final int readTimeoutMillis;
    private final HttpDataSource$RequestProperties requestProperties;
    private int responseCode;
    private boolean transferStarted;
    private final String userAgent;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements DataSource.Factory {
        private boolean allowCrossProtocolRedirects;
        private int connectTimeoutMs = Encode.BitRate.VIDEO_HD_BITRATE;
        private l contentTypePredicate;
        private boolean crossProtocolRedirectsForceOriginal;
        private final HttpDataSource$RequestProperties defaultRequestProperties = new HttpDataSource$RequestProperties();
        private boolean keepPostFor302Redirects;
        private int readTimeoutMs = Encode.BitRate.VIDEO_HD_BITRATE;
        private TransferListener transferListener;
        private String userAgent;

        public Factory setAllowCrossProtocolRedirects(boolean z) {
            this.allowCrossProtocolRedirects = z;
            return this;
        }

        public Factory setConnectTimeoutMs(int i2) {
            this.connectTimeoutMs = i2;
            return this;
        }

        public Factory setReadTimeoutMs(int i2) {
            this.readTimeoutMs = i2;
            return this;
        }

        public Factory setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        public DefaultHttpDataSource createDataSource() {
            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(this.userAgent, this.connectTimeoutMs, this.readTimeoutMs, this.allowCrossProtocolRedirects, this.crossProtocolRedirectsForceOriginal, this.defaultRequestProperties, this.contentTypePredicate, this.keepPostFor302Redirects);
            TransferListener transferListener2 = this.transferListener;
            if (transferListener2 != null) {
                defaultHttpDataSource.addTransferListener(transferListener2);
            }
            return defaultHttpDataSource;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NullFilteringHeadersMap extends J {
        private final Map<String, List<String>> headers;

        public NullFilteringHeadersMap(Map<String, List<String>> map) {
            this.headers = map;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$entrySet$1(Map.Entry entry) {
            if (entry.getKey() != null) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$keySet$0(String str) {
            if (str != null) {
                return true;
            }
            return false;
        }

        public boolean containsKey(Object obj) {
            if (obj == null || !super.containsKey(obj)) {
                return false;
            }
            return true;
        }

        public boolean containsValue(Object obj) {
            return super.standardContainsValue(obj);
        }

        public Set<Map.Entry<String, List<String>>> entrySet() {
            return C0040v.g(super.entrySet(), new a(0));
        }

        public boolean equals(Object obj) {
            if (obj == null || !super.standardEquals(obj)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return super.standardHashCode();
        }

        public boolean isEmpty() {
            if (super.isEmpty() || (super.size() == 1 && super.containsKey((Object) null))) {
                return true;
            }
            return false;
        }

        public Set<String> keySet() {
            return C0040v.g(super.keySet(), new a(1));
        }

        public int size() {
            return super.size() - (super.containsKey((Object) null) ? 1 : 0);
        }

        public Map<String, List<String>> delegate() {
            return this.headers;
        }

        public List<String> get(Object obj) {
            if (obj == null) {
                return null;
            }
            return (List) super.get(obj);
        }
    }

    private void closeConnectionQuietly() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
        }
    }

    private URL handleRedirect(URL url, String str, DataSpec dataSpec2) {
        if (str != null) {
            try {
                URL url2 = new URL(url, str);
                String protocol = url2.getProtocol();
                if (!"https".equals(protocol) && !"http".equals(protocol)) {
                    throw new HttpDataSource$HttpDataSourceException(C0212a.l("Unsupported protocol redirect: ", protocol), dataSpec2, 2001, 1);
                } else if (this.allowCrossProtocolRedirects || protocol.equals(url.getProtocol())) {
                    return url2;
                } else {
                    if (this.crossProtocolRedirectsForceOriginal) {
                        try {
                            return new URL(url2.toString().replaceFirst(protocol, url.getProtocol()));
                        } catch (MalformedURLException e) {
                            throw new HttpDataSource$HttpDataSourceException((IOException) e, dataSpec2, 2001, 1);
                        }
                    } else {
                        throw new HttpDataSource$HttpDataSourceException("Disallowed cross-protocol redirect (" + url.getProtocol() + " to " + protocol + ")", dataSpec2, 2001, 1);
                    }
                }
            } catch (MalformedURLException e7) {
                throw new HttpDataSource$HttpDataSourceException((IOException) e7, dataSpec2, 2001, 1);
            }
        } else {
            throw new HttpDataSource$HttpDataSourceException("Null location redirect", dataSpec2, 2001, 1);
        }
    }

    private static boolean isCompressed(HttpURLConnection httpURLConnection) {
        return Header.GZIP.equalsIgnoreCase(httpURLConnection.getHeaderField(Header.CONTENT_ENCODING));
    }

    private HttpURLConnection makeConnection(DataSpec dataSpec2) {
        HttpURLConnection makeConnection;
        DataSpec dataSpec3 = dataSpec2;
        URL url = new URL(dataSpec3.uri.toString());
        int i2 = dataSpec3.httpMethod;
        byte[] bArr = dataSpec3.httpBody;
        long j2 = dataSpec3.position;
        long j3 = dataSpec3.length;
        int i7 = 1;
        boolean isFlagSet = dataSpec3.isFlagSet(1);
        if (!this.allowCrossProtocolRedirects && !this.crossProtocolRedirectsForceOriginal && !this.keepPostFor302Redirects) {
            return makeConnection(url, i2, bArr, j2, j3, isFlagSet, true, dataSpec3.httpRequestHeaders);
        }
        int i8 = 0;
        while (true) {
            int i10 = i8 + 1;
            if (i8 <= 20) {
                makeConnection = makeConnection(url, i2, bArr, j2, j3, isFlagSet, false, dataSpec3.httpRequestHeaders);
                int responseCode2 = makeConnection.getResponseCode();
                String headerField = makeConnection.getHeaderField("Location");
                if ((i2 == i7 || i2 == 3) && (responseCode2 == 300 || responseCode2 == 301 || responseCode2 == 302 || responseCode2 == 303 || responseCode2 == 307 || responseCode2 == 308)) {
                    makeConnection.disconnect();
                    url = handleRedirect(url, headerField, dataSpec3);
                } else if (i2 != 2 || (responseCode2 != 300 && responseCode2 != 301 && responseCode2 != 302 && responseCode2 != 303)) {
                    return makeConnection;
                } else {
                    makeConnection.disconnect();
                    if (!this.keepPostFor302Redirects || responseCode2 != 302) {
                        bArr = null;
                        i2 = 1;
                    }
                    url = handleRedirect(url, headerField, dataSpec3);
                }
                i8 = i10;
                i7 = 1;
            } else {
                throw new HttpDataSource$HttpDataSourceException((IOException) new NoRouteToHostException(C0086a.i(i10, "Too many redirects: ")), dataSpec3, 2001, 1);
            }
        }
        return makeConnection;
    }

    private int readInternal(byte[] bArr, int i2, int i7) {
        if (i7 == 0) {
            return 0;
        }
        long j2 = this.bytesToRead;
        if (j2 != -1) {
            long j3 = j2 - this.bytesRead;
            if (j3 == 0) {
                return -1;
            }
            i7 = (int) Math.min((long) i7, j3);
        }
        int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, i2, i7);
        if (read == -1) {
            return -1;
        }
        this.bytesRead += (long) read;
        bytesTransferred(read);
        return read;
    }

    private void skipFully(long j2, DataSpec dataSpec2) {
        if (j2 != 0) {
            byte[] bArr = new byte[4096];
            while (j2 > 0) {
                int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, 0, (int) Math.min(j2, (long) 4096));
                if (Thread.currentThread().isInterrupted()) {
                    throw new HttpDataSource$HttpDataSourceException((IOException) new InterruptedIOException(), dataSpec2, 2000, 1);
                } else if (read != -1) {
                    j2 -= (long) read;
                    bytesTransferred(read);
                } else {
                    throw new HttpDataSource$HttpDataSourceException(dataSpec2, 2008, 1);
                }
            }
        }
    }

    public void close() {
        try {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.transferStarted) {
                this.transferStarted = false;
                transferEnded();
            }
            this.connection = null;
            this.dataSpec = null;
        } catch (IOException e) {
            throw new HttpDataSource$HttpDataSourceException(e, (DataSpec) Util.castNonNull(this.dataSpec), 2000, 3);
        } catch (Throwable th) {
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.transferStarted) {
                this.transferStarted = false;
                transferEnded();
            }
            this.connection = null;
            this.dataSpec = null;
            throw th;
        }
    }

    public Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return D0.k;
        }
        return new NullFilteringHeadersMap(httpURLConnection.getHeaderFields());
    }

    public Uri getUri() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            return Uri.parse(httpURLConnection.getURL().toString());
        }
        DataSpec dataSpec2 = this.dataSpec;
        if (dataSpec2 != null) {
            return dataSpec2.uri;
        }
        return null;
    }

    public long open(DataSpec dataSpec2) {
        byte[] bArr;
        DataSourceException dataSourceException;
        this.dataSpec = dataSpec2;
        long j2 = 0;
        this.bytesRead = 0;
        this.bytesToRead = 0;
        transferInitializing(dataSpec2);
        try {
            HttpURLConnection makeConnection = makeConnection(dataSpec2);
            this.connection = makeConnection;
            this.responseCode = makeConnection.getResponseCode();
            String responseMessage = makeConnection.getResponseMessage();
            int i2 = this.responseCode;
            long j3 = -1;
            if (i2 < 200 || i2 > 299) {
                Map<String, List<String>> headerFields = makeConnection.getHeaderFields();
                if (this.responseCode == 416) {
                    if (dataSpec2.position == HttpUtil.getDocumentSize(makeConnection.getHeaderField(Header.CONTENT_RANGE))) {
                        this.transferStarted = true;
                        transferStarted(dataSpec2);
                        long j8 = dataSpec2.length;
                        if (j8 != -1) {
                            return j8;
                        }
                        return 0;
                    }
                }
                InputStream errorStream = makeConnection.getErrorStream();
                if (errorStream != null) {
                    try {
                        bArr = f.b(errorStream);
                    } catch (IOException unused) {
                        bArr = Util.EMPTY_BYTE_ARRAY;
                    }
                } else {
                    bArr = Util.EMPTY_BYTE_ARRAY;
                }
                byte[] bArr2 = bArr;
                closeConnectionQuietly();
                if (this.responseCode == 416) {
                    dataSourceException = new DataSourceException(2008);
                } else {
                    dataSourceException = null;
                }
                throw new HttpDataSource$InvalidResponseCodeException(this.responseCode, responseMessage, dataSourceException, headerFields, dataSpec2, bArr2);
            }
            String contentType = makeConnection.getContentType();
            l lVar = this.contentTypePredicate;
            if (lVar == null || lVar.apply(contentType)) {
                if (this.responseCode == 200) {
                    long j10 = dataSpec2.position;
                    if (j10 != 0) {
                        j2 = j10;
                    }
                }
                boolean isCompressed = isCompressed(makeConnection);
                if (!isCompressed) {
                    long j11 = dataSpec2.length;
                    if (j11 != -1) {
                        this.bytesToRead = j11;
                    } else {
                        long contentLength = HttpUtil.getContentLength(makeConnection.getHeaderField(Header.CONTENT_LENGTH), makeConnection.getHeaderField(Header.CONTENT_RANGE));
                        if (contentLength != -1) {
                            j3 = contentLength - j2;
                        }
                        this.bytesToRead = j3;
                    }
                } else {
                    this.bytesToRead = dataSpec2.length;
                }
                try {
                    this.inputStream = makeConnection.getInputStream();
                    if (isCompressed) {
                        this.inputStream = new GZIPInputStream(this.inputStream);
                    }
                    this.transferStarted = true;
                    transferStarted(dataSpec2);
                    try {
                        skipFully(j2, dataSpec2);
                        return this.bytesToRead;
                    } catch (IOException e) {
                        closeConnectionQuietly();
                        if (e instanceof HttpDataSource$HttpDataSourceException) {
                            throw ((HttpDataSource$HttpDataSourceException) e);
                        }
                        throw new HttpDataSource$HttpDataSourceException(e, dataSpec2, 2000, 1);
                    }
                } catch (IOException e7) {
                    closeConnectionQuietly();
                    throw new HttpDataSource$HttpDataSourceException(e7, dataSpec2, 2000, 1);
                }
            } else {
                closeConnectionQuietly();
                throw new HttpDataSource$InvalidContentTypeException(contentType, dataSpec2);
            }
        } catch (IOException e8) {
            DataSpec dataSpec3 = dataSpec2;
            closeConnectionQuietly();
            throw HttpDataSource$HttpDataSourceException.createForIOException(e8, dataSpec3, 1);
        }
    }

    public HttpURLConnection openConnection(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    public int read(byte[] bArr, int i2, int i7) {
        try {
            return readInternal(bArr, i2, i7);
        } catch (IOException e) {
            throw HttpDataSource$HttpDataSourceException.createForIOException(e, (DataSpec) Util.castNonNull(this.dataSpec), 2);
        }
    }

    private DefaultHttpDataSource(String str, int i2, int i7, boolean z, boolean z3, HttpDataSource$RequestProperties httpDataSource$RequestProperties, l lVar, boolean z7) {
        super(true);
        this.userAgent = str;
        this.connectTimeoutMillis = i2;
        this.readTimeoutMillis = i7;
        this.allowCrossProtocolRedirects = z;
        this.crossProtocolRedirectsForceOriginal = z3;
        if (!z || !z3) {
            this.defaultRequestProperties = httpDataSource$RequestProperties;
            this.contentTypePredicate = lVar;
            this.requestProperties = new HttpDataSource$RequestProperties();
            this.keepPostFor302Redirects = z7;
            return;
        }
        throw new IllegalArgumentException("crossProtocolRedirectsForceOriginal should not be set if allowCrossProtocolRedirects is true");
    }

    private HttpURLConnection makeConnection(URL url, int i2, byte[] bArr, long j2, long j3, boolean z, boolean z3, Map<String, String> map) {
        HttpURLConnection openConnection = openConnection(url);
        openConnection.setConnectTimeout(this.connectTimeoutMillis);
        openConnection.setReadTimeout(this.readTimeoutMillis);
        HashMap hashMap = new HashMap();
        HttpDataSource$RequestProperties httpDataSource$RequestProperties = this.defaultRequestProperties;
        if (httpDataSource$RequestProperties != null) {
            hashMap.putAll(httpDataSource$RequestProperties.getSnapshot());
        }
        hashMap.putAll(this.requestProperties.getSnapshot());
        hashMap.putAll(map);
        for (Map.Entry entry : hashMap.entrySet()) {
            openConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        String buildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(j2, j3);
        if (buildRangeRequestHeader != null) {
            openConnection.setRequestProperty(Header.RANGE, buildRangeRequestHeader);
        }
        String str = this.userAgent;
        if (str != null) {
            openConnection.setRequestProperty(HeaderSetup.Key.USER_AGENT, str);
        }
        openConnection.setRequestProperty(Header.ACCEPT_ENCODING, z ? Header.GZIP : "identity");
        openConnection.setInstanceFollowRedirects(z3);
        openConnection.setDoOutput(bArr != null);
        openConnection.setRequestMethod(DataSpec.getStringForHttpMethod(i2));
        if (bArr != null) {
            openConnection.setFixedLengthStreamingMode(bArr.length);
            openConnection.connect();
            OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
            return openConnection;
        }
        openConnection.connect();
        return openConnection;
    }
}
