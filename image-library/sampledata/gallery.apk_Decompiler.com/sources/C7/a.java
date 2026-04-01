package C7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.AbsGroupItemLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbsGroupItemLoader e;

    public /* synthetic */ a(AbsGroupItemLoader absGroupItemLoader, int i2) {
        this.d = i2;
        this.e = absGroupItemLoader;
    }

    public final void run() {
        int i2 = this.d;
        AbsGroupItemLoader absGroupItemLoader = this.e;
        switch (i2) {
            case 0:
                absGroupItemLoader.lambda$handleBlackboardEvent$0();
                return;
            default:
                absGroupItemLoader.lambda$onSubItemsUpdated$2();
                return;
        }
    }
}
