package w4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.hidescene.HideSceneSelectionDialog;

/* renamed from: w4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0531a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ HideSceneSelectionDialog e;

    public /* synthetic */ C0531a(HideSceneSelectionDialog hideSceneSelectionDialog, int i2) {
        this.d = i2;
        this.e = hideSceneSelectionDialog;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        HideSceneSelectionDialog hideSceneSelectionDialog = this.e;
        switch (i2) {
            case 0:
                hideSceneSelectionDialog.onClickPositive(view);
                return;
            default:
                hideSceneSelectionDialog.onClickNegative(view);
                return;
        }
    }
}
