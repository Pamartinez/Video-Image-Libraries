package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements MediaPlayerCompat.OnInfoListener, MediaPlayerCompat.OnErrorListener, MediaPlayerCompat.OnCompletionListener, MediaPlayerCompat.OnPreparedListener {
    public final /* synthetic */ PreviewVideo d;

    public /* synthetic */ l(PreviewVideo previewVideo) {
        this.d = previewVideo;
    }

    public void onCompletion(MediaPlayerCompat mediaPlayerCompat) {
        this.d.onCompletion(mediaPlayerCompat);
    }

    public boolean onError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        return this.d.onError(mediaPlayerCompat, i2, i7);
    }

    public boolean onInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        return this.d.onVideoInfo(mediaPlayerCompat, i2, i7);
    }

    public void onPrepared(MediaPlayerCompat mediaPlayerCompat) {
        this.d.onPrepared(mediaPlayerCompat);
    }
}
