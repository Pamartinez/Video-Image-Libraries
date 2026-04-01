package w4;

import android.widget.CompoundButton;
import com.samsung.android.gallery.app.ui.dialog.hidescene.HideSceneSelectionDialog;

/* renamed from: w4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0532b implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ HideSceneSelectionDialog e;

    public /* synthetic */ C0532b(HideSceneSelectionDialog hideSceneSelectionDialog, int i2) {
        this.d = i2;
        this.e = hideSceneSelectionDialog;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i2 = this.d;
        HideSceneSelectionDialog hideSceneSelectionDialog = this.e;
        switch (i2) {
            case 0:
                hideSceneSelectionDialog.lambda$bindView$1(compoundButton, z);
                return;
            default:
                hideSceneSelectionDialog.lambda$bindView$2(compoundButton, z);
                return;
        }
    }
}
