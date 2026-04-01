package androidx.media3.datasource;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import androidx.media3.common.util.Util;
import java.io.FileInputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContentDataSource extends BaseDataSource {
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private FileInputStream inputStream;
    private boolean opened;
    private final ContentResolver resolver;
    private Uri uri;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ContentDataSourceException extends DataSourceException {
        public ContentDataSourceException(IOException iOException, int i2) {
            super((Throwable) iOException, i2);
        }
    }

    public ContentDataSource(Context context) {
        super(false);
        this.resolver = context.getContentResolver();
    }

    public void close() {
        this.uri = null;
        try {
            FileInputStream fileInputStream = this.inputStream;
            if (fileInputStream != null) {
                fileInputStream.close();
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
                throw new ContentDataSourceException(e, 2000);
            } catch (Throwable th) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th;
            }
        } catch (IOException e7) {
            throw new ContentDataSourceException(e7, 2000);
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
                throw new ContentDataSourceException(e8, 2000);
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
        return this.uri;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f4, code lost:
        r3 = com.samsung.android.sum.solution.filter.UniImgp.OPTION_PREFERRED_COLOR_FORMAT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f7, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0031, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00fd, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0032, code lost:
        r15 = 2000;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fc A[ExcHandler: ContentDataSourceException (r0v1 'e' androidx.media3.datasource.ContentDataSource$ContentDataSourceException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long open(androidx.media3.datasource.DataSpec r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            java.lang.String r2 = "Could not open file descriptor for: "
            android.net.Uri r4 = r1.uri     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            android.net.Uri r4 = r4.normalizeScheme()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r0.uri = r4     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r16.transferInitializing(r17)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.lang.String r5 = r4.getScheme()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.lang.String r6 = "content"
            boolean r5 = java.util.Objects.equals(r5, r6)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r6 = 1
            if (r5 == 0) goto L_0x0036
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r5.<init>()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.lang.String r7 = "android.provider.extra.ACCEPT_ORIGINAL_MEDIA_FORMAT"
            r5.putBoolean(r7, r6)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            android.content.ContentResolver r7 = r0.resolver     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.lang.String r8 = "*/*"
            android.content.res.AssetFileDescriptor r5 = r7.openTypedAssetFileDescriptor(r4, r8, r5)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            goto L_0x003e
        L_0x0031:
            r0 = move-exception
            r15 = 2000(0x7d0, float:2.803E-42)
            goto L_0x00ee
        L_0x0036:
            android.content.ContentResolver r5 = r0.resolver     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.lang.String r7 = "r"
            android.content.res.AssetFileDescriptor r5 = r5.openAssetFileDescriptor(r4, r7)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
        L_0x003e:
            r0.assetFileDescriptor = r5     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            if (r5 == 0) goto L_0x00d4
            long r7 = r5.getLength()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.io.FileDescriptor r4 = r5.getFileDescriptor()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r2.<init>(r4)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r0.inputStream = r2     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r9 = -1
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r11 = 2008(0x7d8, float:2.814E-42)
            r12 = 0
            if (r4 == 0) goto L_0x0067
            long r13 = r1.position     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            int r13 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r13 > 0) goto L_0x0061
            goto L_0x0067
        L_0x0061:
            androidx.media3.datasource.ContentDataSource$ContentDataSourceException r0 = new androidx.media3.datasource.ContentDataSource$ContentDataSourceException     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r0.<init>(r12, r11)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            throw r0     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
        L_0x0067:
            long r13 = r5.getStartOffset()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r5 = r4
            long r3 = r1.position     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            long r3 = r3 + r13
            long r3 = r2.skip(r3)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            long r3 = r3 - r13
            long r13 = r1.position     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            int r13 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r13 != 0) goto L_0x00ce
            r13 = 0
            if (r5 != 0) goto L_0x009f
            java.nio.channels.FileChannel r2 = r2.getChannel()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            long r3 = r2.size()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            int r5 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r5 != 0) goto L_0x008d
            r0.bytesRemaining = r9     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            goto L_0x00a6
        L_0x008d:
            long r7 = r2.position()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            long r3 = r3 - r7
            r0.bytesRemaining = r3     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            int r2 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r2 < 0) goto L_0x0099
            goto L_0x00a6
        L_0x0099:
            androidx.media3.datasource.ContentDataSource$ContentDataSourceException r0 = new androidx.media3.datasource.ContentDataSource$ContentDataSourceException     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r0.<init>(r12, r11)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            throw r0     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
        L_0x009f:
            long r7 = r7 - r3
            r0.bytesRemaining = r7     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            int r2 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r2 < 0) goto L_0x00c8
        L_0x00a6:
            long r2 = r1.length
            int r4 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r4 == 0) goto L_0x00b9
            long r4 = r0.bytesRemaining
            int r7 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r7 != 0) goto L_0x00b3
            goto L_0x00b7
        L_0x00b3:
            long r2 = java.lang.Math.min(r4, r2)
        L_0x00b7:
            r0.bytesRemaining = r2
        L_0x00b9:
            r0.opened = r6
            r16.transferStarted(r17)
            long r1 = r1.length
            int r3 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r3 == 0) goto L_0x00c5
            return r1
        L_0x00c5:
            long r0 = r0.bytesRemaining
            return r0
        L_0x00c8:
            androidx.media3.datasource.ContentDataSource$ContentDataSourceException r0 = new androidx.media3.datasource.ContentDataSource$ContentDataSourceException     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r0.<init>(r12, r11)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            throw r0     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
        L_0x00ce:
            androidx.media3.datasource.ContentDataSource$ContentDataSourceException r0 = new androidx.media3.datasource.ContentDataSource$ContentDataSourceException     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r0.<init>(r12, r11)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            throw r0     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
        L_0x00d4:
            androidx.media3.datasource.ContentDataSource$ContentDataSourceException r0 = new androidx.media3.datasource.ContentDataSource$ContentDataSourceException     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.io.IOException r1 = new java.io.IOException     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r3.<init>(r2)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r3.append(r4)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            java.lang.String r2 = r3.toString()     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r1.<init>(r2)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x0031 }
            r15 = 2000(0x7d0, float:2.803E-42)
            r0.<init>(r1, r15)     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x00ed }
            throw r0     // Catch:{ ContentDataSourceException -> 0x00fc, IOException -> 0x00ed }
        L_0x00ed:
            r0 = move-exception
        L_0x00ee:
            androidx.media3.datasource.ContentDataSource$ContentDataSourceException r1 = new androidx.media3.datasource.ContentDataSource$ContentDataSourceException
            boolean r2 = r0 instanceof java.io.FileNotFoundException
            if (r2 == 0) goto L_0x00f7
            r3 = 2005(0x7d5, float:2.81E-42)
            goto L_0x00f8
        L_0x00f7:
            r3 = r15
        L_0x00f8:
            r1.<init>(r0, r3)
            throw r1
        L_0x00fc:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.datasource.ContentDataSource.open(androidx.media3.datasource.DataSpec):long");
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
                throw new ContentDataSourceException(e, 2000);
            }
        }
        int read = ((FileInputStream) Util.castNonNull(this.inputStream)).read(bArr, i2, i7);
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
