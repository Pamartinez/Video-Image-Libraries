package S7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterLayoutHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemasterLayoutHandler e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ c(RemasterLayoutHandler remasterLayoutHandler, boolean z, boolean z3, int i2) {
        this.d = i2;
        this.e = remasterLayoutHandler;
        this.f = z;
        this.g = z3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateLayoutComponent$5(this.f, this.g);
                return;
            default:
                this.e.lambda$updateLayout$4(this.f, this.g);
                return;
        }
    }
}
