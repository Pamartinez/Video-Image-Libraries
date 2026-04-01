package g5;

import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ReorderDragManager e;

    public /* synthetic */ a(ReorderDragManager reorderDragManager, int i2) {
        this.d = i2;
        this.e = reorderDragManager;
    }

    public final void run() {
        int i2 = this.d;
        ReorderDragManager reorderDragManager = this.e;
        switch (i2) {
            case 0:
                reorderDragManager.lambda$reorderTo$8();
                return;
            default:
                reorderDragManager.lambda$getScrollEndRunnable$10();
                return;
        }
    }
}
