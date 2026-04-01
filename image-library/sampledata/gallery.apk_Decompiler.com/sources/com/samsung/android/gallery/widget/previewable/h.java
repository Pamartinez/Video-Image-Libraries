package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;

    public /* synthetic */ h(Object obj, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                PreviewHdrVideo.VideoViewPlayer.lambda$muteAudio$5((MediaPlayerCompat) this.f, this.e);
                return;
            default:
                ((PreviewVideo) this.f).lambda$setLooping$2(this.e);
                return;
        }
    }
}
