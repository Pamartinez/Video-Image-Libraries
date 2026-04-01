package O3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.AddTagCmd;
import java.util.ArrayList;

/* renamed from: O3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0407a implements DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AddTagCmd e;

    public /* synthetic */ C0407a(AddTagCmd addTagCmd, int i2) {
        this.d = i2;
        this.e = addTagCmd;
    }

    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        AddTagCmd addTagCmd = this.e;
        switch (i2) {
            case 0:
                addTagCmd.onTagEditorResult(eventContext, arrayList);
                return;
            default:
                addTagCmd.addTag(eventContext, arrayList);
                return;
        }
    }
}
