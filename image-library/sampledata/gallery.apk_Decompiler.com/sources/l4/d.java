package l4;

import android.os.Bundle;
import com.samsung.android.gallery.module.badge.UsbBadgeManager;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements SubscriberListener {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        switch (this.d) {
            case 0:
                UsbBadgeManager.updateWithBadge();
                return;
            default:
                UsbBadgeManager.updateWithBadge();
                return;
        }
    }
}
