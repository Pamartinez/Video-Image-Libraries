package Ca;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Consumer e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ d(int i2, MediaItem mediaItem, Consumer consumer) {
        this.d = i2;
        this.e = consumer;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                PanoramaActivity.lambda$loadBitmap$4(this.f, this.e);
                return;
            case 1:
                this.e.accept(this.f);
                return;
            default:
                this.e.accept(this.f);
                return;
        }
    }

    public /* synthetic */ d(MediaItem mediaItem, Consumer consumer) {
        this.d = 0;
        this.f = mediaItem;
        this.e = consumer;
    }
}
