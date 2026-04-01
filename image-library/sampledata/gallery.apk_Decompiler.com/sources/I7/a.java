package I7;

import com.samsung.android.gallery.app.ui.viewer2.crop.CropViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CropViewFragment e;

    public /* synthetic */ a(CropViewFragment cropViewFragment, int i2) {
        this.d = i2;
        this.e = cropViewFragment;
    }

    public final void run() {
        int i2 = this.d;
        CropViewFragment cropViewFragment = this.e;
        switch (i2) {
            case 0:
                cropViewFragment.lambda$initPreview$0();
                return;
            default:
                cropViewFragment.updateViewVisibility();
                return;
        }
    }
}
