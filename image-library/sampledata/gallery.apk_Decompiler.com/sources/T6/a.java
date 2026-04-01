package T6;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EventContext e;

    public /* synthetic */ a(EventContext eventContext, int i2) {
        this.d = i2;
        this.e = eventContext;
    }

    public final void run() {
        int i2 = this.d;
        EventContext eventContext = this.e;
        switch (i2) {
            case 0:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(1003));
                return;
            case 1:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(1003));
                return;
            case 2:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(1003));
                return;
            default:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(1003));
                return;
        }
    }
}
