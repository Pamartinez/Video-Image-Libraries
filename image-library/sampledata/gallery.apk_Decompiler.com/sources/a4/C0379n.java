package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;

/* renamed from: A4.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0379n implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseListFragment e;

    public /* synthetic */ C0379n(BaseListFragment baseListFragment, int i2) {
        this.d = i2;
        this.e = baseListFragment;
    }

    public final void run() {
        int i2 = this.d;
        BaseListFragment baseListFragment = this.e;
        switch (i2) {
            case 0:
                baseListFragment.lambda$onViewCreated$8();
                return;
            case 1:
                baseListFragment.stopAutoDrag();
                return;
            case 2:
                baseListFragment.lambda$executePendingDataChange$5();
                return;
            default:
                baseListFragment.invalidateToolbar();
                return;
        }
    }
}
