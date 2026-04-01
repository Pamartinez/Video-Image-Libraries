package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ i(Object obj, String str, int i2, int i7) {
        this.d = i7;
        this.g = obj;
        this.e = str;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                PreviewHdrVideo.VideoViewPlayer.lambda$applyFilter$4((MediaPlayerCompat) this.g, this.e, this.f);
                return;
            default:
                ((PreviewVideo) this.g).lambda$applyFilter$3(this.e, this.f);
                return;
        }
    }
}
