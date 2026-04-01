package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerCompat e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ f(Object obj, MediaPlayerCompat mediaPlayerCompat, boolean z, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = mediaPlayerCompat;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((PreviewHdrVideo.VideoViewPlayer) this.g).lambda$setLooping$6(this.e, this.f);
                return;
            default:
                ((PreviewVideo) this.g).lambda$muteAudio$4(this.e, this.f);
                return;
        }
    }
}
