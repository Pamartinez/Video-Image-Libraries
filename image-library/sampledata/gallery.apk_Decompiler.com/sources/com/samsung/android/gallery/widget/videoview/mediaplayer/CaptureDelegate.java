package com.samsung.android.gallery.widget.videoview.mediaplayer;

import Fa.C0569x;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import h.C0199b;
import i.C0212a;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CaptureDelegate {
    private static final boolean SUPPORT_CROP_VIDEO_CAPTURE = PreferenceFeatures.isEnabled(PreferenceFeatures.CropVideoCapture);
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private final IDelegateListener mDelegateListener;
    Bitmap mLastFrameCaptured;
    int mLastFramePosition = -1;
    private final MpViewThread mMpViewThread = new MpViewThread();
    private final IMediaPlayerInnerView mView;

    public CaptureDelegate(IMediaPlayerInnerView iMediaPlayerInnerView, IDelegateListener iDelegateListener) {
        this.mView = iMediaPlayerInnerView;
        this.mDelegateListener = iDelegateListener;
    }

    private Bitmap capturePlayback() {
        int i2;
        boolean z;
        MediaPlayerDelegate mediaPlayerDelegate = this.mDelegateListener.getMediaPlayerDelegate();
        if (this.mView.getPlayState() == PlayState.PAUSE || this.mView.getPlayState() == PlayState.COMPLETE) {
            long currentTimeMillis = System.currentTimeMillis();
            Bitmap[] bitmapArr = new Bitmap[2];
            new LatchBuilder("capturePlayback").setTimeout(300).addWorker(new C0199b(2, bitmapArr, mediaPlayerDelegate)).setCurrent(new C0199b(3, this, bitmapArr)).start();
            boolean z3 = false;
            Bitmap bitmap = bitmapArr[0];
            if (bitmap == null) {
                if (!MediaItemUtil.isHdr10OrHlgVideo(mediaPlayerDelegate.getMediaItem())) {
                    bitmap = bitmapArr[1];
                } else {
                    bitmap = null;
                }
            }
            if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
                i2 = 0;
            } else {
                i2 = bitmap.getPixel(0, 0);
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("capturePlayback");
            Integer valueOf = Integer.valueOf(mediaPlayerDelegate.getRenderingPosition());
            if (bitmapArr[0] != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z);
            if (bitmapArr[1] != null) {
                z3 = true;
            }
            C0212a.z(new Object[]{valueOf, valueOf2, Boolean.valueOf(z3), Integer.toHexString(i2), Logger.toSimpleString(bitmap), Long.valueOf(currentTimeMillis)}, sb2, stringCompat);
            if (bitmap == null || i2 == 0) {
                return null;
            }
            return bitmap;
        }
        return null;
    }

    private Pair<Integer, Integer> getImageXRange(int i2) {
        View view = this.mView.getView();
        float floatValue = ((Float) this.mDelegateListener.getScaleDelegate().getXRange(view.getScaleX()).second).floatValue();
        float scaleX = ((float) i2) / (view.getScaleX() * ((float) view.getWidth()));
        float f = -(view.getX() - floatValue);
        float width = getParentRect().width() + f;
        return new Pair<>(Integer.valueOf(Math.max(0, Math.min(i2, Math.round(f * scaleX)))), Integer.valueOf(Math.max(0, Math.min(i2, Math.round(scaleX * width)))));
    }

    private Pair<Integer, Integer> getImageYRange(int i2) {
        View view = this.mView.getView();
        float floatValue = ((Float) this.mDelegateListener.getScaleDelegate().getYRange(view.getScaleY()).second).floatValue();
        float scaleY = ((float) i2) / (view.getScaleY() * ((float) view.getHeight()));
        float f = -(view.getY() - floatValue);
        float height = getParentRect().height() + f;
        return new Pair<>(Integer.valueOf(Math.max(0, Math.min(i2, Math.round(f * scaleY)))), Integer.valueOf(Math.max(0, Math.min(i2, Math.round(scaleY * height)))));
    }

    private RectF getParentRect() {
        return this.mDelegateListener.getScaleDelegate().getParentRect();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$capturePlayback$1(Bitmap[] bitmapArr, MediaPlayerDelegate mediaPlayerDelegate) {
        bitmapArr[0] = mediaPlayerDelegate.getFrameBitmap(1920, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$capturePlayback$2(Bitmap[] bitmapArr) {
        bitmapArr[1] = this.mView.getViewBitmap();
    }

    public void captureFrame(Consumer<Bitmap> consumer, int i2, boolean z) {
        this.mMpViewThread.run(new C0569x((Consumer) consumer, i2, z, this));
    }

    /* renamed from: captureFrameInner */
    public Bitmap lambda$captureFrame$0(Consumer<Bitmap> consumer, int i2, boolean z) {
        Bitmap bitmap;
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap frameBitmap = this.mDelegateListener.getMediaPlayerDelegate().getFrameBitmap(i2);
        if (z) {
            bitmap = getCroppedBitmap(frameBitmap);
        } else {
            bitmap = frameBitmap;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "captureFrameInner {" + z + ArcCommonLog.TAG_COMMA + this.mDelegateListener.getMediaPlayerDelegate().getRenderingPosition() + "}, " + Logger.toSimpleString(frameBitmap) + " " + Logger.toSimpleString(bitmap) + " +" + (System.currentTimeMillis() - currentTimeMillis));
        if (consumer != null) {
            consumer.accept(bitmap);
        }
        return bitmap;
    }

    public Rect getCropRectOnImage(int i2, int i7) {
        Pair<Integer, Integer> imageXRange = getImageXRange(i2);
        Pair<Integer, Integer> imageYRange = getImageYRange(i7);
        return new Rect(((Integer) imageXRange.first).intValue(), ((Integer) imageYRange.first).intValue(), ((Integer) imageXRange.second).intValue(), ((Integer) imageYRange.second).intValue());
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        if (!SUPPORT_CROP_VIDEO_CAPTURE || bitmap == null) {
            return bitmap;
        }
        Rect cropRectOnImage = getCropRectOnImage(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, cropRectOnImage.left, cropRectOnImage.top, cropRectOnImage.width(), cropRectOnImage.height());
        if (bitmap != createBitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public Bitmap pauseAndCapture() {
        int i2;
        if (!this.mView.isInPlayState()) {
            return null;
        }
        if (this.mView.isPlaying()) {
            this.mView.pauseVideo();
        }
        int renderingPosition = this.mView.getRenderingPosition();
        Bitmap bitmap = this.mLastFrameCaptured;
        if (bitmap != null && this.mLastFramePosition == renderingPosition) {
            return bitmap;
        }
        Bitmap capturePlayback = capturePlayback();
        this.mLastFrameCaptured = capturePlayback;
        if (capturePlayback != null) {
            i2 = renderingPosition;
        } else {
            i2 = -1;
        }
        this.mLastFramePosition = i2;
        StringCompat stringCompat = this.TAG;
        C0212a.z(new Object[]{Integer.valueOf(renderingPosition), Logger.toSimpleString(capturePlayback)}, new StringBuilder("pauseAndCapture"), stringCompat);
        return capturePlayback;
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }

    public void setVideoCaptured(int i2, Bitmap bitmap) {
        this.mLastFrameCaptured = bitmap;
        this.mLastFramePosition = i2;
    }
}
