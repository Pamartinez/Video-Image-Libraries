package H7;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.controller.delegate.DatePickerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoTipHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ o(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((InstantSlowMoTipHandler) obj).lambda$showInstantSlowMoTip$0(dialogInterface);
                return;
            case 1:
                DatePickerDelegate.onCancel((Blackboard) obj);
                return;
            default:
                ((DebugSmartCropRectInfo) obj).lambda$showDebugInfo$2(dialogInterface);
                return;
        }
    }
}
