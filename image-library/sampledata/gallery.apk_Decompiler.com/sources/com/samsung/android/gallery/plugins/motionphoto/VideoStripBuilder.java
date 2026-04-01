package com.samsung.android.gallery.plugins.motionphoto;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RectUtils;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoStripBuilder {
    private static final BitmapCacheMgr<String> sMemoryCacheManager = new BitmapCacheMgr<>(6, (AbsCacheMgr$EvictListener) null);
    private final String cacheKey;
    private boolean diskCacheEnabled;
    private final ThumbnailInterface item;
    private int option = 2;
    private final List<Bitmap> output;
    private int thumbnailSize = 224;

    public VideoStripBuilder(ThumbnailInterface thumbnailInterface, List<Bitmap> list) {
        this.item = thumbnailInterface;
        this.output = list;
        this.cacheKey = "mp0" + list.size() + thumbnailInterface.getDiskCacheKey() + thumbnailInterface.getDateModified();
    }

    private boolean loadCache(String str, List<Bitmap> list) {
        BitmapCacheMgr<String> bitmapCacheMgr = sMemoryCacheManager;
        Bitmap bitmap = (Bitmap) bitmapCacheMgr.getCache(str);
        if (bitmap == null && this.diskCacheEnabled && (bitmap = BitmapCache.getDiskCache(6, str.getBytes())) != null) {
            bitmapCacheMgr.addCache(str, bitmap);
        }
        if (bitmap == null) {
            return false;
        }
        int size = list.size();
        int width = bitmap.getWidth() / size;
        for (int i2 = 0; i2 < size; i2++) {
            list.set(i2, BitmapUtils.createBitmap(bitmap, width * i2, 0, width, bitmap.getHeight()));
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v5, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean parseVideo(android.media.MediaMetadataRetriever r18, int r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            com.samsung.android.gallery.support.utils.TimeTickLog r2 = new com.samsung.android.gallery.support.utils.TimeTickLog
            r2.<init>()
            java.util.List<android.graphics.Bitmap> r3 = r0.output
            int r3 = r3.size()
            int r4 = r19 + -100
            int r4 = r4 / r3
            long[] r5 = new long[r3]
            r6 = 0
            r7 = r6
        L_0x0016:
            if (r7 >= r3) goto L_0x0023
            long r8 = (long) r4
            long r10 = (long) r7
            long r8 = r8 * r10
            r10 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 * r10
            r5[r7] = r8
            int r7 = r7 + 1
            goto L_0x0016
        L_0x0023:
            r4 = r6
        L_0x0024:
            r7 = 125(0x7d, float:1.75E-43)
            java.lang.String r8 = "VideoStripBuilder"
            r9 = 44
            if (r4 >= r3) goto L_0x005f
            r10 = r5[r4]
            int r12 = r0.option
            android.graphics.Bitmap r10 = com.samsung.android.gallery.support.library.SeApiCompat.getVideoFrameAtTime(r1, r10, r12)
            if (r10 == 0) goto L_0x0040
            java.util.List<android.graphics.Bitmap> r7 = r0.output
            android.graphics.Bitmap r8 = r0.getCropBitmap(r10)
            r7.set(r4, r8)
            goto L_0x005c
        L_0x0040:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "fail get i-frame {"
            r10.<init>(r11)
            r10.append(r4)
            r10.append(r9)
            r11 = r5[r4]
            r10.append(r11)
            r10.append(r7)
            java.lang.String r7 = r10.toString()
            com.samsung.android.gallery.support.utils.Log.w(r8, r7)
        L_0x005c:
            int r4 = r4 + 1
            goto L_0x0024
        L_0x005f:
            r2.tick()
            int r4 = r0.option
            r10 = 0
            r12 = 1
            if (r4 != 0) goto L_0x00d8
            java.util.List<android.graphics.Bitmap> r4 = r0.output
            java.lang.Object r4 = r4.get(r6)
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            r13 = -1
            r5[r6] = r13
            r15 = r12
        L_0x0076:
            if (r15 >= r3) goto L_0x0092
            r19 = r6
            java.util.List<android.graphics.Bitmap> r6 = r0.output
            java.lang.Object r6 = r6.get(r15)
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            if (r4 == 0) goto L_0x008a
            boolean r16 = r4.sameAs(r6)
            if (r16 != 0) goto L_0x008d
        L_0x008a:
            r5[r15] = r13
            r4 = r6
        L_0x008d:
            int r15 = r15 + 1
            r6 = r19
            goto L_0x0076
        L_0x0092:
            r19 = r6
            r2.tick()
            r4 = r19
            r6 = r4
        L_0x009a:
            if (r4 >= r3) goto L_0x00d4
            r13 = r5[r4]
            int r15 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r15 < 0) goto L_0x00d1
            int r6 = r6 + 1
            r15 = 3
            android.graphics.Bitmap r13 = com.samsung.android.gallery.support.library.SeApiCompat.getVideoFrameAtTime(r1, r13, r15)
            if (r13 == 0) goto L_0x00b5
            java.util.List<android.graphics.Bitmap> r14 = r0.output
            android.graphics.Bitmap r13 = r0.getCropBitmap(r13)
            r14.set(r4, r13)
            goto L_0x00d1
        L_0x00b5:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "fail get p-frame {"
            r13.<init>(r14)
            r13.append(r4)
            r13.append(r9)
            r14 = r5[r4]
            r13.append(r14)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            com.samsung.android.gallery.support.utils.Log.w(r8, r13)
        L_0x00d1:
            int r4 = r4 + 1
            goto L_0x009a
        L_0x00d4:
            r2.tick()
            goto L_0x00da
        L_0x00d8:
            r19 = r6
        L_0x00da:
            java.util.List<android.graphics.Bitmap> r1 = r0.output
            java.util.stream.Stream r1 = r1.stream()
            h4.a r4 = new h4.a
            r5 = 29
            r4.<init>(r5)
            java.util.stream.Stream r1 = r1.filter(r4)
            long r4 = r1.count()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r7 = "parseVideoAdaptive {"
            r1.<init>(r7)
            int r0 = r0.option
            r1.append(r0)
            r0 = 58
            r1.append(r0)
            r1.append(r3)
            r1.append(r9)
            r1.append(r4)
            r1.append(r9)
            r1.append(r6)
            java.lang.String r0 = "} +"
            r1.append(r0)
            java.lang.String r0 = r2.summary()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.d(r8, r0)
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 <= 0) goto L_0x0128
            return r12
        L_0x0128:
            return r19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.plugins.motionphoto.VideoStripBuilder.parseVideo(android.media.MediaMetadataRetriever, int):boolean");
    }

    private void saveCache(String str, List<Bitmap> list) {
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        int size = list.size();
        Bitmap mergeBitmap = BitmapUtils.mergeBitmap(list, size);
        if (mergeBitmap != null) {
            sMemoryCacheManager.addCache(str, mergeBitmap);
            if (this.diskCacheEnabled) {
                BitmapCache.putDiskCache(6, str.getBytes(), mergeBitmap);
            }
        }
        StringBuilder sb2 = new StringBuilder("saveCache");
        if (this.diskCacheEnabled) {
            str2 = "D";
        } else {
            str2 = "M";
        }
        a.A(new Object[]{str2, Integer.valueOf(size), Logger.toSimpleString(mergeBitmap), Long.valueOf(currentTimeMillis)}, sb2, "VideoStripBuilder");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0086 A[Catch:{ all -> 0x002e, all -> 0x00ce, all -> 0x0076 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c2 A[Catch:{ all -> 0x002e, all -> 0x00ce, all -> 0x0076 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void build() {
        /*
            r12 = this;
            java.lang.String r1 = "VideoStripBuilder"
            com.samsung.android.gallery.support.utils.ThreadUtil.assertBgThread(r1)
            java.lang.String r0 = r12.cacheKey
            java.util.List<android.graphics.Bitmap> r2 = r12.output
            boolean r0 = r12.loadCache(r0, r2)
            if (r0 == 0) goto L_0x0015
            java.lang.String r12 = "build cache hit"
            com.samsung.android.gallery.support.utils.Log.d(r1, r12)
            return
        L_0x0015:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r0 = r12.item
            java.lang.String r2 = r0.getPath()
            android.media.MediaMetadataRetriever r3 = new android.media.MediaMetadataRetriever     // Catch:{ Error | Exception -> 0x0073 }
            r3.<init>()     // Catch:{ Error | Exception -> 0x0073 }
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ all -> 0x0076 }
            r9.<init>(r2)     // Catch:{ all -> 0x0076 }
            int r0 = r12.option     // Catch:{ all -> 0x002e }
            r4 = 1
            if (r0 != 0) goto L_0x0032
            com.samsung.android.gallery.support.library.SeApiCompat.setVideoHwCodecEnabled(r3, r4)     // Catch:{ all -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r0 = move-exception
            r12 = r0
            goto L_0x00ca
        L_0x0032:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r0 = r12.item     // Catch:{ all -> 0x002e }
            int r0 = r0.getFileDuration()     // Catch:{ all -> 0x002e }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r5 = r12.item     // Catch:{ all -> 0x002e }
            boolean r5 = r5.isMotionPhoto()     // Catch:{ all -> 0x002e }
            r10 = 0
            if (r5 == 0) goto L_0x0079
            long[] r0 = com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils.getVideoStreamInfoFromMotionPhoto(r2)     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0067
            r5 = r0[r4]     // Catch:{ all -> 0x002e }
            r7 = 0
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 > 0) goto L_0x0050
            goto L_0x0067
        L_0x0050:
            r7 = r0[r10]     // Catch:{ all -> 0x002e }
            com.samsung.android.gallery.support.utils.MediaHelper$VideoInfo r5 = com.samsung.android.gallery.support.utils.MediaHelper.getVideoInfo(r9, r7, r5)     // Catch:{ all -> 0x002e }
            int r11 = r5.duration     // Catch:{ all -> 0x002e }
            r5 = r4
            java.io.FileDescriptor r4 = r9.getFD()     // Catch:{ all -> 0x002e }
            r7 = r5
            r5 = r0[r10]     // Catch:{ all -> 0x002e }
            r7 = r0[r7]     // Catch:{ all -> 0x002e }
            r3.setDataSource(r4, r5, r7)     // Catch:{ all -> 0x002e }
            r0 = r11
            goto L_0x0080
        L_0x0067:
            java.lang.String r12 = "build failed. invalid motion stream in photo"
            com.samsung.android.gallery.support.utils.Log.e(r1, r12)     // Catch:{ all -> 0x002e }
        L_0x006c:
            r9.close()     // Catch:{ all -> 0x0076 }
            r3.close()     // Catch:{ Error | Exception -> 0x0073 }
            return
        L_0x0073:
            r0 = move-exception
            r12 = r0
            goto L_0x00dc
        L_0x0076:
            r0 = move-exception
            r12 = r0
            goto L_0x00d3
        L_0x0079:
            java.io.FileDescriptor r4 = r9.getFD()     // Catch:{ all -> 0x002e }
            r3.setDataSource(r4)     // Catch:{ all -> 0x002e }
        L_0x0080:
            boolean r4 = com.samsung.android.gallery.support.library.SeApiCompat.supportSetVideoSize()     // Catch:{ all -> 0x002e }
            if (r4 == 0) goto L_0x00bc
            r4 = 18
            java.lang.String r4 = r3.extractMetadata(r4)     // Catch:{ all -> 0x002e }
            int r4 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r4, r10)     // Catch:{ all -> 0x002e }
            r5 = 19
            java.lang.String r5 = r3.extractMetadata(r5)     // Catch:{ all -> 0x002e }
            int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r5, r10)     // Catch:{ all -> 0x002e }
            if (r4 <= 0) goto L_0x00bc
            if (r5 <= 0) goto L_0x00bc
            int r6 = r12.thumbnailSize     // Catch:{ all -> 0x002e }
            float r6 = (float) r6     // Catch:{ all -> 0x002e }
            int r7 = java.lang.Math.max(r4, r5)     // Catch:{ all -> 0x002e }
            float r7 = (float) r7     // Catch:{ all -> 0x002e }
            float r6 = r6 / r7
            r7 = 1065353216(0x3f800000, float:1.0)
            int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r7 >= 0) goto L_0x00bc
            float r4 = (float) r4     // Catch:{ all -> 0x002e }
            float r4 = r4 * r6
            int r4 = java.lang.Math.round(r4)     // Catch:{ all -> 0x002e }
            float r5 = (float) r5     // Catch:{ all -> 0x002e }
            float r5 = r5 * r6
            int r5 = java.lang.Math.round(r5)     // Catch:{ all -> 0x002e }
            com.samsung.android.gallery.support.library.SeApiCompat.setVideoSize(r3, r4, r5)     // Catch:{ all -> 0x002e }
        L_0x00bc:
            boolean r0 = r12.parseVideo(r3, r0)     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x006c
            java.lang.String r0 = r12.cacheKey     // Catch:{ all -> 0x002e }
            java.util.List<android.graphics.Bitmap> r4 = r12.output     // Catch:{ all -> 0x002e }
            r12.saveCache(r0, r4)     // Catch:{ all -> 0x002e }
            goto L_0x006c
        L_0x00ca:
            r9.close()     // Catch:{ all -> 0x00ce }
            goto L_0x00d2
        L_0x00ce:
            r0 = move-exception
            r12.addSuppressed(r0)     // Catch:{ all -> 0x0076 }
        L_0x00d2:
            throw r12     // Catch:{ all -> 0x0076 }
        L_0x00d3:
            r3.close()     // Catch:{ all -> 0x00d7 }
            goto L_0x00db
        L_0x00d7:
            r0 = move-exception
            r12.addSuppressed(r0)     // Catch:{ Error | Exception -> 0x0073 }
        L_0x00db:
            throw r12     // Catch:{ Error | Exception -> 0x0073 }
        L_0x00dc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "build failed {"
            r0.<init>(r3)
            java.lang.String r2 = com.samsung.android.gallery.support.utils.FileUtils.info((java.lang.String) r2)
            r0.append(r2)
            r2 = 125(0x7d, float:1.75E-43)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r1, (java.lang.String) r0, (java.lang.Throwable) r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.plugins.motionphoto.VideoStripBuilder.build():void");
    }

    public Bitmap getCropBitmap(Bitmap bitmap) {
        return getCropBitmap(bitmap, this.item, this.thumbnailSize);
    }

    public VideoStripBuilder setAdaptive(boolean z) {
        int i2;
        if (z) {
            i2 = 0;
        } else {
            i2 = 2;
        }
        this.option = i2;
        return this;
    }

    public static Bitmap getCropBitmap(Bitmap bitmap, ThumbnailInterface thumbnailInterface, int i2) {
        boolean z = false;
        int thumbnailOrientation = thumbnailInterface.isMotionPhoto() ? 0 : thumbnailInterface.getThumbnailOrientation();
        RectF cropRectRatio = thumbnailInterface.getCropRectRatio();
        Rect smartCropRect = RectUtils.isValidRect(cropRectRatio) ? RectUtils.getSmartCropRect(bitmap, cropRectRatio, thumbnailOrientation, true) : null;
        if (!thumbnailInterface.isCreature() && !thumbnailInterface.isPanoramic() && !thumbnailInterface.isCustomCover()) {
            z = true;
        }
        return new BitmapOperator(bitmap).resize(i2).crop(smartCropRect).stretchable(z).rotate(thumbnailOrientation).apply();
    }
}
