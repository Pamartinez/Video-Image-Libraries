package ya;

import com.samsung.android.gallery.plugins.compare.CompareActivity;
import com.samsung.android.gallery.plugins.compare.CompareImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CompareImageView e;

    public /* synthetic */ c(CompareImageView compareImageView, int i2) {
        this.d = i2;
        this.e = compareImageView;
    }

    public final void run() {
        int i2 = this.d;
        CompareImageView compareImageView = this.e;
        switch (i2) {
            case 0:
                compareImageView.forceResetScaleAndCenter();
                return;
            default:
                CompareActivity.lambda$updateGridViews$12(compareImageView);
                return;
        }
    }
}
