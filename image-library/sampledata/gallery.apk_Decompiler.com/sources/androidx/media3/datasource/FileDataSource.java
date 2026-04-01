package androidx.media3.datasource;

import android.net.Uri;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import c0.C0086a;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FileDataSource extends BaseDataSource {
    private long bytesRemaining;
    private RandomAccessFile file;
    private boolean opened;
    private Uri uri;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FileDataSourceException extends DataSourceException {
        public FileDataSourceException(Throwable th, int i2) {
            super(th, i2);
        }

        public FileDataSourceException(String str, Throwable th, int i2) {
            super(str, th, i2);
        }
    }

    public FileDataSource() {
        super(false);
    }

    private static RandomAccessFile openLocalFile(Uri uri2) {
        int i2 = 2006;
        try {
            return new RandomAccessFile((String) Assertions.checkNotNull(uri2.getPath()), "r");
        } catch (FileNotFoundException e) {
            if (!TextUtils.isEmpty(uri2.getQuery()) || !TextUtils.isEmpty(uri2.getFragment())) {
                String path = uri2.getPath();
                String query = uri2.getQuery();
                String fragment = uri2.getFragment();
                StringBuilder q = C0086a.q("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=", path, ",query=", query, ",fragment=");
                q.append(fragment);
                throw new FileDataSourceException(q.toString(), e, 1004);
            }
            if (!(e.getCause() instanceof ErrnoException) || ((ErrnoException) e.getCause()).errno != OsConstants.EACCES) {
                i2 = UniImgp.OPTION_PREFERRED_COLOR_FORMAT;
            }
            throw new FileDataSourceException(e, i2);
        } catch (SecurityException e7) {
            throw new FileDataSourceException(e7, 2006);
        } catch (RuntimeException e8) {
            throw new FileDataSourceException(e8, 2000);
        }
    }

    public void close() {
        this.uri = null;
        try {
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            this.file = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new FileDataSourceException(e, 2000);
        } catch (Throwable th) {
            this.file = null;
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
        Uri uri2 = dataSpec.uri;
        this.uri = uri2;
        transferInitializing(dataSpec);
        RandomAccessFile openLocalFile = openLocalFile(uri2);
        this.file = openLocalFile;
        try {
            openLocalFile.seek(dataSpec.position);
            long j2 = dataSpec.length;
            if (j2 == -1) {
                j2 = this.file.length() - dataSpec.position;
            }
            this.bytesRemaining = j2;
            if (j2 >= 0) {
                this.opened = true;
                transferStarted(dataSpec);
                return this.bytesRemaining;
            }
            throw new FileDataSourceException((String) null, (Throwable) null, 2008);
        } catch (IOException e) {
            throw new FileDataSourceException(e, 2000);
        }
    }

    public int read(byte[] bArr, int i2, int i7) {
        if (i7 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int read = ((RandomAccessFile) Util.castNonNull(this.file)).read(bArr, i2, (int) Math.min(this.bytesRemaining, (long) i7));
            if (read > 0) {
                this.bytesRemaining -= (long) read;
                bytesTransferred(read);
            }
            return read;
        } catch (IOException e) {
            throw new FileDataSourceException(e, 2000);
        }
    }
}
