package Ba;

import android.content.DialogInterface;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                dialogInterface.dismiss();
                return;
            case 1:
                dialogInterface.dismiss();
                return;
            case 2:
                dialogInterface.dismiss();
                return;
            case 3:
                dialogInterface.dismiss();
                return;
            case 4:
                Log.e("LocationPermissionUtil", "showLocationSettingDialog : Cancel. Do nothing");
                return;
            case 5:
                dialogInterface.dismiss();
                return;
            case 6:
                dialogInterface.dismiss();
                return;
            default:
                dialogInterface.dismiss();
                return;
        }
    }
}
