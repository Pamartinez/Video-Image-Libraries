package b7;

import com.samsung.android.gallery.app.ui.map.search.SearchMapPresenter;

/* renamed from: b7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0430b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchMapPresenter e;

    public /* synthetic */ C0430b(SearchMapPresenter searchMapPresenter, int i2) {
        this.d = i2;
        this.e = searchMapPresenter;
    }

    public final void run() {
        int i2 = this.d;
        SearchMapPresenter searchMapPresenter = this.e;
        switch (i2) {
            case 0:
                searchMapPresenter.lambda$onDataChanged$0();
                return;
            default:
                searchMapPresenter.lambda$onDataChanged$1();
                return;
        }
    }
}
