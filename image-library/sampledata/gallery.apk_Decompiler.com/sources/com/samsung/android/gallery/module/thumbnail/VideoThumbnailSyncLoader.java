package com.samsung.android.gallery.module.thumbnail;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import h4.C0464a;
import java.io.FileInputStream;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoThumbnailSyncLoader {
    private final int mMode;

    public VideoThumbnailSyncLoader(int i2) {
        this.mMode = i2;
    }

    public static Bitmap getCropBitmap(ThumbnailInterface thumbnailInterface, int i2, Bitmap bitmap, int i7) {
        Rect rect;
        RectF cropRectRatio = thumbnailInterface.getCropRectRatio();
        boolean z = true;
        if (RectUtils.isValidRect(cropRectRatio)) {
            rect = RectUtils.getSmartCropRect(bitmap, cropRectRatio, i7, true);
        } else {
            rect = null;
        }
        if (thumbnailInterface.isCreature() || thumbnailInterface.isPanoramic() || thumbnailInterface.isCustomCover()) {
            z = false;
        }
        return new BitmapOperator(bitmap).resize(i2).crop(rect).stretchable(z).rotate(i7).apply();
    }

    private static int getOffsetMs(int i2, int i7) {
        if (i2 > 1000) {
            return (i2 - 100) / i7;
        }
        return i2 / i7;
    }

    private boolean getThumbnails(ThumbnailInterface thumbnailInterface, List<Bitmap> list, MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7) {
        int i8 = this.mMode;
        if (i8 == 0) {
            return getThumbnailsInner(thumbnailInterface, list, mediaMetadataRetriever, i2, i7, 2);
        }
        ThumbnailInterface thumbnailInterface2 = thumbnailInterface;
        List<Bitmap> list2 = list;
        MediaMetadataRetriever mediaMetadataRetriever2 = mediaMetadataRetriever;
        int i10 = i2;
        int i11 = i7;
        if (i8 == 1) {
            return getThumbnailsInner(thumbnailInterface2, list2, mediaMetadataRetriever2, i10, i11, 3);
        }
        if (i8 == 2) {
            return getThumbnailsAdaptive2(thumbnailInterface2, list2, mediaMetadataRetriever2, i10, i11);
        }
        return false;
    }

    private static boolean getThumbnailsAdaptive2(ThumbnailInterface thumbnailInterface, List<Bitmap> list, MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7) {
        int i8;
        boolean z;
        ThumbnailInterface thumbnailInterface2 = thumbnailInterface;
        List<Bitmap> list2 = list;
        MediaMetadataRetriever mediaMetadataRetriever2 = mediaMetadataRetriever;
        int i10 = i2;
        TimeTickLog timeTickLog = new TimeTickLog();
        int size = list2.size();
        int offsetMs = getOffsetMs(i7, size);
        boolean[] zArr = new boolean[size];
        long[] jArr = new long[size];
        boolean z3 = false;
        for (int i11 = 0; i11 < size; i11++) {
            jArr[i11] = ((long) offsetMs) * ((long) i11) * 1000;
        }
        if (thumbnailInterface2.isMotionPhoto()) {
            i8 = 0;
        } else {
            i8 = thumbnailInterface2.getThumbnailOrientation();
        }
        for (int i12 = 0; i12 < size; i12++) {
            Bitmap videoFrameAtTime = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever2, jArr[i12], 0);
            if (videoFrameAtTime != null) {
                list2.set(i12, getCropBitmap(thumbnailInterface2, i10, videoFrameAtTime, i8));
            } else {
                Log.w((CharSequence) "VideoThumbSyncLoader", "fail get i-frame", Integer.valueOf(i12), Long.valueOf(jArr[i12]));
                zArr[i12] = true;
            }
        }
        timeTickLog.tick();
        Bitmap bitmap = list2.get(0);
        for (int i13 = 1; i13 < size; i13++) {
            Bitmap bitmap2 = list2.get(i13);
            if (bitmap == null || !bitmap.sameAs(bitmap2)) {
                bitmap = bitmap2;
            } else {
                zArr[i13] = true;
            }
        }
        timeTickLog.tick();
        int i14 = 0;
        int i15 = 0;
        while (i14 < size) {
            if (zArr[i14]) {
                i15++;
                z = z3;
                Bitmap videoFrameAtTime2 = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever2, jArr[i14], 3);
                if (videoFrameAtTime2 != null) {
                    list2.set(i14, getCropBitmap(thumbnailInterface2, i10, videoFrameAtTime2, i8));
                } else {
                    Log.w((CharSequence) "VideoThumbSyncLoader", "fail get p-frame", Integer.valueOf(i14), Long.valueOf(jArr[i14]));
                }
            } else {
                z = z3;
            }
            i14++;
            z3 = z;
        }
        boolean z7 = z3;
        timeTickLog.tick();
        long count = list2.stream().filter(new C0464a(29)).count();
        Log.d("VideoThumbSyncLoader", "getThumbnailsAdaptive2" + Logger.v(Integer.valueOf(size), Long.valueOf(count), Integer.valueOf(i15)) + " +" + timeTickLog.summary());
        if (count > 0) {
            return true;
        }
        return z7;
    }

    private static boolean getThumbnailsInner(ThumbnailInterface thumbnailInterface, List<Bitmap> list, MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7, int i8) {
        int i10;
        int size = list.size();
        int offsetMs = getOffsetMs(i7, size);
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            long j2 = ((long) offsetMs) * ((long) i12) * 1000;
            Bitmap videoFrameAtTime = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, i8);
            if (videoFrameAtTime == null) {
                Log.w((CharSequence) "VideoThumbSyncLoader", "fail get frame", Integer.valueOf(i12), Long.valueOf(j2));
            } else {
                i11++;
                if (thumbnailInterface.isMotionPhoto()) {
                    i10 = 0;
                } else {
                    i10 = thumbnailInterface.getThumbnailOrientation();
                }
                list.set(i12, getCropBitmap(thumbnailInterface, i2, videoFrameAtTime, i10));
            }
        }
        if (i11 < size) {
            recoverMissedFrames(list);
        }
        if (i11 > 0) {
            return true;
        }
        return false;
    }

    private static void recoverMissedFrames(List<Bitmap> list) {
        Bitmap bitmap = null;
        Bitmap orElse = list.stream().filter(new C0464a(29)).findFirst().orElse((Object) null);
        for (int i2 = 0; i2 < list.size(); i2++) {
            Bitmap bitmap2 = list.get(i2);
            if (bitmap2 != null) {
                bitmap = bitmap2;
            } else if (bitmap != null) {
                list.set(i2, bitmap);
                Log.i("VideoThumbSyncLoader", "recover videoFrame by last", Integer.valueOf(i2));
            } else if (orElse != null) {
                list.set(i2, orElse);
                Log.i("VideoThumbSyncLoader", "recover videoFrame by vh", Integer.valueOf(i2));
            } else {
                Log.e((CharSequence) "VideoThumbSyncLoader", "fail recover videoFrame ", Integer.valueOf(i2));
            }
        }
    }

    private static void setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2) {
        int i7 = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(18), 0);
        int i8 = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(19), 0);
        if (i7 > 0 && i8 > 0) {
            float max = ((float) i2) / ((float) Math.max(i7, i8));
            if (max < 1.0f) {
                SeApiCompat.setVideoSize(mediaMetadataRetriever, Math.round(((float) i7) * max), Math.round(((float) i8) * max));
            }
        }
    }

    public boolean getThumbnail(ThumbnailInterface thumbnailInterface, List<Bitmap> list, int i2) {
        MediaMetadataRetriever mediaMetadataRetriever;
        Throwable th;
        int videoDuration;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            FileInputStream fileInputStream = new FileInputStream(thumbnailInterface.getPath());
            try {
                if (this.mMode == 2 && SdkConfig.atLeast(SdkConfig.SEM.R_MR1)) {
                    SeApiCompat.setVideoHwCodecEnabled(mediaMetadataRetriever, true);
                }
                if (thumbnailInterface.isMotionPhoto()) {
                    long[] videoStreamInfoFromMotionPhoto = MotionPhotoUtils.getVideoStreamInfoFromMotionPhoto(thumbnailInterface.getPath());
                    MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(fileInputStream, videoStreamInfoFromMotionPhoto[0], videoStreamInfoFromMotionPhoto[1]);
                    mediaMetadataRetriever.setDataSource(fileInputStream.getFD(), videoStreamInfoFromMotionPhoto[0], videoStreamInfoFromMotionPhoto[1]);
                    videoDuration = videoInfo.duration;
                } else {
                    mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                    videoDuration = VideoPropData.getVideoDuration(thumbnailInterface);
                }
                int i7 = videoDuration;
                if (SeApiCompat.supportSetVideoSize()) {
                    setVideoSize(mediaMetadataRetriever, i2);
                }
                MediaMetadataRetriever mediaMetadataRetriever2 = mediaMetadataRetriever;
                try {
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    if (getThumbnails(thumbnailInterface, list, mediaMetadataRetriever2, i2, i7)) {
                        fileInputStream.close();
                        mediaMetadataRetriever.close();
                        return true;
                    }
                    fileInputStream.close();
                    mediaMetadataRetriever.close();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    Throwable th3 = th;
                    try {
                        fileInputStream.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                    throw th3;
                }
            } catch (Throwable th5) {
                th = th5;
                Throwable th32 = th;
                fileInputStream.close();
                throw th32;
            }
        } catch (Error | Exception e) {
            Log.e((CharSequence) "VideoThumbSyncLoader", "load failed", e);
        } catch (Throwable th6) {
            th.addSuppressed(th6);
        }
        throw th;
    }
}
