package A4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class E implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseListPresenter e;

    public /* synthetic */ E(int i2, BaseListPresenter baseListPresenter) {
        this.d = i2;
        this.e = baseListPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        BaseListPresenter baseListPresenter = this.e;
        switch (i2) {
            case 0:
                baseListPresenter.lambda$createGlobalSubscriberList$3(obj, bundle);
                return;
            case 1:
                baseListPresenter.operateClipboard(obj, bundle);
                return;
            case 2:
                baseListPresenter.lambda$createSubscriberList$0(obj, bundle);
                return;
            case 3:
                baseListPresenter.lambda$createSubscriberList$1(obj, bundle);
                return;
            case 4:
                baseListPresenter.lambda$createSubscriberList$2(obj, bundle);
                return;
            case 5:
                baseListPresenter.lambda$createGlobalSubscriberList$4(obj, bundle);
                return;
            case 6:
                baseListPresenter.onSelectionRequested(obj, bundle);
                return;
            case 7:
                baseListPresenter.startSelectionMode(obj, bundle);
                return;
            case 8:
                baseListPresenter.stopSelectionMode(obj, bundle);
                return;
            case 9:
                baseListPresenter.updateListViewBottomPadding(obj, bundle);
                return;
            default:
                baseListPresenter.onRequestBeamData(obj, bundle);
                return;
        }
    }
}
