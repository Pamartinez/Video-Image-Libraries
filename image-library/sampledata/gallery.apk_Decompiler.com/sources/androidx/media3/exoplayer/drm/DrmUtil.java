package androidx.media3.exoplayer.drm;

import H2.f;
import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.os.Build;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceInputStream;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DrmUtil {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api23 {
        public static boolean isMediaDrmResetException(Throwable th) {
            return th instanceof MediaDrmResetException;
        }
    }

    public static byte[] executePost(DataSource dataSource, String str, byte[] bArr, Map<String, String> map) {
        DataSourceInputStream dataSourceInputStream;
        StatsDataSource statsDataSource = new StatsDataSource(dataSource);
        DataSpec build = new DataSpec.Builder().setUri(str).setHttpRequestHeaders(map).setHttpMethod(2).setHttpBody(bArr).setFlags(1).build();
        int i2 = 0;
        DataSpec dataSpec = build;
        while (true) {
            try {
                dataSourceInputStream = new DataSourceInputStream(statsDataSource, dataSpec);
                byte[] b = f.b(dataSourceInputStream);
                Util.closeQuietly(dataSourceInputStream);
                return b;
            } catch (HttpDataSource$InvalidResponseCodeException e) {
                HttpDataSource$InvalidResponseCodeException httpDataSource$InvalidResponseCodeException = e;
                String redirectUrl = getRedirectUrl(httpDataSource$InvalidResponseCodeException, i2);
                if (redirectUrl != null) {
                    i2++;
                    dataSpec = dataSpec.buildUpon().setUri(redirectUrl).build();
                    Util.closeQuietly(dataSourceInputStream);
                } else {
                    throw httpDataSource$InvalidResponseCodeException;
                }
            } catch (Exception e7) {
                throw new MediaDrmCallbackException(build, statsDataSource.getLastOpenedUri(), statsDataSource.getResponseHeaders(), statsDataSource.getBytesRead(), e7);
            } catch (Throwable th) {
                Throwable th2 = th;
                Util.closeQuietly(dataSourceInputStream);
                throw th2;
            }
        }
    }

    public static int getErrorCodeForMediaDrmException(Throwable th, int i2) {
        if (th instanceof MediaDrm.MediaDrmStateException) {
            return Util.getErrorCodeForMediaDrmErrorCode(Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) th).getDiagnosticInfo()));
        }
        if (Api23.isMediaDrmResetException(th)) {
            return 6006;
        }
        if ((th instanceof NotProvisionedException) || isFailureToConstructNotProvisionedException(th)) {
            return 6002;
        }
        if (th instanceof DeniedByServerException) {
            return 6007;
        }
        if (th instanceof UnsupportedDrmException) {
            return 6001;
        }
        if (th instanceof DefaultDrmSessionManager.MissingSchemeDataException) {
            return 6003;
        }
        if (th instanceof KeysExpiredException) {
            return 6008;
        }
        if (i2 == 1) {
            return 6006;
        }
        if (i2 == 2) {
            return 6004;
        }
        if (i2 == 3) {
            return 6002;
        }
        throw new IllegalArgumentException();
    }

    private static String getRedirectUrl(HttpDataSource$InvalidResponseCodeException httpDataSource$InvalidResponseCodeException, int i2) {
        Map<String, List<String>> map;
        List list;
        int i7 = httpDataSource$InvalidResponseCodeException.responseCode;
        if ((i7 == 307 || i7 == 308) && i2 < 5 && (map = httpDataSource$InvalidResponseCodeException.headerFields) != null && (list = map.get("Location")) != null && !list.isEmpty()) {
            return (String) list.get(0);
        }
        return null;
    }

    public static boolean isFailureToConstructNotProvisionedException(Throwable th) {
        if (Build.VERSION.SDK_INT != 34 || !(th instanceof NoSuchMethodError) || th.getMessage() == null || !th.getMessage().contains("Landroid/media/NotProvisionedException;.<init>(")) {
            return false;
        }
        return true;
    }

    public static boolean isFailureToConstructResourceBusyException(Throwable th) {
        if (Build.VERSION.SDK_INT != 34 || !(th instanceof NoSuchMethodError) || th.getMessage() == null || !th.getMessage().contains("Landroid/media/ResourceBusyException;.<init>(")) {
            return false;
        }
        return true;
    }
}
