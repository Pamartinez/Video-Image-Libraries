package e4;

import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MvpBaseFragment e;

    public /* synthetic */ a(int i2, MvpBaseFragment mvpBaseFragment) {
        this.d = i2;
        this.e = mvpBaseFragment;
    }

    public final void run() {
        int i2 = this.d;
        MvpBaseFragment mvpBaseFragment = this.e;
        switch (i2) {
            case 0:
                mvpBaseFragment.lambda$onCreateAnimation$0();
                return;
            default:
                mvpBaseFragment.lambda$onCreateAnimation$1();
                return;
        }
    }
}
