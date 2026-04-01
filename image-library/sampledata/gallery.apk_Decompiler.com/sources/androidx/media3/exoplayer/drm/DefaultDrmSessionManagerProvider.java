package androidx.media3.exoplayer.drm;

import F2.O0;
import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultDrmSessionManagerProvider implements DrmSessionManagerProvider {
    private MediaItem.DrmConfiguration drmConfiguration;
    private DataSource.Factory drmHttpDataSourceFactory;
    private LoadErrorHandlingPolicy drmLoadErrorHandlingPolicy;
    private final Object lock = new Object();
    private DrmSessionManager manager;
    private String userAgent;

    private DrmSessionManager createManager(MediaItem.DrmConfiguration drmConfiguration2) {
        String str;
        DataSource.Factory factory = this.drmHttpDataSourceFactory;
        if (factory == null) {
            factory = new DefaultHttpDataSource.Factory().setUserAgent(this.userAgent);
        }
        Uri uri = drmConfiguration2.licenseUri;
        if (uri == null) {
            str = null;
        } else {
            str = uri.toString();
        }
        HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(str, drmConfiguration2.forceDefaultLicenseUri, factory);
        O0 v = drmConfiguration2.licenseRequestHeaders.entrySet().iterator();
        while (v.hasNext()) {
            Map.Entry entry = (Map.Entry) v.next();
            httpMediaDrmCallback.setKeyRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        DefaultDrmSessionManager.Builder playClearSamplesWithoutKeys = new DefaultDrmSessionManager.Builder().setUuidAndExoMediaDrmProvider(drmConfiguration2.scheme, FrameworkMediaDrm.DEFAULT_PROVIDER).setMultiSession(drmConfiguration2.multiSession).setPlayClearSamplesWithoutKeys(drmConfiguration2.playClearContentWithoutKey);
        Object[] array = drmConfiguration2.forcedSessionTrackTypes.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = array[i2];
            obj.getClass();
            iArr[i2] = ((Number) obj).intValue();
        }
        DefaultDrmSessionManager.Builder useDrmSessionsForClearContent = playClearSamplesWithoutKeys.setUseDrmSessionsForClearContent(iArr);
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.drmLoadErrorHandlingPolicy;
        if (loadErrorHandlingPolicy != null) {
            useDrmSessionsForClearContent.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        }
        DefaultDrmSessionManager build = useDrmSessionsForClearContent.build(httpMediaDrmCallback);
        build.setMode(0, drmConfiguration2.getKeySetId());
        return build;
    }

    public DrmSessionManager get(MediaItem mediaItem) {
        DrmSessionManager drmSessionManager;
        Assertions.checkNotNull(mediaItem.localConfiguration);
        MediaItem.DrmConfiguration drmConfiguration2 = mediaItem.localConfiguration.drmConfiguration;
        if (drmConfiguration2 == null) {
            return DrmSessionManager.DRM_UNSUPPORTED;
        }
        synchronized (this.lock) {
            try {
                if (!drmConfiguration2.equals(this.drmConfiguration)) {
                    this.drmConfiguration = drmConfiguration2;
                    this.manager = createManager(drmConfiguration2);
                }
                drmSessionManager = (DrmSessionManager) Assertions.checkNotNull(this.manager);
            } catch (Throwable th) {
                throw th;
            }
        }
        return drmSessionManager;
    }
}
