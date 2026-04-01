package D5;

import S1.e;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegateV2;
import q2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements e {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
        int i7 = this.d;
        Object obj = this.e;
        switch (i7) {
            case 0:
                ((FloatingRecommendationDelegateV2) obj).onAppbarOffsetChanged(appBarLayout, i2);
                return;
            default:
                u uVar = (u) obj;
                if (i2 == 0 || uVar.E != i2) {
                    uVar.E = i2;
                    uVar.k(appBarLayout, i2);
                    return;
                }
                return;
        }
    }
}
