package q5;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNamePresenter e;

    public /* synthetic */ g(EditCreatureNamePresenter editCreatureNamePresenter, int i2) {
        this.d = i2;
        this.e = editCreatureNamePresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        EditCreatureNamePresenter editCreatureNamePresenter = this.e;
        switch (i2) {
            case 0:
                editCreatureNamePresenter.lambda$createSubscriberList$3(obj, bundle);
                return;
            case 1:
                editCreatureNamePresenter.lambda$createSubscriberList$4(obj, bundle);
                return;
            case 2:
                editCreatureNamePresenter.lambda$createSubscriberList$5(obj, bundle);
                return;
            default:
                editCreatureNamePresenter.lambda$createGlobalSubscriberList$6(obj, bundle);
                return;
        }
    }
}
