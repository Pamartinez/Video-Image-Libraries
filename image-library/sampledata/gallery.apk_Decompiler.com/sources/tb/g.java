package tb;

import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.dialog.CustomPinPrompt;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnKeyListener {
    public final /* synthetic */ AlertDialog d;

    public /* synthetic */ g(AlertDialog alertDialog) {
        this.d = alertDialog;
    }

    public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
        return CustomPinPrompt.lambda$showDialog$2(this.d, view, i2, keyEvent);
    }
}
