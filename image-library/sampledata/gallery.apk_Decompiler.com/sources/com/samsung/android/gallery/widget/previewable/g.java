package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerCompat e;

    public /* synthetic */ g(MediaPlayerCompat mediaPlayerCompat, int i2) {
        this.d = i2;
        this.e = mediaPlayerCompat;
    }

    public final void run() {
        int i2 = this.d;
        MediaPlayerCompat mediaPlayerCompat = this.e;
        switch (i2) {
            case 0:
                PreviewHdrVideo.VideoViewPlayer.lambda$pause$1(mediaPlayerCompat);
                return;
            default:
                PreviewHdrVideo.VideoViewPlayer.lambda$resume$0(mediaPlayerCompat);
                return;
        }
    }
}
