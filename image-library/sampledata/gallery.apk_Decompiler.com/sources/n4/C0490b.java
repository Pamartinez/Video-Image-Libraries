package n4;

import com.samsung.android.gallery.app.ui.container.tablet.ListContainerManager;

/* renamed from: n4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0490b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListContainerManager e;

    public /* synthetic */ C0490b(ListContainerManager listContainerManager, int i2) {
        this.d = i2;
        this.e = listContainerManager;
    }

    public final void run() {
        int i2 = this.d;
        ListContainerManager listContainerManager = this.e;
        switch (i2) {
            case 0:
                listContainerManager.lambda$removeChildFragment$3();
                return;
            default:
                listContainerManager.lambda$switchFragment$2();
                return;
        }
    }
}
