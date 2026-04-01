package androidx.media3.exoplayer.drm;

import F2.D0;
import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import com.samsung.scsp.common.ContentType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private final DataSource.Factory dataSourceFactory;
    private final String defaultLicenseUrl;
    private final boolean forceDefaultLicenseUrl;
    private final Map<String, String> keyRequestProperties;

    public HttpMediaDrmCallback(String str, boolean z, DataSource.Factory factory) {
        boolean z3;
        if (!z || !TextUtils.isEmpty(str)) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3);
        this.dataSourceFactory = factory;
        this.defaultLicenseUrl = str;
        this.forceDefaultLicenseUrl = z;
        this.keyRequestProperties = new HashMap();
    }

    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) {
        String str;
        String licenseServerUrl = keyRequest.getLicenseServerUrl();
        if (this.forceDefaultLicenseUrl || TextUtils.isEmpty(licenseServerUrl)) {
            licenseServerUrl = this.defaultLicenseUrl;
        }
        if (!TextUtils.isEmpty(licenseServerUrl)) {
            HashMap hashMap = new HashMap();
            UUID uuid2 = C.PLAYREADY_UUID;
            if (uuid2.equals(uuid)) {
                str = "text/xml";
            } else if (C.CLEARKEY_UUID.equals(uuid)) {
                str = "application/json";
            } else {
                str = ContentType.OCTET_STREAM;
            }
            hashMap.put(ContentType.KEY, str);
            if (uuid2.equals(uuid)) {
                hashMap.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
            }
            synchronized (this.keyRequestProperties) {
                try {
                    hashMap.putAll(this.keyRequestProperties);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            return DrmUtil.executePost(this.dataSourceFactory.createDataSource(), licenseServerUrl, keyRequest.getData(), hashMap);
        }
        DataSpec.Builder builder = new DataSpec.Builder();
        Uri uri = Uri.EMPTY;
        throw new MediaDrmCallbackException(builder.setUri(uri).build(), uri, D0.k, 0, new IllegalStateException("No license URL"));
    }

    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) {
        return DrmUtil.executePost(this.dataSourceFactory.createDataSource(), provisionRequest.getDefaultUrl() + "&signedRequest=" + Util.fromUtf8Bytes(provisionRequest.getData()), (byte[]) null, Collections.EMPTY_MAP);
    }

    public void setKeyRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.put(str, str2);
        }
    }
}
