package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GifPlayDelegate;
import com.samsung.android.gallery.module.media.GifPlayer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ GifPlayDelegate e;

    public /* synthetic */ o(GifPlayDelegate gifPlayDelegate, int i2) {
        this.d = i2;
        this.e = gifPlayDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        GifPlayDelegate gifPlayDelegate = this.e;
        switch (i2) {
            case 0:
                gifPlayDelegate.onKeepPause((Object[]) obj);
                return;
            case 1:
                gifPlayDelegate.lambda$addListenEvent$0((Object[]) obj);
                return;
            default:
                gifPlayDelegate.lambda$releaseInternal$9((GifPlayer) obj);
                return;
        }
    }
}
