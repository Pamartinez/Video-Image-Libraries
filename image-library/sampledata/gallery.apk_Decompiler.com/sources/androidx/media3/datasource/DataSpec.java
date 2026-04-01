package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Assertions;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DataSpec {
    @Deprecated
    public final long absoluteStreamPosition;
    public final Object customData;
    public final int flags;
    public final byte[] httpBody;
    public final int httpMethod;
    public final Map<String, String> httpRequestHeaders;
    public final String key;
    public final long length;
    public final long position;
    public final Uri uri;
    public final long uriPositionOffset;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Object customData;
        private int flags;
        private byte[] httpBody;
        private int httpMethod;
        private Map<String, String> httpRequestHeaders;
        private String key;
        private long length;
        private long position;
        private Uri uri;
        private long uriPositionOffset;

        public DataSpec build() {
            Assertions.checkStateNotNull(this.uri, "The uri must be set.");
            return new DataSpec(this.uri, this.uriPositionOffset, this.httpMethod, this.httpBody, this.httpRequestHeaders, this.position, this.length, this.key, this.flags, this.customData);
        }

        public Builder setFlags(int i2) {
            this.flags = i2;
            return this;
        }

        public Builder setHttpBody(byte[] bArr) {
            this.httpBody = bArr;
            return this;
        }

        public Builder setHttpMethod(int i2) {
            this.httpMethod = i2;
            return this;
        }

        public Builder setHttpRequestHeaders(Map<String, String> map) {
            this.httpRequestHeaders = map;
            return this;
        }

        public Builder setKey(String str) {
            this.key = str;
            return this;
        }

        public Builder setPosition(long j2) {
            this.position = j2;
            return this;
        }

        public Builder setUri(String str) {
            this.uri = Uri.parse(str);
            return this;
        }

        public Builder() {
            this.httpMethod = 1;
            this.httpRequestHeaders = Collections.EMPTY_MAP;
            this.length = -1;
        }

        public Builder setUri(Uri uri2) {
            this.uri = uri2;
            return this;
        }

        private Builder(DataSpec dataSpec) {
            this.uri = dataSpec.uri;
            this.uriPositionOffset = dataSpec.uriPositionOffset;
            this.httpMethod = dataSpec.httpMethod;
            this.httpBody = dataSpec.httpBody;
            this.httpRequestHeaders = dataSpec.httpRequestHeaders;
            this.position = dataSpec.position;
            this.length = dataSpec.length;
            this.key = dataSpec.key;
            this.flags = dataSpec.flags;
            this.customData = dataSpec.customData;
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.datasource");
    }

    public static String getStringForHttpMethod(int i2) {
        if (i2 == 1) {
            return "GET";
        }
        if (i2 == 2) {
            return "POST";
        }
        if (i2 == 3) {
            return "HEAD";
        }
        throw new IllegalStateException();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public final String getHttpMethodString() {
        return getStringForHttpMethod(this.httpMethod);
    }

    public boolean isFlagSet(int i2) {
        if ((this.flags & i2) == i2) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("DataSpec[");
        sb2.append(getHttpMethodString());
        sb2.append(" ");
        sb2.append(this.uri);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.position);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.length);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.key);
        sb2.append(ArcCommonLog.TAG_COMMA);
        return C0086a.l(sb2, this.flags, "]");
    }

    public DataSpec(Uri uri2) {
        this(uri2, 0, -1);
    }

    public DataSpec(Uri uri2, long j2, long j3) {
        this(uri2, j2, j3, (String) null);
    }

    @Deprecated
    public DataSpec(Uri uri2, long j2, long j3, String str) {
        this(uri2, 0, 1, (byte[]) null, Collections.EMPTY_MAP, j2, j3, str, 0, (Object) null);
    }

    private DataSpec(Uri uri2, long j2, int i2, byte[] bArr, Map<String, String> map, long j3, long j8, String str, int i7, Object obj) {
        long j10 = j2;
        byte[] bArr2 = bArr;
        long j11 = j3;
        long j12 = j8;
        long j13 = j10 + j11;
        boolean z = false;
        Assertions.checkArgument(j13 >= 0);
        Assertions.checkArgument(j11 >= 0);
        Assertions.checkArgument((j12 > 0 || j12 == -1) ? true : z);
        this.uri = (Uri) Assertions.checkNotNull(uri2);
        this.uriPositionOffset = j10;
        this.httpMethod = i2;
        this.httpBody = (bArr2 == null || bArr2.length == 0) ? null : bArr2;
        this.httpRequestHeaders = Collections.unmodifiableMap(new HashMap(map));
        this.position = j11;
        this.absoluteStreamPosition = j13;
        this.length = j12;
        this.key = str;
        this.flags = i7;
        this.customData = obj;
    }
}
