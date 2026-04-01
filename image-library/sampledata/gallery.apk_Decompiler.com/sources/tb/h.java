package tb;

import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.dialog.PlaceGdprDialog;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ PlaceGdprDialog e;

    public /* synthetic */ h(PlaceGdprDialog placeGdprDialog, int i2) {
        this.d = i2;
        this.e = placeGdprDialog;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        PlaceGdprDialog placeGdprDialog = this.e;
        switch (i2) {
            case 0:
                placeGdprDialog.lambda$updatePositiveButton$1((AlertDialog) obj);
                return;
            default:
                placeGdprDialog.lambda$initLinkText$0((TextView) obj);
                return;
        }
    }
}
