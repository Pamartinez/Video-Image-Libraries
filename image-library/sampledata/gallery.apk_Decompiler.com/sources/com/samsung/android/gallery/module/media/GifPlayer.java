package com.samsung.android.gallery.module.media;

import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.GifAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GifPlayer {
    private final CharSequence TAG = "GifPlayer";
    private GifAnimation mMovie;

    private boolean isMovieAvailable() {
        GifAnimation gifAnimation = this.mMovie;
        if (gifAnimation == null || !gifAnimation.isAnimation()) {
            return false;
        }
        return true;
    }

    public GifAnimation createMovie(Context context, MediaItem mediaItem, GifAnimation.AnimationFrameUpdateListener animationFrameUpdateListener, GifAnimation.AnimationFrameStartListener animationFrameStartListener) {
        return GifAnimationFactory.getDecodedGifAnimation(context, mediaItem).setAnimationFrameUpdateListener(animationFrameUpdateListener).setAnimationFrameStartListener(animationFrameStartListener);
    }

    public GifAnimation decodeMovie(Context context, MediaItem mediaItem, GifAnimation.AnimationFrameUpdateListener animationFrameUpdateListener, GifAnimation.AnimationFrameStartListener animationFrameStartListener) {
        GifAnimation createMovie = createMovie(context, mediaItem, animationFrameUpdateListener, animationFrameStartListener);
        this.mMovie = createMovie;
        return createMovie;
    }

    public boolean hasMovie() {
        if (this.mMovie != null) {
            return true;
        }
        return false;
    }

    public void releaseMovie() {
        GifAnimation gifAnimation = this.mMovie;
        if (gifAnimation != null) {
            gifAnimation.release();
            this.mMovie = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startMovie() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isMovieAvailable()     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x0013
            java.lang.CharSequence r0 = r2.TAG     // Catch:{ all -> 0x0011 }
            java.lang.String r1 = "startMovie skip"
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)     // Catch:{ all -> 0x0011 }
            monitor-exit(r2)
            return
        L_0x0011:
            r0 = move-exception
            goto L_0x001c
        L_0x0013:
            com.samsung.android.gallery.module.media.GifAnimation r0 = r2.mMovie     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x001a
            r0.start()     // Catch:{ all -> 0x0011 }
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.media.GifPlayer.startMovie():void");
    }

    public synchronized void stopMovie() {
        GifAnimation gifAnimation = this.mMovie;
        if (gifAnimation != null) {
            gifAnimation.stop();
        }
    }
}
