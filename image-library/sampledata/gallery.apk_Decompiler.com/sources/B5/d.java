package B5;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewFragment;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchRelationshipPreviewFragment e;

    public /* synthetic */ d(SearchRelationshipPreviewFragment searchRelationshipPreviewFragment, int i2) {
        this.d = i2;
        this.e = searchRelationshipPreviewFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SearchRelationshipPreviewFragment searchRelationshipPreviewFragment = this.e;
        switch (i2) {
            case 0:
                searchRelationshipPreviewFragment.lambda$initRelationshipDelegate$2((PdcSearchDelegate) obj);
                return;
            case 1:
                searchRelationshipPreviewFragment.onSelectedFaceChanged((ArrayList) obj);
                return;
            default:
                searchRelationshipPreviewFragment.lambda$updateSetAsRelationMenu$4((MenuItem) obj);
                return;
        }
    }
}
