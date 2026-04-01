package O3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RemoveFromResultCmd;
import com.samsung.android.gallery.support.search.SearchIndexListener;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements DataCollectionDelegate.OnDataCompletionListener, SearchIndexListener {
    public final /* synthetic */ RemoveFromResultCmd d;

    public /* synthetic */ s(RemoveFromResultCmd removeFromResultCmd) {
        this.d = removeFromResultCmd;
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        this.d.onSelectionCompleted(eventContext, arrayList);
    }

    public void onIndexComplete(int i2) {
        this.d.lambda$removeFromResult$0(i2);
    }
}
