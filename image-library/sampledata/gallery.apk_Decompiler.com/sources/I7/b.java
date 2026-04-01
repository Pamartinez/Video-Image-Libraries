package I7;

import com.samsung.android.gallery.app.ui.viewer2.crop.CropViewPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CropViewPresenter e;

    public /* synthetic */ b(CropViewPresenter cropViewPresenter, int i2) {
        this.d = i2;
        this.e = cropViewPresenter;
    }

    public final void run() {
        int i2 = this.d;
        CropViewPresenter cropViewPresenter = this.e;
        switch (i2) {
            case 0:
                cropViewPresenter.playMovie();
                return;
            case 1:
                cropViewPresenter.lambda$saveCropImageFile$1();
                return;
            case 2:
                cropViewPresenter.lambda$saveCropImageFile$2();
                return;
            default:
                cropViewPresenter.onDataChangedOnUi();
                return;
        }
    }
}
