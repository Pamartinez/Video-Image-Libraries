package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GifPlayDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GifPlayDelegate e;

    public /* synthetic */ q(GifPlayDelegate gifPlayDelegate, int i2) {
        this.d = i2;
        this.e = gifPlayDelegate;
    }

    public final void run() {
        int i2 = this.d;
        GifPlayDelegate gifPlayDelegate = this.e;
        switch (i2) {
            case 0:
                gifPlayDelegate.lambda$play$3();
                return;
            default:
                gifPlayDelegate.lambda$play$1();
                return;
        }
    }
}
