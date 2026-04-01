package b6;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesPinModel e;

    public /* synthetic */ d(StoriesPinModel storiesPinModel, int i2) {
        this.d = i2;
        this.e = storiesPinModel;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        StoriesPinModel storiesPinModel = this.e;
        switch (i2) {
            case 0:
                storiesPinModel.onPinned(obj, bundle);
                return;
            default:
                storiesPinModel.onUnpinned(obj, bundle);
                return;
        }
    }
}
