package com.samsung.android.gallery.module.thumbnail.logic;

import A.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.AppResources;
import java.io.BufferedInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UriThumbnailLoaderImpl extends LocalThumbnailLoaderImpl {
    private Context mAppContext;

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap decodeImageThumbnail(com.samsung.android.gallery.module.thumbnail.type.ReqInfo r11, java.io.InputStream r12) {
        /*
            r10 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r11.item
            int r3 = r2.getWidth()
            if (r3 == 0) goto L_0x0012
            int r3 = r2.getHeight()
            if (r3 != 0) goto L_0x0015
        L_0x0012:
            com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil.updateImageInfo((com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface) r2, (java.io.InputStream) r12)
        L_0x0015:
            int r3 = r2.getWidth()
            if (r3 == 0) goto L_0x0021
            int r3 = r2.getHeight()
            if (r3 != 0) goto L_0x0034
        L_0x0021:
            java.lang.String r3 = r10.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "decodeImageThumbnail wrong arg "
            r4.<init>(r5)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            com.samsung.android.gallery.support.utils.Log.w(r3, r4)
        L_0x0034:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r3 = r11.item
            boolean r3 = r3.isStream()
            r4 = 0
            r5 = 0
            if (r3 == 0) goto L_0x008e
            androidx.exifinterface.media.ExifInterface r3 = new androidx.exifinterface.media.ExifInterface     // Catch:{ Error | Exception -> 0x0068 }
            r3.<init>((java.io.InputStream) r12)     // Catch:{ Error | Exception -> 0x0068 }
            byte[] r3 = r3.getThumbnail()     // Catch:{ Error | Exception -> 0x0068 }
            if (r3 == 0) goto L_0x006b
            com.samsung.android.gallery.module.graphics.BitmapOptions r6 = new com.samsung.android.gallery.module.graphics.BitmapOptions     // Catch:{ Error | Exception -> 0x0068 }
            r6.<init>()     // Catch:{ Error | Exception -> 0x0068 }
            boolean r7 = r2.isJpeg()     // Catch:{ Error | Exception -> 0x0068 }
            com.samsung.android.gallery.module.graphics.BitmapOptions r6 = r6.withQuramCodec(r7)     // Catch:{ Error | Exception -> 0x0068 }
            boolean r7 = r2.isHeif()     // Catch:{ Error | Exception -> 0x0068 }
            com.samsung.android.gallery.module.graphics.BitmapOptions r6 = r6.withHeifCodec(r7)     // Catch:{ Error | Exception -> 0x0068 }
            int r7 = r3.length     // Catch:{ Error | Exception -> 0x0066 }
            android.graphics.Bitmap r3 = com.samsung.android.gallery.module.graphics.ImageDecoder.decodeByteArray(r3, r4, r7, r6)     // Catch:{ Error | Exception -> 0x0066 }
            r11.bitmap = r3     // Catch:{ Error | Exception -> 0x0066 }
            goto L_0x0084
        L_0x0066:
            r3 = move-exception
            goto L_0x006d
        L_0x0068:
            r3 = move-exception
            r6 = r5
            goto L_0x006d
        L_0x006b:
            r6 = r5
            goto L_0x0084
        L_0x006d:
            java.lang.String r7 = r10.TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "decodeImageThumbnail#exif failed. e="
            r8.<init>(r9)
            java.lang.String r3 = r3.getMessage()
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            com.samsung.android.gallery.support.utils.Log.w(r7, r3)
        L_0x0084:
            boolean r3 = r12.markSupported()
            if (r3 == 0) goto L_0x008f
            com.samsung.android.gallery.support.utils.Utils.resetSilently(r12)
            goto L_0x008f
        L_0x008e:
            r6 = r5
        L_0x008f:
            android.graphics.Bitmap r3 = r11.bitmap
            if (r3 != 0) goto L_0x00b4
            com.samsung.android.gallery.module.graphics.BitmapOptions r3 = new com.samsung.android.gallery.module.graphics.BitmapOptions
            int r6 = r2.getWidth()
            int r7 = r2.getHeight()
            java.lang.String r2 = r2.getMimeType()
            r3.<init>((int) r6, (int) r7, (java.lang.String) r2)
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r2 = r11.thumbKind
            int r2 = r2.size()
            com.samsung.android.gallery.module.graphics.BitmapOptions r6 = r3.adjustInSampleSize(r2, r4)
            android.graphics.Bitmap r12 = android.graphics.BitmapFactory.decodeStream(r12, r5, r6)
            r11.bitmap = r12
        L_0x00b4:
            java.lang.String r10 = r10.TAG
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r2 = "decodeImageThumbnail "
            r12.<init>(r2)
            r12.append(r6)
            java.lang.String r2 = " +"
            r12.append(r2)
            A.a.x(r12, r0, r10)
            android.graphics.Bitmap r10 = r11.bitmap
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.thumbnail.logic.UriThumbnailLoaderImpl.decodeImageThumbnail(com.samsung.android.gallery.module.thumbnail.type.ReqInfo, java.io.InputStream):android.graphics.Bitmap");
    }

    public Bitmap decodeVideoThumbnail(Uri uri, long j2) {
        return BitmapUtils.getVideoThumbnailFromMeta(getAppContext(), uri, j2);
    }

    public final Context getAppContext() {
        if (this.mAppContext == null) {
            this.mAppContext = AppResources.getAppContext();
        }
        return this.mAppContext;
    }

    public Bitmap getImageThumbnail(ReqInfo reqInfo) {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(getAppContext().getContentResolver().openInputStream(Uri.parse(reqInfo.path)));
            Bitmap decodeImageThumbnail = decodeImageThumbnail(reqInfo, bufferedInputStream);
            bufferedInputStream.close();
            return decodeImageThumbnail;
        } catch (Exception | OutOfMemoryError | StackOverflowError e) {
            a.z(e, new StringBuilder("getImageThumbnail failed e="), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Bitmap getVideoThumbnail(ReqInfo reqInfo) {
        int i2;
        Bitmap decodeVideoThumbnail = decodeVideoThumbnail(Uri.parse(reqInfo.path), reqInfo.getVideoFrameTime());
        if (decodeVideoThumbnail == null) {
            return null;
        }
        ThumbKind thumbKind = reqInfo.thumbKind;
        ThumbKind thumbKind2 = ThumbKind.FULL_NC_KIND;
        if (thumbKind == thumbKind2) {
            i2 = thumbKind2.size();
        } else {
            i2 = ThumbnailHelper.getVideoMaxSize(reqInfo.getItem().getWidth(), reqInfo.getItem().getHeight());
        }
        return BitmapUtils.downsizeVideoBitmap(decodeVideoThumbnail, i2);
    }

    public boolean isImageInfoRequired(ReqInfo reqInfo) {
        return true;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.getStorageType() == StorageType.UriItem) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "UriThumbnailLoaderImpl";
    }

    public void resizeBitmapFromOriginal(ReqInfo reqInfo, BitmapOptions bitmapOptions) {
    }
}
