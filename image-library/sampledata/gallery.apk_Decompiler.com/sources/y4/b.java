package y4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SwitchableDialog e;

    public /* synthetic */ b(SwitchableDialog switchableDialog, int i2) {
        this.d = i2;
        this.e = switchableDialog;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SwitchableDialog switchableDialog = this.e;
        switch (i2) {
            case 0:
                switchableDialog.onOption1Clicked(view);
                return;
            case 1:
                switchableDialog.lambda$initCancelButton$3(view);
                return;
            default:
                switchableDialog.onOption2Clicked(view);
                return;
        }
    }
}
