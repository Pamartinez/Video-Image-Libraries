package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.ShareWithWebLinkDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShareWithWebLinkDialog e;

    public /* synthetic */ q(ShareWithWebLinkDialog shareWithWebLinkDialog, int i2) {
        this.d = i2;
        this.e = shareWithWebLinkDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        ShareWithWebLinkDialog shareWithWebLinkDialog = this.e;
        switch (i7) {
            case 0:
                shareWithWebLinkDialog.lambda$createDialog$0(dialogInterface, i2);
                return;
            default:
                shareWithWebLinkDialog.lambda$createDialog$1(dialogInterface, i2);
                return;
        }
    }
}
