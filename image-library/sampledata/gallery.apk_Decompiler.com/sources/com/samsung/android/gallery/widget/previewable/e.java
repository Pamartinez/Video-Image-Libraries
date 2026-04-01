package com.samsung.android.gallery.widget.previewable;

import android.graphics.SurfaceTexture;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;
import com.samsung.android.gallery.widget.previewable.PreviewVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((PreviewHdrVideo.VideoViewPlayer) this.e).lambda$stopPlayback$2((MediaPlayerCompat) this.f);
                return;
            default:
                ((PreviewVideo.AnonymousClass1) this.e).lambda$onSurfaceTextureAvailable$0((SurfaceTexture) this.f);
                return;
        }
    }
}
