package m5;

import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryPresenter;

/* renamed from: m5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0486a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchMyQueryPresenter e;

    public /* synthetic */ C0486a(SearchMyQueryPresenter searchMyQueryPresenter, int i2) {
        this.d = i2;
        this.e = searchMyQueryPresenter;
    }

    public final void run() {
        int i2 = this.d;
        SearchMyQueryPresenter searchMyQueryPresenter = this.e;
        switch (i2) {
            case 0:
                searchMyQueryPresenter.lambda$saveMyQueryChange$2();
                return;
            default:
                searchMyQueryPresenter.lambda$saveMyQueryChange$1();
                return;
        }
    }
}
