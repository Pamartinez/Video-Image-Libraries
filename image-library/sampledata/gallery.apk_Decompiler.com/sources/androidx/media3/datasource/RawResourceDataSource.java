package androidx.media3.datasource;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.android.sum.solution.filter.UniImgp;
import i.C0212a;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RawResourceDataSource extends BaseDataSource {
    private final Context applicationContext;
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private DataSpec dataSpec;
    private InputStream inputStream;
    private boolean opened;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RawResourceDataSourceException extends DataSourceException {
        @Deprecated
        public RawResourceDataSourceException(String str) {
            super(str, (Throwable) null, 2000);
        }

        public RawResourceDataSourceException(String str, Throwable th, int i2) {
            super(str, th, i2);
        }
    }

    public RawResourceDataSource(Context context) {
        super(false);
        this.applicationContext = context.getApplicationContext();
    }

    @Deprecated
    public static Uri buildRawResourceUri(int i2) {
        return Uri.parse("rawresource:///" + i2);
    }

    private static AssetFileDescriptor openAssetFileDescriptor(Context context, DataSpec dataSpec2) {
        Resources resources;
        int i2;
        String str;
        Uri normalizeScheme = dataSpec2.uri.normalizeScheme();
        if (TextUtils.equals("rawresource", normalizeScheme.getScheme())) {
            resources = context.getResources();
            List<String> pathSegments = normalizeScheme.getPathSegments();
            if (pathSegments.size() == 1) {
                i2 = parseResourceId(pathSegments.get(0));
            } else {
                throw new RawResourceDataSourceException("rawresource:// URI must have exactly one path element, found " + pathSegments.size());
            }
        } else if (TextUtils.equals("android.resource", normalizeScheme.getScheme())) {
            String str2 = (String) Assertions.checkNotNull(normalizeScheme.getPath());
            if (str2.startsWith("/")) {
                str2 = str2.substring(1);
            }
            if (TextUtils.isEmpty(normalizeScheme.getHost())) {
                str = context.getPackageName();
            } else {
                str = normalizeScheme.getHost();
            }
            if (str.equals(context.getPackageName())) {
                resources = context.getResources();
            } else {
                try {
                    resources = context.getPackageManager().getResourcesForApplication(str);
                } catch (PackageManager.NameNotFoundException e) {
                    throw new RawResourceDataSourceException("Package in android.resource:// URI not found. Check http://g.co/dev/packagevisibility.", e, UniImgp.OPTION_PREFERRED_COLOR_FORMAT);
                }
            }
            if (str2.matches("\\d+")) {
                i2 = parseResourceId(str2);
            } else {
                i2 = resources.getIdentifier(C0212a.B(str, NumericEnum.SEP, str2), "raw", (String) null);
                if (i2 == 0) {
                    throw new RawResourceDataSourceException("Resource not found.", (Throwable) null, UniImgp.OPTION_PREFERRED_COLOR_FORMAT);
                }
            }
        } else {
            throw new RawResourceDataSourceException("Unsupported URI scheme (" + normalizeScheme.getScheme() + "). Only android.resource is supported.", (Throwable) null, 1004);
        }
        try {
            AssetFileDescriptor openRawResourceFd = resources.openRawResourceFd(i2);
            if (openRawResourceFd != null) {
                return openRawResourceFd;
            }
            throw new RawResourceDataSourceException("Resource is compressed: " + normalizeScheme, (Throwable) null, 2000);
        } catch (Resources.NotFoundException e7) {
            throw new RawResourceDataSourceException((String) null, e7, UniImgp.OPTION_PREFERRED_COLOR_FORMAT);
        }
    }

    private static int parseResourceId(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new RawResourceDataSourceException("Resource identifier must be an integer.", (Throwable) null, 1004);
        }
    }

    public void close() {
        this.dataSpec = null;
        try {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            this.inputStream = null;
            try {
                AssetFileDescriptor assetFileDescriptor2 = this.assetFileDescriptor;
                if (assetFileDescriptor2 != null) {
                    assetFileDescriptor2.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
            } catch (IOException e) {
                throw new RawResourceDataSourceException((String) null, e, 2000);
            } catch (Throwable th) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th;
            }
        } catch (IOException e7) {
            throw new RawResourceDataSourceException((String) null, e7, 2000);
        } catch (Throwable th2) {
            this.inputStream = null;
            try {
                AssetFileDescriptor assetFileDescriptor3 = this.assetFileDescriptor;
                if (assetFileDescriptor3 != null) {
                    assetFileDescriptor3.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th2;
            } catch (IOException e8) {
                throw new RawResourceDataSourceException((String) null, e8, 2000);
            } catch (Throwable th3) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th3;
            }
        }
    }

    public Uri getUri() {
        DataSpec dataSpec2 = this.dataSpec;
        if (dataSpec2 != null) {
            return dataSpec2.uri;
        }
        return null;
    }

    public long open(DataSpec dataSpec2) {
        this.dataSpec = dataSpec2;
        transferInitializing(dataSpec2);
        AssetFileDescriptor openAssetFileDescriptor = openAssetFileDescriptor(this.applicationContext, dataSpec2);
        this.assetFileDescriptor = openAssetFileDescriptor;
        long length = openAssetFileDescriptor.getLength();
        FileInputStream fileInputStream = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
        this.inputStream = fileInputStream;
        int i2 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        if (i2 != 0) {
            try {
                if (dataSpec2.position > length) {
                    throw new RawResourceDataSourceException((String) null, (Throwable) null, 2008);
                }
            } catch (RawResourceDataSourceException e) {
                throw e;
            } catch (IOException e7) {
                throw new RawResourceDataSourceException((String) null, e7, 2000);
            }
        }
        long startOffset = this.assetFileDescriptor.getStartOffset();
        long skip = fileInputStream.skip(dataSpec2.position + startOffset) - startOffset;
        if (skip == dataSpec2.position) {
            if (i2 == 0) {
                FileChannel channel = fileInputStream.getChannel();
                if (channel.size() == 0) {
                    this.bytesRemaining = -1;
                } else {
                    long size = channel.size() - channel.position();
                    this.bytesRemaining = size;
                    if (size < 0) {
                        throw new RawResourceDataSourceException((String) null, (Throwable) null, 2008);
                    }
                }
            } else {
                long j2 = length - skip;
                this.bytesRemaining = j2;
                if (j2 < 0) {
                    throw new DataSourceException(2008);
                }
            }
            long j3 = dataSpec2.length;
            if (j3 != -1) {
                long j8 = this.bytesRemaining;
                if (j8 != -1) {
                    j3 = Math.min(j8, j3);
                }
                this.bytesRemaining = j3;
            }
            this.opened = true;
            transferStarted(dataSpec2);
            long j10 = dataSpec2.length;
            if (j10 != -1) {
                return j10;
            }
            return this.bytesRemaining;
        }
        throw new RawResourceDataSourceException((String) null, (Throwable) null, 2008);
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
                throw new RawResourceDataSourceException((String) null, e, 2000);
            }
        }
        int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, i2, i7);
        if (read != -1) {
            long j3 = this.bytesRemaining;
            if (j3 != -1) {
                this.bytesRemaining = j3 - ((long) read);
            }
            bytesTransferred(read);
            return read;
        } else if (this.bytesRemaining == -1) {
            return -1;
        } else {
            throw new RawResourceDataSourceException("End of stream reached having not read sufficient data.", new EOFException(), 2000);
        }
    }
}
