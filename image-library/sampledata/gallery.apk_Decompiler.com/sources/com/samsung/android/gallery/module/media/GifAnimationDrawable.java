package com.samsung.android.gallery.module.media;

import A.a;
import F9.c;
import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GifAnimationDrawable extends GifAnimation {
    private static final long MAX_FPS;
    private static final long MIN_FRAME_INTERVAL_MS;
    private static final Bitmap mDummyBitmap;
    private static final Canvas mDummyCanvas;
    protected AnimationCallback mAnimationCallback;
    private final Drawable.Callback mCallback = new Drawable.Callback() {
        public void invalidateDrawable(Drawable drawable) {
            GifAnimationDrawable.this.mLastScheduleTime = SystemClock.uptimeMillis();
            GifAnimationDrawable.this.onDrawBitmap();
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
            GifAnimationDrawable.this.mHandler.postAtTime(runnable, drawable, j2);
            GifAnimationDrawable.this.mLastScheduleTime = j2;
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            GifAnimationDrawable.this.mHandler.removeCallbacks(runnable, drawable);
            GifAnimationDrawable.this.mLastScheduleTime = Long.MAX_VALUE;
        }
    };
    private CanvasPool mCanvasPool;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public long mLastScheduleTime = Long.MAX_VALUE;
    protected long mLatestFrameTime = 0;
    protected AnimatedImageDrawable mMovie;

    static {
        long j2;
        if (PreferenceFeatures.SEP_GENERIC_DEVICE) {
            j2 = 100;
        } else {
            j2 = 60;
        }
        MAX_FPS = j2;
        MIN_FRAME_INTERVAL_MS = 1000 / j2;
        Bitmap createBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        mDummyBitmap = createBitmap;
        mDummyCanvas = new Canvas(createBitmap);
    }

    private Drawable decodeDrawableByteArray(byte[] bArr, int i2, int i7) {
        try {
            return ImageDecoder.decodeDrawable(ImageDecoder.createSource(ByteBuffer.wrap(bArr, i2, Math.min(52428800, i7))));
        } catch (IOException e) {
            j.r(e, new StringBuilder("decodeDrawableByteArray failed e="), this.TAG);
            return null;
        }
    }

    private Drawable decodeDrawableFile(String str) {
        try {
            return ImageDecoder.decodeDrawable(ImageDecoder.createSource(new File(str)));
        } catch (IOException e) {
            j.r(e, new StringBuilder("decodeDrawableFile failed e="), this.TAG);
            return null;
        }
    }

    private Drawable decodeDrawableUri(Context context, Uri uri) {
        try {
            return ImageDecoder.decodeDrawable(ImageDecoder.createSource(context.getContentResolver(), uri));
        } catch (IOException e) {
            j.r(e, new StringBuilder("decodeFile failed e="), this.TAG);
            return null;
        }
    }

    private void forceNextSchedule(long j2) {
        AnimatedImageDrawable animatedImageDrawable = this.mMovie;
        if (animatedImageDrawable != null) {
            this.mCallback.scheduleDrawable(animatedImageDrawable, new c(animatedImageDrawable, 0), j2 + 100);
        }
    }

    private boolean initialize() {
        if (this.mMovie == null) {
            return false;
        }
        this.mCanvasPool = new CanvasPool(getWidth(), getHeight());
        this.mBitmapOptions = new BitmapOptions(getWidth(), getHeight(), "image/gif");
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$stop$0(AnimatedImageDrawable animatedImageDrawable) {
        if (animatedImageDrawable != null) {
            animatedImageDrawable.stop();
        }
    }

    public boolean decodeByteArray(byte[] bArr, int i2, int i7) {
        Drawable decodeDrawableByteArray = decodeDrawableByteArray(bArr, i2, i7);
        if (decodeDrawableByteArray instanceof AnimatedImageDrawable) {
            this.mMovie = (AnimatedImageDrawable) decodeDrawableByteArray;
            return initialize();
        }
        String str = this.TAG;
        Log.e(str, "decodeByteArray failed " + this);
        return false;
    }

    public boolean decodeFile(String str) {
        Drawable decodeDrawableFile = decodeDrawableFile(str);
        if (decodeDrawableFile instanceof AnimatedImageDrawable) {
            this.mMovie = (AnimatedImageDrawable) decodeDrawableFile;
            return initialize();
        }
        String str2 = this.TAG;
        Log.e(str2, "decodeFile failed " + this);
        return false;
    }

    public boolean decodeUri(Context context, Uri uri) {
        Drawable decodeDrawableUri = decodeDrawableUri(context, uri);
        if (decodeDrawableUri instanceof AnimatedImageDrawable) {
            this.mMovie = (AnimatedImageDrawable) decodeDrawableUri;
            return initialize();
        }
        String str = this.TAG;
        Log.e(str, "decodeUri failed " + this);
        return false;
    }

    public void drawFrame(Bitmap bitmap, Canvas canvas) {
        try {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.mMovie.draw(canvas);
        } catch (NullPointerException e) {
            Log.e(this.TAG, e.getMessage());
        }
    }

    public AnimationCallback getAnimationCallback() {
        if (this.mAnimationCallback == null) {
            this.mAnimationCallback = new AnimationCallback();
        }
        return this.mAnimationCallback;
    }

    public int getHeight() {
        AnimatedImageDrawable animatedImageDrawable = this.mMovie;
        if (animatedImageDrawable != null) {
            return animatedImageDrawable.getIntrinsicHeight();
        }
        return 0;
    }

    public int getWidth() {
        AnimatedImageDrawable animatedImageDrawable = this.mMovie;
        if (animatedImageDrawable != null) {
            return animatedImageDrawable.getIntrinsicWidth();
        }
        return 0;
    }

    public boolean isAnimation() {
        if (this.mMovie != null) {
            return true;
        }
        return false;
    }

    public final void onDrawBitmap() {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis - this.mLatestFrameTime < MIN_FRAME_INTERVAL_MS) {
                skipFrame();
            } else {
                this.mLatestFrameTime = uptimeMillis;
                notifyListener(this.mCanvasPool.updateAndGet(this));
            }
            if (scheduleOnDraw()) {
                long uptimeMillis2 = SystemClock.uptimeMillis();
                if (this.mLastScheduleTime <= uptimeMillis2) {
                    Log.w(this.TAG, "forceNextSchedule");
                    forceNextSchedule(uptimeMillis2);
                }
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("onDrawBitmap failed e="), this.TAG);
        }
    }

    public void release() {
        super.release();
        this.mMovie = null;
        this.mCanvasPool = null;
        this.mLastScheduleTime = Long.MAX_VALUE;
    }

    public boolean scheduleOnDraw() {
        return true;
    }

    public void skipFrame() {
        drawFrame(mDummyBitmap, mDummyCanvas);
    }

    public void start() {
        if (!isAnimation()) {
            Log.e(this.TAG, "start skip ");
        } else {
            startInternal();
        }
    }

    public void startInternal() {
        AnimatedImageDrawable animatedImageDrawable = this.mMovie;
        if (animatedImageDrawable != null) {
            animatedImageDrawable.registerAnimationCallback(getAnimationCallback());
            this.mMovie.setRepeatCount(-1);
            this.mMovie.setCallback(this.mCallback);
            this.mMovie.start();
        }
    }

    public void stop() {
        ThreadUtil.postOnUiThread(new c(this.mMovie, 1));
        this.mHandler.removeCallbacksAndMessages(this.mMovie);
    }

    public String tag() {
        return "GifAnimationDrawable";
    }

    public String toString() {
        if (this.mMovie == null) {
            return "AGIF(D){null}";
        }
        return "AGIF(D){" + getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getHeight() + "}";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AnimationCallback extends Animatable2.AnimationCallback {
        public AnimationCallback() {
        }

        public void onAnimationStart(Drawable drawable) {
            GifAnimation.AnimationFrameStartListener animationFrameStartListener = GifAnimationDrawable.this.mFrameStartListener;
            if (animationFrameStartListener != null) {
                animationFrameStartListener.onAnimationFrameStarted();
            }
        }

        public void onAnimationEnd(Drawable drawable) {
        }
    }
}
