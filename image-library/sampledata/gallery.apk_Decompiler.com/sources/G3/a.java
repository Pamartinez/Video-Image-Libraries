package G3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.ChangeCoverCmd;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ChangeCoverCmd e;

    public /* synthetic */ a(ChangeCoverCmd changeCoverCmd, int i2) {
        this.d = i2;
        this.e = changeCoverCmd;
    }

    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        ChangeCoverCmd changeCoverCmd = this.e;
        switch (i2) {
            case 0:
                changeCoverCmd.changeCoverFromPickerForCrop(eventContext, arrayList);
                return;
            default:
                changeCoverCmd.changeCoverFromCropper(eventContext, arrayList);
                return;
        }
    }
}
