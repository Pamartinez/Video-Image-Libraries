package Ka;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SubscriberListener e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Bundle g;

    public /* synthetic */ a(SubscriberListener subscriberListener, Object obj, Bundle bundle, int i2) {
        this.d = i2;
        this.e = subscriberListener;
        this.f = obj;
        this.g = bundle;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.onNotify(this.f, this.g);
                return;
            case 1:
                this.e.onNotify(this.f, this.g);
                return;
            default:
                this.e.onNotify(this.f, this.g);
                return;
        }
    }
}
