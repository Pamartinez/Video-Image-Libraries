package K3;

import com.samsung.android.gallery.app.controller.creature.people.RelationshipPickerCmd;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ String[] e;

    public /* synthetic */ b(String[] strArr, int i2) {
        this.d = i2;
        this.e = strArr;
    }

    public final Object get() {
        int i2 = this.d;
        String[] strArr = this.e;
        switch (i2) {
            case 0:
                return RelationshipPickerCmd.lambda$createPersonData$3(strArr);
            case 1:
                return PdcRecommendDelegate.lambda$createPersonData$9(strArr);
            case 2:
                return SuggestedCreatureSelectPresenter.lambda$createPersonData$6(strArr);
            default:
                return PdcSearchDelegate.lambda$createPersonData$3(strArr);
        }
    }
}
