package tb;

import android.content.DialogInterface;
import com.samsung.android.gallery.widget.dialog.ChinaGdprDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ChinaGdprDialog e;

    public /* synthetic */ c(ChinaGdprDialog chinaGdprDialog, int i2) {
        this.d = i2;
        this.e = chinaGdprDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        ChinaGdprDialog chinaGdprDialog = this.e;
        switch (i7) {
            case 0:
                chinaGdprDialog.onPositiveButtonClicked(dialogInterface, i2);
                return;
            default:
                chinaGdprDialog.onNegativeButtonClicked(dialogInterface, i2);
                return;
        }
    }
}
