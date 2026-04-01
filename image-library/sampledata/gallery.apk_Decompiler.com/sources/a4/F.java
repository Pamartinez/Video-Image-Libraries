package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ IBaseListView e;

    public /* synthetic */ F(IBaseListView iBaseListView, int i2) {
        this.d = i2;
        this.e = iBaseListView;
    }

    public final void run() {
        int i2 = this.d;
        IBaseListView iBaseListView = this.e;
        switch (i2) {
            case 0:
                iBaseListView.clearAdvancedMouseFocus();
                return;
            default:
                iBaseListView.invalidateOptionsMenu();
                return;
        }
    }
}
