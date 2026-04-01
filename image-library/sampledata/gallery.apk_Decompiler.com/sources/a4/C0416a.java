package a4;

import android.content.DialogInterface;
import android.view.KeyEvent;
import com.samsung.android.gallery.app.remote.dlna.DlnaDisconnectDialog;
import com.samsung.android.gallery.app.ui.dialog.SimpleProgressDialog;
import com.samsung.android.gallery.app.ui.dialog.permission.PermissionRationaleDialog;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.dialog.ChinaGdprDialog;
import com.samsung.android.gallery.widget.dialog.PlaceGdprDialog;

/* renamed from: a4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0416a implements DialogInterface.OnKeyListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0416a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        int i7 = this.d;
        Object obj = this.e;
        switch (i7) {
            case 0:
                return ((DlnaDisconnectDialog) obj).onKeyClicked(dialogInterface, i2, keyEvent);
            case 1:
                return ((SimpleProgressDialog) obj).onKeyClicked(dialogInterface, i2, keyEvent);
            case 2:
                return ((BaseDialog) obj).onKeyClicked(dialogInterface, i2, keyEvent);
            case 3:
                return ((ChinaGdprDialog) obj).onKeyClicked(dialogInterface, i2, keyEvent);
            case 4:
                return ((PlaceGdprDialog) obj).onKeyClicked(dialogInterface, i2, keyEvent);
            default:
                return ((PermissionRationaleDialog) obj).onKeyClicked(dialogInterface, i2, keyEvent);
        }
    }
}
