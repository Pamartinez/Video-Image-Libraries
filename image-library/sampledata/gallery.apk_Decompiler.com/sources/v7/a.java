package V7;

import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SelectionViewFragment e;

    public /* synthetic */ a(SelectionViewFragment selectionViewFragment, int i2) {
        this.d = i2;
        this.e = selectionViewFragment;
    }

    public final void run() {
        int i2 = this.d;
        SelectionViewFragment selectionViewFragment = this.e;
        switch (i2) {
            case 0:
                selectionViewFragment.finish();
                return;
            case 1:
                selectionViewFragment.lambda$onSelected$8();
                return;
            case 2:
                selectionViewFragment.lambda$resetBurstShotViewCenterAlign$4();
                return;
            case 3:
                selectionViewFragment.lambda$new$1();
                return;
            case 4:
                selectionViewFragment.lambda$isNeedRetryBackPressed$5();
                return;
            case 5:
                selectionViewFragment.lambda$isNeedRetryBackPressed$6();
                return;
            default:
                selectionViewFragment.lambda$refreshFilmStripView$10();
                return;
        }
    }
}
