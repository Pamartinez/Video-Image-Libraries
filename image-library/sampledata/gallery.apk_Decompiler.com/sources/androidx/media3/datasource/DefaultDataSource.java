package androidx.media3.datasource;

import android.content.Context;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultDataSource implements DataSource {
    private DataSource assetDataSource;
    private final DataSource baseDataSource;
    private DataSource contentDataSource;
    private final Context context;
    private DataSource dataSchemeDataSource;
    private DataSource dataSource;
    private DataSource fileDataSource;
    private DataSource rawResourceDataSource;
    private DataSource rtmpDataSource;
    private final List<TransferListener> transferListeners;
    private DataSource udpDataSource;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements DataSource.Factory {
        private final DataSource.Factory baseDataSourceFactory;
        private final Context context;
        private TransferListener transferListener;

        public Factory(Context context2) {
            this(context2, new DefaultHttpDataSource.Factory());
        }

        public Factory(Context context2, DataSource.Factory factory) {
            this.context = context2.getApplicationContext();
            this.baseDataSourceFactory = (DataSource.Factory) Assertions.checkNotNull(factory);
        }

        public DefaultDataSource createDataSource() {
            DefaultDataSource defaultDataSource = new DefaultDataSource(this.context, this.baseDataSourceFactory.createDataSource());
            TransferListener transferListener2 = this.transferListener;
            if (transferListener2 != null) {
                defaultDataSource.addTransferListener(transferListener2);
            }
            return defaultDataSource;
        }
    }

    public DefaultDataSource(Context context2, boolean z) {
        this(context2, (String) null, Encode.BitRate.VIDEO_HD_BITRATE, Encode.BitRate.VIDEO_HD_BITRATE, z);
    }

    private void addListenersToDataSource(DataSource dataSource2) {
        for (int i2 = 0; i2 < this.transferListeners.size(); i2++) {
            dataSource2.addTransferListener(this.transferListeners.get(i2));
        }
    }

    private DataSource getAssetDataSource() {
        if (this.assetDataSource == null) {
            AssetDataSource assetDataSource2 = new AssetDataSource(this.context);
            this.assetDataSource = assetDataSource2;
            addListenersToDataSource(assetDataSource2);
        }
        return this.assetDataSource;
    }

    private DataSource getContentDataSource() {
        if (this.contentDataSource == null) {
            ContentDataSource contentDataSource2 = new ContentDataSource(this.context);
            this.contentDataSource = contentDataSource2;
            addListenersToDataSource(contentDataSource2);
        }
        return this.contentDataSource;
    }

    private DataSource getDataSchemeDataSource() {
        if (this.dataSchemeDataSource == null) {
            DataSchemeDataSource dataSchemeDataSource2 = new DataSchemeDataSource();
            this.dataSchemeDataSource = dataSchemeDataSource2;
            addListenersToDataSource(dataSchemeDataSource2);
        }
        return this.dataSchemeDataSource;
    }

    private DataSource getFileDataSource() {
        if (this.fileDataSource == null) {
            FileDataSource fileDataSource2 = new FileDataSource();
            this.fileDataSource = fileDataSource2;
            addListenersToDataSource(fileDataSource2);
        }
        return this.fileDataSource;
    }

    private DataSource getRawResourceDataSource() {
        if (this.rawResourceDataSource == null) {
            RawResourceDataSource rawResourceDataSource2 = new RawResourceDataSource(this.context);
            this.rawResourceDataSource = rawResourceDataSource2;
            addListenersToDataSource(rawResourceDataSource2);
        }
        return this.rawResourceDataSource;
    }

    private DataSource getRtmpDataSource() {
        if (this.rtmpDataSource == null) {
            try {
                DataSource dataSource2 = (DataSource) Class.forName("androidx.media3.datasource.rtmp.RtmpDataSource").getConstructor((Class[]) null).newInstance((Object[]) null);
                this.rtmpDataSource = dataSource2;
                addListenersToDataSource(dataSource2);
            } catch (ClassNotFoundException unused) {
                Log.w("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
            } catch (Exception e) {
                throw new RuntimeException("Error instantiating RTMP extension", e);
            }
            if (this.rtmpDataSource == null) {
                this.rtmpDataSource = this.baseDataSource;
            }
        }
        return this.rtmpDataSource;
    }

    private DataSource getUdpDataSource() {
        if (this.udpDataSource == null) {
            UdpDataSource udpDataSource2 = new UdpDataSource();
            this.udpDataSource = udpDataSource2;
            addListenersToDataSource(udpDataSource2);
        }
        return this.udpDataSource;
    }

    private void maybeAddListenerToDataSource(DataSource dataSource2, TransferListener transferListener) {
        if (dataSource2 != null) {
            dataSource2.addTransferListener(transferListener);
        }
    }

    public void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        this.baseDataSource.addTransferListener(transferListener);
        this.transferListeners.add(transferListener);
        maybeAddListenerToDataSource(this.fileDataSource, transferListener);
        maybeAddListenerToDataSource(this.assetDataSource, transferListener);
        maybeAddListenerToDataSource(this.contentDataSource, transferListener);
        maybeAddListenerToDataSource(this.rtmpDataSource, transferListener);
        maybeAddListenerToDataSource(this.udpDataSource, transferListener);
        maybeAddListenerToDataSource(this.dataSchemeDataSource, transferListener);
        maybeAddListenerToDataSource(this.rawResourceDataSource, transferListener);
    }

    public void close() {
        DataSource dataSource2 = this.dataSource;
        if (dataSource2 != null) {
            try {
                dataSource2.close();
            } finally {
                this.dataSource = null;
            }
        }
    }

    public Map<String, List<String>> getResponseHeaders() {
        DataSource dataSource2 = this.dataSource;
        if (dataSource2 == null) {
            return Collections.EMPTY_MAP;
        }
        return dataSource2.getResponseHeaders();
    }

    public Uri getUri() {
        DataSource dataSource2 = this.dataSource;
        if (dataSource2 == null) {
            return null;
        }
        return dataSource2.getUri();
    }

    public long open(DataSpec dataSpec) {
        boolean z;
        if (this.dataSource == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        String scheme = dataSpec.uri.getScheme();
        if (Util.isLocalFileUri(dataSpec.uri)) {
            String path = dataSpec.uri.getPath();
            if (path == null || !path.startsWith("/android_asset/")) {
                this.dataSource = getFileDataSource();
            } else {
                this.dataSource = getAssetDataSource();
            }
        } else if ("asset".equals(scheme)) {
            this.dataSource = getAssetDataSource();
        } else if ("content".equals(scheme)) {
            this.dataSource = getContentDataSource();
        } else if ("rtmp".equals(scheme)) {
            this.dataSource = getRtmpDataSource();
        } else if ("udp".equals(scheme)) {
            this.dataSource = getUdpDataSource();
        } else if ("data".equals(scheme)) {
            this.dataSource = getDataSchemeDataSource();
        } else if ("rawresource".equals(scheme) || "android.resource".equals(scheme)) {
            this.dataSource = getRawResourceDataSource();
        } else {
            this.dataSource = this.baseDataSource;
        }
        return this.dataSource.open(dataSpec);
    }

    public int read(byte[] bArr, int i2, int i7) {
        return ((DataSource) Assertions.checkNotNull(this.dataSource)).read(bArr, i2, i7);
    }

    public DefaultDataSource(Context context2, String str, int i2, int i7, boolean z) {
        this(context2, (DataSource) new DefaultHttpDataSource.Factory().setUserAgent(str).setConnectTimeoutMs(i2).setReadTimeoutMs(i7).setAllowCrossProtocolRedirects(z).createDataSource());
    }

    public DefaultDataSource(Context context2, DataSource dataSource2) {
        this.context = context2.getApplicationContext();
        this.baseDataSource = (DataSource) Assertions.checkNotNull(dataSource2);
        this.transferListeners = new ArrayList();
    }
}
