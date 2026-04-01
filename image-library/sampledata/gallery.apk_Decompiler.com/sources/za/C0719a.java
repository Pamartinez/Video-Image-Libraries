package za;

import com.samsung.android.gallery.plugins.filebrowser.BitmapLoader;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import java.util.function.Consumer;

/* renamed from: za.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0719a implements FutureListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BitmapLoader e;
    public final /* synthetic */ String f;
    public final /* synthetic */ Consumer g;

    public /* synthetic */ C0719a(BitmapLoader bitmapLoader, String str, Consumer consumer, int i2) {
        this.d = i2;
        this.e = bitmapLoader;
        this.f = str;
        this.g = consumer;
    }

    public final void onFutureDone(Future future) {
        switch (this.d) {
            case 0:
                this.e.lambda$decodeThumbnail$2(this.f, this.g, future);
                return;
            default:
                this.e.lambda$decodeThumbnail$5(this.f, this.g, future);
                return;
        }
    }
}
