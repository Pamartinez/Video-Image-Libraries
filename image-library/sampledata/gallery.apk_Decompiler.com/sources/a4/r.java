package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseListPresenter e;

    public /* synthetic */ r(int i2, BaseListPresenter baseListPresenter) {
        this.d = i2;
        this.e = baseListPresenter;
    }

    public final void run() {
        int i2 = this.d;
        BaseListPresenter baseListPresenter = this.e;
        switch (i2) {
            case 0:
                baseListPresenter.onDataPrepared();
                return;
            default:
                baseListPresenter.updateUsbStorageVolumeMenu();
                return;
        }
    }
}
