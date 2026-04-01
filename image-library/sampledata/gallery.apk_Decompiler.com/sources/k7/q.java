package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SelectModeDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SelectModeDelegate e;

    public /* synthetic */ q(SelectModeDelegate selectModeDelegate, int i2) {
        this.d = i2;
        this.e = selectModeDelegate;
    }

    public final void run() {
        int i2 = this.d;
        SelectModeDelegate selectModeDelegate = this.e;
        switch (i2) {
            case 0:
                selectModeDelegate.lambda$onBindView$1();
                return;
            default:
                selectModeDelegate.updateSelectedCount();
                return;
        }
    }
}
