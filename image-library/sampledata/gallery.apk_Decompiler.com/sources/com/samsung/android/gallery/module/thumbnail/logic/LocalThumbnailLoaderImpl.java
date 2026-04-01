package com.samsung.android.gallery.module.thumbnail.logic;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.exception.NativeCrashLogger;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SafeMode;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalThumbnailLoaderImpl extends AbsThumbnailLoaderImpl {
    private final boolean REMOVE_HDR_GAINMAP;

    public LocalThumbnailLoaderImpl() {
        boolean z;
        if (Build.VERSION.SDK_INT >= 34) {
            z = true;
        } else {
            z = false;
        }
        this.REMOVE_HDR_GAINMAP = z;
    }

    private Bitmap cropBitmap(ReqInfo reqInfo) {
        int i2;
        int width = reqInfo.bitmap.getWidth();
        int height = reqInfo.bitmap.getHeight();
        int i7 = 0;
        if (width > height) {
            float max = Math.max(1.0f, (((float) reqInfo.imageHeight) * ((float) width)) / ((float) reqInfo.imageWidth));
            int round = Math.round((((float) height) - max) / 2.0f);
            i2 = round;
            height = Math.round(((float) round) + max);
        } else {
            float max2 = Math.max(1.0f, (((float) reqInfo.imageWidth) * ((float) height)) / ((float) reqInfo.imageHeight));
            int round2 = Math.round((((float) width) - max2) / 2.0f);
            i7 = round2;
            width = Math.round(((float) round2) + max2);
            i2 = 0;
        }
        return BitmapUtils.crop(reqInfo.bitmap, new Rect(i7, i2, width, height));
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0119 A[Catch:{ OutOfMemoryError -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0134 A[Catch:{ OutOfMemoryError -> 0x0021 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap getBitmapFromOriginal(com.samsung.android.gallery.module.thumbnail.type.ReqInfo r8) {
        /*
            r7 = this;
            java.lang.String r0 = "from org : "
            java.lang.String r1 = "getBitmapFromOriginal : "
            java.lang.String r2 = "getBitmapFromOriginal: invalid options "
            r3 = 0
            boolean r4 = r7.isImageInfoRequired(r8)     // Catch:{ OutOfMemoryError -> 0x003c }
            if (r4 == 0) goto L_0x0040
            java.lang.String r4 = r8.path     // Catch:{ OutOfMemoryError -> 0x003c }
            com.samsung.android.gallery.module.graphics.BitmapOptions r4 = com.samsung.android.gallery.module.graphics.BitmapOptionsFactory.parse(r4)     // Catch:{ OutOfMemoryError -> 0x003c }
            int r5 = r4.outHeight     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r5 <= 0) goto L_0x0024
            int r6 = r4.outWidth     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r6 > 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            r8.imageWidth = r6     // Catch:{ OutOfMemoryError -> 0x0021 }
            r8.imageHeight = r5     // Catch:{ OutOfMemoryError -> 0x0021 }
            goto L_0x0076
        L_0x0021:
            r0 = move-exception
            goto L_0x0154
        L_0x0024:
            java.lang.String r0 = r7.TAG     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0021 }
            r1.<init>(r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.String r2 = r7.getThumbnailItemInfo(r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            r1.append(r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.String r1 = r1.toString()     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.support.utils.Log.w(r0, r1)     // Catch:{ OutOfMemoryError -> 0x0021 }
            return r3
        L_0x003c:
            r0 = move-exception
            r4 = r3
            goto L_0x0154
        L_0x0040:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x003c }
            boolean r2 = r2.isJpeg()     // Catch:{ OutOfMemoryError -> 0x003c }
            if (r2 == 0) goto L_0x0058
            com.samsung.android.gallery.module.graphics.BitmapOptions r4 = new com.samsung.android.gallery.module.graphics.BitmapOptions     // Catch:{ OutOfMemoryError -> 0x003c }
            int r2 = r8.imageWidth     // Catch:{ OutOfMemoryError -> 0x003c }
            int r5 = r8.imageHeight     // Catch:{ OutOfMemoryError -> 0x003c }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r6 = r8.item     // Catch:{ OutOfMemoryError -> 0x003c }
            java.lang.String r6 = r6.getMimeType()     // Catch:{ OutOfMemoryError -> 0x003c }
            r4.<init>((int) r2, (int) r5, (java.lang.String) r6)     // Catch:{ OutOfMemoryError -> 0x003c }
            goto L_0x0076
        L_0x0058:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x003c }
            boolean r2 = r2.isRawImage()     // Catch:{ OutOfMemoryError -> 0x003c }
            if (r2 == 0) goto L_0x0067
            java.lang.String r2 = r8.path     // Catch:{ OutOfMemoryError -> 0x003c }
            com.samsung.android.gallery.module.graphics.BitmapOptions r4 = com.samsung.android.gallery.module.graphics.BitmapOptionsFactory.parse(r2)     // Catch:{ OutOfMemoryError -> 0x003c }
            goto L_0x0076
        L_0x0067:
            com.samsung.android.gallery.module.graphics.BitmapOptions r4 = new com.samsung.android.gallery.module.graphics.BitmapOptions     // Catch:{ OutOfMemoryError -> 0x003c }
            int r2 = r8.imageWidth     // Catch:{ OutOfMemoryError -> 0x003c }
            int r5 = r8.imageHeight     // Catch:{ OutOfMemoryError -> 0x003c }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r6 = r8.item     // Catch:{ OutOfMemoryError -> 0x003c }
            java.lang.String r6 = r6.getMimeType()     // Catch:{ OutOfMemoryError -> 0x003c }
            r4.<init>((int) r2, (int) r5, (java.lang.String) r6)     // Catch:{ OutOfMemoryError -> 0x003c }
        L_0x0076:
            int r2 = r7.getInSampleSizeFromOriginal(r8, r4)     // Catch:{ OutOfMemoryError -> 0x0021 }
            r4.inSampleSize = r2     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.support.config.SdkConfig$SEM r2 = com.samsung.android.gallery.support.config.SdkConfig.SEM.T_MR1     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.SEM) r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 != 0) goto L_0x00c1
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            int r2 = r2.getWidth()     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r5 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            int r5 = r5.getHeight()     // Catch:{ OutOfMemoryError -> 0x0021 }
            int r2 = r2 * r5
            r5 = 600000000(0x23c34600, float:2.1171589E-17)
            if (r2 <= r5) goto L_0x00c1
            java.lang.String r0 = r7.TAG     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.String r1 = "skip high resolution original decoding"
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            long r5 = r2.getFileId()     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r5 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            int r5 = r5.getWidth()     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r6 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            int r6 = r6.getHeight()     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r5, r6, r4}     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r0, (java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            return r3
        L_0x00c1:
            boolean r2 = r8.isHeif     // Catch:{ OutOfMemoryError -> 0x0021 }
            r5 = 1
            if (r2 != 0) goto L_0x00fb
            boolean r2 = com.samsung.android.gallery.module.graphics.CodecCompat.PATCH_HEIF_ALL_FILES     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 == 0) goto L_0x00db
            java.lang.String r2 = r4.outMimeType     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = com.samsung.android.gallery.database.dbtype.MimeType.isHeif(r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 != 0) goto L_0x00fb
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = com.samsung.android.gallery.module.graphics.CodecCompat.isHeif(r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 == 0) goto L_0x00db
            goto L_0x00fb
        L_0x00db:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = r2.isQuramDecodable()     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 != 0) goto L_0x00ed
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = r2.isCloudOnly()     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 == 0) goto L_0x00ec
            goto L_0x00ed
        L_0x00ec:
            r5 = 0
        L_0x00ed:
            com.samsung.android.gallery.module.graphics.BitmapOptions r2 = r4.withQuramCodec(r5)     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r5 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            long r5 = r5.getFileSize()     // Catch:{ OutOfMemoryError -> 0x0021 }
            r2.withHeap((long) r5)     // Catch:{ OutOfMemoryError -> 0x0021 }
            goto L_0x0115
        L_0x00fb:
            r4.withHeifCodec(r5)     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = com.samsung.android.gallery.module.graphics.CodecCompat.PATCH_HEIF_FILE_TRANSCODING     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 == 0) goto L_0x0115
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = r2.isCamModelSamsung()     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 != 0) goto L_0x0115
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item     // Catch:{ OutOfMemoryError -> 0x0021 }
            boolean r2 = com.samsung.android.gallery.module.graphics.CodecCompat.fixHeifSyntax(r2)     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 != 0) goto L_0x0115
            r4.withAndroidCodec(r5)     // Catch:{ OutOfMemoryError -> 0x0021 }
        L_0x0115:
            boolean r2 = com.samsung.android.gallery.support.utils.Logger.THUMBNAIL     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 == 0) goto L_0x012a
            java.lang.String r2 = r7.TAG     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0021 }
            r5.<init>(r1)     // Catch:{ OutOfMemoryError -> 0x0021 }
            r5.append(r4)     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.String r1 = r5.toString()     // Catch:{ OutOfMemoryError -> 0x0021 }
            com.samsung.android.gallery.support.utils.Log.v(r2, r1)     // Catch:{ OutOfMemoryError -> 0x0021 }
        L_0x012a:
            java.lang.String r1 = r8.path     // Catch:{ OutOfMemoryError -> 0x0021 }
            android.graphics.Bitmap r1 = com.samsung.android.gallery.module.graphics.ImageDecoder.decodeFile(r1, r4)     // Catch:{ OutOfMemoryError -> 0x0021 }
            r8.bitmap = r1     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r1 == 0) goto L_0x0185
            boolean r2 = r7.REMOVE_HDR_GAINMAP     // Catch:{ OutOfMemoryError -> 0x0021 }
            if (r2 == 0) goto L_0x013b
            com.samsung.android.gallery.module.graphics.BitmapUtils.clearGainmap(r1)     // Catch:{ OutOfMemoryError -> 0x0021 }
        L_0x013b:
            r7.resizeBitmapFromOriginal(r8, r4)     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0021 }
            r1.<init>(r0)     // Catch:{ OutOfMemoryError -> 0x0021 }
            int r0 = r4.inSampleSize     // Catch:{ OutOfMemoryError -> 0x0021 }
            r1.append(r0)     // Catch:{ OutOfMemoryError -> 0x0021 }
            java.lang.String r0 = r1.toString()     // Catch:{ OutOfMemoryError -> 0x0021 }
            r8.mDecodeInfo = r0     // Catch:{ OutOfMemoryError -> 0x0021 }
            r0 = 256(0x100, float:3.59E-43)
            r8.removeDecodeStatus(r0)     // Catch:{ OutOfMemoryError -> 0x0021 }
            goto L_0x0185
        L_0x0154:
            java.lang.String r7 = r7.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "OOM req="
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r2 = "\nitem="
            r1.append(r2)
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r8.item
            r1.append(r2)
            java.lang.String r2 = "\noptions="
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r7, (java.lang.String) r1, (java.lang.Throwable) r0)
            r8.bitmap = r3
            com.samsung.android.gallery.support.exception.InternalException r7 = new com.samsung.android.gallery.support.exception.InternalException
            java.lang.String r0 = "OOM at THUMB DECODE ORIGINAL"
            r7.<init>(r0)
            r7.post()
        L_0x0185:
            android.graphics.Bitmap r7 = r8.bitmap
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.thumbnail.logic.LocalThumbnailLoaderImpl.getBitmapFromOriginal(com.samsung.android.gallery.module.thumbnail.type.ReqInfo):android.graphics.Bitmap");
    }

    private String getThumbnailItemInfo(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface == null) {
            return "MediaItem{null}";
        }
        return "MediaItem{i," + thumbnailInterface.getMediaId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + thumbnailInterface.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + thumbnailInterface.getAlbumID() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + thumbnailInterface.getStorageType() + "}";
    }

    private Bitmap padBitmap(ReqInfo reqInfo) {
        int i2;
        int i7;
        if (reqInfo.bitmap.getWidth() == 112) {
            i7 = 8;
            i2 = 0;
        } else if (reqInfo.bitmap.getHeight() == 112) {
            i2 = 8;
            i7 = 0;
        } else {
            i7 = 0;
            i2 = 0;
        }
        return BitmapUtils.pad(reqInfo.bitmap, i7, i2, 0, 0, 0);
    }

    public Bitmap applyAdjustPolicy(ReqInfo reqInfo, float f) {
        if ((reqInfo.bitmap.getWidth() > reqInfo.bitmap.getHeight() && f > 0.03f) || (reqInfo.bitmap.getWidth() < reqInfo.bitmap.getHeight() && f < -0.03f)) {
            reqInfo.bitmap = cropBitmap(reqInfo);
        } else if ((reqInfo.bitmap.getWidth() == 160 && reqInfo.bitmap.getHeight() == 112) || (reqInfo.bitmap.getWidth() == 112 && reqInfo.bitmap.getHeight() == 160)) {
            reqInfo.bitmap = padBitmap(reqInfo);
        }
        return reqInfo.bitmap;
    }

    public float compareAspectRatio(ReqInfo reqInfo) {
        if (!(reqInfo.imageWidth == 0 || reqInfo.imageHeight == 0 || reqInfo.bitmap.getHeight() == 0)) {
            float width = (((float) reqInfo.imageWidth) / ((float) reqInfo.imageHeight)) - (((float) reqInfo.bitmap.getWidth()) / ((float) reqInfo.bitmap.getHeight()));
            if (Math.abs(width) > 0.01f) {
                return width;
            }
        }
        return 0.0f;
    }

    public Bitmap getImageThumbnail(ReqInfo reqInfo) {
        boolean z;
        Bitmap bitmap;
        int inSampleSize;
        long currentTimeMillis = System.currentTimeMillis();
        NativeCrashLogger.log(reqInfo.item.toString());
        if (reqInfo.item.getFileSize() > 104857600) {
            Log.w(this.TAG, "getImageThumbnail : too big " + Logger.v(Long.valueOf(reqInfo.item.getFileId()), Long.valueOf(reqInfo.item.getFileSize()), Integer.valueOf(reqInfo.item.getWidth()), Integer.valueOf(reqInfo.item.getHeight())) + Logger.getEncodedString(reqInfo.item.getPath()));
        }
        if (reqInfo.isExifDecodable()) {
            if (SafeMode.ENABLED) {
                Log.e("SafeMode", "decode thumbnail(e) #" + reqInfo.item.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + reqInfo.item.getPath());
            }
            if (reqInfo.item.isJpeg()) {
                bitmap = getThumbnailFromJpeg(reqInfo);
                z = true;
            } else {
                z = false;
                if (reqInfo.isHeif) {
                    bitmap = getThumbnailFromHeif(reqInfo);
                } else if (reqInfo.item.isQuramDng()) {
                    bitmap = getThumbnailFromDng(reqInfo);
                } else {
                    bitmap = null;
                }
            }
            if (!z && bitmap != null && (inSampleSize = getInSampleSize(reqInfo.thumbKind, bitmap.getWidth(), bitmap.getHeight(), reqInfo.thumbKind.lowerBound())) > 1) {
                Bitmap resize = BitmapUtils.resize(bitmap, 1.0f / ((float) inSampleSize));
                if (resize != bitmap) {
                    BitmapUtils.putBitmap(bitmap);
                }
                bitmap = resize;
            }
            if (SafeMode.ENABLED) {
                Log.d("SafeMode", "decode thumbnail(e) #" + reqInfo.item.getFileId() + " done");
            }
            if (checkInterrupted(reqInfo, bitmap)) {
                return null;
            }
            if (bitmap != null) {
                reqInfo.addDecodeStatus(256);
                return bitmap;
            }
        }
        if (SafeMode.ENABLED) {
            Log.i("SafeMode", "decode thumbnail(f) #" + reqInfo.item.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + reqInfo.item.getPath());
            return null;
        }
        Bitmap bitmapFromOriginal = getBitmapFromOriginal(reqInfo);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 499) {
            Log.d(this.TAG, "getImageThumbnail {" + toDebugString(reqInfo, bitmapFromOriginal) + "} +" + currentTimeMillis2 + "");
        }
        return bitmapFromOriginal;
    }

    public int getInSampleSizeFromOriginal(ReqInfo reqInfo, BitmapOptions bitmapOptions) {
        return getInSampleSize(reqInfo.thumbKind, bitmapOptions.outWidth, bitmapOptions.outHeight, reqInfo.targetSize);
    }

    public Bitmap getThumbnail(ReqInfo reqInfo) {
        ThumbnailInterface item = reqInfo.getItem();
        if (TextUtils.isEmpty(item.getPath())) {
            return null;
        }
        if (item.isVideo()) {
            return getVideoThumbnail(reqInfo);
        }
        return getImageThumbnail(reqInfo);
    }

    public Bitmap getThumbnailFromDng(ReqInfo reqInfo) {
        Bitmap decodeDngThumbnail = ImageDecoder.decodeDngThumbnail(reqInfo.path, reqInfo.item.getDngVersion());
        if (decodeDngThumbnail == null) {
            j.y(new StringBuilder("getThumbnailFromDng. no preview Image for thumbnail"), getThumbnailItemInfo(reqInfo.item), this.TAG);
            return null;
        }
        reqInfo.mDecodeInfo = "from dng thumb";
        return decodeDngThumbnail;
    }

    public Bitmap getThumbnailFromHeif(ReqInfo reqInfo) {
        Bitmap thumbnailFromHeif = SeApiCompat.getThumbnailFromHeif(reqInfo.path);
        if (thumbnailFromHeif != null) {
            reqInfo.mDecodeInfo = "from heif exif";
        }
        reqInfo.bitmap = thumbnailFromHeif;
        return thumbnailFromHeif;
    }

    public Bitmap getThumbnailFromJpeg(ReqInfo reqInfo) {
        byte[] bArr;
        boolean z;
        ThumbKind thumbKind = reqInfo.thumbKind;
        if (thumbKind == ThumbKind.XLARGE_NC_KIND || thumbKind == ThumbKind.LARGE_NC_KIND) {
            if (Logger.THUMBNAIL) {
                Log.v(this.TAG, "skip exif for no-cache-kind " + reqInfo.thumbKind);
            }
            return null;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(reqInfo.path);
            if (exifInterface.hasThumbnail()) {
                bArr = exifInterface.getThumbnail();
            } else {
                bArr = null;
            }
            if (!(bArr == null || bArr.length == 0)) {
                BitmapOptions parse = BitmapOptionsFactory.parse(bArr, 0, bArr.length);
                if (parse.outWidth <= 0 || parse.outHeight <= 0) {
                    Log.w(this.TAG, "getThumbnailFromJpeg invalid options " + reqInfo.path);
                } else {
                    if (reqInfo.thumbKind.size() > ThumbKind.XSMALL_KIND.size()) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z && Math.min(parse.outWidth, parse.outHeight) < reqInfo.thumbKind.lowerBound()) {
                        if (Logger.THUMBNAIL) {
                            Log.v(this.TAG, "getThumbnailFromJpeg not enough size {" + reqInfo.thumbKind + GlobalPostProcInternalPPInterface.SPLIT_REGEX + reqInfo.targetSize + GlobalPostProcInternalPPInterface.SPLIT_REGEX + parse.outWidth + "x" + parse.outHeight + "}");
                        }
                        reqInfo.addDecodeStatus(16);
                    }
                    parse.inSampleSize = getInSampleSize(reqInfo.thumbKind, parse.outWidth, parse.outHeight, reqInfo.targetSize);
                    Bitmap decodeByteArray = ImageDecoder.decodeByteArray(bArr, 0, bArr.length, parse);
                    reqInfo.bitmap = decodeByteArray;
                    if (decodeByteArray != null) {
                        if (z) {
                            float compareAspectRatio = compareAspectRatio(reqInfo);
                            if (compareAspectRatio != 0.0f) {
                                if (Logger.THUMBNAIL) {
                                    Log.e(this.TAG, "getThumbnailFromJpeg exif thumbnail ratio is different with original image" + reqInfo.path);
                                }
                                reqInfo.addDecodeStatus(32);
                                reqInfo.bitmap = applyAdjustPolicy(reqInfo, compareAspectRatio);
                            }
                        }
                        reqInfo.mDecodeInfo = "from jpg exif : " + parse.inSampleSize;
                    } else {
                        Log.e(this.TAG, "getThumbnailFromJpeg failed");
                    }
                    return reqInfo.bitmap;
                }
            }
            return null;
        } catch (Error | Exception e) {
            Log.e(this.TAG, "getThumbnailFromJpeg failed " + reqInfo.path + ". e=" + e.getMessage());
            return null;
        }
    }

    public boolean isImageInfoRequired(ReqInfo reqInfo) {
        if (reqInfo.imageWidth <= 0 || reqInfo.imageHeight <= 0) {
            return true;
        }
        return false;
    }

    public void resizeBitmapFromOriginal(ReqInfo reqInfo, BitmapOptions bitmapOptions) {
        float max;
        float f;
        Bitmap bitmap = reqInfo.bitmap;
        if (bitmap != null && reqInfo.thumbKind == ThumbKind.MEDIUM_KIND) {
            int width = bitmap.getWidth();
            int height = reqInfo.bitmap.getHeight();
            if (BitmapUtils.isPanoramic(width, height)) {
                trimPanoramicBitmap(reqInfo, width, height);
            } else if (Math.max(width, height) > 640) {
                boolean isAlmostPanoramic = BitmapUtils.isAlmostPanoramic(width, height);
                if (isAlmostPanoramic) {
                    max = (float) Math.min(width, height);
                    f = 240.0f;
                } else {
                    max = (float) Math.max(width, height);
                    f = 512.0f;
                }
                float f5 = f / max;
                if (f5 < 1.0f) {
                    reqInfo.bitmap = BitmapUtils.resize(reqInfo.bitmap, Math.round(((float) width) * f5), Math.round(f5 * ((float) height)));
                    if (isAlmostPanoramic) {
                        String str = this.TAG;
                        Log.d(str, "resizeBitmap#panoramic {" + reqInfo.thumbKind + GlobalPostProcInternalPPInterface.SPLIT_REGEX + reqInfo.item.getFileId() + reqInfo.item.getWidth() + "x" + reqInfo.item.getHeight() + "} " + Logger.toSimpleString(reqInfo.bitmap));
                    }
                }
            }
        }
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        StorageType storageType = thumbnailInterface.getStorageType();
        if (storageType == StorageType.Local || storageType == StorageType.LocalCloud || storageType == StorageType.PrivateItem || storageType == StorageType.TempItem) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "LocalThumbnailLoaderImpl";
    }

    public void trimPanoramicBitmap(ReqInfo reqInfo, int i2, int i7) {
        Rect rect;
        Bitmap bitmap = reqInfo.bitmap;
        long currentTimeMillis = System.currentTimeMillis();
        if (i7 > i2) {
            int round = Math.round((((float) i7) - (((float) i2) * 6.0f)) * 0.5f);
            rect = new Rect(0, round, i2, i7 - round);
            i7 = rect.height();
        } else {
            int round2 = Math.round((((float) i2) - (((float) i7) * 6.0f)) * 0.5f);
            rect = new Rect(round2, 0, i2 - round2, i7);
            i2 = rect.width();
        }
        float min = 240.0f / ((float) Math.min(i2, i7));
        if (min < 1.0f) {
            i2 = Math.round(((float) rect.width()) * min);
            i7 = Math.round(min * ((float) rect.height()));
        }
        Bitmap resizeAndCrop = BitmapUtils.resizeAndCrop(bitmap, rect, i2, i7);
        if (resizeAndCrop != null) {
            reqInfo.bitmap = resizeAndCrop;
        }
        String str = this.TAG;
        Log.d(str, "trimPanoramicBitmap {" + reqInfo.item.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + reqInfo.thumbKind + GlobalPostProcInternalPPInterface.SPLIT_REGEX + reqInfo.item.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + reqInfo.item.getHeight() + "} " + Logger.toSimpleString(reqInfo.bitmap) + " +" + (System.currentTimeMillis() - currentTimeMillis));
    }
}
