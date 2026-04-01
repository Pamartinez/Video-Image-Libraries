package x5;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.TopResultsClusterPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TopResultsClusterPresenter e;

    public /* synthetic */ c(TopResultsClusterPresenter topResultsClusterPresenter, int i2) {
        this.d = i2;
        this.e = topResultsClusterPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        TopResultsClusterPresenter topResultsClusterPresenter = this.e;
        switch (i2) {
            case 0:
                topResultsClusterPresenter.startSelectionMode(obj, bundle);
                return;
            default:
                topResultsClusterPresenter.stopSelectionMode(obj, bundle);
                return;
        }
    }
}
