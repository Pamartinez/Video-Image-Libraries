package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PreviewVideo e;
    public final /* synthetic */ MediaPlayerCompat f;

    public /* synthetic */ k(PreviewVideo previewVideo, MediaPlayerCompat mediaPlayerCompat, int i2) {
        this.d = i2;
        this.e = previewVideo;
        this.f = mediaPlayerCompat;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$stopPreview$0(this.f);
                return;
            case 1:
                this.e.lambda$resumePlayer$6(this.f);
                return;
            default:
                this.e.lambda$pausePlayer$5(this.f);
                return;
        }
    }
}
