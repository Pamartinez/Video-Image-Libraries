package com.samsung.android.app.sdk.deepsky.objectcapture.video;

import A.a;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.objectcapture.common.Rune;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import srib.vizinsight.dvs.DVS;
import srib.vizinsight.dvs.DVSConfig;
import srib.vizinsight.dvs.DVSegmenter;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0003J\r\u0010\u0017\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u0003R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010$\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b$\u0010\u001c\u001a\u0004\b$\u0010\u0006\"\u0004\b%\u0010\u000e¨\u0006'"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;", "", "<init>", "()V", "", "supportVideoClipper", "()Z", "Lsrib/vizinsight/dvs/DVS;", "segment", "isDetectedBoxes", "(Lsrib/vizinsight/dvs/DVS;)Z", "isAnimated", "Lme/x;", "updateAnimatedBitmapInfo", "(Z)V", "Landroid/graphics/Bitmap;", "bitmap", "", "x", "y", "updateClippedImageInformation", "(Landroid/graphics/Bitmap;FF)V", "abort", "finish", "Lsrib/vizinsight/dvs/DVSConfig;", "dvsConfig", "Lsrib/vizinsight/dvs/DVSConfig;", "isAnimatedBitmap", "Z", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoData;", "videoData", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoData;", "getVideoData", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoData;", "setVideoData", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoData;)V", "isSupportedPoint", "setSupportedPoint", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoClipper {
    public static final Companion Companion = new Companion((e) null);
    public static final String NOT_SUPPORTED_MODEL = "";
    public static final int SELECT_ALL_POSITION = -1;
    public static final String TAG = "VideoClipper";
    private final DVSConfig dvsConfig;
    private boolean isAnimatedBitmap;
    private boolean isSupportedPoint;
    private VideoData videoData;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper$Companion;", "", "<init>", "()V", "TAG", "", "NOT_SUPPORTED_MODEL", "SELECT_ALL_POSITION", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public VideoClipper() {
        DVSConfig dVSConfig = new DVSConfig();
        this.dvsConfig = dVSConfig;
        dVSConfig.setDetectThreshold(dVSConfig.getDetectThreshold());
        dVSConfig.setQualityThreshold(dVSConfig.getQualityThreshold());
        dVSConfig.setSegmentThreshold(dVSConfig.getSegmentThreshold());
        dVSConfig.setMaxPass(dVSConfig.getMaxPass());
    }

    private final boolean isDetectedBoxes(DVS dvs) {
        Bitmap bitmap;
        VideoData videoData2;
        PointF point;
        VideoData videoData3 = this.videoData;
        if (videoData3 == null || (bitmap = videoData3.getBitmap()) == null || (videoData2 = this.videoData) == null || (point = videoData2.getPoint()) == null) {
            return false;
        }
        Log.i("VideoClipper", a.d((int) point.x, (int) point.y, "isDetectedBoxes : [", " , ", "]"));
        return dvs.DVSDetectCheck(bitmap, (int) point.x, (int) point.y, bitmap.getWidth(), bitmap.getHeight());
    }

    private final boolean supportVideoClipper() {
        boolean z = false;
        if (!this.isAnimatedBitmap) {
            Log.w("VideoClipper", "this bitmap is not animated bitmap.");
            return false;
        }
        Rune rune = Rune.INSTANCE;
        if (!rune.getSUPPORT_VIDEO_CLIPPER()) {
            Log.w("VideoClipper", "videoClipper not support native AI feature");
            return false;
        } else if (!rune.getSUPPORT_DETECT_VIDEO_CLIPPER()) {
            Log.w("VideoClipper", "videoClipper Model is not supported");
            return false;
        } else {
            boolean DVSCheckConfig = new DVS().DVSCheckConfig(this.dvsConfig);
            Log.i("VideoClipper", "supportVideoClipper : " + DVSCheckConfig);
            if (DVSCheckConfig) {
                DVS segmenter = DVSegmenter.getSegmenter(this.dvsConfig);
                j.b(segmenter);
                z = isDetectedBoxes(segmenter);
                DVSegmenter.releaseSegmenter();
            }
            Log.i("VideoClipper", "shouldContainRectArea : " + z);
            return z;
        }
    }

    public final void abort() {
        DVSegmenter.abortSegmenter();
    }

    public final void finish() {
        Bitmap bitmap;
        VideoData videoData2 = this.videoData;
        if (!(videoData2 == null || (bitmap = videoData2.getBitmap()) == null)) {
            bitmap.recycle();
        }
        this.videoData = null;
        this.isAnimatedBitmap = false;
    }

    public final VideoData getVideoData() {
        return this.videoData;
    }

    public final boolean isSupportedPoint() {
        return this.isSupportedPoint;
    }

    public final void setSupportedPoint(boolean z) {
        this.isSupportedPoint = z;
    }

    public final void setVideoData(VideoData videoData2) {
        this.videoData = videoData2;
    }

    public final void updateAnimatedBitmapInfo(boolean z) {
        this.isAnimatedBitmap = z;
    }

    public final void updateClippedImageInformation(Bitmap bitmap, float f, float f5) {
        j.e(bitmap, "bitmap");
        this.videoData = new VideoData(bitmap, new PointF(f, f5));
        this.isSupportedPoint = supportVideoClipper();
    }
}
