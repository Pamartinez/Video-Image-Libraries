package com.samsung.android.gallery.module.thumbnail.logic;

import A.a;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.io.ByteArrayInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StreamThumbnailLoaderImpl extends UriThumbnailLoaderImpl {
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0052 A[Catch:{ all -> 0x0023, all -> 0x0082, Exception -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e A[Catch:{ all -> 0x0023, all -> 0x0082, Exception -> 0x007c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap decodeVideoThumbnail(com.samsung.android.gallery.module.thumbnail.type.ReqInfo r6, byte[] r7) {
        /*
            r5 = this;
            if (r7 == 0) goto L_0x0096
            int r0 = r7.length
            if (r0 != 0) goto L_0x0007
            goto L_0x0096
        L_0x0007:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r0 = r6.item
            android.media.MediaMetadataRetriever r1 = new android.media.MediaMetadataRetriever     // Catch:{ Exception -> 0x007c }
            r1.<init>()     // Catch:{ Exception -> 0x007c }
            com.samsung.android.gallery.module.thumbnail.logic.StreamThumbnailLoaderImpl$MediaDataSourceImpl r2 = new com.samsung.android.gallery.module.thumbnail.logic.StreamThumbnailLoaderImpl$MediaDataSourceImpl     // Catch:{ all -> 0x0023 }
            r2.<init>(r7)     // Catch:{ all -> 0x0023 }
            r1.setDataSource(r2)     // Catch:{ all -> 0x0023 }
            int r7 = r0.getWidth()     // Catch:{ all -> 0x0023 }
            if (r7 == 0) goto L_0x0025
            int r7 = r0.getHeight()     // Catch:{ all -> 0x0023 }
            if (r7 != 0) goto L_0x004a
            goto L_0x0025
        L_0x0023:
            r7 = move-exception
            goto L_0x007e
        L_0x0025:
            r7 = 18
            java.lang.String r7 = r1.extractMetadata(r7)     // Catch:{ all -> 0x0023 }
            r2 = 0
            int r7 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r7, r2)     // Catch:{ all -> 0x0023 }
            r3 = 19
            java.lang.String r3 = r1.extractMetadata(r3)     // Catch:{ all -> 0x0023 }
            int r3 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r3, r2)     // Catch:{ all -> 0x0023 }
            r4 = 24
            java.lang.String r4 = r1.extractMetadata(r4)     // Catch:{ all -> 0x0023 }
            int r2 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r4, r2)     // Catch:{ all -> 0x0023 }
            r0.setSize(r7, r3)     // Catch:{ all -> 0x0023 }
            r0.setOrientation(r2)     // Catch:{ all -> 0x0023 }
        L_0x004a:
            android.graphics.Bitmap r7 = com.samsung.android.gallery.module.graphics.BitmapUtils.decodeEmbeddedInVideo(r1)     // Catch:{ all -> 0x0023 }
            r6.bitmap = r7     // Catch:{ all -> 0x0023 }
            if (r7 != 0) goto L_0x005a
            r2 = 0
            android.graphics.Bitmap r7 = r5.getVideoThumbnail(r1, r2)     // Catch:{ all -> 0x0023 }
            r6.bitmap = r7     // Catch:{ all -> 0x0023 }
        L_0x005a:
            android.graphics.Bitmap r7 = r6.bitmap     // Catch:{ all -> 0x0023 }
            if (r7 == 0) goto L_0x0078
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r0 = r6.getItem()     // Catch:{ all -> 0x0023 }
            int r0 = r0.getWidth()     // Catch:{ all -> 0x0023 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r6.getItem()     // Catch:{ all -> 0x0023 }
            int r2 = r2.getHeight()     // Catch:{ all -> 0x0023 }
            int r0 = com.samsung.android.gallery.module.thumbnail.logic.ThumbnailHelper.getVideoMaxSize(r0, r2)     // Catch:{ all -> 0x0023 }
            android.graphics.Bitmap r7 = com.samsung.android.gallery.module.graphics.BitmapUtils.downsizeVideoBitmap(r7, r0)     // Catch:{ all -> 0x0023 }
            r6.bitmap = r7     // Catch:{ all -> 0x0023 }
        L_0x0078:
            r1.close()     // Catch:{ Exception -> 0x007c }
            goto L_0x0093
        L_0x007c:
            r7 = move-exception
            goto L_0x0087
        L_0x007e:
            r1.close()     // Catch:{ all -> 0x0082 }
            goto L_0x0086
        L_0x0082:
            r0 = move-exception
            r7.addSuppressed(r0)     // Catch:{ Exception -> 0x007c }
        L_0x0086:
            throw r7     // Catch:{ Exception -> 0x007c }
        L_0x0087:
            java.lang.String r5 = r5.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "decodeVideoThumbnail failed. e="
            r0.<init>(r1)
            A.a.s(r7, r0, r5)
        L_0x0093:
            android.graphics.Bitmap r5 = r6.bitmap
            return r5
        L_0x0096:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.thumbnail.logic.StreamThumbnailLoaderImpl.decodeVideoThumbnail(com.samsung.android.gallery.module.thumbnail.type.ReqInfo, byte[]):android.graphics.Bitmap");
    }

    public Bitmap getImageThumbnail(ReqInfo reqInfo) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(reqInfo.item.getByteArray());
            Bitmap decodeImageThumbnail = decodeImageThumbnail(reqInfo, byteArrayInputStream);
            byteArrayInputStream.close();
            return decodeImageThumbnail;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("getImageThumbnail failed. e="), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Bitmap getVideoThumbnail(ReqInfo reqInfo) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Bitmap decodeVideoThumbnail = decodeVideoThumbnail(reqInfo, reqInfo.item.getByteArray());
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis2 > 280) {
                String str = this.TAG;
                Log.d(str, "getVideoThumbnail " + Logger.toSimpleString(reqInfo.bitmap) + " +" + currentTimeMillis2);
            }
            return decodeVideoThumbnail;
        } catch (Error | Exception e) {
            String str2 = this.TAG;
            Log.e(str2, "getImageThumbnail failed. e=" + e.getMessage());
            long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis3 <= 280) {
                return null;
            }
            String str3 = this.TAG;
            Log.d(str3, "getVideoThumbnail " + Logger.toSimpleString(reqInfo.bitmap) + " +" + currentTimeMillis3);
            return null;
        } catch (Throwable th) {
            long currentTimeMillis4 = System.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis4 > 280) {
                String str4 = this.TAG;
                Log.d(str4, "getVideoThumbnail " + Logger.toSimpleString(reqInfo.bitmap) + " +" + currentTimeMillis4);
            }
            throw th;
        }
    }

    public boolean isImageInfoRequired(ReqInfo reqInfo) {
        return true;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.getStorageType() == StorageType.Stream) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "StreamThumbnailLoaderImpl";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaDataSourceImpl extends MediaDataSource {
        final byte[] data;

        public MediaDataSourceImpl(byte[] bArr) {
            this.data = bArr;
        }

        public long getSize() {
            return (long) this.data.length;
        }

        public int readAt(long j2, byte[] bArr, int i2, int i7) {
            byte[] bArr2 = this.data;
            if (((long) i7) + j2 > ((long) bArr2.length)) {
                i7 = bArr2.length - ((int) j2);
            }
            System.arraycopy(bArr2, (int) j2, bArr, i2, i7);
            return i7;
        }

        public void close() {
        }
    }

    public void resizeBitmapFromOriginal(ReqInfo reqInfo, BitmapOptions bitmapOptions) {
    }
}
