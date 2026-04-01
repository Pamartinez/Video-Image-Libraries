package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GifPlayDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ GifPlayDelegate.PlayerHolder f;

    public /* synthetic */ a(GifPlayDelegate.PlayerHolder playerHolder, Bitmap bitmap) {
        this.d = 4;
        this.f = playerHolder;
        this.e = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((GifPlayDelegate) this.e).lambda$onFrameUpdate$6(this.f);
                return;
            case 1:
                ((GifPlayDelegate) this.e).lambda$stop$4(this.f);
                return;
            case 2:
                ((GifPlayDelegate) this.e).lambda$release$5(this.f);
                return;
            case 3:
                ((GifPlayDelegate) this.e).lambda$play$2(this.f);
                return;
            default:
                GifPlayDelegate.lambda$onFrameUpdate$7(this.f, (Bitmap) this.e);
                return;
        }
    }

    public /* synthetic */ a(GifPlayDelegate gifPlayDelegate, GifPlayDelegate.PlayerHolder playerHolder, int i2) {
        this.d = i2;
        this.e = gifPlayDelegate;
        this.f = playerHolder;
    }
}
