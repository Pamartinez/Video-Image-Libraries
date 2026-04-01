package O3;

import com.samsung.android.gallery.app.controller.internals.ChangeDateCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ ChangeDateCmd d;
    public final /* synthetic */ MediaItem[] e;
    public final /* synthetic */ String f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2406h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2407i;

    public /* synthetic */ e(ChangeDateCmd changeDateCmd, MediaItem[] mediaItemArr, String str, int i2, int i7, int i8) {
        this.d = changeDateCmd;
        this.e = mediaItemArr;
        this.f = str;
        this.g = i2;
        this.f2406h = i7;
        this.f2407i = i8;
    }

    public final void run() {
        this.d.lambda$changeDateAsync$4(this.e, this.f, this.g, this.f2406h, this.f2407i);
    }
}
