package S7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterViewerHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemasterViewerHolder e;
    public final /* synthetic */ Object[] f;

    public /* synthetic */ k(RemasterViewerHolder remasterViewerHolder, Object[] objArr, int i2) {
        this.d = i2;
        this.e = remasterViewerHolder;
        this.f = objArr;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onUpdateBitmapPair$5(this.f);
                return;
            default:
                this.e.lambda$onBitmapLoaded$9(this.f);
                return;
        }
    }
}
