package tb;

import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.dialog.ChinaGdprDialog;
import java.util.function.Consumer;

/* renamed from: tb.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0710b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ChinaGdprDialog e;

    public /* synthetic */ C0710b(ChinaGdprDialog chinaGdprDialog, int i2) {
        this.d = i2;
        this.e = chinaGdprDialog;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ChinaGdprDialog chinaGdprDialog = this.e;
        switch (i2) {
            case 0:
                chinaGdprDialog.lambda$updatePositiveButton$1((AlertDialog) obj);
                return;
            default:
                chinaGdprDialog.lambda$initLinkText$0((TextView) obj);
                return;
        }
    }
}
