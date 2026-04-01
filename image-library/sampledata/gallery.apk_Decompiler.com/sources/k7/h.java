package k7;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DlnaDelegate;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DlnaDelegate e;

    public /* synthetic */ h(DlnaDelegate dlnaDelegate, int i2) {
        this.d = i2;
        this.e = dlnaDelegate;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        DlnaDelegate dlnaDelegate = this.e;
        switch (i2) {
            case 0:
                dlnaDelegate.onDisconnectDMRContent(obj, bundle);
                return;
            case 1:
                dlnaDelegate.lambda$createGlobalSubscriberList$0(obj, bundle);
                return;
            default:
                dlnaDelegate.lambda$createGlobalSubscriberList$1(obj, bundle);
                return;
        }
    }
}
