package w5;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterHeaderView;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: w5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0534a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchClusterHeaderView e;

    public /* synthetic */ C0534a(SearchClusterHeaderView searchClusterHeaderView, int i2) {
        this.d = i2;
        this.e = searchClusterHeaderView;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SearchClusterHeaderView searchClusterHeaderView = this.e;
        switch (i2) {
            case 0:
                searchClusterHeaderView.loadClusterData(obj, bundle);
                return;
            default:
                searchClusterHeaderView.loadTopResultData(obj, bundle);
                return;
        }
    }
}
