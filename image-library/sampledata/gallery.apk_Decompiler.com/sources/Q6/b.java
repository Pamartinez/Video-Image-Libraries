package q6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LastPageView e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ b(LastPageView lastPageView, boolean z, int i2) {
        this.d = i2;
        this.e = lastPageView;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$requestPreview$11(this.f);
                return;
            default:
                this.e.lambda$onMultiWindowModeChanged$5(this.f);
                return;
        }
    }
}
