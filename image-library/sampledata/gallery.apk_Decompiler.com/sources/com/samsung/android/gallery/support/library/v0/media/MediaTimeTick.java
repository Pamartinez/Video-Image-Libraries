package com.samsung.android.gallery.support.library.v0.media;

import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaTimeTick {
    private boolean mEnabled;
    /* access modifiers changed from: private */
    public final Runnable mTickListener;
    /* access modifiers changed from: private */
    public Timer mTimer;

    public MediaTimeTick(Runnable runnable) {
        this.mTickListener = runnable;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public MediaTimeTick setEnabled(boolean z) {
        this.mEnabled = z;
        return this;
    }

    public void start(int i2) {
        if (this.mEnabled && this.mTimer == null) {
            if (i2 > 1000) {
                i2 = 1000;
            } else if (i2 < 30) {
                i2 = 33;
            }
            Log.d("MediaTimeTick", "start {" + i2 + "}");
            Timer timer = new Timer("video-player");
            this.mTimer = timer;
            timer.schedule(new TimerTask() {
                public void run() {
                    if (MediaTimeTick.this.mTimer != null) {
                        MediaTimeTick.this.mTickListener.run();
                    } else {
                        Log.e("MediaTimeTick", "TimeTick canceled");
                    }
                }
            }, (long) i2, 30);
        }
    }

    public void stop() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTickListener.run();
            this.mTimer = null;
        }
    }
}
