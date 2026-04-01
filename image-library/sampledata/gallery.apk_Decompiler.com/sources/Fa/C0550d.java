package Fa;

import android.os.Bundle;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: Fa.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0550d implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ IBaseView e;

    public /* synthetic */ C0550d(IBaseView iBaseView, int i2) {
        this.d = i2;
        this.e = iBaseView;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        IBaseView iBaseView = this.e;
        switch (i2) {
            case 0:
                iBaseView.onHandleEvent(obj, bundle);
                return;
            default:
                iBaseView.onHandleBroadcastEvent(obj, bundle);
                return;
        }
    }
}
