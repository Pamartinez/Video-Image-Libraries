package O3;

import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ ChangeLocationCmd e;
    public final /* synthetic */ MediaItem[] f;
    public final /* synthetic */ double g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ double f2408h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f2409i;

    public /* synthetic */ g(ChangeLocationCmd changeLocationCmd, MediaItem[] mediaItemArr, double d2, double d3, String str) {
        this.e = changeLocationCmd;
        this.f = mediaItemArr;
        this.g = d2;
        this.f2408h = d3;
        this.f2409i = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$changeLocation$3(this.f, this.f2409i, this.g, this.f2408h);
                return;
            default:
                this.e.lambda$changeLocationAsync$5(this.f, this.g, this.f2408h, this.f2409i);
                return;
        }
    }

    public /* synthetic */ g(ChangeLocationCmd changeLocationCmd, MediaItem[] mediaItemArr, String str, double d2, double d3) {
        this.e = changeLocationCmd;
        this.f = mediaItemArr;
        this.f2409i = str;
        this.g = d2;
        this.f2408h = d3;
    }
}
