package D3;

import android.content.Intent;
import com.samsung.android.gallery.app.activity.external.PickerSelectionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PickerSelectionHandler e;
    public final /* synthetic */ Intent f;

    public /* synthetic */ h(PickerSelectionHandler pickerSelectionHandler, Intent intent, int i2) {
        this.d = i2;
        this.e = pickerSelectionHandler;
        this.f = intent;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setResultAndFinish$4(this.f);
                return;
            default:
                this.e.lambda$setResultAndFinish$2(this.f);
                return;
        }
    }
}
