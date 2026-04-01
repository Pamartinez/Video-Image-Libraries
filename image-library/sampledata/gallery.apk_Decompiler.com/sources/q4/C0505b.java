package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.AppRatingDialog;

/* renamed from: q4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0505b implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AppRatingDialog e;

    public /* synthetic */ C0505b(AppRatingDialog appRatingDialog, int i2) {
        this.d = i2;
        this.e = appRatingDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        AppRatingDialog appRatingDialog = this.e;
        switch (i7) {
            case 0:
                appRatingDialog.onNoThanksButtonClicked(dialogInterface, i2);
                return;
            default:
                appRatingDialog.onLaterButtonClicked(dialogInterface, i2);
                return;
        }
    }
}
