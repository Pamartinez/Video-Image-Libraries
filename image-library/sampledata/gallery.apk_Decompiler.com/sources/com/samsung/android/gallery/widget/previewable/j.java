package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ j(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                PreviewHdrVideo.VideoViewPlayer.lambda$seekTo$3((MediaPlayerCompat) this.f, this.e);
                return;
            default:
                ((PreviewVideo) this.f).lambda$seekTo$1(this.e);
                return;
        }
    }
}
