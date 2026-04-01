package u5;

import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;

/* renamed from: u5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0519a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PdcSearchDelegate e;

    public /* synthetic */ C0519a(PdcSearchDelegate pdcSearchDelegate, int i2) {
        this.d = i2;
        this.e = pdcSearchDelegate;
    }

    public final void run() {
        int i2 = this.d;
        PdcSearchDelegate pdcSearchDelegate = this.e;
        switch (i2) {
            case 0:
                pdcSearchDelegate.lambda$updateListData$6();
                return;
            case 1:
                pdcSearchDelegate.addRelationship();
                return;
            default:
                pdcSearchDelegate.moveToNextRelationship();
                return;
        }
    }
}
