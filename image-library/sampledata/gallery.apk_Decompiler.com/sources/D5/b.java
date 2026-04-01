package D5;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegateV2;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item.FloatingItemDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FloatingRecommendationDelegateV2 e;

    public /* synthetic */ b(FloatingRecommendationDelegateV2 floatingRecommendationDelegateV2, int i2) {
        this.d = i2;
        this.e = floatingRecommendationDelegateV2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FloatingRecommendationDelegateV2 floatingRecommendationDelegateV2 = this.e;
        switch (i2) {
            case 0:
                floatingRecommendationDelegateV2.lambda$startShowAnimation$3((FloatingItemDelegate) obj);
                return;
            default:
                floatingRecommendationDelegateV2.lambda$prepareOptionsMenu$4((MenuItem) obj);
                return;
        }
    }
}
