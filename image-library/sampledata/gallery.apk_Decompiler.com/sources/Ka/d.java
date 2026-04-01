package Ka;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Blackboard e;
    public final /* synthetic */ SubscriberInfo f;

    public /* synthetic */ d(Blackboard blackboard, SubscriberInfo subscriberInfo, int i2) {
        this.d = i2;
        this.e = blackboard;
        this.f = subscriberInfo;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.notify(this.f.getKey(), this.e.read(this.f.getKey()), this.f.getSubscriber());
                return;
            default:
                this.e.notify(this.f.getKey(), this.e.read(this.f.getKey()), this.f.getSubscriber());
                return;
        }
    }
}
