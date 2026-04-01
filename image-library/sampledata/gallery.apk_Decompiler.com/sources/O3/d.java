package O3;

import com.samsung.android.gallery.app.controller.internals.ChangeDateCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ ChangeDateCmd d;
    public final /* synthetic */ MediaItem[] e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2405h;

    public /* synthetic */ d(ChangeDateCmd changeDateCmd, MediaItem[] mediaItemArr, int i2, int i7, int i8) {
        this.d = changeDateCmd;
        this.e = mediaItemArr;
        this.f = i2;
        this.g = i7;
        this.f2405h = i8;
    }

    public final void run() {
        this.d.lambda$changeDate$1(this.e, this.f, this.g, this.f2405h);
    }
}
