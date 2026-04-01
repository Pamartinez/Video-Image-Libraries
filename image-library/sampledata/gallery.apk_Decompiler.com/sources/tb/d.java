package tb;

import android.view.View;
import com.samsung.android.gallery.widget.dialog.ChinaGdprDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ChinaGdprDialog e;

    public /* synthetic */ d(ChinaGdprDialog chinaGdprDialog, int i2) {
        this.d = i2;
        this.e = chinaGdprDialog;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        ChinaGdprDialog chinaGdprDialog = this.e;
        switch (i2) {
            case 0:
                chinaGdprDialog.onCheckBoxAllClicked(view);
                return;
            default:
                chinaGdprDialog.onLinkClicked(view);
                return;
        }
    }
}
