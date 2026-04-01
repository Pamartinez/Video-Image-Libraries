package com.samsung.android.gallery.module.thumbnail.logic;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Gainmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.util.Size;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.chain.Chain;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsThumbnailLoaderImpl implements Chain<AbsThumbnailLoaderImpl> {
    protected final String TAG = tag();
    private AbsThumbnailLoaderImpl mNext;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FeatureHolder {
        static final boolean REMOVE_HDR_GAINMAP;

        static {
            boolean z;
            if (!PocFeatures.USE_ANDROID_CODEC || Build.VERSION.SDK_INT < 34) {
                z = false;
            } else {
                z = true;
            }
            REMOVE_HDR_GAINMAP = z;
        }
    }

    private Bitmap getBrightThumbnail(ReqInfo reqInfo, long j2, MediaMetadataRetriever mediaMetadataRetriever, Bitmap bitmap) {
        Bitmap videoThumbnail;
        if (PocFeatures.isEnabled(PocFeatures.EnhancedVideoThumbnail) && !reqInfo.item.isStories()) {
            long fileDuration = (long) reqInfo.item.getFileDuration();
            long currentTimeMillis = System.currentTimeMillis();
            if (!FileUtils.isNonMoviePath(reqInfo.path) && fileDuration > 60000 && BitmapUtils.isDark(bitmap)) {
                int i2 = 1;
                while (true) {
                    videoThumbnail = getVideoThumbnail(mediaMetadataRetriever, (((long) i2) * 20000000) + j2);
                    int i7 = i2 + 1;
                    if (i2 < 5 && BitmapUtils.isDark(videoThumbnail)) {
                        i2 = i7;
                    } else if (!(videoThumbnail == null || i7 == 5)) {
                        String str = this.TAG;
                        a.A(new Object[]{Integer.valueOf(i2), Long.valueOf(((((long) i2) * 20000000) + j2) / 1000000), Long.valueOf(currentTimeMillis)}, new StringBuilder("replace video thumb"), str);
                        return videoThumbnail;
                    }
                }
                String str2 = this.TAG;
                a.A(new Object[]{Integer.valueOf(i2), Long.valueOf(((((long) i2) * 20000000) + j2) / 1000000), Long.valueOf(currentTimeMillis)}, new StringBuilder("replace video thumb"), str2);
                return videoThumbnail;
            }
        }
        return bitmap;
    }

    private Size setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, ReqInfo reqInfo) {
        if (!SeApiCompat.supportSetVideoSize()) {
            return null;
        }
        int i2 = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(18), 0);
        int i7 = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(19), 0);
        if (i2 <= 0 || i7 <= 0) {
            return null;
        }
        int i8 = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(24), 0);
        float videoDecodingSize = ((float) ThumbnailHelper.getVideoDecodingSize(reqInfo.thumbKind, i2, i7)) / ((float) Math.max(i2, i7));
        if (videoDecodingSize < 1.0f) {
            SeApiCompat.setVideoSize(mediaMetadataRetriever, Math.round(((float) i2) * videoDecodingSize), Math.round(((float) i7) * videoDecodingSize));
        }
        if (i8 == 90 || i8 == 270) {
            return new Size(i7, i2);
        }
        return new Size(i2, i7);
    }

    public final boolean checkInterrupted(ReqInfo reqInfo, Bitmap bitmap) {
        if (!reqInfo.mInterruptChecker.isInterrupted()) {
            return false;
        }
        if (Logger.THUMBNAIL) {
            Log.v(this.TAG, "skip by interrupt");
        }
        recycleBitmap(bitmap);
        return true;
    }

    public void clear() {
        AbsThumbnailLoaderImpl absThumbnailLoaderImpl = this.mNext;
        if (absThumbnailLoaderImpl != null) {
            absThumbnailLoaderImpl.clear();
        }
    }

    public Bitmap forceResizeBitmapIfNeeded(Bitmap bitmap, ThumbKind thumbKind, ThumbnailInterface thumbnailInterface) {
        if (Math.min(bitmap.getWidth(), bitmap.getHeight()) < thumbKind.size() * 2) {
            return bitmap;
        }
        int inSampleSize = getInSampleSize(thumbKind, bitmap.getWidth(), bitmap.getHeight(), thumbKind.lowerBound());
        String str = this.TAG;
        Log.w(str, "forceResize {" + thumbKind + GlobalPostProcInternalPPInterface.SPLIT_REGEX + inSampleSize + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bitmap.getWidth() + "x" + bitmap.getHeight() + "}\n" + thumbnailInterface);
        Bitmap resize = BitmapUtils.resize(bitmap, 1.0f / ((float) inSampleSize));
        if (resize != bitmap) {
            BitmapUtils.putBitmap(bitmap);
        }
        return resize;
    }

    public final int getCacheId(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind) {
        if (support(thumbnailInterface)) {
            return getCacheId(thumbKind);
        }
        AbsThumbnailLoaderImpl absThumbnailLoaderImpl = this.mNext;
        if (absThumbnailLoaderImpl == null) {
            return getCacheId(thumbKind);
        }
        return absThumbnailLoaderImpl.getCacheId(thumbnailInterface, thumbKind);
    }

    public long getFrameTime(ReqInfo reqInfo) {
        return reqInfo.getVideoFrameTime();
    }

    public final int getInSampleSize(ThumbKind thumbKind, int i2, int i7, int i8) {
        if (thumbKind == ThumbKind.MINI_KIND || (thumbKind == ThumbKind.LARGE_KIND && BitmapUtils.isPanoramic(i2, i7))) {
            return BitmapOptions.computeInSampleSizeLowerBound(i2, i7, i8);
        }
        return BitmapOptions.computeInSampleSizeLargerThan(i2, i7, i8);
    }

    public abstract Bitmap getThumbnail(ReqInfo reqInfo);

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v14, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v15, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r16v8 */
    /* JADX WARNING: type inference failed for: r16v13 */
    /* JADX WARNING: type inference failed for: r16v16 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bd A[SYNTHETIC, Splitter:B:52:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap getVideoThumbnail(com.samsung.android.gallery.module.thumbnail.type.ReqInfo r23) {
        /*
            r22 = this;
            r1 = r22
            r2 = r23
            java.lang.String r7 = ","
            java.lang.String r0 = "getVideoThumbnail failed. wrong size "
            java.lang.String r3 = "getVideoThumbnail failed"
            java.lang.String r4 = "bitmap for first "
            long r8 = java.lang.System.currentTimeMillis()
            java.lang.String r10 = r2.path
            long r5 = r22.getFrameTime(r23)
            java.io.FileInputStream r12 = new java.io.FileInputStream     // Catch:{ Error | Exception -> 0x0265 }
            r12.<init>(r10)     // Catch:{ Error | Exception -> 0x0265 }
            r13 = r5
            android.media.MediaMetadataRetriever r5 = new android.media.MediaMetadataRetriever     // Catch:{ all -> 0x0255 }
            r5.<init>()     // Catch:{ all -> 0x0255 }
            java.io.FileDescriptor r6 = r12.getFD()     // Catch:{ all -> 0x0242 }
            r5.setDataSource(r6)     // Catch:{ all -> 0x0242 }
            android.util.Size r15 = r1.setVideoSize(r5, r2)     // Catch:{ all -> 0x0242 }
            boolean r6 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_EMBEDDED_THUMBNAIL_IN_VIDEO     // Catch:{ all -> 0x0242 }
            r16 = 0
            if (r6 == 0) goto L_0x0044
            int r6 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r6 != 0) goto L_0x0044
            android.graphics.Bitmap r6 = com.samsung.android.gallery.module.graphics.BitmapUtils.decodeEmbeddedInVideo(r5)     // Catch:{ all -> 0x003b }
            goto L_0x0045
        L_0x003b:
            r0 = move-exception
            r3 = r0
            r17 = r5
            r5 = r13
            r16 = 0
            goto L_0x0249
        L_0x0044:
            r6 = 0
        L_0x0045:
            if (r6 != 0) goto L_0x008f
            boolean r6 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_VIDEO_KEY_FRAME_INDEX     // Catch:{ all -> 0x0084 }
            if (r6 == 0) goto L_0x0088
            int r6 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r6 != 0) goto L_0x0088
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r6 = r2.item     // Catch:{ all -> 0x0084 }
            boolean r6 = r6.isStories()     // Catch:{ all -> 0x0084 }
            if (r6 != 0) goto L_0x0088
            long r13 = com.samsung.android.gallery.module.graphics.BitmapUtils.getVideoThumbnailFrameTime(r5)     // Catch:{ all -> 0x0084 }
            java.lang.String r6 = r1.TAG     // Catch:{ all -> 0x0084 }
            r16 = 0
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
            r11.<init>(r4)     // Catch:{ all -> 0x007d }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r4 = r2.item     // Catch:{ all -> 0x007d }
            java.lang.String r4 = r4.getDisplayName()     // Catch:{ all -> 0x007d }
            r11.append(r4)     // Catch:{ all -> 0x007d }
            java.lang.String r4 = " / "
            r11.append(r4)     // Catch:{ all -> 0x007d }
            r11.append(r13)     // Catch:{ all -> 0x007d }
            java.lang.String r4 = r11.toString()     // Catch:{ all -> 0x007d }
            com.samsung.android.gallery.support.utils.Log.d(r6, r4)     // Catch:{ all -> 0x007d }
            goto L_0x008a
        L_0x007d:
            r0 = move-exception
        L_0x007e:
            r3 = r0
            r17 = r5
        L_0x0081:
            r5 = r13
            goto L_0x0249
        L_0x0084:
            r0 = move-exception
            r16 = 0
            goto L_0x007e
        L_0x0088:
            r16 = 0
        L_0x008a:
            android.graphics.Bitmap r6 = r1.getVideoThumbnail(r5, r13)     // Catch:{ all -> 0x007d }
            goto L_0x0091
        L_0x008f:
            r16 = 0
        L_0x0091:
            if (r6 != 0) goto L_0x00b7
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r4 = r2.item     // Catch:{ all -> 0x00b3 }
            int r4 = r4.getRecordingMode()     // Catch:{ all -> 0x00b3 }
            r11 = 29
            if (r4 != r11) goto L_0x00b7
            r4 = 3
            android.graphics.Bitmap r6 = r1.getVideoThumbnail(r5, r13, r4)     // Catch:{ all -> 0x00b3 }
            java.lang.String r4 = r1.TAG     // Catch:{ all -> 0x00b3 }
            java.lang.String r11 = "3d video thumbnail fallback"
            r17 = r5
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r15}     // Catch:{ all -> 0x00b0 }
            com.samsung.android.gallery.support.utils.Log.i(r4, r11, r5)     // Catch:{ all -> 0x00b0 }
            goto L_0x00b9
        L_0x00b0:
            r0 = move-exception
        L_0x00b1:
            r3 = r0
            goto L_0x0081
        L_0x00b3:
            r0 = move-exception
            r17 = r5
            goto L_0x00b1
        L_0x00b7:
            r17 = r5
        L_0x00b9:
            java.lang.String r11 = " "
            if (r6 != 0) goto L_0x00f4
            java.lang.String r0 = r1.TAG     // Catch:{ all -> 0x00b0 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r4.<init>(r3)     // Catch:{ all -> 0x00b0 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r3 = r2.thumbKind     // Catch:{ all -> 0x00b0 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r5 = r2.item     // Catch:{ all -> 0x00b0 }
            long r18 = r5.getFileId()     // Catch:{ all -> 0x00b0 }
            java.lang.Long r5 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x00b0 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x00b0 }
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r5, r8}     // Catch:{ all -> 0x00b0 }
            java.lang.String r3 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r3)     // Catch:{ all -> 0x00b0 }
            r4.append(r3)     // Catch:{ all -> 0x00b0 }
            r4.append(r11)     // Catch:{ all -> 0x00b0 }
            java.lang.String r3 = com.samsung.android.gallery.support.utils.FileUtils.info((java.lang.String) r10)     // Catch:{ all -> 0x00b0 }
            r4.append(r3)     // Catch:{ all -> 0x00b0 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00b0 }
            com.samsung.android.gallery.support.utils.Log.e(r0, r3)     // Catch:{ all -> 0x00b0 }
            r18 = r13
            goto L_0x0172
        L_0x00f4:
            r3 = r13
            r5 = r17
            android.graphics.Bitmap r6 = r1.getBrightThumbnail(r2, r3, r5, r6)     // Catch:{ all -> 0x023b }
            int r5 = r6.getWidth()     // Catch:{ all -> 0x0236 }
            int r13 = r6.getHeight()     // Catch:{ all -> 0x0236 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r14 = r2.thumbKind     // Catch:{ all -> 0x0236 }
            r18 = r3
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r3 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.XLARGE_NC_KIND     // Catch:{ all -> 0x0184 }
            java.lang.String r4 = "} +"
            r20 = r8
            java.lang.String r8 = "x"
            java.lang.String r9 = "getVideoThumbnail {"
            if (r14 != r3) goto L_0x018a
            java.lang.String r0 = r1.TAG     // Catch:{ all -> 0x0184 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0184 }
            r3.<init>(r9)     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r9 = r2.thumbKind     // Catch:{ all -> 0x0184 }
            r3.append(r9)     // Catch:{ all -> 0x0184 }
            r3.append(r7)     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r9 = r2.item     // Catch:{ all -> 0x0184 }
            long r14 = r9.getFileId()     // Catch:{ all -> 0x0184 }
            r3.append(r14)     // Catch:{ all -> 0x0184 }
            r3.append(r7)     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r9 = r2.item     // Catch:{ all -> 0x0184 }
            int r9 = r9.getWidth()     // Catch:{ all -> 0x0184 }
            r3.append(r9)     // Catch:{ all -> 0x0184 }
            r3.append(r8)     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r9 = r2.item     // Catch:{ all -> 0x0184 }
            int r9 = r9.getHeight()     // Catch:{ all -> 0x0184 }
            r3.append(r9)     // Catch:{ all -> 0x0184 }
            java.lang.String r9 = "@"
            r3.append(r9)     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r9 = r2.item     // Catch:{ all -> 0x0184 }
            int r9 = r9.getOrientation()     // Catch:{ all -> 0x0184 }
            r3.append(r9)     // Catch:{ all -> 0x0184 }
            r3.append(r7)     // Catch:{ all -> 0x0184 }
            r3.append(r5)     // Catch:{ all -> 0x0184 }
            r3.append(r8)     // Catch:{ all -> 0x0184 }
            r3.append(r13)     // Catch:{ all -> 0x0184 }
            r3.append(r4)     // Catch:{ all -> 0x0184 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0184 }
            long r4 = r4 - r20
            r3.append(r4)     // Catch:{ all -> 0x0184 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.support.utils.Log.d(r0, r3)     // Catch:{ all -> 0x0184 }
        L_0x0172:
            r17.close()     // Catch:{ all -> 0x017e }
            r12.close()     // Catch:{ Error | Exception -> 0x0179 }
            return r6
        L_0x0179:
            r0 = move-exception
            r5 = r18
            goto L_0x0269
        L_0x017e:
            r0 = move-exception
            r3 = r0
            r5 = r18
            goto L_0x025a
        L_0x0184:
            r0 = move-exception
        L_0x0185:
            r3 = r0
            r5 = r18
            goto L_0x0249
        L_0x018a:
            if (r5 <= 0) goto L_0x0210
            if (r13 > 0) goto L_0x0190
            goto L_0x0210
        L_0x0190:
            if (r15 == 0) goto L_0x0196
            int r5 = r15.getWidth()     // Catch:{ all -> 0x0184 }
        L_0x0196:
            if (r15 == 0) goto L_0x019c
            int r13 = r15.getHeight()     // Catch:{ all -> 0x0184 }
        L_0x019c:
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r0 = r2.thumbKind     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r3 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.FULL_NC_KIND     // Catch:{ all -> 0x0184 }
            if (r0 != r3) goto L_0x01a7
            int r0 = r3.size()     // Catch:{ all -> 0x0184 }
            goto L_0x01ab
        L_0x01a7:
            int r0 = com.samsung.android.gallery.module.thumbnail.logic.ThumbnailHelper.getVideoMaxSize(r5, r13)     // Catch:{ all -> 0x0184 }
        L_0x01ab:
            float r0 = (float) r0     // Catch:{ all -> 0x0184 }
            int r3 = r6.getWidth()     // Catch:{ all -> 0x0184 }
            int r11 = r6.getHeight()     // Catch:{ all -> 0x0184 }
            int r3 = java.lang.Math.max(r3, r11)     // Catch:{ all -> 0x0184 }
            float r3 = (float) r3     // Catch:{ all -> 0x0184 }
            float r0 = r0 / r3
            r3 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x01c5
            android.graphics.Bitmap r0 = com.samsung.android.gallery.module.graphics.BitmapUtils.resize((android.graphics.Bitmap) r6, (float) r0)     // Catch:{ all -> 0x0184 }
            r6 = r0
        L_0x01c5:
            if (r6 == 0) goto L_0x01d4
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r0 = r2.thumbKind     // Catch:{ all -> 0x0184 }
            boolean r0 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.isSmallKind(r0)     // Catch:{ all -> 0x0184 }
            if (r0 == 0) goto L_0x01d4
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r0 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.MEDIUM_KIND     // Catch:{ all -> 0x0184 }
            r1.putVideoToDiskCache(r2, r0, r6)     // Catch:{ all -> 0x0184 }
        L_0x01d4:
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0184 }
            long r14 = r14 - r20
            r20 = 499(0x1f3, double:2.465E-321)
            int r0 = (r14 > r20 ? 1 : (r14 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x0172
            java.lang.String r0 = r1.TAG     // Catch:{ all -> 0x0184 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0184 }
            r3.<init>(r9)     // Catch:{ all -> 0x0184 }
            java.lang.String r9 = r1.toDebugString(r2, r6)     // Catch:{ all -> 0x0184 }
            r3.append(r9)     // Catch:{ all -> 0x0184 }
            java.lang.String r9 = ":"
            r3.append(r9)     // Catch:{ all -> 0x0184 }
            r3.append(r5)     // Catch:{ all -> 0x0184 }
            r3.append(r8)     // Catch:{ all -> 0x0184 }
            r3.append(r13)     // Catch:{ all -> 0x0184 }
            r3.append(r4)     // Catch:{ all -> 0x0184 }
            r3.append(r14)     // Catch:{ all -> 0x0184 }
            java.lang.String r4 = ""
            r3.append(r4)     // Catch:{ all -> 0x0184 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.support.utils.Log.d(r0, r3)     // Catch:{ all -> 0x0184 }
            goto L_0x0172
        L_0x0210:
            java.lang.String r3 = r1.TAG     // Catch:{ all -> 0x0184 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0184 }
            r4.<init>(r0)     // Catch:{ all -> 0x0184 }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.toSimpleString((android.graphics.Bitmap) r6)     // Catch:{ all -> 0x0184 }
            r4.append(r0)     // Catch:{ all -> 0x0184 }
            r4.append(r11)     // Catch:{ all -> 0x0184 }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.FileUtils.info((java.lang.String) r10)     // Catch:{ all -> 0x0184 }
            r4.append(r0)     // Catch:{ all -> 0x0184 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0184 }
            com.samsung.android.gallery.support.utils.Log.w(r3, r0)     // Catch:{ all -> 0x0184 }
            r17.close()     // Catch:{ all -> 0x017e }
            r12.close()     // Catch:{ Error | Exception -> 0x0179 }
            return r16
        L_0x0236:
            r0 = move-exception
            r18 = r3
            goto L_0x0185
        L_0x023b:
            r0 = move-exception
            r18 = r3
            r17 = r5
            goto L_0x0185
        L_0x0242:
            r0 = move-exception
            r17 = r5
            r16 = 0
            goto L_0x00b1
        L_0x0249:
            r17.close()     // Catch:{ all -> 0x024d }
            goto L_0x0251
        L_0x024d:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ all -> 0x0252 }
        L_0x0251:
            throw r3     // Catch:{ all -> 0x0252 }
        L_0x0252:
            r0 = move-exception
            r3 = r0
            goto L_0x025a
        L_0x0255:
            r0 = move-exception
            r16 = 0
            r3 = r0
            r5 = r13
        L_0x025a:
            r12.close()     // Catch:{ all -> 0x025e }
            goto L_0x0262
        L_0x025e:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ Error | Exception -> 0x0263 }
        L_0x0262:
            throw r3     // Catch:{ Error | Exception -> 0x0263 }
        L_0x0263:
            r0 = move-exception
            goto L_0x0269
        L_0x0265:
            r0 = move-exception
            r13 = r5
            r16 = 0
        L_0x0269:
            java.lang.String r1 = r1.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "getVideoThumbnail failed {"
            r3.<init>(r4)
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r4 = r2.thumbKind
            r3.append(r4)
            r3.append(r7)
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r2 = r2.item
            long r8 = r2.getFileId()
            r3.append(r8)
            r3.append(r7)
            r3.append(r5)
            java.lang.String r2 = "} "
            r3.append(r2)
            java.lang.String r2 = com.samsung.android.gallery.support.utils.FileUtils.info((java.lang.String) r10)
            r3.append(r2)
            java.lang.String r2 = " e="
            r3.append(r2)
            java.lang.String r0 = r0.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.thumbnail.logic.AbsThumbnailLoaderImpl.getVideoThumbnail(com.samsung.android.gallery.module.thumbnail.type.ReqInfo):android.graphics.Bitmap");
    }

    public final Bitmap load(ReqInfo reqInfo, ThumbKind thumbKind) {
        ThumbnailInterface item = reqInfo.getItem();
        if (!support(item)) {
            AbsThumbnailLoaderImpl absThumbnailLoaderImpl = this.mNext;
            if (absThumbnailLoaderImpl != null) {
                return absThumbnailLoaderImpl.load(reqInfo, thumbKind);
            }
            String str = this.TAG;
            Log.e(str, Logger.getEncodedString(item.getPath()) + " is not supported");
            return null;
        }
        Bitmap thumbnail = getThumbnail(reqInfo);
        if (thumbnail == null || item.isVideo() || ThumbKind.isCropKind(reqInfo.thumbKind)) {
            return thumbnail;
        }
        Bitmap forceResizeBitmapIfNeeded = forceResizeBitmapIfNeeded(thumbnail, thumbKind, item);
        if (FeatureHolder.REMOVE_HDR_GAINMAP) {
            forceResizeBitmapIfNeeded.setGainmap((Gainmap) null);
        }
        return forceResizeBitmapIfNeeded;
    }

    public void putVideoToDiskCache(ReqInfo reqInfo, ThumbKind thumbKind, Bitmap bitmap) {
        if (bitmap != null && reqInfo.isFileCacheableFromDecodeStatus() && thumbKind.ordinal() <= ThumbKind.LARGE_KIND.ordinal() && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
            System.currentTimeMillis();
            int cacheId = thumbKind.cacheId();
            byte[] alterFileCacheKey = reqInfo.getAlterFileCacheKey();
            CacheManager.getInstance().add(cacheId, alterFileCacheKey, BitmapUtils.compressToBytes(bitmap, 95));
            CacheManager.getInstance().writeToFile(cacheId, alterFileCacheKey);
            CacheManager.getInstance().commit(cacheId, alterFileCacheKey);
        }
    }

    public void recycleBitmap(Bitmap bitmap) {
        BitmapUtils.putBitmap(bitmap);
    }

    public abstract boolean support(ThumbnailInterface thumbnailInterface);

    public abstract String tag();

    public String toDebugString(ReqInfo reqInfo, Bitmap bitmap) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(reqInfo.thumbKind);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(reqInfo.item.getFileId());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(reqInfo.item.getMimeType());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(reqInfo.item.getFileSize());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (bitmap != null) {
            str = bitmap.getWidth() + "x" + bitmap.getHeight();
        } else {
            str = "null";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public void setNext(AbsThumbnailLoaderImpl absThumbnailLoaderImpl) {
        this.mNext = absThumbnailLoaderImpl;
    }

    public int getCacheId(ThumbKind thumbKind) {
        return thumbKind.cacheId();
    }

    public void setRemoteThumbnailLoader(RemoteThumbnailLoader remoteThumbnailLoader) {
    }

    public Bitmap getVideoThumbnail(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        return SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, i2);
    }

    public Bitmap getVideoThumbnail(MediaMetadataRetriever mediaMetadataRetriever, long j2) {
        return SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, 2);
    }
}
