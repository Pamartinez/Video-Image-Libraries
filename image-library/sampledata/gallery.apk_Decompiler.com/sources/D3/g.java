package D3;

import android.content.Intent;
import com.samsung.android.gallery.app.activity.external.PickerSelectionHandler;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PickerSelectionHandler e;
    public final /* synthetic */ Intent f;
    public final /* synthetic */ MediaItem[] g;

    public /* synthetic */ g(PickerSelectionHandler pickerSelectionHandler, Intent intent, MediaItem[] mediaItemArr, int i2) {
        this.d = i2;
        this.e = pickerSelectionHandler;
        this.f = intent;
        this.g = mediaItemArr;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setResultAndFinish$3(this.f, this.g);
                return;
            default:
                this.e.lambda$setResultAndFinish$5(this.f, this.g);
                return;
        }
    }
}
