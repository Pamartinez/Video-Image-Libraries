package androidx.media3.datasource;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AssetDataSource extends BaseDataSource {
    private final AssetManager assetManager;
    private long bytesRemaining;
    private InputStream inputStream;
    private boolean opened;
    private Uri uri;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AssetDataSourceException extends DataSourceException {
        public AssetDataSourceException(Throwable th, int i2) {
            super(th, i2);
        }
    }

    public AssetDataSource(Context context) {
        super(false);
        this.assetManager = context.getAssets();
    }

    public void close() {
        this.uri = null;
        try {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new AssetDataSourceException(e, 2000);
        } catch (Throwable th) {
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
            throw th;
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec dataSpec) {
        int i2;
        try {
            Uri uri2 = dataSpec.uri;
            this.uri = uri2;
            String str = (String) Assertions.checkNotNull(uri2.getPath());
            if (str.startsWith("/android_asset/")) {
                str = str.substring(15);
            } else if (str.startsWith("/")) {
                str = str.substring(1);
            }
            transferInitializing(dataSpec);
            InputStream open = this.assetManager.open(str, 1);
            this.inputStream = open;
            if (open.skip(dataSpec.position) >= dataSpec.position) {
                long j2 = dataSpec.length;
                if (j2 != -1) {
                    this.bytesRemaining = j2;
                } else {
                    long available = (long) this.inputStream.available();
                    this.bytesRemaining = available;
                    if (available == 2147483647L) {
                        this.bytesRemaining = -1;
                    }
                }
                this.opened = true;
                transferStarted(dataSpec);
                return this.bytesRemaining;
            }
            throw new AssetDataSourceException((Throwable) null, 2008);
        } catch (AssetDataSourceException e) {
            throw e;
        } catch (IOException e7) {
            if (e7 instanceof FileNotFoundException) {
                i2 = UniImgp.OPTION_PREFERRED_COLOR_FORMAT;
            } else {
                i2 = 2000;
            }
            throw new AssetDataSourceException(e7, i2);
        }
    }

    public int read(byte[] bArr, int i2, int i7) {
        if (i7 == 0) {
            return 0;
        }
        long j2 = this.bytesRemaining;
        if (j2 == 0) {
            return -1;
        }
        if (j2 != -1) {
            try {
                i7 = (int) Math.min(j2, (long) i7);
            } catch (IOException e) {
                throw new AssetDataSourceException(e, 2000);
            }
        }
        int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, i2, i7);
        if (read == -1) {
            return -1;
        }
        long j3 = this.bytesRemaining;
        if (j3 != -1) {
            this.bytesRemaining = j3 - ((long) read);
        }
        bytesTransferred(read);
        return read;
    }
}
