package k7;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.HdrContentsDelegate;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ HdrContentsDelegate e;

    public /* synthetic */ i(HdrContentsDelegate hdrContentsDelegate, int i2) {
        this.d = i2;
        this.e = hdrContentsDelegate;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        HdrContentsDelegate hdrContentsDelegate = this.e;
        switch (i2) {
            case 0:
                hdrContentsDelegate.lambda$createGlobalSubscriberList$0(obj, bundle);
                return;
            default:
                hdrContentsDelegate.lambda$createGlobalSubscriberList$1(obj, bundle);
                return;
        }
    }
}
