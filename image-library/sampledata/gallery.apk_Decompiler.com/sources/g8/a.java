package g8;

import Fa.C0558l;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.samsung.android.gallery.widget.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Activity e;

    public /* synthetic */ a(Activity activity, int i2) {
        this.d = i2;
        this.e = activity;
    }

    public final void run() {
        int i2 = this.d;
        Activity activity = this.e;
        switch (i2) {
            case 0:
                activity.finish();
                return;
            default:
                new AlertDialog.Builder(activity).setTitle(R$string.setup_screen_lock_title).setMessage(R$string.setup_screen_lock_message).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R$string.go_to_settings, new C0558l(21, activity)).create().show();
                return;
        }
    }
}
