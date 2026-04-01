package D3;

import android.os.Bundle;
import com.samsung.android.gallery.app.activity.external.PickerSelectionHandler;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PickerSelectionHandler e;

    public /* synthetic */ f(PickerSelectionHandler pickerSelectionHandler, int i2) {
        this.d = i2;
        this.e = pickerSelectionHandler;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        PickerSelectionHandler pickerSelectionHandler = this.e;
        switch (i2) {
            case 0:
                pickerSelectionHandler.onExecuteSingle(obj, bundle);
                return;
            default:
                pickerSelectionHandler.onExecuteMultiple(obj, bundle);
                return;
        }
    }
}
