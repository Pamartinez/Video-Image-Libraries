package l4;

import com.samsung.android.gallery.app.ui.container.phone.BottomTabPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabPresenter e;

    public /* synthetic */ e(BottomTabPresenter bottomTabPresenter, int i2) {
        this.d = i2;
        this.e = bottomTabPresenter;
    }

    public final void run() {
        int i2 = this.d;
        BottomTabPresenter bottomTabPresenter = this.e;
        switch (i2) {
            case 0:
                bottomTabPresenter.lambda$onMoveExit$2();
                return;
            default:
                bottomTabPresenter.lambda$onSelectionEnabled$1();
                return;
        }
    }
}
