package M3;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.controller.delegate.DatePickerDelegate;
import com.samsung.android.gallery.app.controller.externals.StartRemoteServerCmd;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* renamed from: M3.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0406b implements DialogInterface.OnCancelListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0406b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                DatePickerDelegate.onCancel((Blackboard) obj);
                return;
            case 1:
                ((StartRemoteServerCmd) obj).lambda$onExecute$5(dialogInterface);
                return;
            case 2:
                ((BottomTabMenu) obj).onCancel(dialogInterface);
                return;
            default:
                ((Runnable) obj).run();
                return;
        }
    }
}
