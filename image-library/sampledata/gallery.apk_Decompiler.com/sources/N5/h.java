package n5;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SuggestedCreatureSelectFragment e;

    public /* synthetic */ h(SuggestedCreatureSelectFragment suggestedCreatureSelectFragment, int i2) {
        this.d = i2;
        this.e = suggestedCreatureSelectFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SuggestedCreatureSelectFragment suggestedCreatureSelectFragment = this.e;
        MenuItem menuItem = (MenuItem) obj;
        switch (i2) {
            case 0:
                suggestedCreatureSelectFragment.lambda$updateSetAsRelationMenu$1(menuItem);
                return;
            default:
                suggestedCreatureSelectFragment.lambda$updateBottomMenuVisibility$0(menuItem);
                return;
        }
    }
}
