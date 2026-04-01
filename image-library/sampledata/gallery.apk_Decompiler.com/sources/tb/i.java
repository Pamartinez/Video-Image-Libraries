package tb;

import android.content.DialogInterface;
import com.samsung.android.gallery.widget.dialog.PlaceGdprDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PlaceGdprDialog e;

    public /* synthetic */ i(PlaceGdprDialog placeGdprDialog, int i2) {
        this.d = i2;
        this.e = placeGdprDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        PlaceGdprDialog placeGdprDialog = this.e;
        switch (i7) {
            case 0:
                placeGdprDialog.onPositiveButtonClicked(dialogInterface, i2);
                return;
            default:
                placeGdprDialog.onNegativeButtonClicked(dialogInterface, i2);
                return;
        }
    }
}
