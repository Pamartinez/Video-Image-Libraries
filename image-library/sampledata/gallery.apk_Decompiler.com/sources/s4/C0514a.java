package s4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveSubscribedCreatureDialog;

/* renamed from: s4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0514a implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemoveSubscribedCreatureDialog e;

    public /* synthetic */ C0514a(RemoveSubscribedCreatureDialog removeSubscribedCreatureDialog, int i2) {
        this.d = i2;
        this.e = removeSubscribedCreatureDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        RemoveSubscribedCreatureDialog removeSubscribedCreatureDialog = this.e;
        switch (i7) {
            case 0:
                removeSubscribedCreatureDialog.onClickPositive(dialogInterface, i2);
                return;
            default:
                removeSubscribedCreatureDialog.onClickNegative(dialogInterface, i2);
                return;
        }
    }
}
