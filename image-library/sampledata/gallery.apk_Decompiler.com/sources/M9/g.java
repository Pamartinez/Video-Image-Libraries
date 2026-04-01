package M9;

import android.os.Bundle;
import com.samsung.android.gallery.module.publisher.LifeCycleReLoader;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LifeCycleReLoader e;

    public /* synthetic */ g(LifeCycleReLoader lifeCycleReLoader, int i2) {
        this.d = i2;
        this.e = lifeCycleReLoader;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        LifeCycleReLoader lifeCycleReLoader = this.e;
        switch (i2) {
            case 0:
                lifeCycleReLoader.onPause(obj, bundle);
                return;
            case 1:
                lifeCycleReLoader.onResume(obj, bundle);
                return;
            default:
                lifeCycleReLoader.onDataChanged(obj, bundle);
                return;
        }
    }
}
