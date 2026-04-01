package O3;

import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FixDateTimeCmd e;
    public final /* synthetic */ MediaItem[] f;
    public final /* synthetic */ Consumer g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2412h;

    public /* synthetic */ m(FixDateTimeCmd fixDateTimeCmd, MediaItem[] mediaItemArr, Consumer consumer, String str, int i2) {
        this.d = i2;
        this.e = fixDateTimeCmd;
        this.f = mediaItemArr;
        this.g = consumer;
        this.f2412h = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateDateTime$3(this.f, this.g, this.f2412h);
                return;
            default:
                this.e.lambda$updateDateTimeAsync$5(this.f, this.g, this.f2412h);
                return;
        }
    }
}
