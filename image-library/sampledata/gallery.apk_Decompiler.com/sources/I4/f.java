package I4;

import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MxAlbumsPresenter e;

    public /* synthetic */ f(MxAlbumsPresenter mxAlbumsPresenter, int i2) {
        this.d = i2;
        this.e = mxAlbumsPresenter;
    }

    public final void run() {
        int i2 = this.d;
        MxAlbumsPresenter mxAlbumsPresenter = this.e;
        switch (i2) {
            case 0:
                mxAlbumsPresenter.updateTipCardPreference();
                return;
            default:
                mxAlbumsPresenter.lambda$onViewResume$1();
                return;
        }
    }
}
