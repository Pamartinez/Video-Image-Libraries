package com.samsung.android.gallery.module.media;

import A.a;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.module.media.GifAnimationDrawable;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GifStickerAnimationDrawable extends GifAnimationDrawable {
    /* access modifiers changed from: private */
    public Timer mTimer;

    public GifAnimationDrawable.AnimationCallback getAnimationCallback() {
        if (this.mAnimationCallback == null) {
            this.mAnimationCallback = new GifAnimationDrawable.AnimationCallback() {
                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onAnimationEnd$0() {
                    AnimatedImageDrawable animatedImageDrawable;
                    if (GifStickerAnimationDrawable.this.mTimer != null && (animatedImageDrawable = GifStickerAnimationDrawable.this.mMovie) != null) {
                        animatedImageDrawable.start();
                    }
                }

                public void onAnimationEnd(Drawable drawable) {
                    ThreadUtil.postOnUiThreadDelayed(new a(this), 100);
                }
            };
        }
        return this.mAnimationCallback;
    }

    public boolean scheduleOnDraw() {
        return false;
    }

    public void start() {
        if (!isAnimation() || this.mTimer != null) {
            String str = this.TAG;
            Log.e(str, "start skip " + this.mTimer);
            return;
        }
        Timer timer = new Timer("movie");
        this.mTimer = timer;
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    GifStickerAnimationDrawable.this.onDrawBitmap();
                } catch (Exception e) {
                    a.s(e, new StringBuilder("timer#run failed e="), GifStickerAnimationDrawable.this.TAG);
                }
            }
        }, 0, 100);
        super.start();
    }

    public void startInternal() {
        AnimatedImageDrawable animatedImageDrawable;
        if (this.mTimer != null && (animatedImageDrawable = this.mMovie) != null) {
            animatedImageDrawable.registerAnimationCallback(getAnimationCallback());
            this.mMovie.setRepeatCount(0);
            this.mMovie.start();
        }
    }

    public void stop() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
        super.stop();
    }

    public String tag() {
        return "GifStickerAnimationDrawable";
    }
}
