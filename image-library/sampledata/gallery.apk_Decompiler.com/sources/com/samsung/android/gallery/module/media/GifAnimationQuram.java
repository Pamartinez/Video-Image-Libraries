package com.samsung.android.gallery.module.media;

import A.a;
import D7.g;
import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.media.quramsoft.AgifMovie;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GifAnimationQuram extends GifAnimation {
    private CanvasPool mCanvasPool;
    /* access modifiers changed from: private */
    public int mFrameIndex;
    /* access modifiers changed from: private */
    public AgifMovie mMovie;
    /* access modifiers changed from: private */
    public Timer mTimer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FrameTask extends TimerTask {
        private final GifAnimationQuram mAnimQuram;

        public FrameTask(GifAnimationQuram gifAnimationQuram) {
            this.mAnimQuram = gifAnimationQuram;
        }

        public void run() {
            GifAnimation.AnimationFrameStartListener animationFrameStartListener;
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mAnimQuram.mMovie == null) {
                Log.e(this.mAnimQuram.TAG, "mMovie is already released.");
                return;
            }
            this.mAnimQuram.onDrawBitmap();
            if (this.mAnimQuram.mTimer != null) {
                try {
                    this.mAnimQuram.mTimer.schedule(new FrameTask(this.mAnimQuram), Math.max(15, ((long) this.mAnimQuram.mMovie.getFrameInterval()) - (System.currentTimeMillis() - currentTimeMillis)));
                } catch (Exception e) {
                    String tag = this.mAnimQuram.tag();
                    Log.e(tag, "FrameTask : failed " + this.mAnimQuram.mTimer + ", e=" + e.getMessage());
                    this.mAnimQuram.stop();
                }
            }
            GifAnimationQuram gifAnimationQuram = this.mAnimQuram;
            int a7 = gifAnimationQuram.mFrameIndex;
            gifAnimationQuram.mFrameIndex = a7 + 1;
            if (a7 == 0 && (animationFrameStartListener = this.mAnimQuram.mFrameStartListener) != null) {
                animationFrameStartListener.onAnimationFrameStarted();
            }
            GifAnimationQuram gifAnimationQuram2 = this.mAnimQuram;
            gifAnimationQuram2.mFrameIndex = gifAnimationQuram2.mFrameIndex % this.mAnimQuram.getNumOfFrame();
        }
    }

    /* access modifiers changed from: private */
    public int getNumOfFrame() {
        AgifMovie agifMovie = this.mMovie;
        if (agifMovie != null) {
            return agifMovie.getNumOfFrame();
        }
        return 1;
    }

    private boolean initialize() {
        AgifMovie agifMovie = this.mMovie;
        if (agifMovie != null) {
            if (agifMovie.getWidth() <= 0 || this.mMovie.getHeight() <= 0 || this.mMovie.getNumOfFrame() <= 1) {
                Log.d(this.TAG, "init fail :" + this.mMovie.getNumOfFrame(), this.mMovie.getWidth() + "x" + this.mMovie.getHeight());
            } else {
                this.mFrameIndex = 0;
                this.mCanvasPool = new CanvasPool(this.mMovie.getWidth(), this.mMovie.getHeight(), false);
                this.mBitmapOptions = new BitmapOptions(this.mMovie.getWidth(), this.mMovie.getHeight(), "image/gif");
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onDrawBitmap() {
        try {
            notifyListener(this.mCanvasPool.updateAndGet(this));
        } catch (Exception e) {
            a.s(e, new StringBuilder("onDrawBitmap failed e="), this.TAG);
        }
    }

    public boolean decodeByteArray(byte[] bArr, int i2, int i7) {
        this.mMovie = AgifMovie.of(bArr, bArr.length);
        return initialize();
    }

    public boolean decodeFile(String str) {
        this.mMovie = AgifMovie.of(str);
        return initialize();
    }

    public boolean decodeStream(InputStream inputStream) {
        this.mMovie = AgifMovie.of(inputStream);
        return initialize();
    }

    public boolean decodeUri(Context context, Uri uri) {
        InputStream openInputStream;
        try {
            openInputStream = context.getContentResolver().openInputStream(uri);
            boolean decodeStream = decodeStream(openInputStream);
            if (openInputStream == null) {
                return decodeStream;
            }
            openInputStream.close();
            return decodeStream;
        } catch (IOException e) {
            j.r(e, new StringBuilder("decodeUri failed e="), this.TAG);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void drawFrame(Bitmap bitmap, Canvas canvas) {
        AgifMovie agifMovie = this.mMovie;
        if (agifMovie != null && bitmap != null) {
            agifMovie.decodeFrame(bitmap);
        }
    }

    public boolean isAnimation() {
        AgifMovie agifMovie = this.mMovie;
        if (agifMovie == null || agifMovie.getNumOfFrame() <= 1) {
            return false;
        }
        return true;
    }

    public void release() {
        super.release();
        AgifMovie agifMovie = this.mMovie;
        this.mMovie = null;
        if (agifMovie != null) {
            ThreadUtil.postOnNewBgThread(new g(10, agifMovie), "FinMov");
        }
        this.mCanvasPool = null;
        this.mFrameIndex = 0;
    }

    public void start() {
        if (!isAnimation() || this.mTimer != null || this.mMovie == null) {
            String str = this.TAG;
            Log.e(str, "start skipped " + this.mTimer);
            return;
        }
        try {
            Timer timer = new Timer("movie");
            this.mTimer = timer;
            timer.schedule(new FrameTask(this), 0);
            String str2 = this.TAG;
            Log.d(str2, "startMovie duration=" + this.mMovie.getDuration() + ", frames=" + this.mMovie.getFrameCount());
        } catch (Exception e) {
            String str3 = this.TAG;
            Log.e(str3, "startMovie failed " + this.mTimer + ", e=" + e.getMessage());
            stop();
        }
    }

    public void stop() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    public String tag() {
        return "GifAnimationQuram";
    }

    public String toString() {
        String str;
        AgifMovie agifMovie = this.mMovie;
        if (agifMovie != null) {
            return agifMovie.toString();
        }
        StringBuilder sb2 = new StringBuilder("AGIF(");
        if (Build.VERSION.SDK_INT >= 36) {
            str = "AgifMovieSemImpl";
        } else {
            str = "AgifMovieQrmImpl";
        }
        return C0212a.p(sb2, str, "){null}");
    }
}
