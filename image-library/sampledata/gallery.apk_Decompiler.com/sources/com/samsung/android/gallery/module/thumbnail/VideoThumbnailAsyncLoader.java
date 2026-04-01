package com.samsung.android.gallery.module.thumbnail;

import A4.M;
import N2.j;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import qa.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoThumbnailAsyncLoader implements SemAsyncVideoFrameDecoder.OnInitCompleteListener, SemAsyncVideoFrameDecoder.OnVideoFrameListener, SemAsyncVideoFrameDecoder.OnErrorListener, SemAsyncVideoFrameDecoder.OnDecodingCompleteListener {
    private static final Semaphore LOCK = new Semaphore(1);
    private static SemAsyncVideoFrameDecoder sDecoder;
    int duration;
    int mDeltaMax;
    ThumbnailInterface mItem;
    int mMode;
    private MediaHelper.VideoInfo mMotionPhotoVideoInfo;
    int mOffsetMs;
    int mOrientation;
    int mSeekType;
    long mStartTime;
    ArrayList<Integer> mTimeList;
    ArrayList<Integer> mTimeList2ndPhase = new ArrayList<>();
    Consumer<Boolean> onCompleted;
    List<Bitmap> outputList;
    int thumbSize;

    public VideoThumbnailAsyncLoader(int i2, Consumer<Boolean> consumer) {
        this.mMode = i2;
        int i7 = 2;
        if (!(i2 == 2 || i2 == 0)) {
            i7 = 3;
        }
        this.mSeekType = i7;
        this.onCompleted = consumer;
    }

    private void calculateAdaptiveMaxDelta() {
        int i2 = this.mOffsetMs;
        if (i2 > 30000) {
            this.mDeltaMax = i2 / 10;
        } else if (i2 > 10000) {
            this.mDeltaMax = 2000;
        } else if (i2 > 4000) {
            this.mDeltaMax = 1000;
        } else {
            this.mDeltaMax = Math.min(500, i2 / 2);
        }
    }

    private ArrayList<Integer> calculateTargetTimeList() {
        int size = this.outputList.size();
        this.mOffsetMs = getOffsetMs(this.duration, size);
        calculateAdaptiveMaxDelta();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(Integer.valueOf(this.mOffsetMs * i2));
        }
        return arrayList;
    }

    private void checkAndReserve2ndPhase(int i2, int i7, int i8) {
        int abs = Math.abs(i2 - i7);
        if (abs > this.mDeltaMax) {
            Log.i("VideoThumbAsyncLoader", "reserve 2nd phase", Integer.valueOf(i8), Integer.valueOf(abs), Integer.valueOf(this.mDeltaMax), Integer.valueOf(this.mOffsetMs));
            this.mTimeList2ndPhase.add(Integer.valueOf(i2));
        }
    }

    public static Bitmap getCropBitmap(ThumbnailInterface thumbnailInterface, int i2, Bitmap bitmap, int i7) {
        RectF rectF;
        if (bitmap == null || bitmap.isRecycled()) {
            return bitmap;
        }
        Rect rect = null;
        if (thumbnailInterface.isMotionPhoto()) {
            rectF = null;
        } else {
            rectF = thumbnailInterface.getCropRectRatio();
        }
        boolean z = true;
        if (RectUtils.isValidRect(rectF)) {
            rect = RectUtils.getSmartCropRect(bitmap, rectF, i7, true);
        }
        if (thumbnailInterface.isCreature() || thumbnailInterface.isPanoramic() || thumbnailInterface.isCustomCover()) {
            z = false;
        }
        return new BitmapOperator(bitmap).resize(i2).crop(rect).stretchable(z).rotate(i7).apply();
    }

    private int getHeight() {
        MediaHelper.VideoInfo videoInfo = this.mMotionPhotoVideoInfo;
        if (videoInfo != null) {
            return videoInfo.height;
        }
        return this.mItem.getHeight();
    }

    private static int getOffsetMs(int i2, int i7) {
        if (i2 > 1000) {
            return (i2 - 100) / i7;
        }
        return i2 / i7;
    }

    private int getWidth() {
        MediaHelper.VideoInfo videoInfo = this.mMotionPhotoVideoInfo;
        if (videoInfo != null) {
            return videoInfo.width;
        }
        return this.mItem.getWidth();
    }

    private boolean initDecoder(ThumbnailInterface thumbnailInterface) {
        FileInputStream fileInputStream;
        Throwable th;
        String mdeDownloadedPath = thumbnailInterface.getMdeDownloadedPath();
        if (mdeDownloadedPath == null) {
            Log.e((CharSequence) "VideoThumbAsyncLoader", "initDecoder failed : " + thumbnailInterface.getFileId(), Logger.getEncodedString(thumbnailInterface.getPath()));
            return false;
        }
        sDecoder = new SemAsyncVideoFrameDecoder();
        Log.i("VideoThumbAsyncLoader", "<<new SemAsyncVideoFrameDecoder : " + sDecoder);
        try {
            fileInputStream = new FileInputStream(mdeDownloadedPath);
            sDecoder.setOnInitCompleteListener(this);
            sDecoder.setOnDecodingCompleteListener(this);
            sDecoder.setOnVideoFrameListener(this);
            sDecoder.setOnErrorListener(this);
            Log.i("VideoThumbAsyncLoader", "<<setListeners");
            if (thumbnailInterface.isMotionPhoto()) {
                long[] videoStreamInfoFromMotionPhoto = MotionPhotoUtils.getVideoStreamInfoFromMotionPhoto(mdeDownloadedPath);
                if (videoStreamInfoFromMotionPhoto == null) {
                    fileInputStream.close();
                    return false;
                }
                this.mMotionPhotoVideoInfo = MediaHelper.getVideoInfo(fileInputStream, videoStreamInfoFromMotionPhoto[0], videoStreamInfoFromMotionPhoto[1]);
                sDecoder.init(fileInputStream.getFD(), videoStreamInfoFromMotionPhoto[0], videoStreamInfoFromMotionPhoto[1]);
                Log.i("VideoThumbAsyncLoader", "<<init motion photo : " + this.mMotionPhotoVideoInfo.width + "x" + this.mMotionPhotoVideoInfo.height + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mMotionPhotoVideoInfo.orientation);
                this.duration = this.mMotionPhotoVideoInfo.duration;
            } else {
                this.mMotionPhotoVideoInfo = null;
                sDecoder.init(fileInputStream.getFD());
                Log.i("VideoThumbAsyncLoader", "<<init");
                this.duration = VideoPropData.getVideoDuration(thumbnailInterface);
            }
            fileInputStream.close();
            return true;
        } catch (Exception e) {
            Exception exc = e;
            Log.e((CharSequence) "VideoThumbAsyncLoader", "initDecoder error : " + exc, Long.valueOf(thumbnailInterface.getFileId()), Logger.getEncodedString(thumbnailInterface.getPath()));
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private boolean is2ndPhase() {
        return !this.mTimeList2ndPhase.isEmpty();
    }

    private boolean isAdaptivePFrame1stPhase() {
        if (this.mSeekType == 3 || this.mMode != 2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$1() {
        this.onCompleted.accept(Boolean.FALSE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$2(int i2, int i7, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        Log.d("VideoThumbAsyncLoader", "OnError", Integer.valueOf(i2), Integer.valueOf(i7), semAsyncVideoFrameDecoder);
        releaseIfExist();
        Log.i("VideoThumbAsyncLoader", "<<release");
        ThreadUtil.runOnUiThread(new d(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run2ndPhase$0() {
        this.onCompleted.accept(Boolean.TRUE);
    }

    private boolean releaseIfExist() {
        if (sDecoder == null) {
            return true;
        }
        try {
            Semaphore semaphore = LOCK;
            if (semaphore.tryAcquire(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
                if (sDecoder != null) {
                    Log.e("VideoThumbAsyncLoader", "duplicate request : " + sDecoder);
                    sDecoder.release();
                    sDecoder = null;
                    Log.i("VideoThumbAsyncLoader", "<<release");
                }
                semaphore.release();
                return true;
            }
            Log.e("VideoThumbAsyncLoader", "fail get LOCK");
            return false;
        } catch (InterruptedException e) {
            Log.e((CharSequence) "VideoThumbAsyncLoader", "fail get LOCK 2", (Throwable) e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void run2ndPhase() {
        this.mStartTime = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder();
        C0086a.A(sb2, this.mTimeList2ndPhase, "/");
        Log.i("VideoThumbAsyncLoader", "run2ndPhase", j.g(sb2, this.mTimeList), Arrays.toString(this.mTimeList2ndPhase.toArray()));
        Collections.sort(this.mTimeList2ndPhase);
        this.mSeekType = 3;
        if (!initDecoder(this.mItem)) {
            Log.e("VideoThumbAsyncLoader", "run2ndPhase fail : " + sDecoder);
            ThreadUtil.runOnUiThread(new d(this, 1));
        }
    }

    private void setSize(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        int width = getWidth();
        int height = getHeight();
        if (width > 0 && height > 0) {
            float max = ((float) this.thumbSize) / ((float) Math.max(width, height));
            if (max < 1.0f) {
                int round = Math.round(((float) width) * max);
                int round2 = Math.round(((float) height) * max);
                semAsyncVideoFrameDecoder.setOutputImageSize(round, round2, true);
                Log.i("VideoThumbAsyncLoader", "<<setOutputImageSize : " + round + "x" + round2);
            }
        }
    }

    public boolean getThumbnail(ThumbnailInterface thumbnailInterface, List<Bitmap> list, int i2) {
        int i7;
        this.mStartTime = System.currentTimeMillis();
        this.mItem = thumbnailInterface;
        if (thumbnailInterface.isMotionPhoto()) {
            i7 = 0;
        } else {
            i7 = thumbnailInterface.getThumbnailOrientation();
        }
        this.mOrientation = i7;
        this.thumbSize = i2;
        this.outputList = list;
        if (!releaseIfExist()) {
            return false;
        }
        return initDecoder(thumbnailInterface);
    }

    public void onDecodingCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2) {
        Log.d("VideoThumbAsyncLoader", C0086a.i(i2, "onDecodingCompleted : "), Logger.vt(this.mStartTime));
        releaseIfExist();
        Log.i("VideoThumbAsyncLoader", "<<release : " + semAsyncVideoFrameDecoder);
        if (is2ndPhase()) {
            SimpleThreadPool.getInstance().execute(new d(this, 0));
        } else {
            this.onCompleted.accept(Boolean.TRUE);
        }
    }

    public boolean onError(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2, int i7) {
        SimpleThreadPool.getInstance().execute(new M((Object) this, i2, i7, (Object) semAsyncVideoFrameDecoder, 10));
        return false;
    }

    public void onInitCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        if (this.outputList.isEmpty()) {
            Log.e("VideoThumbAsyncLoader", "onInitCompleted outputList is cleared");
            return;
        }
        setSize(semAsyncVideoFrameDecoder);
        if (is2ndPhase()) {
            Log.i("VideoThumbAsyncLoader", "setTargetFrameTimeList 2ndPhase", Arrays.toString(this.mTimeList2ndPhase.toArray()));
            semAsyncVideoFrameDecoder.setTargetFrameTimeList(this.mTimeList2ndPhase);
            this.mTimeList2ndPhase.clear();
        } else {
            ArrayList<Integer> calculateTargetTimeList = calculateTargetTimeList();
            this.mTimeList = calculateTargetTimeList;
            Log.i("VideoThumbAsyncLoader", "setTargetFrameTimeList", Arrays.toString(calculateTargetTimeList.toArray()));
            semAsyncVideoFrameDecoder.setTargetFrameTimeList(this.mTimeList);
        }
        semAsyncVideoFrameDecoder.setSeekOption(this.mSeekType);
        Log.i("VideoThumbAsyncLoader", "setSeekOption : " + this.mSeekType);
        semAsyncVideoFrameDecoder.start();
        Log.i("VideoThumbAsyncLoader", "<<start");
    }

    public void onVideoFrame(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, Bitmap bitmap, int i2, int i7) {
        int indexOf = this.mTimeList.indexOf(Integer.valueOf(i2));
        if (isAdaptivePFrame1stPhase()) {
            checkAndReserve2ndPhase(i2, i7, indexOf);
        }
        if (indexOf < 0 || indexOf >= this.outputList.size()) {
            Log.e((CharSequence) "VideoThumbAsyncLoader", "onVideoFrame update fail", Integer.valueOf(indexOf), Integer.valueOf(i2), Arrays.toString(this.mTimeList.toArray()));
        } else {
            this.outputList.set(indexOf, getCropBitmap(this.mItem, this.thumbSize, bitmap, this.mOrientation));
        }
    }
}
